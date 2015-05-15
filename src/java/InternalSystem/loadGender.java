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
public class loadGender extends HttpServlet {

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
        
        
    String getexistingdata="select * from gender where tableid='"+tableid+"'";
    
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


    
    conn.rs=conn.st.executeQuery(getexistingdata);
    while(conn.rs.next()){
    
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

    }
    
    String createdtable="<tr class='form-actions'><th colspan='6'><b> Prevention Sub Area 12:Gender</b></th></tr>";
    
    createdtable+="<tr><td rowspan='7'><b> P12.1.D: </b></td><td rowspan='7'>GEND_NORM: Number of people completing an intervention pertaining to gender norms, that meets minimum</td><td class='form-actions'>Age</td><td class='form-actions'>MALE</td><td style='width:80px;' class='form-actions'>FEMALE</td><td class='form-actions'>TOTAL</td></tr>";
    createdtable+="<tr><td><b>0-9</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P121DM0');p121total();\" value='"+P121DM0+"' name='P121DM0' id='P121DM0' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P121DF0');p121total();\" value='"+P121DF0+"' name='P121DF0' id='P121DF0' autofocus></td><td></td></tr>";
    createdtable+="<tr><td><b>10-14</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P121DM10');p121total();\" value='"+P121DM10+"' name='P121DM10' id='P121DM10' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P121DF10');p121total();\" value='"+P121DF10+"' name='P121DF10' id='P121DF10' autofocus></td><td></td></tr>";
    createdtable+="<tr><td><b>15-19</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P121DM15');p121total();\" value='"+P121DM15+"' name='P121DM15' id='P121DM15' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P121DF15');p121total();\" value='"+P121DF15+"' name='P121DF15' id='P121DF15' autofocus></td><td></td></tr>";
    createdtable+="<tr><td><b>20-24</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P121DM20');p121total();\" value='"+P121DM20+"' name='P121DM20' id='P121DM20' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P121DF20');p121total();\" value='"+P121DF20+"' name='P121DF20' id='P121DF20' autofocus></td><td></td></tr>";
    createdtable+="<tr><td><b>25+</b></b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P121DM25');p121total();\" value='"+P121DM25+"' name='P121DM25' id='P121DM25' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P121DF25');p121total();\" value='"+P121DF25+"' name='P121DF25' id='P121DF25' autofocus></td><td></td></tr>";
    createdtable+="<tr><td><b>Total</b></td><td><input type='text' tabindex='-1' readonly onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P121DMT');p121total();\" value='"+P121DMT+"' name='P121DMT' id='P121DMT' autofocus></td><td><input type='text' tabindex='-1' readonly onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P121DFT');p121total();\" value='"+P121DFT+"' name='P121DFT' id='P121DFT' autofocus></td><td><input type='text' tabindex='-1' readonly   value='"+P121DTT+"' name='P121DTT' id='P121DTT' autofocus></td></tr>";
   
    createdtable+="<tr><td rowspan='4'><b> P12.2.D: </b></td><td rowspan='4'>Gender Based Violence and Coercion: Number of people reached by an individual, small group or community‐level intervention or service that explicitly addresses gender‐based violence and coercion related to HIV/AIDS<td><b>0-14</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P122DM0');p122total();\" value='"+P122DM0+"' name='P122DM0' id='P122DM0' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P122DF0');p122total();\" value='"+P122DF0+"' name='P122DF0' id='P122DF0' autofocus></td><td></td></tr>";
    createdtable+="<tr><td><b>15-24</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P122DM15');p122total();\" value='"+P122DM15+"' name='P122DM15' id='P122DM15' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P122DF15');p122total();\" value='"+P122DF15+"' name='P122DF15' id='P122DF15' autofocus></td><td></td></tr>";
    createdtable+="<tr><td><b>25+</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P122DM25');p122total();\" value='"+P122DM25+"' name='P122DM25' id='P122DM25' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P122DF25');p122total();\" value='"+P122DF25+"' name='P122DF25' id='P122DF25' autofocus></td><td></td></tr>";
    createdtable+="<tr><td><b>Total</b></td><td><input type='text' tabindex='-1' readonly onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P122DMT');p122total();\" value='"+P122DMT+"' name='P122DMT' id='P122DMT' autofocus></td><td><input type='text' tabindex='-1' readonly onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P122DFT');p122total();\" value='"+P122DFT+"' name='P122DFT' id='P122DFT' autofocus></td><td><input type='text' tabindex='-1' readonly   value='"+P122DTT+"' name='P122DTT' id='P122DTT' autofocus></td></tr>";

    createdtable+="<tr><td rowspan='4'><b> P12.3.D: </b></td><td rowspan='4'>Women's Legal Rights and Protection Number of people reached by an individual, smallgroup, or community‐level intervention or service that explicitly addresses the legal rights and protection of women and girls impacted by HIV/AIDS<td><b>0-14</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P123DM0');p123total();\" value='"+P123DM0+"' name='P123DM0' id='P123DM0' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P123DF0');p123total();\" value='"+P123DF0+"' name='P123DF0' id='P123DF0' autofocus></td><td></td></tr>";
    createdtable+="<tr><td><b>15-24</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P123DM15');p123total();\" value='"+P123DM15+"' name='P123DM15' id='P123DM15' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P123DF15');p123total();\" value='"+P123DF15+"' name='P123DF15' id='P123DF15' autofocus></td><td></td></tr>";
    createdtable+="<tr><td>2<b>25+</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P123DM25');p123total();\" value='"+P123DM25+"' name='P123DM25' id='P123DM25' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P123DF25');p123total();\" value='"+P123DF25+"' name='P123DF25' id='P123DF25' autofocus></td><td></td></tr>";
    createdtable+="<tr><td><b>Total</b></td><td><input type='text' tabindex='-1' readonly onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P123DMT');p123total();\" value='"+P123DMT+"' name='P123DMT' id='P123DMT' autofocus></td><td><input type='text' tabindex='-1' readonly onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P123DFT');p123total();\" value='"+P123DFT+"' name='P123DFT' id='P123DFT' autofocus></td><td><input type='text' tabindex='-1' readonly   value='"+P123DTT+"' name='P123DTT' id='P123DTT' autofocus></td></tr>";

    createdtable+="<tr><td rowspan='4'><b> P12.4.D: </b></td><td rowspan='4'>Number of people reached by an individual, small group, or community‐level intervention or service that explicitly aims to increase access to income and productive resources of women and girls impacted by HIV/AIDS M 0-15<td><b>0-14</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P124DM0');p124total();\" value='"+P124DM0+"' name='P124DM0' id='P124DM0' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P124DF0');p124total();\" value='"+P124DF0+"' name='P124DF0' id='P124DF0' autofocus></td><td></td></tr>";
    createdtable+="<tr><td><b>15-24</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P124DM15');p124total();\" value='"+P124DM15+"' name='P124DM15' id='P124DM15' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P124DF15');p124total();\" value='"+P124DF15+"' name='P124DF15' id='P124DF15' autofocus></td><td></td></tr>";
    createdtable+="<tr><td><b>25+</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P124DM25');p124total();\" value='"+P124DM25+"' name='P124DM25' id='P124DM25' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P124DF25');p124total();\" value='"+P124DF25+"' name='P124DF25' id='P124DF25' autofocus></td><td></td></tr>";
    createdtable+="<tr><td><b>Total</b></td><td><input type='text' tabindex='-1' readonly onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P124DMT');p124total();\" value='"+P124DMT+"' name='P124DMT' id='P124DMT' autofocus></td><td><input type='text' tabindex='-1' readonly onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P124DFT');p124total();\" value='"+P124DFT+"' name='P124DFT' id='P124DFT' autofocus></td><td><input type='text' tabindex='-1' readonly   value='"+P124DTT+"' name='P124DTT' id='P124DTT' autofocus></td></tr>";
   createdtable+="<tr><td rowspan='6'><b> GEND_GBV </b></td><td rowspan='6'>Number of people receiving post-GBV Care<td><b>0-14</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('GEND_GBV9M');p124total();\" value='"+GEND_GBV9M+"' name='GEND_GBV9M' id='GEND_GBV9M' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('GEND_GBV9F');p124total();\" value='"+GEND_GBV9F+"' name='GEND_GBV9F' id='GEND_GBV9F' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('GEND_GBV9');\" value='"+GEND_GBV9+"' name='GEND_GBV9' id='GEND_GBV9' autofocus></td></tr>";
   createdtable+="<tr><td><b>15-24</b></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P124DM15');p124total();\" value='"+P124DM15+"' name='P124DM15' id='P124DM15' autofocus></td><td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('P124DF15');p124total();\" value='"+P124DF15+"' name='P124DF15' id='P124DF15' autofocus></td><td></td></tr>";
    
    
      //System.out.println(createdtable);
      
    PrintWriter out = response.getWriter();
    try {
        /* TODO output your page here. You may use following sample code. */
  
        out.println(createdtable);
    } finally {
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(loadGender.class.getName()).log(Level.SEVERE, null, ex);
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
