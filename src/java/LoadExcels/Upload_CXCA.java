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
public class Upload_CXCA extends HttpServlet {
String data_columns[][]={{"a_u","a_19","a_24","a_29","a_34","a_39","a_44","a_49","a_50","a_total"},{"b_u","b_19","b_24","b_29","b_34","b_39","b_44","b_49","b_50","b_total"},{"c_u","c_19","c_24","c_29","c_34","c_39","c_44","c_49","c_50","c_total"},{"d_u","d_19","d_24","d_29","d_34","d_39","d_44","d_49","d_50","d_total"},{"e_u","e_19","e_24","e_29","e_34","e_39","e_44","e_49","e_50","e_total"},{"f_u","f_19","f_24","f_29","f_34","f_39","f_44","f_49","f_50","f_total"},{"g_u","g_19","g_24","g_29","g_34","g_39","g_44","g_49","g_50","g_total"},{"h_u","h_19","h_24","h_29","h_34","h_39","h_44","h_49","h_50","h_total"},{"i_u","i_19","i_24","i_29","i_34","i_39","i_44","i_49","i_50","i_total"},{},{"j_u","j_19","j_24","j_29","j_34","j_39","j_44","j_49","j_50","j_total"},{"k_u","k_19","k_24","k_29","k_34","k_39","k_44","k_49","k_50","k_total"},{"l_u","l_19","l_24","l_29","l_34","l_39","l_44","l_49","l_50","l_total"},{"m_u","m_19","m_24","m_29","m_34","m_39","m_44","m_49","m_50","m_total"},{"n_u","n_19","n_24","n_29","n_34","n_39","n_44","n_49","n_50","n_total"},{"o_u","o_19","o_24","o_29","o_34","o_39","o_44","o_49","o_50","o_total"},{"p_u","p_19","p_24","p_29","p_34","p_39","p_44","p_49","p_50","p_total"},{"q_u","q_19","q_24","q_29","q_34","q_39","q_44","q_49","q_50","q_total"},{"r_u","r_19","r_24","r_29","r_34","r_39","r_44","r_49","r_50","r_total"}};
String other_columns[]={"id","SubPartnerID","user_id","timestamp","isValidated","yearmonth","updatedBy","updatedOn","isLocked","quarter"};
HttpSession session;
String sub_county,facility,mflcode,year,month,yearmonth,facilityID,id;
int pepfaryear,existing_values;
private static final long serialVersionUID = 205242440643911308L;
private static final String UPLOAD_DIR = "uploads";
String full_path="";
String fileName="";
File file_source;
String output="",sheetName =""; 
String query="";
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         dbConn  conn = new dbConn();
        session = request.getSession();
        
        output="";
            XSSFSheet  worksheet;
        
        
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
            
            if(OSValidator.isWindows()){
            full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
            }
            else if(OSValidator.isUnix()){
             full_path=fileSaveDir.getAbsolutePath()+"/"+fileName;   
            }
            System.out.println("fullpath : "+full_path);
           // read the contents of the workbook and sheets here
           
           FileInputStream fileInputStream = new FileInputStream(full_path);
            
//           obj_files.put("file_name", fileName);
           
//****************************WORKBOOK INFORMATION****************************************
        //Call to read all sheets.
              
       
       //call sheet for accounting for linkages--validation
       
       XSSFWorkbook workbook = null;
       if(fileName.endsWith(".xlsx")){
       workbook = new XSSFWorkbook(fileInputStream);
       output+="<br><b><u>File Name:"+fileName+"</u></b><br>";
//       loop through the sheets to read data
       int all_sheets = workbook.getNumberOfSheets();
       for(int q=0;q<all_sheets;q++){
           existing_values = 0;
           query="REPLACE INTO cxca SET ";
           
           
          worksheet = workbook.getSheetAt(q);
          sheetName = worksheet.getSheetName();
           //System.out.println("SheetName : "+sheetName);   
          if(sheetName.contains("CXCA Reporting")){
             // System.out.println("Correct Sheet is : "+sheetName);
              
              XSSFRow row3 = worksheet.getRow(2);
              XSSFCell cell;
              cell = row3.getCell(3);
              cell.setCellType(1);
              sub_county = cell.getStringCellValue();
              
              cell = row3.getCell(5);
              cell.setCellType(1);
              facility = cell.getStringCellValue();
              cell = row3.getCell(7);
              cell.setCellType(1);
              mflcode = cell.getStringCellValue();
              cell = row3.getCell(10);
              cell.setCellType(1);
              year = cell.getStringCellValue();
              cell = row3.getCell(13);
              cell.setCellType(1);
              month = cell.getStringCellValue();
       
              
              
              
              yearmonth=year+""+month;
              
              
                      
              facilityID = getSubPartnerID(conn, mflcode);
              
              if(!(facilityID.equals("") || year.equals("") || month.equals(""))){
               
              id=yearmonth+"_"+facilityID; 
              
              query = query+ " id='"+id+"', SubPartnerID='"+facilityID+"', ";
              
//              System.out.println("Sub county : "+sub_county+", facility : "+facility+" mflcode : "+mflcode+" year : "+year+" month : "+month+"");
               
             //start reading data here in arraylike 
              
              
             for(int i=0;i<data_columns.length;i++){
               String row_data[] = data_columns[i];
               XSSFRow row = worksheet.getRow(i+5);
                for(int j=0; j<row_data.length;j++){
                    String column_name = row_data[j];
                  XSSFCell cell_data  =  row.getCell(j+4);
                  cell_data.setCellType(1); // convert to string cellvalue
                  String value = cell_data.getStringCellValue();
                  if(isNumeric(value)){
                      if(Integer.parseInt(value)>0){
                  query = query+ " "+column_name+"='"+value+"', "; 
                  existing_values++;
                  }
                 }

                }
             }
            
            if(existing_values>0){
             query = query+ " yearmonth='"+yearmonth+"'"; 
             System.out.println(sheetName+" query:"+query);
             
             conn.st.executeUpdate(query);
             
             
            }
            else{
                //System.out.println(sheetName+" of "+yearmonth+ " has no data");
            }
           
              //System.out.println("Final Query : "+query);
          }
          else{
//              missing facility, year or month data
          }
          }
          else{
              System.out.println("Wrong sheetName :"+sheetName);
          }
        
       }
       
       }
       else{
           System.out.println("Wrong file format");
           output+="<br><b><u>Wrong file format for file "+fileName+". Your file must end with .xlsx </u></b><br>";
       }
        }
    }
               
     
                response.sendRedirect("Upload_CXCA.jsp");
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
        Logger.getLogger(Upload_CXCA.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(Upload_CXCA.class.getName()).log(Level.SEVERE, null, ex);
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
     
    String gett="SELECT SubPartnerID FROM subpartnera WHERE CentreSanteId=?";
    conn.pst=conn.conn.prepareStatement(gett);
    conn.pst.setString(1, code);
    conn.rs=conn.pst.executeQuery();
    if(conn.rs.next()){
        subpatID =conn.rs.getString(1);
    }
     
     return subpatID;
    }
    
    public boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
    }
}
