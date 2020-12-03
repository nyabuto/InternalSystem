/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

 */
package VL;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 20 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)

/**
 *
 * @author Emmanuel Kaunda
 */
public class checkvlresults extends HttpServlet {

    HttpSession session;
    private static final long serialVersionUID = 205242440643911308L;
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

         String full_path = "";
         String fileName = "";
         String fileNameCopy = "";
          XSSFWorkbook workbook = null;

                   
        
       

        String quarterName, facilityName = "";

        String cccnumber = "";
        String date = "";
        String sessionText = "";

        session = request.getSession();

        fileNameCopy = "";
        fileName = "";

        File file_source;

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

        String mflcode = request.getParameter("facility");
        String startdate = request.getParameter("weekstart");
        String enddate = request.getParameter("weekend");
        
        ArrayList cccnos= new ArrayList();

        //call internal_system.Viral_Load_Raw_Data_surge_PerSite('15763','2020-01-01','2020-09-01');
        dbConn conn = new dbConn();

        String nextpage = "PullVlResults.jsp";

        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        session = request.getSession();

        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        // System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());

        for (Part part : request.getParts()) {

            if (!getFileName(part).equals("")) {

                fileName = getFileName(part);

                fileNameCopy += fileName + ",";
                part.write(uploadFilePath + File.separator + fileName);

                if (!fileName.endsWith(".xlsx")) {
                    nextpage = "PullVlResults.jsp";
                    sessionText = "<font color=\"red\">Failed to load a .xlsx excel file. Please open the file, go to file> options > save as , then save as missing_vl_records.xlsx </font>";
                }

            }
            //}

            if (!fileName.endsWith(".xlsx")) {

                nextpage = "PullVlResults.jsp";
            } else {

                try {

                    full_path = fileSaveDir.getAbsolutePath() + "/" + fileName; //end of checking if excel file is valid

                    FileInputStream fileInputStream = new FileInputStream(full_path);

                  workbook= (XSSFWorkbook) ReadExcel(full_path);
                    
//_________________________________________________________________________________________________________________________________________________________________


        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("Daytona");
        font.setColor((short) 0000);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        XSSFFont font2 = workbook.createFont();
        font2.setFontName("Daytona");
        font2.setColor((short) 0000);
        font2.setFontHeightInPoints((short) 11);
        CellStyle style2 = workbook.createCellStyle();
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
        

        XSSFCellStyle stborder = workbook.createCellStyle();
        stborder.setBorderTop(XSSFCellStyle.BORDER_THIN);
        stborder.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        stborder.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        stborder.setBorderRight(XSSFCellStyle.BORDER_THIN);
        stborder.setBottomBorderColor(HSSFColor.GREEN.index);
        stborder.setTopBorderColor(HSSFColor.GREEN.index);
        stborder.setLeftBorderColor(HSSFColor.GREEN.index);
        stborder.setRightBorderColor(HSSFColor.GREEN.index);
        stborder.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        XSSFCellStyle stylex = workbook.createCellStyle();
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

        XSSFCellStyle stylesum = workbook.createCellStyle();
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

        XSSFFont fontx = workbook.createFont();
        fontx.setColor(HSSFColor.BLACK.index);
        fontx.setFontName("Daytona");
        
        XSSFFont fontwhite = workbook.createFont();
        fontwhite.setColor(HSSFColor.WHITE.index);
        fontwhite.setFontName("Daytona");
        fontwhite.setFontHeightInPoints((short)9);
                
        stylex.setFont(fontwhite);
        stylex.setWrapText(true);

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);


//_________________________________________________________________________________________________________________________________________________________________
                    
                    

                    int totalsheets = 1;

                    DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale

                    int rowsngapi = 1;

                    int rowCount = 4;

                 //   for (int a = 0; a < totalsheets; a++) {

                        XSSFSheet worksheet = workbook.getSheetAt(0);

                     
                        
                        
                        String hasdata = "no";

                       
                        String sheetname = workbook.getSheetName(0);
                        
                           

                        if (1==1) {

                            Iterator rowIterator = worksheet.rowIterator();
                            rowCount = worksheet.getLastRowNum();
                            int row = 0, col = 0;
                            
                            
                            while(rowIterator.hasNext())
                            {
//System.out.println(" Sheet name "+sheetname+" row number  "+row+" ");

XSSFRow rowi = worksheet.getRow(row);
        if( rowi==null){
         break;}

                            XSSFCell cellfacil = rowi.getCell((short) 0);

                            if (cellfacil.getCellType() == 0) 
                            {

                                cccnos.add((int) cellfacil.getNumericCellValue());
                                
                            } else if (cellfacil.getCellType() == 1) 
                            {
                                cccnos.add(cellfacil.getStringCellValue());
                            }
                            
                            row++;
                            } // end of iterator
                            
   System.out.println("\n VL Results Check for: " + facilityName + " \n  between Date:: " + startdate + " and "+enddate+"  \n Total Records: " + row);
 
                            
                           
                        }
                   // }//end of worksheets loop
                   
                    
                    
                     
//________________________________________________________________________________________________________________________________
                   
String qry="call internal_system.Viral_Load_Raw_Data_surge_PerSite('"+mflcode+"','"+startdate+"','"+enddate+"')";
        
conn.rs=conn.st.executeQuery(qry);
        

  XSSFSheet shet = workbook.createSheet("Records With Results");
        
        XSSFRow rw0=shet.createRow(1);
        XSSFCell cell = rw0.createCell(0);
                  
                
        int count1  = 1;
        
       
         ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();

         metaData = conn.rs.getMetaData();
         columnCount = metaData.getColumnCount();
        int count = count1;
        ArrayList mycolumns = new ArrayList();

        while (conn.rs.next()) {

            if (count == (count1)) 
            {
//header rows
                XSSFRow rw = shet.createRow(count);
                rw.setHeightInPoints(32);
                for (int i = 1; i <= columnCount; i++) 
                {

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

           
  
            if(cccnos.toString().contains(conn.rs.getString("Patient_CCC_No"))){
                    
            System.out.println(conn.rs.getString("Patient_CCC_No")+" iko ");   
                    
                    
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
            count++;
        }
            else {
            
            System.out.println(conn.rs.getString("Patient_CCC_No")+":HAIKO ");   
                    
            }
            // System.out.println("");
            
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
        shet.createFreezePane(6, 4);

//________________________________________________________________________________________________________________________________
                     
                    
                    
                } catch (InvalidFormatException ex) {
                    Logger.getLogger(checkvlresults.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(checkvlresults.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

           


            

        }//end of for loop

        if (conn.rs != null) {
            try {
                conn.rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(checkvlresults.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn.st != null) {
            try {
                conn.st.close();
            } catch (SQLException ex) {
                Logger.getLogger(checkvlresults.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn.pst != null) {
            try {
                conn.pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(checkvlresults.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (conn.conn != null) {
            try {
                conn.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(checkvlresults.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (conn.conn != null) {
            try {
                conn.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(checkvlresults.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // pullHTS hts= new pullHTS();
        // hts.hts_pns(yearmonth, yearmonth, "");
        sessionText += "<br/>" + " ";
        // System.out.println(""+sessionText);
        session.setAttribute("uploadedart", "<ul><b>Last Data Upload Results:</b></ul><br/>  File(s) : <b>" + fileNameCopy + ".</b> " + sessionText);
       // response.sendRedirect(nextpage);
        
         IdGenerator IG = new IdGenerator();
        String createdOn = IG.CreatedOn();

        System.out.println("" + "Missing_VL_reports_Gen_" + createdOn.trim() + ".xlsx");

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        workbook.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=" + "Missing_VL_reports_Gen_"+startdate+"_to_"+enddate+"__gen_" + createdOn.trim() + ".xlsx");
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
        String file_name = "";
        String contentDisp = part.getHeader("content-disposition");
        //    System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");

        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                file_name = token.substring(token.indexOf("=") + 2, token.length() - 1);
                break;
            }

        }
        // System.out.println("content-disposition final : "+file_name);
        return file_name;
    }

    public Workbook ReadExcel(String excelpath) throws IOException, InvalidFormatException {
        Workbook wb = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(excelpath));
            wb = WorkbookFactory.create(inputStream);
            int numberOfSheet = wb.getNumberOfSheets();

            for (int i = 0; i < numberOfSheet; i++) {
                Sheet sheet = wb.getSheetAt(i);
                //.... Customize your code here
                // To get sheet name, try -> sheet.getSheetName()
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(checkvlresults.class.getName()).log(Level.SEVERE, null, ex);
        }

        return wb;
    }
    
    
    public boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
    

}
