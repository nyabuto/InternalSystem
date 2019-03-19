/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataCleaning;

import General.IdGenerator;
import database.OSValidator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author GNyabuto
 */
public class DataCleaner extends HttpServlet {
HttpSession session;
String report_type,start_date,end_date;
String full_path="";
String fileName="";
File file_source;
boolean unsupported=true;
private static final long serialVersionUID = 205242440643911308L;
private static final String UPLOAD_DIR = "uploads";
      XSSFSheet worksheet=null;
      HSSFSheet worksheet1=null;
      boolean checked=false;
 String fullname,UserID,date_accessed,id;  
 String month,date;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException, InvalidFormatException {
        session = request.getSession();
        dbConn conn = new dbConn();
        WhoCleaned Who = new WhoCleaned();
        fullname=UserID=date_accessed=id="";
        
        if(session.getAttribute("userid")==null){ //exit application
          session.setAttribute("upload_success", "<font color=\"red\"></b>ERROR: We need to know you. Logout and login afresh.</b></red>");
            response.sendRedirect("DataCleaner.jsp");   
        }
        else{
         UserID = session.getAttribute("userid").toString();
         date_accessed=today();
         
         //get full name
         
         String getuser = "SELECT CAP_FIRST(CONCAT(fname,' ',mname,' ',lname)) AS fullname FROM user WHERE userid=?";
         conn.pst = conn.conn.prepareStatement(getuser);
         conn.pst.setString(1, UserID);
         conn.rs = conn.pst.executeQuery();
         if(conn.rs.next()){
             fullname = conn.rs.getString(1);
         }
         else{
             fullname="";
         }
         
            
        report_type = request.getParameter("report_type");
      
        start_date = request.getParameter("start_date");
        end_date = request.getParameter("end_date");
        
        unsupported=false;
        
      OPCPackage pkg  = null;
//      SXSSFWorkbook wb = null;
      XSSFWorkbook wb = null;
      XSSFWorkbook wb_prev = null; // EID TST Prev data
      HSSFWorkbook wb2 = null;
      HSSFWorkbook wb_prev2 = null; // EID TST Prev data
      
      
      
      DataCleanerClass dcleaner = new DataCleanerClass();
        
        
        if(report_type.equals("eidtst")){
            id=Who.save_started_cleaning(fullname,UserID,date_accessed);
            
         String applicationPath2 = request.getServletContext().getRealPath("");
         String uploadFilePath2 = applicationPath2 + File.separator + UPLOAD_DIR;
          File fileSaveDir2 = new File(uploadFilePath2);
        if (!fileSaveDir2.exists()) {
            fileSaveDir2.mkdirs();
        }
        
        for (Part part : request.getParts()) {
            if(!getFileNamePrev(part).equals("")){
           fileName = getFileNamePrev(part);
            part.write(uploadFilePath2 + File.separator + fileName);
            }
        }
        if(fileName.endsWith(".xls")){
        full_path=fileSaveDir2.getAbsolutePath()+"\\"+fileName;
        if(OSValidator.isUnix()){ full_path=fileSaveDir2.getAbsolutePath()+"/"+fileName;  }
        FileInputStream fileInputStream = new FileInputStream(full_path);
        wb_prev2 = new HSSFWorkbook(fileInputStream);
        worksheet1 = wb_prev2.getSheetAt(0);
        }
        else if(fileName.endsWith(".xlsx")){
        full_path=fileSaveDir2.getAbsolutePath()+"\\"+fileName;
        if(OSValidator.isUnix()){ full_path=fileSaveDir2.getAbsolutePath()+"/"+fileName;  }
        FileInputStream fileInputStream = new FileInputStream(full_path);
        wb_prev = new XSSFWorkbook(fileInputStream);
        worksheet = wb_prev.getSheetAt(0);
            System.out.println("full path : "+full_path);
        }
        else{
          unsupported=true;  
        }
        }
        

        //end of dropping yearmonth data
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
        }
        if(fileName.endsWith(".xls")){
    full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
     if(OSValidator.isUnix()){ full_path=fileSaveDir.getAbsolutePath()+"/"+fileName;  }
 
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
      FileInputStream fileInputStream = new FileInputStream(full_path);
//       File allpathfile= new File(full_path);
//  pkg = OPCPackage.open(allpathfile);
//  XSSFWorkbook wb1 = new XSSFWorkbook(pkg);
//  wb = new SXSSFWorkbook(wb1, 100); 
  wb2 = new HSSFWorkbook(fileInputStream);
      
          // for the red color
    CellStyle redstyle = wb2.createCellStyle();
    redstyle.setFillForegroundColor(HSSFColor.RED.index);
    redstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    redstyle.setBorderTop(CellStyle.BORDER_THIN);
    redstyle.setBorderTop(CellStyle.BORDER_THIN);
    redstyle.setBorderTop(CellStyle.BORDER_THIN);
    redstyle.setBorderTop(CellStyle.BORDER_THIN);
    redstyle.setBorderTop(CellStyle.BORDER_THIN);
    redstyle.setWrapText(true);
    
    CellStyle styleborder = wb2.createCellStyle();
    styleborder.setBorderTop(CellStyle.BORDER_THIN);
    styleborder.setBorderTop(CellStyle.BORDER_THIN);
    styleborder.setBorderTop(CellStyle.BORDER_THIN);
    styleborder.setBorderTop(CellStyle.BORDER_THIN);
    styleborder.setBorderTop(CellStyle.BORDER_THIN);
    styleborder.setWrapText(true);
    
        
        if(report_type.equals("tb")){
          wb2 =  dcleaner.TB(wb2,redstyle,start_date,end_date);
        }
        else if(report_type.equals("vl")){
          wb2 =  dcleaner.ViralLoad(wb2,redstyle,styleborder,start_date,end_date);
        }
        else if(report_type.equals("eidtst")){
          wb2 = dcleaner.EIDTST(worksheet1,wb2,redstyle,styleborder,start_date,end_date);
          Who.update_status(id);
        }
        else if(report_type.equals("eidpos")){
          wb2 =  dcleaner.EIDPOS(wb2,redstyle,start_date,end_date);
        }
        else{}
     
        
                
        IdGenerator IG = new IdGenerator();
        String createdOn = IG.CreatedOn();

      
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb2.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename="+createdOn+"_"+fileName.replace(" ", "_")+"");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        outStream.close(); 

        }
        else if(fileName.endsWith(".xlsx")){ //FOR xlsx
          full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
           if(OSValidator.isUnix()){ full_path=fileSaveDir.getAbsolutePath()+"/"+fileName;  }
 
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
      FileInputStream fileInputStream = new FileInputStream(full_path);
//       File allpathfile= new File(full_path);
//  pkg = OPCPackage.open(allpathfile);
//  XSSFWorkbook wb1 = new XSSFWorkbook(pkg);
//  wb = new SXSSFWorkbook(wb1, 100); 
  wb = new XSSFWorkbook(fileInputStream);
      
          // for the red color
    CellStyle redstyle = wb.createCellStyle();
    redstyle.setFillForegroundColor(HSSFColor.RED.index);
    redstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    redstyle.setBorderTop(CellStyle.BORDER_THIN);
    redstyle.setBorderTop(CellStyle.BORDER_THIN);
    redstyle.setBorderTop(CellStyle.BORDER_THIN);
    redstyle.setBorderTop(CellStyle.BORDER_THIN);
    redstyle.setBorderTop(CellStyle.BORDER_THIN);
    redstyle.setWrapText(true);
    
    CellStyle styleborder = wb.createCellStyle();
    styleborder.setBorderTop(CellStyle.BORDER_THIN);
    styleborder.setBorderTop(CellStyle.BORDER_THIN);
    styleborder.setBorderTop(CellStyle.BORDER_THIN);
    styleborder.setBorderTop(CellStyle.BORDER_THIN);
    styleborder.setBorderTop(CellStyle.BORDER_THIN);
    styleborder.setWrapText(true);
        
        if(report_type.equals("tb")){
          wb =  dcleaner.TB(wb,redstyle,start_date,end_date);
        }
        else if(report_type.equals("vl")){
          wb =  dcleaner.ViralLoad(wb,redstyle,styleborder,start_date,end_date);
        }
        else if(report_type.equals("eidtst")){
          wb = dcleaner.EIDTST(worksheet,wb,redstyle,styleborder,start_date,end_date);
          Who.update_status(id);
        }
        else if(report_type.equals("eidpos")){
          wb =  dcleaner.EIDPOS(wb,redstyle,start_date,end_date);
        }
        else{}
      
        
                
        IdGenerator IG = new IdGenerator();
        String createdOn = IG.CreatedOn();

      
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename="+createdOn+"_"+fileName.replace(" ", "_")+"");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        outStream.close(); 

    }
        
        else{
          session.setAttribute("upload_success", "<font color=\"red\"></b>ERROR: YOU HAVE UPLOADED AN UNSUPPORTED DOCUMENT FORMAT. WE ALLOW .xlsx or xls.</b></red>");
            response.sendRedirect("DataCleaner.jsp");
        }
        }
        
//        
//        IdGenerator IG = new IdGenerator();
//        String createdOn = IG.CreatedOn();
//
//      
//        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
//        wb.write(outByteStream);
//        byte[] outArray = outByteStream.toByteArray();
//        response.setContentType("application/ms-excel");
//        response.setContentLength(outArray.length);
//        response.setHeader("Expires:", "0"); // eliminates browser caching
//        response.setHeader("Content-Disposition", "attachment; filename="+createdOn+"_"+fileName.replace(" ", "_")+"");
//        OutputStream outStream = response.getOutputStream();
//        outStream.write(outArray);
//        outStream.flush();
//        outStream.close(); 

        
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
    } catch (ParseException ex) {
        Logger.getLogger(DataCleaner.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DataCleaner.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(DataCleaner.class.getName()).log(Level.SEVERE, null, ex);
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
    } catch (ParseException ex) {
        Logger.getLogger(DataCleaner.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DataCleaner.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(DataCleaner.class.getName()).log(Level.SEVERE, null, ex);
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
        if(contentDisp.contains("name=\"file_name\";")){
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
      
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                file_name = token.substring(token.indexOf("=") + 2, token.length()-1);
              break;  
            }   
        }
         System.out.println("content-disposition final current : "+file_name);
        }
        else{
            
        }
        return file_name;
    }
        
        private String getFileNamePrev(Part part) {
        String file_name="";
        String contentDisp = part.getHeader("content-disposition");
        if(contentDisp.contains("name=\"file_name_prev\";")){
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
      
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                file_name = token.substring(token.indexOf("=") + 2, token.length()-1);
              break;  
            }   
        }
         System.out.println("content-disposition final : "+file_name);
        }
        else{
            
        }
        return file_name;
    }
        
        private String today(){
       Calendar cal = Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int mn=cal.get(Calendar.MONTH)+1;
        int dt=cal.get(Calendar.DATE);
        
        if(mn<10){month="0"+mn;} else{month=""+mn;}
        if(dt<10){date="0"+dt;} else{date=""+dt;}
         
        return year+"-"+month+"-"+date;
        }
}
