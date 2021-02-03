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
import java.util.Calendar;
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

public class loadLastMontofQuarter extends HttpServlet {
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            session =request.getSession();
            response.setContentType("text/html;charset=UTF-8");
String sessionmonth="";
            //The current year such that if a year is not passed
 if(session.getAttribute("monthid")!=null){
    
    sessionmonth=session.getAttribute("monthid").toString();
    }
//System.out.println("sessionmonth   "+sessionmonth);

Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);  
int currentmonth= cal.get(Calendar.MONTH)+1;

String above10="";



if(currentmonth>=10)
{

year+=1;
above10=" and  id <='"+currentmonth+"' ";
    
}

            String passedyear =""+year;

            //String mywhere=" where id <='"+currentmonth+"' ";
            String mywhere="  ";
            
            if (request.getParameter("year") != null&&!request.getParameter("year").equals("")) {

                passedyear = request.getParameter("year");
                //if the passsed year is the current year, then disable future months from appearing in data entry and report generation selects.
             if(year==new Integer(passedyear)){
             mywhere=" where (( id <='"+currentmonth+"') or (id > '9' "+above10+" )) and id in (3,6,9,12) ";
                 
             }
             else {
             mywhere=" where  id in (3,6,9,12) ";
             }
             
            }
            //get the previous year

            int prevyear = 0;

            if (!passedyear.equals("")) {

                prevyear = Integer.parseInt(passedyear) - 1;

            }

            dbConn conn = new dbConn();

            String getmonths = "select * from month "+mywhere+" order by mois asc";
           // System.out.println(""+getmonths);
            String months = "<option value=''>Select Month </option>";


            conn.rs = conn.st.executeQuery(getmonths);

            while (conn.rs.next()) {

                
               if(sessionmonth.equalsIgnoreCase(conn.rs.getString("id"))){
               
                  //If selected year is 2015, prev year is 2014  . Oct, Nov, Dec will appear like October, 2014 while the others will appear like jan , 2015
                //if no year passed, show october only
                if (conn.rs.getInt("id") >= 10) {
                    if (prevyear != 0) {

                        months += "<option selected value='" + conn.rs.getString("id") + "'>" + conn.rs.getString("name") + " ," + prevyear + "</option> ";
                    } else {
                        months += "<option  selected value='" + conn.rs.getString("id") + "'>" + conn.rs.getString("name") + "</option> ";
                    }
                                                } 
                else if (conn.rs.getInt("id") < 10) {

                    if (!passedyear.equals("")) {

                        months += "<option selected value='" + conn.rs.getString("id") + "'>" + conn.rs.getString("name") + " ," + passedyear + "</option> ";
                    } else {
                        months += "<option selected value='" + conn.rs.getString("id") + "'>" + conn.rs.getString("name") + "</option> ";
                    }
                }
               
               }
               else{
                //If selected month is 2015, prev year is 2014  . Oct, Nov Dec will appear like October, 2014 while the others will appear like jan , 2015
                //if no year passed, show october only
                if (conn.rs.getInt("id") >= 10) {
                    if (prevyear != 0) {

                        months += "<option value='" + conn.rs.getString("id") + "'>" + conn.rs.getString("name") + " ," + prevyear + "</option> ";
                    } else {
                        months += "<option value='" + conn.rs.getString("id") + "'>" + conn.rs.getString("name") + "</option> ";
                    }
                } else if (conn.rs.getInt("id") < 10) {

                    if (!passedyear.equals("")) {

                        months += "<option value='" + conn.rs.getString("id") + "'>" + conn.rs.getString("name") + " ," + passedyear + "</option> ";
                                                } 
                    else {
                        months += "<option value='" + conn.rs.getString("id") + "'>" + conn.rs.getString("name") + "</option> ";
                    }
                }
               }
            }
            PrintWriter out = response.getWriter();
            try {

                out.println(months);

            } finally {
                   if(conn.conn!=null){ conn.conn.close();}
               if(conn.rs!=null){ conn.rs.close();}
               if(conn.st!=null){ conn.st.close();}
                out.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(loadYear.class.getName()).log(Level.SEVERE, null, ex);
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
