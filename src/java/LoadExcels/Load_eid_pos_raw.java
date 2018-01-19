/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

 */

package LoadExcels;

import General.IdGenerator;
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


  public class Load_eid_pos_raw extends HttpServlet {
   
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
String age,pcr_type,system_id;
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 //id	SubPartnerID 	Mflcode	samplecode	collectiondate	testingdate	validation	enrollment	treatment_init_date	enroll_cccno	other_reasons	year	quarter
 
  id="";    
 String samplecode_6="";
 String datecollected_7="";
 String datetested_8="";

 String validation_9="";
 String enrollmentstatus_10="";
 String treatmentinitdate_11="";
 String enroll_ccc_12="";
 String othereasons_13="";
 
mflcode = facilityID=age=pcr_type=system_id=samplecode_6=datecollected_7=datetested_8=validation_9=enrollmentstatus_10=treatmentinitdate_11=enroll_ccc_12=othereasons_13="";
 //-----------------------------------
 //(0)#	
 //(1)County	
 //(2)Sub-County	
 //(3)Facility Name	
 //(4)MFL Code	
 //(5)Partner	
 //(6)Sample Code	
 //(7)Date Collected	
 //(8)Date Tested	
 //(9)Validation (CP,A,VL,RT,UF)	
 //(10)Enrollement Status	
 //(11)Date Initiated on Treatment	
 //(12)Enrollement CCC #	
 //(13)Other Reasons

  //<tr><td>#</td><td>County</td><td>Sub-County</td><td>Facility Name</td><td>MFL Code</td><td>Partner</td><td>Sample Code</td><td>Date Collected</td><td>Date Tested</td><td>Validation (CP,A,VL,RT,UF)</td><td>Enrolment Status</td><td>Date Initiated on Treatment</td><td>Enrollment CCC#</td><td>Other Reasons</td></tr>   
     String serialnumber="";       
     
  
     String dbname="eid_raw_pos";
  

     
    
     
         try {
      session=request.getSession();
      dbConn conn = new dbConn();
   nextpage="load_eid_positive.jsp";
   
   
   
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
         nextpage="load_eid_positive.jsp";
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
                        
serialnumber = mflcode = facilityID=age=pcr_type=system_id=samplecode_6=datecollected_7=datetested_8=validation_9=enrollmentstatus_10=treatmentinitdate_11=enroll_ccc_12=othereasons_13="";                                           
//______________________________________________________________________

                         XSSFCell cellserialno = rowi.getCell((short) 0);
                         
                         if(cellserialno.getCellType()==0){
                             //numeric
			serialnumber =""+(int)cellserialno.getNumericCellValue();
                         } 
                         else if(cellserialno.getCellType()==1){
			serialnumber =cellserialno.getStringCellValue();
                         } 
                      
                           
                        //dont save county and subcounty directly since they may change
                            //________systemid________________
                        XSSFCell cellsystemid = rowi.getCell((short) 1);
			 if(cellsystemid.getCellType()==0){
                             //numeric
			system_id =""+(int)cellsystemid.getNumericCellValue();
                         } 
                         else if(cellsystemid.getCellType()==1){
			system_id =cellsystemid.getStringCellValue();
                         } 
                         
                        
                            //________county________________
                        XSSFCell cellcounty = rowi.getCell((short) 2);
			county_name = cellcounty.getStringCellValue();
                        
                        
                        //_____________subcounty_____________
                        XSSFCell cellsubcounty = rowi.getCell((short) 3);
			district_name = cellsubcounty.getStringCellValue();
                        
                        
                        //____________FacilityName______________
                         XSSFCell cellfacil = rowi.getCell((short) 4);
                         
			facilityName = cellfacil.getStringCellValue();
                        
                       //_______________MFL______ 
                        XSSFCell cellmfl = rowi.getCell((short) 5);
			
                        
                        if(cellmfl.getCellType()==1){
                              //string
			 mflcode = (String) cellmfl.getStringCellValue();
                          }
                          else {
                              //numeric
                           mflcode = ""+(int)cellmfl.getNumericCellValue();                         
                          }
                        
                        
                        
                        
                      //______________________sample Code_______________
                          XSSFCell cellsamplecode = rowi.getCell((short)7);
                          if(cellsamplecode.getCellType()==1){
                              //string
			 samplecode_6 = (String) cellsamplecode.getStringCellValue();
                          }
                          else {
                              //numeric
                           samplecode_6 = ""+(int)cellsamplecode.getNumericCellValue();                         
                          }
                         
                              //________age________________
                        XSSFCell cellage = rowi.getCell((short) 8);
                        if(cellage.getCellType()==1)
                            {
                                //this is a string
			age = (String)cellage.getStringCellValue();
                            }
                            else if(cellage.getCellType()==0)
                            {
                           //this is a numeric value     
                            age =""+(int)cellage.getNumericCellValue();
                            
                            }
                            else 
                            {
                            age = ""+cellage.getDateCellValue();
                        
                            }
			
                        
                            //________county________________
                        XSSFCell cellpcr_type = rowi.getCell((short) 9);
			if(cellpcr_type.getCellType()==1)
                            {
                                //this is a string
			pcr_type = (String)cellpcr_type.getStringCellValue();
                            }
                            else if(cellpcr_type.getCellType()==0)
                            {
                           //this is a numeric value     
                            pcr_type =""+(int)cellpcr_type.getNumericCellValue();
                            
                            }
                            else 
                            {
                            pcr_type = ""+cellpcr_type.getDateCellValue();
                        
                            }
                        
                          
                      //______________________Date Collected__________________________   
                          
                          XSSFCell cellregdate = rowi.getCell((short)10);
//                            System.out.println("CELLTYPE IS "+cellregdate.getCellType());
                            if(cellregdate.getCellType()==1)
                            {
                                //this is a string
			datecollected_7 = (String)cellregdate.getStringCellValue();
                            }
                            else if(cellregdate.getCellType()==0)
                            {
                           //this is a numeric value     
                            datecollected_7 =""+(int)cellregdate.getNumericCellValue();
                            
                            }
                            else 
                            {
                            datecollected_7 = ""+cellregdate.getDateCellValue();
                        
                            } 
                          
                            
                            
                         //_______________Date tested_________________
                            
                           XSSFCell celldatetes = rowi.getCell((short)11);
//                            System.out.println("CELLTYPE IS "+cellregdate.getCellType());
                            if(celldatetes.getCellType()==1)
                            {
                                //this is a string
			   datetested_8 = (String)celldatetes.getStringCellValue();
                            }
                            else if(celldatetes.getCellType()==0)
                            {
                           //this is a numeric value     
                            datetested_8 =""+(int)celldatetes.getNumericCellValue();
                            
                            }
                            else 
                            {
                            datetested_8 = ""+celldatetes.getDateCellValue();
                        
                            }  
                            
                            
                            
                     //_________________________quarter and year_______
                     //split the date, year and month
                          //raw date is of form yyyy-mm-dd eg 08 Jul 2015
                         
                          String dateparameters[]=datetested_8.split("-");
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
                        
                            System.out.println("Error in date of testing _ :"+datetested_8);
                              
                              }       
                            
                            
                            
                            
                       //___________________validation_____________________
                            
                       XSSFCell cellval = rowi.getCell((short) 12);
                         
			validation_9 = cellval.getStringCellValue(); 
                        
                            
                       //___________________Enrollment Status______________
                        XSSFCell cellenroll = rowi.getCell((short) 13);
                         
			enrollmentstatus_10 = cellenroll.getStringCellValue();
                        
                       //___________________Date initiated on Treatment____
                        
                        
                        XSSFCell celltraetmentdate = rowi.getCell((short)14);
//                            System.out.println("CELLTYPE IS "+cellregdate.getCellType());
                            if(celltraetmentdate.getCellType()==1)
                            {
                                //this is a string
			   treatmentinitdate_11 = (String)celltraetmentdate.getStringCellValue();
                            }
                            else if(celltraetmentdate.getCellType()==0)
                            {
                           //this is a numeric value     
                            treatmentinitdate_11 =""+(int)celltraetmentdate.getNumericCellValue();
                            
                            }
                            else 
                            {
                            treatmentinitdate_11 = ""+celltraetmentdate.getDateCellValue();
                        
                            }
                        
                       //___________________Enrollment CCC_________________
                       XSSFCell cellenrollccc = rowi.getCell((short) 15);
                        if(cellenrollccc.getCellType()==1)
                            {
                                //this is a string
			   enroll_ccc_12 = (String)cellenrollccc.getStringCellValue();
                            }
                            else if(celltraetmentdate.getCellType()==0)
                            {
                           //this is a numeric value     
                            enroll_ccc_12 =""+(int)cellenrollccc.getNumericCellValue();
                            
                            }
                            else 
                            {
                            enroll_ccc_12 = ""+cellenrollccc.getRawValue();
                        
                            } 
		      
                       //___________________Other Reasons__________________
                        XSSFCell cellothereasons = rowi.getCell((short) 16);
                         
		       othereasons_13 = cellothereasons.getStringCellValue();  
                          
                         
                         
                       if(treatmentinitdate_11.equals("null")){treatmentinitdate_11="";}
                       if(enroll_ccc_12.equals("null")){enroll_ccc_12="";}
                     
                       
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
                    if(facilityID.length()>0 && datetested_8.length()==10) {
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
                       id=serialnumber+"_"+datetested_8; 
//                   System.out.println("to add data : "+facilityName+" id : "+facilityID+"mfl code "+mflcode+" year : "+year+" quarter : "+quarter+" numerator : "+Numerator+" denominator : "+Denominator);
                       
                       String checkerExisting="SELECT id FROM "+dbname+" WHERE id='"+id+"'";
                       conn.rs=conn.st.executeQuery(checkerExisting);
                       if(conn.rs.next()==true){
                           checker++;
                                               }

                     
                       
  if(checker==0){

  //id	SubPartnerID 	Mflcode	samplecode	collectiondate	testingdate	validation	enrollment	treatment_init_date	enroll_cccno	other_reasons	year	quarter

  String inserter="INSERT INTO "+dbname+" (id,SubPartnerID,year,quarter,Mflcode,samplecode,collectiondate,testingdate,validation,enrollment,"
          + "treatment_init_date, enroll_cccno,other_reasons,SystemID,Age,PCR_Type) "
                         + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1,id);
                        conn.pst.setString(2,facilityID);
                        conn.pst.setInt(3, year);
                        conn.pst.setInt(4, quarter);
                        conn.pst.setString(5, mflcode);
                        conn.pst.setString(6, samplecode_6);
                        conn.pst.setString(7, datecollected_7);
                        conn.pst.setString(8, datetested_8);
                        conn.pst.setString(9, validation_9);
                        conn.pst.setString(10, enrollmentstatus_10);
                        conn.pst.setString(11, treatmentinitdate_11);
                        conn.pst.setString(12, enroll_ccc_12);
                        conn.pst.setString(13, othereasons_13);
                        conn.pst.setString(14, system_id);
                        conn.pst.setString(15, age);
                        conn.pst.setString(16, pcr_type);
                        conn.pst.executeUpdate();  
                        
                        added++;
                        
                       }
  else {
          //id,SubPartnerID,Year,Quarter,Mflcode,Sex ,age,agebracket,SubPartnerNom,dateoftesting,patientccc,batchno,supporttype
        String inserter=" UPDATE "+dbname+" SET SubPartnerID=?,year=?,quarter=?,Mflcode=?, samplecode=?, collectiondate=? ,testingdate=? , validation=? ,enrollment=? ,"
          + "treatment_init_date=? , enroll_cccno=? ,other_reasons=?,SystemID=?,Age=?,PCR_Type=?  "
                + " WHERE id=?";
//
                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, facilityID);
                           conn.pst.setInt(2, year);
                           conn.pst.setInt(3, quarter);
                           conn.pst.setString(4, mflcode);
                        conn.pst.setString(5,samplecode_6);
                        conn.pst.setString(6,datecollected_7);
                        conn.pst.setString(7,datetested_8);
                        conn.pst.setString(8,validation_9);
                        conn.pst.setString(9,enrollmentstatus_10);
                        conn.pst.setString(10,treatmentinitdate_11);
                        conn.pst.setString(11,enroll_ccc_12);
                        conn.pst.setString(12,othereasons_13);                        
                        conn.pst.setString(13,system_id);                        
                        conn.pst.setString(14,age);                        
                        conn.pst.setString(15,pcr_type);                        
                        conn.pst.setString(16,id);
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
