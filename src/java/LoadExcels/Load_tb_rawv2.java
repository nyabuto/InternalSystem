/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LoadExcels;

import General.IdGenerator;
import dashboards.pullTb;
import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100) 

/**
 *
 * @author Emmanuel Kaunda
 */


  public class Load_tb_rawv2 extends HttpServlet {
   
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
          int mflcode;
  int year,quarter,checker,missing,added,updated;

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

     String startdate="";
     String enddate="";
  
      startdate=request.getParameter("startdate");
           enddate=request.getParameter("enddate");
     String serialnumber="";
     String distregno="";   
     String agebracket="";
     String registrationdate="";
     String registrationdatecopy="";
     int age=0;
     String agestring="";
     String sex="F";
     String treatmentstartdate="";
     String hivtestdate="";
     String hivstatus="";
     String artstatus="";
     String artdate="";
     String treatmentoutcome="";
     String treatmentoutcomedate="";
     int ageinteger=0;
     String supporttype="";
     
     
     String datacategory="";
     String dbname="tibu_tb_raw";
     
String tbtype="";
String patienttype="";
String smear0="";
String smear2_3="";
String smear5="";
String smear6_8="";
String genexpert="";

//     if(request.getParameter("datacategory")!=null){
//     datacategory=request.getParameter("datacategory");
//         System.out.println(" data category fetched"); 
//     }
     
     if(datacategory.equals("completeoutcome"))
     {
     //this is the data for monthly uploads
     dbname="tibu_tb_raw_outcome";
     
     }
     
         try {
      session=request.getSession();
      dbConn conn = new dbConn();
   nextpage="sync_tb.jsp";
   
   
   
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
            if(!getFileName(part).equals("")){
           fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            }
        }
        
        
          
        
        
        if(!fileName.endsWith(".xls")){
         nextpage="upload_tb_raw_data.jsp";
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose the correct File.</font>");   
        }
        else{
            
        
 full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
 
 System.out.println("the saved file directory is  :  "+full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
 
  FileInputStream fileInputStream = new FileInputStream(full_path);
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheetAt(0);
			Iterator rowIterator = worksheet.iterator();
                        
                        int i=1,y=0;
			while(rowIterator.hasNext()){
//                            System.out.println(" in while");
			HSSFRow rowi = worksheet.getRow(i);
                        if( rowi==null){
                                nextpage="upload_tb_raw_data.jsp";
                         break;
                                        }
//([0])Serial Number	([1])Date of Registration	([2])District Registration Number	(3)Province	(4)County	(5)District	(6)Zone	([7])Health Facility	([8])Year	([9])Quarter	(10)Sector	(11)Patient Name	([12])Sex M/F	([13])Age on registration	(14)Weight (Kgs)	(15)Height (Mtrs)	(16)BMI	(17)MUAC	(18)Physical address (Neighbor,Primary School) Cell Phone	(19)DOT by	(20)Type of TB P/EP	(21)EPTB Sub Type	(22)EPTB Others	(23)Type of patient	(24)CD4 First Date	(25)CD4 Last Date	(26)Culture S	(27)Culture R	(28)Culture E	(29)Culture H	(30)X-ray Y/N	(31)Sputum Smear Examination (32)0th Month Result	(33)0th Month Serial No and Quantification	(34)Sputum Smear Examination 2by3 Month Result	(35)2by3 Month Serial No and Quantification	(36)Sputum Smear Examination 5th Month Result	(37)5th Month Serial No and Quantification	(38)Sputum Smear Examination 6by8 Month Result	(39)6by8 Month Serial No and Quantification	(40)Regimen	([41])Date of treatment started	(42)Gen expert	(43)Lipa Hain Rifampicin	(44)Lipa Hain Isoniazid	([45])HIV Test Date	([46])HIV Status	(47)Partner HIV Test Date	(48)Partner HIV Status	(49)Referred BY: VCT/HCC/STI/HBC/PS/ANC/SR/CI	(50)Referred TO VCT/HCC/STI/HBC/PS/ANC	(51)Cotrimoxazole Preventive Therapy Y/N	(52)Cotrimoxazole Preventive Therapy (Date Started)	([53])ART Y/N	([54])ART (Date Started)	(55)Nutrition Support	([56])Treatment Outcome	([57])Treatment Outcome Date	([58])Remarks
//<tr><td>(0)Serial Number</td><td>(1)Date of Registration</td><td>(2)District Registration Number</td><td>(3)Province</td><td>(4) County</td><td>(5)District</td><td>(6)Zone</td><td>(7)Health Facility</td><td>(8)Year</td><td>(9)Quarter</td><td>(10)Sector</td><td>(11)Patient Name</td><td>(12)Sex M/F</td><td>(13)Age on registration</td><td>(14)Weight (Kgs)</td><td>(15)Height (Mtrs)</td><td>(16)BMI</td><td>(17)MUAC</td><td>(18)Physical address (Neighbor,Primary School) Cell Phone</td><td>(19)DOT by</td><td>(20)Type of TB P/EP</td><td>(21)EPTB Sub Type</td><td>(22)	EPTB Others</td><td>(23)Type of patient	</td><td>(24)CD4 First Date</td><td>(25)CD4 Last Date</td><td>(26)Culture S</td><td>(27)Culture R</td><td>(28)Culture E	</td><td>(29)Culture H</td><td>(30)X-ray Y/N</td><td>(31)Sputum Smear Examination</td><td>(32) 0th Month Result</td><td>(33)0th Month Serial No and Quantification</td><td>(34)Sputum Smear Examination 2by3 Month Result</td><td>(35)2by3 Month Serial No and Quantification</td><td>(36)Sputum Smear Examination 5th Month Result</td><td>(37)5th Month Serial No and Quantification</td><td>(38)Sputum Smear Examination 6by8 Month Result</td><td>(39)6by8 Month Serial No and Quantification</td><td>(40)Regimen</td><td>(41)Date of treatment started</td><td>(42)Gen expert</td><td>(43)Lipa Hain Rifampicin</td><td>(44)Lipa Hain Isoniazid</td><td>(45)HIV Test Date</td><td>(46)HIV Status</td><td>(47)Partner HIV Test Date</td><td>(48)Partner HIV Status</td><td>(49)Referred BY: VCT/HCC/STI/HBC/PS/ANC/SR/CI</td><td>(50)Referred TO VCT/HCC/STI/HBC/PS/ANC</td><td>(51)Cotrimoxazole Preventive Therapy Y/N</td><td>(52)Cotrimoxazole Preventive Therapy (Date Started)</td><td>(53)ART Y/N</td><td>(54)ART (Date Started)</td><td>(55)Nutrition Support</td><td>(56)Treatment Outcome</td><td>(57)Treatment Outcome Date</td><td>(58)Remarks</td></tr>

                        
//______________________________________________________________________                        
                        
//([0])Serial Number	
//([1])Date of Registration	
//([2])District Registration Number 
//([4])County
//([5])District                        
//([7])Health Facility	
//([8])Year	
//([9])Quarter 
//([12])Sex M/F	
//([13])Age on registration
//([40])Date of treatment started	
//([44])HIV Test Date	
//([45])HIV Status  
//([52])ART Y/N	
//([53])ART (Date Started)	
//([55])Treatment Outcome	
//([56])Treatment Outcome Date

//([20])Type of TB P/EP
//([23])Type of patient
//([31])Sputum Smear Examination 0th Month Result
//([33])Sputum Smear Examination 2by3 Month Result
//([35])Sputum Smear Examination 5th Month Result
//([37])Sputum Smear Examination 6by8 Month Result
//([41])Gen expert
                       
//tbtype
//patienttype
//smear0
//smear2_3
//smear5
//smear6_8
//genexpert

                        
                        
                                                
//______________________________________________________________________

                         HSSFCell cellserialno = rowi.getCell((short) 0);
                         
                         if(cellserialno.getCellType()==0){
                             //numeric
			serialnumber =""+(int)cellserialno.getNumericCellValue();
                         } else if(cellserialno.getCellType()==1){
			serialnumber =cellserialno.getStringCellValue();
                         } {
                         
                         }
                        
                        HSSFCell cellregdate = rowi.getCell((short)1);
//                            System.out.println("CELLTYPE IS "+cellregdate.getCellType());
                            if(cellregdate.getCellType()==1){
                                //this is a string
			registrationdate = (String)cellregdate.getStringCellValue();
                            }
                            else if(cellregdate.getCellType()==0){
                           //this is a numeric value     
                            registrationdate =""+(int)cellregdate.getNumericCellValue();
                            
                            }
                            else {
                                
                            registrationdate = ""+cellregdate.getDateCellValue();
                        
                            }
                         
                           // System.out.println("Reg Date "+registrationdate);
                            
                         //District Reg number
                             HSSFCell celldistreg = rowi.getCell((short) 2);
                         
                         if(celldistreg.getCellType()==0){
                             //numeric
			distregno =""+(int)celldistreg.getNumericCellValue();
                         } else if(celldistreg.getCellType()==1){
			distregno =celldistreg.getStringCellValue();
                         } {
                         
                         }
                          
                         
                         
                            
                            
                        //dont save county and subcounty directly since they may change
                        HSSFCell cellcounty = rowi.getCell((short) 4);
			county_name = cellcounty.getStringCellValue();
                        
                         HSSFCell cellsubcounty = rowi.getCell((short) 5);
			district_name = cellsubcounty.getStringCellValue();
                        
                         HSSFCell cellfacil = rowi.getCell((short) 7);
			facilityName = cellfacil.getStringCellValue();
                        
                        HSSFCell cellmfl = rowi.getCell((short)8);
                        if(cellmfl.getCellType()==1){
			mflcode = new Integer(cellmfl.getStringCellValue().trim());
                        }
                        else  if(cellmfl.getCellType()==0) {
                        mflcode = (int) cellmfl.getNumericCellValue();
                        }
                         else  {
                        mflcode = mflcode = new Integer(cellmfl.getStringCellValue().trim());
                        }
                        
                          HSSFCell cellsex = rowi.getCell((short)13);
                          if(cellsex.getCellType()==1){
                              //string
			 sex = (String) cellsex.getStringCellValue();
                          }
                          else {
                              //numeric
                           sex = ""+(int)cellsex.getNumericCellValue();
                         
                          }
                          HSSFCell agestringcell = rowi.getCell((short)14);
                           if(agestringcell.getCellType()==1){
                              //string
			 agestring = (String) agestringcell.getStringCellValue();
                          }
                          else {
                              //numeric
                           agestring = ""+(int)agestringcell.getNumericCellValue();
                         
                          }
                           
                           
                                 System.out.println(" mpya agestring is *"+agestring+"*"+distregno);
                          
                         if(agestring.contains("M") && agestring.contains("Y")){
                          //age is in months
                             String agearray[]=agestring.split(" ");
                              //System.out.println("agestring "+agestring+" _ "+age);
                          age=((new Double(agearray[0].trim().replace("Y",""))).intValue()) + (int) Math.round((new Double(agearray[1].replace("M",""))/12));
                           System.out.println(" agestring M&Y "+agestring+" _ "+age);    
                              
                          }
                       
                           else  if(agestring.contains("Y") && !agestring.contains("M")){
                              //age is in years
                              age=(new Double(agestring.trim().replace("Y",""))).intValue();
                              System.out.println(" agestring Y Only "+agestring+" _ "+age); 
                          }
                          else if(agestring.contains("M") && !agestring.contains("Y")){
                          //age is in months
                              System.out.println("agestring "+agestring+" _ "+age);
                          age=(int) Math.round((new Double(agestring.trim().replace("M",""))/12));
                         //  System.out.println("agestring "+agestring+" _ "+age); 
                           System.out.println(" agestring M Only "+agestring+" _ "+age); 
                              
                          }
                          
                          ageinteger=age;
                          agebracket=getageBracket(ageinteger);
                         
                          //tbtype
                          
                           HSSFCell celltbtype=rowi.getCell((short)21);
                          
                           if(celltbtype.getCellType()==1){
                                //this is a string
			   tbtype = (String)celltbtype.getStringCellValue();
                            }
                            else if(celltbtype.getCellType()==0){
                           //this is a numeric value     
                           tbtype =""+(int)celltbtype.getNumericCellValue();
                            
                            }
                            else {
                           tbtype = ""+celltbtype.getDateCellValue();
                        
                            }
                          
                          //type of patient
                           
                           
                           
                           HSSFCell cellpatienttype=rowi.getCell((short)24);
                          
                           if(cellpatienttype.getCellType()==1){
                                //this is a string
			   patienttype = (String)cellpatienttype.getStringCellValue();
                            }
                            else if(cellpatienttype.getCellType()==0){
                           //this is a numeric value     
                           patienttype =""+(int)cellpatienttype.getNumericCellValue();
                            
                            }
                            else {
                           patienttype = ""+cellpatienttype.getDateCellValue();
                        
                            }
                           
                           
                           //------------
                          
                           
                           
                           
                          //smear0
                           
                           
                           
                           HSSFCell cellsmear0=rowi.getCell((short)32);
                          
                           if(cellsmear0.getCellType()==1){
                                //this is a string
			   smear0 = (String)cellsmear0.getStringCellValue();
                            }
                            else if(cellsmear0.getCellType()==0){
                           //this is a numeric value     
                           smear0 =""+(int)cellsmear0.getNumericCellValue();
                            
                            }
                            else {
                           smear0 = ""+cellsmear0.getDateCellValue();
                        
                            }
                           
                           
                           //------------
                           
                       
                           
                           
                           
                           HSSFCell cellsmear2_3=rowi.getCell((short)34);
                          
                           if(cellsmear2_3.getCellType()==1){
                                //this is a string
			   smear2_3 = (String)cellsmear0.getStringCellValue();
                            }
                            else if(cellsmear2_3.getCellType()==0){
                           //this is a numeric value     
                           smear2_3 =""+(int)cellsmear2_3.getNumericCellValue();
                            
                            }
                            else {
                           //smear2_3 = ""+cellsmear0.getValue();
                           smear2_3 = "";
                        
                            }
                           
                           
                           //------------smear5------                       
                            
                           HSSFCell cellsmear5=rowi.getCell((short)36);
                          
                           if(cellsmear5.getCellType()==1){
                                //this is a string
			   smear5 = (String)cellsmear0.getStringCellValue();
                            }
                            else if(cellsmear5.getCellType()==0){
                           //this is a numeric value     
                           smear5 =""+(int)cellsmear5.getNumericCellValue();
                            
                            }
                            else {
                           smear5 = ""+cellsmear5.getDateCellValue();
                        
                            }
                           
                           
                           //------------
                           
                           //------------smear5------                       
                            
                           HSSFCell cellsmear6_8=rowi.getCell((short)38);
                          
                           if(cellsmear6_8.getCellType()==1){
                                //this is a string
			   smear6_8 = (String)cellsmear0.getStringCellValue();
                            }
                            else if(cellsmear6_8.getCellType()==0){
                           //this is a numeric value     
                           smear6_8 =""+(int)cellsmear6_8.getNumericCellValue();
                            
                            }
                            else {
                           smear6_8 = ""+cellsmear6_8.getDateCellValue();
                        
                            }
                           //-------------------
                           
                           
                           
                           
                           
                          HSSFCell celltreatmentdate=rowi.getCell((short)41);
                          
                           if(celltreatmentdate.getCellType()==1){
                                //this is a string
			treatmentstartdate = (String)celltreatmentdate.getStringCellValue();
                            }
                            else if(celltreatmentdate.getCellType()==0){
                           //this is a numeric value     
                            treatmentstartdate =""+(int)celltreatmentdate.getNumericCellValue();
                            
                            }
                            else {
                            treatmentstartdate = ""+celltreatmentdate.getDateCellValue();
                        
                            }
                          
                           //------------smear5------                       
                            
                           HSSFCell cellgenexpert=rowi.getCell((short)42);
                          
                           if(cellgenexpert.getCellType()==1){
                                //this is a string
			   genexpert = (String)cellgenexpert.getStringCellValue();
                            }
                            else if(cellgenexpert.getCellType()==0){
                           //this is a numeric value     
                           genexpert =""+(int)cellgenexpert.getNumericCellValue();
                            
                            }
                            else {
                           genexpert = ""+cellgenexpert.getDateCellValue();
                        
                            }
                           //-------------------
                           
                           
                          
			  //treatmentstartdate=""+celltreatmentdate.getDateCellValue();
                         
                           //Format formatter = new SimpleDateFormat("MM/dd/yyyy");
                         //registrationdatecopy= new SimpleDateFormat("MM/dd/yyyy").format(registrationdate);
                          // registrationdatecopy = formatter.format(registrationdatecopy);
                          //hiv test date 
                           HSSFCell cellhivtestdate=rowi.getCell((short)45);
                           
                           
			 // hivtestdate=""+cellhivtestdate.getStringCellValue();
                         
                          if(cellhivtestdate.getCellType()==1){
                                //this is a string
			   hivtestdate = (String)cellhivtestdate.getStringCellValue();
                           System.out.println("hiv test date solved at level 1="+hivtestdate);
                            }
                            else if(cellhivtestdate.getCellType()==0){
                           //this is a numeric value     
                           hivtestdate =""+(int)cellhivtestdate.getNumericCellValue();
                            System.out.println("hiv test date solved at level 2="+hivtestdate);
                            }
                            else {
                           hivtestdate = ""+cellhivtestdate.getDateCellValue();
                              System.out.println("hiv test date solved at level 3="+hivtestdate);
                            }
                          
                           //hiv status
                          HSSFCell cellhivstatus = rowi.getCell((short)46);
			  hivstatus =(String) cellhivstatus.getStringCellValue();
                         
                          
                          //art status
                           HSSFCell cellartstatus = rowi.getCell((short)53);
                           cellartstatus.setCellType(1);
			  artstatus =(String) cellartstatus.getStringCellValue();
                          
                          
                          //art date
                        
                           HSSFCell cellartdate=rowi.getCell((short)54);
                           cellartdate.setCellType(1);
			  artdate=""+cellartdate.getStringCellValue();
                          
                          //treatment outcome
                            HSSFCell celltreatmentoutcome = rowi.getCell((short)56);
			  treatmentoutcome =(String) celltreatmentoutcome.getStringCellValue();
                          
                          
                          //treatment outcome date 
                          HSSFCell celltreatmentoutcomedate=rowi.getCell((short)57);
			  treatmentoutcomedate=""+celltreatmentoutcomedate.getStringCellValue();
                          
                          
                         
                          //split the date, year and month
                          //raw date is of form dd mmm yyyy eg 08 Jul 2015
                         
                          String dateparameters[]=registrationdate.split(" ");
                        if(dateparameters.length==3){
                            
                         if(!dateparameters[0].equals("")){
                           String month="";
                           month=dateparameters[1];
                           if(month.equalsIgnoreCase("Jan")||month.equalsIgnoreCase("Feb")||month.equalsIgnoreCase("Mar")){
                           
                           quarterName="January-March"; 
                           
                               if(dateparameters[2].length()==4)
                           {
                           year=Integer.parseInt(dateparameters[2]);
                           }
                           
                          
                           }
                           else if(month.equalsIgnoreCase("Apr")||month.equalsIgnoreCase("May")||month.equalsIgnoreCase("Jun")){
                          
                               quarterName="April-June"; 
                               if(dateparameters[2].length()==4)
                           {
                           year=Integer.parseInt(dateparameters[2]);
                           }
                               
                           }
                           
                           else if(month.equalsIgnoreCase("Jul")||month.equalsIgnoreCase("Aug")||month.equalsIgnoreCase("Sep")){
                           
                               quarterName="July-September";  
                                 if(dateparameters[2].length()==4)
                           {
                           year=Integer.parseInt(dateparameters[2]);
                           }
                               
                           }
                            else if(month.equalsIgnoreCase("Oct")||month.equalsIgnoreCase("Nov")||month.equalsIgnoreCase("Dec")){
                           
                               quarterName="October-December";  
                                if(dateparameters[2].length()==4)
                           {
                               //assume
                           year=Integer.parseInt(dateparameters[2])+1;
                           }
                               
                            }
                           
                            }
                       
                        }
                        else {
                        
                            System.out.println("Error in date of testing _ :"+registrationdate);
                              
                              }
                              
                       // System.out.println("Quarter "+quarterName + "Year "+year); 
                       
                      facilityID="";
                      checker=0;  
                      supporttype="DSD";
           
                      String link1="%"+facilityName+"%";
                      
                      if(facilityName.equals("Rongai Health Centre")){ link1=facilityName; }
                      //if(facilityName.equals("Rongai Health Centre")){ link1=facilityName; }
                      
                   String get_id="SELECT SubPartnerID,ART_Support,CentreSanteId as mflcode,HTC_Support1,PMTCT_Support FROM subpartnera WHERE CentreSanteId = ? ";
                   conn.pst=conn.conn.prepareStatement(get_id);
                   conn.pst.setInt(1,mflcode);
                  //conn.pst.setString(2,link1);
                   conn.rs=conn.pst.executeQuery();
                   if(conn.rs.next()==true)
                   {
                       facilityID=conn.rs.getString(1);
                      
                       //mflcode=conn.rs.getInt(3);
                     
                   }
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
                       
                       
                       
                       
//                     CHECK IF ALREADY ADDED TO PMTCT_FO TABLE
                      // id=serialnumber+"_"+registrationdate+"_"+distregno; 
                       id=serialnumber+"_"+registrationdate+"_"+mflcode; 
//                   System.out.println("to add data : "+facilityName+" id : "+facilityID+"mfl code "+mflcode+" year : "+year+" quarter : "+quarter+" numerator : "+Numerator+" denominator : "+Denominator);
                       
                       String checkerExisting="SELECT id FROM "+dbname+" WHERE id='"+id+"'";
                       conn.rs=conn.st.executeQuery(checkerExisting);
                       if(conn.rs.next()==true){
                           checker++;
                                               }

//id 
//SubPartnerID  
//Quarter 
//Year 
//Sex 
//Mflcode 
//age
//agebracket
//SubPartnerNom 
//dateoftesting
//patientccc
//batchno
//suppression_status                       
                       
  if(checker==0){

  
  String inserter="INSERT INTO "+dbname+" (id,SubPartnerID,year,quarter,Mflcode,sex ,age,agebracket,SubPartnerNom,registrationdate,treatmentdate,supporttype,hivstatus,hivtestdate, "
          + " artstatus,artdate,outcomedate,treatmentoutcome,tbtype,patienttype,smear0,smear2_3,smear5,smear6_8,genexpert) "
                         + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1,id);
                        conn.pst.setString(2,facilityID);
                        conn.pst.setInt(3, year);
                        conn.pst.setInt(4, quarter);
                        conn.pst.setInt(5, mflcode);
                        conn.pst.setString(6, sex);
                        conn.pst.setInt(7, age);
                        conn.pst.setString(8, agebracket);
                        conn.pst.setString(9, facilityName);
                        conn.pst.setString(10, registrationdate);
                        conn.pst.setString(11,treatmentstartdate );
                        conn.pst.setString(12, supporttype);
                        conn.pst.setString(13, hivstatus);
                        conn.pst.setString(14, hivtestdate);
                        conn.pst.setString(15,artstatus );
                        conn.pst.setString(16, artdate);
                        conn.pst.setString(17,treatmentoutcomedate );
                        conn.pst.setString(18, treatmentoutcome);
                        
                        conn.pst.setString(19, tbtype);
                        conn.pst.setString(20, patienttype);
                        conn.pst.setString(21, smear0);
                        conn.pst.setString(22, smear2_3);
                        conn.pst.setString(23, smear5);
                        conn.pst.setString(24, smear6_8);
                        conn.pst.setString(25, genexpert);
                        
                        
                        conn.pst.executeUpdate();
                   
                      added++;
                       }
                       else{
          //id,SubPartnerID,Year,Quarter,Mflcode,Sex ,age,agebracket,SubPartnerNom,dateoftesting,patientccc,batchno,supporttype
        String inserter=" UPDATE "+dbname+" SET SubPartnerID=?,year=?,quarter=?,Mflcode=?,sex=? ,age=?,agebracket=?,SubPartnerNom=?,registrationdate=?,treatmentdate=?,supporttype=?,"
                + " hivstatus=?,hivtestdate=?, "
          + " artstatus=?,artdate=?,outcomedate=?,treatmentoutcome=? ,tbtype=?,patienttype=?,smear0=?,smear2_3=?,smear5=?,smear6_8=?,genexpert=?"
                + " WHERE id=?";
//
                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, facilityID);
                        conn.pst.setInt(2, year);
                        conn.pst.setInt(3, quarter);
                        conn.pst.setInt(4, mflcode);
                        conn.pst.setString(5, sex);
                        conn.pst.setInt(6, age);
                        conn.pst.setString(7, agebracket);
                        conn.pst.setString(8, facilityName);
                        conn.pst.setString(9, registrationdate);
                        conn.pst.setString(10, treatmentstartdate);
                        conn.pst.setString(11, supporttype);
                        conn.pst.setString(12, hivstatus);
                        conn.pst.setString(13, hivtestdate);
                        conn.pst.setString(14, artstatus);
                        conn.pst.setString(15,artdate);
                        conn.pst.setString(16,treatmentoutcomedate);
                        conn.pst.setString(17,treatmentoutcome);
                        conn.pst.setString(18,tbtype);
                        
                        conn.pst.setString(19,patienttype);
                        conn.pst.setString(20,smear0);
                        conn.pst.setString(21,smear2_3);
                        conn.pst.setString(22,smear5);
                        conn.pst.setString(23,smear6_8);
                        conn.pst.setString(24,genexpert);
                        conn.pst.setString(25,id);
                        
                        conn.pst.executeUpdate();
                       
                     updated++;
                       }
    
                    }
                    
                    else{
                       missing++; 
//                        missing facilities
                     missingFacility+="facility name : "+facilityName+" mfl code : "+mflcode+" excel row num : "+i+"<br>"; 
                        System.out.println(facilityName+ "_missing");
                    }
                    i++;
                        }

        }
        
         if(conn.rs!=null){conn.rs.close();}
         if(conn.st!=null){conn.st.close();}
         if(conn.pst!=null){conn.pst.close();}
         
         }
         catch (SQLException ex) {
         Logger.getLogger(Load_tb_raw.class.getName()).log(Level.SEVERE, null, ex);
     }
    String sessionText="<br/><b> "+added+ "</b> New data added <br/> <b> "+updated+"</b> updated facilities<br> <br> <b>"+missing+"</b> sites not in Imis Facilities List ";    
    session.setAttribute("upload_success", sessionText);
    pullTb updatedashboards= new pullTb();
    updatedashboards.tbDashboard(startdate.replace("-",""),enddate.replace("-",""));
    
  
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
            String file_name="";
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
      
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                file_name = token.substring(token.indexOf("=") + 2, token.length()-1);
              break;  
            }
            
        }
         System.out.println("content-disposition final : "+file_name);
        return file_name;
    }
    
    public String getageBracket(int age){
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
else if(age>=20&&age<=24){
finalbracket="20-24";
                        }
else if(age>=25&&age<=49){
finalbracket="25-49";
                        }

else if(age>=50){
finalbracket="50+";
                        }

else {
finalbracket="no age";
}
  return finalbracket;  
    }
    
    
  public String maximumdate(String currentmaximum,String newdate){
  String newmaxdate="";
  
  
  return newmaxdate;
  }  
    
}
