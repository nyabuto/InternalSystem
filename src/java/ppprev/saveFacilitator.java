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
public class saveFacilitator extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            dbConn conn = new dbConn();
            
           
            
            String fname=request.getParameter("fname").trim();
            String mname=request.getParameter("mname").trim();
            String sname=request.getParameter("sname").trim();
            String phone=request.getParameter("phone").trim();
            String subcounty=request.getParameter("subcounty");
            String group=request.getParameter("group");
            
            String responsetxt="";
//group_id,group_name,partner_id,target_pop_id,district_id,wardid
            
            getParticipants gp= new getParticipants();
            
           // String checklist="replace into () values () ";
            String id=""+gp.RandomNo(10000, 800000);
            
            
            String checker="select * from hc_facilitator where first_name like ? and middle_name like ? and sur_name like ? and phone like ? and groups_id like ?";
            conn.pst2=conn.conn.prepareStatement(checker);    
                          
                        conn.pst2.setString(1,fname);
                        conn.pst2.setString(2,mname);
                        conn.pst2.setString(3,sname);
                        conn.pst2.setString(4,phone);
                        conn.pst2.setString(5,group);
            
                        conn.rs=conn.pst2.executeQuery();
                        
                        if(!conn.rs.next()){
            
            
             String replace="replace into hc_facilitator(facilitator_id,first_name,middle_name,sur_name,phone,partner_id,groups_id)  values (?,?,?,?,?,?,?)";
                      conn.pst1=conn.conn.prepareStatement(replace);    
                          
                        conn.pst1.setString(1,id);
                        conn.pst1.setString(2,fname);
                        conn.pst1.setString(3,mname);
                        conn.pst1.setString(4,sname);
                        conn.pst1.setString(5,phone);
                        conn.pst1.setString(6,"0");                       
                        conn.pst1.setString(7,group);                       
                      
                        if(conn.pst1.executeUpdate()==1){
                            
                        responsetxt="<font color='green'>Facilitator "+fname+" "+mname+" "+sname+" saved successfully</font>";
                        
                        }
                        else {
                       responsetxt="<font color='red'>Facilitator "+fname+" "+mname+" "+sname+" Already added</font>";
                        }
            
            //-------------------------------------------------
                        }
                        else {
                        responsetxt="<font color='red'>Facilitator "+fname+" "+mname+" "+sname+" Already added</font>";
                        
                        }
            
            
            
             out.println(responsetxt);
             
             if(conn.rs!=null){conn.rs.close();}
            if(conn.st!=null){conn.st.close();}
            if(conn.pst1!=null){conn.pst1.close();}
            if(conn.pst2!=null){conn.pst2.close();}
             if(conn.conn!=null){conn.conn.close();  System.out.println(" connection closed ");} 
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
