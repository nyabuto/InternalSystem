/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

 */

package LoadExcels;

import dashboards.PushDataSet2;
import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100) 

  public class Load_eid_tes_raw extends HttpServlet {
   
  String county_name,county_id, district_name,district_id,hf_name,hf_id;
  
  String full_path="";
  String fileName="";
  int checker_dist,checker_hf;
  File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";
  String nextpage="";
  String quarterName,facilityName,facilityID,id,missingFacility;
          String mflcode;
  int year,quarter,checker,missing,added,updated;
  String min_date="",max_date="",date_tested="";
  String  SystemID,Sample_ID,Batch,Lab_Tested_In,County,Sub_County,Partner,Facilty,Facility_Code,Gender,DOB,Age_Months,PCR_Type,Enrollment_CCC_No,Date_Collected,Date_Received,Date_Tested,Date_Dispatched,Infant_Prophylaxis,Received_Status,Lab_Comment,Reason_for_Repeat,Spots,Feeding,Entry_Point,Result,PMTCT_Intervention,Mother_Result,Mother_Age,Mother_CCC_No,Mother_Last_VL;
  String agebracket="";
    String upload_message="";
      String value_vl="";
  @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     added=missing=0;
    //System ID(0) 
    //Sample ID (1) 
    //Batch Lab (2)
    //Tested In (3) 
    //County (4) 
    //Sub-County (5) 
    //Partner (6) 
    //Facilty (7) 
    //Facility Code (8) 
    //Gender (9) 
    //DOB (10) 
    //Age (Months) (11) 
    //PCR Type (12) 
    //Enrollment CCC No (13) 
    //Date Collected (14) 
    //Date Received (15) 
    //Date Tested (16) 
    //Date Dispatched (17) 
    //Infant Prophylaxis (18) 
    //Received Status (19) 
    //Lab Comment (20) 
    //Reason for Repeat (21) 
    //Spots (22) 
    //Feeding  (23)
    //Entry Point (24) 
    //Result (25) 
    //PMTCT Intervention (26) 
    //Mother Result (27) 
    //Mother Age (28) 
    //Mother CCC No (29) 
    //Mother Last VL (30)

     
     
  id="";
    
     String dbname="eid_raw_tested";
  
     
         try {
      session=request.getSession();
      dbConn conn = new dbConn();
   added=missing=0;
   //---------------------------------------------------------------------
  
      String applicationPath = request.getServletContext().getRealPath("");
         String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
         session=request.getSession();
          File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
        
        for (Part part : request.getParts()) {
           fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            System.out.println("file name is  :  "+fileName);
        }
        if(!fileName.endsWith(".xlsx")){
         nextpage="sync_eid.jsp";
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");   
        }
        else{
            added=missing=0;
        XSSFCell CellSystemID,CellSample_ID,CellBatch,CellLab_Tested_In,CellCounty,CellSub_County,CellPartner,CellFacilty,CellFacility_Code,CellGender,CellDOB,CellAge_Months,CellPCR_Type,CellEnrollment_CCC_No,CellDate_Collected,CellDate_Received,CellDate_Tested,CellDate_Dispatched,CellInfant_Prophylaxis,CellReceived_Status,CellLab_Comment,CellReason_for_Repeat,CellSpots,CellFeeding,CellEntry_Point,CellResult,CellPMTCT_Intervention,CellMother_Result,CellMother_Age,CellMother_CCC_No,CellMother_Last_VL;           
        full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;

 System.out.println("the saved file directory is  :  "+full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
 
                        FileInputStream fileInputStream = new FileInputStream(full_path);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet worksheet = workbook.getSheetAt(0);
			Iterator rowIterator = worksheet.iterator();
                           int rowCount = worksheet.getLastRowNum();
                        int i=1,y=0;
			while(rowIterator.hasNext()){
agebracket=SystemID=Sample_ID=Batch=Lab_Tested_In=County=Sub_County=Partner=Facilty=Facility_Code=Gender=DOB=Age_Months=PCR_Type=Enrollment_CCC_No=Date_Collected=Date_Received=Date_Tested=Date_Dispatched=Infant_Prophylaxis=Received_Status=Lab_Comment=Reason_for_Repeat=Spots=Feeding=Entry_Point=Result=PMTCT_Intervention=Mother_Result=Mother_Age=Mother_CCC_No=Mother_Last_VL="";
			XSSFRow rowi = worksheet.getRow(i);
                        if( rowi==null){
                                
                         break;
                        }
                            System.out.println("added is : "+added);
               
        //SystemID
         CellSystemID = rowi.getCell((short) 0);
            if(CellSystemID==null){
              SystemID="";
            }
            else{
               switch (CellSystemID.getCellType()) {
                   case 0:
                       //numeric
                       SystemID =""+(int)CellSystemID.getNumericCellValue();
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
                       Batch =""+(int)CellBatch.getNumericCellValue();
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
                       Facility_Code =""+(int)CellFacility_Code.getNumericCellValue();
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
               switch (CellDOB.getCellType()) {
                   case 0:
                       //numeric
                       DOB =""+(double)CellDOB.getNumericCellValue();
                       break;
                   case 1:
                       DOB =CellDOB.getStringCellValue();
                       break;
                   default:
                       DOB = CellDOB.getRawValue();
                       break;
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
               switch (CellDate_Collected.getCellType()) {
                   case 0:
                       //numeric
                       Date_Collected =""+(double)CellDate_Collected.getNumericCellValue();
                       break;
                   case 1:
                       Date_Collected =CellDate_Collected.getStringCellValue();
                       break;
                   default:
                       Date_Collected = CellDate_Collected.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellDate_Received = rowi.getCell((short) 15);
            if(CellDate_Received==null){
              Date_Received="";
            }
            else{
               switch (CellDate_Received.getCellType()) {
                   case 0:
                       //numeric
                       Date_Received =""+(double)CellDate_Received.getNumericCellValue();
                       break;
                   case 1:
                       Date_Received =CellDate_Received.getStringCellValue();
                       break;
                   default:
                       Date_Received = CellDate_Received.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellDate_Tested = rowi.getCell((short) 16);
            if(CellDate_Tested==null){
              Date_Tested="";
            }
            else{
               switch (CellDate_Tested.getCellType()) {
                   case 0:
                       //numeric
                       Date_Tested =""+(double)CellDate_Tested.getNumericCellValue();
                       break;
                   case 1:
                       Date_Tested =CellDate_Tested.getStringCellValue();
                       break;
                   default:
                       Date_Tested = CellDate_Tested.getRawValue();
                       break;
               }
            }
            
            
            
        //Age in Months
         CellDate_Dispatched = rowi.getCell((short) 17);
            if(CellDate_Dispatched==null){
              Date_Dispatched="";
            }
            else{
               switch (CellDate_Dispatched.getCellType()) {
                   case 0:
                       //numeric
                       Date_Dispatched =""+(double)CellDate_Dispatched.getNumericCellValue();
                       break;
                   case 1:
                       Date_Dispatched =CellDate_Dispatched.getStringCellValue();
                       break;
                   default:
                       Date_Dispatched = CellDate_Dispatched.getRawValue();
                       break;
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
                       Spots =""+(int)CellSpots.getNumericCellValue();
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
            
      if(isNumeric(Age_Months)){agebracket=getageBracket(Double.parseDouble(Age_Months));}               
                            
                            
                     //_________________________quarter and year_______
                     //split the date, year and month
                          //raw date is of form yyyy-mm-dd eg 08 Jul 2015
                         
                          String dateparameters[]=Date_Tested.split("-");
                        if(dateparameters.length==3){
                            
                         if(!dateparameters[0].equals("")){//ensure tha date field is valid
                           String month="";
                           month=dateparameters[1];
                           if(month.equalsIgnoreCase("01")||month.equalsIgnoreCase("02")||month.equalsIgnoreCase("03")){
                           
                           quarterName="January-March"; 
                           
                               if(dateparameters[0].length()==4)
                           {
                           year=Integer.parseInt(dateparameters[0]);
                           }
                           
                          
                           }
                           else if(month.equalsIgnoreCase("04")||month.equalsIgnoreCase("05")||month.equalsIgnoreCase("06")){
                          
                               quarterName="April-June"; 
                               if(dateparameters[0].length()==4)
                           {
                           year=Integer.parseInt(dateparameters[0]);
                           }
                               
                           }
                           
                           else if(month.equalsIgnoreCase("07")||month.equalsIgnoreCase("08")||month.equalsIgnoreCase("09")){
                           
                               quarterName="July-September";  
                                 if(dateparameters[0].length()==4)
                           {
                           year=Integer.parseInt(dateparameters[0]);
                           }
                               
                           }
                            else if(month.equalsIgnoreCase("10")||month.equalsIgnoreCase("11")||month.equalsIgnoreCase("12")){
                           
                               quarterName="October-December";  
                                if(dateparameters[0].length()==4)
                           {
                               //assume
                           year=Integer.parseInt(dateparameters[0])+1;
                           }
                               
                            }
                           
                            }
                       
                        }
                        else {
                        
                             
                              }       
                            
                            
                            
                            
                         
                       
                     
                       
                      facilityID=get_facilityID(Facility_Code,conn);
                      checker=0;  
                    if(facilityID.length()>0) {
//                        DISTRICT FOUND ADD THE HF TO THE SYSTEM.........................
                        
                        String getQuarterID="SELECT id FROM quarter WHERE pmtct_fo_name like ?";
                       conn.pst=conn.conn.prepareStatement(getQuarterID);
                       conn.pst.setString(1, quarterName);
                       conn.rs=conn.pst.executeQuery();
                       
                       if(conn.rs.next()==true){
                        quarter=conn.rs.getInt(1);
                        }
                       
                       checker=0;
                       
                       id=SystemID+"_"+Sample_ID+"_"+Date_Tested; 

      String query_to_add="REPLACE INTO "+dbname+" SET id=?,orderno=?,samplecode=?,batchno=?,testinglab=?,SubPartnerID=?,Mflcode=?,sex=?,dob=?,age_months=?,"
              + "PCR_Type=?,infantprophylaxis=?,receivedstatus=?,lab_comment=?,enrollment_ccc=?,datecollected=?,datereceived=?,datetested=?,datedispatched=?,"
              + "repeat_rejection_reason=?,spots=?,mother_age=?,mother_cccno=?,mother_lastVL=?,breastfeeding=?,entrypoint=?,testresult=?,pmtct_intervention=?,"
              + "hivstatus_mum=?,year=?,quarter=?,agebracket=?";
         conn.pst = conn.conn.prepareStatement(query_to_add);
         conn.pst.setString(1, id);
         conn.pst.setString(2, SystemID);
         conn.pst.setString(3, Sample_ID);
         conn.pst.setString(4, Batch);
         conn.pst.setString(5, Lab_Tested_In);
         conn.pst.setString(6, facilityID);
         conn.pst.setString(7, Facility_Code);
         conn.pst.setString(8, Gender);
         conn.pst.setString(9, DOB);
         conn.pst.setString(10, Age_Months);
         conn.pst.setString(11, PCR_Type);
         conn.pst.setString(12, Infant_Prophylaxis);
         conn.pst.setString(13, Received_Status);
         conn.pst.setString(14, Lab_Comment);
         conn.pst.setString(15, Enrollment_CCC_No);
         conn.pst.setString(16, Date_Collected);
         conn.pst.setString(17, Date_Received);
         conn.pst.setString(18, Date_Tested);
         conn.pst.setString(19, Date_Dispatched);
         conn.pst.setString(20, Reason_for_Repeat);
         conn.pst.setString(21, Spots);
         conn.pst.setString(22, Mother_Age);
         conn.pst.setString(23, Mother_CCC_No);
         conn.pst.setString(24, Mother_Last_VL);
         conn.pst.setString(25, Feeding);
         conn.pst.setString(26, Entry_Point);
         conn.pst.setString(27, Result);
         conn.pst.setString(28, PMTCT_Intervention);
         conn.pst.setString(29, Mother_Result);
         conn.pst.setInt(30, year);
         conn.pst.setInt(31, quarter);
         conn.pst.setString(32, agebracket);  
         
         conn.pst.executeUpdate();
         added++;
  }
    else{
        missing++; 
//                        missing facilities
      missingFacility+="facility name : "+facilityName+" mfl code : "+mflcode+" excel row num : "+i+"<br>"; 
         System.out.println(facilityName+ "_missing");
    }
                    
        session.setAttribute("eid_tested", "<b>"+i+"/"+rowCount+"</b>");
        session.setAttribute("eid_tested_count", (i*100)/rowCount);
        
                    i++;
                    
                                            
        compare_date(Date_Tested);
            System.out.println("Current date : "+Date_Tested+" Min date : "+min_date+" max date : "+max_date);  
                        }

                        
                        //add dashboard data
         PushDataSet2 ds2 = new PushDataSet2();
           
            Map m1 = new HashMap(); 
            m1.put("startdate", min_date);
            m1.put("enddate", max_date);
            
            ds2.pmtct_eid(m1);//eid tested and pos

    //end of adding todashboards
                                       
        }
        
         if(conn.rs!=null){conn.rs.close();}
         if(conn.st!=null){conn.st.close();}
         if(conn.pst!=null){conn.pst.close();}
         
         }
         catch (SQLException ex) {
         Logger.getLogger(Load_tb_raw.class.getName()).log(Level.SEVERE, null, ex);
     }
    String sessionText="<br/><b> "+added+ "</b> records  added/updated facilities<br> <br> <b>"+missing+"</b> sites not in IMIS Facilities List ";    
    session.setAttribute("upload_success", sessionText);
    nextpage = "load_eid_tested.jsp";
    added=0;
    
    session.removeAttribute("eid_tested");
    session.removeAttribute("eid_tested_count");
        
    response.sendRedirect(nextpage);  
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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//     try {
//         processRequest(request, response);
//     } catch (SQLException ex) {
//         Logger.getLogger(loadPMTCT_FO.class.getName()).log(Level.SEVERE, null, ex);
//     }
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//     try {
//         processRequest(request, response);
//     } catch (SQLException ex) {
//         Logger.getLogger(loadPMTCT_FO.class.getName()).log(Level.SEVERE, null, ex);
//     }
//    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
      
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return contentDisp;
    }
    
    public String getageBracket(double age)
    {
    //<1	1-4	5-9  10-14	15-19	20+
        String finalbracket="";
if(age<1){
finalbracket="<1";
}
else if(age>=1&&age<5){
finalbracket="1-4";
                        }
else if(age>=5&&age<10){
finalbracket="5-9";
                        }
else if(age>=10&&age<15){
finalbracket="10-14";
                        }
else if(age>=15&&age<20){
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
  
       public void compare_date(String date){
            String c_date_key="",in_date_key="";
            if(min_date.equals("")){
                min_date=date;
            }
            else
            {
             c_date_key = min_date.replace("-", "");
             in_date_key = date.replace("-", "");
             
             if(Integer.parseInt(c_date_key)>=Integer.parseInt(in_date_key)){
              min_date=date;   
             }
             
            }
            
            if(max_date.equals("")){
                max_date=date;
            }
            else
            {
             c_date_key = max_date.replace("-", "");
             in_date_key = date.replace("-", "");
             
             if(Integer.parseInt(c_date_key)<=Integer.parseInt(in_date_key)){
              max_date=date;   
             }
             
            }
        }   
    
       public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}   
  
       public String get_facilityID(String mflcode,dbConn conn) throws SQLException{
      String subpartnerID="";
      String getID="SELECT SubPartnerID FROM subpartnera WHERE CentreSanteId=? ";
      conn.pst = conn.conn.prepareStatement(getID);
      conn.pst.setString(1, mflcode);
      conn.rs = conn.pst.executeQuery();
      if(conn.rs.next()){
          subpartnerID = conn.rs.getString(1);
          
      }
      
      return subpartnerID;
  }
  
}
