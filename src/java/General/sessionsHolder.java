/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package General;

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
public class sessionsHolder extends HttpServlet {

    HttpSession session=null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            
            session = request.getSession();
            
            String form=request.getParameter("form");
            
            String year=request.getParameter("year");
            String month=request.getParameter("month");
            String facility=request.getParameter("facility");
            
            //make the month to appear like January , 2015 or Oct , 2014
            
            int prevyear=Integer.parseInt(year)-1;
            
            dbConn conn=new dbConn();
            
            //get the facility name 
            String getfacilname="select SubPartnerNom from subpartnera where SubPartnerId='"+facility+"'";
            
            conn.rs=conn.st.executeQuery(getfacilname);
            while(conn.rs.next()){                
             
              session.setAttribute("facilityname", conn.rs.getString(1));
            }
            
            //get facility name
            
            String getmonth="select name from month where id='"+month+"'";
            conn.rs=conn.st.executeQuery(getmonth);
            while(conn.rs.next()){ 
                String yr=year;
                if(Integer.parseInt(month) >=10){
                yr=""+prevyear;
                }
              session.setAttribute("monthname", conn.rs.getString(1)+" ,"+yr);  
            
            }
            
            session.setAttribute("year", year);
            session.setAttribute("monthid", month);
            session.setAttribute("facilityid", facility);
           
          
            
           System.out.println("___ Sessions Holder Page ___");
            
            response.sendRedirect(form);
        } catch (SQLException ex) {
            Logger.getLogger(sessionsHolder.class.getName()).log(Level.SEVERE, null, ex);
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
