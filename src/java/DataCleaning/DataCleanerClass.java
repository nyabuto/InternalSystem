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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author GNyabuto
 */
public class DataCleanerClass {
    dbConn conn = new dbConn();
    
  public XSSFWorkbook TB(XSSFWorkbook tb,CellStyle redstyle,String start_date,String end_date) throws ParseException, SQLException{
    String where,errors,sex,age,hiv_status,hiv_test_date,art_status,art_start_date,treatment_date,area,health_facility;
        XSSFSheet worksheet;
        int col_error = 59;
        int date_format=0,unsupported_site=0;
        XSSFSheet errorSheet = null;
        XSSFSheet cleanSheet = null;
        //*Blank Sex (M)(12) --12
        //*Age (N)(13) above 100 ysrs and below 0--13 
        //*HIV Status (AT)(45) is Neg or POs and  HIV Test Date is Blank (AS)(44)
        //*ART Status (BA)(52) is Yes and ART start Date (BB)(53) is Blank
        //*ART Start date (BB)(53) is greater than HIV Test Date (AS)(44)
        //*ART Start Date (BB)(53), HIV Test Date(AS)(44) , Date of treatment started (AO)(40) Not in dd mmm YYYY (eg 04 Jan 2018) format.

    
        worksheet = tb.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();
        if(tb.getSheet("Clean Data")!=null){
        errorSheet = tb.getSheet("Clean Data");
        }
        else{
        errorSheet = tb.createSheet("Clean Data");    
        }
        if(tb.getSheet("TB Errors")!=null){
        cleanSheet = tb.getSheet("TB Errors");
        }
        else{
        cleanSheet = tb.createSheet("TB Errors");    
        }
        
        int i=1,j=0,y=0;
        
        XSSFRow rowhead = worksheet.getRow(0);
        XSSFCell cellh = rowhead.createCell(col_error);
        cellh.setCellValue("Errors");
        
       copyRow(tb,rowhead,cleanSheet,0);
       copyRow(tb,rowhead,errorSheet,0);

        while(rowIterator.hasNext()){
          errors=where=sex=age=hiv_status=hiv_test_date=art_status=art_start_date=treatment_date=area=health_facility=""; 
          date_format = unsupported_site = 0;
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;
        }
       
        //sex
         XSSFCell cellSex = rowi.getCell((short) 12);
            if(cellSex==null){
                break;
            }
            else{
               switch (cellSex.getCellType()) {
                   case 0:
                       //numeric
                       sex =""+(int)cellSex.getNumericCellValue();
                       break;
                   case 1:
                       sex =cellSex.getStringCellValue();
                       break;
                   default:
                       sex = cellSex.getRawValue();
                       break;
               }
            }
           
        //age
         XSSFCell cellAge = rowi.getCell((short) 13);
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
           
        //HIVStatus
         XSSFCell cellHIVStatus = rowi.getCell((short) 45);
            if(cellHIVStatus==null){
                break;
            }
            else{
               switch (cellHIVStatus.getCellType()) {
                   case 0:
                       //numeric
                       hiv_status =""+(int)cellHIVStatus.getNumericCellValue();
                       break;
                   case 1:
                       hiv_status =cellHIVStatus.getStringCellValue();
                       break;
                   default:
                       hiv_status = cellHIVStatus.getRawValue();
                       break;
               }
            }
           
            
        //HIVTestDate
         XSSFCell cellHIVTestDate = rowi.getCell((short) 44);
            if(cellHIVTestDate==null){
                break;
            }
            else{
               switch (cellHIVTestDate.getCellType()) {
                   case 0:
                       //numeric
                       hiv_test_date =""+(int)cellHIVTestDate.getNumericCellValue();
                       break;
                   case 1:
                       hiv_test_date =cellHIVTestDate.getStringCellValue();
                       break;
                   default:
                       hiv_test_date = cellHIVTestDate.getRawValue();
                       break;
               }
            } 
           
            
        //ARTStatus
         XSSFCell cellARTStatus = rowi.getCell((short) 52);
            if(cellARTStatus==null){
                break;
            }
            else{
               switch (cellARTStatus.getCellType()) {
                   case 0:
                       //numeric
                       art_status =""+(int)cellARTStatus.getNumericCellValue();
                       break;
                   case 1:
                       art_status =cellARTStatus.getStringCellValue();
                       break;
                   default:
                       art_status = cellARTStatus.getRawValue();
                       break;
               }
            }
           
           
            
        //ARTStartDate
         XSSFCell cellARTStartDate = rowi.getCell((short) 53);
            if(cellARTStartDate==null){
                break;
            }
            else{
               switch (cellARTStartDate.getCellType()) {
                   case 0:
                       //numeric
                       art_start_date =""+(int)cellARTStartDate.getNumericCellValue();
                       break;
                   case 1:
                       art_start_date =cellARTStartDate.getStringCellValue();
                       break;
                   default:
                       art_start_date = cellARTStartDate.getRawValue();
                       break;
               }
            }
           
           
            
        //TreatmentDate
         XSSFCell cellTreatmentDate = rowi.getCell((short) 40);
            if(cellTreatmentDate==null){
                break;
            }
            else{
               switch (cellTreatmentDate.getCellType()) {
                   case 0:
                       //numeric
                       treatment_date =""+(int)cellTreatmentDate.getNumericCellValue();
                       break;
                   case 1:
                       treatment_date =cellTreatmentDate.getStringCellValue();
                       break;
                   default:
                       treatment_date = cellTreatmentDate.getRawValue();
                       break;
               }
            }
            
            
        //HealthFacility
          XSSFCell cellHealthFacility = rowi.getCell((short) 7);
            if(cellHealthFacility==null){
                break;
            }
            else{
               switch (cellHealthFacility.getCellType()) {
                   case 0:
                       //numeric
                       health_facility =""+(int)cellHealthFacility.getNumericCellValue();
                       break;
                   case 1:
                       health_facility =cellHealthFacility.getStringCellValue();
                       break;
                   default:
                       health_facility = cellHealthFacility.getRawValue();
                       break;
               }
            }
           
            if(age==null){age="";}
            if(health_facility==null){health_facility="";}
            if(sex==null){sex="";}
            if(art_start_date==null){art_start_date="";}
            if(art_status==null){art_status="";}
            if(hiv_test_date==null){hiv_test_date="";}
            if(hiv_status==null){hiv_status="";}
            if(treatment_date==null){treatment_date="";}
            
          
        where = " (tibu_name=? OR SubPartnerNom=?) AND  ART=1 ";
        String [] where_params = {"1##"+health_facility+"","2##"+health_facility+""};
        if(!issupported(where,where_params)){
          cellHealthFacility.setCellStyle(redstyle);
          errors+=" This facility is not a HSDSA TB supported site.\n"; 
          unsupported_site++;
        }
        
        
        if(sex==null || sex.equals("")){
          cellSex.setCellStyle(redstyle);
          errors+=" Missing sex.\n";  
        }
            System.out.println("Age : "+age);
         age = age.replace("Y", ".");
         age = age.split("\\.", 2)[0];
        if(Integer.parseInt(age)>100){
          cellAge.setCellStyle(redstyle);
          errors+="Age is more than 100 yrs i.e "+age+".\n";  
        }
        
        hiv_status = hiv_status.trim();
        hiv_test_date = hiv_test_date.trim();
        if((hiv_status.equalsIgnoreCase("Neg") || hiv_status.equalsIgnoreCase("Pos")) && (hiv_test_date==null || hiv_test_date.equals(""))){
         cellHIVTestDate.setCellStyle(redstyle);
          errors+="Missing HIV Test Date.\n";     
        }
        
        art_status = art_status.trim();
        art_start_date = art_start_date.trim();
        if(art_status.equalsIgnoreCase("Yes") && (art_start_date==null || art_start_date.equals(""))){
         cellARTStartDate.setCellStyle(redstyle);
          errors+="Missing ART Start Date.\n";     
        }
        
        if(!art_start_date.equals("")){
        try{
    new SimpleDateFormat("dd MMM yyyy").parse(art_start_date);     
        }
        catch(Exception e){
            date_format++;
         cellARTStartDate.setCellStyle(redstyle);
         errors+="Wrong ART start date format.\n";     
        }
        }
        
        if(!hiv_test_date.equals("")){
        try{
    new SimpleDateFormat("dd MMM yyyy").parse(hiv_test_date);     
        }
        catch(Exception e){
            date_format++;
         cellHIVTestDate.setCellStyle(redstyle);
         errors+="Wrong HIV test date format.\n";     
        }
        }
        
        if(!treatment_date.equals("")){
        try{
    new SimpleDateFormat("dd MMM yyyy").parse(treatment_date);     
        }
        catch(Exception e){
         cellTreatmentDate.setCellStyle(redstyle);
         errors+="Wrong treatment date format.\n";     
        }
        }
        
     if(date_format==0 && !art_start_date.equals("") && !hiv_test_date.equals("")){
     Date art = new SimpleDateFormat("dd MMM yyyy").parse(art_start_date);  
     Date hiv = new SimpleDateFormat("dd MMM yyyy").parse(hiv_test_date);     
        
        if(art.before(hiv)){
         cellARTStartDate.setCellStyle(redstyle);
         cellHIVTestDate.setCellStyle(redstyle);
         errors+="ART start date is less than HIV Test date.\n";     
        }
        
        
        }
     
     // reporting date within range
     
     if(!hiv_test_date.equals("")){
         System.out.println("HIV Test date exist");
        try{
     Date start = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);  
    Date end = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
    Date hiv = new SimpleDateFormat("dd MMM yyyy").parse(hiv_test_date); 
            System.out.println("HIV Test date is a date");
    if(hiv.before(start) || hiv.after(end)){
     cellHIVTestDate.setCellStyle(redstyle);
     errors+="HIV Test Date is not within reporting Range.\n";  
        System.out.println("Is not within range");     
    }
    }
        catch(Exception e){
       }
        }
     
     
     
     
     
         XSSFCell cellerror = rowi.createCell(col_error);
         cellerror.setCellValue(errors);

         if(!errors.equals("")){
             j++;
//           copyRow(tb,rowi,errorSheet,j);
        if(unsupported_site>0){
//                    worksheet.removeRow(rowi);   
        }

         }
         else{
             y++;
//           copyRow(tb,rowi,cleanSheet,y);
           //worksheet.removeRow(rowi);
         }
//         if(errors.equals("")){
//           worksheet.removeRow(rowi);
//         }
System.out.println("at position : "+i);
            i++;
         
        }
        
            
//        worksheet = removeRow(worksheet);
        
        return tb;
    }
 
  public XSSFWorkbook ViralLoad(XSSFWorkbook vl,CellStyle redstyle,CellStyle styleborder,String start_date,String end_date) throws ParseException, SQLException{
    String where,errors,mfl_code,sex,age,date_tested,valid_result;
        XSSFSheet worksheet;
        int col_error = 27;
        int unsupported_site=0;
        XSSFSheet errorSheet = null;
        XSSFSheet cleanSheet = null;
        //MFL Code active=1,(ART=1 OR PMTCT=1) 9
        //Sex Sex !='' --------------------------10
        //Age and (AgeYrs!='' and AgeYrs>=0) ----11
        //Date Tested Date_Tested BETWEEN '"+startdate+"' AND '"+enddate+"' --22
        //Valid results Valid_Result='Y' --------24
        
        

        worksheet = vl.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();
        
        if(vl.getSheet("Clean Data")!=null){
        errorSheet = vl.getSheet("Clean Data");
        }
        else{
        errorSheet = vl.createSheet("Clean Data");    
        }
        if(vl.getSheet("Viral Load Errors")!=null){
        cleanSheet = vl.getSheet("Viral Load Errors");
        }
        else{
        cleanSheet = vl.createSheet("Viral Load Errors");    
        }
//        XSSFSheet errorSheet = vl.createSheet("Clean Data");
//        XSSFSheet cleanSheet = vl.createSheet("Viral Load Errors");
        
        int i=1,j=0,y=0;
        
        XSSFRow rowhead = worksheet.getRow(0);
        XSSFCell cellh = rowhead.createCell(col_error);
        cellh.setCellValue("Errors");
        cellh.setCellStyle(styleborder);
        
        
        XSSFCell firstCell = rowhead.getCell(0);
        XSSFCell lastCell = rowhead.getCell(col_error);
        
        worksheet.setAutoFilter(new CellRangeAddress( firstCell.getRowIndex(), lastCell.getRowIndex(), firstCell.getColumnIndex(), lastCell.getColumnIndex() ));
        
        
       copyRow(vl,rowhead,cleanSheet,0);
       copyRow(vl,rowhead,errorSheet,0);

        while(rowIterator.hasNext()){
          errors=where=sex=age=mfl_code=date_tested=valid_result=""; 
          unsupported_site = 0;
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;
        }
       
        
                    
        //MFL Code
          XSSFCell cellMFLCode = rowi.getCell((short) 9);
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
                       mfl_code = cellMFLCode.getStringCellValue();
                       break;
                   default:
                       mfl_code = cellMFLCode.getRawValue();
                       break;
               }
            }
           
        //sex
         XSSFCell cellSex = rowi.getCell((short) 10);
            if(cellSex==null){
                break;
            }
            else{
               switch (cellSex.getCellType()) {
                   case 0:
                       //numeric
                       sex =""+(int)cellSex.getNumericCellValue();
                       break;
                   case 1:
                       sex =cellSex.getStringCellValue();
                       break;
                   default:
                       sex = cellSex.getRawValue();
                       break;
               }
            }
           
        //age
         XSSFCell cellAge = rowi.getCell((short) 11);
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
        
            
        //Date Tested
         XSSFCell cellTestDate = rowi.getCell((short) 22);
            if(cellTestDate==null){
                break;
            }
            else{
               switch (cellTestDate.getCellType()) {
                   case 0:
                       //numeric
                       date_tested =""+(int)cellTestDate.getNumericCellValue();
                       break;
                   case 1:
                       date_tested =cellTestDate.getStringCellValue();
                       break;
                   default:
                       date_tested = cellTestDate.getRawValue();
                       break;
               }
            } 
           
          
            
        //ValidResults 
         XSSFCell cellValidResults = rowi.getCell((short) 24);
            if(cellValidResults==null){
                break;
            }
            else{
               switch (cellValidResults.getCellType()) {
                   case 0:
                       //numeric
                       valid_result =""+(int)cellValidResults.getNumericCellValue();
                       break;
                   case 1:
                       valid_result =cellValidResults.getStringCellValue();
                       break;
                   default:
                       valid_result = cellValidResults.getRawValue();
                       break;
               }
            } 
           
          
            if(mfl_code==null){mfl_code="";}            
            if(age==null){age="";}
            if(sex==null){sex="";}
            if(date_tested==null){date_tested="";}
            if(valid_result==null){valid_result="";}
            
          
        where = " CentreSanteID=? AND  (ART=1 OR PMTCT=1) ";
        String [] where_params = {"1##"+mfl_code+""};
        if(!issupported(where,where_params)){
          cellMFLCode.setCellStyle(redstyle);
          errors+=" This facility is not a HSDSA ART OR PMTCT supported site.\n"; 
          unsupported_site++;
        }
        
        
        if(sex.equals("")){
          cellSex.setCellStyle(redstyle);
          errors+=" Missing sex.\n";  
        }
        if(age.equals("")){
        cellAge.setCellStyle(redstyle);
          errors+="Missing Age \n";      
        }
        else{
            age = age.replace(" ", "").trim();
        if(Double.parseDouble(age)>100 || Double.parseDouble(age)<0){
          cellAge.setCellStyle(redstyle);
          errors+="Age is less than 0 or more than 100 yrs\n";  
        }
        }
        
         if(valid_result.equals("")){
          cellSex.setCellStyle(redstyle);
          errors+=" Missing valid results.\n";  
        }
         else{
        if(valid_result.equals("N")){
          cellValidResults.setCellStyle(redstyle);
          errors+="Results not valid.\n";  
        }   
         }
       
     if(!date_tested.equals("")){
         try{
     Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);  
     Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);     
     Date DateTested = new SimpleDateFormat("yyyy-MM-dd").parse(date_tested);     
         
        if(DateTested.before(startDate) || DateTested.after(endDate)){
         cellTestDate.setCellStyle(redstyle);
         errors+="Date tested is out of range.\n";     
        }
         }
         catch(Exception e){
         cellTestDate.setCellStyle(redstyle);
         errors+="Wrong Date tested format.\n";     
        }
        }
        
        
         XSSFCell cellerror = rowi.createCell(col_error);
         cellerror.setCellValue(errors);
         cellerror.setCellStyle(styleborder);

         if(!errors.equals("")){
             j++;
//           copyRow(tb,rowi,errorSheet,j);
        if(unsupported_site>0){
                   // worksheet.removeRow(rowi);   
        }

         }
         else{
             y++;
//           copyRow(tb,rowi,cleanSheet,y);
           //worksheet.removeRow(rowi);
         }
//         if(errors.equals("")){
//           worksheet.removeRow(rowi);
//         }
System.out.println(" viral load at position : "+i);
            i++;
         
        }
        
            
//        worksheet = removeRow(worksheet);
        
        return vl;
    }
 
  public XSSFWorkbook EIDTST(XSSFWorkbook eid,CellStyle redstyle,String start_date,String end_date) throws ParseException, SQLException{
    String errors,age,where,mfl_code,pcr_type,date_tested;
        XSSFSheet worksheet;
    // Column to check age<=12 col-no11
    // site not pmtct and not on our list of supported sites - mflcode col-no8
    // PCR Type is not initial PCR col-no16
    // Date Between use date tested-23
    
        worksheet = eid.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        
        XSSFRow rowhead = worksheet.getRow(0);
        XSSFCell cellh = rowhead.createCell(26);
        cellh.setCellValue("Errors");
        
        
        
        while(rowIterator.hasNext()){
          errors=age=mfl_code=where=pcr_type=date_tested="" ; 
        
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;
        }
       
        //age
         XSSFCell cellAge = rowi.getCell((short) 11);
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
          XSSFCell cellMFLCode = rowi.getCell((short) 8);
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
        where = "CentreSanteId='"+mfl_code+"' AND  PMTCT=1 ";
        String [] where_params = {};
        if(!issupported(where,where_params)){
          cellMFLCode.setCellStyle(redstyle);
          errors+=" This facility is not a HSDSA PMTCT supported site \n";  
        }
            
        //pcr type
          XSSFCell cellPCR = rowi.getCell((short) 16);
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

        //date tested
  XSSFCell cellDate = rowi.getCell((short) 23);
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
            
            
         XSSFCell cellerror = rowi.createCell(26);
         cellerror.setCellValue(errors);
           
            
            i++;
        }
        
            
        
        
        return eid;
    }
 
  public XSSFWorkbook EIDPOS(XSSFWorkbook eid,CellStyle redstyle,String start_date,String end_date) throws ParseException, SQLException{
    String errors,age,where,mfl_code,pcr_type,validation,date_tested;
        XSSFSheet worksheet;
    // Column to check age<=12 col-no8
    // site not pmtct and not on our list of supported sites - mflcode col-no5
    // PCR Type is not initial PCR col-no9
    // validation` IS NOT A col-no12
    // Date Between use date tested 11
    
        worksheet = eid.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        
        XSSFRow rowhead = worksheet.getRow(0);
        XSSFCell cellh = rowhead.createCell(17);
        cellh.setCellValue("Errors");
        
        
        
        while(rowIterator.hasNext()){
          errors=age=mfl_code=where=pcr_type=validation=date_tested="" ; 
        
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
           where = "CentreSanteId='"+mfl_code+"' AND  PMTCT=1 ";
        String [] where_params = {};
        if(!issupported(where,where_params)){
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
    
  
    
  public HSSFWorkbook TB(HSSFWorkbook tb,CellStyle redstyle,String start_date,String end_date) throws ParseException, SQLException{
    String where,errors,sex,age,hiv_status,hiv_test_date,art_status,art_start_date,treatment_date,area,health_facility;
        HSSFSheet worksheet;
        int col_error = 59;
        int date_format=0,unsupported_site=0;
        HSSFSheet errorSheet = null;
        HSSFSheet cleanSheet = null;
        //*Blank Sex (M)(12) --12
        //*Age (N)(13) above 100 ysrs and below 0--13 
        //*HIV Status (AT)(45) is Neg or POs and  HIV Test Date is Blank (AS)(44)
        //*ART Status (BA)(52) is Yes and ART start Date (BB)(53) is Blank
        //*ART Start date (BB)(53) is greater than HIV Test Date (AS)(44)
        //*ART Start Date (BB)(53), HIV Test Date(AS)(44) , Date of treatment started (AO)(40) Not in dd mmm YYYY (eg 04 Jan 2018) format.

    
        worksheet = tb.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();
        if(tb.getSheet("Clean Data")!=null){
        errorSheet = tb.getSheet("Clean Data");
        }
        else{
        errorSheet = tb.createSheet("Clean Data");    
        }
        if(tb.getSheet("TB Errors")!=null){
        cleanSheet = tb.getSheet("TB Errors");
        }
        else{
        cleanSheet = tb.createSheet("TB Errors");    
        }
        
        
        int i=1,j=0,y=0;
        
        HSSFRow rowhead = worksheet.getRow(0);
        HSSFCell cellh = rowhead.createCell(col_error);
        cellh.setCellValue("Errors");
        
       copyRow(tb,rowhead,cleanSheet,0);
       copyRow(tb,rowhead,errorSheet,0);

        while(rowIterator.hasNext()){
          errors=where=sex=age=hiv_status=hiv_test_date=art_status=art_start_date=treatment_date=area=health_facility=""; 
          date_format = unsupported_site = 0;
        HSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;
        }
       
        //sex
         HSSFCell cellSex = rowi.getCell((short) 12);
            if(cellSex==null){
                break;
            }
            else{
               switch (cellSex.getCellType()) {
                   case 0:
                       //numeric
                       sex =""+(int)cellSex.getNumericCellValue();
                       break;
                   case 1:
                       sex =cellSex.getStringCellValue();
                       break;
                   default:
                       sex = cellSex.getStringCellValue();
                       break;
               }
            }
           
        //age
         HSSFCell cellAge = rowi.getCell((short) 13);
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
                       age = cellAge.getStringCellValue();
                       break;
               }
            }
           
        //HIVStatus
         HSSFCell cellHIVStatus = rowi.getCell((short) 45);
            if(cellHIVStatus==null){
                break;
            }
            else{
               switch (cellHIVStatus.getCellType()) {
                   case 0:
                       //numeric
                       hiv_status =""+(int)cellHIVStatus.getNumericCellValue();
                       break;
                   case 1:
                       hiv_status =cellHIVStatus.getStringCellValue();
                       break;
                   default:
                       hiv_status = cellHIVStatus.getStringCellValue();
                       break;
               }
            }
           
            
        //HIVTestDate
         HSSFCell cellHIVTestDate = rowi.getCell((short) 44);
            if(cellHIVTestDate==null){
                break;
            }
            else{
               switch (cellHIVTestDate.getCellType()) {
                   case 0:
                       //numeric
                       hiv_test_date =""+(int)cellHIVTestDate.getNumericCellValue();
                       break;
                   case 1:
                       hiv_test_date =cellHIVTestDate.getStringCellValue();
                       break;
                   default:
                       hiv_test_date = cellHIVTestDate.getStringCellValue();
                       break;
               }
            } 
           
            
        //ARTStatus
         HSSFCell cellARTStatus = rowi.getCell((short) 52);
            if(cellARTStatus==null){
                break;
            }
            else{
               switch (cellARTStatus.getCellType()) {
                   case 0:
                       //numeric
                       art_status =""+(int)cellARTStatus.getNumericCellValue();
                       break;
                   case 1:
                       art_status =cellARTStatus.getStringCellValue();
                       break;
                   default:
                       art_status = cellARTStatus.getStringCellValue();
                       break;
               }
            }
           
           
            
        //ARTStartDate
         HSSFCell cellARTStartDate = rowi.getCell((short) 53);
            if(cellARTStartDate==null){
                break;
            }
            else{
               switch (cellARTStartDate.getCellType()) {
                   case 0:
                       //numeric
                       art_start_date =""+(int)cellARTStartDate.getNumericCellValue();
                       break;
                   case 1:
                       art_start_date =cellARTStartDate.getStringCellValue();
                       break;
                   default:
                       art_start_date = cellARTStartDate.getStringCellValue();
                       break;
               }
            }
           
           
            
        //TreatmentDate
         HSSFCell cellTreatmentDate = rowi.getCell((short) 40);
            if(cellTreatmentDate==null){
                break;
            }
            else{
               switch (cellTreatmentDate.getCellType()) {
                   case 0:
                       //numeric
                       treatment_date =""+(int)cellTreatmentDate.getNumericCellValue();
                       break;
                   case 1:
                       treatment_date =cellTreatmentDate.getStringCellValue();
                       break;
                   default:
                       treatment_date = cellTreatmentDate.getStringCellValue();
                       break;
               }
            }
            
            
        //HealthFacility
          HSSFCell cellHealthFacility = rowi.getCell((short) 7);
            if(cellHealthFacility==null){
                break;
            }
            else{
               switch (cellHealthFacility.getCellType()) {
                   case 0:
                       //numeric
                       health_facility =""+(int)cellHealthFacility.getNumericCellValue();
                       break;
                   case 1:
                       health_facility =cellHealthFacility.getStringCellValue();
                       break;
                   default:
                       health_facility = cellHealthFacility.getStringCellValue();
                       break;
               }
            }
           
            if(age==null){age="";}
            if(health_facility==null){health_facility="";}
            if(sex==null){sex="";}
            if(art_start_date==null){art_start_date="";}
            if(art_status==null){art_status="";}
            if(hiv_test_date==null){hiv_test_date="";}
            if(hiv_status==null){hiv_status="";}
            if(treatment_date==null){treatment_date="";}
            
          
        where = " (tibu_name=? OR SubPartnerNom=?) AND  ART=1 ";
        String [] where_params = {"1##"+health_facility+"","2##"+health_facility+""};
        if(!issupported(where,where_params)){
          cellHealthFacility.setCellStyle(redstyle);
          errors+=" This facility is not a HSDSA TB supported site.\n"; 
          unsupported_site++;
        }
        
        
        if(sex==null || sex.equals("")){
          cellSex.setCellStyle(redstyle);
          errors+=" Missing sex.\n";  
        }
            System.out.println("Age : "+age);
         age = age.replace("Y", ".");
         age = age.split("\\.", 2)[0];
        if(Integer.parseInt(age)>100){
          cellAge.setCellStyle(redstyle);
          errors+="Age is more than 100 yrs i.e "+age+".\n";  
        }
        
        hiv_status = hiv_status.trim();
        hiv_test_date = hiv_test_date.trim();
        if((hiv_status.equalsIgnoreCase("Neg") || hiv_status.equalsIgnoreCase("Pos")) && (hiv_test_date==null || hiv_test_date.equals(""))){
         cellHIVTestDate.setCellStyle(redstyle);
          errors+="Missing HIV Test Date.\n";     
        }
        
        art_status = art_status.trim();
        art_start_date = art_start_date.trim();
        if(art_status.equalsIgnoreCase("Yes") && (art_start_date==null || art_start_date.equals(""))){
         cellARTStartDate.setCellStyle(redstyle);
          errors+="Missing ART Start Date.\n";     
        }
        
        if(!art_start_date.equals("")){
        try{
    new SimpleDateFormat("dd MMM yyyy").parse(art_start_date);     
        }
        catch(Exception e){
            date_format++;
         cellARTStartDate.setCellStyle(redstyle);
         errors+="Wrong ART start date format.\n";     
        }
        }
        
        if(!hiv_test_date.equals("")){
        try{
    new SimpleDateFormat("dd MMM yyyy").parse(hiv_test_date);     
        }
        catch(Exception e){
            date_format++;
         cellHIVTestDate.setCellStyle(redstyle);
         errors+="Wrong HIV test date format.\n";     
        }
        }
        
        if(!treatment_date.equals("")){
        try{
    new SimpleDateFormat("dd MMM yyyy").parse(treatment_date);     
        }
        catch(Exception e){
         cellTreatmentDate.setCellStyle(redstyle);
         errors+="Wrong treatment date format.\n";     
        }
        }
        
     if(date_format==0 && !art_start_date.equals("") && !hiv_test_date.equals("")){
     Date art = new SimpleDateFormat("dd MMM yyyy").parse(art_start_date);  
     Date hiv = new SimpleDateFormat("dd MMM yyyy").parse(hiv_test_date);     
        
        if(art.before(hiv)){
         cellARTStartDate.setCellStyle(redstyle);
         cellHIVTestDate.setCellStyle(redstyle);
         errors+="ART start date is less than HIV Test date.\n";     
        }
        }
        
     
      // reporting date within range
     
     if(!hiv_test_date.equals("")){
         System.out.println("HIV Test date exist");
        try{
     Date start = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);  
    Date end = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
    Date hiv = new SimpleDateFormat("dd MMM yyyy").parse(hiv_test_date); 
            System.out.println("HIV Test date is a date");
    if(hiv.before(start) || hiv.after(end)){
     cellHIVTestDate.setCellStyle(redstyle);
     errors+="HIV Test Date is not within reporting Range.\n";  
        System.out.println("Is not within range");     
    }
    else{
        System.out.println("hiv test date : "+hiv_test_date+" hvi : "+hiv+" start date : "+start+" end date "+end);
    }
    }
        catch(Exception e){
       }
        }
     
   
        
         HSSFCell cellerror = rowi.createCell(col_error);
         cellerror.setCellValue(errors);

         if(!errors.equals("")){
             j++;
//           copyRow(tb,rowi,errorSheet,j);
        if(unsupported_site>0){
//                    worksheet.removeRow(rowi);   
        }

         }
         else{
             y++;
//           copyRow(tb,rowi,cleanSheet,y);
           //worksheet.removeRow(rowi);
         }
//         if(errors.equals("")){
//           worksheet.removeRow(rowi);
//         }
System.out.println("at position : "+i);
            i++;
         
        }
        
            
//        worksheet = removeRowxls(worksheet);
        
        return tb;
    }
 
  public HSSFWorkbook ViralLoad(HSSFWorkbook vl,CellStyle redstyle,CellStyle styleborder,String start_date,String end_date) throws ParseException, SQLException{
    String where,errors,mfl_code,sex,age,date_tested,valid_result;
        HSSFSheet worksheet;
        int col_error = 27;
        int unsupported_site=0;
         HSSFSheet errorSheet = null;
        HSSFSheet cleanSheet = null;
        //MFL Code active=1,(ART=1 OR PMTCT=1) 9
        //Sex Sex !='' --------------------------10
        //Age and (AgeYrs!='' and AgeYrs>=0) ----11
        //Date Tested Date_Tested BETWEEN '"+startdate+"' AND '"+enddate+"' --22
        //Valid results Valid_Result='Y' --------24
        
        

        worksheet = vl.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();
        
        if(vl.getSheet("Clean Data")!=null){
        errorSheet = vl.getSheet("Clean Data");
        }
        else{
        errorSheet = vl.createSheet("Clean Data");    
        }
        if(vl.getSheet("Viral Load Errors")!=null){
        cleanSheet = vl.getSheet("Viral Load Errors");
        }
        else{
        cleanSheet = vl.createSheet("Viral Load Errors");    
        }
        
        int i=1,j=0,y=0;
        
        HSSFRow rowhead = worksheet.getRow(0);
        HSSFCell cellh = rowhead.createCell(col_error);
        cellh.setCellValue("Errors");
        cellh.setCellStyle(styleborder);
        
        
        HSSFCell firstCell = rowhead.getCell(0);
        HSSFCell lastCell = rowhead.getCell(col_error);
        
        worksheet.setAutoFilter(new CellRangeAddress( firstCell.getRowIndex(), lastCell.getRowIndex(), firstCell.getColumnIndex(), lastCell.getColumnIndex() ));
        
        
       copyRow(vl,rowhead,cleanSheet,0);
       copyRow(vl,rowhead,errorSheet,0);

        while(rowIterator.hasNext()){
          errors=where=sex=age=mfl_code=date_tested=valid_result=""; 
          unsupported_site = 0;
        HSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;
        }
       
        
                    
        //MFL Code
          HSSFCell cellMFLCode = rowi.getCell((short) 9);
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
                       mfl_code = cellMFLCode.getStringCellValue();
                       break;
                   default:
                       mfl_code = cellMFLCode.getStringCellValue();
                       break;
               }
            }
           
        //sex
         HSSFCell cellSex = rowi.getCell((short) 10);
            if(cellSex==null){
                break;
            }
            else{
               switch (cellSex.getCellType()) {
                   case 0:
                       //numeric
                       sex =""+(int)cellSex.getNumericCellValue();
                       break;
                   case 1:
                       sex =cellSex.getStringCellValue();
                       break;
                   default:
                       sex = cellSex.getStringCellValue();
                       break;
               }
            }
           
        //age
         HSSFCell cellAge = rowi.getCell((short) 11);
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
                       age = cellAge.getStringCellValue();
                       break;
               }
            }
        
            
        //Date Tested
         HSSFCell cellTestDate = rowi.getCell((short) 22);
            if(cellTestDate==null){
                break;
            }
            else{
               switch (cellTestDate.getCellType()) {
                   case 0:
                       //numeric
                       date_tested =""+(int)cellTestDate.getNumericCellValue();
                       break;
                   case 1:
                       date_tested =cellTestDate.getStringCellValue();
                       break;
                   default:
                       date_tested = cellTestDate.getStringCellValue();
                       break;
               }
            } 
           
          
            
        //ValidResults 
         HSSFCell cellValidResults = rowi.getCell((short) 24);
            if(cellValidResults==null){
                break;
            }
            else{
               switch (cellValidResults.getCellType()) {
                   case 0:
                       //numeric
                       valid_result =""+(int)cellValidResults.getNumericCellValue();
                       break;
                   case 1:
                       valid_result =cellValidResults.getStringCellValue();
                       break;
                   default:
                       valid_result = cellValidResults.getStringCellValue();
                       break;
               }
            } 
           
          
            if(mfl_code==null){mfl_code="";}            
            if(age==null){age="";}
            if(sex==null){sex="";}
            if(date_tested==null){date_tested="";}
            if(valid_result==null){valid_result="";}
            
          
        where = " CentreSanteID=? AND  (ART=1 OR PMTCT=1) ";
        String [] where_params = {"1##"+mfl_code+""};
        if(!issupported(where,where_params)){
          cellMFLCode.setCellStyle(redstyle);
          errors+=" This facility is not a HSDSA ART OR PMTCT supported site.\n"; 
          unsupported_site++;
        }
        
        
        if(sex.equals("")){
          cellSex.setCellStyle(redstyle);
          errors+=" Missing sex.\n";  
        }
        if(age.equals("")){
        cellAge.setCellStyle(redstyle);
          errors+="Missing Age \n";      
        }
        else{
            age = age.replace(" ", "").trim();
        if(Double.parseDouble(age)>100 || Double.parseDouble(age)<0){
          cellAge.setCellStyle(redstyle);
          errors+="Age is less than 0 or more than 100 yrs\n";  
        }
        }
        
         if(valid_result.equals("")){
          cellSex.setCellStyle(redstyle);
          errors+=" Missing valid results.\n";  
        }
         else{
        if(valid_result.equals("N")){
          cellValidResults.setCellStyle(redstyle);
          errors+="Results not valid.\n";  
        }   
         }
       
     if(!date_tested.equals("")){
         try{
     Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);  
     Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);     
     Date DateTested = new SimpleDateFormat("yyyy-MM-dd").parse(date_tested);     
         
        if(DateTested.before(startDate) || DateTested.after(endDate)){
         cellTestDate.setCellStyle(redstyle);
         errors+="Date tested is out of range.\n";     
        }
         }
         catch(Exception e){
         cellTestDate.setCellStyle(redstyle);
         errors+="Wrong Date tested format.\n";     
        }
        }
        
        
         HSSFCell cellerror = rowi.createCell(col_error);
         cellerror.setCellValue(errors);
         cellerror.setCellStyle(styleborder);

         if(!errors.equals("")){
             j++;
//           copyRow(tb,rowi,errorSheet,j);
        if(unsupported_site>0){
                   // worksheet.removeRow(rowi);   
        }

         }
         else{
             y++;
//           copyRow(tb,rowi,cleanSheet,y);
           //worksheet.removeRow(rowi);
         }
//         if(errors.equals("")){
//           worksheet.removeRow(rowi);
//         }
System.out.println(" viral load at position : "+i);
            i++;
         
        }
        
            
//        worksheet = removeRow(worksheet);
        
        return vl;
    }
 
  public HSSFWorkbook EIDTST(HSSFWorkbook eid,CellStyle redstyle,String start_date,String end_date) throws ParseException, SQLException{
    String errors,age,where,mfl_code,pcr_type,date_tested;
        HSSFSheet worksheet;
    // Column to check age<=12 col-no11
    // site not pmtct and not on our list of supported sites - mflcode col-no8
    // PCR Type is not initial PCR col-no16
    // Date Between use date tested-23
    
        worksheet = eid.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        
        HSSFRow rowhead = worksheet.getRow(0);
        HSSFCell cellh = rowhead.createCell(26);
        cellh.setCellValue("Errors");
        
        
        
        while(rowIterator.hasNext()){
          errors=age=mfl_code=where=pcr_type=date_tested="" ; 
        
        HSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;
        }
       
        //age
         HSSFCell cellAge = rowi.getCell((short) 11);
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
                       age = cellAge.getStringCellValue();
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
          HSSFCell cellMFLCode = rowi.getCell((short) 8);
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
                       mfl_code = cellMFLCode.getStringCellValue();
                       break;
               }
            }
        where = "CentreSanteId='"+mfl_code+"' AND  PMTCT=1 ";
        String [] where_params = {};
        if(!issupported(where,where_params)){
          cellMFLCode.setCellStyle(redstyle);
          errors+=" This facility is not a HSDSA PMTCT supported site \n";  
        }
            
        //pcr type
          HSSFCell cellPCR = rowi.getCell((short) 16);
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
                       pcr_type = cellPCR.getStringCellValue();
                       break;
               }
            }
                        
          if(pcr_type.contains("initial PCR")) {
           cellPCR.setCellStyle(redstyle);
            errors+="PCR type is not initial PCR \n";   
          } 

        //date tested
  HSSFCell cellDate = rowi.getCell((short) 23);
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
                       date_tested = cellDate.getStringCellValue();
                       break;
               }
            }
            
           if(!isbetween(date_tested,start_date,end_date)){
          cellDate.setCellStyle(redstyle);
            errors+=" Date tested is not within the accepted range i.e "+date_tested+"  not within "+start_date+" and "+end_date+" \n";  
        }
            
            
         HSSFCell cellerror = rowi.createCell(26);
         cellerror.setCellValue(errors);
           
            
            i++;
        }
        
            
        
        
        return eid;
    }
 
  public HSSFWorkbook EIDPOS(HSSFWorkbook eid,CellStyle redstyle,String start_date,String end_date) throws ParseException, SQLException{
    String errors,age,where,mfl_code,pcr_type,validation,date_tested;
        HSSFSheet worksheet;
    // Column to check age<=12 col-no8
    // site not pmtct and not on our list of supported sites - mflcode col-no5
    // PCR Type is not initial PCR col-no9
    // validation` IS NOT A col-no12
    // Date Between use date tested 11
    
        worksheet = eid.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        
        HSSFRow rowhead = worksheet.getRow(0);
        HSSFCell cellh = rowhead.createCell(17);
        cellh.setCellValue("Errors");
        
        
        
        while(rowIterator.hasNext()){
          errors=age=mfl_code=where=pcr_type=validation=date_tested="" ; 
        
        HSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;
        }
       
        //age
         HSSFCell cellAge = rowi.getCell((short) 8);
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
                       age = cellAge.getStringCellValue();
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
          HSSFCell cellMFLCode = rowi.getCell((short) 5);
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
                       mfl_code = cellMFLCode.getStringCellValue();
                       break;
               }
            }
           where = "CentreSanteId='"+mfl_code+"' AND  PMTCT=1 ";
        String [] where_params = {};
        if(!issupported(where,where_params)){
          cellMFLCode.setCellStyle(redstyle);
          errors+=" This facility is not a HSDSA PMTCT supported site \n";  
        }
            
        //pcr type
          HSSFCell cellPCR = rowi.getCell((short) 9);
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
                       pcr_type = cellPCR.getStringCellValue();
                       break;
               }
            }
                        
          if(pcr_type.contains("initial PCR")) {
           cellPCR.setCellStyle(redstyle);
            errors+="PCR type is not initial PCR \n";   
          } 
          
        //validation
  HSSFCell cellValidation = rowi.getCell((short) 12);
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
                       validation = cellValidation.getStringCellValue();
                       break;
               }
            }
            System.out.println("validation : "+validation);       
          if(validation !=null && validation.equals("A")) { 
          cellValidation.setCellStyle(redstyle);
            errors+=" Validation is A \n";    
          }      
            
        //date tested
  HSSFCell cellDate = rowi.getCell((short) 11);
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
                       date_tested = cellDate.getStringCellValue();
                       break;
               }
            }
            
           if(!isbetween(date_tested,start_date,end_date)){
          cellDate.setCellStyle(redstyle);
            errors+=" Date tested is not within the accepted range i.e "+date_tested+"  not within "+start_date+" and "+end_date+" \n";  
        }
            
            
         HSSFCell cellerror = rowi.createCell(17);
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
  
  public boolean issupported(String where,String[] where_params) throws SQLException{
   boolean is_mfl= true; 
   
   String checkifsupported="SELECT SubPartnerID FROM subpartnera WHERE active=1 AND ("+where+")";
   conn.pst = conn.conn.prepareStatement(checkifsupported);
   for(String s:where_params){
       int pos = Integer.parseInt(s.split("##")[0]);
       String value = s.split("##")[1];
       conn.pst.setString(pos, value);
   }
   conn.rs = conn.pst.executeQuery();
   if(!conn.rs.next()){
   is_mfl=false;    
   }
   
   return is_mfl;
  }     
 
  //remove a row
  public XSSFSheet removeRow(XSSFSheet sheet) {
for(int i = 0; i < sheet.getLastRowNum(); i++){
    System.out.println("remove blank at point : "+i);
    if(isEmpty(sheet.getRow(i))){
        sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
        i--;//Adjusts the sweep in accordance to a row removal
    }
}
    return sheet;
}
  
  public HSSFSheet removeRow(HSSFSheet sheet) {
for(int i = 0; i < sheet.getLastRowNum(); i++){
    System.out.println("remove blank at point : "+i);
    if(isEmpty(sheet.getRow(i))){
        sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
        i--;//Adjusts the sweep in accordance to a row removal
    }
}
    return sheet;
}
  
 boolean isEmpty(XSSFRow row){
boolean no_data=false;
//Code to determine if a row is empty
  if (row == null) {
        no_data = true;
    }
   else if (row.getLastCellNum() <= 0) {
         no_data = true;
    }

    return  no_data;
} 
 boolean isEmpty(HSSFRow row){
boolean no_data=false;
//Code to determine if a row is empty
  if (row == null) {
        no_data = true;
    }
   else if (row.getLastCellNum() <= 0) {
         no_data = true;
    }

    return  no_data;
} 
 
public XSSFSheet copyRow(XSSFWorkbook wb, XSSFRow rowold,XSSFSheet toSheet, int num) {
     // Loop through source columns to add to new row
     
     XSSFRow newRow = toSheet.createRow(num);
    for (int i = 0; i < rowold.getLastCellNum(); i++) {
        // Grab a copy of the old/new cell
        XSSFCell oldCell = rowold.getCell(i);
        XSSFCell newCell = newRow.createCell(i);

        // If the old cell is null jump to next cell
        if (oldCell == null) {
            newCell = null;
            continue;
        }

        // Copy style from old cell and apply to new cell
        CellStyle newCellStyle = wb.createCellStyle();
        newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
        
        newCell.setCellStyle(newCellStyle);

        // If there is a cell comment, copy
        if (oldCell.getCellComment() != null) {
            newCell.setCellComment(oldCell.getCellComment());
        }

        // If there is a cell hyperlink, copy
        if (oldCell.getHyperlink() != null) {
            newCell.setHyperlink(oldCell.getHyperlink());
        }

        // Set the cell data type
        newCell.setCellType(oldCell.getCellType());

        // Set the cell data value
        switch (oldCell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                newCell.setCellValue(oldCell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                newCell.setCellValue(oldCell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                newCell.setCellErrorValue(oldCell.getErrorCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                newCell.setCellFormula(oldCell.getCellFormula());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                newCell.setCellValue(oldCell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                newCell.setCellValue(oldCell.getRichStringCellValue());
                break;
        }
    
  }
    return toSheet;
}
public HSSFSheet copyRow(HSSFWorkbook wb, HSSFRow rowold,HSSFSheet toSheet, int num) {
     // Loop through source columns to add to new row
     
     HSSFRow newRow = toSheet.createRow(num);
    for (int i = 0; i < rowold.getLastCellNum(); i++) {
        // Grab a copy of the old/new cell
        HSSFCell oldCell = rowold.getCell(i);
        HSSFCell newCell = newRow.createCell(i);

        // If the old cell is null jump to next cell
        if (oldCell == null) {
            newCell = null;
            continue;
        }

        // Copy style from old cell and apply to new cell
        CellStyle newCellStyle = wb.createCellStyle();
        newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
        
        newCell.setCellStyle(newCellStyle);

        // If there is a cell comment, copy
        if (oldCell.getCellComment() != null) {
            newCell.setCellComment(oldCell.getCellComment());
        }

        // If there is a cell hyperlink, copy
        if (oldCell.getHyperlink() != null) {
            newCell.setHyperlink(oldCell.getHyperlink());
        }

        // Set the cell data type
        newCell.setCellType(oldCell.getCellType());

        // Set the cell data value
        switch (oldCell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                newCell.setCellValue(oldCell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                newCell.setCellValue(oldCell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                newCell.setCellErrorValue(oldCell.getErrorCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                newCell.setCellFormula(oldCell.getCellFormula());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                newCell.setCellValue(oldCell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                newCell.setCellValue(oldCell.getRichStringCellValue());
                break;
        }
    
  }
    return toSheet;
}

}
