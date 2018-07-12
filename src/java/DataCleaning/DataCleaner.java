/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataCleaning;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author GNyabuto
 */
public class DataCleaner extends HttpServlet {
HttpSession session;
String report_type;
String full_path="";
String fileName="";
File file_source;
private static final long serialVersionUID = 205242440643911308L;
private static final String UPLOAD_DIR = "uploads";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        dbConn conn = new dbConn();
        DataCleanerClass dcleaner = new DataCleanerClass();

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
        if(!fileName.endsWith(".xlsx")){
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");   
        }
        else{
          full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
 
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
      FileInputStream fileInputStream = new FileInputStream(full_path);
        XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
      
        report_type = request.getParameter("report_type");
        if(report_type.equals("tb")){
          wb =   dcleaner.TB(wb);
        }
        else if(report_type.equals("viralload")){
          wb =   dcleaner.ViralLoad(wb);
        }
        else if(report_type.equals("tb")){
          wb =   dcleaner.EIDTST(wb);
        }
        else if(report_type.equals("tb")){
          wb =   dcleaner.EIDPOS(wb);
        }
        else{}
        
    }
        
        
        IdGenerator IG = new IdGenerator();
        String createdOn = IG.CreatedOn();

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
       // wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=IMIS_DHIS_Comparison_" + createdOn + ".xlsx");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        outStream.close();  
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
}
