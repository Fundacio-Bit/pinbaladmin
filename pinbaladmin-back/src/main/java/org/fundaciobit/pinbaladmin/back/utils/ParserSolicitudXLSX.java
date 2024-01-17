package org.fundaciobit.pinbaladmin.back.utils;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author anadal
 *
 */
public class ParserSolicitudXLSX {

    protected static final Logger log = Logger.getLogger(ParserSolicitudXLSX.class);

    /**
     * 
     * @param plantillaXLSX
     * @param debug
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static SolicitudInfo extreureInfo(InputStream input_document, boolean debug) throws Exception {

        // convert it into a POI object
        XSSFWorkbook my_xlsx_workbook = null;

        final int MAX_FILES = 20;
        final int MAX_COLUMNES = 16;
        final int FIRST_DATA_ROW = 6;
        
        String [] darreraFila = new String[MAX_COLUMNES];
        try {

            // convert it into a POI object
            my_xlsx_workbook = new XSSFWorkbook(input_document);

            // Read excel sheet that needs to be updated
            XSSFSheet my_worksheet = my_xlsx_workbook.getSheetAt(1); // Segona Fulla

            final FormulaEvaluator evaluator = my_xlsx_workbook.getCreationHelper().createFormulaEvaluator();

            // CHECK QUE SIGUI DEL NOU MODEL !!!!!!
            // Miram que dins la cel·la E6 (row 5, col 4) hi hagi la paraula "Periodo" 
            final int PERIODO_COL_E = 4;
            String periodo = my_worksheet.getRow(5).getCell(PERIODO_COL_E).getStringCellValue();
            if (!"Periodo".equals(periodo.trim())) {
                throw new Exception("El format de la fulla excel no està actualitzat."
                        + " A la casella E6 hi hauria d'haver la paraula 'Periodo' i ha obtingut la paraula ]" + periodo + "[");
            }

            // ==========================================================
            // =============== DEPURACIO VOLCAT DE XLSX ===========================
            // ==========================================================

            SolicitudInfo info = new SolicitudInfo(my_worksheet.getRow(1).getCell(0).getStringCellValue());
            String [] filaExcel;
            
            Cell cell;
            for (int row = 0; row < MAX_FILES; row++) {
                
                filaExcel = new String[MAX_COLUMNES];

                XSSFRow therow = my_worksheet.getRow(row);

                if (therow == null) {
                    filaExcel = null;
                    continue;
                }

                String v;

                boolean filaBuida = true;
                for (int col = 0; col < MAX_COLUMNES; col++) {

                    cell = therow.getCell(col);

                    if (debug) {
                        System.out.print("{r: " + row + " | c:" + col + "} ");
                    }

                    if (cell == null) {
                        v = " => --NULL--";
                    } else {

                        CellType type = cell.getCellTypeEnum();

                        switch (type) {
                            case STRING:
                                v = cell.getStringCellValue();
                            break;
                            case FORMULA:
                                final CellValue cellValue = evaluator.evaluate(cell);
                                v = cellValue.getStringValue();
                            break;
                            case BLANK:
                                v = "";
                            break;
                            default:
                                int num = (int) cell.getNumericCellValue();
                                v = String.valueOf(num);
                            break;
                        }
                        if (debug) {
                            System.out.println("(" + type + ")} => ]" + v + "[");
                        }
                        
                    }
                    
                    if (v.trim().length() == 0) {
                        filaBuida &= true;
                    } else {
                        filaBuida &= false;
                    }

                    if (row >= FIRST_DATA_ROW +1 && v.trim().length() == 0  && darreraFila != null) {
                        String valPrev = darreraFila[col];
                        if (valPrev != null) {
                            v = valPrev;
                        }
                    }
                    
                    filaExcel[col] = v;
                }
                
                if (!filaBuida && row >= FIRST_DATA_ROW) {
                    darreraFila = filaExcel;
                    afegirFilaAInfo(filaExcel, info);
                }
            }

            if (debug) {
                System.out.println("\n\nFINAL DE DEBUG\n\n");
            }
            
            return info;

        } finally {
            if (my_xlsx_workbook != null) {
                try {
                    my_xlsx_workbook.close();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }

        }
    }

    private static void afegirFilaAInfo(String[] row, SolicitudInfo info) {
        final int CODI_PROC_COL_A = 0;
        final int NOM_PROC_COL_B = 1;
        final int CEDENT_COL_C = 2;
        final int NOM_SERVEI_COL_D = 3;

        final int TIPUS_PROC_COL_G = 6;
        final int CONSENTIMENT_COL_H = 7;
        final int NORMA_LEGAL_COL_I = 8;
        final int ARTICULOS_COL_J = 9;
        final int ENLLAZ_NORMA_LEGAL_COL_K = 10;
        final int ENLLAZ_CONSENTIMENT_COL_L = 11;
        final int DATA_CADUCITAT_COL_M = 12;

        final int PERODIC_COL_N = 13;
        final int AUTOMATIZAT_COL_O = 14;
        final int PETICIONS_ESTIMADES_COL_P = 15;

        String codiProc = row[CODI_PROC_COL_A];

        System.out.println("codiProc: " + codiProc);

        ProcedimentInfo iproc = info.getProcediment(codiProc);

        if (iproc == null) {
            String nom = row[NOM_PROC_COL_B];
            String tipusProcediment = row[TIPUS_PROC_COL_G];

            iproc = new ProcedimentInfo(codiProc, nom, tipusProcediment);

            info.addProcediment(iproc);
        }

        String nomServei = row[NOM_SERVEI_COL_D];
        String cedent = row[CEDENT_COL_C];

        String consentiment = row[CONSENTIMENT_COL_H];

        String enllazConsentiment = null;
        String tipusConsentiment = null;

        if (!consentiment.equals("Ley")) {
            enllazConsentiment = row[ENLLAZ_CONSENTIMENT_COL_L];
            if (enllazConsentiment.startsWith("http")) {
                tipusConsentiment = "Publicat";
            } else {
                tipusConsentiment = "Adjunt";
            }
        }

        String notes = null;

        String caducitat = row[DATA_CADUCITAT_COL_M];

        String caduca;
        String fechaCaduca;

        if (caducitat.equals("No caduca")) {
            caduca = caducitat;
            fechaCaduca = null;
        } else {
            caduca = "Caduca";
            fechaCaduca = caducitat;
        }

        System.out.println("nomServei: " + nomServei);
        System.out.println("cedent: " + cedent);

        ServeiInfo servei = new ServeiInfo(nomServei, cedent, tipusConsentiment, consentiment, enllazConsentiment,
                notes, caduca, fechaCaduca);

        iproc.addServei(servei);

        String norma = row[NORMA_LEGAL_COL_I];
        String articles = row[ARTICULOS_COL_J];
        String enllaz = row[ENLLAZ_NORMA_LEGAL_COL_K];

        servei.addNormativa(new NormativaInfo(norma, articles, enllaz));
    }

    @SuppressWarnings("deprecation")
    private static String toString(Cell cell) {
        if (cell == null) {
            return null;
        }

        if (cell.getCellTypeEnum() == CellType.BLANK) {
            return "";
        }
        String str;

        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            str = String.valueOf((long) cell.getNumericCellValue());
        } else {
            str = cell.getStringCellValue();
        }
        return str;
    }

}
