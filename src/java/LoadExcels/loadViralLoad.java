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
public class loadViralLoad extends HttpServlet {
 String county_name,county_id, district_name,district_id,hf_name,hf_id;
String full_path="";
 String fileName="";
   int checker_dist,checker_hf;
   File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";
  String nextpage="";
  String quarterName,facilityName,facilityID,id,missingFacility,mflcode;
  int year,quarter,checker,missing,added,updated;

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
      session=request.getSession();
      dbConn conn = new dbConn();
   nextpage="loadTBExcel.jsp";
   
   
   
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
                                nextpage="loadViralLoad.jsp";
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
                        
                         HSSFCell cellstype = rowi.getCell((short) 6);
			String supporttype = cellstype.getStringCellValue();
                        
                        HSSFCell cellNumerator = rowi.getCell((short) 7);
			numerator_un = ""+(int)cellNumerator.getNumericCellValue();
                        
                        HSSFCell cellDenominator = rowi.getCell((short) 8);
			denominator_un = ""+(int) cellDenominator.getNumericCellValue();

                        HSSFCell less1funcell = rowi.getCell((short) 9);
			fun_less1 =  ""+(int)less1funcell.getNumericCellValue();
                        
                        HSSFCell cl10 = rowi.getCell((short) 10);
			fun_1to4 = ""+(int)cl10.getNumericCellValue();
                        
                        HSSFCell cl11 = rowi.getCell((short) 11);
			fun_5to14 = ""+(int)cl11.getNumericCellValue();
                        
                        HSSFCell cl12 = rowi.getCell((short) 12);
			fun_15to19 =  ""+(int)cl12.getNumericCellValue();
                        
                        HSSFCell cl13 = rowi.getCell((short) 13);
			fun_20 =  ""+(int)cl13.getNumericCellValue();
                        
                        
                        HSSFCell cl14 = rowi.getCell((short) 14);
			mun_less1 =  ""+(int)cl14.getNumericCellValue();
                        
                        HSSFCell cl15 = rowi.getCell((short) 15);
			mun_1to4 =  ""+(int)cl15.getNumericCellValue();
                        
                         HSSFCell cl16 = rowi.getCell((short) 16);
			mun_5to14 =  ""+(int)cl16.getNumericCellValue();
                        
                         HSSFCell cl17 = rowi.getCell((short) 17);
			mun_15to19 = ""+(int)cl17.getNumericCellValue();
                    
                        
                           HSSFCell cl18 = rowi.getCell((short) 18);
			mun_20 = ""+(int)cl18.getNumericCellValue();
                        
                        
                        HSSFCell cl19 = rowi.getCell((short) 19);
			subtotal_un = ""+(int)cl19.getNumericCellValue();
                        
                        
                        
                        HSSFCell cl20 = rowi.getCell((short) 20);
			numerator_vi =  ""+(int)cl20.getNumericCellValue();
                        
                           HSSFCell cl21 = rowi.getCell((short) 21);
			denominator_vi = ""+(int)cl21.getNumericCellValue();
                        
                           HSSFCell cl22 = rowi.getCell((short) 22);
			fvi_less1 =  ""+(int)cl22.getNumericCellValue();
                        
                         HSSFCell cl23 = rowi.getCell((short) 23);
			fvi_1to4 =  ""+(int)cl23.getNumericCellValue();
                        
                           HSSFCell cl24 = rowi.getCell((short) 24);
			fvi_5to14 =  ""+(int)cl24.getNumericCellValue();
                        
                          HSSFCell cl25 = rowi.getCell((short) 25);
			fvi_15to19 =  ""+(int)cl25.getNumericCellValue();
                        
                        HSSFCell cl26 = rowi.getCell((short) 26);
			fvi_20 = ""+(int)cl26.getNumericCellValue();
                        
                          HSSFCell cl27 = rowi.getCell((short) 27);
			mvi_less1 =  ""+(int)cl27.getNumericCellValue();
                        
                         HSSFCell cl28= rowi.getCell((short) 28);
			mvi_5to14 = ""+(int)cl28.getNumericCellValue();
                        
                         HSSFCell cl29= rowi.getCell((short) 29);
			mvi_15to19= ""+(int)cl29.getNumericCellValue();
//                        
                         HSSFCell cl30= rowi.getCell((short) 30);
			mvi_20 = ""+(int)cl30.getNumericCellValue();
                        
                        
                        HSSFCell cl31= rowi.getCell((short) 31);
			subtotal_vi = ""+(int)cl31.getNumericCellValue();
                        
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
                       
                       String checkerExisting="SELECT id FROM viral_load WHERE id='"+id+"'";
                       conn.rs=conn.st.executeQuery(checkerExisting);
                       if(conn.rs.next()==true){
                           checker++;
                       }
//                       
//                       
//                       
                       if(checker==0){
  String inserter="INSERT INTO viral_load (id,SubPartnerID,year,quarter,numerator_un ,denominator_un,fun_less1,fun_1to4,fun_5to14,fun_15to19,fun_20,mun_less1,mun_1to4,mun_5to14,mun_15to19,mun_20,subtotal_un,numerator_vi,denominator_vi,fvi_less1,fvi_1to4 ,fvi_5to14,fvi_15to19,fvi_20,mvi_less1,mvi_1to4,mvi_5to14,mvi_15to19,mvi_20 ,subtotal_vi,supporttype) "
                         + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, id);
                        conn.pst.setString(2, facilityID);
                        conn.pst.setInt(3, year);
                        conn.pst.setInt(4, quarter);
                        conn.pst.setString(5, numerator_un);
                        conn.pst.setString(6, denominator_un);
                        conn.pst.setString(7, fun_less1);
                        conn.pst.setString(8, fun_1to4);
                        conn.pst.setString(9, fun_5to14);
                        conn.pst.setString(10, fun_15to19);
                        conn.pst.setString(11, fun_20);
                        conn.pst.setString(12, mun_less1);
                        conn.pst.setString(13, mun_1to4);
                        conn.pst.setString(14, mun_5to14);
                        conn.pst.setString(15, mun_15to19);
                        conn.pst.setString(16, mun_20);
                        conn.pst.setString(17, subtotal_un);
                        conn.pst.setString(18, numerator_vi);
                        conn.pst.setString(19, denominator_vi);
                        conn.pst.setString(20, fvi_less1);
                        conn.pst.setString(21, fvi_1to4);
                        conn.pst.setString(22, fvi_5to14);
                        conn.pst.setString(23, fvi_15to19);
                        conn.pst.setString(24, fvi_20);
                        conn.pst.setString(25, mvi_less1);
                        conn.pst.setString(26, mvi_1to4);
                        conn.pst.setString(27, mvi_5to14);
                        conn.pst.setString(28, mvi_15to19);
                        conn.pst.setString(29, mvi_20);
                        conn.pst.setString(30, subtotal_vi);
                        conn.pst.setString(31, supporttype);
                        conn.pst.executeUpdate();
                   
                      added++;
                       }
                       else{
        String inserter="UPDATE viral_load SET SubPartnerID=?,year=?,quarter=?,numerator_un =?,denominator_un=?,fun_less1=?,fun_1to4=?,fun_5to14=?,fun_15to19=?,fun_20=?,mun_less1=?,mun_1to4=?,mun_5to14=?,mun_15to19=?,mun_20=?,subtotal_un=?,numerator_vi=?,denominator_vi=?,fvi_less1=?,fvi_1to4=? ,fvi_5to14=?,fvi_15to19=?,fvi_20=?,mvi_less1=?,mvi_1to4=?,mvi_5to14=?,mvi_15to19=?,mvi_20=?,subtotal_vi=?,supporttype=?"
                + "WHERE id=?";

                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, facilityID);
                        conn.pst.setInt(2, year);
                        conn.pst.setInt(3, quarter);
                        conn.pst.setString(4, numerator_un);
                        conn.pst.setString(5, denominator_un);
                        conn.pst.setString(6, fun_less1);
                        conn.pst.setString(7, fun_1to4);
                        conn.pst.setString(8, fun_5to14);
                        conn.pst.setString(9, fun_15to19);
                        conn.pst.setString(10, fun_20);
                        conn.pst.setString(11, mun_less1);
                        conn.pst.setString(12, mun_1to4);
                        conn.pst.setString(13, mun_5to14);
                        conn.pst.setString(14, mun_15to19);
                        conn.pst.setString(15, mun_20);
                        conn.pst.setString(16, subtotal_un);
                        conn.pst.setString(17, numerator_vi);
                        conn.pst.setString(18, denominator_vi);
                        conn.pst.setString(19, fvi_less1);
                        conn.pst.setString(20, fvi_1to4);
                        conn.pst.setString(21, fvi_5to14);
                        conn.pst.setString(22, fvi_15to19);
                        conn.pst.setString(23, fvi_20);
                        conn.pst.setString(24, mvi_less1);
                        conn.pst.setString(25, mvi_1to4);
                        conn.pst.setString(26, mvi_5to14);
                        conn.pst.setString(27, mvi_15to19);
                        conn.pst.setString(28, mvi_20);
                        conn.pst.setString(29, subtotal_vi);
                        conn.pst.setString(30, supporttype);
                        conn.pst.setString(31, id);
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
