/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMATT;


import General.IdGenerator;
import static database.OSValidator.isUnix;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import static reports.RawQuery.isNumeric;


import scripts.copytemplates;

/**
 *
 * @author EKaunda
 */
public class fmatt_report extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();

        
         IdGenerator IG = new IdGenerator();
        String createdOn = IG.CreatedOn();
        
        
        
        
        
        
        
        
        
        /* TODO output your page here. You may use following sample code. */
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
        



String allpath = getServletContext().getRealPath("/fmatt_rpt.xlsx");

XSSFWorkbook wb1;
 
String pathtodelete=null;

Date da= new Date();
String dat2 = da.toString().replace(" ", "_");

dat2 = dat2.toString().replace(":", "_");

String mydrive = allpath.substring(0, 1);

String np=mydrive+":\\HSDSA\\PNS\\MACROS\\";

String filepath="FMATT"+dat2+".xlsx";


if(isUnix()){
    np="/HSDSA/PNS/MACROS/";
}


 new File(np).mkdirs();

 np+=filepath;
 
//check if file exists
String sourcepath = getServletContext().getRealPath("/fmatt_rpt.xlsx");

File f = new File(np);
if(!f.exists()&& !f.isFile() ) {
    /* do something */
    
    copytemplates ct= new copytemplates();
    System.out.println("Copying macros..");
    ct.transfermacros(sourcepath,np);
    
}
else
    //copy the file alone  
{
    
    copytemplates ct= new copytemplates();
//copy the agebased file only
ct.copymacros(sourcepath,np);

}

            System.out.println("Copying done..");


File allpathfile= new File(np);

OPCPackage pkg = OPCPackage.open(allpathfile);

pathtodelete=np;


//wb = new XSSFWorkbook( OPCPackage.open(allpath) );
wb1 = new XSSFWorkbook(pkg);


//SXSSFWorkbook wb = new SXSSFWorkbook(wb1, 100);





XSSFWorkbook wb = wb1;




        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 18);
        font.setFontName("Cambria");
        font.setColor((short) 0000);
        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        XSSFFont font2 = wb.createFont();
        font2.setFontName("calibri");
        font2.setColor((short) 0000);
        CellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        XSSFCellStyle stborder = wb.createCellStyle();
        stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        XSSFCellStyle stylex = wb.createCellStyle();
        stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylex.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        XSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        XSSFFont fontx = wb.createFont();
        fontx.setColor(HSSFColor.BLACK.index);
        fontx.setFontName("calibri");
        stylex.setFont(fontx);
        stylex.setWrapText(true);

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        XSSFSheet shet = wb.getSheet("data");

        String year="";
       IdGenerator dats= new IdGenerator();
        
        String startdate="2021-10-01";
        String enddate=IG.toDay();
      
        String county="";
        if(request.getParameter("startdate")!=null)
        {
        
        startdate=request.getParameter("startdate");
        
        }
        if(request.getParameter("enddate")!=null)
        {
        
            enddate=request.getParameter("enddate");
        
        }
        

        dbConn conn = new dbConn();
        
        //========Query 1=================
        
      
  
        
        
        //_______________________________________________________________________________________________
        
                 
                int count1  = 2;
        
       
        
        //========Query two====Facility Details==============
        
        String qry = "call sp_fmatt_rawdata('"+startdate+"','"+enddate+"');";
        System.out.println(qry);
        conn.rs = conn.st.executeQuery(qry);
        
         ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();

         metaData = conn.rs.getMetaData();
         columnCount = metaData.getColumnCount();
         int count = count1;
         ArrayList mycolumns = new ArrayList();

         while (conn.rs.next()) {

         if (count == (count1)) {
//header rows
         XSSFRow rw = shet.getRow(count);
//rw.setHeightInPoints(26);
                for (int i = 1; i <= columnCount; i++) 
                {
//skip header
                    mycolumns.add(metaData.getColumnLabel(i));
//                    XSSFCell cell0 = rw.getCell(i - 1);
//                    cell0.setCellValue(metaData.getColumnLabel(i));
//                    cell0.setCellStyle(stylex);

                    //create row header
                }//end of for loop
                count++;
            }//end of if
            //data rows     
            XSSFRow rw = shet.createRow(count);

            for (int a = 0; a < columnCount; a++) 
            {
                //System.out.print(mycolumns.get(a) + ":" + conn.rs.getString("" + mycolumns.get(a)));

                XSSFCell cell0 = rw.createCell(a);
                 if(isNumeric(conn.rs.getString("" + mycolumns.get(a))))
                 {
               // if(1==1){
                
                     cell0.setCellValue(conn.rs.getInt(mycolumns.get(a).toString()));
                    
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
     if(count!=count1)   {
       // shet.setAutoFilter(new CellRangeAddress(count1, count - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
        for (int i = 0; i <= columnCount; i++) 
        {
          //  shet.autoSizeColumn(i);
        }

      //  shet.setDisplayGridlines(false);
      //  shet.createFreezePane(6, 4);
    }
     
     
     
   if(1==1){
     XSSFSheet sheet= wb.getSheet("data");
        // tell your xssfsheet where its content begins and where it ends
((XSSFSheet)shet).getCTWorksheet().getDimension().setRef("A1:AR" + (shet.getLastRowNum() + 1));

CTTable ctTable = ((XSSFSheet)shet).getTables().get(0).getCTTable();

ctTable.setRef("A1:AR" + (shet.getLastRowNum() + 1)); // adjust reference as needed


        
          }
     
     
     
        
        if(conn.rs!=null){conn.rs.close();}
        if(conn.rs1!=null){conn.rs1.close();}
        if(conn.st!=null){conn.st.close();}
        if(conn.st1!=null){conn.st1.close();}
        if(conn.conn!=null){conn.conn.close();}
        
        
       

        System.out.println("" + "FMatt_Gen_" + createdOn.trim() + ".xlsx");

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=" + "Fmatt_Summary_"+startdate+"_to_"+enddate+"_gen_" + createdOn.trim() + ".xlsx");
         response.setHeader("Set-Cookie","fileDownload=true; path=/");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(fmatt_report.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(fmatt_report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(fmatt_report.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(fmatt_report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
