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
import java.util.ArrayList;
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

/**
 *
 * @author Geofrey Nyabuto
 */
public class htctracker731 extends HttpServlet {
HttpSession session;
int year,prevYear,month,isPMTCT,isART,isPEP;
String header,facilityName,countyName,districtName,mflcode,monthName,createdOn;
String prevFacility,currentFacility,currentMonth,duration;
int secCounter,currentYear,selectedYear,status,monthid;
int maxYearMonth,minYearMonth,counterHeader,monthPosition,counter,districtCounter;
String monthsData,selectedMonth,currentDistrict,prevDistrict ;
ArrayList allMonths=new ArrayList();
ArrayList allReports=new ArrayList();
int countyCounter,districtsMerged,noReports,subMittedReports,arraySize;
String currentCounty,prevCounty;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         
        dbConn conn = new dbConn();
        session=request.getSession();
        
        year=Integer.parseInt(request.getParameter("year"));
//        year=2015;
       prevYear=year-1; 
        IdGenerator IG = new IdGenerator();
        allMonths.clear();
        allReports.clear();
        duration="WHERE (moh731.yearmonth BETWEEN "+prevYear+""+10+" AND "+year+"09) AND ( subpartnera.HTC=1) AND ( HV0103 > 0)";
        
        currentMonth=IG.CurrentMonth();
       
        
        monthsData="";
 //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
   HSSFWorkbook wb=new HSSFWorkbook();
  HSSFSheet shet1=wb.createSheet("MOH 731 HTC REPORTS TRACKER");

  HSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)18);
    font.setFontName("Arial Black");
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     HSSFFont font2=wb.createFont();
    font2.setFontName("Arial Black");
    font2.setColor((short)0000);
    CellStyle style2=wb.createCellStyle();
    style2.setFont(font2);
   
   HSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    stborder.setWrapText(true);
    
    HSSFCellStyle stylex = wb.createCellStyle();
stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
HSSFFont fontx = wb.createFont();
fontx.setColor(HSSFColor.DARK_BLUE.index);
stylex.setFont(fontx);
stylex.setWrapText(true);

   HSSFCellStyle styleHeader = wb.createCellStyle();
styleHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
HSSFFont fontHeader = wb.createFont();
fontHeader.setColor(HSSFColor.DARK_BLUE.index);
styleHeader.setFont(fontHeader);
styleHeader.setWrapText(true);

    
    for (int i=0;i<=2;i++){
   shet1.setColumnWidth(i, 8000);     
    }
   HSSFRow rw1S1=shet1.createRow(0);
   HSSFCell  S1cell=rw1S1.createCell(0);
    S1cell.setCellValue("COUNTY NAME");
    S1cell.setCellStyle(stylex);
    
    HSSFCell  S1cellX=rw1S1.createCell(1);
    S1cellX.setCellValue("SUB COUNTY");
    S1cellX.setCellStyle(stylex);
    
    S1cellX=rw1S1.createCell(2);
    S1cellX.setCellValue("HEALTH FACILITY");
    S1cellX.setCellStyle(stylex);
    
    S1cellX=rw1S1.createCell(3);
    S1cellX.setCellValue("MFL CODE");
    S1cellX.setCellStyle(stylex);
    
    S1cellX=rw1S1.createCell(4);
    S1cellX.setCellValue("EXPECTED REPORTS");
    S1cellX.setCellStyle(stylex);
    
    counterHeader=5;
    String getMaxandMin="SELECT DISTINCT(month.name),month.id FROM moh731 JOIN month ON moh731.Mois=month.id JOIN subpartnera ON moh731.SubPartnerID=subpartnera.SubPartnerID "+duration+" "
    + " ORDER BY  moh731.yearmonth";
    conn.rs=conn.st.executeQuery(getMaxandMin);
   while (conn.rs.next()){
      monthName=conn.rs.getString(1);
      monthid=conn.rs.getInt(2);
      if(monthid<=9){currentYear=year;}
      else{currentYear=prevYear;}
     System.out.println(" Months are : "+monthName);
  allMonths.add(monthName);
  allReports.add(0);
   S1cellX=rw1S1.createCell(counterHeader);
    S1cellX.setCellValue(monthName);
    S1cellX.setCellStyle(stylex);
   counterHeader++;
   }
prevFacility=currentFacility="";
currentDistrict=prevDistrict="";
currentCounty=prevCounty="";
   counter=districtCounter=countyCounter=districtsMerged=0;
   arraySize=allReports.size();
   
   
   if(allMonths.size()>0)
   {
     String checkReports="SELECT county.County,district.DistrictNom,subpartnera.SubPartnerNom,"
     + "subpartnera.CentreSanteId,COUNT(moh731.SubPartnerID),month.name,subpartnera.SubPartnerID "
    + "FROM subpartnera "
    + "LEFT JOIN moh731 ON moh731.SubPartnerID=subpartnera.SubPartnerID "
    + "LEFT JOIN district ON subpartnera.DistrictID=district.DistrictID "
    + "LEFT JOIN county ON county.CountyID=district.CountyID "
    + "LEFT JOIN month ON moh731.Mois=month.id "
    + " "+duration+" "
    + " GROUP BY subpartnera.SubPartnerNom,moh731.Annee,moh731.Mois "
    + "ORDER BY county.County,district.DistrictNom,subpartnera.SubPartnerNom,moh731.Mois";
     System.out.println(checkReports);
    conn.rs=conn.st.executeQuery(checkReports);
    while(conn.rs.next()){
     countyName=conn.rs.getString(1);
     districtName=conn.rs.getString(2);
     facilityName=conn.rs.getString(3);
     mflcode=conn.rs.getString(4);  
     status=conn.rs.getInt(5);
     selectedMonth=conn.rs.getString(6);
     currentFacility=conn.rs.getString(7);
     currentDistrict=districtName;
     currentCounty=countyName;
//     CHECK WHERE TO PLACE THE NUMBER; 
     
   monthPosition= allMonths.indexOf(selectedMonth);
   
    if(!prevFacility.equals(currentFacility)){
       
        if(!prevDistrict.equals(currentDistrict) && !prevDistrict.equals("")){
        counter++;
        noReports=districtCounter+1;
   HSSFRow rwTotal=shet1.createRow(counter);
   HSSFCell SX=rwTotal.createCell(0);
   SX.setCellStyle(stborder);
    
    SX=rwTotal.createCell(1);
    SX.setCellValue(prevDistrict+" TOTALS : ");
    SX.setCellStyle(styleHeader);
    
    SX=rwTotal.createCell(2);
    SX.setCellStyle(styleHeader);
    
    SX=rwTotal.createCell(3);
    SX.setCellStyle(styleHeader);
    
    SX=rwTotal.createCell(4);
    SX.setCellValue(noReports);
    SX.setCellStyle(styleHeader);
    shet1.addMergedRegion(new CellRangeAddress(counter,counter,1,3));
      
     for(int j=0;j<allReports.size();j++){
//         System.out.println("district name : "+prevDistrict+" no of reports : "+allReports.get(j).toString()+" for month : "+allMonths.get(j).toString());
    int dataPos=5+j;
    SX=rwTotal.createCell(dataPos);
    SX.setCellValue(Integer.parseInt(allReports.get(j).toString()));
    SX.setCellStyle(styleHeader);  
    }
      for(int k=0;k<arraySize;k++){
   allReports.set(k, 0);
     }
        }
    counter++;
   HSSFRow rw1=shet1.createRow(counter);
   HSSFCell  S1=rw1.createCell(0);
    S1.setCellValue(countyName);
    S1.setCellStyle(stborder);
    
    S1=rw1.createCell(1);
    S1.setCellValue(districtName);
    S1.setCellStyle(stborder);
    
    S1=rw1.createCell(2);
    S1.setCellValue(facilityName);
    S1.setCellStyle(stborder);
    
    S1=rw1.createCell(3);
    S1.setCellValue(mflcode);
    S1.setCellStyle(stborder);
    
    S1=rw1.createCell(4);
    S1.setCellValue(1);
    S1.setCellStyle(stborder);
    
     
    for(int j=0;j<allMonths.size();j++){
    int cellPos=j+5;
    S1=rw1.createCell(cellPos);
// System.out.println("counter : "+counter+" datapos : "+cellPos+" status : "+status);
    S1.setCellStyle(stborder);    
        
    }
    int dataPos=5+monthPosition;
    S1=rw1.getCell(dataPos);
    S1.setCellValue(status);
   
    if(!prevDistrict.equals(currentDistrict)&& !prevDistrict.equals("")){
        int distStart=counter-districtCounter-2;
        int distEnd=counter-2;
     shet1.addMergedRegion(new CellRangeAddress(distStart,distEnd,1,1)); 
districtsMerged++;
     districtCounter=0;
     
     
     for(int j=0;j<arraySize;j++){
   allReports.set(j, 0);
     }
     
    }
    else{
        if(counter==1){}
        else{districtCounter++;}
    }
    if(!prevCounty.equals(currentCounty) && !prevCounty.equals("")){
     int countyStart=counter-countyCounter-districtsMerged-1;
     int countyEnd=counter-1;
     shet1.addMergedRegion(new CellRangeAddress(countyStart,countyEnd,0,0)); 
    countyCounter=0;
    districtsMerged=0;
    }
    else{
        if(counter==1){}
        else{countyCounter++;}
    }
    prevCounty=currentCounty;
   prevDistrict=currentDistrict;
    }
    else{  
   
   HSSFRow rw1=shet1.getRow(counter);
      int dataPos=5+monthPosition;
    HSSFCell  S1 =rw1.getCell(dataPos);
    S1.setCellValue(status);   
//    
    }

    if(status==1){
     int currentData=Integer.parseInt(allReports.get(monthPosition).toString())+1;
     allReports.set(monthPosition, currentData);
    }
    
    prevFacility=currentFacility;

    }
//    MATCH THE LAST DISTRICTS
        counter++;
        noReports=districtCounter+1;
     HSSFRow rwTotal=shet1.createRow(counter);
   HSSFCell SX=rwTotal.createCell(0);
   SX.setCellStyle(stborder);
    
    SX=rwTotal.createCell(1);
    SX.setCellValue(prevDistrict+" TOTALS : ");
    SX.setCellStyle(styleHeader);
    
    SX=rwTotal.createCell(2);
    SX.setCellStyle(styleHeader);
    
    SX=rwTotal.createCell(3);
    SX.setCellStyle(styleHeader);
    
    SX=rwTotal.createCell(4);
    SX.setCellValue(noReports);
    SX.setCellStyle(styleHeader);
    shet1.addMergedRegion(new CellRangeAddress(counter,counter,1,3));
    
     int distStart=counter-districtCounter-1;
     int distEnd=counter-1;
//     System.out.println("MERGE BETWEEN : START : "+distStart+" END : "+distEnd);
     shet1.addMergedRegion(new CellRangeAddress(distStart,distEnd,1,1)); 
    districtCounter=0;
    
     int countyStart=counter-countyCounter-2;
     int countyEnd=counter;
//        System.out.println("MERGE BETWEEN : START : "+distStart+" END : "+distEnd);
     shet1.addMergedRegion(new CellRangeAddress(countyStart,countyEnd,0,0)); 
    countyCounter=0; 
    
    for(int j=0;j<allReports.size();j++){
//         System.out.println("district name : "+prevDistrict+" no of reports : "+allReports.get(j).toString()+" for month : "+allMonths.get(j).toString());
    int dataPos=5+j;
    SX=rwTotal.createCell(dataPos);
    SX.setCellValue(Integer.parseInt(allReports.get(j).toString()));
    SX.setCellStyle(styleHeader);  
    }
        
     if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.conn!=null){conn.conn.close();} 
     
       
        createdOn=IG.CreatedOn();
     
      // write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=HTC_Tracker_summary_for_PEPFAR_YEAR("+year+")_"+createdOn.trim()+".xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
   }
   else{
       
       session.setAttribute("noTrackerReport", "<font color=\"red\"><b>SORRY:</b> No report was found for "+year+".</red>");
       response.sendRedirect("reportsTracker.jsp");
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
        Logger.getLogger(reportsTracker731.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(reportsTracker731.class.getName()).log(Level.SEVERE, null, ex);
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
