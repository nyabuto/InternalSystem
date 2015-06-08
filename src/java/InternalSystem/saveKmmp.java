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
public class saveKmmp extends HttpServlet {
HttpSession session=null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    session =request.getSession();
    
    
    String col=request.getParameter("col");
    String achieved=request.getParameter("achieved");
    
     if(achieved.equals("")){
    achieved=null;
    }
     
     if(achieved.equalsIgnoreCase("Infinity")){  achieved="0";}
     if(achieved.equalsIgnoreCase("NaN")){  achieved="0";}
    dbConn conn=new dbConn();
//get the existing data for the month, year and facility that is already on session

String month="";      
String year="";      
String facil="";
String userid="unknown";

    if(session.getAttribute("userid")!=null){        
userid=session.getAttribute("userid").toString();
}

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
    
String Insertqr= "insert into kmmp  set SubPartnerID='"+facil+"',Annee='"+year+"',Mois='"+month+"', "+col+"="+achieved+" , tableid='"+tableid+"' , user_id='"+userid+"'";
String updateqr="update kmmp set "+col+"="+achieved+" , isValidated='0' where tableid='"+tableid+"'";
//check whether data for that month, year and facility has been saved

String checker="select "+col+" from kmmp where tableid='"+tableid+"'";


conn.rs=conn.st.executeQuery(checker);


if(conn.rs.next()){
    
    conn.st.executeUpdate(updateqr);
    System.out.println("~~ "+updateqr);
}
else {
    System.out.println(">> "+Insertqr);
        conn.st.executeUpdate(Insertqr);

}

    
    PrintWriter out = response.getWriter();
    try {
        /* TODO output your page here. You may use following sample code. */
       
    } finally {
        
        if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         
         if(conn.st!=null){ conn.st.close();}
        
        
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(saveKmmp.class.getName()).log(Level.SEVERE, null, ex);
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
