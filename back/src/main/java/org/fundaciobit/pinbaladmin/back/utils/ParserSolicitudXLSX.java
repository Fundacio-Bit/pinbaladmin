package org.fundaciobit.pinbaladmin.back.utils;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author anadal
 *
 */
public class ParserSolicitudXLSX {

  /**
   * 
   * @param plantillaXLSX
   * @param debug
   * @return
   * @throws Exception
   */
  public static SolicitudInfo extreureInfo(InputStream input_document, boolean debug)
      throws Exception {


    
        // convert it into a POI object
    XSSFWorkbook my_xlsx_workbook = null;

    try {
      
      // convert it into a POI object
      my_xlsx_workbook = new XSSFWorkbook(input_document);
      // Read excel sheet that needs to be updated
      XSSFSheet my_worksheet = my_xlsx_workbook.getSheetAt(1); // Segona Fulla

      SolicitudInfo info = new SolicitudInfo(
          my_worksheet.getRow(1).getCell(0).getStringCellValue());

      Cell cell;
      {
        int row = 5;
        int blank = 0;
        ServeiInfo lastServei = null;
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
          
          cell = therow.getCell(0);

          if (cell.getCellTypeEnum() == CellType.BLANK) {
            blank++;
            if (blank > 6) {
              break;
            }
            
            String norma = therow.getCell(7).getStringCellValue();
            String articles = therow.getCell(8).getStringCellValue();
            String enllaz = therow.getCell(9).getStringCellValue();

            lastServei.addNormativa(new NormativaInfo(norma, articles, enllaz));

            continue;
          }
          blank = 0;

          String codi;
          if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            codi = String.valueOf((long) cell.getNumericCellValue());
          } else {
            codi = cell.getStringCellValue();
          }

          ProcedimentInfo iproc = info.getProcediment(codi);
          if (iproc == null) {
            String nom = therow.getCell(1).getStringCellValue();
            String tipusProcediment = therow.getCell(5).getStringCellValue();

            iproc = new ProcedimentInfo(codi, nom, tipusProcediment);

            info.addProcediment(iproc);
          }

          // System.err.println("Afegint Servei: " +
          // therow.getCell(3).getStringCellValue());
          {
            String nom = therow.getCell(3).getStringCellValue();
            String cedent = therow.getCell(2).getStringCellValue();
            lastServei = new ServeiInfo(nom, cedent);
          }
          iproc.addServei(lastServei);
          
          String norma = therow.getCell(7).getStringCellValue();
          String articles = therow.getCell(8).getStringCellValue();
          String enllaz = therow.getCell(9).getStringCellValue();

          lastServei.addNormativa(new NormativaInfo(norma, articles, enllaz));

        } while (true);
      }

      if (debug) {

        for (int row = 0; row < 20; row++) {

          XSSFRow therow = my_worksheet.getRow(row);

          if (therow == null) {
            System.out.println("--EMPTY_ROW_" + row + "--");
            continue;
          }

          String v;

          for (int col = 0; col < 10; col++) {

            cell = therow.getCell(col);

            System.out.print("{r: " + row + " | c:" + col + "} ");

            if (cell == null) {
              v = " => --NULL--";
            } else {

              CellType type = cell.getCellTypeEnum();

              System.out.print("(" + type + ")} => ]");

              if (type == CellType.STRING) {
                v = cell.getStringCellValue(); // toString();
              } else {
                v = String.valueOf((int) cell.getNumericCellValue()); // toString();
              }
            }
            System.out.println(v + "[");

          }

        }
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

}
