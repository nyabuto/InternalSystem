/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMapping;

import General.IdGenerator;
import database.OSValidator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import scripts.copytemplates;

/**
 *
 * @author GNyabuto
 */
public class DHIS_IMIS_Variance_Output extends HttpServlet {
HttpSession session;
String pathtodelete=null;
 int no_months = 0;  
 String[] columns_labels =  {"County","Sub County","Ward","Health Facility","MFL Code","Year","Month","Reporting Period","IST ANC Clients","First Testing HIV","Repeat Testing HIV","Total Tested HIV","Couples Testing","Static Testing HIV (Health Facility)","Outreach Testing HIV","Concordant Couples Receiving Results (Couples Only)","Discordant Couples Receiving Results (Couples Only)","Male under 15yrs Receiving HIV + Results","Female under 15yrs Receiving HIV + Results","Male 15-24yrs Receiving HIV + Results","Female 15-24yrs Receiving HIV + Results","Male above 25yrs Receiving HIV + Results","Female above 25yrs Receiving HIV + Results","Total Receiving Positive Results HV01-16","Antenatal Testing for HIV","Labour and Delivery (Infant ARV prophylaxis)","Postnatal (within 72hrs) Testing for HIV","Total Tested (PMTCT)","Known positive status (at entry into ANC)","Antenatal Positive to HIV Test","Labour and Delivery Postive to HIV Test","Postnatal (within 72hrs) Postive to HIV Test","Total Positive (PMTCT)","Male partners tested -( ANC/L&D)","Discordant Couples Partner Involvement","Prophylaxis-NVP Only","Prophylaxis - (AZT+SdNVP)","Prophylaxis - interrupted HAART","Prophylaxis â€“ HAART","Total PMTCT prophylaxis","Assessed Eligibility in ANC","Assessed for eligibility in 1st ANC - CD4","Assessed for eligibility in 1st ANC - WHO Staging done","Started on ART during ANC","PCR (within 2 months) Infant Testing (Initial test only)","PCR (from3 to 8 months) Infant Testing (Initial test only)","Serology (from 9 to 12 months) Infant Testing (Initial test only)","PCR (from 9 to 12 months) Infant Testing (Initial test only)","Total HEI tested by 12 months","PCR (by 2 months) Confirmed Infant Test Results Positive","PCR (3 to 8 months) Confirmed Infant Test Results Positive","PCR (9 to 12 months) Confirmed Infant Test Results Positive","Total Confirmed Positive Infant test result by PCR","EBF (6 months) Infant Feeding","ERF (6 months) Infant Feeding","MF (6 months) Infant Feeding","Total Exposed aged 6 months","BF (at 12 months) Infant Feeding","Not BF (12 months) Infant Feeding","Not Known Infant Feeding (12 months)","Total Exposed 12 months","Issued in ANC (Infant ARV prophylaxis)","Labour and Delivery Testing for HIV","PNC (<72hrs) (Infant ARV prophylaxis)","Total Infants Issued Prophylaxis","HIV Exposed Infant (within 2 months) on Cotrimoxazole Prophylaxis","HIV Exposed Infant (Eligible for CTX 2 months)","Total on CTX","Total Enrolled in Care","HIV Currently in Care - Total","Under 1yr Starting on ART","Male under 15yrs Starting on ART","Female under 15yrs Starting on ART","Male above 15yrs Starting on ART","Female above 15yrs Starting on ART","Total Starting on ART","Pregnant women Starting on ART","TB Patient Starting on ART","Total Revisit on ART","Currently on ART - below 1 year","Currently on ART - Male below 15 years","Currently on ART - Female Below 15 years","Currently on ART - Male above 15 years","Currently on ART - Female above 15 years","Total currently on ART","Total Ever on ART","ART Net Cohort at 12 months Survival and Retention on ART","On Original 1st Line at 12 months Survival and Retention on ART","On alternative 1st Line at 12 months Survival and Retention on ART","On 2nd Line (or higher) at 12 months Survival and Retention on ART","Total on therapy at 12 months","Total Screened for TB","Screened for cervical cancer (females 18 years and older)","HIV Care visits Females (18 years and older)","HIV Care visit Scheduled","HIV Care visit- unscheduled","Total HIV Care visit","Modern contraceptive methods","Provided with condoms","Total HTC Variances","Total PMTCT Variances","Total ART Variances","Upload Counter","Timestamp When Uploaded"};
String yearmonth,where;
String yr; 
int pepfaryear;
ArrayList yms = new ArrayList();
 int row=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
        response.setContentType("text/html;charset=UTF-8");
        try {
        session = request.getSession();
       dbConn conn = new dbConn();
         
    yms.clear();
            
    yr = request.getParameter("year");
    String[] months = request.getParameterValues("month");
    
    int year = Integer.parseInt(yr);
     
    where="";
    
    
    for(String mn:months){
    int month = Integer.parseInt(mn);
    
    if(month>=10){
        pepfaryear = year-1;
    }
    else{
        pepfaryear = year;  
        mn = "0"+month;
    }
      yearmonth = pepfaryear+""+mn;
   where+="yearmonth ='"+yearmonth+"' OR ";
    }
    
    where = removeLastChars(where, 3);
    
    row=0;
    
             XSSFWorkbook wb; 
             String allpath = getServletContext().getRealPath("/IMIS_DHIS_Variances_Template2.xlsm");
             String mydrive = allpath.substring(0, 1);
              Date da= new Date();
            String dat2 = da.toString().replace(" ", "_");
            dat2 = dat2.toString().replace(":", "_");
            
            String np="";
            if(OSValidator.isWindows()){
            np=mydrive+":\\HSDSA\\InternalSystem\\IMISDHISVariances"+dat2+".xlsm";
            }
            else if(OSValidator.isUnix()){
             np="/HSDSA/InternalSystem/IMISDHISVariances"+dat2+".xlsm";    
            }
            String sr = getServletContext().getRealPath("/IMIS_DHIS_Variances_Template2.xlsm"); 
                
            File f = new File(np);
            if(!f.exists()&& !f.isDirectory() ) { /* do something */
            copytemplates ct= new copytemplates();
                ct.transfermacros(sr,np); 
            }
            
            else 
  //copy the file alone  
        {
        copytemplates ct= new copytemplates();
        //copy the agebased file only
        ct.copymacros(sr,np);

        }
        String filepth=np;      

          File allpathfile= new File(filepth);

              OPCPackage pkg = OPCPackage.open(allpathfile);

           pathtodelete=filepth;
            wb = new XSSFWorkbook(pkg);

            
             
             
             Font font = wb.createFont();
            font.setFontHeightInPoints((short) 18);
            font.setFontName("Cambria");
            font.setColor((short) 0000);
            CellStyle style = wb.createCellStyle();
            style.setFont(font);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
           Font font2 = wb.createFont();
            font2.setFontName("Cambria");
            font2.setColor((short) 0000);
            CellStyle style2 = wb.createCellStyle();
            style2.setFont(font2);
            style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

            CellStyle stborder = wb.createCellStyle();
            stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            CellStyle stylex = wb.createCellStyle();
            stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            
            
            CellStyle stylex1 = wb.createCellStyle();
            stylex1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            stylex1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            stylex1.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stylex1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stylex1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stylex1.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stylex1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            
            
            CellStyle stylex2 = wb.createCellStyle();
            stylex2.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            stylex2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            stylex2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stylex2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stylex2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stylex2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stylex2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            
            CellStyle stylex3 = wb.createCellStyle();
            stylex3.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            stylex3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            stylex3.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stylex3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stylex3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stylex3.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stylex3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            
            Font fontx = wb.createFont();
            fontx.setColor(HSSFColor.BLACK.index);
            fontx.setFontName("Cambria");
            stylex.setFont(fontx);
            stylex.setWrapText(true);
            stylex1.setFont(fontx);
            stylex1.setWrapText(true);
            
            stylex2.setFont(fontx);
            stylex2.setWrapText(true);
   
            //==================================================
            
            
    Sheet shet= wb.getSheet("RawData");
    int col_no=0;
    Row rwh=shet.createRow(row);            
    rwh.setHeightInPoints(34);
      
    for(String column_name:columns_labels){
        shet.setColumnWidth(col_no, 8000);
         
      Cell c1x= rwh.createCell(col_no);
      c1x.setCellValue(column_name);
      c1x.setCellStyle(stylex);
      
               col_no++;
    }
    
    String value="";
    row++;
       String get_data = "SELECT county,sub_county,ward,facility,mfl_code,year,month,yearmonth,new_anc,hv0101,hv0102,hv0103,hv0105,hv0106,hv0107,hv0108,hv0109,hv0110,hv0111,hv0112,hv0113,hv0114,hv0115,hv0116,hv0201,hv0202,hv0203,hv0204,hv0205,hv0206,hv0207,hv0208,hv0209,hv0211,hv0212,hv0213,hv0214,hv0215,hv0216,hv0217,hv0218,hv0219,hv0220,hv0221,hv0224,hv0225,hv0226,hv0227,hv0228,hv0229,hv0230,hv0231,hv0232,hv0233,hv0234,hv0235,hv0236,hv0237,hv0238,hv0239,hv0240,hv0241,hv0242,hv0243,hv0244,hv0301,hv0302,hv0307,hv0313,hv0319,hv0320,hv0321,hv0322,hv0323,hv0324,hv0325,hv0326,hv0327,hv0333,hv0334,hv0335,hv0336,hv0337,hv0338,hv0339,hv0344,hv0345,hv0346,hv0347,hv0348,hv0349,hv0354,hv0355,hv0370,hv0371,hv0372,hv0373,hv0904,hv0905,htc,pmtct,art,upload_no,timestamp FROM dhis_731_variances WHERE ("+where+") AND mfl_code IS NOT NULL";
       conn.rs = conn.st.executeQuery(get_data);
       while(conn.rs.next()){
        int col_pos=1;
        Row rw_data=shet.createRow(row);            
        rw_data.setHeightInPoints(24);
           while(col_pos<=columns_labels.length){
               value = conn.rs.getString(col_pos);
               if(columns_labels[col_pos-1].equals("Reporting Period")){
                value = getperiod(Integer.parseInt(value));
               }
               
                Cell c1x= rw_data.createCell((col_pos-1));
                if(isNumeric(value)){
                c1x.setCellValue(Integer.parseInt(value));
                }
                else{
                c1x.setCellValue(value);    
                }
                c1x.setCellStyle(stborder); 
               col_pos++;
           }
           
           row++;
    }
      
    IdGenerator IG = new IdGenerator();
    String createdOn=IG.CreatedOn();

    
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte[] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=IMIS_DHIS_Variance_Comparison_Report"+createdOn.trim()+".xlsm");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
outStream.close(); 
   pkg.close();

   if(conn.rs!=null){conn.rs.close();}
   if(conn.rs1!=null){conn.rs1.close();}
   if(conn.st1!=null){conn.st1.close();}
   if(conn.st!=null){conn.st.close();}
       
         File file= new File(pathtodelete);
            System.out.println("path: 2"+pathtodelete);
           
        if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation  failed.");
    		}
        } 
        catch (SQLException ex) { 
            Logger.getLogger(DHIS_IMIS_Variance_Output.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(DHIS_IMIS_Variance_Output.class.getName()).log(Level.SEVERE, null, ex);
        }        finally 
        {
           
        }
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
        Logger.getLogger(DHIS_IMIS_Variance_Output.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(DHIS_IMIS_Variance_Output.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(DHIS_IMIS_Variance_Output.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(DHIS_IMIS_Variance_Output.class.getName()).log(Level.SEVERE, null, ex);
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

    
private static String removeLastChars(String str, int num) {
    return str.substring(0, str.length() - num);
}

   public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
    public String getperiod(int ym){
    String period="";  
    String[] montharray = String.valueOf(ym).split("");
    int year=Integer.parseInt(montharray[0]+""+montharray[1]+""+montharray[2]+""+montharray[3]);
    int month=Integer.parseInt(montharray[4]+""+montharray[5]);

    String mn_name="";
    switch (month){
        case 1: mn_name="Jan";
        break;
        case 2: mn_name="Feb"; 
        break;
        case 3: mn_name="Mar";  
        break;
        case 4: mn_name="Apr"; 
        break;
        case 5: mn_name="May"; 
        break;
        case 6: mn_name="Jun"; 
        break;
        case 7: mn_name="Jul";
        break;
        case 8: mn_name="Aug"; 
        break;
        case 9: mn_name="Sep";  
        break;
        case 10: mn_name="Oct";  
        break;
        case 11: mn_name="Nov"; 
        break;
        case 12: mn_name="Dec"; 
        break;
        default:
            mn_name="No Month";
    }
   
    period = mn_name+"' "+year;
return period;
}   
   
}
