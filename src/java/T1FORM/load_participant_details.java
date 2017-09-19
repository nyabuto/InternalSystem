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
import org.json.simple.JSONObject;

/**
 *
 * @author GNyabuto
 */
public class load_participant_details extends HttpServlet {
HttpSession session;
String output="";
String id,participant_name,gender_selected,profession,personal_no,organization,district,telephone,address;
String summary_id,district_id,district_ids,gender;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            JSONObject jobj = new JSONObject();
            session=request.getSession();
            dbConn conn = new dbConn();
            
//          id="1";
          id=request.getParameter("id");
          
          
          String get_details="SELECT t1_details.id as id,summary_id,district,participant_name,gender,profession,personal_no,organization,telephone,address,districts FROM t1_details JOIN t1_summary ON t1_details.summary_id=t1_summary.id WHERE t1_details.id=? ";
          conn.pst=conn.conn.prepareStatement(get_details);
          conn.pst.setString(1, id);
          conn.rs=conn.pst.executeQuery();
          while(conn.rs.next()){
          id = conn.rs.getString("id");
          summary_id = conn.rs.getString("summary_id");
          district_id = conn.rs.getString("district");
          district_ids = conn.rs.getString("districts");
          participant_name = conn.rs.getString("participant_name");
          gender_selected = conn.rs.getString("gender");
          profession = conn.rs.getString("profession");
          personal_no = conn.rs.getString("personal_no");
          organization = conn.rs.getString("organization");
          telephone = conn.rs.getString("telephone");
          address = conn.rs.getString("address");
          district =gender ="";
          
          String getDistricts="SELECT DistrictID AS id,DistrictNom AS name FROM district WHERE active=1 ORDER BY name";
          conn.rs1=conn.st1.executeQuery(getDistricts);
          while(conn.rs1.next()){
            if(district_ids.contains("*"+conn.rs1.getString(1)+"*")){
              if (district.equals(conn.rs1.getString(1))){
                    district+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(2)+"</option>";    
              }
              else{
                  
               district+="<option value=\""+conn.rs1.getString(1)+"\">"+conn.rs1.getString(2)+"</option>";     
              }     
                
            }
              
          }
          
          // gender
          String getGender="SELECT name from t1_gender";
         conn.rs=conn.st.executeQuery(getGender);
         while(conn.rs.next()){
             
             if(gender_selected.equals(conn.rs.getString(1))){
                 gender+="<option value=\""+conn.rs.getString(1)+"\" selected>"+conn.rs.getString(1)+"</option>";
             }
             else{
                 gender+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(1)+"</option>";
             }
         }
          jobj.clear();
          jobj.put("id", id);
          jobj.put("participant_name", participant_name);
          jobj.put("gender", gender);
          jobj.put("profession", profession);
          jobj.put("personal_no", personal_no);
          jobj.put("organization", organization);
          jobj.put("telephone", telephone);
          jobj.put("address", address);
          jobj.put("district", district);
          
          
          session.setAttribute("detailed_id", id);
          session.setAttribute("summary_id", summary_id);
          session.setAttribute("gender", gender_selected);
          }
          
            System.out.println("the object data is : "+jobj);
            out.println(jobj);
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
        Logger.getLogger(load_participant_details.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(load_participant_details.class.getName()).log(Level.SEVERE, null, ex);
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
