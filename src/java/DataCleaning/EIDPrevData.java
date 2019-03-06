/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataCleaning;

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
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author GNyabuto
 */
public class EIDPrevData extends HttpServlet {
String full_path="";
String fileName="";
File file_source;
private static final long serialVersionUID = 205242440643911308L;
private static final String UPLOAD_DIR = "uploads";
XSSFSheet worksheet=null;
HSSFSheet worksheet1=null;

SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
         dbConn conn = new dbConn();
         
      XSSFWorkbook wb_prev = null; // EID TST Prev data
        
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
        if(fileName.endsWith(".xlsx")){
        full_path=fileSaveDir2.getAbsolutePath()+"\\"+fileName;
        FileInputStream fileInputStream = new FileInputStream(full_path);
        wb_prev = new XSSFWorkbook(fileInputStream);
        worksheet = wb_prev.getSheetAt(0);
        upload_eid_tst_prev_data(worksheet,conn);
            System.out.println("full path : "+full_path);
        }
        else{ // wrong file
        }
 
        response.sendRedirect("EIDPrevData.jsp");
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
        Logger.getLogger(EIDPrevData.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(EIDPrevData.class.getName()).log(Level.SEVERE, null, ex);
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

    public void upload_eid_tst_prev_data(XSSFSheet worksheet, dbConn conn) throws SQLException{
      
     conn.st.executeUpdate("TRUNCATE eid_tested_prev"); // delete all data
      
          int missing=0,added=0,updated=0;
          String dbname="eid_tested_prev";   
    String min_date="",max_date="",date_tested="",agebracket="",upload_message="";
    String[] columns =  {"orderno","samplecode","batchno","testinglab","County","Sub_County","Partner","Facility","Mflcode","sex","dob","age_months","PCR_Type","enrollment_ccc","datecollected","datereceived","datetested","datedispatched","infantprophylaxis","receivedstatus","lab_comment","repeat_rejection_reason","spots","breastfeeding","entrypoint","testresult","pmtct_intervention","hivstatus_mum","mother_age","mother_cccno","mother_lastVL"};
    String query="",query_update="",value,checker_query,Age_Months="";
    String year,quarter,SubPartnerID,mfl_code,month,yearmonth,id,samplecode,system_id="";

        Iterator rowIterator = worksheet.iterator();
           int rowCount = worksheet.getLastRowNum();
        int i=1,y=0;
        while(rowIterator.hasNext()){
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){

         break;
        }
          System.out.println("added is : "+added);
                            
            query = "REPLACE INTO "+dbname+" SET ";
            query_update = "UPDATE "+dbname+" SET ";
            checker_query="";
             int colmnscounter=0;
        SubPartnerID=mfl_code=date_tested=samplecode="";            
                            
        for (String label : columns){
          
           XSSFCell cell = rowi.getCell((short) colmnscounter);
            if(cell==null){
                break;
            }
            
           else if("dob,datecollected,datereceived,datetested,datedispatched".contains(label)){
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
                System.out.println(i+" nowdate : "+value);
            }
            else{
               switch (cell.getCellType()) {
                   case 0:
                       //numeric
                       if(colmnscounter==11){
                  value =""+(double)cell.getNumericCellValue();               
                       }
                       else{
                       value =""+(int)cell.getNumericCellValue();     
                       }
                       break;
                   case 1:
                       value =cell.getStringCellValue();
                       break;
                   default:
                       value = cell.getRawValue();
                       break;
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
            if(colmnscounter==0){
                system_id = value;
            }
            if(colmnscounter==1){
                samplecode = value;
            }
            if(colmnscounter==8){
                mfl_code = value;
            }
            if(colmnscounter==11){
               Age_Months =  value; 
            }
            if(colmnscounter==16){
               date_tested =  value; 
            }
            
            colmnscounter++;
       }
        
   if(isNumeric(Age_Months)){agebracket=getageBracket(Double.parseDouble(Age_Months));}   
   id=system_id+"_"+samplecode+"_"+date_tested;    
   if(!date_tested.equalsIgnoreCase("")){
   String[] datesvalues = getperiod(date_tested,conn);
    year = datesvalues[0];
    quarter = datesvalues[1];
   }
   else{
    year = "";
    quarter = "";     
   }
//    quartername = datesvalues[2];
        SubPartnerID=getSubPartnerID(conn,mfl_code); 
    query_update +="year='"+year+"',quarter='"+quarter+"',SubPartnerID='"+SubPartnerID+"' WHERE id='"+id+"'";
    query +="year='"+year+"',quarter='"+quarter+"',SubPartnerID='"+SubPartnerID+"', id='"+id+"'";

           // System.out.println("insert : "+query);
            System.out.println("update : "+query_update);

    //end of adding todashboards
              
        if(!SubPartnerID.equals("")){
            //REMOVE LAST ELEMENT 
         conn.st.executeUpdate(query);
            added++;
        
        }
        else{
          //System.out.println("mfl : "+mfl_code+" Facility is missing in our master facility list.");   
        }
           // System.out.println("Current date : "+date_tested+" Min date : "+min_date+" max date : "+max_date);
            i++;
        }   
  }
    public String[] getperiod(String date_tested, dbConn conn) throws SQLException{

    String QuarterName="",QuarterID="",yr="";

    String dateparameters[]=date_tested.split("-");
    if(dateparameters.length==3){

     if(!dateparameters[0].equals("")){//ensure tha date field is valid
       String month="";
       month=dateparameters[1];
       if(month.equalsIgnoreCase("01")||month.equalsIgnoreCase("02")||month.equalsIgnoreCase("03")){

       QuarterName="January-March"; 

           if(dateparameters[0].length()==4)
       {
       yr=dateparameters[0];
       }


       }
       else if(month.equalsIgnoreCase("04")||month.equalsIgnoreCase("05")||month.equalsIgnoreCase("06")){

           QuarterName="April-June"; 
           if(dateparameters[0].length()==4)
       {
       yr=dateparameters[0];
       }

       }

       else if(month.equalsIgnoreCase("07")||month.equalsIgnoreCase("08")||month.equalsIgnoreCase("09")){

           QuarterName="July-September";  
             if(dateparameters[0].length()==4)
       {
       yr=dateparameters[0];
       }

       }
        else if(month.equalsIgnoreCase("10")||month.equalsIgnoreCase("11")||month.equalsIgnoreCase("12")){

           QuarterName="October-December";  
            if(dateparameters[0].length()==4)
       {
           //assume
       yr=""+Integer.parseInt(dateparameters[0])+1;
       }
        }		   


String getQuarterID="SELECT id FROM quarter WHERE pmtct_fo_name like ?";
   conn.pst=conn.conn.prepareStatement(getQuarterID);
   conn.pst.setString(1, QuarterName);
   conn.rs=conn.pst.executeQuery();

if(conn.rs.next()==true){
    QuarterID=conn.rs.getString(1);
}
     }
    }
String response[]={yr,QuarterID,QuarterName};
   
return response;
 }
    public String getSubPartnerID(dbConn conn, String code) throws SQLException{
     String subpatID="";
     
    String gett="SELECT SubPartnerID FROM subpartnera WHERE CentreSanteId=? AND (ART=1 OR PMTCT=1)";
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
    public String getageBracket(Double age){
    //<1	1-4	5-9  10-14	15-19	20+
        String finalbracket="";
if(age<1){
finalbracket="<1";
}
else if(age>=1&&age<=4){
finalbracket="1-4";
                        }
else if(age>=5&&age<=9){
finalbracket="5-9";
                        }
else if(age>=10&&age<=14){
finalbracket="10-14";
                        }
else if(age>=15&&age<=19){
finalbracket="15-19";
                        }
else if(age>=20){
finalbracket="20+";
                        }
else {
finalbracket="no age";
}
  return finalbracket;  
    }
      
    public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
 
    private String getFileNamePrev(Part part) {
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
         System.out.println("content-disposition final : "+file_name);
        }
        else{
            
        }
        return file_name;
    }
}
