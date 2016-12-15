/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class loadPMTCT_FO_OLD extends HttpServlet {
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
                        
                        int i=2,y=0;
			while(rowIterator.hasNext()) {
			HSSFRow rowi = worksheet.getRow(i);
                        if( rowi==null){
                                nextpage="loadExcel.jsp";
                         break;
                        }
                        HSSFCell cellYear = rowi.getCell((short) 0);
			year = (int) cellYear.getNumericCellValue();
			HSSFCell cellQuarter = rowi.getCell((short) 1);
			quarterName = cellQuarter.getStringCellValue();
                        HSSFCell cellFacilityName = rowi.getCell((short) 4);
			facilityName = cellFacilityName.getStringCellValue();
                        HSSFCell cellMFLCODE = rowi.getCell((short) 5);
			mflcode = Integer.parseInt(cellMFLCODE.getStringCellValue());
                        HSSFCell cellNumerator = rowi.getCell((short) 7);
			Numerator = (int) cellNumerator.getNumericCellValue();
                        HSSFCell cellDenominator = rowi.getCell((short) 8);
			Denominator = (int) cellDenominator.getNumericCellValue();
                    
                        //linked_art
                        //not_linked_art
                        //unknown_link
                        //not_breastfeeding
                        //breastfeeding	
                        //breastfeeding_unknown	
                        //care_no_test	
                         //ltfu	
                         //died	
                         //transferred_out

                        
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
//                       
                       if(checker==0){
                        String inserter="INSERT INTO pmtct_fo (id,SubPartnerID,year,quarter,numerator,denominator) "
                         + "VALUES(?,?,?,?,?,?)";
                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, id);
                        conn.pst.setString(2, facilityID);
                        conn.pst.setInt(3, year);
                        conn.pst.setInt(4, quarter);
                        conn.pst.setInt(5, Numerator);
                        conn.pst.setInt(6, Denominator);
                        conn.pst.executeUpdate();
                   
                      added++;
                       }
                       else{
        String inserter="UPDATE pmtct_fo SET SubPartnerID=?,year=?,quarter=?,numerator=?,denominator=? WHERE id=?";

                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, facilityID);
                        conn.pst.setInt(2, year);
                        conn.pst.setInt(3, quarter);
                        conn.pst.setInt(4, Numerator);
                        conn.pst.setInt(5, Denominator);
                        conn.pst.setString(6, id);
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
