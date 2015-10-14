/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

import General.IdGenerator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class Vmmcpdf extends HttpServlet {

    HttpSession session=null;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    session=request.getSession();
    
    
    dbConn conn=new dbConn();
    //get the existing data for the month, year and facility that is already on session
    
    String month="";      
    String year="";      
    String facil="";
  
    String form="vmmc";
      
   
    
 
  
    

//=====================================================================================================
    
    year="2015";
     month="5";
    String county="";
   
    String header="";
    
    String  reportType="";
    if(request.getParameter("reportType")!=null){
      reportType=request.getParameter("reportType");
    }
    String  reportDuration="";
    if(request.getParameter("reportDuration")!=null){
    reportDuration=request.getParameter("reportDuration");
    }
    if(request.getParameter("year")!=null){
    year=request.getParameter("year");
    }
    
     if(request.getParameter("facility")!=null  && reportType.equals("2")){
    facil=request.getParameter("facility");
    
    String getfacil="select SubPartnerNom,CentreSanteId as mflcode from subpartnera where SubPartnerID='"+facil+"'";
    conn.rs=conn.st.executeQuery(getfacil);
    
    while(conn.rs.next()){
    
    header+=" FACILITY : <u>"+conn.rs.getString(1).toUpperCase()+" </u>     MFL CODE  :  <u>"+conn.rs.getString(2)+" </u> ";
    
    }
    
    
    
    }
    
    if(request.getParameter("county")!=null && reportType.equals("2")){
    county=request.getParameter("county");
    
    
    String getcounty="select County from county where CountyID='"+county+"'";
    conn.rs=conn.st.executeQuery(getcounty);
    
    while(conn.rs.next()){
    
    header+=" COUNTY : <u>"+conn.rs.getString(1).toUpperCase()+" </u>";
    
    }
    
    }
    
    if(request.getParameter("month")!=null && reportDuration.equals("4")){
    month=request.getParameter("month");
    
    
      String getmonth="select name as monthname from month where id='"+month+"'";
    conn.rs=conn.st.executeQuery(getmonth);
    
    while(conn.rs.next()){
    
    header+=" MONTH : <u>"+conn.rs.getString(1).toUpperCase()+" </u>";
    
    }
    
    
    }
    
     
    header+=" YEAR : <u>"+year+"</u>";
     
     
     
     
     
   String facilitywhere="";
   String yearwhere="";
   String monthwhere="";
   String countywhere="";
   String duration="";
   String semi_annual="";
   String quarter="";
 
   //==================================================================================================
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
  
       
       int  yearcopy=Integer.parseInt(year);
       
        
//        reportType="2";
//        year=2015;
//        reportDuration="3";
        String yearmonth=""+year;
       int prevYear=yearcopy-1; 
       int maxYearMonth=0;
       int monthcopy=0; 
//        GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
            yearmonth+="_AnnualReport";
         duration=" "+form+".yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
            yearmonth=prevYear+"_Oct_"+year+"_Mar";
       duration=" "+form+".yearmonth BETWEEN "+prevYear+"10 AND "+year+"03";      
       }
           else{
            yearmonth+="_Apr_Sep";
       duration=" "+form+".yearmonth BETWEEN "+year+"04 AND "+year+"09";      
           }
       }
        
        else if(reportDuration.equals("3")){
            String startMonth,endMonth;
       quarter=request.getParameter("quarter");
//       quarter="3";
       String getMonths="SELECT months,name FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
           
          
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" "+form+".yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth; 
 yearmonth=prevYear+"_"+conn.rs.getString(2);
      }
      else{
           yearmonth=year+"_"+conn.rs.getString(2);
     duration=" "+form+".yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
      }
                              }
        }  
        
      else if(reportDuration.equals("4")){
     monthcopy=Integer.parseInt(request.getParameter("month"));
   
     
//     month=5;
     if(monthcopy>=10){
          yearmonth=prevYear+"_"+month;
     duration=" "+form+".yearmonth="+prevYear+""+month;    
     }
     else{
  duration=" "+form+".yearmonth="+year+"0"+month;  
    yearmonth=year+"_("+month+")";
     }
      }
      else{
     duration="";     
      }
        
	//======================================================================


//==================================================================================================
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   String getexistingdata="";
   
   
    if(!county.equals("")){
   
   countywhere=" and countyid = '"+county+"'";    
   
   }
   
   if(!facil.equals("")&& reportType.equalsIgnoreCase("2")){
   
   facilitywhere=" and "+form+".SubPartnerID = '"+facil+"'";    
   
   } 
   
    
    
 //String joinedwhwere=" where 1=1 "+yearwhere+" && "+duration;  
 String joinedwhwere=" where 1=1 "+facilitywhere+"  "+yearwhere+" && "+duration;  
     
    
    
//=====================================================================================================    
    
    
    
    
 
    
    
    
    
    
    
        
getexistingdata="select sum(P51D1) as P51D1,   sum(P51D9) as P51D9,   sum(P51D10) as P51D10,   sum(P51D19) as P51D19,sum(P51D24) as P51D24, sum(P51D29) as P51D29,  sum(P51D49) as  P51D49,   sum(P51D50) as P51D50,    sum(P51DT) as P51DT,   sum(P521DM) as  P521DM,    sum(P521DS) as P521DS,   sum(P521DT) as P521DT,   sum(P522DM) as P522DM,    sum(P522DS) as P522DS,    sum(P522DT) as P522DT,   sum(P52DM) as  P52DM,   sum(P52DS) as P52DS,    sum(P52DT) as P52DT,   sum(P511KP) as P511KP,   sum(P511KN) as P511KN,   sum(P511KU) as P511KU,   sum(P511Surg) as P511Surg,   sum(P511Dev) as P511Dev,   sum(P53DF) as P53DF,    sum(P53DO) as P53DO,   sum(P53DM) as P53DM,    sum(P53D) as P53D,   sum(P54D) as P54D  from "+form+" join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+"  ";

            System.out.println(getexistingdata);
String P51D1 = "";
            String P51D9 = "";
            String P51D10 = "";
            String P51D19 = "";
            String P51D24 = "";
            String P51D29 = "";
            String P51D49 = "";
            String P51D50 = "";
            String P51DT = "";
            String P521DM = "";
            String P521DS = "";
            String P521DT = "";
            String P522DM = "";
            String P522DS = "";
            String P522DT = "";
            String P52DM = "";
            String P52DS = "";
            String P52DT = "";
            String P511KP = "";
            String P511KN = "";
            String P511KU = "";
            String P511Surg = "";
            String P511Dev = "";
            String P53DF = "";
            String P53DO = "";
            String P53DM = "";
            String P53D = "";
            String P54D = "";


String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


int counter=0;
 
 
 
 
 
    
    conn.rs=conn.st.executeQuery(getexistingdata);
    while(conn.rs.next()){
   //now check if form was updated and if its one month after data entry
//now load the column values here
        
        
                P51D1 = conn.rs.getString("P51D1");
                if (P51D1 == null) {
                    P51D1 = "";
                }

                P51D9 = conn.rs.getString("P51D9");
                if (P51D9 == null) {
                    P51D9 = "";
                }

                P51D10 = conn.rs.getString("P51D10");
                if (P51D10 == null) {
                    P51D10 = "";
                }

                P51D19 = conn.rs.getString("P51D19");
                if (P51D19 == null) {
                    P51D19 = "";
                }

                P51D24 = conn.rs.getString("P51D24");
                if (P51D24 == null) {
                    P51D24 = "";
                }

                  P51D29 = conn.rs.getString("P51D29");
                if (P51D29 == null) {
                    P51D29 = "";
                }

                
                
                P51D49 = conn.rs.getString("P51D49");
                if (P51D49 == null) {
                    P51D49 = "";
                }

                P51D50 = conn.rs.getString("P51D50");
                if (P51D50 == null) {
                    P51D50 = "";
                }

                P51DT = conn.rs.getString("P51DT");
                if (P51DT == null) {
                    P51DT = "";
                }

                P521DM = conn.rs.getString("P521DM");
                if (P521DM == null) {
                    P521DM = "";
                }

                P521DS = conn.rs.getString("P521DS");
                if (P521DS == null) {
                    P521DS = "";
                }

                P521DT = conn.rs.getString("P521DT");
                if (P521DT == null) {
                    P521DT = "";
                }



                P522DM = conn.rs.getString("P522DM");
                if (P522DM == null) {
                    P522DM = "";
                }

                P522DS = conn.rs.getString("P522DS");
                if (P522DS == null) {
                    P522DS = "";
                }

                P522DT = conn.rs.getString("P522DT");
                if (P522DT == null) {
                    P522DT = "";
                }


                P52DM = conn.rs.getString("P52DM");
                if (P52DM == null) {
                    P52DM = "";
                }


                P52DS = conn.rs.getString("P52DS");
                if (P52DS == null) {
                    P52DS = "";
                }


                P52DT = conn.rs.getString("P52DT");
                if (P52DT == null) {
                    P52DT = "";
                }


                P511KP = conn.rs.getString("P511KP");
                if (P511KP == null) {
                    P511KP = "";
                }


                P511KN = conn.rs.getString("P511KN");
                if (P511KN == null) {
                    P511KN = "";
                }

                P511KU = conn.rs.getString("P511KU");
                if (P511KU == null) {
                    P511KU = "";
                }

                P511Surg = conn.rs.getString("P511Surg");
                if (P511Surg == null) {
                    P511Surg = "";
                }


                P511Dev = conn.rs.getString("P511Dev");
                if (P511Dev == null) {
                    P511Dev = "";
                }

                P53DF = conn.rs.getString("P53DF");
                if (P53DF == null) {
                    P53DF = "";
                }

                P53DO = conn.rs.getString("P53DO");
                if (P53DO == null) {
                    P53DO = "";
                }

                P53DM = conn.rs.getString("P53DM");
                if (P53DM == null) {
                    P53DM = "";
                }

                P53D = conn.rs.getString("P53D");
                if (P53D == null) {
                    P53D = "";
                }

                P54D = conn.rs.getString("P54D");
                if (P54D == null) {
                    P54D = "";
                }

        
       }
    
    String createdtable="";
    
    
if(1==1){ 
    
    
                    createdtable +=header+"<br/><table   border='1' style='border-color: #e5e5e5;margin-bottom: 3px;font-size:10;font-family:cambria;'>"
                            + "<tr><th colspan='5' style='text-align:center;'><b>Voluntary Male Circumcision Reporting Form</b></th></tr>"
                            + "<tr class='form-actions'><th rowspan='10' style='width:40px;'> <b>P5.1.D </b></th><th colspan='4'><b>Number of Males Circumcised as part of the minimum package of MC for HIV Prevention Services </b></th></tr>"
                            + "<tr><td colspan='3' style='text-align:left;'><b> <1 </b></td><td>" + P51D1 + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b> 1-9 </b></td></td><td>" + P51D9 + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b> 10-14 </b></td></td><td>" + P51D10 + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b> 15-19 </b></td></td><td>" + P51D19 + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b> 20-24 </b></td></td><td>" + P51D24 + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b> 25-29 </b></td></td><td>" + P51D29 + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b> 30-49 </b></td></td><td>" + P51D49 + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b> 50+ </b></td></td><td>" + P51D50 + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b> Total </b></td></td><td>" + P51DT + "</td></tr>";
                    createdtable += "<tr class='form-actions'><th rowspan='5'> P5.2.D</th><th colspan='4' > Number of clients circumcised who experienced one or more moderate or severe adverse event(s) within the reporting period</th></tr>";
                    createdtable += "<tr><td ></td><td><b>Moderate</b></td><td><b>Severe</b></td><td><b>Total</b></td></tr>";
                    createdtable += "<tr><td><b>During circumcision</b></td><td>" + P521DM + "</td><td>" + P521DS + "</td><td>" + P521DT + "</td></tr>";
                    createdtable += "<tr><td><b>Post circumcision</b></td><td>" + P522DM + "</td><td>" + P522DS + "</td><td>" + P522DT + "</td></tr>";
                    createdtable += "<tr><td><b>Total adverse events</b></td><td>" + P52DM + "</td><td>" + P52DS + "</td><td>" + P52DT + "</td></tr>";
                    createdtable += "<tr class='form-actions'><th rowspan='4' > <b> P5.1.1.K </b></th><th colspan='4' style='background:green;'><b> HIV Status of MC Clients</b></th></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b>Tested/self-reported positive</b></td><td>" + P511KP + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b>Tested negative</b></td><td>" + P511KN + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b>Unknown/self-reported negative</b></td><td>" + P511KU + "</td></tr>";
                    createdtable += "<tr class='form-actions'><th rowspan='3'> <b> P5.1.1.T </b></th><th colspan='4'><b>Circumcision technique</b></th></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b>Surgical VMMC</b></td><td>" + P511Surg + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b>Device-Based VMMC</b></td><td>" + P511Dev + "</td></tr>";
                    createdtable += "<tr class='form-actions'><th rowspan='5'> <b> P5.3.D </b></th><th colspan='4'><b> Number of locations providing MC surgery as part of the minimum package of MC for HIV prevention services within the reporting period</b> </th></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b>Fixed/Static</b></td><td>" + P53DF + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b>Outreach</b></td><td>" + P53DO + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b>Mobile</b></td><td>" + P53DM + "</td></tr>";
                    createdtable += "<tr><td colspan='3' style='text-align:left;'><b>Total</b></td><td>" + P53D + "</td></tr>";
                    createdtable += "<tr class='form-actions'><th rowspan='2'> <b> P5.4.D </b></th><th colspan='4'>Number of males circumcised within the reporting period who return at least once for postoperative follow‐up care (routine or emergent)</th></tr>";
                    createdtable += "<tr><td colspan='4' style='text-align:left;'>" + P54D + "</td></tr>"
                            + "</table>";

    
    }
    
    
     
      //System.out.println(createdtable);
      
   
        
        if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}
         if(conn.rs2!=null){ conn.rs2.close();}
         if(conn.st!=null){ conn.st.close();}
         if(conn.st2!=null){ conn.st2.close();}
        
     
    
    
    
    
        Document document = new Document();
  ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            try {
                PdfWriter.getInstance(document, outByteStream);
            } catch (DocumentException ex) {
                Logger.getLogger(Vmmcpdf.class.getName()).log(Level.SEVERE, null, ex);
            }
      document.open();
      
      HTMLWorker htmlWorker = new HTMLWorker(document);
       htmlWorker.parse(new StringReader(createdtable));
       
       CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(false);  
            try {
                cssResolver.addCss("", true);
            } catch (CssResolverException ex) {
                Logger.getLogger(Vmmcpdf.class.getName()).log(Level.SEVERE, null, ex);
            }
	  IdGenerator IG = new IdGenerator();
     String   createdOn=IG.CreatedOn();
   document.close();    
response.setContentType("application/pdf");
response.setContentLength(outByteStream.size());
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename="+form+yearmonth+"_Generated_On_"+createdOn+".pdf");

ServletOutputStream sos;
sos = response.getOutputStream();
outByteStream.writeTo(sos);
sos.flush();
    
    
    
}       catch (SQLException ex) {
            Logger.getLogger(Vmmcpdf.class.getName()).log(Level.SEVERE, null, ex);
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
