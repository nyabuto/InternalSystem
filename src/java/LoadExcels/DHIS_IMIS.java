/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoadExcels;

import database.dbConn;
import java.sql.SQLException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;

/**
 *
 * @author GNyabuto
 */
public class DHIS_IMIS {
 String yearmonth; 
 dbConn conn = new dbConn();
 String id,query,dhis_label,imis_label,explanation,service_area;
 int imis,dhis,variance;
 String county,sub_county,ward,facility,mfl_code;
 int rowcounter;
 int elems_counter,facil_counter;
    public HSSFWorkbook get_params(String ym, HSSFWorkbook wb) throws SQLException{
        // HSSFWorkbook wb = new HSSFWorkbook();

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
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);

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
        stylex.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        HSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFFont fontx = wb.createFont();
        fontx.setColor(HSSFColor.BLACK.index);
        fontx.setFontName("Cambria");
        stylex.setFont(fontx);
        stylex.setWrapText(true);

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        HSSFSheet shet = wb.createSheet("IMIS-DHIS MAPPING");
        rowcounter =facil_counter= 0;
        HSSFRow rw = shet.createRow(rowcounter);
        rw.setHeightInPoints(26);
        
        
                
            HSSFCell cellcounty = rw.createCell(0);
            HSSFCell cellsubcounty = rw.createCell(1);
            HSSFCell cellward = rw.createCell(2);
            HSSFCell cellfacility = rw.createCell(3);
            HSSFCell cellmfl = rw.createCell(4);
            
            cellcounty.setCellValue("County");
            cellsubcounty.setCellValue("Sub County");
            cellward.setCellValue("Ward");
            cellfacility.setCellValue("Health Facility");
            cellmfl.setCellValue("MFL Code");
            
            
         rowcounter++;   
        yearmonth = ym;
      elems_counter = 0;
     String compare_data = "SELECT id,IFNULL(query,'') AS query,IFNULL(dhis_label,'') AS dhis_label,IFNULL(imis_label,'') AS imis_label,IFNULL(explanation,'') AS explanation,IFNULL(service_area,'') AS service_area FROM imis_dhis_mapping WHERE active=1"; 
     conn.rs = conn.st.executeQuery(compare_data);
     while(conn.rs.next()){
        id  = conn.rs.getString("id");
        query  = conn.rs.getString("query");
        dhis_label  = conn.rs.getString("dhis_label");
        imis_label  = conn.rs.getString("imis_label");
        explanation  = conn.rs.getString("explanation");
        service_area  = conn.rs.getString("service_area");
        
        //output headers
        
            HSSFCell celldhis = rw.createCell((elems_counter*4)+1);
            HSSFCell cellimis = rw.createCell((elems_counter*4)+2);
            HSSFCell cellvariance = rw.createCell((elems_counter*4)+3);
            
            celldhis.setCellValue(dhis_label);
            cellimis.setCellValue(imis_label);
            cellvariance.setCellValue("Variance");
       
        
        
        query = query.replace("YM", yearmonth);
         System.out.println("final query is : "+query);
         
         facil_counter=0;
        conn.rs1 = conn.st1.executeQuery(query);
        while(conn.rs1.next()){
            HSSFRow rwdata = null;
            facil_counter++;
         county = conn.rs1.getString(2);
         sub_county = conn.rs1.getString(3);
         ward = conn.rs1.getString(4);
         facility = conn.rs1.getString(1);
         mfl_code = conn.rs1.getString(5); 
            
        imis = conn.rs1.getInt(6);
        dhis = conn.rs1.getInt(7);
        variance = conn.rs1.getInt(8); 
       if(elems_counter==0){ 
       rwdata = shet.createRow(rowcounter);
        rowcounter++;
       }
       else{
           rwdata = shet.getRow(facil_counter);
       }
//       append data here
              rwdata.setHeightInPoints(26); 
            
              
        }
        elems_counter++;
     }
     return wb; 
    }
}