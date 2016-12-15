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
 * @author Geofrey Nyabuto
 */


  public class Load_viral_load_raw extends HttpServlet {
   
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

     String patientccc="";
     String suppression="";
     String testingdate="";
     String agebracket="";
     String dateoftesting="";
     int age=0;
     String sex="";
     String batchno="";
     String supporttype="";
     int ageinteger=0;
     
         try {
      session=request.getSession();
      dbConn conn = new dbConn();
   nextpage="sync_viral_load.jsp";
   
   
   
   //---------------------------------------------------------------------
   
  String numerator_un ,denominator_un;
  String fun_less1,fun_1to4,fun_5to14,fun_15to19,fun_20;
  String mun_less1,mun_1to4,mun_5to14,mun_15to19,mun_20;
  String subtotal_un,numerator_vi,denominator_vi;
  String fvi_less1,fvi_1to4 ,fvi_5to14,fvi_15to19,fvi_20;
  String mvi_less1,mvi_1to4,mvi_5to14,mvi_15to19,mvi_20 ,subtotal_vi;
  
   //---------------------------------------------------------------------
   
   numerator_un =denominator_un="";
   fun_less1=fun_1to4=fun_5to14=fun_15to19=fun_20="";
   mun_less1=mun_1to4=mun_5to14=mun_15to19=mun_20="";
   subtotal_un=numerator_vi=denominator_vi="";
   fvi_less1=fvi_1to4 =fvi_5to14=fvi_15to19=fvi_20="";
   mvi_less1=mvi_1to4=mvi_5to14=mvi_15to19=mvi_20 =subtotal_vi="";
  
   

 

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
//#(0)	Batch No(1)	Patient CCC No(2)	Testing Lab(3)	County (4)	District(5)	Facility Name(6)	MFL Code(7)	Sex(8)	Age(9)	Sample Type(10)	Collection Date(11)	Received Status(12)	Reason for Repeat(13)	Regimen (14)	Justification (15)	ART Initiation Date(16)	Date of Receiving(17)	Date of Testing(18)	Date of Dispatch (19)	Result(cp/ml) (20)	Result(Log) (21)	Suppressed? (22)
//Patient CCC No(2)  County (4)	District(5) Facility Name(6) MFL Code(7) Sex(8)	Age(9)  Date of Testing(18) 	Suppressed? (22)

                         HSSFCell cellbatcno = rowi.getCell((short) 1);
                         
                         if(cellbatcno.getCellType()==0){
                             //numeric
			batchno =""+(int)cellbatcno.getNumericCellValue();
                         } else if(cellbatcno.getCellType()==1){
			batchno =cellbatcno.getStringCellValue();
                         } {
                         
                         }
                        
                        HSSFCell cellpatienceno = rowi.getCell((short) 2);
                            System.out.println("CELLTYPE IS "+cellpatienceno.getCellType());
                            if(cellpatienceno.getCellType()==1){
                                //this is a string
			patientccc = (String)cellpatienceno.getStringCellValue();
                            }
                            else if(cellpatienceno.getCellType()==0){
                           //this is a numeric value     
                            patientccc =""+(int)cellpatienceno.getNumericCellValue();
                            
                            }
                            else {
                            patientccc = (String)cellpatienceno.getStringCellValue();
                        
                            }
                        //dont save county and subcounty directly since they may change
                        HSSFCell cellcounty = rowi.getCell((short) 4);
			county_name = cellcounty.getStringCellValue();
                        
                         HSSFCell cellsubcounty = rowi.getCell((short) 5);
			district_name = cellsubcounty.getStringCellValue();
                        
                         HSSFCell cellfacil = rowi.getCell((short) 6);
			facilityName = cellfacil.getStringCellValue();
                        
                          HSSFCell cellmfl = rowi.getCell((short) 7);
			
                        
                        if(cellmfl.getCellType()==1){
                              //string
			 mflcode = new Integer(cellmfl.getStringCellValue());
                          }
                          else {
                              //numeric
                           mflcode =(int)cellmfl.getNumericCellValue();
                         
                          }
                        
                          HSSFCell cellsex = rowi.getCell((short)8);
                          if(cellsex.getCellType()==1){
                              //string
			 sex = (String) cellsex.getStringCellValue();
                          }
                          else {
                              //numeric
                           sex = ""+(int)cellsex.getNumericCellValue();
                         
                          }
                          HSSFCell cellage = rowi.getCell((short)9);
			  age = (int)cellage.getNumericCellValue();
                        
                       
                          ageinteger=age;
                          agebracket=getageBracket(ageinteger);
                         
                          
                          HSSFCell celldate=rowi.getCell((short)18);
			  dateoftesting=""+celldate.getStringCellValue();
                         
                           //Format formatter = new SimpleDateFormat("MM/dd/yyyy");
                        // dateoftesting= new SimpleDateFormat("MM/dd/yyyy").format(celldate.getDateCellValue());
                           //dateoftesting = formatter.format(dateoftesting);
                          
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
                         
                          String dateparameters[]=dateoftesting.split("-");
                        if(dateparameters.length==3){
                            
                         if(!dateparameters[0].equals("")){
                           String month="";
                           month=dateparameters[1];
                           if(month.equals("01")||month.equals("02")||month.equals("03")){
                           
                           quarterName="January-March"; 
                           
                               if(dateparameters[0].length()==4)
                           {
                           year=Integer.parseInt(dateparameters[0]);
                           }
                           
                          
                           }
                           else if(month.equals("04")||month.equals("05")||month.equals("06")){
                          
                               quarterName="April-June"; 
                               if(dateparameters[0].length()==4)
                           {
                           year=Integer.parseInt(dateparameters[0]);
                           }
                               
                           }
                           
                           else if(month.equals("07")||month.equals("08")||month.equals("09")){
                           
                               quarterName="July-September";  
                                 if(dateparameters[0].length()==4)
                           {
                           year=Integer.parseInt(dateparameters[0]);
                           }
                               
                           }
                            else if(month.equals("10")||month.equals("11")||month.equals("12")){
                           
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
                    if(facilityID.length()>0 && !sex.equals("")) {
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
  System.out.println("INSERT >> "+numerator_un);
  
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
         Logger.getLogger(loadTBExcel.class.getName()).log(Level.SEVERE, null, ex);
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
