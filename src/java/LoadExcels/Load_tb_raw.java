/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100) 

/**
 *
 * @author Emmanuel Kaunda
 */


  public class Load_tb_raw extends HttpServlet {
   
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

     String serialnumber="";
     String distregno="";   
     String agebracket="";
     String registrationdate="";
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
           fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            System.out.println("file name is  :  "+fileName);
        }
        if(!fileName.endsWith(".xls")){
         nextpage="sync_viral_load.jsp";
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
                        
                        int i=2,y=0;
			while(rowIterator.hasNext()){
                            System.out.println(" in while");
			HSSFRow rowi = worksheet.getRow(i);
                        if( rowi==null){
                                nextpage="sync_viral_load.jsp";
                         break;
                        }
//([0])Serial Number	([1])Date of Registration	([2])District Registration Number	(3)Province	(4)County	(5)District	(6)Zone	([7])Health Facility	([8])Year	([9])Quarter	(10)Sector	(11)Patient Name	([12])Sex M/F	([13])Age on registration	(14)Weight (Kgs)	(15)Height (Mtrs)	(16)BMI	(17)MUAC	(18)Physical address (Neighbor,Primary School) Cell Phone	(19)DOT by	(20)Type of TB P/EP	(21)EPTB Sub Type	(22)EPTB Others	(23)Type of patient	(24)CD4 First Date	(25)CD4 Last Date	(26)Culture S	(27)Culture R	(28)Culture E	(29)Culture H	(30)X-ray Y/N	(31)Sputum Smear Examination (32)0th Month Result	(33)0th Month Serial No and Quantification	(34)Sputum Smear Examination 2by3 Month Result	(35)2by3 Month Serial No and Quantification	(36)Sputum Smear Examination 5th Month Result	(37)5th Month Serial No and Quantification	(38)Sputum Smear Examination 6by8 Month Result	(39)6by8 Month Serial No and Quantification	(40)Regimen	([41])Date of treatment started	(42)Gen expert	(43)Lipa Hain Rifampicin	(44)Lipa Hain Isoniazid	([45])HIV Test Date	([46])HIV Status	(47)Partner HIV Test Date	(48)Partner HIV Status	(49)Referred BY: VCT/HCC/STI/HBC/PS/ANC/SR/CI	(49)Referred TO VCT/HCC/STI/HBC/PS/ANC	(50)Cotrimoxazole Preventive Therapy Y/N	(51)Cotrimoxazole Preventive Therapy (Date Started)	([52])ART Y/N	([53])ART (Date Started)	(54)Nutrition Support	([55])Treatment Outcome	([56])Treatment Outcome Date	([57])Remarks

                        
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
//([41])Date of treatment started	
//([45])HIV Test Date	
//([46])HIV Status  
//([52])ART Y/N	
//([53])ART (Date Started)	
//([55])Treatment Outcome	
//([56])Treatment Outcome Date

                                                
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
                            System.out.println("CELLTYPE IS "+cellregdate.getCellType());
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
                        
                        //HSSFCell cellmfl = rowi.getCell((short) 7);
			//mflcode = (int) cellmfl.getNumericCellValue();
                        
                          HSSFCell cellsex = rowi.getCell((short)12);
                          if(cellsex.getCellType()==1){
                              //string
			 sex = (String) cellsex.getStringCellValue();
                          }
                          else {
                              //numeric
                           sex = ""+(int)cellsex.getNumericCellValue();
                         
                          }
                          HSSFCell agestringcell = rowi.getCell((short)13);
			  agestring =(String) agestringcell.getStringCellValue();
                        
                       
                          if(agestring.contains("Y")){
                              //age is in years
                              age=new Integer(agestring.replace("Y",""));
                          }
                          else if(agestring.contains("M")){
                          //age is in months
                          age=Math.round((new Integer(agestring.replace("M",""))/12)*100);
                              
                              
                          }
                          
                          ageinteger=age;
                          agebracket=getageBracket(ageinteger);
                         
                          
                          HSSFCell celltreatmentdate=rowi.getCell((short)41);
			  treatmentstartdate=""+celltreatmentdate.getDateCellValue();
                         
                           Format formatter = new SimpleDateFormat("MM/dd/yyyy");
                         registrationdate= new SimpleDateFormat("MM/dd/yyyy").format(cellregdate.getDateCellValue());
                           registrationdate = formatter.format(registrationdate);
                          //hiv test date 
                           HSSFCell cellhivtestdate=rowi.getCell((short)45);
			  hivtestdate=""+cellhivtestdate.getDateCellValue();
                           
                           //hiv status
                          HSSFCell cellhivstatus = rowi.getCell((short)46);
			  hivstatus =(String) cellhivstatus.getStringCellValue();
                         
                          
                          //art status
                           HSSFCell cellartstatus = rowi.getCell((short)13);
			  hivstatus =(String) cellartstatus.getStringCellValue();
                          
                          HSSFCell cellsuppression=rowi.getCell((short)22);
                          if(cellsuppression.getCellType()==1){
                          //string
                                suppression=cellsuppression.getStringCellValue();
                         
                          }
                          else {
                              
			  suppression=""+(int)cellsuppression.getNumericCellValue();
                          
                          }
                          //split the date, year and month
                          //raw date is of form m/d/yyyy
                         
                          String dateparameters[]=dateoftesting.split("/");
                        if(dateparameters.length==3){
                            
                         if(!dateparameters[0].equals("")){
                           String month="";
                           month=dateparameters[0];
                           if(month.equals("01")||month.equals("02")||month.equals("03")){
                           
                           quarterName="January-March"; 
                           
                               if(dateparameters[2].length()==4)
                           {
                           year=Integer.parseInt(dateparameters[2]);
                           }
                           
                          
                           }
                           else if(month.equals("04")||month.equals("05")||month.equals("06")){
                          
                               quarterName="April-June"; 
                               if(dateparameters[2].length()==4)
                           {
                           year=Integer.parseInt(dateparameters[2]);
                           }
                               
                           }
                           
                           else if(month.equals("07")||month.equals("08")||month.equals("09")){
                           
                               quarterName="July-September";  
                                 if(dateparameters[2].length()==4)
                           {
                           year=Integer.parseInt(dateparameters[2]);
                           }
                               
                           }
                            else if(month.equals("10")||month.equals("11")||month.equals("12")){
                           
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
                        
                            System.out.println("Error in date of testing _ :"+dateoftesting);
                              
                              }
                              
                        System.out.println("Quarter "+quarterName + "Year "+year); 
                       
                      facilityID="";
                      checker=0;     
           
                   String get_id="SELECT SubPartnerID,ART_Support FROM subpartnera WHERE CentreSanteId=?";
                   conn.pst=conn.conn.prepareStatement(get_id);
                   conn.pst.setInt(1,mflcode);
                   conn.rs=conn.pst.executeQuery();
                   if(conn.rs.next()==true)
                   {
                       facilityID=conn.rs.getString(1);
                       supporttype=conn.rs.getString(2);
                       if(supporttype==null){supporttype="";}
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
                       id=batchno+"_"+patientccc+"_"+dateoftesting; 
//                   System.out.println("to add data : "+facilityName+" id : "+facilityID+"mfl code "+mflcode+" year : "+year+" quarter : "+quarter+" numerator : "+Numerator+" denominator : "+Denominator);
                       
                       String checkerExisting="SELECT id FROM viral_load_raw WHERE id='"+id+"'";
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

  
  String inserter="INSERT INTO viral_load_raw (id,SubPartnerID,Year,Quarter,Mflcode,Sex ,age,agebracket,SubPartnerNom,dateoftesting,patientccc,batchno,supporttype,suppression_status) "
                         + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                        conn.pst.setString(10, dateoftesting);
                        conn.pst.setString(11, patientccc);
                        conn.pst.setString(12, batchno);
                        conn.pst.setString(13, supporttype);
                        conn.pst.setString(14, suppression);
                        conn.pst.executeUpdate();
                   
                      added++;
                       }
                       else{
          //id,SubPartnerID,Year,Quarter,Mflcode,Sex ,age,agebracket,SubPartnerNom,dateoftesting,patientccc,batchno,supporttype
        String inserter=" UPDATE viral_load_raw SET SubPartnerID=?,Year=?,Quarter=?,Mflcode =?,Sex=?,age=?,agebracket=?,SubPartnerNom=?,dateoftesting=?,patientccc=?,batchno=?,supporttype=?,suppression_status=? "
                + " WHERE id=?";

                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, facilityID);
                        conn.pst.setInt(2, year);
                        conn.pst.setInt(3, quarter);
                        conn.pst.setInt(4, mflcode);
                        conn.pst.setString(5, sex);
                        conn.pst.setInt(6, age);
                        conn.pst.setString(7, agebracket);
                        conn.pst.setString(8, facilityName);
                        conn.pst.setString(9, dateoftesting);
                        conn.pst.setString(10, patientccc);
                        conn.pst.setString(11, batchno);
                        conn.pst.setString(12, supporttype);
                        conn.pst.setString(13, suppression);
                        conn.pst.setString(14, id);
                        conn.pst.executeUpdate();
                       
                     updated++;
                       }
    
                    }
                    
                    else{
                       missing++; 
//                        missing facilities
                     missingFacility+="facility name : "+facilityName+" mfl code : "+mflcode+" excel row num : "+i+"<br>"; 
                        System.out.println(facilityName+ "facility is missing mflcode on subpartner :"+mflcode);
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
    
    public String getageBracket(int age){
    //<1	1-4	5-14	15-19	20+
        String finalbracket="";
if(age<1){
finalbracket="<1";
}
else if(age>=1&&age<=4){
finalbracket="1-4";
                        }
else if(age>=5&&age<=14){
finalbracket="5-14";
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
