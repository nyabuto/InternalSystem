/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prep;

import database.OSValidator;
import database.dbConn;
import form1a.lockf1a;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import scripts.copytemplates;

/**
 *
 * @author EKaunda
 */
public class downloadPrepTemplate extends HttpServlet {

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
        //response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
            
//_______________________ Create excel templates _________________________________________

            XSSFWorkbook wb = null;

           

            String allpath = getServletContext().getRealPath("/prep.xlsx");

         

        
            String mydrive = allpath.substring(0, 1);
            // wb = new XSSFWorkbook( OPCPackage.open(allpath) );

            Date da = new Date();
            String dat2 = da.toString().replace(" ", "_");
            dat2 = dat2.toString().replace(":", "_");
            
             
     String mwakamwezi="";
            String year = "";
            String monthar[] = null;
            String month = "";
            String facilityid = "";
            String county = "";
            String facilityname = "";
            String subcountyname = "";
            String subcountynamelist = "";
            String mflcode = "";
            String todeletefiles="";

            String subcounty = "(";
            String subcountyar[] = null;
            String facilityarr[] = null;
            String facility = "(";

            if (request.getParameter("year") != null) 
            {
                year = request.getParameter("year");
            }

            if (request.getParameterValues("month") != null) 
            {
                monthar = request.getParameterValues("month");
            }

            if (request.getParameter("county") != null) 
            {
                county = request.getParameter("county");

            }

            if (request.getParameterValues("subcounty") != null) 
            {

                subcountyar = request.getParameterValues("subcounty");

                for (int a = 0; a < subcountyar.length; a++) {

                    if (a == subcountyar.length - 1) {

                        subcounty += subcountyar[a] + ")";

                    } else {

                        subcounty += subcountyar[a] + ",";

                    }

                }

            }

            if (request.getParameterValues("facility") != null) {
                facilityarr = request.getParameterValues("facility");

                for (int a = 0; a < facilityarr.length; a++) {

                    if (a == facilityarr.length - 1) {

                        facility += facilityarr[a] + ")";

                    } else {

                        facility += facilityarr[a] + ",";

                    }
                }
            }

            //do a loop with all the parameters above considered
            String finalpath = "";

            String where = " and subpartnera.active=1 and ART=1";

            if (!subcounty.equals("(")) {
                where += " and district.districtid in  " + subcounty;

            }

            if (!facility.equals("(")) {
                where += " and subpartnera.SubPartnerID in  " + facility;

            }
         if (!county.equals("")) {
                where += " and district.CountyID = " + county;

            }
            
            String getCount = " select count(*) as facils from subpartnera join ( district join county on county.countyid=district.countyid) on subpartnera.DistrictID=district.DistrictID "
                    + " " + where + " ";
            
            String fetchqry = " select county,districtnom as subcounty ,SubPartnerNom as Facility,CentreSanteId as mflcode,subpartnerid as facilid from subpartnera join ( district join county on county.countyid=district.countyid) on subpartnera.DistrictID=district.DistrictID "
                    + " " + where + " ";
            System.out.println("" + fetchqry);
            dbConn conn = new dbConn();
  int countworkbooks = 0;
            conn.rs = conn.st.executeQuery(getCount);
              while (conn.rs.next()) 
              {
              
               countworkbooks = conn.rs.getInt("facils");   
              
              }
            
             int runhashmap=0;
              HashMap<String, String> hm= null;
              HashMap<String, String> hm_pn= null;
            conn.rs = conn.st.executeQuery(fetchqry);
          
            int curworkbook = 0;
            String workbooknames="";
            String workbookidentifier="";
            
             String npt = "";
             workbooknames="";
            
            while (conn.rs.next()) {
               runhashmap++; 
                    county = conn.rs.getString("county");
                    facilityname = conn.rs.getString("Facility");
                    subcountyname = conn.rs.getString("subcounty");
                    mflcode = conn.rs.getString("mflcode");
                    if(!subcountynamelist.contains(subcountyname)){
                    subcountynamelist+=subcountyname+"_";
                    }
                facilityid = conn.rs.getString("facilid");
                    System.out.println("Mflcode: "+mflcode);
                    System.out.println("Subcounty: "+subcountyname);
                    System.out.println("subcountylist: "+subcountynamelist);
                    System.out.println("workbooks: "+countworkbooks);
                    
                //create workbook for each facility
                
                String smonth=monthar[0];
String emonth=monthar[monthar.length-1];
String mwezi="";
if(smonth.equals(emonth)){  mwezi=emonth;  } else { mwezi=smonth+"_to_"+emonth; }
                
                //The suffix should be facility, year, firstmonth_lastmonth

            String np = "";
            String zippath="";
            if (OSValidator.isWindows()) 
            {
                np = mydrive + ":\\HSDSA\\InternalSystem\\F1a\\Templates\\Consolidated_Prep_" + dat2 +generateRandomNumber(100, 2000)+ ".xlsx";
                 if(curworkbook==1){//if the workbooks are more than 1
                 zippath = mydrive + ":\\HSDSA\\InternalSystem\\F1a\\Templates\\Consolidated_Prep_Zip_" + county.replace(" ", "_")+"_"+year +"_"+mwezi+ ".zip";
                                   }
            }
            else if (OSValidator.isUnix()) 
            {
                np = "/HSDSA/InternalSystem/F1a/Templates/Consolidated_Prep_" + dat2+generateRandomNumber(100, 2000) + ".xlsx";
                if(curworkbook==1){ //if the workbooks are more than 1
                 zippath = "/HSDSA/InternalSystem/F1a/Templates/Consolidated_Prep_Zip_" + county.replace(" ", "_")+"_"+year+"_"+mwezi+ ".zip";
                                  }
                
            }
            
           todeletefiles+=np+"@"; 
            
            
            
            
           
            String sr = getServletContext().getRealPath("/prep.xlsx");
            //check if file exists

            //first time , it should create those folders that host the macro file
            File f = new File(np);
            if (!f.exists() && !f.isDirectory()) 
            {
                /* do something */
                copytemplates ct = new copytemplates();
                ct.transfermacros(sr, np);
                //rem np is the destination file name  

                System.out.println("Copying  template..");

            } 
            
            else //copy the file alone  
            {
                copytemplates ct = new copytemplates();

                ct.copymacros(sr, np);

            }
                
            System.out.println("New Path "+np);
                
            String filepth = np;

            File allpathfile = new File(filepth);

            OPCPackage pkg = OPCPackage.open(allpathfile);

           
            wb = new XSSFWorkbook(pkg);
            
            
            
   //_______________________________________________________________         
            

                curworkbook++;
                
                
                
                


                for (int a = 0; a < monthar.length; a++) {


                    //each workbook here should have all the months 
                    //loop through the months
                    //if the user has selected the county alone,then get data for all the facilities in the county
                    
                    
                    int mwaka=0;
                    month = monthar[a];
                    
                    if(month.length()==1){month="0"+month;}
                    
                    if(new Integer(month)>=10)
                    {
                    mwaka=new Integer(year)-1;
                    
                    }
                    else{
                    mwaka=new Integer(year);
                    }
                    
                    mwakamwezi=mwaka+month;
                    
                    
                    String lini=mwaka+"-"+month+"-"+monthDays(month,mwaka);
                    
                    
                    
                   if(runhashmap==1)
                   {
                       String sp="call internal_system.sp_pull_data_prep('"+lini+"','')";
                       String sp_pn="call internal_system.sp_pull_data_prepnew('"+lini+"','')";
                    
                    System.out.println("Stored Procedure: "+sp);
                    System.out.println("Stored Procedure: "+sp_pn);
                     hm= convertResultSetToMap(conn.st1.executeQuery(sp));
                     hm_pn= convertResultSetToMap_PrepNew(conn.st1.executeQuery(sp_pn));
                   }
              
                   
                   /***
                    * 
prep_1month_ago     -2
prep_3month_ago     -3
prep_6month_ago     -4
prep_9month_ago     -5
prep_12month_ago    -6
                    * 
                    * 
                    **/
                   
                    //if(month.equals(npt))
                                                            
                    if(a<monthar.length-1)
                    {
                    wb.cloneSheet(1);
                    }
                    //System.out.println("no of sheets:"+wb.getNumberOfSheets());
                    XSSFSheet shet=wb.getSheet("Prep Partner Performance");
                    //wb.setSheetName(a+1, monthName(monthar[a]));
                    //shet.protectSheet("f1av4");
                    //in here, creata sheets    
                    XSSFRow rw = shet.getRow(1);
                    
                    XSSFCell facilcl= rw.getCell(2);
                    facilcl.setCellValue(facilityname);
                    
                    XSSFCell mflcl= rw.getCell(6);
                    mflcl.setCellValue(mflcode);
                    
                    
                    XSSFCell distcl= rw.getCell(11);
                    distcl.setCellValue(subcountyname);
                    
                    
                    XSSFCell countycl= rw.getCell(20);
                    countycl.setCellValue(county);
                    
                    
                    XSSFCell monthcl= rw.getCell(25);
                    monthcl.setCellValue(month);
                    
                    
                    XSSFCell yearcl= rw.getCell(30);
                    yearcl.setCellValue(mwaka);
                    
                    
                  lockf1a lf1a= new lockf1a();
                  
                 //wb= lf1a.lockexcel(shet, wb);
                 
                 
                 
                         //txcurr_113_202007   ie indicator_facilityid_yearmonth_indicatorcolumn
                    //tx_new 7
                    //txcurr _10
                    //p_txcurr _6
                    
/**                    
prep_1month_ago     -2
prep_3month_ago     -3
prep_6month_ago     -4
prep_9month_ago     -5
prep_12month_ago    -6
*/
                    
                         String rw_suffix =facilityid+"_"+mwakamwezi;
                    
                    String [] required_rows={"1:prep_1month_ago_"+rw_suffix,"2:prep_3month_ago_"+rw_suffix,"3:prep_6month_ago_"+rw_suffix,"4:prep_9month_ago_"+rw_suffix,"5:prep_12month_ago_"+rw_suffix};
                  
                    
                    
                    
                    
          ArrayList allin = new ArrayList();
          
//allin.add("muk");
//allin.add("fuk");
allin.add("m1");
allin.add("f1");
allin.add("m4");
allin.add("f4");
allin.add("m9");
allin.add("f9");
allin.add("m14");
allin.add("f14");
allin.add("m19");
allin.add("f19");
allin.add("m24");
allin.add("f24");
allin.add("m29");
allin.add("f29");
allin.add("m34");
allin.add("f34");
allin.add("m39");
allin.add("f39");
allin.add("m44");
allin.add("f44");
allin.add("m49");
allin.add("f49");
allin.add("m50");
allin.add("f50");
allin.add("m54");
allin.add("f54");
allin.add("m59");
allin.add("f59");
allin.add("m60");
allin.add("f60");
allin.add("total");
                    
                    
                  wb=  populatePrep(wb, required_rows, allin, hm);
                 
  String [] required_rows_prepnew={"1:528_"+rw_suffix,"2:530_"+rw_suffix,"3:527_"+rw_suffix,"4:531_"+rw_suffix,"5:529_"+rw_suffix,"6:526_"+rw_suffix,"7:532_"+rw_suffix,"8:525_"+rw_suffix,"9:565_"+rw_suffix,"10:566_"+rw_suffix,"11:533_"+rw_suffix};
                 
                  //525,526,527,528,529,530,531,532,533,565,566
                  
                 





                  
ArrayList allin_pn = new ArrayList();
allin_pn.add("m19");
allin_pn.add("f19");
allin_pn.add("m24");
allin_pn.add("f24");
allin_pn.add("m29");
allin_pn.add("f29");
allin_pn.add("m34");
allin_pn.add("f34");
allin_pn.add("m39");
allin_pn.add("f39");
allin_pn.add("m44");
allin_pn.add("f44");
allin_pn.add("m49");
allin_pn.add("f49");
allin_pn.add("m50");
allin_pn.add("f50");
allin_pn.add("total");
                  
 wb=  populatePrepnew(wb, required_rows_prepnew, allin_pn, hm_pn);
                  
                }
                //outside here, create workbooks
                
                  npt = "";
               
                  
                  workbookidentifier=facilityname+"_"+year +"_"+mwezi;
                  
            if (OSValidator.isWindows()) 
            {
                npt = mydrive + ":\\HSDSA\\InternalSystem\\F1a\\Templates\\Monthly_Prep_" + facilityname.replace(" ", "_")+"_"+year +"_"+mwezi+ ".xlsx";
            }
            else if (OSValidator.isUnix()) 
            {
                npt = "/HSDSA/InternalSystem/F1a/Templates/Monthly_Prep_" + facilityname.replace(" ", "_")+"_"+year+"_"+mwezi+ ".xlsx";
            }
                

  //if this is not the last workbook name, then           
if(curworkbook<countworkbooks)
{
 //workbooknames+=facilityname.replace(" ", "_")+"_"+year+"_"+mwezi+ ".xlsx"+"@";
 workbooknames+=npt+"@";
}
else 
{  
 //workbooknames+=facilityname.replace(" ", "_")+"_"+year+"_"+mwezi+ ".xlsx";
 workbooknames+=npt;
 
}



            String pathtodelete = npt;
            
            //pkg.close();
     
            if(countworkbooks==1){
            
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte[] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=" + "Monthly_Prep_"+workbookidentifier.replace(" ", "_") + ".xlsx");
response.setHeader("Set-Cookie","fileDownload=true; path=/");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
 pkg.close();
             System.out.println("download workbooks:"+workbooknames);  
             
             File file= new File(pathtodelete);
        
        if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
             
            }
            else 
            {
                
            //write each excel file in the local drive
           
            FileOutputStream outputStream = new FileOutputStream(npt);
            wb.write(outputStream);
            outputStream.flush();
              
            //pkg.close();
             
            //System.out.println("Create workbook file:"+workbooknames);
            
            }
            
            if(curworkbook==countworkbooks && countworkbooks>1 ){
            
            //write the zip
            
                System.out.println(" Last workbook "+workbooknames);
            
            String files[]=workbooknames.split("@");
            

               
            
            
       String filename=county.replace(" ", "_")+"County_"+year +"_"+mwezi;
       
          if(subcountyar!=null){
          if(subcountyar.length<=3){
              filename=county.replace(" ", "_")+"_county_"+subcountynamelist.replace(" ", "_")+"_"+year +"_"+mwezi; 
          
                                  }     
          }

byte[] outArray = zipFiles(files);
response.setContentType("application/zip");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=Consolidated_Prep_"+filename+".zip");
response.setHeader("Set-Cookie","fileDownload=true; path=/");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
 pkg.close();
 
 
                        for (String file1 : files) {
                            File file = new File(file1);
                            if (!file1.equals("")) {
                                if (file.delete()) {
                                    System.out.println(file.getName() + " is deleted!");
                                } else {
                                    System.out.println(file.getName() +" Delete operation failed.");
                                }
                            }       }  
                        
                        
                        
                         System.out.println("To delete "+todeletefiles);
            String filescopy[]=todeletefiles.split("@");
                        for (String filescopy1 : filescopy) {
                            if (!filescopy1.equals("")) {
                                File file = new File(filescopy1);
                                if(file.delete()){
                                    System.out.println(file.getName() + "  deleted!");
                                }else{
                                    System.out.println("Delete operation  failed.");
                                }           }
                        }
                        
                        
            
            
            }//end of if it is the last workbook
            
            
            
            
            
            }//end of while loop
            
            
            
            
            

        } finally {
          // out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(downloadPrepTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(downloadPrepTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(downloadPrepTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(downloadPrepTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    public String  monthName(String monthno){
        String mn="";
    if(monthno.equals("1")){
    mn="Jan";
    }
    else if(monthno.equals("2")){
    mn="Feb";
    }
    else if(monthno.equals("3")){
    mn="Mar";
    }
     else if(monthno.equals("4")){
    mn="Apr";
    }   
    else if(monthno.equals("5")){
    mn="May";
    }  
else if(monthno.equals("6")){
    mn="Jun";
    } 
    
    else if(monthno.equals("7")){
    mn="Jul";
    } 
     else if(monthno.equals("8")){
    mn="Aug";
    } 
    
    else if(monthno.equals("9")){
    mn="Sep";
    }
    else if(monthno.equals("10")){
    mn="Oct";
    }
    else if(monthno.equals("11")){
    mn="Nov";
    }
    else if(monthno.equals("12")){
    mn="Dec";
    }
    return mn;
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
    
      public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
    
      public HashMap<String,String> convertResultSetToMap(ResultSet rs) throws SQLException{
          
          
          //Create an arraylist for all the indicators
          
          ArrayList allin = new ArrayList();
          
//allin.add("muk");
//allin.add("fuk");
allin.add("m1");
allin.add("f1");
allin.add("m4");
allin.add("f4");
allin.add("m9");
allin.add("f9");
allin.add("m14");
allin.add("f14");
allin.add("m19");
allin.add("f19");
allin.add("m24");
allin.add("f24");
allin.add("m29");
allin.add("f29");
allin.add("m34");
allin.add("f34");
allin.add("m39");
allin.add("f39");
allin.add("m44");
allin.add("f44");
allin.add("m49");
allin.add("f49");
allin.add("m50");
allin.add("f50");
allin.add("m54");
allin.add("f54");
allin.add("m59");
allin.add("f59");
allin.add("m60");
allin.add("f60");
allin.add("total");

      
HashMap<String,String> hm = new HashMap<String,String>();
          
ResultSetMetaData md = rs.getMetaData();
          
           int columns = md.getColumnCount();
           
           while (rs.next()){
           String indicid=rs.getString("indicid");
               for(int a=1;a<=columns;a++)
               {
                   String colname=md.getColumnName(a);
                   
               if(allin.contains(colname))
               {
                   
                hm.put(indicid+"_"+colname, rs.getString(colname));
                   
               }
               }               
                
                   
           
           }          
          
          
      
      return hm;
      }
       
      public HashMap<String,String> convertResultSetToMap_PrepNew(ResultSet rs) throws SQLException{
          
          
          //Create an arraylist for all the indicators
          
ArrayList allin = new ArrayList();
          
//allin.add("muk");
//allin.add("fuk");

allin.add("m19");
allin.add("f19");

allin.add("m24");
allin.add("f24");

allin.add("m29");
allin.add("f29");

allin.add("m34");
allin.add("f34");

allin.add("m39");
allin.add("f39");

allin.add("m44");
allin.add("f44");

allin.add("m49");
allin.add("f49");

allin.add("m50");
allin.add("f50");

allin.add("total");

      
HashMap<String,String> hm1 = new HashMap<String,String>();
          
ResultSetMetaData md = rs.getMetaData();
          
           int columns = md.getColumnCount();
           
           while (rs.next()){
           String indicid=rs.getString("indicid");
               for(int a=1;a<=columns;a++)
               {
                   String colname=md.getColumnName(a);
                   
               if(allin.contains(colname))
               {
                   
                hm1.put(indicid+"_"+colname, rs.getString(colname));
                   
               }
               }               
                
                   
           
           }          
          
          
      
      return hm1;
      }
       
      
      public XSSFWorkbook populatePrep(XSSFWorkbook wb, String[] RowstoUpdate,ArrayList ColstoUpdate, HashMap<String, String > data_to_populatedatahm  )
      {
          //System.out.println(" Hash map is "+data_to_populatedatahm);
          
          int sheetcount=wb.getNumberOfSheets();
          
          ArrayList sheetsAL= new ArrayList();
         
          for(int p=0;p<sheetcount;p++){
          if(wb.getSheetName(p).equals("prep_history")){
              
              sheetsAL.add(wb.getSheetName(p));
          
          }
          
          }
          
          
          for(int a=0;a<sheetsAL.size(); a++)
          {
          
            XSSFSheet sht = wb.getSheet(sheetsAL.get(a).toString());
            for(int b=0; b<RowstoUpdate.length; b++)
            {
             String [] row_key=RowstoUpdate[b].split(":");   
             //data is saved as row:Indicator eg 4:tx_new
             
                System.out.println("sheet is "+sheetsAL.get(a).toString());
                System.out.println("row is "+row_key[0]);
                
             //loop through all the rows while updating data
             
             
             
             XSSFRow rw=sht.getRow(new Integer(row_key[0]));
            for(int c=0; c<ColstoUpdate.size(); c++)
            {
            
             XSSFCell cl=rw.getCell(c+2);
             //txcurr_113_202007
             
             String fullkey=row_key[1]+"_"+ColstoUpdate.get(c);
                System.out.println("Key to search for:"+fullkey);
             
             if(data_to_populatedatahm.get(fullkey)!=null){
             if(isNumeric(data_to_populatedatahm.get(fullkey))){
                 
                 System.out.println(" Value ya "+fullkey+" ni "+data_to_populatedatahm.get(fullkey));
                 
               cl.setCellValue( new Double(data_to_populatedatahm.get(fullkey).toString()));
               
                //System.out.println("Populated as Integer");  
             }
             else {
                 //System.out.println("Not Populated as Integer");
                cl.setCellValue(data_to_populatedatahm.get(fullkey));
             }
             
           
             }
             else {
             
                 //System.out.println(" Hash map is null ");
             }
            
            }
            }
         
              
          }
         
        // XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);  
      wb.setForceFormulaRecalculation(true);
      return wb;
      }
      
      public XSSFWorkbook populatePrepnew(XSSFWorkbook wb, String[] RowstoUpdate,ArrayList ColstoUpdate, HashMap<String, String > data_to_populatedatahm  )
      {
          //System.out.println(" Hash map is "+data_to_populatedatahm);
          
          int sheetcount=wb.getNumberOfSheets();
          
          ArrayList sheetsAL= new ArrayList();
         
          for(int p=0;p<sheetcount;p++){
          if(wb.getSheetName(p).equals("prep_new_f1a")){
              
              sheetsAL.add(wb.getSheetName(p));
          
          }
          
          }
          
          
          for(int a=0;a<sheetsAL.size(); a++)
          {
          
            XSSFSheet sht = wb.getSheet(sheetsAL.get(a).toString());
            for(int b=0; b<RowstoUpdate.length; b++)
            {
             String [] row_key=RowstoUpdate[b].split(":");   
             //data is saved as row:Indicator eg 4:tx_new
             
                System.out.println("sheet is "+sheetsAL.get(a).toString());
                System.out.println("row is "+row_key[0]);
                
             //loop through all the rows while updating data
             
             XSSFRow rw=sht.getRow(new Integer(row_key[0]));
            for(int c=0; c<ColstoUpdate.size(); c++)
            {
                System.out.println("Row is "+row_key[0]+"c is: "+c+" and thus cell is "+(c+2));
             XSSFCell cl=rw.getCell(c+2);
             //txcurr_113_202007
             
             String fullkey=row_key[1]+"_"+ColstoUpdate.get(c);
                System.out.println("Key to search for:"+fullkey);
             
             if(data_to_populatedatahm.get(fullkey)!=null){
             if(isNumeric(data_to_populatedatahm.get(fullkey))){
                 
                 System.out.println(" Value ya "+fullkey+" ni "+data_to_populatedatahm.get(fullkey));
                 
               cl.setCellValue(new Double(data_to_populatedatahm.get(fullkey).toString()));
               
                //System.out.println("Populated as Integer");  
             }
             else {
                 //System.out.println("Not Populated as Integer");
                cl.setCellValue(data_to_populatedatahm.get(fullkey));
             }
             
           
             }
             else {
             
                 //System.out.println(" Hash map is null ");
             }
            
            }
            }
         
              
          }
         
        // XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);  
      wb.setForceFormulaRecalculation(true);
      return wb;
      }
      
  
        public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
      
    
        
    public String  monthDays(String monthno,int mwaka){
        String mn="";
    if(monthno.equals("01")){
    mn="31";
    }
    else if(monthno.equals("02") ){
        
         if (((mwaka % 4 == 0) && (mwaka % 100!= 0)) || (mwaka%400 == 0))
        mn="29";
      else
         mn="28";
   }
        
   
    else if(monthno.equals("03")){
    mn="31";
    }
     else if(monthno.equals("04")){
    mn="30";
    }   
    else if(monthno.equals("05")){
    mn="31";
    }  
else if(monthno.equals("06")){
    mn="30";
    } 
    
    else if(monthno.equals("07")){
    mn="31";
    } 
     else if(monthno.equals("08")){
    mn="31";
    } 
    
    else if(monthno.equals("09")){
    mn="30";
    }
    else if(monthno.equals("10")){
    mn="31";
    }
    else if(monthno.equals("11")){
    mn="30";
    }
    else if(monthno.equals("12")){
    mn="31";
    }
    return mn;
    }
      
      
}
