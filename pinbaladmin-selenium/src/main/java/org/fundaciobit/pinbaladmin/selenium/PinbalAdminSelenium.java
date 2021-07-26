package org.fundaciobit.pinbaladmin.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class PinbalAdminSelenium {

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        return OS.contains("win");
    }

    public static String consultaIncidencia(String email, String incidencia, String seguimiento) {
        String pageSource;
        WebDriver driver = null;
        try {
            //JavascriptExecutor js;
            driver = getDriver(true);

            //js = (JavascriptExecutor) driver;
            // vars = new HashMap<String, Object>();

            System.out.println(" ENTRA");

            driver.get("https://ssweb.seap.minhap.es/ayuda/seguimiento");
            driver.findElement(By.id("data_email")).click();
            driver.findElement(By.id("data_email")).click();
            driver.findElement(By.id("data_email")).sendKeys(email);
            driver.findElement(By.id("data_incidencia")).click();
            driver.findElement(By.id("data_incidencia")).sendKeys(incidencia);
            driver.findElement(By.id("data_seguimiento")).click();
            driver.findElement(By.id("data_seguimiento")).sendKeys(seguimiento);

            System.out.println(" PRE SUBMIT");

            // NO FUNCIONA
            // driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/form/fieldset[2]/input")).click();

            WebElement element = driver
                    .findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/form/fieldset[2]/input"));
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click()", element);

            System.out.println(" PRE SLEEP");

            
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            

            

             pageSource = driver.getPageSource();

            

        } finally {

            if (driver != null) {
                driver.quit();
            }

        }
        return pageSource;
    }

    protected static WebDriver getDriver(boolean hide) {
        WebDriver driver;
        {
            final String winPath = "D:\\dades\\dades\\CarpetesPersonals\\Programacio\\pinbaladmin\\MinHAP_Selenium\\geckodriver.exe";
            final String linuxPath = "/home/pinbaladmin/pinbaladmin-selenium/geckodriver";
            System.setProperty("webdriver.gecko.driver", isWindows() ? winPath : linuxPath);

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if (hide) {
                FirefoxBinary firefoxBinary = new FirefoxBinary();
                
                firefoxBinary.addCommandLineOptions("--headless");
                firefoxBinary.addCommandLineOptions("--no-sandbox");
               
                firefoxOptions.setBinary(firefoxBinary);
            }

            driver = new FirefoxDriver(firefoxOptions);

        }
        return driver;
    }
    
    
    

    public static String altaIncidencia(AltaIncidenciaInfo alta) {

        WebDriver driver = null;
        try {
            driver = getDriver(true); // new FirefoxDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            driver.get("https://sede.administracionespublicas.gob.es/ayuda/consulta/CAID");
            driver.manage().window().setSize(new Dimension(1296, 1000));

            driver.findElement(By.id("buscar_unidad")).click();
            driver.findElement(By.id("buscar_unidad")).sendKeys("Fundació Bit");

            String s1 = "document.getElementById('data_organismo').value='" + alta.getOrganisme() + "'";
            js.executeScript(s1);

            driver.findElement(By.id("data_nombre")).click();
            driver.findElement(By.id("data_nombre")).sendKeys(alta.getNom());

            driver.findElement(By.id("data_apellido1")).click();
            driver.findElement(By.id("data_apellido1")).sendKeys(alta.getLlinatge1());
            driver.findElement(By.id("data_apellido2")).click();
            driver.findElement(By.id("data_apellido2")).sendKeys(alta.getLlinatge2());
            driver.findElement(By.id("data_telefono")).click();
            driver.findElement(By.id("data_telefono")).sendKeys(alta.getTelefon());
            driver.findElement(By.id("data_email")).click();
            driver.findElement(By.id("data_email")).sendKeys(alta.getEmail());
            driver.findElement(By.id("data_email2")).click();
            driver.findElement(By.id("data_email2")).sendKeys(alta.getEmail());
            driver.findElement(By.id("data_asunto")).click();
            driver.findElement(By.id("data_asunto")).sendKeys(alta.getAsunto());

            driver.findElement(By.id("data_tipo")).click();
            {
                WebElement dropdown = driver.findElement(By.id("data_tipo"));
                dropdown.findElement(By.xpath("//option[. = '" + alta.getTipus() + "']")).click();
            }
            driver.findElement(By.cssSelector("optgroup:nth-child(4) > option:nth-child(3)")).click();
            driver.findElement(By.id("data_entorno_afectado")).click();
            {
                WebElement dropdown = driver.findElement(By.id("data_entorno_afectado"));
                dropdown.findElement(By.xpath("//option[. = '" + alta.getEntorn() + "']")).click();
            }
            driver.findElement(By.cssSelector("#data_entorno_afectado > option:nth-child(4)")).click();

            if (alta.getFitxer() != null) {

                WebElement botoFile = driver.findElement(By.xpath("//*[@class='pickfiles simbutton']"));
                // botoFile.click();

                System.out.println("**** " + botoFile.getText() + "****");

                WebElement element = driver.findElement(By.xpath("//*[@class='plupload html5']"));

                String html = element.getAttribute("innerHTML");
                System.out.println("[[[[ " + html + "]]]]");

                // <input id="p1fb9e24qujhdjqlk3j169a1dnp0_html5" style="font-size: 999px;
                // position: absolute; width: 100%; height: 100%;" type="file" accept=""
                // multiple="multiple">

                int pos1 = html.indexOf('"');
                int pos2 = html.indexOf('"', pos1 + 2);

                String id = html.substring(pos1 + 1, pos2);
                System.out.println("ID => [" + id + "]");

                WebElement uploadElement = driver.findElement(By.id(id));

                uploadElement.sendKeys(alta.getFitxer().getAbsolutePath());

            }

            driver.findElement(By.id("data_comentario")).click();
            driver.findElement(By.id("data_comentario")).sendKeys(alta.getConsulta());

            driver.findElement(By.cssSelector(".primary")).click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("PRE GETPAGESOURCE:");

            return driver.getPageSource();
        
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }

    }
    
}
