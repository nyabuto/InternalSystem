/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoadExcels;

import dashboards.PushDataSet2;
import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
  String query="",query_update="",value,checker_query;
  String SubPartnerID,mfl_code,year,month,yearmonth,id;
  String[] columns =  {"System_ID","Batch_No","Patient_CCC_No","Testing_Lab","County","Sub_County","Partner","Facility_Name","MFL_Code","Sex","DOB","AgeYrs","Sample_Type","Date_Collected","Justification","Date_Received","Date_Tested","Date_Dispatched","ART_Initiation_Date","Received_Status","Reason_for_Repeat","Rejected_Reason","Regimen","Regimen_Line","PMTCT","Value"};
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
        }
        if(!fileName.endsWith(".xlsx")){
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");   
        }
        else{
           if(OSValidator.isWindows()){
          full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
            }
            else if(OSValidator.isUnix()){
             full_path=fileSaveDir.getAbsolutePath()+"/"+fileName;   
            }
 
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
            query = "INSERT INTO vl_validation SET ";
            query_update = "UPDATE vl_validation SET ";
            checker_query="SELECT autokey FROM vl_validation WHERE ";
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
            
             else if("DOB,Date_Collected,Date_Received,Date_Tested,Date_Dispatched,ART_Initiation_Date".contains(label)){
            if(cell.getCellType()==1){
              value = cell.getStringCellValue(); 
            }
            else{
                try{
              value = dateformat.format(cell.getDateCellValue()); 
                }
                catch(Exception e){
                 value="";   
                }
            }
            if(label.equalsIgnoreCase("Date_Tested")){
              date_tested =  value; 
            }
                System.out.println(i+" nowdate : "+value);
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
               
              if(colmnscounter==9){
                 if(value==null){value="";}
                  if(value.trim().equalsIgnoreCase("Male")){
                      value="M";
                  }
                  else if(value.trim().equalsIgnoreCase("Female")){
                      value="F";
                  }
            } 
             }  
               
               if(value==null){
          query+=label+"="+value+",";
          query_update+=label+"="+value+",";
//          checker_query+=label+"="+value+" AND ";
               }
               else{
                   
                   if(value.contains("'")){
                       value=value.replace("'", "");
                   }
               query+=label+"='"+value+"',";
               query_update+=label+"='"+value+"',";
               if(colmnscounter<=3){
               checker_query+=label+"='"+value+"' AND "; 
               }
               }
            
            
            if(colmnscounter==8){
                mfl_code = value;
            }
            
            if(label.equalsIgnoreCase("Value")){
                value_vl = value;
            }
            
            colmnscounter++;
       }
       
       //Change gender
       
       
       
       //end of changing gender
       
       
       //update suppressed column
       if(value_vl.contains("<")){
         query+="Suppressed='Y',Valid_Result='Y'";  
         query_update+="Suppressed='Y',Valid_Result='Y'";  
       }
       else{
         if(isNumeric(value_vl.trim())){
            if(Integer.parseInt(value_vl.replace(" ", ""))>1000){
                  query+="Suppressed='N',Valid_Result='Y'"; 
                  query_update+="Suppressed='N',Valid_Result='Y'"; 
             }
             else{
               query+="Suppressed='Y',Valid_Result='Y'"; 
               query_update+="Suppressed='Y',Valid_Result='Y'";    
             }
         } 
         else{
              query+="Valid_Result='N'"; 
               query_update+="Valid_Result='N'";   
         }
       }
       
       
       
        SubPartnerID=getSubPartnerID(conn,mfl_code); 
        if(!SubPartnerID.equals("")){
            //REMOVE LAST ELEMENT 
               checker_query=removeLast(checker_query,5); 
            //END OF REMOVING LAST ELEMENT
            System.out.println("CHECKER : "+checker_query);
//            System.out.println(query);
            
//            check existence
        conn.rs1 = conn.st1.executeQuery(checker_query);
        if(conn.rs1.next()){
            
//            System.out.println("record not added : "+mfl_code);
        query_update+=" WHERE autokey='"+conn.rs1.getString(1)+"'";
        conn.st.executeUpdate(query_update);
            updated++;
        }
        else{
         conn.st.executeUpdate(query);
//            System.out.println("success : "+query);
            added++;
        }
        }
        else{
//          System.out.println("mfl : "+mfl_code+" Facility is missing in our master facility list.");   
        }
        
        session.setAttribute("viral_load", "<b>"+i+"/"+rowCount+"</b>");
        session.setAttribute("viral_load_count", (i*100)/rowCount);
        compare_date(date_tested);
            System.out.println("Current date : "+date_tested+" Min date : "+min_date+" max date : "+max_date);
            i++;
        }
        
        j++;
        } 
        }
        
        session.setAttribute("viral_load", "<b>Upload complete. Syncing Data to Dashboards system</b>");
        session.setAttribute("viral_load_count", 100);
        //add data to dashboards
         PushDataSet2 ds2 = new PushDataSet2();
           
            Map m1 = new HashMap(); 
            m1.put("startdate", min_date);
            m1.put("enddate", max_date);
            
            ds2.viral_load(m1);//viral load data upload
        //end of adding data to dashboards.
        
        //remove counter attributes
        
        session.removeAttribute("viral_load");
        session.removeAttribute("viral_load_count");
        
        // end of removing county attributes
        
        conn.rs.close();
        conn.rs1.close();
        
        session.setAttribute("vl_loaded", "Upload complete. <b style=\"color:green\">"+added+"</b> records were added and <b style=\"color:red\">"+updated+"</b> records were updated.");
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
