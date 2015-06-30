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
public class moh731_Facility extends HttpServlet {
    HttpSession session;
String data,id;
String facilityId,year,month;
String county,district,facilityname,mflcode;
int counter,counterPMTCT,counterART,counterPEP,counterPMTCT1,counterART1,counterPEP1;
String sheets,headers,elementName,monthName,createdOn;
String isValidated;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
       
       
      year=request.getParameter("year");
      month=request.getParameter("month");
       
//      year="2015";
//      month="5";
      counter=0;
      monthName="";
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
    
    headers="COUNTY,SUB COUNTY,FACILITY NAME,MFL CODE";
   
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
    XSSFCell  S3cella=rw1S1.createCell(46);
    S3cella.setCellValue("Validation Run");
    S3cella.setCellStyle(stylex); 
    
    XSSFCell  S3cell2a=rw1S2.createCell(65);
    S3cell2a.setCellValue("Validation Run");
    S3cell2a.setCellStyle(stylex); 
    
   XSSFCell  S3cell3a=rw1S3.createCell(17);
    S3cell3a.setCellValue("Validation Run");
    S3cell3a.setCellStyle(stylex);  
    
//   counter=1; 
  System.out.println("pmtct : "+counterPMTCT+" art : "+counterART+" PEPE : "+counterPEP);
//  counterPMTCT=counterPMTCT-5;
//  counterART=counterART-5;
//  counterPEP=counterPEP-5;
  
  
     counterPMTCT1=counterART1=counterPEP1=0;
      String getData="SELECT county.County,district.DistrictNom,subpartnera.SubPartnerNom,subpartnera.CentreSanteId,"
+ "moh731.HV0201,moh731.HV0202,moh731.HV0203,moh731.HV0204,moh731.HV0205,moh731.HV0206,moh731.HV0207,moh731.HV0208,moh731.HV0209,moh731.HV0210,moh731.HV0211,moh731.HV0212,moh731.HV0213," +
"moh731.HV0214,moh731.HV0215,moh731.HV0216,moh731.HV0217,moh731.HV0218,moh731.HV0219,moh731.HV0220,moh731.HV0221,moh731.HV0224,moh731.HV0225,moh731.HV0226,moh731.HV0227,moh731.HV0228,moh731.HV0229," +
"        moh731.HV0230,moh731.HV0231,moh731.HV0232,moh731.HV0233,moh731.HV0234,moh731.HV0235,moh731.HV0236,moh731.HV0237,moh731.HV0238,moh731.HV0239,moh731.HV0240,moh731.HV0241,moh731.HV0242," +
"        moh731.HV0243,moh731.HV0244,moh731.HV0301,moh731.HV0302,moh731.HV0303,moh731.HV0304,moh731.HV0305,moh731.HV0306,moh731.HV0307,moh731.HV0308,moh731.HV0309,moh731.HV0310,moh731.HV0311,moh731.HV0312,moh731.HV0313,moh731.HV0314," +
"        moh731.HV0315,moh731.HV0316,moh731.HV0317,moh731.HV0318,moh731.HV0319,moh731.HV0320,moh731.HV0321,moh731.HV0322,moh731.HV0323,moh731.HV0324,moh731.HV0325,moh731.HV0326,moh731.HV0327,moh731.HV0328," +
"        moh731.HV0329,moh731.HV0330,moh731.HV0331,moh731.HV0332,moh731.HV0333,moh731.HV0334,moh731.HV0335,moh731.HV0336,moh731.HV0337,moh731.HV0338,moh731.HV0339,moh731.HV0340,moh731.HV0341," +
"        moh731.HV0342,moh731.HV0343,moh731.HV0344,moh731.HV0345,moh731.HV0346,moh731.HV0347,moh731.HV0348,moh731.HV0349,moh731.HV0350,moh731.HV0351,moh731.HV0352,moh731.HV0353," +
"        moh731.HV0354,moh731.HV0355,moh731.HV0904,moh731.HV0905,moh731.HV0370,moh731.HV0371,moh731.HV0372,moh731.HV0373,"+
"        moh731.HV0501,moh731.HV0502,moh731.HV0503,moh731.HV0504,moh731.HV0505,moh731.HV0506,moh731.HV0507,moh731.HV0508,moh731.HV0509,moh731.HV0510,moh731.HV0511,moh731.HV0512,moh731.HV0513,moh731.HV0514,"
+ "subpartnera.PMTCT,subpartnera.ART,subpartnera.PEP,isValidated "
 + " FROM moh731 JOIN subpartnera ON moh731.SubPartnerID=subpartnera.SubPartnerID "
              + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
              + "JOIN county ON county.CountyID=district.CountyID "
              + " WHERE moh731.Mois='"+month+"' && moh731.Annee='"+year+"'"
              + " ORDER BY county.County,district.DistrictNom,subpartnera.SubPartnerNom";
      conn.rs=conn.st.executeQuery(getData);
     while (conn.rs.next()){
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
System.out.println("counter : "+counter);
     } 
      
    
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
response.setHeader("Content-Disposition", "attachment; filename=MOH731_RAW_DATA_REPORT_FOR_"+year.trim()+"("+monthName.trim()+")_CREATED_"+createdOn.trim()+".xlsx");
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
            Logger.getLogger(moh731_Facility.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(moh731_Facility.class.getName()).log(Level.SEVERE, null, ex);
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
