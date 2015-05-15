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
public class loadKmmp extends HttpServlet {

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
        
        
    String getexistingdata="select * from kmmp where tableid='"+tableid+"'";
    
String KMMP1="";
String KMMP2="";
String KMMP3a="";
String KMMP3b="";
String KMMP3c="0";
String KMMP4="";
String KMMP5a="";
String KMMP5b="";
String KMMP5c="";
String HV0205="";
String HV0206="";

    
    conn.rs=conn.st.executeQuery(getexistingdata);
    while(conn.rs.next()){
    
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
    
    String createdtable="<tr class='form-actions'><th colspan='1'><b> KMMP OUTPUT DATA</b></th><th>Total</th></tr>";
    
    createdtable+="<tr><td><b> 1 </b></td><td colspan='2'>No of New HIV positive clients enrolled in KMMP Services (ANC and PN) </td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('KMMP1','"+tableid+"');\" value='"+KMMP1+"' name='KMMP1' id='KMMP1' autofocus></td></tr>";
    
    createdtable+="<tr><td><b> 2 </b></td><td colspan='2'>No of New HIV positive clients enrolled in KMMP Services (ANC and PN) </td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('KMMP2','"+tableid+"');\" value='"+KMMP2+"' name='KMMP1' id='KMMP2'></td></tr>";
    
    createdtable+="<tr><td rowspan='3'><b> 3 </b></td><td colspan='2'> a) No. of HIV-positive pregnant women enrolled in KMMP Services </td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"showpercent();autosave('KMMP3a','"+tableid+"');\" value='"+KMMP3a+"' name='KMMP3a' id='KMMP3a'></td></tr>";
    
    createdtable+="<tr><td colspan='2'>  b) Total number of HIV-positive pregnant women in facility (New positive and Knwn Positive-MOH731) </td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"showpercent();autosave('KMMP3b','"+tableid+"');\" value='"+KMMP3b+"' name='KMMP3b' id='KMMP3b'></td></tr>";
    
    createdtable+="<tr><td colspan='2'><b> Perceantage of new IV-positive pregnant women enrolled in KMMP Services </b></td><td><input type='text' value='"+KMMP3c+"' readonly name='KMMP3c' id='KMMP3c'></td></tr>";
      
    createdtable+="<tr><td ><b> 4 </b></td><td colspan='2'>No. of KMMP support group sessions held </td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('KMMP4','"+tableid+"');\" value='"+KMMP4+"' name='KMMP4' id='KMMP4'></td></tr>";
     
      createdtable+="<tr><td rowspan='3'><b> 5 </b></td><td rowspan='3'>Defaulter Tracing </td><td>New Defaulted Clients </td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('KMMP5a','"+tableid+"');\" value='"+KMMP5a+"' name='KMMP5a' id='KMMP5a'></td></tr>";
     
      createdtable+="<tr><td>Clients Reached</td><td><input type='text' onblur=\"autosave('KMMP5b','"+tableid+"');\" value='"+KMMP5b+"' onkeypress=\"return numbers(event,this);\" name='KMMP5b' id='KMMP5b'></td></tr>";
      
      createdtable+="<tr><td>Succesfully Resolved</td><td><input type='text' onkeypress=\"return numbers(event,this);\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('KMMP5c','"+tableid+"');\" value='"+KMMP5c+"' name='KMMP5c' id='KMMP5c'></td></tr>";
   
      createdtable+="<tr><td></td><td colspan='2'>MOH 731 HV02-05 Known positive status (at entry into ANC) :</td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('HV0205','"+tableid+"');\" value='"+HV0205+"' name='HV0205' id='HV0205'></td></tr>";
   
      createdtable+="<tr><td></td><td colspan='2'>MOH 731 HV02-06 Antenatal:</td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('HV0206','"+tableid+"');\" value='"+HV0206+"' name='HV0206' id='HV0206'></td></tr>";
   
    
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
