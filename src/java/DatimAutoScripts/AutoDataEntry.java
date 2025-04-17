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
import static junit.framework.Assert.assertEquals;

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
        String scts_arr[]=null;
        String forms_arr[]=null;
        
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
        scts_arr=request.getParameterValues("ds[]");
        forms_arr=request.getParameterValues("specificform[]");
        
        
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
options.addArguments("--remote-debugging-port=7045");

//options.addArguments("user-data-dir=C:\\Users\\Emmanuel Kaunda\\AppData\\Local\\Google\\Chrome\\User Data");
options.addArguments("user-data-dir=C:\\HSDSA\\Selenium");
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
                
                
    	
       // String baseUrl = "https://state.okta.com/oauth2/v1/authorize?response_type=code&client_id=0oaqewjmeqf4J8YH8297&scope=openid%20email&state=2H1FADRx0E6JYVIwteqJFAdGrdunYMTypRkyOxmNEYQ%3D&redirect_uri=https://www.datim.org/oauth2/code/okta&nonce=zy20h3yQpCHEaWnfb067B9ZbOzZKq6G2N4rWoVE5wDk";
        String baseUrl = "https://www.datim.org/dhis-web-dataentry/index.action";
        
        // launch chrome and direct it to the Base URL
        driver.get(baseUrl);
driver.manage().window().maximize();
 //Opens a new tab and switches to new tab
        //driver.switchTo().newWindow(WindowType.TAB);
        //assertEquals("",driver.getTitle());
        
        
        
//WebElement accept=driver.findElement(By.cssSelector("button"));
//        accept.click();
//        // get the actual value of the title
//WebElement username=driver.findElement(By.id("identifier"));
//WebElement password=driver.findElement(By.id("j_password"));
//WebElement login=driver.findElement(By.id("submit"));
//username.sendKeys("emaingi@usaidtujengejamii.org");
//password.sendKeys("Plusaphia2013!!");
//login.click();

/****OCTA Verification*****/
if(1==2){
if(new WebDriverWait(driver, Duration.ofSeconds(200)).until(ExpectedConditions.elementToBeClickable(By.id("input28"))).isEnabled())
       {
  //driver.manage().window().setSize(new Dimension(1936, 1056));
    driver.findElement(By.id("input28")).clear();
    driver.findElement(By.id("input28")).sendKeys("emaingi@usaidtujengejamii.org");
    driver.findElement(By.id("signin-container")).click();
    
      //System.out.println("initiate 10 seconds timeout");
          // Thread.sleep(20000);
          //   System.out.println(" 5 seconds timeout completed");
    
//    driver.findElement(By.cssSelector(".hover")).click();
    driver.findElement(By.cssSelector(".button")).click();
    
    if(new WebDriverWait(driver, Duration.ofSeconds(200)).until(ExpectedConditions.elementToBeClickable(By.id("input64"))).isEnabled()){
    driver.findElement(By.id("input64")).sendKeys("");
    driver.findElement(By.cssSelector(".button")).click();
    }
       if(new WebDriverWait(driver, Duration.ofSeconds(200)).until(ExpectedConditions.elementToBeClickable(By.id("input90"))).isEnabled()){
           
              //Thread.sleep(20000);
   // driver.findElement(By.id("input90")).sendKeys("Plusaphia2013!");
   // driver.findElement(By.cssSelector(".button")).click();
    }
//    System.out.println("initiate 10 seconds timeout");
//           Thread.sleep(20000);
//             System.out.println(" 5 seconds timeout completed");
//    
    //driver.findElement(By.id("input90")).sendKeys("146833");
    //driver.findElement(By.cssSelector(".button")).click();
    
    
       }

        }//disable login via selenium 

/**********/
    //WebElement clickapps=driver.findElement(By.cssSelector("[data-test=headerbar-apps-icon] > svg > path"));
//clickapps.click();
   // WebElement dataentry=driver.findElement(By.linkText("Data Entry"));    
        String dataentryurl="https://www.datim.org/dhis-web-dataentry/index.action";
       // driver.get(dataentryurl);
     //Thread.sleep(20000);
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

int lmt=1;
for(int a=0;a<sites.length;a++)
{    
    //add facility name to list
    
     prdhm.put("hf", sites[a]);
     
     
    System.out.println("Facility:"+sites[a]);
   out.println("Facility:"+sites[a]+"<br/>"); 
   
    if(!sites[a].equals("")){

if(new WebDriverWait(driver, Duration.ofSeconds(200)).until(ExpectedConditions.elementToBeClickable(By.id("searchField"))).isDisplayed()){
    searchfield.clear();
    
}
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
        for(int b=0;b<scts_arr.length;b++){
        
            
//WebElement clicktab=driver.findElement(By.id(datim_tabs[b]));
            //div[@id='PEPFAR_Tabs_vertical']/ul/li[4]/a
         //WebElement clicktab = new WebDriverWait(driver,Duration.ofSeconds(100)).until(ExpectedConditions.visibilityOfElementLocated(By.id("PEPFAR_Tabs_vertical")));
         //clicktab.click();
        if(new WebDriverWait(driver, Duration.ofSeconds(200)).until(ExpectedConditions.elementToBeClickable(By.linkText("Prevention"))).isDisplayed()){
            
            //click the section name the first time only
         
             if(lmt==1){ }
             
             if(b==0){
             System.out.println("initiate 10 seconds timeout");
           Thread.sleep(10000);
             System.out.println(" 10 seconds timeout completed");
             }
             else {
                  System.out.println("initiate 2 seconds timeout");
           Thread.sleep(2000);
             System.out.println(" 2 seconds timeout completed");
             
             }
             if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.linkText(scts_arr[b]))).isDisplayed()){ 
                   System.out.println(scts_arr[b]);
                 new WebDriverWait(driver, Duration.ofSeconds(150)).until(ExpectedConditions.elementToBeClickable(By.linkText(scts_arr[b]))).click();
               //driver.findElement(By.linkText("Viral Suppression")).click();
             }
              // new WebDriverWait(driver, Duration.ofSeconds(100),Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.linkText("Viral Suppression"))).click();
            
              
           //driver.findElement(By.linkText("Viral Suppression")).click();
           
           //loop through the indicators
           for(int ay=0;ay<forms_arr.length;ay++)
           {
               
               System.out.println("Form being entered"+forms_arr[ay]);
               
          // if(datim_tabs[b].equals("Prevention")){
//           if(scts_arr[ay].equals("Treatment")){
                          
               
//               //enterViralSuppression(driver);
//               if(specificform.equals("TXML"))
//               {
//              
             enterAnyData(conn,driver,prdhm,scts_arr[b],forms_arr[ay]);
//               }
//               else if(specificform.equals("HPT"))
//               {              
//                enterTX_HTN(conn,driver,prdhm);
//               }
//               else if(specificform.equals("RTT"))
//               {              
//                
//                enterTX_RTT(conn,driver,prdhm);
//               }
//               else if(specificform.equals("TXTB"))
//               {              
//                
//                enterTX_TB(conn,driver,prdhm);
//               }
//               else  
//               {
//               enterTX_RTT(conn,driver,prdhm);
//               //enterTX_HTN(conn,driver,prdhm);
//               enterTX_ML(conn,driver,prdhm);
//               enterTX_TB(conn,driver,prdhm);
//               }
               
               
           
             
           }
//           else  if(scts_arr[ay].equals("Prevention")){                         
//               
//               //enterViralSuppression(driver);
//               enterPrevention(conn,driver,prdhm);
//             
//           }
           
//             else  if(scts_arr[ay].equals("Viral Suppression")){                         
//               
//              // enterViralSuppression(conn,driver,prdhm);
//             
//             
//           }
       // } 
       
        //Validate and close   
           
           
        
        }
         //clicktab1.click();
        //new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(datim_tabs_show_hid[b]))).click();
         //#PEPFAR_Form_1_DSD > .PEPFAR_Form_ShowHide
         
       //  clickcollapse.click();
         
        //driver.findElement(By.id("ui-id-27")).click();
        // driver.findElement(By.cssSelector("#PEPFAR_Form_4_DSD > .PEPFAR_Form:nth-child(10) > .PEPFAR_Form_Container")).click();
         
       
           JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
         
}
           new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.id("validateButton"))).click();
            new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ui-button-icon"))).click();
            
        
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

    
 
    
      public  void enterAnyData(dbConn conn,WebDriver driver,HashMap<String,String> basics, String section, String Storedprocedure) throws InterruptedException{
    
        try {
            //Run the query for pulling all sites data from IMIS
            //convert the resultset into a string hashmap
            //on the hashmap, add a metadata containing the columns and create a coma separated string that should be used to split the variables into an array
            //on the hashmap, add a metadata containing the columns and create a coma separated string that should be used to split the variables into an array
            
            String getdetailsqry="select * from datimbotqueries where spname in ('"+Storedprocedure+"') and Section in ('"+section+"') limit 1";
            
            conn.rs1=conn.st1.executeQuery(getdetailsqry);
            String firstelement="";
            String reportingperiod="";
            while(conn.rs1.next())
            {
                firstelement=conn.rs1.getString("startingelemnt");
                reportingperiod=conn.rs1.getString("frequency");
                System.out.println("Starting Element is "+firstelement);
            }
            System.out.println("pull query details::"+getdetailsqry);
            String startd=basics.get("sd");
            //check the reporting period and skip some indicators
            if((reportingperiod.equals("QA"))|| (reportingperiod.equals("SA") && (startd.contains("-03-01") || startd.contains("-07-01") )) || (reportingperiod.equals("AN") && (startd.contains("-07-01") )) ){
                
                String entrytype=basics.get("entrytype");
                try {
                    String qry="call internal_system."+Storedprocedure+"(\""+basics.get("sd")+"\",\""+basics.get("ed")+"\",\""+basics.get("hf")+"\");";
                    
                    System.out.println("query:"+qry);
                    
                    HashMap<String, String> alldata = new HashMap< >();
                    
                    
                    alldata=convertDatimResultSetToMap(getAnyDataFromDb(conn,qry));
                    System.out.println("alldata size is "+alldata.size());
                    String dfacil=basics.get("hf");
                    //The starting element has been picked automatically
                    if(alldata.size()>0){
                    if(new WebDriverWait(driver, Duration.ofSeconds(200)).until(ExpectedConditions.elementToBeClickable(By.linkText(section))).isDisplayed()){
                        
                        //new WebDriverWait(driver, Duration.ofSeconds(150)).until(ExpectedConditions.elementToBeClickable(By.linkText(section))).click();
                        
                        if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.presenceOfElementLocated(By.id(firstelement))).isDisplayed()){
                            
                            
                            //get all columns
                            String clmns=alldata.get("indicator_metadata");
                            
                            System.out.println("Size of clms is:"+clmns.length()+" and value is: "+clmns);
                            if(clmns.length()>0){
                            String clmnar[]=clmns.split(",");
                            //St. Joseph RiftValley Hospital_Facility
                            String siteexits=alldata.get(dfacil+"_Facility");
                            System.out.println("Check if site existsts:::"+siteexits);
                            if(siteexits!=null)
                            {
                                
                                for(int cl=0;cl<clmnar.length;cl++)
                                {
//                                    Actions act =  new Actions(driver);
//                                    act.moveToElement(driver.findElement(By.id(clmnar[cl]))).click().perform();
                                    WebElement uam= null;
                                    if(!clmnar[cl].equals("Facility") && !clmnar[cl].equals("datimname") ){
                                       
                                        
                                        String val="";
                                         if(alldata.get(dfacil+"_"+clmnar[cl])!=null){ val=alldata.get(dfacil+"_"+clmnar[cl]); }
                                        
                                        
                                        switch (entrytype) {
                                            case "nonzero":
                                                //here, only non zero numeric values are entered. This excludes Zeros
                                                if(!val.equals("0.0") && !val.equals("0") ){
                                                    
                                                    if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.presenceOfElementLocated(By.id(clmnar[cl]))).isDisplayed()){
                                                        
                                                    uam=driver.findElement(By.id(clmnar[cl])); 
                                                    
                                                    
                                                    
                                                     uam.click();
                                                    uam.clear();
                                                    System.out.println("What is entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                                                    val=val.replace(".0","");
                                                    
                                                    
                                                    
                                                    uam.sendKeys(val);
                                                    }
                                                    
                                                   
                                                }           break;
                                            case "allnumeric":
                                                //here, all the data elements are entered with all numeric values including zeros
                                                
                                                if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.presenceOfElementLocated(By.id(clmnar[cl]))).isDisplayed()){
                                                        
                                                    uam=driver.findElement(By.id(clmnar[cl])); 
                                                    }
                                                
                                                
                                                
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
                                            case "deletenonblanks":
                                                 if(!val.equals("0.0") && !val.equals("0") ){
                                                //here, only blank values are entered. This excludes Zeros
                                                if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.presenceOfElementLocated(By.id(clmnar[cl]))).isDisplayed()){
                                                        
                                                    uam=driver.findElement(By.id(clmnar[cl])); 
                                                    }
                                                
                                                uam.click();
                                                uam.clear();
                                                uam.sendKeys("");
                                                System.out.println("Zeros entered:"+dfacil+"_"+clmnar[cl]+"_"+val);
                                        }
                                                break;
                                                 case "enterblanks":
                                              
                                                //here, only blank values are entered. This excludes Zeros
                                                if(new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.presenceOfElementLocated(By.id(clmnar[cl]))).isDisplayed()){
                                                        
                                                    uam=driver.findElement(By.id(clmnar[cl])); 
                                                    }
                                                
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
                            
                        }//end of checking for blank sections
                        }//end of checking if fist element is displayed
                        
                    } //end of checking if section is displayed
                    
                    
                }//end of checking if that site has any data after runing the stored procedure
                } catch (SQLException ex) {
                    Logger.getLogger(AutoDataEntry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                
                System.out.println("Skip this Indicator, it is not entered during this quarter");
                
            }
            //  new WebDriverWait(driver, Duration.ofSeconds(150)).until(ExpectedConditions.elementToBeClickable(By.linkText("Treatment"))).click();
        } catch (SQLException ex) {
            Logger.getLogger(AutoDataEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
    
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
                   if(colname.equals("datimname")){colname="Facility";}
                  System.out.println("colname is :"+colname);
                  
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
