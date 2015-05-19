/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalSystem;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class loadVmmc extends HttpServlet {

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
    
    if(session.getAttribute("year")!=null){        
   year=session.getAttribute("year").toString();
    }
      if(session.getAttribute("monthid")!=null){        
   month=session.getAttribute("monthid").toString();
    }
   
        if(session.getAttribute("facilityid")!=null){        
   facil=session.getAttribute("facilityid").toString();
    }
    String tableid=year+"_"+month+"_"+facil;
        
        
    String getexistingdata="select * from vmmc where tableid='"+tableid+"'";
    
String P51D1="";
String P51D9="";
String P51D10="";
String P51D19="";
String P51D24="";
String P51D49="";
String P51D50="";
String P51DT="";
String P521DM="";
String P521DS="";
String P521DT="";
String P522DM="";
String P522DS="";
String P522DT="";
String P52DM="";
String P52DS="";
String P52DT="";
String P511KP="";
String P511KN="";
String P511KU="";
String P511Surg="";
String P511Dev="";
String P53DF="";
String P53DO="";
String P53DM="";
String P53D="";
String P54D="";





    conn.rs=conn.st.executeQuery(getexistingdata);
    while(conn.rs.next()){
    
        //now load the column values here
       
P51D1=conn.rs.getString("P51D1");
if(P51D1==null){P51D1=""; }

P51D9=conn.rs.getString("P51D9");
if(P51D9==null){P51D9=""; }

P51D10=conn.rs.getString("P51D10");
if(P51D10==null){P51D10=""; }

P51D19=conn.rs.getString("P51D19");
if(P51D19==null){P51D19=""; }

P51D24=conn.rs.getString("P51D24");
if(P51D24==null){P51D24=""; }

P51D49=conn.rs.getString("P51D49");
if(P51D49==null){P51D49=""; }

P51D50=conn.rs.getString("P51D50");
if(P51D50==null){P51D50=""; }

P51DT=conn.rs.getString("P51DT");
if(P51DT==null){P51DT=""; }

P521DM=conn.rs.getString("P521DM");
if(P521DM==null){P521DM=""; }

P521DS=conn.rs.getString("P521DS");
if(P521DS==null){P521DS=""; }

P521DT=conn.rs.getString("P521DT");
if(P521DT==null){P521DT=""; }  



P522DM=conn.rs.getString("P522DM");
if(P522DM==null){P522DM=""; }

P522DS=conn.rs.getString("P522DS");
if(P522DS==null){P522DS=""; }

P522DT=conn.rs.getString("P522DT");
if(P522DT==null){P522DT=""; }


P52DM=conn.rs.getString("P52DM");
if(P52DM==null){P52DM=""; }


P52DS=conn.rs.getString("P52DS");
if(P52DS==null){P52DS=""; }


P52DT=conn.rs.getString("P52DT");
if(P52DT==null){P52DT=""; }


P511KP=conn.rs.getString("P511KP");
if(P511KP==null){P511KP=""; }


P511KN=conn.rs.getString("P511KN");
if(P511KN==null){P511KN=""; }

P511KU=conn.rs.getString("P511KU");
if(P511KU==null){P511KU=""; }

P511Surg=conn.rs.getString("P511Surg");
if(P511Surg==null){P511Surg=""; }


P511Dev=conn.rs.getString("P511Dev");
if(P511Dev==null){P511Dev=""; }

P53DF=conn.rs.getString("P53DF");
if(P53DF==null){P53DF=""; }

P53DO=conn.rs.getString("P53DO");
if(P53DO==null){P53DO=""; }

P53DM=conn.rs.getString("P53DM");
if(P53DM==null){P53DM=""; }

P53D=conn.rs.getString("P53D");
if(P53D==null){P53D=""; }
        
 P54D=conn.rs.getString("P54D");
if(P54D==null){P54D=""; }       

    }
    
    String createdtable="<tr class='form-actions'><th colspan='4'>Number of Males CircumCised as part of the minimum package of MC for HIV Prevention Services</th></tr>";
    
    createdtable+="<tr><td rowspan='8'><b> P5.1.D </b></td><td colspan='3'><b> <1 </b></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P51D1');p51dtotal();\" value='"+P51D1+"' name='P51D1' id='P51D1' autofocus></td></tr>";
    createdtable+="<tr><td colspan='3'><b> 1-9 </b></td></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P51D9');p51dtotal();\" value='"+P51D9+"' name='P51D9' id='P51D9' ></td></tr>";
    createdtable+="<tr><td colspan='3'><b> 10-14 </b></td></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P51D10');p51dtotal();\" value='"+P51D10+"' name='P51D10' id='P51D10' ></td></tr>";
    createdtable+="<tr><td colspan='3'><b> 15-19 </b></td></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P51D19');p51dtotal();\" value='"+P51D19+"' name='P51D19' id='P51D19' ></td></tr>";
    createdtable+="<tr><td colspan='3'><b> 20-24 </b></td></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P51D24');p51dtotal();\" value='"+P51D24+"' name='P51D24' id='P51D24' ></td></tr>";
    createdtable+="<tr><td colspan='3'><b> 25-49 </b></td></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P51D49');p51dtotal();\" value='"+P51D49+"' name='P51D49' id='P51D49' ></td></tr>";
    createdtable+="<tr><td colspan='3'><b> 50+ </b></td></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P51D50');p51dtotal();\" value='"+P51D50+"' name='P51D50' id='P51D50' ></td></tr>";
    createdtable+="<tr><td colspan='3'><b> Total </b></td></td><td><input style='width:100px;' type='text' tabindex='-1' readonly   value='"+P51DT+"' name='P51DT' id='P51DT' ></td></tr>";
    
    createdtable+="<tr class='form-actions'><th colspan='4'>Number of clients circumcised who experienced one or more moderate or severe adverse event(s) within the reporting period</th></tr>";
   
    createdtable+="<tr><td rowspan='4'><b> P5.2.D </b></td><td ></td><td><b>Moderate</b></td><td><b>Severe</b></td><td><b>Total</b></td></tr>";
    createdtable+="<tr><td><b>During circumcision</b></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P521DM');p52dtotal();\" value='"+P521DM+"' name='P521DM' id='P521DM' ></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P521DS');p52dtotal();\" value='"+P521DS+"' name='P521DS' id='P521DS' ></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" tabindex='-1' readonly onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P521DT');\" value='"+P521DT+"' name='P521DT' id='P521DT' ></td></tr>";
    createdtable+="<tr><td><b>Post circumcision</b></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P522DM');p52dtotal();\" value='"+P522DM+"' name='P522DM' id='P522DM' ></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P522DS');p52dtotal();\" value='"+P522DS+"' name='P522DS' id='P522DS' ></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" tabindex='-1' readonly onkeypress=\"return numbers(event,this);\"  value='"+P522DT+"' name='P522DT' id='P522DT' ></td></tr>";
    createdtable+="<tr><td><b>Total adverse events</b></td><td><input style='width:100px;' tabindex='-1' readonly type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\"  value='"+P52DM+"' name='P52DM' id='P52DM' ></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" tabindex='-1' readonly onkeypress=\"return numbers(event,this);\"  value='"+P52DS+"' name='P52DS' id='P52DS' autofocus></td><td><input style='width:100px;' tabindex='-1' readonly type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\"  value='"+P52DT+"' name='P52DT' id='P52DT' ></td></tr>";
   
     createdtable+="<tr class='form-actions'><th colspan='4'>HIV Status of MC Clients</th></tr>";
     createdtable+="<tr><td rowspan='3'><b> P5.1.1.K </b></td><td colspan='3'><b>Tested/self-reported positive</b></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P511KP');\" value='"+P511KP+"' name='P511KP' id='P511KP' ></td></tr>";
     createdtable+="<tr><td colspan='3'><b>Tested negative</b></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P511KN');\" value='"+P511KN+"' name='P511KN' id='P511KN' ></td></tr>";
     createdtable+="<tr><td colspan='3'><b>Unknown/self-reported negative</b></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P511KU');\" value='"+P511KU+"' name='P511KU' id='P511KU' ></td></tr>";
    
     createdtable+="<tr class='form-actions'><th colspan='4'>Circumcsion technique</th></tr>";
     createdtable+="<tr><td rowspan='2'><b> P5.1.1.T </b></td><td colspan='3'><b>Surgical VMMC</b></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P511Surg');p511ttotal();\" value='"+P511Surg+"' name='P511Surg' id='P511Surg' ></td></tr>";
     createdtable+="<tr><td colspan='3'><b>Device-Based VMMC</b></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P511Dev');\" value='"+P511Dev+"' name='P511Dev' id='P511Dev' ></td></tr>";
     
     createdtable+="<tr class='form-actions'><th colspan='4'>Number of locations providing MC surgery as part of the minimum package of MC for HIV prevention services within the reporting period </th></tr>";
     createdtable+="<tr><td rowspan='4'><b> P5.3.D </b></td><td colspan='3'><b>Fixed/Static</b></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P53DF');p53dtotal();\" value='"+P53DF+"' name='P53DF' id='P53DF' ></td></tr>";
     createdtable+="<tr><td colspan='3'><b>Outreach</b></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P53DO');p53dtotal();\" value='"+P53DO+"' name='P53DO' id='P53DO' ></td></tr>";
     createdtable+="<tr><td colspan='3'><b>Mobile</b></td><td><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P53DM');p53dtotal();\" value='"+P53DM+"' name='P53DM' id='P53DM' ></td></tr>";
     createdtable+="<tr><td colspan='3'><b>Total</b></td><td><input readonly tabindex='-1' style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\"  value='"+P53D+"' name='P53D' id='P53D' ></td></tr>";
     
     createdtable+="<tr class='form-actions'><th colspan='4'>Number of males circumcised within the reporting period who return at least once for postoperative follow‐up care (routine or emergent) within 14 days of surgery </th></tr>";
     createdtable+="<tr><td rowspan='4'><b> P5.4.D </b></td><td colspan='4'><input style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P54D');\" value='"+P54D+"' name='P54D' id='P54D' ></td></tr>";
     
     
 
    
    
      System.out.println(createdtable);
      
    PrintWriter out = response.getWriter();
    try {
        /* TODO output your page here. You may use following sample code. */
  
        out.println(createdtable);
    } finally {
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(loadKmmp.class.getName()).log(Level.SEVERE, null, ex);
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
