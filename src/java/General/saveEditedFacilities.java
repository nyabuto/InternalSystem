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
public class saveEditedFacilities extends HttpServlet {
    HttpSession session;
String facilityID,columnName,data;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
      response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
        try {
        session=request.getSession();
       dbConn conn = new dbConn();
     facilityID=columnName=data="";
     facilityID=request.getParameter("facilityID");
     columnName=request.getParameter("columnName");
     data=request.getParameter("data");
 String updator="UPDATE subpartnera SET "+columnName+"=? WHERE SubPartnerID=?";
 conn.pst=conn.conn.prepareStatement(updator);
conn.pst.setString(1, data);
conn.pst.setString(2, facilityID);

 conn.pst.executeUpdate();
    
   IdGenerator IG = new IdGenerator();
   
   String createdOn = IG.toDay();

  
  String qry="insert into facility_history (SubPartnerID,service,status,startdate) values ('"+facilityID+"','"+columnName+"','"+data+"','"+createdOn+"')";
 
  conn.st.executeUpdate(qry);
  
  
     if(conn.pst!=null){conn.pst.close();}
     if(conn.conn!=null){conn.conn.close();}
 
  System.out.println("updated : facility id : "+facilityID+" columnname : "+columnName+"  value : "+data);
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
            Logger.getLogger(saveEditedFacilities.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(saveEditedFacilities.class.getName()).log(Level.SEVERE, null, ex);
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
