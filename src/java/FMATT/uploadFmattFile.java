/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

 */

package FMATT;

import General.AttachFileOnEmail;
import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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


  public class uploadFmattFile extends HttpServlet {
   
 
  
  String full_path="";
  String fileName="";
  int checker_dist,checker_hf;
  File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";
  String nextpage="";
  String quarterName,facilityName,facilityID,missingFacility,mfl;
          
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
    String userid="unknown user";
    
    
     
     HashMap<String, String> versions= new HashMap<String, String>();
     
     versions.put("FMATT", "Facility Missed Appointment Tracking Tool (FMATT) Version 2.1.0");
     //versions.put("MCA", "Maternal Cohort Analysis (MCA) Version 2.0.0");
     
     int rowgani=1;
     int rowCount=222;
     



String dv[]={"id","facility_id","yearmonth","form","date","indicator_id","carryover","d1","d2","d3","d4","d5","d6","d7","d8","d9","d10","d11","d12","d13","d14","d15","d16","d17","d18","d19","d20","d21","d22","d23","d24","d25","d26","d27","d28","d29","d30","d31","total"};

//___________________________________________________________________________________________________________



//___________________________________________________________________________________________________________



int filescount=0;



 
    String serialnumber="";
    
     String dbname="internal_system.fmatt_data";
  
     
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
     
     nextpage="fmattupload.jsp";
     
     
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
       
          nextpage="fmattupload.jsp";
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");
          
          }
          
          else {
           
             try {
                 rowgani=1;
                 rowCount=222;
                 
                 fileNames+=fileName+",<br/> ";
                 
                 full_path=fileSaveDir.getAbsolutePath()+"/"+fileName;
                 
                 System.out.println("the saved file directory is  :  "+full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
         
//







FileInputStream fileInputStream = new FileInputStream(full_path);
XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

int totalsheets=workbook.getNumberOfSheets();


for(int a=0;a<totalsheets;a++)
{
    
    
    XSSFSheet worksheet = workbook.getSheetAt(a);
    
    
    System.out.println( a+" ("+workbook.getSheetName(a)+") out of "+totalsheets+" sheets");
    
    
//______________________________________________________________________

//======================================================================DB Sheet======================================================================================
//import data from the db sheet
if(workbook.getSheetName(a).equals("db"))
{
    System.out.println("Inside loop");
    
    
    
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
    
    
    
    boolean haserror=false;
    String haserrorvalue="";
    
    
    if(1==1 ){
        
        System.out.println(" No FMATT error value or version ");
        
        
        
        
        Iterator rowIterator = worksheet.rowIterator();
        
        
        int i=1,y=0;
        
        //static number of rows
        while(i<=216){
            
            String insertqr_parta= "replace into fmatt_data (";  // finish with )
            String insertqr_partb= " values ("; // finish with )
            
            try {
                
                rowgani++;
                session.setAttribute("fmattpos", "<b>"+rowgani+"/"+rowCount+"</b>");
                session.setAttribute("fmattpos_count", (rowgani*100)/rowCount);
                
                System.out.println(" row number "+i);
                
                XSSFRow rowi = worksheet.getRow(i);
                if( rowi==null )
                {
                    
                    break;
                }
                
                if(i>=1 && i<=222) {
                    
                    
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
                    
                    if(conn.pst1.executeUpdate()==1)
                    {
                        System.out.println("FMATT Data Saved succesfully ");
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
                Logger.getLogger(uploadFmattFile.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
            
        }//end of while
        
        
        
    }
    
    
} //end of DB WORKSHEET









}//end of worksheets loop


//Send the file via mail
AttachFileOnEmail sf = new AttachFileOnEmail();

XSSFCell facilmfl = workbook.getSheet("db").getRow(2).getCell((short) 1);
String Facii=getFacilityName(facilmfl.getRawValue(), conn);

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
sf.SendEmail("FMatt", Facii, "Uploaded Successfully!", full_path, fileName,  Uploader, "EMaingi@usaidtujengejamii.org,DJuma@usaidtujengejamii.org,mnderitu@usaidtujengejamii.org"+em,usern);
}   
 uploadedfiles.add(full_path);       
        
        
        } catch (SQLException ex) {
                 Logger.getLogger(uploadFmattFile.class.getName()).log(Level.SEVERE, null, ex);
             } catch (MessagingException ex) {
                 Logger.getLogger(uploadFmattFile.class.getName()).log(Level.SEVERE, null, ex);
             }
         
         
         
     
          }
     
         
     }
    //end of checking if excel file is valid
     if(conn.rs!=null){try {
         conn.rs.close();
          } catch (SQLException ex) {
              Logger.getLogger(uploadFmattFile.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      if(conn.st!=null){try {
          conn.st.close();
          } catch (SQLException ex) {
              Logger.getLogger(uploadFmattFile.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      if(conn.pst!=null){try {
          conn.pst.close();
          } catch (SQLException ex) {
              Logger.getLogger(uploadFmattFile.class.getName()).log(Level.SEVERE, null, ex);
          }
}
    
            if(conn.conn!=null){try {
                conn.conn.close();
         } catch (SQLException ex) {
             Logger.getLogger(uploadFmattFile.class.getName()).log(Level.SEVERE, null, ex);
         }
}
      
       String nomflcode="";
      if(!nomflsheets.equals(""))
      {
      
      nomflcode="<b> "+nomflsheets+"</b> have no mflcodes ";
      
      }
      
     String sessionText="<br/><b> "+added+ "</b> New data added <br/> <b> "+updated+"</b> updated facilities<br> <br> <b>"+nomflcode+"</b>";
     
     session.setAttribute("uploadedFmatt"," Data for Workbooks: <br/>"+fileNames+". Uploaded Successfully! ");
    
        session.setAttribute("fmattpos", "<b>0/1</b>");
        session.setAttribute("fmattpos_count", (0*100)/1);
  
    
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
   
       public String getFacilityName(String mflcode, dbConn conn ) throws SQLException 
    {
        String id = "";

        String getindicator = "SELECT SubPartnerNom FROM subpartnera where CentreSanteId = '" +mflcode+"'";
             
        conn.rs = conn.st.executeQuery(getindicator);
        while (conn.rs.next()) 
        {
            id = conn.rs.getString("SubPartnerNom");
        }
        return id;

    }
    
     
    
}
