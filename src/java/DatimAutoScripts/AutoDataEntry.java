/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatimAutoScripts;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




//Selenium Imports

import java.net.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.*;

import org.openqa.selenium.remote.*;  

import org.openqa.selenium.support.ui.*;



/**
 *
 * @author Emmanuel Kaunda
 */
public class AutoDataEntry extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        String un="";
        String pw="";
        String ds="";
        String prd="";
        String sts="";
        String sts_arr[]=null;
        
        if(request.getParameter("ds")!=null){ds=request.getParameter("ds");}
        if(request.getParameter("prd")!=null){prd=request.getParameter("prd");}
        if(request.getParameter("sts")!=null){sts=request.getParameter("sts");}
        sts_arr=request.getParameterValues("sts[]");
        
        
        System.out.println(""+sts);
        System.out.println("Number of sites:_"+sts_arr.length+"_elemnt one"+sts_arr[0]);
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            // declaration and instantiation of objects/variables
    	//System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
		//WebDriver driver = new FirefoxDriver();
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		System.setProperty("webdriver.chrome.driver","C:\\HSDSA\\chromedriver.exe");
                
                ChromeOptions options = new ChromeOptions();
options.addArguments("--remote-allow-origins=*");
//ChromeDriver driver = new ChromeDriver(options);
                
		WebDriver driver = new ChromeDriver(options);
                
        if(1==1)  {    

  //Thread.sleep(5000);  // Let the user actually see something!     

 // WebElement searchBox = driver.findElement(By.name("q"));

  //searchBox.sendKeys("ChromeDriver");     

  //searchBox.submit();    

  //Thread.sleep(5000);
                
                
    	
        String baseUrl = "https://www.datim.org/";
        
        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);
driver.manage().window().maximize();
WebElement accept=driver.findElement(By.cssSelector("button"));
        accept.click();
        // get the actual value of the title
WebElement username=driver.findElement(By.id("j_username"));
WebElement password=driver.findElement(By.id("j_password"));
WebElement login=driver.findElement(By.id("submit"));
username.sendKeys("ekaunda");
password.sendKeys("Plusaphia2013!!");
login.click();
    //WebElement clickapps=driver.findElement(By.cssSelector("[data-test=headerbar-apps-icon] > svg > path"));
//clickapps.click();
   // WebElement dataentry=driver.findElement(By.linkText("Data Entry"));    
        String dataentryurl="https://www.datim.org/dhis-web-dataentry/index.action";
        driver.get(dataentryurl);
     
     WebElement search=driver.findElement(By.id("searchIcon"));
     
        //System.out.println("before site search click");
     
     search.click();
       System.out.println("After site search click");
     
     WebElement searchfield=driver.findElement(By.id("searchField"));
//     WebElement country=driver.findElement(By.cssSelector("#orgUnitHfVjCurKxh2 img"));
//     WebElement county=driver.findElement(By.cssSelector("#orgUnitTbq3JMDALZt img"));
//     //https://www.datim.org
//     country.click();


String sites[]=sts_arr;


String datim_tabs[]={"Prevention"};
//String datim_tabs[]={"Prevention","Testing - HTS_TST","Testing - All Others","Treatment","Viral Suppression","Health Systems","Testing - HTS_RECENT"};
String datim_tabs_show_hid[]={"#PEPFAR_Form_2_DSD > .PEPFAR_Form_ShowHide","#PEPFAR_Form_2_DSD > .PEPFAR_Form_ShowHide","#PEPFAR_Form_3_DSD > .PEPFAR_Form_ShowHide","#PEPFAR_Form_4_DSD > .PEPFAR_Form_ShowHide","#PEPFAR_Form_5_DSD > .PEPFAR_Form_ShowHide","#PEPFAR_Form_6_DSD > .PEPFAR_Form_ShowHide","#PEPFAR_Form_7_DSD > .PEPFAR_Form_ShowHide"};

int lmt=1;
for(int a=0;a<sites.length;a++){
    
    System.out.println("Facility:"+sites[a]);
   out.println("Facility:"+sites[a]+"<br/>"); 
   
    if(!sites[a].equals("")){


    searchfield.clear();
     //System.out.println("After site clear click");
     searchfield.sendKeys(sites[a]);
      
       WebElement clicksearch = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchSpan\"]/input[2]")));
     
        
       if(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.id("selectedDataSetId"))).isEnabled())
       {
     
        clicksearch.click();
        
       }
               
       
        
  WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.id("selectedPeriodId")));
  System.out.println(firstResult.getText());
        //prevButton
        
        //selectedDataSetId
         
        //clicksearch.click();
        Select period = new Select(driver.findElement(By.id("selectedPeriodId")));

        period.selectByValue(prd);
        //period.sendKeys("");
        //April - June 2022
        //Thread.sleep(7000);
        
        for(int b=0;b<datim_tabs.length;b++){
        
            
//WebElement clicktab=driver.findElement(By.id(datim_tabs[b]));
            //div[@id='PEPFAR_Tabs_vertical']/ul/li[4]/a
         //WebElement clicktab = new WebDriverWait(driver,Duration.ofSeconds(100)).until(ExpectedConditions.visibilityOfElementLocated(By.id("PEPFAR_Tabs_vertical")));
         //clicktab.click();
        if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.linkText("Prevention"))).isDisplayed()){
            
            //click the section name the first time only
         
             if(lmt==1){ }
             System.out.println("initiate 5 seconds timeout");
           Thread.sleep(5000);
             System.out.println(" 5 seconds timeout completed");
             
             if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.linkText("Viral Suppression"))).isDisplayed()){ 
                   System.out.println("Load VL section");
                 new WebDriverWait(driver, Duration.ofSeconds(150)).until(ExpectedConditions.elementToBeClickable(By.linkText("Viral Suppression"))).click();
               //driver.findElement(By.linkText("Viral Suppression")).click();
             }
              // new WebDriverWait(driver, Duration.ofSeconds(100),Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.linkText("Viral Suppression"))).click();
            
              
           //driver.findElement(By.linkText("Viral Suppression")).click();
           
          // if(datim_tabs[b].equals("Prevention")){
           if(1==1){
               
            
               
               enterViralSuppression(driver);
             
           }
           
           
        
        }
         //clicktab1.click();
        //new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(datim_tabs_show_hid[b]))).click();
         //#PEPFAR_Form_1_DSD > .PEPFAR_Form_ShowHide
         
       //  clickcollapse.click();
         
        //driver.findElement(By.id("ui-id-27")).click();
        // driver.findElement(By.cssSelector("#PEPFAR_Form_4_DSD > .PEPFAR_Form:nth-child(10) > .PEPFAR_Form_Container")).click();
         
         
}
        
   lmt++;      

}
}   
        
       // # Wait for the data entry form to load
//wait.until(EC.presence_of_element_located((By.CLASS_NAME, 'data-entry-form')))

 
       
    
        //driver.close();
        
        
        }
       
            
            out.println("<br/>Finished Data Entry");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(AutoDataEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(AutoDataEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
     public void enterPrevention(WebDriver driver){
    
        
             //
    driver.findElement(By.id("KNO4emPfF91-a4ClnAbpWXW-val")).click();
    driver.findElement(By.id("KNO4emPfF91-BYmlmGMcCWx-val")).click();
    driver.findElement(By.id("KNO4emPfF91-zE5NFpGXDy4-val")).click();
    driver.findElement(By.id("KNO4emPfF91-u88hOHhmLuF-val")).click();
    driver.findElement(By.id("KNO4emPfF91-tcJ9vZbCWcO-val")).click();
    driver.findElement(By.id("KNO4emPfF91-WghEsgfAUAb-val")).click();
    driver.findElement(By.id("KNO4emPfF91-Ij7k6DBjI3i-val")).click();
    driver.findElement(By.id("KNO4emPfF91-dIfXCJxd5bY-val")).click();
    driver.findElement(By.id("KNO4emPfF91-xqiQnxlVCYm-val")).click();
    driver.findElement(By.id("KNO4emPfF91-t46YTexAkDE-val")).click();
    driver.findElement(By.id("KNO4emPfF91-kQ58FETBxFn-val")).click();
    driver.findElement(By.id("KNO4emPfF91-jJifRzf2Z8j-val")).click();
    driver.findElement(By.id("KNO4emPfF91-necuVZOR1HB-val")).click();
    driver.findElement(By.id("KNO4emPfF91-HnDmWypXRdG-val")).click();
    driver.findElement(By.id("KNO4emPfF91-Sq9vathzQd9-val")).click();
    driver.findElement(By.id("KNO4emPfF91-efXnrOzWCGW-val")).click();
    driver.findElement(By.id("KNO4emPfF91-fSgFPhUpbWq-val")).click();
    
    //Prep CT
    
    driver.findElement(By.id("JoERp5gZ6o1-mkXXjV42FM9-val")).click();
    driver.findElement(By.id("JoERp5gZ6o1-mkXXjV42FM9-val")).click();
    driver.findElement(By.id("JoERp5gZ6o1-X5WOZxTBU2j-val")).click();
    driver.findElement(By.id("JoERp5gZ6o1-EoZ7f4rkx2g-val")).click();
    driver.findElement(By.id("JoERp5gZ6o1-hvgp9xnuUrx-val")).click();
    driver.findElement(By.id("JoERp5gZ6o1-etnBr84tFtJ-val")).click();	
    driver.findElement(By.id("ectUGvYjtaK-a4ClnAbpWXW-val")).click();
    driver.findElement(By.id("ectUGvYjtaK-BYmlmGMcCWx-val")).click();
    driver.findElement(By.id("ectUGvYjtaK-zE5NFpGXDy4-val")).click();
    driver.findElement(By.id("ectUGvYjtaK-u88hOHhmLuF-val")).click();
    driver.findElement(By.id("ectUGvYjtaK-WghEsgfAUAb-val")).click();
    driver.findElement(By.id("ectUGvYjtaK-Ij7k6DBjI3i-val")).click();
    driver.findElement(By.id("ectUGvYjtaK-t46YTexAkDE-val")).click();
    driver.findElement(By.id("ectUGvYjtaK-Sq9vathzQd9-val")).click();
    driver.findElement(By.id("ectUGvYjtaK-efXnrOzWCGW-val")).click();
    driver.findElement(By.id("Y5zUjJ7a5fK-ppYYPECOvBK-val")).click();
    driver.findElement(By.id("Y5zUjJ7a5fK-zeIPVS0tehO-val")).click();
    driver.findElement(By.id("czF2lzyXksI-mkXXjV42FM9-val")).click();
    driver.findElement(By.id("czF2lzyXksI-etnBr84tFtJ-val")).click();
    driver.findElement(By.id("oREygLjW7vd-dHeLwUzvlcx-val")).click();
    
            
    
        
    
    }
    
    public static void enterViralSuppression(WebDriver driver) throws InterruptedException{
    
        
  if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.presenceOfElementLocated(By.id("YvPOllVtINQ-KrhiRAB3V7S-val"))).isDisplayed()){
        
    WebElement ua= driver.findElement(By.id("YvPOllVtINQ-KrhiRAB3V7S-val")); ua.clear();ua.sendKeys("");
    driver.findElement(By.id("YvPOllVtINQ-HDhg4LTHBRa-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-dpFsZrc6Ffc-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-A30fQSASmum-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-d7veFTMK1Jw-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-I0zEWK2C11q-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-RKp8rxNgQAX-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-MRnYv4nt5gc-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-wOxLLZhNrPi-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-ONQ9uSvOkGB-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-LCPIxZynLQM-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-B839JaBDwHH-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-C87DDcMatAl-val")).click();
    
    
    WebElement uam= driver.findElement(By.id("YvPOllVtINQ-VVegjxR0OsQ-val")); uam.clear();uam.sendKeys("");
    driver.findElement(By.id("YvPOllVtINQ-bQY52yJFcaj-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-sjBprG9Atqw-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-Cz8TfD9G4NS-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-hX01YQ8Xd0A-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-X3iUwZMRbpC-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-PRsdy4olkFE-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-Ba3F9Cdo4TM-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-gndk9mTIvDg-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-vsxR9fL8r7u-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-JaGu7802vQP-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-GL8Trkj5nU5-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-pjkXBdgweKp-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-UmKpnaBWKNG-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-Wl6Xe4IRe5N-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-B3YJoWLCkue-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-nVgwQTwVWng-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-Xv7byNPl3sp-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-mKoxFv2cCli-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-nzibhBFbTbH-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-XZYzun07tIg-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-fsudMeTXP1k-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-qWTNI2wbVHs-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-nTviezFvNzm-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-CUcChuyrJO2-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-WalMMpT8Ue2-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-dsq0QpQMPj0-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-dVZwJhviGrl-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-uQJ2RFlLZ8L-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-RtzNjHlVYXH-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-nXkcVmTUbM7-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-I067A5SQouw-val")).click();
    driver.findElement(By.id("YvPOllVtINQ-OlITVSRy2sm-val")).click();
    driver.findElement(By.id("JTmqyoIWNsj-b1veZoOczoR-val")).click();
    driver.findElement(By.id("JTmqyoIWNsj-FR9ZDmeA4Az-val")).click();
    driver.findElement(By.id("JTmqyoIWNsj-jBJaVu6svtP-val")).click();
    driver.findElement(By.id("Fs6OLZSb2mg-wQEJHaLbSn1-val")).click();
    driver.findElement(By.id("Fs6OLZSb2mg-hOmBYbgBZ0O-val")).click();
    driver.findElement(By.id("Fs6OLZSb2mg-oMV1pF48ZLc-val")).click();
    driver.findElement(By.id("Fs6OLZSb2mg-qbFLLU6Fimz-val")).click();
    driver.findElement(By.id("Fs6OLZSb2mg-SkrleMTebAa-val")).click();
    driver.findElement(By.id("Fs6OLZSb2mg-e6laBt0a4H5-val")).click();
    driver.findElement(By.id("Fs6OLZSb2mg-vG5FAcGH9US-val")).click();
    driver.findElement(By.id("Fs6OLZSb2mg-qaqq0s49le3-val")).click();
	
	
	
   
    driver.findElement(By.id("kznQBykTtJt-KrhiRAB3V7S-val")).click();
    driver.findElement(By.id("kznQBykTtJt-HDhg4LTHBRa-val")).click();
    driver.findElement(By.id("kznQBykTtJt-rAvlLbG5dAb-val")).click();
    driver.findElement(By.id("kznQBykTtJt-dpFsZrc6Ffc-val")).click();
    driver.findElement(By.id("kznQBykTtJt-A30fQSASmum-val")).click();
    driver.findElement(By.id("kznQBykTtJt-d7veFTMK1Jw-val")).click();
    driver.findElement(By.id("kznQBykTtJt-I0zEWK2C11q-val")).click();
    driver.findElement(By.id("kznQBykTtJt-RKp8rxNgQAX-val")).click();
    driver.findElement(By.id("kznQBykTtJt-wOxLLZhNrPi-val")).click();
    driver.findElement(By.id("kznQBykTtJt-ONQ9uSvOkGB-val")).click();
    driver.findElement(By.id("kznQBykTtJt-B839JaBDwHH-val")).click();
    driver.findElement(By.id("kznQBykTtJt-I5IlN1PxYaA-val")).click();
    driver.findElement(By.id("kznQBykTtJt-C87DDcMatAl-val")).click();
    driver.findElement(By.id("kznQBykTtJt-VVegjxR0OsQ-val")).click();
    driver.findElement(By.id("kznQBykTtJt-vIZW4Jv7qqy-val")).click();
    driver.findElement(By.id("kznQBykTtJt-sjBprG9Atqw-val")).click();
    driver.findElement(By.id("kznQBykTtJt-hX01YQ8Xd0A-val")).click();
    driver.findElement(By.id("kznQBykTtJt-X3iUwZMRbpC-val")).click();
    driver.findElement(By.id("kznQBykTtJt-PRsdy4olkFE-val")).click();
    driver.findElement(By.id("kznQBykTtJt-TcbPwuDGR7C-val")).click();
    driver.findElement(By.id("kznQBykTtJt-gndk9mTIvDg-val")).click();
    driver.findElement(By.id("kznQBykTtJt-vsxR9fL8r7u-val")).click();
    driver.findElement(By.id("kznQBykTtJt-yXJpfecMOok-val")).click();
    driver.findElement(By.id("kznQBykTtJt-GL8Trkj5nU5-val")).click();
    driver.findElement(By.id("kznQBykTtJt-UmKpnaBWKNG-val")).click();
    driver.findElement(By.id("kznQBykTtJt-Wl6Xe4IRe5N-val")).click();
    driver.findElement(By.id("kznQBykTtJt-B3YJoWLCkue-val")).click();
    driver.findElement(By.id("kznQBykTtJt-XkXgVeD7zWW-val")).click();
    driver.findElement(By.id("kznQBykTtJt-nVgwQTwVWng-val")).click();
    driver.findElement(By.id("kznQBykTtJt-Xv7byNPl3sp-val")).click();
    driver.findElement(By.id("kznQBykTtJt-mKoxFv2cCli-val")).click();
    driver.findElement(By.id("kznQBykTtJt-mGvZHOcps52-val")).click();
    driver.findElement(By.id("kznQBykTtJt-XZYzun07tIg-val")).click();
    driver.findElement(By.id("kznQBykTtJt-fsudMeTXP1k-val")).click();
    driver.findElement(By.id("kznQBykTtJt-nTviezFvNzm-val")).click();
    driver.findElement(By.id("kznQBykTtJt-UBAW8zO2PXf-val")).click();
    driver.findElement(By.id("kznQBykTtJt-CUcChuyrJO2-val")).click();
    driver.findElement(By.id("kznQBykTtJt-WCUwCrmtbTo-val")).click();
    driver.findElement(By.id("kznQBykTtJt-MEG4maaWoA7-val")).click();
    driver.findElement(By.id("kznQBykTtJt-dsq0QpQMPj0-val")).click();
    driver.findElement(By.id("kznQBykTtJt-dVZwJhviGrl-val")).click();
    driver.findElement(By.id("kznQBykTtJt-Gi32xq0roZx-val")).click();
    driver.findElement(By.id("kznQBykTtJt-RtzNjHlVYXH-val")).click();
    driver.findElement(By.id("kznQBykTtJt-zIZwI16B6U4-val")).click();
    driver.findElement(By.id("kznQBykTtJt-nXkcVmTUbM7-val")).click();
    driver.findElement(By.id("kznQBykTtJt-I067A5SQouw-val")).click();
    driver.findElement(By.id("kznQBykTtJt-OlITVSRy2sm-val")).click();
    driver.findElement(By.id("eQdclZl2AoR-b1veZoOczoR-val")).click();
    driver.findElement(By.id("eQdclZl2AoR-FR9ZDmeA4Az-val")).click();
    driver.findElement(By.id("eQdclZl2AoR-jBJaVu6svtP-val")).click();
    driver.findElement(By.id("KqVN4pDxEGq-wQEJHaLbSn1-val")).click();
    driver.findElement(By.id("KqVN4pDxEGq-oMV1pF48ZLc-val")).click();
    driver.findElement(By.id("KqVN4pDxEGq-qbFLLU6Fimz-val")).click();
    driver.findElement(By.id("KqVN4pDxEGq-yuVedHVF5au-val")).click();
    driver.findElement(By.id("KqVN4pDxEGq-SkrleMTebAa-val")).click();
    driver.findElement(By.id("KqVN4pDxEGq-e6laBt0a4H5-val")).click();
    driver.findElement(By.id("KqVN4pDxEGq-vG5FAcGH9US-val")).click();
    driver.findElement(By.id("KqVN4pDxEGq-qaqq0s49le3-val")).click();
    
    //driver.navigate().refresh();
    
          }
    
    }
    
    
    
}
