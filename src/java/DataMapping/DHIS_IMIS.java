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
    public XSSFWorkbook get_data(String ym, XSSFWorkbook wb) throws SQLException{
        // HSSFWorkbook wb = new HSSFWorkbook();
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

        XSSFSheet shet = wb.createSheet("IMIS-DHIS MAPPING");
        rowcounter  =facil_counter= 0;
        XSSFRow rw0 = shet.createRow(rowcounter);
        rw0.setHeightInPoints(45);
        
        rowcounter++;
        XSSFRow rw = shet.createRow(rowcounter);
        rw.setHeightInPoints(45);
        
        
                
        XSSFCell cellcounty = rw.createCell(0);
        XSSFCell cellsubcounty = rw.createCell(1);
        XSSFCell cellward = rw.createCell(2);
        XSSFCell cellfacility = rw.createCell(3);
        XSSFCell cellmfl = rw.createCell(4);

        cellcounty.setCellValue("County");
        cellsubcounty.setCellValue("Sub County");
        cellward.setCellValue("Ward");
        cellfacility.setCellValue("Health Facility");
        cellmfl.setCellValue("MFL Code");

        cellcounty.setCellStyle(style2);
        cellsubcounty.setCellStyle(style2);
        cellward.setCellStyle(style2);
        cellfacility.setCellStyle(style2);
        cellmfl.setCellStyle(style2);
         
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
        
        int j=5;
       while(j<((numcolumns+1)*3)){
          int i=0;
           
           for(String h:systems){
             XSSFCell cell = rw0.createCell(j+i);
             cell.setCellValue(h);  
             cell.setCellStyle(style2);  
               i++;
           }
        j+=3; 
        }
        //
            
         rowcounter++;   
        yearmonth = ym;
      elems_counter = 0;
     String compare_data = "SELECT id,IFNULL(query,'') AS query,IFNULL(dhis_label,'') AS dhis_label,IFNULL(imis_label,'') AS imis_label,IFNULL(explanation,'') AS explanation,IFNULL(service_area,'') AS service_area FROM imis_dhis_mapping WHERE active=1"; 
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
        
            XSSFCell celldhis = rw.createCell(((elems_counter+1)*4)+1-(elems_counter));
            XSSFCell cellimis = rw.createCell(((elems_counter+1)*4)+2-(elems_counter));
            XSSFCell cellvariance = rw.createCell(((elems_counter+1)*4)+3-(elems_counter));
            
            celldhis.setCellValue(dhis_label);
            cellimis.setCellValue(imis_label);
            cellvariance.setCellValue("Variance");
            celldhis.setCellStyle(stDHIS);
            cellimis.setCellStyle(stIMIS);
            cellvariance.setCellStyle(style2);
       
        
         query = query.replace("YM", yearmonth);
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
        if(elems_counter==0){ 
        mfl_codes.add(mfl_code);
        rwdata = shet.createRow(rowcounter);
       
            XSSFCell cellcountyV = rwdata.createCell(0);
            XSSFCell cellsubcountyV = rwdata.createCell(1);
            XSSFCell cellwardV = rwdata.createCell(2);
            XSSFCell cellfacilityV = rwdata.createCell(3);
            XSSFCell cellmflV = rwdata.createCell(4);
            
            cellcountyV.setCellValue(county);
            cellsubcountyV.setCellValue(sub_county);
            cellwardV.setCellValue(ward);
            cellfacilityV.setCellValue(facility);
            cellmflV.setCellValue(mfl_code);
            
            cellcountyV.setCellStyle(stborder);
            cellsubcountyV.setCellStyle(stborder);
            cellwardV.setCellStyle(stborder);
            cellfacilityV.setCellStyle(stborder);
            cellmflV.setCellStyle(stborder);

        rowcounter++;
       }
       else{
          facil_counter = mfl_codes.indexOf(mfl_code)+2;
            
           rwdata = shet.getRow(facil_counter);
       }
//       append data here
              rwdata.setHeightInPoints(26); 
            
        XSSFCell celldhisV = rwdata.createCell(((elems_counter+1)*4)+1-(elems_counter));
        XSSFCell cellimisV = rwdata.createCell(((elems_counter+1)*4)+2-(elems_counter));
        XSSFCell cellvarianceV = rwdata.createCell(((elems_counter+1)*4)+3-(elems_counter));   
        
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
     return wb; 
    }
}