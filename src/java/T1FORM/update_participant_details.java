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
import org.json.simple.parser.ParseException;

/**
 *
 * @author GNyabuto
 */
public class update_participant_details extends HttpServlet {
HttpSession session;
String id,participant_name,gender,profession,personal_no,organization,district,telephone,address;
String gender_session,gender_text,summary_id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            System.out.println("called the updater ");
           session = request.getSession();
           dbConn conn = new dbConn();
           id=gender_session=summary_id="";
           gender_session=gender_text="";
           if(session.getAttribute("detailed_id")!=null){
               id = session.getAttribute("detailed_id").toString();
           }
           if(session.getAttribute("gender")!=null){
               gender_session = session.getAttribute("gender").toString();
           }
           
           if(session.getAttribute("summary_id")!=null){
               summary_id = session.getAttribute("summary_id").toString();
           }
           
           
           participant_name = request.getParameter("participant_name");
           gender = request.getParameter("gender");
           profession = request.getParameter("profession");
           personal_no = request.getParameter("personal_no");
           organization = request.getParameter("organization");
           district = request.getParameter("district");
           telephone = request.getParameter("telephone");
           address = request.getParameter("address");
            System.out.println("gender session:"+gender_session+" gender passed : "+gender);
           if(gender.equals(gender_session)){
            gender_text="" ;      
           }
           else if(gender.equals("M")){
              gender_text="d_male=d_male+1,d_female=d_female-1" ;    
           }
           else if(gender.equals("F")){
             gender_text="d_male=d_male-1,d_female=d_female+1" ;    
           }
           
          String updatorParticipant = "UPDATE t1_details SET participant_name=?,gender=?,profession=?,personal_no=?,organization=?,district=?,telephone=?,address=? where id=?" ;
          conn.pst = conn.conn.prepareStatement(updatorParticipant);
          conn.pst.setString(1, participant_name);
          conn.pst.setString(2, gender);
          conn.pst.setString(3, profession);
          conn.pst.setString(4, personal_no);
          conn.pst.setString(5, organization);
          conn.pst.setString(6, district);
          conn.pst.setString(7, telephone);
          conn.pst.setString(8, address);
          conn.pst.setString(9, id);
          
          conn.pst.executeUpdate();
          //update summary
          if(!gender_text.equals("")){
          String updateSummary="UPDATE t1_summary SET "+gender_text+" WHERE id='"+summary_id+"'";
          conn.st.executeUpdate(updateSummary);
              System.out.println(updateSummary);
          }
           String message = "<font color=\"green\">Participant details updated successfully.</font>";
           
            out.println(message);
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
    } catch (ParseException ex) {
        Logger.getLogger(update_participant_details.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(update_participant_details.class.getName()).log(Level.SEVERE, null, ex);
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
    } catch (ParseException ex) {
        Logger.getLogger(update_participant_details.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(update_participant_details.class.getName()).log(Level.SEVERE, null, ex);
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
