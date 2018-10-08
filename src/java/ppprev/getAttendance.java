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
public class getAttendance extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        try {
            /* TODO output your page here. You may use following sample code. */
            
            String regdata="";
            
             
            String groupid="";
            String lessons="7";
            
            if(request.getParameter("groupid")!=null){
            
            groupid=request.getParameter("groupid");
            
            }
            
            if(request.getParameter("lessons")!=null){
            
                if(!request.getParameter("lessons").equals("")){
            lessons=request.getParameter("lessons");
                     }
            }
            
            int c=new Integer(lessons);
            dbConn conn = new dbConn();
            
            
            
              regdata="<table id='attendancetable' class= 'table table-bordered table-responsive'>"
   
         +"<thead><tr><th>#</th><th>Participant</th>";
                      for(int b=1;b<=c;b++){
                     regdata+= "<th>S"+b+"</th>";
                              }
                      regdata+= "<th></th></tr></thead>"
         + "<tbody>";
   
        String regrows=""; 
            
            
            
            
            String qry="select * from hc_participants where group_id='"+groupid+"' ";
           
            
            conn.rs=conn.st.executeQuery(qry);
            int count=1;
            
           // int x=0;
            
            while(conn.rs.next()){
             
                 regrows+="<tr> "
                + "<td class= 'col-sm-1' >"+count+"</td>"
                + "<td class= 'col-sm-2' >"+conn.rs.getString("fname")+" "+conn.rs.getString("mname")+" "+conn.rs.getString("sname")+"</td>";
                  for(int d=1;d<=c;d++)
                              {  
                          //presentAbsent 
                                  
                                 String icon="icon-check";
                                 if(conn.rs.getString("s"+count).equals("0")){icon="icon-remove-sign";}
                                 
                     regrows+= "<td class= 'col-sm-1'><select onchange='attendanceicon(\""+count+"_"+d+"\");' style='width:100px;'  id='s"+count+"_"+d+"' name='s"+count+"_"+d+"' class= 'form-control' >"+presentAbsent(""+conn.rs.getString("s"+count))+"</select><i class='"+icon+"' id='status"+count+"_"+d+"'></i></td>";
                              } 
                  
                regrows+="<td class= 'col-sm-1' ><a class= 'deleteRow'></a></td></tr>";
                
                
           count++;
           
            }
            
             System.out.println(" qry is "+qry);
                     
                //read from the db and get the participants for editing
              
               
                int a=count;
         regrows+="<tr> "
                + "<td class='col-sm-1' >"+count+"</td>"
                + "<td class= 'col-sm-2'><div id='participant"+count+"'></div></td>";
                  for(int d=1;d<=c;d++)
                              {  
                          //presentAbsent 
                     regrows+= "<td '><select onchange='attendanceicon(\""+count+"_"+d+"\");' style='width:100px;'  id='s"+count+"_"+d+"' name='s"+count+"_"+d+"' class='form-control' >"+presentAbsent("1")+" </select> <i class='icon-check' id='status"+count+"_"+d+"'></i></td>";
                              } 
                  
                regrows+="<td class= 'col-sm-1' ></a></td></tr>";
        count++;
                   
            
                
    regrows+="</tfoot></table>";
            
            System.out.println(regdata+regrows);
    
             out.println(regdata+regrows);
         if(conn.rs!=null){conn.rs.close(); System.out.println(" resultset closed ");}   
         if(conn.st!=null){conn.st.close();  System.out.println(" resultset closed ");}   
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


public String presentAbsent(String curstatus){

 
    
    String allattendance[]={"Present","Absent"};
    String allattendanceid[]={"1","0"};
    
   // String options="<option value=''>Attendance</option>";
    String options="";
    for(int b=0;b<allattendance.length;b++){
        String isselected="";
        //System.out.println(" Compare statuses "+curstatus+"  "+allattendanceid[b]);
        if(curstatus.equals(allattendanceid[b])){isselected=" selected ";}
        
    options+="<option "+isselected+" value='"+allattendanceid[b]+"'>"+allattendance[b]+"</option>";
    
    
    }
    

return options;
}




   


}
