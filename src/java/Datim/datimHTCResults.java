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
 * @author Emmanuel E
 */
public class datimHTCResults extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            dbConn conn = new dbConn();
            
            String month = "";
            String year = "";
            String facil = "361";
            String form = "moh711";
            
//=====================================================================================================
            year = "2015";
            month = "5";
            String county = "";
            String header = "";
            
            
            
            String subheaders[]={"Tested","Positive","Negative"};
            String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Type Of Support","Antenatal Clinic","","","Labour & Delivery","","","Under 5 Clinic","","","Postnatal","","","TB_STAT","","","Sexually Transmitted Infections","","","Outpatient Department","","","Inpatient","","","Hiv Care and Treatment Clinic","","","Voluntary Medical Male Circumcission","","","Voluntary Counselling & Testing (Standalone)","","","Voluntary Counselling & Testing (Standalone)","","","Mobile","","","Home-based","","","Other","",""};
            
            String reportType = "";
            if (request.getParameter("reportType") != null) {
                reportType = request.getParameter("reportType");
            }
            String reportDuration = "";
            if (request.getParameter("reportDuration") != null) {
                reportDuration = request.getParameter("reportDuration");
            }
            
            if (request.getParameter("year") != null) {
                year = request.getParameter("year");
            }
            
            if (request.getParameter("facility") != null && reportType.equals("2")) {
                try {
                    facil = request.getParameter("facility");
                    
                    String getfacil = "select SubPartnerNom,CentreSanteId as mflcode from subpartnera where SubPartnerID='" + facil + "'";
                    conn.rs = conn.st.executeQuery(getfacil);
                    
                    while (conn.rs.next()) {
                        
                        header += " FACILITY : " + conn.rs.getString(1).toUpperCase() + "     MFL CODE  :  " + conn.rs.getString(2) + "  ";
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            if (request.getParameter("county") != null && reportType.equals("2")) {
                try {
                    county = request.getParameter("county");
                    
                    String getcounty = "select County from county where CountyID='" + county + "'";
                    conn.rs = conn.st.executeQuery(getcounty);
                    
                    while (conn.rs.next()) {
                        
                        header += " COUNTY : " + conn.rs.getString(1).toUpperCase() + " ";
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            if (request.getParameter("month") != null && reportDuration.equals("4")) {
                try {
                    month = request.getParameter("month");
                    
                    String getmonth = "select name as monthname from month where id='" + month + "'";
                    conn.rs = conn.st.executeQuery(getmonth);
                    
                    while (conn.rs.next()) {
                        
                        header += " MONTH : " + conn.rs.getString(1).toUpperCase() + "";
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            header += " YEAR : " + year + "";
            
            String facilitywhere = "";
            String yearwhere = "";
            String monthwhere = "";
            String countywhere = "";
            String duration = "";
            String semi_annual = "";
            String quarter = "";
            String tbstatduration="";
            //==================================================================================================
            //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            int yearcopy = Integer.parseInt(year);
            
//        reportType="2";
//        year=2015;
//        reportDuration="3";
            String yearmonth = "" + year;
            int prevYear = yearcopy - 1;
            int maxYearMonth = 0;
            int monthcopy = 0;
//        GET REPORT DURATION============================================
            //annually
            if (reportDuration.equals("1")) {
                yearmonth = "Annual Report For " + year;
                duration = " " + form + ".yearmonth BETWEEN " + prevYear + "10 AND " + year + "09";
                tbstatduration="year='"+year+"'";
            } else if (reportDuration.equals("2")) {
                semi_annual = request.getParameter("semi_annual");
//        semi_annual="2";
                if (semi_annual.equals("1")) {
                    yearmonth = "Semi Annual Report For " + prevYear + " Oct to " + year + " Mar";
                    duration = " " + form + ".yearmonth BETWEEN " + prevYear + "10 AND " + year + "03";
                     tbstatduration="year='"+year+"' and (quarter='1' || quarter='2') ";
                } else {
                    yearmonth = "Semi Annual Report for Apr to  Sep " + year;
                    duration = " " + form + ".yearmonth BETWEEN " + year + "04 AND " + year + "09";
                     tbstatduration="year='"+year+"' and (quarter='3' || quarter='4') ";
                }
            } else if (reportDuration.equals("3")) {
                try {
                    
                    //quarterly
                    String startMonth, endMonth;
                    quarter = request.getParameter("quarter");
                    //       quarter="3";
                    
                     tbstatduration="year='"+year+"' and quarter='"+quarter+"'  ";
                     
                    String getMonths = "SELECT months,name FROM quarter WHERE id='" + quarter + "'";
                    conn.rs = conn.st.executeQuery(getMonths);
                    if (conn.rs.next() == true) {
                        
                        try {
                            String months[] = conn.rs.getString(1).split(",");
                            startMonth = months[0];
                            endMonth = months[2];
                            if (quarter.equals("1")) {
                                duration = " " + form + ".yearmonth BETWEEN " + prevYear + "" + startMonth + " AND " + prevYear + "" + endMonth;
                                yearmonth = "Quarterly Report For " + prevYear + " " + conn.rs.getString(2);
                            } else {
                                yearmonth = "Quarterly Report For " + year + " (" + conn.rs.getString(2) + ")";
                                duration = " " + form + ".yearmonth BETWEEN " + year + "" + startMonth + " AND " + year + "" + endMonth;
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (reportDuration.equals("4")) {
                monthcopy = Integer.parseInt(request.getParameter("month"));
                
                //since we dont want data to appear for monthly reports, we set an impossible 
                tbstatduration=" 1=2 "; 
//     month=5;
                if (monthcopy >= 10) {
                    
                    yearmonth = "Monthly Report For " + prevYear + "_(" + month + ")";
                    duration = " " + form + ".yearmonth=" + prevYear + "" + month;
                } else {
                    duration = " " + form + ".yearmonth=" + year + "0" + month;
                    yearmonth = "Monthly Report For " + year + "_(" + month + ")";
                }
            } else {
                duration = "";
            }
            
            //======================================================================
//==================================================================================================
            //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            String subcountywhere = "";
            
            String subcounty = "";
            
            if (!request.getParameter("subcounty").equals("")) {
                
                subcounty = request.getParameter("subcounty");
                
            }
            
            String getexistingdata = "";
            
            if (!county.equals("")) {
                
                countywhere = " and district.countyid = '" + county + "'";
                
            }
            
            if (!subcounty.equals("")) {
                
                subcountywhere = " and subpartnera.DistrictID = '" + subcounty + "'";
                
            }
            
            if (!facil.equals("")) {
                
                facilitywhere = " and " + form + ".SubPartnerID = '" + facil + "'";
                
            }
            
            String joinedwhwere = " where 1=1 " + yearwhere + " && " + duration + " " + countywhere + " " + subcountywhere;
            
            //getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 left join moh731 on moh731.id=moh711.id left join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county  union select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 right join moh731 on moh731.id=moh711.id right join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county";
            getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 left join moh731 on moh731.id=moh711.id left join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID   union select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 right join moh731 on moh731.id=moh711.id right join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county";
            System.out.println(getexistingdata);
              String Tbid=year+"_"+quarter+"_"+facil;
           // String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration;
            
            
          String distincttbstatfacils="select distinct(SubPartnerID) as partnerid from  tb_stat_art WHERE "+tbstatduration;  
            
      ArrayList tbstat= new ArrayList();  
      
     conn.rs1=conn.st1.executeQuery(distincttbstatfacils);
     
     
     while(conn.rs1.next())
     {
     tbstat.add(conn.rs1.getString(1));     
     }
     
     
//=====================================================================================================
//=====================================================================================================    
//______________________________________________________________________________________
            //                       NOW CREATE THE WORKSHEETS
            //______________________________________________________________________________________
            HSSFWorkbook wb = new HSSFWorkbook();
            
            //______________________________________________________________________________________
            //______________________________________________________________________________________
            HSSFFont font = wb.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("Cambria");
            font.setColor((short) 0000);
            CellStyle style = wb.createCellStyle();
            style.setFont(font);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
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
            
            HSSFSheet shet = wb.createSheet("HTC RESULTS BY SDP");
            
            
            int rowpos=0;
            //create headers for that worksheet
            HSSFRow rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            HSSFCell cl0 = rw.createCell(0);
            cl0.setCellValue("HTC Results BY Service Delivery Point and Test Result(Required) " + yearmonth);
            cl0.setCellStyle(style);
            rowpos++;
            for (int a = 1; a < sectionheaders.length; a++) {
                HSSFCell clx = rw.createCell(a);
                clx.setCellValue("");
                clx.setCellStyle(style);
                shet.setColumnWidth(a, 5000);
            }
            
            
            
            HSSFRow rw1 = shet.createRow(rowpos);
            rw1.setHeightInPoints(38);
            rowpos++;
            
            for (int a = 0; a <sectionheaders.length; a++) {
                HSSFCell clx = rw1.createCell(a);
                clx.setCellValue(sectionheaders[a]);
                clx.setCellStyle(stylex);
              
                if(a>4&&a<sectionheaders.length){
                    if(sectionheaders[a].equals("")){}
                    else {
                        shet.addMergedRegion(new CellRangeAddress(1,1,a,a+2));
                    }
                }
            }
            
            
            shet.addMergedRegion(new CellRangeAddress(0,0,0,sectionheaders.length-1));
            
            shet.setColumnWidth(0, 5000);
            
            //add section 2
            HSSFRow rw2 = shet.createRow(rowpos);
            rw2.setHeightInPoints(25);
            
            for (int a = 0; a <5; a++) {
                HSSFCell clx = rw2.createCell(a);
                clx.setCellValue("");
                clx.setCellStyle(stylex);
                shet.addMergedRegion(new CellRangeAddress(rowpos-1,rowpos,a,a));
                                       }
            
            int b=0;
            for (int a = 5; a <sectionheaders.length; a++) {
                HSSFCell clx = rw2.createCell(a);
                clx.setCellValue(subheaders[b]);
                b++;
                if(b==3){b=0;}
                clx.setCellStyle(stylex);
                
                                                            }
            rowpos++;
          
            conn.rs= conn.st.executeQuery(getexistingdata);
            while(conn.rs.next()){
                int colpos=0; 
           int conpos=1; 
               HSSFRow rwx = shet.createRow(rowpos); 
               rwx.setHeightInPoints(25);
               
               //county
               if(1==1){
                       
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                //subcounty
                 if(1==1){
                        
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                 
                 //facility name
                   if(1==1){
                      
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                   //mfl
                   if(1==1){
                         
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
//support type//######################################################################################
                    if(1==1){
                         
 String support="NA";
 if(conn.rs.getString("HTC_Support1")==null||conn.rs.getString("HTC_Support1").equals("")){
    if(conn.rs.getString("PMTCT_Support")!=null&&!conn.rs.getString("PMTCT_Support").equals("null")){              
 support=conn.rs.getString("PMTCT_Support");
    }         
                  }
 else {
    
  support=conn.rs.getString("HTC_Support1");
 }
   System.out.println("______:"+conn.rs.getString("HTC_Support1")+":__"+support);                
                  
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(support);
                clx.setCellStyle(style2);
               
            colpos++;
            //skip both pmtct support s
            conpos++;
            conpos++;
                           
               } 
                
 
                    
   //____________________Totals begin here______________                 
                    
                   //Antenatal
                   if(1==1){
//sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("HV0206");
                tested=conn.rs.getInt("HV0201");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }                 
             
                    //Labour & Delivery
                   if(1==1){
//,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("HV0207");
                tested=conn.rs.getInt("HV0202");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }       
                
              
                     //Under 5 Clinic
                   if(1==1){
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("HV0232");
                tested=conn.rs.getInt("HV0228");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }       
                
              
                   
                   
                     //Post Natal
                   if(1==1){
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("HV0208");
                tested=conn.rs.getInt("HV0203");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }       
         
                   
                   
                   
//-------------------------------------------------------------------------------------------------------------------
                   
      //TB Stat
      if(1==1){
          int tested=0;
                int positive=0;
                int negative=0;
          String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration+" and  SubPartnerID='"+conn.rs.getString("SubPartnerID")+"'";
           conn.rs1=conn.st1.executeQuery(getstat);
           
           if(conn.rs1.next()){
           
              positive=conn.rs1.getInt("positive");
              negative=conn.rs1.getInt("negative");
              tested=negative+positive;
              //remove the current facility id
           if(tbstat.contains(conn.rs.getString("SubPartnerID"))){
           tbstat.remove(conn.rs.getString("SubPartnerID"));
           }    
           
           
                              }
          
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                
                
             
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
               if(!reportDuration.equals("4")){                          
                clx.setCellValue(tested);
                                                }
                else {
                clx.setCellValue("");
                     }
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                   HSSFCell clx1 = rwx.createCell(colpos);   
                if(!reportDuration.equals("4")){                          
                clx1.setCellValue(positive);
                                                }
                else {
                clx1.setCellValue("");
                     }
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                if(!reportDuration.equals("4")){                          
                clx2.setCellValue(negative);
                                                }
                else {
                clx2.setCellValue("");
                     }
                clx2.setCellStyle(style2);               
                colpos++;
                
               }                     
                   
                   
//-------------------------------------------------------------------------------------------------------------------                   
 //sexually transmitted insfections          
       if(1==1){
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                //positive=conn.rs.getInt("HV0208");
                //tested=conn.rs.getInt("HV0203");
                //negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }       
      //Outpatient
              if(1==1){
// sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("DTCC_HIV_Out_Tot");
                tested=conn.rs.getInt("DTCB_Test_Out_Tot");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }             
            
              
              //Inpatient
              if(1==1){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("DTCC_HIV_In_Tot");
                tested=conn.rs.getInt("DTCB_Test_In_Tot");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }             
            
              //HIV Care and Treatment
              if(1==1){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
               // positive=conn.rs.getInt("DTCC_HIV_In_Tot");
                //tested=conn.rs.getInt("DTCB_Test_In_Tot");
               // negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }        
               
              
              
              
              
              //VMMC
              if(1==1){
//     sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("P511KP");
                negative=conn.rs.getInt("P511KN");
                tested=negative+positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }        
              
             
              
                 //VCT  (Co-located)
              if(1==1){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("VCTClient_HIV_TOT");
                tested=conn.rs.getInt("VCTClient_Tested_TOT");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }        
               
             
           
              //Voluntary counselling and testing (Stand alone)
              if(1==1){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
               // positive=conn.rs.getInt("");
                //tested=conn.rs.getInt("");
                //negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }  
              
              //Mobile 
              if(1==1){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
               // positive=conn.rs.getInt("");
                //tested=conn.rs.getInt("");
                //negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }  
              
                //Home Based  
              if(1==1){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
               // positive=conn.rs.getInt("");
                //tested=conn.rs.getInt("");
                //negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }  
              
              
              
              
              //Other
              if(1==1){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
               // positive=conn.rs.getInt("");
                //tested=conn.rs.getInt("");
                //negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }  
              
              
               rowpos++;    
            }
            
            
            
          //now check if any facilities were skipped 
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            
            System.out.println("_____======______"+tbstat.size());
            
            
            for(int a=0;a<tbstat.size();a++)
            {
        
             System.out.println("%%%%%======______RoWno::"+tbstat.get(a));    
                
   
                
                if(1==1){
                
               int colpos=0; 
               int conpos=1; 
               HSSFRow rwx = shet.createRow(rowpos); 
               rwx.setHeightInPoints(25); 
          
               
           String getstat=    "select  county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,supporttype, sum(positive) as positive ,sum(negative) as negative, tb_stat_art.SubPartnerID as SubPartnerID from tb_stat_art  join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on tb_stat_art.SubPartnerID = subpartnera.SubPartnerID  WHERE "+tbstatduration+" and  tb_stat_art.SubPartnerID='"+tbstat.get(a)+"'";
           conn.rs=conn.st.executeQuery(getstat);
           
           if(conn.rs.next()){      
               
            //county
               if(1==1){
                       
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                //subcounty
                 if(1==1){
                        
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                 
                 //facility name
                   if(1==1){
                      
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                   //mfl
                   if(1==1){
                         
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
//support type//######################################################################################
                    if(1==1){
                         
 String support="NA";

    
  support=conn.rs.getString("supporttype");
 
             
                  
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(support);
                clx.setCellStyle(style2);
               
            colpos++;
            //skip both pmtct support s
            conpos++;
          
                           
               } 
                
    //enter blanks in columns from Facility type  up to the tb column
                    
                      if(1==1){
                 for(int c=5;c<17;c++){     
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue("");
                clx.setCellStyle(style2);               
            colpos++;
            
                 }     
               } 
               
               
               
               
               
               
         //tb stat     
               
               
               if(1==1){
               
                int tested=0;
                int positive=0;
                int negative=0;
          // String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration+" and  SubPartnerID='"+tbstat.get(a)+"'";
          
         
           
              positive=conn.rs.getInt("positive");
              negative=conn.rs.getInt("negative");
              tested=negative+positive;
               
           
           
                              //}
          
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                
                
             
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
               if(!reportDuration.equals("4")){                          
                clx.setCellValue(tested);
                                                }
                else {
                clx.setCellValue("");
                     }
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                   HSSFCell clx1 = rwx.createCell(colpos);   
                if(!reportDuration.equals("4")){                          
                clx1.setCellValue(positive);
                                                }
                else {
                clx1.setCellValue("");
                     }
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                if(!reportDuration.equals("4")){                          
                clx2.setCellValue(negative);
                                                }
                else {
                clx2.setCellValue("");
                     }
                clx2.setCellStyle(style2);               
                colpos++; 
                
                
                }//end of tbstat if query
               
         
               //finish posting blanks to the remaining columns
               
                    if(1==1){
                 for(int c=20;c<50;c++){     
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue("");
                clx.setCellStyle(style2);               
            colpos++;
           // conpos++;
                 }     
               } 
               
               
               
               
               
               
               
           }//end of if query
               
               
               
               
                }//end of if 1==1
                
                
                
                
                System.out.println("____"+tbstat.get(a));    
            
                rowpos++;
                
            }//end of for loop
            
            
            
            
            
            
            
            
            
         if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}
         if(conn.rs2!=null){ conn.rs2.close();}
         if(conn.st!=null){ conn.st.close();}
         if(conn.st2!=null){ conn.st2.close();}
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            IdGenerator IG = new IdGenerator();
            String createdOn = IG.CreatedOn();
            
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            wb.write(outByteStream);
            byte[] outArray = outByteStream.toByteArray();
            response.setContentType("application/ms-excel");
            response.setContentLength(outArray.length);
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setHeader("Content-Disposition", "attachment; filename=HTC_RESULTS_BY_SDP_Generatted_On_" + createdOn + ".xls");
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
            outStream.close();
        } catch (SQLException ex) {
            Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
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
