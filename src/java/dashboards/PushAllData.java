/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboards;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GNyabuto
 */
public class PushAllData extends HttpServlet {
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           PushDataSet2 ds2 = new PushDataSet2();
           
      Map m1 = new HashMap(); 
      m1.put("startyearmonth", "201710");
      m1.put("endyearmonth", "201812");
      m1.put("startdate", "2017-10-01");
      m1.put("enddate", "2018-12-31");
//      m1.put("mfl_code", "15288");
           
           
        System.out.println("called push");
            ds2.current_art_care(m1);//moh731
            ds2.new_art_care(m1);//moh731
            ds2.pmtct(m1);//moh731
            ds2.RetNum(m1);//moh731
            ds2.RetDen(m1);//moh731
            ds2.PMTCT_ART(m1);//moh731
            
            ds2.viral_load(m1);//viral load data upload
            ds2.pmtct_eid(m1);//eid tested and pos
            ds2.TBPrev(m1);//IPT Module
            ds2.VMMC_Circ(m1);//vmmc module
            ds2.SGBV(m1);//SGBV Module
            ds2.pmtct_fo(m1);//HEI Cohort
            ds2.prep(m1);//PREP Data
             
            System.out.println("end pushed data");
        } finally {
            out.close();
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
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(PushAllData.class.getName()).log(Level.SEVERE, null, ex);
    }
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
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(PushAllData.class.getName()).log(Level.SEVERE, null, ex);
    }
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
