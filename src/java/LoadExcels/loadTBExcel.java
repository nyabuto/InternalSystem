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
@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100) 

/**
 *
 * @author Geofrey Nyabuto
 */
public class loadTBExcel extends HttpServlet {
 String county_name,county_id, district_name,district_id,hf_name,hf_id;
String full_path="";
 String fileName="";
   int checker_dist,checker_hf;
   File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";
  String nextpage="";
  String quarterName,facilityName,facilityID,id,missingFacility,Numerator,mflcode;
  int year,quarter,Denominator,checker,missing,added,updated;

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
      session=request.getSession();
      dbConn conn = new dbConn();
   nextpage="loadTBExcel.jsp";
String female_stat,male_stat;
String less1, stat_1to4,stat_5to9,stat_10to14,stat_15to19,stat_20above,positive,negative;
 
String art_numerator, art_denominator, art_female;
String art_male,art_less1,art_1to4,art_5to9,art_10to14,art_15to19,art_20above;

 female_stat=male_stat="";
 less1=stat_1to4=stat_5to9=stat_10to14=stat_15to19=stat_20above=positive=negative="";
 
 art_numerator=art_denominator=art_female="";
 art_male=art_less1=art_1to4=art_5to9=art_10to14=art_15to19=art_20above="";

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
			HSSFSheet worksheet = workbook.getSheetAt(0);
			Iterator rowIterator = worksheet.iterator();
                        
                        int i=2,y=0;
			while(rowIterator.hasNext()) {
                            System.out.println(" in while");
			HSSFRow rowi = worksheet.getRow(i);
                        if( rowi==null){
                                nextpage="loadTBExcel.jsp";
                         break;
                        }
                        HSSFCell cellYear = rowi.getCell((short) 0);
			year = (int) cellYear.getNumericCellValue();
			HSSFCell cellQuarter = rowi.getCell((short) 1);
			quarterName = cellQuarter.getStringCellValue();
                        HSSFCell cellFacilityName = rowi.getCell((short) 4);
			facilityName = cellFacilityName.getStringCellValue();
                        HSSFCell cellMFLCODE = rowi.getCell((short) 5);
			mflcode = cellMFLCODE.getStringCellValue();
                        HSSFCell cellNumerator = rowi.getCell((short) 7);
			Numerator = ""+(int)cellNumerator.getNumericCellValue();
                        HSSFCell cellDenominator = rowi.getCell((short) 8);
			Denominator = (int) cellDenominator.getNumericCellValue();

                        HSSFCell cellfemale = rowi.getCell((short) 9);
			female_stat =  ""+(int)cellfemale.getNumericCellValue();
                        
                        HSSFCell cellmale = rowi.getCell((short) 10);
			male_stat = ""+(int)cellmale.getNumericCellValue();
                        
                        HSSFCell cellless1 = rowi.getCell((short) 11);
			less1 = ""+(int)cellless1.getNumericCellValue();
                        
                        HSSFCell cell1to4 = rowi.getCell((short) 12);
			stat_1to4 =  ""+(int)cell1to4.getNumericCellValue();
                        
                        HSSFCell cell5to9 = rowi.getCell((short) 13);
			stat_5to9 =  ""+(int)cell5to9.getNumericCellValue();
                        
                        
                        HSSFCell cell10to14 = rowi.getCell((short) 14);
			stat_10to14 =  ""+(int)cell10to14.getNumericCellValue();
                        
                        HSSFCell cell15to19 = rowi.getCell((short) 15);
			stat_15to19 =  ""+(int)cell15to19.getNumericCellValue();
                        
                         HSSFCell cell20above = rowi.getCell((short) 16);
			stat_20above =  ""+(int)cell20above.getNumericCellValue();
                        
                         HSSFCell cellpositive = rowi.getCell((short) 17);
			positive = ""+(int)cellpositive.getNumericCellValue();
                    
                        
                           HSSFCell cellnegative = rowi.getCell((short) 18);
			negative = ""+(int)cellnegative.getNumericCellValue();
                        
                        
                          HSSFCell cellart_numerator = rowi.getCell((short) 19);
			art_numerator = ""+(int)cellart_numerator.getNumericCellValue();
                        
                          HSSFCell cellart_denominator = rowi.getCell((short) 20);
			art_denominator =  ""+(int)cellart_denominator.getNumericCellValue();
                        
                           HSSFCell cellart_female = rowi.getCell((short) 21);
			art_female = ""+(int)cellart_female.getNumericCellValue();
                        
                           HSSFCell cellart_male = rowi.getCell((short) 22);
			art_male =  ""+(int)cellart_male.getNumericCellValue();
                        
                         HSSFCell cellart_less1 = rowi.getCell((short) 23);
			art_less1 =  ""+(int)cellart_less1.getNumericCellValue();
                        
                           HSSFCell cellart_1to4 = rowi.getCell((short) 24);
			art_1to4 =  ""+(int)cellart_1to4.getNumericCellValue();
                        
                          HSSFCell cellart_5to9 = rowi.getCell((short) 25);
			art_5to9 =  ""+(int)cellart_5to9.getNumericCellValue();
                        
                        HSSFCell cellart_10to14 = rowi.getCell((short) 26);
			art_10to14 = ""+(int)cellart_10to14.getNumericCellValue();
                        
                          HSSFCell cellart_15to19 = rowi.getCell((short) 27);
			art_15to19 =  ""+(int)cellart_15to19.getNumericCellValue();
                        
                         HSSFCell cellart_20above= rowi.getCell((short) 28);
			art_20above = ""+(int)cellart_20above.getNumericCellValue();
//                        
//                        int female_stat,male_stat;
//int less1, stat_1to4,stat_5to9,stat_10to14,stat_15to19,stat_20above,positive,negative;
// 
//int art_numerator, art_denominator, art_female;
//int art_male,art_less1,art_1to4,art_5to9,art_10to14,art_15to19,art_20above;
                        
           facilityID="";
            checker=0;     
           
                   String get_id="SELECT SubPartnerID FROM subpartnera WHERE CentreSanteId=?";
                 conn.pst=conn.conn.prepareStatement(get_id);
                   conn.pst.setString(1,mflcode);
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
                       
                       String checkerExisting="SELECT id FROM tb_stat_art WHERE id='"+id+"'";
                       conn.rs=conn.st.executeQuery(checkerExisting);
                       if(conn.rs.next()==true){
                           checker++;
                       }
//                       
//                       
//                       
                       if(checker==0){
  String inserter="INSERT INTO tb_stat_art (id,SubPartnerID,year,quarter,numerator,denominator,female,male,less1,1to4,5to9,10to14,15to19,20above,positive,negative,art_numerator,art_denominator,art_female,"
                                + "art_male,art_less1,art_1to4,art_5to9,art_10to14,art_15to19,art_20above) "
                         + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, id);
                        conn.pst.setString(2, facilityID);
                        conn.pst.setInt(3, year);
                        conn.pst.setInt(4, quarter);
                        conn.pst.setString(5, Numerator);
                        conn.pst.setInt(6, Denominator);
                        conn.pst.setString(7, female_stat);
                        conn.pst.setString(8, male_stat);
                        conn.pst.setString(9, less1);
                        conn.pst.setString(10, stat_1to4);
                        conn.pst.setString(11, stat_5to9);
                        conn.pst.setString(12, stat_10to14);
                        conn.pst.setString(13, stat_15to19);
                        conn.pst.setString(14, stat_20above);
                        conn.pst.setString(15, positive);
                        conn.pst.setString(16, negative);
                        conn.pst.setString(17, art_numerator);
                        conn.pst.setString(18, art_denominator);
                        conn.pst.setString(19, art_female);
                        conn.pst.setString(20, art_male);
                        conn.pst.setString(21, art_less1);
                        conn.pst.setString(22, art_1to4);
                        conn.pst.setString(23, art_5to9);
                        conn.pst.setString(24, art_10to14);
                        conn.pst.setString(25, art_15to19);
                        conn.pst.setString(26, art_20above);
                       
                        conn.pst.executeUpdate();
                   
                      added++;
                       }
                       else{
        String inserter="UPDATE tb_stat_art SET SubPartnerID=?,year=?,quarter=?,numerator=?,denominator=?, "
                +"female=?,male=?,less1=?,1to4=?,5to9=?,10to14=?,15to19=?,20above=?,positive=?,negative=?,art_numerator=?,art_denominator=?,"
                + "art_female=?,art_male=?,art_less1=?,art_1to4=?,art_5to9=?,art_10to14=?,art_15to19=?,art_20above=?"
                + "WHERE id=?";

                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, facilityID);
                        conn.pst.setInt(2, year);
                        conn.pst.setInt(3, quarter);
                        conn.pst.setString(4, Numerator);
                        conn.pst.setInt(5, Denominator);
                        conn.pst.setString(6, female_stat);
                        conn.pst.setString(7, male_stat);
                        conn.pst.setString(8, less1);
                        conn.pst.setString(9, stat_1to4);
                        conn.pst.setString(10, stat_5to9);
                        conn.pst.setString(11, stat_10to14);
                        conn.pst.setString(12, stat_15to19);
                        conn.pst.setString(13, stat_20above);
                        conn.pst.setString(14, positive);
                        conn.pst.setString(15, negative);
                        conn.pst.setString(16, art_numerator);
                        conn.pst.setString(17, art_denominator);
                        conn.pst.setString(18, art_female);
                        conn.pst.setString(19, art_male);
                        conn.pst.setString(20, art_less1);
                        conn.pst.setString(21, art_1to4);
                        conn.pst.setString(22, art_5to9);
                        conn.pst.setString(23, art_10to14);
                        conn.pst.setString(24, art_15to19);
                        conn.pst.setString(25, art_20above);
                        conn.pst.setString(26, id);
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

        }
         }
         catch (SQLException ex) {
         Logger.getLogger(loadTBExcel.class.getName()).log(Level.SEVERE, null, ex);
     }
    String sessionText=added+ "New data added <> "+updated+" updated facilities<br> and "+missing+" missing facilities";    
         
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
