/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T1FORM;

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
 * @author GNyabuto
 */
public class LoadDetailed extends HttpServlet {
HttpSession session;
String output;
String summary_id;
String id,participant_name,gender,profession,personal_no,organization,district,telephone,address;
int counter;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
            session=request.getSession();
          output="<thead><th>No.</th><th>Name</th><th>Sex</th><th>Job title/Profession</th><th>Personal Number</th><th>Name of Organization/ Facility</th><th>Sub-county</th><th>Telephone Number</th><th>Address (Email or Postal)</th><th>Edit</th> </thead><tbody>";
          
          id=participant_name=gender=profession=personal_no=organization=district=telephone=address="";  
          if(session.getAttribute("summary_id")!=null){
          summary_id=session.getAttribute("summary_id").toString();
              
          counter=0;
          String getDetails="SELECT t1_details.id AS id,participant_name,gender,profession,personal_no,organization,district.DistrictNom AS district_name, telephone,address "
                             + "FROM   t1_details JOIN district ON t1_details.district=district.DistrictID WHERE summary_id=? ORDER BY timestamp DESC";
          conn.pst=conn.conn.prepareStatement(getDetails);
          conn.pst.setString(1, summary_id);
          conn.rs=conn.pst.executeQuery();
          while(conn.rs.next()){
                    id = conn.rs.getString(1);
                    participant_name = conn.rs.getString(2);
                    gender = conn.rs.getString(3);
                    profession = conn.rs.getString(4);
                    personal_no = conn.rs.getString(5);
                    organization = conn.rs.getString(6);
                    district = conn.rs.getString(7);
                    telephone = conn.rs.getString(8);
                    address = conn.rs.getString(9);
                   
                    counter++;
//                  write data here;  
           output+="<tr>"
         + "<td class=\"detailed-elems\">"+counter+"</td><td class=\"detailed-elems\">"+participant_name+"</td><td class=\"detailed-elems\">"+gender+"</td><td class=\"detailed-elems\">"+profession+"</td><td class=\"detailed-elems\">"+personal_no+"</td><td class=\"detailed-elems\">"+organization+"</td><td class=\"detailed-elems\">"+district+"</td><td class=\"detailed-elems\">"+telephone+"</td><td class=\"detailed-elems\">"+address+"</td><td><button type=\"button\" class=\"btn-info\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#editor\" onclick=\"editor("+id+")\"> Edit </button></td>"
         + "</tr>";          
                    
          }
          
        }
          output+="</tbody>";
            out.println(output);
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
        Logger.getLogger(LoadDetailed.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(LoadDetailed.class.getName()).log(Level.SEVERE, null, ex);
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
