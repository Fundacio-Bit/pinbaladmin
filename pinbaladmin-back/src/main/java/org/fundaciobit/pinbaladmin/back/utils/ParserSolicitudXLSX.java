package org.fundaciobit.pinbaladmin.back.utils;

import java.io.InputStream;

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
    public static SolicitudInfo extreureInfo(InputStream input_document, boolean debug) throws Exception {

        // convert it into a POI object
        XSSFWorkbook my_xlsx_workbook = null;

        try {

            // convert it into a POI object
            my_xlsx_workbook = new XSSFWorkbook(input_document);

            // Read excel sheet that needs to be updated
            XSSFSheet my_worksheet = my_xlsx_workbook.getSheetAt(1); // Segona Fulla

            final FormulaEvaluator evaluator = my_xlsx_workbook.getCreationHelper().createFormulaEvaluator();

            // ==========================================================
            // =============== DEPURACIO VOLCAT DE XLSX ===========================
            // ==========================================================

            if (debug) {

                Cell cell;
                for (int row = 0; row < 20; row++) {

                    XSSFRow therow = my_worksheet.getRow(row);

                    if (therow == null) {
                        System.out.println("--EMPTY_ROW_" + row + "--");
                        continue;
                    }

                    String v;

                    for (int col = 0; col < 12; col++) {

                        cell = therow.getCell(col);

                        System.out.print("{r: " + row + " | c:" + col + "} ");

                        if (cell == null) {
                            v = " => --NULL--";
                        } else {

                            CellType type = cell.getCellTypeEnum();

                            System.out.print("(" + type + ")} => ]");

                            if (type == CellType.STRING) {
                                v = cell.getStringCellValue(); // toString();
                            } else if (type == CellType.FORMULA) {
                                v = cell.getCellFormula();
                                log.info("FORMULA ORIG {r: " + row + " | c:" + col + "} => ]" + v + "[");

                                final CellValue cellValue = evaluator.evaluate(cell);

                                v = cellValue.getStringValue();

                                log.info("FORMULA STR {r: " + row + " | c:" + col + "} => ]" + v + "[");

                            } else {
                                v = String.valueOf((int) cell.getNumericCellValue()); // toString();
                            }
                        }
                        System.out.println(v + "[");

                    }

                }

                System.out.println();
                System.out.println();
                System.out.println("FINAL DE DEBUG");
                System.out.println();
                System.out.println();

            }

            // ==========================================================
            // =============== FINAL DEPURACIO VOLCAT DE XLSX ===========================
            // ==========================================================

            SolicitudInfo info = new SolicitudInfo(my_worksheet.getRow(1).getCell(0).getStringCellValue());
            
            
            final int CODI_PROC_COL_A = 0;
            final int NOM_PROC_COL_B = 1;
            final int CEDENT_COL_C = 2;
            final int NOM_SERVEI_COL_D = 3;
            final int PERIODO_COL_E = 4;
            final int TIPUS_PROC_COL_G = 6;
            final int NORMA_LEGAL_COL_I = 8;
            final int ARTICULOS_COL_J = 9;
            final int ENLLAZ_NORMA_LEGAL_COL_K = 10;
            

            // CHECK QUE SIGUI DEL NOU MODEL !!!!!!
            // Miram que dins la cel·la E6 (row 5, col 4) hi hagi la paraula "Periodo" 
            String periodo = my_worksheet.getRow(5).getCell(PERIODO_COL_E).getStringCellValue();
            if (!"Periodo".equals(periodo.trim())) {
                throw new Exception("El format de la fulla excel no està actualitzat."
                        + " A la casella E6 hi hauria d'haver la paraula 'Periodo' i ha obtingut la paraula ]" + periodo + "[");
            }

            Cell cellNorma;
            {
                int row = 5;
                int blank = 0;
                ServeiInfo lastServei = null;
                String lastCodiProcediment = null;
                do {
                    row++;

                    XSSFRow therow = my_worksheet.getRow(row);

                    if (therow == null) {
                        blank++;
                        if (blank > 6) {
                            break;
                        }
                        continue;
                    }



                    final int col = NORMA_LEGAL_COL_I;
                    cellNorma = therow.getCell(col); // Norma legal

                    if (cellNorma == null || cellNorma.getCellTypeEnum() == CellType.BLANK) {

                        log.info("(r:" + row + "c:" + col + ") BLANK");

                        blank++;
                        if (blank > 3) {
                            break;
                        }

                        /**
                        String norma = therow.getCell(7).getStringCellValue();
                        String articles = therow.getCell(8).getStringCellValue();
                        String enllaz = therow.getCell(9).getStringCellValue();
                        
                        lastServei.addNormativa(new NormativaInfo(norma, articles, enllaz));
                        */

                        continue;
                    }
                    blank = 0;

                    String codiProc;
                    Cell cell = therow.getCell(CODI_PROC_COL_A);
                    if (cell.getCellTypeEnum() == CellType.BLANK) {
                        codiProc = lastCodiProcediment;
                    } else {
                        codiProc = toString(cell);
                        lastCodiProcediment = codiProc;
                    }

                    ProcedimentInfo iproc = info.getProcediment(codiProc);
                    if (iproc == null) {
                        String nom = therow.getCell(NOM_PROC_COL_B).getStringCellValue();
                        String tipusProcediment = therow.getCell(TIPUS_PROC_COL_G).getStringCellValue();

                        iproc = new ProcedimentInfo(codiProc, nom, tipusProcediment);

                        info.addProcediment(iproc);
                    }

                    // System.err.println("Afegint Servei: " +
                    // therow.getCell(3).getStringCellValue());

                    {
                        String nomServei = therow.getCell(NOM_SERVEI_COL_D).getStringCellValue();
                        String cedent = therow.getCell(CEDENT_COL_C).getStringCellValue();
                        lastServei = new ServeiInfo(nomServei, cedent);
                    }
                    iproc.addServei(lastServei);

                    String norma = toString(therow.getCell(NORMA_LEGAL_COL_I));

                    String articles = toString(therow.getCell(ARTICULOS_COL_J));

                    String enllaz = toString(therow.getCell(ENLLAZ_NORMA_LEGAL_COL_K));

                    lastServei.addNormativa(new NormativaInfo(norma, articles, enllaz));

                } while (true);
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
