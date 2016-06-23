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
 * @author Emmanuel Kaunda
 */
public class save711new extends HttpServlet {
HttpSession session=null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    session =request.getSession();
        PrintWriter out = response.getWriter();
    
    String col=request.getParameter("col");
    String achieved=request.getParameter("achieved");
    
    
    dbConn conn=new dbConn();
//get the existing data for the month, year and facility that is already on session

String month="";      
String year="";      
String facil="";
String userid="unknown";
 String error="success";

        if(achieved.equals("")){achieved="0";}
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
   if(year.equals("") || month.equals("") ||facil.equals("") ){
       error="<font color=\"red\">ERROR : Please select year and facility.</font>";}
    else{
       
           String yearmonth="";
String tempmonth=month;
int pepfaryear=Integer.parseInt(year);

if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
else {pepfaryear--;}

yearmonth=pepfaryear+""+tempmonth;
        error="success";  
String Insertqr= "insert into moh711_new set SubPartnerID='"+facil+"',Annee='"+year+"',Mois='"+month+"', "+col+"='"+achieved+"' , ID='"+tableid+"' ,yearmonth='"+yearmonth+"', userid='"+userid+"'";
//System.out.println(Insertqr);
String updateqr="update  moh711_new set "+col+"='"+achieved+"' where id='"+tableid+"'";
//check whether data for that month, year and facility has been saved

String checker="select "+col+" from moh711_new where ID='"+tableid+"'";


conn.rs=conn.st.executeQuery(checker);


if(conn.rs.next()){
    
    conn.st.executeUpdate(updateqr);
    System.out.println("~~ "+updateqr);
}
else {
    System.out.println(">> "+Insertqr);
        conn.st.executeUpdate(Insertqr);

}
   }
        System.out.println("error : "+error);
 out.println(error);
 
  if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.conn!=null){conn.conn.close();}
   
   
    try {
        /* TODO output your page here. You may use following sample code. */
       
    } finally {
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(save711.class.getName()).log(Level.SEVERE, null, ex);
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
