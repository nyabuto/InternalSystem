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
public class kmmppdf extends HttpServlet {

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
    String facil="361";
  
    String form="kmmp";
      
   
    
 
  
    

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
    
     if(request.getParameter("facility")!=null && reportType.equals("2")){
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
   
   if(!facil.equals("")){
   
   facilitywhere=" and kmmp.SubPartnerID = '"+facil+"'";    
   
   } 
   
    
    
 String joinedwhwere=" where 1=1 "+yearwhere+" && "+duration;  
    
    
    
//=====================================================================================================    
    
    
    
    
 
    
    
    
    
    
    
        
getexistingdata="select  sum(KMMP1) as KMMP1,   sum(KMMP2) as KMMP2,  sum(KMMP3a) as KMMP3a,   sum(KMMP3b) as KMMP3b,   avg(KMMP3c) as KMMP3c ,   sum(KMMP4) as KMMP4 ,   sum(KMMP5a) as KMMP5a,    sum(KMMP5b) as KMMP5b,    sum(KMMP5c) as KMMP5c,    sum(HV0205) as HV0205,    sum(HV0206) as HV0206 from kmmp join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on kmmp.SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+"  ";

            System.out.println(getexistingdata);
String formtype="<b><font color='#4b8df8'>New Entry</font></b>";
String KMMP1="";
String KMMP2="";
String KMMP3a="";
String KMMP3b="";
String KMMP3c="";
String KMMP4="";
String KMMP5a="";
String KMMP5b="";
String KMMP5c="";
String HV0205="";
String HV0206="";


String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


int counter=0;
 
 
 
 
 
    
    conn.rs=conn.st.executeQuery(getexistingdata);
    while(conn.rs.next()){
   //now check if form was updated and if its one month after data entry
//now load the column values here
       
KMMP1=conn.rs.getString("KMMP1");
if(KMMP1==null){KMMP1=""; }

KMMP2=conn.rs.getString("KMMP2");
if(KMMP2==null){KMMP2=""; }

KMMP3a=conn.rs.getString("KMMP3a");
if(KMMP3a==null){KMMP3a=""; }

KMMP3b=conn.rs.getString("KMMP3b");
if(KMMP3b==null){KMMP3b=""; }

KMMP3c=conn.rs.getString("KMMP3c");
if(KMMP3c==null){KMMP3c=""; }

KMMP4=conn.rs.getString("KMMP4");
if(KMMP4==null){KMMP4=""; }

KMMP5a=conn.rs.getString("KMMP5a");
if(KMMP5a==null){KMMP5a=""; }

KMMP5b=conn.rs.getString("KMMP5b");
if(KMMP5b==null){KMMP5b=""; }

KMMP5c=conn.rs.getString("KMMP5c");
if(KMMP5c==null){KMMP5c=""; }

HV0205=conn.rs.getString("HV0205");
if(HV0205==null){HV0205=""; }

HV0206=conn.rs.getString("HV0206");
if(HV0206==null){HV0206=""; }
        
    }
    
    String createdtable="";
    
    
if(1==1){ 
    
    
    createdtable+=header+"<br/><br/><br/><table   border=\"1\" style=\"border-color: #e5e5e5;margin-bottom: 3px;font-size:12;font-family:cambria;\" ><tr class='form-actions'><th colspan='3'><b style=\"text-align:center;\"> KMMP OUTPUT DATA</b></th><th>Total</th></tr><tr><td><b> 1 </b></td><td colspan='2'>No of New HIV positive clients enrolled in KMMP Services (ANC and PN) </td><td>"+KMMP1+"</td></tr>";
    
    createdtable+="<tr><td><b> 2 </b></td><td colspan='2'>No of New HIV negative clients enrolled in KMMP Services (ANC Only) </td><td>"+KMMP2+"</td></tr>";
    
    createdtable+="<tr><td rowspan='3'><b> 3 </b></td><td colspan='2'> a) No. of HIV-positive pregnant women enrolled in KMMP Services </td><td>"+KMMP3a+"</td></tr>";
    
    createdtable+="<tr><td colspan='2'>  b) Total number of HIV-positive pregnant women in facility (New positive and Known Positive-MOH731) </td><td>"+KMMP3b+"</td></tr>";
    
    createdtable+="<tr><td colspan='2'><b> Percentage of new IV-positive pregnant women enrolled in KMMP Services </b></td><td>"+KMMP3c.substring(0, 2)+""+" <b>%</b> </td></tr>";
      
    createdtable+="<tr><td ><b> 4 </b></td><td colspan='2'>No. of KMMP support group sessions held </td><td>"+KMMP4+"</td></tr>";
     
    createdtable+="<tr><td rowspan='3'><b> 5 </b></td><td rowspan='3'>Defaulter Tracing </td><td>New Defaulted Clients </td><td>"+KMMP5a+"</td></tr>";
     
    createdtable+="<tr><td>Clients Reached</td><td>"+KMMP5b+"</td></tr>";
      
      createdtable+="<tr><td>Successfully Resolved</td><td>"+KMMP5c+"</td></tr>";
   
      createdtable+="<tr><td></td><td colspan='2'>MOH 731 HV02-05 Known positive status (at entry into ANC) :</td><td>"+HV0205+"</td></tr>";
   
      createdtable+="<tr><td></td><td colspan='2'>MOH 731 HV02-06 Antenatal:</td><td>"+HV0206+"</td></tr></table> <div class='form-actions'></div>";
  
    
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
                Logger.getLogger(kmmppdf.class.getName()).log(Level.SEVERE, null, ex);
            }
      document.open();
      
      HTMLWorker htmlWorker = new HTMLWorker(document);
       htmlWorker.parse(new StringReader(createdtable));
       
       CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(false);  
            try {
                cssResolver.addCss("", true);
            } catch (CssResolverException ex) {
                Logger.getLogger(kmmppdf.class.getName()).log(Level.SEVERE, null, ex);
            }

              IdGenerator IG = new IdGenerator();
     String   createdOn=IG.CreatedOn();

            
   document.close();    
response.setContentType("application/pdf");
response.setContentLength(outByteStream.size());
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=kmmp"+yearmonth+"_Generated_On_"+createdOn+".pdf");

ServletOutputStream sos;
sos = response.getOutputStream();
outByteStream.writeTo(sos);
sos.flush();
    
    
    
}       catch (SQLException ex) {
            Logger.getLogger(kmmppdf.class.getName()).log(Level.SEVERE, null, ex);
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
