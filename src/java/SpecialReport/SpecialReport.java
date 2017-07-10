/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpecialReport;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author GNyabuto
 */
public class SpecialReport extends HttpServlet {
HttpSession session;
String start_date,end_date;
String[] program_areas = new String[10];
String data_element,form,rule,main_query,group_by,having_query,where,query,subpartnera_column;
int is_special,element_id;
String County, DistrictNom,SubPartnerNom,constituency,ward,mfl_code,
            GSN,ART_Support,PMTCT_Support,HTC_Support1,Type,ART_highvolume,PMTCT_highvolume,
            HTC_highvolume,latitude,longitude,yearmonth;
ArrayList data = new ArrayList();
    ArrayList<String> titles4 = new ArrayList<String>();
    String db_year,semi_annual,quarter;
    String group_period,organization_unit,age_set;
    int current_column;
    int only_totals;
    String specific_elements;
    int header_startcell,headercells,end_of_basic_info;
    boolean ischecked;
    ArrayList dates = new ArrayList();
    String specific_facility="";
    String current_care_art="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
            session=request.getSession();
            dbConn conn = new dbConn();
            titles4.clear();
            dates.clear();
            
            ischecked=false;
            current_care_art=",6,7,51,54,"; // holds query ids for the current in care and current in ART. We only take data for the last month
            specific_facility="";
//            RECEIVE PASSED PARAMETERS

       String year = request.getParameter("year");
       String startdate = request.getParameter("startdate");
       String enddate = request.getParameter("enddate");
       String customdate = request.getParameter("customdate");
       String reportDuration = request.getParameter("reportDuration");
       
       String reportType = request.getParameter("reportType");
       
       String county = request.getParameter("county");
       String subcounty = request.getParameter("subcounty");
       String facility = request.getParameter("facility");
       String[] elements = request.getParameterValues("elements");
       
       age_set = Arrays.toString(request.getParameterValues("agebrackets"));
       organization_unit=request.getParameter("orgunit");
       group_period = request.getParameter("groupby");
       
       if(reportType.equals("2")){
       if(!facility.equals("")){
       specific_facility=" and subpartnera.SubPartnerID="+facility;    
       }
       else if(!subcounty.equals("")){
       specific_facility=" and district.DistrictID="+subcounty;    
       }
       else if(!county.equals("")){
       specific_facility=" and county.CountyID="+county;    
       }
       else{
        specific_facility="";   
       }
       }
       
       
         if(customdate!=null) {
             ischecked=true;
         }
         
        group_by=" GROUP BY ";
          
            
        program_areas=request.getParameterValues("programarea");
//        program_areas[0]="Special Elements";
//        program_areas[1]="HTS";
//        program_areas[2]="PMTCT";
//        start_date="201501";
//        end_date="201704";
//        age_set = "datim";
              
        
//      for specifically added elements
       int elem_added=0;
        specific_elements="";
        header_startcell = headercells = 0;
        
        specific_elements+="and (";
        
//        loop through while adding specific elements
        if(elements!=null){
            for(String elem:elements){
                if(!elem.equals("all")){
                    specific_elements+=" id="+elem+" or ";
                    elem_added++;
                }
            }
    }
//        end of loop

        specific_elements+=")";
        
       specific_elements=specific_elements.replace("or )", " )"); 
        
       if(elem_added==0){
        specific_elements="";   
       }
       
       
        if(ischecked){
        start_date=startdate.replace("-", "");
        end_date=enddate.replace("-", "");
        String datebtn = start_date+"-"+end_date;
        dates.add(datebtn);
        }
        else{
         if(reportDuration.equals("1") ){ //annual
         start_date=year+"0101";
         end_date=year+"1231"; 
         String datebtn = start_date+"-"+end_date;
         dates.add(datebtn);
         group_period="annual";
         } 
         if(reportDuration.equals("2") ){ //semi-annual
         String semi_annual = Arrays.toString(request.getParameterValues("semi_annual"));
         
         if(semi_annual.contains("1")){
         start_date=(Integer.parseInt(year)-1)+"1001";        
         end_date=year+"0331"; 
         String datebtn = start_date+"-"+end_date;
         dates.add(datebtn);
         }
         else if (semi_annual.contains("2")){
         start_date=year+"0401";        
         end_date=year+"0931"; 
         String datebtn = start_date+"-"+end_date;
         dates.add(datebtn);
         }
         group_period="semiannual";
         }
        if(reportDuration.equals("3") ){ //Quarterly
          String quarter = Arrays.toString(request.getParameterValues("quarter"));
          if(quarter.contains("1")){
           start_date=(Integer.parseInt(year)-1)+"1001";        
           end_date=(Integer.parseInt(year)-1)+"1231";
           String datebtn = start_date+"-"+end_date;
           dates.add(datebtn);
         } 
          if(quarter.contains("2")){
          start_date=year+"0101";        
          end_date=year+"0331";
          String datebtn = start_date+"-"+end_date;
          dates.add(datebtn);
         } 
          if(quarter.contains("3")){
          start_date=year+"0401";        
          end_date=year+"0631";
          String datebtn = start_date+"-"+end_date;
          dates.add(datebtn);     
         } 
          if(quarter.contains("4")){
          start_date=year+"0701";        
          end_date=year+"0931";
          String datebtn = start_date+"-"+end_date;
          dates.add(datebtn);     
         } 
        group_period="quarter";
        }
         if(reportDuration.equals("4") ){ //Monthly
          String[] month = request.getParameterValues("month");
          for (String mnt: month){
             int mon=Integer.parseInt(mnt);
             if(mon<10){
             start_date=year+"0"+mon+"01";
             end_date=year+"0"+mon+"31";
             String datebtn = start_date+"-"+end_date;
             dates.add(datebtn);
             }
             else{
             start_date = (Integer.parseInt(year)-1)+""+mon+"01";
             end_date = (Integer.parseInt(year)-1)+""+mon+"31";
             String datebtn = start_date+"-"+end_date;
             dates.add(datebtn);    
             }
          }
          group_period="month";
         } 
          
        }

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
            style2.setWrapText(true);
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

   
   HSSFSheet shet=wb.createSheet("Detailed Report");   
     
    //create headers for that worksheet
       
        if(organization_unit.equals("County")){
        group_by +=" County,";   
        }
//        else if(organization_unit.equals("subcounty")){
//        group_by +=" County,DistrictNom,";       
//        }
        else if(organization_unit.equals("Sub-County")){
        group_by +=" County,DistrictNom,constituency,";     
        }
        else if(organization_unit.equals("Ward")){
        group_by +=" County,DistrictNom,constituency,ward,";      
        }
        else if(organization_unit.equals("Facility")){
        group_by +=" County,DistrictNom,constituency,ward,SubPartnerNom,";     
        }
        
        if(group_period.equals("annual")){
        group_by +=" year,";        
        }
        else if(group_period.equals("semiannual")){
         group_by +=" year,semi_annual,";       
        }
        else if(group_period.equals("quarter")){
        group_by +=" year,semi_annual,quarter,";        
        }
        else if(group_period.equals("month")){
        group_by +=" year,semi_annual,quarter,yearmonth,";        
        }
       
        
        
        if(group_by.contains("County")){
        titles4.add("County");
        header_startcell++;
        }
        if(group_by.contains("DistrictNom")){
        titles4.add("DistrictNom");
        header_startcell++;
        }
        if(group_by.contains("constituency")){
        titles4.add("Constituency");
        header_startcell++;
        }
        if(group_by.contains("ward")){
        titles4.add("Ward");
        header_startcell++;
        }
        if(group_by.contains("SubPartnerNom")){
        titles4.add("Health Facility");
        titles4.add("mfl_code");
        titles4.add("GSN");
        titles4.add("ART_Support");
        titles4.add("PMTCT_Support");
        titles4.add("HTC_Support1");
        titles4.add("Type");
        titles4.add("ART_highvolume");
        titles4.add("PMTCT_highvolume");
        titles4.add("HTC_highvolume");
        titles4.add("latitude");
        titles4.add("longitude");
        header_startcell+=12;
        }
        if(group_by.contains("year")){
        titles4.add("Year");
        header_startcell++;
        }
        if(group_by.contains("semi_annual")){
        titles4.add("Semi Annual");
        header_startcell++;
        }
        if(group_by.contains("quarter")){
        titles4.add("Quarter");
        header_startcell++;
        }
        if(group_by.contains("yearmonth")){
        titles4.add("Yearmonth");
        header_startcell++;
        }
        titles4.add("Program Area");
        titles4.add("Element");
        header_startcell+=2;
        end_of_basic_info=header_startcell;
        if(age_set.contains("datim")){
        titles4.add("m <1 ");
        titles4.add("m 1-4");
        titles4.add("m 5-9");
        titles4.add("m 10-14");
        titles4.add("m 15-19");
        titles4.add("m 20-24");
        titles4.add("m 25-49");
        titles4.add("m 50+");
        titles4.add("f <1 ");
        titles4.add("f 1-4");
        titles4.add("f 5-9");
        titles4.add("f 10-14");
        titles4.add("f 15-19");
        titles4.add("f 20-24");
        titles4.add("f 25-49");
        titles4.add("f 50+");
        headercells+=16;
        }
        if(age_set.contains("special")){
        titles4.add("m <14 ");
        titles4.add("m 10-19");
        titles4.add("m 15+");
        titles4.add("m 24-35");
        titles4.add("f <14 ");
        titles4.add("f 10-19");
        titles4.add("f 15+");
        titles4.add("f 24-35");
        headercells+=8;
        }
        
        
        titles4.add("Total Male");
        titles4.add("Total Female");
        titles4.add("Total");
        headercells+=3;
        
                HSSFRow rw0=shet.createRow(0);
                HSSFRow rw1=shet.createRow(1);
       int i=0;   
       i=0;
       while(i<header_startcell){
       HSSFCell clx= rw0.createCell(i);
        clx.setCellValue("");
        clx.setCellStyle(stylex);
        
       HSSFCell clx1= rw1.createCell(i);
        clx1.setCellValue("");
        clx1.setCellStyle(stylex);
        i++;
       }
       shet.addMergedRegion(new CellRangeAddress(0,1,0,header_startcell-1));
        
//        Heading Row 3 Gender Titles
        rw0.setHeightInPoints(25);
        
        rw1.setHeightInPoints(25);
        i=0;
        int j=0;
        while(i<=headercells-1){
        HSSFCell clx= rw0.createCell(header_startcell+i);
        clx.setCellStyle(stylex);
        
        HSSFCell clx2= rw1.createCell(header_startcell+i);
        clx2.setCellStyle(stylex);
        i++;
        }
        HSSFCell clT2=null,clT1=null;
        HSSFCell clT3_1=null,clT3_2=null,clT3_3=null,clT3_4=null;
        clT1 = rw0.getCell(header_startcell);
        clT3_1= rw1.getCell(header_startcell);
        clT3_2= rw1.getCell(header_startcell);
        if(age_set.contains("datim")){
        clT1.setCellValue("Datim Age Dissegragation");
        clT3_1.setCellValue("Male");
        clT3_2.setCellValue("Female");

        
        shet.addMergedRegion(new CellRangeAddress(0,0,header_startcell,header_startcell+15));
        shet.addMergedRegion(new CellRangeAddress(1,1,header_startcell,header_startcell+7));
        shet.addMergedRegion(new CellRangeAddress(1,1,header_startcell+8,header_startcell+15));
        header_startcell+=16;
        }
        else{
        }
        if(age_set.contains("special")){
        clT2 = rw0.getCell(header_startcell);
        clT3_3= rw1.getCell(header_startcell);
        clT3_4= rw1.getCell(header_startcell+4);
        clT2.setCellValue("Special Age Dissegragation");
        clT3_3.setCellValue("Male");
        clT3_4.setCellValue("Female");
        
        shet.addMergedRegion(new CellRangeAddress(0,0,header_startcell,header_startcell+7));
        shet.addMergedRegion(new CellRangeAddress(1,1,header_startcell,header_startcell+3));
        shet.addMergedRegion(new CellRangeAddress(1,1,header_startcell+4,header_startcell+7));
        header_startcell+=8;
        }
        shet.addMergedRegion(new CellRangeAddress(0,1,header_startcell,header_startcell+2));
    //        Heading Row 4 AGES Titles
        HSSFRow rw2=shet.createRow(2);
        rw2.setHeightInPoints(25);
        i=0;
        while(i<titles4.size()){
        HSSFCell clx= rw2.createCell(i);
        clx.setCellValue(titles4.get(i));
        clx.setCellStyle(stylex);
        
        i++;
        }
      
        int row=3;
        
            for(String program_area : program_areas){
            String getElements="SELECT * FROM detailed_report WHERE program_area='"+program_area+"' and is_active=1 "+specific_elements+"";
//                System.out.println("query : "+getElements);
            conn.rs=conn.st.executeQuery(getElements);
            while(conn.rs.next()){
            data_element=form=rule=main_query=having_query="";
            
            element_id= conn.rs.getInt("id");
            data_element=conn.rs.getString("data_element");
            form = conn.rs.getString("form");
            rule = conn.rs.getString("rule");
            main_query = conn.rs.getString("query");
            is_special= conn.rs.getInt("is_special");
            subpartnera_column = conn.rs.getString("subpartnera_column");
            only_totals = conn.rs.getInt("only_totals");
            
            //            remove last ,
            group_by = getGroupBy(group_by);
            
            int k=0;
            while(k<dates.size()){
            
            String[] date_info = dates.get(k).toString().split("-");
            String  dated_query="";
            String s_date="",e_date="";
            s_date = date_info[0];
            e_date = date_info[1];
            if(current_care_art.contains(","+element_id+",")){
               String querymax="select max(yearmonth) from moh731 Where concat(yearmonth,'01') between "+s_date+" and "+e_date+"";
               conn.rs2=conn.st2.executeQuery(querymax);
               if(conn.rs2.next()){
                e_date = conn.rs2.getString(1)+"31"; 
                s_date = conn.rs2.getString(1)+"01";
            }
            } 
             dated_query = main_query.replace("start_date", s_date);
             dated_query = dated_query.replace("end_date", e_date);
            

            where=" subpartnera."+subpartnera_column+"=1 "+specific_facility;    
            
            query=dated_query+" "+where+" "+group_by; 
                System.out.println("query: "+query);
            conn.rs1=conn.st1.executeQuery(query);
            
             while(conn.rs1.next()){  
            data.clear();
            
            County = conn.rs1.getString("County");
            DistrictNom = conn.rs1.getString("DistrictNom");
            SubPartnerNom = conn.rs1.getString("SubPartnerNom");
            constituency = conn.rs1.getString("constituency");
            ward = conn.rs1.getString("ward");
            mfl_code = conn.rs1.getString("mfl_code");
            GSN = conn.rs1.getString("GSN");
            ART_Support = conn.rs1.getString("ART_Support");
            PMTCT_Support = conn.rs1.getString("PMTCT_Support");
            HTC_Support1 = conn.rs1.getString("HTC_Support1");
            Type = conn.rs1.getString("Type");
            ART_highvolume = conn.rs1.getString("ART_highvolume");
            PMTCT_highvolume = conn.rs1.getString("PMTCT_highvolume");
            HTC_highvolume = conn.rs1.getString("HTC_highvolume");
            latitude = conn.rs1.getString("latitude");
            longitude = conn.rs1.getString("longitude");
            db_year = conn.rs1.getString("year");
            semi_annual = conn.rs1.getString("semi_annual");
            quarter = conn.rs1.getString("quarter");
            yearmonth = conn.rs1.getString("yearmonth");
            
            if(group_by.contains("County")){
                  data.add(County);
            }
            if(group_by.contains("DistrictNom")){
                 data.add(DistrictNom);
            }
            if(group_by.contains("constituency")){
                 data.add(constituency);
            }
            if(group_by.contains("ward")){
                data.add(ward);
            }
            
            
            if(group_by.contains("SubPartnerNom")){
            data.add(SubPartnerNom);
            data.add(mfl_code);
            data.add(GSN);
            data.add(ART_Support);
            data.add(PMTCT_Support);
            data.add(HTC_Support1);
            data.add(Type);
            data.add(ART_highvolume);
            data.add(PMTCT_highvolume);
            data.add(HTC_highvolume);
            data.add(latitude);
            data.add(longitude);
            }
            if(group_by.contains("year")){
        data.add(db_year);
        }
        if(group_by.contains("semi_annual")){
        data.add(semi_annual);
        }
        if(group_by.contains("quarter")){
        data.add(quarter);
        }
        if(group_by.contains("yearmonth")){
        data.add(yearmonth);
        }
        data.add(program_area);
        data.add(data_element);
         
        if(only_totals==1){
         System.out.println("age set : "+age_set);
         
        if(age_set.contains("datim")){
          for (int m=1;m<=16;m++){
          data.add("");
          }  
        }
        if(age_set.contains("special")){ 
          for (int m=1;m<=8;m++){
          data.add("");    
          }  
        }
        for (int m=1;m<=2;m++){
          data.add("");    
          }
        
        data.add(conn.rs1.getInt("total"));
        }
        
        else{
        
            if(element_id==3){
            data.addAll(NewOnCare(conn.rs1,age_set));
            }
            if(element_id==4){
            data.addAll(NewOnART(conn.rs1,age_set));
             if(age_set.contains("datim")){
                shet.addMergedRegion(new CellRangeAddress(0,0,header_startcell,header_startcell+7));
            }
            }
            if(element_id==6){
            data.addAll(CurrentOnCare(conn.rs1,age_set));
            }
            if(element_id==7){
            data.addAll(CurrentOnART(conn.rs1,age_set));
            }
            
            if(element_id==13){
            data.addAll(ViralSuppression(conn.rs1,age_set));
            }
            if(element_id==14){
            data.addAll(TotalViralLoadsDone(conn.rs1,age_set));
            }
            if(element_id==15){
            data.addAll(TotalViralLoadssuppressedPeds(conn.rs1,age_set));
            }
            
            if(element_id==24){
            data.addAll(Less1YearInitiatedOnART(conn.rs1,age_set));
            }
            
             }
           
            HSSFRow rw_d=shet.createRow(row);
            rw_d.setHeightInPoints(25);
            
            i=0;
            while(i<data.size()){
            HSSFCell clx= rw_d.createCell(i);
            if(data.get(i)!=null){
            String value = data.get(i).toString();
            Double value2;
            if(isNumeric(value)){
                value2=Double.parseDouble(value);
            clx.setCellValue(value2);   
            }
            else{
           clx.setCellValue(value);     
            }
            }
            clx.setCellStyle(style2);

            i++;
            } 
            if(element_id==4){
             if(age_set.contains("datim")){
                shet.addMergedRegion(new CellRangeAddress(row,row,end_of_basic_info+1,end_of_basic_info+2));
                shet.addMergedRegion(new CellRangeAddress(row,row,end_of_basic_info+9,end_of_basic_info+10));
            }
            }
            
            row++;
            } 
             k++;
//             end of different dates
            }
            }
            
            } 
            
    IdGenerator IG = new IdGenerator();
     String   createdOn=IG.CreatedOn();
   
 ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte[] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=Detailed_report_Generated_On_"+createdOn+".xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
outStream.close();    

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
        Logger.getLogger(SpecialReport.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(SpecialReport.class.getName()).log(Level.SEVERE, null, ex);
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
 
    public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
   
    public String getGroupBy(String group_by) {
    if (group_by != null && group_by.length() > 0 && group_by.charAt(group_by.length() - 1) == ',') {
        group_by = group_by.substring(0, group_by.length() - 1);
    }
    return group_by;
   }
    
    public ArrayList NewOnCare(ResultSet rset,String ageset) throws SQLException{
        int HV0308,HV0309,HV0310,HV0311,HV0312,HV0310_1,HV0309_1;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        
        HV0308 = rset.getInt("HV0308");
        HV0309 = rset.getInt("HV0309");
        HV0310 = rset.getInt("HV0310");
        HV0311 = rset.getInt("HV0311");
        HV0312 = rset.getInt("HV0312");
        
        m_1=(int)  0.5*HV0308;
        f_1=(int) (0.5*HV0308);
//        normalization
//          <1
        int newsum_1=f_1+m_1;
        int counter=0;
        while(newsum_1<HV0308){
         if(counter%2==0){
             if(HV0310>=HV0309){
                f_1++; 
             }
             else{
                m_1++;     
             }
         }
         else{
         if(HV0309>=HV0310){    
                m_1++;
         }
         else{
                f_1++;     
         }
         }
         
        newsum_1=f_1+m_1;
        counter++;
        }
    while(f_1>HV0310){
        f_1--;
        m_1++;
    }
    
    while(m_1>HV0309){
        m_1--;
        f_1++;
    }
      
        HV0309_1 = HV0309 - f_1;
        HV0310_1 = HV0310 - m_1;       
        
        m_4 = (int) (0.34*HV0309_1);
        m_9 = (int) (0.38*HV0309_1);
        m_14 = (int) (0.28*HV0309_1);
                
        m_19 = (int) (0.02*HV0311);
        m_24 = (int) (0.09*HV0311);
        m_49 = (int) (0.8*HV0311);
        m_50 = (int) (0.09*HV0311);
        
        
        f_4 = (int) (0.34*HV0310_1);
        f_9 = (int) (0.38*HV0310_1);
        f_14 = (int) (0.28*HV0310_1);
                
        f_19 = (int) (0.02*HV0312);
        f_24 = (int) (0.09*HV0312);
        f_49 = (int) (0.8*HV0312);
        f_50 = (int) (0.09*HV0312);
         
//        male 1-14

        int newsum_m_1_14=m_4+m_9+m_14;
        counter=0;
        while(newsum_m_1_14<HV0309_1){
         counter++;
            switch (counter) {
                case 1:
                    m_9++;
                    break;
                case 2:
                    m_4++;
                    break;
                case 3:
                    m_14++;
                    counter=0;
                    break;
                default:
                    break;
            }
         newsum_m_1_14=m_4+m_9+m_14;
        counter++;
        }
        
        int newsum_f_1_14=f_4+f_9+f_14;
        counter=0;
        while(newsum_f_1_14<HV0310_1){
         counter++;
            switch (counter) {
                case 1:
                    f_9++;
                    break;
                case 2:
                    f_4++;
                    break;
                case 3:
                    f_14++;
                    counter=0;
                    break;
                default:
                    break;
            }
         newsum_f_1_14=f_4+f_9+f_14;
        counter++;
        }
      
        //        male 15-50+

        int newsum_m_15_50=m_19+m_24+m_49+m_50;
        counter=0;
        while(newsum_m_15_50<HV0311){
         counter++;
                if(counter<10){
                    m_49++;
                   }
            else if(counter==11){
                    m_24++; 
                    }
            else if(counter==12){
                    m_50++; 
                   counter=0; 
                    }
        newsum_m_15_50=m_19+m_24+m_49+m_50;
        counter++;
        }
        
        //        female 15-50+

        int newsum_f_15_50=f_19+f_24+f_49+f_50;
        counter=0;
        while(newsum_f_15_50<HV0312){
         counter++;
                if(counter<10){
                    f_49++;
                   }
            else if(counter==11){
                    f_24++; 
                    }
            else if(counter==12){
                    f_50++; 
                   counter=0; 
                    }
        newsum_f_15_50=f_19+f_24+f_49+f_50;
        counter++;
        }
        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
//        Hashmap array
//        Datim ages

        
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
        int females=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
        int males=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        int totals = females+males;
        
        
        new_data.add(males);
        new_data.add(females);
        new_data.add(totals);
      
        return new_data;
    }
   
    public ArrayList NewOnART(ResultSet rset,String ageset) throws SQLException{
        int HV0320,HV0321,HV0322,HV0323,HV0324,HV0325,HV0321_1,HV0322_1;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        
        HV0320 = rset.getInt("HV0320");
        HV0321 = rset.getInt("HV0321");
        HV0322 = rset.getInt("HV0322");
        HV0323 = rset.getInt("HV0323");
        HV0324 = rset.getInt("HV0324");
        
        m_1=(int)0.5*HV0320;
        f_1=(int) (0.5*HV0320);
        
        //        normalization
        //          <1
        int newsum_1=f_1+m_1;
        int counter=0;
        while(newsum_1<HV0320){
        if(counter%2==0){
             if(HV0322>=HV0321){
                f_1++; 
             }
             else{
                m_1++;     
             }
         }
         else{
         if(HV0321>=HV0322){    
                m_1++;
         }
         else{
                f_1++;     
         }
         }
         
        newsum_1=f_1+m_1;
        counter++;
        }
    while(f_1>HV0322){
        f_1--;
        m_1++;
    }
    
    while(m_1>HV0321){
        m_1--;
        f_1++;
    }
        
        HV0321_1 = HV0321 - f_1;
        HV0322_1 = HV0322 - m_1;     
        
        m_4 = (int) (0);//1-9 YEARS NEED TO BE EMERGED AND COME OUT AS ONE CELL
        m_9 = (int) (0.5385*HV0321_1);
        m_14 = (int) (0.4615*HV0321_1);
                
        m_19 = (int) (0.008*HV0323);
        m_24 = (int) (0.0752*HV0323);
        m_49 = (int) (0.8195*HV0323);
        m_50 = (int) (0.0977*HV0323);
        
        
        f_4 = (int) (0);//1-9 YEARS NEED TO BE EMERGED AND COME OUT AS ONE CELL
        f_9 = (int) (0.5833*HV0322_1);
        f_14 = (int) (0.4167*HV0322_1);
                
        f_19 = (int) (0.0433*HV0324);
        f_24 = (int) (0.1169*HV0324);
        f_49 = (int) (0.7485*HV0324);
        f_50 = (int) (0.0909*HV0324);

//        male 1-14

        int newsum_m_1_14=m_4+m_9+m_14;
        counter=0;
        while(newsum_m_1_14<HV0321){
         counter++;
            switch (counter) {
                case 1:
                    m_9++;
                    break;
                case 2:
                    m_14++;
                    counter=0;
                    break;
                default:
                    break;
            }
         newsum_m_1_14=m_4+m_9+m_14;
        counter++;
        }
        
        int newsum_f_1_14=f_4+f_9+f_14;
        counter=0;
        while(newsum_f_1_14<HV0322){
         counter++;
            switch (counter) {
                case 1:
                    f_9++;
                    break;
                case 2:
                    f_14++;
                    counter=0;
                    break;
                default:
                    break;
            }
         newsum_f_1_14=f_4+f_9+f_14;
        counter++;
        }
      
        //        male 15-50+

        int newsum_m_15_50=m_19+m_24+m_49+m_50;
        counter=0;
        while(newsum_m_15_50<HV0323){
         counter++;
                if(counter<10){
                    m_49++;
                   }
            else if(counter==11){
                    m_50++; 
                    }
            else if(counter==12){
                    m_24++; 
                   counter=0; 
                    }
        newsum_m_15_50=m_19+m_24+m_49+m_50;
        counter++;
        }
        
        //        female 15-50+

        int newsum_f_15_50=f_19+f_24+f_49+f_50;
        counter=0;
        while(newsum_f_15_50<HV0324){
         counter++;
                if(counter<8){
                    f_49++;
                   }
            else if(counter==11){
                    f_24++; 
                    }
            else if(counter==12){
                    f_50++; 
                   counter=0; 
                    }
        newsum_f_15_50=f_19+f_24+f_49+f_50;
        counter++;
        }
        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
//        Hashmap array
//        Datim ages

        
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
        int females=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
        int males=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        int totals = females+males;
        
        new_data.add(males);
        new_data.add(females);
        new_data.add(totals);
      
        return new_data;
    }
   
    public ArrayList CurrentOnCare(ResultSet rset,String ageset) throws SQLException{
        int HV0314,HV0315,HV0316,HV0317,HV0318,HV0315_1,HV0316_1;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        
        HV0314 = rset.getInt("HV0314");
        HV0315 = rset.getInt("HV0315");
        HV0316 = rset.getInt("HV0316");
        HV0317 = rset.getInt("HV0317");
        HV0318 = rset.getInt("HV0318");
        
        m_1=(int)0.5*HV0314;
        f_1=(int) (0.5*HV0314);
                
        //        normalization
        //          <1
        int newsum_1=f_1+m_1;
        int counter=0;
        while(newsum_1<HV0314){
        if(counter%2==0){
             if(HV0316>=HV0315){
                f_1++; 
             }
             else{
                m_1++;     
             }
         }
         else{
         if(HV0315>=HV0316){    
                m_1++;
         }
         else{
                f_1++;     
         }
         }
         
        newsum_1=f_1+m_1;
        counter++;
        }
        while(f_1>HV0316){
            f_1--;
            m_1++;
        }

        while(m_1>HV0315){
            m_1--;
            f_1++;
        }

        HV0315_1 = HV0315 - f_1;
        HV0316_1 = HV0316 - m_1; 
        
        m_4 = (int) (0.25*HV0315_1);
        m_9 = (int) (0.37*HV0315_1);
        m_14 = (int) (0.38*HV0315_1);
                
        m_19 = (int) (0.02*HV0317);
        m_24 = (int) (0.09*HV0317);
        m_49 = (int) (0.8*HV0317);
        m_50 = (int) (0.09*HV0317);
        
        
        f_4 = (int) (0.25*HV0316_1);
        f_9 = (int) (0.37*HV0316_1);
        f_14 = (int) (0.38*HV0316_1);
                
        f_19 = (int) (0.02*HV0318);
        f_24 = (int) (0.09*HV0318);
        f_49 = (int) (0.8*HV0318);
        f_50 = (int) (0.09*HV0318);

//        male 1-14

        int newsum_m_1_14=m_4+m_9+m_14;
        counter=0;
        while(newsum_m_1_14<HV0315){
         counter++;
            switch (counter) {
                case 1:
                    m_9++;
                    break;
                case 2:
                    m_14++;
                    break;
                case 3:
                    m_4++;
                    counter=0;
                    break;
                default:
                    break;
            }
         newsum_m_1_14=m_4+m_9+m_14;
        counter++;
        }
        
        int newsum_f_1_14=f_4+f_9+f_14;
        counter=0;
        while(newsum_f_1_14<HV0316){
         counter++;
            switch (counter) {
                case 1:
                    f_9++;
                    break;
                case 2:
                    f_14++;
                    break;
                case 3:
                    f_4++;
                    counter=0;
                    break;
                default:
                    break;
            }
         newsum_f_1_14=f_4+f_9+f_14;
        counter++;
        }
      
        //        male 15-50+

        int newsum_m_15_50=m_19+m_24+m_49+m_50;
        counter=0;
        while(newsum_m_15_50<HV0317){
         counter++;
                if(counter<10){
                    m_49++;
                   }
            else if(counter==11){
                    m_24++; 
                    }
            else if(counter==12){
                    m_50++; 
                   counter=0; 
                    }
        newsum_m_15_50=m_19+m_24+m_49+m_50;
        counter++;
        }
        
        //        female 15-50+

        int newsum_f_15_50=f_19+f_24+f_49+f_50;
        counter=0;
        while(newsum_f_15_50<HV0318){
         counter++;
                if(counter<10){
                    f_49++;
                   }
            else if(counter==11){
                    f_24++; 
                    }
            else if(counter==12){
                    f_50++; 
                   counter=0; 
                    }
        newsum_f_15_50=f_19+f_24+f_49+f_50;
        counter++;
        }
        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
//        Hashmap array
//        Datim ages

        
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        new_data.add(m_1);
        new_data.add(m_9);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_9);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
        int females=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
        int males=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        int totals = females+males;
        
        
        new_data.add(males);
        new_data.add(females);
        new_data.add(totals);
      
        return new_data;
    }
   
    public ArrayList CurrentOnART(ResultSet rset,String ageset) throws SQLException{
        int HV0334,HV0335,HV0336,HV0337,HV0338,HV0335_1,HV0336_1;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        
        HV0334 = rset.getInt("HV0334");
        HV0335 = rset.getInt("HV0335");
        HV0336 = rset.getInt("HV0336");
        HV0337 = rset.getInt("HV0337");
        HV0338 = rset.getInt("HV0338");
        
        m_1=(int)0.5*HV0334;
        f_1=(int) (0.5*HV0334);
        
                
        //        normalization
        //          <1
        int newsum_1=f_1+m_1;
        int counter=0;
        while(newsum_1<HV0334){
        if(counter%2==0){
             if(HV0336>=HV0335){
                f_1++; 
             }
             else{
                m_1++;     
             }
         }
         else{
         if(HV0335>=HV0336){    
                m_1++;
         }
         else{
                f_1++;     
         }
         }
         
        newsum_1=f_1+m_1;
        counter++;
        }
        while(f_1>HV0336){
            f_1--;
            m_1++;
        }

        while(m_1>HV0335){
            m_1--;
            f_1++;
        }

        HV0335_1 = HV0335 - f_1;
        HV0336_1 = HV0336 - m_1; 
        
        m_4 = (int) (0);//1-9 YEARS NEED TO BE EMERGED AND COME OUT AS ONE CELL
        m_9 = (int) (0.43*HV0335_1);
        m_14 = (int) (0.57*HV0335_1);
                
        m_19 = (int) (0.05*HV0337);
        m_24 = (int) (0.04*HV0337);
        m_49 = (int) (0.73*HV0337);
        m_50 = (int) (0.18*HV0337);
        
        
        f_4 = (int) (0);//1-9 YEARS NEED TO BE EMERGED AND COME OUT AS ONE CELL
        f_9 = (int) (0.4265*HV0336_1);
        f_14 = (int) (0.5735*HV0336_1);
                
        f_19 = (int) (0.03*HV0338);
        f_24 = (int) (0.07*HV0338);
        f_49 = (int) (0.78*HV0338);
        f_50 = (int) (0.12*HV0338);
        
//        male 1-14

        int newsum_m_1_14=m_4+m_9+m_14;
        counter=0;
        while(newsum_m_1_14<HV0335){
         counter++;
            switch (counter) {
                case 1:
                    m_14++;
                    break;
                case 2:
                    m_9++;
                    counter=0;
                    break;
                default:
                    break;
            }
         newsum_m_1_14=m_4+m_9+m_14;
        counter++;
        }
        
        int newsum_f_1_14=f_4+f_9+f_14;
        counter=0;
        while(newsum_f_1_14<HV0336){
         counter++;
            switch (counter) {
                case 1:
                    f_14++;
                    break;
                case 2:
                    f_9++;
                    counter=0;
                    break;
                default:
                    break;
            }
         newsum_f_1_14=f_4+f_9+f_14;
        counter++;
        }
      
        //        male 15-50+

        int newsum_m_15_50=m_19+m_24+m_49+m_50;
        counter=0;
        while(newsum_m_15_50<HV0337){
         counter++;
                if(counter<8){
                    m_49++;
                   }
            else if(counter<10){
                    m_50++; 
                    }
            if(counter==9){ 
                   counter=0; 
                    }
        newsum_m_15_50=m_19+m_24+m_49+m_50;
        counter++;
        }
        
        //        female 15-50+

        int newsum_f_15_50=f_19+f_24+f_49+f_50;
        counter=0;
        while(newsum_f_15_50<HV0338){
         counter++;
                if(counter<9){
                    f_49++;
                   }
            else if(counter==9){
                    f_50++;
                    counter=0;
                    }
        newsum_f_15_50=f_19+f_24+f_49+f_50;
        counter++;
        }
        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
//        Hashmap array
//        Datim ages

        
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        new_data.add(m_1);
        new_data.add(m_9);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_9);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
        int females=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
        int males=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        int totals = females+males;
        
        new_data.add(males);
        new_data.add(females);
        new_data.add(totals);
      
        return new_data;
    }
   
    public ArrayList ViralSuppression(ResultSet rset,String ageset) throws SQLException{

        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        m_1 = rset.getInt("m_1");
        m_4 = rset.getInt("m_4");
        m_9 = rset.getInt("m_9");
        m_14 = rset.getInt("m_14");
        m_19 = rset.getInt("m_19");
        m_24 = rset.getInt("m_24");
        m_49 = rset.getInt("m_49");
        m_50 = rset.getInt("m_50");
        f_1 = rset.getInt("f_1");
        f_4 = rset.getInt("f_4");
        f_9 = rset.getInt("f_9");
        f_14 = rset.getInt("f_14");
        f_19 = rset.getInt("f_19");
        f_24 = rset.getInt("f_24");
        f_49 = rset.getInt("f_49");
        f_50 = rset.getInt("f_50");
        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35= rset.getInt("m_24_35");
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35= rset.getInt("f_24_35");
        
//        Hashmap array
//        Datim ages

        
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
        int females=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
        int males=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        int totals = females+males;
        
        new_data.add(males);
        new_data.add(females);
        new_data.add(totals);
      
        return new_data;
    }
   
    public ArrayList TotalViralLoadsDone(ResultSet rset,String ageset) throws SQLException{
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        m_1 = rset.getInt("m_1");
        m_4 = rset.getInt("m_4");
        m_9 = rset.getInt("m_9");
        m_14 = rset.getInt("m_14");
        m_19 = rset.getInt("m_19");
        m_24 = rset.getInt("m_24");
        m_49 = rset.getInt("m_49");
        m_50 = rset.getInt("m_50");
        f_1 = rset.getInt("f_1");
        f_4 = rset.getInt("f_4");
        f_9 = rset.getInt("f_9");
        f_14 = rset.getInt("f_14");
        f_19 = rset.getInt("f_19");
        f_24 = rset.getInt("f_24");
        f_49 = rset.getInt("f_49");
        f_50 = rset.getInt("f_50");
        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35= rset.getInt("m_24_35");
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35= rset.getInt("f_24_35");
        
//        Hashmap array
//        Datim ages

        
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
        int females=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
        int males=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        int totals = females+males;
        
        new_data.add(males);
        new_data.add(females);
        new_data.add(totals);
      
        return new_data;
    }
   
    public ArrayList TotalViralLoadssuppressedPeds(ResultSet rset,String ageset) throws SQLException{
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        m_1 = rset.getInt("m_1");
        m_4 = rset.getInt("m_4");
        m_9 = rset.getInt("m_9");
        m_14 = rset.getInt("m_14");
        m_19 = rset.getInt("m_19");
        m_24 = rset.getInt("m_24");
        m_49 = rset.getInt("m_49");
        m_50 = rset.getInt("m_50");
        f_1 = rset.getInt("f_1");
        f_4 = rset.getInt("f_4");
        f_9 = rset.getInt("f_9");
        f_14 = rset.getInt("f_14");
        f_19 = rset.getInt("f_19");
        f_24 = rset.getInt("f_24");
        f_49 = rset.getInt("f_49");
        f_50 = rset.getInt("f_50");
        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35= rset.getInt("m_24_35");
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35= rset.getInt("f_24_35");
        
//        Hashmap array
//        Datim ages

        
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
        
        int females=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
        int males=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        int totals = females+males;
        
        new_data.add(males);
        new_data.add(females);
        new_data.add(totals);
      
        return new_data;
    }
    
    public ArrayList Less1YearInitiatedOnART(ResultSet rset,String ageset) throws SQLException{
        int HV0320,HV0321,HV0322;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,f_0_14=0;
        
        HV0320 = rset.getInt("HV0320");
        HV0321 = rset.getInt("HV0321");
        HV0322 = rset.getInt("HV0322");
        
        m_1=(int)0.5*HV0320;
        
//        normalization
//          <1
        int newsum_1=f_1+m_1;
        int counter=0;
        while(newsum_1<HV0320){
         if(counter%2==0){
             if(HV0322>=HV0321){
                f_1++; 
             }
             else{
                m_1++;     
             }
         }
         else{
         if(HV0321>=HV0322){    
                m_1++;
         }
         else{
                f_1++;     
         }
         }
         
        newsum_1=f_1+m_1;
        counter++;
        }
    while(f_1>HV0322){
        f_1--;
        m_1++;
    }
    
    while(m_1>HV0321){
        m_1--;
        f_1++;
    }
        

        m_0_14=m_1+m_4+m_9+m_14;
        
                
        f_0_14=f_1+f_4+f_9+f_14;
        
        
//        Hashmap array
//        Datim ages

        
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        new_data.add(m_1);
        new_data.add("");
        new_data.add("");
        new_data.add("");
        new_data.add("");
        new_data.add("");
        new_data.add("");
        new_data.add("");
        new_data.add(f_1);
        new_data.add("");
        new_data.add("");
        new_data.add("");
        new_data.add("");
        new_data.add("");
        new_data.add("");
        new_data.add("");
        }
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add("");
        new_data.add("");
        new_data.add("");
        new_data.add(f_0_14);
        new_data.add("");
        new_data.add("");
        new_data.add("");
        }
        int females=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
        int males=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        int totals = females+males;
        
        
        new_data.add(males);
        new_data.add(females);
        new_data.add(totals);
      
        return new_data;
    }
   
}
