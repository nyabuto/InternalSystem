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
public class getParticipants extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        try {
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
               // System.out.println(" DEFAULT SEX "+defaultsex);
            }
            
            }
            
            dbConn conn = new dbConn();
            
            
            
              data="<table id='participantstable' class= 'table order-list table-bordered'>"
   
         +"<thead><tr><th>Serial No</th><th>First Name</th><th>Middle Name</th><th>Last Name</th><th>Patient Unique ID</th><th>Age</th><th>Sex</th><th></th></tr></thead>"
         + "<tbody>";
   
        String rows=""; 
            
            
            
            
            String qry="select id,fname,mname,sname,age,sex,IFNULL(patient_unique_id,'') AS patient_unique_id from hc_participants where group_id='"+groupid+"' order by timestamp asc ";
           
            
            conn.rs=conn.st.executeQuery(qry);
            int count=1;
            
           // int x=0;
            
            while(conn.rs.next()){
             
                 rows+="<tr> "
                + "<td class= 'col-sm-1 '>"+count+"<input value='"+conn.rs.getString("id")+"' type='hidden' id='id"+count+"' name= 'id"+count+"'  /></td>"
                + "<td class= 'col-sm-2 '><input style='text-transform: lowercase;' onblur='appendnames(\""+count+"\");' value='"+conn.rs.getString("fname")+"' type= 'text' id='firstname"+count+"' name= 'firstname"+count+"' class= 'form-control' /></td>"
                + "<td class= 'col-sm-1 '><input style='text-transform: lowercase;' onblur='appendnames(\""+count+"\");' value='"+conn.rs.getString("mname")+"' placeholder='optional' type= 'text' id='middlename"+count+"' name='middlename"+count+"' class= 'form-control' /></td>"
                + "<td class= 'col-sm-2 '><input style='text-transform: lowercase;' onblur='appendnames(\""+count+"\");' value='"+conn.rs.getString("sname")+"' type= 'text' id='lastname"+count+"' name='lastname"+count+"' class= 'form-control' /></td>"
                + "<td class= 'col-sm-2 '><input style='text-transform: lowercase;' onblur='isunique(\"patient_unique_id"+count+"\",\""+conn.rs.getString("id")+"\");' value='"+conn.rs.getString("patient_unique_id")+"' type= 'text' id='patient_unique_id"+count+"' name='patient_unique_id"+count+"' class= 'form-control' /></td>"
                + "<td class= 'col-sm-1 ' style='width:170px;'><input style='width:150px;' onkeypress='return numbers(event);' maxlength='2' value='"+conn.rs.getString("age")+"' type= 'text' id='age"+count+"' name='age"+count+"' class= 'form-control' /></td>"
                + "<td class= 'col-sm-2 ' style='width:170px;'><select type= 'text' id='sex"+count+"' name='sex"+count+"' class= 'form-control' style='width:150px;' >"+getGender(""+conn.rs.getString("sex"))+"</select></td>"
                +"<td class= 'col-sm-1 '><a  tabindex='-1' class= 'deleteRow '></a></td></tr>";
                
                
           count++;
           
            }
            
            // System.out.println(" qry is "+qry);
                     
                //read from the db and get the participants for editing
              
               
                int a=count;
                int rn=RandomNo(1000, 90000);
            rows+="<tr> "
                + "<td class= 'col-sm-1 ' >"+a+"<input value='"+rn+"' type='hidden' id='id"+a+"' name= 'id"+a+"'  /></td>"
                + "<td class= 'col-sm-2 ' ><input autofocus='autofocus' onblur='appendnames(\""+a+"\");' type= 'text' id='firstname"+a+"' name= 'firstname"+a+"' class= 'form-control' /></td>"
                + "<td class= 'col-sm-1 ' ><input onblur='appendnames(\""+a+"\");' placeholder='optional' type= 'text' id='middlename"+a+"' name='middlename"+a+"' class= 'form-control' /></td>"
                + "<td class= 'col-sm-2 ' ><input onblur='appendnames(\""+a+"\");' type= 'text' id='lastname"+a+"' name='lastname"+a+"' class= 'form-control' /></td>"
                + "<td class= 'col-sm-2 ' ><input style='text-transform: lowercase;' onblur='isunique(\"patient_unique_id"+a+"\",\""+rn+"\");'  type= 'text' id='patient_unique_id"+a+"' name='patient_unique_id"+a+"' class= 'form-control' /></td>"
                + "<td class= 'col-sm-1 ' style='width:170px;'><input style='width:150px;' onkeypress='return numbers(event);' maxlength='2' type= 'text' id='age"+a+"' name='age"+a+"' class= 'form-control' /></td>"
                + "<td class= 'col-sm-2 ' style='width:170px;'><select style='width:150px;' id='sex"+a+"' name='sex"+a+"' class= 'form-control' >"+getGender("")+"</select></td>"
                 +"<td class= 'col-sm-1 ' ><a  tabindex='-1' class= 'deleteRow'></a></td></tr>";
        count++;
                   
            
                
    rows+="</tbody><tfoot><tr><td  style= 'text-align: left;' colspan='7'><input type='button' onclick='addrow();' class='btn btn-lg btn-block btn-success' id='ongezarow' value='Add Participant row' /></td></tr>"
        +"<tr><td ><input type='hidden' value='"+count+"' id='totalrows' name='totalrows'></td></tr></tfoot></table>";
            
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


public String getGender(String cursex){

 
    
    String allsex[]={"Female","Male"};
    String options="<option value=''>Select Sex</option>";
    for(int b=0;b<allsex.length;b++){
        String isselected="";
        
        if(cursex.equals(allsex[b])){isselected=" selected ";}
        
    options+="<option "+isselected+" value='"+allsex[b]+"'>"+allsex[b]+"</option>";
    
    
    }
    

return options;
}




    public int RandomNo(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }


}
