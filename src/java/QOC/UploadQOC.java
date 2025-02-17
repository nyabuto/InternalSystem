/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QOC;

import General.AttachFileOnEmail;
import General.GetFacilityDetails;
import dashboards.PushDataSet2;
import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
@MultipartConfig(fileSizeThreshold=1024*1024*30, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100) 

/**
 *
 * @author GNyabuto
 */
public class UploadQOC extends HttpServlet {
  String full_path="";
  String fileName="";
  File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";
  String query="",query_update="",value,checker_query;
  String SubPartnerID,mfl_code,year,month,yearmonth,id;
  String[] columns =  {"vl_kenyaemr_id","cccno","Quality_Of_Care_Gap","phonenumber","AgeYrs","Sex","HIV_Enrollment_Date","ART_Start_Date","Facility_Name","MFL_Code","Yearmonth","Last_Visit_Date","Next_appointment_date","Age_Bracket","Last_VL_order_Date","MCH"};
  int updated,added;
  String min_date="",max_date="",date_tested="";
  String value_vl="";
  String upload_message="";
  
  SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        dbConn  conn = new dbConn();
        session = request.getSession();
        
        min_date=max_date="";
        String applicationPath = request.getServletContext().getRealPath("");
         String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
         session=request.getSession();
          File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        
        for (Part part : request.getParts()) {
            if(!getFileName(part).equals("")){
           fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            }
         ArrayList uploadedfiles=new ArrayList();
        if(!fileName.endsWith(".xlsx")){
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");   
        }
        else{
          full_path=fileSaveDir.getAbsolutePath()+"/"+fileName;
 
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
        query=query_update=value="";
        updated=added=0;
  FileInputStream fileInputStream = new FileInputStream(full_path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        int j=0;
        int number_sheets = workbook.getNumberOfSheets();
        while(j<number_sheets){
        XSSFSheet worksheet;
        
        worksheet = workbook.getSheetAt(j);
        Iterator rowIterator = worksheet.iterator();
        int rowCount = worksheet.getLastRowNum();
        int i=1,y=0;
        while(rowIterator.hasNext()){
           
//            session.removeAttribute("viral_load");
            query = "REPLACE INTO qoc_baseline SET ";
           
            int colmnscounter=0;
        SubPartnerID=mfl_code="";
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;}
        value_vl="";
     
       for (String label : columns){
          
           XSSFCell cell = rowi.getCell((short) colmnscounter);
            if(cell==null){
                break;
            }
            
       
          else  if(cell.getCellType()==1)
            {
              value = cell.getStringCellValue(); 
              
            System.out.println(i+" nowvalue : "+value);
           
            }
           
             
            else{
               switch (cell.getCellType()) {
                   case 0:
                       //numeric
                       value =""+new BigDecimal(cell.getNumericCellValue()).toPlainString();
                       
                         System.out.println(i+" nowvalue : "+value+" cell type:"+cell.getCellType());
                       break;
                   case 1:
                       value =cell.getStringCellValue();
                       break;
                   default:
                       value = cell.getRawValue();
                       break;
               }
             
              
             }  
               
            if(!"Missing".equalsIgnoreCase(value)){}  else {
                value="";
                }
            
               if(value==null)
               {
          query+=label+"="+value+",";
        
//          checker_query+=label+"="+value+" AND ";
               }
               else{
                   
                   if(value.contains("'"))
                   {
                       value=value.replace("'", "");
                   }
               query+=label+"='"+value+"',";
              
            
               }
            
            
            
            
            colmnscounter++;
       }
       
       //Change gender
       
  
       
       
        SubPartnerID=getSubPartnerID(conn,mfl_code); 
        
        if(!SubPartnerID.equals("")){
      
            query=removeLast(query, 1);
            
      System.out.println("success : "+query);
         conn.st.executeUpdate(query);
            
            added++;
       
        }
        else{
//          System.out.println("mfl : "+mfl_code+" Facility is missing in our master facility list.");   
        }
        
        session.setAttribute("emr_viral_load", "<b>"+i+"/"+rowCount+"</b>");
        session.setAttribute("emr_viral_load_count", (i*100)/rowCount);
        compare_date(date_tested);
         
            i++;
        }
        
        j++;
        }
        
        
        
//Send the file via mail
AttachFileOnEmail sf = new AttachFileOnEmail();
GetFacilityDetails fd= new GetFacilityDetails();

XSSFCell facilmfl = workbook.getSheetAt(0).getRow(1).getCell((short) 9);
String Facii=fd.getFacilityName(facilmfl.getRawValue(), conn);

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
  try {
                  sf.SendEmail("QOC_KENYAEMR", Facii, "Uploaded Successfully!", full_path, fileName,  Uploader, "EMaingi@usaidtujengejamii.org,cbonde@usaidtujengejamii.org,DJuma@usaidtujengejamii.org"+em,usern);
      } catch (MessagingException ex) {
                  Logger.getLogger(UploadQOC.class.getName()).log(Level.SEVERE, null, ex);
              }
}
uploadedfiles.add(full_path);       
             

        
        }//end of worksheet
        
      
			   
        
        
        
        
        }
        
        session.setAttribute("emr_viral_load", "<b>Upload complete</b>");
        session.setAttribute("emr_viral_load_count", 100);
        //add data to dashboards
     
        session.removeAttribute("emr_viral_load");
        session.removeAttribute("emr_viral_load_count");
        
        // end of removing county attributes
        if(conn.rs!=null){
        conn.rs.close();
        }
        
        session.setAttribute("emr_vl_loaded", "Upload complete. <b style=\"color:green\">"+added+"</b> records were added and <b style=\"color:red\">"+updated+"</b> records were updated.");
       
        
        
        response.sendRedirect("uploadqoc.jsp");
        
      
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
      } catch (SQLException ex) {
          Logger.getLogger(UploadQOC.class.getName()).log(Level.SEVERE, null, ex);
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
      } catch (SQLException ex) {
          Logger.getLogger(UploadQOC.class.getName()).log(Level.SEVERE, null, ex);
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
    public String getSubPartnerID(dbConn conn, String code) throws SQLException{
     String subpatID="";
     
    String gett="SELECT SubPartnerID FROM subpartnera WHERE CentreSanteId=? AND ART=1 OR PMTCT=1";
    conn.pst=conn.conn.prepareStatement(gett);
    conn.pst.setString(1, code);
    conn.rs=conn.pst.executeQuery();
    if(conn.rs.next()){
        subpatID =conn.rs.getString(1);
    }
     
     return subpatID;
    }
    
        public String removeLast(String str, int num) {
    if (str != null && str.length() > 0) {
        str = str.substring(0, str.length() - num);
    }
    return str;
    }
        
        public void compare_date(String date){
            String c_date_key="",in_date_key="";
            if(min_date.equals("")){
                min_date=date;
            }
            else
            {
             c_date_key = min_date.replace("-", "");
             in_date_key = date.replace("-", "");
             
             if(Integer.parseInt(c_date_key)>=Integer.parseInt(in_date_key)){
              min_date=date;   
             }
             
            }
            
            if(max_date.equals("")){
                max_date=date;
            }
            else
            {
             c_date_key = max_date.replace("-", "");
             in_date_key = date.replace("-", "");
             
             if(Integer.parseInt(c_date_key)<=Integer.parseInt(in_date_key)){
              max_date=date;   
             }
             
            }
        }
        
  public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
}
