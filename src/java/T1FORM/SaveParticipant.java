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
public class SaveParticipant extends HttpServlet {
HttpSession session;
String summary_id,participant_name,gender,profession,personal_no,organization,district,telephone,address;
String output;
int records;
String user_id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
            session=request.getSession();
            output="reached here";
            
          records=0;
          
        if(session.getAttribute("summary_id")!=null){
          summary_id=session.getAttribute("summary_id").toString();
          participant_name = request.getParameter("participant_name");
          gender = request.getParameter("gender");
          profession = request.getParameter("profession");
          personal_no = request.getParameter("personal_no");
          organization = request.getParameter("organization");
          district = request.getParameter("district");
          telephone = request.getParameter("telephone");
          address = request.getParameter("address");
          
          user_id="21";
                  
          String checker="SELECT COUNT(id) FROM t1_details WHERE summary_id=? AND participant_name=? AND (telephone=? OR address=?)";
          conn.pst=conn.conn.prepareStatement(checker);
          conn.pst.setString(1, summary_id);
          conn.pst.setString(2, participant_name);
          conn.pst.setString(3, telephone);
          conn.pst.setString(4, address);
          conn.rs=conn.pst.executeQuery();
          if(conn.rs.next()){
              records=conn.rs.getInt(1);
          }
          
          if(records==0){
              
            String inserter="INSERT INTO t1_details (summary_id,participant_name,gender,profession,personal_no,organization,district,telephone,address,user_id) VALUES(?,?,?,?,?,?,?,?,?,?)";  
            conn.pst=conn.conn.prepareStatement(inserter);
            conn.pst.setString(1, summary_id);
            conn.pst.setString(2, participant_name);
            conn.pst.setString(3, gender);
            conn.pst.setString(4, profession);
            conn.pst.setString(5, personal_no);
            conn.pst.setString(6, organization);
            conn.pst.setString(7, district);
            conn.pst.setString(8, telephone);
            conn.pst.setString(9, address);
            conn.pst.setString(10, user_id);
            
            conn.pst.executeUpdate();
            
            output="Participant added successfully";

//            update summary
            String set ="";
            if(gender.equals("M")){set="d_male=d_male+1";}
            else if(gender.equals("F")){set="d_female=d_female+1";}
            
            String updater="UPDATE t1_summary SET "+set+" WHERE id="+summary_id;
            conn.st.executeUpdate(updater);
          }
            else{
             output="Participant was not added, similar details already exist.";  
          }
        }
        else{
            output="Error, No summary Attached";
        }
        
        
          
          
          
  // Update gender on the summary database        
          
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
        Logger.getLogger(SaveParticipant.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(SaveParticipant.class.getName()).log(Level.SEVERE, null, ex);
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
