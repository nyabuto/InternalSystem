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
public class ehts_ML extends HttpServlet {

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
            throws ServletException, IOException, InterruptedException, SQLException {
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
        
      
       
        dbConn conn= new dbConn();
        
       HashMap<String,String> prdhm = new HashMap<String, String>();
       
     
        
      
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
                
                
    	
        String baseUrl = "https://docs.google.com/forms/d/1cEQyH3WJIcpNr57CDOqJmGq9U9gLMrUo11Uty-k-k1g/viewform?edit_requested=true";
        
        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);
driver.manage().window().maximize();



String qry="select * from machinelearning where submited=0 limit 29";

conn.rs=conn.st.executeQuery(qry);

while(conn.rs.next()){

   if(new WebDriverWait(driver, Duration.ofSeconds(200)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Qr7Oae:nth-child(1) .whsOnd"))).isDisplayed()){

    driver.findElement(By.cssSelector(".Qr7Oae:nth-child(1) .whsOnd")).sendKeys(conn.rs.getString("Your_name"));
    
    driver.findElement(By.cssSelector(".Qr7Oae:nth-child(2) .whsOnd")).sendKeys(conn.rs.getString("Your_email_address"));
    
    driver.findElement(By.cssSelector(".Qr7Oae:nth-child(3) .whsOnd")).sendKeys("USAID Tujenge Jamii");

    driver.findElement(By.cssSelector(".Qr7Oae:nth-child(4) .whsOnd")).sendKeys(conn.rs.getString("county"));

    driver.findElement(By.cssSelector(".Qr7Oae:nth-child(5) .whsOnd")).sendKeys(conn.rs.getString("subcounty"));
    
    driver.findElement(By.cssSelector(".Qr7Oae:nth-child(6) .whsOnd")).sendKeys(conn.rs.getString("facility"));

    driver.findElement(By.cssSelector(".Qr7Oae:nth-child(7) .whsOnd")).sendKeys(conn.rs.getString("mflcode"));
    
   
    
     // ART Sites
    String htssites_yes="#i33 .AB7Lab";
    String htssites_no="#i36 .AB7Lab";
    String htssites_seleted="";  
    //if condition
    if(conn.rs.getString("ishts").equals("Yes")){htssites_seleted=htssites_yes;} else {htssites_seleted=htssites_no;}
   driver.findElement(By.cssSelector(htssites_seleted)).click();
    
    
    
    
    
    
    // ART Sites
    String artsites_yes="#i43 .AB7Lab";
    String artsites_no="#i46 .AB7Lab";
    String artsites_seleted="";  
    //if condition
    if(conn.rs.getString("isart").equals("Yes")){artsites_seleted=artsites_yes;} else {artsites_seleted=artsites_no;}
   driver.findElement(By.cssSelector(artsites_seleted)).click();
    
    
    
  
    driver.findElement(By.cssSelector(".KHxj8b")).sendKeys(conn.rs.getString("sdps"));
  
    // Are eHTS tablets or devices used in each HTS service delivery point
    String ehtsdevices_yes="#i58 > .uHMk6b";
    String ehtsdevices_no="#i61 > .uHMk6b";
    String ehtsdevices_seleted="";  
    //if condition
    if(conn.rs.getString("are_tablets_used_in_allsdps").equals("Yes")){ehtsdevices_seleted=ehtsdevices_yes;} else {ehtsdevices_seleted=ehtsdevices_no;}
   driver.findElement(By.cssSelector(ehtsdevices_seleted)).click();
    
    
    
    
    driver.findElement(By.cssSelector(".Qr7Oae:nth-child(12) .whsOnd")).sendKeys(conn.rs.getString("no_of_providers"));    
    driver.findElement(By.cssSelector(".Qr7Oae:nth-child(13) .whsOnd")).sendKeys(conn.rs.getString("devices_assigned_to_HTS"));    
    driver.findElement(By.cssSelector(".Qr7Oae:nth-child(14) .whsOnd")).sendKeys(conn.rs.getString("afyastat_only_devices"));   
    driver.findElement(By.cssSelector(".Qr7Oae:nth-child(15) .whsOnd")).sendKeys(conn.rs.getString("KenyaEMR_only_devices"));   
    driver.findElement(By.cssSelector(".Qr7Oae:nth-child(16) .whsOnd")).sendKeys(conn.rs.getString("keemr_afyastat_combined"));
    
    
    String dwh_yes="#i89 > .uHMk6b";
    String dwh_no="#i92 > .uHMk6b";
    String dwh_seleted="";  
    //if condition
  if(conn.rs.getString("ehts_dwh_upload").equals("Yes")){dwh_seleted=dwh_yes;} else {dwh_seleted=dwh_no;}
   driver.findElement(By.cssSelector(dwh_seleted)).click();
    
    
    
    String mlhts_yes="#i100 > .uHMk6b";
    String mlhts_no="#i103 > .uHMk6b";
    String mlhts_seleted="";
    
    if(conn.rs.getString("Ml_hts").equals("Yes")){mlhts_seleted=mlhts_yes;} else {mlhts_seleted=mlhts_no;}
    
    driver.findElement(By.cssSelector(mlhts_seleted)).click();//ML Hts Yes
    
    
    
    String mliit_yes="#i110 .AB7Lab";
    String mliit_no="#i113 .AB7Lab";
    String mliit_seleted="";
     if(conn.rs.getString("ml_for_iit").equals("Yes")){mliit_seleted=mliit_yes;} else {mliit_seleted=mliit_no;}
    driver.findElement(By.cssSelector(mliit_seleted)).click();//ML IIT
    
   // conn.st1.executeUpdate("update internal_system.machinelearning set submited=1 where mflcode='"+conn.rs.getString("mflcode")+"'");
    
//driver.findElement(By.cssSelector(".Y5sE8d > .l4V7wb")).click();   //submit


//driver.findElement(By.cssSelector(".c2gzEf")).click();   //submit
  driver.get(baseUrl);   
    //driver.findElement(By.cssSelector(".l3F1ye .NPEfkd")).click();//clear form
    //driver.findElement(By.cssSelector(".RveJvd .snByac .NPEfkd")).click(); //excecute clear
      
    
}
    }
    
    
    
//login.click();
    //WebElement clickapps=driver.findElement(By.cssSelector("[data-test=headerbar-apps-icon] > svg > path"));
//clickapps.click();
   // WebElement dataentry=driver.findElement(By.linkText("Data Entry"));    
    
//     WebElement country=driver.findElement(By.cssSelector("#orgUnitHfVjCurKxh2 img"));
//     WebElement county=driver.findElement(By.cssSelector("#orgUnitTbq3JMDALZt img"));
//     //https://www.datim.org
//     country.click();




        
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
        } catch (SQLException ex) {
            Logger.getLogger(ehts_ML.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(ehts_ML.class.getName()).log(Level.SEVERE, null, ex);
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
