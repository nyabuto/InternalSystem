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
import scripts.AddQuarter;

/**
 *
 * @author Geofrey Nyabuto
 */
public class saveNutrition extends HttpServlet {
HttpSession session=null;
    boolean isinsert=true;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    session =request.getSession();
    isinsert=true;
    
    String col=request.getParameter("col");
    String achieved=request.getParameter("achieved");
    
    if(achieved.equals("")){
    achieved=null;
    }
    
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
   
int tempyear=Integer.parseInt(year);
String yearmonth="";
String tempmonth=month;
if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
if(Integer.parseInt(month)>=10){tempyear=Integer.parseInt(year)-1;}
yearmonth=tempyear+tempmonth;
String Insertqr= "replace into nutrition  set SubPartnerID='"+facil+"',Annee='"+year+"',Mois='"+month+"', "+col+"="+achieved+" , tableid='"+tableid+"' , user_id='"+userid+"' , yearmonth='"+yearmonth+"'";
String updateqr="update nutrition set "+col+"="+achieved+" , isValidated='0' where tableid='"+tableid+"'";
//check whether data for that month, year and facility has been saved

String checker="select 1 from nutrition where tableid='"+tableid+"'";




conn.rs=conn.st.executeQuery(checker);
   System.out.println("ISINSERT is "+isinsert);  

if(conn.rs.next()){
    
    System.out.println("_________Update qry result ::"+conn.st.executeUpdate(updateqr));
    //System.out.println("~~ "+updateqr);
}
else {
  if(isinsert=true){
     isinsert=false;     
     
   //if an insert fails, run an update
     if(conn.st.executeUpdate(Insertqr)==0){
     
     conn.st1.executeUpdate(updateqr);
     
     }
  
  }
  else {
  
 System.out.println("LUCKY Update qry result ::"+conn.st.executeUpdate(updateqr));
  
  }
          
          
  // System.out.println("Update qry result ::"+conn.st.executeUpdate(updateqr));
  
}
//a code to loop through all synced records without a quarter
            //the affected tables are "moh711","moh731","moh711_new","kmmp","gender","tb","vmmc","nutrition"
            AddQuarter am= new AddQuarter();
            am.addQuarter();
            //end of sync last month
    
    PrintWriter out = response.getWriter();
    try {
        /* TODO output your page here. You may use following sample code. */
       
    } finally {
        
        if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
        
         if(conn.st!=null){ conn.st.close();}
         if(conn.st1!=null){ conn.st1.close();}

        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(saveNutrition.class.getName()).log(Level.SEVERE, null, ex);
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
