/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

 */

package checklist;

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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
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


  public class upload_data_verification_v200 extends HttpServlet {
   
 
  
  String full_path="";
  String fileName="";
  int checker_dist,checker_hf;
  File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";
  String nextpage="";
  String quarterName,facilityName,facilityID,missingFacility;
          
  String fileNames="";

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
          String sessionText="Data for Workbooks: <br/> "+fileNames+"Uploaded Successfully ";  
     
     String nomflsheets="";
    
      int year,quarter,checker,missing = 0,added = 0,updated = 0;
      
      String county_name,county_id, district_name,district_id,hf_name,hf_id;
     fileNames="";
     fileName="";
 
     
     String ujumbe="";
     
    String user="unknown user";
    String userid="unknown user";
    
    
     
     HashMap<String, String> versions= new HashMap<String, String>();
     
     versions.put("DVT", "USAID Tujenge Jamii Facility Data Quality Verification Tool Version 2.0.0");
     //versions.put("MCA", "Maternal Cohort Analysis (MCA) Version 2.0.0");
     
     int rowgani=1;
     int rowCount=41;
     



String dv[]={"id","facility_id","indicator_id","yearmonth","verificationdate","recounted_register_emr","moh731","form1a","concordance","khis","fmaps_adt","gaps","action_taken","responsible","timeline","status","value_before_correction","value_after_correction","correction_reason","correction_action","staff","tool_version"};

//___________________________________________________________________________________________________________



//___________________________________________________________________________________________________________



int filescount=0;



 
    String serialnumber="";
    
     String dbname="internal_system.mne_cl_all";
  
     
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
     
     nextpage="mne_upload_template.jsp";
     
     
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
       
          
          nextpage="mne_upload_template.jsp";
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
    
    //if()
//______________________________________________________________________

//======================================================================DB Sheet======================================================================================
//import data from the db sheet
if(workbook.getSheetName(a).equals("3_Data Verification")){

     XSSFCell correctyearcell = worksheet.getRow(4).getCell((short) 9);
     XSSFCell wrongyearcell = worksheet.getRow(4).getCell((short) 10);

     
     
     String cyc="";
     String  wyc="";
     
     
     
      if(correctyearcell.getCellType()==0)
    {   //numeric
        cyc =""+(int)correctyearcell.getNumericCellValue();
    }
    else if(correctyearcell.getCellType()==1)
    {
        cyc =correctyearcell.getStringCellValue();
    }
      
      
      
        if(wrongyearcell.getCellType()==0)
    {   
    wyc =""+(int)wrongyearcell.getNumericCellValue();
    }
    else if(wrongyearcell.getCellType()==1)
    {
   wyc =wrongyearcell.getStringCellValue();
    }
     
     
     if(wyc.length()>0 || wyc.equals("2023"))
     { 
     
               
                    
 correctyearcell.setCellValue(wyc);
     
     }
     
     XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
}
if(workbook.getSheetName(a).equals("db"))
{
    System.out.println("Inside loop");
    
    
    
    String version="";
    
     XSSFCell cellversion = worksheet.getRow(4).getCell((short) 21);
    
    
    if(cellversion.getCellType()==0)
    {   //numeric
        version =""+(int)cellversion.getNumericCellValue();
    }
    else if(cellversion.getCellType()==1)
    {
        version =cellversion.getStringCellValue();
    }
    else {
     version =cellversion.getRawValue();
    
    }
    
    
    System.out.println("Version"+version);
    
    
    
    boolean haserror=false;
    String haserrorvalue="";
    
    
    if(version.equals(versions.get("DVT")) ){
        
        System.out.println(" No DVC error value or version ");
        
        
        
        
        Iterator rowIterator = worksheet.rowIterator();
        
        
        int i=1,y=0;
        
        //static number of rows
        while(i<=216){
            
            String insertqr_parta= "replace into mne_cl_all (";  // finish with )
            String insertqr_partb= " values ("; // finish with )
            
            try {
                
                rowgani++;
                session.setAttribute("dvtpos", "<b>"+rowgani+"/"+rowCount+"</b>");
                session.setAttribute("dvtpos_count", (rowgani*100)/rowCount);
                
                System.out.println(" row number "+i);
                
                XSSFRow rowi = worksheet.getRow(i);
                if( rowi==null )
                {
                    
                    break;
                }
                
                if(i>=1 && i<=41) {
                    
                    
                    HashMap<String,String> dvhm=new HashMap<String, String>();
                    
                    String val="";
                    
                    for(int cl=0;cl<dv.length;cl++){
                        
                        XSSFCell custcell = rowi.getCell((short) cl);
                        if(custcell!=null){
                            switch (custcell.getCellType())
                            {
                                case 0:
                                    //numeric
                                    val =""+(int)custcell.getNumericCellValue();
                                    break;
                                case 1:
                                    //string
                                    val =custcell.getStringCellValue();
                                    break;
                                case 2:
                                    //String
                                    val =""+custcell.getRawValue();
                                    break;
                                default:
                                    val =custcell.getRawValue();
                                    break;
                            }
                        }
                        
                        if(val==null){val="";}
                        if(val.endsWith(".0")){val=val.replace(".0", "");}
                        System.out.println("Value ni "+val);
                        //if(val.trim().equals("")){val="";}
                        dvhm.put(dv[cl], val);
                        
//build an inster qry
if(cl==dv.length-1){
    insertqr_parta+=dv[cl]+")";
    insertqr_partb+="?)";
    //last section
//insertqr_parta+=")";
//insertqr_partb+=")";
}
else {
    insertqr_parta+=dv[cl]+",";
    insertqr_partb+="?,";
}


//dvs.add(val);

                    }//end of for looop
                    
                    String insertqry=insertqr_parta+insertqr_partb;
                    
                    System.out.println(""+insertqry);
                    
                    //conn.st_2.executeUpdate(updateqr);
                    conn.pst1=conn.conn.prepareStatement(insertqry);
                    //now append the values
                    int rc=1;
                    for(int cl=0;cl<dv.length;cl++)
                    {
                        
                        String data=dvhm.get(dv[cl]);
                        
                        conn.pst1.setString(rc,data);
                        
                        rc++;
                        
                    }
                    
                    
                    System.out.println("____"+conn.pst1);
                    if(conn.pst1.executeUpdate()==1)
                    {
                        System.out.println("Data Verification Data Saved succesfully ");
                    }
                    else {
                        System.out.println(" Data Not saved ");
                        
                    }
                    
                    
                    
                }//end of while 1 to 215 cell values
                
                
                
                
                //================================continue from here========================================
                
                
                
                facilityID="";
                
                checker=0;
                
                
                
                i++;
            } //end of iterator
            catch (SQLException ex) {
                Logger.getLogger(mne_upload_template.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
            
        }//end of while
        
        
        
    }
    else {
    
    
    sessionText=" You are using a wrong version of the data verification template. ";
    
    }
    
    
} //end of DB WORKSHEET









}//end of worksheets loop



//Send the file via mail
AttachFileOnEmail sf = new AttachFileOnEmail();
GetFacilityDetails fd= new GetFacilityDetails();

XSSFCell facilmfl = workbook.getSheet("db").getRow(2).getCell((short) 1);
String Facii=fd.getFacilityNameUsingID(facilmfl.getRawValue(), conn);

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



if(!uploadedfiles.contains(full_path))
{
    sf.SendEmail("Data Verification", Facii, "Uploaded Successfully!", full_path, fileName,  Uploader, "EMaingi@usaidtujengejamii.org,DJuma@usaidtujengejamii.org,mnderitu@usaidtujengejamii.org"+em,usern);
}
uploadedfiles.add(full_path);       
             } catch (SQLException | MessagingException ex) {
                 Logger.getLogger(upload_data_verification.class.getName()).log(Level.SEVERE, null, ex);
             }
             
            
         
         
     
          }
     
         
     }
    //end of checking if excel file is valid
     if(conn.rs!=null){try {
         conn.rs.close();
          } catch (SQLException ex) {
              Logger.getLogger(mne_upload_template.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      if(conn.st!=null){try {
          conn.st.close();
          } catch (SQLException ex) {
              Logger.getLogger(mne_upload_template.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      if(conn.pst!=null){try {
          conn.pst.close();
          } catch (SQLException ex) {
              Logger.getLogger(mne_upload_template.class.getName()).log(Level.SEVERE, null, ex);
          }
}
    
            if(conn.conn!=null){try {
                conn.conn.close();
         } catch (SQLException ex) {
             Logger.getLogger(mne_upload_template.class.getName()).log(Level.SEVERE, null, ex);
         }
}
      
       String nomflcode="";
      if(!nomflsheets.equals("")){
      
      nomflcode="<b> "+nomflsheets+"</b> have no mflcodes ";
      }
      
      sessionText="Data for Workbooks: <br/> "+fileNames+"Uploaded Successfully ";    
     session.setAttribute("uploadedDVT",sessionText);
    
        session.setAttribute("dvtpos", "<b>0/1</b>");
        session.setAttribute("dvtpos_count", (0*100)/1);
  
    
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
