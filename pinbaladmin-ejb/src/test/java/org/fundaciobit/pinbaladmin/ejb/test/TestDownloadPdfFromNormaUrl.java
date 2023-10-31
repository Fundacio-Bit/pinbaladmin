package org.fundaciobit.pinbaladmin.ejb.test;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.fundaciobit.pinbaladmin.logic.utils.FileInfo;
import org.fundaciobit.pinbaladmin.logic.utils.FileInfo.FileInfoType;
import org.fundaciobit.pinbaladmin.logic.utils.PdfDownloader;

/**
 * 
 * @author anadal
 *
 */
public class TestDownloadPdfFromNormaUrl {

    public static void main(String[] args) {

        try {

            String[][] tests = new String[][] {

                    //  URLs a PDFs

                    { "https://dogv.gva.es/datos/2023/03/09/pdf/2023_2254.pdf",
                            FileInfoType.DIRECTPDF + "_250335_2023_2254.pdf" },
                    { "https://intranet.caib.es/eboibfront/pdf/es/2023/24/1130370",
                            FileInfoType.DIRECTPDF + "_1134803_1476.pdf" },
                    { "https://www.caib.es/seucaib/arxiuServlet?id=5520952",
                            FileInfoType.DIRECTPDF + "_41220_Información sobre protección de datos personales.pdf" },
                    { "https://www.caib.es/seucaib/ca/arxiuServlet?id=5511051",
                            FileInfoType.DIRECTPDF + "_224236_SOL2023_REV_AN- DEF-(ES).pdf" },
                    { "https://www.boe.es/buscar/pdf/2017/BOE-A-2017-12902-consolidado.pdf",
                            FileInfoType.DIRECTPDF + "_1638541_BOE-A-2017-12902-consolidado.pdf" },
                    { "https://www.entrambasaguas.org/sites/default/files/2022-02/10-ORDENANZA-FISCAL-SERVICIO-AYUDA-A-DOMICILIO.pdf",
                            FileInfoType.DIRECTPDF + "_75678_10-ORDENANZA-FISCAL-SERVICIO-AYUDA-A-DOMICILIO.pdf" },

                    //  URLs no a PDFs
                    { "https://intranet.caib.es/eboibfront/es/2023/11700/670384/resolucion-del-consejero-de-educacion-y-formacion-",
                            FileInfoType.HTMLPDF + "_488313_1529.pdf" },
                    { "https://www.caib.es/eboibfront/es/2023/11703/670619/resolucion-del-director-general-de-planificacion-o",
                            FileInfoType.HTMLPDF + "_2496991_1764.pdf" },
                    { "https://www.boe.es/eli/es/l/2017/11/08/9/con",
                            FileInfoType.HTMLPDF + "_1638541_BOE-A-2017-12902-consolidado.pdf" },
                    { "https://www.boe.es/eli/es/l/1999/12/23/50/con",
                            FileInfoType.HTMLPDF + "_154121_BOE-A-1999-24419-consolidado.pdf" },

                    // ERROR
                    { "https://google.cat", null } };

            boolean debug = false;

            for (String[] test : tests) {
                String url = test[0];

                FileInfo fi = PdfDownloader.downloadPDFFromBoeBoibUrl(url, debug);

                String expected = test[1];

                if (fi == null) {
                    if (expected == null) {
                        System.out.println("Esperat un Error i s'ha produït OK");
                    } else {
                        System.err.println("Esperat " + expected + " però rebut null.");
                    }
                } else {
                    String current = fi.getType() + "_" + fi.getSize() + "_" + fi.getFileName();

                    if (current.equals(expected)) {

                        if (is_pdf(fi.getContent())) {
                            System.out.println(current + "  OK");
                        } else {
                            System.err.println(current + "  Fitxer Descarregat NO ES UN PDF");
                        }

                    } else {
                        System.err.println(" Esperat " + expected + " però rebut " + current);
                    }

                }

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static boolean is_pdf(byte[] data) {
        try {
            PDDocument.load(data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
