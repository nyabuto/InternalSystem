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
public class nutritionpdf extends HttpServlet {

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
  
    String form="nutrition";
      
   
    
 
  
    

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
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   String getexistingdata="";
   
   
    if(!county.equals("")){
   
   countywhere=" and countyid = '"+county+"'";    
   
   }
   
   if(!facil.equals("")){
   
   facilitywhere=" and "+form+".SubPartnerID = '"+facil+"'";    
   
   } 
   
    
    
 String joinedwhwere=" where 1=1 "+yearwhere+" && "+duration;  
    
    
    
//=====================================================================================================    
    
    
    
    
 
    
    
    
    
    
    
        
getexistingdata="select  sum(MCHCCNtrTM) as MCHCCNtrTM,    MCHCCNtrTMC,    sum(MCHCCNtrTF) as MCHCCNtrTF,    MCHCCNtrTFC,   sum(MCHCCNtrTT) as MCHCCNtrTT,    MCHCCNtrTTC,    sum(MCHNtrnCHWTrain) as MCHNtrnCHWTrain,   sum(MCHNutChRch) as MCHNutChRch,   sum(MCHNtrnWasted) as MCHNtrnWasted,   sum(MCHNtrnUnderweight) as MCHNtrnUnderweight,   sum(MCHChild5D) as MCHChild5D,   sum(MCHNtrnHealthFacility) as MCHNtrnHealthFacility,   sum(MCHVaccVitA) as MCHVaccVitA,   sum(MCHNtrnFoodOVC) as MCHNtrnFoodOVC,   sum(MCHNtrnFoodPLHIV) as MCHNtrnFoodPLHIV,   sum(MCHNtrnFood) as MCHNtrnFood,   sum(C51DCM) as C51DCM,   sum(C51DCF) as C51DCF,	sum(C51DC) as C51DC,    sum(C51DAM) as C51DAM,   sum(C51DAF) as C51DAF,   sum(C51DA) as C51DA,    sum(C51DP) as C51DP,    sum(C51DMT) as C51DMT,    sum(C51DFT) as C51DFT,    sum(C51DT) as C51DT  from "+form+" join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+"  ";

            //System.out.println(getexistingdata);
             
String MCHCCNtrTM="";
String MCHCCNtrTF="";
String MCHCCNtrTT="";

String MCHCCNtrTMC="0";
String MCHCCNtrTFC="0";
String MCHCCNtrTTC="0";


String MCHCCNtrTMCH="0";
String MCHCCNtrTFCH="0";
String MCHCCNtrTTCH="0";

String MCHNtrnCHWTrain="";
String MCHNutChRch="";
String MCHNtrnWasted="";
String MCHNtrnUnderweight="";
String MCHChild5D="";
String MCHNtrnHealthFacility="";
String MCHVaccVitA="";
String MCHNtrnFoodOVC="";
String MCHNtrnFoodPLHIV="";
String MCHNtrnFood="";
String C51DCM="";
String C51DCF="";
String C51DC="";
String C51DAM="";
String C51DAF="";
String C51DA="";
String C51DP="";
String C51DMT="";
String C51DFT="";
String C51DT="";



String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


int counter=0;
 
 
    conn.rs=conn.st.executeQuery(getexistingdata);
    while(conn.rs.next()){
   //now check if form was updated and if its one month after data entry
   //now load the column values here
        
        
             
MCHCCNtrTM=conn.rs.getString("MCHCCNtrTM");
if(MCHCCNtrTM==null){MCHCCNtrTM=""; }

MCHCCNtrTF=conn.rs.getString("MCHCCNtrTF");
if(MCHCCNtrTF==null){MCHCCNtrTF=""; }

MCHCCNtrTT=conn.rs.getString("MCHCCNtrTT");
if(MCHCCNtrTT==null){MCHCCNtrTT=""; }


 MCHCCNtrTMC=conn.rs.getString("MCHCCNtrTMC");
if(MCHCCNtrTMC==null){MCHCCNtrTMC="0"; }

MCHCCNtrTFC=conn.rs.getString("MCHCCNtrTFC");
if(MCHCCNtrTFC==null){MCHCCNtrTFC="0"; }

MCHCCNtrTTC=conn.rs.getString("MCHCCNtrTTC");
if(MCHCCNtrTTC==null){MCHCCNtrTTC="0"; }


MCHNtrnCHWTrain=conn.rs.getString("MCHNtrnCHWTrain");
if(MCHNtrnCHWTrain==null){MCHNtrnCHWTrain=""; }

MCHNutChRch=conn.rs.getString("MCHNutChRch");
if(MCHNutChRch==null){MCHNutChRch=""; }

MCHNtrnWasted=conn.rs.getString("MCHNtrnWasted");
if(MCHNtrnWasted==null){MCHNtrnWasted=""; }

MCHNtrnUnderweight=conn.rs.getString("MCHNtrnUnderweight");
if(MCHNtrnUnderweight==null){MCHNtrnUnderweight=""; }

MCHChild5D=conn.rs.getString("MCHChild5D");
if(MCHChild5D==null){MCHChild5D=""; }

MCHNtrnHealthFacility=conn.rs.getString("MCHNtrnHealthFacility");
if(MCHNtrnHealthFacility==null){MCHNtrnHealthFacility=""; }

MCHVaccVitA=conn.rs.getString("MCHVaccVitA");
if(MCHVaccVitA==null){MCHVaccVitA=""; }

MCHNtrnFoodOVC=conn.rs.getString("MCHNtrnFoodOVC");
if(MCHNtrnFoodOVC==null){MCHNtrnFoodOVC=""; }
        
MCHNtrnFoodPLHIV=conn.rs.getString("MCHNtrnFoodPLHIV");
if(MCHNtrnFoodPLHIV==null){MCHNtrnFoodPLHIV=""; }
        
MCHNtrnFood=conn.rs.getString("MCHNtrnFood");
if(MCHNtrnFood==null){MCHNtrnFood=""; }
        
C51DCM=conn.rs.getString("C51DCM");
if(C51DCM==null){C51DCM=""; }
        
C51DCF=conn.rs.getString("C51DCF");
if(C51DCF==null){C51DCF=""; }
        
C51DC=conn.rs.getString("C51DC");
if(C51DC==null){C51DC=""; }
        
C51DAM=conn.rs.getString("C51DAM");
if(C51DAM==null){C51DAM=""; }
        
C51DAF=conn.rs.getString("C51DAF");
if(C51DAF==null){C51DAF=""; }
        
C51DA=conn.rs.getString("C51DA");
if(C51DA==null){C51DA=""; }
        
C51DP=conn.rs.getString("C51DP");
if(C51DP==null){C51DP=""; }
        
C51DMT=conn.rs.getString("C51DMT");
if(C51DMT==null){C51DMT=""; }
        
C51DFT=conn.rs.getString("C51DFT");
if(C51DFT==null){C51DFT=""; }
        
C51DT=conn.rs.getString("C51DT");
if(C51DT==null){C51DT=""; }
   
        
        
       }
    
    String createdtable="";
    
    
if(1==1){ 
    createdtable+=header+"<br/><table   border='1' style='border-color: #e5e5e5;margin-bottom: 3px;font-size:11;'><tr class='form-actions'>"
            + "<th rowspan='10' colspan='2'><b style='text-align:center;'>3.1.9.2 <br/> population-based Nutrition Service Delivery</b></th>"
            + "<td colspan='4'><b>Number of People trained in child health care and nutrition through USG-supported health area programs</b></td><td><b>"+MCHCCNtrTTC+"</b></td></tr>";
    createdtable+="<tr><td colspan='4' style='text-align:left;'>No of Men </td><td>"+MCHCCNtrTM+"</td></tr>";
    createdtable+="<tr><td colspan='4' style='text-align:left;'>No of Women </td><td>"+MCHCCNtrTF+"</td></tr>";
    createdtable+="<tr><td colspan='4'><b>Total Number of people trained in child health care and nutrition through USG-supported health area programs</b></td><td>"+MCHCCNtrTT+"</td></tr>";
    createdtable+="<tr><td colspan='4'><b>Number of Community health workers trained in child health and/or nutrition</b></td><td>"+MCHNtrnCHWTrain+"</td></tr>";
    createdtable+="<tr><td colspan='4'><b>Number of children reached by USG-supported nutrition programs</b></td><td>"+MCHNutChRch+"</td></tr>";
    createdtable+="<tr><td colspan='4'>Total number of children under five who are wasted (with weight for height Z score < - 2)</td><td>"+MCHNtrnWasted+"</td></tr>";
    createdtable+="<tr><td colspan='4'>Total number of children under five who are underweight (with weight for age Z score < - 2) (SEE Indicator </td><td>"+MCHNtrnUnderweight+"</td></tr>";
    createdtable+="<tr><td colspan='4'>Total number of children under five years</td><td>"+MCHChild5D+"</td></tr>";
    createdtable+="<tr><td colspan='4'>Number of health facilities with established capacity to manage acute under-nutrition</td><td>"+MCHNtrnHealthFacility+"</td></tr>";    
   
    createdtable+="<tr><td colspan='1' rowspan='9'>HIV and Nutrition</td><td></td> <td colspan='4'> <b> Number of children under 5 years of age who received Vitamin A from USG-supported programs </b> </td><td>"+MCHVaccVitA+"</td></tr>";
    createdtable+="<tr><td rowspan='3' colspan='1'> <b> C2.3.D </b> </td> <td colspan='4'>Number of HIV – positive clinically malnourished clients who received therapeutic and/or supplementary food < 18 </td><td>"+MCHNtrnFoodOVC+"</td></tr>";
    createdtable+="<tr><td colspan='4'>Number of HIV – positive clinically malnourished clients who received therapeutic and/or supplementary food 18+ (PLHIV)</td><td>"+MCHNtrnFoodPLHIV+"</td></tr>";
    createdtable+="<tr><td colspan='4'>Number of HIV – positive clinically malnourished clients who received therapeutic and/or supplementary food -<b> Total</b></td><td>"+MCHNtrnFood+"</td></tr>";
    
    createdtable+="<tr> <td></td><td></td><td></td><td><b>Male</b></td><td><b>Female</b></td><td><b>Total</b></td></tr>";
    createdtable+="<tr><td rowspan='4' colspan='1'> <b>C5.1.D </b> </td> <td colspan='1' rowspan='4'>Number of eligible clients who received food and / or other nutrition Services</td><td> <b> less Than 18 </b>  </td> <td>"+C51DCM+"</td><td>"+C51DCF+"</td><td>"+C51DC+"</td></tr>";
    createdtable+="<tr><td> <b> >=18 </b> </td> <td>"+C51DAM+"</td><td>"+C51DAF+"</td><td>"+C51DA+"</td></tr>";
    createdtable+="<tr><td colspan='3'> <b> Pregnant/Lactating (PMTCT 1.5)</b> </td><td>"+C51DP+"</td></tr>";
    createdtable+="<tr><td> <b> Total </b>  </td> <td> "+C51DMT+" </td> <td> "+C51DFT+" </td> <td> "+C51DT+" </td></tr></table>";
   

    
    }
    
    
     
      System.out.println(createdtable);
      
   
        
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

   document.close(); 
   
   	  IdGenerator IG = new IdGenerator();
     String   createdOn=IG.CreatedOn();

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
