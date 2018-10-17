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
public class UploadPrepNew extends HttpServlet {
String data_columns[]={"f_1","f_1_9","f_10_14","f_15_19","f_20_24","f_25_29","f_30_34","f_35_39","f_40_49","f_50","female","m_1","m_1_9","m_10_14","m_15_19","m_20_24","m_25_29","m_30_34","m_35_39","m_40_49","m_50","male","total","current_prep","hts_discordant","pmtct_discordant","total_discondants","variance","linked_other_facility","on_preparation","condom_use","comments"};
String other_columns[]={"id","SubPartnerID","userid","yearmonth"};

HttpSession session;
String sub_county,facility,mflcode,year,month,yearmonth,facilityID,id;
int pepfaryear,existing_values;
private static final long serialVersionUID = 205242440643911308L;
private static final String UPLOAD_DIR = "uploads";
String full_path="";
String fileName="";
File file_source;
String output="",sheetName ="",info=""; 
String query="";
int total_workbooks=0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         dbConn  conn = new dbConn();
        session = request.getSession();
        
        output = info = "";
            XSSFSheet  worksheet;
        
        
         String applicationPath = request.getServletContext().getRealPath("");
         String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
         session=request.getSession();
          File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        
            total_workbooks = request.getParts().size();
            
                for (Part part : request.getParts()) {
                    System.out.println("total_workbooks : "+total_workbooks);
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
       info+="<br><b><u>File Name:"+fileName+"</u></b><br><br>";
//       loop through the sheets to read data
        mflcode="";
       int all_sheets = workbook.getNumberOfSheets();
       for(int q=0;q<all_sheets;q++){
   
          worksheet = workbook.getSheetAt(q);
          sheetName = worksheet.getSheetName();
           //System.out.println("SheetName : "+sheetName);  
             // System.out.println("Correct Sheet is : "+sheetName);
              Iterator rowIterator = worksheet.iterator();
             
             int i=5,y=0;
        while(rowIterator.hasNext()){
           existing_values = 0;
           query="REPLACE INTO prep_new SET ";
           
             XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;}
              XSSFCell cell;
              cell = rowi.getCell(3);
              cell.setCellType(1);
              mflcode = cell.getStringCellValue();
              
              year="2018";
              month="09";
              yearmonth=year+""+month;
                     
              facilityID = getSubPartnerID(conn, mflcode);
              
              if(!(facilityID.equals("") || year.equals("") || month.equals(""))){
               
              id=yearmonth+"_"+facilityID; 
              
              query = query+ " id='"+id+"', SubPartnerID='"+facilityID+"', ";
              
//              System.out.println("Sub county : "+sub_county+", facility : "+facility+" mflcode : "+mflcode+" year : "+year+" month : "+month+"");
               
             //start reading data here in arraylike 
              
                for(int j=0; j<data_columns.length;j++){
                    String column_name = data_columns[j];
                  XSSFCell cell_data  =  rowi.getCell(j+9);
                  cell_data.setCellType(1); // convert to string cellvalue
                  String value = cell_data.getStringCellValue();
                  if(isNumeric(value)){
                  query = query+ " "+column_name+"='"+value+"', "; 
                  existing_values++;
                  
                 }
                }
             
            if(existing_values>10){
             query = query+ " yearmonth='"+yearmonth+"'"; 
             System.out.println(sheetName+" query:"+query);
             
             conn.st.executeUpdate(query);
             info+="<b style=\"color:black\">Successfully uploaded</b>: Row"+i+"<br>";
            }
            else{
                info+="<b style=\"color:red\">No Data</b>: Ro"+i+"<br>";
                //System.out.println(sheetName+" of "+yearmonth+ " has no data");
            }
           
              //System.out.println("Final Query : "+query);
          }
          else{
                  info+="<b style=\"color:red\">Missing MFL Code, year or month</b>: "+sheetName+"<br>";
                  
//              missing facility, year or month data
          }
        i++;  
        }
        session.setAttribute("prep_new", "<b>"+q+"/"+(total_workbooks)+"</b>");
        session.setAttribute("prep_new_count", (q*100)/(total_workbooks));
         
        
       }
       
       }
       else{
              info+="<b style=\"color:red\">Wrong file uploaded</b>:"+fileName+"<br>";
                      
           System.out.println("Wrong file format");
           output+="<br><b><u>Wrong file format for file "+fileName+". Your file must end with .xlsx </u></b><br>";
       }
        }
    }   
                
        session.setAttribute("prep_new", "<b>Upload complete.</b>");
        session.setAttribute("prep_new_count", 100);
        
        
     session.setAttribute("cxca_uploaded", info);
     response.sendRedirect("Upload_PrePNew.jsp");
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
        Logger.getLogger(UploadPrepNew.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(UploadPrepNew.class.getName()).log(Level.SEVERE, null, ex);
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
