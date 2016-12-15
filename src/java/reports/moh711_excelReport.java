/*
 * To change this template, choose Tools | Templates
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
import org.apache.poi.ss.util.CellRangeAddress;
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
 * @author Maureen
 */
public class moh711_excelReport extends HttpServlet {

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
    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        String data,id;
String facilityId;
String county,district,facilityname,mflcode;       
  String isValidated,validity;
int maxYearMonth;
String subcountyid,facility;
String reportType,duration,reportDuration,quarter,semi_annual;
int year,prevYear,month;
String header,facilityName,countyName,districtName,monthName;
int pos=0;
int newpos=0;
month=0;
String FPMicrolutN,FPMicrolutR,FPMicrolutT,FPMicrogynonN,FPMicrogynonR,FPMicrogynonT,FPINJECTIONSN,FPINJECTIONSR,
FPINJECTIONST,FPIUCDN,FPIUCDR,FPIUCDT,FPIMPLANTSN,FPIMPLANTSR,FPIMPLANTST,FPBTLN,FPBTLR,FPBTLT,FPVasectomyN,FPVasectomyR;
String FPVasectomyT,FPCONDOMSN,FPCONDOMSR,FPCONDOMST,FPOTHERN,FPOTHERR,FPOTHERT,FPCLIENTSN,FPCLIENTSR,FPCLIENTST,FPIUCDRemoval,
FPIMPLANTSRemoval,PMCTA_1stVisit_ANC,PMCTA_ReVisit_ANC,PMCTANCClientsT,PMCTHB7,PMCTIPT1,PMCTIPT2,PMCTANCClients4,PMCTITN,MATNormalDelivery,MATCSection;
String MATBreech,MATAssistedVag,MATDeliveryT,MATLiveBirth,MATStillBirth,MATWeight2500,MATPreTerm,MATDischargealive,MATReferral,MATNeoNatalD,
MATMaternalD,MATAPHAlive,MATAPHDead,MATPPHAlive,MATPPHDead,MATEclampAlive,MATEclampDead,MATRupUtAlive,MATRupUtDead,MATObstrLaborAlive,MATObstrLaborDead,MATSepsisAlive,MATSepsisDead;
String VCTClient_Couns_CM,VCTClient_Couns_CF,VCTClient_Couns_AM,VCTClient_Couns_AF,VCTClient_Couns_TOT,VCTClient_Tested_CM,VCTClient_Tested_CF,VCTClient_Tested_AM,VCTClient_Tested_AF
,VCTClient_Tested_TOT,VCTClient_HIV_CM,VCTClient_HIV_CF,VCTClient_HIV_AM,VCTClient_HIV_AF,VCTClient_HIV_TOT,VCTPartner_Couns_TOT,VCTPartner_Tested_TOT,VCTPartner_HIV_TOT,VCTPartner_Disc_TOT;
String DTCA_Couns_In_CM,DTCA_Couns_In_CF,DTCA_Couns_In_AM,DTCA_Couns_In_AF,DTCA_Couns_In_Tot,DTCA_Couns_Out_CM,DTCA_Couns_Out_CF,DTCA_Couns_Out_AM,DTCA_Couns_Out_AF,DTCA_Couns_Out_Tot,DTCB_Test_In_CM,DTCB_Test_In_CF
,DTCB_Test_In_AM,DTCB_Test_In_AF,DTCB_Test_In_Tot,DTCB_Test_Out_CM,DTCB_Test_Out_CF,DTCB_Test_Out_AM,DTCB_Test_Out_AF,DTCB_Test_Out_Tot,DTCC_HIV_In_CM,DTCC_HIV_In_CF,DTCC_HIV_In_AM
,DTCC_HIV_In_AF, DTCC_HIV_In_Tot,DTCC_HIV_Out_CM,DTCC_HIV_Out_CF,DTCC_HIV_Out_AM,DTCC_HIV_Out_AF,DTCC_HIV_Out_Tot,Userid;


String FamilyPlanninng, pmct,maternity,vct,dtc;
      header="";  
      duration="";
//        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
             reportType=request.getParameter("reportType");
        year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
        
//        reportType="2";
//        year=2015;
//        reportDuration="3";
        
        
        
        
        //--------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------
        //added later to accomodate the years
           String subpartnerid="SubPartnerID";
           String subpartnera="subpartnera";
           
           
           int monthint=0;
           int yearint=0;
           yearint=year;
         
      
        
        
        
        
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================
//        startPMTCT=startART=startPEP=noPMTCT=noART=noPEP=0;
        if(reportDuration.equals("1")){
           
            //solve subpartner table and facil_id first            
        if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
                         }
       else if(yearint==2015) {
           //this should be skipped since it picks both facil tables. It has been disabled at the interface position
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
                              }
            
         duration=" moh711.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
         
         
         
                                      }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
           //oct-mar 
           
           if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
       }
	   else if(yearint==2015){
               //for oct-mar, use old database list
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";	   
		   
	   }
           
           
       duration=" moh711.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
       
       
                                  }
           else{
           //apr-sep
           
              if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
       }
	   else if(yearint==2015){
	subpartnerid="SubPartnerID";
        subpartnera="subpartnera";	   
		   
	   }
           
       duration=" moh711.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
               }
                                            }
        
        else if(reportDuration.equals("3")){
            //quarterly
          quarter=request.getParameter("quarter");
            //specify subparter table and facil id first
            
              //oct-mar
          if(quarter.equals("1")||quarter.equals("2")){
	   if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
       }
	   else if(yearint==2015){
		   //for oct-mar, use old database list
	 subpartnerid="SP_ID";
       subpartnera="subpartnera2014";  
		   
	   }
            
          }
          else if(quarter.equals("3")||quarter.equals("4")){
          //apr-sep
          
               //apr-sep
           
              if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) 
       {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
       }
	   else if(yearint==2015){
	     subpartnerid="SubPartnerID";
        subpartnera="subpartnera";	   
		   
	   }
              
          }
            
            
            
       String startMonth,endMonth;
     
//       quarter="3";
       String getMonths="SELECT months FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" moh711.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
      }
      else{
     duration=" moh711.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
      }
        }
        }  
        
      else if(reportDuration.equals("4")){
     month=Integer.parseInt(request.getParameter("month"));
     
     //deal with subpartnertable and facilid first
      
        monthint=month;
       
        
       if(yearint==2015){
           
       if(monthint==10|| monthint==11 || monthint==12 || monthint==1||monthint==2|| monthint==3){
       //here use a different subpartner id
        subpartnerid="SP_ID";   
       subpartnera="subpartnera2014";
                                                                                                }
       else  {
           
       subpartnerid="SubPartnerID";
       subpartnera="subpartnera";
       
             }
           
                        }
       else  if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
       }
       //---------------------------------------------------------------------------------------
       //---------------------------------------------------------------------------------------
      
     
     
     
     
//     month=5;
     if(month>=10){
     duration=" moh711.yearmonth="+prevYear+""+month;    
     }
     else{
  duration=" moh711.yearmonth="+year+"0"+month;  
     }
      }
      else{
     duration="";     
      }
           
     
//     GET FACILITIES TO OUTPUT.................................
    mflcode=countyName=districtName=facilityName="";    
        
      if(reportType.equals("1")){  
    facility=""; 
    
      facilityName="ALL APHIAPLUS SUPPORTED HEALTH FACILITIES";
      districtName="ALL";
      countyName="ALL COUNTIES";
      mflcode="NONE";
    }
      
      else{
  facilityId=request.getParameter("facility");
  String spid="";
  //get the correct facil id based on the year id...
//  facilityId="403";
  facility="moh711.SubPartnerID='"+facilityId+"' &&";    
  //this part should remain constant since the facility parameters being passed are using the subpartnera table
  String getName="SELECT subpartnera.SubPartnerNom,district.DistrictNom,county.County,subpartnera.CentreSanteId, SP_ID FROM subpartnera "
          + "JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID WHERE subpartnera.SubPartnerID='"+facilityId+"'";
  
  
   System.out.println("getname__"+getName);
  conn.rs=conn.st.executeQuery(getName);
  
         
  if(conn.rs.next()==true){
      facilityName=conn.rs.getString(1);
      districtName=conn.rs.getString(2);
      countyName=conn.rs.getString(3);
      mflcode=conn.rs.getString(4);
      spid= conn.rs.getString(5);
      
  }
  //if its subpartner2014, use SP_ID not subpartnerid
  
          System.out.println("____SubPartnerID::"+subpartnerid);
  if(subpartnerid.equalsIgnoreCase("SP_ID"))
  {
      
  facility="moh711.SubPartnerID='"+spid+"' &&";
  
  }
  
  
  
     }
     
    header+="</table>";  
      
    String getMaxYearMonth="SELECT MAX(yearmonth) FROM moh711 WHERE "+facility+" "+duration ;
    conn.rs=conn.st.executeQuery(getMaxYearMonth);
    if(conn.rs.next()==true){
        maxYearMonth=conn.rs.getInt(1);
    }
   System.out.println("max year month : "+maxYearMonth);
        
   
    //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
   XSSFWorkbook wb=new XSSFWorkbook();
  XSSFSheet shet1=wb.createSheet("Family Planning");
  XSSFSheet shet2=wb.createSheet("MCH-ANC PMTCT");
  XSSFSheet shet3=wb.createSheet("MATERNITY SAFE DELIVERIES");
  XSSFSheet shet4=wb.createSheet("VCT");
  XSSFSheet shet5=wb.createSheet("DTC");
  XSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)18);
    font.setFontName("Arial Black");
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
    stborder.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    stborder.setWrapText(true);
    
    XSSFCellStyle stylex = wb.createCellStyle();
stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylex.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    
XSSFFont fontx = wb.createFont();
fontx.setColor(HSSFColor.DARK_BLUE.index);
stylex.setFont(fontx);
stylex.setWrapText(true);

   XSSFCellStyle styleHeader = wb.createCellStyle();
styleHeader.setFillForegroundColor(HSSFColor.LIME.index);
styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleHeader.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    
XSSFFont fontHeader = wb.createFont();
fontHeader.setColor(HSSFColor.DARK_BLUE.index);
styleHeader.setFont(fontHeader);
styleHeader.setWrapText(true);
  
 shet1.setColumnWidth(0, 8000);
 shet2.setColumnWidth(0, 8000);  
 shet3.setColumnWidth(0, 8000);
 shet4.setColumnWidth(0, 8000);
 shet5.setColumnWidth(0, 8000);


 shet1.setColumnWidth(1, 8000);
 shet2.setColumnWidth(1, 8000);  
 shet3.setColumnWidth(1, 8000);
 shet4.setColumnWidth(1, 8000);
 shet5.setColumnWidth(1, 8000);

   
 shet1.setColumnWidth(2, 4000);
 shet2.setColumnWidth(2, 4000);  
 shet3.setColumnWidth(2, 4000);
 shet4.setColumnWidth(2, 4000);
 shet5.setColumnWidth(2, 4000);
 
 shet1.setColumnWidth(3, 3500);
 shet2.setColumnWidth(3, 3500);  
 shet3.setColumnWidth(3, 3500);
 shet4.setColumnWidth(3, 3500);
 shet5.setColumnWidth(3, 3500);
 
 shet1.setColumnWidth(4, 3500);
 shet2.setColumnWidth(4, 3500);  
 shet3.setColumnWidth(4, 3500);
 shet4.setColumnWidth(4, 3500);
 shet5.setColumnWidth(4, 3500);
  
 shet1.setColumnWidth(5, 3500);
 shet2.setColumnWidth(5, 3500);  
 shet3.setColumnWidth(5, 3500);
 shet4.setColumnWidth(5, 3500);
 shet5.setColumnWidth(5, 3500);
  
  String  headers="COUNTY,SUB COUNTY,FACILITY NAME,MFL CODE";
   
    String arrayHeader []=headers.split(",");
    int headerno=0;int valueNo=0; int arrayCounter=0;
   
//   XSSFRow rw0S1=shet1.createRow(0);
   XSSFRow rw1S1=shet1.createRow(0);  
   
//    XSSFRow rw0S2=shet2.createRow(0);
   XSSFRow rw1S2=shet2.createRow(0);
    
//    XSSFRow rw0S3=shet3.createRow(0);
   XSSFRow rw1S3=shet3.createRow(0);

  
    String getMonth="SELECT name FROM month WHERE id='"+month+"'";
    conn.rs=conn.st.executeQuery(getMonth);
    if(conn.rs.next()==true){
        monthName=conn.rs.getString(1);
    }
     
     
   
//  counterPMTCT=counterART=counterPEP=3; 
              
  // FAMILY PLANNING            
FPMicrolutN=FPMicrolutR=FPMicrolutT=FPMicrogynonN=FPMicrogynonR=FPMicrogynonT=FPINJECTIONSN=FPINJECTIONSR="";
FPINJECTIONST=FPIUCDN=FPIUCDR=FPIUCDT=FPIMPLANTSN=FPIMPLANTSR=FPIMPLANTST=FPBTLN=FPBTLR=FPBTLT=FPVasectomyN=FPVasectomyR="";
FPVasectomyT=FPCONDOMSN=FPCONDOMSR=FPCONDOMST=FPOTHERN=FPOTHERR=FPOTHERT=FPCLIENTSN=FPCLIENTSR=FPCLIENTST=FPIUCDRemoval=
FPIMPLANTSRemoval="";
//PMCT VARIABLES
 PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTHB7=PMCTIPT1=PMCTIPT2=PMCTANCClients4=PMCTITN=MATNormalDelivery=MATCSection="";

 MATBreech=MATAssistedVag=MATDeliveryT=MATLiveBirth=MATStillBirth=MATWeight2500=MATPreTerm=MATDischargealive=MATReferral=MATNeoNatalD=
MATMaternalD=MATAPHAlive=MATAPHDead=MATPPHAlive=MATPPHDead=MATEclampAlive=MATEclampDead=MATRupUtAlive=MATRupUtDead=MATObstrLaborAlive=MATObstrLaborDead=MATSepsisAlive=MATSepsisDead="";
 
 VCTClient_Couns_CM=VCTClient_Couns_CF=VCTClient_Couns_AM=VCTClient_Couns_AF=VCTClient_Couns_TOT=VCTClient_Tested_CM=VCTClient_Tested_CF=VCTClient_Tested_AM=VCTClient_Tested_AF
=VCTClient_Tested_TOT=VCTClient_HIV_CM=VCTClient_HIV_CF=VCTClient_HIV_AM=VCTClient_HIV_AF=VCTClient_HIV_TOT=VCTPartner_Couns_TOT=VCTPartner_Tested_TOT=VCTPartner_HIV_TOT=VCTPartner_Disc_TOT="";
 
 DTCA_Couns_In_CM=DTCA_Couns_In_CF=DTCA_Couns_In_AM=DTCA_Couns_In_AF=DTCA_Couns_In_Tot=DTCA_Couns_Out_CM=DTCA_Couns_Out_CF=DTCA_Couns_Out_AM=DTCA_Couns_Out_AF=DTCA_Couns_Out_Tot=DTCB_Test_In_CM=DTCB_Test_In_CF
=DTCB_Test_In_AM=DTCB_Test_In_AF=DTCB_Test_In_Tot=DTCB_Test_Out_CM=DTCB_Test_Out_CF=DTCB_Test_Out_AM=DTCB_Test_Out_AF=DTCB_Test_Out_Tot=DTCC_HIV_In_CM=DTCC_HIV_In_CF=DTCC_HIV_In_AM
=DTCC_HIV_In_AF= DTCC_HIV_In_Tot=DTCC_HIV_Out_CM=DTCC_HIV_Out_CF=DTCC_HIV_Out_AM=DTCC_HIV_Out_AF=DTCC_HIV_Out_Tot=Userid="";

           
          String checker="SELECT sum(FPMicrolutN),"
            + " sum(FPMicrolutR), sum(FPMicrolutT), sum(FPMicrogynonN), sum(FPMicrogynonR), sum(FPMicrogynonT),"
            + " sum(FPINJECTIONSN), sum(FPINJECTIONSR), sum(FPINJECTIONST), sum(FPIUCDN), sum(FPIUCDR),sum(FPIUCDT),sum(FPIMPLANTSN),sum(FPIMPLANTSR), "
            + " sum(FPIMPLANTST), sum(FPBTLN), sum(FPBTLR),sum(FPBTLT),sum(FPVasectomyN),sum(FPVasectomyR),sum(FPVasectomyT),sum(FPCONDOMSN),sum(FPCONDOMSR), "
            + "sum(FPCONDOMST), sum(FPOTHERN), sum(FPOTHERR), sum(FPOTHERT), sum(FPCLIENTSN), sum(FPCLIENTSR), sum(FPCLIENTST), sum(FPIUCDRemoval),"
            + "sum(FPIMPLANTSRemoval), sum(PMCTA_1stVisit_ANC),sum(PMCTA_ReVisit_ANC), sum(PMCTANCClientsT),sum(PMCTHB7), sum(PMCTIPT1),"
            + "sum(PMCTIPT2), sum(PMCTANCClients4),sum(PMCTITN), sum(MATNormalDelivery), sum(MATCSection), sum(MATBreech),sum(MATAssistedVag), "
            + "sum(MATDeliveryT),sum(MATLiveBirth), sum(MATStillBirth), sum(MATWeight2500), sum(MATPreTerm), sum(MATDischargealive), sum(MATReferral), sum(MATNeoNatalD),"
            + " sum(MATMaternalD), sum(MATAPHAlive), sum(MATAPHDead), sum(MATPPHAlive), sum(MATPPHDead), sum(MATEclampAlive),sum( MATEclampDead),"
            + " sum(MATRupUtAlive), sum(MATRupUtDead), sum(MATObstrLaborAlive), sum(MATObstrLaborDead), sum(MATSepsisAlive), sum(MATSepsisDead), "
            + "sum(VCTClient_Couns_CM), sum(VCTClient_Couns_CF), sum(VCTClient_Couns_AM), sum(VCTClient_Couns_AF), "
            + "sum(VCTClient_Couns_TOT), sum(VCTClient_Tested_CM), sum(VCTClient_Tested_CF), sum(VCTClient_Tested_AM),"
            + "sum( VCTClient_Tested_AF), sum(VCTClient_Tested_TOT), sum(VCTClient_HIV_CM), sum(VCTClient_HIV_CF), sum(VCTClient_HIV_AM),sum(VCTClient_HIV_AF),"
            + "sum( VCTClient_HIV_TOT), sum(VCTPartner_Couns_TOT), sum(VCTPartner_Tested_TOT), sum(VCTPartner_HIV_TOT),sum(VCTPartner_Disc_TOT),"
            + " sum(DTCA_Couns_In_CM), sum(DTCA_Couns_In_CF), sum(DTCA_Couns_In_AM), sum(DTCA_Couns_In_AF), sum(DTCA_Couns_In_Tot), sum(DTCA_Couns_Out_CM), "
            + "sum(DTCA_Couns_Out_CF), sum(DTCA_Couns_Out_AM), sum(DTCA_Couns_Out_AF), sum(DTCA_Couns_Out_Tot),sum(DTCB_Test_In_CM), sum(DTCB_Test_In_CF),"
            + " sum(DTCB_Test_In_AM), sum(DTCB_Test_In_AF), sum(DTCB_Test_In_Tot), sum(DTCB_Test_Out_CM), sum(DTCB_Test_Out_CF), sum(DTCB_Test_Out_AM),"
            + " sum(DTCB_Test_Out_AF), sum(DTCB_Test_Out_Tot),sum(DTCC_HIV_In_CM), sum(DTCC_HIV_In_CF), sum(DTCC_HIV_In_AM), sum(DTCC_HIV_In_AF),"
            + " sum(DTCC_HIV_In_Tot),sum( DTCC_HIV_Out_CM), sum(DTCC_HIV_Out_CF), sum(DTCC_HIV_Out_AM),sum(DTCC_HIV_Out_AF),sum(DTCC_HIV_Out_Tot) "
            + "FROM moh711 WHERE "+facility+" "+duration ;
          System.out.println("@@@"+checker);
          
          conn.rs=conn.st.executeQuery(checker);
      

if(conn.rs.next()==true){
        
          String headerValues=countyName+","+districtName+","+facilityName+","+mflcode;
          String arrayValues[]=headerValues.split(",");
          String headerValue="";  
//   CREATE HEADERS
     for(String headername:arrayHeader){
   headerValue=arrayValues[arrayCounter];
   XSSFRow rw1S10=shet1.createRow(pos);  
   XSSFRow rw1S20=shet2.createRow(pos);
   XSSFRow rw1S30=shet3.createRow(pos);
   XSSFRow rw1S40=shet4.createRow(pos);
   XSSFRow rw1S50=shet5.createRow(pos);
  
    
    XSSFCell  S1cell=rw1S10.createCell(0);
    S1cell.setCellValue(headername+" : "+headerValue);
    S1cell.setCellStyle(stylex);
    
    XSSFCell  S1cellX=rw1S10.createCell(1);
    S1cellX.setCellValue("");
    S1cellX.setCellStyle(stylex);
    
    S1cellX=rw1S10.createCell(2);
    S1cellX.setCellValue("");
    S1cellX.setCellStyle(stylex);
    
    S1cellX=rw1S10.createCell(3);
    S1cellX.setCellValue("");
    S1cellX.setCellStyle(stylex);
   
    XSSFCell  S2cell=rw1S20.createCell(0);
    S2cell.setCellValue(headername +" : "+headerValue);
    S2cell.setCellStyle(stylex);
    
    XSSFCell  S2cellX=rw1S20.createCell(1);
    S2cellX.setCellValue("");
    S2cellX.setCellStyle(stylex);
    
    S2cellX=rw1S20.createCell(2);
    S2cellX.setCellValue("");
    S2cellX.setCellStyle(stylex);
    
    S2cellX=rw1S20.createCell(3);
    S2cellX.setCellValue("");
    S2cellX.setCellStyle(stylex);
    
    
    XSSFCell  S3cell=rw1S30.createCell(0);
    S3cell.setCellValue(headername +" : "+headerValue);
    S3cell.setCellStyle(stylex);
    
    XSSFCell  S3cellX=rw1S30.createCell(1);
    S3cellX.setCellValue("");
    S3cellX.setCellStyle(stylex);
    
    S3cellX=rw1S30.createCell(2);
    S3cellX.setCellValue("");
    S3cellX.setCellStyle(stylex);
    
    S3cellX=rw1S40.createCell(3);
    S3cellX.setCellValue("");
    S3cellX.setCellStyle(stylex);
    
    XSSFCell  S4cell=rw1S40.createCell(0);
    S4cell.setCellValue(headername +" : "+headerValue);
    S4cell.setCellStyle(stylex);
    
    XSSFCell  S4cellX=rw1S40.createCell(1);
    S4cellX.setCellValue("");
    S4cellX.setCellStyle(stylex);
    
    S4cellX=rw1S40.createCell(2);
    S4cellX.setCellValue("");
    S4cellX.setCellStyle(stylex);
    
    S4cellX=rw1S40.createCell(3);
    S4cellX.setCellValue("");
    S4cellX.setCellStyle(stylex);
    
    
    XSSFCell  S5cell=rw1S50.createCell(0);
    S5cell.setCellValue(headername +" : "+headerValue);
    S5cell.setCellStyle(stylex);
    
    XSSFCell  S5cellX=rw1S50.createCell(1);
    S5cellX.setCellValue("");
    S5cellX.setCellStyle(stylex);
    
    S5cellX=rw1S50.createCell(2);
    S5cellX.setCellValue("");
    S5cellX.setCellStyle(stylex);
    
    S5cellX=rw1S50.createCell(3);
    S5cellX.setCellValue("");
    S5cellX.setCellStyle(stylex);
   
      arrayCounter++;
     
      shet1.addMergedRegion(new CellRangeAddress(pos,pos,0,3));
      shet2.addMergedRegion(new CellRangeAddress(pos,pos,0,3));
      shet3.addMergedRegion(new CellRangeAddress(pos,pos,0,3));
      shet4.addMergedRegion(new CellRangeAddress(pos,pos,0,3));
      shet5.addMergedRegion(new CellRangeAddress(pos,pos,0,3));
      pos++;
     } 
      
   
      if(conn.rs.getString(1)!=null){FPMicrolutN=conn.rs.getString(1);}
          else if (FPMicrolutN==null){FPMicrolutN="";}
    if(conn.rs.getString(2)!=null){       
  FPMicrolutR=conn.rs.getString(2);}
    else if(FPMicrolutR==null){FPMicrolutR="";}
  
  if(conn.rs.getString(3)!=null){FPMicrolutT=conn.rs.getString(3);}else{FPMicrolutT="";}
  if(conn.rs.getString(4)!=null){FPMicrogynonN=conn.rs.getString(4);}else{FPMicrogynonN="";}
  if(conn.rs.getString(5)!=null){FPMicrogynonR=conn.rs.getString(5);}else{FPMicrogynonR="";}
  if(conn.rs.getString(6)!=null){FPMicrogynonT=conn.rs.getString(6);}else{FPMicrogynonT="";}
  if(conn.rs.getString(7)!=null){FPINJECTIONSN=conn.rs.getString(7);}else{FPINJECTIONSN="";}
  if(conn.rs.getString(8)!=null){FPINJECTIONSR=conn.rs.getString(8);}else{FPINJECTIONSR="";}
  if(conn.rs.getString(9)!=null){FPINJECTIONST=conn.rs.getString(9);}else{FPINJECTIONST="";}
  if(conn.rs.getString(10)!=null){FPIUCDN=conn.rs.getString(10);}else{FPIUCDN="";}
  if(conn.rs.getString(11)!=null){FPIUCDR=conn.rs.getString(11);}else{FPIUCDR="";}
  if(conn.rs.getString(12)!=null){FPIUCDT=conn.rs.getString(12);}else{FPIUCDT="";}
  if(conn.rs.getString(13)!=null){FPIMPLANTSN=conn.rs.getString(13);}else{FPIMPLANTSN="";}
  if(conn.rs.getString(14)!=null){FPIMPLANTSR=conn.rs.getString(14);}else{FPIMPLANTSR="";}
  if(conn.rs.getString(15)!=null){FPIMPLANTST=conn.rs.getString(15);}else{FPIMPLANTST="";}
  if(conn.rs.getString(16)!=null){FPBTLN=conn.rs.getString(16);}else{FPBTLN="";}
  if(conn.rs.getString(17)!=null){FPBTLR=conn.rs.getString(17);}else{FPBTLR="";}
  if(conn.rs.getString(18)!=null){FPBTLT=conn.rs.getString(18);}else{FPBTLT="";}
  if(conn.rs.getString(19)!=null){FPVasectomyN=conn.rs.getString(19);}else{FPVasectomyN="";}
  if(conn.rs.getString(20)!=null){FPVasectomyR=conn.rs.getString(20);}else{FPVasectomyR="";}
  if(conn.rs.getString(21)!=null){FPVasectomyT=conn.rs.getString(21);}else{FPVasectomyT="";}
  if(conn.rs.getString(22)!=null){FPCONDOMSN=conn.rs.getString(22);}else{FPCONDOMSN="";}
  if(conn.rs.getString(23)!=null){FPCONDOMSR=conn.rs.getString(23);}else{FPCONDOMSR="";}
  if(conn.rs.getString(24)!=null){FPCONDOMST=conn.rs.getString(24);}else{FPCONDOMST="";}
         
  if(conn.rs.getString(25)!=null){FPOTHERN=conn.rs.getString(25);}else{FPOTHERN="";}
  if(conn.rs.getString(26)!=null){FPOTHERR=conn.rs.getString(26);}else{FPOTHERR="";}
  if(conn.rs.getString(27)!=null){FPOTHERT=conn.rs.getString(27);}else{FPOTHERT="";}
         
  if(conn.rs.getString(28)!=null){FPCLIENTSN=conn.rs.getString(28);}else{
  FPCLIENTSN="";}
  if(conn.rs.getString(29)!=null){FPCLIENTSR=conn.rs.getString(29);}else{
  FPCLIENTSR="";
  }
  if(conn.rs.getString(30)!=null){FPCLIENTST=conn.rs.getString(30);}
  else{FPCLIENTST="";
  }
  if(conn.rs.getString(31)!=null){FPIUCDRemoval=conn.rs.getString(31);}else{FPIUCDRemoval="";}
  if(conn.rs.getString(32)!=null){FPIMPLANTSRemoval=conn.rs.getString(32);}else{FPIMPLANTSRemoval="";}
         System.out.println("xxxxx "+newpos+" "+pos);
//        newpos=pos+1;  
       
  
     XSSFRow rw1S10=shet1.createRow(5);  
  
  XSSFCell  rw1cell1=rw1S10.createCell(0);
    rw1cell1.setCellValue("FAMILY PLANNING");
    rw1cell1.setCellStyle(styleHeader);
    
  XSSFCell  rw1cell2=rw1S10.createCell(1);
    rw1cell2.setCellValue("");
    rw1cell2.setCellStyle(styleHeader);
    
  XSSFCell  rw1cell3=rw1S10.createCell(2);
    rw1cell3.setCellValue("NEW CLIENTS");
    rw1cell3.setCellStyle(styleHeader);
    
  XSSFCell  rw1cell4=rw1S10.createCell(3);
    rw1cell4.setCellValue("REVISITS");
    rw1cell4.setCellStyle(styleHeader);
    
  XSSFCell  rw1cell5=rw1S10.createCell(4);
    rw1cell5.setCellValue("TOTAL");
    rw1cell5.setCellStyle(styleHeader);
    
    
    
   XSSFRow rw2S10=shet1.createRow(6);    
    XSSFCell  rw2cell2=rw2S10.createCell(0);
    rw2cell2.setCellValue("PILLS ");
    rw2cell2.setCellStyle(stborder);
    
    XSSFCell  rw2cell3=rw2S10.createCell(1);
    rw2cell3.setCellValue("MICROLUT ");
    rw2cell3.setCellStyle(stborder);
    XSSFCell  rw2cell4=rw2S10.createCell(2);
    rw2cell4.setCellValue(FPMicrolutN);
    rw2cell4.setCellStyle(stborder);
    XSSFCell  rw2cell5=rw2S10.createCell(3);
    rw2cell5.setCellValue(FPMicrolutR);
    rw2cell5.setCellStyle(stborder);
    XSSFCell  rw2cell6=rw2S10.createCell(4);
    rw2cell6.setCellValue(FPMicrolutT);
    rw2cell6.setCellStyle(stborder);
    
    
    XSSFRow rw3S10=shet1.createRow(7);    
    XSSFCell  rw3cell2=rw3S10.createCell(0);
    rw3cell2.setCellValue("");
    rw3cell2.setCellStyle(stborder);
    
    XSSFCell  rw3cell3=rw3S10.createCell(1);
    rw3cell3.setCellValue("MICROGYNON ");
    rw3cell3.setCellStyle(stborder);
    
    XSSFCell  rw3cell4=rw3S10.createCell(2);
    rw3cell4.setCellValue(FPMicrogynonN);
    rw3cell4.setCellStyle(stborder);
    
    XSSFCell  rw3cell5=rw3S10.createCell(3);
    rw3cell5.setCellValue(FPMicrogynonR);
    rw3cell5.setCellStyle(stborder);
    
    XSSFCell  rw3cell6=rw3S10.createCell(4);
    rw3cell6.setCellValue(FPMicrogynonT);
    rw3cell6.setCellStyle(stborder);
    
     shet1.addMergedRegion(new CellRangeAddress(6,7,0,0)); 
     
     
    XSSFRow rw4S10=shet1.createRow(8);    
    XSSFCell  rw4cell2=rw4S10.createCell(0);
    rw4cell2.setCellValue("INJECTIONS");
    rw4cell2.setCellStyle(stborder);
    
    XSSFCell  rw4cell3=rw4S10.createCell(1);
    rw4cell3.setCellValue("INJECTIONS ");
    rw4cell3.setCellStyle(stborder);
    
    XSSFCell  rw4cell4=rw4S10.createCell(2);
    rw4cell4.setCellValue(FPINJECTIONSN);
    rw4cell4.setCellStyle(stborder);
    
    XSSFCell  rw4cell5=rw4S10.createCell(3);
    rw4cell5.setCellValue(FPINJECTIONSR);
    rw4cell5.setCellStyle(stborder);
    
    XSSFCell  rw4cell6=rw4S10.createCell(4);
    rw4cell6.setCellValue(FPINJECTIONST);
    rw4cell6.setCellStyle(stborder);
    
    
      XSSFRow rw5S10=shet1.createRow(9);    
    XSSFCell  rw5cell2=rw5S10.createCell(0);
    rw5cell2.setCellValue("I.U.C.D.");
    rw5cell2.setCellStyle(stborder);
    
    XSSFCell  rw5cell3=rw5S10.createCell(1);
    rw5cell3.setCellValue("INSERTION ");
    rw5cell3.setCellStyle(stborder);
    
    XSSFCell  rw5cell4=rw5S10.createCell(2);
    rw5cell4.setCellValue(FPIUCDN);
    rw5cell4.setCellStyle(stborder);
    
    XSSFCell  rw5cell5=rw5S10.createCell(3);
    rw5cell5.setCellValue(FPIUCDR);
    rw5cell5.setCellStyle(stborder);
    
    XSSFCell  rw5cell6=rw5S10.createCell(4);
    rw5cell6.setCellValue(FPIUCDT);
    rw5cell6.setCellStyle(stborder);
    
    
     XSSFRow rw6S10=shet1.createRow(10);    
    XSSFCell  rw6cell2=rw6S10.createCell(0);
    rw6cell2.setCellValue("IMPLANTS");
    rw6cell2.setCellStyle(stborder);
    
    XSSFCell  rw6cell3=rw6S10.createCell(1);
    rw6cell3.setCellValue("INSERTIONS ");
    rw6cell3.setCellStyle(stborder);
    
    XSSFCell  rw6cell4=rw6S10.createCell(2);
    rw6cell4.setCellValue(FPIMPLANTSN);
    rw6cell4.setCellStyle(stborder);
    
    XSSFCell  rw6cell5=rw6S10.createCell(3);
    rw6cell5.setCellValue(FPIMPLANTSR);
    rw6cell5.setCellStyle(stborder);
    
    XSSFCell  rw6cell6=rw6S10.createCell(4);
    rw6cell6.setCellValue(FPIMPLANTST);
    rw6cell6.setCellStyle(stborder);
    
    
     XSSFRow rw7S10=shet1.createRow(11);    
    XSSFCell  rw7cell2=rw7S10.createCell(0);
    rw7cell2.setCellValue("STERILIZATION");
    rw7cell2.setCellStyle(stborder);
    
    XSSFCell  rw7cell3=rw7S10.createCell(1);
    rw7cell3.setCellValue("B.T.L. ");
    rw7cell3.setCellStyle(stborder);
    
    XSSFCell  rw7cell4=rw7S10.createCell(2);
    rw7cell4.setCellValue(FPBTLN);
    rw7cell4.setCellStyle(stborder);
    
    XSSFCell  rw7cell5=rw7S10.createCell(3);
    rw7cell5.setCellValue(FPBTLR);
    rw7cell5.setCellStyle(stborder);
    
    XSSFCell  rw7cell6=rw7S10.createCell(4);
    rw7cell6.setCellValue(FPBTLT);
    rw7cell6.setCellStyle(stborder);
    
     XSSFRow rw8S10=shet1.createRow(12);    
    XSSFCell  rw8cell2=rw8S10.createCell(0);
    rw8cell2.setCellValue("STERILIZATION");
    rw8cell2.setCellStyle(stborder);
    
    XSSFCell  rw8cell3=rw8S10.createCell(1);
    rw8cell3.setCellValue("VASECTOMY ");
    rw8cell3.setCellStyle(stborder);
    
    XSSFCell  rw8cell4=rw8S10.createCell(2);
    rw8cell4.setCellValue(FPVasectomyN);
    rw8cell4.setCellStyle(stborder);
    
    XSSFCell  rw8cell5=rw8S10.createCell(3);
    rw8cell5.setCellValue(FPVasectomyR);
    rw8cell5.setCellStyle(stborder);
    
    XSSFCell  rw8cell6=rw8S10.createCell(4);
    rw8cell6.setCellValue(FPVasectomyT);
    rw8cell6.setCellStyle(stborder);
   shet1.addMergedRegion(new CellRangeAddress(11,12,0,0));  
    
     XSSFRow rw9S10=shet1.createRow(13);    
    XSSFCell  rw9cell2=rw9S10.createCell(0);
    rw9cell2.setCellValue("CONDOMS");
    rw9cell2.setCellStyle(stborder);
    
    XSSFCell  rw9cell3=rw9S10.createCell(1);
    rw9cell3.setCellValue("No. of clients receiving ");
    rw9cell3.setCellStyle(stborder);
    
    XSSFCell  rw9cell4=rw9S10.createCell(2);
    rw9cell4.setCellValue(FPCONDOMSN);
    rw9cell4.setCellStyle(stborder);
    
    XSSFCell  rw9cell5=rw9S10.createCell(3);
    rw9cell5.setCellValue(FPCONDOMSR);
    rw9cell5.setCellStyle(stborder);
    
    XSSFCell  rw9cell6=rw9S10.createCell(4);
    rw9cell6.setCellValue(FPCONDOMST);
    rw9cell6.setCellStyle(stborder);
    
    
     XSSFRow rw10S10=shet1.createRow(14);    
    XSSFCell  rw10cell2=rw10S10.createCell(0);
    rw10cell2.setCellValue("ALL OTHERS:");
    rw10cell2.setCellStyle(stborder);
    
    XSSFCell  rw10cell3=rw10S10.createCell(1);
    rw10cell3.setCellValue(" ");
    rw10cell3.setCellStyle(stborder);
    
    XSSFCell  rw10cell4=rw10S10.createCell(2);
    rw10cell4.setCellValue(FPOTHERN);
    rw10cell4.setCellStyle(stborder);
    
    XSSFCell  rw10cell5=rw10S10.createCell(3);
    rw10cell5.setCellValue(FPOTHERR);
    rw10cell5.setCellStyle(stborder);
    
    XSSFCell  rw10cell6=rw10S10.createCell(4);
    rw10cell6.setCellValue(FPOTHERT);
    rw10cell6.setCellStyle(stborder);
        
    
    
    
     
     XSSFRow rw11S10=shet1.createRow(15);    
    XSSFCell  rw11cell2=rw11S10.createCell(0);
    rw11cell2.setCellValue("TOTAL NO. OF CLIENTS");
    rw11cell2.setCellStyle(stborder);
    
    XSSFCell  rw11cell3=rw11S10.createCell(1);
    rw11cell3.setCellValue(" ");
    rw11cell3.setCellStyle(stborder);
    
    XSSFCell  rw11cell4=rw11S10.createCell(2);
    rw11cell4.setCellValue(FPOTHERN);
    rw11cell4.setCellStyle(stborder);
    
    XSSFCell  rw11cell5=rw11S10.createCell(3);
    rw11cell5.setCellValue(FPOTHERR);
    rw11cell5.setCellStyle(stborder);
    
    XSSFCell  rw11cell6=rw11S10.createCell(4);
    rw11cell6.setCellValue(FPOTHERT);
    rw11cell6.setCellStyle(stborder);
    
    
    
     XSSFRow rw12S10=shet1.createRow(16);    
    XSSFCell  rw12cell2=rw12S10.createCell(0);
    rw12cell2.setCellValue("REMOVALS");
    rw12cell2.setCellStyle(stborder);
    
    XSSFCell  rw12cell3=rw12S10.createCell(1);
    rw12cell3.setCellValue(" IUCD");
    rw12cell3.setCellStyle(stborder);
    
    XSSFCell  rw12cell4=rw12S10.createCell(2);
    rw12cell4.setCellValue(FPIUCDRemoval);
    rw12cell4.setCellStyle(stborder);
    
    XSSFCell  rw12cell5=rw12S10.createCell(3);
    rw12cell5.setCellValue("IMPLANTS");
    rw12cell5.setCellStyle(stborder);
    
    XSSFCell  rw12cell6=rw12S10.createCell(4);
    rw12cell6.setCellValue(FPIMPLANTSRemoval);
    rw12cell6.setCellStyle(stborder);
          
    
    
          // mch 
 
   if(conn.rs.getString(33)!=null){PMCTA_1stVisit_ANC=conn.rs.getString(33);}else{PMCTA_1stVisit_ANC="";}
  if(conn.rs.getString(34)!=null){PMCTA_ReVisit_ANC=conn.rs.getString(34);}else{PMCTA_ReVisit_ANC="";}
  if(conn.rs.getString(35)!=null){PMCTANCClientsT=conn.rs.getString(35);}else{PMCTANCClientsT="";}
  if(conn.rs.getString(36)!=null){PMCTHB7=conn.rs.getString(36);}else{PMCTHB7="";}
  if(conn.rs.getString(37)!=null){PMCTIPT1=conn.rs.getString(37);}else{PMCTIPT1="";}
  if(conn.rs.getString(38)!=null){PMCTIPT2=conn.rs.getString(38);}else{PMCTIPT2="";}
  if(conn.rs.getString(39)!=null){PMCTANCClients4=conn.rs.getString(39);}else{PMCTANCClients4="";}
  if(conn.rs.getString(40)!=null){PMCTITN=conn.rs.getString(40);}else{PMCTITN="";}
  
  
  
  
    XSSFRow rw1S20=shet2.createRow(5);    
    XSSFCell  rw1cells1=rw1S20.createCell(0);
    rw1cells1.setCellValue("B: MCH-ANC PMCT");
    rw1cells1.setCellStyle(styleHeader);
    
    XSSFCell  rw1cells2=rw1S20.createCell(1);
    rw1cells2.setCellValue(" NEW");
    rw1cells2.setCellStyle(styleHeader);
    
    XSSFCell  rw1cells3=rw1S20.createCell(2);
    rw1cells3.setCellValue("REVISIT");
    rw1cells3.setCellStyle(styleHeader);
    
    XSSFCell  rw1cells4=rw1S20.createCell(3);
    rw1cells4.setCellValue("TOTAL");
    rw1cells4.setCellStyle(styleHeader);
    
    XSSFRow rw2S20=shet2.createRow(6);    
    XSSFCell  rw2cells1=rw2S20.createCell(0);
    rw2cells1.setCellValue("NO OF ANC CLIENTS");
    rw2cells1.setCellStyle(stborder);
    
    XSSFCell  rw2cells2=rw2S20.createCell(1);
    rw2cells2.setCellValue(PMCTA_1stVisit_ANC);
    rw2cells2.setCellStyle(stborder);
    
    XSSFCell  rw2cells3=rw2S20.createCell(2);
    rw2cells3.setCellValue(PMCTA_ReVisit_ANC);
    rw2cells3.setCellStyle(stborder);
    
    XSSFCell  rw2cells4=rw2S20.createCell(3);
    rw2cells4.setCellValue(PMCTANCClientsT);
    rw2cells4.setCellStyle(stborder);
    
     XSSFRow rw3S20=shet2.createRow(7);    
    XSSFCell  rw3cells1=rw3S20.createCell(0);
    rw3cells1.setCellValue("NO OF CLIENTS WITH Hb <7 g/dl ");
    rw3cells1.setCellStyle(stborder);
    
    XSSFCell  rw3cells2=rw3S20.createCell(1);
    rw3cells2.setCellValue("");
    rw3cells2.setCellStyle(stborder);
    
    XSSFCell  rw3cells3=rw3S20.createCell(2);
    rw3cells3.setCellValue("");
    rw3cells3.setCellStyle(stborder);
    
    XSSFCell  rw3cells4=rw3S20.createCell(3);
    rw3cells4.setCellValue(PMCTHB7);
    rw3cells4.setCellStyle(stborder);
    shet2.addMergedRegion(new CellRangeAddress(7,7,0,2));
    
    
      XSSFRow rw4S20=shet2.createRow(8);    
    XSSFCell  rw4cells1=rw4S20.createCell(0);
    rw4cells1.setCellValue("NO OF CLIENTS GIVEN IPT(1st dose)");
    rw4cells1.setCellStyle(stborder);
    
    XSSFCell  rw4cells2=rw4S20.createCell(1);
    rw4cells2.setCellValue("");
    rw4cells2.setCellStyle(stborder);
    
    XSSFCell  rw4cells3=rw4S20.createCell(2);
    rw4cells3.setCellValue("");
    rw4cells3.setCellStyle(stborder);
    
    XSSFCell  rw4cells4=rw4S20.createCell(3);
    rw4cells4.setCellValue(PMCTIPT1);
    rw4cells4.setCellStyle(stborder);
    shet2.addMergedRegion(new CellRangeAddress(8,8,0,2));
    
      
      XSSFRow rw5S20=shet2.createRow(9);    
    XSSFCell  rw5cells1=rw5S20.createCell(0);
    rw5cells1.setCellValue("NO OF CLIENTS GIVEN IPT(2nd dose)");
    rw5cells1.setCellStyle(stborder);
    
    XSSFCell  rw5cells2=rw5S20.createCell(1);
    rw5cells2.setCellValue("");
    rw5cells2.setCellStyle(stborder);
    
    XSSFCell  rw5cells3=rw5S20.createCell(2);
    rw5cells3.setCellValue("");
    rw5cells3.setCellStyle(stborder);
    
    XSSFCell  rw5cells4=rw5S20.createCell(3);
    rw5cells4.setCellValue(PMCTIPT2);
    rw5cells4.setCellStyle(stborder);
    shet2.addMergedRegion(new CellRangeAddress(9,9,0,2));
     
    
    XSSFRow rw6S20=shet2.createRow(10);    
    XSSFCell  rw6cells1=rw6S20.createCell(0);
    rw6cells1.setCellValue("NO OF CLIENTS COMPLETED 4TH ANTENATAL VISIT");
    rw6cells1.setCellStyle(stborder);
    
    XSSFCell  rw6cells2=rw6S20.createCell(1);
    rw6cells2.setCellValue("");
    rw6cells2.setCellStyle(stborder);
    
    XSSFCell  rw6cells3=rw6S20.createCell(2);
    rw6cells3.setCellValue("");
    rw6cells3.setCellStyle(stborder);
    
    XSSFCell  rw6cells4=rw6S20.createCell(3);
    rw6cells4.setCellValue(PMCTANCClients4);
    rw6cells4.setCellStyle(stborder);
    shet2.addMergedRegion(new CellRangeAddress(10,10,0,2));
          
    
     XSSFRow rw7S20=shet2.createRow(11);    
    XSSFCell  rw7cells1=rw6S20.createCell(0);
    rw7cells1.setCellValue("No of ITNs distributed to ANC Clients");
    rw7cells1.setCellStyle(stborder);
    
    XSSFCell  rw7cells2=rw6S20.createCell(1);
    rw7cells2.setCellValue("");
    rw7cells2.setCellStyle(stborder);
    
    XSSFCell  rw7cells3=rw6S20.createCell(2);
    rw7cells3.setCellValue("");
    rw7cells3.setCellStyle(stborder);
    
    XSSFCell  rw7cells4=rw6S20.createCell(3);
    rw7cells4.setCellValue(PMCTITN);
    rw7cells4.setCellStyle(stborder);
    shet2.addMergedRegion(new CellRangeAddress(11,11,0,2));
  

  if(conn.rs.getString(41)!=null){MATNormalDelivery=conn.rs.getString(41);}else{MATNormalDelivery="";}
  if(conn.rs.getString(42)!=null){MATCSection=conn.rs.getString(42);}else{MATCSection="";}
  if(conn.rs.getString(43)!=null){MATBreech=conn.rs.getString(43);}else{MATBreech="";}
  if(conn.rs.getString(44)!=null){MATAssistedVag=conn.rs.getString(44);}else{MATAssistedVag="";}
  if(conn.rs.getString(45)!=null){MATDeliveryT=conn.rs.getString(45);}else{MATDeliveryT="";}
  if(conn.rs.getString(46)!=null){MATLiveBirth=conn.rs.getString(46);}else{MATLiveBirth="";}
  if(conn.rs.getString(47)!=null){MATStillBirth=conn.rs.getString(47);}else{MATStillBirth="";}

  if(conn.rs.getString(48)!=null){MATWeight2500=conn.rs.getString(48);}else{MATWeight2500="";}
  if(conn.rs.getString(49)!=null){MATPreTerm=conn.rs.getString(49);}else{MATPreTerm="";}
  if(conn.rs.getString(50)!=null){MATDischargealive=conn.rs.getString(50);}else{MATDischargealive="";}
  if(conn.rs.getString(51)!=null){MATReferral=conn.rs.getString(51);}else{MATReferral="";}
  if(conn.rs.getString(52)!=null){MATNeoNatalD=conn.rs.getString(52);}else{MATNeoNatalD="";}
  if(conn.rs.getString(53)!=null){MATMaternalD=conn.rs.getString(53);}else{MATMaternalD="";}
  if(conn.rs.getString(54)!=null){MATAPHAlive=conn.rs.getString(54);}else{MATAPHAlive="";}
  if(conn.rs.getString(55)!=null){MATAPHDead=conn.rs.getString(55);}else{MATAPHDead="";}
  if(conn.rs.getString(56)!=null){MATPPHAlive=conn.rs.getString(56);}else{MATPPHAlive="";}
  if(conn.rs.getString(57)!=null){MATPPHDead=conn.rs.getString(57);}else{MATPPHDead="";}
  if(conn.rs.getString(58)!=null){MATEclampAlive=conn.rs.getString(58);}else{MATEclampAlive="";}
  if(conn.rs.getString(59)!=null){MATEclampDead=conn.rs.getString(59);}else{MATEclampDead="";}
  if(conn.rs.getString(60)!=null){MATRupUtAlive=conn.rs.getString(60);}else{MATRupUtAlive="";}
  if(conn.rs.getString(61)!=null){MATRupUtDead=conn.rs.getString(61);}else{MATRupUtDead="";}
  if(conn.rs.getString(62)!=null){MATObstrLaborAlive=conn.rs.getString(62);}else{MATObstrLaborAlive="";}
  if(conn.rs.getString(63)!=null){MATObstrLaborDead=conn.rs.getString(63);}else{MATObstrLaborDead="";}
  if(conn.rs.getString(64)!=null){MATSepsisAlive=conn.rs.getString(64);}else{MATSepsisAlive="";}
  if(conn.rs.getString(65)!=null){MATSepsisDead=conn.rs.getString(65);}else{MATSepsisDead="";}
  
  
  //    ---------------------------------------------
  
  XSSFRow rw3S30=shet3.createRow(5);    
    XSSFCell  rw1cellS3=rw3S30.createCell(0);
    rw1cellS3.setCellValue("E: MATERNITY SAFE DELIVERIES");
    rw1cellS3.setCellStyle(styleHeader);
    
    XSSFCell  rw2cellS3=rw3S30.createCell(1);
    rw2cellS3.setCellValue(" NUMBER");
    rw2cellS3.setCellStyle(styleHeader);
    //    ---------------------------------------------
     XSSFRow rw4S30=shet3.createRow(6);  
    XSSFCell  rw3cellS3=rw4S30.createCell(0);
    rw3cellS3.setCellValue("Normal Deliveries");
    rw3cellS3.setCellStyle(stborder);
    
    XSSFCell  rw4cellS3=rw4S30.createCell(1);
    rw4cellS3.setCellValue(MATNormalDelivery);
    rw4cellS3.setCellStyle(stborder);
    //_______________________________________
     XSSFRow rw5S30=shet3.createRow(7);  
    XSSFCell  rw5cellS3=rw5S30.createCell(0);
    rw5cellS3.setCellValue("Caesarean Section");
    rw5cellS3.setCellStyle(stborder);
    
    XSSFCell  rw6cellS3=rw5S30.createCell(1);
    rw6cellS3.setCellValue(MATCSection);
    rw6cellS3.setCellStyle(stborder);
//    ---------------------------------------------
    XSSFRow rw6S30=shet3.createRow(8);  
    XSSFCell  rw7cellS3=rw6S30.createCell(0);
    rw7cellS3.setCellValue("Breech Delivery");
    rw7cellS3.setCellStyle(stborder);
    
    XSSFCell  rw8cellS3=rw6S30.createCell(1);
    rw8cellS3.setCellValue(MATBreech);
    rw8cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
    XSSFRow rw7S30=shet3.createRow(9);  
    XSSFCell  rw9cellS3=rw7S30.createCell(0);
    rw9cellS3.setCellValue("Assisted Vaginal Delivery");
    rw9cellS3.setCellStyle(stborder);
    
    XSSFCell  rw10cellS3=rw7S30.createCell(1);
    rw10cellS3.setCellValue(MATAssistedVag);
    rw10cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
  
    XSSFRow rw8S30=shet3.createRow(10);  
    XSSFCell  rw11cellS3=rw8S30.createCell(0);
    rw11cellS3.setCellValue("TOTAL DELIVERIES");
    rw11cellS3.setCellStyle(stylex);
    
    XSSFCell  rw12cellS3=rw8S30.createCell(1);
    rw12cellS3.setCellValue(MATDeliveryT);
    rw12cellS3.setCellStyle(stylex);
  //    ---------------------------------------------
    XSSFRow rw9S30=shet3.createRow(11);  
    XSSFCell  rw13cellS3=rw9S30.createCell(0);
    rw13cellS3.setCellValue("Live Births");
    rw13cellS3.setCellStyle(stborder);
    
    XSSFCell  rw14cellS3=rw9S30.createCell(1);
    rw14cellS3.setCellValue(MATLiveBirth);
    rw14cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
  
//    XSSFRow rw10S30=shet3.createRow(12);  
//    XSSFCell  rw15cellS3=rw10S30.createCell(0);
//    rw15cellS3.setCellValue("Live Births");
//    rw15cellS3.setCellStyle(stborder);
//    
//    XSSFCell  rw16cellS3=rw10S30.createCell(1);
//    rw16cellS3.setCellValue(MATLiveBirth);
//    rw16cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
    XSSFRow rw11S30=shet3.createRow(12);  
    XSSFCell  rw17cellS3=rw11S30.createCell(0);
    rw17cellS3.setCellValue("Still Births");
    rw17cellS3.setCellStyle(stborder);
    
    XSSFCell  rw18cellS3=rw11S30.createCell(1);
    rw18cellS3.setCellValue(MATStillBirth);
    rw18cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
  
    XSSFRow rw12S30=shet3.createRow(13);  
    XSSFCell  rw19cellS3=rw12S30.createCell(0);
    rw19cellS3.setCellValue("Under Weight Babies (Weight below 2500 grams)");
    rw19cellS3.setCellStyle(stborder);
    
    XSSFCell  rw20cellS3=rw12S30.createCell(1);
    rw20cellS3.setCellValue(MATWeight2500);
    rw20cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
    XSSFRow rw13S30=shet3.createRow(14);  
    XSSFCell  rw21cellS3=rw13S30.createCell(0);
    rw21cellS3.setCellValue("Pre-Term Babies");
    rw21cellS3.setCellStyle(stborder);
    
    XSSFCell  rw22cellS3=rw13S30.createCell(1);
    rw22cellS3.setCellValue(MATPreTerm);
    rw22cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
    XSSFRow rw14S30=shet3.createRow(15);  
    XSSFCell  rw23cellS3=rw14S30.createCell(0);
    rw23cellS3.setCellValue("No. of babies discharged alive");
    rw23cellS3.setCellStyle(stborder);
    
    XSSFCell  rw24cellS3=rw14S30.createCell(1);
    rw24cellS3.setCellValue(MATDischargealive);
    rw24cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
//    XSSFRow rw15S30=shet3.createRow(16);  
//    XSSFCell  rw25cellS3=rw15S30.createCell(0);
//    rw25cellS3.setCellValue("Referrals");
//    rw25cellS3.setCellStyle(stborder);
//    
//    XSSFCell  rw26cellS3=rw15S30.createCell(1);
//    rw26cellS3.setCellValue(MATDischargealive);
//    rw26cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
    XSSFRow rw16S30=shet3.createRow(16);  
    XSSFCell  rw27cellS3=rw16S30.createCell(0);
    rw27cellS3.setCellValue("Referrals");
    rw27cellS3.setCellStyle(stborder);
    
    XSSFCell  rw28cellS3=rw16S30.createCell(1);
    rw28cellS3.setCellValue(MATReferral);
    rw28cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
    XSSFRow rw17S30=shet3.createRow(17);  
    XSSFCell  rw29cellS3=rw17S30.createCell(0);
    rw29cellS3.setCellValue("Neonatal Deaths");
    rw29cellS3.setCellStyle(stborder);
    
    XSSFCell  rw30cellS3=rw17S30.createCell(1);
    rw30cellS3.setCellValue(MATNeoNatalD);
    rw30cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
    
      XSSFRow rw18S30=shet3.createRow(18);  
    XSSFCell  rw31cellS3=rw18S30.createCell(0);
    rw31cellS3.setCellValue("Maternal Deaths");
    rw31cellS3.setCellStyle(stborder);
    
    XSSFCell  rw32cellS3=rw18S30.createCell(1);
    rw32cellS3.setCellValue(MATMaternalD);
    rw32cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
      XSSFRow rw19S30=shet3.createRow(19);  
    XSSFCell  rw33cellS3=rw19S30.createCell(0);
    rw33cellS3.setCellValue("Maternal Complications");
    rw33cellS3.setCellStyle(stylex);
    
    XSSFCell  rw34cellS3=rw19S30.createCell(1);
    rw34cellS3.setCellValue("Alive");
    rw34cellS3.setCellStyle(stylex);
    
    XSSFCell  rw35cellS3=rw19S30.createCell(2);
    rw35cellS3.setCellValue("Dead");
    rw35cellS3.setCellStyle(stylex);
  //    ---------------------------------------------
      XSSFRow rw20S30=shet3.createRow(20);  
    XSSFCell  rw305cellS3=rw20S30.createCell(0);
    rw305cellS3.setCellValue("A.P.H. (Ante Partum Heamorrhage)");
    rw305cellS3.setCellStyle(stborder);
    
    XSSFCell  rw36cellS3=rw20S30.createCell(1);
    rw36cellS3.setCellValue(MATAPHAlive);
    rw36cellS3.setCellStyle(stborder);
    
    XSSFCell  rw37cellS3=rw20S30.createCell(2);
    rw37cellS3.setCellValue(MATAPHDead);
    rw37cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
      XSSFRow rw21S30=shet3.createRow(21);  
    XSSFCell  rw38cellS3=rw21S30.createCell(0);
    rw38cellS3.setCellValue("P.P.H. (Post Partum Heamorrhage)");
    rw38cellS3.setCellStyle(stborder);
    
    XSSFCell  rw39cellS3=rw21S30.createCell(1);
    rw39cellS3.setCellValue(MATPPHAlive);
    rw39cellS3.setCellStyle(stborder);
    
    XSSFCell  rw40cellS3=rw21S30.createCell(2);
    rw40cellS3.setCellValue(MATPPHDead);
    rw40cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
      XSSFRow rw22S30=shet3.createRow(22);  
    XSSFCell  rw41cellS3=rw22S30.createCell(0);
    rw41cellS3.setCellValue("Eclampsia");
    rw41cellS3.setCellStyle(stborder);
    
    XSSFCell  rw42cellS3=rw22S30.createCell(1);
    rw42cellS3.setCellValue(MATEclampAlive);
    rw42cellS3.setCellStyle(stborder);
    
    XSSFCell  rw43cellS3=rw22S30.createCell(2);
    rw43cellS3.setCellValue(MATEclampDead);
    rw43cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
      XSSFRow rw23S30=shet3.createRow(23);  
    XSSFCell  rw44cellS3=rw23S30.createCell(0);
    rw44cellS3.setCellValue("Ruptured Uterus");
    rw44cellS3.setCellStyle(stborder);
    
    XSSFCell  rw45cellS3=rw23S30.createCell(1);
    rw45cellS3.setCellValue(MATRupUtAlive);
    rw45cellS3.setCellStyle(stborder);
    
    XSSFCell  rw46cellS3=rw23S30.createCell(2);
    rw46cellS3.setCellValue(MATRupUtDead);
    rw46cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
      XSSFRow rw24S30=shet3.createRow(24);  
    XSSFCell  rw47cellS3=rw24S30.createCell(0);
    rw47cellS3.setCellValue("Obstructed Labor");
    rw47cellS3.setCellStyle(stborder);
    
    XSSFCell  rw48cellS3=rw24S30.createCell(1);
    rw48cellS3.setCellValue(MATObstrLaborAlive);
    rw48cellS3.setCellStyle(stborder);
    
    XSSFCell  rw49cellS3=rw24S30.createCell(2);
    rw49cellS3.setCellValue(MATObstrLaborDead);
    rw49cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
    
     XSSFRow rw25S30=shet3.createRow(25);  
    XSSFCell  rw50cellS3=rw25S30.createCell(0);
    rw50cellS3.setCellValue("Sepsis");
    rw50cellS3.setCellStyle(stborder);
    
    XSSFCell  rw51cellS3=rw25S30.createCell(1);
    rw51cellS3.setCellValue(MATSepsisAlive);
    rw51cellS3.setCellStyle(stborder);
    
    XSSFCell  rw52cellS3=rw25S30.createCell(2);
    rw52cellS3.setCellValue(MATSepsisDead);
    rw52cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
  
  
  
  if(conn.rs.getString(66)!=null){VCTClient_Couns_CM=conn.rs.getString(66);}else{VCTClient_Couns_CM="";}
  if(conn.rs.getString(67)!=null){VCTClient_Couns_CF=conn.rs.getString(67);}else{VCTClient_Couns_CF="";}
  if(conn.rs.getString(68)!=null){VCTClient_Couns_AM=conn.rs.getString(68);}else{VCTClient_Couns_AM="";}
  if(conn.rs.getString(69)!=null){VCTClient_Couns_AF=conn.rs.getString(69);}else{VCTClient_Couns_AF="";}
  if(conn.rs.getString(70)!=null){VCTClient_Couns_TOT=conn.rs.getString(70);}else{VCTClient_Couns_TOT="";}
  if(conn.rs.getString(71)!=null){VCTClient_Tested_CM=conn.rs.getString(71);}else{VCTClient_Tested_CM="";}
  if(conn.rs.getString(72)!=null){VCTClient_Tested_CF=conn.rs.getString(72);}else{VCTClient_Tested_CF="";}
  if(conn.rs.getString(73)!=null){VCTClient_Tested_AM=conn.rs.getString(73);}else{VCTClient_Tested_AM="";}
  if(conn.rs.getString(74)!=null){VCTClient_Tested_AF=conn.rs.getString(74);}else{VCTClient_Tested_AF="";}
  if(conn.rs.getString(75)!=null){VCTClient_Tested_TOT=conn.rs.getString(75);}else{VCTClient_Tested_TOT="";}
  if(conn.rs.getString(76)!=null){VCTClient_HIV_CM=conn.rs.getString(76);}else{VCTClient_HIV_CM="";}
  if(conn.rs.getString(77)!=null){VCTClient_HIV_CF=conn.rs.getString(77);}else{VCTClient_HIV_CF="";}
  if(conn.rs.getString(78)!=null){VCTClient_HIV_AM=conn.rs.getString(78);}else{VCTClient_HIV_AM="";}
  if(conn.rs.getString(79)!=null){VCTClient_HIV_AF=conn.rs.getString(79);}else{VCTClient_HIV_AF="";}
  if(conn.rs.getString(80)!=null){VCTClient_HIV_TOT=conn.rs.getString(80);}else{VCTClient_HIV_TOT="";}
  if(conn.rs.getString(81)!=null){VCTPartner_Couns_TOT=conn.rs.getString(81);}else{VCTPartner_Couns_TOT="";}
  if(conn.rs.getString(82)!=null){VCTPartner_Tested_TOT=conn.rs.getString(82);}else{VCTPartner_Tested_TOT="";}
  if(conn.rs.getString(83)!=null){VCTPartner_HIV_TOT=conn.rs.getString(83);}else{VCTPartner_HIV_TOT="";}
  if(conn.rs.getString(84)!=null){VCTPartner_Disc_TOT=conn.rs.getString(84);}else{VCTPartner_Disc_TOT="";}
  
   XSSFRow rw1S30=shet4.createRow(5);    
    XSSFCell  rw1cellSH3=rw1S30.createCell(0);
    rw1cellSH3.setCellValue("H: VCT");
    rw1cellSH3.setCellStyle(styleHeader);
    
    XSSFCell  rw2celSH3=rw1S30.createCell(1);
    rw2celSH3.setCellValue(" ");
    rw2celSH3.setCellStyle(styleHeader);
     shet4.addMergedRegion(new CellRangeAddress(5,5,0,1));
     
    XSSFCell  rw3celSH3=rw1S30.createCell(2);
    rw3celSH3.setCellValue("15-24 Years");
    rw3celSH3.setCellStyle(styleHeader);
   
     
    XSSFCell  rw4celSH4=rw1S30.createCell(3);
    rw4celSH4.setCellValue("");
    rw4celSH4.setCellStyle(styleHeader);
      shet4.addMergedRegion(new CellRangeAddress(5,5,2,3));
    XSSFCell  rw5celSH3=rw1S30.createCell(4);
    rw5celSH3.setCellValue(">=25 Years");
    rw5celSH3.setCellStyle(styleHeader);
 
   
   
    XSSFCell  rw6celSH3=rw1S30.createCell(5);
    rw6celSH3.setCellValue("Total");
    rw6celSH3.setCellStyle(styleHeader);
      shet4.addMergedRegion(new CellRangeAddress(5,5,4,5));
    
    
    XSSFCell  rw9celSH3=rw1S30.createCell(6);
    rw9celSH3.setCellValue("Total");
    rw9celSH3.setCellStyle(styleHeader);
    
    //=-------------------------------------------------------------
   
    XSSFRow rw2S30=shet4.createRow(6);    
    XSSFCell  rw2cellSH3=rw2S30.createCell(0);
    rw2cellSH3.setCellValue("");
    rw2cellSH3.setCellStyle(styleHeader);
    
    XSSFCell  rw3cellSH3=rw2S30.createCell(1);
    rw3cellSH3.setCellValue(" ");
    rw3cellSH3.setCellStyle(styleHeader);
    shet4.addMergedRegion(new CellRangeAddress(6,6,0,1));
     
    XSSFCell  rw4cellSH3=rw2S30.createCell(2);
    rw4cellSH3.setCellValue("F");
    rw4cellSH3.setCellStyle(styleHeader);
     
    XSSFCell  rw5cellSH3=rw2S30.createCell(3);
    rw5cellSH3.setCellValue("M");
    rw5cellSH3.setCellStyle(styleHeader);
    
    XSSFCell  rw6cellSH3=rw2S30.createCell(4);
    rw6cellSH3.setCellValue("F");
    rw6cellSH3.setCellStyle(styleHeader);
   
    
     XSSFCell  rw7cellSH3=rw2S30.createCell(5);
    rw7cellSH3.setCellValue("M");
    rw7cellSH3.setCellStyle(styleHeader);
    
     XSSFCell  rw8cellSH3=rw2S30.createCell(6);
    rw8cellSH3.setCellValue("TOTAL");
    rw8cellSH3.setCellStyle(styleHeader);
    
    shet4.addMergedRegion(new CellRangeAddress(5,6,6,6));
    
    
    
    
    
    
     XSSFRow rw3S3=shet4.createRow(7);    
    XSSFCell  rw9cellSH3=rw3S3.createCell(0);
    rw9cellSH3.setCellValue("VCT CLIENTS");
    rw9cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw10cellSH3=rw3S3.createCell(1);
    rw10cellSH3.setCellValue("Counselled");
    rw10cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw11cellSH3=rw3S3.createCell(2);
    rw11cellSH3.setCellValue(VCTClient_Couns_CF);
    rw11cellSH3.setCellStyle(stborder);
     
    XSSFCell  rw12cellSH3=rw3S3.createCell(3);
    rw12cellSH3.setCellValue(VCTClient_Couns_CM);
    rw12cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw13celSH3=rw3S3.createCell(4);
    rw13celSH3.setCellValue(VCTClient_Couns_AF);
    rw13celSH3.setCellStyle(stborder);
   
    
     XSSFCell  rw14celSH3=rw3S3.createCell(5);
    rw14celSH3.setCellValue(VCTClient_Couns_AM);
    rw14celSH3.setCellStyle(stborder);
    
     XSSFCell  rw15celSH3=rw3S3.createCell(6);
    rw15celSH3.setCellValue(VCTClient_Couns_TOT);
    rw15celSH3.setCellStyle(stborder);
    
    
    
    
     XSSFRow rw4S3=shet4.createRow(8);    
    XSSFCell  rw16cellSH3=rw4S3.createCell(0);
    rw16cellSH3.setCellValue("");
    rw16cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw17cellSH3=rw4S3.createCell(1);
    rw17cellSH3.setCellValue("Tested ");
    rw17cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw18cellSH3=rw4S3.createCell(2);
    rw18cellSH3.setCellValue(VCTClient_Tested_CF);
    rw18cellSH3.setCellStyle(stborder);
     
    XSSFCell  rw19cellSH3=rw4S3.createCell(3);
    rw19cellSH3.setCellValue(VCTClient_Tested_CM);
    rw19cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw20celSH3=rw4S3.createCell(4);
    rw20celSH3.setCellValue(VCTClient_Tested_AF);
    rw20celSH3.setCellStyle(stborder);
   
    
     XSSFCell  rw21celSH3=rw4S3.createCell(5);
    rw21celSH3.setCellValue(VCTClient_Tested_AM);
    rw21celSH3.setCellStyle(stborder);
    
     XSSFCell  rw22celSH3=rw4S3.createCell(6);
    rw22celSH3.setCellValue(VCTClient_Tested_TOT);
    rw22celSH3.setCellStyle(stborder);
    
    
    
       XSSFRow rw5S3=shet4.createRow(9);    
    XSSFCell  rw21cellSH3=rw5S3.createCell(0);
    rw21cellSH3.setCellValue("");
    rw21cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw22cellSH3=rw5S3.createCell(1);
    rw22cellSH3.setCellValue("HIV+ ");
    rw22cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw23cellSH3=rw5S3.createCell(2);
    rw23cellSH3.setCellValue(VCTClient_HIV_CF);
    rw23cellSH3.setCellStyle(stborder);
     
    XSSFCell  rw24cellSH3=rw5S3.createCell(3);
    rw24cellSH3.setCellValue(VCTClient_HIV_CM);
    rw24cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw25celSH3=rw5S3.createCell(4);
    rw25celSH3.setCellValue(VCTClient_HIV_AF);
    rw25celSH3.setCellStyle(stborder);
   
    
     XSSFCell  rw26celSH3=rw5S3.createCell(5);
    rw26celSH3.setCellValue(VCTClient_HIV_AM);
    rw26celSH3.setCellStyle(stborder);
    
     XSSFCell  rw27celSH3=rw5S3.createCell(6);
    rw27celSH3.setCellValue(VCTClient_HIV_TOT);
    rw27celSH3.setCellStyle(stborder);
        shet4.addMergedRegion(new CellRangeAddress(7,9,0,0)); 
        
        
         XSSFRow rw6S3=shet4.createRow(10);    
    XSSFCell  rw28cellSH3=rw6S3.createCell(0);
    rw28cellSH3.setCellValue("No Of Couples");
    rw28cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw29cellSH3=rw6S3.createCell(1);
    rw29cellSH3.setCellValue("Tested");
    rw29cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw30cellSH3=rw6S3.createCell(2);
    rw30cellSH3.setCellValue("");
    rw30cellSH3.setCellStyle(stborder);
     
    XSSFCell  rw31cellSH3=rw6S3.createCell(3);
    rw31cellSH3.setCellValue("");
    rw31cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw32cellSH3=rw6S3.createCell(4);
    rw32cellSH3.setCellValue("");
    rw32cellSH3.setCellStyle(stborder);
   
    
     XSSFCell  rw33celSH3=rw6S3.createCell(5);
    rw33celSH3.setCellValue("");
    rw33celSH3.setCellStyle(stborder);
    
     XSSFCell  rw34celSH3=rw6S3.createCell(6);
    rw34celSH3.setCellValue(VCTPartner_Couns_TOT);
    rw34celSH3.setCellStyle(stborder);
            shet4.addMergedRegion(new CellRangeAddress(10,10,2,5)); 

    
    
    
      XSSFRow rw7S3=shet4.createRow(11);    
    XSSFCell  rw35cellSH3=rw7S3.createCell(0);
    rw35cellSH3.setCellValue("No Of Couples");
    rw35cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw36cellSH3=rw7S3.createCell(1);
    rw36cellSH3.setCellValue("Both HIV+");
    rw36cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw37cellSH3=rw7S3.createCell(2);
    rw37cellSH3.setCellValue("");
    rw37cellSH3.setCellStyle(stborder);
     
    XSSFCell  rw38cellSH3=rw7S3.createCell(3);
    rw38cellSH3.setCellValue("");
    rw38cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw39cellSH3=rw7S3.createCell(4);
    rw39cellSH3.setCellValue("");
    rw39cellSH3.setCellStyle(stborder);
   
    
     XSSFCell  rw40celSH3=rw7S3.createCell(5);
    rw40celSH3.setCellValue("");
    rw40celSH3.setCellStyle(stborder);
    
     XSSFCell  rw41celSH3=rw7S3.createCell(6);
    rw41celSH3.setCellValue(VCTPartner_HIV_TOT);
    rw41celSH3.setCellStyle(stborder);
    
    
       shet4.addMergedRegion(new CellRangeAddress(11,11,2,5)); 
     
    
      XSSFRow rw8S3=shet4.createRow(12);    
    XSSFCell  rw35_cellSH3=rw8S3.createCell(0);
    rw35_cellSH3.setCellValue("No Of Couples");
    rw35_cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw36_cellSH3=rw8S3.createCell(1);
    rw36_cellSH3.setCellValue("With discordant");
    rw36_cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw37_cellSH3=rw8S3.createCell(2);
    rw37_cellSH3.setCellValue("");
    rw37_cellSH3.setCellStyle(stborder);
     
    XSSFCell  rw38_cellSH3=rw8S3.createCell(3);
    rw38_cellSH3.setCellValue("");
    rw38_cellSH3.setCellStyle(stborder);
    
    XSSFCell  rw39_cellSH3=rw8S3.createCell(4);
    rw39_cellSH3.setCellValue("");
    rw39_cellSH3.setCellStyle(stborder);
   
    
     XSSFCell  rw40_celSH3=rw8S3.createCell(5);
    rw40_celSH3.setCellValue("");
    rw40_celSH3.setCellStyle(stborder);
    
     XSSFCell  rw41_celSH3=rw8S3.createCell(6);
    rw41_celSH3.setCellValue(VCTPartner_Disc_TOT);
    rw41_celSH3.setCellStyle(stborder);
     shet4.addMergedRegion(new CellRangeAddress(10,12,0,0)); 
       shet4.addMergedRegion(new CellRangeAddress(12,12,2,5)); 
       shet4.addMergedRegion(new CellRangeAddress(0,0,10,12)); 
//  DTCA_Couns_In_CM=DTCA_Couns_In_CF=DTCA_Couns_In_AM=DTCA_Couns_In_AF=DTCA_Couns_In_Tot=DTCA_Couns_Out_CM=DTCA_Couns_Out_CF=
  //DTCA_Couns_Out_AM=DTCA_Couns_Out_AF=DTCA_Couns_Out_Tot=DTCB_Test_In_CM=DTCB_Test_In_CF
//=DTCB_Test_In_AM=DTCB_Test_In_AF=DTCB_Test_In_Tot=DTCB_Test_Out_CM=DTCB_Test_Out_CF=DTCB_Test_Out_AM=DTCB_Test_Out_AF=DTCB_Test_Out_Tot
  //=DTCC_HIV_In_CM=DTCC_HIV_In_CF=DTCC_HIV_In_AM
//=DTCC_HIV_In_AF= DTCC_HIV_In_Tot=DTCC_HIV_Out_CM=DTCC_HIV_Out_CF=DTCC_HIV_Out_AM=DTCC_HIV_Out_AF=DTCC_HIV_Out_Tot=Userid="";

  
  //dtc
  if(conn.rs.getString(85)!=null){DTCA_Couns_In_CM=conn.rs.getString(85);}else{DTCA_Couns_In_CM="";}
  if(conn.rs.getString(86)!=null){DTCA_Couns_In_CF=conn.rs.getString(86);}else{DTCA_Couns_In_CF="";}
  if(conn.rs.getString(87)!=null){DTCA_Couns_In_AM=conn.rs.getString(87);}else{DTCA_Couns_In_AM="";}
  if(conn.rs.getString(88)!=null){DTCA_Couns_In_AF=conn.rs.getString(88);}else{DTCA_Couns_In_AF="";}
  if(conn.rs.getString(89)!=null){DTCA_Couns_In_Tot=conn.rs.getString(89);}else{DTCA_Couns_In_Tot="";}
  if(conn.rs.getString(90)!=null){DTCA_Couns_Out_CM=conn.rs.getString(90);}else{DTCA_Couns_Out_CM="";}
  if(conn.rs.getString(91)!=null){DTCA_Couns_Out_CF=conn.rs.getString(91);}else{DTCA_Couns_Out_CF="";}
  if(conn.rs.getString(92)!=null){DTCA_Couns_Out_AM=conn.rs.getString(92);}else{DTCA_Couns_Out_AM="";}
  if(conn.rs.getString(93)!=null){DTCA_Couns_Out_AF=conn.rs.getString(93);}else{DTCA_Couns_Out_AF="";}
  if(conn.rs.getString(94)!=null){DTCA_Couns_Out_Tot=conn.rs.getString(94);}else{DTCA_Couns_Out_Tot="";}
  if(conn.rs.getString(95)!=null){DTCB_Test_In_CM=conn.rs.getString(95);}else{DTCB_Test_In_CM="";}
  if(conn.rs.getString(96)!=null){DTCB_Test_In_CF=conn.rs.getString(96);}else{DTCB_Test_In_CF="";}
  if(conn.rs.getString(97)!=null){DTCB_Test_In_AM=conn.rs.getString(97);}else{DTCB_Test_In_AM="";}
  if(conn.rs.getString(98)!=null){DTCB_Test_In_AF=conn.rs.getString(98);}else{DTCB_Test_In_AF="";}
  if(conn.rs.getString(99)!=null){DTCB_Test_In_Tot=conn.rs.getString(99);}else{DTCB_Test_In_Tot="";}
  if(conn.rs.getString(100)!=null){DTCB_Test_Out_CM=conn.rs.getString(100);}else{DTCB_Test_Out_CM="";}
  if(conn.rs.getString(101)!=null){DTCB_Test_Out_CF=conn.rs.getString(101);}else{DTCB_Test_Out_CF="";}
  if(conn.rs.getString(102)!=null){DTCB_Test_Out_AM=conn.rs.getString(102);}else{DTCB_Test_Out_AM="";}
  if(conn.rs.getString(103)!=null){DTCB_Test_Out_AF=conn.rs.getString(103);}else{DTCB_Test_Out_AF="";}
  if(conn.rs.getString(104)!=null){DTCB_Test_Out_Tot=conn.rs.getString(104);}else{DTCB_Test_Out_Tot="";}
  if(conn.rs.getString(105)!=null){DTCC_HIV_In_CM=conn.rs.getString(105);}else{DTCC_HIV_In_CM="";}
  if(conn.rs.getString(106)!=null){DTCC_HIV_In_CF=conn.rs.getString(106);}else{DTCC_HIV_In_CF="";}
  if(conn.rs.getString(107)!=null){DTCC_HIV_In_AM=conn.rs.getString(107);}else{DTCC_HIV_In_AM="";}
  if(conn.rs.getString(108)!=null){DTCC_HIV_In_AF=conn.rs.getString(108);}else{DTCC_HIV_In_AF="";}
  if(conn.rs.getString(109)!=null){DTCC_HIV_In_Tot=conn.rs.getString(109);}else{DTCC_HIV_In_Tot="";}
  if(conn.rs.getString(110)!=null){DTCC_HIV_Out_CM=conn.rs.getString(110);}else{DTCC_HIV_Out_CM="";}
  if(conn.rs.getString(111)!=null){DTCC_HIV_Out_CF=conn.rs.getString(111);}else{DTCC_HIV_Out_CF="";}
  if(conn.rs.getString(112)!=null){DTCC_HIV_Out_AM=conn.rs.getString(112);}else{DTCC_HIV_Out_AM="";}
  if(conn.rs.getString(113)!=null){DTCC_HIV_Out_AF=conn.rs.getString(113);}else{DTCC_HIV_Out_AF="";}
  if(conn.rs.getString(114)!=null){DTCC_HIV_Out_Tot=conn.rs.getString(114);}else{DTCC_HIV_Out_Tot="";}
  
  
     XSSFRow rw1S5=shet5.createRow(5);    
    XSSFCell  rw0cellSH5=rw1S5.createCell(0);
    rw0cellSH5.setCellValue("I: DTC");
    rw0cellSH5.setCellStyle(styleHeader);
    
    XSSFCell  rw2_celSH3=rw1S5.createCell(1);
    rw2_celSH3.setCellValue(" ");
    rw2_celSH3.setCellStyle(styleHeader);
     shet5.addMergedRegion(new CellRangeAddress(5,5,0,1));
     
    XSSFCell  rw3_celSH3=rw1S5.createCell(2);
    rw3_celSH3.setCellValue("Children(0-14 Yrs)");
    rw3_celSH3.setCellStyle(styleHeader);
   
     
    XSSFCell  rw4_celSH4=rw1S5.createCell(3);
    rw4_celSH4.setCellValue("");
    rw4_celSH4.setCellStyle(styleHeader);
    
   shet5.addMergedRegion(new CellRangeAddress(5,5,2,3));
   
    XSSFCell  rw5_celSH3=rw1S5.createCell(4);
    rw5_celSH3.setCellValue("Adults(>=14 Yrs)");
    rw5_celSH3.setCellStyle(styleHeader);
 
   
   
    XSSFCell  rw6_celSH3=rw1S5.createCell(5);
    rw6_celSH3.setCellValue("Total");
    rw6_celSH3.setCellStyle(styleHeader);
      shet5.addMergedRegion(new CellRangeAddress(5,5,4,5));
    
    
    XSSFCell  rw9_celSH3=rw1S5.createCell(6);
    rw9_celSH3.setCellValue("Total");
    rw9_celSH3.setCellStyle(styleHeader);
    
    //=-------------------------------------------------------------
   
    XSSFRow rw2S5=shet5.createRow(6);    
    XSSFCell  rw2_cellSH3=rw2S5.createCell(0);
    rw2_cellSH3.setCellValue("");
    rw2_cellSH3.setCellStyle(styleHeader);
    
    XSSFCell  rw3_cellSH3=rw2S5.createCell(1);
    rw3_cellSH3.setCellValue(" ");
    rw3_cellSH3.setCellStyle(styleHeader);
    shet5.addMergedRegion(new CellRangeAddress(6,6,0,1));
     
    XSSFCell  rw4_cellSH3=rw2S5.createCell(2);
    rw4_cellSH3.setCellValue("F");
    rw4_cellSH3.setCellStyle(styleHeader);
     
    XSSFCell  rw5_cellSH3=rw2S5.createCell(3);
    rw5_cellSH3.setCellValue("M");
    rw5_cellSH3.setCellStyle(styleHeader);
    
    XSSFCell  rw6_cellSH3=rw2S5.createCell(4);
    rw6_cellSH3.setCellValue("F");
    rw6_cellSH3.setCellStyle(styleHeader);
   
    
     XSSFCell  rw7_cellSH3=rw2S5.createCell(5);
    rw7_cellSH3.setCellValue("M");
    rw7_cellSH3.setCellStyle(styleHeader);
    
     XSSFCell  rw8_cellSH3=rw2S5.createCell(6);
    rw8_cellSH3.setCellValue("TOTAL");
    rw8_cellSH3.setCellStyle(styleHeader);
    
    shet5.addMergedRegion(new CellRangeAddress(5,6,6,6));
  
     XSSFRow rw3S5=shet5.createRow(7);    
    XSSFCell  rw1cellSH5=rw3S5.createCell(0);
    rw1cellSH5.setCellValue("No counselled");
    rw1cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw2cellSH5=rw3S5.createCell(1);
    rw2cellSH5.setCellValue("Outpatient");
    rw2cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw3cellSH5=rw3S5.createCell(2);
    rw3cellSH5.setCellValue(DTCA_Couns_Out_CF);
    rw3cellSH5.setCellStyle(stborder);
     
    XSSFCell  rw4cellSH5=rw3S5.createCell(3);
    rw4cellSH5.setCellValue(DTCA_Couns_Out_CM);
    rw4cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw5cellSH5=rw3S5.createCell(4);
    rw5cellSH5.setCellValue(DTCA_Couns_Out_AF);
    rw5cellSH5.setCellStyle(stborder);
   
    
     XSSFCell  rw6celSH5=rw3S5.createCell(5);
    rw6celSH5.setCellValue(DTCA_Couns_Out_AM);
    rw6celSH5.setCellStyle(stborder);
    
     XSSFCell  rw7celSH5=rw3S5.createCell(6);
    rw7celSH5.setCellValue(DTCA_Couns_Out_Tot);
    rw7celSH5.setCellStyle(stborder);   
     
    
    
         XSSFRow rw4S5=shet5.createRow(8);    
    XSSFCell  rw8cellSH5=rw4S5.createCell(0);
    rw8cellSH5.setCellValue("No counselled");
    rw8cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw9cellSH5=rw4S5.createCell(1);
    rw9cellSH5.setCellValue("Inpatient");
    rw9cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw10cellSH5=rw4S5.createCell(2);
    rw10cellSH5.setCellValue(DTCA_Couns_In_CF);
    rw10cellSH5.setCellStyle(stborder);
     
    XSSFCell  rw11cellSH5=rw4S5.createCell(3);
    rw11cellSH5.setCellValue(DTCA_Couns_In_CM);
    rw11cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw12cellSH5=rw4S5.createCell(4);
    rw12cellSH5.setCellValue(DTCA_Couns_In_AF);
    rw12cellSH5.setCellStyle(stborder);
   
    
     XSSFCell  rw13celSH5=rw4S5.createCell(5);
    rw13celSH5.setCellValue(DTCA_Couns_In_AM);
    rw13celSH5.setCellStyle(stborder);
    
     XSSFCell  rw14celSH5=rw4S5.createCell(6);
    rw14celSH5.setCellValue(DTCA_Couns_In_Tot);
    rw14celSH5.setCellStyle(stborder);
    
      shet5.addMergedRegion(new CellRangeAddress(7,8,0,0)); 
    
    
    
    XSSFRow rw5S5=shet5.createRow(9);    
    XSSFCell  rw15cellSH5=rw5S5.createCell(0);
    rw15cellSH5.setCellValue("No tested");
    rw15cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw16cellSH5=rw5S5.createCell(1);
    rw16cellSH5.setCellValue("Outpatient");
    rw16cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw17cellSH5=rw5S5.createCell(2);
    rw17cellSH5.setCellValue(DTCB_Test_Out_CF);
    rw17cellSH5.setCellStyle(stborder);
     
    XSSFCell  rw18cellSH5=rw5S5.createCell(3);
    rw18cellSH5.setCellValue(DTCB_Test_Out_CM);
    rw18cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw19cellSH5=rw5S5.createCell(4);
    rw19cellSH5.setCellValue(DTCB_Test_Out_AF);
    rw19cellSH5.setCellStyle(stborder);
   
    
     XSSFCell  rw20celSH5=rw5S5.createCell(5);
    rw20celSH5.setCellValue(DTCB_Test_Out_AM);
    rw20celSH5.setCellStyle(stborder);
    
     XSSFCell  rw21celSH5=rw5S5.createCell(6);
    rw21celSH5.setCellValue(DTCB_Test_Out_Tot);
    rw21celSH5.setCellStyle(stborder);
    
    
    
    XSSFRow rw6S5=shet5.createRow(10);    
    XSSFCell  rw22cellSH5=rw6S5.createCell(0);
    rw22cellSH5.setCellValue("No tested");
    rw22cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw23cellSH5=rw6S5.createCell(1);
    rw23cellSH5.setCellValue("Inpatient");
    rw23cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw24cellSH5=rw6S5.createCell(2);
    rw24cellSH5.setCellValue(DTCB_Test_In_CF);
    rw24cellSH5.setCellStyle(stborder);
     
    XSSFCell  rw25cellSH5=rw6S5.createCell(3);
    rw25cellSH5.setCellValue(DTCB_Test_In_CM);
    rw25cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw26cellSH5=rw6S5.createCell(4);
    rw26cellSH5.setCellValue(DTCB_Test_In_AF);
    rw26cellSH5.setCellStyle(stborder);
   
    
     XSSFCell  rw27celSH5=rw6S5.createCell(5);
    rw27celSH5.setCellValue(DTCB_Test_In_AM);
    rw27celSH5.setCellStyle(stborder);
    
     XSSFCell  rw28celSH5=rw6S5.createCell(6);
    rw28celSH5.setCellValue(DTCB_Test_In_Tot);
    rw28celSH5.setCellStyle(stborder);
    
     shet5.addMergedRegion(new CellRangeAddress(9,10,0,0)); 
     
    XSSFRow rw7S5=shet5.createRow(11);    
    XSSFCell  rw29cellSH5=rw7S5.createCell(0);
    rw29cellSH5.setCellValue("No HIV+");
    rw29cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw30cellSH5=rw7S5.createCell(1);
    rw30cellSH5.setCellValue("Outpatient");
    rw30cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw31cellSH5=rw7S5.createCell(2);
    rw31cellSH5.setCellValue(DTCC_HIV_Out_CF);
    rw31cellSH5.setCellStyle(stborder);
     
    XSSFCell  rw32cellSH5=rw7S5.createCell(3);
    rw32cellSH5.setCellValue(DTCC_HIV_Out_CM);
    rw32cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw33cellSH5=rw7S5.createCell(4);
    rw33cellSH5.setCellValue(DTCC_HIV_Out_AF);
    rw33cellSH5.setCellStyle(stborder);
   
    
     XSSFCell  rw34celSH5=rw7S5.createCell(5);
    rw34celSH5.setCellValue(DTCC_HIV_Out_AM);
    rw34celSH5.setCellStyle(stborder);
    
     XSSFCell  rw35celSH5=rw7S5.createCell(6);
    rw35celSH5.setCellValue(DTCC_HIV_Out_Tot);
    rw35celSH5.setCellStyle(stborder);
    
    
     
     
    XSSFRow rw8S5=shet5.createRow(12);    
    XSSFCell  rw29_cellSH5=rw8S5.createCell(0);
    rw29_cellSH5.setCellValue("No HIV+");
    rw29_cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw30_cellSH5=rw8S5.createCell(1);
    rw30_cellSH5.setCellValue("Inpatient");
    rw30_cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw31_cellSH5=rw8S5.createCell(2);
    rw31_cellSH5.setCellValue(DTCC_HIV_In_CF);
    rw31_cellSH5.setCellStyle(stborder);
     
    XSSFCell  rw32_cellSH5=rw8S5.createCell(3);
    rw32_cellSH5.setCellValue(DTCC_HIV_In_CM);
    rw32_cellSH5.setCellStyle(stborder);
    
    XSSFCell  rw33_cellSH5=rw8S5.createCell(4);
    rw33_cellSH5.setCellValue(DTCC_HIV_In_AF);
    rw33_cellSH5.setCellStyle(stborder);
   
    
     XSSFCell  rw34_celSH5=rw8S5.createCell(5);
    rw34_celSH5.setCellValue(DTCC_HIV_In_AM);
    rw34_celSH5.setCellStyle(stborder);
    
     XSSFCell  rw35_celSH5=rw8S5.createCell(6);
    rw35_celSH5.setCellValue(DTCC_HIV_In_Tot);
    rw35_celSH5.setCellStyle(stborder);
    shet5.addMergedRegion(new CellRangeAddress(11,12,0,0)); 
    
  System.out.println(conn.rs.getString(1)+" "+conn.rs.getString(2))  ;
 
}


  if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
//     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
//     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.conn!=null){conn.conn.close();} 
     
        IdGenerator IG = new IdGenerator();
     String createdOn="";
               createdOn=IG.CreatedOn();
     
      // write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=MOH711_STATIC_REPORT_CREATED_ON_"+createdOn.trim()+".xlsx");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
    


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
            Logger.getLogger(moh711_excelReport.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(moh711_excelReport.class.getName()).log(Level.SEVERE, null, ex);
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
