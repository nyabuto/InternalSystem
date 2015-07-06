/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
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
public class staticReportExcel731 extends HttpServlet {
HttpSession session;
String data,id;
String facilityId;
String county,district,facilityname,mflcode;       
  String isValidated,validity;
int maxYearMonth;
String subcountyid,facility;
String reportType,duration,reportDuration,quarter,semi_annual;
int year,prevYear,month;
String header,facilityName,countyName,districtName,monthName;
String createdOn,elementName;
int counter,counterPMTCT,counterART,counterPEP,counterPMTCT1,counterART1,counterPEP1;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        dbConn conn = new dbConn();
            session=request.getSession();
        
//        reportType=request.getParameter("reportType");
//        year=Integer.parseInt(request.getParameter("year"));
//        reportDuration=request.getParameter("reportDuration");
        
        reportType="2";
        year=2015;
        reportDuration="4";
        
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================
        
        if(reportDuration.equals("1")){
         duration=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
       duration=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03";      
       }
           else{
       duration=" moh731.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
           }
       }
        
        else if(reportDuration.equals("3")){
            String startMonth,endMonth;
       quarter=request.getParameter("quarter");
//       quarter="3";
       String getMonths="SELECT months FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" moh731.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
      }
      else{
     duration=" moh731.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
      }
        }
        }  
        
      else if(reportDuration.equals("4")){
//     month=Integer.parseInt(request.getParameter("month"));
     month=4;
     if(month>=10){
     duration=" moh731.yearmonth="+prevYear+""+month;    
     }
     else{
  duration=" moh731.yearmonth="+year+"0"+month;  
     }
      }
      else{
     duration="";     
      }
           
     
//     GET FACILITIES TO OUTPUT.................................
    mflcode=countyName=districtName=facilityName="";    
        
      if(reportType.equals("1")){  
    facility="";  
    }
      
      else{
//  facilityId=request.getParameter("facility");
  facilityId="403";
  facility="moh731.SubPartnerID='"+facilityId+"' &&";    
  
  String getName="SELECT subpartnera.SubPartnerNom,district.DistrictNom,county.County,subpartnera.CentreSanteId FROM subpartnera "
          + "JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID WHERE subpartnera.SubPartnerID='"+facilityId+"'";
  conn.rs=conn.st.executeQuery(getName);
  if(conn.rs.next()==true){
      facilityName=conn.rs.getString(1);
      districtName=conn.rs.getString(2);
      countyName=conn.rs.getString(3);
      mflcode=conn.rs.getString(4);
  }
     }
     
    header+="</table>";  
      
    String getMaxYearMonth="SELECT MAX(yearmonth) FROM moh731 WHERE "+facility+" "+duration ;
    conn.rs=conn.st.executeQuery(getMaxYearMonth);
    if(conn.rs.next()==true){
        maxYearMonth=conn.rs.getInt(1);
    }
   System.out.println("max year month : "+maxYearMonth);
        
   
    //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
   XSSFWorkbook wb=new XSSFWorkbook();
  XSSFSheet shet1=wb.createSheet("PMTCT");
  XSSFSheet shet2=wb.createSheet("Care and Treatment");
  XSSFSheet shet3=wb.createSheet("PEP");
  XSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)18);
    font.setFontName("Arial Black");
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     XSSFFont font2=wb.createFont();
    font2.setFontName("Arial Black");
    font2.setColor((short)0000);
    CellStyle style2=wb.createCellStyle();
    style2.setFont(font2);
   
   XSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
    XSSFCellStyle stylex = wb.createCellStyle();
stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
XSSFFont fontx = wb.createFont();
fontx.setColor(HSSFColor.DARK_BLUE.index);
stylex.setFont(fontx);
stylex.setWrapText(true);

  
    
    for (int i=0;i<=3;i++){
   shet1.setColumnWidth(i, 6000);     
    }
    
    for (int i=4;i<=47;i++){
   shet1.setColumnWidth(i, 4000);     
    }
    
  for (int i=0;i<=3;i++){
   shet2.setColumnWidth(i, 6000);     
    }
    
    for (int i=4;i<=66;i++){
   shet2.setColumnWidth(i, 3000);     
    }
    
 for (int i=0;i<=3;i++){
   shet3.setColumnWidth(i, 6000);     
    }
    
    for (int i=4;i<=19;i++){
   shet3.setColumnWidth(i, 3000);     
    }
    
  String  headers="COUNTY,SUB COUNTY,FACILITY NAME,MFL CODE";
   
    String arrayHeader []=headers.split(",");
    int headerno=0;
   
//   XSSFRow rw0S1=shet1.createRow(0);
   XSSFRow rw1S1=shet1.createRow(0);  
   
//    XSSFRow rw0S2=shet2.createRow(0);
   XSSFRow rw1S2=shet2.createRow(0);
    
//    XSSFRow rw0S3=shet3.createRow(0);
   XSSFRow rw1S3=shet3.createRow(0);

    
//    LOOP THROUGH AND WRITE STATIC HEADERS TO THE ELEMENTS
     for(String headername:arrayHeader){
    
    XSSFCell  S1cell=rw1S1.createCell(headerno);
    S1cell.setCellValue(headername);
    S1cell.setCellStyle(stylex);
   
    XSSFCell  S2cell=rw1S2.createCell(headerno);
    S2cell.setCellValue(headername);
    S2cell.setCellStyle(stylex);
    
    XSSFCell  S3cell=rw1S3.createCell(headerno);
    S3cell.setCellValue(headername);
    S3cell.setCellStyle(stylex);
      headerno++;
     }
  
    String getMonth="SELECT name FROM month WHERE id='"+month+"'";
    conn.rs=conn.st.executeQuery(getMonth);
    if(conn.rs.next()==true){
        monthName=conn.rs.getString(1);
    }
     
     
   
  counterPMTCT=counterART=counterPEP=3; 
          
    String getVariables="SELECT * FROM moh731_indicators ORDER BY id"; 
    conn.rs1=conn.st1.executeQuery(getVariables);
    while(conn.rs1.next()){
        elementName=conn.rs1.getString("indicator")+" \n"+conn.rs1.getString("code");
        
        if(conn.rs1.getString("section").equals("2. Prevention of Mother-to-Child Transmission")){
     counterPMTCT++;
   XSSFCell  S1cell=rw1S1.createCell(counterPMTCT);
    S1cell.setCellValue(elementName);
    S1cell.setCellStyle(stylex);
  }
        else if (conn.rs1.getString("section").equals("3. Care and Treatment")){    
     counterART++;
     XSSFCell  S2cell=rw1S2.createCell(counterART);
    S2cell.setCellValue(elementName); 
    S2cell.setCellStyle(stylex); 
        }
        
         else if (conn.rs1.getString("section").equals("5. Post-Exposure Prophylaxis")){    
      counterPEP++; 
      XSSFCell  S3cell=rw1S3.createCell(counterPEP);
    S3cell.setCellValue(elementName);
    S3cell.setCellStyle(stylex);
     
        }
         else{} 
    
    }
      
//   START OUTPUTTING THE RESULTS=================================================   
 

     System.out.println("facility : "+facility+"   duration : "+duration);
 
          String checker="SELECT county.County,district.DistrictNom,subpartnera.SubPartnerNom,subpartnera.CentreSanteId,"
+ "SUM(HV0101),SUM(HV0102),SUM(HV0103),SUM(HV0105),SUM(HV0106),SUM(HV0107),SUM(HV0108),SUM(HV0109),SUM(HV0110),SUM(HV0111),SUM(HV0112),SUM(HV0113),SUM(HV0114)," +
"SUM(HV0115),SUM(HV0116)," +
"SUM(HV0201),SUM(HV0202),SUM(HV0203),SUM(HV0204),SUM(HV0205),SUM(HV0206),SUM(HV0207),SUM(HV0208),SUM(HV0209),SUM(HV0210),SUM(HV0211),SUM(HV0212),SUM(HV0213)," +
"SUM(HV0214),SUM(HV0215),SUM(HV0216),SUM(HV0217),SUM(HV0218),SUM(HV0219),SUM(HV0220),SUM(HV0221),SUM(HV0224),SUM(HV0225),SUM(HV0226),SUM(HV0227),SUM(HV0228),SUM(HV0229)," +
"SUM(HV0230),SUM(HV0231),SUM(HV0232),SUM(HV0233),SUM(HV0234),SUM(HV0235),SUM(HV0236),SUM(HV0237),SUM(HV0238),SUM(HV0239),SUM(HV0240),SUM(HV0241),SUM(HV0242)," +
"SUM(HV0243),SUM(HV0244)," +
"SUM(HV0301),SUM(HV0302),SUM(HV0303),SUM(HV0304),SUM(HV0305),SUM(HV0306),SUM(HV0307),SUM(HV0308),SUM(HV0309),SUM(HV0310),SUM(HV0311),SUM(HV0312),SUM(HV0313),SUM(HV0314)," +
"SUM(HV0315),SUM(HV0316),SUM(HV0317),SUM(HV0318),SUM(HV0319),SUM(HV0320),SUM(HV0321),SUM(HV0322),SUM(HV0323),SUM(HV0324),SUM(HV0325),SUM(HV0326),SUM(HV0327),SUM(HV0328)," +
"SUM(HV0329),SUM(HV0330),SUM(HV0331),SUM(HV0332),SUM(HV0333),SUM(HV0334),SUM(HV0335),SUM(HV0336),SUM(HV0337),SUM(HV0338),SUM(HV0339),SUM(HV0340),SUM(HV0341)," +
"SUM(HV0342),SUM(HV0343),SUM(HV0344),SUM(HV0345),SUM(HV0346),SUM(HV0347),SUM(HV0348),SUM(HV0349),SUM(HV0350),SUM(HV0351),SUM(HV0352),SUM(HV0353)," +
"SUM(HV0354),SUM(HV0355),SUM(HV0904),SUM(HV0905),SUM(HV0370),SUM(HV0371),SUM(HV0372),SUM(HV0373)," +
"SUM(HV0401),SUM(HV0402),SUM(HV0403),SUM(HV0406),SUM(HV0407),SUM(HV0408),SUM(HV0409),SUM(HV0410),SUM(HV0411),SUM(HV0412),SUM(HV0413),SUM(HV0414),SUM(HV0415)," +
"SUM(HV0501),SUM(HV0502),SUM(HV0503),SUM(HV0504),SUM(HV0505),SUM(HV0506),SUM(HV0507),SUM(HV0508),SUM(HV0509),SUM(HV0510),SUM(HV0511),SUM(HV0512),SUM(HV0513),SUM(HV0514)," +
"SUM(HV0601),SUM(HV0602),SUM(HV0605),subpartnera.PMTCT,subpartnera.ART,subpartnera.PEP "
 +"FROM moh731 JOIN subpartnera ON moh731.SubPartnerID=subpartnera.SubPartnerID "
+ "JOIN district ON subpartnera.DistrictID=district.DistrictID "
+ "JOIN county ON county.CountyID=district.CountyID "
+ " WHERE "+facility+" "+duration+" GROUP BY subpartnera.SubPartnerID "
+ " ORDER BY county.County,district.DistrictNom,subpartnera.SubPartnerNom";

          conn.rs=conn.st.executeQuery(checker);
          if(conn.rs.next()==true){
              
 System.out.println("Data already exist loading............................");

        counter++;  
  
    
     county=conn.rs.getString(1);
     district=conn.rs.getString(2);
     facilityname=conn.rs.getString(3);
     mflcode=conn.rs.getString(4);    
String basicDetails=county+"@"+district+"@"+facilityname+"@"+mflcode;
String arrayDetails []=basicDetails.split("@");

   if(conn.rs.getInt(122)==1) { 
  counterPMTCT1++;
  XSSFRow rw2S1=shet1.createRow(counterPMTCT1);
  int facilno=0;
  
   for(String facilityDetails:arrayDetails){
     
  XSSFCell  S3cell=rw2S1.createCell(facilno);
    S3cell.setCellValue(facilityDetails);
    S3cell.setCellStyle(stborder); 
    
     facilno++; 
   }
  int pos;
  for (int i=4;i<=counterPMTCT;i++){
   XSSFCell  S3cell=rw2S1.createCell(i);
   pos=i+1;
   System.out.println("cell no 1 : "+i+" value no : "+pos);
    S3cell.setCellValue(conn.rs.getInt(pos));
    S3cell.setCellStyle(stborder);    
      
  }
  isValidated=conn.rs.getString(125);
  if(isValidated.equals("1")){
      isValidated="Yes";
  }
  else{
      isValidated="No";
  }
   XSSFCell  S3cell=rw2S1.createCell(46);
    S3cell.setCellValue(isValidated);
    S3cell.setCellStyle(stborder); 
    
   }
   
   
   if(conn.rs.getInt(123)==1){
  counterART1++;
   
  XSSFRow rw2S2=shet2.createRow(counterART1);
  
  int facilno=0;
   for(String facilityDetails:arrayDetails){
    
  XSSFCell  S3cell=rw2S2.createCell(facilno);
    S3cell.setCellValue(facilityDetails);
    S3cell.setCellStyle(stborder);
    
      facilno++; 
   }
   
   
  int pos;
  for (int i=4;i<=counterART;i++){
   XSSFCell  S3cell=rw2S2.createCell(i);
   pos=i+43;
   System.out.println("cell no 2 : "+i+" value no : "+pos);
    S3cell.setCellValue(conn.rs.getInt(pos));
    S3cell.setCellStyle(stborder);    
      
  }
  
   isValidated=conn.rs.getString(125);
  if(isValidated.equals("1")){
      isValidated="Yes";
  }
  else{
      isValidated="No";
  }
   XSSFCell  S3cell=rw2S2.createCell(65);
    S3cell.setCellValue(isValidated);
    S3cell.setCellStyle(stborder); 
    
    
  
   }
   
   if(conn.rs.getInt(124)==1){
counterPEP1++;

  XSSFRow rw2S3=shet3.createRow(counterPEP1);
  int facilno=0;
   for(String facilityDetails:arrayDetails){
    
  XSSFCell  S3cell=rw2S3.createCell(facilno);
    S3cell.setCellValue(facilityDetails);
    S3cell.setCellStyle(stborder); 
    
     facilno++;  
   }
  
  int pos;
  for (int i=4;i<=counterPEP;i++){
   XSSFCell  S3cell=rw2S3.createCell(i);
   pos=i+104;
   System.out.println("cell no 3 : "+i+" value no : "+pos);
    S3cell.setCellValue(conn.rs.getInt(pos));
    S3cell.setCellStyle(stborder);    
      
  }

    
   isValidated=conn.rs.getString(125);
  if(isValidated.equals("1")){
      isValidated="Yes";
  }
  else{
      isValidated="No";
  }
   XSSFCell  S3cell=rw2S3.createCell(17);
    S3cell.setCellValue(isValidated);
    S3cell.setCellStyle(stborder); 
    
  
   }

   
 String getCummulatives="SELECT SUM(HV0314),SUM(HV0315),SUM(HV0316),SUM(HV0317),SUM(HV0318),SUM(HV0319),SUM(HV0334),SUM(HV0335),"
    + "SUM(HV0336),SUM(HV0337),SUM(HV0338),SUM(HV0339),SUM(HV0340),SUM(HV0341),SUM(HV0342),SUM(HV0343),SUM(HV0344)"+
    "FROM moh731 WHERE "+facility+" yearmonth="+maxYearMonth;
    conn.rs1=conn.st1.executeQuery(getCummulatives);
    if(conn.rs1.next()==true){
System.out.println("entered to get cumulatives : "+maxYearMonth);
   
    }
   }
         
          System.out.println("Validity checker : "+isValidated);
    
    if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
//     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
//     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.conn!=null){conn.conn.close();} 
     
        IdGenerator IG = new IdGenerator();
        createdOn=IG.CreatedOn();
     
      // write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=MOH731_RAW_DATA_REPORT_FOR_"+year+"("+monthName.trim()+")_CREATED_"+createdOn.trim()+".xlsx");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
    
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
        Logger.getLogger(staticReportExcel731.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(staticReportExcel731.class.getName()).log(Level.SEVERE, null, ex);
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

}
