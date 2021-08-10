/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VL;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Administrator
 */
public class getVlNonStandardOutput extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      
        
             XSSFWorkbook wb = new XSSFWorkbook();
        try {
            /* TODO output your page here. You may use following sample code. */
           
          
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
   

        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("Daytona");
        font.setColor((short) 0000);
        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        XSSFFont font2 = wb.createFont();
        font2.setFontName("Daytona");
        font2.setColor((short) 0000);
        font2.setFontHeightInPoints((short) 11);
        CellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);
        style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style2.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        style2.setBottomBorderColor(HSSFColor.GREEN.index);
        style2.setTopBorderColor(HSSFColor.GREEN.index);
        style2.setLeftBorderColor(HSSFColor.GREEN.index);
        style2.setRightBorderColor(HSSFColor.GREEN.index);
        

        XSSFCellStyle stborder = wb.createCellStyle();
        stborder.setBorderTop(XSSFCellStyle.BORDER_THIN);
        stborder.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        stborder.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        stborder.setBorderRight(XSSFCellStyle.BORDER_THIN);
        stborder.setBottomBorderColor(HSSFColor.GREEN.index);
        stborder.setTopBorderColor(HSSFColor.GREEN.index);
        stborder.setLeftBorderColor(HSSFColor.GREEN.index);
        stborder.setRightBorderColor(HSSFColor.GREEN.index);
        stborder.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        XSSFCellStyle stylex = wb.createCellStyle();
        stylex.setFillForegroundColor(HSSFColor.GREEN.index);
        stylex.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        stylex.setBorderTop(XSSFCellStyle.BORDER_THIN);
        stylex.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        stylex.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        stylex.setBorderRight(XSSFCellStyle.BORDER_THIN);
        stylex.setBottomBorderColor(HSSFColor.GREEN.index);
        stylex.setTopBorderColor(HSSFColor.GREEN.index);
        stylex.setLeftBorderColor(HSSFColor.GREEN.index);
        stylex.setRightBorderColor(HSSFColor.GREEN.index);
        
        stylex.setAlignment(XSSFCellStyle.ALIGN_LEFT);

        XSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREEN.index);
        stylesum.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(XSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(XSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        
        stylesum.setBottomBorderColor(HSSFColor.GREEN.index);
        stylesum.setTopBorderColor(HSSFColor.GREEN.index);
        stylesum.setLeftBorderColor(HSSFColor.GREEN.index);
        stylesum.setRightBorderColor(HSSFColor.GREEN.index);

        XSSFFont fontx = wb.createFont();
        fontx.setColor(HSSFColor.BLACK.index);
        fontx.setFontName("Daytona");
        
        XSSFFont fontwhite = wb.createFont();
        fontwhite.setColor(HSSFColor.WHITE.index);
        fontwhite.setFontName("Daytona");
        fontwhite.setFontHeightInPoints((short)9);
                
        stylex.setFont(fontwhite);
        stylex.setWrapText(true);

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);
        
        HashMap<Integer , String> sps= new HashMap<Integer, String>();
        
            
            String spname="";
            String mf="";
            if(request.getParameter("sp")!=null){
            spname=request.getParameter("sp");
                                                }
            if(request.getParameter("mf")!=null){
            mf=request.getParameter("mf");
                                                }
            
        
        dbConn conn = new dbConn();
         
        String sp="call "+spname+"('"+mf+"');";
            System.out.println(sp);
       conn.rs= conn.st.executeQuery(sp);
        
         sps.put(1, "VL_DATA_CLEAN@"+spname);
        
        
         for(int sheetno=1;sheetno <= sps.size();sheetno++)
               {
        //for(HSSFSheet shet:Sheetnames){
        
        XSSFSheet shet = wb.createSheet(sps.get(sheetno).split("@")[0]);
        
        XSSFRow rw0=shet.createRow(1);
        XSSFCell cell = rw0.createCell(0);
                    cell.setCellValue(shet.getSheetName());
                    cell.setCellStyle(style);
        shet.addMergedRegion(new CellRangeAddress(1, 1, 0,10));
                    
                int count1  = 3;
        
              //shet.getSheetName();
              
                String storedprocedure="";
                
     
                
               
        
         ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();

         metaData = conn.rs.getMetaData();
         columnCount = metaData.getColumnCount();
        int count = count1;
        ArrayList mycolumns = new ArrayList();

        while (conn.rs.next()) {

            if (count == (count1)) {
//header rows
                XSSFRow rw = shet.createRow(count);
rw.setHeightInPoints(32);
                for (int i = 1; i <= columnCount; i++) {

                    mycolumns.add(metaData.getColumnLabel(i));
                    XSSFCell cell0 = rw.createCell(i - 1);
                    cell0.setCellValue(metaData.getColumnLabel(i));
                    cell0.setCellStyle(stylex);

                    //create row header
                }//end of for loop
                count++;
            }//end of if
            
            
            //data rows     
            XSSFRow rw = shet.createRow(count);

            for (int a = 0; a < columnCount; a++) {
                //System.out.print(mycolumns.get(a) + ":" + conn.rs.getString("" + mycolumns.get(a)));

                XSSFCell cell0 = rw.createCell(a);
                 if(isNumeric(conn.rs.getString("" + mycolumns.get(a)))){
               // if(1==1){
                
                     cell0.setCellValue(conn.rs.getDouble(mycolumns.get(a).toString()));
                    
                   }
                else 
                {
                     cell0.setCellValue(conn.rs.getString("" + mycolumns.get(a)));
                    //cell0.setCellValue(conn.rs.getString("" + mycolumns.get(a)));
                   
                }
            
                cell0.setCellStyle(style2);

            }

            // System.out.println("");
            count++;
        }

        
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        if(count1<count-1){
        shet.setAutoFilter(new CellRangeAddress(count1, count - 1, 0, columnCount-1));
        }

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
        for (int i = 0; i <= columnCount; i++) {
            shet.autoSizeColumn(i);
        }

        shet.setDisplayGridlines(false);
        shet.createFreezePane(4, 4);

    }
       
        
        
        
        
            
              if(conn.rs!=null){conn.rs.close();}
              if(conn.st!=null){conn.st.close();}
              if(conn.conn!=null){conn.conn.close();}
        
        } catch (SQLException ex) {
            Logger.getLogger(getVlNonStandardOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         IdGenerator IG = new IdGenerator();
        String createdOn = IG.CreatedOn();

        System.out.println("" + "VL_Data_Cleaning_rpt_gen_" + createdOn.trim() + ".xlsx");

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=" + "VL_Data_Cleaning_rpt_gen_" + createdOn.trim() + ".xlsx");
         response.setHeader("Set-Cookie","fileDownload=true; path=/");
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
        processRequest(request, response);
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
        processRequest(request, response);
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


  
   public boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
}
