/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

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
 * @author Maureen
 */
public class tbpdf extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
 int maxYearMonth;
String subcountyid,facility,period;
String reportType,duration,reportDuration,quarter,semi_annual;
int year=0,prevYear=0,month=0;
String header,facilityName,countyName,districtName,mflcode,monthName,facilityId;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, DocumentException, CssResolverException {
        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
        try {
 

    
    dbConn conn=new dbConn();
    //get the existing data for the month, year and facility that is already on session
    String isValidated="";
    String validity="";
     
    String facil="";
    String invalidTBTXT="";
    
        

      subcountyid=facility=period="";
 reportType=duration=reportDuration=quarter=semi_annual="";

 header=facilityName=countyName=districtName=mflcode=monthName="";      
     facilityId="";         
        reportType=request.getParameter("reportType");
        year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
        
        header="<table><tr><td colspan=\"12\">REPUBLIC OF KENYA-MINISTRY OF HEALTH</td></tr>";
        header+="<tr><td colspan=\"12\"> NATIONAL INTEGRATED FORM FOR REPRODUCTIVE HEALTH,HIV/AIDS,MALARIA,TB and CHILD NUTRITION</td></tr>";
//        reportType="1";
//        year=2015;
//        reportDuration="4";
        
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================
        
        if(reportDuration.equals("1")){
         duration=" tb.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
      period="Annual Report ";
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
       duration=" tb.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03";      
      period="Semi-Annual : <b> OCT ("+prevYear+") -  MARCH ("+year+")</b>"; 
       }
           else{
       duration=" tb.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
       period="Semi-Annual : <b> APRIL ("+year+") -  SEPT ("+year+")</b>";  
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
      duration=" tb.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
     period="Quarter: <b>"+conn.rs.getString(2).replace("-", prevYear+" - ")+" "+prevYear+"</b>";
      }
      else{
     duration=" tb.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;
     period="Quarter: <b>"+conn.rs.getString(2).replace("-", year+" - ")+" "+year+"</b>";
      }
        }
        }  
        
      else if(reportDuration.equals("4")){
     month=Integer.parseInt(request.getParameter("month"));
//     month=4;
     if(month>=10){
     duration=" tb.yearmonth="+prevYear+""+month;    
     }
     else{
  duration=" tb.yearmonth="+year+"0"+month;  
     }
    String getMonthName="SELECT name FROM month WHERE id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
       if(month>=10){
   period="Month : <b>"+conn.rs.getString(1)+"("+prevYear+")</b>"; 
     }
       else{
        period="Month : <b>"+conn.rs.getString(1)+"("+year+")</b>";
    }
     
    }
      }
      else{
     duration="";     
      }
           
     
//     GET FACILITIES TO OUTPUT.................................
    mflcode=countyName=districtName=facilityName="";    
        
      if(reportType.equals("1")){  
    facility="";  
  header+="<tr><td colspan=\"3\"> All health facilities.</td> <td>Year</td><td> <b>"+year+"</b></td><td colspan=\"7\"> "+period+"</td></tr>"  ;
     }
      
      else{
  facilityId=request.getParameter("facility");
//  facilityId="403";
  facility="SubPartnerID='"+facilityId+"' &&";    
  
  String getName="SELECT subpartnera.SubPartnerNom,district.DistrictNom,county.County,subpartnera.CentreSanteId FROM subpartnera "
          + "JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID WHERE subpartnera.SubPartnerID='"+facilityId+"'";
  conn.rs=conn.st.executeQuery(getName);
  if(conn.rs.next()==true){
      facilityName=conn.rs.getString(1);
      districtName=conn.rs.getString(2);
      countyName=conn.rs.getString(3);
      mflcode=conn.rs.getString(4);
  }
  header+="<tr><td>District</td><td> <b>"+districtName+"</b></td><td>  County</td><td> <b>"+countyName+"</b></td><td>   Facility</td><td> <b>"+facilityName+"</b></td><td colspan=\"2\">"+period+"</td><td>   Year</td><td> <b>"+year+"</b></td><td>   MFL Code</td><td> <b>"+mflcode+"</b></td><td></tr>";
      }
     
    header+="</table>";  
    
String C31D="";
String TB_STATN="";
String TB_STATD="";
String TB_STATP="";
String TB_IPTN="";
String TB_IPTD="";
String TB_IPTP="";
String TB_IPT1="";
String TB_IPT4="";
String TB_IPT9="";
String TB_IPT14="";
String TB_IPT19="";
String TB_IPT20="";
String TB_IPTM="";
String TB_IPTF="";
String TB_SCREENN="";
String TB_SCREEND="";
String TB_SCREENP="";
String TB_OUTCOME="";
String CARPCTHTMPR="";
String CARPCTHTFPR="";
String CARPCTHTTPR="";

String enterdby="";




 

     String getexistingdata="select * from tb WHERE "+facility+" "+duration ;
    conn.rs=conn.st.executeQuery(getexistingdata);
    while(conn.rs.next()){
    
         
        //now load the column values here
       
C31D=conn.rs.getString("C31D");
if(C31D==null){C31D=""; }

TB_STATN=conn.rs.getString("TB_STATN");
if(TB_STATN==null){TB_STATN=""; }

TB_STATD=conn.rs.getString("TB_STATD");
if(TB_STATD==null){TB_STATD=""; }

TB_STATP=conn.rs.getString("TB_STATP");
if(TB_STATP==null){TB_STATP=""; }

TB_IPTN=conn.rs.getString("TB_IPTN");
if(TB_IPTN==null){TB_IPTN=""; }

TB_IPTD=conn.rs.getString("TB_IPTD");
if(TB_IPTD==null){TB_IPTD=""; }

TB_IPTP=conn.rs.getString("TB_IPTP");
if(TB_IPTP==null){TB_IPTP=""; }

TB_IPT1=conn.rs.getString("TB_IPT1");
if(TB_IPT1==null){TB_IPT1=""; }

TB_IPT4=conn.rs.getString("TB_IPT4");
if(TB_IPT4==null){TB_IPT4=""; }

TB_IPT9=conn.rs.getString("TB_IPT9");
if(TB_IPT9==null){TB_IPT9=""; }

TB_IPT14=conn.rs.getString("TB_IPT14");
if(TB_IPT14==null){TB_IPT14=""; }

TB_IPT19=conn.rs.getString("TB_IPT19");
if(TB_IPT19==null){TB_IPT19=""; }

TB_IPT20=conn.rs.getString("TB_IPT20");
if(TB_IPT20==null){TB_IPT20=""; }

TB_IPTM=conn.rs.getString("TB_IPTM");
if(TB_IPTM==null){TB_IPTM=""; }

TB_IPTF=conn.rs.getString("TB_IPTF");
if(TB_IPTF==null){TB_IPTF=""; }

TB_SCREENN=conn.rs.getString("TB_SCREENN");
if(TB_SCREENN==null){TB_SCREENN=""; }
        
TB_SCREEND=conn.rs.getString("TB_SCREEND");
if(TB_SCREEND==null){TB_SCREEND=""; }
        
TB_SCREENP=conn.rs.getString("TB_SCREENP");
if(TB_SCREENP==null){TB_SCREENP=""; }
        
TB_OUTCOME=conn.rs.getString("TB_OUTCOME");
if(TB_OUTCOME==null){TB_OUTCOME=""; }

CARPCTHTMPR=conn.rs.getString("CARPCTHTMPR");
if(CARPCTHTMPR==null){CARPCTHTMPR=""; }
        
CARPCTHTMPR=conn.rs.getString("CARPCTHTMPR");
if(CARPCTHTMPR==null){CARPCTHTMPR=""; }
        
CARPCTHTFPR=conn.rs.getString("CARPCTHTFPR");
if(CARPCTHTFPR==null){CARPCTHTFPR=""; }
        
CARPCTHTTPR=conn.rs.getString("CARPCTHTTPR");
if(CARPCTHTTPR==null){CARPCTHTTPR=""; }
 isValidated=conn.rs.getString("isValidated");

        
          
    }
  
      
//     System.out.println("read from session : "+session.getAttribute("isValidated").toString());
 
             String createdtable="" ; 
             
    createdtable+="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\"> TB/HIV</b></legend><table id=\"tbtable\"   style=\"border-color: #e5e5e5;margin-bottom: 3px;  \"><tr class='form-actions'><th colspan='4'><b> Case Sub Area 3: Clinical/ Preventive Services- Additional TB/HIV </b></th></tr>";
    
    createdtable+="<tr><td rowspan=\"3\" ><b> TB_STAT </b></td>"
            + "<td rowspan=\"3\">Proportion of registered new and relapse TB cases with document HIV </td>"
            + "<td  >Number of registered new and relapse TB cases with documented HIV status</td>"
            + "<td>"+TB_STATN+"</td></tr>";
    
    createdtable+="<tr>"
            + "<td  >Total Registered and new relapse TB cases</td>"
            + "<td>"+TB_STATD+"</td></tr>";
    
    createdtable+="<tr>"
            + "<td >Proportion</td>"
            + "<td>"+TB_STATP+"</td></tr>";
    
    
    createdtable+="<tr><td rowspan=\"3\" ><b> TB_IPT </b></td>"
            + "<td rowspan=\"3\">Percentage of PLHIV newly enrolled in HIV clinical care who start isoniazid preventative therapy(IPT) </td>"
            + "<td >PLHIV newly enrolled in HIV clinical care who start isoniazid preventative therapy</td>"
            + "<td>"+TB_IPTN+"</td></tr>";
    
    createdtable+="<tr>"
            + "<td >Total Registered and new relapse TB cases</td>"
            + "<td>"+TB_IPTD+"</td></tr>";
    
    createdtable+="<tr>"
            + "<td >Proportion</td>"
            + "<td>"+TB_IPTP+"</td></tr>";
    
    createdtable+="<tr><td  colspan='2' rowspan='8'></td>"
            + "<td > 1 </td>"
            + "<td>"+TB_IPT1+"</td></tr>";
    
    createdtable+="<tr>"
            + "<td  > 1-4 </td>"
            + "<td>"+TB_IPT4+"</td></tr>";
    
    createdtable+="<tr>"
            + "<td > 5-9 </td>"
            + "<td>"+TB_IPT9+"</td></tr>";
    
    createdtable+="<tr>"
            + "<td > 10-14 </td>"
            + "<td>"+TB_IPT14+"</td></tr>";
    
    
    createdtable+="<tr>"
            + "<td> 15-19 </td>"
            + "<td>"+TB_IPT19+"</td></tr>";
    
    createdtable+="<tr>"
            + "<td> 20+ </td>"
            + "<td>"+TB_IPT20+"</td></tr>";
    
    createdtable+="<tr>"
            + "<td> Total Male </td>"
            + "<td>"+TB_IPTM+"</td></tr>";
    
    createdtable+="<tr>"
            + "<td> Total Female </td>"
            + "<td>"+TB_IPTF+"</td></tr>";
    
    createdtable+="<tr><td rowspan=\"3\" ><b> TB_SCREEN </b></td>"
            + "<td rowspan=\"3\"> Proportion of PLHIV in HIV Clinical care who were screend for TB symptoms at the last clinical visit</td>"
            + "<td>Number of PLHIV in clinical care who were screened for TB </td>"
            + "<td>"+TB_SCREENN+"</td></tr>";
    
    createdtable+="<tr>"
            + "<td>Total Registered and new relapse TB cases</td>"
            + "<td>"+TB_SCREEND+"</td></tr>";
    
    createdtable+="<tr>"
            + "<td>Proportion</td>"
            + "<td>"+TB_SCREENP+"</td></tr>";
    
      createdtable+="<tr><td ><b> TB_OUTCOME </b></td>"
            + "<td colspan=\"2\"> Outcome of TB treatment among registered new and relapse TB cases who are HIV positive</td>"
         
            + "<td>"+TB_OUTCOME+"</td></tr>";
    
      createdtable+=""
              + "<tr><td colspan=\"4\"> 7.4 Number of reigistered TB patients who recieved HIV counselling, testing, and their test results at a USG supported TB service</td></tr>"
         
              + "<tr class='form-actions'><td>MALE</td>"
              
              + "<td>FEMALE</td>"
              + "<td>TOTAL</td></tr>"
           
            + "<tr><td>"+CARPCTHTMPR+"</td>"
            + "<td>"+CARPCTHTFPR+"</td>"
            + "<td>"+CARPCTHTTPR+"</td>"
              + "</tr>   </table>   </fieldset>";
        
        
        
String str="";
//      HTMLWorker htmlWorker = new HTMLWorker(document);
//     String str = "<html><head></head><body>"+header+""+FP_TAB+" </br></br> "+MCH_TAB+" </br></br> "+MATERNITY_TAB+"</br></br>  "+VCT_TAB+"  </br></br></br></br> "+DTC_TAB+" </body></html>";
  str+="<table border=\"1\" style=\"color:black; font-size:6px;\" width=\"90%\">"
               + "<tr>"
               + "<td>"+header+"</td>"
               + "</tr>"
               + "<tr>"
               + "<td>"+createdtable+"</td>"
              + "</tr>"
             
                
               + "</table>"
               + "";
        System.out.println( "PDF Created!" );
           Document document = new Document();
  ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
PdfWriter.getInstance(document, outByteStream);
      document.open();
      
      HTMLWorker htmlWorker = new HTMLWorker(document);
       htmlWorker.parse(new StringReader(str));
       
       CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(false);  
cssResolver.addCss("", true);

   document.close();    
response.setContentType("application/pdf");
response.setContentLength(outByteStream.size());
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=TB.pdf");

ServletOutputStream sos;
sos = response.getOutputStream();
outByteStream.writeTo(sos);
sos.flush();
        
        
         if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     if(conn.st3!=null){conn.st3.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.rs3!=null){conn.rs3.close();}
     if(conn.conn!=null){conn.conn.close();}
   
     }
      catch (SQLException ex) {
            Logger.getLogger(tbpdf.class.getName()).log(Level.SEVERE, null, ex);
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
                    try {
                        processRequest(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(tbpdf.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (CssResolverException ex) {
                        Logger.getLogger(tbpdf.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(tbpdf.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
                    try {
                        processRequest(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(tbpdf.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (CssResolverException ex) {
                        Logger.getLogger(tbpdf.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(tbpdf.class.getName()).log(Level.SEVERE, null, ex);
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
    