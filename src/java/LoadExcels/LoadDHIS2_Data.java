/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoadExcels;

import DataMapping.DHIS_IMIS;
import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author GNyabuto
 */
public class LoadDHIS2_Data extends HttpServlet {
    String output;
    HttpSession session;
    String full_path="";
    String fileName="";
    File file_source;
    private static final long serialVersionUID = 205242440643911308L;
    private static final String UPLOAD_DIR = "uploads";
    String[] columns =  {"country","county","subcounty","ward","facility","facility_id","facility_name","mfl_code","orgdescription","new_anc","hv0206","hv0201","hv0218","hv0219","hv0220","hv0237","hv0212","hv0233","hv0234","hv0241","hv0205","hv0242","hv0207","hv0202","hv0235","hv0211","hv0238","hv0239","hv0230","hv0231","hv0229","hv0227","hv0225","hv0224","hv0243","hv0208","hv0203","hv0214","hv0215","hv0216","hv0213","hv0226","hv0232","hv0240","hv0236","hv0228","hv0244","hv0217","hv0209","hv0204","hv0345","hv0334","hv0336","hv0338","hv0335","hv0337","hv0371","hv0372","hv0370","hv0319","hv0302","hv0301","hv0904","therapy_m_less_15","therapy_m_greater_15","isoniazid_preventive_therapy","therapy_f_greater_15","hv0348","hv0346","hv0347","hv0326","hv0905","hv0355","hv0327","hv0313","hv0344","hv0373","hv0333","hv0354","hv0325","hv0339","hv0307","hv0349","hv0320","hv0322","hv0324","hv0321","hv0323","hv0101","hv0102","hv0103","hv0107","hv0106","hv0105","hv0108","hv0109","hv0113","hv0115","hv0111","hv0112","hv0114","hv0110"};
    String SubPartnerID,mfl_code,year,month,yearmonth,id;
    int pepfaryear;
    String query="",value;
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    
        dbConn  conn = new dbConn();
        session = request.getSession();
        
        year = request.getParameter("year");
        String mn = request.getParameter("month");
        
            
        month = getMonth(mn);
        int mois=Integer.parseInt(month);
        if(mois>=10){
            pepfaryear=Integer.parseInt(year)-1;
        }
        else{
            pepfaryear = Integer.parseInt(year);
        }

        yearmonth=pepfaryear+""+month;
        //drop yearmonth data
        
        String dropper = "DELETE FROM dhis_data WHERE yearmonth=?";
        conn.pst = conn.conn.prepareStatement(dropper);
        conn.pst.setString(1, yearmonth);
        conn.pst.executeUpdate();
        
        //end of dropping yearmonth data
         String applicationPath = request.getServletContext().getRealPath("");
         String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
         session=request.getSession();
          File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        
        for (Part part : request.getParts()) {
            if(!getFileName(part).equals("")){
           fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            }
        }
        if(!fileName.endsWith(".xlsx")){
          session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");   
        }
        else{
          full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
 
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
        query=value="";
  FileInputStream fileInputStream = new FileInputStream(full_path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        int j=0;
        int number_sheets = workbook.getNumberOfSheets();
        while(j<number_sheets){
        XSSFSheet worksheet;
        
        worksheet = workbook.getSheetAt(j);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        while(rowIterator.hasNext()){
            query = "REPLACE INTO dhis_data SET ";
             int colmnscounter=0;
        SubPartnerID=mfl_code="";
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;}
        
        
       for (String label : columns){
          
           XSSFCell cell = rowi.getCell((short) colmnscounter);
            if(cell==null){
                break;
            }
            else{
               switch (cell.getCellType()) {
                   case 0:
                       //numeric
                       value =""+(int)cell.getNumericCellValue();
                       break;
                   case 1:
                       value =cell.getStringCellValue();
                       break;
                   default:
                       value = cell.getRawValue();
                       break;
               }
               if(value==null){
          query+=label+"="+value+",";
               }
               else{
                   if(value.contains("'")){
                       value=value.replace("'", "");
                   }
               query+=label+"='"+value+"',";    
               }
            
            }
            if(colmnscounter==7){
                mfl_code = value;
            }
            colmnscounter++;
       } 

        SubPartnerID=getSubPartnerID(conn,mfl_code);    
         id=year+"_"+mois+"_"+SubPartnerID; 
        if(!SubPartnerID.equals("")){
         query+="Annee='"+pepfaryear+"',Mois='"+month+"',yearmonth='"+yearmonth+"',SubPartnerID='"+SubPartnerID+"',id='"+id+"'";
            System.out.println(query);
         conn.st.executeUpdate(query);
        
        }
        else{
          System.out.println("mfl : "+mfl_code+" Facility is missing in our master facility list.");   
        }
            i++;
        }
        
        j++;
        }
          
        }
        
        //GENERATE EXCEL OUTPUT
        
//    DHIS_IMIS imis_dhis_mapping = new DHIS_IMIS();
//      XSSFWorkbook wb =  new XSSFWorkbook();
//      
//      String yearmonths[] = {yearmonth};
//      wb = imis_dhis_mapping.get_data(yearmonths, wb);
//
//  IdGenerator IG = new IdGenerator();
//  String createdOn = IG.CreatedOn();
//
//  ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
//  wb.write(outByteStream);
//  byte[] outArray = outByteStream.toByteArray();
//  response.setContentType("application/ms-excel");
//  response.setContentLength(outArray.length);
//  response.setHeader("Expires:", "0"); // eliminates browser caching
//  response.setHeader("Content-Disposition", "attachment; filename=IMIS_DHIS_Comparison_For_"+pepfaryear+"("+month+")_Created_On_" + createdOn + ".xlsx");
//  OutputStream outStream = response.getOutputStream();
//  outStream.write(outArray);
//  outStream.flush();
//  outStream.close();      
//        
        
        
//   END OF EXCEL OUTPUT     
        
        
        
        response.sendRedirect("UploadDHISData.jsp");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoadDHIS2_Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoadDHIS2_Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
    
    
    public String getMonth(String st_month){
        int mn=Integer.parseInt(st_month);
        String month_1="";
        if(mn<10){
        month_1="0"+mn;    
        }
        else{
            month_1=""+mn;
        }
        
        return month_1;
    }
    
    public String getSubPartnerID(dbConn conn, String code) throws SQLException{
     String subpatID="";
     
    String gett="SELECT SubPartnerID FROM subpartnera WHERE CentreSanteId=?";
    conn.pst=conn.conn.prepareStatement(gett);
    conn.pst.setString(1, code);
    conn.rs=conn.pst.executeQuery();
    if(conn.rs.next()){
        subpatID =conn.rs.getString(1);
    }
     
     return subpatID;
    }
 
}
