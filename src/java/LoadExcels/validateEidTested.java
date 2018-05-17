/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

 */

package LoadExcels;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;

@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)

/**
 *
 * @author Emmanuel Kaunda
 */


  public class validateEidTested extends HttpServlet {
   
  String county_name,county_id, district_name,district_id,hf_name,hf_id;
  
  String full_path="";
  String fileName="";
  int checker_dist,checker_hf;
  File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";
  String nextpage="";
  String quarterName,facilityName,facilityID,id,missingFacility;
          String mflcode;
  int year,quarter,checker,missing,added,updated;
  XSSFWorkbook wb;

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
     //#(0)	
     //System ID(1)	
     //Batch No(2)	
     //Sample Code(3)	
     //Testing Lab(4)	
     //County(5)	
     //Sub-County(6)
     //Facility Name(7)	
     //MFL Code(8)	
     //Partner(9)
     //Sex(10)
     //Age(Months)(11)	
     //Infant Prophylaxis(12)	
     //Date Collected(13)	
     //Spots(14)	
     //Received Status(15)	
     //Reason for Repeat / Rejection(16)	
     //HIV Status of Mother(17)	
     //PMTCT Intervention(18)	
     //Breast Feeding(19)	
     //Entry Point(20)	
     //Date Received(21)	
     //Date Tested(22)	
     //Date Dispatched(23)	
     //Test Result(24)
     
     
  id=""; 
  String order_0="";
  String batchno_2="";
  String samplecode_3="";
  String testinglab_4="";
  String sex_10="";
  String ageString_11="";
  String infant_prop_12="";
  String datecollected_13="";
  String spots_14="";
  String received_status_15="";
  String repeatreason_16="";
  String mumhiv_status_17="";
  String pmtct_intervation_18="";
  String breastfeeding_19="";
  String entrypoint_20="";
  String datereceived_21="";  
 String datetested_22="";
 String datedispatched_23="";
 String testresult_24="";
 String pcr_type="";
 
 //<tr><td>#(1)</td><td>System ID(2)</td><td>Batch No(3)</td><td>Sample Code(4)</td><td>Testing Lab(5)</td><td>County(6)</td><td>Sub-County(7)</td><td>Facility Name(8)</td><td>MFL Code(9)</td><td>Partner(10)</td><td>Sex(11)</td><td>Age(Months)(12)</td><td>Infant Prophylaxis(13)</td><td>Date Collected(14)</td><td>Spots(15)</td><td>Received Status(16)</td><td>Reason for Repeat / Rejection(17)</td><td>HIV Status of Mother(18)</td><td>PMTCT Intervention(19)</td><td>Breast Feeding(20)</td><td>Entry Point(21)</td><td>Date Received(22)</td><td>Date Tested(23)</td><td>Date Dispatched(24)</td><td>Test Result(25)</td></tr>
 
 //-----------------------------------
 //id	order	batchno	samplecode	testinglab	SubPartnerID	Mflcode	sex	age	infantprophylaxis	datecollected	spots	receivedstatus	repeat_rejection_reason	hivstatus_mum	pmtct_intervention	breastfeeding	entrypoint	datereceived	datetested	datedispatched	testresult	year	quarter

    String serialnumber="";
    
     String dbname="eid_raw_tested";
  
     
         try {
      session=request.getSession();
      dbConn conn = new dbConn();
   nextpage="Validateeidraw.jsp";
   
   
   
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
         nextpage="Validateeidraw.jsp";
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");   
        }
        else{
            
 full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
 
 System.out.println("the saved file directory is  :  "+full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
 
  FileInputStream fileInputStream = new FileInputStream(full_path);
			 wb = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator rowIterator = sheet.iterator();
                        
                        int i=1,y=0;
			while(rowIterator.hasNext()){
//                            System.out.println(" in while");
			XSSFRow rowi = sheet.getRow(i);
                        if( rowi==null){
                                
                         break;
                                        }
                        i++;
                        }

SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();

 ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule("COUNTIF($D$1:$D$"+i+",D1)>1");
 FontFormatting font = rule1.createFontFormatting();
 font.setFontStyle(false, true);
 font.setFontColorIndex(IndexedColors.WHITE.index);

 PatternFormatting fill2 = rule1.createPatternFormatting();
        fill2.setFillBackgroundColor(IndexedColors.RED.index);
        fill2.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
 

 CellRangeAddress[] regions = {CellRangeAddress.valueOf("D1:D"+i)};

 sheetCF.addConditionalFormatting(regions, rule1);
 
 
 //filter
CTAutoFilter sheetFilter=sheet.getCTWorksheet().getAutoFilter();
/* Step -2: Create Filter Column Object and Define Column ID */
 



//apply filter

/* Step-5: Refresh Records to Match Filter Condition */
XSSFRow r1;             
/* Step-6: Loop through Rows and Apply Filter */
for(Row r : sheet) {
        for (Cell c : r) {
                //if foreground filter color is not green then hide the record
                if ( c.getColumnIndex()==3  && c.getCellStyle().getFillBackgroundColor()!=IndexedColors.RED.index){
                    System.out.println(" Row number"+c.getRowIndex()+" = "+c.getCellStyle().getFillBackgroundColor()+" instead of "+IndexedColors.RED.index+"__");
                        r1=(XSSFRow) c.getRow();
                        
                        if (r1.getRowNum()!=0) { /* Ignore top row */
                        /* Hide Row that does not meet Filter Criteria */
                                r1.getCTRow().setHidden(true); }
                }                               
        }
}

        }
        
         if(conn.rs!=null){conn.rs.close();}
         if(conn.st!=null){conn.st.close();}
         if(conn.pst!=null){conn.pst.close();}
         
         
          IdGenerator IG = new IdGenerator();
            String createdOn = IG.CreatedOn();

          
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            wb.write(outByteStream);
            byte[] outArray = outByteStream.toByteArray();
            response.setContentType("application/ms-excel");
            response.setContentLength(outArray.length);
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setHeader("Content-Disposition", "attachment; filename=EID_REPORT_FOR__CREATED_" + createdOn.trim() + ".xlsx");
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
         
         }
         
         
         catch (SQLException ex) {
         Logger.getLogger(Load_tb_raw.class.getName()).log(Level.SEVERE, null, ex);
     }
    String sessionText="<br/><b> "+added+ "</b> New data added <br/> <b> "+updated+"</b> updated facilities<br> <br> <b>"+missing+"</b> sites not in Imis Facilities List ";    
    session.setAttribute("upload_success", sessionText);
   // response.sendRedirect(nextpage);  
 

 
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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//     try {
//         processRequest(request, response);
//     } catch (SQLException ex) {
//         Logger.getLogger(loadPMTCT_FO.class.getName()).log(Level.SEVERE, null, ex);
//     }
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//     try {
//         processRequest(request, response);
//     } catch (SQLException ex) {
//         Logger.getLogger(loadPMTCT_FO.class.getName()).log(Level.SEVERE, null, ex);
//     }
//    }

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
    
    public String getageBracket(int age)
    {
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
    
    
    
    
    
    
    
}
