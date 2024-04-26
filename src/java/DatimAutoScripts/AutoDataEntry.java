/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatimAutoScripts;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




//Selenium Imports

import java.net.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;

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
        String sd="";
        String ed="";
        String entrytype="";
        String specificform="";
        
        String sts_arr[]=null;
        
        if(request.getParameter("ds")!=null){ds=request.getParameter("ds");}
        if(request.getParameter("prd")!=null){prd=request.getParameter("prd");}
        if(request.getParameter("sts")!=null){sts=request.getParameter("sts");}
        if(request.getParameter("sd")!=null){sd=request.getParameter("sd");}
        if(request.getParameter("ed")!=null){ed=request.getParameter("ed");}
        if(request.getParameter("et")!=null){entrytype=request.getParameter("et");}
        if(request.getParameter("specificform")!=null){specificform=request.getParameter("specificform");}
        
       
        dbConn conn= new dbConn();
        
       HashMap<String,String> prdhm = new HashMap<String, String>();
       
       prdhm.put("sd", sd);
       prdhm.put("ed", ed);
       prdhm.put("prd", prd);
       prdhm.put("entrytype", entrytype);
       prdhm.put("specificform", specificform);
        
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
//options.addArguments("--remote-allow-origins=*");
//options.addArguments("--remote-debugging-port=9222");

//options.addArguments("user-data-dir=C:\\Users\\Emmanuel Kaunda\\AppData\\Local\\Google\\Chrome\\User Data");
//options.addArguments("--profile-directory=Person 1");


//options.addArguments("debuggerAddress","127.0.0.1:9222");
//options.addArguments("--headless");



options.setPageLoadStrategy(PageLoadStrategy.EAGER);

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
    
    //add facility name to list
    
     prdhm.put("hf", sites[a]);
     
     
    System.out.println("Facility:"+sites[a]);
   out.println("Facility:"+sites[a]+"<br/>"); 
   
    if(!sites[a].equals("")){


    searchfield.clear();
     //System.out.println("After site clear click");
     searchfield.sendKeys(sites[a]);
      
       WebElement clicksearch = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchSpan\"]/input[2]")));
     
        
       if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.id("selectedDataSetId"))).isEnabled())
       {
     
        clicksearch.click();
        
       }
               
       //select period and previous button the first time
   if(a==0){     
  WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.id("selectedPeriodId")));
  System.out.println(firstResult.getText());
        //prevButton
        if(prd.contains("Q4"))
        {
        WebElement element = driver.findElement(By.id("prevButton"));
     element.click();
        }
        
        //selectedDataSetId
         
        //clicksearch.click();
        Select period = new Select(driver.findElement(By.id("selectedPeriodId")));

        period.selectByValue(prd);
        //period.sendKeys("");
        //April - June 2022
        //Thread.sleep(7000);
    }
        for(int b=0;b<datim_tabs.length;b++){
        
            
//WebElement clicktab=driver.findElement(By.id(datim_tabs[b]));
            //div[@id='PEPFAR_Tabs_vertical']/ul/li[4]/a
         //WebElement clicktab = new WebDriverWait(driver,Duration.ofSeconds(100)).until(ExpectedConditions.visibilityOfElementLocated(By.id("PEPFAR_Tabs_vertical")));
         //clicktab.click();
        if(new WebDriverWait(driver, Duration.ofSeconds(200)).until(ExpectedConditions.elementToBeClickable(By.linkText("Prevention"))).isDisplayed()){
            
            //click the section name the first time only
         
             if(lmt==1){ }
             System.out.println("initiate 5 seconds timeout");
           Thread.sleep(10000);
             System.out.println(" 5 seconds timeout completed");
             
             if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.linkText(ds))).isDisplayed()){ 
                   System.out.println(ds);
                 new WebDriverWait(driver, Duration.ofSeconds(150)).until(ExpectedConditions.elementToBeClickable(By.linkText(ds))).click();
               //driver.findElement(By.linkText("Viral Suppression")).click();
             }
              // new WebDriverWait(driver, Duration.ofSeconds(100),Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.linkText("Viral Suppression"))).click();
            
              
           //driver.findElement(By.linkText("Viral Suppression")).click();
           
          // if(datim_tabs[b].equals("Prevention")){
           if(ds.equals("Treatment")){
                          
               
               //enterViralSuppression(driver);
               if(specificform.equals("TXML"))
               {
              
               enterTX_ML(conn,driver,prdhm);
               }
               else if(specificform.equals("HPT"))
               {              
                enterTX_HTN(conn,driver,prdhm);
               }
               else if(specificform.equals("RTT"))
               {              
                
                enterTX_RTT(conn,driver,prdhm);
               }
               else if(specificform.equals("TXTB"))
               {              
                
                enterTX_TB(conn,driver,prdhm);
               }
               else  
               {
               enterTX_RTT(conn,driver,prdhm);
               enterTX_HTN(conn,driver,prdhm);
               enterTX_ML(conn,driver,prdhm);
               enterTX_TB(conn,driver,prdhm);
               }
               
               
            //Validate and close   
             new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.id("validateButton"))).click();
            new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ui-button-icon"))).click();
            
             
           }
           else  if(ds.equals("Prevention")){                         
               
               //enterViralSuppression(driver);
               enterPrevention(conn,driver,prdhm);
             
           }
           
             else  if(ds.equals("Viral Suppression")){                         
               
               enterViralSuppression(conn,driver,prdhm);
             
             
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

    
    public void enterPrevention(dbConn conn,WebDriver driver,HashMap<String,String> hm){
    
        
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
    
    public  void enterViralSuppression(dbConn conn,WebDriver driver,HashMap<String,String> hm) throws InterruptedException{
    
        
  if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.presenceOfElementLocated(By.id("AakrBNBU2G4-GILufDV2tEB-val"))).isDisplayed()){
        
 
  
    
    
    WebElement uam= driver.findElement(By.id("AakrBNBU2G4-GILufDV2tEB-val")); uam.clear();uam.sendKeys("");
 
    uam=driver.findElement(By.id("AakrBNBU2G4-GILufDV2tEB-val")); uam.clear();uam.sendKeys("");
   uam= driver.findElement(By.id("AakrBNBU2G4-dRjezxQktoz-val")); uam.clear();uam.sendKeys("");
    driver.findElement(By.id("AakrBNBU2G4-zLbjm4E1NsG-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-QqlHrg6f0Sm-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-zqARzn2wVj5-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-hRq4baaUamW-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-J8fGj3Iefbc-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-VdMgEQkOtuV-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-ay6Bp2UGIE5-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-YOMb0sp9VLV-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-undUoo1tECg-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-jjUGfPF0ObP-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-tEMe0224zlP-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-NCnIv37EwU1-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-YDzeLL6xf5o-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-zouTxRQ0kXP-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-i1yTaswn4qW-val")).click();
    driver.findElement(By.id("AakrBNBU2G4-lNnwcd2RT4K-val")).click();
    driver.findElement(By.id("L2n6ajKliOT-vxBSF1mguas-val")).click();
    driver.findElement(By.id("L2n6ajKliOT-jaxEUorPKgv-val")).click();
    driver.findElement(By.id("kegCp8t3TVW-FyhQbdBMM1p-val")).click();
    driver.findElement(By.id("kegCp8t3TVW-fCiy8R7Dv9x-val")).click();
    driver.findElement(By.id("kegCp8t3TVW-dUCNvz8ByrS-val")).click();
    driver.findElement(By.id("kegCp8t3TVW-VCEoYHLyTxk-val")).click();
    
    //TX_PVLS_D
    
    driver.findElement(By.id("YMfWvFuB5kH-GILufDV2tEB-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-dRjezxQktoz-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-zLbjm4E1NsG-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-AG0milXShQM-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-LyXZybq6Sjf-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-Vi8sd7mvZW4-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-PEXIFVXGP9S-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-VdMgEQkOtuV-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-ay6Bp2UGIE5-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-YOMb0sp9VLV-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-undUoo1tECg-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-jjUGfPF0ObP-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-tEMe0224zlP-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-NCnIv37EwU1-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-YDzeLL6xf5o-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-zouTxRQ0kXP-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-zUjkTTlva36-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-i1yTaswn4qW-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-N9EXA1gNh16-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-lNnwcd2RT4K-val")).click();
    driver.findElement(By.id("YMfWvFuB5kH-Yy619I6uuTP-val")).click();
    driver.findElement(By.id("PkVxxyOLky3-vxBSF1mguas-val")).click();
    driver.findElement(By.id("PkVxxyOLky3-jaxEUorPKgv-val")).click();
    driver.findElement(By.id("HOtmew3ZhxO-FyhQbdBMM1p-val")).click();
    driver.findElement(By.id("HOtmew3ZhxO-wbJ9Nh2jqUG-val")).click();
    driver.findElement(By.id("HOtmew3ZhxO-fCiy8R7Dv9x-val")).click();
    driver.findElement(By.id("HOtmew3ZhxO-dUCNvz8ByrS-val")).click();
    //driver.navigate().refresh();
    
          }
    
    }
    
    public  void enterTX_ML(dbConn conn,WebDriver driver,HashMap<String,String> basics) throws InterruptedException{
    
        //Run the query for pulling all sites data from IMIS
        //convert the resultset into a string hashmap 
        //on the hashmap, add a metadata containing the columns and create a coma separated string that should be used to split the variables into an array 
        //on the hashmap, add a metadata containing the columns and create a coma separated string that should be used to split the variables into an array 
        
        String entrytype=basics.get("entrytype");
        try {
            String qry="call internal_system.sp_dbot_TX_ML_20_Jan_mar('"+basics.get("sd")+"','"+basics.get("ed")+"');";
            
            System.out.println("query:"+qry);
            
           HashMap<String, String> alldata = new HashMap< >();
            
         
            alldata=convertDatimResultSetToMap(getAnyDataFromDb(conn,qry));
            
            String dfacil=basics.get("hf");          
            
            if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.presenceOfElementLocated(By.id("A5A8LKqJw4w-TtohM6zck1v-val"))).isDisplayed()){
                
                
                //get all columns
                String clmns=alldata.get("indicator_metadata");
                String clmnar[]=clmns.split(",");
              //St. Joseph RiftValley Hospital_Facility
              String siteexits=alldata.get(dfacil+"_Facility");
                System.out.println("Check if site existsts:::"+siteexits);
                if(siteexits!=null)
                {
                
            for(int cl=0;cl<clmnar.length;cl++)
            {
                
              WebElement uam= null;
              if(!clmnar[cl].equals("Facility")){
                  String val=alldata.get(dfacil+"_"+clmnar[cl]);
                  
                  switch (entrytype) {
                      case "nonzero":
                          //here, only non zero numeric values are entered. This excludes Zeros
                          if(!val.equals("0.0") && !val.equals("0") ){
                              
                              uam=driver.findElement(By.id(clmnar[cl]));
                             
                              uam.click();
                              uam.clear();
                              System.out.println("What is entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                              val=val.replace(".0","");
                              
                              
                              
                              uam.sendKeys(val);
                          }           break;
                      case "allnumeric":
                          //here, all the data elements are entered with all numeric values including zeros
                          
                          uam=driver.findElement(By.id(clmnar[cl]));
                         
                         
                           
                          System.out.println("What is entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                           val=val.replace(".0","");
                           
                           String ic=uam.getAttribute("value");
                           System.out.println("ic value:::"+ic);
                           if((isNumeric(ic) && new Integer(ic)>0) || ic.equals("") || ic.equals("0"))
                           {
                               System.out.println("ic value inside is:::"+ic);
                               
                           if(!ic.equals(val)){
                               //dont replace blank with 0s
                               if(!(val.equals("0")&&ic.equals(""))){
                                   
                                uam.click();
                             uam.clear();  
                          uam.sendKeys(val);
                               }
                           }
                            }
                          break;
                      case "enterblanks":
                          //here, only blank values are entered. This excludes Zeros
                          uam=driver.findElement(By.id(clmnar[cl]));
                          
                          uam.click();
                          uam.clear();
                          uam.sendKeys("");
                          System.out.println("Zeros entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                          break;
                      default:
                          break;
                  }
                 
                  
                 
                  }
                                
            }
          //validateButton
                    //ui-button-icon ui-icon ui-icon-closethick
                
            }  //checking if site exists
                
                else {
                    System.out.println("Site Does not exist in Datim list::::::");
                    
                }
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutoDataEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       //  new WebDriverWait(driver, Duration.ofSeconds(150)).until(ExpectedConditions.elementToBeClickable(By.linkText("Treatment"))).click();
    
    }
    
    
    public  void enterTX_RTT(dbConn conn,WebDriver driver,HashMap<String,String> basics) throws InterruptedException{
    
        //Run the query for pulling all sites data from IMIS
        //convert the resultset into a string hashmap 
        //on the hashmap, add a metadata containing the columns and create a coma separated string that should be used to split the variables into an array 
        //on the hashmap, add a metadata containing the columns and create a coma separated string that should be used to split the variables into an array 
        
        String entrytype=basics.get("entrytype");
        try {
            String qry="call internal_system.sp_dbot_TX_RTT('"+basics.get("sd")+"','"+basics.get("ed")+"');";
            
            System.out.println("query:"+qry);
            
           HashMap<String, String> alldata = new HashMap< >();
            
         
            alldata=convertDatimResultSetToMap(getAnyDataFromDb(conn,qry));
            
            String dfacil=basics.get("hf");          
            
            if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.presenceOfElementLocated(By.id("in0Xpzvlr33-bxf9A4AqmpS-val"))).isDisplayed()){
                
                
                //get all columns
                String clmns=alldata.get("indicator_metadata");
                String clmnar[]=clmns.split(",");
              //St. Joseph RiftValley Hospital_Facility
              String siteexits=alldata.get(dfacil+"_Facility");
                System.out.println("Check if site existsts:::"+siteexits);
                if(siteexits!=null)
                {
                
            for(int cl=0;cl<clmnar.length;cl++)
            {
                
              WebElement uam= null;
              if(!clmnar[cl].equals("Facility")){
                  String val=alldata.get(dfacil+"_"+clmnar[cl]);
                  
                  switch (entrytype) {
                      case "nonzero":
                          //here, only non zero numeric values are entered. This excludes Zeros
                          if(!val.equals("0.0") && !val.equals("0") ){
                              
                              uam=driver.findElement(By.id(clmnar[cl]));
                             
                              uam.click();
                              uam.clear();
                              System.out.println("What is entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                              val=val.replace(".0","");
                              
                              
                              
                              uam.sendKeys(val);
                          }           break;
                      case "allnumeric":
                          //here, all the data elements are entered with all numeric values including zeros
                          
                          uam=driver.findElement(By.id(clmnar[cl]));
                         
                         
                           
                          System.out.println("What is entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                           val=val.replace(".0","");
                           
                           String ic=uam.getAttribute("value");
                           System.out.println("ic value:::"+ic);
                           if((isNumeric(ic) && new Integer(ic)>0) || ic.equals("") || ic.equals("0"))
                           {
                               System.out.println("ic value inside is:::"+ic);
                               
                           if(!ic.equals(val)){
                               //dont replace blank with 0s
                               if(!(val.equals("0")&&ic.equals(""))){
                                   
                                uam.click();
                             uam.clear();  
                          uam.sendKeys(val);
                               }
                           }
                            }
                          break;
                      case "enterblanks":
                          //here, only blank values are entered. This excludes Zeros
                          uam=driver.findElement(By.id(clmnar[cl]));
                          
                          uam.click();
                          uam.clear();
                          uam.sendKeys("");
                          System.out.println("Zeros entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                          break;
                      default:
                          break;
                  }
                 
                  
                 
                  }
                                
            }
          //validateButton
                    //ui-button-icon ui-icon ui-icon-closethick
                
            }  //checking if site exists
                
                else {
                    System.out.println("Site Does not exist in Datim list::::::");
                    
                }
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutoDataEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       //  new WebDriverWait(driver, Duration.ofSeconds(150)).until(ExpectedConditions.elementToBeClickable(By.linkText("Treatment"))).click();
    
    }
    
    public  void enterTX_HTN(dbConn conn,WebDriver driver,HashMap<String,String> basics) throws InterruptedException{
    
        //Run the query for pulling all sites data from IMIS
        //convert the resultset into a string hashmap 
        //on the hashmap, add a metadata containing the columns and create a coma separated string that should be used to split the variables into an array 
        //on the hashmap, add a metadata containing the columns and create a coma separated string that should be used to split the variables into an array 
        
        String entrytype=basics.get("entrytype");
        try {
            String qry="call internal_system.sp_dbot_HPT('"+basics.get("sd")+"','"+basics.get("ed")+"');";
            
            System.out.println("query:"+qry);
            
           HashMap<String, String> alldata = new HashMap< >();
            
         
            alldata=convertDatimResultSetToMap(getAnyDataFromDb(conn,qry));
            
            String dfacil=basics.get("hf");          
            
            if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.presenceOfElementLocated(By.id("oDe9APeim8V-T8xKXv1IFV3-val"))).isDisplayed()){
                
                
                //get all columns
                String clmns=alldata.get("indicator_metadata");
                String clmnar[]=clmns.split(",");
              //St. Joseph RiftValley Hospital_Facility
              String siteexits=alldata.get(dfacil+"_Facility");
              
                System.out.println("Check if site existsts:::"+siteexits);
                if(siteexits!=null)
                {
                
            for(int cl=0;cl<clmnar.length;cl++)
            {
                
              WebElement uam= null;
              if(!clmnar[cl].equals("Facility")){
                  String val=alldata.get(dfacil+"_"+clmnar[cl]);
                  
                  switch (entrytype) {
                      case "nonzero":
                          //here, only non zero numeric values are entered. This excludes Zeros
                          if(!val.equals("0.0") && !val.equals("0") ){
                              
                              uam=driver.findElement(By.id(clmnar[cl]));
                             
                              uam.click();
                              uam.clear();
                              System.out.println("What is entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                              val=val.replace(".0","");
                              
                              
                              
                              uam.sendKeys(val);
                          }           break;
                      case "allnumeric":
                          //here, all the data elements are entered with all numeric values including zeros
                          
                          uam=driver.findElement(By.id(clmnar[cl]));
                         
                         
                           
                          System.out.println("What is entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                           val=val.replace(".0","");
                           
                           String ic=uam.getAttribute("value");
                           System.out.println("ic value:::"+ic);
                           if((isNumeric(ic) && new Integer(ic)>0) || ic.equals("") || ic.equals("0"))
                           {
                               System.out.println("ic value inside is:::"+ic);
                               
                           if(!ic.equals(val)){
                               //dont replace blank with 0s
                               if(!(val.equals("0")&&ic.equals(""))){
                                   
                                uam.click();
                             uam.clear();  
                          uam.sendKeys(val);
                               }
                           }
                            }
                          break;
                      case "enterblanks":
                          //here, only blank values are entered. This excludes Zeros
                          uam=driver.findElement(By.id(clmnar[cl]));
                          
                          uam.click();
                          uam.clear();
                          uam.sendKeys("");
                          System.out.println("Zeros entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                          break;
                      default:
                          break;
                  }
                 
                  
                 
                  }
                                
            }
          //validateButton
                    //ui-button-icon ui-icon ui-icon-closethick
                
            }  //checking if site exists
                
                else {
                    System.out.println("Site Does not exist in Datim list::::::");
                    
                }
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutoDataEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       //  new WebDriverWait(driver, Duration.ofSeconds(150)).until(ExpectedConditions.elementToBeClickable(By.linkText("Treatment"))).click();
    
    }
    
    public  void enterTX_TB(dbConn conn,WebDriver driver,HashMap<String,String> basics) throws InterruptedException{
    
        //Run the query for pulling all sites data from IMIS
        //convert the resultset into a string hashmap 
        //on the hashmap, add a metadata containing the columns and create a coma separated string that should be used to split the variables into an array 
        //on the hashmap, add a metadata containing the columns and create a coma separated string that should be used to split the variables into an array 
        
        String entrytype=basics.get("entrytype");
        try {
            String qry="call internal_system.sp_dbot_TXTB('"+basics.get("sd")+"','"+basics.get("ed")+"');";
            
            System.out.println("query:"+qry);
            
           HashMap<String, String> alldata = new HashMap< >();
            
         
            alldata=convertDatimResultSetToMap(getAnyDataFromDb(conn,qry));
            
            String dfacil=basics.get("hf");          
            
            if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.presenceOfElementLocated(By.id("oDe9APeim8V-T8xKXv1IFV3-val"))).isDisplayed()){
                
                
                //get all columns
                String clmns=alldata.get("indicator_metadata");
                String clmnar[]=clmns.split(",");
              //St. Joseph RiftValley Hospital_Facility
              String siteexits=alldata.get(dfacil+"_Facility");
                System.out.println("Check if site existsts:::"+siteexits);
                if(siteexits!=null)
                {
                
            for(int cl=0;cl<clmnar.length;cl++)
            {
                
              WebElement uam= null;
              if(!clmnar[cl].equals("Facility")){
                  String val=alldata.get(dfacil+"_"+clmnar[cl]);
                  
                  switch (entrytype) {
                      case "nonzero":
                          //here, only non zero numeric values are entered. This excludes Zeros
                          if(!val.equals("0.0") && !val.equals("0") ){
                              
                              uam=driver.findElement(By.id(clmnar[cl]));
                             
                              uam.click();
                              uam.clear();
                              System.out.println("What is entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                              val=val.replace(".0","");
                              
                              
                              
                              uam.sendKeys(val);
                          }           break;
                      case "allnumeric":
                          //here, all the data elements are entered with all numeric values including zeros
                          
                          uam=driver.findElement(By.id(clmnar[cl]));
                         
                         
                           
                          System.out.println("What is entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                           val=val.replace(".0","");
                           
                           String ic=uam.getAttribute("value");
                           System.out.println("ic value:::"+ic);
                           if((isNumeric(ic) && new Integer(ic)>0) || ic.equals("") || ic.equals("0"))
                           {
                               System.out.println("ic value inside is:::"+ic);
                               
                           if(!ic.equals(val)){
                               //dont replace blank with 0s
                               if(!(val.equals("0")&&ic.equals(""))){
                                   
                                uam.click();
                             uam.clear();  
                          uam.sendKeys(val);
                               }
                           }
                            }
                          break;
                      case "enterblanks":
                          //here, only blank values are entered. This excludes Zeros
                          uam=driver.findElement(By.id(clmnar[cl]));
                          
                          uam.click();
                          uam.clear();
                          uam.sendKeys("");
                          System.out.println("Zeros entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                          break;
                      default:
                          break;
                  }
                 
                  
                 
                  }
                                
            }
          //validateButton
                    //ui-button-icon ui-icon ui-icon-closethick
                
            }  //checking if site exists
                
                else {
                    System.out.println("Site Does not exist in Datim list::::::");
                    
                }
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutoDataEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       //  new WebDriverWait(driver, Duration.ofSeconds(150)).until(ExpectedConditions.elementToBeClickable(By.linkText("Treatment"))).click();
    
    }
    
    
    
       public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
     
private HashMap<String,String> convertDatimResultSetToMap(ResultSet rs) throws SQLException{
          
          

      
HashMap<String,String> hm = new HashMap<String,String>();
          
ResultSetMetaData md = rs.getMetaData();
          
           int columns = md.getColumnCount();
           int ct=0;
           while (rs.next())
           {
               String cols="";
           
           String facilid=rs.getString("Facility");
           
               for(int a=1;a<=columns;a++)
               {
                   if(ct==0)
                   {
                   cols+=md.getColumnName(a)+",";
                   
                   
                   if(a==columns){
                        hm.put("indicator_metadata",cols); 
                
             System.out.println("Added:indicator_metadata"+cols);
             
                                 }
                   }
                   String colname=md.getColumnName(a);
                   
                                  
                hm.put(facilid+"_"+colname, rs.getString(colname));
                
                   //System.out.println("Added:"+facilid+"_"+colname+","+rs.getString(colname));
                  
               
               }               
                
                   
           ct++;
           }          
          
          
      
      return hm;
      }
      
             public List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException 
 {
    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();
    List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

    while (rs.next()) 
    {
        HashMap<String,Object> row = new HashMap<String, Object>(columns);
        for(int i=1; i<=columns; ++i) {
            row.put(md.getColumnName(i),rs.getObject(i));
        }
        list.add(row);
    }

    return list;
}
  
             
             
public ResultSet getAnyDataFromDb(dbConn conn, String query) throws SQLException{
    
        return conn.st2.executeQuery(query);
        
        
    }
             
    
}
