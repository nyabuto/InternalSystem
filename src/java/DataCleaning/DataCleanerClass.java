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
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
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
    
    public XSSFWorkbook EIDTST(XSSFSheet worksheetpREV,XSSFWorkbook eid,CellStyle redstyle,CellStyle borderstyle,String start_date,String end_date) throws ParseException, SQLException{
   SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
      //upload previous errors
      //upload_eid_tst_prev_data(worksheetpREV); 
      //end
      
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    String errors;
    int has_initial=0;
        XSSFSheet worksheet;
String  SystemID,Sample_ID,Batch,Lab_Tested_In,County,Sub_County,Partner,Facilty,Facility_Code,Gender,DOB,Age_Months,PCR_Type,Enrollment_CCC_No,Date_Collected,Date_Received,Date_Tested,Date_Dispatched,Infant_Prophylaxis,Received_Status,Lab_Comment,Reason_for_Repeat,Spots,Feeding,Entry_Point,Result,PMTCT_Intervention,Mother_Result,Mother_Age,Mother_CCC_No,Mother_Last_VL;
    // Column to check age<=12 col-no11
    // site not pmtct and not on our list of supported sites - mflcode col-no8
    // PCR Type is not initial PCR col-no16
    // Date Between use date tested-23
    
// Possible errors
        // Flag:
//    1. Duplicates,
//    Missing: 
//    1. age 
//    2. gender 
//    3. mother HIV status
//    4. unknown HIV status
//     5. infant prophylaxis
//     6. PMTCT intervention (no data, none, other, SdNVP only, SdNVP/AZT/3TC & interrupted HAART)
//    7. inappropriate breastfeeding options by age 
//    8. entry point

       conn.st.executeUpdate("TRUNCATE eid_cleaning");
       

        worksheet = eid.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        
        XSSFRow rowhead = worksheet.getRow(0);
        XSSFCell cellErrors = rowhead.createCell(31);
        cellErrors.setCellValue("Possible Errors");
        cellErrors.setCellStyle(redstyle);
        
        XSSFCell CellSystemID,CellSample_ID,CellBatch,CellLab_Tested_In,CellCounty,CellSub_County,CellPartner,CellFacilty,CellFacility_Code,CellGender,CellDOB,CellAge_Months,CellPCR_Type,CellEnrollment_CCC_No,CellDate_Collected,CellDate_Received,CellDate_Tested,CellDate_Dispatched,CellInfant_Prophylaxis,CellReceived_Status,CellLab_Comment,CellReason_for_Repeat,CellSpots,CellFeeding,CellEntry_Point,CellResult,CellPMTCT_Intervention,CellMother_Result,CellMother_Age,CellMother_CCC_No,CellMother_Last_VL;

        
        while(rowIterator.hasNext()){
            errors="";
            SystemID=Sample_ID=Batch=Lab_Tested_In=County=Sub_County=Partner=Facilty=Facility_Code=Gender=DOB=Age_Months=PCR_Type=Enrollment_CCC_No=Date_Collected=Date_Received=Date_Tested=Date_Dispatched=Infant_Prophylaxis=Received_Status=Lab_Comment=Reason_for_Repeat=Spots=Feeding=Entry_Point=Result=PMTCT_Intervention=Mother_Result=Mother_Age=Mother_CCC_No=Mother_Last_VL="";
            has_initial=0;
            long ageInMonths=0;
          
        
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;
        }
        
              // rowi.setHeightInPoints(25);
               
        //SystemID
         CellSystemID = rowi.getCell((short) 0);
            if(CellSystemID==null){
              SystemID="";
            }
            else{
               switch (CellSystemID.getCellType()) {
                   case 0:
                       //numeric
                       SystemID =""+(double)CellSystemID.getNumericCellValue();
                       break;
                   case 1:
                       SystemID =CellSystemID.getStringCellValue();
                       break;
                   default:
                       SystemID = CellSystemID.getRawValue();
                       break;
               }
            }
            
            
        //Sample_ID
         CellSample_ID = rowi.getCell((short) 1);
            if(CellSample_ID==null){
              Sample_ID="";
            }
            else{
               switch (CellSample_ID.getCellType()) {
                   case 0:
                       //numeric
                       Sample_ID =""+(double)CellSample_ID.getNumericCellValue();
                       break;
                   case 1:
                       Sample_ID =CellSample_ID.getStringCellValue();
                       break;
                   default:
                       Sample_ID = CellSample_ID.getRawValue();
                       break;
               }
            }
            
            
            
        //Batch
         CellBatch = rowi.getCell((short) 2);
            if(CellBatch==null){
              Batch="";
            }
            else{
               switch (CellBatch.getCellType()) {
                   case 0:
                       //numeric
                       Batch =""+(double)CellBatch.getNumericCellValue();
                       break;
                   case 1:
                       Batch =CellBatch.getStringCellValue();
                       break;
                   default:
                       Batch = CellBatch.getRawValue();
                       break;
               }
            }
            
           
        //Lab tested
         CellLab_Tested_In = rowi.getCell((short) 3);
            if(CellLab_Tested_In==null){
              Lab_Tested_In="";
            }
            else{
               switch (CellLab_Tested_In.getCellType()) {
                   case 0:
                       //numeric
                       Lab_Tested_In =""+(double)CellLab_Tested_In.getNumericCellValue();
                       break;
                   case 1:
                       Lab_Tested_In =CellLab_Tested_In.getStringCellValue();
                       break;
                   default:
                       Lab_Tested_In = CellLab_Tested_In.getRawValue();
                       break;
               }
            }
            
            
            
        //County
         CellCounty = rowi.getCell((short) 4);
            if(CellCounty==null){
              County="";
            }
            else{
               switch (CellCounty.getCellType()) {
                   case 0:
                       //numeric
                       County =""+(double)CellCounty.getNumericCellValue();
                       break;
                   case 1:
                       County =CellCounty.getStringCellValue();
                       break;
                   default:
                       County = CellCounty.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellSub_County = rowi.getCell((short) 5);
            if(CellSub_County==null){
              Sub_County="";
            }
            else{
               switch (CellSub_County.getCellType()) {
                   case 0:
                       //numeric
                       Sub_County =""+(double)CellSub_County.getNumericCellValue();
                       break;
                   case 1:
                       Sub_County =CellSub_County.getStringCellValue();
                       break;
                   default:
                       Sub_County = CellSub_County.getRawValue();
                       break;
               }
            }
            
            
            
        //Partner
         CellPartner = rowi.getCell((short) 6);
            if(CellPartner==null){
              Partner="";
            }
            else{
               switch (CellPartner.getCellType()) {
                   case 0:
                       //numeric
                       Partner =""+(double)CellPartner.getNumericCellValue();
                       break;
                   case 1:
                       Partner =CellPartner.getStringCellValue();
                       break;
                   default:
                       Partner = CellPartner.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellFacilty = rowi.getCell((short) 7);
            if(CellFacilty==null){
              Facilty="";
            }
            else{
               switch (CellFacilty.getCellType()) {
                   case 0:
                       //numeric
                       Facilty =""+(double)CellFacilty.getNumericCellValue();
                       break;
                   case 1:
                       Facilty =CellFacilty.getStringCellValue();
                       break;
                   default:
                       Facilty = CellFacilty.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellFacility_Code = rowi.getCell((short) 8);
            if(CellFacility_Code==null){
              Facility_Code="";
            }
            else{
               switch (CellFacility_Code.getCellType()) {
                   case 0:
                       //numeric
                       Facility_Code =""+(double)CellFacility_Code.getNumericCellValue();
                       break;
                   case 1:
                       Facility_Code =CellFacility_Code.getStringCellValue();
                       break;
                   default:
                       Facility_Code = CellFacility_Code.getRawValue();
                       break;
               }
            }
            
            
            
        //CellGender
         CellGender = rowi.getCell((short) 9);
            if(CellGender==null){
              Gender="";
            }
            else{
               switch (CellGender.getCellType()) {
                   case 0:
                       //numeric
                       Gender =""+(double)CellGender.getNumericCellValue();
                       break;
                   case 1:
                       Gender =CellGender.getStringCellValue();
                       break;
                   default:
                       Gender = CellGender.getRawValue();
                       break;
               }
            }
            
            
        //Age in Months
         CellDOB = rowi.getCell((short) 10);
            if(CellDOB==null){
              DOB="";
            }
            else{
                
               if(CellDOB.getCellType()==1){
              DOB = CellDOB.getStringCellValue(); 
            }
            else{
                try{
              DOB = dateformat.format(CellDOB.getDateCellValue()); 
                }
                catch(Exception e){
                 DOB="";   
                }
            }
              
            }
            
            
            
        //Age in Months
         CellAge_Months = rowi.getCell((short) 11);
            if(CellAge_Months==null){
              Age_Months="";
            }
            else{
               switch (CellAge_Months.getCellType()) {
                   case 0:
                       //numeric
                       Age_Months =""+(double)CellAge_Months.getNumericCellValue();
                       break;
                   case 1:
                       Age_Months =CellAge_Months.getStringCellValue();
                       break;
                   default:
                       Age_Months = CellAge_Months.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellPCR_Type = rowi.getCell((short) 12);
            if(CellPCR_Type==null){
              PCR_Type="";
            }
            else{
               switch (CellPCR_Type.getCellType()) {
                   case 0:
                       //numeric
                       PCR_Type =""+(double)CellPCR_Type.getNumericCellValue();
                       break;
                   case 1:
                       PCR_Type =CellPCR_Type.getStringCellValue();
                       break;
                   default:
                       PCR_Type = CellPCR_Type.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellEnrollment_CCC_No = rowi.getCell((short) 13);
            if(CellEnrollment_CCC_No==null){
              Enrollment_CCC_No="";
            }
            else{
               switch (CellEnrollment_CCC_No.getCellType()) {
                   case 0:
                       //numeric
                       Enrollment_CCC_No =""+(double)CellEnrollment_CCC_No.getNumericCellValue();
                       break;
                   case 1:
                       Enrollment_CCC_No =CellEnrollment_CCC_No.getStringCellValue();
                       break;
                   default:
                       Enrollment_CCC_No = CellEnrollment_CCC_No.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellDate_Collected = rowi.getCell((short) 14);
            if(CellDate_Collected==null){
              Date_Collected="";
            }
            else{
                
               if(CellDate_Collected.getCellType()==1){
              Date_Collected = CellDate_Collected.getStringCellValue(); 
            }
            else{
                try{
              Date_Collected = dateformat.format(CellDate_Collected.getDateCellValue()); 
                }
                catch(Exception e){
                 Date_Collected="";   
                }
            }
             
            }
            
            
            
        //Age in Months
         CellDate_Received = rowi.getCell((short) 15);
            if(CellDate_Received==null){
              Date_Received="";
            }
            else{
                 
               if(CellDate_Received.getCellType()==1){
              Date_Received = CellDate_Received.getStringCellValue(); 
            }
            else{
                try{
              Date_Received = dateformat.format(CellDate_Received.getDateCellValue()); 
                }
                catch(Exception e){
                 Date_Received="";   
                }
            }
            
            }
            
            
            
        //Age in Months
         CellDate_Tested = rowi.getCell((short) 16);
            if(CellDate_Tested==null){
              Date_Tested="";
            }
            else{
                
               if(CellDate_Tested.getCellType()==1){
              Date_Tested = CellDate_Tested.getStringCellValue(); 
            }
            else{
                try{
              Date_Tested = dateformat.format(CellDate_Tested.getDateCellValue()); 
                }
                catch(Exception e){
                 Date_Tested="";   
                }
            }
            
            }
            
            
            
        //Age in Months
         CellDate_Dispatched = rowi.getCell((short) 17);
            if(CellDate_Dispatched==null){
              Date_Dispatched="";
            }
            else{
                 
               if(CellDate_Dispatched.getCellType()==1){
              Date_Dispatched = CellDate_Dispatched.getStringCellValue(); 
            }
            else{
                try{
              Date_Dispatched = dateformat.format(CellDate_Dispatched.getDateCellValue()); 
                }
                catch(Exception e){
                 Date_Dispatched="";   
                }
            }
              
            }
            
            
            
        //Age in Months
         CellInfant_Prophylaxis = rowi.getCell((short) 18);
            if(CellInfant_Prophylaxis==null){
              Infant_Prophylaxis="";
            }
            else{
               switch (CellInfant_Prophylaxis.getCellType()) {
                   case 0:
                       //numeric
                       Infant_Prophylaxis =""+(double)CellInfant_Prophylaxis.getNumericCellValue();
                       break;
                   case 1:
                       Infant_Prophylaxis =CellInfant_Prophylaxis.getStringCellValue();
                       break;
                   default:
                       Infant_Prophylaxis = CellInfant_Prophylaxis.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellReceived_Status = rowi.getCell((short) 19);
            if(CellReceived_Status==null){
              Received_Status="";
            }
            else{
               switch (CellReceived_Status.getCellType()) {
                   case 0:
                       //numeric
                       Received_Status =""+(double)CellReceived_Status.getNumericCellValue();
                       break;
                   case 1:
                       Received_Status =CellReceived_Status.getStringCellValue();
                       break;
                   default:
                       Received_Status = CellReceived_Status.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellLab_Comment = rowi.getCell((short) 20);
            if(CellLab_Comment==null){
              Lab_Comment="";
            }
            else{
               switch (CellLab_Comment.getCellType()) {
                   case 0:
                       //numeric
                       Lab_Comment =""+(double)CellLab_Comment.getNumericCellValue();
                       break;
                   case 1:
                       Lab_Comment =CellLab_Comment.getStringCellValue();
                       break;
                   default:
                       Lab_Comment = CellLab_Comment.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellReason_for_Repeat = rowi.getCell((short) 21);
            if(CellReason_for_Repeat==null){
              Reason_for_Repeat="";
            }
            else{
               switch (CellReason_for_Repeat.getCellType()) {
                   case 0:
                       //numeric
                       Reason_for_Repeat =""+(double)CellReason_for_Repeat.getNumericCellValue();
                       break;
                   case 1:
                       Reason_for_Repeat =CellReason_for_Repeat.getStringCellValue();
                       break;
                   default:
                       Reason_for_Repeat = CellReason_for_Repeat.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellSpots = rowi.getCell((short) 22);
            if(CellSpots==null){
              Spots="";
            }
            else{
               switch (CellSpots.getCellType()) {
                   case 0:
                       //numeric
                       Spots =""+(double)CellSpots.getNumericCellValue();
                       break;
                   case 1:
                       Spots =CellSpots.getStringCellValue();
                       break;
                   default:
                       Spots = CellSpots.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellFeeding = rowi.getCell((short) 23);
            if(CellFeeding==null){
              Feeding="";
            }
            else{
               switch (CellFeeding.getCellType()) {
                   case 0:
                       //numeric
                       Feeding =""+(double)CellFeeding.getNumericCellValue();
                       break;
                   case 1:
                       Feeding =CellFeeding.getStringCellValue();
                       break;
                   default:
                       Feeding = CellFeeding.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellEntry_Point = rowi.getCell((short) 24);
            if(CellEntry_Point==null){
              Entry_Point="";
            }
            else{
               switch (CellEntry_Point.getCellType()) {
                   case 0:
                       //numeric
                       Entry_Point =""+(double)CellEntry_Point.getNumericCellValue();
                       break;
                   case 1:
                       Entry_Point =CellEntry_Point.getStringCellValue();
                       break;
                   default:
                       Entry_Point = CellEntry_Point.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellResult = rowi.getCell((short) 25);
            if(CellResult==null){
              Result="";
            }
            else{
               switch (CellResult.getCellType()) {
                   case 0:
                       //numeric
                       Result =""+(double)CellResult.getNumericCellValue();
                       break;
                   case 1:
                       Result =CellResult.getStringCellValue();
                       break;
                   default:
                       Result = CellResult.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellPMTCT_Intervention = rowi.getCell((short) 26);
            if(CellPMTCT_Intervention==null){
              PMTCT_Intervention="";
            }
            else{
               switch (CellPMTCT_Intervention.getCellType()) {
                   case 0:
                       //numeric
                       PMTCT_Intervention =""+(double)CellPMTCT_Intervention.getNumericCellValue();
                       break;
                   case 1:
                       PMTCT_Intervention =CellPMTCT_Intervention.getStringCellValue();
                       break;
                   default:
                       PMTCT_Intervention = CellPMTCT_Intervention.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellMother_Result = rowi.getCell((short) 27);
            if(CellMother_Result==null){
              Mother_Result="";
            }
            else{
               switch (CellMother_Result.getCellType()) {
                   case 0:
                       //numeric
                       Mother_Result =""+(double)CellMother_Result.getNumericCellValue();
                       break;
                   case 1:
                       Mother_Result =CellMother_Result.getStringCellValue();
                       break;
                   default:
                       Mother_Result = CellMother_Result.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellMother_Age = rowi.getCell((short) 28);
            if(CellMother_Age==null){
              Mother_Age="";
            }
            else{
               switch (CellMother_Age.getCellType()) {
                   case 0:
                       //numeric
                       Mother_Age =""+(double)CellMother_Age.getNumericCellValue();
                       break;
                   case 1:
                       Mother_Age =CellMother_Age.getStringCellValue();
                       break;
                   default:
                       Mother_Age = CellMother_Age.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellMother_CCC_No = rowi.getCell((short) 29);
            if(CellMother_CCC_No==null){
              Mother_CCC_No="";
            }
            else{
               switch (CellMother_CCC_No.getCellType()) {
                   case 0:
                       //numeric
                       Mother_CCC_No =""+(double)CellMother_CCC_No.getNumericCellValue();
                       break;
                   case 1:
                       Mother_CCC_No =CellMother_CCC_No.getStringCellValue();
                       break;
                   default:
                       Mother_CCC_No = CellMother_CCC_No.getRawValue();
                       break;
               }
            }
            
        //Age in Months
         CellMother_Last_VL = rowi.getCell((short) 30);
            if(CellMother_Last_VL==null){
              Mother_Last_VL="";
            }
            else{
               switch (CellMother_Last_VL.getCellType()) {
                   case 0:
                       //numeric
                       Mother_Last_VL =""+(double)CellMother_Last_VL.getNumericCellValue();
                       break;
                   case 1:
                       Mother_Last_VL =CellMother_Last_VL.getStringCellValue();
                       break;
                   default:
                       Mother_Last_VL = CellMother_Last_VL.getRawValue();
                       break;
               }
            }
            
//            System.out.println("EID tested data is : "+SystemID+" - "+Sample_ID+" - "+Batch+" - "+Lab_Tested_In+" - "+County+" - "+Sub_County+" - "+Partner+" - "+Facilty+" - "+Facility_Code+" - "+Gender+" - "+DOB+" - "+Age_Months+" - "+PCR_Type+" - "+Enrollment_CCC_No+" - "+Date_Collected+" - "+Date_Received+" - "+Date_Tested+" - "+Date_Dispatched+" - "+Infant_Prophylaxis+" - "+Received_Status+" - "+Lab_Comment+" - "+Reason_for_Repeat+" - "+Spots+" - "+Feeding+" - "+Entry_Point+" - "+Result+" - "+PMTCT_Intervention+" - "+Mother_Result+" - "+Mother_Age+" - "+Mother_CCC_No+" - "+Mother_Last_VL);   
            
            
            
            //START OF LEVEL1 ERROR CHECKING##################################
            //check for age
            if(Age_Months==null){Age_Months="";}
            if(!Age_Months.equals("")){
             if(isNumeric(Age_Months)) {
             if((!Facility_Code.equals("15288") && (Double.parseDouble(Age_Months)<=0)) || Double.parseDouble(Age_Months)>=13){   
                 System.out.println("row :"+i+"Age is : "+Age_Months);
              errors+="Wrong age in months. Age should not be less than 0 or more than 13 months \n";   
             }
             }  
             else{
                 errors+="Age entered is non-numeric \n";
             }
                
            }
            else{
                errors+=" Missing age \n";
            }
           //end of checking age
           
           
        //check gender
        if(Gender==null){Gender="";}
          if(Gender.equals("")) {
              errors+=" Missing gender of HEI \n";
          } 
          else if(!(Gender.equalsIgnoreCase("Male") || Gender.equalsIgnoreCase("Female"))){
              errors+="Wrong gender entered. \n";
          }
        //end of checking for gender
        
        
        //check mother HIV Status
        if(Mother_Result==null){Mother_Result="";}
        if(Mother_Result.equals("")){
            errors+="Missing mother's HIV Status \n";
        }
        else{
            if(!(Mother_Result.equalsIgnoreCase("Positive") || Mother_Result.equalsIgnoreCase("Negative"))){
            errors+=" Wrong mother's HIV Status\n";    
            }
            else if(Mother_Result.equalsIgnoreCase("Negative")){
                errors+="Mother's HIV status should not be Negative\n";
            }
        }
         //end of checking for mother HIV status
         
//         HEI's HIV status
    if(Result==null){Result="";}
       if(Result.equals("")){
            errors+="Missing HIV Status \n";
        }
        else{
            if(!(Result.equalsIgnoreCase("Positive") || Result.equalsIgnoreCase("Negative"))){
            errors+=" Wrong HIV Status\n";    
            }
        }
         
//       end of HEI's HIV STATUS  
         //check for infant prophylaxis
         if(Infant_Prophylaxis==null){Infant_Prophylaxis="";}
        if(Infant_Prophylaxis.equalsIgnoreCase("No Data") || Infant_Prophylaxis.equalsIgnoreCase("None") || Infant_Prophylaxis.equalsIgnoreCase("Others") || Infant_Prophylaxis.equals("")){
            errors+="Missing infant Prophylaxis \n";
        }
//        else if(!((Infant_Prophylaxis.contains("AZT") && Infant_Prophylaxis.contains("NVP")) && !Infant_Prophylaxis.contains("SdNVP"))){
//         // Wrong infant prophylaxis  
//            errors+="Wrong infant prophylaxis\n";
//        }
//        end of infant prophylaxis


        //pmtct intervention
        if(PMTCT_Intervention==null){PMTCT_Intervention="";}
        if(PMTCT_Intervention.contains("No Data") || PMTCT_Intervention.contains("None") || PMTCT_Intervention.equals("")){
         errors+="Missing PMTCT Intervention\n";   
        }
        
        else if(PMTCT_Intervention.contains("Other") || PMTCT_Intervention.equalsIgnoreCase("SdNVP only") || 
                PMTCT_Intervention.equalsIgnoreCase("SdNVP/AZT/3TC") || PMTCT_Intervention.equalsIgnoreCase("HAART")){
            errors+=" Wrong PMTCT Intervention\n";
        }
        
//         if(PMTCT_Intervention.equalsIgnoreCase("No Data") || PMTCT_Intervention.contains("None") || PMTCT_Intervention.contains("Other") || 
//                PMTCT_Intervention.equalsIgnoreCase("NVP") || PMTCT_Intervention.contains("AZT+3TC+NVP") || PMTCT_Intervention.equalsIgnoreCase("interrupted HAART")){
//            errors+=" Wrong PMTCT Intervention";
//        }
        
    //Breastfeeding options per age
    if(Feeding==null){Feeding="";}
    if(Feeding.equals("")){
        errors+="Missing feeding option\n";
    }
    else{
        if(Feeding.equalsIgnoreCase("None") || Feeding.equalsIgnoreCase("No Data")|| Feeding.equalsIgnoreCase("Empty")){
            errors+="Wrong feeding option\n";
        }
//        else if(Feeding.equalsIgnoreCase("MBF")){
//           errors+="Wrong breastfeeding option";  
//           //mixed breastfeeding option
//           
//        }
        else if(isNumeric(Age_Months)) {
            if((Feeding.equalsIgnoreCase("EBF") || Feeding.equalsIgnoreCase("ERF") || Feeding.equalsIgnoreCase("MF")) && Double.parseDouble(Age_Months)>=7){
                errors+="Updated feeding option to BF\n";
                //change to BF 
                CellFeeding.setCellValue("BF");
            }
            else if((Feeding.equalsIgnoreCase("NBF") || Feeding.equalsIgnoreCase("BF")) && Double.parseDouble(Age_Months)<7){
              errors+="Wrong breastfeeding option for kids aged  less than 6 months\n";    
        }
            else{}
    }
     
    }
        //end of feeding
      
      if(Entry_Point.equals("")){
          errors+="Missing Entry point\n";
      } 
      else if(Entry_Point.equalsIgnoreCase("Other")){
        errors+="Wrong Entry point\n";        
            }
      
      
      //check for PCR Type
      
//      if(isNumeric(Age_Months)) {
//        if(Double.parseDouble(Age_Months)<6 && (PCR_Type.contains("2nd PCR") || PCR_Type.contains("3rd PCR"))){
//         errors+="The selected PCR does not match the age of child\n";   
//        }
//    }
      if(DOB==null){DOB="";}
      if(Date_Tested==null){Date_Tested="";}
      if(!DOB.equals("") && !Date_Tested.equals("") && !isNumeric(DOB) && !isNumeric(Date_Tested) && isNumeric(Age_Months)){

            Calendar cal_DOB = Calendar.getInstance();
            cal_DOB.setTime(sdf.parse(DOB));// all done
            
            Calendar cal_DateTested = Calendar.getInstance();
            cal_DateTested.setTime(sdf.parse(Date_Tested));// all done

          int yearsInBetween = cal_DateTested.get(Calendar.YEAR) - cal_DOB.get(Calendar.YEAR); 
          int monthsDiff = cal_DateTested.get(Calendar.MONTH) - cal_DOB.get(Calendar.MONTH); 
          ageInMonths = yearsInBetween*12 + monthsDiff; 
          long age = yearsInBetween; 
//          System.out.println("Months : " + ageInMonths); 
//          System.out.println("Years : " + age);
          
          
          if((ageInMonths - Double.parseDouble(Age_Months))>1 || (ageInMonths - Double.parseDouble(Age_Months))<-1){
           errors+="There is a problem in age calculation for this child is not correct, (DOB-Date_Tested) is not equal to Age_Months\n";
           }
       }
      
         //END OF ERROR CHECKING LEVEL1#####################################
         
        
         
         //START OF ERROR CHECKING LEVEL2####################################
         //check for duplicates within uploaded data
        String checker1="SELECT * from eid_cleaning WHERE Sample_ID=?";
//        String checker1="SELECT * from eid_cleaning WHERE Sample_ID=? AND Facility_Code=?";
        conn.pst = conn.conn.prepareStatement(checker1);
        conn.pst.setString(1, Sample_ID);
//        conn.pst.setString(2, Facility_Code);

        conn.rs = conn.pst.executeQuery();
         if(conn.rs.next()){
             has_initial++;
          //similar records already in the system
//             System.out.println("row num : "+(i+1)+" sc:"+Sample_ID+" row :"+(conn.rs.getInt("num")+1)+" sc:"+conn.rs.getString("Sample_ID"));
             

             //if gender is different for the same number flag out
             if(!Gender.equals(conn.rs.getString("Gender")) || !DOB.equals(conn.rs.getString("DOB"))){
             //flag out as wrong entry
             errors+="Duplicated Sample ID. There is a posibility of wrong entry. Date or Gender\n";
             XSSFRow anotherRow = worksheet.getRow(conn.rs.getInt("num"));
             XSSFCell anotherCell = anotherRow.getCell(31);
             String errrs = anotherCell.getStringCellValue();
             
             errrs+="Duplicated Sample ID. There is a posibility of wrong entry. Date or Gender\n";
             anotherCell.setCellValue(errrs);
             anotherCell.setCellStyle(redstyle);
             }
             
             
             else if(Gender.equals(conn.rs.getString("Gender")) && DOB.equals(conn.rs.getString("DOB")) && PCR_Type.equals(conn.rs.getString("PCR_Type"))){
             //flag out as wrong entry
             
                System.out.println("row num : "+(i+1)+" sc:"+Sample_ID+" row :"+(conn.rs.getInt("num")+1)+" sc:"+conn.rs.getString("Sample_ID"));
                
             errors+="Duplicated Record [Check Record Row Number "+(conn.rs.getInt("num")+1)+"]\n";
             XSSFRow anotherRow = worksheet.getRow(conn.rs.getInt("num"));
             XSSFCell anotherCell = anotherRow.getCell(31);
             String errrs = "";
                if(anotherCell!=null){
                    errrs = anotherCell.getStringCellValue();
                }
                else{
                   anotherCell = anotherRow.createCell(31); 
                    System.out.println("Another cell is null ::"+conn.rs.getInt("num")+":: row");
                }
             
             errrs+="Duplicated Record [Check Record Row Number "+(i+1)+"]\n";
             anotherCell.setCellValue(errrs);
             anotherCell.setCellStyle(redstyle);
             }
             
             //check for confirmatory
             
             if(Result.equalsIgnoreCase("Positive") && conn.rs.getString("Result").equalsIgnoreCase("Positive")){
                 //check the latest and convert int to confirmatory
                 if(isNumeric(Date_Tested.replace("-", "")) && isNumeric(conn.rs.getString("Date_Tested").replace("-", ""))){
                     
                     if(Integer.parseInt(Date_Tested.replace("-", ""))>Integer.parseInt(conn.rs.getString("Date_Tested").replace("-", ""))){
                      // convert current to confirmatory 
                      CellPCR_Type.setCellValue("Confirmatory PCR and Baseline VL");
                      errors+="The PCR Type has been updated to Confirmatory PCR and Baseline VL. Check Row "+(conn.rs.getInt("num")+1)+"\n";
                     }
                     else{
                         //convert previous to convermatory
                        XSSFRow anotherRow = worksheet.getRow(conn.rs.getInt("num"));
                        XSSFCell anotherCell = anotherRow.getCell(12);
                        anotherCell.setCellValue("Confirmatory PCR and Baseline VL");
                        
                        anotherCell = anotherRow.getCell(31);
                        String errrs = anotherCell.getStringCellValue()+"The PCR Type has been updated to Confirmatory PCR and Baseline VL. Check Row "+(i+1)+"";
                        anotherCell.setCellValue(errrs);
                        anotherCell.setCellStyle(redstyle);
                     }
                 }
             }
             
             //end of checking for confirmatory
             
             //first test is positive and second is negative
              
             if((Result.equalsIgnoreCase("Negative") && conn.rs.getString("Result").equalsIgnoreCase("Positive")) || (Result.equalsIgnoreCase("Positive") && conn.rs.getString("Result").equalsIgnoreCase("Negative"))){
                 //check the latest and if negative flag out
                 if(isNumeric(Date_Tested.replace("-", "")) && isNumeric(conn.rs.getString("Date_Tested").replace("-", ""))){
                     
                     if((Integer.parseInt(Date_Tested.replace("-", ""))>Integer.parseInt(conn.rs.getString("Date_Tested").replace("-", ""))) &&(Result.equalsIgnoreCase("Negative") && conn.rs.getString("Result").equalsIgnoreCase("Positive")) ){

                      errors+="First Test result is Positive and this test result is Negative. Check Row "+(conn.rs.getInt("num")+1)+"\n";
                     }
                     else if((Integer.parseInt(Date_Tested.replace("-", ""))<Integer.parseInt(conn.rs.getString("Date_Tested").replace("-", ""))) &&(Result.equalsIgnoreCase("Positive") && conn.rs.getString("Result").equalsIgnoreCase("Negative")) ){
                         //convert previous to convermatory
                        XSSFRow anotherRow = worksheet.getRow(conn.rs.getInt("num"));
                        
                       XSSFCell anotherCell = anotherRow.getCell(31);
                        String errrs = anotherCell.getStringCellValue()+"First test result is Positive and this test result is Negative. Check Row "+(i+1)+"";
                        anotherCell.setCellValue(errrs);
                        anotherCell.setCellStyle(redstyle);
                     }
                 }
             }
             
             //end of checking for this first positive then negative
             
             
             
             //CHECK FOR DATE OF BIRTH
         
         }
         
//            System.out.println(Gender+":"+conn.rs.getString("Gender")+"--"+ DOB+":"+conn.rs.getString("DOB")+":"+PCR_Type.equals("PCR_Type"));
         
         //END OF ERROR CHECKING LEVEL2######################################
      
         //LEVEL3 CHECKING 
//         Check data against previosly reported data

        
if(PCR_Type==null){PCR_Type="";}
      if(PCR_Type.contains("Initial PCR")){
//          if(Sample_ID.equals("14510/2017/425")){
//              System.out.println("Here sample code : "+Sample_ID);
//          }
         String checker="SELECT * FROM eid_tested_prev WHERE samplecode=?";
//         String checker="SELECT * FROM eid_tested_prev WHERE samplecode=? && Mflcode=? && datetested<'"+Date_Tested+"'";
            conn.pst = conn.conn.prepareStatement(checker);
            conn.pst.setString(1, Sample_ID);
//            conn.pst.setString(2, Facility_Code);

            conn.rs1 = conn.pst.executeQuery();
            if(conn.rs1.next()){
                int issue_grouped=0;
                if((isNumeric(Age_Months) && (!Age_Months.equals("")) || ageInMonths<=0)){
                    double entered_age = -1;
                    if((isNumeric(Age_Months) && !Age_Months.equals(""))){
                            entered_age=Double.parseDouble(Age_Months);
                    }
                    System.out.println("2. Age in months is --"+Age_Months+"--");
//                    errors+="The record previously exist.\n";
                   if((entered_age<6 && entered_age>=0) || ageInMonths<6) {   
                       errors+="Similar record exist in previous data. However, based on age, this is an initial PCR.\n";
                       issue_grouped++;
//                        CellPCR_Type.setCellStyle(borderstyle);
                   }
                   else if((entered_age>=6 && Double.parseDouble(Age_Months)<12) || (ageInMonths>=6 && ageInMonths<12)) {
                           CellPCR_Type.setCellValue("2nd PCR (6 months)"); 
                           errors+="Updated PCR Type to 2nd PCR (6 months) because the record previously exist.\n";
                           issue_grouped++;
                            CellPCR_Type.setCellStyle(redstyle);
                   }
                   else if(entered_age>=12 || ageInMonths>=12) {
                           CellPCR_Type.setCellValue("3rd PCR (12 months)");   
                       errors+="Updated PCR Type to 3rd PCR (12 months) because the record previously exist.\n";
                       issue_grouped++;
                        CellPCR_Type.setCellStyle(redstyle);
                   }
                   else{}
                   
                   if(issue_grouped==0){
                 CellPCR_Type.setCellValue("2nd PCR (6 months)");  
                 errors+="Updated PCR Type to 2nd PCR (6 months) because the record previously exist. However, age of the child could not be correctly determined. \n"; 
                  CellPCR_Type.setCellStyle(redstyle);
                   }

                }
                else{
                 CellPCR_Type.setCellValue("2nd PCR (6 months)");  
                 errors+="Updated PCR Type to 2nd PCR (6 months) because the record previously exist. Age is not numeric. \n";
                  CellPCR_Type.setCellStyle(redstyle);
                }
           
        }
      }
        
      if(PCR_Type.contains("2nd PCR") || PCR_Type.contains("3rd PCR")){
         String checker="SELECT * FROM eid_tested_prev WHERE samplecode=? && Mflcode=? && datetested<'"+Date_Tested+"'";
            conn.pst = conn.conn.prepareStatement(checker);
            conn.pst.setString(1, Sample_ID);
            conn.pst.setString(2, Facility_Code);

            conn.rs1 = conn.pst.executeQuery();
            if(conn.rs1.next()){
             // has initial in the previous data set   
            }
            else if(has_initial==0){ // has no initial records in the current dataset
                if(isNumeric(Age_Months) && !Age_Months.equals("")){
                    double entered_age = -1;
                    if((isNumeric(Age_Months) && !Age_Months.equals(""))){
                            entered_age=Double.parseDouble(Age_Months);
                    }
                   if(entered_age<5 && entered_age>=0 && ageInMonths<6) {
                       errors+="This should be an initial PCR. No Initial records found in dataset\n";
                   }
                   else{
                       // it is a repeat PCR
                   }
                }
                else{
                // Age is not numeric     
                }
        }
        else{
                
            }
      }
      
      
      //check for positives and convert to confirmatory
      
              String checker="SELECT * FROM eid_tested_prev WHERE samplecode=?";
            conn.pst = conn.conn.prepareStatement(checker);
            conn.pst.setString(1, Sample_ID);

            conn.rs1 = conn.pst.executeQuery();
            if(conn.rs1.next()){
              
                //check for confirmatory
             
             if(Result.equalsIgnoreCase("Positive") && conn.rs1.getString("testresult").equalsIgnoreCase("Positive")){
                 //check the latest and convert it to confirmatory
                 if(isNumeric(Date_Tested.replace("-", "")) && isNumeric(conn.rs1.getString("datetested").replace("-", ""))){
                     
                     if(Integer.parseInt(Date_Tested.replace("-", ""))>Integer.parseInt(conn.rs1.getString("datetested").replace("-", ""))){
                      // convert current to confirmatory 
                      CellPCR_Type.setCellValue("Confirmatory PCR and Baseline VL");
                      errors+="The PCR Type has been updated to Confirmatory PCR and Baseline VL. Data on the previous data set i.e 1 and 1/2 years ago has a positive record.\n";
                     }
                 }
             }
             
             //end of checking for confirmatory
             
             //first test is positive and second is negative
              
             if((Result.equalsIgnoreCase("Negative") && conn.rs1.getString("testresult").equalsIgnoreCase("Positive"))){
                 //check the latest and if negative flag out
                 if(isNumeric(Date_Tested.replace("-", "")) && isNumeric(conn.rs1.getString("datetested").replace("-", ""))){
                     
                     if((Integer.parseInt(Date_Tested.replace("-", ""))>Integer.parseInt(conn.rs1.getString("datetested").replace("-", "")))){

                      errors+="First Test result (from previous 1 and 1/2 years data) is Positive and this test result is Negative.\n";
                     }
                 }
             }
             //end of checking for this first positive then negative
            } 
      
      
        //END OF LEVEL 3 Checking
      //ADD DATA TO THE DATABASE FOR COMPARISON
         
         String query_to_add="REPLACE INTO eid_cleaning SET num=?,SystemID=?,Sample_ID=?,Batch=?,Lab_Tested_In=?,County=?,Sub_County=?,Partner=?,Facilty=?,Facility_Code=?,Gender=?,DOB=?,Age_Months=?,PCR_Type=?,Enrollment_CCC_No=?,Date_Collected=?,Date_Received=?,Date_Tested=?,Date_Dispatched=?,Infant_Prophylaxis=?,Received_Status=?,Lab_Comment=?,Reason_for_Repeat=?,Spots=?,Feeding=?,Entry_Point=?,Result=?,PMTCT_Intervention=?,Mother_Result=?,Mother_Age=?,Mother_CCC_No=?,Mother_Last_VL=?";
         conn.pst = conn.conn.prepareStatement(query_to_add);
         conn.pst.setInt(1, i);
         conn.pst.setString(2, SystemID);
         conn.pst.setString(3, Sample_ID);
         conn.pst.setString(4, Batch);
         conn.pst.setString(5, Lab_Tested_In);
         conn.pst.setString(6, County);
         conn.pst.setString(7, Sub_County);
         conn.pst.setString(8, Partner);
         conn.pst.setString(9, Facilty);
         conn.pst.setString(10, Facility_Code);
         conn.pst.setString(11, Gender);
         conn.pst.setString(12, DOB);
         conn.pst.setString(13, Age_Months);
         conn.pst.setString(14, PCR_Type);
         conn.pst.setString(15, Enrollment_CCC_No);
         conn.pst.setString(16, Date_Collected);
         conn.pst.setString(17, Date_Received);
         conn.pst.setString(18, Date_Tested);
         conn.pst.setString(19, Date_Dispatched);
         conn.pst.setString(20, Infant_Prophylaxis);
         conn.pst.setString(21, Received_Status);
         conn.pst.setString(22, Lab_Comment);
         conn.pst.setString(23, Reason_for_Repeat);
         conn.pst.setString(24, Spots);
         conn.pst.setString(25, Feeding);
         conn.pst.setString(26, Entry_Point);
         conn.pst.setString(27, Result);
         conn.pst.setString(28, PMTCT_Intervention);
         conn.pst.setString(29, Mother_Result);
         conn.pst.setString(30, Mother_Age);
         conn.pst.setString(31, Mother_CCC_No);
         conn.pst.setString(32, Mother_Last_VL);   
         conn.pst.executeUpdate();
      //Create Errors Column
      
      XSSFCell error_value = rowi.createCell(31);
       
      if(!errors.equals("")){ // seterrors and color background
         error_value.setCellValue(errors);
         error_value.setCellStyle(redstyle);  
      }
      else{
         error_value.setCellValue("");
         error_value.setCellStyle(borderstyle);   
      }
            
          i++;
        }
        
        
        //Truncate data from db
       worksheet.autoSizeColumn(31);     
      
       conn.st.executeUpdate("TRUNCATE eid_cleaning");
       //conn.st.executeUpdate("TRUNCATE eid_tested_prev"); // delete all data
       
                //close connections
        if(conn.rs!=null){conn.rs.close();}
        if(conn.st!=null){conn.st.close();}
        if(conn.rs1!=null){conn.rs1.close();}
        if(conn.pst!=null){conn.pst.close();}
        if(conn.conn!=null){conn.conn.close();}
        return eid;
    }
 
    public HSSFWorkbook EIDTST(HSSFSheet worksheet_prev, HSSFWorkbook eid,CellStyle redstyle,CellStyle borderstyle,String start_date,String end_date) throws ParseException, SQLException{
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
 
      
    
  public XSSFWorkbook TB(XSSFWorkbook tb,CellStyle redstyle,String start_date,String end_date) throws ParseException, SQLException{
    String where,errors,sex,age,hiv_status,hiv_test_date,art_status,art_start_date,treatment_date,area,health_facility,date_started_treatment,date_of_registration;
        XSSFSheet worksheet;
        int col_error = 62;
        int date_format=0,unsupported_site=0,missing_data=0;
//        XSSFSheet errorSheet = null;
//        XSSFSheet cleanSheet = null;
        //*Blank Sex (M)(12) --12
        //*Age (N)(13) above 100 ysrs and below 0--13 
        //*HIV Status (AT)(45) is Neg or POs and  HIV Test Date is Blank (AS)(44)
        //*ART Status (BA)(52) is Yes and ART start Date (BB)(53) is Blank
        //*ART Start date (BB)(53) is greater than HIV Test Date (AS)(44)
        //*ART Start Date (BB)(53), HIV Test Date(AS)(44) , Date of treatment started (AO)(40) Not in dd mmm YYYY (eg 04 Jan 2018) format.
        //Date of Registration within period
        //date of treatment within period

    
        worksheet = tb.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();
//        if(tb.getSheet("Clean Data")!=null){
//        errorSheet = tb.getSheet("Clean Data");
//        }
//        else{
//        errorSheet = tb.createSheet("Clean Data");    
//        }
//        if(tb.getSheet("TB Errors")!=null){
//        cleanSheet = tb.getSheet("TB Errors");
//        }
//        else{
//        cleanSheet = tb.createSheet("TB Errors");    
//        }
        
        int i=1,j=0,y=0;
        
        XSSFRow rowhead = worksheet.getRow(0);
        XSSFCell cellh = rowhead.createCell(col_error);
        cellh.setCellValue("Errors");
        
//       copyRow(tb,rowhead,cleanSheet,0);
//       copyRow(tb,rowhead,errorSheet,0);

        while(rowIterator.hasNext()){
          errors=where=sex=age=hiv_status=hiv_test_date=art_status=art_start_date=treatment_date=area=health_facility=date_started_treatment=date_of_registration=""; 
          date_format = unsupported_site = 0;
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;
        }
       
        //sex
         XSSFCell cellSex = rowi.getCell((short) 13);
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
         XSSFCell cellAge = rowi.getCell((short) 14);
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
         XSSFCell cellHIVStatus = rowi.getCell((short) 46);
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
         XSSFCell cellHIVTestDate = rowi.getCell((short) 45);
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
         XSSFCell cellARTStatus = rowi.getCell((short) 53);
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
         XSSFCell cellARTStartDate = rowi.getCell((short) 54);
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
         XSSFCell cellTreatmentDate = rowi.getCell((short) 41);
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
          XSSFCell cellHealthFacility = rowi.getCell((short) 8);
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
         
        //Date of Registration
          XSSFCell cellRegistrationDate = rowi.getCell((short) 1);
            if(cellRegistrationDate==null){
                break;
            }
            else{
               switch (cellRegistrationDate.getCellType()) {
                   case 0:
                       //numeric
                       date_of_registration =""+(int)cellRegistrationDate.getNumericCellValue();
                       break;
                   case 1:
                       date_of_registration =cellRegistrationDate.getStringCellValue();
                       break;
                   default:
                       date_of_registration = cellRegistrationDate.getRawValue();
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
            if(date_of_registration==null){date_of_registration="";}
            
           if(!health_facility.equals("")){
        where = " (CentreSanteID=? OR SubPartnerNom=?) AND  ART=1 ";
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
       //check for rage here 
          System.out.println("Treatment date exist");
        try{
     Date start = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);  
    Date end = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
    Date treat = new SimpleDateFormat("dd MMM yyyy").parse(treatment_date); 
    if(treat.before(start) || treat.after(end)){
     cellTreatmentDate.setCellStyle(redstyle);
     errors+="Date started treatment is not within reporting Range.\n";    
    }
    }
        catch(Exception e){
       } 
        }
        else{
        cellTreatmentDate.setCellStyle(redstyle);
         errors+="Missing treatment start date.\n";     
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
     
//     if(!date_of_registration.equals("") && !treatment_date.equals("")){
//     Date reg = new SimpleDateFormat("dd MMM yyyy").parse(date_of_registration);  
//     Date treat = new SimpleDateFormat("dd MMM yyyy").parse(treatment_date);     
//        
//        if(treat.before(reg)){
//         cellTreatmentDate.setCellStyle(redstyle);
//         cellRegistrationDate.setCellStyle(redstyle);
//         errors+="Date of treatment is less than date of registration.\n";     
//        }
//    }
    
     
     
     
     // reporting date within range
   
     
     if(!date_of_registration.equals("")){
        try{
     Date start = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);  
    Date end = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
    Date reg = new SimpleDateFormat("dd MMM yyyy").parse(date_of_registration); 
    if(reg.before(start) || reg.after(end)){
     cellRegistrationDate.setCellStyle(redstyle);
     errors+="Date of registration is not within reporting Range.\n";    
    }
    }
        catch(Exception e){
        cellRegistrationDate.setCellStyle(redstyle);
         errors+="Wrong date of registration format.\n";      
       }
        }
     else{
      cellRegistrationDate.setCellStyle(redstyle);
     errors+="Missing date of registration.\n";       
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
           }
           else{
               System.out.println("No facility at position :"+i);
               missing_data++;
           }
           
           if(missing_data>5){
               break;
           }
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
        String where,errors,sex,age,hiv_status,hiv_test_date,art_status,art_start_date,treatment_date,area,health_facility,date_of_registration;
        HSSFSheet worksheet;
        int col_error = 62;
        int date_format=0,unsupported_site=0,missing_data=0;
        HSSFSheet errorSheet = null;
        HSSFSheet cleanSheet = null;
        //*Blank Sex (M)(12) --12
        //*Age (N)(13) above 100 ysrs and below 0--13 
        //*HIV Status (AT)(45) is Neg or POs and  HIV Test Date is Blank (AS)(44)
        //*ART Status (BA)(52) is Yes and ART start Date (BB)(53) is Blank
        //*ART Start date (BB)(53) is greater than HIV Test Date (AS)(44)
        //*ART Start Date (BB)(53), HIV Test Date(AS)(44) , Date of treatment started (AO)(40) Not in dd mmm YYYY (eg 04 Jan 2018) format.
        //Date of Registration within period
        //date of treatment within period

    
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
          errors=where=sex=age=hiv_status=hiv_test_date=art_status=art_start_date=treatment_date=area=health_facility=date_of_registration=""; 
          date_format = unsupported_site = 0;
        HSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;
        }
       
        //sex
         HSSFCell cellSex = rowi.getCell((short) 13);
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
         HSSFCell cellAge = rowi.getCell((short) 14);
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
         HSSFCell cellHIVStatus = rowi.getCell((short) 46);
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
         HSSFCell cellHIVTestDate = rowi.getCell((short) 45);
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
         HSSFCell cellARTStatus = rowi.getCell((short) 53);
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
         HSSFCell cellARTStartDate = rowi.getCell((short) 54);
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
         HSSFCell cellTreatmentDate = rowi.getCell((short) 41);
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
            
            
        //MFLCode
          HSSFCell cellHealthFacility = rowi.getCell((short) 8);
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
         
        //Date of Registration
          HSSFCell cellRegistrationDate = rowi.getCell((short) 1);
            if(cellRegistrationDate==null){
                break;
            }
            else{
               switch (cellRegistrationDate.getCellType()) {
                   case 0:
                       //numeric
                       date_of_registration =""+(int)cellRegistrationDate.getNumericCellValue();
                       break;
                   case 1:
                       date_of_registration =cellRegistrationDate.getStringCellValue();
                       break;
                   default:
                       date_of_registration = cellRegistrationDate.getStringCellValue();
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
            if(date_of_registration==null){date_of_registration="";}
            
                   if(!health_facility.equals("")){
                       System.out.println("health facility : "+health_facility);
        where = " (CentreSanteID=? OR SubPartnerNom=?) AND  ART=1 ";
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
       //check for rage here 
          System.out.println("Treatment date exist");
        try{
     Date start = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);  
    Date end = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
    Date treat = new SimpleDateFormat("dd MMM yyyy").parse(treatment_date); 
    if(treat.before(start) || treat.after(end)){
     cellTreatmentDate.setCellStyle(redstyle);
     errors+="Date started treatment is not within reporting Range.\n";    
    }
    }
        catch(Exception e){
       } 
        }
        else{
        cellTreatmentDate.setCellStyle(redstyle);
         errors+="Missing treatment start date.\n";     
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
     
//     if(!date_of_registration.equals("") && !treatment_date.equals("")){
//     Date reg = new SimpleDateFormat("dd MMM yyyy").parse(date_of_registration);  
//     Date treat = new SimpleDateFormat("dd MMM yyyy").parse(treatment_date);     
//        
//        if(treat.before(reg)){
//         cellTreatmentDate.setCellStyle(redstyle);
//         cellRegistrationDate.setCellStyle(redstyle);
//         errors+="Date of treatment is less than date of registration.\n";     
//        }
//    }
    
     
     
     
     // reporting date within range
   
     
     if(!date_of_registration.equals("")){
        try{
     Date start = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);  
    Date end = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
    Date reg = new SimpleDateFormat("dd MMM yyyy").parse(date_of_registration); 
    if(reg.before(start) || reg.after(end)){
     cellRegistrationDate.setCellStyle(redstyle);
     errors+="Date of registration is not within reporting Range.\n";    
    }
    }
        catch(Exception e){
        cellRegistrationDate.setCellStyle(redstyle);
         errors+="Wrong date of registration format.\n";      
       }
        }
     else{
      cellRegistrationDate.setCellStyle(redstyle);
     errors+="Missing date of registration.\n";       
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
         
        }
        else{
               System.out.println("No facility at position :"+i);
               missing_data++;
           }
           
           if(missing_data>5){
               break;
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
       System.out.println("s is : "+s);
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


  public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
  
//  function to add temporary data
  
  public void upload_eid_tst_prev_data(XSSFSheet worksheet) throws SQLException{
      
     conn.st.executeUpdate("TRUNCATE eid_tested_prev"); // delete all data
      
          int missing=0,added=0,updated=0;
          String dbname="eid_tested_prev";   
    String min_date="",max_date="",date_tested="",agebracket="",upload_message="";
    String[] columns =  {"orderno","samplecode","batchno","testinglab","County","Sub_County","Partner","Facility","Mflcode","sex","dob","age_months","PCR_Type","enrollment_ccc","datecollected","datereceived","datetested","datedispatched","infantprophylaxis","receivedstatus","lab_comment","repeat_rejection_reason","spots","breastfeeding","entrypoint","testresult","pmtct_intervention","hivstatus_mum","mother_age","mother_cccno","mother_lastVL"};
    String query="",query_update="",value,checker_query,Age_Months="";
    String year,quarter,SubPartnerID,mfl_code,month,yearmonth,id,samplecode,system_id="";

        Iterator rowIterator = worksheet.iterator();
           int rowCount = worksheet.getLastRowNum();
        int i=1,y=0;
        while(rowIterator.hasNext()){
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){

         break;
        }
                            System.out.println("added is : "+added);
                            
            query = "REPLACE INTO "+dbname+" SET ";
            query_update = "UPDATE "+dbname+" SET ";
            checker_query="";
             int colmnscounter=0;
        SubPartnerID=mfl_code=date_tested=samplecode="";            
                            
        for (String label : columns){
          
           XSSFCell cell = rowi.getCell((short) colmnscounter);
            if(cell==null){
                break;
            }
            else{
               switch (cell.getCellType()) {
                   case 0:
                       //numeric
                       if(colmnscounter==11){
                  value =""+(double)cell.getNumericCellValue();               
                       }
                       else{
                       value =""+(int)cell.getNumericCellValue();     
                       }
                       break;
                   case 1:
                       value =cell.getStringCellValue();
                       break;
                   default:
                       value = cell.getRawValue();
                       break;
               }
        
            } 
               
               
               if(value==null){
          query+=label+"="+value+",";
          query_update+=label+"="+value+",";
//          checker_query+=label+"="+value+" AND ";
               }
               else{
                   
                   if(value.contains("'")){
                       value=value.replace("'", "");
                   }
               query+=label+"='"+value+"',";
               query_update+=label+"='"+value+"',";
               if(colmnscounter<=3){
               checker_query+=label+"='"+value+"' AND "; 
               }
               }
            if(colmnscounter==0){
                system_id = value;
            }
            if(colmnscounter==1){
                samplecode = value;
            }
            if(colmnscounter==8){
                mfl_code = value;
            }
            if(colmnscounter==11){
               Age_Months =  value; 
            }
            if(colmnscounter==16){
               date_tested =  value; 
            }
            
            colmnscounter++;
       }
        
   if(isNumeric(Age_Months)){agebracket=getageBracket(Double.parseDouble(Age_Months));}   
   id=system_id+"_"+samplecode+"_"+date_tested;    
   if(!date_tested.equalsIgnoreCase("")){
   String[] datesvalues = getperiod(date_tested,conn);
    year = datesvalues[0];
    quarter = datesvalues[1];
   }
   else{
    year = "";
    quarter = "";     
   }
//    quartername = datesvalues[2];
        SubPartnerID=getSubPartnerID(conn,mfl_code); 
    query_update +="year='"+year+"',quarter='"+quarter+"',SubPartnerID='"+SubPartnerID+"' WHERE id='"+id+"'";
    query +="year='"+year+"',quarter='"+quarter+"',SubPartnerID='"+SubPartnerID+"', id='"+id+"'";

            System.out.println("insert : "+query);
            System.out.println("update : "+query_update);

    //end of adding todashboards
              
        if(!SubPartnerID.equals("")){
            //REMOVE LAST ELEMENT 
         conn.st.executeUpdate(query);
            added++;
        
        }
        else{
          System.out.println("mfl : "+mfl_code+" Facility is missing in our master facility list.");   
        }
            System.out.println("Current date : "+date_tested+" Min date : "+min_date+" max date : "+max_date);
            i++;
        }   
  }
  
      public String[] getperiod(String date_tested, dbConn conn) throws SQLException{

    String QuarterName="",QuarterID="",yr="";

    String dateparameters[]=date_tested.split("-");
    if(dateparameters.length==3){

     if(!dateparameters[0].equals("")){//ensure tha date field is valid
       String month="";
       month=dateparameters[1];
       if(month.equalsIgnoreCase("01")||month.equalsIgnoreCase("02")||month.equalsIgnoreCase("03")){

       QuarterName="January-March"; 

           if(dateparameters[0].length()==4)
       {
       yr=dateparameters[0];
       }


       }
       else if(month.equalsIgnoreCase("04")||month.equalsIgnoreCase("05")||month.equalsIgnoreCase("06")){

           QuarterName="April-June"; 
           if(dateparameters[0].length()==4)
       {
       yr=dateparameters[0];
       }

       }

       else if(month.equalsIgnoreCase("07")||month.equalsIgnoreCase("08")||month.equalsIgnoreCase("09")){

           QuarterName="July-September";  
             if(dateparameters[0].length()==4)
       {
       yr=dateparameters[0];
       }

       }
        else if(month.equalsIgnoreCase("10")||month.equalsIgnoreCase("11")||month.equalsIgnoreCase("12")){

           QuarterName="October-December";  
            if(dateparameters[0].length()==4)
       {
           //assume
       yr=""+Integer.parseInt(dateparameters[0])+1;
       }
        }		   


String getQuarterID="SELECT id FROM quarter WHERE pmtct_fo_name like ?";
   conn.pst=conn.conn.prepareStatement(getQuarterID);
   conn.pst.setString(1, QuarterName);
   conn.rs=conn.pst.executeQuery();

if(conn.rs.next()==true){
    QuarterID=conn.rs.getString(1);
}
     }
    }
String response[]={yr,QuarterID,QuarterName};
   
return response;
 }
      public String getSubPartnerID(dbConn conn, String code) throws SQLException{
     String subpatID="";
     
    String gett="SELECT SubPartnerID FROM subpartnera WHERE CentreSanteId=? AND (ART=1 OR PMTCT=1)";
        System.out.println(gett);
    conn.pst=conn.conn.prepareStatement(gett);
    conn.pst.setString(1, code);
    conn.rs=conn.pst.executeQuery();
    if(conn.rs.next()){
        subpatID =conn.rs.getString(1);
    }
        System.out.println("subpartneris : "+subpatID+" code : "+code);
     return subpatID;
    }
      public String getageBracket(Double age){
    //<1	1-4	5-9  10-14	15-19	20+
        String finalbracket="";
if(age<1){
finalbracket="<1";
}
else if(age>=1&&age<=4){
finalbracket="1-4";
                        }
else if(age>=5&&age<=9){
finalbracket="5-9";
                        }
else if(age>=10&&age<=14){
finalbracket="10-14";
                        }
else if(age>=15&&age<=19){
finalbracket="15-19";
                        }
else if(age>=20){
finalbracket="20+";
                        }
else {
finalbracket="no age";
}
  return finalbracket;  
    }
}
