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

/**
 *
 * @author Geofrey Nyabuto
 */
public class loadMonth extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");

            //The current year such that if a year is not passed


            String passedyear = "";

            if (request.getParameter("year") != null) {

                passedyear = request.getParameter("year");

            }
            //get the previous year

            int prevyear = 0;

            if (!passedyear.equals("")) {

                prevyear = Integer.parseInt(passedyear) - 1;

            }

            dbConn conn = new dbConn();

            String getmonths = "select * from month order by mois asc";

            String months = "<option value=''>Select Month </option>";


            conn.rs = conn.st.executeQuery(getmonths);

            while (conn.rs.next()) {

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
                    } else {
                        months += "<option value='" + conn.rs.getString("id") + "'>" + conn.rs.getString("name") + "</option> ";
                    }
                }

            }
            PrintWriter out = response.getWriter();
            try {

                out.println(months);

            } finally {
                  conn.conn.close();
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
