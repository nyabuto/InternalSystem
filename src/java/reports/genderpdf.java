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
public class genderpdf extends HttpServlet {

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
  
    String form="gender";
      
   
    
 
  
    

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
        




//==================================================================================================
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
   
   
   
   
   String getexistingdata="";
   
   
    if(!county.equals("")){
   
   countywhere=" and countyid = '"+county+"'";    
   
   }
   
   if(!facil.equals("") && reportType.equalsIgnoreCase("2")){
   
   facilitywhere=" and "+form+".SubPartnerID = '"+facil+"'";    
   
   } 
   
    
  
 String joinedwhwere=" where 1=1 "+facilitywhere+"  "+yearwhere+" && "+duration;  
    
      
// String joinedwhwere=" where 1=1 "+yearwhere+" && "+duration;  
    
    
    
//=====================================================================================================    
    
    
    
    
 
    
    
    
    
    
    
        
getexistingdata="select sum(P121DM0) as P121DM0,    sum(P121DF0) as P121DF0,    sum(P121DM10) as P121DM10,    sum(P121DF10) as P121DF10,    sum(P121DM15) as P121DM15,   sum(P121DF15) as P121DF15,   sum(P121DM20) as P121DM20,    sum(P121DF20) as P121DF20,   sum(P121DM25) as  P121DM25,    sum(P121DF25) as P121DF25,    sum(P121DMT) as  P121DMT,    sum(P121DFT) as P121DFT,    sum(P121DTT) as P121DTT,    sum(P122DM0) as P122DM0,    sum(P122DF0) as P122DF0,    sum(P122DM15) as  P122DM15,     sum(P122DF15) as P122DF15,     sum(P122DM25) as P122DM25,     sum(P122DF25) as P122DF25,     sum(P122DMT) as P122DMT,     sum(P122DFT) as P122DFT,     sum(P122DTT) as P122DTT,     sum(P123DM0) as P123DM0,     sum(P123DF0) as P123DF0,     sum(P123DM15) as P123DM15,     sum(P123DF15) as P123DF15,     sum(P123DM25) as P123DM25,     sum(P123DF25) as P123DF25,     sum(P123DMT) as P123DMT,     sum(P123DFT) as P123DFT,     sum(P123DTT) as P123DTT,     sum(P124DM0) as P124DM0,     sum(P124DF0) as P124DF0,     sum(P124DM15) as P124DM15,     sum(P124DF15) as P124DF15,     sum(P124DM25) as P124DM25,     sum(P124DF25) as P124DF25,     sum(P124DMT) as P124DMT,     sum(P124DFT) as P124DFT,     sum(P124DTT) as P124DTT,     sum(GEND_GBV9M) as GEND_GBV9M,     sum(GEND_GBV9F) as GEND_GBV9F,     sum(GEND_GBV9) as GEND_GBV9,     sum(GEND_GBV14M) as GEND_GBV14M,     sum(GEND_GBV14F) as GEND_GBV14F,     sum(GEND_GBV14) as GEND_GBV14,     sum(GEND_GBV17M) as GEND_GBV17M,     sum(GEND_GBV17F) as GEND_GBV17F,     sum(GEND_GBV17) as GEND_GBV17,     sum(GEND_GBV24M) as GEND_GBV24M,     sum(GEND_GBV24F) as GEND_GBV24F,     sum(GEND_GBV24) as GEND_GBV24,     sum(GEND_GBV25M) as GEND_GBV25M,     sum(GEND_GBV25F) as GEND_GBV25F,     sum(GEND_GBV25) as GEND_GBV25,     sum(GEND_GBVM) as GEND_GBVM,     sum(GEND_GBVF) as GEND_GBVF,     sum(GEND_GBV) as GEND_GBV,       sum(P121D0) as P121D0,     sum(P121D10) as P121D10,     sum(P121D15) as P121D15,     sum(P121D20) as P121D20,     sum(P121D25) as P121D25,     sum(P122D0) as P122D0,     sum(P122D15) as P122D15,     sum(P122D25) as P122D25,     sum(P123D0) as P123D0,     sum(P123D15) as P123D15,     sum(P123D25) as P123D25,     sum(P124D0) as P124D0,     sum(P124D15) as P124D15,     sum(P124D25) as P124D25    from "+form+" join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+"  ";

            System.out.println(getexistingdata);
String P121DM0="";
String P121DF0="";
String P121DM10="";
String P121DF10="";
String P121DM15="";
String P121DF15="";
String P121DM20="";
String P121DF20="";
String P121DM25="";
String P121DF25="";
String P121DMT="";
String P121DFT="";
String P121DTT="";
String P122DM0="";
String P122DF0="";
String P122DM15="";
String P122DF15="";
String P122DM25="";
String P122DF25="";
String P122DMT="";
String P122DFT="";
String P122DTT="";
String P123DM0="";
String P123DF0="";
String P123DM15="";
String P123DF15="";
String P123DM25="";
String P123DF25="";
String P123DMT="";
String P123DFT="";
String P123DTT="";
String P124DM0="";
String P124DF0="";
String P124DM15="";
String P124DF15="";
String P124DM25="";
String P124DF25="";
String P124DMT="";
String P124DFT="";
String P124DTT="";
String GEND_GBV9M="";
String GEND_GBV9F="";
String GEND_GBV9="";
String GEND_GBV14M="";
String GEND_GBV14F="";
String GEND_GBV14="";
String GEND_GBV17M="";
String GEND_GBV17F="";
String GEND_GBV17="";
String GEND_GBV24M="";
String GEND_GBV24F="";
String GEND_GBV24="";
String GEND_GBV25M="";
String GEND_GBV25F="";
String GEND_GBV25="";
String GEND_GBVM="";
String GEND_GBVF="";
String GEND_GBV="";


String P121D0="";
String P121D10="";
String P121D15="";
String P121D20="";
String P121D25="";
String P122D0="";
String P122D15="";
String P122D25="";
String P123D0="";
String P123D15="";
String P123D25="";
String P124D0="";
String P124D15="";
String P124D25="";


String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


int counter=0;
 
 
 
 
 
    
    conn.rs=conn.st.executeQuery(getexistingdata);
    while(conn.rs.next()){
   //now check if form was updated and if its one month after data entry
//now load the column values here
        
        
                //====================================================================p122       
P121DM0=conn.rs.getString("P121DM0");
if(P121DM0==null){P121DM0=""; }

P121DF0=conn.rs.getString("P121DF0");
if(P121DF0==null){P121DF0=""; }

P121DM10=conn.rs.getString("P121DM10");
if(P121DM10==null){P121DM10=""; }

P121DF10=conn.rs.getString("P121DF10");
if(P121DF10==null){P121DF10=""; }

P121DM15=conn.rs.getString("P121DM15");
if(P121DM15==null){P121DM15=""; }

P121DF15=conn.rs.getString("P121DF15");
if(P121DF15==null){P121DF15=""; }

P121DM20=conn.rs.getString("P121DM20");
if(P121DM20==null){P121DM20=""; }

P121DF20=conn.rs.getString("P121DF20");
if(P121DF20==null){P121DF20=""; }


P121DM25=conn.rs.getString("P121DM25");
if(P121DM25==null){P121DM25=""; }

P121DF25=conn.rs.getString("P121DF25");
if(P121DF25==null){P121DF25=""; }


P121DMT=conn.rs.getString("P121DMT");
if(P121DMT==null){P121DMT=""; }

P121DFT=conn.rs.getString("P121DFT");
if(P121DFT==null){P121DFT=""; }


P121DTT=conn.rs.getString("P121DTT");
if(P121DTT==null){P121DTT=""; }

//====================================================================p122

P122DM0=conn.rs.getString("P122DM0");
if(P122DM0==null){P122DM0=""; }

P122DF0=conn.rs.getString("P122DF0");
if(P122DF0==null){P122DF0=""; }



P122DM15=conn.rs.getString("P122DM15");
if(P122DM15==null){P122DM15=""; }

P122DF15=conn.rs.getString("P122DF15");
if(P122DF15==null){P122DF15=""; }



P122DM25=conn.rs.getString("P122DM25");
if(P122DM25==null){P122DM25=""; }

P122DF25=conn.rs.getString("P122DF25");
if(P122DF25==null){P122DF25=""; }


P122DMT=conn.rs.getString("P122DMT");
if(P122DMT==null){P122DMT=""; }

P122DFT=conn.rs.getString("P122DFT");
if(P122DFT==null){P122DFT=""; }


P122DTT=conn.rs.getString("P122DTT");
if(P122DTT==null){P122DTT=""; }


//====================================================================p123

P123DM0=conn.rs.getString("P123DM0");
if(P123DM0==null){P123DM0=""; }

P123DF0=conn.rs.getString("P123DF0");
if(P123DF0==null){P123DF0=""; }



P123DM15=conn.rs.getString("P123DM15");
if(P123DM15==null){P123DM15=""; }

P123DF15=conn.rs.getString("P123DF15");
if(P123DF15==null){P123DF15=""; }



P123DM25=conn.rs.getString("P123DM25");
if(P123DM25==null){P123DM25=""; }

P123DF25=conn.rs.getString("P123DF25");
if(P123DF25==null){P123DF25=""; }


P123DMT=conn.rs.getString("P123DMT");
if(P123DMT==null){P123DMT=""; }

P123DFT=conn.rs.getString("P123DFT");
if(P123DFT==null){P123DFT=""; }


P123DTT=conn.rs.getString("P123DTT");
if(P123DTT==null){P123DTT=""; }
     

//====================================================================p124

P124DM0=conn.rs.getString("P124DM0");
if(P124DM0==null){P124DM0=""; }

P124DF0=conn.rs.getString("P124DF0");
if(P124DF0==null){P124DF0=""; }



P124DM15=conn.rs.getString("P124DM15");
if(P124DM15==null){P124DM15=""; }

P124DF15=conn.rs.getString("P124DF15");
if(P124DF15==null){P124DF15=""; }



P124DM25=conn.rs.getString("P124DM25");
if(P124DM25==null){P124DM25=""; }

P124DF25=conn.rs.getString("P124DF25");
if(P124DF25==null){P124DF25=""; }


P124DMT=conn.rs.getString("P124DMT");
if(P124DMT==null){P124DMT=""; }

P124DFT=conn.rs.getString("P124DFT");
if(P124DFT==null){P124DFT=""; }


P124DTT=conn.rs.getString("P124DTT");
if(P124DTT==null){P124DTT=""; }


//=========================================================GEND_GBV

GEND_GBV9M=conn.rs.getString("GEND_GBV9M");
if(GEND_GBV9M==null){GEND_GBV9M=""; }

GEND_GBV9F=conn.rs.getString("GEND_GBV9F");
if(GEND_GBV9F==null){GEND_GBV9F=""; }

GEND_GBV9=conn.rs.getString("GEND_GBV9");
if(GEND_GBV9==null){GEND_GBV9=""; }

//============
GEND_GBV14M=conn.rs.getString("GEND_GBV14M");
if(GEND_GBV14M==null){GEND_GBV14M=""; }

GEND_GBV14F=conn.rs.getString("GEND_GBV14F");
if(GEND_GBV14F==null){GEND_GBV14F=""; }

GEND_GBV14=conn.rs.getString("GEND_GBV14");
if(GEND_GBV14==null){GEND_GBV14=""; }
//=======


//============
GEND_GBV17M=conn.rs.getString("GEND_GBV17M");
if(GEND_GBV17M==null){GEND_GBV17M=""; }

GEND_GBV17F=conn.rs.getString("GEND_GBV17F");
if(GEND_GBV17F==null){GEND_GBV17F=""; }

GEND_GBV17=conn.rs.getString("GEND_GBV17");
if(GEND_GBV17==null){GEND_GBV17=""; }
//=======

//============
GEND_GBV24M=conn.rs.getString("GEND_GBV24M");
if(GEND_GBV24M==null){GEND_GBV24M=""; }

GEND_GBV24F=conn.rs.getString("GEND_GBV24F");
if(GEND_GBV24F==null){GEND_GBV24F=""; }

GEND_GBV24=conn.rs.getString("GEND_GBV24");
if(GEND_GBV24==null){GEND_GBV24=""; }
//=======


//============
GEND_GBV25M=conn.rs.getString("GEND_GBV25M");
if(GEND_GBV25M==null){GEND_GBV25M=""; }

GEND_GBV25F=conn.rs.getString("GEND_GBV25F");
if(GEND_GBV25F==null){GEND_GBV25F=""; }

GEND_GBV25=conn.rs.getString("GEND_GBV25");
if(GEND_GBV25==null){GEND_GBV25=""; }
//=======

//============
GEND_GBVM=conn.rs.getString("GEND_GBVM");
if(GEND_GBVM==null){GEND_GBVM=""; }

GEND_GBVF=conn.rs.getString("GEND_GBVF");
if(GEND_GBVF==null){GEND_GBVF=""; }

GEND_GBV=conn.rs.getString("GEND_GBV");
if(GEND_GBV==null){GEND_GBV=""; }

//=======
//added totals
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
P121D0=conn.rs.getString("P121D0");
if(P121D0==null){P121D0=""; }

P121D10=conn.rs.getString("P121D10");
if(P121D10==null){P121D10=""; }

P121D15=conn.rs.getString("P121D15");
if(P121D15==null){P121D15=""; }

P121D20=conn.rs.getString("P121D20");
if(P121D20==null){P121D20=""; }

P121D25=conn.rs.getString("P121D25");
if(P121D25==null){P121D25=""; }

P122D0=conn.rs.getString("P122D0");
if(P122D0==null){P122D0=""; }

P122D15=conn.rs.getString("P122D15");
if(P122D15==null){P122D15=""; }

P122D25=conn.rs.getString("P122D25");
if(P122D25==null){P122D25=""; }

P123D0=conn.rs.getString("P123D0");
if(P123D0==null){P123D0=""; }

P123D15=conn.rs.getString("P123D15");
if(P123D15==null){P123D15=""; }

P123D25=conn.rs.getString("P123D25");
if(P123D25==null){P123D25=""; }

P124D0=conn.rs.getString("P124D0");
if(P124D0==null){P124D0=""; }

P124D15=conn.rs.getString("P124D15");
if(P124D15==null){P124D15=""; }

P124D25=conn.rs.getString("P124D25");
if(P124D25==null){P124D25=""; }
        
       }
    
    String createdtable="";
    
    
if(1==1){ 
    
    createdtable=header+"<br/><br/><table   border='1' style='border-color: #e5e5e5;margin-bottom: 3px;font-size:10;font-family:cambria;'>"
            + "<tr class='form-actions'><th colspan='6'><b style='text-align:center;'> Prevention Sub Area 12:Gender</b></th></tr>";
    
    createdtable+="<tr><td rowspan='7'><b> P12.1.D: </b></td><td rowspan='7'> GEND_NORM: Number of people completing an intervention pertaining to gender norms, that meets minimum</td><td class='form-actions'>AGE</td><td class='form-actions'>MALE</td><td style='width:80px;' class='form-actions'>FEMALE</td><td class='form-actions'>TOTAL</td></tr>";
    createdtable+="<tr><td><b>0-9</b></td><td>"+P121DM0+"</td><td>"+P121DF0+"</td><td>"+P121D0+"</td></tr>";
    createdtable+="<tr><td><b>10-14</b></td><td>"+P121DM10+"</td><td>"+P121DF10+"</td><td>"+P121D10+"</td></tr>";
    createdtable+="<tr><td><b>15-19</b></td><td>"+P121DM15+"</td><td>"+P121DF15+"</td><td>"+P121D15+"</td></tr>";
    createdtable+="<tr><td><b>20-24</b></td><td>"+P121DM20+"</td><td>"+P121DF20+"</td><td>"+P121D20+"</td></tr>";
    createdtable+="<tr><td><b>25+ </b></b></td><td>"+P121DM25+"</td><td>"+P121DF25+"</td><td>"+P121D25+"</td></tr>";
    createdtable+="<tr><td><b>Total</b></td><td>"+P121DMT+"</td><td>"+P121DFT+"</td><td>"+P121DTT+"</td></tr>";
   
    createdtable+="<tr><td rowspan='4'><b> P12.2.D: </b></td><td rowspan='4'>Gender Based Violence and Coercion: Number of people reached by an individual, small group or community‐level intervention or service that explicitly addresses gender‐based violence and coercion related to HIV/AIDS<td><b>0-14</b></td><td>"+P122DM0+"</td><td>"+P122DF0+"</td><td>"+P122D0+"</td></tr>";
    createdtable+="<tr><td><b>15-24</b></td><td>"+P122DM15+"</td><td>"+P122DF15+"</td><td>"+P122D15+"</td></tr>";
    createdtable+="<tr><td><b>25+</b></td><td>"+P122DM25+"</td><td>"+P122DF25+"</td><td>"+P122D25+"</td></tr>";
    createdtable+="<tr><td><b>Total</b></td><td>"+P122DMT+"</td><td>"+P122DFT+"</td><td>"+P122DTT+"</td></tr>";

    createdtable+="<tr><td rowspan='4'><b> P12.3.D: </b></td><td rowspan='4'>Women's Legal Rights and Protection Number of people reached by an individual, small group, or community‐level intervention or service that explicitly addresses the legal rights and protection of women and girls impacted by HIV/AIDS<td><b>0-14</b></td><td>"+P123DM0+"</td><td>"+P123DF0+"</td><td>"+P123D0+"</td></tr>";
    createdtable+="<tr><td><b>15-24</b></td><td>"+P123DM15+"</td><td>"+P123DF15+"</td><td>"+P123D15+"</td></tr>";
    createdtable+="<tr><td><b>25+</b></td><td>"+P123DM25+"</td><td>"+P123DF25+"</td><td>"+P123D25+"</td></tr>";
    createdtable+="<tr><td><b>Total</b></td><td>"+P123DMT+"</td><td>"+P123DFT+"</td><td>"+P123DTT+"</td></tr>";

    createdtable+="<tr><td rowspan='4'><b> P12.4.D: </b></td><td rowspan='4'>Number of people reached by an individual, small group, or community‐level intervention or service that explicitly aims to increase access to income and productive resources of women and girls impacted by HIV/AIDS M 0-15<td><b>0-14</b></td><td>"+P124DM0+"</td><td>"+P124DF0+"</td><td>"+P124D0+"</td></tr>";
    createdtable+="<tr><td><b>15-24</b></td><td>"+P124DM15+"</td><td>"+P124DF15+"</td><td> "+P124D15+"</td></tr>";
    createdtable+="<tr><td><b>25+</b></td><td>"+P124DM25+"</td><td>"+P124DF25+"</td><td>"+P124D25+"</td></tr>";
    createdtable+="<tr><td><b>Total</b></td><td>"+P124DMT+"</td><td>"+P124DFT+"</td><td>"+P124DTT+"</td></tr>";
    createdtable+="<tr><td rowspan='6'><b> GEND_GBV </b></td><td rowspan='6'>Number of people receiving post-GBV Care<td><b> less than 10 </b> </td><td>"+GEND_GBV9M+"</td><td>"+GEND_GBV9F+"</td><td>"+GEND_GBV9+"</td></tr>";
    createdtable+="<tr><td><b>10-14</b></td><td>"+GEND_GBV14M+"</td><td>"+GEND_GBV14F+"</td><td>"+GEND_GBV14+"</td></tr>";
    createdtable+="<tr><td><b>15-17</b></td><td>"+GEND_GBV17M+"</td><td>"+GEND_GBV17F+"</td><td>"+GEND_GBV17+"</td></tr>";
    createdtable+="<tr><td><b>18-24</b></td><td>"+GEND_GBV24M+"</td><td>"+GEND_GBV24F+"</td><td>"+GEND_GBV24+"</td></tr>";
    createdtable+="<tr><td><b>25+</b></td><td>"+GEND_GBV25M+"</td><td>"+GEND_GBV25F+"</td><td>"+GEND_GBV25+"</td></tr>";
    createdtable+="<tr><td><b>Total</b></td><td>"+GEND_GBVM+"</td><td>"+GEND_GBVF+"</td><td>"+GEND_GBV+"</td></tr>";
    createdtable+="<tr><td></td><td></td><td class='form-actions'>AGE</td><td class='form-actions'>MALE</td><td style='width:80px;' class='form-actions'>FEMALE</td><td class='form-actions'>TOTAL</td></tr>";
            createdtable+= "</table>";
    
   
    
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
