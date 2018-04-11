/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMapping;

import database.dbConn;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author GNyabuto
 */
public class DHIS_IMIS{
 String yearmonth; 
 dbConn conn = new dbConn();
 String id,query,dhis_label,imis_label,explanation,service_area;
 int imis,dhis,variance;
 String county,sub_county,ward,facility;
 int rowcounter,mfl_code;
 int elems_counter,facil_counter;
 String systems[] = {"DHIS","IMIS",""};
 ArrayList mfl_codes = new ArrayList();
 int art,htc,pmtct;
    public XSSFWorkbook get_data(String[] yearmonths, XSSFWorkbook wb) throws SQLException{
        // HSSFWorkbook wb = new HSSFWorkbook();
        
        //get number of supported indicators
        art=htc=pmtct=0;
        String service_area_counter = "SELECT " +
                "COUNT(CASE WHEN service_area='ART' THEN 1 END) AS art," +
                "COUNT(CASE WHEN service_area='HTC' THEN 1 END) AS htc," +
                "COUNT(CASE WHEN service_area='PMTCT' THEN 1 END) AS pmtct " +
                "FROM imis_dhis_mapping WHERE active=1";
        conn.rs = conn.st.executeQuery(service_area_counter);
        if(conn.rs.next()){
         art = conn.rs.getInt(1);
         htc = conn.rs.getInt(2);
         pmtct = conn.rs.getInt(3); 
        }
        
        //end of getting supported areas
        
        mfl_codes.clear();
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 18);
        font.setFontName("Cambria");
        font.setColor((short) 0000);
        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        
        XSSFFont font2 = wb.createFont();
        font2.setFontName("Cambria");
        font2.setColor((short) 0000);
        CellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);
        style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style2.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        
        XSSFFont fontheader = wb.createFont();
        fontheader.setFontName("Cambria");
        fontheader.setColor((short) 0000);
        fontheader.setFontHeight(30);
        
        CellStyle style_header = wb.createCellStyle();
        style_header.setFont(fontheader);
        style_header.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style_header.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style_header.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style_header.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style_header.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style_header.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style_header.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        XSSFCellStyle stborder = wb.createCellStyle();
        stborder.setBorderTop(XSSFCellStyle.BORDER_THIN);
        stborder.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        stborder.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        stborder.setBorderRight(XSSFCellStyle.BORDER_THIN);
        stborder.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        
        XSSFCellStyle stDHIS = wb.createCellStyle();
        stDHIS.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        stDHIS.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        stDHIS.setBorderTop(XSSFCellStyle.BORDER_THIN);
        stDHIS.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        stDHIS.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        stDHIS.setBorderRight(XSSFCellStyle.BORDER_THIN);
        stDHIS.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        
        XSSFCellStyle stIMIS = wb.createCellStyle();
        stIMIS.setFillForegroundColor(IndexedColors.ROSE.getIndex());
        stIMIS.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        stIMIS.setBorderTop(XSSFCellStyle.BORDER_THIN);
        stIMIS.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        stIMIS.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        stIMIS.setBorderRight(XSSFCellStyle.BORDER_THIN);
        stIMIS.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        XSSFCellStyle stylex = wb.createCellStyle();
        stylex.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        stylex.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        stylex.setBorderTop(XSSFCellStyle.BORDER_THIN);
        stylex.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        stylex.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        stylex.setBorderRight(XSSFCellStyle.BORDER_THIN);
        stylex.setAlignment(XSSFCellStyle.ALIGN_LEFT);

        XSSFCellStyle stylered = wb.createCellStyle();
        stylered.setFillForegroundColor(IndexedColors.RED.getIndex());
        stylered.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        stylered.setBorderTop(XSSFCellStyle.BORDER_THIN);
        stylered.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        stylered.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        stylered.setBorderRight(XSSFCellStyle.BORDER_THIN);
        stylered.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        XSSFFont fontx = wb.createFont();
        fontx.setColor(IndexedColors.BLACK.getIndex());
        fontx.setFontName("Cambria");
        stylex.setFont(fontx);
        stylex.setWrapText(true);

        stylered.setFont(fontx);
        stylered.setWrapText(true);

        XSSFCellStyle STYLE_TITLE = wb.createCellStyle();
        STYLE_TITLE.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        STYLE_TITLE.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        STYLE_TITLE.setBorderTop(XSSFCellStyle.BORDER_THIN);
        STYLE_TITLE.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        STYLE_TITLE.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        STYLE_TITLE.setBorderRight(XSSFCellStyle.BORDER_THIN);
        STYLE_TITLE.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        STYLE_TITLE.setWrapText(true);
        
        
        XSSFSheet shet = wb.createSheet("IMIS-DHIS MAPPING");
        rowcounter  =facil_counter= 0;
        
        XSSFRow rw_area = shet.createRow(rowcounter);
        rw_area.setHeightInPoints(70);
         
       int m=6,n=0; 
       for(int a=0;a<(((art+pmtct+htc)*3)+6);a++){
          XSSFCell cellrw = rw_area.createCell(a); 
          cellrw.setCellStyle(style_header);
       }
       
        if(art>0){
         XSSFCell cellart = rw_area.getCell(6);
         cellart.setCellValue("CARE AND TREATMENT");
         shet.addMergedRegion(new CellRangeAddress(rowcounter,rowcounter,6,6+(art*3)-1));//ART
        }
        
        if(htc>0){
          XSSFCell cellhtc = rw_area.getCell(6+(art*3)); 
          cellhtc.setCellValue("HIV TESTING SERVICES");
          shet.addMergedRegion(new CellRangeAddress(rowcounter,rowcounter,6+(art*3),6+((art+htc)*3)-1));//HTC
        }
        
        if(pmtct>0){
         XSSFCell cellpmtct = rw_area.getCell(6+((art+htc)*3)); 
        cellpmtct.setCellValue("PMTCT");
        shet.addMergedRegion(new CellRangeAddress(rowcounter,rowcounter,6+((art+htc)*3),6+((art+htc+pmtct)*3)-1));//PMTCT
        }
        
//        end of setting headers
        
        rowcounter++;
        XSSFRow rw0 = shet.createRow(rowcounter);
        rw0.setHeightInPoints(45);
        
        rowcounter++;
        XSSFRow rw = shet.createRow(rowcounter);
        XSSFRow rwdt = shet.getRow(0);
//        rw.setHeightInPoints(45);
             
        XSSFCell cellcounty = rwdt.getCell(0);
        XSSFCell cellsubcounty = rwdt.getCell(1);
        XSSFCell cellward = rwdt.getCell(2);
        XSSFCell cellfacility = rwdt.getCell(3);
        XSSFCell cellmfl = rwdt.getCell(4);
        XSSFCell cellym_name = rwdt.getCell(5);

        
        cellcounty.setCellValue("County");
        cellsubcounty.setCellValue("Sub County");
        cellward.setCellValue("Ward");
        cellfacility.setCellValue("Health Facility");
        cellmfl.setCellValue("MFL Code");
        cellym_name.setCellValue("Period");

        cellcounty.setCellStyle(STYLE_TITLE);
        cellsubcounty.setCellStyle(STYLE_TITLE);
        cellward.setCellStyle(STYLE_TITLE);
        cellfacility.setCellStyle(STYLE_TITLE);
        cellmfl.setCellStyle(STYLE_TITLE);
        cellym_name.setCellStyle(STYLE_TITLE);
         
        
        for(int y=0;y<6;y++){
            shet.addMergedRegion(new CellRangeAddress(0,2,y,y)); // merge facil dets
        }
        
        //
        int numcolumns = 0;
        String countactiveelems = "SELECT COUNT(id) FROM imis_dhis_mapping WHERE active=1";
        conn.rs = conn.st.executeQuery(countactiveelems);
        if(conn.rs.next()){
         numcolumns=conn.rs.getInt(1);
        }
        
        for(int i=0;i<numcolumns;i++){
            if(i<5){
                shet.setColumnWidth(i, 6000);
            }
            else{
             shet.setColumnWidth(i, 4000);   
            }
        }
        
        int j=6;
       while(j<((numcolumns+2)*3)){
          int i=0;
           
           for(String h:systems){
             XSSFCell cell = rw0.createCell(j+i);
             cell.setCellValue(h);  
             cell.setCellStyle(STYLE_TITLE);  
               i++;
           }
        j+=3; 
        }
        //
            
      rowcounter++;   
      int ymcounter=0;
      
      
      for(String ym:yearmonths){
      elems_counter = 0;
      yearmonth = ym;
//      mfl_codes.clear();
      
      String yearmonth_name = getperiod(Integer.parseInt(yearmonth));
      
      ymcounter++;
     String compare_data = "SELECT id,IFNULL(query,'') AS query,IFNULL(dhis_label,'') AS dhis_label,IFNULL(imis_label,'') AS imis_label,IFNULL(explanation,'') AS explanation,IFNULL(service_area,'') AS service_area FROM imis_dhis_mapping WHERE active=1 order by service_area"; 
     conn.rs = conn.st.executeQuery(compare_data);
     while(conn.rs.next()){
         dhis_label = imis_label = explanation = service_area = "";
        id  = conn.rs.getString("id");
        query  = conn.rs.getString("query");
        if(conn.rs.getString("dhis_label")!=null){
        dhis_label  = conn.rs.getString("dhis_label");
        }
        if(conn.rs.getString("imis_label")!=null){
        imis_label  = conn.rs.getString("imis_label");
        }
        explanation  = conn.rs.getString("explanation");
        service_area  = conn.rs.getString("service_area");
        
        //output headers
        if(ymcounter==1){
            XSSFCell celldhis = rw.createCell(((elems_counter+1)*4)+2-(elems_counter));
            XSSFCell cellimis = rw.createCell(((elems_counter+1)*4)+3-(elems_counter));
            XSSFCell cellvariance = rw.createCell(((elems_counter+1)*4)+4-(elems_counter));
            
            celldhis.setCellValue(dhis_label);
            cellimis.setCellValue(imis_label);
            cellvariance.setCellValue("Variance");
            celldhis.setCellStyle(STYLE_TITLE);
            cellimis.setCellStyle(STYLE_TITLE);
            cellvariance.setCellStyle(STYLE_TITLE);
       
        }
        
         query = query.replace("YM", yearmonth+" AND subpartnera."+service_area+"=1 AND subpartnera.active=1");
         System.out.println("final query is : "+query);
         
         facil_counter=1;
        conn.rs1 = conn.st1.executeQuery(query);
        while(conn.rs1.next()){
            XSSFRow rwdata = null;
            facil_counter++;
         county = conn.rs1.getString(2);
         sub_county = conn.rs1.getString(3);
         ward = conn.rs1.getString(4);
         facility = conn.rs1.getString(1);
         mfl_code = conn.rs1.getInt(5); 
         
         
        imis = conn.rs1.getInt(6);
        dhis = conn.rs1.getInt(7);
        variance = conn.rs1.getInt(8); 
        if(!mfl_codes.contains(ymcounter+"-"+mfl_code)){ 
        mfl_codes.add(ymcounter+"-"+mfl_code);
        rwdata = shet.createRow(rowcounter);
       
            XSSFCell cellcountyV = rwdata.createCell(0);
            XSSFCell cellsubcountyV = rwdata.createCell(1);
            XSSFCell cellwardV = rwdata.createCell(2);
            XSSFCell cellfacilityV = rwdata.createCell(3);
            XSSFCell cellmflV = rwdata.createCell(4);
            XSSFCell yearmonthV = rwdata.createCell(5);
            
            cellcountyV.setCellValue(county);
            cellsubcountyV.setCellValue(sub_county);
            cellwardV.setCellValue(ward);
            cellfacilityV.setCellValue(facility);
            cellmflV.setCellValue(mfl_code);
            yearmonthV.setCellValue(yearmonth_name);
            
            cellcountyV.setCellStyle(stborder);
            cellsubcountyV.setCellStyle(stborder);
            cellwardV.setCellStyle(stborder);
            cellfacilityV.setCellStyle(stborder);
            cellmflV.setCellStyle(stborder);
            yearmonthV.setCellStyle(stborder);

        rowcounter++;
       }
       else{
          facil_counter = mfl_codes.indexOf(ymcounter+"-"+mfl_code)+3;
            
           rwdata = shet.getRow(facil_counter);
           
       }
//       append data here
        rwdata.setHeightInPoints(26); 
            
        XSSFCell celldhisV = rwdata.createCell(((elems_counter+1)*4)+2-(elems_counter));
        XSSFCell cellimisV = rwdata.createCell(((elems_counter+1)*4)+3-(elems_counter));
        XSSFCell cellvarianceV = rwdata.createCell(((elems_counter+1)*4)+4-(elems_counter));   
        
        celldhisV.setCellValue(dhis);
        cellimisV.setCellValue(imis);
        cellvarianceV.setCellValue(variance);
        
        celldhisV.setCellStyle(stDHIS);
        cellimisV.setCellStyle(stIMIS);
        if(variance==0){
        cellvarianceV.setCellStyle(stborder);
        }
        else{
        cellvarianceV.setCellStyle(stylered);    
        }
        }
        
        elems_counter++;
     }
    }
      
      wb = addborders(rowcounter,((numcolumns*3)+6),wb,stborder,STYLE_TITLE);
     return wb; 
    }
    
    public XSSFWorkbook addborders(int rows, int columns, XSSFWorkbook wb,XSSFCellStyle stborder,XSSFCellStyle STYLE_TITLE){
     //adds borders for the indicators not supported by that facility. i.e where the cell was not created.
        for (int i=0;i<rows;i++){
            System.out.println("row : "+i);
            XSSFSheet shet = wb.getSheet("IMIS-DHIS MAPPING");
            XSSFRow row = shet.getRow(i);
            for (int j=0;j<columns;j++){
                if(row.getCell(j)==null){
               XSSFCell cell = row.createCell(j);
               if(rows<=2){
                   cell.setCellStyle(STYLE_TITLE);
               }
               else{
                cell.setCellStyle(stborder);    
               }
               
                }
            }
        }
        
        return wb;
    }
    public String getperiod(int ym){
    String period="";  
    String[] montharray = String.valueOf(ym).split("");
    int year=Integer.parseInt(montharray[0]+""+montharray[1]+""+montharray[2]+""+montharray[3]);
    int month=Integer.parseInt(montharray[4]+""+montharray[5]);

    String mn_name="";
    switch (month){
        case 1: mn_name="Jan";
        break;
        case 2: mn_name="Feb"; 
        break;
        case 3: mn_name="Mar";  
        break;
        case 4: mn_name="Apr"; 
        break;
        case 5: mn_name="May"; 
        break;
        case 6: mn_name="Jun"; 
        break;
        case 7: mn_name="Jul";
        break;
        case 8: mn_name="Aug"; 
        break;
        case 9: mn_name="Sep";  
        break;
        case 10: mn_name="Oct";  
        break;
        case 11: mn_name="Nov"; 
        break;
        case 12: mn_name="Dec"; 
        break;
        default:
            mn_name="No Month";
    }
   
    period = mn_name+"' "+year;
return period;
}
}