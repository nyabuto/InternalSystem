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
import scripts.AddLastMonth;
@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100) 

/**
 *
 * @author Geofrey Nyabuto
 */
public class loadPMTCT_FO extends HttpServlet {
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
  int year,quarter,mflcode,Numerator,Denominator,checker,missing,added,updated;

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            year=quarter=mflcode=Numerator=Denominator=checker=missing=added=updated=0; 
           
             String linked_art = "0";
             String not_linked_art = "0";
             String unknown_link = "0";
             String not_breastfeeding = "0";
             String breastfeeding = "0";
             String breastfeeding_unknown = "0";
             String care_no_test = "0";
             String ltfu = "0";
             String died = "0";
             String transferred_out = "0";
             
             
      session=request.getSession();
      dbConn conn = new dbConn();
   nextpage="loadExcel.jsp";
   
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
         nextpage="loadExcel.jsp"; 
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose the correct File.</font>");   
        }
        else{
            
        
 full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
 
 System.out.println("the saved file directory is  :  "+full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
 
  FileInputStream fileInputStream = new FileInputStream(full_path);
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet("PMTCT-FO");
			Iterator rowIterator = worksheet.iterator();
                        
                        int i=1,y=0;
			while(rowIterator.hasNext()) {
			HSSFRow rowi = worksheet.getRow(i);
                        if( rowi==null){
                                nextpage="loadExcel.jsp";
                         break;
                        }
                        //Year	Quarter	County	Sub County	Health Facility	MFL Code	Type of support	Numerator	Denominator	HIV-infected:Linked to ART	HIV-infected: Not linked to ART	HIV-infected : Unknown link	HIV-uninfected:Not beastfeeding	HIV-uninfected: Still breastfeeeding	HIV-uninfected:Breastfeeding unknown	Other outcomes: In care but not test done	 Other outcomes:Lost to follow up	Other outcomes : Died	Other outcomes:Transferred out

                        int rowcount=0;
                        HSSFCell cellYear = rowi.getCell((short) rowcount); rowcount++;
			year = (int) cellYear.getNumericCellValue();
			HSSFCell cellQuarter = rowi.getCell((short) rowcount); rowcount=rowcount+3;
			quarterName = cellQuarter.getStringCellValue();
                        HSSFCell cellFacilityName = rowi.getCell((short)  rowcount);
			facilityName = cellFacilityName.getStringCellValue();rowcount++;
                        //HSSFCell cellMFLCODE = rowi.getCell((short)  rowcount); 
			//mflcode = Integer.parseInt(cellMFLCODE.getStringCellValue());
                        
                         if(1==1){
                            
                        HSSFCell cellrow = rowi.getCell((short)  rowcount); rowcount=rowcount+2;
                         if(cellrow.getCellType()==0){                             //numeric
			
                        mflcode = (int)cellrow.getNumericCellValue();
                         } 
                         else if(cellrow.getCellType()==1){
			
                        mflcode = new Integer(cellrow.getStringCellValue());
                        
                         }
                                }
                        
                        HSSFCell cellNumerator = rowi.getCell((short)  rowcount); rowcount++;
			Numerator = (int) cellNumerator.getNumericCellValue();
                        HSSFCell cellDenominator = rowi.getCell((short)  rowcount); rowcount++;
			Denominator = (int) cellDenominator.getNumericCellValue();
                    
                        //linked_art
                        if(1==1){
                            
                        HSSFCell cellrow = rowi.getCell((short)  rowcount); rowcount++;
                         if(cellrow.getCellType()==0){                             //numeric
			
                        linked_art = ""+(int)cellrow.getNumericCellValue();
                         } else if(cellrow.getCellType()==1){
			
                        linked_art = cellrow.getStringCellValue();
                         }
                                }
                        
                        
                        
                        //not_linked_art
                        
                        if(1==1){
                            
                        HSSFCell cellrow = rowi.getCell((short)  rowcount); rowcount++;
                         if(cellrow.getCellType()==0){                             //numeric
			
                        not_linked_art = ""+(int)cellrow.getNumericCellValue();
                         } else if(cellrow.getCellType()==1){
			
                        not_linked_art = cellrow.getStringCellValue();
                         }
                                }
                        
                        
                        
                        //unknown_link
                        
                        if(1==1){
                            
                        HSSFCell cellrow = rowi.getCell((short)  rowcount); rowcount++;
                         if(cellrow.getCellType()==0){                             //numeric
			
                        unknown_link = ""+(int)cellrow.getNumericCellValue();
                         } else if(cellrow.getCellType()==1){
			
                        unknown_link = cellrow.getStringCellValue();
                         }
                                }
                        //
                        
                        
                        //not_breastfeeding
                        
                        if(1==1){
                            
                        HSSFCell cellrow = rowi.getCell((short)  rowcount); rowcount++;
                         if(cellrow.getCellType()==0){                             //numeric
			
                        not_breastfeeding = ""+(int)cellrow.getNumericCellValue();
                         } else if(cellrow.getCellType()==1){
			
                        not_breastfeeding = cellrow.getStringCellValue();
                         }
                                }
                        
                        //breastfeeding
                        
                        if(1==1){
                            
                        HSSFCell cellrow = rowi.getCell((short)  rowcount); rowcount++;
                         if(cellrow.getCellType()==0){                             //numeric
			
                        breastfeeding = ""+(int)cellrow.getNumericCellValue();
                         } else if(cellrow.getCellType()==1){
			
                        breastfeeding = cellrow.getStringCellValue();
                         }
                                }
                        
                        //breastfeeding_unknown
                        
                        if(1==1){
                            
                        HSSFCell cellrow = rowi.getCell((short)  rowcount); rowcount++;
                         if(cellrow.getCellType()==0){                             //numeric
			
                        breastfeeding_unknown = ""+(int)cellrow.getNumericCellValue();
                         } else if(cellrow.getCellType()==1){
			
                        breastfeeding_unknown = cellrow.getStringCellValue();
                         }
                                }
                        //care_no_test
                        
                         if(1==1){
                            
                        HSSFCell cellrow = rowi.getCell((short)  rowcount); rowcount++;
                         if(cellrow.getCellType()==0){                             //numeric
			
                        care_no_test = ""+(int)cellrow.getNumericCellValue();
                         } else if(cellrow.getCellType()==1){
			
                        care_no_test = cellrow.getStringCellValue();
                         }
                                }
                        
                         //ltfu	
                         
                         if(1==1){
                            
                        HSSFCell cellrow = rowi.getCell((short)  rowcount); rowcount++;
                         if(cellrow.getCellType()==0){                             //numeric
			
                        ltfu = ""+(int)cellrow.getNumericCellValue();
                         } else if(cellrow.getCellType()==1){
			
                        ltfu = cellrow.getStringCellValue();
                         }
                                }
                         
                         //died	
                         
                         if(1==1){
                            
                        HSSFCell cellrow = rowi.getCell((short)  rowcount); rowcount++;
                         if(cellrow.getCellType()==0){                             //numeric
			
                        died = ""+(int)cellrow.getNumericCellValue();
                         } else if(cellrow.getCellType()==1){
			
                        died = cellrow.getStringCellValue();
                         }
                                }
                         
                         //transferred_out
                        
                        if(1==1){
                            
                        HSSFCell cellrow = rowi.getCell((short)  rowcount); rowcount++;
                         if(cellrow.getCellType()==0){                             //numeric
			
                        transferred_out = ""+(int)cellrow.getNumericCellValue();
                         } else if(cellrow.getCellType()==1){
			
                        transferred_out = cellrow.getStringCellValue();
                         }
                                } 
                         
                        
           facilityID="";
            checker=0;     
           
                   String get_id="SELECT SubPartnerID FROM subpartnera WHERE CentreSanteId=?";
                 conn.pst=conn.conn.prepareStatement(get_id);
                   conn.pst.setInt(1,mflcode);
                 conn.rs=conn.pst.executeQuery();
                   if(conn.rs.next()==true){
                       facilityID=conn.rs.getString(1);
                   }
                    if(facilityID.length()>0) {
//                        DISTRICT FOUND ADD THE HF TO THE SYSTEM.........................
                        
                        String getQuarterID="SELECT id FROM quarter WHERE pmtct_fo_name=?";
                       conn.pst=conn.conn.prepareStatement(getQuarterID);
                       conn.pst.setString(1, quarterName);
                       conn.rs=conn.pst.executeQuery();
                       if(conn.rs.next()==true){
                        quarter=conn.rs.getInt(1);
                       }
                       checker=0;
//                        CHECK IF ALREADY ADDED TO PMTCT_FO TABLE
                       id=year+"_"+quarter+"_"+facilityID; 
//                   System.out.println("to add data : "+facilityName+" id : "+facilityID+"mfl code "+mflcode+" year : "+year+" quarter : "+quarter+" numerator : "+Numerator+" denominator : "+Denominator);
                       
                       String checkerExisting="SELECT id FROM pmtct_fo WHERE id='"+id+"'";
                       conn.rs=conn.st.executeQuery(checkerExisting);
                       if(conn.rs.next()==true){
                           checker++;
                       }
//                       
//                       
//                    //,,,,,,,,,,
   
                       if(checker==0){
                        String inserter="INSERT INTO pmtct_fo (id,SubPartnerID,year,quarter,numerator,denominator,linked_art,not_linked_art,unknown_link,not_breastfeeding,breastfeeding,breastfeeding_unknown,care_no_test,ltfu,died,transferred_out) "
                         + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, id);
                        conn.pst.setString(2, facilityID);
                        conn.pst.setInt(3, year);
                        conn.pst.setInt(4, quarter);
                        conn.pst.setInt(5, Numerator);
                        conn.pst.setInt(6, Denominator);
                        conn.pst.setString(7,linked_art );
                        conn.pst.setString(8,not_linked_art );
                        conn.pst.setString(9, unknown_link);
                        conn.pst.setString(10, not_breastfeeding);
                        conn.pst.setString(11,breastfeeding );
                        conn.pst.setString(12,breastfeeding_unknown );
                        conn.pst.setString(13, care_no_test);
                        conn.pst.setString(14, ltfu);
                        conn.pst.setString(15, died);
                        conn.pst.setString(16, transferred_out);
                        conn.pst.executeUpdate();
                   
                      added++;
                       }
                       else{
        String inserter="UPDATE pmtct_fo SET SubPartnerID=?,year=?,quarter=?,numerator=?,denominator=? ,linked_art=? ,not_linked_art = ? ,unknown_link = ? ,not_breastfeeding = ? ,breastfeeding = ? ,breastfeeding_unknown = ? ,care_no_test = ? ,ltfu = ? ,died = ? ,	transferred_out = ?  WHERE id=?";

                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, facilityID);
                        conn.pst.setInt(2, year);
                        conn.pst.setInt(3, quarter);
                        conn.pst.setInt(4, Numerator);
                        conn.pst.setInt(5, Denominator);
                        conn.pst.setString(6, linked_art);
                        conn.pst.setString(7, not_linked_art);
                        conn.pst.setString(8, unknown_link);
                        conn.pst.setString(9, not_breastfeeding);
                        conn.pst.setString(10, breastfeeding);
                        conn.pst.setString(11, breastfeeding_unknown);
                        conn.pst.setString(12, care_no_test);
                        conn.pst.setString(13, ltfu);
                        conn.pst.setString(14, died);
                        conn.pst.setString(15, transferred_out);
                        conn.pst.setString(16, id);
                        conn.pst.executeUpdate();
                       
                        
                       
                        
                        
                     updated++;
                       }
    
                    }
                    
                    else{
                       missing++; 
//                        missing facilities
                     missingFacility+="facility name : "+facilityName+" mfl code : "+mflcode+" excel row num : "+i+"<br>"; 
                        System.out.println(facilityName+ "facility is missing mflcode :"+mflcode);
                    }
                    i++;
                        }
                         //a code to loop through all synced records without a last month
            //the affected tables are "eid_datim","viral_load","pmtct_fo","tb_stat_art"
            AddLastMonth am= new AddLastMonth();
            am.addfirstmonth();
            //end of sync last month

        }
         }
         catch (SQLException ex) {
         Logger.getLogger(loadPMTCT_FO.class.getName()).log(Level.SEVERE, null, ex);
     }
    String sessionText="Data for  <b>"+added+ "</b> sites newly added. <br/> Data for <b>"+updated+"</b> updated. <br/>Data for <b>"+missing+"</b> sites skipped because they are missing in IMIS";    
     session.setAttribute("pmtctresponse", sessionText);
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
}
