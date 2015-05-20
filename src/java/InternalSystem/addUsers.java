/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalSystem;

import General.IdGenerator;
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
public class addUsers extends HttpServlet {
HttpSession session;
String fname,mname,lname,username,password,pass,level,userid,fullname;
String found;
MessageDigest m;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
    session=request.getSession();
    dbConn conn = new dbConn();
    
    fname=request.getParameter("fname").toUpperCase();
    mname=request.getParameter("mname").toUpperCase();
    lname=request.getParameter("lname").toUpperCase();
    username=request.getParameter("username");
    pass=request.getParameter("password");
    level=request.getParameter("level");
    
        IdGenerator IG = new IdGenerator();
    
    
   userid=IG.current_id();
    m = MessageDigest.getInstance("MD5");
       m.update(pass.getBytes(), 0, pass.length());
       password = new BigInteger(1, m.digest()).toString(16);
       
    
    String checker="SELECT userid FROM user WHERE (fname=? && mname=? && lname=?) || username=? || userid=?";
    conn.pst=conn.conn.prepareStatement(checker);
    conn.pst.setString(1, fname);
    conn.pst.setString(2, mname);
    conn.pst.setString(3, lname);
    conn.pst.setString(4, username);
    conn.pst.setString(5, userid);
    
    conn.rs=conn.pst.executeQuery();
    if(conn.rs.next()==true){
        
   session.setAttribute("addUser", "<font color=\"blue\">"+fullname+"</font> <font color=\"red\"> was not added.</font>");     
    }
    
    
    else{
//     ADD THE USER
        String inserter="INSERT INTO user(userid,fname,mname,lname,username,password,level) "
                + "VALUES(?,?,?,?,?,?,?)";
        conn.pst=conn.conn.prepareStatement(inserter);
        conn.pst.setString(1, userid);
        conn.pst.setString(2, fname);
        conn.pst.setString(3, mname);
        conn.pst.setString(4, lname);
        conn.pst.setString(5, username);
        conn.pst.setString(6, password);
        conn.pst.setString(7, level);
        
        conn.pst.executeUpdate();
        
        fullname=fname+" "+mname+" "+lname;
     session.setAttribute("addUser", "<font color=\"black\">"+fullname+"</font> <font color=\"green\"> Added Successfully.</font>");
    }
    
    
    response.sendRedirect("addUsers.jsp");
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
        Logger.getLogger(addUsers.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(addUsers.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(addUsers.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(addUsers.class.getName()).log(Level.SEVERE, null, ex);
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
