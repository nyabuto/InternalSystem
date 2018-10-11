/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppprev;

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
 * @author EKaunda
 */
public class loaddata extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
            String loadparticipants="Select hc_formdata_1.formid,group_name,hc_facilitator.first_name,hc_facilitator.middle_name,hc_facilitator.sur_name,DistrictNom,ward.ward,hc_formdata_1.startdate,hc_formdata_1.enddate,hc_formdata_1.lessons,hc_formdata_1.formid,hc_groups.wardid,count(hc_participants.id) as participants,hc_facilitator.phone FROM " +
" hc_formdata_1 " +
" join hc_groups on hc_formdata_1.kundi=hc_groups.group_id " +
" join hc_facilitator on hc_formdata_1.facilitator=hc_facilitator.facilitator_id " +
" join ward on ward.ward_id=hc_groups.wardid " +
" join district on hc_formdata_1.subcounty=district.DistrictID " +
" join county on county.CountyID=district.CountyID"
                    + " join hc_participants on hc_participants.formid=hc_formdata_1.formid group by  hc_formdata_1.formid";
            
           
            
            dbConn conn= new dbConn();
       
            conn.rs=conn.st.executeQuery(loadparticipants);
            String data="<table id='editattendancetable' class='display' style='width:100%' border='1'>"
                    + "<thead>" +
"<tr >" +
"<th>Group Name</th>" +
"<th>Participants</th>" +
"<th>Facilitator</th>" +
"<th>Sub-County</th>" +
"<th>Ward</th>" +
"<th>Start date</th>" +
"<th>End date</th>" +
"<th>Lessons</th>" +
"<th>Edit</th>" +

"</tr>" +
"</thead><tbody>";
            while(conn.rs.next()){
            String facilno="";
            
         if(conn.rs.getString("hc_facilitator.phone")!=null &&!conn.rs.getString("hc_facilitator.phone").equals("") ){
         
         facilno="["+conn.rs.getString("hc_facilitator.phone")+"]";
         }   
            
           data+="<tr id='"+conn.rs.getString("hc_formdata_1.formid")+"'>" +
"<td>"+conn.rs.getString("group_name")+"</td>" +
"<td>"+conn.rs.getString("participants")+"</td>" +
"<td>"+conn.rs.getString("hc_facilitator.first_name")+" "+conn.rs.getString("hc_facilitator.middle_name")+" "+conn.rs.getString("hc_facilitator.sur_name")+facilno+"</td>" +
"<td>"+conn.rs.getString("DistrictNom")+"</td>" +
"<td>"+conn.rs.getString("ward.ward")+"</td>" +
"<td>"+conn.rs.getString("startdate")+"</td>" +
"<td>"+conn.rs.getString("enddate")+"</td>" +
"<td>"+conn.rs.getString("hc_formdata_1.lessons")+"</td>" +
"<td><label data-dismiss='modal'  class='btn btn-success' onclick='editAttendanceForm("+conn.rs.getString("hc_formdata_1.formid")+","+conn.rs.getString("hc_groups.wardid")+");' >Edit</label></td>" +

"</tr>" +
"";     
                
    }
            
        data+="</tbody></table>";    
            
           out.println(data);
           
              if(conn.rs!=null){conn.rs.close();}
              if(conn.st!=null){conn.st.close();}
              if(conn.conn!=null){conn.conn.close();}
              
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
            Logger.getLogger(loaddata.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loaddata.class.getName()).log(Level.SEVERE, null, ex);
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
