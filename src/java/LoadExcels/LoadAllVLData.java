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
public class LoadAllVLData extends HttpServlet {
  String full_path="";
  String fileName="";
  File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";
  String query="",value,checker_query;
  String SubPartnerID,mfl_code,year,month,yearmonth,id;
  String[] columns =  {"`#`","System_ID","Batch_No","Patient_CCC_No","Testing_Lab","Partner","County","Sub_County","Facility_Name","MFL_Code","Sex","AgeYrs","Sample_Type","Date_Collected","Received_Status","`Reason for Repeat / Rejection`","Regimen","Other_Regimen","Justification","PMTCT","ART_Initiation_Date","Date_Received","Date_Tested","Date_Dispatched","Valid_Result","Value","Suppressed"};
  int skipped,added;
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        dbConn  conn = new dbConn();
        session = request.getSession();
        
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
        skipped=added=0;
  FileInputStream fileInputStream = new FileInputStream(full_path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        int j=0;
        int number_sheets = workbook.getNumberOfSheets();
        while(j<number_sheets){
        XSSFSheet worksheet;
        
        worksheet = workbook.getSheetAt(j);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        while(rowIterator.hasNext()){
            query = "INSERT INTO vl_validation SET ";
            checker_query="SELECT System_ID FROM vl_validation WHERE ";
             int colmnscounter=0;
        SubPartnerID=mfl_code="";
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
               if(value==null){
          query+=label+"="+value+",";
//          checker_query+=label+"="+value+" AND ";
               }
               else{
                   if(value.contains("'")){
                       value=value.replace("'", "");
                   }
               query+=label+"='"+value+"',";
               if(colmnscounter<=3){
               checker_query+=label+"='"+value+"' AND "; 
               }
               }
            
            }
            if(colmnscounter==9){
                mfl_code = value;
            }
            colmnscounter++;
       } 
        SubPartnerID=getSubPartnerID(conn,mfl_code); 
        if(!SubPartnerID.equals("")){
            //REMOVE LAST ELEMENT
               query=removeLast(query,1);    
               checker_query=removeLast(checker_query,5); 
            //END OF REMOVING LAST ELEMENT
            System.out.println("CHECKER : "+checker_query);
//            System.out.println(query);
            
//            check existence
        conn.rs1 = conn.st1.executeQuery(checker_query);
        if(conn.rs1.next()){
            System.out.println("record not added : "+mfl_code);
            skipped++;
        }
        else{
         conn.st.executeUpdate(query);
            System.out.println("success : "+query);
            added++;
        }
        }
        else{
          System.out.println("mfl : "+mfl_code+" Facility is missing in our master facility list.");   
        }
            i++;
        }
        
        j++;
        }
          
        }
        
        session.setAttribute("vl_loaded", "Upload complete. <b style=\"color:green\">"+added+"</b> records were added/updated and <b style=\"color:red\">"+skipped+"</b> records were skipped.");
        response.sendRedirect("UploadVL.jsp");
        
      
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
          Logger.getLogger(LoadAllVLData.class.getName()).log(Level.SEVERE, null, ex);
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
          Logger.getLogger(LoadAllVLData.class.getName()).log(Level.SEVERE, null, ex);
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
}
