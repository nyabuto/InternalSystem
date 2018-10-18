/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

 */

package DataMapping;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100) 

/**
 *
 * @author Emmanuel Kaunda
 */


  public class DATIM_IMIS extends HttpServlet {
   
 
  
  String full_path="";
  String fileName="";
  int checker_dist,checker_hf;
  File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";
  String nextpage="";
  String quarterName,facilityName,facilityID,id,missingFacility;
          
 String value="";

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
      HSSFWorkbook wb = new HSSFWorkbook();
      
       String startMonth, endMonth;
        String startdate="";
          String enddate="";
       
      try {
          
          int year,checker,missing = 0,added = 0,updated = 0;
          String county_name,county_id, district_name,district_id,hf_name,hf_id,quarter;
          session=request.getSession();
          id="";
         
         
          
          
          
          
          
          
          dbConn conn= new dbConn();
          
//[1]________________________________________get start and end date______________________________________________
          
         
          quarter = request.getParameter("quarter");
          year = new Integer(request.getParameter("year"));
          
          
          
          
          int prevYear = year - 1;
          //       quarter="3";
          String getMonths = "SELECT months,name,enddate FROM quarter WHERE id='" + quarter + "'";

          conn.rs = conn.st.executeQuery(getMonths);
          if (conn.rs.next() == true) {

              String months[] = conn.rs.getString(1).split(",");

              startMonth = months[0];

              endMonth = months[2];

              if (quarter.equals("1")) {

                  //___start and end date____
                  startdate = prevYear + "1001";
                  enddate = prevYear + "1231";

              } else {

                  //___start and end date____
                  startdate = year + startMonth + "01";
                  enddate = year + endMonth + "" + conn.rs.getString("enddate");

              }
          }
          
      //[2]_______________________________________Create workbook__________________________________    
          
     String where="";
          
          if(request.getParameter("onlyvariances")!=null){
          
        where=" and tofauti <> 0 ";
          
          }
          
          
          if(request.getParameter("generateoutput")!=null){
          
          generateOutput(response, startdate.substring(0,6), enddate.substring(0, 6), conn,  wb, where);
          
          }
          
          
//[2]________________________________________upload excel______________________________________________
         
          //System.out.println(" iko hapa nche");
int datastartcol=10;   //column that starts reading data

                 
             String dbname = "datim_imis";

          nextpage = "uploaddatimreports.jsp";

          String applicationPath = request.getServletContext().getRealPath("");
          
          String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
          
          session = request.getSession();
          File fileSaveDir = new File(uploadFilePath);
          if (!fileSaveDir.exists()) {
              fileSaveDir.mkdirs();
          }
          System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());
          for (Part part : request.getParts()) {
              if (!getFileName(part).equals("")) {
                  fileName = getFileName(part);
                  part.write(uploadFilePath + File.separator + fileName);
              }
          }
          if (!fileName.endsWith(".xlsx")) {
              nextpage = "uploaddatimreports.jsp";
              session.setAttribute("datimuploadrpt", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");
          } else {

              full_path = fileSaveDir.getAbsolutePath() + "/" + fileName;

              System.out.println("the saved file directory is  :  " + full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................

              FileInputStream fileInputStream = new FileInputStream(full_path);
              XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

              int totalsheets = workbook.getNumberOfSheets();
              DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale

             
//[3]________________________________________loop through excel sheets______________________________________________         


for(int a=0;a<totalsheets;a++){
    
     HashMap<String, String> imishm = new HashMap<String, String>();
     HashMap<String, String> elementidhm = new HashMap<String, String>();
     //save this with column name accompanied
     
     
    ArrayList headers= new ArrayList();
    ArrayList activecolumns= new ArrayList();
    ArrayList elementid= new ArrayList();
    
   int rowcount=0; 
    XSSFSheet sheet = workbook.getSheetAt(a);
    
    
    System.out.println( "Now at sheet number "+a+" named ("+workbook.getSheetName(a)+") out of "+totalsheets+" sheets");
    
    String sheetname =  workbook.getSheetName(a);
    
//[4]________________________________________loop through rows______________________________________________
  Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            //System.out.print("Row_"+rowcount+"_");
            XSSFRow rowi = (XSSFRow) rowIterator.next();
//            For each row, iterate through all the columns
            Iterator<Cell> cellIterator = rowi.cellIterator();

            int i=0;
            
//[5]________________________________________loop through columns___________________________________________
String mflcode="";
while (cellIterator.hasNext()) {
                
//----------------------------------------------------------------------

XSSFCell datacell = sheet.getRow(rowcount).getCell((short) i);
//______________________consider valid cells only________________________
if(sheet.getRow(rowcount).getCell((short) i)!=null)
                {
                
    switch (datacell.getCellType()) {
        case 0:
            //numeric
            value =""+(int)datacell.getNumericCellValue();
            break;
        case 1:
            value =datacell.getStringCellValue();
            break;
        case 3:
            //__blank cells with borders
            value ="0";
            break;
        default:
            value =datacell.getRawValue();
            break;
    }

//[6]________________________________________get headers from excel and find which column stores which data______________________________________________
if(rowcount==0){
   
    //add headers to arraylist for future reference
headers.add(value);

    String getheaders="select * from datim_imis_map where datim_element='"+value+"' and active='1'";
    conn.rs=conn.st.executeQuery(getheaders);
    
    if(conn.rs.next()){
        
        String eleid=conn.rs.getString("elementid");
        
        elementid.add(eleid);
        elementidhm.put("column_"+i, eleid);
        
        String imisqry="";
        //System.out.println(eleid+"** was added "+value+" size is "+elementid.size());
    //____ get data source for all the inidicators from IMIS. The indicators have been mapped in IMIS table_______
    if(!conn.rs.getString("technicalarea").equals("Organisational Unit")){
    //_____ skip the indicators whose datim source has not been defined____   
        if(!conn.rs.getString("imiscolumn").equals("")){
        // mark the columns that are active
        activecolumns.add("1");
            //query for fetching data from imis into datim output
        imisqry=conn.rs.getString("orgunits")+","+conn.rs.getString("imiscolumn")+" From "+conn.rs.getString("maintable")+" "+conn.rs.getString("jointable")+" where "+conn.rs.getString("where");
           
        //System.out.println("imisqry for "+eleid+"::"+imisqry);
        
        imishm.put("col"+i,imisqry);
        
        }
        else {
        
        // mark the columns that are active but whose source is not in IMIS
        activecolumns.add("2");
        imishm.put("col"+i,"");
        
        }
        
    }
    //the orgunit heder columns
    else {
    // mark the columns that are active
        activecolumns.add("0");
        imishm.put("col"+i,"");
    }
    
    
    
    }
    //where the element is mapped in IMIS
    else {
    // mark the columns that are active
        activecolumns.add("0");
        imishm.put("col"+i,"");
    
    }
    
    
}//__________________________________________end of header_____________________________



//___________________________________________rows above 0______________________________
    
//[7]________________________________________Now get the values and run insert into datim ______________________________________________
    
else  
{
    //column 8 (counting from 0) is the mflcode
    
if(i<10)
{       

if(i==8){mflcode=value.replace("keipsl","");}
    
    
}
else {
//____check if imis queries are already set_____
//use active columns arraylist  to determine the active data columns 
 // 0 are org units columns, 1 are active elements  , 2 are active elements without Imis data source
 
 if(activecolumns.get(i).equals("1")){
 
     //@lastmonthkey
     //@startkey
     //@endkey
     //@startdatekey
     //@enddatekey
     //@mflcode
     
     //pull imis qry from the hashmap
 String extractimisdata=imishm.get("col"+i).
         replace("@mflcode","'"+mflcode+"'").
         replace("@startkey",startdate.substring(0,6)).
         replace("@endkey",enddate.substring(0,6)).
         replace("@lastmonthkey",enddate.substring(0,6)).
         replace("@startdatekey",startdate).
         replace("@enddatekey",enddate);
 //replace startdate, enddate, mflcode, lastdate
 
   
 
     if(!extractimisdata.contains("group")){ extractimisdata+=" group by mflcode";}
      
     System.out.println("  Imisreadyquerry "+extractimisdata);
        
     conn.rs2=conn.st2.executeQuery(extractimisdata);
     
     while(conn.rs2.next()){
/**
County,
SubCounty,  
Facility,
mflcode ,
SupportType,
SUM(HV0201+HV0205)
**/
         
         
     String county=conn.rs2.getString(1);
     String subcounty=conn.rs2.getString(2);
     String facility=conn.rs2.getString(3);
     String supporttype=conn.rs2.getString(5);
     int imisvalue=conn.rs2.getInt(6);
     int datimvalue=0;
     //System.out.println((i-1)+"_Element in position ");
     System.out.println("_Element in position_"+elementidhm.get("column_"+i));
     String eleid=elementidhm.get("column_"+i);
     String id=eleid+"_"+mflcode+"_"+enddate.substring(0,6);
     if(!value.equals(""))
     {
     datimvalue=new Integer(value);
     }
     
     String datiminsert="replace into datim_imis(id,county,subcounty,facilityname,mflcode,indicatorid,supporttype,datimvalue,imisvalue,startyearmonth,endyearmonth) "
                                   + "values ('"+id+"','"+county+"',?,?,'"+mflcode+"','"+eleid+"','"+supporttype+"','"+datimvalue+"','"+imisvalue+"','"+startdate.substring(0,6)+"','"+enddate.substring(0,6)+"')";
     
     conn.pst=conn.conn.prepareStatement(datiminsert);
                         conn.pst.setString(1,subcounty);
                         conn.pst.setString(2,facility);
						 conn.pst.executeUpdate();
                          System.out.println("insertcode:: "+conn.pst);
     
     }
     
 }
 else if(activecolumns.get(i).equals("1")){
 
 
 
 }
 
    
}
            
            
            

        }



    //        System.out.print(i+""+value+"_|_");
                }
                else {
                    
                    System.out.println("end of valid cells");
                    break;
                }
                
                //----------------------------------------------------------------------
         // System.out.println("Row no"+rowcount+" I is is "+i);
            i++;
            }     //end of row
    rowcount++;
    
}
            
            
            
}//end of worksheets loop

          }//end of checking if excel file is valid
          if(conn.rs!=null){try {
              conn.rs.close();
          } catch (SQLException ex) {
              Logger.getLogger(DATIM_IMIS.class.getName()).log(Level.SEVERE, null, ex);
          }
          }         if(conn.st!=null){try {
              conn.st.close();
          } catch (SQLException ex) {
              Logger.getLogger(DATIM_IMIS.class.getName()).log(Level.SEVERE, null, ex);
          }
          }         if(conn.pst!=null){try {
              conn.pst.close();
          } catch (SQLException ex) {
              Logger.getLogger(DATIM_IMIS.class.getName()).log(Level.SEVERE, null, ex);
          }
          }         
          String sessionText="<br/><b> "+added+ "</b> New data added <br/> <b> "+updated+"()</b> updated facilities<br> <br> <b>"+missing+"</b> sites not in Imis Facilities List ";
          session.setAttribute("uploadedpns"," File name is "+fileName+". "+ sessionText);
          response.sendRedirect(nextpage);
      } catch (SQLException ex) {  
          Logger.getLogger(DATIM_IMIS.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    public void generateOutput( HttpServletResponse response, String startyearmonth,String endyearmonth, dbConn conn, HSSFWorkbook wb, String where) throws SQLException, IOException{
        
        
      HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 18);
        font.setFontName("Cambria");
        font.setColor((short) 0000);
        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font2 = wb.createFont();
        font2.setFontName("Cambria");
        font2.setColor((short) 0000);
        CellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        HSSFCellStyle stborder = wb.createCellStyle();
        stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCellStyle stylex = wb.createCellStyle();
        stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylex.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        HSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFFont fontx = wb.createFont();
        fontx.setColor(HSSFColor.BLACK.index);
        fontx.setFontName("Cambria");
        stylex.setFont(fontx);
        stylex.setWrapText(true);

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        HSSFSheet shet = wb.createSheet("Datim IMIS Output");  
        
        
         HSSFRow rw0=shet.createRow(1);
        HSSFCell cell = rw0.createCell(0);
                    cell.setCellValue("IMIS DATIM Gap Analysis between "+startyearmonth+" and "+endyearmonth);
                    cell.setCellStyle(style);
        shet.addMergedRegion(new CellRangeAddress(1, 1, 0,10));
                    
                int count1  = 3;
        
    
String qry=" SELECT county as County, " +
" subcounty as Subcounty, " +
" facilityname as Facility, " +
" mflcode as MFLCode,'' as ward, " +
" supporttype,label as Indicator,technicalarea as 'Main Indicator',datimvalue as 'DATIM', imisvalue as 'IMIS', tofauti as 'Variance', startyearmonth as 'Start YearMonth',endyearmonth as 'End YearMonth' FROM " +
" internal_system.datim_imis " +
" join datim_imis_map on datim_imis_map.elementid=datim_imis.indicatorid " +
" where (startyearmonth between "+startyearmonth+" and "+endyearmonth+") "+where+" order by tofauti desc ";
    
    conn.rs=conn.st.executeQuery(qry);
   
    
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
                HSSFRow rw = shet.createRow(count);
rw.setHeightInPoints(26);
                for (int i = 1; i <= columnCount; i++) {

                    mycolumns.add(metaData.getColumnLabel(i));
                    HSSFCell cell0 = rw.createCell(i - 1);
                    cell0.setCellValue(metaData.getColumnLabel(i));
                    cell0.setCellStyle(stylex);

                    //create row header
                }//end of for loop
                count++;
            }//end of if
            //data rows     
            HSSFRow rw = shet.createRow(count);

            for (int a = 0; a < columnCount; a++) {
                //System.out.print(mycolumns.get(a) + ":" + conn.rs.getString("" + mycolumns.get(a)));

                HSSFCell cell0 = rw.createCell(a);
                 if(isNumeric(conn.rs.getString("" + mycolumns.get(a)))){
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
        
        shet.setAutoFilter(new CellRangeAddress(count1, count - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
        for (int i = 0; i <= columnCount; i++) {
            shet.autoSizeColumn(i);
        }

        shet.setDisplayGridlines(false);
        shet.createFreezePane(6, 4);
    
      IdGenerator IG = new IdGenerator();
        String createdOn = IG.CreatedOn();

        System.out.println("" + "Imis_Datim_reports_Gen_" + createdOn.trim() + ".xls");

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=" + "IMIS_DATIM_Validation_rpt_between_"+startyearmonth+"_to_"+startyearmonth+"__gen_" + createdOn.trim() + ".xls");
         response.setHeader("Set-Cookie","fileDownload=true; path=/");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
    
    }
    
    
    
    
       
  public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
    
    
    
}
