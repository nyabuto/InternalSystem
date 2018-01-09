/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ratios;

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
import org.json.simple.JSONObject;

/**
 *
 * @author GNyabuto
 */
public class SaveRatio extends HttpServlet {
String f_1,f_9,f_14,f_19,f_24,f_29,f_34,f_39,f_49,f_50;
String m_1,m_9,m_14,m_19,m_24,m_29,m_34,m_39,m_49,m_50;
HttpSession session;
String message;
int code;
String county_id,county_name,indicator,is_active;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
         session = request.getSession();
         dbConn conn = new  dbConn();
         
         
        county_id = request.getParameter("county_id");
        indicator = request.getParameter("indicator_id");
        f_1 = request.getParameter("f_1");
        f_9 = request.getParameter("f_9");
        f_14 = request.getParameter("f_14");
        f_19 = request.getParameter("f_19");
        f_24 = request.getParameter("f_24");
        f_29 = request.getParameter("f_29");
        f_34 = request.getParameter("f_34");
        f_39 = request.getParameter("f_39");
        f_49 = request.getParameter("f_49");
        f_50 = request.getParameter("f_50");
        m_1 = request.getParameter("m_1");
        m_9 = request.getParameter("m_9");
        m_14 = request.getParameter("m_14");
        m_19 = request.getParameter("m_19");
        m_24 = request.getParameter("m_24");
        m_29 = request.getParameter("m_29");
        m_34 = request.getParameter("m_34");
        m_39 = request.getParameter("m_39");
        m_49 = request.getParameter("m_49");
        m_50 = request.getParameter("m_50"); 
        is_active="1";
        
        String getCounty = "SELECT County FROM county WHERE CountyID='"+county_id+"'";
        conn.rs = conn.st.executeQuery(getCounty);
        if(conn.rs.next()){
            county_name=conn.rs.getString(1);
        }
        
        String checker="SELECT id FROM ratios WHERE county_id=? AND indicator=? AND is_active=?";
        conn.pst = conn.conn.prepareStatement(checker);
        conn.pst.setString(1, county_id);
        conn.pst.setString(2, indicator);
        conn.pst.setString(3, is_active);
        
        conn.rs=conn.pst.executeQuery();
        if(conn.rs.next()){
         message="Similar records exist";
         code = 0;
        }
        else{
        String insert="INSERT INTO ratios (indicator,county_id,county_name,is_active,f_1,f_9,f_14,f_19,f_24,f_29,f_34,f_39,f_49,f_50,m_1,m_9,m_14,m_19,m_24,m_29,m_34,m_39,m_49,m_50) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
        conn.pst = conn.conn.prepareStatement(insert);
        conn.pst.setString(1, indicator);
        conn.pst.setString(2, county_id);
        conn.pst.setString(3, county_name);
        conn.pst.setString(4, is_active);
        conn.pst.setString(5, f_1);
        conn.pst.setString(6, f_9);
        conn.pst.setString(7, f_14);
        conn.pst.setString(8, f_19);
        conn.pst.setString(9, f_24);
        conn.pst.setString(10, f_29);
        conn.pst.setString(11, f_34);
        conn.pst.setString(12, f_39);
        conn.pst.setString(13, f_49);
        conn.pst.setString(14, f_50);
        conn.pst.setString(15, m_1);
        conn.pst.setString(16, m_9);
        conn.pst.setString(17, m_14);
        conn.pst.setString(18, m_19);
        conn.pst.setString(19, m_24);
        conn.pst.setString(20, m_29);
        conn.pst.setString(21, m_34);
        conn.pst.setString(22, m_39);
        conn.pst.setString(23, m_49);
        conn.pst.setString(24, m_50);
        
        int res = conn.pst.executeUpdate();
        if(res>0){
            message = "<font color=\"green\"><b>Ratio saved successfully.</b></font>";
            code = 1;
        }
        else{
             message = "<font color=\"blue\"><b>No changes detected.</b></font>";
            code = 0;
        }
        }
            JSONObject obj_final = new JSONObject();
            JSONObject obj = new JSONObject();
            obj.put("message", message);
            obj.put("code", code);
            
            obj_final.put("data", obj);
            out.println(obj_final);
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
        Logger.getLogger(SaveRatio.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(SaveRatio.class.getName()).log(Level.SEVERE, null, ex);
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
