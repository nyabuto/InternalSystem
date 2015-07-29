/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

import General.IdGenerator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import database.dbConn;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class pdf731 extends HttpServlet {
HttpSession session;
String data,id;
String facilityId;
String HIV_CT,PMTCT,CT,VMMC,PEP,Blood;

String testing,receiving_results,receiving_postive_results;
String testing_for_HIV,HIV_Postive_results,partner_involvement,
        maternal_prophylaxis,assessment_ART,infant_testing,
        confirmed_infant,infant_feeding,infant_ARV;
String on_CP,enrolled_care,currently_care,starting_ART,revisit_ART,
        current_ART,cumulative_ART,cumulative_ARTPrevious,survival_ART,screening,pwp,HIV_care;
String number_circumcised,hiv_status,adverse_events;
String type_exposure,provided_p;
String blood_safety;
int HV0101,HV0102,HV0103,HV0105,HV0106,HV0107,HV0108,HV0109,HV0110,HV0111,HV0112,HV0113,HV0114,
        HV0115,HV0116;
int HV0201,HV0202,HV0203,HV0204,HV0205,HV0206,HV0207,HV0208,HV0209,HV0210,HV0211,HV0212,HV0213,
HV0214,HV0215,HV0216,HV0217,HV0218,HV0219,HV0220,HV0221,HV0224,HV0225,HV0226,HV0227,HV0228,HV0229,
        HV0230,HV0231,HV0232,HV0233,HV0234,HV0235,HV0236,HV0237,HV0238,HV0239,HV0240,HV0241,HV0242,
        HV0243,HV0244;
int HV0301,HV0302,HV0303,HV0304,HV0305,HV0306,HV0307,HV0308,HV0309,HV0310,HV0311,HV0312,HV0313,HV0314,
        HV0315,HV0316,HV0317,HV0318,HV0319,HV0320,HV0321,HV0322,HV0323,HV0324,HV0325,HV0326,HV0327,HV0328,
        HV0329,HV0330,HV0331,HV0332,HV0333,HV0334,HV0335,HV0336,HV0337,HV0338,HV0339,HV0340,HV0341,
        HV0342,HV0343,HV0344,HV0345,HV0346,HV0347,HV0348,HV0349,HV0350,HV0351,HV0352,HV0353,
        HV0354,HV0355,HV0904,HV0905,HV0370,HV0371,HV0372,HV0373;
int HV0401,HV0402,HV0403,HV0406,HV0407,HV0408,HV0409,HV0410,HV0411,HV0412,HV0413,HV0414,HV0415;
int HV0501,HV0502,HV0503,HV0504,HV0505,HV0506,HV0507,HV0508,HV0509,HV0510,HV0511,HV0512,HV0513,HV0514;
int HV0601,HV0602,HV0605;
        
  String isValidated,validity,createdOn;
int maxYearMonth;
String subcountyid,facility,period,url;
String reportType,duration,reportDuration,quarter,semi_annual;
int year,prevYear,month;
String header,facilityName,countyName,districtName,mflcode,monthName,headerInfo;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, DocumentException, CssResolverException {

        dbConn conn = new dbConn();
        session=request.getSession();
        
        reportType=request.getParameter("reportType");
        year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
        
        headerInfo="";
//        reportType="1";
//        year=2015;
//        reportDuration="4";
        
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================
        
        if(reportDuration.equals("1")){
         duration=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
      period="Annual Report ";
      url="ANNUAL_REPORT_YEAR("+year+")";
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
       duration=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03";      
      period="Semi-Annual : <b> OCT ("+prevYear+") -  MARCH ("+year+")</b>"; 
      url="SEMI_ANNUAL_REPORT_FOR_OCT("+prevYear+")_MARCH("+year+")";
       }
           else{
       duration=" moh731.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
       period="Semi-Annual : <b> APRIL ("+year+") -  SEPT ("+year+")</b>";  
      url="SEMI_ANNUAL_REPORT_FOR_APRIL("+year+")_SEPT("+year+")";
       }
       }
        
        else if(reportDuration.equals("3")){
            String startMonth,endMonth;
       quarter=request.getParameter("quarter");
//       quarter="3";
       String getMonths="SELECT months,name FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" moh731.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
     period="Quarter: <b>"+conn.rs.getString(2).replace("-", prevYear+" - ")+" "+prevYear+"</b>";
     url="QUARTERLY_REPORT_FOR_"+conn.rs.getString(2).replace("-", prevYear+"-")+"_"+prevYear+"";
      }
      else{
     duration=" moh731.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;
     period="Quarter: <b>"+conn.rs.getString(2).replace("-", year+" - ")+" "+year+"</b>";
    url="QUARTERLY_REPORT_FOR_"+conn.rs.getString(2).replace("-", year+"-")+"_"+year+"";
      }
        }
        }  
        
      else if(reportDuration.equals("4")){
     month=Integer.parseInt(request.getParameter("month"));
//     month=4;
     if(month>=10){
     duration=" moh731.yearmonth="+prevYear+""+month;    
     }
     else{
  duration=" moh731.yearmonth="+year+"0"+month;  
     }
    String getMonthName="SELECT name FROM month WHERE id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
       if(month>=10){
   period="Month : <b>"+conn.rs.getString(1)+"("+prevYear+")</b>"; 
   url="MONTHLY_REPORT_FOR_"+conn.rs.getString(1)+"("+prevYear+")";
       }
       else{
        period="Month : <b>"+conn.rs.getString(1)+"("+year+")</b>";
        url="MONTHLY_REPORT_FOR_"+conn.rs.getString(1)+"("+year+")";
    }
     
    }
      }
      else{
     duration="";     
      }
           
     
//     GET FACILITIES TO OUTPUT.................................
    mflcode=countyName=districtName=facilityName="";    
        
      if(reportType.equals("1")){  
    facility="";  
  header+=""  ;
   headerInfo="All health facilities.  Year: <b>"+year+"</b> "+period+"" ;
      }
      
      else{
  facilityId=request.getParameter("facility");
//  facilityId="403";
  facility="SubPartnerID='"+facilityId+"' &&";    
  
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
  header+=
 headerInfo= " County: <b>"+countyName+"</b> District: <b>"+districtName+"</b>  Facility: <b>"+facilityName+"</b>"+period+"  Year: <b>"+year+"</b>   MFL Code: <b>"+mflcode+"</b>";  
      }
     
    header+="</table>";  
    
    System.out.println(header);
    String getMaxYearMonth="SELECT MAX(yearmonth) FROM moh731 WHERE "+facility+" "+duration ;
    conn.rs=conn.st.executeQuery(getMaxYearMonth);
    if(conn.rs.next()==true){
        maxYearMonth=conn.rs.getInt(1);
    }
   System.out.println("max year month : "+maxYearMonth);
        
      
      
//   START OUTPUTTING THE RESULTS=================================================   
       

      
      
HIV_CT=PMTCT=CT=VMMC=PEP=Blood="";
           data="<html><head>"
                   + "<style>  </style>"
                   + ""
                   + "</head><body>";
           
 isValidated="";validity="";          
 HV0101=HV0102=HV0103=HV0105=HV0106=HV0107=HV0108=HV0109=HV0110=HV0111=HV0112=HV0113=HV0114=
        HV0115=HV0116=0;
HV0201=HV0202=HV0203=HV0204=HV0205=HV0206=HV0207=HV0208=HV0209=HV0210=HV0211=HV0212=HV0213=
HV0214=HV0215=HV0216=HV0217=HV0218=HV0219=HV0220=HV0221=HV0224=HV0225=HV0226=HV0227=HV0228=HV0229=
        HV0230=HV0231=HV0232=HV0233=HV0234=HV0235=HV0236=HV0237=HV0238=HV0239=HV0240=HV0241=HV0242=
        HV0243=HV0244=0;
HV0301=HV0302=HV0303=HV0304=HV0305=HV0306=HV0307=HV0308=HV0309=HV0310=HV0311=HV0312=HV0313=HV0314=
        HV0315=HV0316=HV0317=HV0318=HV0319=HV0320=HV0321=HV0322=HV0323=HV0324=HV0325=HV0326=HV0327=HV0328=
        HV0329=HV0330=HV0331=HV0332=HV0333=HV0334=HV0335=HV0336=HV0337=HV0338=HV0339=HV0340=HV0341=
        HV0342=HV0343=HV0344=HV0345=HV0346=HV0347=HV0348=HV0349=HV0350=HV0351=HV0352=HV0353=
        HV0354=HV0355=HV0904=HV0905=HV0370=HV0371=HV0372=HV0373=0;
HV0401=HV0402=HV0403=HV0406=HV0407=HV0408=HV0409=HV0410=HV0411=HV0412=HV0413=HV0414=HV0415=0;
 HV0501=HV0502=HV0503=HV0504=HV0505=HV0506=HV0507=HV0508=HV0509=HV0510=HV0511=HV0512=HV0513=HV0514=0;
 HV0601=HV0602=HV0605=0;

     System.out.println("facility : "+facility+"   duration : "+duration);
 
          String checker="SELECT "
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
"SUM(HV0601),SUM(HV0602),SUM(HV0605)"
+ " FROM moh731 WHERE "+facility+" "+duration ;

          conn.rs=conn.st.executeQuery(checker);
          if(conn.rs.next()==true){
              
 System.out.println("Data already exist loading............................");

 if(conn.rs.getInt(1)==0){HV0101=conn.rs.getInt(1);}
  if(conn.rs.getInt(2)!=0){HV0102=conn.rs.getInt(2);}
  if(conn.rs.getInt(3)!=0){HV0103=conn.rs.getInt(3);}
  if(conn.rs.getInt(4)!=0){HV0105=conn.rs.getInt(4);}
  if(conn.rs.getInt(5)!=0){HV0106=conn.rs.getInt(5);}
  if(conn.rs.getInt(6)!=0){HV0107=conn.rs.getInt(6);}
  if(conn.rs.getInt(7)!=0){HV0108=conn.rs.getInt(7);}
  if(conn.rs.getInt(8)!=0){HV0109=conn.rs.getInt(8);}
  if(conn.rs.getInt(9)!=0){HV0110=conn.rs.getInt(9);}
  if(conn.rs.getInt(10)!=0){HV0111=conn.rs.getInt(10);}
  if(conn.rs.getInt(11)!=0){HV0112=conn.rs.getInt(11);}
  if(conn.rs.getInt(12)!=0){HV0113=conn.rs.getInt(12);}
  if(conn.rs.getInt(13)!=0){HV0114=conn.rs.getInt(13);}
  if(conn.rs.getInt(14)!=0){HV0115=conn.rs.getInt(14);}
  if(conn.rs.getInt(15)!=0){HV0116=conn.rs.getInt(15);}
 
if(conn.rs.getInt(16)!=0){HV0201=conn.rs.getInt(16);}
if(conn.rs.getInt(17)!=0){HV0202=conn.rs.getInt(17);}
if(conn.rs.getInt(18)!=0){HV0203=conn.rs.getInt(18);}
if(conn.rs.getInt(19)!=0){HV0204=conn.rs.getInt(19);}
if(conn.rs.getInt(20)!=0){HV0205=conn.rs.getInt(20);}
if(conn.rs.getInt(21)!=0){HV0206=conn.rs.getInt(21);}
if(conn.rs.getInt(22)!=0){HV0207=conn.rs.getInt(22);}
if(conn.rs.getInt(23)!=0){HV0208=conn.rs.getInt(23);}
if(conn.rs.getInt(24)!=0){HV0209=conn.rs.getInt(24);}
if(conn.rs.getInt(25)!=0){HV0210=conn.rs.getInt(25);}
if(conn.rs.getInt(26)!=0){HV0211=conn.rs.getInt(26);}
if(conn.rs.getInt(27)!=0){HV0212=conn.rs.getInt(27);}
if(conn.rs.getInt(28)!=0){HV0213=conn.rs.getInt(28);}
if(conn.rs.getInt(29)!=0){HV0214=conn.rs.getInt(29);}
if(conn.rs.getInt(30)!=0){HV0215=conn.rs.getInt(30);}
if(conn.rs.getInt(31)!=0){HV0216=conn.rs.getInt(31);}
if(conn.rs.getInt(32)!=0){HV0217=conn.rs.getInt(32);}
if(conn.rs.getInt(33)!=0){HV0218=conn.rs.getInt(33);}
if(conn.rs.getInt(34)!=0){HV0219=conn.rs.getInt(34);}
if(conn.rs.getInt(35)!=0){HV0220=conn.rs.getInt(35);}
if(conn.rs.getInt(36)!=0){HV0221=conn.rs.getInt(36);}
if(conn.rs.getInt(37)!=0){HV0224=conn.rs.getInt(37);}
if(conn.rs.getInt(38)!=0){HV0225=conn.rs.getInt(38);}
if(conn.rs.getInt(39)!=0){HV0226=conn.rs.getInt(39);}
if(conn.rs.getInt(40)!=0){HV0227=conn.rs.getInt(40);}
if(conn.rs.getInt(41)!=0){HV0228=conn.rs.getInt(41);}
if(conn.rs.getInt(42)!=0){HV0229=conn.rs.getInt(42);}
if(conn.rs.getInt(43)!=0){HV0230=conn.rs.getInt(43);}
if(conn.rs.getInt(44)!=0){HV0231=conn.rs.getInt(44);}
if(conn.rs.getInt(45)!=0){HV0232=conn.rs.getInt(45);}
if(conn.rs.getInt(46)!=0){HV0233=conn.rs.getInt(46);}
if(conn.rs.getInt(47)!=0){HV0234=conn.rs.getInt(47);}
if(conn.rs.getInt(48)!=0){HV0235=conn.rs.getInt(48);}
if(conn.rs.getInt(49)!=0){HV0236=conn.rs.getInt(49);}
if(conn.rs.getInt(50)!=0){HV0237=conn.rs.getInt(50);}
if(conn.rs.getInt(51)!=0){HV0238=conn.rs.getInt(51);}
if(conn.rs.getInt(52)!=0){HV0239=conn.rs.getInt(52);}
if(conn.rs.getInt(53)!=0){HV0240=conn.rs.getInt(53);}
if(conn.rs.getInt(54)!=0){HV0241=conn.rs.getInt(54);}
if(conn.rs.getInt(55)!=0){HV0242=conn.rs.getInt(55);}
if(conn.rs.getInt(56)!=0){HV0243=conn.rs.getInt(56);}
if(conn.rs.getInt(57)!=0){HV0244=conn.rs.getInt(57);}

//if(conn.rs.getInt(58)!=0){HV0301=conn.rs.getInt(58);}
//if(conn.rs.getInt(59)!=0){HV0302=conn.rs.getInt(59);}
//if(conn.rs.getInt(60)!=0){HV0303=conn.rs.getInt(60);}
//if(conn.rs.getInt(61)!=0){HV0304=conn.rs.getInt(61);}
//if(conn.rs.getInt(62)!=0){HV0305=conn.rs.getInt(62);}
//if(conn.rs.getInt(63)!=0){HV0306=conn.rs.getInt(63);}
//if(conn.rs.getInt(64)!=0){HV0307=conn.rs.getInt(64);}
if(conn.rs.getInt(65)!=0){HV0308=conn.rs.getInt(65);}
if(conn.rs.getInt(66)!=0){HV0309=conn.rs.getInt(66);}
if(conn.rs.getInt(67)!=0){HV0310=conn.rs.getInt(67);}
if(conn.rs.getInt(68)!=0){HV0311=conn.rs.getInt(68);}
if(conn.rs.getInt(69)!=0){HV0312=conn.rs.getInt(69);}
if(conn.rs.getInt(70)!=0){HV0313=conn.rs.getInt(70);}
//if(conn.rs.getInt(71)!=0){HV0314=conn.rs.getInt(71);}
//if(conn.rs.getInt(72)!=0){HV0315=conn.rs.getInt(72);}
//if(conn.rs.getInt(73)!=0){HV0316=conn.rs.getInt(73);}
//if(conn.rs.getInt(74)!=0){HV0317=conn.rs.getInt(74);}
//if(conn.rs.getInt(75)!=0){HV0318=conn.rs.getInt(75);}
//if(conn.rs.getInt(76)!=0){HV0319=conn.rs.getInt(76);}
if(conn.rs.getInt(77)!=0){HV0320=conn.rs.getInt(77);}
if(conn.rs.getInt(78)!=0){HV0321=conn.rs.getInt(78);}
if(conn.rs.getInt(79)!=0){HV0322=conn.rs.getInt(79);}
if(conn.rs.getInt(80)!=0){HV0323=conn.rs.getInt(80);}
if(conn.rs.getInt(81)!=0){HV0324=conn.rs.getInt(81);}
if(conn.rs.getInt(82)!=0){HV0325=conn.rs.getInt(82);}
if(conn.rs.getInt(83)!=0){HV0326=conn.rs.getInt(83);}
if(conn.rs.getInt(84)!=0){HV0327=conn.rs.getInt(84);}
//if(conn.rs.getInt(85)!=0){HV0328=conn.rs.getInt(85);}
//if(conn.rs.getInt(86)!=0){HV0329=conn.rs.getInt(86);}
//if(conn.rs.getInt(87)!=0){HV0330=conn.rs.getInt(87);}
//if(conn.rs.getInt(88)!=0){HV0331=conn.rs.getInt(88);}
//if(conn.rs.getInt(89)!=0){HV0332=conn.rs.getInt(89);}
//if(conn.rs.getInt(90)!=0){HV0333=conn.rs.getInt(90);}
//if(conn.rs.getInt(91)!=0){HV0334=conn.rs.getInt(91);}
//if(conn.rs.getInt(92)!=0){HV0335=conn.rs.getInt(92);}
//if(conn.rs.getInt(93)!=0){HV0336=conn.rs.getInt(93);}
//if(conn.rs.getInt(94)!=0){HV0337=conn.rs.getInt(94);}
//if(conn.rs.getInt(95)!=0){HV0338=conn.rs.getInt(95);}
//if(conn.rs.getInt(96)!=0){HV0339=conn.rs.getInt(96);}
//if(conn.rs.getInt(97)!=0){HV0340=conn.rs.getInt(97);}
//if(conn.rs.getInt(98)!=0){HV0341=conn.rs.getInt(98);}
//if(conn.rs.getInt(99)!=0){HV0342=conn.rs.getInt(99);}
//if(conn.rs.getInt(100)!=0){HV0343=conn.rs.getInt(100);}
//if(conn.rs.getInt(101)!=0){HV0344=conn.rs.getInt(101);}
if(conn.rs.getInt(102)!=0){HV0345=conn.rs.getInt(102);}
if(conn.rs.getInt(103)!=0){HV0346=conn.rs.getInt(103);}
if(conn.rs.getInt(104)!=0){HV0347=conn.rs.getInt(104);}
if(conn.rs.getInt(105)!=0){HV0348=conn.rs.getInt(105);}
if(conn.rs.getInt(106)!=0){HV0349=conn.rs.getInt(106);}
//if(conn.rs.getInt(107)!=0){HV0350=conn.rs.getInt(107);}
//if(conn.rs.getInt(108)!=0){HV0351=conn.rs.getInt(108);}
//if(conn.rs.getInt(109)!=0){HV0352=conn.rs.getInt(109);}
//if(conn.rs.getInt(110)!=0){HV0353=conn.rs.getInt(110);}
//if(conn.rs.getInt(111)!=0){HV0354=conn.rs.getInt(111);}
//if(conn.rs.getInt(112)!=0){HV0355=conn.rs.getInt(112);}
if(conn.rs.getInt(113)!=0){HV0904=conn.rs.getInt(113);}
if(conn.rs.getInt(114)!=0){HV0905=conn.rs.getInt(114);}
if(conn.rs.getInt(115)!=0){HV0370=conn.rs.getInt(115);}
if(conn.rs.getInt(116)!=0){HV0371=conn.rs.getInt(116);}
if(conn.rs.getInt(117)!=0){HV0372=conn.rs.getInt(117);}
if(conn.rs.getInt(118)!=0){HV0373=conn.rs.getInt(118);}

if(conn.rs.getInt(119)!=0){HV0401=conn.rs.getInt(119);}
if(conn.rs.getInt(120)!=0){HV0402=conn.rs.getInt(120);}
if(conn.rs.getInt(121)!=0){HV0403=conn.rs.getInt(121);}
if(conn.rs.getInt(122)!=0){HV0406=conn.rs.getInt(122);}
if(conn.rs.getInt(123)!=0){HV0407=conn.rs.getInt(123);}
if(conn.rs.getInt(124)!=0){HV0408=conn.rs.getInt(124);}
if(conn.rs.getInt(125)!=0){HV0409=conn.rs.getInt(125);}
if(conn.rs.getInt(126)!=0){HV0410=conn.rs.getInt(126);}
if(conn.rs.getInt(127)!=0){HV0411=conn.rs.getInt(127);}
if(conn.rs.getInt(128)!=0){HV0412=conn.rs.getInt(128);}
if(conn.rs.getInt(129)!=0){HV0413=conn.rs.getInt(129);}
if(conn.rs.getInt(130)!=0){HV0414=conn.rs.getInt(130);}
if(conn.rs.getInt(131)!=0){HV0415=conn.rs.getInt(131);}

if(conn.rs.getInt(132)!=0){HV0501=conn.rs.getInt(132);}
if(conn.rs.getInt(133)!=0){HV0502=conn.rs.getInt(133);}
if(conn.rs.getInt(134)!=0){HV0503=conn.rs.getInt(134);}
if(conn.rs.getInt(135)!=0){HV0504=conn.rs.getInt(134);}
if(conn.rs.getInt(136)!=0){HV0505=conn.rs.getInt(136);}
if(conn.rs.getInt(137)!=0){HV0506=conn.rs.getInt(137);}
if(conn.rs.getInt(138)!=0){HV0507=conn.rs.getInt(138);}
if(conn.rs.getInt(139)!=0){HV0508=conn.rs.getInt(139);}
if(conn.rs.getInt(140)!=0){HV0509=conn.rs.getInt(140);}
if(conn.rs.getInt(141)!=0){HV0510=conn.rs.getInt(141);}
if(conn.rs.getInt(142)!=0){HV0511=conn.rs.getInt(142);}
if(conn.rs.getInt(143)!=0){HV0512=conn.rs.getInt(143);}
if(conn.rs.getInt(144)!=0){HV0513=conn.rs.getInt(144);}
if(conn.rs.getInt(145)!=0){HV0514=conn.rs.getInt(145);}

if(conn.rs.getInt(146)!=0){HV0601=conn.rs.getInt(146);}
if(conn.rs.getInt(147)!=0){HV0602=conn.rs.getInt(147);}
if(conn.rs.getInt(148)!=0){HV0605=conn.rs.getInt(148);}

   }
//  CURRENTLY IN CARE HV03-14 -> HV03-19
//  CURRENTLY ON ART  HV03-34 -> HV03-39
//   CUMULATIVE EVER ON ART HV03-40 -> HV03-44
          
          
   String getCummulatives="SELECT SUM(HV0301),SUM(HV0302),SUM(HV0303),SUM(HV0304),SUM(HV0305),SUM(HV0306),SUM(HV0307),"
     + "SUM(HV0314),SUM(HV0315),SUM(HV0316),SUM(HV0317),SUM(HV0318),SUM(HV0319),"
     + "SUM(HV0328),SUM(HV0329),SUM(HV0330),SUM(HV0331),SUM(HV0332),SUM(HV0333),SUM(HV0334),SUM(HV0335),"
     + "SUM(HV0336),SUM(HV0337),SUM(HV0338),SUM(HV0339),SUM(HV0340),SUM(HV0341),SUM(HV0342),SUM(HV0343),SUM(HV0344), "
     + "SUM(HV0350),SUM(HV0351),SUM(HV0352),SUM(HV0353),SUM(HV0354),SUM(HV0355) "
     +"FROM moh731 join subpartnera on moh731.subpartnerid=subpartnera.subpartnerid WHERE "+facility+" art=1 && yearmonth="+maxYearMonth;
    conn.rs=conn.st.executeQuery(getCummulatives);
   if(conn.rs.next()==true){
System.out.println("entered to get culum]latives : "+maxYearMonth);
HV0301=conn.rs.getInt(1);
HV0302=conn.rs.getInt(2);
HV0303=conn.rs.getInt(3);
HV0304=conn.rs.getInt(4);
HV0305=conn.rs.getInt(5);
HV0306=conn.rs.getInt(6);
HV0307=conn.rs.getInt(7);
HV0314=conn.rs.getInt(8);
HV0315=conn.rs.getInt(9);
HV0316=conn.rs.getInt(10);
HV0317=conn.rs.getInt(11);
HV0318=conn.rs.getInt(12);
HV0319=conn.rs.getInt(13);
HV0328=conn.rs.getInt(14);
HV0329=conn.rs.getInt(15);
HV0330=conn.rs.getInt(16);
HV0331=conn.rs.getInt(17);
HV0332=conn.rs.getInt(18);
HV0333=conn.rs.getInt(19);
HV0334=conn.rs.getInt(20);
HV0335=conn.rs.getInt(21);
HV0336=conn.rs.getInt(22);
HV0337=conn.rs.getInt(23);
HV0338=conn.rs.getInt(24);
HV0339=conn.rs.getInt(25);
HV0340=conn.rs.getInt(26);
HV0341=conn.rs.getInt(27);
HV0342=conn.rs.getInt(28);
HV0343=conn.rs.getInt(29);
HV0344=conn.rs.getInt(30);    
HV0350=conn.rs.getInt(31);
HV0351=conn.rs.getInt(32);
HV0352=conn.rs.getInt(33);
HV0353=conn.rs.getInt(34);
HV0354=conn.rs.getInt(35);
HV0355=conn.rs.getInt(36);


    }
          
          
          System.out.println("Validity checker : "+isValidated);


       testing=receiving_results=receiving_postive_results="";    
          HIV_CT+="";
      testing="<table >"
                + "<tr>"
                + "<td colspan=\"5\"><br></td>"
                + "</tr>"
                + "<tr>"
                + "<td ></td><td colspan=\"4\"><b>1HIV COUNSELLING AND TESTING.</b></td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"5\"><br></td>"
                + "</tr>"
              + "<tr><td colspan=\"5\"><b>1.1 Testing.</b></td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">First</td><td >HV01-01</td><td colspan=\"2\" width=\"5%\" >"+HV0101+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Repeat</td><td >HV01-02</td><td colspan=\"2\">"+HV0102+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Total Tested (HVO0-01 plus HV01-02)</td><td >HV01-03</td><td colspan=\"2\">"+HV0103+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Couples</td><td >HV01-05</td><td colspan=\"2\">"+HV0105+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Static [Facility]</td><td >HV01-06</td><td colspan=\"2\">"+HV0106+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Outreach</td><td >HV01-07</td><td colspan=\"2\">"+HV0107+"</td>"
              + "</tr>"
              
              + "</table>"
              + "";
      
       receiving_results="<table style=\"margin-left:250px;\"><tr><td colspan=\"5\"><b>1.2 Receiving results - </b>(<i>Couples only</i>)</td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">Cocordant couples</td><td >HV01-08</td><td colspan=\"2\">"+HV0108+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Discordant couples</td><td >HV01-09</td><td colspan=\"2\">"+HV0109+"</td>"
              + "</tr>"
               + "</table></td>"
              + "";
       
        receiving_postive_results="<table style=\"margin-left:250px;\"><tr><td colspan=\"5\"><b>1.3 Receiving postive results.</b></td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">Males - Below 15 years</td><td >HV01-10</td><td colspan=\"2\">"+HV0110+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Females - Below 15 years.</td><td >HV01-11</td><td colspan=\"2\">"+HV0111+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Males 15-24 years </td><td >HV01-12</td><td colspan=\"2\">"+HV0112+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Females 15-24 years </td><td >HV01-13</td><td colspan=\"2\">"+HV0113+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Males 25 years and older</td><td >HV01-14</td><td colspan=\"2\">"+HV0114+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Females 25 years and older</td><td >HV01-15</td><td colspan=\"2\">"+HV0115+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"2\">Total receiving postive results <br>(Sum HV01-10 to HV01-15)</td><td >HV01-16</td><td colspan=\"2\">"+HV0116+"</td>"
              + "</tr>"
              + "</table>"
              + "";
      
             HIV_CT+=testing+""+receiving_results+""+receiving_postive_results+""; 
//              System.out.println("Loading new form for data entry.......................");

//    PMTCT################################################################
//               ##########################################################
       
        testing_for_HIV=HIV_Postive_results=partner_involvement=
        maternal_prophylaxis=assessment_ART=infant_testing=
        confirmed_infant=infant_feeding=infant_ARV="";
           
          testing_for_HIV="<table >"
                + "<tr>"
                + "<td colspan=\"5\"><br></td>"
                + "</tr>"
                + "<tr>"
                + "<td ></td><td colspan=\"4\"><b>2 PREVENTION OF MOTHER TO CHILD TRANSMISSION.</b></td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"5\"><br></td>"
                + "</tr>"
                
                  + "<tr><td colspan=\"5\"><b>2.1 Testing for HIV.</b></td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">Antenatal</td><td>HV02-01</td><td  colspan=\"2\"  width=\"1\" >"+HV0201+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td  colspan=\"2\">Labour and delivery.</td><td >HV02-02</td><td colspan=\"2\"><p >"+HV0202+"</p></td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Postnatal (within 72 hrs) </td><td >HV02-03</td><td colspan=\"2\">"+HV0203+"</td>"
              + "</tr>"
              + "<td colspan=\"2\">Total Tested (PMTCT) (Sum HV02-01 to HV02-03) </td colspan=\"2\"><td >HV02-04</td><td>"+HV0204+"</td>"
              + "</tr>"
             + "</table>"
              + "";
          
       HIV_Postive_results="<table ><tr><td colspan=\"5\"><b>2.2 HIV Postive Results.</b></td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">Known Postive status(at entry into ANC)</td><td >HV02-05</td><td colspan=\"2\">"+HV0205+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Antenatal</td><td >HV02-06</td><td colspan=\"2\">"+HV0206+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Labour and delivery.</td><td >HV02-07</td><td colspan=\"2\">"+HV0207+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Postnatal (within 72 hrs) </td><td >HV02-08</td><td colspan=\"2\">"+HV0208+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Total Tested (PMTCT)<br> (Sum HV02-05 to HV02-08) </td><td >HV02-09</td><td colspan=\"2\">"+HV0209+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"2\">Total with known status <br> (Sum HV02-04 to HV02-05) </td><td >HV02-10</td><td colspan=\"2\">"+HV0210+"</td>"
              + "</tr>"
              + "</table>"
              + "";   
          
          partner_involvement="<table ><tr><td colspan=\"5\"><b>2.3 Partner Involvement.</b></td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">Male partners tested (ANC/L&D)</td><td >HV02-11</td><td colspan=\"2\">"+HV0211+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Discordant couples</td><td >HV02-12</td><td colspan=\"2\">"+HV0212+"</td>"
              + "</tr>"
              + "</table>"
              + "";
          
          
        
           maternal_prophylaxis="<table ><tr><td colspan=\"5\"><b>2.4 Maternal Prophylaxis .</b>(<i>at first contact only</i>)</td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">Prophylaxis - NVP only </td><td >HV02-13</td><td colspan=\"2\">"+HV0213+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Prophylaxis - (AZT + SdNVP) </td><td >HV02-14</td><td colspan=\"2\">"+HV0214+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Prophylaxis -Interrupted HAART </td><td >HV02-15</td><td colspan=\"2\">"+HV0215+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">HAART (ART) </td><td >HV02-16</td><td colspan=\"2\">"+HV0216+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">total PMTCT prophylaxis (Sum HV02-13 to HV02-16) </td><td >HV02-17</td><td colspan=\"2\">"+HV0217+"</td>"
              + "</tr>"
              + "</table>"
              + "";
           
          assessment_ART="<table ><tr><td colspan=\"5\"><b>2.5 Assessment for ART Eligibility in MCH .</b>(<i>at diagnosis</i>)</td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">Assessed for eligibility at 1st ANC - WHO staging done </td><td >HV02-18</td><td colspan=\"2\">"+HV0218+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Assessed for eligibility at 1st ANC - CD4 </td><td >HV02-19</td><td colspan=\"2\">"+HV0219+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Assessed for eligibility in ANC (Sum HV02-18 to HV02-19) </td><td >HV02-20</td><td colspan=\"2\">"+HV0220+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Started on ART during ANC </td><td >HV02-21</td><td colspan=\"2\">"+HV0221+"</td>"
              + "</tr>"
              + "</table>"
              + "";
           
           infant_testing="<table ><tr><td colspan=\"5\"><b>2.6 Infant Testing</b> (<i>Initial tests only</i>)</td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">PCR (within 2 months) </td><td >HV02-24</td><td colspan=\"2\">"+HV0224+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">PCR (From 3 to 8 months) </td><td >HV02-25</td><td colspan=\"2\">"+HV0225+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Serology antibody test (from 9 to 12 months)  </td colspan=\"2\"><td >HV02-26</td><td colspan=\"2\">"+HV0226+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">PCR (from 9 to 12 months) </td><td >HV02-27</td><td colspan=\"2\">"+HV0227+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Total HEI Tested by 12 months (Sum HV02-24 to HV02-26) </td><td >HV02-28</td><td colspan=\"2\">"+HV0228+"</td>"
              + "</tr>"
              + "</table>"
              + "";
                   
          confirmed_infant="<table ><tr><td colspan=\"5\"><b>2.7 Confirmed Infant Test Results.</b> </td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">Postive - (within 2 months) -PCR </td><td >HV02-29</td><td colspan=\"2\">"+HV0229+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Postive - (From 3 to 8 months) -PCR </td><td >HV02-30</td><td colspan=\"2\">"+HV0230+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Postive -  (from 9 to 12 months) -PCR </td><td >HV02-31</td><td colspan=\"2\">"+HV0231+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Total Confirmed Postive (Sum HV02-29 to HV02-31) </td><td >HV02-32</td><td colspan=\"2\">"+HV0232+"</td>"
              + "</tr>"
              + "</table>"
              + "";
              
          infant_feeding="<table ><tr><td colspan=\"5\"><b>2.8 Infant feeding.</b> </td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">EBF (at 6 months)</td><td >HV02-33</td><td colspan=\"2\">"+HV0233+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">ERF (at 6 months) </td><td >HV02-34</td><td colspan=\"2\">"+HV0234+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">MF (at 6 months) </td><td >HV02-35</td><td colspan=\"2\">"+HV0235+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Total exposed aged 6 months. </td><td >HV02-36</td><td colspan=\"2\">"+HV0236+"</td>"
              + "</tr>"
             + "<tr>"
              + "<td colspan=\"2\">BF (12 months) </td><td >HV02-37</td><td colspan=\"2\">"+HV0237+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Not BF (12 months) </td><td >HV02-38</td><td colspan=\"2\">"+HV0238+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Not known </td><td >HV02-39</td><td colspan=\"2\">"+HV0239+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Total exposed aged 12 months (Sum HV02-37 to HV02-39) </td><td >HV02-40</td><td colspan=\"2\">"+HV0240+"</td>"
              + "</tr>"
              + "</table>"
              + "";
          
           infant_ARV="<table ><tr><td colspan=\"5\"><b>2.9 Infant ARV Prophlaxis</b> (<i>at first contact only</i>) </td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">Issued in ANC </td><td >HV02-41</td><td colspan=\"2\">"+HV0241+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Labour and Delivery </td><td >HV02-42</td><td colspan=\"2\">"+HV0242+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">PNC(<72hrs) </td><td >HV02-43</td><td colspan=\"2\">"+HV0243+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Total Infants Issued Prophylaxis (Sum HV02-41 to HV02-43) </td><td >HV02-44</td><td colspan=\"2\">"+HV0244+"</td>"
              + "</tr>"
              + "</table>"
              + "";
           
  //    ###############################CARE AND TREATMENT #################################
//               ##########################################################         
           
          PMTCT+=testing_for_HIV+""+HIV_Postive_results+""+partner_involvement+""+
        maternal_prophylaxis+""+assessment_ART+""+infant_testing+""+
        confirmed_infant+""+infant_feeding+""+infant_ARV;
          
         on_CP=enrolled_care=currently_care=starting_ART=revisit_ART=
        current_ART=cumulative_ART=cumulative_ARTPrevious=survival_ART=screening=pwp=HIV_care="";
         
         on_CP="<table >"
                 + "<tr>"
                + "<td colspan=\"5\"><br></td>"
                + "</tr>"
                 + "<tr>"
                + "<td ></td><td colspan=\"4\"><b>3 CARE AND TREATMENT.</b></td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"5\"><br></td>"
                + "</tr>"
                 
                 + "<tr><td colspan=\"5\"><b>3.1 On Cotrimoxazole Prophylaxis</b> </td></tr>"
              + "<tr>"
              + "<td>HIV Exposed Infant (within 2 months) </td><td >HV03-01</td><td>"+HV0301+"</td><td colspan=\"2\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td>HIV Exposed infant (Eligible for CTX at 2 months) </td><td >HV03-02</td><td>"+HV0302+"</td><td colspan=\"2\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td>On CTX - below 15 years</td><td >HV03-03(M)</td><td>"+HV0303+"</td> <td >HV03-04(F)</td>  <td>"+HV0304+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td>On CTX - 15 years & older </td><td >HV03-05(M)</td><td>"+HV0305+"</td> <td >HV03-06(F)</td>  <td>"+HV0306+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>On CTX - 15 years & olderTotal On CTX (Sum HV03-03 to HV03-06) </td><td >HV03-07</td><td>"+HV0307+"</td><td colspan=\"2\"></td>"
              + "</tr>"
              + "</table>"
              + "";
         
         enrolled_care="<table ><tr><td colspan=\"5\"><b>3.2 Enrolled in Care</b> </td></tr>"
              + "<tr>"
              + "<td>Enrolled in care - Below 1 year </td><td >HV03-08</td><td>"+HV0308+"</td><td colspan=\"2\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td>Enrolled in care - Below 15 year </td><td >HV03-09(M)</td><td>"+HV0309+"</td>  <td >HV03-10(F)</td>  <td>"+HV0310+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td>Enrolled in care - 15 years and older </td><td >HV03-11(M)</td><td>"+HV0311+"</td> <td >HV03-12(F)</td>  <td>"+HV0312+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Enrolled in care - Total (Sum HV03-09 to HV03-12) </td><td >HV03-13</td><td>"+HV0313+"</td><td colspan=\"2\"></td>"
              + "</tr>"
              + "</table>"
              + "";
         
         currently_care="<table ><tr><td colspan=\"5\"><b>3.3 Currently in Care</b>(<i>from the tally sheet-this month only and from last 2 months</i>) </td></tr>"
              + "<tr>"
              + "<td>Currently in care - Below 1 year </td><td >HV03-14</td><td>"+HV0314+"</td><td colspan=\"2\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td>Currently in care - Below 15 year </td><td >HV03-15(M)</td><td>"+HV0315+"</td>  <td >HV03-16(F)</td>  <td>"+HV0316+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td>Currently in care - 15 years and older </td><td >HV03-17(M)</td><td>"+HV0317+"</td> <td >HV03-18(F)</td>  <td>"+HV0318+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Currently in care - Total (Sum HV03-09 to HV03-12) </td><td >HV03-19</td><td>"+HV0319+"</td><td colspan=\"2\"></td>"
              + "</tr>"
              + "</table>"
              + "";
         
         starting_ART="<table ><tr><td colspan=\"5\"><b>3.4 Starting ART</b> </td></tr>"
              + "<tr>"
              + "<td>Starting ART - Below 1 year </td><td >HV03-20</td><td>"+HV0320+"</td><td colspan=\"2\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td>Starting ART - Below 15 year </td><td >HV03-21(M)</td><td>"+HV0321+"</td>  <td >HV03-22(F)</td>  <td>"+HV0322+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td>Starting ART - 15 years and older </td><td >HV03-23(M)</td><td>"+HV0323+"</td> <td >HV03-24(F)</td>  <td>"+HV0324+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Starting ART - Total (Sum HV03-21 to HV03-24) </td><td >HV03-25</td><td>"+HV0325+" </td><td colspan=\"2\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td>Starting - Pregnant </td><td >HV03-26</td><td>"+HV0326+"</td><td colspan=\"2\"></td>  "
              + "</tr>"
               + "<tr>"
              + "<td>Starting - TB Patient </td><td >HV03-27</td><td>"+HV0327+"</td><td colspan=\"2\"></td> "
              + "</tr>"
              + "</table>"
              + "";
         
         
         revisit_ART="<table ><tr><td colspan=\"5\"><b>3.5 Revisits on ART</b>(<i>from the tally sheet-this month only and from last 2 months</i>)</td></tr>"
              + "<tr>"
              + "<td>Revisits on ART - Below 1 year </td><td >HV03-28</td><td>"+HV0328+"</td><td colspan=\"2\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td>Revisits on ART - Below 15 year </td><td >HV03-29(M)</td><td>"+HV0329+"</td>  <td >HV03-30(F)</td>  <td>"+HV0330+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td>Revisits on ART - 15 years and older </td><td >HV03-31(M)</td><td>"+HV0331+"</td> <td >HV03-32(F)</td>  <td>"+HV0332+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Revisits on ART - Total (Sum HV03-29 to HV03-32) </td><td >HV03-33</td><td>"+HV0333+"</td><td colspan=\"2\"></td>"
              + "</tr>"
              + "</table>"
              + "";
         
          current_ART="<table ><tr><td colspan=\"5\"><b>3.6 Currently on ART [All]</b>(<i>Add 3.4 and 3.5 e.g HV03-34=HV03-20+HV03-28</i>) </td></tr>"
              + "<tr>"
              + "<td>Currently on ART - Below 1 year </td><td >HV03-34</td><td>"+HV0334+"</td><td colspan=\"2\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td>Currently on ART - Below 15 year </td><td >HV03-35(M)</td><td>"+HV0335+"</td>  <td >HV03-36(F)</td>  <td>"+HV0336+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td>Currently on ART - 15 years and older </td><td >HV03-37(M)</td><td>"+HV0337+"</td> <td >HV03-38(F)</td>  <td>"+HV0338+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Currently on ART - Total (Sum HV03-35 to HV03-38) </td><td >HV03-39</td><td>"+HV0339+"</td><td colspan=\"2\"></td>"
              + "</tr>"
              + "</table>"
              + "";
          
         cumulative_ART="<table ><tr><td colspan=\"5\"><b>3.7 Cumullative ever on ART </b> </td></tr>"
              + "<tr>"
              + "<td>Ever on ART - Below 15 year </td><td >HV03-40(M)</td><td>"+HV0340+"</td>  <td >HV03-41(F)</td>  <td>"+HV0341+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td>Ever on ART - 15 years and older </td><td >HV03-42(M)</td><td>"+HV0342+"</td> <td >HV03-43(F)</td>  <td>"+HV0343+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Ever on ART - Total (Sum HV03-40 to HV03-43) </td><td >HV03-44</td><td>"+HV0344+"</td><td colspan=\"2\"></td>"
              + "</tr>"
              + "</table>"
              + "";
          
         survival_ART="<table ><tr><td colspan=\"5\"><b>3.8 Survival and Retention on ART at 12 months </b> </td></tr>"
              + "<tr>"
              + "<td colspan=\"3\">ART Net cohort at 12 months </td><td >HV03-45</td><td>"+HV0345+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"3\">On Original 1st Line at 12 months </td><td >HV03-46</td><td>"+HV0346+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"3\">On alternative 1st Line at 12 months </td><td >HV03-47</td><td>"+HV0347+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"3\">On 2nd Line (or higher) at 12 months </td><td >HV03-48</td><td>"+HV0348+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"3\">On therapy at 12 months (Sum HV03-46 to HV03-48) </td><td >HV03-49</td><td>"+HV0349+"</td>"
              + "</tr>"
              + "</table>"
              + ""; 
         
            screening="<table ><tr><td colspan=\"5\"><b>3.9 Screening </b> </td></tr>"
             
               + "<tr>"
              + "<td>Screened for TB - Below 15 year </td><td >HV03-50(M)</td><td>"+HV0350+"</td>  <td >HV03-51(F)</td>  <td>"+HV0351+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Screened for TB - 15 years and older </td><td >HV03-52(M)</td><td>"+HV0352+"</td> <td >HV03-53(F)</td>  <td>"+HV0353+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Screened for TB - Total (Sum HV03-35 to HV03-38) </td><td >HV03-54</td><td>"+HV0354+"</td><td colspan=\"2\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td>Screened for cervical cancer (F 18+) </td><td >HV03-55</td><td>"+HV0355+"</td><td colspan=\"2\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
            
            pwp="<table ><tr><td colspan=\"5\"><b>3.10 Prevention with Postive </b> </td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">Modern Contraceptive methods </td><td >HV09-04</td><td colspan=\"2\">"+HV0904+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Provided with Condoms </td><td >HV09-05</td><td colspan=\"2\">"+HV0905+"</td>"
              + "</tr>"
              + "</table>"
              + ""; 
            
         HIV_care="<table ><tr><td colspan=\"5\"><b>3.11 HIV Care Visits </b> </td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">Females (18+) </td><td >HV03-70</td><td colspan=\"2\">"+HV0370+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Scheduled </td><td >HV03-71</td><td colspan=\"2\">"+HV0371+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"2\">Unscheduled </td><td >HV03-72</td><td colspan=\"2\">"+HV0372+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"2\">Total Visits (Sum HV03-71 to HV03-72) </td><td >HV03-73</td><td colspan=\"2\">"+HV0373+"</td>"
              + "</tr>"
              + "</table>"
              + "";   
            
         CT+=on_CP+""+enrolled_care+""+currently_care+""+starting_ART+""+revisit_ART+""+
        current_ART+""+cumulative_ART+""+cumulative_ARTPrevious+""+survival_ART+""+screening+""+pwp+""+HIV_care;
         
        number_circumcised=hiv_status=adverse_events;
        
        number_circumcised="<table style=\"margin-left:250px;\">"
                + "<tr>"
                + "<td colspan=\"5\"><br></td>"
                + "</tr>"
                + "<tr>"
                + "<td ></td><td colspan=\"4\"><b>4 VOLUNTARY MEDICAL MALE CIRCUMCISION.</b></td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"5\"><br></td>"
                + "</tr>"
                + "<tr><td colspan=\"5\"><b>4.1  Number Circumcised </b> </td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">0 - 14 </td><td >HV04-01</td><td colspan=\"2\">"+HV0401+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">14 - 24 </td><td >HV04-02</td><td colspan=\"2\">"+HV0402+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"2\">25+ </td><td >HV04-03</td><td colspan=\"2\">"+HV0403+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"2\">Total (Sum HV04-01 to HV04-02) </td><td >HV04-06</td><td colspan=\"2\">"+HV0406+"</td>"
              + "</tr>"
              + "</table>"
              + ""; 
        
        hiv_status="<table style=\"margin-left:250px;\"><tr><td colspan=\"5\"><b>4.2  HIV Status (at circumcision) </b> </td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">Postive </td><td >HV04-07</td><td colspan=\"2\">"+HV0407+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Negative </td><td >HV04-08</td><td colspan=\"2\">"+HV0408+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"2\">Unknown </td><td >HV04-09</td><td colspan=\"2\">"+HV0409+"</td>"
              + "</tr>"
              + "</table>"
              + ""; 
        
        adverse_events="<table style=\"margin-left:250px;\"><tr><td colspan=\"5\"><b>4.3  Adverse events (Circumcision) </b> </td></tr>"
              + "<tr>"
              + "<td colspan=\"2\">During AE(s) moderate </td><td >HV04-10</td><td colspan=\"2\">"+HV0410+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">During AE(s) severe  </td><td >HV04-11</td><td colspan=\"2\">"+HV0411+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"2\">Post AE(s) moderate  </td><td >HV04-12</td><td colspan=\"2\">"+HV0412+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"2\">Post AE(s) severe  </td><td >HV04-13</td><td colspan=\"2\">"+HV0413+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"2\">Total AE During (Sum HV04-10  & HV04-11) </td><td>HV04-14</td><td colspan=\"2\">"+HV0414+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"2\">Total AE Post (Sum HV04-12  & HV04-13) </td><td >HV04-15</td><td colspan=\"2\">"+HV0415+"</td>"
              + "</tr>"
              + "</table>"
              + "";
        
        VMMC+=number_circumcised+""+hiv_status+""+adverse_events;
         
        type_exposure=provided_p="";
       
        type_exposure="<table style=\"border:1px; border-collapse:collapse;\">"
                + "<tr>"
                + "<td colspan=\"5\"><br></td>"
                + "</tr>"
                + "<tr>"
                + "<td ></td><td colspan=\"4\"><b>5 POST-EXPOSURE PROPHYLAXIS.</b></td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"5\"><br></td>"
                + "</tr>"
                + "<tr><td colspan=\"5\"><b>5.1 Type of exposure </b> </td></tr>"
              + "<tr style=\"border-bottom:1pt solid black;\">"
              + "<td>Occupational </td><td >HV05-01(M)</td><td>"+HV0501+"</td>  <td >HV05-02(F)</td>  <td>"+HV0502+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Sexual assault </td><td >HV05-03(M)</td><td>"+HV0503+"</td>  <td >HV05-04(F)</td>  <td>"+HV0504+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Other reasons </td><td >HV05-05(M)</td><td>"+HV0505+"</td> <td >HV05-06(F)</td>  <td>"+HV0506+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Total  </td><td >HV05-07</td><td>"+HV0507+"</td><td colspan=\"2\"></td>"
              + "</tr>"
              + "</table>"
              + "";
                
           provided_p ="<table style=\"\"><tr><td colspan=\"5\"><b>5.2 Provided with Prophylaxis </b> </td></tr>"
              + "<tr>"
              + "<td>Occupational </td><td >HV05-08(M)</td><td>"+HV0508+"</td>  <td >HV05-09(F)</td>  <td>"+HV0509+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Sexual assault </td><td >HV05-10(M)</td><td>"+HV0510+"</td>  <td >HV05-11(F)</td>  <td>"+HV0511+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Other reasons </td><td >HV05-12(M)</td><td>"+HV0512+"</td> <td >HV05-13(F)</td>  <td>"+HV0513+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Total PEP  </td><td >HV05-14</td><td><p border=\"1px;\">"+HV0514+"</p></td><td colspan=\"2\"></td>"
              + "</tr>"
              + "</table>"
              + "";
           
           
                
        PEP+=type_exposure+""+provided_p;
        
        blood_safety="";
        
         blood_safety="<table style=\"margin-left:250px;\">"
                 + "<tr>"
                + "<td colspan=\"5\"><br></td>"
                + "</tr>"
                  + "<tr>"
                + "<td ></td><td colspan=\"4\"><b>6 BLOOD SAFETY.</b></td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"5\"><br></td>"
                + "</tr>"
                 + "<tr><td colspan=\"5\"><b>Blood Safety </b> </td></tr>"
              + "<tr>"
              + "<td colspan=\"3\">Donated Blood Units </td><td >HV06-01</td><td>"+HV0601+"</td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"3\">Blood Units screened for TTIs </td><td >HV06-02</td><td>"+HV0602+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td colspan=\"3\">Blood units reactive to HIVH </td><td >HV06-05</td><td>"+HV0605+"</td>"
              + "</tr>"
              + "</table>"
              + ""; 
        
        Blood+=blood_safety;       
          
          
//          }
          

          
//          HIV_CT+="</div></div></div>";
//        PMTCT+="</div></div></div>";
//        CT+="</div></div></div>";
//        VMMC+="</div></div></div>";
//        PEP+="</div></div></div>";
//        Blood+="</div></div></div>";
      
//        data+=""+enterdby+"<br><br>"+tabs;
        
//       data+=PMTCT;
//       data+=CT;
//       data+=PEP;
//        data+="<p style=\"font-size:7px;\">"+header+""+PMTCT+""+CT+""+PEP+"</p>";
        
        
        header="<table>"
                + "<tr>"
                + "<td colspan=\"5\">National AIDS & STIs Control Programme.</td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"5\">MOH 731 - Comprehensive HIV/AIDS Facility Reporting Form - NASCOP.</td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"5\">"+headerInfo+"</td>"
                + "</tr>"
               + "</table>";
        
        
       data+="<table style=\"color:black; font-size:6px;\" width=\"90%\">"
              + "<tr>"
               + "<td>"+header+""+PMTCT+""+CT+""+PEP+"</td>"
               + "</tr>"
               + "</table>"
               + "";
        
    data+=" </div></body></html>";
   System.out.println( "PDF Created!" );

   
   IdGenerator IG = new IdGenerator();
        createdOn=IG.CreatedOn();
   
        Document document = new Document();
  ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
PdfWriter.getInstance(document, outByteStream);
      document.open();
      
      HTMLWorker htmlWorker = new HTMLWorker(document);
       htmlWorker.parse(new StringReader(data));
       
       CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(false);  
cssResolver.addCss("", true);

   document.close();    
response.setContentType("application/pdf");
response.setContentLength(outByteStream.size());
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=MOH731_"+url.trim().replace(" ", "")+"_CREATED_ON_"+createdOn+".pdf");

ServletOutputStream sos;
sos = response.getOutputStream();
outByteStream.writeTo(sos);
sos.flush();
    
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
        Logger.getLogger(pdf731.class.getName()).log(Level.SEVERE, null, ex);
    } catch (DocumentException ex) {
        Logger.getLogger(pdf731.class.getName()).log(Level.SEVERE, null, ex);
    } catch (CssResolverException ex) {
        Logger.getLogger(pdf731.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(pdf731.class.getName()).log(Level.SEVERE, null, ex);
    } catch (DocumentException ex) {
        Logger.getLogger(pdf731.class.getName()).log(Level.SEVERE, null, ex);
    } catch (CssResolverException ex) {
        Logger.getLogger(pdf731.class.getName()).log(Level.SEVERE, null, ex);
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

