/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataCleaning;

import database.dbConn;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author GNyabuto
 */
public class DataCleanerClass {
    dbConn conn = new dbConn();
    
    public XSSFWorkbook TB(XSSFWorkbook tb,XSSFCellStyle redstyle,String start_date,String end_date){
        int col_count = 20;
        XSSFSheet worksheet;
        
        worksheet = tb.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        while(rowIterator.hasNext()){
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;}
        
        
       for (int j=0;j<col_count;j++){
     
       } 

            i++;
        }
        
         
        
        return tb;
    }
    public XSSFWorkbook ViralLoad(XSSFWorkbook viralload,XSSFCellStyle redstyle,String start_date,String end_date){
           int col_count = 20;
        XSSFSheet worksheet;
        
        worksheet = viralload.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        while(rowIterator.hasNext()){
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;}
        
        
       for (int j=0;j<col_count;j++){
     
       } 

            i++;
        }
        
        
        
        return viralload;
    }
    public XSSFWorkbook EIDTST(XSSFWorkbook eid,XSSFCellStyle redstyle,String start_date,String end_date){
         int col_count = 20;
        XSSFSheet worksheet;
        
        worksheet = eid.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        while(rowIterator.hasNext()){
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;}
        
        
       for (int j=0;j<col_count;j++){
     
       } 

            i++;
        }
 
        return eid;
    }
    
    
    public XSSFWorkbook EIDPOS(XSSFWorkbook eid,XSSFCellStyle redstyle,String start_date,String end_date) throws ParseException, SQLException{
    String errors,age,area,mfl_code,pcr_type,validation,date_tested;
        XSSFSheet worksheet;
    // Column to check age<=12 col-no8
    // site not pmtct and not on our list of supported sites - mflcode col-no5
    // PCR Type is not initial PCR col-no9
    // validation` IS NOT A col-no12
    // Date Between use date tested
    
        worksheet = eid.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        
        XSSFRow rowhead = worksheet.getRow(0);
        XSSFCell cellh = rowhead.createCell(17);
        cellh.setCellValue("Errors");
        
        
        
        while(rowIterator.hasNext()){
          errors=age=mfl_code=area=pcr_type=validation=date_tested="" ; 
        
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;
        }
       
        //age
         XSSFCell cellAge = rowi.getCell((short) 8);
            if(cellAge==null){
                break;
            }
            else{
               switch (cellAge.getCellType()) {
                   case 0:
                       //numeric
                       age =""+(int)cellAge.getNumericCellValue();
                       break;
                   case 1:
                       age =cellAge.getStringCellValue();
                       break;
                   default:
                       age = cellAge.getRawValue();
                       break;
               }
            }
            age = age.replace(" ", "").trim();
           if(age.equals("")){
            cellAge.setCellStyle(redstyle);
            errors+=" No age \n";
           }
           else{
               double eid_age = Double.parseDouble(age);
               if(eid_age<0 || eid_age>12){ // age out of brackets.
              cellAge.setCellStyle(redstyle);
              errors+=" Age given is outside accepted range \n";    
               }
           }
            
        //mflcode
          XSSFCell cellMFLCode = rowi.getCell((short) 5);
            if(cellMFLCode==null){
                break;
            }
            else{
               switch (cellMFLCode.getCellType()) {
                   case 0:
                       //numeric
                       mfl_code =""+(int)cellMFLCode.getNumericCellValue();
                       break;
                   case 1:
                       mfl_code =cellMFLCode.getStringCellValue();
                       break;
                   default:
                       mfl_code = cellMFLCode.getRawValue();
                       break;
               }
            }
            area = " PMTCT=1 ";
        if(!issupported(mfl_code,area)){
          cellMFLCode.setCellStyle(redstyle);
          errors+=" This facility is not a HSDSA PMTCT supported site \n";  
        }
            
        //pcr type
          XSSFCell cellPCR = rowi.getCell((short) 9);
            if(cellPCR==null){
                break;
            }
            else{
               switch (cellPCR.getCellType()) {
                   case 0:
                       //numeric
                       pcr_type =""+(int)cellPCR.getNumericCellValue();
                       break;
                   case 1:
                       pcr_type =cellPCR.getStringCellValue();
                       break;
                   default:
                       pcr_type = cellPCR.getRawValue();
                       break;
               }
            }
                        
          if(pcr_type.contains("initial PCR")) {
           cellPCR.setCellStyle(redstyle);
            errors+="PCR type is not initial PCR \n";   
          } 
          
        //validation
  XSSFCell cellValidation = rowi.getCell((short) 12);
            if(cellValidation==null){
                break;
            }
            else{
               switch (cellValidation.getCellType()) {
                   case 0:
                       //numeric
                       validation =""+(int)cellValidation.getNumericCellValue();
                       break;
                   case 1:
                       validation =cellValidation.getStringCellValue();
                       break;
                   default:
                       validation = cellValidation.getRawValue();
                       break;
               }
            }
            System.out.println("validation : "+validation);       
          if(validation !=null && validation.equals("A")) { 
          cellValidation.setCellStyle(redstyle);
            errors+=" Validation is A \n";    
          }      
            
        //date tested
  XSSFCell cellDate = rowi.getCell((short) 11);
            if(cellDate==null){
                break;
            }
            else{
               switch (cellDate.getCellType()) {
                   case 0:
                       //numeric
                       date_tested =""+(int)cellDate.getNumericCellValue();
                       break;
                   case 1:
                       date_tested =cellDate.getStringCellValue();
                       break;
                   default:
                       date_tested = cellDate.getRawValue();
                       break;
               }
            }
            
           if(!isbetween(date_tested,start_date,end_date)){
          cellDate.setCellStyle(redstyle);
            errors+=" Date tested is not within the accepted range i.e "+date_tested+"  not within "+start_date+" and "+end_date+" \n";  
        }
            
            
         XSSFCell cellerror = rowi.createCell(17);
         cellerror.setCellValue(errors);
           
            
            i++;
        }
        
            
        
        
        return eid;
    }
    
  public boolean isbetween(String date_tested,String start_date,String end_date) throws ParseException {
    boolean is_d=true;
    if(!date_tested.equals("")){
    Date tst,start,end;
    tst = new SimpleDateFormat("yyyy-MM-dd").parse(date_tested);  
    start = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);  
    end = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);  
    
    is_d = tst.after(start) && tst.before(end);
    }
    else{
     is_d = false;   
    }
    
    return is_d;
   }
  
  public boolean issupported(String mfl_code,String area) throws SQLException{
   boolean is_mfl= true; 
   
   String checkifsupported="SELECT SubPartnerID FROM subpartnera WHERE active=1 AND CentreSanteId='"+mfl_code+"' AND ("+area+")";
   conn.rs = conn.st.executeQuery(checkifsupported);
   if(!conn.rs.next()){
   is_mfl=false;    
   }
   
   return is_mfl;
  }     
}
