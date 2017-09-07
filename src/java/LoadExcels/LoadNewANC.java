/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoadExcels;

import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
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
public class LoadNewANC extends HttpServlet {
    String output;
    HttpSession session;
    String full_path="";
    String fileName="";
    File file_source;
    private static final long serialVersionUID = 205242440643911308L;
    private static final String UPLOAD_DIR = "uploads";
    String  SubPartnerID,mfl_code,new_anc,year,month,year_month,id;
    int added,updated,counter, pepfaryear;
    int mois;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        session = request.getSession();
        dbConn conn = new dbConn();
        
         SubPartnerID=mfl_code=new_anc=year=month=year_month;
         added=updated=pepfaryear=0;
         
        String applicationPath = request.getServletContext().getRealPath("");
         String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
         session=request.getSession();
          File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        
        for (Part part : request.getParts()) {
           fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
        }
        if(!fileName.endsWith(".xlsx")){
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");   
        }
        else{
          full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
 
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
 
  FileInputStream fileInputStream = new FileInputStream(full_path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet worksheet = workbook.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        while(rowIterator.hasNext()){
        SubPartnerID=mfl_code=new_anc=year=month=year_month="";
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){

         break;
                        }
            System.out.println("here");
//        3_______________________mfl_code__________________________
            XSSFCell cellserialno = rowi.getCell((short) 3);
            if(cellserialno==null){
                break;
            }
            
            if(cellserialno.getCellType()==0){
                //numeric
           mfl_code =""+(int)cellserialno.getNumericCellValue();
            } 
            else if(cellserialno.getCellType()==1){
           mfl_code =cellserialno.getStringCellValue();
            } 
            
            
            
        
//        4_______________________new_anc__________________________
            XSSFCell cellBatch_No = rowi.getCell((short) 4);
            if(cellBatch_No.getCellType()==0){
                //numeric
           new_anc =""+(int)cellBatch_No.getNumericCellValue();
            } 
            else if(cellBatch_No.getCellType()==1){
           new_anc =cellBatch_No.getStringCellValue();
            } 
            
            
            
        
//        5_______________________year__________________________
            XSSFCell cellPatient_CCC_No = rowi.getCell((short) 5);
            if(cellPatient_CCC_No.getCellType()==0){
                //numeric
           year =""+(int)cellPatient_CCC_No.getNumericCellValue();
            } 
            else if(cellPatient_CCC_No.getCellType()==1){
           year =cellPatient_CCC_No.getStringCellValue();
            } 
            
            
            
        String mn="";
//        6_______________________month__________________________
            XSSFCell cellTesting_Lab = rowi.getCell((short) 6);
            if(cellTesting_Lab.getCellType()==0){
                //numeric
           mn =""+(int)cellTesting_Lab.getNumericCellValue();
            } 
            else if(cellTesting_Lab.getCellType()==1){
           mn =cellTesting_Lab.getStringCellValue();
            } 
            
            month = getMonth(mn);
            mois=Integer.parseInt(month);
            if(mois>=10){
                pepfaryear=Integer.parseInt(year)+1;
            }
            else{
                pepfaryear = Integer.parseInt(year);
            }
            
            year_month=year+""+month;
        SubPartnerID=getSubPartnerID(conn,mfl_code);    
         id=pepfaryear+"_"+mois+"_"+SubPartnerID;   
         
         String checker = "SELECT SubPartnerID FROM new_anc WHERE SubPartnerID=?" ;
         conn.pst=conn.conn.prepareStatement(checker);
         conn.pst.setString(1, SubPartnerID);
         
         conn.rs=conn.pst.executeQuery();
         if(conn.rs.next()){
            id=conn.rs.getString(1);
            
           //update the record
           String updater = "UPDATE new_anc SET mfl_code=?,new_anc=?, year=?,month=?, "
            + "yearmonth=?,SubPartnerID=? WHERE id=?";
           
           conn.pst1=conn.conn.prepareStatement(updater);
           conn.pst1.setString(1, mfl_code);
           conn.pst1.setString(2, new_anc);
           conn.pst1.setInt(3, pepfaryear);
           conn.pst1.setInt(4, mois);
           conn.pst1.setString(5, year_month);
           conn.pst1.setString(6, SubPartnerID);
           conn.pst1.setString(7, id);
           
           conn.pst1.executeUpdate();
           updated++;
         }
         else{
          String inserter = "INSERT INTO new_anc (SubPartnerID,mfl_code,new_anc,year,month,yearmonth,id) VALUES(?,?,?,?,?,?,?)"; 
           
           conn.pst1 = conn.conn.prepareStatement(inserter);
           conn.pst1.setString(1, SubPartnerID);
           conn.pst1.setString(2, mfl_code);
           conn.pst1.setString(3, new_anc);
           conn.pst1.setInt(4, pepfaryear);
           conn.pst1.setInt(5, mois);
           conn.pst1.setString(6, year_month);
           conn.pst1.setString(7, id);
//           
            System.out.println("conn.pst : "+conn.pst1);
//             System.out.println("query: "+conn.pst1);
           conn.pst1.executeUpdate();
           
           
           added++;
         }
              
            i++;
        }
        session.setAttribute("stf_synced", "");
        
        String message="<font color=\"green\">"+added+"</font> <font color=\"black\">New records added successfully.</font> <font color=\"blue\">"+updated+"</font> <font color=\"black\">Records updated.</font>" ; 
        session.setAttribute("upload_success", message);  
        }
        
        response.sendRedirect("UploadNewANCData.jsp");
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
        Logger.getLogger(LoadNewANC.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(LoadNewANC.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    public String getMonth(String st_month){
        int mn=Integer.parseInt(st_month);
        String month_1="";
        if(mn<10){
        month_1="0"+mn;    
        }
        else{
            month_1=""+mn;
        }
        
        return month_1;
    }
    
    public String getSubPartnerID(dbConn conn, String code) throws SQLException{
     String subpatID="";
     
    String gett="SELECT SubPartnerID FROM subpartnera WHERE CentreSanteId=?";
    conn.pst=conn.conn.prepareStatement(gett);
    conn.pst.setString(1, code);
    conn.rs=conn.pst.executeQuery();
    if(conn.rs.next()){
        subpatID =conn.rs.getString(1);
    }
     
     return subpatID;
    }
    
}
