/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

 */

package ACAMCA;

import General.AttachFileOnEmail;
import General.GetFacilityDetails;
import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100) 

/**
 *
 * @author Emmanuel Kaunda
 */


  public class importacamca extends HttpServlet {
   
 
  
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
     
      int year,quarter,checker,missing = 0,added = 0,updated = 0;
      String county_name,county_id, district_name,district_id,hf_name,hf_id;
     fileNames="";
     fileName="";
 
     
     String ujumbe="";
     
    String user="unknown user";
    
    
     
     HashMap<String, String> versions= new HashMap<String, String>();
     
     versions.put("ACA", "ART Cohort Analysis (ACA) Version 2.0.0");
     versions.put("MCA", "Maternal Cohort Analysis (MCA) Version 2.0.0");
     
     int rowgani=1;
     int rowCount=22;
     
     
  id=""; 
String indicator="";
String indicatorid="";

String adult_3m="";
String child_3m="";
String tl_3m="";

String adult_6m="";
String child_6m="";
String tl_6m="";

String adult_12m="";
String child_12m="";
String tl_12m="";

String adult_24m="";
String child_24m="";
String tl_24m="";

String adult_36m="";
String child_36m="";
String tl_36m="";

String mflcode="";
String reportingyear="";
String reportingmonth="";
String yearmonth="";

 



//___________________________________________________________________________________________________________

String kp_3m="";
String np_3m="";

String kp_6m="";
String np_6m="";

String kp_9m="";
String np_9m="";
String tl_9m="";
String kp_12m="";
String np_12m="";

String kp_24m="";
String np_24m="";

//___________________________________________________________________________________________________________



int filescount=0;



 
    String serialnumber="";
    
     String dbname="art_cohort";
  
     
     session=request.getSession();
     
     
     if(session.getAttribute("username")!=null) {
         
     user=session.getAttribute("username").toString();
     
     }
     
     
     
      if(session.getAttribute("filesngapi")!=null) 
      {
          
     int idadiyafiles= new Integer(session.getAttribute("filesngapi").toString());
     System.out.println(" Files za kuupload ni :"+idadiyafiles);  
     rowCount=rowCount*idadiyafiles;
     }
     
     
     dbConn conn = new dbConn();
     
     nextpage="uploadacamca.jsp";
     
     
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
            ArrayList uploadedfiles=new ArrayList();
          if(!fileName.endsWith(".xlsx"))
          {          
       
          
          nextpage="importacamca.jsp";
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");
          
          }
          
          else {
           
                
              
             try {
                 fileNames+=fileName+",<br/> ";
                 
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

if(workbook.getSheetName(a).equals("ACA")) {
//______________________________________________________________________
//-----------facility name-----------------------
XSSFCell cellfacil = worksheet.getRow(0).getCell((short) 1);

if(cellfacil.getCellType()==0){
    //numeric
    facilityName =""+(int)cellfacil.getNumericCellValue();
}
else if(cellfacil.getCellType()==1)
{
    facilityName =cellfacil.getStringCellValue();
}


//-----------facility mfl code-----------------------
XSSFCell cellmfl = worksheet.getRow(0).getCell((short) 3);

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
XSSFCell cellyear = worksheet.getRow(0).getCell((short) 7);

if(cellyear.getCellType()==0){
    //numeric
    reportingyear =""+(int)cellyear.getNumericCellValue();
}
else if(cellyear.getCellType()==1){
    reportingyear =cellyear.getStringCellValue();
}



//-----------month-----------------------
XSSFCell cellmonth = worksheet.getRow(0).getCell((short) 5);

if(cellmonth.getCellType()==0)
{
    //numeric
    reportingmonth =""+(int)cellmonth.getNumericCellValue();
}
else if(cellmonth.getCellType()==1)
{
    reportingmonth =cellmonth.getStringCellValue();
}

//_________________________Check Version______________________________

String version="";
XSSFCell cellversion = worksheet.getRow(0).getCell((short) 9);


if(cellversion.getCellType()==0)
{   //numeric
    version =""+(int)cellversion.getNumericCellValue();
}
else if(cellversion.getCellType()==1)
{
    version =cellversion.getStringCellValue();
}






//_________________________Check Reports Rules______________________________

/*
19	More Active than Net Cohort
20	More Supressed than VL Done
21	Validation error More Alive and Active on Treatment Patients than Net Cohort
22	Validation error More Virally Supressed than Viral Load Collected
23	Validation error , Enrolled Into Cohort Not same for 12 Months Cohorts
24	Validation error, Viral Load Collected is More than Alive at 12 Months
*/
boolean haserror=false;
String haserrorvalue="";
if(1==1) {
    for(int rq=19;rq<=24;rq++)
    {
        
        XSSFCell cellacaerrorval = worksheet.getRow(rq).getCell((short) 17);    
        
        XSSFCell cellacaerrorlabel = worksheet.getRow(rq).getCell((short) 0);
        
        String errorvalue="";
        String errorlabel="";
        
        //_____error label______
        if(cellacaerrorlabel.getCellType()==0)
        {   //numeric
            errorlabel=""+(int)cellacaerrorlabel.getNumericCellValue();
        }
        else if(cellacaerrorlabel.getCellType()==1)
        {
            errorlabel=cellacaerrorlabel.getStringCellValue();
        }
        else {
         errorlabel=cellacaerrorlabel.getRawValue();
        }
        
        
        //_____error value______
        // String haserrorvalue="";
        
        if(cellacaerrorval.getCellType()==0)
        {
            //numeric
            errorvalue =""+(int)cellacaerrorval.getNumericCellValue();
        }
        else if(cellacaerrorval.getCellType()==1)
        {
            errorvalue =cellacaerrorval.getStringCellValue();
        }
        else {
        {
            errorvalue =cellacaerrorval.getRawValue();
        }
        
        }
        if(errorvalue.equals("0") || errorvalue.equals("") )
        {
            
        }
        else {
            haserrorvalue+=errorlabel+" detected ";
        }
        
        System.out.println(":::: Error status"+errorvalue+"_");
        
    }
}



System.out.println("error status"+haserrorvalue+"_");

System.out.println("version :"+version+" vs "+versions.get("ACA").toString());

if( version.equals(versions.get("ACA").toString()) && haserrorvalue.equals("") ){
    
    System.out.println(" No ACA error value or version ");
    
    
    
    
    
    
    
    
    
    
    Iterator rowIterator = worksheet.rowIterator();
    
    
    int i=5,y=0;
    
    //static number of rows
    while(i<=15){
        try {
            
            rowgani++;
            session.setAttribute("acamcapos", "<b>"+rowgani+"/"+rowCount+"</b>");
            session.setAttribute("acamcapos_count", (rowgani*100)/rowCount);
            
            System.out.println(" row number "+i);
            
            XSSFRow rowi = worksheet.getRow(i);
            if( rowi==null )
            {
                
                break;
            }
            
            if(i>=5 && i<=15) {
                
                //elements
                
                //indicator
                
                XSSFCell indiccell = rowi.getCell((short) 1);
                
                if(indiccell.getCellType()==0){
                    //numeric
                    indicator =""+(int)indiccell.getNumericCellValue();
                }
                else if(indiccell.getCellType()==1){
                    indicator =indiccell.getStringCellValue();
                }
                
                
                //adult_3m
                
                XSSFCell adult_3mcell = rowi.getCell((short) 2);
                
                switch (adult_3mcell.getCellType()) {
                    case 0:
                        //numeric
                        adult_3m =""+(int)adult_3mcell.getNumericCellValue();
                        break;
                    case 1:
                        adult_3m =adult_3mcell.getStringCellValue();
                        break;
                    default:
                        System.out.println("cell type "+adult_3mcell.getCellType());
                        adult_3m =""+(int)adult_3mcell.getNumericCellValue();
                        break;
                }
                
                if(adult_3m.trim().equals("")){adult_3m="0";}
                
                
                //child_3m
                
                XSSFCell child_3mcell = rowi.getCell((short) 3);
                
                switch (child_3mcell.getCellType()) {
                    case 0:
                        //numeric
                        child_3m =""+(int)child_3mcell.getNumericCellValue();
                        break;
                    case 1:
                        child_3m =child_3mcell.getStringCellValue();
                        break;
                    default:
                        child_3m =""+(int)child_3mcell.getNumericCellValue();
                        break;
                }
                
                if(child_3m.trim().equals("")){child_3m="0";}
                
                //tl_3m
                
                
                tl_3m =""+(new Integer(child_3m)+new Integer(adult_3m));
                
                
                
                /**  6 Months   **/
                //adult_6m
                
                XSSFCell adult_6mcell = rowi.getCell((short) 5);
                
                switch (adult_6mcell.getCellType()) {
                    case 0:
                        //numeric
                        adult_6m = "" + (int) adult_6mcell.getNumericCellValue();
                        break;
                    case 1:
                        adult_6m = adult_6mcell.getStringCellValue();
                        break;
                    default:
                        adult_6m = "" + (int) adult_6mcell.getNumericCellValue();
                        break;
                }
                if (adult_6m.trim().equals("")) {
                    adult_6m = "0";
                }
                
                //child_6m
                
                XSSFCell child_6mcell = rowi.getCell((short) 6);
                
                switch (child_6mcell.getCellType()) {
                    case 0:
                        //numeric
                        child_6m = "" + (int) child_6mcell.getNumericCellValue();
                        break;
                    case 1:
                        child_6m = child_6mcell.getStringCellValue();
                        break;
                    default:
                        child_6m = "" + (int) child_6mcell.getNumericCellValue();
                        break;
                }
                
                if (child_6m.trim().equals("")) {
                    child_6m = "0";
                }
                
                //tl_6m
                
                tl_6m=""+(new Integer(child_6m)+new Integer(adult_6m));
                
                
                //adult_12m
                
                XSSFCell adult_12mcell = rowi.getCell((short) 8);
                
                switch (adult_12mcell.getCellType()) {
                    case 0:
                        //numeric
                        adult_12m =""+(int)adult_12mcell.getNumericCellValue();
                        break;
                    case 1:
                        adult_12m =adult_12mcell.getStringCellValue();
                        break;
                    default:
                        adult_12m =""+(int)adult_12mcell.getNumericCellValue();
                        break;
                }
                
                
                //child_12m
                
                XSSFCell child_12mcell = rowi.getCell((short) 9);
                
                switch (child_12mcell.getCellType()) {
                    case 0:
                        //numeric
                        child_12m =""+(int)child_12mcell.getNumericCellValue();
                        break;
                    case 1:
                        child_12m =child_12mcell.getStringCellValue();
                        break;
                    default:
                        child_12m =""+(int)child_12mcell.getNumericCellValue();
                        break;
                }
                
                
                if(adult_12m.trim().equals("")){adult_12m="0";}
                if(child_12m.trim().equals("")){child_12m="0";}
                
                
                tl_12m=""+(new Integer(child_12m)+new Integer(adult_12m));
                
                //adult_24m
                
                XSSFCell adult_24mcell = rowi.getCell((short) 11);
                
                switch (adult_24mcell.getCellType()) {
                    case 0:
                        //numeric
                        adult_24m =""+(int)adult_24mcell.getNumericCellValue();
                        break;
                    case 1:
                        adult_24m =adult_24mcell.getStringCellValue();
                        break;
                    default:
                        adult_24m =""+(int)adult_24mcell.getNumericCellValue();
                        break;
                }
                
                
                //child_24m
                
                XSSFCell child_24mcell = rowi.getCell((short) 12);
                
                switch (child_24mcell.getCellType()) {
                    case 0:
                        //numeric
                        child_24m =""+(int)child_24mcell.getNumericCellValue();
                        break;
                    case 1:
                        child_24m =child_24mcell.getStringCellValue();
                        break;
                    default:
                        child_24m =""+(int)child_24mcell.getNumericCellValue();
                        break;
                }
                
                //tl_24m
                
                if(adult_24m.trim().equals("")){adult_24m="0";}
                if(child_24m.trim().equals("")){child_24m="0";}
                
                tl_24m=""+(new Integer(child_24m)+new Integer(adult_24m));
                
                //adult_36m
                
                XSSFCell adult_36mcell = rowi.getCell((short) 14);
                
                switch (adult_36mcell.getCellType()) {
                    case 0:
                        //numeric
                        adult_36m =""+(int)adult_36mcell.getNumericCellValue();
                        break;
                    case 1:
                        adult_36m =adult_36mcell.getStringCellValue();
                        break;
                    default:
                        adult_36m =""+(int)adult_36mcell.getNumericCellValue();
                        break;
                }
                
                
                //child_36m
                
                XSSFCell child_36mcell = rowi.getCell((short) 15);
                
                switch (child_36mcell.getCellType()) {
                    case 0:
                        //numeric
                        child_36m =""+(int)child_36mcell.getNumericCellValue();
                        break;
                        //tl_36m
                    case 1:
                        child_36m =child_36mcell.getStringCellValue();
                        break;
                    default:
                        child_36m =""+(int)child_36mcell.getNumericCellValue();
                        break;
                }
                
                
                
                if(adult_36m.trim().equals("")){adult_36m="0";}
                if(child_36m.trim().equals("")){child_36m="0";}
                
                tl_36m=""+(new Integer(child_36m)+new Integer(adult_36m));
                
                
                
                
            }//end of while 5 to 15 cell values
            
            /**Make sure reporting month is of format mm  **/
            if(reportingmonth.length()==1){  reportingmonth="0"+reportingmonth; }
            
            yearmonth=reportingyear+""+reportingmonth;
            
            System.out.println("yearmonth is "+yearmonth);
            //================================continue from here========================================
            
            //get indicator id from list of indicators
            //create an id consisting of year_month_mflcode_indicator
            
            indicatorid=getIndicatorID("art", indicator, conn);
            
            
            String id=reportingyear+"_"+reportingmonth+"_"+mflcode+"_"+indicatorid;
            
            
            System.out.println("test__"+id+"  "+indicator);
            
            facilityID="";
            
            checker=0;
            
            
            if(!mflcode.equals("")) {
//                        DISTRICT FOUND ADD THE HF TO THE SYSTEM.........................

// if(new Integer(yearmonth)>=201710 && new Integer(yearmonth)<=201712 ){


if(checker==0){
    
    //id	SubPartnerID 	Mflcode	samplecode	collectiondate	testingdate	validation	enrollment	treatment_init_date	enroll_cccno	other_reasons	year	quarter
    
    String inserter="Replace INTO pmtct_art_cohort.art_cohort "
            + "(id,"
            + "indicator,"
            + "adult_3m,"
            + "child_3m,"
            + "tl_3m,"
            + "adult_6m,"
            + "child_6m,"
            + "tl_6m,"
            + "adult_12m,"
            + "child_12m,"
            + "tl_12m,"
            + "adult_24m,"
            + "child_24m,"
            + "tl_24m,"
            + "adult_36m,"
            + "child_36m,"
            + "tl_36m,"
            + "mflcode,"
            + "reportingyear,"
            + "reportingmonth,"
            + "yearmonth,"
            + "userid) "
            + "VALUES"
            + "(?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
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
    conn.pst.setString(2,indicatorid);
    conn.pst.setString(3,adult_3m);
    conn.pst.setString(4,child_3m);
    conn.pst.setString(5,tl_3m);
    conn.pst.setString(6,adult_6m);
    conn.pst.setString(7,child_6m);
    conn.pst.setString(8,tl_6m);
    conn.pst.setString(9,adult_12m);
    conn.pst.setString(10,child_12m);
    conn.pst.setString(11,tl_12m);
    conn.pst.setString(12,adult_24m);
    conn.pst.setString(13,child_24m);
    conn.pst.setString(14,tl_24m);
    conn.pst.setString(15,adult_36m);
    conn.pst.setString(16,child_36m);
    conn.pst.setString(17,tl_36m);
    conn.pst.setString(18,mflcode);
    conn.pst.setString(19,reportingyear);
    conn.pst.setString(20,reportingmonth);
    conn.pst.setString(21,yearmonth);
    conn.pst.setString(22,user);
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
            Logger.getLogger(importacamca.class.getName()).log(Level.SEVERE, null, ex);
            break;
        }
        
    }//end of while
    
}// end of if no error
else
{
    System.out.println("ACA error value or version ");
    
}



}
//======================================================================MCA======================================================================================

else if(workbook.getSheetName(a).equals("MCA"))  {
    
    
    //-----------facility name-----------------------
    XSSFCell cellfacil = worksheet.getRow(0).getCell((short) 1);
    
    if(cellfacil.getCellType()==0){
        //numeric
        facilityName =""+(int)cellfacil.getNumericCellValue();
    }
    else if(cellfacil.getCellType()==1)
    {
        facilityName =cellfacil.getStringCellValue();
    }
    
    
    //-----------facility mfl code-----------------------
    XSSFCell cellmfl = worksheet.getRow(0).getCell((short) 3);
    
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
    XSSFCell cellyear = worksheet.getRow(0).getCell((short) 7);
    
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
    XSSFCell cellmonth = worksheet.getRow(0).getCell((short) 5);
    
    if(cellmonth.getCellType()==0){
        //numeric
        reportingmonth =""+(int)cellmonth.getNumericCellValue();
    }
    else if(cellmonth.getCellType()==1){
        reportingmonth =cellmonth.getStringCellValue();
    }
    
    
    
    
    
    
    
    
    //_________________________Check Version______________________________
    
    String version="";
    XSSFCell cellversion = worksheet.getRow(0).getCell((short) 9);
    
    
    if(cellversion.getCellType()==0)
    {   //numeric
        version =""+(int)cellversion.getNumericCellValue();
    }
    else if(cellversion.getCellType()==1)
    {
        version =cellversion.getStringCellValue();
    }
    
    
    
    
    
    
    //_________________________Check Reports Rules______________________________
    
    /*
    19	More Active than Net Cohort
    20	More Supressed than VL Done
    21	Validation error More Alive and Active on Treatment Patients than Net Cohort
    22	Validation error More Virally Supressed than Viral Load Collected
    23	Validation error , Enrolled Into Cohort Not same for 12 Months Cohorts
    24	Validation error, Viral Load Collected is More than Alive at 12 Months
    */
    boolean haserror=false;
    String haserrorvalue="";
    
    if(1==1){
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
            else{
            errorlabel =cellacaerrorlabel.getRawValue();
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

    
    
    if(version.equals(versions.get("MCA")) && haserrorvalue.equals("") ){
        
        System.out.println(" No MCA error value or version ");
        
       
        Iterator rowIterator = worksheet.rowIterator();
        
        
        int i=5,y=0;
        
        //static number of rows
        while(i<=15){
            try {
                
                rowgani++;
                session.setAttribute("acamcapos", "<b>"+rowgani+"/"+rowCount+"</b>");
                session.setAttribute("acamcapos_count", (rowgani*100)/rowCount);
                
                System.out.println(" row number "+i);
                
                XSSFRow rowi = worksheet.getRow(i);
                if( rowi==null )
                {                     
                    
                    break;
                }
                
                if(i>=5 && i<=15) {
                    
                    //elements
                    
                    //indicator
                    
                    XSSFCell indiccell = rowi.getCell((short) 1);
                    
                    if(indiccell.getCellType()==0){
                        //numeric
                        indicator =""+(int)indiccell.getNumericCellValue();
                    }
                    else if(indiccell.getCellType()==1){
                        indicator =indiccell.getStringCellValue();
                    }
                    
                    
                    //adult_3m
                    
                    XSSFCell kp_3mcell = rowi.getCell((short) 2);
                    
                    switch (kp_3mcell.getCellType()) {
                        case 0:
                            //numeric
                            kp_3m =""+(int)kp_3mcell.getNumericCellValue();
                            break;
                        case 1:
                            kp_3m =kp_3mcell.getStringCellValue();
                            break;
                        default:
                            kp_3m =""+(int)kp_3mcell.getNumericCellValue();
                            break;
                    }
                    
                    if(kp_3m.trim().equals("")){kp_3m="0";}
                    
                    
                    //np_3m
                    
                    XSSFCell np_3mcell = rowi.getCell((short) 3);                     
                    
                    switch (np_3mcell.getCellType()) {
                        case 0:
                            //numeric
                            np_3m =""+(int)np_3mcell.getNumericCellValue();
                            break;
                        case 1:
                            np_3m =np_3mcell.getStringCellValue();
                            break;
                        default:
                            np_3m =""+(int)np_3mcell.getNumericCellValue();
                            break;
                    }
                    
                    if(np_3m.trim().equals("")){np_3m="0";}
                    
                    //tl_3m
                    
                    
                    tl_3m=""+(new Integer(np_3m)+new Integer(kp_3m));
                    
                    
                    if(tl_3m.trim().equals("")){tl_3m="0";}
                    //kp_6m
                    
                    XSSFCell kp_6mcell = rowi.getCell((short) 5);
                    
                    switch (kp_6mcell.getCellType()) {
                        case 0:
                            //numeric
                            kp_6m =""+(int)kp_6mcell.getNumericCellValue();
                            break;
                        case 1:
                            kp_6m =kp_6mcell.getStringCellValue();
                            break;
                        default:
                            kp_6m =""+(int)kp_6mcell.getNumericCellValue();
                            break;
                    }
                    if(kp_6m.trim().equals("")){kp_6m="0";}
                    
                    //np_6m
                    
                    XSSFCell np_6mcell = rowi.getCell((short) 6);
                    
                    switch (np_6mcell.getCellType()) {
                        case 0:
                            //numeric
                            np_6m =""+(int)np_6mcell.getNumericCellValue();
                            break;
                        case 1:
                            np_6m =np_6mcell.getStringCellValue();
                            break;
                        default:
                            np_6m =""+(int)np_6mcell.getNumericCellValue();
                            break;
                    }
                    
                    if(np_6m.trim().equals("")){np_6m="0";}
                    
                    //tl_7m
                    
                    tl_6m=""+(new Integer(np_6m)+new Integer(kp_6m));
                    
                    
                    
                    
                    //kp_12m
                    
                    XSSFCell kp_12mcell = rowi.getCell((short) 8);
                    
                    switch (kp_12mcell.getCellType()) {
                        case 0:
                            //numeric
                            kp_12m =""+(int)kp_12mcell.getNumericCellValue();
                            break;
                        case 1:
                            kp_12m =kp_12mcell.getStringCellValue();
                            break;
                        default:
                            kp_12m =""+(int)kp_12mcell.getNumericCellValue();
                            break;
                    }
                    
                    
                    //np_12m
                    
                    XSSFCell np_12mcell = rowi.getCell((short) 9);
                    
                    switch (np_12mcell.getCellType())
                    {
                        case 0:
                            //numeric
                            np_12m =""+(int)np_12mcell.getNumericCellValue();
                            break;
                        case 1:
                            np_12m =np_12mcell.getStringCellValue();
                            break;
                        default:
                            np_12m =""+(int)np_12mcell.getNumericCellValue();
                            break;
                    }
                    
                    //tl_12m
                    
                    if(kp_12m.trim().equals("")){kp_12m="0";}
                    if(np_12m.trim().equals("")){np_12m="0";}
                    tl_12m=""+(new Integer(np_12m)+new Integer(kp_12m));
                    
                    
                    
                    //kp_24m
                    
                    XSSFCell kp_24mcell = rowi.getCell((short) 11);
                    
                    switch (kp_24mcell.getCellType()) {
                        case 0:
                            //numeric
                            kp_24m =""+(int)kp_24mcell.getNumericCellValue();
                            break;
                        case 1:
                            kp_24m =kp_24mcell.getStringCellValue();
                            break;
                        default:
                            kp_24m =""+(int)kp_24mcell.getNumericCellValue();
                            break;
                    }
                    
                    
                    //np_24m
                    
                    XSSFCell np_24mcell = rowi.getCell((short) 12);
                    
                    switch (np_24mcell.getCellType()) {
                        case 0:
                            //numeric
                            np_24m =""+(int)np_24mcell.getNumericCellValue();
                            break;
                        case 1:
                            np_24m =np_24mcell.getStringCellValue();
                            break;
                        default:
                            np_24m =""+(int)np_24mcell.getNumericCellValue();
                            break;
                    }
                    
                    //tl_24m
                    
                    
                    if(kp_24m.trim().equals("")){kp_24m="0";}
                    
                    if(np_24m.trim().equals("")){np_24m="0";}
                    
                    tl_12m=""+(new Integer(np_12m)+new Integer(kp_12m));
                    
                    
                }//end of while 5 to 15 cell values
                
                /**Make sure reporting month is of format mm  **/
                if(reportingmonth.length()==1){  reportingmonth="0"+reportingmonth; }
                
                yearmonth=reportingyear+""+reportingmonth;
                
                System.out.println("yearmonth is "+yearmonth);
                //================================continue from here========================================
                
                //get indicator id from list of indicators
                //create an id consisting of year_month_mflcode_indicator
                
                indicatorid=getIndicatorID("pmtct", indicator, conn);
                
                String id=reportingyear+"_"+reportingmonth+"_"+mflcode+"_"+indicatorid;
                
                
                System.out.println("test__"+id+"  "+indicator);
                
                facilityID="";
                
                checker=0;
                
                
                if(!mflcode.equals("")) {
//                        DISTRICT FOUND ADD THE HF TO THE SYSTEM.........................

// if(new Integer(yearmonth)>=201710 && new Integer(yearmonth)<=201712 ){


if(checker==0){
    
    //id	SubPartnerID 	Mflcode	samplecode	collectiondate	testingdate	validation	enrollment	treatment_init_date	enroll_cccno	other_reasons	year	quarter
    
    String inserter="REPLACE INTO pmtct_art_cohort.pmtct_cohort "
            + "( id,"
            + "indicator,"
            
            + "kp_3m,"   
            + "np_3m,"
            + "tl_3m,"
            
            + "kp_6m,"
            + "np_6m,"
            + "tl_6m,"
            
            + "kp_12m,"
            + "np_12m,"
            + "tl_12m,"
            
            + "kp_24m,"
            + "np_24m,"
            + "tl_24m,"
            
            + "mflcode,"
            + "reportingyear,"
            + "reportingmonth,"
            + "yearmonth,userid) "
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
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,?)";
    
    conn.pst=conn.conn.prepareStatement(inserter);
    conn.pst.setString(1,id);
    conn.pst.setString(2,indicatorid);
    conn.pst.setString(3,kp_3m);
    conn.pst.setString(4,np_3m);
    conn.pst.setString(5,tl_3m);
    
    conn.pst.setString(6,kp_6m);
    conn.pst.setString(7,np_6m);
    conn.pst.setString(8,tl_6m);
    
    conn.pst.setString(9,kp_12m);
    conn.pst.setString(10,np_12m);
    conn.pst.setString(11,tl_12m);
    
    conn.pst.setString(12,kp_24m);
    conn.pst.setString(13,np_24m);
    conn.pst.setString(14,tl_24m);
    
    conn.pst.setString(15,mflcode);
    conn.pst.setString(16,reportingyear);
    conn.pst.setString(17,reportingmonth);
    conn.pst.setString(18,yearmonth);
    conn.pst.setString(19,user);
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
                Logger.getLogger(importacamca.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
            
        }//end of while
        
        
        
    }
    else
    {
        
        
        System.out.println("There is an MCA error or version issue");
        
    }
    
} //end of MCA









}//end of worksheets loop

 session.setAttribute("acamcapos", "<b>0/1</b>");
                session.setAttribute("acamcapos_count", (0*100)/1);

//Send the file via mail
AttachFileOnEmail sf = new AttachFileOnEmail();
GetFacilityDetails fd= new GetFacilityDetails();

XSSFCell facilmfl = workbook.getSheetAt(1).getRow(0).getCell((short) 3);
String Facii=fd.getFacilityName(facilmfl.getStringCellValue(), conn);

String Uploader="Not Captured";
String em="";
String usern="";

if(session.getAttribute("email")!=null){
    em=","+session.getAttribute("email").toString();
}
if(session.getAttribute("fullname")!=null){
    usern=session.getAttribute("fullname").toString();
}
if(session.getAttribute("username")!=null){
    Uploader=session.getAttribute("username").toString();
}



if(!uploadedfiles.contains(full_path)){
    sf.SendEmail("ACA/MCA", Facii, "Uploaded Successfully!", full_path, fileName,  Uploader, "EMaingi@usaidtujengejamii.org,DJuma@usaidtujengejamii.org,mnderitu@usaidtujengejamii.org"+em,usern);
}
uploadedfiles.add(full_path);       
             } catch (SQLException | MessagingException ex) {
                 Logger.getLogger(importacamca.class.getName()).log(Level.SEVERE, null, ex);
             }
             
			    
         
         
         
     
          }
     
         
     }
    //end of checking if excel file is valid
     if(conn.rs!=null){try {
         conn.rs.close();
          } catch (SQLException ex) {
              Logger.getLogger(importacamca.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      if(conn.st!=null){try {
          conn.st.close();
          } catch (SQLException ex) {
              Logger.getLogger(importacamca.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      if(conn.pst!=null){try {
          conn.pst.close();
          } catch (SQLException ex) {
              Logger.getLogger(importacamca.class.getName()).log(Level.SEVERE, null, ex);
          }
}
    
            if(conn.conn!=null){try {
                conn.conn.close();
         } catch (SQLException ex) {
             Logger.getLogger(importacamca.class.getName()).log(Level.SEVERE, null, ex);
         }
}
      
       String nomflcode="";
      if(!nomflsheets.equals("")){
      
      nomflcode="<b> "+nomflsheets+"</b> have no mflcodes ";
      }
      
     String sessionText="<br/><b> "+added+ "</b> New data added <br/> <b> "+updated+"</b> updated facilities<br> <br> <b>"+nomflcode+"</b>";    
     session.setAttribute("uploadedart","Data for Workbooks<br/> : "+fileNames+".uploaded successfully ");
    
 
  
    
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
    
    
    
    private String getIndicatorID(String cohort,String Indicname, dbConn conn ) throws SQLException {
        String id = "";

        String getindicator = "SELECT indicators_id,id,indicator FROM pmtct_art_cohort.indicators where cohort='"+cohort+"' and indicator like '%" + Indicname + "'";
             
        conn.rs = conn.st.executeQuery(getindicator);
        while (conn.rs.next()) {

            id = conn.rs.getString("indicators_id");

        }

        return id;

    }
    
}
