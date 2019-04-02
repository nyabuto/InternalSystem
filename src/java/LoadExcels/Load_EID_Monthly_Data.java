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
import java.text.SimpleDateFormat;
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
    String inserter,updator;
    
    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    String not_uploaded;
    int to_skip=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String dbname="eid_monthly_data";     
        String not_uploaded = "";
     inserter = "INSERT INTO "+dbname+" (id,facility_id,serial_no,hei_no,sex,dob,date_tested,results,date_initiated_art,remarks,reporting_month,reporting_year) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";   
     updator = "UPDATE "+dbname+" SET facility_id=?,serial_no=?,hei_no=?,sex=?,dob=?,date_tested=?,results=?,date_initiated_art=?,remarks=?,reporting_month=?,reporting_year=? WHERE id=? ";   
        
        
       added=missing=0;      
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
           if(!getFileName(part).equals("")){
           fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            System.out.println("file name is  :  "+fileName);
        
        if(!fileName.endsWith(".xlsx")){
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>"); 
          not_uploaded+=" Wrong File format for "+fileName;
        }
        else{
            not_uploaded+="<br><u>File Name:"+fileName+"</u></br>";
             if(database.OSValidator.isWindows()){
          full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
            }
            else if(database.OSValidator.isUnix()){
              full_path=fileSaveDir.getAbsolutePath()+"/"+fileName;  
            }
             
 System.out.println("the saved file directory is  :  "+full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
 
                        FileInputStream fileInputStream = new FileInputStream(full_path);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
                        
                                               no_sheets = workbook.getNumberOfSheets();
                                               
                                   System.out.println("no of sheets : "+no_sheets);  
                                  
                        for(int j=0;j<no_sheets;j++){
                            System.out.println("j is : "+j);
			XSSFSheet worksheet = workbook.getSheetAt(j);
                        to_skip=0;
                       //get facility and period data 
                     
                         String sheetName = worksheet.getSheetName();
                              System.out.println("Sheet Name : "+sheetName);
                              
                         if(!sheetName.contains("Instructions")){
                        not_uploaded+=j+". <u>"+sheetName+"</u><br>";
                    //     Converting the data type from any format to excel
                      if(worksheet.getRow(6).getCell(7)!=null){worksheet.getRow(6).getCell(7).setCellType(Cell.CELL_TYPE_STRING);}else{to_skip++; not_uploaded+="Missing Reporting Month<br>";}
                      if(worksheet.getRow(6).getCell(9)!=null){worksheet.getRow(6).getCell(9).setCellType(Cell.CELL_TYPE_STRING);}else{to_skip++; not_uploaded+="Missing Reporting Year<br>";}
                      if(worksheet.getRow(8).getCell(1)!=null){worksheet.getRow(8).getCell(1).setCellType(Cell.CELL_TYPE_STRING);}else{not_uploaded+="";}
                      if(worksheet.getRow(8).getCell(3)!=null){worksheet.getRow(8).getCell(3).setCellType(Cell.CELL_TYPE_STRING);}else{not_uploaded+="";}
                      if(worksheet.getRow(8).getCell(5)!=null){worksheet.getRow(8).getCell(5).setCellType(Cell.CELL_TYPE_STRING);}else{not_uploaded+="";}
                      if(worksheet.getRow(8).getCell(9)!=null){worksheet.getRow(8).getCell(9).setCellType(Cell.CELL_TYPE_STRING);}else{to_skip++; not_uploaded+="Missing MFL Code<br>";}
                      
                     if(to_skip==0){
                      //reading data from the header columns
                      reporting_month = worksheet.getRow(6).getCell(7).getStringCellValue().trim();
                      reporting_year = worksheet.getRow(6).getCell(9).getStringCellValue().trim();
                      //County = worksheet.getRow(8).getCell(1).getStringCellValue().trim();
                      //sub_county = worksheet.getRow(8).getCell(3).getStringCellValue().trim();
                      //facility = worksheet.getRow(8).getCell(5).getStringCellValue().trim();
                      mfl_code = worksheet.getRow(8).getCell(9).getStringCellValue().trim();
                        
                      if(reporting_month.length()!=3){to_skip++; not_uploaded+="Wrong Month Code. We Expect month Name Like Jan, Feb etc<br>";}
                      if(reporting_year.length()!=4){to_skip++; not_uploaded+="Wrong reporting Year we expect year like 2018, 2019<br>";}
                      if(mfl_code.length()!=5){to_skip++; not_uploaded+="Wrong MFL Code provided. MFL Code is 5 characters<br>";}
                        
                      System.out.println("reporting month : "+reporting_month+" reporting year : "+reporting_year);  
                      System.out.println("county : "+County+" sub county :"+sub_county+" facility code : "+facility+" code : "+mfl_code);
                      
                             
          SubPartnerID = getSubPartnerID(conn, mfl_code);
        if(SubPartnerID.equals("")){
            System.out.println("Missing Health facility for sheet, "+sheetName);
               not_uploaded+="This site with  MFL Code "+mfl_code+" is not in our Master Facility List.<br>";
               to_skip++; 
        }
        else{
            if(to_skip==0){
                          
			Iterator rowIterator = worksheet.iterator();
                           int rowCount = worksheet.getLastRowNum();
                        int i=12,y=0;
                        
			while(rowIterator.hasNext()){
                        XSSFRow rowi = worksheet.getRow(i);
                        if( rowi==null){
                            System.out.println("break here");
                         break;
                        }
           
           XSSFCell cell_0 = rowi.getCell((short) 0);
           XSSFCell cell_1 = rowi.getCell((short) 1);
           XSSFCell cell_2 = rowi.getCell((short) 2);
           XSSFCell cell_3 = rowi.getCell((short) 3);
           XSSFCell cell_4 = rowi.getCell((short) 4);
           XSSFCell cell_5 = rowi.getCell((short) 5);
           XSSFCell cell_6 = rowi.getCell((short) 6);
           XSSFCell cell_7 = rowi.getCell((short) 8);
                
             
            if(cell_0==null){break;}
            else{
              cell_0.setCellType(Cell.CELL_TYPE_STRING);
              serial_no = cell_0.getStringCellValue().trim();
              
              if(!isNumeric(serial_no)){
              break;    
              }
            }
             
             cell_1.setCellType(Cell.CELL_TYPE_STRING);
              hei_no = cell_1.getStringCellValue().trim();
             
               cell_2.setCellType(Cell.CELL_TYPE_STRING);
              sex = cell_2.getStringCellValue().trim();
              
             if(cell_5==null){
             results="";    
             }
             else{
               cell_5.setCellType(Cell.CELL_TYPE_STRING);
              results = cell_5.getStringCellValue().trim();
             }
              
              if(cell_7==null){
                  remarks="";
              }
              
              else{
               cell_7.setCellType(Cell.CELL_TYPE_STRING);
              remarks = cell_7.getStringCellValue().trim();
              }
              //DATE CELLS 
              
              if(cell_3.getCellType()==0){
          dob =""+dateformat.format(cell_3.getDateCellValue());
        }
        else{
           if (cell_3.getCellType()==1) {
               try{
            dob = dateformat.format(cell_3.getStringCellValue().trim());
               }
               catch (Exception e){
               dob = cell_3.getStringCellValue().trim();    
               }
        }
        else{
         dob = cell_3.getStringCellValue().trim();
        }
            }
    
              if(cell_4.getCellType()==0){
          date_tested =""+dateformat.format(cell_4.getDateCellValue());
        }
        else{
           if (cell_4.getCellType()==1) {
               
             try{
            date_tested = dateformat.format(cell_4.getStringCellValue().trim());
               }
               catch (Exception e){
               date_tested = cell_4.getStringCellValue().trim();    
               }  
        }
        else{
         date_tested = cell_4.getStringCellValue().trim();
        }
            }
              
              
              
              
              
              if(cell_6.getCellType()==0){
          date_initiated_art =""+dateformat.format(cell_6.getDateCellValue());
        }
        else{
           if (cell_6.getCellType()==1) {
               
             try{
            date_initiated_art = dateformat.format(cell_6.getStringCellValue().trim());
               }
               catch (Exception e){
               date_initiated_art = cell_6.getStringCellValue().trim();    
               }  
        }
        else{
         date_initiated_art = cell_6.getStringCellValue().trim();
        }
            }
          
              //format gender
              if(sex.equalsIgnoreCase("Male")){
                  sex="M";
              }
              else if(sex.equalsIgnoreCase("Female")){
                  sex="F";
              }
              
              //format results
               if(results.equalsIgnoreCase("Negative")){
                  results="N";
              }
              else if(results.equalsIgnoreCase("Positive")){
                  results="P";
              }
              else if(results.equalsIgnoreCase("Postive")){
                  results="P";
              }
              
              
              
            String  record_issue=""; int num_recs=0;
       if(hei_no.length()<1) {num_recs++; record_issue+="Hei no,";}      
       if(sex.length()!=1) {num_recs++; record_issue+="Sex,";}      
       if(dob.length()!=10) {num_recs++; record_issue+="Date of Birth,";}      
       if(date_tested.length()!=10) {num_recs++; record_issue+="Date EID Tested,";}      
//       if(!(date_initiated_art.length()==3 || date_initiated_art.length()==10)) {num_recs++; record_issue+="Date Initiated on ART,";}
       
         if(record_issue.length()>0 && num_recs<4){not_uploaded+="Record with S/NO: "+serial_no+" not uplodaded because it is missing: "+record_issue+"<br>";}     
         
         else{    
              //END
 
          // check data quality
              
        
        if(hei_no!=null && !hei_no.equals("")){
                   
        id=mfl_code+"_"+hei_no+"_"+date_tested;    
   
        checker_query="SELECT id FROM "+dbname+" WHERE id=? ";
        conn.pst = conn.conn.prepareStatement(checker_query);
        conn.pst.setString(1, id);
        conn.rs1 = conn.pst.executeQuery();

        if(conn.rs1.next()){
            
         conn.pst1 = conn.conn.prepareStatement(updator);
         conn.pst1.setString(1, SubPartnerID);
         conn.pst1.setString(2, serial_no);
         conn.pst1.setString(3, hei_no);
         conn.pst1.setString(4, sex);
         conn.pst1.setString(5, dob);
         conn.pst1.setString(6, date_tested);
         conn.pst1.setString(7, results);
         conn.pst1.setString(8, date_initiated_art);
         conn.pst1.setString(9, remarks);
         conn.pst1.setString(10, reporting_month);
         conn.pst1.setString(11, reporting_year);
         conn.pst1.setString(12, id);
         
         conn.pst1.executeUpdate();
            
            updated++;
        }
        else{
            
        conn.pst1 = conn.conn.prepareStatement(inserter);
        
         conn.pst1.setString(1, id);
         conn.pst1.setString(2, SubPartnerID);
         conn.pst1.setString(3, serial_no);
         conn.pst1.setString(4, hei_no);
         conn.pst1.setString(5, sex);
         conn.pst1.setString(6, dob);
         conn.pst1.setString(7, date_tested);
         conn.pst1.setString(8, results);
         conn.pst1.setString(9, date_initiated_art);
         conn.pst1.setString(10, remarks);
         conn.pst1.setString(11, reporting_month);
         conn.pst1.setString(12, reporting_year);
         
         conn.pst1.executeUpdate();
           
            added++;
        }

        
        session.setAttribute("eid_tested", "<b>"+i+"/"+rowCount+"</b>");
        session.setAttribute("eid_tested_count", (i*100)/rowCount);
               }
       }
        i++;
         }// end of reading rows
        }
        else{
//                Missing basic info
            }
        }//facility exist
                         }// details are ok basic info exist
                         else{
                          not_uploaded+="Missing basic information as highlighted above. <br>";   
                         }
        }// of of data sheet
        } // loop through the data sheets
        }
        }
        } // end of reading xlsx file 
        //     END    
            
         if(conn.rs!=null){conn.rs.close();}
         if(conn.st!=null){conn.st.close();}
         if(conn.pst!=null){conn.pst.close();}
         if(conn.conn!=null){conn.conn.close();}

         
        session.setAttribute("upload_success", "Upload complete. <b style=\"color:green\">"+added+"</b> records were added and <b style=\"color:red\">"+updated+"</b> records were updated.");
        session.setAttribute("upload_output", "UPLOAD STATUS <br>"+not_uploaded);
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
            String file_name="";
        String contentDisp = part.getHeader("content-disposition");
//        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
      
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                file_name = token.substring(token.indexOf("=") + 2, token.length()-1);
              break;  
            }
            
        }
//         System.out.println("content-disposition final : "+file_name);
        return file_name;
    }
   
    public String getSubPartnerID(dbConn conn, String code) throws SQLException{
     String subpatID="";
     
    String gett="SELECT SubPartnerID FROM subpartnera WHERE CentreSanteId=?";
        //System.out.println(gett);
    conn.pst=conn.conn.prepareStatement(gett);
    conn.pst.setString(1, code);
    conn.rs=conn.pst.executeQuery();
    if(conn.rs.next()){
        subpatID =conn.rs.getString(1);
    }
       // System.out.println("subpartneris : "+subpatID+" code : "+code);
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


