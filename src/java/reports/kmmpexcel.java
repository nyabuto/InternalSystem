/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;


import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Geofrey Nyabuto
 */
public class kmmpexcel extends HttpServlet {

    HttpSession session=null;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    session=request.getSession();
    
    
    dbConn conn=new dbConn();
    //get the existing data for the month, year and facility that is already on session
    
    String month="";      
    String year="";      
    String facil="361";
  
    String form="kmmp";
      
   
    
 String  reportType="";
    if(request.getParameter("reportType")!=null){
      reportType=request.getParameter("reportType");
    }
    String  reportDuration="";
    if(request.getParameter("reportDuration")!=null){
    reportDuration=request.getParameter("reportDuration");
    }
  
    

//=====================================================================================================
    
    year="2015";
     month="5";
    String county="";
   
    String header="";
    
    String reporttype="";
    
    
    if(request.getParameter("year")!=null){
    year=request.getParameter("year");
    }
    
     if(request.getParameter("facil")!=null && reportType.equals("2")){
    facil=request.getParameter("facil");
    
    String getfacil="select SubPartnerNom,CentreSanteId as mflcode from subpartnera where SubPartnerID='"+facil+"'";
    conn.rs=conn.st.executeQuery(getfacil);
    
    while(conn.rs.next()){
    
    header+=" FACILITY : "+conn.rs.getString(1).toUpperCase()+"     MFL CODE  :  "+conn.rs.getString(2)+"  ";
    
    }
    
    
    
    }
    
    if(request.getParameter("county")!=null && reportType.equals("2")){
    county=request.getParameter("county");
    
    
    String getcounty="select County from county where CountyID='"+county+"'";
    conn.rs=conn.st.executeQuery(getcounty);
    
    while(conn.rs.next()){
    
    header+=" COUNTY : "+conn.rs.getString(1).toUpperCase()+" ";
    
    }
    
    }
    
    if(request.getParameter("month")!=null && reportDuration.equals("4")){
    month=request.getParameter("month");
    
    
      String getmonth="select name as monthname from month where id='"+month+"'";
    conn.rs=conn.st.executeQuery(getmonth);
    
    while(conn.rs.next()){
    
    header+=" MONTH : "+conn.rs.getString(1).toUpperCase()+" ";
    
    }
    
    
    }
    
     
    header+=" YEAR : "+year+"";
     
     
     
     
     
   String facilitywhere="";
   String yearwhere="";
   String monthwhere="";
   String countywhere="";
   String duration="";
   String semi_annual="";
   String quarter="";
 
   //==================================================================================================
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
  
       
       int  yearcopy=Integer.parseInt(year);
     
        
//        reportType="2";
//        year=2015;
//        reportDuration="3";
        String yearmonth=""+year;
       int prevYear=yearcopy-1; 
       int maxYearMonth=0;
       int monthcopy=0; 
//        GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
            yearmonth+="_AnnualReport";
         duration=" "+form+".yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
            yearmonth+="_Oct_Mar";
       duration=" "+form+".yearmonth BETWEEN "+prevYear+"10 AND "+year+"03";      
       }
           else{
            yearmonth+="_Apr_Sep";
       duration=" "+form+".yearmonth BETWEEN "+year+"04 AND "+year+"09";      
           }
       }
        
        else if(reportDuration.equals("3")){
            String startMonth,endMonth;
       quarter=request.getParameter("quarter");
//       quarter="3";
       String getMonths="SELECT months,name FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
           yearmonth+=year+"_"+conn.rs.getString(2);
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" "+form+".yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth; 

      }
      else{
     duration=" "+form+".yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
      }
                              }
        }  
        
      else if(reportDuration.equals("4")){
     monthcopy=Integer.parseInt(request.getParameter("month"));
     yearmonth+=year+"_("+month+")";
     
//     month=5;
     if(monthcopy>=10){
     duration=" "+form+".yearmonth="+prevYear+""+month;    
     }
     else{
  duration=" "+form+".yearmonth="+year+"0"+month;  
     }
      }
      else{
     duration="";     
      }
        




//==================================================================================================
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   String getexistingdata="";
   
   
    if(!county.equals("")){
   
   countywhere=" and countyid = '"+county+"'";    
   
   }
   
   if(!facil.equals("")){
   
   facilitywhere=" and kmmp.SubPartnerID = '"+facil+"'";    
   
   } 
   
    
    
 String joinedwhwere=" where 1=1 "+yearwhere+" && "+duration;  
    
    
    
//=====================================================================================================    
    
//______________________________________________________________________________________
  //                       NOW CREATE THE WORKSHEETS          
  //______________________________________________________________________________________  
            
              HSSFWorkbook wb=new HSSFWorkbook();
              
              
              
              
    //______________________________________________________________________________________
    //______________________________________________________________________________________
              
            HSSFFont font = wb.createFont();
            font.setFontHeightInPoints((short) 18);
            font.setFontName("Cambria");
            font.setColor((short) 0000);
            CellStyle style = wb.createCellStyle();
            style.setFont(font);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            HSSFFont font2 = wb.createFont();
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

            HSSFCellStyle stborder = wb.createCellStyle();
            stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCellStyle stylex = wb.createCellStyle();
            stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFFont fontx = wb.createFont();
            fontx.setColor(HSSFColor.BLACK.index);
            fontx.setFontName("Cambria");
            stylex.setFont(fontx);
            stylex.setWrapText(true);

   
   HSSFSheet shet=wb.createSheet(form);   
     
    //create headers for that worksheet
      
      HSSFRow rw=shet.createRow(0);
      rw.setHeightInPoints(25);
 HSSFCell cl0= rw.createCell(0);
 cl0.setCellValue("KMMP HEALTH FACILITY REPORTING FORM");
 cl0.setCellStyle(stylex);
    
 for(int a=1;a<4;a++){ 
 HSSFCell clx= rw.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                     }
 
 HSSFRow rw1=shet.createRow(1);
   rw1.setHeightInPoints(23);
  HSSFCell cl= rw1.createCell(0);
 cl.setCellValue(header);
 cl.setCellStyle(stylex);
 
  for(int a=1;a<4;a++){ 
 HSSFCell clx= rw1.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                     }
  
   HSSFRow rw2=shet.createRow(2);
     rw2.setHeightInPoints(23);
  HSSFCell cl3= rw2.createCell(0);
 cl3.setCellValue("KMMP OUTPUT DATA");
 cl3.setCellStyle(stylex);
 
  for(int a=1;a<4;a++){ 
 HSSFCell clx= rw2.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                     }
 
    shet.addMergedRegion(new CellRangeAddress(0,0,0,3));  
    shet.addMergedRegion(new CellRangeAddress(1,1,0,3));  
    shet.addMergedRegion(new CellRangeAddress(2,2,0,3));  
    shet.addMergedRegion(new CellRangeAddress(3,3,1,2));  
    shet.addMergedRegion(new CellRangeAddress(4,4,1,2));  
    shet.addMergedRegion(new CellRangeAddress(5,5,1,2));  
    shet.addMergedRegion(new CellRangeAddress(5,7,0,0));  
    shet.addMergedRegion(new CellRangeAddress(6,6,1,2));  
    shet.addMergedRegion(new CellRangeAddress(7,7,1,2));  
    shet.addMergedRegion(new CellRangeAddress(8,8,1,2));  
    shet.addMergedRegion(new CellRangeAddress(9,11,0,0)); 
    shet.addMergedRegion(new CellRangeAddress(9,11,1,1)); 
    shet.addMergedRegion(new CellRangeAddress(12,12,1,2)); 
    shet.addMergedRegion(new CellRangeAddress(13,13,1,2)); 
    shet.setColumnWidth(0, 2000);  
    shet.setColumnWidth(1, 17000);  
    shet.setColumnWidth(2, 5000);  
    

     
    
    
        
getexistingdata="select  sum(KMMP1) as KMMP1,   sum(KMMP2) as KMMP2,  sum(KMMP3a) as KMMP3a,   sum(KMMP3b) as KMMP3b,   avg(KMMP3c) as KMMP3c ,   sum(KMMP4) as KMMP4 ,   sum(KMMP5a) as KMMP5a,    sum(KMMP5b) as KMMP5b,    sum(KMMP5c) as KMMP5c,    sum(HV0205) as HV0205,    sum(HV0206) as HV0206 from kmmp join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on kmmp.SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+"  ";

            System.out.println(getexistingdata);
String formtype="<b><font color='#4b8df8'>New Entry</font></b>";
String KMMP1="";
String KMMP2="";
String KMMP3a="";
String KMMP3b="";
String KMMP3c="";
String KMMP4="";
String KMMP5a="";
String KMMP5b="";
String KMMP5c="";
String HV0205="";
String HV0206="";


String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


int counter=0;
 
 
 
 
 
    
    conn.rs=conn.st.executeQuery(getexistingdata);
    while(conn.rs.next()){
   //now check if form was updated and if its one month after data entry
//now load the column values here
       
KMMP1=conn.rs.getString("KMMP1");
if(KMMP1==null){KMMP1=""; }

KMMP2=conn.rs.getString("KMMP2");
if(KMMP2==null){KMMP2=""; }

KMMP3a=conn.rs.getString("KMMP3a");
if(KMMP3a==null){KMMP3a=""; }

KMMP3b=conn.rs.getString("KMMP3b");
if(KMMP3b==null){KMMP3b=""; }

KMMP3c=conn.rs.getString("KMMP3c");
if(KMMP3c==null){KMMP3c=""; }

KMMP4=conn.rs.getString("KMMP4");
if(KMMP4==null){KMMP4=""; }

KMMP5a=conn.rs.getString("KMMP5a");
if(KMMP5a==null){KMMP5a=""; }

KMMP5b=conn.rs.getString("KMMP5b");
if(KMMP5b==null){KMMP5b=""; }

KMMP5c=conn.rs.getString("KMMP5c");
if(KMMP5c==null){KMMP5c=""; }

HV0205=conn.rs.getString("HV0205");
if(HV0205==null){HV0205=""; }

HV0206=conn.rs.getString("HV0206");
if(HV0206==null){HV0206=""; }
        
    }
    
    String createdtable="";
    
    
if(1==1){ 
    
    if(1==1){
  HSSFRow rw3=shet.createRow(3);
    rw3.setHeightInPoints(23);
  HSSFCell cl4= rw3.createCell(0);
  cl4.setCellValue("1");
  cl4.setCellStyle(style2);
  
   HSSFCell cl41= rw3.createCell(1);
  cl41.setCellValue("No of New HIV positive clients enrolled in KMMP Services (ANC and PN) ");
  cl41.setCellStyle(style2);
   
  HSSFCell cl42= rw3.createCell(2);
  cl42.setCellValue("");
  cl42.setCellStyle(style2); 
  
  HSSFCell cl43= rw3.createCell(3);
  cl43.setCellValue(KMMP1);
  cl43.setCellStyle(style2);  
}
 //================================================================================================  
 
  if(1==1){
  HSSFRow rw3=shet.createRow(4);
    rw3.setHeightInPoints(23);
  HSSFCell cl4= rw3.createCell(0);
  cl4.setCellValue("2");
  cl4.setCellStyle(style2);
  
   HSSFCell cl41= rw3.createCell(1);
  cl41.setCellValue("No of New HIV negative clients enrolled in KMMP Services (ANC Only) ");
  cl41.setCellStyle(style2);
   
  HSSFCell cl42= rw3.createCell(2);
  cl42.setCellValue("");
  cl42.setCellStyle(style2); 
  
  HSSFCell cl43= rw3.createCell(3);
  cl43.setCellValue(KMMP2);
  cl43.setCellStyle(style2);  
  }
 //================================================================================================
  
  
  
   if(1==1){
  HSSFRow rw3=shet.createRow(5);
    rw3.setHeightInPoints(23);
  HSSFCell cl4= rw3.createCell(0);
  cl4.setCellValue("3");
  cl4.setCellStyle(style2);
  
   HSSFCell cl41= rw3.createCell(1);
  cl41.setCellValue("a) No. of HIV-positive pregnant women enrolled in KMMP Services");
  cl41.setCellStyle(style2);
   
  HSSFCell cl42= rw3.createCell(2);
  cl42.setCellValue("");
  cl42.setCellStyle(style2); 
  
  HSSFCell cl43= rw3.createCell(3);
  cl43.setCellValue(KMMP3a);
  cl43.setCellStyle(style2);  
  }
  //================================================================================================
  
  
  
   if(1==1){
  HSSFRow rw3=shet.createRow(6);
  rw3.setHeightInPoints(23);
  
   HSSFCell cl41= rw3.createCell(1);
  cl41.setCellValue(" b) Total number of HIV-positive pregnant women in facility (New positive \n and Known Positive-MOH731)");
  cl41.setCellStyle(style2);
   
  HSSFCell cl42= rw3.createCell(2);
  cl42.setCellValue("");
  cl42.setCellStyle(style2); 
  
  HSSFCell cl43= rw3.createCell(3);
  cl43.setCellValue(KMMP3b);
  cl43.setCellStyle(style2);  
  }
 //================================================================================================
  
   
   
  
  
   if(1==1){
  HSSFRow rw3=shet.createRow(7);
  rw3.setHeightInPoints(23);
  
   HSSFCell cl41= rw3.createCell(1);
  cl41.setCellValue(" Percentage of new IV-positive pregnant women enrolled in KMMP Services");
  cl41.setCellStyle(style2);
   
  HSSFCell cl42= rw3.createCell(2);
  cl42.setCellValue("");
  cl42.setCellStyle(style2); 
  
  HSSFCell cl43= rw3.createCell(3);
  cl43.setCellValue(KMMP3c.substring(0, 2)+"%");
  cl43.setCellStyle(style2);  
  }
 //================================================================================================
  
  
   if(1==1){
  HSSFRow rw3=shet.createRow(8);
    rw3.setHeightInPoints(23);
  HSSFCell cl4= rw3.createCell(0);
  cl4.setCellValue("4");
  cl4.setCellStyle(style2);
  
   HSSFCell cl41= rw3.createCell(1);
  cl41.setCellValue("No. of KMMP support group sessions held");
  cl41.setCellStyle(style2);
   
  HSSFCell cl42= rw3.createCell(2);
  cl42.setCellValue("");
  cl42.setCellStyle(style2); 
  
  HSSFCell cl43= rw3.createCell(3);
  cl43.setCellValue(KMMP4);
  cl43.setCellStyle(style2);  
  }
  //================================================================================================
  
   
   if(1==1){
  HSSFRow rw3=shet.createRow(9);
    rw3.setHeightInPoints(23);
  HSSFCell cl4= rw3.createCell(0);
  cl4.setCellValue("");
  cl4.setCellStyle(style2);
  
   HSSFCell cl41= rw3.createCell(1);
  cl41.setCellValue("Defaulter tracing");
  cl41.setCellStyle(style2);
   
  HSSFCell cl42= rw3.createCell(2);
  cl42.setCellValue("New Defaulted Clients");
  cl42.setCellStyle(style2); 
  
  HSSFCell cl43= rw3.createCell(3);
  cl43.setCellValue(KMMP5a);
  cl43.setCellStyle(style2);  
  }
  //================================================================================================
  
   
   
   if(1==1){
  HSSFRow rw3=shet.createRow(10);
   rw3.setHeightInPoints(23);
  HSSFCell cl4= rw3.createCell(1);
  cl4.setCellValue("");
  cl4.setCellStyle(style2); 
  HSSFCell cl42= rw3.createCell(2);
  cl42.setCellValue("Clients Reached");
  cl42.setCellStyle(style2); 
  
  HSSFCell cl43= rw3.createCell(3);
  cl43.setCellValue(KMMP5b);
  cl43.setCellStyle(style2);  
  }
  //================================================================================================
      if(1==1){
  HSSFRow rw3=shet.createRow(11);
    rw3.setHeightInPoints(23);
  HSSFCell cl4= rw3.createCell(1);
  cl4.setCellValue("");
  cl4.setCellStyle(style2);
   
  HSSFCell cl42= rw3.createCell(2);
  cl42.setCellValue("Successfully resolved");
  cl42.setCellStyle(style2); 
  
  HSSFCell cl43= rw3.createCell(3);
  cl43.setCellValue(KMMP5c);
  cl43.setCellStyle(style2);  
  }
      
      
  //================================================================================================
      
      if(1==1){
  HSSFRow rw3=shet.createRow(12);
    rw3.setHeightInPoints(23);
  HSSFCell cl4= rw3.createCell(0);
  cl4.setCellValue("");
  cl4.setCellStyle(style2);
  
   HSSFCell cl41= rw3.createCell(1);
  cl41.setCellValue("MOH 731 HV02-05 Known positive status (at entry into ANC) ");
  cl41.setCellStyle(style2);
   
  HSSFCell cl42= rw3.createCell(2);
  cl42.setCellValue("");
  cl42.setCellStyle(style2); 
  
  HSSFCell cl43= rw3.createCell(3);
  cl43.setCellValue(HV0205);
  cl43.setCellStyle(style2);  
}
 //================================================================================================  
 
 if(1==1){
  HSSFRow rw3=shet.createRow(13);
   rw3.setHeightInPoints(23);
  HSSFCell cl4= rw3.createCell(0);
  cl4.setCellValue("");
  cl4.setCellStyle(style2);
  
   HSSFCell cl41= rw3.createCell(1);
  cl41.setCellValue("MOH 731 HV02-06 Antenatal");
  cl41.setCellStyle(style2);
   
  HSSFCell cl42= rw3.createCell(2);
  cl42.setCellValue("");
  cl42.setCellStyle(style2); 
  
  HSSFCell cl43= rw3.createCell(3);
  cl43.setCellValue(HV0206);
  cl43.setCellStyle(style2);  
}

 
 
 //================================================================================================     
    createdtable+=header+"<br/><br/><br/><table class='mytable'   border=\"1\" style=\"font-family:cambria; border-color: #e5e5e5;margin-bottom: 3px; width:500px;\"><tr class='form-actions'><th colspan='3'><b style=\"text-align:center;\"> KMMP OUTPUT DATA</b></th><th>Total</th></tr><tr><td><b> 1 </b></td><td colspan='2'>No of New HIV positive clients enrolled in KMMP Services (ANC and PN) </td><td>"+KMMP1+"</td></tr>";
    
      createdtable+="<tr><td>Successfully Resolved</td><td>"+KMMP5c+"</td></tr>";
   
      createdtable+="<tr><td></td><td colspan='2'>MOH 731 HV02-05 Known positive status (at entry into ANC) :</td><td>"+HV0205+"</td></tr>";
   
      createdtable+="<tr><td></td><td colspan='2'>MOH 731 HV02-06 Antenatal:</td><td>"+HV0206+"</td></tr></table> <div class='form-actions'></div>";
  
    
    }
    
    
     
      //System.out.println(createdtable);
      
   
        
        if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}
         if(conn.rs2!=null){ conn.rs2.close();}
         if(conn.st!=null){ conn.st.close();}
         if(conn.st2!=null){ conn.st2.close();}
        
     
    
    //#############################################################################################################
         
         
     



ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte[] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=kmmp"+yearmonth+".xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
outStream.close();

         
         
    //#############################################################################################################
    
    
    
    
    
}       catch (SQLException ex) {
            Logger.getLogger(kmmppdf.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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

}
