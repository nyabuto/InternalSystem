/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GapAnalysis;

import database.dbConn;
import database.dbConnWeb;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author GNyabuto
 */
public class LoadGaps extends HttpServlet {
    String output;
    HttpSession session;
    String full_path="";
    String fileName="";
    File file_source;
    private static final long serialVersionUID = 205242440643911308L;
    private static final String UPLOAD_DIR = "uploads";
    String[] columns =  {"rule","gap","program_area","county","sub_county","facility","year","month","ward","latitude","longitude"};
    String year,month;
    int pepfaryear;
    String query="",value,query_checker;
    ArrayList deletedrecords = new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        dbConnWeb  conn = new dbConnWeb(); // it uploads gaps to the set web server
        session = request.getSession();

        deletedrecords.clear();
        
        
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
        query=value="";
        year=month="";
  FileInputStream fileInputStream = new FileInputStream(full_path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        int j=0;
//        int number_sheets = workbook.getNumberOfSheets();
        int number_sheets = 1;
        while(j<number_sheets){
        XSSFSheet worksheet;
        
        worksheet = workbook.getSheetAt(j);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        while(rowIterator.hasNext()){
            query = "INSERT INTO gaps SET ";
            query_checker = "SELECT id FROM gaps WHERE ";
             int colmnscounter=0;
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;}
        
        
       for (String label : columns){
          
           XSSFCell cell = rowi.getCell((short) colmnscounter);
            if(cell==null){
                break;
            }
            else{
               switch (cell.getCellType()) {
                   case 0:
                       //numeric
                       value =""+(int)cell.getNumericCellValue();
                       break;
                   case 1:
                       value =cell.getStringCellValue();
                       break;
                   default:
                       value = cell.getRawValue();
                       break;
               }
              if(label.equals("latitude") || label.equals("longitude")) {
               value = ""+(Double)cell.getNumericCellValue();   
              }
               
               if(value==null){
          query+=label+"="+value+",";
               }
               else{
                   if(value.contains("'")){
                       value=value.replace("'", "");
                   }
               query+=label+"='"+value+"',";    
               query_checker+=label+"='"+value+"' AND ";    
               }
            
            }
            if(colmnscounter==6){
                year = value;
            }
            if(colmnscounter==7){
                if(!month.equals(value)){
                month = value;
                // clear previous gaps data
                if(!deletedrecords.contains(year+""+month)){ // if the yearmonth has not already been deleted.. do delete
                String deleter = "DELETE FROM gaps WHERE year=? AND month=? AND status=?";
                conn.pst = conn.conn.prepareStatement(deleter);
                conn.pst.setString(1, year);
                conn.pst.setString(2, month);
                conn.pst.setInt(3, 0);
                
                    System.out.println("deleter: "+conn.pst);
                conn.pst.executeUpdate();
                deletedrecords.add(year+""+month);
                }
                else{ // if it has already been deleted, skip
                    
                }
                }
                
                else{
              month = value;   
            } 
            }
            
            colmnscounter++;
       } 
// check the record, add or jump it
   query=removeLast(query,1);    
   query_checker=removeLast(query_checker,5);
   
//check existence
conn.rs = conn.st.executeQuery(query_checker);
if(conn.rs.next()){
 System.out.println("EXIST>>> : "+query_checker);   
}
else{
    conn.st.executeUpdate(query);
    System.out.println("success>>> : "+query);
}

     i++;
        }
        
        j++;
        }
          
        }
     
//   END OF EXCEL OUTPUT     
session.setAttribute("gaps", "Gaps uploaded successfully.");
        
        response.sendRedirect("UploadGaps.jsp");
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
            Logger.getLogger(LoadGaps.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoadGaps.class.getName()).log(Level.SEVERE, null, ex);
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
  
   public String removeLast(String str, int num) {
    if (str != null && str.length() > 0) {
        str = str.substring(0, str.length() - num);
    }
    return str;
    }  
}
