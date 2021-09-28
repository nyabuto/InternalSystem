/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalSystem;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class updateProfile extends HttpServlet {
HttpSession session;
String fname,mname,lname,username,password,pass,level,userid,fullname,email;
String found;
MessageDigest m;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, SQLException {
     session=request.getSession();
    dbConn conn = new dbConn();
    
      userid="1";
      userid=session.getAttribute("userid").toString();
    
    fname=request.getParameter("fname").toUpperCase();
    mname=request.getParameter("mname").toUpperCase();
    lname=request.getParameter("lname").toUpperCase();
    email=request.getParameter("email");
    username=request.getParameter("username");
    pass=request.getParameter("password");

       m = MessageDigest.getInstance("MD5");
       m.update(pass.getBytes(), 0, pass.length());
       password = new BigInteger(1, m.digest()).toString(16);
       
    
    String checker="SELECT userid FROM user WHERE ((fname=? && mname=? && lname=?) || username=?) && userid!=?";
    conn.pst=conn.conn.prepareStatement(checker);
    conn.pst.setString(1, fname);
    conn.pst.setString(2, mname);
    conn.pst.setString(3, lname);
    conn.pst.setString(4, username);
    conn.pst.setString(5, userid);
    
    conn.rs=conn.pst.executeQuery();
    if(conn.rs.next()==true){
        
   session.setAttribute("editUser", "<font color=\"blue\">"+fullname+"</font> <font color=\"red\"> Already exist.</font>");     
    }
    
    
    else{
//     ADD THE USER
        String inserter="UPDATE user SET fname=?,mname=?,lname=?,username=?,password=?,email=? WHERE userid=?";
        conn.pst=conn.conn.prepareStatement(inserter);
        conn.pst.setString(1, fname);
        conn.pst.setString(2, mname);
        conn.pst.setString(3, lname);
        conn.pst.setString(4, username);
        conn.pst.setString(5, password);
        conn.pst.setString(6, userid);
        conn.pst.setString(7, email);
    
        conn.pst.executeUpdate();
        
        fullname=fname+" "+mname+" "+lname;
      
             
             session.setAttribute("fullname", fullname);
             session.setAttribute("username", username);
             session.setAttribute("fname", fname);
             session.setAttribute("mname", mname);
             session.setAttribute("lname", lname);
             session.setAttribute("email", email);
     session.setAttribute("editProfile", "<font color=\"black\">"+fullname+"</font> <font color=\"green\"> Details edited successfully.</font>");
    }
    
    
    response.sendRedirect("editProfile.jsp");
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
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(updateProfile.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(updateProfile.class.getName()).log(Level.SEVERE, null, ex);
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
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(updateProfile.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(updateProfile.class.getName()).log(Level.SEVERE, null, ex);
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
