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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author GNyabuto
 */
public class Load_EID_Monthly_Data extends HttpServlet {
    
    String full_path="",fileName="";
    File file_source;
    HttpSession session;
    private static final long serialVersionUID = 205242440643911308L;
    private static final String UPLOAD_DIR = "uploads";
    int missing,added,updated;
    String[] columns_shared = {"facility_id","reporting_month","reporting_year"};
    String[] columns =  {"serial_no","hei_no","sex","dob","date_tested","results","date_initiated_art","remarks"};
    
    String query="",query_update="",value,checker_query;
    String SubPartnerID,mfl_code,id;
    String reporting_year,reporting_month,County,sub_county,facility;
    int no_sheets=0;
    String serial_no,hei_no,sex,dob,date_tested,results,date_initiated_art,remarks;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       added=missing=0;   
    String dbname="eid_monthly_data";    
      session=request.getSession();
      dbConn conn = new dbConn();
   added=missing=0;
   //---------------------------------------------------------------------
  
      String applicationPath = request.getServletContext().getRealPath("");
         String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
         session=request.getSession();
          File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
        
        for (Part part : request.getParts()) {
           fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            System.out.println("file name is  :  "+fileName);
        }
        if(!fileName.endsWith(".xlsx")){
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");   
        }
        else{
            added=missing=0;
            full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;

 System.out.println("the saved file directory is  :  "+full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
 
                        FileInputStream fileInputStream = new FileInputStream(full_path);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
                        
                                               no_sheets = workbook.getNumberOfSheets();
                                               
                                               
                        for(int j=0;j<no_sheets;j++){
			XSSFSheet worksheet = workbook.getSheetAt(j);
                        
                       //get facility and period data 
                     
                         String sheetName = worksheet.getSheetName();
                         
                         if(sheetName.contains("template") || sheetName.contains("Continuation")){
                             System.out.println("Sheet Name : "+sheetName);
                    //     Converting the data type from any format to excel
                      worksheet.getRow(6).getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                      worksheet.getRow(6).getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                      worksheet.getRow(8).getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                      worksheet.getRow(8).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                      worksheet.getRow(8).getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                      worksheet.getRow(8).getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                      
                      //reading data from the header columns
                      reporting_month = worksheet.getRow(6).getCell(7).getStringCellValue();
                      reporting_year = worksheet.getRow(6).getCell(9).getStringCellValue();
                      County = worksheet.getRow(8).getCell(1).getStringCellValue();
                      sub_county = worksheet.getRow(8).getCell(3).getStringCellValue();
                      facility = worksheet.getRow(8).getCell(5).getStringCellValue();
                      mfl_code = worksheet.getRow(8).getCell(9).getStringCellValue();
                        
                        
                             System.out.println("reporting month : "+reporting_month+" reporting year : "+reporting_year);  
                             System.out.println("county : "+County+" sub county :"+sub_county+" facility code : "+facility+" code : "+mfl_code);
                      
			Iterator rowIterator = worksheet.iterator();
                           int rowCount = worksheet.getLastRowNum();
                        int i=11,y=0;
			while(rowIterator.hasNext()){
                        XSSFRow rowi = worksheet.getRow(i);
                        if( rowi==null){
                         break;
                        }
                            System.out.println("added is : "+added);
                            
            query = "INSERT INTO "+dbname+" SET ";
            query_update = "UPDATE "+dbname+" SET ";
        SubPartnerID=mfl_code="";            
            
          
           XSSFCell cell_0 = rowi.getCell((short) 0);
           XSSFCell cell_1 = rowi.getCell((short) 1);
           XSSFCell cell_2 = rowi.getCell((short) 2);
           XSSFCell cell_3 = rowi.getCell((short) 3);
           XSSFCell cell_4 = rowi.getCell((short) 4);
           XSSFCell cell_5 = rowi.getCell((short) 5);
           XSSFCell cell_6 = rowi.getCell((short) 6);
           XSSFCell cell_7 = rowi.getCell((short) 7);
                
             
            if(cell_0==null){break;}
            else{
              cell_0.setCellType(Cell.CELL_TYPE_STRING);
              serial_no = cell_0.getStringCellValue();
            }
             
             cell_1.setCellType(Cell.CELL_TYPE_STRING);
              hei_no = cell_1.getStringCellValue();
              
              
               cell_2.setCellType(Cell.CELL_TYPE_STRING);
              sex = cell_2.getStringCellValue();
              
              
               cell_3.setCellType(Cell.CELL_TYPE_STRING);
              dob = cell_3.getStringCellValue();
              
               cell_4.setCellType(Cell.CELL_TYPE_STRING);
              date_tested = cell_4.getStringCellValue();
              
              
               cell_5.setCellType(Cell.CELL_TYPE_STRING);
              results = cell_5.getStringCellValue();
              
              
               cell_6.setCellType(Cell.CELL_TYPE_STRING);
              date_initiated_art = cell_6.getStringCellValue();
              
              
               cell_7.setCellType(Cell.CELL_TYPE_STRING);
              remarks = cell_7.getStringCellValue();
            
          // check data quality
          
              
              
             
   id=mfl_code+"_"+hei_no+"_"+date_tested;    
   
   
        SubPartnerID=getSubPartnerID(conn,mfl_code); 
    query_update +="SubPartnerID='"+SubPartnerID+"' WHERE id='"+id+"'";
    query +="SubPartnerID='"+SubPartnerID+"', id='"+id+"'";

            System.out.println("insert : "+query);
            System.out.println("update : "+query_update);

    //end of adding todashboards
              
        if(!SubPartnerID.equals("")){
            //REMOVE LAST ELEMENT 
            //END OF REMOVING LAST ELEMENT
               
        checker_query="SELECT id FROM "+dbname+" WHERE id=? ";
        conn.pst = conn.conn.prepareStatement(checker_query);
        conn.pst.setString(1, id);
        conn.rs1 = conn.pst.executeQuery();

        if(conn.rs1.next()){
            
        conn.st.executeUpdate(query_update);
            updated++;
        }
        else{
         conn.st.executeUpdate(query);
            added++;
        }
        }
        else{
          System.out.println("mfl : "+mfl_code+" Facility is missing in our master facility list.");   
        }
        
        session.setAttribute("eid_tested", "<b>"+i+"/"+rowCount+"</b>");
        session.setAttribute("eid_tested_count", (i*100)/rowCount);

                            System.out.println("sheet : "+sheetName+" pos : "+i);
        i++;
         }
        }
       j++;
        }
     
        } 
        //     END    
            
         if(conn.rs!=null){conn.rs.close();}
         if(conn.st!=null){conn.st.close();}
         if(conn.pst!=null){conn.pst.close();}
         if(conn.conn!=null){conn.conn.close();}

         
        session.setAttribute("upload_success", "Upload complete. <b style=\"color:green\">"+added+"</b> records were added and <b style=\"color:red\">"+updated+"</b> records were updated.");
        response.sendRedirect("UploadEID.jsp");
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
            Logger.getLogger(Load_EID_Monthly_Data.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Load_EID_Monthly_Data.class.getName()).log(Level.SEVERE, null, ex);
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
   
    public String getSubPartnerID(dbConn conn, String code) throws SQLException{
     String subpatID="";
     
    String gett="SELECT SubPartnerID FROM subpartnera WHERE CentreSanteId=?";
        System.out.println(gett);
    conn.pst=conn.conn.prepareStatement(gett);
    conn.pst.setString(1, code);
    conn.rs=conn.pst.executeQuery();
    if(conn.rs.next()){
        subpatID =conn.rs.getString(1);
    }
        System.out.println("subpartneris : "+subpatID+" code : "+code);
     return subpatID;
    }
    public String removeLast(String str, int num) {
    if (str != null && str.length() > 0) {
        str = str.substring(0, str.length() - num);
    }
    return str;
    }
    public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}   
}


