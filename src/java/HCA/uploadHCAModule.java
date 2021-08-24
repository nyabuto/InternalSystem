/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

 */

package HCA;

import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100) 

/**
 *
 * @author Emmanuel Kaunda
 */


  public class uploadHCAModule extends HttpServlet {
   
 
  
  String full_path="";
  String fileName="";
  int checker_dist,checker_hf;
  File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";
  String nextpage="";
  String quarterName,facilityName,facilityID,id,missingFacility;
          
  String fileNames="";

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
     String nomflsheets="";
      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
      int year,quarter,checker,missing = 0,added = 0,updated = 0;
      String county_name,county_id, district_name,district_id,hf_name,hf_id;
     fileNames="";
     fileName="";
 
     
     String ujumbe="";
     
    String user="unknown user";
    String userid="unknown user";
    
    
     
     HashMap<String, String> versions= new HashMap<String, String>();
     
     versions.put("HCA", "HEI Cohort Analysis  version 1.0.0");
     //versions.put("MCA", "Maternal Cohort Analysis (MCA) Version 2.0.0");
     
     int rowgani=1;
     int rowCount=26;
     
     
  id=""; 
String indicator="";
String indicatorid="";

String numerator="";
String denominator="";
String percentage="";



String mflcode="";
String reportingyear="";
String reportingmonth="";
String yearmonth="";
String birthyear="";
String birthyear12="";
String birthyear24="";

 



//___________________________________________________________________________________________________________



//___________________________________________________________________________________________________________



int filescount=0;



 
    String serialnumber="";
    
     String dbname="hei.results";
  
     
     session=request.getSession();
     
     
     if(session.getAttribute("username")!=null) {
         
     user=session.getAttribute("username").toString();
     userid=session.getAttribute("userid").toString();
     
     }
     
     
     
      if(session.getAttribute("filesngapi")!=null) 
      {
          
     int idadiyafiles= new Integer(session.getAttribute("filesngapi").toString());
     System.out.println(" Files za kuupload ni :"+idadiyafiles);  
     rowCount=rowCount*idadiyafiles;
     }
     
     
     dbConn conn = new dbConn();
     
     nextpage="hcaupload.jsp";
     
     
     String applicationPath = request.getServletContext().getRealPath("");
     
     String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
     
     session=request.getSession();
     
     File fileSaveDir = new File(uploadFilePath);
     
     if (!fileSaveDir.exists()) 
     {
         fileSaveDir.mkdirs();
     }
     
     System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
     
     for (Part part : request.getParts()) 
     {
         
         
          
         
         
         if(!getFileName(part).equals("") && getFileName(part).contains(".xlsx") )
         
         { 
         fileName = getFileName(part);
         part.write(uploadFilePath + File.separator + fileName);
         System.out.println("file name is  :  "+fileName);
         
         
         
         }
          if(!fileName.endsWith(".xlsx"))
          {          
       
          
          nextpage="hcaupload.jsp";
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");
          
          }
          
          else {
           
                
              
         fileNames+=fileName+", ";
         
         full_path=fileSaveDir.getAbsolutePath()+"/"+fileName;
         
         System.out.println("the saved file directory is  :  "+full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
         
         FileInputStream fileInputStream = new FileInputStream(full_path);
         XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
         
         int totalsheets=workbook.getNumberOfSheets();
     
        
         for(int a=0;a<totalsheets;a++)
         {
         
             
         XSSFSheet worksheet = workbook.getSheetAt(a);
         
         
          System.out.println( a+" ("+workbook.getSheetName(a)+") out of "+totalsheets+" sheets");
         
            
//______________________________________________________________________
         
//======================================================================HCA======================================================================================                 
//skip the instructions sheet              
if(!workbook.getSheetName(a).equals("Instructions")) 
{
System.out.println("Inside loop");
                 
                     //-----------facility name-----------------------
                     XSSFCell cellfacil = worksheet.getRow(0).getCell((short) 2);
                     
                     if(cellfacil.getCellType()==0){
                         //numeric
                         facilityName =""+(int)cellfacil.getNumericCellValue();
                     }
           else if(cellfacil.getCellType()==1)
                     {
                         facilityName =cellfacil.getStringCellValue();
                     }
                     
                     
             //-----------facility mfl code-----------------------
             XSSFCell cellmfl = worksheet.getRow(0).getCell((short) 4);
                     
             if(cellmfl.getCellType()==0){
                         //numeric
                         mflcode =""+(int)cellmfl.getNumericCellValue();
                                          }
       else if(cellmfl.getCellType()==1)
                     {   //string
                         mflcode =cellmfl.getStringCellValue().trim();
                     }
                     
                     
                     if(mflcode==null || mflcode.equals(""))
                     {
                     nomflsheets+=workbook.getSheetName(a)+"("+fileName+") ,";
                     }
                     
                     
                     //-----------year-----------------------
                     XSSFCell cellyear = worksheet.getRow(0).getCell((short) 8);
                     
                     if(cellyear.getCellType()==0)
                     {
                         //numeric
                         reportingyear =""+(int)cellyear.getNumericCellValue();
                     }
                     else if(cellyear.getCellType()==1)
                     {
                         reportingyear =cellyear.getStringCellValue();
                     }
                     
                 
                     
                     //-----------month-----------------------
                     XSSFCell cellmonth = worksheet.getRow(0).getCell((short) 6);
                     
                     if(cellmonth.getCellType()==0){
                         //numeric
                         reportingmonth =""+(int)cellmonth.getNumericCellValue();
                     }
                     else if(cellmonth.getCellType()==1){
                         reportingmonth =cellmonth.getStringCellValue();
                     }
                     
                      //-----------birthyear-----------------------
                     XSSFCell cellbirthyear12 = worksheet.getRow(5).getCell((short) 4);
                     
             switch (cellbirthyear12.getCellType()) {
                 case 0:
                     //numeric
                     birthyear12 =""+(int)cellbirthyear12.getNumericCellValue();
                     break;
                 case 1:
                     birthyear12 =cellbirthyear12.getStringCellValue();
                     break;
                 default:
                     birthyear12 =""+dateformat.format(cellbirthyear12.getDateCellValue());
                     break;
             }
                      
                      //-----------birthyear-----------------------
                     XSSFCell cellbirthyear24 = worksheet.getRow(25).getCell((short) 4);
                     
                     if(cellbirthyear24.getCellType()==0){
                         //numeric
                         birthyear24 =""+(int)cellbirthyear24.getNumericCellValue();
                     }
                     else if(cellbirthyear24.getCellType()==1){
                         birthyear24 =cellbirthyear24.getStringCellValue();
                     }
                     else {
                         birthyear24 =""+dateformat.format(cellbirthyear24.getDateCellValue());
                     }
                     
                     
                     
                     
                     
                      //_________________________Check Version______________________________ 
    
       String version="";
      XSSFCell cellversion = worksheet.getRow(4).getCell((short) 1);
                 
      
         if(cellversion.getCellType()==0)
                     {   //numeric
                         version =""+(int)cellversion.getNumericCellValue();
                     }
                     else if(cellversion.getCellType()==1)
                     {
                         version =cellversion.getStringCellValue();
                     }
      
         
                    System.out.println("Version"+version);
       
       
         
    //_________________________Check Reports Rules______________________________ 
    
/*  
19	Are all the denominator and numerator filled?	
20	Any case of numerator more than denominator?	

*/
    boolean haserror=false;
    String haserrorvalue="";
  
    if(1==2){
    for(int rq=19;rq<=24;rq++)
    {
    
          XSSFCell cellacaerrorval = worksheet.getRow(rq).getCell((short) 14);
          
          XSSFCell cellacaerrorlabel = worksheet.getRow(rq).getCell((short) 0);
              
          String errorvalue="";
          String errorlabel="";
          
          //_____error label______
           if(cellacaerrorlabel.getCellType()==0)
                     {   //numeric
                         errorlabel =""+(int)cellacaerrorlabel.getNumericCellValue();
                     }
                     else if(cellacaerrorlabel.getCellType()==1)
                     {
                         errorlabel =cellacaerrorlabel.getStringCellValue();
                     }
          
          
                         //_____error value______
                         switch (cellacaerrorval.getCellType()) {
                             case 0:
                                 //numeric
                                 System.out.println(" error value is Numeric ");
                                 errorvalue =""+(int)cellacaerrorval.getNumericCellValue();
                                 break;
                             case 1:
                                 System.out.println(" error value is String ");
                                 errorvalue =cellacaerrorval.getStringCellValue();
                                 break;
                             default:
                                 errorvalue =cellacaerrorval.getRawValue();
                                 System.out.println(" error value is raw value ");
                                 break;
                         }
                     
        if(errorvalue.equals("0"))
        {    
        
        }
   else {
            haserrorvalue+=errorlabel+" detected ";
        }
               
    }
                 }
        
        
        
  if(version.equals(versions.get("HCA")) && haserrorvalue.equals("") ){
       
       System.out.println(" No HCA error value or version ");
        
      
                 
         
         Iterator rowIterator = worksheet.rowIterator();
         
         
         int i=5,y=0;
         
         //static number of rows
         while(i<=38){
             try {
                 
                 rowgani++;
        session.setAttribute("hcapos", "<b>"+rowgani+"/"+rowCount+"</b>");
        session.setAttribute("hcapos_count", (rowgani*100)/rowCount);
                 
                 System.out.println(" row number "+i);
                 
                 XSSFRow rowi = worksheet.getRow(i);
                 if( rowi==null )
                 {
                     
                     break;
                 }
                 
                 if(i>=7 && i<=38) {
                     
                     //elements
                     
                     //indicator
                     indicator ="";
                     XSSFCell indiccell = rowi.getCell((short) 2);
                     
                     if(indiccell.getCellType()==0){
                         //numeric
                         indicator =""+indiccell.getRawValue();
                     }
                     else if(indiccell.getCellType()==1){
                         indicator =indiccell.getStringCellValue();
                     }
                     System.out.println("Indicator ni "+indicator);
                     
                     //numerator
                     
                     XSSFCell numcell = rowi.getCell((short) 3);
                     
                     switch (numcell.getCellType()) 
                     {
                         case 0:
                             //numeric
                             numerator =""+(int)numcell.getNumericCellValue();
                             break;
                         case 1:
                             numerator =numcell.getStringCellValue();
                             break;
                         default:
                             numerator =""+(int)numcell.getNumericCellValue();
                             break;
                     }
                     
                     if(numerator.trim().equals("")){numerator="0";}
                     
                     
                     //denominator
                     
                     XSSFCell dencell = rowi.getCell((short) 4);
                     
                     switch (dencell.getCellType()) 
                     {
                         case 0:
                             //numeric
                             denominator =""+(int)dencell.getNumericCellValue();
                             break;
                         case 1:
                             denominator =dencell.getStringCellValue();
                             break;
                         default:
                             denominator =""+(int)dencell.getNumericCellValue();
                             break;
                     }
                     
                     if(denominator.trim().equals("")){denominator="0";}
                     
                    //percentage
                     
                     XSSFCell perccell = rowi.getCell((short) 5);
                     
                     switch (perccell.getCellType()) 
                     {
                         case 0:
                             //numeric
                             percentage =""+(int)perccell.getNumericCellValue();
                             break;
                         case 1:
                             percentage =perccell.getStringCellValue();
                             break;
                         default:
                             percentage =""+(int)perccell.getNumericCellValue();
                             break;
                     }
                     
                     if(percentage.trim().equals("")){percentage="0";}
                     
                     
                 
                  
                     
                 }//end of while 5 to 15 cell values
                 
                 String mnname=monthName(reportingmonth);
                 
                 
                /**Make sure reporting month is of format mm  **/ 
                 if(reportingmonth.length()==1){  reportingmonth="0"+reportingmonth; }
                 
                 yearmonth=reportingyear+""+reportingmonth;
                 
                 System.out.println("yearmonth is "+yearmonth); 
                 //================================continue from here========================================
                 
                 //get indicator id from list of indicators
                 //create an id consisting of year_month_mflcode_indicator
                 
                       indicatorid=""+getIndicatorID(indicator, conn).get("indicatorid");
                       
                       
                       //set the birth year month
                       
                       String ct=""+getIndicatorID(indicator, conn).get("cohorttype");
                       
                      System.out.println("Care type"+ct);
                      
                       
                       if(ct.equals("12 month")){birthyear=birthyear12.substring(0, 4);}
                  else if(ct.equals("24 month")){birthyear=birthyear24.substring(0, 4);}
                       
                       String facilid=getFacilityID(mflcode,conn);
                  
      String id=reportingyear+"_"+reportingmonth+"_"+facilid+"_"+indicatorid;
                 
               
      System.out.println("test__"+id+"  "+indicator+" birth yearmonth "+birthyear12);
   
      facilityID="";
      
                 checker=0;
                 
                 //only do insert where there is a valid indicator
                 
                 if(!indicatorid.equals("")) {
//                        DISTRICT FOUND ADD THE HF TO THE SYSTEM.........................
                     
                  // if(new Integer(yearmonth)>=201710 && new Integer(yearmonth)<=201712 ){
                  
                     
                     if(checker==0){
                         
                         //id	SubPartnerID 	Mflcode	samplecode	collectiondate	testingdate	validation	enrollment	treatment_init_date	enroll_cccno	other_reasons	year	quarter
                         
                       String inserter="REPLACE INTO hei.results "
                               + "(result_id,"
                               + "birth_year,"                               
                               + "facility_id,"
                               + "indicator_id,"
                               + "numerator,"                               
                               + "denominator,"
                               + "percentage,"                                                          
                               + "month,"
                               + "userid,"
                               + "reportingyearmonth) "
                               + " VALUES "
                               + "(?,"
                               + "?,"
                               + "?,"
                               + "?,"
                               + "?,"
                               + "?,"
                               + "?,"
                               + "?,"
                               + "?,"                             
                               + "?)";
                       
                         conn.pst=conn.conn.prepareStatement(inserter);
                         conn.pst.setString(1,id);                        
                         conn.pst.setString(2,birthyear);
                         conn.pst.setString(3,facilid);
                         conn.pst.setString(4,indicatorid);                         
                         conn.pst.setString(5,numerator);
                         conn.pst.setString(6,denominator);
                         conn.pst.setString(7,percentage);                        
                         conn.pst.setString(8,mnname);                        
                         conn.pst.setString(9,userid);
                         conn.pst.setString(10,yearmonth);                         
                         conn.pst.executeUpdate();
                          
                         
                         added++;
                         
                     }
                    
                     
                 //}
             }
                 else{
                     missing++; 
//                        missing facilities
                     missingFacility+="facility  : "+facilityName+" mfl code : "+mflcode+" not in system "+i+"<br>";
                     System.out.println(facilityName+ " has no mflcode.");
                 }
                 i++;
                 } //end of iterator
             catch (SQLException ex) {
                 Logger.getLogger(uploadHCAModule.class.getName()).log(Level.SEVERE, null, ex);
                 break;
             }
             
         }//end of while
         
         
         
        }
        else 
        {            
           
            
            System.out.println("There is an HCA error or version issue");   
            
        }
                 
                 } //end of MCA       
                 
                 
                 
                 
                 
                 
                 
                 
                 
         }//end of worksheets loop

     
          }
     
         
     }
    //end of checking if excel file is valid
     if(conn.rs!=null){try {
         conn.rs.close();
          } catch (SQLException ex) {
              Logger.getLogger(uploadHCAModule.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      if(conn.st!=null){try {
          conn.st.close();
          } catch (SQLException ex) {
              Logger.getLogger(uploadHCAModule.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      if(conn.pst!=null){try {
          conn.pst.close();
          } catch (SQLException ex) {
              Logger.getLogger(uploadHCAModule.class.getName()).log(Level.SEVERE, null, ex);
          }
}
    
            if(conn.conn!=null){try {
                conn.conn.close();
         } catch (SQLException ex) {
             Logger.getLogger(uploadHCAModule.class.getName()).log(Level.SEVERE, null, ex);
         }
}
      
       String nomflcode="";
      if(!nomflsheets.equals("")){
      
      nomflcode="<b> "+nomflsheets+"</b> have no mflcodes ";
      }
      
     String sessionText="<br/><b> "+added+ "</b> New data added <br/> <b> "+updated+"</b> updated facilities<br> <br> <b>"+nomflcode+"</b>";    
     session.setAttribute("uploadedart"," Workbooks: "+fileNames+". "+ sessionText);
    
 
  
    
    response.sendRedirect(nextpage);  
 

 
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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//     try {
//         processRequest(request, response);
//     } catch (SQLException ex) {
//         Logger.getLogger(loadPMTCT_FO.class.getName()).log(Level.SEVERE, null, ex);
//     }
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//     try {
//         processRequest(request, response);
//     } catch (SQLException ex) {
//         Logger.getLogger(loadPMTCT_FO.class.getName()).log(Level.SEVERE, null, ex);
//     }
//    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
      
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return contentDisp;
    }
    
    
    
    private JSONObject getIndicatorID(String indicator_name, dbConn conn ) throws SQLException 
    {
        String id = "";
        String ct = "";
        JSONObject jo = new JSONObject();
        
        String getindicator = "SELECT indicator_id,indicator_name,cohort_type FROM hei.indicators where indicator_name = '" + indicator_name + "'";
             
        conn.rs = conn.st.executeQuery(getindicator);
        if (conn.rs.next()) {

            id = conn.rs.getString("indicator_id");
            ct = conn.rs.getString("cohort_type");
            jo.put("indicatorid", id);
            jo.put("cohorttype", ct);

        }
        else {
         jo.put("indicatorid", id);
         jo.put("cohorttype", ct);
        }

        
        
        return jo;

    }
    
     public String getFacilityID(String mflcode, dbConn conn ) throws SQLException 
    {
        String id = "";

        String getindicator = "SELECT SubPartnerID FROM subpartnera where CentreSanteId = '" +mflcode+"'";
             
        conn.rs = conn.st.executeQuery(getindicator);
        while (conn.rs.next()) 
        {
            id = conn.rs.getString("SubPartnerID");
        }
        return id;

    }
     
     
     public String  monthName(String monthno)
     {
        String mn="";
    if(monthno.equals("01")){
    mn="January";
    }
    else if(monthno.equals("02")){
    mn="February";
    }
    else if(monthno.equals("03")){
    mn="March";
    }
     else if(monthno.equals("04")){
    mn="April";
    }   
    else if(monthno.equals("05")){
    mn="May";
    }  
else if(monthno.equals("06")){
    mn="June";
    } 
    
    else if(monthno.equals("07")){
    mn="July";
    } 
     else if(monthno.equals("08")){
    mn="August";
    } 
    
    else if(monthno.equals("09")){
    mn="September";
    }
    else if(monthno.equals("10")){
    mn="October";
    }
    else if(monthno.equals("11")){
    mn="November";
    }
    else if(monthno.equals("12")){
    mn="December";
    }
    return mn;
    }
    
    
     
    
}
