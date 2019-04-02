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
import java.util.Random;
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
public class loadParticipantsforServices extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        try 
        {
            /* TODO output your page here. You may use following sample code. */
            
            String data="";
            
             
            String groupid="";
            String agegroup="";
            String defaultsex="";
            
            if(request.getParameter("groupid")!=null){
            
            groupid=request.getParameter("groupid");
            
            }
            
            if(request.getParameter("agegroup")!=null){
            
            agegroup=request.getParameter("agegroup");
            if(agegroup.equalsIgnoreCase("Young Women") || agegroup.equalsIgnoreCase("Older Women")){
            defaultsex="Female";
                System.out.println(" DEFAULT SEX "+defaultsex);
            }
            
            }
            
            dbConn conn = new dbConn();
            
            
            
            data = "<table id='participants2table' class= 'table  table-bordered'>"
                    + "<thead><tr>"
                    + "<th>Serial No</th>"
                    + "<th>Unique ID</th>"
                    + "<th>Participant Name</th>"
                    + "<th>Age</th>"
                    + "<th>Sex</th>"
                    + "<th>Group</th>"
                    + "<th>End Date</th>"
                    + "<th>Form Id</th>"
                    + "<th>Facilitator</th>"
                    + "<th>Ward</th>"
                     + "<th>Services given?</th>"
                    + "<th>Services</th>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>";
   
        String rows=""; 
            
            
            
            
            String qry=" SELECT  " +
"    hc_participants.id , ifnull(hc_participants.patient_unique_id,'') as patient_unique_id,   Concat(fname,\" \",mname,\" \",sname) as participant,    age,    sex,    group_name,    hc_participants.enddate,    hc_participants.formid,Concat(first_name,\" \",middle_name,\" \",sur_name) as Facilitator,  ward " +
",case when hc_services.participantid !='' then 'Yes' else 'No' end as status  " +
" " +
"FROM " +
" " +
"    internal_system.hc_participants " +
"     " +
"    left join (hc_groups ) on hc_participants.group_id=hc_groups.group_id " +
"    join ward on ward.ward_id=hc_participants.wardid " +
"     " +
"    left join (hc_formdata_1 left join hc_facilitator on hc_formdata_1.facilitator=hc_facilitator.facilitator_id) on hc_formdata_1.formid=hc_participants.formid " +
"    left join hc_services on hc_services.participantid=hc_participants.id " +
"     " +
"    group by hc_participants.id ";
           
            
            conn.rs=conn.st.executeQuery(qry);
            int count=1;
            
           // int x=0;
            
            while(conn.rs.next())
            {
               String clas="btn btn-success"; 
               if(conn.rs.getString("status").equals("No")){ clas="btn btn-danger"; }
             
                 rows+="<tr> "
                + "<td class= 'col-sm-1 '>"+count+"<input value='"+conn.rs.getString("id")+"' type='hidden' id='participantid"+count+"' name= 'participantid"+count+"'  /></td>"
                + "<td class= 'col-sm-1 '>"+conn.rs.getString("patient_unique_id")+"</td>"
                + "<td class= 'col-sm-2 '>"+conn.rs.getString("participant")+"</td>"
                + "<td class= 'col-sm-1 '>"+conn.rs.getString("age")+"</td>"
                + "<td class= 'col-sm-1 '>"+conn.rs.getString("sex")+"</td>"
                + "<td class= 'col-sm-1 '>"+conn.rs.getString("group_name")+"</td>"
                + "<td class= 'col-sm-1 '>"+conn.rs.getString("enddate")+"</td>"
                + "<td class= 'col-sm-1 '>"+conn.rs.getString("formid")+"</td>"
                + "<td class= 'col-sm-1 '>"+conn.rs.getString("Facilitator")+"</td>"
                + "<td class= 'col-sm-1 '>"+conn.rs.getString("ward")+"</td>"
                + "<td class= 'col-sm-1 '><span class='"+clas+"' id='status_"+conn.rs.getString("id")+"'>"+conn.rs.getString("status")+"</span></td>"
                         
               +"<td class= 'col-sm-1 '><label class='btn btn-info' onclick=\"launchservices('"+conn.rs.getString("sex")+"','"+conn.rs.getString("id")+"','"+conn.rs.getString("participant").replace("'","")+"','"+conn.rs.getString("age")+"','"+conn.rs.getString("ward").replace("'","")+"');\">Services</label></td>"
                         + "</tr>";
                
                
           count++;
            
            }
            
            System.out.println(" qry is "+qry);
                     
            //read from the db and get the participants for editing
              
         
                
    rows+="</tbody><tfoot>"
        +"<tr>"
           
                    + "<th>Serial No</th>"
            + "<th>Unique ID</th>"
                    + "<th>Participant Name</th>"
                    
                    + "<th>Age</th>"
                    + "<th>Sex</th>"
                    + "<th>Group</th>"
                    + "<th>End Date</th>"
                    + "<th>Form Id</th>"
                    + "<th>Facilitator</th>"
                    + "<th>Ward</th>"
                     + "<th>Services given?</th>"
                    + "<th>Services</th>"
                    
            + "</tr></tfoot></table>";
            
            //System.out.println(data+rows);
    
             out.println(data+rows);
         if(conn.rs!=null){conn.rs.close(); }   
         if(conn.st!=null){conn.st.close();  }   
         if(conn.conn!=null){conn.conn.close();  }  
            
           
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
            Logger.getLogger(getParticipants.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(getParticipants.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>






   


}
