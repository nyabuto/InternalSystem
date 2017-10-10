/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T1FORM;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 *
 * @author GNyabuto
 */
public class DatimReport extends HttpServlet {
HttpSession session;
String start_date,end_date;
      String  id,program_area,area,cordinator,district_ids,agency,venue,curriculum_id,s_start_date,s_end_date,training_name,year,month,s_male,s_female,d_male,d_female,yearmonth,duration;
      String districts,curriculums;
      int pos=0,row_pos;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session = request.getSession();
        dbConn conn = new dbConn();
        
    HSSFWorkbook wb=new HSSFWorkbook(); 
    HSSFSheet shet=wb.createSheet("Trainings Report"); 
  
    HSSFFont font=wb.createFont();
    font.setFontHeightInPoints((short)18);
    font.setFontName("Cambria");
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     HSSFFont font2=wb.createFont();
    font2.setFontName("Cambria");
    font2.setColor((short)0000);
    CellStyle style2=wb.createCellStyle();
    style2.setFont(font2);
   
   HSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    stborder.setWrapText(true);
    
    // for the red color
   HSSFCellStyle redstyle = wb.createCellStyle();
    redstyle.setFillForegroundColor(HSSFColor.RED.index);
    redstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    redstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
    redstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    redstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    redstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
    redstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    redstyle.setWrapText(true);
    
      HSSFCellStyle styleHeader = wb.createCellStyle();
    styleHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
    styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    styleHeader.setWrapText(true);
    
    HSSFCellStyle styleminiHeader = wb.createCellStyle();
    styleminiHeader.setFillForegroundColor(HSSFColor.ORCHID.index);
    styleminiHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    styleminiHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    styleminiHeader.setWrapText(true);
    
HSSFFont fontHeader = wb.createFont();
fontHeader.setColor(HSSFColor.BLACK.index);
fontHeader.setFontName("Cambria");
fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
styleHeader.setFont(fontHeader);
styleHeader.setWrapText(true);

String titles[] = {"Year","Month","Region","District","Program Area","Start Date","End Date","Training Title","Training Duration","Number Trained","Male","Female","Budgeted","Actual in Dollars","Cadre","Curriculum"};
 for(int i=0;i<=15;i++){
   shet.setColumnWidth(i, 4000);     
    }
 row_pos=0;
  HSSFRow  row;
        row = shet.createRow(row_pos);
        row.setHeightInPoints(30);
  HSSFCell c11;
  
  int col_num=0;
      for (String value:titles){
         c11=row.createCell(col_num);
         if(isNumeric(value)){
                c11.setCellValue(Integer.parseInt(value));
            }
            else{
                c11.setCellValue(value);
            }
         c11.setCellStyle(styleHeader);   
          
      col_num++;    
      }
  
       String date_range = request.getParameter("date_range").replace(" ", ""); 
       if(date_range.equals("")){
       start_date = "2010-01-01";
       end_date = "2019-10-01";
       }
       else{
           String[] date_range_array=date_range.split("-");
           start_date=date_range_array[0].replace("/", "-");
           end_date=date_range_array[1].replace("/", "-");
       }
       
       
       String getSummary = "select t1_summary.id AS id, t1_program_area.name AS program_area, t1_areas.name AS area, cordinator,districts,agency,venue,curriculum_id,start_date,end_date,training_name,"
               + "year,month,s_male,s_female,d_male,d_female,ym, DATEDIFF(end_date,start_date)+1 AS duration "
               + "FROM t1_summary "
               + "LEFT JOIN t1_program_area ON t1_summary.program_area_id=t1_program_area.id "
               + "LEFT JOIN t1_areas ON t1_program_area.area=t1_areas.id "
               + "WHERE (end_date BETWEEN ? AND ? ) or (start_date BETWEEN ? AND ? )";
        System.out.println(getSummary);
       conn.pst = conn.conn.prepareStatement(getSummary);
      conn.pst.setString(1, start_date);
      conn.pst.setString(2, end_date);
      conn.pst.setString(3, start_date);
      conn.pst.setString(4, end_date);
      
      conn.rs = conn.pst.executeQuery();
      while(conn.rs.next()){
          System.out.println("called for output: ");
      pos++;
      row_pos++;
      id = conn.rs.getString(1);
      program_area = conn.rs.getString(2);
      area = conn.rs.getString(3);
      cordinator = conn.rs.getString(4);
      district_ids = conn.rs.getString(5);
      agency = conn.rs.getString(6);
      venue = conn.rs.getString(7);
      curriculum_id = conn.rs.getString(8);
      s_start_date = conn.rs.getString(9);
      s_end_date = conn.rs.getString(10);
      training_name = conn.rs.getString(11);
      year = conn.rs.getString(12);
      month = conn.rs.getString(13);
      s_male = conn.rs.getString(14);
      s_female = conn.rs.getString(15);
      d_male = conn.rs.getString(16);
      d_female = conn.rs.getString(17);
      yearmonth = conn.rs.getString(18); 
      duration = conn.rs.getString(19); 
      districts = getdistricts(conn, district_ids);
      curriculums = getCurriculum(conn, curriculum_id);
      int total = Integer.parseInt(s_male)+Integer.parseInt(s_female);
      //array ooutput
      
      
      String data[] = {year,month,"",districts,program_area,s_start_date,s_end_date,training_name,duration,""+total,s_male,s_female,"","","",curriculums};
      
      row = shet.createRow(row_pos);
      col_num=0;
      for (String value:data){
      
           c11=row.createCell(col_num);
           if(isNumeric(value)){
                c11.setCellValue(Integer.parseInt(value));
            }
            else{
                c11.setCellValue(value);
            }
         c11.setCellStyle(stborder);   
           
      col_num++;    
      }
      
      }
      
      
      IdGenerator IG = new IdGenerator();
      String createdOn = IG.CreatedOn();
      ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte [] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=ComprehensiveReport_"+createdOn+".xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
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
        Logger.getLogger(DatimReport.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(DatimReport.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getdistricts(dbConn conn,String ids) throws SQLException{
     String dists="" ;
     int has_one=0;
     String dist_q=" WHERE (";
     for(String district_id : ids.replace("*", "-").split("-")){
         if(!district_id.equals("")){
       dist_q+=" DistrictID="+district_id+" OR"; 
       has_one++;
     }
     }
     
     dist_q=dist_q.substring(0, dist_q.length()-2);
     dist_q+=")";
     if(has_one==0){
        dist_q="";
     }
     has_one=0;
     String getDistricts="SELECT DistrictID AS id,DistrictNom AS name FROM district "+dist_q+" ORDER BY name";
        System.out.println(getDistricts);
     conn.rs2=conn.st2.executeQuery(getDistricts);
     while(conn.rs2.next()){
             String district_name=conn.rs2.getString("name");
           dists+=district_name+","; 
           has_one++;
         }
     if(has_one>0){
         System.out.println("districts:"+dists);
     dists=dists.substring(0, dists.length()-1);
     }
     return dists;
    }
    
    public String getCurriculum(dbConn conn,String ids) throws SQLException{
     String currs="" ;
     int has_one=0;
     String curr_q=" WHERE  (";
     for(String curriculum_id : ids.replace("*", "-").split("-")){
         if(!curriculum_id.equals("")){
       curr_q+=" id="+curriculum_id+" OR"; 
       has_one++;
     }
     }
     
     curr_q=curr_q.substring(0, curr_q.length()-2);
     
     curr_q+=")";
     if(has_one==0){
      curr_q="";   
     }
     has_one=0;
     String getCurr="SELECT id,name from t1_curriculum "+curr_q+" ORDER BY name";
        System.out.println(getCurr);
     conn.rs2=conn.st2.executeQuery(getCurr);
     while(conn.rs2.next()){
             String curriculum_name=conn.rs2.getString("name");
           currs+=curriculum_name+","; 
           has_one++;
         }
     if(has_one>0){
     currs=currs.substring(0, currs.length()-1);
     }
     return currs;
    }
    
    public boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
    }
}
