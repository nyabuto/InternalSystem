/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppprev;

import GapAnalysis.subcounty;
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
public class saveGroup extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            dbConn conn = new dbConn();
            
            //"subcounty="+subcounty+"&ward="+ward+"&partner="+partnername+"&population="+targetpopname+"&group"+groupnam
            
            
            String subcounty=request.getParameter("subcounty");
            String ward=request.getParameter("ward");
            String partner=request.getParameter("partner");
            String population=request.getParameter("population");
            String group=request.getParameter("group");
            
            String responsetxt="<font color='green'>Group "+group+" added successfully</font>";
//group_id,group_name,partner_id,target_pop_id,district_id,wardid
            
            
           // String checklist="replace into () values () ";
            String id=""+subcounty+"_"+ward+"_"+partner+"_"+population+"_"+group.trim();
             String replace="replace into hc_groups(group_id,group_name,partner_id,target_pop_id,district_id,wardid)  values (?,?,?,?,?,?)";
                      conn.pst1=conn.conn.prepareStatement(replace);    
                          
                        conn.pst1.setString(1,id);
                        conn.pst1.setString(2,group);
                        conn.pst1.setString(3,partner);
                        conn.pst1.setString(4,population);
                        conn.pst1.setString(5,subcounty);
                        conn.pst1.setString(6,ward);                       
                      
                        if(conn.pst1.executeUpdate()==1){
                        
                        
                        }
                        else {
                        responsetxt="<font color='red'>Group not added successfully</font>";
                        }
            
            //-------------------------------------------------
            
            
            
            
             out.println(responsetxt);
             
            if(conn.rs!=null){conn.rs.close();}
            if(conn.st!=null){conn.st.close();}
            if(conn.pst1!=null){conn.pst1.close();}
            if(conn.pst2!=null){conn.pst2.close();}
             if(conn.conn!=null){conn.conn.close(); // System.out.println(" connection closed ");
             } 
        } finally {
            out.close();
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(saveGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(saveGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
