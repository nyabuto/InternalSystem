/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100) 

  public class Load_eid_tes_raw extends HttpServlet {
    String full_path="",fileName="";
    File file_source;
    HttpSession session;
    private static final long serialVersionUID = 205242440643911308L;
    private static final String UPLOAD_DIR = "uploads";
    int missing,added,updated;
    String min_date="",max_date="",date_tested="",agebracket="",upload_message="";
    String[] columns =  {"orderno","samplecode","batchno","testinglab","County","Sub_County","Partner","Facility","Mflcode","sex","dob","age_months","PCR_Type","enrollment_ccc","datecollected","datereceived","datetested","datedispatched","infantprophylaxis","receivedstatus","lab_comment","repeat_rejection_reason","spots","breastfeeding","entrypoint","testresult","pmtct_intervention","hivstatus_mum","mother_age","mother_cccno","mother_lastVL"};
    String query="",query_update="",value,checker_query,Age_Months;
    String year,quarter,SubPartnerID,mfl_code,month,yearmonth,id,samplecode,system_id;
      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
     added=missing=0;   
    String dbname="eid_raw_tested";    
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
             if(OSValidator.isWindows()){
          full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
            }
            else if(OSValidator.isUnix()){
             full_path=fileSaveDir.getAbsolutePath()+"/"+fileName;   
            }

 System.out.println("the saved file directory is  :  "+full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
 
                        FileInputStream fileInputStream = new FileInputStream(full_path);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet worksheet = workbook.getSheetAt(0);
			Iterator rowIterator = worksheet.iterator();
                           int rowCount = worksheet.getLastRowNum();
                        int i=1,y=0;
			while(rowIterator.hasNext()){
                        XSSFRow rowi = worksheet.getRow(i);
                        if( rowi==null){
                                
                         break;
                        }
                            System.out.println("added is : "+added);
                            
            query = "INSERT INTO "+dbname+" SET ";
            query_update = "UPDATE "+dbname+" SET ";
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
            if(label.equals("datetested")){
               date_tested =  value; 
            }
                System.out.println(i+" nowdate : "+value);
            }
            
            else{
               switch (cell.getCellType()) {
                   case 0:
                       //numeric
                       if(colmnscounter==11){
                  value =""+cell.getRawValue();               
                       }
                       else{
                       value =""+cell.getRawValue(); 
                       
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
            
            colmnscounter++;
       }
        
   if(isNumeric(Age_Months)){agebracket=getageBracket(Double.parseDouble(Age_Months));}   
   id=system_id+"_"+samplecode+"_"+date_tested;    
   
   String[] datesvalues = getperiod(date_tested,conn);
    year = datesvalues[0];
    quarter = datesvalues[1];
//    quartername = datesvalues[2];
        SubPartnerID=getSubPartnerID(conn,mfl_code); 
    query_update +="year='"+year+"',quarter='"+quarter+"',SubPartnerID='"+SubPartnerID+"' WHERE id='"+id+"'";
    query +="year='"+year+"',quarter='"+quarter+"',SubPartnerID='"+SubPartnerID+"', id='"+id+"'";

            System.out.println("insert : "+query);
            System.out.println("update : "+query_update);

    //end of adding todashboards
              
        if(!SubPartnerID.equals("")){
            //REMOVE LAST ELEMENT 
            //END OF REMOVING LAST ELEMENT
               
        checker_query="SELECT id FROM "+dbname+" WHERE samplecode=? AND  Mflcode=? AND orderno=?";
        conn.pst = conn.conn.prepareStatement(checker_query);
        conn.pst.setString(1, samplecode);
        conn.pst.setString(2, mfl_code);
        conn.pst.setString(3, system_id);
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
        System.out.println("CURRENT EID Test date : "+date_tested);
        compare_date(date_tested);
            System.out.println("Current date : "+date_tested+" Min date : "+min_date+" max date : "+max_date);
            i++;
        }
         
        }
        
        session.setAttribute("eid_tested", "<b>Upload complete. Syncing Data to Dashboards system</b>");
        session.setAttribute("eid_tested_count", 100);
        //add dashboard data
         PushDataSet2 ds2 = new PushDataSet2();
           
            Map m1 = new HashMap(); 
            m1.put("startdate", min_date);
            m1.put("enddate", max_date);
            
            ds2.pmtct_eid(m1);//eid tested and pos
        
        //remove counter attributes
        
        session.removeAttribute("eid_tested");
        session.removeAttribute("eid_tested_count");
        
        // end of removing county attributes
        
        //     END    
            
         if(conn.rs!=null){conn.rs.close();}
         if(conn.st!=null){conn.st.close();}
         if(conn.pst!=null){conn.pst.close();}
         if(conn.conn!=null){conn.conn.close();}

         
        session.setAttribute("upload_success", "Upload complete. <b style=\"color:green\">"+added+"</b> records were added and <b style=\"color:red\">"+updated+"</b> records were updated.");
        response.sendRedirect("load_eid_tested.jsp");
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
          Logger.getLogger(Load_eid_tes_raw.class.getName()).log(Level.SEVERE, null, ex);
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
          Logger.getLogger(Load_eid_tes_raw.class.getName()).log(Level.SEVERE, null, ex);
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
    public String getSubPartnerID(dbConn conn, String code) throws SQLException{
     String subpatID="";
     
    String gett="SELECT SubPartnerID FROM subpartnera WHERE CentreSanteId=? AND (ART=1 OR PMTCT=1) and active=1";
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
       year=dateparameters[0];
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
}
