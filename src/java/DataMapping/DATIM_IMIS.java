/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

 */

package DataMapping;

import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
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
 
    
      try {
          
          int year,checker,missing = 0,added = 0,updated = 0;
          String county_name,county_id, district_name,district_id,hf_name,hf_id,quarter;
          session=request.getSession();
          id="";
         
          String startdate="";
          String enddate="";
          
          
          
          dbConn conn= new dbConn();
          
//[1]________________________________________get start and end date______________________________________________
          
          String startMonth, endMonth;
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
//[2]________________________________________upload excel______________________________________________
         

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

              full_path = fileSaveDir.getAbsolutePath() + "\\" + fileName;

              System.out.println("the saved file directory is  :  " + full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................

              FileInputStream fileInputStream = new FileInputStream(full_path);
              XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

              int totalsheets = workbook.getNumberOfSheets();
              DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale

              
              
//[3]________________________________________loop through excel data______________________________________________         


for(int a=0;a<totalsheets;a++){
    
     HashMap<String, String> imishm = new HashMap<String, String>();
    
   int rowcount=0; 
    XSSFSheet sheet = workbook.getSheetAt(a);
    
    
    System.out.println( a+" ("+workbook.getSheetName(a)+") out of "+totalsheets+" sheets");
    
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

while (cellIterator.hasNext()) {
                
//----------------------------------------------------------------------
                
XSSFCell cellfacil = sheet.getRow(rowcount).getCell((short) i);

if(sheet.getRow(rowcount).getCell((short) i)!=null)
                {
                
if(cellfacil.getCellType()==0){
    //numeric
    value =""+(int)cellfacil.getNumericCellValue();
}
else if(cellfacil.getCellType()==1){
    value =cellfacil.getStringCellValue();
}
//[6]________________________________________get headers from excel and find which column stores which data______________________________________________
if(rowcount==0){

    
    String getheaders="select * from datim_imis_map where datim_element='"+value+"' and active='1'";
    conn.rs=conn.st.executeQuery(getheaders);
    
    while(conn.rs.next()){
        
        
        
    
    if(!conn.rs.getString("technicalarea").equals("Organisational Unit")){
        
        String imisqry=conn.rs.getString("orgunits")+conn.rs.getString("imiscolumn");
        
        imishm.put("col"+i,imisqry);
    }
    
    
    
    }
    
    
}


            System.out.print(i+""+facilityName+"_|_");
                }
                else {
                    
                    System.out.println("");
                    break;
                }
                
                //----------------------------------------------------------------------
         
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
    
}
