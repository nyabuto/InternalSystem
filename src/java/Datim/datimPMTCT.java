/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Datim;

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
public class datimPMTCT extends HttpServlet {
HttpSession session;
int year,month,prevYear,maxYearMonth;
String reportDuration,duration,semi_annual,quarter;
String facilityName,mflcode,countyName,districtName,facilityIds,facilityId;
String createdOn,period,PMTCTSupport;

int pmtctpos;

int HV0210,HV0209,HV0205,HV0217,HV0216;
int HV0224,HV0225,HV0227,HV0232,HV0229,HV0230,HV0231;
int HV0301,HV0206,HV0207,HV0208;

Double PMTCT_FO_I_N,PMTCT_FO_I_D,PMTCT_FO_I_LINKED,PMTCT_FO_I_NOT_LINKED,PMTCT_FO_I_UNKNOWN,
        PMTCT_FO_U_NOT_BREASTFEEDING,PMTCT_FO_U_STILL_BREASTFEEDING,PMTCT_FO_U_BREASTFEEDING_UNKNOWN,
        PMTCT_FO_OTHER_INCARE,PMTCT_FO_OTHER_NOFOLLOWUP,PMTCT_FO_DIED,PMTCT_FO_TRANSFERRED;
Double PMTCT_ARV_N,PMTCT_ARV_D,PMTCT_ARV_LIFELONGART_NEW,PMTCT_ARV_LIFELONGART_EXISTING,
        PMTCT_ARV_MATERNAL_TRIPLEDRUG_ARV,PMTCT_ARV_MATERNAL_AZT,PMTCT_ARV_SINGLEDOSE;
Double PMTCT_EID_N,PMTCT_EID_VIRO_2MONTHS,PMTCT_EID_VIRO_2_12MONTHS,
        PMTCT_EID_P_VIRO_2MONTHS,PMTCT_EID_P_VIRO_2_12MONTHS;
Double PMTCT_STATN_N,PMTCT_STATN_KNOWNPOSTIVE,PMTCTN_STAT_NEWPOSTIVE;
Double PMTCT_STATD_D,PMTCT_STATD_LESS15,PMTCT_STATD_15_19,PMTCT_STATD_20_24,PMTCT_STATD_25;
Double PMTCT_CTX;
    ArrayList allFacilities = new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session = request.getSession();
       dbConn conn = new dbConn();
       allFacilities.clear();
//       year=Integer.parseInt(request.getParameter("year"));
//        reportDuration=request.getParameter("reportDuration");
        
       String headerPMTCT []="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,Denominator,HIV-infected:Linked to ART,HIV-infected: Not linked to ART,HIV-infected : Unknown link,HIV-uninfected:Not beastfeeding,HIV-uninfected: Still breastfeeeding,HIV-uninfected:Breastfeeding unknown,Other outcomes: In care but not test done, Other outcomes:Lost to follow up,Other outcomes : Died,Other outcomes:Transferred out,Numerator,Denominator,Life-long ART:New,Life-long ART: Already on treatment at the beginning of the current pregnancy,Maternal Triple-Drug ARV,Maternal AZT,Single-dose nevirapine(with or without tail),Numerator,Infants who received a virologic test within 2 months of birth, Infants who received their first virologic HIV test between 2 and 12 months of age,Infants with a postive virologic test results within 2 months of birth, Infants with a postive virologic test resultsbetween 2 and 12 months of age,Numerator,Known postive at entry,New postives,Denominator,Numerator ".split(",") ;

        year=2015;
        reportDuration="4";
        period="";
        prevYear=year-1; 
        maxYearMonth=0;
        facilityIds="(";
        pmtctpos=0;
//        GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
         duration=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        period="DATIM ANNUAL DATA REPORT FOR PEPFAR YEAR : "+year;
        }
        else if(reportDuration.equals("2")){
//        semi_annual=request.getParameter("semi_annual");
        semi_annual="2";
       if(semi_annual.equals("1")){
     duration=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period="DATIM SEMI - ANNUAL DATA REPORT FOR : OCT "+prevYear+" to MARCH "+year;
       }
           else{
       duration=" moh731.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
      period="DATIM SEMI - ANNUAL DATA REPORT FOR : APRIL "+year+" to SEPT "+year; 
       }
       }
        
        else if(reportDuration.equals("3")){
            String startMonth,endMonth;
//       quarter=request.getParameter("quarter");
       quarter="3";
       String getMonths="SELECT months,name FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" moh731.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
      period="DATIM QUARTERLY DATA REPORT FOR : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      }
      else{
     duration=" moh731.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
     period="DATIM QUARTERLY DATA REPORT FOR : "+conn.rs.getString(2).replace("-", " "+year+" TO ")+" "+year+"";
      }
        }
        }  
        
      else if(reportDuration.equals("4")){
//     month=Integer.parseInt(request.getParameter("month"));
            month=5;
           String getMonthName="SELECT name FROM month WHERE id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
   if(month>=10){
     duration=" moh731.yearmonth="+prevYear+""+month;    
     period="DATIM MONTHLY DATA REPORT FOR : "+conn.rs.getString(1)+"("+prevYear+")"; 
     }
     else{
  duration=" moh731.yearmonth="+year+"0"+month;  
    period="DATIM MONTHLY DATA REPORT FOR : "+conn.rs.getString(1)+"("+year+")";
     }
      }
      }
      else{
     duration="";     
      }
        
     if(request.getParameter("subcounty")!=null && !request.getParameter("subcounty").equals(""))   {
         String subcounty=request.getParameter("subcounty");
    String getDist="SELECT subpartnera.SubPartnerID FROM subpartnera "
    + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
     + "WHERE district.DistrictID='"+subcounty+"'" ;
    conn.rs=conn.st.executeQuery(getDist);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
     facilityIds+=" moh731.SubPartnerID='"+conn.rs.getString(1)+"' || ";
    }
      facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && ";   
     } 
     else{
        if(request.getParameter("county")!=null && !request.getParameter("county").equals(""))   {  
         String county=request.getParameter("county");
         String getCounty="SELECT subpartnera.SubPartnerID FROM subpartnera "
    + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
     + "JOIN county ON district.CountyID=county.CountyID WHERE county.CountyID='"+county+"'" ;
    conn.rs=conn.st.executeQuery(getCounty);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
     facilityIds+=" moh731.SubPartnerID='"+conn.rs.getString(1)+"' || ";
    }
    facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && ";     
     }
       
        else{
       facilityIds="";    
        }   
        
     }
     System.out.println("period is : "+period);
     
//     GET FACILITIES TO OUTPUT.................................
    mflcode=countyName=districtName=facilityName="";    
    
//  facilityId=request.getParameter("facility");
//  facilityIds="403";
  
    HSSFWorkbook wb=new HSSFWorkbook();
  HSSFSheet shetPMTCT=wb.createSheet("PMTCT");

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
    
    
    HSSFCellStyle styleHeader = wb.createCellStyle();
styleHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    styleHeader.setWrapText(true);
    
    HSSFCellStyle styleminiHeader = wb.createCellStyle();
styleminiHeader.setFillForegroundColor(HSSFColor.ORCHID.index);
styleminiHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   styleminiHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    styleminiHeader.setWrapText(true);
    
HSSFFont fontHeader = wb.createFont();
fontHeader.setColor(HSSFColor.DARK_BLUE.index);
styleHeader.setFont(fontHeader);
styleHeader.setWrapText(true);

 
    for (int i=3;i<=headerPMTCT.length;i++){
   shetPMTCT.setColumnWidth(i, 4000);     
    }
    for (int i=0;i<=1;i++){
   shetPMTCT.setColumnWidth(i, 5000);     
    }
  shetPMTCT.setColumnWidth(2, 8000);  
 
  for(int i=5;i<=16;i++){
    shetPMTCT.setColumnWidth(i, 4000);   
  }
  
//  HSSFRow  rw00shetPMTCT=shetPMTCT.createRow(1);
//  rw00shetPMTCT.setHeightInPoints(30);
//
//  
//    HSSFCell  c011;
//  
// for(int j=0;j<headerPMTCT.length;j++){
//        c011=rw00shetPMTCT.createCell(j);
//         c011.setCellStyle(styleHeader);
//    }
//     
//    
//   c011=rw00shetPMTCT.getCell(0);
//   c011.setCellValue(period);
//   
//    c011=rw00shetPMTCT.getCell(5);
//   c011.setCellValue("CURRENT ON ART");
//   
//   c011=rw00shetPMTCT.getCell(16);
//   c011.setCellValue("NEW ON ART");
//  shetPMTCT.addMergedRegion(new CellRangeAddress(1,1,5,15));
//  shetPMTCT.addMergedRegion(new CellRangeAddress(1,1,16,32));
//
//  
//  
  HSSFRow  rw0shetPMTCT=shetPMTCT.createRow(1);
  rw0shetPMTCT.setHeightInPoints(30);
  
    HSSFCell  c001;
  
 for(int j=0;j<headerPMTCT.length;j++){
        c001=rw0shetPMTCT.createCell(j);
         c001.setCellStyle(styleHeader);
    } 
 c001=rw0shetPMTCT.getCell(0);
 c001.setCellValue(period); 
 
 c001=rw0shetPMTCT.getCell(5);
 c001.setCellValue("PMTCT_FO"); 
 
 c001=rw0shetPMTCT.getCell(17);
 c001.setCellValue("PMTCT_ARV"); 
 
    
  c001=rw0shetPMTCT.getCell(24);
 c001.setCellValue("PMTCT_EID");
   
   c001=rw0shetPMTCT.getCell(29);
   c001.setCellValue("PMTCT_STAT (Numerator)");
   
   c001=rw0shetPMTCT.getCell(32);
   c001.setCellValue("PMTCT_STAT (Denominator)");
   
   c001=rw0shetPMTCT.getCell(33);
   c001.setCellValue("PMTCT_CTX");
    
  shetPMTCT.addMergedRegion(new CellRangeAddress(1,1,0,4));
  shetPMTCT.addMergedRegion(new CellRangeAddress(1,1,5,16));
  shetPMTCT.addMergedRegion(new CellRangeAddress(1,1,17,23));
  shetPMTCT.addMergedRegion(new CellRangeAddress(1,1,24,28)); 
  shetPMTCT.addMergedRegion(new CellRangeAddress(1,1,29,31));
//  shetPMTCT.addMergedRegion(new CellRangeAddress(2,2,25,32)); 
    

  HSSFRow  rw2shetPMTCT=shetPMTCT.createRow(2);
  rw2shetPMTCT.setHeightInPoints(50);
  
    HSSFCell  c11;
    for(int headerpos=0;headerpos<headerPMTCT.length;headerpos++){
        String headerInfor=headerPMTCT[headerpos];
        c11=rw2shetPMTCT.createCell(headerpos);
         c11.setCellValue(headerInfor);
         c11.setCellStyle(styleHeader);
    }
     System.out.println("art header length : "+headerPMTCT.length);
 
       pmtctpos=3;
      
//      MAIN QUERY HERE ---------------------------------------------
    String getPMTCTData="SELECT subpartnera.SubPartnerNom,district.DistrictNom,county.County,"
            + "subpartnera.CentreSanteId,ART_Support,PMTCT_Support,"
            + "SUM(HV0308),SUM(HV0309),SUM(HV0310),SUM(HV0311),SUM(HV0312),"
            + "SUM(HV0320),SUM(HV0321),SUM(HV0322),SUM(HV0323),SUM(HV0324),"
            + "subpartnera.SubPartnerID,"
            + "SUM(HV0205),SUM(HV0209),SUM(HV0210),SUM(HV0216),SUM(HV0217),"
            + "SUM(HV0224),SUM(HV0225),SUM(HV0227),SUM(HV0229),SUM(HV0230),SUM(HV0231),SUM(HV0232),"
            + "SUM(HV0301),SUM(HV0206),SUM(HV0207),SUM(HV0208) "
            + " FROM moh731 JOIN subpartnera "
            + "ON moh731.SubPartnerID=subpartnera.SubPartnerID "
            + "JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID"
            + " WHERE "
    + " "+facilityIds+" "+duration+" && (subpartnera.PMTCT=1 || ART=1) "
            + "GROUP BY moh731.SubPartnerID " ;
     System.out.println("new : "+getPMTCTData);
    conn.rs=conn.st.executeQuery(getPMTCTData);
    while(conn.rs.next()){
       
        PMTCT_FO_I_N=PMTCT_FO_I_D=PMTCT_FO_I_LINKED=PMTCT_FO_I_NOT_LINKED=PMTCT_FO_I_UNKNOWN=
        PMTCT_FO_U_NOT_BREASTFEEDING=PMTCT_FO_U_STILL_BREASTFEEDING=PMTCT_FO_U_BREASTFEEDING_UNKNOWN=
        PMTCT_FO_OTHER_INCARE=PMTCT_FO_OTHER_NOFOLLOWUP=PMTCT_FO_DIED=PMTCT_FO_TRANSFERRED=0.0;
 PMTCT_ARV_N=PMTCT_ARV_D=PMTCT_ARV_LIFELONGART_NEW=PMTCT_ARV_LIFELONGART_EXISTING=
        PMTCT_ARV_MATERNAL_TRIPLEDRUG_ARV=PMTCT_ARV_MATERNAL_AZT=PMTCT_ARV_SINGLEDOSE=0.0;
 PMTCT_EID_N=PMTCT_EID_VIRO_2MONTHS=PMTCT_EID_VIRO_2_12MONTHS=
        PMTCT_EID_P_VIRO_2MONTHS=PMTCT_EID_P_VIRO_2_12MONTHS=0.0;
 PMTCT_STATN_N=PMTCT_STATN_KNOWNPOSTIVE=PMTCTN_STAT_NEWPOSTIVE=0.0;
 PMTCT_STATD_D=PMTCT_STATD_LESS15=PMTCT_STATD_15_19=PMTCT_STATD_20_24=PMTCT_STATD_25=0.0;
PMTCT_CTX=0.0;  
        
     facilityName=conn.rs.getString(1);
      districtName=conn.rs.getString(2);
      countyName=conn.rs.getString(3);
      mflcode=conn.rs.getString(4);
      PMTCTSupport=conn.rs.getString(6);
        
        HV0205=conn.rs.getInt(18);
        HV0209=conn.rs.getInt(19);
        HV0210=conn.rs.getInt(20);
        HV0216=conn.rs.getInt(21);
        HV0217=conn.rs.getInt(22);
        HV0224=conn.rs.getInt(23);
        HV0225=conn.rs.getInt(24);
        HV0227=conn.rs.getInt(25);
        HV0229=conn.rs.getInt(26);
        HV0230=conn.rs.getInt(27);
        HV0231=conn.rs.getInt(28);
        HV0232=conn.rs.getInt(29);
        HV0301=conn.rs.getInt(30);
        HV0206=conn.rs.getInt(31);
        HV0207=conn.rs.getInt(32);
        HV0208=conn.rs.getInt(33);
//      PMTCT_FO_I_N=(double) HV0210;
//      PMTCT_FO_I_D=(double)Math.round((1.03*HV0210));
  
//        PMTCT_FO===================================================================================================
        
        
//        PMTCT_ARV===================================================================================================
        
        
      PMTCT_ARV_N=(double) HV0217;
      PMTCT_ARV_D=(double) HV0209;
      PMTCT_ARV_LIFELONGART_NEW=(double)Math.round((0.75*HV0217));
      PMTCT_ARV_LIFELONGART_EXISTING=(double)Math.round((0.25*HV0217));
      
      double normalizer=PMTCT_ARV_LIFELONGART_NEW+PMTCT_ARV_LIFELONGART_EXISTING;
      int pmtctnum=0;
      while(HV0217>normalizer){
          if(pmtctnum<3){
           PMTCT_ARV_LIFELONGART_NEW++;   
          }
          else{
          PMTCT_ARV_LIFELONGART_EXISTING++;    
          }
          if(pmtctnum==3){pmtctnum=0;}
          normalizer++;
         pmtctnum++; 
      }
      
      normalizer=PMTCT_ARV_LIFELONGART_NEW+PMTCT_ARV_LIFELONGART_EXISTING;
       pmtctnum=0;
      while(normalizer>HV0217){
          if(pmtctnum<3){
           PMTCT_ARV_LIFELONGART_NEW--;   
          }
          else{
          PMTCT_ARV_LIFELONGART_EXISTING--;    
          }
          if(pmtctnum==3){pmtctnum=0;}
          normalizer--;
         pmtctnum++; 
      }
      
      PMTCT_ARV_MATERNAL_TRIPLEDRUG_ARV=0.0;
      PMTCT_ARV_MATERNAL_AZT=0.0;
      PMTCT_ARV_SINGLEDOSE=0.0;
      
//      PMTCT_EID===================================================================================================
 PMTCT_EID_N=(double)(HV0224+HV0225+HV0227);     
 PMTCT_EID_VIRO_2MONTHS=(double) (HV0224);    
 PMTCT_EID_VIRO_2_12MONTHS=(double) (HV0225+HV0227);     
 PMTCT_EID_P_VIRO_2MONTHS=(double)  (HV0229);    
 PMTCT_EID_P_VIRO_2_12MONTHS=(double) (HV0230+HV0231);     
  
//  PMTCT_STAT NUMERATOR======================================================================================================
      
 PMTCT_STATN_N=(double)HV0210;     
 PMTCT_STATN_KNOWNPOSTIVE=(double) HV0205;      
 PMTCTN_STAT_NEWPOSTIVE=(double) (HV0206+HV0207+HV0208);      
 PMTCT_STATD_D=(double) Math.round((1.03*HV0210));       
  
// PMTCT_CTX=====================================================================================================
 
         PMTCT_CTX=(double) HV0301; //Mo clarification whether to um or take most recent
         
         
//         String dataPMTCT []=(countyName+","+districtName+","+facilityName+","+mflcode+","+PMTCTSupport+","+PMTCT_FO_I_N+","
//           + ""+PMTCT_FO_I_D+","+PMTCT_FO_I_LINKED+","+PMTCT_FO_I_NOT_LINKED+","+PMTCT_FO_I_UNKNOWN+","
//           + ""+PMTCT_FO_U_NOT_BREASTFEEDING+","+PMTCT_FO_U_STILL_BREASTFEEDING+","+PMTCT_FO_U_BREASTFEEDING_UNKNOWN+","
//           +PMTCT_FO_OTHER_INCARE+","+PMTCT_FO_OTHER_NOFOLLOWUP+","+PMTCT_FO_DIED+","+PMTCT_FO_TRANSFERRED+","
//           + ""+PMTCT_ARV_N+","+PMTCT_ARV_D+","+PMTCT_ARV_LIFELONGART_NEW+","+PMTCT_ARV_LIFELONGART_EXISTING+","
//           +PMTCT_ARV_MATERNAL_TRIPLEDRUG_ARV+","+PMTCT_ARV_MATERNAL_AZT+","
//           + ""+PMTCT_ARV_SINGLEDOSE+","+PMTCT_EID_N+","+PMTCT_EID_VIRO_2MONTHS+","+PMTCT_EID_VIRO_2_12MONTHS+","
//           +PMTCT_EID_P_VIRO_2MONTHS+","+PMTCT_EID_P_VIRO_2_12MONTHS+","
//           + ""+PMTCT_STATN_N+","+PMTCT_STATN_KNOWNPOSTIVE+","+PMTCTN_STAT_NEWPOSTIVE+","
//           + ""+PMTCT_STATD_D+","+PMTCT_CTX).split(","); 
         
       if(PMTCTSupport!=null){
//        HAVE FORMULAS HERE AND THE OUTPUT FOR PMTCT   
   String dataPMTCT []=(countyName+","+districtName+","+facilityName+","+mflcode+","+PMTCTSupport+",,"
           + " ,,,,,,,,,,,"
           + ""+PMTCT_ARV_N+","+PMTCT_ARV_D+","+PMTCT_ARV_LIFELONGART_NEW+","+PMTCT_ARV_LIFELONGART_EXISTING+","
           +PMTCT_ARV_MATERNAL_TRIPLEDRUG_ARV+","+PMTCT_ARV_MATERNAL_AZT+","
           + ""+PMTCT_ARV_SINGLEDOSE+","+PMTCT_EID_N+","+PMTCT_EID_VIRO_2MONTHS+","+PMTCT_EID_VIRO_2_12MONTHS+","
           +PMTCT_EID_P_VIRO_2MONTHS+","+PMTCT_EID_P_VIRO_2_12MONTHS+","
           + ""+PMTCT_STATN_N+","+PMTCT_STATN_KNOWNPOSTIVE+","+PMTCTN_STAT_NEWPOSTIVE+","
           + ""+PMTCT_STATD_D+",").split(",");   
           
      HSSFRow rw3shetPMTCT=shetPMTCT.createRow(pmtctpos); 
       rw3shetPMTCT.setHeightInPoints(25);
       for(int positionPMTCT=0;positionPMTCT<dataPMTCT.length;positionPMTCT++){
       String value=dataPMTCT[positionPMTCT];
           c11=rw3shetPMTCT.createCell(positionPMTCT);
        if(positionPMTCT>16 &&positionPMTCT<(dataPMTCT.length)){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
          if(positionPMTCT==5 || positionPMTCT==6 || positionPMTCT==17 || positionPMTCT==18 || positionPMTCT==24 || positionPMTCT==29 || positionPMTCT==32 || positionPMTCT==33){ c11.setCellStyle(styleHeader);}
              }      
        pmtctpos++;   
       } 
        
    }
       
       
       
       
      
      if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
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
response.setHeader("Content-Disposition", "attachment; filename=PMTCT_DATIM_SPECIAL_REPORT_CREATED_ON_"+createdOn.trim()+".xls");
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
        Logger.getLogger(datimPMTCT.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(datimPMTCT.class.getName()).log(Level.SEVERE, null, ex);
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
