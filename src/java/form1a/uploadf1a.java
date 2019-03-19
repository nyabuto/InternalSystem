/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

 */

package form1a;


import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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


  public class uploadf1a extends HttpServlet {
   
 
  
  String full_path="";
  String fileName="";
  String fileNameCopy="";
  int checker_dist,checker_hf;
 
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";
  String nextpage="";
  String facilityName,facilityID,id,missingFacility,county,subcounty;
    int nomflcodesites;      
 

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
  String sessionText="";
    
      int year = 0,added = 0,updated = 0,islocked=0;
      String subpartnerid="",yearmonth="",month = "";
     
        session=request.getSession();
        
      nomflcodesites=0; 
   
      added=0; 
      updated=0; 
      missingFacility=""; 
      fileNameCopy="";
      fileName="";
      checker_dist=0;
      checker_hf=0;
      File file_source;
 
     
  id=""; 
String indicator="";
String indicatorid="";


String updatedfacil="";
String insertedfacil="";
String missingwithdatafacil="";
String unimporteddata="";
String updateddata="";
String newdaata="";

int startrow=7;
int endrow=164;

int startcol=3;
int endcol=27;

//String  f1a_ukf="0";
//String     f1a_ukm="0";
String     f1a_1f="0";
String     f1a_1m="0";
String     f1a_4f="0";
String     f1a_4m="0";
String     f1a_9f="0";
String     f1a_9m="0";
String     f1a_14f="0";
String     f1a_14m="0";
String     f1a_19f="0";
String     f1a_19m="0";
String     f1a_24f="0";
String     f1a_24m="0";
String     f1a_29f="0";
String     f1a_29m="0";
String     f1a_34f="0";
String     f1a_34m="0";
String     f1a_39f="0";
String     f1a_39m="0";
String     f1a_44f="0";
String     f1a_44m="0";
String     f1a_49f="0";
String     f1a_49m="0";
String     f1a_50f="0";
String     f1a_50m="0";
String     f1a_t="0";


 

String mflcode="";

 
 String activeversion="Form 1A  version 1.0.0";
    
    
     String dbname="fas_temp";
  
     
   
     dbConn conn = new dbConn();
     
     nextpage="uploadf1a.jsp";
     
     String applicationPath = request.getServletContext().getRealPath("");
     String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
     session=request.getSession();
     
     File fileSaveDir = new File(uploadFilePath);
     if (!fileSaveDir.exists()) {
         fileSaveDir.mkdirs();
                                }
     System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
   
      
      added=0; 
      updated=0; 
      for (Part part : request.getParts()) {
         
           nomflcodesites=0; 
     
      missingFacility="";
          
            if(!getFileName(part).equals("")){
                
           fileName = getFileName(part);
           
           fileNameCopy+=fileName+",";
            part.write(uploadFilePath + File.separator + fileName);
            
             if(!fileName.endsWith(".xlsx")){
        
         
         nextpage="uploadf1a.jsp";
         sessionText="<font color=\"red\">Failed to load a .xls excel file. Please open the file, go to file> options > save as , then save as .xlsx </font>";
     }
            
            }
        //}
     
     if(!fileName.endsWith(".xlsx")){
       
         nextpage="uploadf1a.jsp";
     }
     else {
         
         //start reading the contents
         
               try {
                   full_path=fileSaveDir.getAbsolutePath()+"/"+fileName; //end of checking if excel file is valid
                   System.out.println("the saved file directory is  :  "+full_path);

FileInputStream fileInputStream = new FileInputStream(full_path);
XSSFWorkbook workbook = (XSSFWorkbook) ReadExcel(full_path);

int totalsheets=workbook.getNumberOfSheets();
DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
for(int a=0;a<totalsheets;a++){
    
    XSSFSheet worksheet = workbook.getSheetAt(a);
    
    
    System.out.println( a+" ("+workbook.getSheetName(a)+") out of "+totalsheets+" sheets");
    
    String sheetname =  workbook.getSheetName(a);
    

//skip instructions page
if(!sheetname.equals("InstructionsForm1A")){
    
    Iterator rowIterator = worksheet.rowIterator();
    
    
   
    
    //get basic period and orgunit details
    
    XSSFCell facilcell= worksheet.getRow(0).getCell((short) 1);

 if(facilcell.getCellType()==1)
{
    facilityName =facilcell.getStringCellValue();
}
    
    XSSFCell mflcell= worksheet.getRow(0).getCell((short) 5);
    
    if (mflcell.getCellType() == 0) {
        mflcode = "" + (int) mflcell.getNumericCellValue();
    } else if (mflcell.getCellType() == 1) {
        mflcode = mflcell.getStringCellValue();
    }
    
    XSSFCell subcountycell= worksheet.getRow(0).getCell((short) 10);
     if(subcountycell.getCellType()==1){
    subcounty =subcountycell.getStringCellValue();
                                       }
     
    XSSFCell countycell= worksheet.getRow(0).getCell((short) 19);
      if(countycell.getCellType()==1){
    county =countycell.getStringCellValue();
                                       }
    
    
    XSSFCell monthcell= worksheet.getRow(0).getCell((short) 24);
    
     if (monthcell.getCellType() == 0) {
        month = "" + (int) monthcell.getNumericCellValue();
    } else if (monthcell.getCellType() == 1) {
        month = monthcell.getStringCellValue();
    }
    
    
    XSSFCell yearcell= worksheet.getRow(0).getCell((short) 26);
    
     if (yearcell.getCellType() == 0) {
        year = (int) yearcell.getNumericCellValue();
    } else if (yearcell.getCellType() == 1) {
        year = new Integer (yearcell.getStringCellValue());
    }
     
     
     
     
     
     
     
     //______________________CHECK EXCEL TEMPLATE  VERSION___________________
   
     String excelversion="";

     XSSFCell versioncell= worksheet.getRow(3).getCell((short) 0);
     
if(versioncell.getCellType()==0){
    //numeric
    excelversion =""+(int)versioncell.getNumericCellValue();
}
else if(versioncell.getCellType()==3){
    excelversion =versioncell.getStringCellValue();
}

  if(excelversion.equals(activeversion)){ 
  
  
//_______version row________________
 HashMap<String,String> colsvalue= new HashMap<String, String>();    
ArrayList colskey= new ArrayList();
     


//    colskey.add("id");
//    colskey.add("facility_id");
//    colskey.add("indicator_id");
//    colskey.add("yearmonth");
//    colskey.add("m_uk");
//    colskey.add("f_uk");
    colskey.add("m_1");
    colskey.add("f_1");
    colskey.add("m_4");
    colskey.add("f_4");
    colskey.add("m_9");
    colskey.add("f_9");
    colskey.add("m_14");
    colskey.add("f_14");
    colskey.add("m_19");
    colskey.add("f_19");
    colskey.add("m_24");
    colskey.add("f_24");
    colskey.add("m_29");
    colskey.add("f_29");
    colskey.add("m_34");
    colskey.add("f_34");
    colskey.add("m_39");
    colskey.add("f_39");
    colskey.add("m_44");
    colskey.add("f_44");
    colskey.add("m_49");
    colskey.add("f_49");
    colskey.add("m_50");
    colskey.add("f_50");
    colskey.add("total");

  
  
  //____________________Supported Areas per Facility and SubpartnerID____________________
  
  
  String supported_services = " WHERE is_active=1 && poi_row_no is not null && ";
  
  
  
    String support_column_name,support_column_value;
            int num_serv_supported=0;
            // READ FACILITY SUPPORTED SERVICES
            String get_supported_service = "SELECT SubPartnerID, IFNULL(PMTCT,0) AS PMTCT,IFNULL(ART,0) AS ART,IFNULL(VMMC,0) AS VMMC,IFNULL(HTC,0) AS HTC,IFNULL(Gender,0) AS Gender,IFNULL(PNS,0) AS PNS, IFNULL(IPD,0) AS IPD FROM subpartnera WHERE CentresanteID='"+mflcode+"'";
            System.out.println(""+get_supported_service);
            conn.rs = conn.st.executeQuery(get_supported_service);
               ResultSetMetaData metaData = conn.rs.getMetaData();
                int col_count = metaData.getColumnCount(); //number of column
             if(conn.rs.next()){
                 supported_services +=" AND (";
              for(int i=1;i<=col_count; i++)
              {
                  support_column_name = metaData.getColumnLabel(i);
                support_column_value=conn.rs.getString(support_column_name);
                  if(i==1){
                      //first column is the subpartner ID
                  subpartnerid=support_column_value;
                  }
                  else { 
                
                //if the facility supports atleast one service
                if(support_column_value.equals("1")){
                    num_serv_supported++;
                  supported_services+=support_column_name+"="+support_column_value+" OR ";    
                }
              }
                }
              
              if(num_serv_supported>0){
               supported_services = removeLast(supported_services, 3)+")";
              }
            }
            
            System.out.println("Supported services : "+supported_services);
            
            
            //___________________Read Active and Supported Indicators Only______________________
            
     // --Here, we have already mapped each element/indicator's row no as per the excel upload module into an existing fas_indicators table
     //--we will fetch a list of the indicators and the respective row no. then use the result set to tell us in which row of the uploaded excel template to get data for each indicator element.
     //--Any time there is a row-wise change in the excel upload file(including insertoing a new row), 
     //there is need to update the column poi_row_no in the table fas_indicators accordingly
     
  String table="";
  String code="";
  int poirow=0;
          
  String getsections = "SELECT id,database_name,code,poi_row_no FROM fas_indicators "+supported_services+" order by poi_row_no ";
          
            System.out.println(""+getsections);
          conn.rs2 = conn.st2.executeQuery(getsections);
          while(conn.rs2.next())
          {
              
         table = conn.rs2.getString("database_name");
         indicatorid=conn.rs2.getString("id");
         code=conn.rs2.getString("code");
         
         poirow=conn.rs2.getInt("poi_row_no");
         //while inside this , now read each indicator from the respective table row
         
         
         
         
         
            try {
               
               // XSSFRow rowi = worksheet.getRow(poirow);
                if( rowi==null )
                {
                    System.out.println(" Row not existing thus skipped for code "+code);
                    break;
                }
                
                //put data data values into an excel file
                
                
                for(int d=0;d<=colskey.size();d++)
                {
                
                    
                    String val="";
   
    XSSFCell valcell= worksheet.getRow(poirow).getCell((short) d+startcol);

                    
                    if (valcell.getCellType() == 0) {
                        val = "" + (int) monthcell.getNumericCellValue();
                    } else if (monthcell.getCellType() == 1) {
                        val = monthcell.getStringCellValue();
                    } 
                    else {
                        val = "" + (int) monthcell.getNumericCellValue();
                    }
                    //put the values into a hash tabe
                   colsvalue.put(colskey.get(d).toString(),val);
                
                
                
                }
                
                
                //================================check if data for facility is locked========================================
                
                
                
                
                
                String getindicator="SELECT id,indicator FROM fas_hts union where is_locked = '1'";
                //dbConn conn= new dbConn();
                //System.out.println(" Qry "+getindicator);
                conn.rs=conn.st.executeQuery(getindicator);
                while(conn.rs.next()){
                    
                    indicatorid=conn.rs.getString("id");
                    
                }
                
                
                
                String id=reportingyear+""+reportingmonth+"_"+subpartnerid+"_"+indicatorid;
                
                
              
                
                facilityID="";
                int checker = 0;
                
                //
                String get_id="SELECT id FROM "+dbname+" WHERE id like ? ";
                conn.pst1=conn.conn.prepareStatement(get_id);
                conn.pst1.setString(1,"%"+id+"%");
                
                conn.rs=conn.pst1.executeQuery();
                if(conn.rs.next()==true)
                {
                    facilityID=conn.rs.getString(1);
                    
                    checker=1;
                    
                }
                if(!mflcode.equals("") && !mflcode.equals("Enter MFL Code")) {
//                        DISTRICT FOUND ADD THE HF TO THE SYSTEM.........................

// if(new Integer(yearmonth)>=201710 && new Integer(yearmonth)<=201712 ){

if(checker==0){
    //System.out.println("** Inserting data ");
    //id	SubPartnerID 	Mflcode	samplecode	collectiondate	testingdate	validation	enrollment	treatment_init_date	enroll_cccno	other_reasons	year	quarter
    
    String inserter="INSERT INTO "+dbname+" (id,mflcode,year,month,weekstart,weekend,indicator,pns_ukf,pns_ukm,pns_1f,pns_1m,pns_4f,pns_4m,pns_9f,pns_9m,pns_14f,pns_14m,pns_19f,pns_19m,pns_24f,pns_24m,pns_29f,pns_29m,pns_34f,pns_34m,pns_39f,pns_39m,pns_44f,pns_44m,pns_49f,pns_49m,pns_50f,pns_50m,pns_t,yearmonth) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    conn.pst=conn.conn.prepareStatement(inserter);
    conn.pst.setString(1,id);
    conn.pst.setString(2,mflcode);
   
 
    conn.pst.setString(7,indicatorid);
    
    conn.pst.setString(10,f1a_1f);
    conn.pst.setString(11,f1a_1m);
    conn.pst.setString(12,f1a_4f);
    conn.pst.setString(13,f1a_4m);
    conn.pst.setString(14,f1a_9f);
    conn.pst.setString(15,f1a_9m);
    conn.pst.setString(16,f1a_14f);
    conn.pst.setString(17,f1a_14m);
    conn.pst.setString(18,f1a_19f);
    conn.pst.setString(19,f1a_19m);
    conn.pst.setString(20,f1a_24f);
    conn.pst.setString(21,f1a_24m);
    conn.pst.setString(22,f1a_29f);
    conn.pst.setString(23,f1a_29m);
    conn.pst.setString(24,f1a_34f);
    conn.pst.setString(25,f1a_34m);
    conn.pst.setString(26,f1a_39f);
    conn.pst.setString(27,f1a_39m);
    conn.pst.setString(28,f1a_44f);
    conn.pst.setString(29,f1a_44m);
    conn.pst.setString(30,f1a_49f);
    conn.pst.setString(31,f1a_49m);
    conn.pst.setString(32,f1a_50f);
    conn.pst.setString(33,f1a_50m);
    conn.pst.setString(34,f1a_t);
    conn.pst.setString(35,yearmonth);
    
     System.out.println(""+conn.pst);
    
    conn.pst.executeUpdate();
   
    
    //added++;
    
    if(!insertedfacil.contains(facilityName))
    {
        insertedfacil+=facilityName.replace("'", "")+",";
        added++;
    }
    
}


//}
                }
               
                
                
                
              
                
            } //end of try
            catch (SQLException ex) {
                Logger.getLogger(uploadf1a.class.getName()).log(Level.SEVERE, null, ex);
            }
         
         
         
         
            
          }//end of while loop
  
     
     
      
         
        
    }//end of correct version  

    else {
        
        sessionText="<h2><font color=\"red\">Note: Data was uploaded using Wrong Templete version. Click here to <a class=\"btn btn-success\" href=\"pns/PNSIndexweeklyTemplate20181019Version200.xlsx\">download new template</a></font><h2>";
        
        
    }
    
    

}
}//end of worksheets loop
               } catch (InvalidFormatException ex) {
                   Logger.getLogger(uploadf1a.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
                   Logger.getLogger(uploadf1a.class.getName()).log(Level.SEVERE, null, ex);
               }

     }
    
     
     if(nomflcodesites>0){
          if(nomflcodesites==1){
      unimporteddata="<br/> Data for <b>"+nomflcodesites+"</b> Sheet <b>("+missingwithdatafacil+")</b> skipped because it has no MFLCode";
          }
      
       else
      {
      
      
      unimporteddata="<br/> Data for <b>"+nomflcodesites+"</b> Sheets  <i>("+missingwithdatafacil+")</i>  skipped because no MflCode ";
      
     
      }
      }
     
     
      if(updated>0){
       updateddata=" <br/>  Data for <b>"+updated+" </b> sites <i>("+updatedfacil+")</i> updated <br> ";
      }
      
      
      if(added>0){
      
      newdaata=" Data for <b>"+added+ " </b> sites newly imported ";
      }
      
      
     
     
     
 }//end of for loop
     
     if(conn.rs!=null){try {
         conn.rs.close();
          } catch (SQLException ex) {
              Logger.getLogger(uploadf1a.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      if(conn.st!=null){try {
          conn.st.close();
          } catch (SQLException ex) {
              Logger.getLogger(uploadf1a.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      if(conn.pst!=null){try {
          conn.pst.close();
          } catch (SQLException ex) {
              Logger.getLogger(uploadf1a.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      
  
            if(conn.conn!=null){try {
                conn.conn.close();
      } catch (SQLException ex) {
          Logger.getLogger(uploadf1a.class.getName()).log(Level.SEVERE, null, ex);
      }
}
     
      
       if(conn.conn!=null){try {
          conn.conn.close();
          } catch (SQLException ex) {
              Logger.getLogger(uploadf1a.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      
      
      
     // pullHTS hts= new pullHTS();
    // hts.hts_pns(yearmonth, yearmonth, "");
      
      
     sessionText+="<br/>"+newdaata+"   "+updateddata+" "+unimporteddata+" ";    
     System.out.println(""+sessionText);
    session.setAttribute("uploadef1a","<ul><b>Last Data Upload Results:</b></ul><br/><br/><br/>  File(s) : <b>"+fileNameCopy+".</b> "+ sessionText);
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
            String file_name="";
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
      
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                file_name = token.substring(token.indexOf("=") + 2, token.length()-1);
              break;  
            }
            
        }
         System.out.println("content-disposition final : "+file_name);
        return file_name;
    }
    
    
    
    public  Workbook ReadExcel(String excelpath) throws IOException, InvalidFormatException {
        Workbook wb=null;
InputStream inputStream = null;
   try {
        inputStream = new FileInputStream(new File(excelpath));
         wb = WorkbookFactory.create(inputStream);
        int numberOfSheet = wb.getNumberOfSheets();

        for (int i = 0; i < numberOfSheet; i++) {
             Sheet sheet = wb.getSheetAt(i);
             //.... Customize your code here
             // To get sheet name, try -> sheet.getSheetName()
        }
   }  catch (FileNotFoundException ex) { 
          Logger.getLogger(uploadf1a.class.getName()).log(Level.SEVERE, null, ex);
      } 
   
   return wb;
}
    
    
           private static String removeLast(String str, int num) {
    return str.substring(0, str.length() - num);
}
    
    
}


