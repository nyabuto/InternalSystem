/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GapAnalysis;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import scripts.copytemplates;

/**
 *
 * @author Emmanuel E
 */
public class gapAnalysis extends HttpServlet {

String pathtodelete=null;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        try {
        
            ArrayList keyal= new ArrayList();
            ArrayList countyal= new ArrayList();
            ArrayList scountyal= new ArrayList();
            ArrayList facilal= new ArrayList();
            ArrayList yearmonthal= new ArrayList();
            ArrayList sectional= new ArrayList();
            
         XSSFWorkbook wb;    
            
    String periodname="";        
String allpath = getServletContext().getRealPath("/Gapanalysis.xlsm");

 System.out.println(allpath);
 
    XSSFWorkbook workbook;
      String mydrive = allpath.substring(0, 1);
   // wb = new XSSFWorkbook( OPCPackage.open(allpath) );
     
 Date da= new Date();
String dat2 = da.toString().replace(" ", "_");
 dat2 = dat2.toString().replace(":", "_");

      
       String np=mydrive+":\\APHIAPLUS\\InternalSystem\\Gapanalysis"+dat2+".xlsm";
            System.out.println("path:: "+np);
             // String desteepath1 = getServletContext().getRealPath("/Females 15to24.xlsm");
              String sr = getServletContext().getRealPath("/Gapanalysis.xlsm");
    //check if file exists
              
   //first time , it should create those folders that host the macro file
    File f = new File(np);
if(!f.exists()&& !f.isDirectory() ) { /* do something */
copytemplates ct= new copytemplates();
    ct.transfermacros(sr,np);
 //rem np is the destination file name  
   
    System.out.println("Copying macro template first time ..");
    
}
else 
  //copy the file alone  
{
copytemplates ct= new copytemplates();
//copy the agebased file only
ct.copymacros(sr,np);

}
String filepth=np;      

  File allpathfile= new File(filepth);
     
      OPCPackage pkg = OPCPackage.open(allpathfile);

pathtodelete=filepth;
    wb = new XSSFWorkbook(pkg);

            
            
            
            
            
            
          dbConn conn= new dbConn();
          HashMap<String, String> rawdatahashmap= new HashMap<String,String>();
          
          int year=0;
          String yearval="";
          int prevyear=0;
          
          String quarter="";
          
          String yearmonth=""; 
         String startyearmonth="";
         String endyearmonth="";
         
          yearval=request.getParameter("year").toString();
          
            System.out.println("YEARVAL"+yearval);
          year=Integer.parseInt(yearval);
          prevyear=year-1;
          quarter=request.getParameter("quarter");
          periodname+=yearval+"_";
          if(quarter.equals("1")){ 
          startyearmonth=prevyear+"10";
          endyearmonth=prevyear+"12";
            periodname=prevyear+"_(Oct_Dec)";  
          }
          else if(quarter.equals("2")){
          startyearmonth=year+"01";
          endyearmonth=year+"03";
            periodname=yearval+"_(Jan-Mar)";
          }
          else if(quarter.equals("3")){
            startyearmonth=year+"04";
          endyearmonth=year+"06";
            periodname=yearval+"_(Apr_Jun)";
          }
          else if(quarter.equals("4")){
          startyearmonth=year+"07";
          endyearmonth=year+"09";
          periodname=yearval+"_(Jul_Sep)";
          }
          
          int colsmerging=4;
            String Sections[]={"ART","HTC","PMTCT"};
            String headers[]={"County","Sub-County","Facility","YearMonth"};
            String headergsn[]={"County","Sub-County","Facility"};
           if(request.getParameterValues("gapsection")!=null){
               
           Sections=request.getParameterValues("gapsection");
           
           } 
         //This is the loop that well use to create worksheets for each 
           
           String period=" 1=1 and Annee="+yearval+" and yearmonth between "+startyearmonth+" and "+endyearmonth+" ";
           String gsnperiod=" 1=1  ";
           
             //______________________________________________________________________________________
             //______________________________________________________________________________________
              
            Font font = wb.createFont();
            font.setFontHeightInPoints((short) 18);
            font.setFontName("Cambria");
            font.setColor((short) 0000);
            CellStyle style = wb.createCellStyle();
            style.setFont(font);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
           Font font2 = wb.createFont();
            font2.setFontName("Cambria");
            font2.setColor((short) 0000);
            CellStyle style2 = wb.createCellStyle();
            style2.setFont(font2);
            style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

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
            stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            
            
            CellStyle stylex1 = wb.createCellStyle();
            stylex1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            stylex1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            stylex1.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stylex1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stylex1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stylex1.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stylex1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            
            Font fontx = wb.createFont();
            fontx.setColor(HSSFColor.BLACK.index);
            fontx.setFontName("Cambria");
            stylex.setFont(fontx);
            stylex.setWrapText(true);
            stylex1.setFont(fontx);
            stylex1.setWrapText(true);
   
            //==================================================
            
            
           
         for(int a=0;a< Sections.length; a++){
            int column=0; 
             int Row=3;
            
            Sheet shet= wb.createSheet(Sections[a]);
             
             Row rwx=shet.createRow(2);
       Row rw1=null;
       Row rw2=null;
      Row rw=shet.createRow(0);            
      rw.setHeightInPoints(25);
      Cell cl0= rw.createCell(0);
      cl0.setCellValue(Sections[a]+" GAP ANALYSIS");
      cl0.setCellStyle(stylex1);
      
    //this will depend on the length of the number of elements being checked
       
 for(int b=1;b<=colsmerging;b++){ 
 Cell clx= rw.createCell(b);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                                 }
         
 //now go to the database and do a query for each section
 
 String getqueries=" Select * from gap_analysis where active=1 and section='"+Sections[a]+"' ";
 
 conn.rs=conn.st.executeQuery(getqueries); 
 while(conn.rs.next()){
  
        if(shet.getRow(1)!=null){
     rw1=shet.getRow(1);
     }
     else {     
      rw1=shet.createRow(1);            
      rw1.setHeightInPoints(25);
          }
        
        //print blanks before printing real header
         if(conn.rs.getString("id").equals("1")){
  
             for(int p=0;p< headergsn.length;p++){
                Cell cl2= rw1.createCell(column+p);
                cl2.setCellValue("");
                cl2.setCellStyle(stylex);
                 shet.setColumnWidth(column+p, 5000);
             }
                                      }
         else {
         
             
              for(int p=0;p< headers.length;p++){
                 Cell cl2= rw1.createCell(column+p);
                 cl2.setCellValue("");
                 cl2.setCellStyle(stylex);
                 shet.setColumnWidth(column+p, 5000);
                                                 }
             
         }
   
        
      Cell cl1= rw1.createCell(column);
      cl1.setCellValue(conn.rs.getString("rule"));
      cl1.setCellStyle(stylex); 
   
        
//Create the column header  
  
   
      
      
        if(shet.getRow(2)!=null){
     rw2=shet.getRow(2);
                                }
                           else {     
      rw2=shet.createRow(2);            
      rw2.setHeightInPoints(25);
                                }
         if(conn.rs.getString("id").equals("1")){
  
             for(int p=0;p< headergsn.length;p++){
                Cell cl2= rw2.createCell(column+p);
                cl2.setCellValue(headergsn[p]);
                cl2.setCellStyle(stylex);
             }
                                      }
         else {
         
             
              for(int p=0;p< headers.length;p++){
                 Cell cl2= rw2.createCell(column+p);
                 cl2.setCellValue(headers[p]);
                 cl2.setCellStyle(stylex);
                                                 }
             
         }
   
      
     
 String currentqry=conn.rs.getString("query"); 
   //process each query as you 
 //pass the necessary period parameters from the interface
 //rem each query ends with a 'and'
 if(conn.rs.getString("id").equals("1")){
  currentqry+=gsnperiod;
                                      }
 else {
     
   currentqry+=period+" and subpartnera."+Sections[a]+"= 1 ";
 
      }
 
     System.out.println(""+currentqry);
      Row=3;
 conn.rs1=conn.st1.executeQuery(currentqry);
 
 while (conn.rs1.next()) {
     
     if(shet.getRow(Row)!=null){
     rwx=shet.getRow(Row);
     }
     else {     
      rwx=shet.createRow(Row);            
      rwx.setHeightInPoints(25);
          }
      Cell cly= rwx.createCell(column);
      cly.setCellValue(conn.rs1.getString("County"));
      cly.setCellStyle(style2);
     
      Cell cly2= rwx.createCell(column+1);
      cly2.setCellValue(conn.rs1.getString("DistrictNom"));
      cly2.setCellStyle(style2);//gsn sites do not have a yearmonth
      
      Cell cly1= rwx.createCell(column+2);
      cly1.setCellValue(conn.rs1.getString("SubPartnerNom"));
      cly1.setCellStyle(style2);
     
      
      if(!conn.rs.getString(1).equals("1")){
       Cell cly3= rwx.createCell(column+3);
      cly3.setCellValue(conn.rs1.getString("yearmonth"));
      cly3.setCellStyle(style2);
      //my key is a 
      String mykey=Sections[a]+conn.rs1.getString("SubPartnerNom")+"_"+conn.rs1.getString("yearmonth")+"_";
      //add all the facilities at this point
      //ignore the sites in ART since they are static
      if(!keyal.contains(mykey)){
      keyal.add(mykey);
      countyal.add(conn.rs1.getString("County"));
      scountyal.add(conn.rs1.getString("DistrictNom"));
      facilal.add(conn.rs1.getString("SubPartnerNom"));
      sectional.add(Sections[a]);
      yearmonthal.add(conn.rs1.getString("yearmonth"));
      
      }
      
      
      
      
                                           }
 
 Row++;
 
                         }

 if(conn.rs.getString(1).equals("1")){
 column+=3;
 }
 else{
 column+=4;
 }
     if(conn.rs.getString("id").equals("1")){
 shet.addMergedRegion(new CellRangeAddress(1,1,0,column-1));
                                      }
       else {
 shet.addMergedRegion(new CellRangeAddress(1,1,column-4,column-1));  
       }
 
                         }//end of all queries per section
 
 
  shet.addMergedRegion(new CellRangeAddress(0,0,0,column-1));  
      
 
                                              }// end of sheets loop   
            
         
         
         //create a new sheet
         
         //county	subcounty	facility	yearmonth	section

         Sheet shet=  wb.getSheet("Sheet1");
             
       
      Row rw=shet.createRow(0);
      Cell cl0= rw.createCell(0);
      cl0.setCellValue("county");
      cl0.setCellStyle(stylex1);
      
      Cell cl1= rw.createCell(1);
      cl1.setCellValue("subcounty");
      cl1.setCellStyle(stylex1);
         
      Cell cl2= rw.createCell(2);
      cl2.setCellValue("facility");
      cl2.setCellStyle(stylex1);
         
      Cell cl3= rw.createCell(3);
      cl3.setCellValue("yearmonth");
      cl3.setCellStyle(stylex1);
       
      Cell cl4= rw.createCell(4);
      cl4.setCellValue("section");
      cl4.setCellStyle(stylex1);
      
      for(int q=0;q< keyal.size();q++){
    
     Row rwx=shet.createRow(q+1);
      
   
      Cell cl01= rwx.createCell(0);
      cl01.setCellValue(countyal.get(q).toString());
      cl01.setCellStyle(style2);
      
      Cell cl11= rwx.createCell(1);
      cl11.setCellValue(scountyal.get(q).toString());
      cl11.setCellStyle(style2);
         
      Cell cl21= rwx.createCell(2);
      cl21.setCellValue(facilal.get(q).toString());
      cl21.setCellStyle(style2);
         
      Cell cl31= rwx.createCell(3);
      cl31.setCellValue(yearmonthal.get(q).toString());
      cl31.setCellStyle(style2);
       
      Cell cl41= rwx.createCell(4);
      cl41.setCellValue(sectional.get(q).toString());
      cl41.setCellStyle(style2);  
          
      
      }
      
      
	  IdGenerator IG = new IdGenerator();
     String   createdOn=IG.CreatedOn();


ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte[] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=GapAnalysis_For"+periodname+"_Generatted_On_"+createdOn+".xlsm");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
outStream.close(); 
   pkg.close();

   if(conn.rs!=null){conn.rs.close();}
   if(conn.rs1!=null){conn.rs1.close();}
   if(conn.st1!=null){conn.st1.close();}
   if(conn.st!=null){conn.st.close();}
       
         File file= new File(pathtodelete);
            System.out.println("path: 2"+pathtodelete);
           
        if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation  failed.");
    		}

        } 
        catch (SQLException ex) { 
            Logger.getLogger(gapAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(gapAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }        finally 
        {
           
        }
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

}
