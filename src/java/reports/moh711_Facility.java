/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

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
 * @author Maureen
 */
public class moh711_Facility extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   String county,district,facilityname,mflcode;
int counter,counterPMTCT,counterVCT,counterDTC,counterFP,counterMAT;
String sheets,headers,elementName;
String year="";
String month="";
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
       if(request.getParameter("year")!=null && !request.getParameter("year").equals("")){
         year=request.getParameter("year");}
        if(request.getParameter("month")!=null && !request.getParameter("month").equals("")){
       month=request.getParameter("month");}
     
      try{
      counter=0;
      int rowcounter=0;
      int fprows=1;
      int pmtctrows=1;
      int matrows=1;
      int vctrows=1;
      int dtcrows=1;
      //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
   XSSFWorkbook wb=new XSSFWorkbook();
  XSSFSheet shet1=wb.createSheet("FP");
  XSSFSheet shet2=wb.createSheet("MCH-ANC PMCT");
  XSSFSheet shet3=wb.createSheet("MATERNITY SAFE DELIVERIES");
  XSSFSheet shet4=wb.createSheet("VCT");
  XSSFSheet shet5=wb.createSheet("DTC");
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
stylex.setFillForegroundColor(HSSFColor.LIME.index);
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

    shet1.setColumnWidth(0, 10);
    
    for (int i=1;i<=47;i++){
   shet1.setColumnWidth(i, 6000);     
    }
    
    shet2.setColumnWidth(0, 10);
    
    for (int i=1;i<=66;i++){
   shet2.setColumnWidth(i, 3000);     
    }
    
    shet3.setColumnWidth(0, 10);
    
    for (int i=1;i<=19;i++){
   shet3.setColumnWidth(i, 3000);     
    }
    shet4.setColumnWidth(0, 10);
    
    for (int i=1;i<=19;i++){
   shet4.setColumnWidth(i, 3000);     
    }
    shet5.setColumnWidth(0, 10);
    
    for (int i=1;i<=19;i++){
   shet5.setColumnWidth(i, 3000);     
    }
    
    headers="COUNTY,SUB COUNTY,FACILITY NAME,MFL CODE";
   
    String arrayHeader []=headers.split(",");
    int headerno=0;
   
   XSSFRow rw0S1=shet1.createRow(0);
   XSSFRow rw1S1=shet1.createRow(1);  
   
    XSSFRow rw0S2=shet2.createRow(0);
   XSSFRow rw1S2=shet2.createRow(1);
    
    XSSFRow rw0S3=shet3.createRow(0);
   XSSFRow rw1S3=shet3.createRow(1);
   
    XSSFRow rw0S4=shet4.createRow(0);
   XSSFRow rw1S4=shet4.createRow(1);
   
    XSSFRow rw0S5=shet5.createRow(0);
   XSSFRow rw1S5=shet5.createRow(1);

    
//    LOOP THROUGH AND WRITE STATIC HEADERS TO THE ELEMENTS
     for(String headername:arrayHeader){
        headerno++;
    XSSFCell  S1cell=rw1S1.createCell(headerno);
    S1cell.setCellValue(headername);
    S1cell.setCellStyle(stylex);
   
    XSSFCell  S2cell=rw1S2.createCell(headerno);
    S2cell.setCellValue(headername);
    S2cell.setCellStyle(stylex);
    
    XSSFCell  S3cell=rw1S3.createCell(headerno);
    S3cell.setCellValue(headername);
    S3cell.setCellStyle(stylex);
    
      XSSFCell  S4cell=rw1S4.createCell(headerno);
    S4cell.setCellValue(headername);
    S4cell.setCellStyle(stylex);
    
      XSSFCell  S5cell=rw1S5.createCell(headerno);
    S5cell.setCellValue(headername);
    S5cell.setCellStyle(stylex);
    }
  
   
  counterFP=counterPMTCT=counterMAT=counterVCT=counterDTC=4; 
          
    String getVariables="SELECT * FROM pivottable where form ='moh711' "; 
    conn.rs1=conn.st1.executeQuery(getVariables);
    while(conn.rs1.next()){
//        elementName=conn.rs1.getString("section")+" \n"+conn.rs1.getString("code");
        
        if(conn.rs1.getString("section").equals("Family Planning")){
     counterFP++;
   XSSFCell  S1cell=rw1S1.createCell(counterFP);
    S1cell.setCellValue(conn.rs1.getString("shortlabel"));
    S1cell.setCellStyle(stylex);
  }
    else if (conn.rs1.getString("section").equals("MCH-ANC/PMCT")){    
     counterPMTCT++;
     XSSFCell  S2cell=rw1S2.createCell(counterPMTCT);
    S2cell.setCellValue(conn.rs1.getString("shortlabel")); 
    S2cell.setCellStyle(stylex); 
        }
        
  else if (conn.rs1.getString("section").equals("MATERNITY/SAFE DELIVERIES")){    
      counterMAT++; 
      XSSFCell  S3cell=rw1S3.createCell(counterMAT);
    S3cell.setCellValue(conn.rs1.getString("shortlabel"));
    S3cell.setCellStyle(stylex);
     System.out.println("section "+conn.rs1.getString("shortlabel"));
        }
 
  else if (conn.rs1.getString("section").equals("VCT")){    
      counterVCT++; 
      XSSFCell  S4cell=rw1S4.createCell(counterVCT);
    S4cell.setCellValue(conn.rs1.getString("shortlabel"));
    S4cell.setCellStyle(stylex);
     
        }
  else if (conn.rs1.getString("section").equals("DTC")){    
      counterDTC++; 
      XSSFCell  S5cell=rw1S5.createCell(counterDTC);
    S5cell.setCellValue(conn.rs1.getString("shortlabel"));
    S5cell.setCellStyle(stylex);
     
        }
//     System.out.println("bbbb "+counterMAT);
    }
      
    String getdata="SELECT county.County,district.DistrictNom,subpartnera.SubPartnerNom,subpartnera.CentreSanteId,FPMicrolutN,"
            + " FPMicrolutR, FPMicrolutT, FPMicrogynonN, FPMicrogynonR, FPMicrogynonT,"
            + " FPINJECTIONSN, FPINJECTIONSR, FPINJECTIONST, FPIUCDN, FPIUCDT, FPIUCDR, FPIMPLANTSN, FPIMPLANTSR, "
            + "FPIMPLANTST, FPBTLN, FPBTLR, FPBTLT, FPVasectomyN, FPVasectomyR, FPVasectomyT, FPCONDOMSN, FPCONDOMSR, "
            + "FPCONDOMST, FPOTHERN, FPOTHERR, FPOTHERT, FPCLIENTSN, FPCLIENTSR, FPCLIENTST, FPIUCDRemoval,"
            + " FPIMPLANTSRemoval, PMCTA_1stVisit_ANC, PMCTA_ReVisit_ANC, PMCTANCClientsT, PMCTHB7, PMCTIPT1,"
            + " PMCTIPT2, PMCTANCClients4, PMCTITN, MATNormalDelivery, MATCSection, MATBreech, MATAssistedVag, "
            + "MATDeliveryT, MATLiveBirth, MATStillBirth, MATWeight2500, MATPreTerm, MATDischargealive, MATReferral, MATNeoNatalD,"
            + " MATMaternalD, MATAPHAlive, MATAPHDead, MATPPHAlive, MATPPHDead, MATEclampAlive, MATEclampDead,"
            + " MATRupUtAlive, MATRupUtDead, MATObstrLaborAlive, MATObstrLaborDead, MATSepsisAlive, MATSepsisDead, "
            + "VCTClient_Couns_CM, VCTClient_Couns_CF, VCTClient_Couns_AM, VCTClient_Couns_AF, "
            + "VCTClient_Couns_TOT, VCTClient_Tested_CM, VCTClient_Tested_CF, VCTClient_Tested_AM,"
            + " VCTClient_Tested_AF, VCTClient_Tested_TOT, VCTClient_HIV_CM, VCTClient_HIV_CF, VCTClient_HIV_AM, VCTClient_HIV_AF,"
            + " VCTClient_HIV_TOT, VCTPartner_Couns_TOT, VCTPartner_Tested_TOT, VCTPartner_HIV_TOT, VCTPartner_Disc_TOT,"
            + " DTCA_Couns_In_CM, DTCA_Couns_In_CF, DTCA_Couns_In_AM, DTCA_Couns_In_AF, DTCA_Couns_In_Tot, DTCA_Couns_Out_CM, "
            + "DTCA_Couns_Out_CF, DTCA_Couns_Out_AM, DTCA_Couns_Out_AF, DTCA_Couns_Out_Tot,DTCB_Test_In_CM, DTCB_Test_In_CF,"
            + " DTCB_Test_In_AM, DTCB_Test_In_AF, DTCB_Test_In_Tot, DTCB_Test_Out_CM, DTCB_Test_Out_CF, DTCB_Test_Out_AM,"
            + " DTCB_Test_Out_AF, DTCB_Test_Out_Tot, DTCC_HIV_In_CM, DTCC_HIV_In_CF, DTCC_HIV_In_AM, DTCC_HIV_In_AF,"
            + " DTCC_HIV_In_Tot, DTCC_HIV_Out_CM, DTCC_HIV_Out_CF, DTCC_HIV_Out_AM, DTCC_HIV_Out_AF, DTCC_HIV_Out_Tot, "
            + "subpartnera.FP,subpartnera.PMTCT,subpartnera.Maternity,subpartnera.HTC "
            + "FROM moh711 JOIN subpartnera ON moh711.SubPartnerID=subpartnera.SubPartnerID "
              + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
              + "JOIN county ON county.CountyID=district.CountyID "
              + " WHERE moh711.Mois='"+month+"' && moh711.Annee='"+year+"' && isValidated=1";
    conn.rs = conn.st.executeQuery(getdata);
    while(conn.rs.next()){
//        county.County,district.DistrictNom,subpartnera.SubPartnerNom,subpartnera.CentreSanteId,
     county=conn.rs.getString(1);
     district=conn.rs.getString(2);
     facilityname=conn.rs.getString(3);
     mflcode=conn.rs.getString(4);   
     
String basicDetails=county+"@"+district+"@"+facilityname+"@"+mflcode;
String arrayDetails []=basicDetails.split("@");

System.out.println(counterFP   +"  "+basicDetails);

 if(conn.rs.getInt(119)==1){ 
  fprows++;
  XSSFRow rw2S1=shet1.createRow(fprows);
  int facilno=0;
  
   for(int j=0;j<arrayDetails.length;j++){
     facilno++;  
  XSSFCell  S3cell=rw2S1.createCell(facilno);
    S3cell.setCellValue(arrayDetails[j]);
//    System.out.println(arrayDetails[j]);
    S3cell.setCellStyle(stborder);  
    System.out.println("facildet pos : "+facilno+"     det : "+arrayDetails[j]);
   }
  int pos;
  for (int i=4;i<=counterFP;i++){
   XSSFCell  S3cell=rw2S1.createCell(i);
   pos=i;
   System.out.println("cell no 1 : "+i+" value no : "+pos);
    S3cell.setCellValue(conn.rs.getInt(pos));
    
    S3cell.setCellStyle(stborder);    
      
  }
   }
  System.out.println("counterPMTCT"+ counterPMTCT);
   if(conn.rs.getInt(120)==1){ 
  pmtctrows++;
  XSSFRow rw2S1=shet2.createRow(pmtctrows);
  int facilno=0;
  
   for(int j=0;j<arrayDetails.length;j++){
     facilno++;  
  XSSFCell  S3cell=rw2S1.createCell(facilno);
    S3cell.setCellValue(arrayDetails[j]);
//    System.out.println(arrayDetails[j]);
    S3cell.setCellStyle(stborder);  
    System.out.println("facildet pos PMTCT : "+facilno+"     det : "+arrayDetails[j]);
   }
  int pos;
  for (int i=5;i<=counterPMTCT;i++){
   XSSFCell  S3cell=rw2S1.createCell(i);
      pos=i+32;
   System.out.println("cell no 2 : "+i+" value no : "+pos);
    S3cell.setCellValue(conn.rs.getInt(pos));
    
    S3cell.setCellStyle(stborder);    
      
  }
   }
   System.out.println("counterMAT"+ counterMAT);
   if(conn.rs.getInt(121)==1){ 
  matrows++;
  XSSFRow rw2S1=shet3.createRow(matrows);
  int facilno=0;
  
   for(int j=0;j<arrayDetails.length;j++){
     facilno++;  
  XSSFCell  S3cell=rw2S1.createCell(facilno);
    S3cell.setCellValue(arrayDetails[j]);
//    System.out.println(arrayDetails[j]);
    S3cell.setCellStyle(stborder);  
    System.out.println("facildet pos MAT : "+facilno+"     det : "+arrayDetails[j]);
   }
  int pos;
  for (int i=5;i<=counterMAT;i++){
   XSSFCell  S3cell=rw2S1.createCell(i);
      pos=i+40;
   System.out.println("cell no 3 : "+i+" value no : "+pos);
    S3cell.setCellValue(conn.rs.getInt(pos));
    
    S3cell.setCellStyle(stborder);    
      
  }
  
   }
   
    if(conn.rs.getInt(122)==1){ 
  vctrows++;
  XSSFRow rw2S1=shet4.createRow(vctrows);
  int facilno=0;
  
   for(int j=0;j<arrayDetails.length;j++){
     facilno++;  
  XSSFCell  S3cell=rw2S1.createCell(facilno);
    S3cell.setCellValue(arrayDetails[j]);
//    System.out.println(arrayDetails[j]);
    S3cell.setCellStyle(stborder);  
    System.out.println("facildet pos VCT : "+facilno+"     det : "+arrayDetails[j]);
   }
  int pos;
  for (int i=5;i<=counterVCT;i++){
   XSSFCell  S3cell=rw2S1.createCell(i);
      pos=i+65;
   System.out.println("cell no 4 : "+i+" value no : "+pos);
    S3cell.setCellValue(conn.rs.getInt(pos));
    
    S3cell.setCellStyle(stborder);    
      
  }
  
  
   }  
    if(conn.rs.getInt(122)==1){ 
  dtcrows++;
  XSSFRow rw2S1=shet5.createRow(dtcrows);
  int facilno=0;
  
   for(int j=0;j<arrayDetails.length;j++){
     facilno++;  
  XSSFCell  S3cell=rw2S1.createCell(facilno);
    S3cell.setCellValue(arrayDetails[j]);
//    System.out.println(arrayDetails[j]);
    S3cell.setCellStyle(stborder);  
    System.out.println("facildet pos DTC : "+facilno+"     det : "+arrayDetails[j]);
   }
  int pos;
  for (int i=5;i<=counterDTC;i++){
   XSSFCell  S3cell=rw2S1.createCell(i);
      pos=i+84;
   System.out.println("cell no 4 : "+i+" value no : "+pos);
    S3cell.setCellValue(conn.rs.getInt(pos));
    
    S3cell.setCellStyle(stborder);    
      
  }
  
  
   }  
 
   }   //end of while
   
    
// write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=MOH711_RAW_DATA_PER_FACILITY.xlsx");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
//     counterPMTCT1=counterART1=counterPEP1=1;   
        } finally {            
//            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
            Logger.getLogger(moh711_Facility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
            Logger.getLogger(moh711_Facility.class.getName()).log(Level.SEVERE, null, ex);
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
