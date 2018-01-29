/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

 */

package LoadExcels;

import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100) 

/**
 *
 * @author Emmanuel Kaunda
 */


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

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
     //#(0)	
     //System ID(1)	
     //Batch No(2)	
     //Sample Code(3)	
     //Testing Lab(4)	
     //County(5)	
     //Sub-County(6)
     //Facility Name(7)	
     //MFL Code(8)	
     //Partner(9)
     //Sex(10)
     //Age(Months)(11)	
     //Infant Prophylaxis(12)	
     //Date Collected(13)	
     //Spots(14)	
     //Received Status(15)	
     //Reason for Repeat / Rejection(16)	
     //HIV Status of Mother(17)	
     //PMTCT Intervention(18)	
     //Breast Feeding(19)	
     //Entry Point(20)	
     //Date Received(21)	
     //Date Tested(22)	
     //Date Dispatched(23)	
     //Test Result(24)
     
     
  id=""; 
  String order_0="";
  String batchno_2="";
  String samplecode_3="";
  String testinglab_4="";
  String sex_10="";
  String ageString_11="";
  String infant_prop_12="";
  String datecollected_13="";
  String spots_14="";
  String received_status_15="";
  String repeatreason_16="";
  String mumhiv_status_17="";
  String pmtct_intervation_18="";
  String breastfeeding_19="";
  String entrypoint_20="";
  String datereceived_21="";  
 String datetested_22="";
 String datedispatched_23="";
 String testresult_24="";
 String pcr_type="";
 
 //<tr><td>#(1)</td><td>System ID(2)</td><td>Batch No(3)</td><td>Sample Code(4)</td><td>Testing Lab(5)</td><td>County(6)</td><td>Sub-County(7)</td><td>Facility Name(8)</td><td>MFL Code(9)</td><td>Partner(10)</td><td>Sex(11)</td><td>Age(Months)(12)</td><td>Infant Prophylaxis(13)</td><td>Date Collected(14)</td><td>Spots(15)</td><td>Received Status(16)</td><td>Reason for Repeat / Rejection(17)</td><td>HIV Status of Mother(18)</td><td>PMTCT Intervention(19)</td><td>Breast Feeding(20)</td><td>Entry Point(21)</td><td>Date Received(22)</td><td>Date Tested(23)</td><td>Date Dispatched(24)</td><td>Test Result(25)</td></tr>
 
 //-----------------------------------
 //id	order	batchno	samplecode	testinglab	SubPartnerID	Mflcode	sex	age	infantprophylaxis	datecollected	spots	receivedstatus	repeat_rejection_reason	hivstatus_mum	pmtct_intervention	breastfeeding	entrypoint	datereceived	datetested	datedispatched	testresult	year	quarter

    String serialnumber="";
    
     String dbname="eid_raw_tested";
  
     
         try {
      session=request.getSession();
      dbConn conn = new dbConn();
   nextpage="sync_eid.jsp";
   
   
   
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
            
 full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
 
 System.out.println("the saved file directory is  :  "+full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
 
  FileInputStream fileInputStream = new FileInputStream(full_path);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet worksheet = workbook.getSheetAt(0);
			Iterator rowIterator = worksheet.iterator();
                        
                        int i=1,y=0;
			while(rowIterator.hasNext()){
//                            System.out.println(" in while");
			XSSFRow rowi = worksheet.getRow(i);
                        if( rowi==null){
                                
                         break;
                                        }
                        

                        
//______________________________________________________________________                        
                        
                                           
//______________________________________________________________________
                         //-----------serial number-----------------------
                         XSSFCell cellserialno = rowi.getCell((short) 0);
                            System.out.println("___i is__"+i);
                         if(cellserialno.getCellType()==0){
                             //numeric
			order_0 =""+(int)cellserialno.getNumericCellValue();
                         } 
                         else if(cellserialno.getCellType()==1){
			order_0 =cellserialno.getStringCellValue();
                         } 
                      
                         
         //_____________________________systemid_____________________________
         XSSFCell cellsystemid = rowi.getCell((short) 1);
                         
                         if(cellsystemid.getCellType()==0){
                             //numeric
			serialnumber =""+(int)cellsystemid.getNumericCellValue();
                         } 
                         else if(cellsystemid.getCellType()==1){
			serialnumber =cellsystemid.getStringCellValue();
                         }
                        
         //_____________________________batchno_____________________________
         XSSFCell cellbatchno = rowi.getCell((short) 2);
                         
                         if(cellbatchno.getCellType()==0){
                             //numeric
			batchno_2 =""+(int)cellbatchno.getNumericCellValue();
                         } 
                         else if(cellbatchno.getCellType()==1){
			batchno_2 =cellbatchno.getStringCellValue();
                         }                
                         
                                        
         //______________________sample Code_______________
                          XSSFCell cellsamplecode = rowi.getCell((short)3);
                          if(cellsamplecode.getCellType()==1){
                              //string
			 samplecode_3 = (String) cellsamplecode.getStringCellValue();
                          }
                          else {
                              //numeric
                           samplecode_3 = ""+(int)cellsamplecode.getNumericCellValue();                         
                          }                   
                            
                        //dont save county and subcounty directly since they may change
                            //________county________________
                        XSSFCell cellcounty = rowi.getCell((short) 5);
			county_name = cellcounty.getStringCellValue();
                        
                        
                        //_____________subcounty_____________
                        XSSFCell cellsubcounty = rowi.getCell((short) 6);
			district_name = cellsubcounty.getStringCellValue();
                        
                        
                        //____________FacilityName______________
                         XSSFCell cellfacil = rowi.getCell((short) 7);
                         
			facilityName = cellfacil.getStringCellValue();
                        
                       //_______________MFL______ 
                        XSSFCell cellmfl = rowi.getCell((short) 8);
			
                        if(cellmfl.getCellType()==1){
                              //string
			 mflcode = (String) cellmfl.getStringCellValue();
                          }
                          else {
                              //numeric
                           mflcode = ""+(int)cellmfl.getNumericCellValue();                         
                          }
                        
                        
                        
                        //_______________SEX_______________ 
                        XSSFCell cellsex = rowi.getCell((short) 10);
			sex_10 = cellsex.getStringCellValue();
                        
                        //________________AGE______________
                         XSSFCell cellage = rowi.getCell((short)11);			 
                        
                           if(cellage.getCellType()==1)
                           {
                              //string
			 ageString_11 = (String) cellage.getStringCellValue();
                           }
                        else {
                              //numeric
                           ageString_11 = ""+(int)cellage.getNumericCellValue();                         
                             }
                          
                             
                       
                        int ageinteger=0; 
                        if(ageString_11.length()>0 && ageString_11.length()<4){ //i expect age in interms of numbers to be of length 1 eg 0 to 3 eg 323
                         //ageinteger= (int) Math.rint((new Integer(ageString_11)/12)); 
                         
                         double myage=(Double.parseDouble(ageString_11)/12);
                         ageinteger= (int) myage;
                         
                        
                        }
                        
                        String agebracket=getageBracket(ageinteger); 
                           
                       System.out.println("AGE :months_ "+ageString_11+" AGE years_ "+ageinteger+" Agebracket:"+agebracket);
                            
                        
                      //_____________________Infant Prophylaxis_______________________
                      
                            XSSFCell cellip = rowi.getCell((short)12);
			  infant_prop_12 = cellip.getStringCellValue();
                            
                          
                      //______________________Date Collected__________________________   
                          
                          XSSFCell cellregdate = rowi.getCell((short)13);
//                            System.out.println("CELLTYPE IS "+cellregdate.getCellType());
                            if(cellregdate.getCellType()==1)
                            {
                                //this is a string
			datecollected_13 = (String)cellregdate.getStringCellValue();
                            }
                            else if(cellregdate.getCellType()==0)
                            {
                           //this is a numeric value     
                            datecollected_13 =""+(int)cellregdate.getNumericCellValue();
                            
                            }
                            else 
                            {
                            datecollected_13 = ""+cellregdate.getDateCellValue();
                        
                            } 
                          
                        //_____________________spots____________________      
                          XSSFCell cellspots = rowi.getCell((short)14);
			 
                           if(cellspots.getCellType()==1)
                           {
                              //string
			 spots_14 = (String) cellspots.getStringCellValue();
                           }
                        else {
                              //numeric
                           spots_14 = ""+(int)cellspots.getNumericCellValue();                         
                             }
                          
                        //_____________________received status___________      
                          XSSFCell cellrs = rowi.getCell((short)15);
			  received_status_15 = cellrs.getStringCellValue(); 
                          
                          
                          //__________________pcr_type____
                          XSSFCell cellpcr_type = rowi.getCell((short)16);
			 
                          if(cellpcr_type.getCellType()==1)
                           {
                              //string
			 pcr_type = (String) cellpcr_type.getStringCellValue();
                           }
                        else if(cellpcr_type.getCellType()==0)
                        {
                              //numeric
                           pcr_type = ""+(int)cellpcr_type.getNumericCellValue();                         
                        } 
                        else {
                                  
                                     
                             }
                          //__________________repeat_rejection_reason____
                          XSSFCell cellrepeatrej = rowi.getCell((short)17);
			 
                          if(cellrepeatrej.getCellType()==1)
                           {
                              //string
			 repeatreason_16 = (String) cellrepeatrej.getStringCellValue();
                           }
                        else if(cellrepeatrej.getCellType()==0)
                        {
                              //numeric
                           repeatreason_16 = ""+(int)cellrepeatrej.getNumericCellValue();                         
                        } 
                        else {
                                  
                                     
                             }
                          
                          
                          //__________________mother hiv status__________
                          XSSFCell cellmumhivstatus = rowi.getCell((short)18);
			  mumhiv_status_17 = cellmumhivstatus.getStringCellValue();
                          
                          
                          //__________________pmtct_intervention___________
                          XSSFCell cellpmtctint = rowi.getCell((short)19);
			  pmtct_intervation_18 = cellpmtctint.getStringCellValue();
                          
                          
                          //__________________breastfeeding___________
                          XSSFCell cellbf = rowi.getCell((short)20);
			  breastfeeding_19 = cellbf.getStringCellValue();
                          
                           //__________________entrypoint___________
                          XSSFCell cellep = rowi.getCell((short)21);
			  entrypoint_20 = cellep.getStringCellValue();
                          
                          //___________________date received_______
                          
                            
                           XSSFCell celldaterec = rowi.getCell((short)22);
//                            System.out.println("CELLTYPE IS "+cellregdate.getCellType());
                            if(celldaterec.getCellType()==1)
                            {
                                //this is a string
			   datereceived_21 = (String)celldaterec.getStringCellValue();
                            }
                            else if(celldaterec.getCellType()==0)
                            {
                           //this is a numeric value     
                            datereceived_21 =""+(int)celldaterec.getNumericCellValue();
                            
                            }
                            else 
                            {
                            datereceived_21 = ""+celldaterec.getDateCellValue();
                        
                            }
                          
                          
                          
                         //_______________Date tested_________________
                            
                           XSSFCell celldatetes = rowi.getCell((short)23);
//                            System.out.println("CELLTYPE IS "+cellregdate.getCellType());
                            if(celldatetes.getCellType()==1)
                            {
                                //this is a string
			   datetested_22 = (String)celldatetes.getStringCellValue();
                            }
                            else if(celldatetes.getCellType()==0)
                            {
                           //this is a numeric value     
                            datetested_22 =""+(int)celldatetes.getNumericCellValue();
                            
                            }
                            else 
                            {
                            datetested_22 = ""+celldatetes.getDateCellValue();
                        
                            }  
                            
                           
                            //_______________Date dispatched_________________
                            
                           XSSFCell celldatedis = rowi.getCell((short)24);
//                            System.out.println("CELLTYPE IS "+cellregdate.getCellType());
                            if(celldatedis.getCellType()==1)
                            {
                                //this is a string
			   datedispatched_23 = (String)celldatedis.getStringCellValue();
                            }
                            else if(celldatedis.getCellType()==0)
                            {
                           //this is a numeric value     
                            datedispatched_23 =""+(int)celldatedis.getNumericCellValue();
                            
                            }
                            else 
                            {
                            datedispatched_23 = ""+celldatedis.getDateCellValue();
                        
                            } 
                          
                            
                          //__________________testresult___________
                          XSSFCell celltestres = rowi.getCell((short)25);
			  testresult_24 = celltestres.getStringCellValue();  
                            
                            
                     //_________________________quarter and year_______
                     //split the date, year and month
                          //raw date is of form yyyy-mm-dd eg 08 Jul 2015
                         
                          String dateparameters[]=datetested_22.split("-");
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
                        
                            System.out.println("Error in date of testing _ :"+datetested_22);
                              
                              }       
                            
                            
                            
                            
                         
                       
                     
                       
                      facilityID="";
                      checker=0;  
                      
           //
                   String get_id="SELECT SubPartnerID,ART_Support,CentreSanteId as mflcode,HTC_Support1,PMTCT_Support FROM subpartnera WHERE CentreSanteId like ? ";
                   conn.pst=conn.conn.prepareStatement(get_id);
                   conn.pst.setString(1,"%"+mflcode+"%");
                   
                   conn.rs=conn.pst.executeQuery();
                   if(conn.rs.next()==true)
                   {
                       facilityID=conn.rs.getString(1);
                       //supporttype=conn.rs.getString("ART_Support");
                       //mflcode=conn.rs.getInt(3);
                      
                      //if(supporttype==null){supporttype=conn.rs.getString("HTC_Support1");}
                      //if(supporttype==null){supporttype=conn.rs.getString("PMTCT_Support");}
                      //if(supporttype==null){supporttype="";}
                   }
                    if(facilityID.length()>0 && datetested_22.length()==10 && !sex_10.trim().equals("")) {
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
                       id=order_0+"_"+serialnumber+"_"+datetested_22; 
//                   System.out.println("to add data : "+facilityName+" id : "+facilityID+"mfl code "+mflcode+" year : "+year+" quarter : "+quarter+" numerator : "+Numerator+" denominator : "+Denominator);
                       
                       String checkerExisting="SELECT id FROM "+dbname+" WHERE id='"+id+"'";
                       conn.rs=conn.st.executeQuery(checkerExisting);
                       if(conn.rs.next()==true){
                           checker++;
                                               }

                     
                       
  if(checker==0){

  //id	SubPartnerID 	Mflcode	samplecode	collectiondate	testingdate	validation	enrollment	treatment_init_date	enroll_cccno	other_reasons	year	quarter

  String inserter="INSERT INTO "+dbname+" ( id,orderno,batchno,samplecode,SubPartnerID,Mflcode,sex,age,agebracket,infantprophylaxis,datecollected,spots,receivedstatus,"
          + "repeat_rejection_reason,hivstatus_mum,pmtct_intervention,breastfeeding,entrypoint,datereceived,datetested,datedispatched,testresult,year,quarter,age_months,PCR_Type) "
                         + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1,id);
                        conn.pst.setString(2,order_0);
                        conn.pst.setString(3,batchno_2);
                        conn.pst.setString(4,samplecode_3);
                        conn.pst.setString(5,facilityID);
                        conn.pst.setString(6,mflcode);
                        conn.pst.setString(7,sex_10);
                        conn.pst.setInt(8,ageinteger);
                        conn.pst.setString(9,agebracket);
                        conn.pst.setString(10,infant_prop_12);
                        conn.pst.setString(11,datecollected_13);
                        conn.pst.setString(12,spots_14);
                        conn.pst.setString(13,received_status_15);
                        conn.pst.setString(14,repeatreason_16);
                        conn.pst.setString(15,mumhiv_status_17);
                        conn.pst.setString(16,pmtct_intervation_18);
                        conn.pst.setString(17,breastfeeding_19);
                        conn.pst.setString(18,entrypoint_20);
                        conn.pst.setString(19,datereceived_21);
                        conn.pst.setString(20,datetested_22);
                        conn.pst.setString(21,datedispatched_23);
                        conn.pst.setString(22,testresult_24);
                        conn.pst.setInt(23,year);
                        conn.pst.setInt(24,quarter);                        
                        conn.pst.setString(25,ageString_11);                        
                        conn.pst.setString(26,pcr_type);                        
                        conn.pst.executeUpdate();  
                        
                        added++;
                        
                       }
  else {
          //id,SubPartnerID,Year,Quarter,Mflcode,Sex ,age,agebracket,SubPartnerNom,dateoftesting,patientccc,batchno,supporttype
        String inserter=" UPDATE "+dbname+" SET orderno=? ,batchno=? ,samplecode=? ,SubPartnerID=? ,Mflcode=? ,sex=? ,age=? ,agebracket=? ,infantprophylaxis=? ,datecollected=? ,spots=? ,receivedstatus=? ,"
          + "repeat_rejection_reason=? ,hivstatus_mum=? ,pmtct_intervention=? ,breastfeeding=? ,entrypoint=? ,datereceived=? ,datetested=? ,datedispatched=? ,testresult=? ,year=? ,quarter=?, age_months=?,PCR_Type=? "
                + " WHERE id=?";
//
                        conn.pst=conn.conn.prepareStatement(inserter);
                       
                        conn.pst.setString(1,order_0);
                        conn.pst.setString(2,batchno_2);
                        conn.pst.setString(3,samplecode_3);
                        conn.pst.setString(4,facilityID);
                        conn.pst.setString(5,mflcode);
                        conn.pst.setString(6,sex_10);
                        conn.pst.setInt(7,ageinteger);
                        conn.pst.setString(8,agebracket);
                        conn.pst.setString(9,infant_prop_12);
                        conn.pst.setString(10,datecollected_13);
                        conn.pst.setString(11,spots_14);
                        conn.pst.setString(12,received_status_15);
                        conn.pst.setString(13,repeatreason_16);
                        conn.pst.setString(14,mumhiv_status_17);
                        conn.pst.setString(15,pmtct_intervation_18);
                        conn.pst.setString(16,breastfeeding_19);
                        conn.pst.setString(17,entrypoint_20);
                        conn.pst.setString(18,datereceived_21);
                        conn.pst.setString(19,datetested_22);
                        conn.pst.setString(20,datedispatched_23);
                        conn.pst.setString(21,testresult_24);
                        conn.pst.setInt(22,year);
                        conn.pst.setInt(23,quarter);
                        conn.pst.setString(24,ageString_11);
                         conn.pst.setString(25,pcr_type);
                         conn.pst.setString(26,id);
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
    
    public String getageBracket(int age)
    {
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
