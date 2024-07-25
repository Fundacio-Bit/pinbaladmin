package org.fundaciobit.pinbaladmin.logic.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.fundaciobit.pinbaladmin.logic.utils.FileInfo.FileInfoType;

/**
 * 
 * @author anadal
 *
 */
public class PdfDownloader {


    static {
        try {
            ignoreCertificateErrors();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    protected static final HtmlPagePdfUrlParser[] AVAILABLE_HTML_PARSERS = new HtmlPagePdfUrlParser[] { new BoibParser(),
            new BoeParser() };

    public static FileInfo downloadPDFFromBoeBoibUrl(String urlString, boolean debug) throws Exception {

        if (debug) {
            System.out.println(" ============================================");
            System.out.println(urlString);
            System.out.println(" --------------------------------------------");
        }

        URL url = new URL(urlString);
        // open the connection
        URLConnection con = url.openConnection();

        Map<String, List<String>> headers = con.getHeaderFields();

        String contentDisposition = null;
        String contentType = null;
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            String key = entry.getKey();
            List<String> val = entry.getValue();

            if (debug)
                System.out.println(key + " => " + Arrays.toString(val.toArray()));

            if ("Content-Disposition".equalsIgnoreCase(key)) {
                if (val != null) {
                    contentDisposition = val.get(0);
                }
            } else if ("Content-Type".equalsIgnoreCase(key)) {
                if (val != null) {
                    contentType = val.get(0);
                }
            }

        }

        // content-disposition => [attachment;filename=1476.pdf]
        if (contentDisposition == null || contentDisposition.indexOf("filename=") == -1) {
            //
            //contentDisposition = "[attachment;fitxer_de_norma.pdf]";
            contentDisposition = null;
        }

        // Content-Type => [application/pdf;charset=UTF-8]
        if (contentType == null || contentType.trim().length() == 0) {

            //
            contentType = null;
        }

        if (debug) {
            System.err.println("contentType == " + contentType);
            System.err.println("contentDisposition == " + contentDisposition);
        }

        if (contentType.toLowerCase().contains("text/html")) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            StringBuilder content = new StringBuilder();

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
            return getPdfUrlFromHtml(urlString, content.toString(), debug);

        } else if (contentType.toLowerCase().contains("application/pdf")) {

            String filename;
            if (contentDisposition != null && contentDisposition.contains("attachment")
                    && contentDisposition.contains("filename")) {
                boolean endsWithQuotes = contentDisposition.endsWith("\"");
                filename = contentDisposition.substring(
                        contentDisposition.indexOf("filename=") + (endsWithQuotes ? 10 : 9),
                        contentDisposition.length() - (endsWithQuotes ? 1 : 0));
            } else {
                if (urlString.toLowerCase().endsWith(".pdf")) {

                    filename = urlString.substring(urlString.lastIndexOf('/') + 1);

                } else {

                    filename = "norma.pdf";

                }
            }

            // TODO Check PDF  XYZ 

            return downloadFileFromURL(con, filename);
        }

        return null;

    }

    protected static FileInfo getPdfUrlFromHtml(String url, String html, boolean debug) throws Exception {

        for (HtmlPagePdfUrlParser parser : AVAILABLE_HTML_PARSERS) {
            String urlToPdf = parser.parse(url, html, debug);

            if (urlToPdf != null) {
                FileInfo fi = downloadPDFFromBoeBoibUrl(urlToPdf, debug);
                if (fi != null) {
                    fi.setType(FileInfoType.HTMLPDF);
                }
                return fi;
            }
        }

        return null;

    }

    protected static FileInfo downloadFileFromURL(URLConnection con, String filename) throws IOException {


        byte[] content;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        org.fundaciobit.pluginsib.core.v3.utils.FileUtils.copy(con.getInputStream(), baos);
        content = baos.toByteArray();
        // content = StreamUtils.getBytes(con.getInputStream());

        return new FileInfo(filename, content, FileInfo.FileInfoType.DIRECTPDF);
    }

    protected static void ignoreCertificateErrors() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }

        } };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        /* End of the fix*/
    }

    protected static abstract class HtmlPagePdfUrlParser {

        /**
         * 
         * @param html
         * @return null si no troba els resultats esperats. URL al pdf.
         */
        public abstract String parse(String url, String htmlText, boolean debug);

    }

    protected static class BoeParser extends HtmlPagePdfUrlParser {

        @Override
        public String parse(String url, String htmlText, boolean debug) {
            /*
             <a target="_blank" title="Abre el PDF en una nueva ventana" href="/buscar/pdf/2017/BOE-A-2017-12902-consolidado.pdf">PDF</a>
             */

            final String search = "target=\"_blank\" title=\"Abre el PDF en una nueva ventana\" href=\"";
            ;

            int index = htmlText.indexOf(search);

            if (index != -1) {

                index = index + search.length();

                int index2 = htmlText.indexOf("\"", index);

                try {
                    URL u = new URL(url);

                    return u.getProtocol() + "://" + u.getHost() + (u.getPort() == -1 ? "" : (":" + u.getPort()))
                            + htmlText.substring(index, index2);

                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            return null;
        }

    }

    protected static class BoibParser extends HtmlPagePdfUrlParser {

        @Override
        public String parse(String url, String htmlText, boolean debug) {

            // /eboibfront/img/ico/ico_pdf.gif\" alt=\"Documento pdf\">&nbsp;<a class=\"document\" rel=\"external\" href=\"/eboibfront/pdf/es/2023/25/1130476\" 

            String toSearch = "<img src=\"/eboibfront/img/ico/ico_pdf.gif\" alt=\"Documento adjunto\" />";
            //"/eboibfront/img/ico/ico_pdf.gif"
            int index = htmlText.indexOf(toSearch);

            
            
            if (index != -1) {

                final String href = "href=\"";

                index = htmlText.indexOf(href, index);

                if (index != -1) {

                    index = index + href.length();

                    int index2 = htmlText.indexOf("\"", index);

                    try {
                        URL u = new URL(url);

                        String PdfURL = htmlText.substring(index, index2);
                        String newUrl = u.getProtocol() + "://" + u.getHost() + (u.getPort() == -1 ? "" : (":" + u.getPort()))
                                + PdfURL;
                        if (debug) {
                            System.out.println("PdfURL: " + PdfURL);
                        }
                        return newUrl;

                    } catch (MalformedURLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }

            return null;

        }

    }

    
}
