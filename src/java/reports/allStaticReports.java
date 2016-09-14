/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
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
 * @author Geofrey Nyabuto
 */
public class allStaticReports extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String monthrange = "";
        String periodlabel = "";
        String period = "Month"; //The default one
        try {
            response.setContentType("text/html;charset=UTF-8");

    //a page to get Report of all the servlets
            String months[] = null;

            String year = "2015";
            String month = "";
            String county = "";
            String form = "moh731";

            if (request.getParameter("year") != null) {
                year = request.getParameter("year");
            }

            if (request.getParameter("county") != null) {
                county = request.getParameter("county");
            }

            if (request.getParameter("month") != null) {
                months = request.getParameterValues("month");
            }

            if (request.getParameter("form") != null) {
                form = request.getParameter("form");
            }
            String pivotform = form;
            if (form.equalsIgnoreCase("MOH 731")) {
                form = "MOH731";
            }
            if (form.equalsIgnoreCase("MOH 711A")) {
                form = "MOH711";
            }
            if (form.equalsIgnoreCase("MOH 711 (New)")) {
                form = "moh711_new";
            }
            String facilitywhere = "";
            String yearwhere = "";
            String monthwhere = "";
            String countywhere = "";
            String districtwhere = "";
            String reporttype = "";

            if (!year.equals("")) {

                yearwhere = " and Annee = '" + year + "'";

            }
            if (!county.equals("")) {

                countywhere = " and countyid = '" + county + "'";

            }

            dbConn conn = new dbConn();

    //an array to store haeder information.
    //the header information should appear only if certain parameters are met
            //The parameters listed in here can be removed if the report type doesnt require some parameters
            ArrayList Headerorgunits = new ArrayList();
            Headerorgunits.add(period);
            Headerorgunits.add("County");
            Headerorgunits.add("Sub-County");
            Headerorgunits.add("Facility");
            Headerorgunits.add("MFL Code");
            

            //An arralist to store a list of columns that will be selected from the database
            ArrayList dbcolumns = new ArrayList();

            ArrayList labels = new ArrayList();

            ArrayList tablename = new ArrayList();

            ArrayList iscumulative = new ArrayList();

            ArrayList ispercent = new ArrayList();

   // ArrayList isactive=new ArrayList();
            //An arralist to store a list of worksheets that will be selected from the sections and the respective service area to determine the facilities whose data will appear in that sheet
            ArrayList worksheets = new ArrayList();
            //An arralist to store distinct worksheets. This will be derived from the the sections column
            ArrayList distinctsheets = new ArrayList();
            ArrayList distinctservicearea = new ArrayList();

   //create an array to store the number of row for each excel worksheet. 
            //This will help in retrieving the number of rows for each month since we are wring data for different months with increasing rows.
            //the size of that array will be determined by the number of excel worksheets
            String selectdistinctworksheet = "select section,servicearea from pivottable where form='" + form.replace("_", "") + "' and active='1' group by section order by order_per_form";

            conn.rs = conn.st.executeQuery(selectdistinctworksheet);

            while (conn.rs.next()) {
                //add the name of distinct sections
                distinctsheets.add(conn.rs.getString(1).replace("/", "_"));

                String servicearea = "  2=2 ";
                if (conn.rs.getString(2) != null) {
                    servicearea = "  " + conn.rs.getString(2) + "=1";
                }
                distinctservicearea.add(servicearea);

            }

            int rowstartpersheet[] = new int[distinctsheets.size()];

    //initialize the row start with 2
            for (int x = 0; x < rowstartpersheet.length; x++) 
            {

                rowstartpersheet[x] = 2;

            }

            String getattribs = "select indicator,label,section,cumulative,percentage,active ,shortlabel from pivottable where form='" + form.replace("_", "") + "' order by order_per_form, section";
            conn.rs = conn.st.executeQuery(getattribs);

            while (conn.rs.next()) {

        //add active indicators only
                if (conn.rs.getString("active").equals("1")) {
    //System.out.println(conn.rs.getString("indicator")+"");
                    //add indicator
                    dbcolumns.add(conn.rs.getString("indicator"));
                    //add label
                    if (form.equalsIgnoreCase("MOH731")) {
                        labels.add(conn.rs.getString("shortlabel") + " \n" + conn.rs.getString("label"));

                    } else {
                        labels.add(conn.rs.getString("label"));
                    }
                    //add worksheets
                    worksheets.add(conn.rs.getString("section").replace("/", "_"));

                    String perc = "0";
                    String cum = "0";

                    if (conn.rs.getString("cumulative") != null) {
                        iscumulative.add(conn.rs.getString("cumulative"));
                    } else {
                        iscumulative.add(cum);
                    }

                    if (conn.rs.getString("percentage") != null) {
                        ispercent.add(conn.rs.getString("percentage"));
                    } else {
                        ispercent.add(perc);
                    }

                }//end of active 

            }  //end of pivot table active
            //labels.add("ART High Volume");
            //labels.add("HTC High Volume");
            //labels.add("PMTCT High Volume");

                //System.out.println(perfacilselect);
            //______________________________________________________________________________________
            //                       CREATE THE WORKSHEETS          
            //______________________________________________________________________________________  
            XSSFWorkbook wb = new XSSFWorkbook();

            XSSFFont font = wb.createFont();
            font.setFontHeightInPoints((short) 18);
            font.setFontName("Cambria");
            font.setColor((short) 0000);
            CellStyle style = wb.createCellStyle();
            style.setFont(font);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            XSSFFont font2 = wb.createFont();
            font2.setFontName("Cambria");
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
            fontx.setFontName("Cambria");
            stylex.setFont(fontx);
            stylex.setWrapText(true);
 
            stylesum.setFont(fontx);
            stylesum.setWrapText(true);
            int cellrange[] = new int[4];

            for (int b = 0; b < distinctsheets.size(); b++) {
                XSSFSheet shet = wb.createSheet(distinctsheets.get(b).toString().toUpperCase());

//Made my life veery simple...
                shet.setDisplayGridlines(false);
                shet.createFreezePane(5, 2);
                int firstcell = 1;

     //shet.setAutoFilter(CellRangeAddress.valueOf("A2:N1"));
    //create headers for that worksheet
                XSSFRow rw = shet.createRow(1);
                int headercellpos = 0;
      //create the orgunit header eg COUNTY | SUBCOUNTY  | FACILITY

                for (int e = 0; e < Headerorgunits.size(); e++) {
                    XSSFCell cell0 = rw.createCell(headercellpos);
                    cell0.setCellValue(Headerorgunits.get(e).toString());
                    cell0.setCellStyle(stylex);

                    headercellpos++;
       //shet.setColumnWidth(e, 6000);  

                }

                //create the indicators header eg HV0101 | HIV 09676  | TOTAL    
                for (int c = 0; c < dbcolumns.size(); c++) {
       //compare if the indicator belongs to the specified section and hence worksheet 
                    //recall, each indicator has got an associated section / worksheet
                    //An indicator should be put as an header in the respective worksheet
                    if (worksheets.get(c).equals(distinctsheets.get(b))) {

                        shet.setColumnWidth(headercellpos, 6000);
                        XSSFCell cell0 = rw.createCell(headercellpos);
                        cell0.setCellValue(labels.get(c).toString());
                        cell0.setCellStyle(stylex);
                        headercellpos++;
                    }//end of comparing if

                }//end of for loop

     //create is validated header
               
                shet.setColumnWidth(headercellpos, 6000);
                XSSFCell cell0 = rw.createCell(headercellpos);
                cell0.setCellValue("ART High Volume");
                cell0.setCellStyle(stylex);
                headercellpos++;
                
                
                shet.setColumnWidth(headercellpos, 6000);
                XSSFCell cell1 = rw.createCell(headercellpos);
                cell1.setCellValue("HTC High Volume");
                cell1.setCellStyle(stylex);
                headercellpos++;
                
                shet.setColumnWidth(headercellpos, 6000);
                XSSFCell cell2 = rw.createCell(headercellpos);
                cell2.setCellValue("PMTCT High Volume");
                cell2.setCellStyle(stylex);
                headercellpos++;
                
                
                shet.setColumnWidth(headercellpos, 6000);
                XSSFCell cell3 = rw.createCell(headercellpos);
                cell3.setCellValue("Form Validated ?");
                cell3.setCellStyle(stylex);
                headercellpos++;

            }

    //______________________________________________________________________________________
            //______________________________________________________________________________________
    //--------------------------------------------------------------------------------------------
            //             MONTH LOOPS
            //-------------------------------------------------------------------------------------------- 
            int cumulativestatingpoint=3;
            boolean cumstartpointnoted=false;
            int colposcopy = 0;
            for (int w = 0; w < months.length; w++) {

                month = months[w];

               
                
                if (!month.equals("")) {

                    monthwhere = " and Mois = '" + month + "'";

                }

                String getMonth = "SELECT name FROM month WHERE id='" + month + "'";
                conn.rs = conn.st.executeQuery(getMonth);

                if (conn.rs.next() == true) {
                    monthrange = conn.rs.getString(1);
                    if (periodlabel.equals("")) {
                        periodlabel = monthrange.substring(0, 3);
                    } else {
                        periodlabel += "_" + monthrange.substring(0, 3);
                    }
                }

                String perfacilselect = "select '" + monthrange + "', CONCAT(UPPER(SUBSTRING(County,1,1)),LOWER(SUBSTRING(County,2))) as County , DistrictNom as District , SubPartnerNom as facility ,CentreSanteId as mflcode ,  district.CountyID as countyid , ";

    //--------------------------------------------------------------------------------------------
                //             PREPARE SELECT
                //--------------------------------------------------------------------------------------------
                //prepare selects
                for (int a = 0; a < dbcolumns.size(); a++) {

     //if the indicator is a percent, get an avaerage
                    if (ispercent.get(a).equals("1")) {
                        perfacilselect += "  AVG(" + dbcolumns.get(a) + ") as " + dbcolumns.get(a);

                    } else if (iscumulative.get(a).equals("1")) {
                        perfacilselect += "  " + dbcolumns.get(a) + " as " + dbcolumns.get(a);

                    } else {
                        perfacilselect += "  SUM(" + dbcolumns.get(a) + ") as " + dbcolumns.get(a);

                    }

        //if the item is not the last, append a comma
       //if(a<dbcolumns.size()-1){
                    perfacilselect += " ,";

      // } 
                }

  //---------------------------------add highvolume------------------------------------------------
                
        perfacilselect += " IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume,";         
                
  //-------------------------------------------------------------------------------------------------
                //     FROM  
                //------------------------------------------------------------------------------------  
                perfacilselect += "  isValidated as Form_Validated from " + form + "  join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on " + form + ".SubPartnerID = subpartnera.SubPartnerID ";

//------------------------------------------------------------------------------------------
// WHERE 
//------------------------------------------------------------------------------------------ 
                perfacilselect += " where  1=1 " + monthwhere + yearwhere;

 //-----------------------------------------------------------------------------------------
                //GROUP BY 
                //----------------------------------------------------------------------------------------
                perfacilselect += " group by subpartnera.SubPartnerID";

                String sectioncopy = "";

                int sheetpos = 0;
                int rowpos = 2;

    //-----------------INSIDE THE DATA FORM---------------------------------
                //if the section changes, change the position of the worksheet too
                //also, reset the position counter to begin from 2 again. 
                XSSFSheet shet = null;
                
//      if(--!sectioncopy.equals(shet)){}
      //create the org unit data values e.g BARINGO | BARINGO CENTRAL |KABARNET DISTRICT HOSPITAL | MFL CODE
                for (int g = 0; g < distinctsheets.size(); g++) {
                    
               if ( w == months.length - 1 && cumstartpointnoted==false) {
                 
                 //save the current row position.
                 //get it from the current rowstartpersheet[g]  
                    cumulativestatingpoint=rowstartpersheet[0];   
                   
                    cumstartpointnoted=true;  //this ensures that we are fetching dataonce.
                    System.out.println("LAST STARTING POINT__"+cumulativestatingpoint);
                   
                }
                    
                    rowpos = rowstartpersheet[g];

                    shet = wb.getSheetAt(g);
                    int colpos = 0;
                   

                    String finalquery = perfacilselect.replace("1=1", distinctservicearea.get(g).toString());
                    System.out.println("" + finalquery);
                    conn.rs = conn.st.executeQuery(finalquery);
                    while (conn.rs.next()) {

                        //the fourth cell should     
                        XSSFRow rw = shet.createRow(rowpos);
                        for (int e = 0; e < Headerorgunits.size(); e++) {
                            XSSFCell cell0 = rw.createCell(colpos);
                            //for mfl code, last header, output integers
                            if (e > 3) {
                                cell0.setCellValue(conn.rs.getInt(e + 1));
                            } else {
                                cell0.setCellValue(conn.rs.getString(e + 1));
                            }

                            cell0.setCellStyle(style2);
                            colpos++;

                        }

     //_________________________________________________________________
                        //VALUES
                        //_________________________________________________________________
                        //create the indicators values eg 90 | 45  | 356    
                        for (int c = 0; c < dbcolumns.size(); c++) {
         //get the section of the current dbcolumn

            //compare if the indicator belongs to the specified section and hence worksheet 
                            //recall, each indicator has got an associated section / worksheet
                            //An indicator should be put as an header in the respective worksheet
                            if (worksheets.get(c).equals(distinctsheets.get(g))) {

                                XSSFCell cell0 = rw.createCell(colpos);
                                cell0.setCellValue(conn.rs.getInt(dbcolumns.get(c).toString()));
                                cell0.setCellStyle(stborder);
                                colpos++;
                            }//end of comparing if

                        }//end of for loop

                        //ART_highvolume
                        XSSFCell cell0 = rw.createCell(colpos);
                        cell0.setCellValue(conn.rs.getInt("ART_highvolume"));
                        cell0.setCellStyle(stborder);                        
                        colpos++;
                        
                        //HTC_highvolume
                        XSSFCell cell1 = rw.createCell(colpos);
                        cell1.setCellValue(conn.rs.getInt("HTC_highvolume"));
                        cell1.setCellStyle(stborder);                        
                        colpos++;
                        
                        //PMTCT_highvolume
                        XSSFCell cell2 = rw.createCell(colpos);
                        cell2.setCellValue(conn.rs.getInt("PMTCT_highvolume"));
                        cell2.setCellStyle(stborder);                        
                        colpos++;
                        
                        String isvalidated = "Yes";

                        if (conn.rs.getString("Form_Validated").equals("0")) {
                            isvalidated = "No";
                        }
                        XSSFCell cell3 = rw.createCell(colpos);
                        cell3.setCellValue(isvalidated);
                        cell3.setCellStyle(stborder);
                        
                        colpos++;

                        rowpos++;
                        if(colpos>1){
                        colposcopy = colpos - 1;
                            
                        }
                        colpos = 0;
                    }// end of while loop getting data from the db
                    
                    //____________________________________________________________________________________
                    //COLUMN AUTOSIZE COLUMN WIDTH , AUTOFILTER & AUTOSUM
                    //____________________________________________________________________________________
                    
                    //At this point we are sure this is the last row and we have exhausted fetching data for all months
                    
                 
                    //System.out.println(" Column position Before "+colposcopy);
                    
                    if (w == months.length - 1) {
                        
               //System.out.println(" Column after "+colposcopy);
                        
                        shet.setAutoFilter(new CellRangeAddress(1, rowpos - 1, 0, colposcopy));
                        
                        //System.out.println("1,"+rowpos+",0,"+colposcopy);
                        
                        for (int e = 0; e < Headerorgunits.size(); e++) {
                            shet.autoSizeColumn(e);
                        }
                    
                        //autosum
                          XSSFRow rwsum = shet.createRow(rowpos);
                          XSSFRow initialrow = shet.getRow(2);
                          XSSFRow prevrow = shet.getRow(rowpos-1);                          
                          XSSFRow cumrow = shet.getRow(cumulativestatingpoint);//not used for now but let it stay here may be of use in future  
                          
                          int colpossum=0;
                          int firstcols=5;
                          int periodcolumn=0;
                          
                           for (int f = 0; f < firstcols; f++) {
                            
                      
                             if(f==0){
                                  
                           XSSFCell cellsum = rwsum.createCell(0);
                          cellsum.setCellValue("Total");
                          cellsum.setCellStyle(stylesum);
                             }
                             else if(f>0 && f<firstcols){
                              XSSFCell cellsum = rwsum.createCell(f);
                          cellsum.setCellValue(" ");
                          cellsum.setCellStyle(stylesum);
                              }
                           }
                          
                          
                       for (int c = 0; c < dbcolumns.size(); c++) {
                            
                          if (worksheets.get(c).equals(distinctsheets.get(g))) {
                           
                            XSSFCell cellsum = rwsum.createCell(colpossum+firstcols);
                            XSSFCell initialcell=initialrow.getCell(colpossum+firstcols);
                            
                             String cellformula="";
                             XSSFCell prevcell=prevrow.getCell(colpossum+firstcols);//the last cell of the current column. rem whe are looping through all the columns
                             //periodcolumncell
                             XSSFCell initialperiodcell=initialrow.getCell(periodcolumn);// the first cell of the period(month, year) column
                             XSSFCell currentperiodcell=prevrow.getCell(periodcolumn);//the last cell of the period column
                             
                            cellsum.setCellType(cellsum.CELL_TYPE_FORMULA);
                            String startcellreference=initialcell.getReference();
                            String lastavailableperiod=currentperiodcell.getStringCellValue();
                            if(iscumulative.get(c).equals("1"))
                            {
                            //initialcell=cumrow.getCell(colpossum+firstcols);
                            //Create a formula that sums all the data for the last column to appear in the row. This should pick the first column which is the period column.
                            cellformula="SUMPRODUCT(--(SUBTOTAL(3,OFFSET(INDEX("+initialperiodcell.getReference()+":"+currentperiodcell.getReference()+",1,1),ROW("+initialperiodcell.getReference()+":"+currentperiodcell.getReference()+")-ROW(INDEX("+initialperiodcell.getReference()+":"+currentperiodcell.getReference()+",1,1)),0))=1),--("+initialperiodcell.getReference()+":"+currentperiodcell.getReference()+"=\""+lastavailableperiod+"\"),"+startcellreference+":"+prevcell.getReference()+")";
                           
                            }
                            else  if(ispercent.get(c).equals("1")){
                            //initialcell=cumrow.getCell(colpossum+firstcols);
                            //Create a formula that sums all the data for the last column to appear in the row. This should pick the first column which is the period column.
                            cellformula="ROUNDUP(SUBTOTAL(9,"+startcellreference+":"+prevcell.getReference()+"),1)"; // round of maximum values
                           
                            }
                            else{
                            
                            cellformula="SUBTOTAL(9,"+startcellreference+":"+prevcell.getReference()+")";
                            }
                            
                            
                           
                            //for cumulative indicators, we need to do column total for the last selected month
                            //we therefore need to always track where the previous month started at.
                                                      
                            cellsum.setCellFormula(cellformula);
                            cellsum.setCellStyle(stylesum);
                                          
                        colpossum++;
                                                                                }
                                                                   }  
                       //merge last cell
                        shet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(rowpos,rowpos,0,4));
                    }

                    //
                    
                    
                    rowstartpersheet[g] = rowpos;

                }// end of distinct sheets report
             
             
             
            }//end of monthly loop

            IdGenerator IG = new IdGenerator();
            String createdOn = IG.CreatedOn();

            System.out.println("" + form.toUpperCase().trim() + "_RPT_FOR_" + year.trim() + "(" + month.trim() + ")_GEN_" + createdOn.trim() + ".xlsx");

            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            wb.write(outByteStream);
            byte[] outArray = outByteStream.toByteArray();
            response.setContentType("application/ms-excel");
            response.setContentLength(outArray.length);
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setHeader("Content-Disposition", "attachment; filename=" + form.toUpperCase().trim() + "_REPORT_FOR_" + year.trim() + "(" + periodlabel + ")_CREATED_" + createdOn.trim() + ".xlsx");
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
        } catch (SQLException ex) {
            Logger.getLogger(allStaticReports.class.getName()).log(Level.SEVERE, null, ex);
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
