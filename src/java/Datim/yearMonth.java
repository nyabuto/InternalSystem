/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Datim;

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

/**
 *
 * @author Nyabuto Geofrey
 */
public class yearMonth extends HttpServlet {
int year, month, position;
String yearMonth,monthTXT,id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        dbConn conn = new dbConn();
        
        position=0;
        String getMOH711="SELECT ID,Annee,Mois FROM moh711";
        conn.rs=conn.st.executeQuery(getMOH711);
        while(conn.rs.next()){
            id=conn.rs.getString(1);
            year=conn.rs.getInt(2);
            month=conn.rs.getInt(3);
            
          if(month<10){
              monthTXT="0"+month;
          } 
          else{
              monthTXT=""+month;
          }
          
          yearMonth=year+""+monthTXT;
          
          String update="UPDATE moh711 SET yearmonth='"+yearMonth+"' WHERE id='"+id+"'";
          conn.st1.executeUpdate(update);
          position++;
            System.out.println(" MOH 711 A at position : "+position);
        }
        
        
         position=0;
        String getMOH731="SELECT id,Annee,Mois FROM moh731";
        conn.rs=conn.st.executeQuery(getMOH731);
        while(conn.rs.next()){
            id=conn.rs.getString(1);
            year=conn.rs.getInt(2);
            month=conn.rs.getInt(3);
            
          if(month<10){
              monthTXT="0"+month;
          } 
          else{
              monthTXT=""+month;
          }
          
          yearMonth=year+""+monthTXT;
          
          String update="UPDATE moh731 SET yearmonth='"+yearMonth+"' WHERE id='"+id+"'";
          conn.st1.executeUpdate(update);
          position++;
            System.out.println("MOH 731 at position : "+position);
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
        Logger.getLogger(yearMonth.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(yearMonth.class.getName()).log(Level.SEVERE, null, ex);
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
