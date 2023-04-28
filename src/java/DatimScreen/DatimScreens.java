/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatimScreen;


import General.IdGenerator;
import static LoadExcels.OSValidator.isUnix;
import database.dbConn;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import scripts.copytemplates;

/**
 *
 * @author EKaunda
 */
public class DatimScreens extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
       
        //______________________________________________________________________________________________
        // Get Variables
        //______________________________________________________________________________________________
        
            String yr="2020";
        
        if(request.getParameter("year")!=null)
        {
        yr=request.getParameter("year");  
        }
        
        String quarterid="1";
        
        if(request.getParameter("quarter")!=null)
        {
         quarterid=request.getParameter("quarter"); 
        }
        
        String datimuser="";
        if(request.getParameter("datimuser")!=null)
        {
         datimuser=request.getParameter("datimuser"); 
        }
        
       
             
        
        int YearInt=new Integer(yr);        
        int PrevYearInt=YearInt-1;
        
        
        String periodid="1";
        
     
        
        String an="";
        String sa="";
        String qa="";
        
               an="'"+PrevYearInt+"-10-01','"+YearInt+"-09-30'";
        String sa1="'"+PrevYearInt+"-10-01','"+YearInt+"-03-31'";
        String sa2="'"+YearInt+"-04-01','"+YearInt+"-09-30'";
        
        
        String q1="'"+PrevYearInt+"-10-01','"+PrevYearInt+"-12-31'";
        String q2="'"+YearInt+"-01-01','"+YearInt+"-03-31'";
        String q3="'"+YearInt+"-04-01','"+YearInt+"-06-30'";
        String q4="'"+YearInt+"-07-01','"+YearInt+"-09-30'";
        
         
       if(quarterid.equals("1"))
       { 
       sa=sa1;
       qa=q1;
       }
       
       else if(quarterid.equals("2"))
       { 
       sa=sa1;
       qa=q2;
       }
       
        else if(quarterid.equals("3"))
        { 
       sa=sa2;
       qa=q3;
       }
        else if(quarterid.equals("4"))
        { 
       sa=sa2;
       qa=q4;
       }
        
        
       
       dbConn conn = new dbConn(); 
        
        
         IdGenerator IG = new IdGenerator();
        String createdOn = IG.CreatedOn();
        
     
        
        /* TODO output your page here. You may use following sample code. */
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
        



String allpath = getServletContext().getRealPath("/Des_blank.xlsx");

//XSSFWorkbook wb1;
XSSFWorkbook wb;
 
String pathtodelete=null;

Date da= new Date();
String dat2 = da.toString().replace(" ", "_");

dat2 = dat2.toString().replace(":", "_");

String mydrive = allpath.substring(0, 1);

String np=mydrive+":\\HSDSA\\PNS\\MACROS\\";

String filepath="DatimScreen"+dat2+".xlsx";


if(isUnix())
{
    np="/HSDSA/PNS/MACROS/";
}


 new File(np).mkdirs();

 np+=filepath;
 
//check if file exists
String sourcepath = getServletContext().getRealPath("/Des_blank.xlsx");

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


wb = new XSSFWorkbook( OPCPackage.open(allpath) );
//wb1 = new XSSFWorkbook(pkg);



//XSSFWorkbook wb = wb1;


//SXSSFWorkbook wb = new SXSSFWorkbook(wb1, 1000);

        Font font =  wb.createFont();
        font.setFontHeightInPoints((short) 18);
        font.setFontName("Cambria");
        font.setColor((short) 0000);
        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        XSSFFont font2 = (XSSFFont) wb.createFont();
        font2.setFontName("Cambria");
        font2.setColor((short) 0000);
        CellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        CellStyle stborder = wb.createCellStyle();
        stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        CellStyle stylex = wb.createCellStyle();
        stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylex.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        CellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        Font fontx = wb.createFont();
        fontx.setColor(HSSFColor.BLACK.index);
        fontx.setFontName("Cambria");
        stylex.setFont(fontx);
        stylex.setWrapText(true);

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        
        
        String gettables="select  `order`, `section`, `sheetname`, `frequency`, `sp_name` from datim_screens where `active`=1";
        
        
        conn.rs2=conn.st2.executeQuery(gettables);
        
        while(conn.rs2.next()){
        
            String sheetname=conn.rs2.getString("sheetname");
            String mainsheet=conn.rs2.getString("section");
            String frequency=conn.rs2.getString("frequency");
            String storedprocedure=conn.rs2.getString("sp_name");
        
        
            Sheet shet = wb.getSheet(sheetname);
            System.out.println("sheetname:"+sheetname);
        
        
       
     
        
        //_______________________________________________________________________________________________
        
      
//        
//        XSSFRow rw0=shet.createRow(1);
//        XSSFCell cell = rw0.createCell(0);
//        cell.setCellValue("Surge Tracker for Period "+startdate+"  to "+enddate+"");
//        cell.setCellStyle(style);
//        shet.addMergedRegion(new CellRangeAddress(1, 1, 0,10));
//                    
                int count1  = 0;
        
       String currperiod="";
        
        //========Query two====Facility Details==============
        
             if(frequency.equals("QA")){ currperiod=qa; }
        else if(frequency.equals("SA")){ currperiod=sa; }
        else if(frequency.equals("AN")){ currperiod=an; }
        
    
        
         String qry = "call "+storedprocedure+"("+currperiod+");";
         
         //updating the facility worksheet
         
         
         
          if(storedprocedure.equals("sp_des_get_facility_user")){
              
     qry = "call "+storedprocedure+"('"+datimuser+"');";
     
     }
        
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
         //Row rw = shet.getRow(count);
//rw.setHeightInPoints(26);
                for (int i = 1; i <= columnCount; i++) 
                {
//skip header
                    mycolumns.add(metaData.getColumnLabel(i));
                  // Cell cell0 = rw.getCell(i - 1);
                  // cell0.setCellValue(metaData.getColumnLabel(i));
                  //  cell0.setCellStyle(stylex);

                    //create row header
                }//end of for loop
                count++;
            }//end of if
            //data rows  
             //System.out.println("Create row in position:"+count);
             Row rw = null;
             if(shet.getRow(count)!=null){
             rw = shet.getRow(count);
             }
             else {
             rw = shet.createRow(count);
             }
            

            
            for (int a = 0; a < columnCount; a++) 
            {
                //System.out.print(mycolumns.get(a) + ":" + conn.rs.getString("" + mycolumns.get(a)));

                Cell cell0 = rw.createCell(a);
                 if(isNumeric(conn.rs.getString((a+1))))
                 {
               // if(1==1){
                
                     cell0.setCellValue(conn.rs.getInt((a+1)));
                    
                 }
                else 
                {
                    //System.out.println(mycolumns.get(a)+" Last option"+conn.rs.getString("" + mycolumns.get(a)));
//                    System.out.println( mycolumns.get(a)+" --Last option"+conn.rs.getString("" + mycolumns.get(a)));
                     cell0.setCellValue(conn.rs.getString((a+1)));
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
     
     
     

    }//end while loop
  
    
     
     
        
        if(conn.rs!=null){conn.rs.close();}
        if(conn.rs1!=null){conn.rs1.close();}
        if(conn.rs2!=null){conn.rs2.close();}
        if(conn.st2!=null){conn.st2.close();}
        if(conn.st!=null){conn.st.close();}
        if(conn.st1!=null){conn.st1.close();}
        if(conn.conn!=null){conn.conn.close();}
        
       wb.setForceFormulaRecalculation(true); 
       

        System.out.println("" + "DatimSCreens_" +datimuser+"_"+ createdOn.trim() + ".xlsx");
        
        datimuser=datimuser.toUpperCase();

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=" + "DatimScrn_"+datimuser+"_"+qa.replace("'", "").replace(",","_").replace("-", "")+"_gen_" + createdOn.trim() + ".xlsx");
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
            Logger.getLogger(DatimScreens.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(DatimScreens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DatimScreens.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(DatimScreens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    } 

    
    // private byte[] zipFiles(File directory, String[] files) throws IOException {
     private byte[] zipFiles(String[] files) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        byte bytes[] = new byte[2048];

        for (String fileName : files) 
        {
            //FileInputStream fis = new FileInputStream(directory.getPath() + downloadTemplate.FILE_SEPARATOR + fileName);
            File srcFile = new File(fileName);
 
                //FileInputStream fis = new FileInputStream(srcFile);
            
            FileInputStream fis = new FileInputStream(srcFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            zos.putNextEntry(new ZipEntry(srcFile.getName()));

            int bytesRead;
            while ((bytesRead = bis.read(bytes)) != -1) 
            {
                zos.write(bytes, 0, bytesRead);
            }
            zos.closeEntry();
            bis.close();
            fis.close();
        }
        zos.flush();
        baos.flush();
        zos.close();
        baos.close();

        return baos.toByteArray();
    }
    
    

}


