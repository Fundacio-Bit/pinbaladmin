package org.fundaciobit.pinbaladmin.back.utils;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.fundaciobit.genapp.common.i18n.I18NException;

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
			if (my_xlsx_workbook.getNumberOfSheets() < 2) {
				throw new Exception("El formato del excel no es correcto, solo hay una hoja. Devolver pidiendo que utilicen la plantilla correcta");
			}
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

            XSSFCell cellEntitat = my_worksheet.getRow(1).getCell(0);
            
            if (cellEntitat == null || cellEntitat.getCellTypeEnum() == CellType.BLANK) {
                throw new I18NException("A l'excel no surt l'Organ Solicitant (cel·la A2)");
            }
				
            String entitat = cellEntitat.getStringCellValue();
            
            SolicitudInfo info = new SolicitudInfo(entitat);
            String [] filaExcel;
            
            Cell cell;
            for (int row = 0; row < MAX_FILES; row++) {
                filaExcel = new String[MAX_COLUMNES];

                XSSFRow therow = my_worksheet.getRow(row);
                if(debug) {
                	log.info("Processam row: " + row + " " + therow);
                }
                
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
                    afegirFilaAInfo(filaExcel, info, darreraFila);
                    darreraFila = filaExcel;
                }
            }

            if (debug) {
                System.out.println("\n\nFINAL DE DEBUG\n\n");
            }
            
            return info;
		} catch (Exception e) {
			String msg = "Error al llegir el fitxer excel: " + e.getMessage();
			log.error(msg, e);
			throw new Exception(msg, e);
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

    private static void afegirFilaAInfo(String[] row, SolicitudInfo info, String[] darreraFila) throws Exception {
        final int CODI_PROC_COL_A = 0;
        final int NOM_PROC_COL_B = 1;
//        final int CEDENT_COL_C = 2;
//        final int NOM_SERVEI_COL_D = 3;

        final int TIPUS_PROC_COL_G = 6;
//        final int CONSENTIMENT_COL_H = 7;
        final int NORMA_LEGAL_COL_I = 8;
        final int ARTICULOS_COL_J = 9;
        final int ENLLAZ_NORMA_LEGAL_COL_K = 10;
//        final int ENLLAZ_CONSENTIMENT_COL_L = 11;
//        final int DATA_CADUCITAT_COL_M = 12;

//        final int PERODIC_COL_N = 13;
//        final int AUTOMATIZAT_COL_O = 14;
//        final int PETICIONS_ESTIMADES_COL_P = 15;
        
        
        //Si te codi de procediment, veure si ja el tenim i crear-lo o no.
        //Si no te codi, cercar l'anterior i utilitzar-ho.
        
        String codiProc = row[CODI_PROC_COL_A];
        System.out.println("codiProc: " + codiProc);

		if (codiProc == null || codiProc.trim().length() == 0) {
			String codiProcAnterior = darreraFila[CODI_PROC_COL_A];
			//Si el anterior es null, el excel esta mal.
			
			if (codiProcAnterior != null && codiProcAnterior.trim().length() > 0) {
				codiProc = codiProcAnterior;
				row[CODI_PROC_COL_A] = codiProcAnterior;
			} else {
				String msg = "ERROR: Codi de procediment buit i no te anterior.";
				throw new Exception(msg);
			}
		}
		
		ProcedimentInfo iproc = info.getProcediment(codiProc);

        if (iproc == null) {
            String nom = row[NOM_PROC_COL_B];
            String tipusProcediment = row[TIPUS_PROC_COL_G];

            iproc = new ProcedimentInfo(codiProc, nom, tipusProcediment);

            info.addProcediment(iproc);
        }

        ServeiInfo servei = getServei(row, iproc, darreraFila);
        
        String norma = row[NORMA_LEGAL_COL_I];
        String articles = row[ARTICULOS_COL_J];
        String enllaz = row[ENLLAZ_NORMA_LEGAL_COL_K];

        servei.addNormativa(new NormativaInfo(norma, articles, enllaz));
    }

    @SuppressWarnings("deprecation")
    protected static String toString(Cell cell) {
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

	private static ServeiInfo getServei(String[] row, ProcedimentInfo iproc, String[] darreraFila) throws Exception {

		final int CEDENT_COL_C = 2;
		final int NOM_SERVEI_COL_D = 3;

		final int CONSENTIMENT_COL_H = 7;
		final int ENLLAZ_CONSENTIMENT_COL_L = 11;
		final int DATA_CADUCITAT_COL_M = 12;

		String nomServei = row[NOM_SERVEI_COL_D];

		// Comprovar si es null o no. Si es null, agafar l'anterior.
		// Llavors, veure si ja el tenim, i si el tenim, tornar-ho. Sino, crear-ho.

		// Si l'anterior també es null, tornar error.

		if (nomServei == null || nomServei.trim().length() == 0) {
			String nomServeiAnterior = darreraFila[NOM_SERVEI_COL_D];

			if (nomServeiAnterior != null && nomServeiAnterior.trim().length() > 0) {
				nomServei = nomServeiAnterior;
				row[NOM_SERVEI_COL_D] = nomServeiAnterior;
			} else {
				String msg = "ERROR: Nom de servei buit i no te anterior.";
				throw new Exception(msg);
			}
		}

		// Cercar si ja tenim el servei, i retornar-lo.
		log.info("Servei: " + nomServei);
		ServeiInfo iServ = iproc.getServei(nomServei);
		log.info("iServ: " + iServ);
		if (iServ != null) {
			return iServ;
		}
		log.info("Creant nou servei: " + nomServei);

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

		return servei;

	}
    
    
}
