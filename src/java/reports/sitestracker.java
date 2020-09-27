/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import scripts.copytemplates;

/**
 *
 * @author EKaunda
 */
public class sitestracker extends HttpServlet {
String pathtodelete=null;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
        //response.setContentType("text/html;charset=UTF-8");
       // PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/html;charset=UTF-8");
            
             IdGenerator IG = new IdGenerator();
            
            /* TODO output your page here. You may use following sample code. */
           dbConn conn = new dbConn();
           
           
           String allpath = getServletContext().getRealPath("/sitestracker.xlsm");

 System.out.println("_+"+allpath);
 
  
      String mydrive = allpath.substring(0, 1);
           
      Date da= new Date();
String dat2 = da.toString().replace(" ", "_");
 dat2 = dat2.toString().replace(":", "_");

      
       String np=mydrive+":\\APHIAPLUS\\InternalSystem\\sitestracker"+dat2+".xlsm";
            System.out.println("path:: "+np);
             // String desteepath1 = getServletContext().getRealPath("/Females 15to24.xlsm");
              String sr = getServletContext().getRealPath("/sitestracker.xlsm");
    //check if file exists
              
   //first time , it should create those folders that host the macro file
    File f = new File(np);
if(!f.exists() && !f.isDirectory() ) { /* do something */
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
   
      
      
           XSSFWorkbook wb = new XSSFWorkbook(pkg);
           
           String quarter="";
          
            String year=""+IG.currentYear();
            
           String indicator[]=null;
           String indicators=" and 1=1";
           
           if(request.getParameter("year")!=null)
           {
           year=request.getParameter("year");
           }
           
           if(request.getParameter("quarter")!=null)
           {
           quarter=request.getParameter("quarter");
           }
           
           if(request.getParameterValues("indicator")!=null)
           {
          indicator=request.getParameterValues("indicator");
           }
           
           if(indicator.length>0){
           indicators=" and indicator in (";
           for(int a=0;a<indicator.length;a++){
           indicators+="'"+indicator[a]+"'";
           if(a<(indicator.length-1)){
           indicators+=",";
           }
           }
           indicators+=") ";
           }
           
           //if(){}
           //if(){}
           
           String startdate="201710";
           String enddate="201712";
           
          int mwaka=Integer.parseInt(year);
          int mwakajana=mwaka-1;
        
      
          if(quarter.equals("1")){ 
          startdate=mwakajana+"10";
          enddate=mwakajana+"12";
             
          }
          else if(quarter.equals("2")){
          startdate=year+"01";
          enddate=year+"03";
            
          }
          else if(quarter.equals("3")){
            startdate=year+"04";
          enddate=year+"06";
           
          }
          else if(quarter.equals("4")){
          startdate=year+"07";
          enddate=year+"09";
          
          }
           
           
           
           //______________________________________________________
           //     build the query
           //______________________________________________________
           
           String qrybuild="";
           String getbasic=" select * from trackermain where active=1  "+indicators+"";
           
          
          
           
           if(new Integer(enddate.substring(4))>=10){
               
           year=   enddate.substring(0,4); 
           
           }
           
           int sd= new Integer(startdate);
           int ed= new Integer(enddate);
           
  Calendar ca= Calendar.getInstance();
  int currentyear=ca.get(Calendar.YEAR);
 
  String facilitiestable="subpartnera";
  
  int selectedyear=new Integer(year);
  
  if(selectedyear<currentyear){
      
      if(selectedyear<2014){          
      //db for 2014 is the smallest          
       facilitiestable="subpartnera2014";  
      }
      else 
      {      
  facilitiestable="subpartnera"+year;  
      }
  }
           
           conn.rs=conn.st.executeQuery(getbasic);
           int count=0;
           while(conn.rs.next())
           {
               
             
               String category=conn.rs.getString("category");//whether its the first main report of the last one
               String facilityvalue=conn.rs.getString("facilityvalue"); //facility value
               String value=conn.rs.getString("value"); //condition
               String indic=conn.rs.getString("indicator"); //label
               String mflvalue=conn.rs.getString("mflvalue");  //mflcode
               String table=conn.rs.getString("table"); //table source
               
               
                
               
              
               
               
               if(facilityvalue.equals(" ") || facilityvalue.trim().equals("") ){
               
               facilityvalue="''";
               }
               else if(!facilityvalue.contains("SubPartnerNom")){
               
               facilityvalue="'"+facilityvalue+"'";
               }
               else if (facilityvalue.contains("SubPartnerNom")){
               
               facilityvalue="concat('[1] ',"+facilityvalue+")";
               
               
               }
               
                if(mflvalue.equals(" ") || mflvalue.trim().equals("") ){
               
               mflvalue="''";
               
                                                                       }
                         
           if(!table.equals("subpartnera")){
           
                if(count>0){
             qrybuild+=" union all ";  
                          }
                else {
                 count++;
                }
                
                String groupby=" group by yearmonth,"+table+".SubPartnerID ";
                //[2] Reported
                if(facilityvalue.equals("[2] Reported")){
                 // groupby=" group by yearmonth,DistrictNom ";  
                }
               
           qrybuild+=" Select County, DistrictNom as subcounty ,"+facilityvalue+" as Facility , "+mflvalue+" as   MFLCode ,'"+year+"' as year, case " +
"  when Mois = 1 then '[1] Jan'" +
"  when Mois = 2 then '[2] Feb'" +
"  when Mois = 3 then '[3] Mar'" +
"  when Mois = 4 then '[4] Apr'" +
"  when Mois = 5 then '[5] May'" +
"  when Mois = 6 then '[6] Jun'" +
"  when Mois = 7 then '[7] Jul'" +
"  when Mois = 8 then '[8] Aug'" +
"  when Mois = 9 then '[9] Sep'" +
" when Mois = 10 then '[10] Oct'" +
" when Mois = 11 then '[11] Nov'" +
" when Mois = 12 then '[12] Dec'" +
" end as Month, " +
" yearmonth, "
+ " sum( case when "+value+" then 1 else 0 end ) AS 'value', " +
"'"+indic+"' as indicator, '"+category+"' as category FROM "+table+" "
+" JOIN ( "+facilitiestable+" JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID " +
"JOIN county ON district.CountyID=county.CountyID ) ON "+table+".SubPartnerID="+facilitiestable+".SubPartnerID where yearmonth between '"+startdate+"' and '"+enddate+"' "+groupby+" ";
          
           
           
  
               
               
                                          }
           else {
          //do a loop
         
          for(int a=sd; a<=ed; a++){
              
           if(count>0){
             qrybuild+=" union all ";  
                       }
           else {
            count++;
           }
           
           qrybuild+="  SELECT County,DistrictNom as subcounty ,"+facilityvalue+" as Facility,  "+mflvalue+" as MFLCode,substring("+a+",1,4) as year ,"
+ " case " +
" when substring("+a+",5,2) = '01' then '[1] Jan'" +
" when substring("+a+",5,2) = '02' then '[2] Feb'" +
" when substring("+a+",5,2) = '03' then '[3] Mar'" +
" when substring("+a+",5,2) = '04' then '[4] Apr'" +
" when substring("+a+",5,2) = '05' then '[5] May'" +
" when substring("+a+",5,2) = '06' then '[6] Jun'" +
" when substring("+a+",5,2) = '07' then '[7] Jul'" +
" when substring("+a+",5,2) = '08' then '[8] Aug'" +
" when substring("+a+",5,2) = '09' then '[9] Sep'" +
" when substring("+a+",5,2) = '10' then '[10] Oct'" +
" when substring("+a+",5,2) = '11' then '[11] Nov'" +
" when substring("+a+",5,2) = '12' then '[12] Dec'" +
" end as Month, '"+a+"' ,  sum( case when "+value+" then 1 else 0 end ) as value, " +
" '"+indic+"' as indicator ,'"+category+"' as category "
                   + "   FROM  "+facilitiestable+" JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID " +
" JOIN county ON district.CountyID=county.CountyID where  DistrictNom=DistrictNom group by subcounty"; 
           
              
              
          
          
          }
               
               
               
           
           
           }
           
           
           
           }
            System.out.println(qrybuild);
            
            
            
           
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 18);
        font.setFontName("Cambria");
        font.setColor((short) 0000);
        XSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        XSSFFont font2 = wb.createFont();
        font2.setFontName("Cambria");
        font2.setColor((short) 0000);
        XSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);
        style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style2.setAlignment(XSSFCellStyle.ALIGN_LEFT);

        XSSFCellStyle stborder = wb.createCellStyle();
        stborder.setBorderTop(XSSFCellStyle.BORDER_THIN);
        stborder.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        stborder.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        stborder.setBorderRight(XSSFCellStyle.BORDER_THIN);
        stborder.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        XSSFCellStyle stylex = wb.createCellStyle();
        stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylex.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        stylex.setBorderTop(XSSFCellStyle.BORDER_THIN);
        stylex.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        stylex.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        stylex.setBorderRight(XSSFCellStyle.BORDER_THIN);
        stylex.setAlignment(XSSFCellStyle.ALIGN_LEFT);

        XSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(XSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(XSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        XSSFFont fontx = wb.createFont();
        fontx.setColor(HSSFColor.BLACK.index);
        fontx.setFontName("Cambria");
        stylex.setFont(fontx);
        stylex.setWrapText(true);

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        XSSFSheet shet = wb.getSheet("rawdata"); 
            
            
            XSSFRow rw=null;
       
                   

                    
                    int count1=0;
                    
        conn.rs = conn.st.executeQuery(qrybuild);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();
int rn=0;
        while (conn.rs.next()) {
rn++;
            System.out.println(" row no:"+rn);
                        if (count1 == 0) {
//header rows
                rw = shet.createRow(count1);
               rw.setHeightInPoints(26);
                for (int i = 1; i <= columnCount; i++) 
                {

                    mycolumns1.add(metaData.getColumnLabel(i));
                    XSSFCell cell0 = rw.createCell(i - 1);
                    cell0.setCellValue(metaData.getColumnLabel(i).replace("_"," "));
                    cell0.setCellStyle(stylex);

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            
           
            //data rows     
             rw = shet.createRow(count1);

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

                XSSFCell cell0 = rw.createCell(a);
                 if(a==3||a==4||a==6||a==7)
                 {
                
                    cell0.setCellValue(conn.rs.getInt(a+1));
                    
                   }
                
                else 
                 {
                    
                    cell0.setCellValue(conn.rs.getString(a+1));
                }
            
                cell0.setCellStyle(style2);

            }

            // System.out.println("");
            count1++;
        }
            System.out.println(" query run successfully ");

     
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        //shet.setAutoFilter(new CellRangeAddress(3, count1 - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
        for (int i = 0; i <= columnCount; i++) 
        {
            shet.autoSizeColumn(i);
        }

        shet.setDisplayGridlines(false);
        shet.createFreezePane(4,1);  


            
            //now excecute the report
            
            
            
            String createdOn = IG.toDay();

            System.out.println("SitesTracker_" + startdate + "_and_"+enddate+"_by_" + createdOn.trim() + ".xlsm");

            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            wb.write(outByteStream);
            byte[] outArray = outByteStream.toByteArray();
            response.setContentType("application/ms-excel");
            response.setContentLength(outArray.length);
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setHeader("Content-Disposition", "attachment; filename=SitesTracker_" + startdate + "_and_"+enddate+"_by_" + createdOn.trim() + ".xlsm");
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
            pkg.close();

   if(conn.rs!=null){conn.rs.close();}
   if(conn.rs1!=null){conn.rs1.close();}
   if(conn.st1!=null){conn.st1.close();}
   if(conn.st!=null){conn.st.close();}
       
         File file= new File(pathtodelete);
            System.out.println("path: 2"+pathtodelete);
           
        if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		} else{
    			System.out.println("Delete operation  failed.");
    		  }
            
        } finally {
            //out.close();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(sitestracker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
        Logger.getLogger(sitestracker.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(sitestracker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
        Logger.getLogger(sitestracker.class.getName()).log(Level.SEVERE, null, ex);
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

}
