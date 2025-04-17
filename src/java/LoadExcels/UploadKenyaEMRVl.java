/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoadExcels;

import General.AttachFileOnEmail;
import General.GetFacilityDetails;
import dashboards.PushDataSet2;
import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
@MultipartConfig(fileSizeThreshold=1024*1024*30, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100) 

/**
 *
 * @author GNyabuto
 */
public class UploadKenyaEMRVl extends HttpServlet {
  String full_path="";
  String fileName="";
  File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";
  String query="",query_update="",value,checker_query;
  String SubPartnerID,mfl_code,year,month,yearmonth,id;
  String[] columns =  {"vl_kenyaemr_id","AgeYrs","Sex","cccno","HIV_Enrollment_Date","ART_Start_Date","Last_VL","Last_VL_Date","Facility_Name","MFL_Code","Justification","PMTCT","Yearmonth","Next_appointment_date","Last_Visit_Date","Indicator","regimen_name","regimen_line","uzito","Patient_stable","Differentiated_care","Population_Type","key_population_type","pregnancy_status","expected_delivery_date","family_planning_status","family_planning_method","Screened_for_Cervical_Cancer","VisitScheduled","cxca_Screened_last6Months","pregnant_last12months","mch","patient_type","height","Date_of_Birth","nupi","ART_Regimen_at_init","Date_Start_Current_Regimen","phone","date_confirmed_hiv_positive","Age_Bracket","has_ncd","ncd_Name","systolic_pressure","diastolic_pressure","cd4_results","cd4_collection_Date","enrolled_in_cpims","ovc_comprehensive_program","CPIMS_unique_identifier","Enrolled_in_ovc","Enrolled_in_otz","WHO_stage","ptn_county","ptn_subcounty","ptn_location","ptn_sublocation","ptn_village","ptn_landmark","screened_for_tb","tb_status","spatum_smear_ordered","genexpert_ordered","chest_xray_ordered","spatum_smear_result","genexpert_result","chest_xray_result","clinical_tb_diagnosis","referral","cvd_Ever_vaccinated","first_vaccine_type","first_dose_date","second_vaccine_type","second_dose_date","final_vaccination_status","ever_received_booster","booster_vaccine_taken","on_ipt","ever_on_ipt","pregnancy_status_","breastfeeding","IPTStartDate","ushauri_consent","nutritional_status","person_present","arv_adherence","total_listed_contacts","biological_children","parents","siblings","sexual_partner","co_wife","sns","biological_children_hiv_neg","biological_children_hiv_pos","biological_children_unknown_hiv","sexual_partner_hiv_neg","sexual_partner_hiv_pos","sexual_partner_unknown_hiv","sibling_hiv_neg","sibling_hiv_pos","sibling_unknown_hiv","parent_hiv_neg","parent_hiv_pos","parent_unknown_hiv","pwp_disclosure","pwp_pead_disclosure","pwp_partner_tested","condom_provided","substance_abuse_screening","screened_for_sti","cacx_screening","sti_partner_notification","at_risk_population","Last_phq9_screening_Date","phq9_rating","ipt_outcome_date","ipt_outcome","Date_Hypertension_recorded","Patient_Has_Hypertension","Hypertension_Onset_Date","Hypertension_Contolled","risk_eval_date","iit_risk_category","risk_factors","Serum_Cryptococcal_Ag_Done","Date_Cryptococcal_Ag_Done","Last_Visit_Weight","Screening_Type_CXCA_Form","Screening_Method_CXCA_Form","Screening_Result_CXCA_Form","Treatment_Status_CXCA_Form","Last_SCreening_Date_CXCA_Form","On_DSD","DSD_model","Date_Started_DSD","On_DSD_abv_1_Yr","Retained_on_DSD_abv_1_yr","Virally_Supressed","Date_Diabetes_recorded","Patient_Has_Diabetis","Diabetes_Onset_Date","Diabetes_Contolled","Client_Eligible_for_IPT_TPT","GAD7_Date_Last_Screened_for_Anxiety","GAD7_Anxiety_Screening_Outcome","alcohol_drinking_frequency","smoking_frequency","drugs_use_frequency","Date_Last_Screened_for_Alcohol_Abuse","Heis_Ever_Enrolled","Deliveries_Ever_Done","PNCs_Ever_Done","ANCs_Ever_Done","Prep_Enrollments_Ever_Done"};
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
         ArrayList uploadedfiles=new ArrayList();
        if(!fileName.endsWith(".xlsx")){
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");   
        }
        else{
          full_path=fileSaveDir.getAbsolutePath()+"/"+fileName;
 
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
        query=query_update=value="";
        updated=added=0;
  FileInputStream fileInputStream = new FileInputStream(full_path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        int j=0;
        int number_sheets = workbook.getNumberOfSheets();
        while(j<number_sheets){
        XSSFSheet worksheet;
        String hasdetected_vl="no";
        worksheet = workbook.getSheetAt(j);
        Iterator rowIterator = worksheet.iterator();
        int rowCount = worksheet.getLastRowNum();
        int i=1,y=0;
        while(rowIterator.hasNext()){
           
//            session.removeAttribute("viral_load");
            query = "REPLACE INTO vl_kenyaemr SET ";
           
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
            
       
          else  if(cell.getCellType()==1)
            {
              value = cell.getStringCellValue(); 
              
            System.out.println(i+" nowvalue : "+value);
           
            }
           
             
          else 
            {
               switch (cell.getCellType()) {
                   case 0:
                       //numeric
                       value =""+new BigDecimal(cell.getNumericCellValue()).toPlainString();
                       
                         System.out.println(i+" nowvalue : "+value+" cell type:"+cell.getCellType());
                       break;
                   case 1:
                       value =cell.getStringCellValue();
                       break;
                   default:
                       value = cell.getRawValue();
                       break;
               }
             
              
             }  
               
            if(!"Missing".equalsIgnoreCase(value)){}  else 
                {
                value="";
                }
            
               if(value==null)
               {
          query+=label+"="+value+",";
        
//          checker_query+=label+"="+value+" AND ";
               }
               
               else 
               {
                   
                   if(value.contains("'"))
                   {
                       value=value.replace("'", "");
                   }
                   
                   if(label.equals("Last_VL"))
                   {
                   
                   if( value.equals("DETECTED") || value.equals("BEYOND DETECTABLE LIMIT") || value.equals("POOR SAMPLE QUALITY") )
                   {
                       hasdetected_vl="yes";
                   value="";
                   }
                       
                   }
                   if(label.equals("Last_VL_Date"))
                   {
                     if(hasdetected_vl.equals("yes"))
                     {
                  
                         value="";
                         hasdetected_vl="no";
                     }
                   
                   }
                   
                   
               query+=label+"='"+value+"',";
              
            
                }
            
            
            colmnscounter++;
       }//end of for loop
       
       //Change gender
       
  
       
       
        SubPartnerID=getSubPartnerID(conn,mfl_code); 
        
        if(!SubPartnerID.equals("")){
      
            query=removeLast(query, 1);
            
      System.out.println("success : "+query);
         conn.st.executeUpdate(query);
            
            added++;
       
        }
        else{
//          System.out.println("mfl : "+mfl_code+" Facility is missing in our master facility list.");   
        }
        
        session.setAttribute("emr_viral_load", "<b>"+i+"/"+rowCount+"</b>");
        session.setAttribute("emr_viral_load_count", (i*100)/rowCount);
        compare_date(date_tested);
         
            i++;
        }
        
        j++;
        }
        
        
        
//Send the file via mail
AttachFileOnEmail sf = new AttachFileOnEmail();
GetFacilityDetails fd= new GetFacilityDetails();

XSSFCell facilmfl = workbook.getSheetAt(0).getRow(1).getCell((short) 9);
String Facii=fd.getFacilityName(facilmfl.getRawValue(), conn);

String Uploader="Not Captured";
String em="";
String usern="";

if(session.getAttribute("email")!=null){
    em=","+session.getAttribute("email").toString();
}
if(session.getAttribute("fullname")!=null){
    usern=session.getAttribute("fullname").toString();
}
if(session.getAttribute("username")!=null){
    Uploader=session.getAttribute("username").toString();
}



if(!uploadedfiles.contains(full_path))
{
  try {
                  sf.SendEmail("VL_KENYAEMR", Facii, "Uploaded Successfully!", full_path, fileName,  Uploader, "EMaingi@usaidtujengejamii.org,DJuma@usaidtujengejamii.org"+em,usern);
      } catch (MessagingException ex) {
                  Logger.getLogger(UploadKenyaEMRVl.class.getName()).log(Level.SEVERE, null, ex);
              }
}
uploadedfiles.add(full_path);       
             

        
        }//end of worksheet
        
      
			   
        
        
        
        
        }
        
        session.setAttribute("emr_viral_load", "<b>Upload complete</b>");
        session.setAttribute("emr_viral_load_count", 100);
        //add data to dashboards
     
        session.removeAttribute("emr_viral_load");
        session.removeAttribute("emr_viral_load_count");
        
        // end of removing county attributes
        if(conn.rs!=null){
        conn.rs.close();
        }
        
        session.setAttribute("emr_vl_loaded", "Upload complete. <b style=\"color:green\">"+added+"</b> records were added and <b style=\"color:red\">"+updated+"</b> records were updated.");
       
        
        
        response.sendRedirect("uploademrvl.jsp");
        
      
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
          Logger.getLogger(UploadKenyaEMRVl.class.getName()).log(Level.SEVERE, null, ex);
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
          Logger.getLogger(UploadKenyaEMRVl.class.getName()).log(Level.SEVERE, null, ex);
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
