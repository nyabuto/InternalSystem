/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
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
public class save_cl_data extends HttpServlet {
HttpSession session;

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String columns[] = {"recounted_register_emr","emr","moh731","khis","form1a","gaps","action_taken","timeline","responsible","status"};
String query="",query_insert,query_update;
int num_indicators,columns_counter;
String year = null,month = null,facil = null,yearmonth,tableid;
String table_name="fas_art",indic_id="",value;
String user_id = null,fullname,health_facility,indicator_name,error,message = null;
int counted_values=0,missing_info,num_added=0,code = 0;
boolean has_prev_data;
        
        
        try {
           session = request.getSession();
           dbConn conn = new dbConn();
           
           error="Form data NOT saved. Correct the following:\n";
           missing_info=0;
           has_prev_data=false;
           num_indicators = Integer.parseInt(request.getParameter("num_indicators"));
           table_name = request.getParameter("table_name");
                       System.out.println("entered here: nm indics"+num_indicators+" table name : "+table_name);
//           num_indicators = 5;
          
            if (session.getAttribute("year") != null) {
                year = session.getAttribute("year").toString();
            }
            else{
                missing_info++; 
                error+=missing_info+". Missing Year \n";
            }
            if (session.getAttribute("monthid") != null) {
                month = session.getAttribute("monthid").toString();
            }
            else{
                missing_info++; 
                error+=missing_info+". Missing Month \n";
            }
            if (session.getAttribute("facilityid") != null) {
                facil = session.getAttribute("facilityid").toString();
            }
            else{
                missing_info++; 
                error+=missing_info+". Missing Health Facility \n";
            }
                if(session.getAttribute("userid")!=null){        
                user_id=session.getAttribute("userid").toString();
                }
            else{
                missing_info++; 
                error+=missing_info+". Missing User Information \n";
            }

//          year="2018";
//          month="10";
//          facil = "383"; 
          
    if(missing_info==0){
        num_added=0;
        String tempmonth=month;
        int pepfaryear=Integer.parseInt(year);
        if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
        else {pepfaryear--;}
        yearmonth=pepfaryear+""+tempmonth;
        
        
        for(int i=1;i<=num_indicators;i++){
            indic_id = request.getParameter("indic_pos_"+i);
            tableid = yearmonth+"_"+facil+"_"+indic_id; 
            
            counted_values=columns_counter=0;
     
            
        //query = "REPLACE INTO "+table_name+" SET id='"+tableid+"',facility_id='"+facil+"',indicator_id='"+indic_id+"',yearmonth='"+yearmonth+"',user_id='"+user_id+"',user_pc='"+getComputerName()+"',";    
 
        query_insert = "INSERT INTO "+table_name+" SET id='"+tableid+"',facility_id='"+facil+"',indicator_id='"+indic_id+"',yearmonth='"+yearmonth+"',";    
        query_update = "UPDATE  "+table_name+" SET "; 
        query="";
        
        for(String column_name:columns){
            columns_counter++;
            value = request.getParameter(column_name+"_"+indic_id);
             
            if(value!=null){
            if(!value.equals("") && !(counted_values==0 && columns_counter==columns.length && value.equals("0"))){
             counted_values++;
             query+=" "+column_name+"='"+value+"',";  
           }
            else{
           query+=" "+column_name+"=NULL,";       
            }
            }
        }

                fullname=health_facility=indicator_name="";
                String getusername = "SELECT fname,lname FROM user WHERE userid='"+user_id+"'";
                 conn.rs1=conn.st1.executeQuery(getusername);
                 if(conn.rs1.next()){
                  fullname = conn.rs1.getString(1)+" "+conn.rs1.getString(2);
                 }
                 
                 String getfacil = "SELECT SubPartnerNom FROM subpartnera WHERE SubPartnerID='"+facil+"'";
                  conn.rs1=conn.st1.executeQuery(getfacil);
                 if(conn.rs1.next()){
                  health_facility = conn.rs1.getString(1);
                 }
                 
                 String getIndicator = "SELECT main_indicator,indicator FROM fas_indicators WHERE id='"+indic_id+"'";
                  conn.rs1=conn.st1.executeQuery(getIndicator);
                 if(conn.rs1.next()){
                  indicator_name = conn.rs1.getString(1)+", "+conn.rs1.getString(2);
                 } 
                 
             
             // check if data already exist
             String checker = "SELECT id FROM "+table_name+" WHERE id='"+tableid+"'";
             conn.rs1 = conn.st1.executeQuery(checker);
             if(conn.rs1.next()){ // data exist
             // update the audit trails table with relevant information
              has_prev_data=true;
             String insert_audit_trails = "INSERT INTO fas_audit_trails (entry_id,table_name,fullname,facility,indicator,yearmonth,user_pc) VALUES(?,?,?,?,?,?,?)";
             conn.pst = conn.conn.prepareStatement(insert_audit_trails);
             conn.pst.setString(1, tableid);
             conn.pst.setString(2, table_name);
             conn.pst.setString(3, fullname);
             conn.pst.setString(4, health_facility);
             conn.pst.setString(5, indicator_name);
             conn.pst.setString(6, yearmonth);
             conn.pst.setString(7, getComputerName());
             
             conn.pst.executeUpdate();
             
             query = removeLast(query, 1);
             query_update+=""+query+" WHERE id='"+tableid+"' ";
             if(counted_values>0){
             System.out.println("Query UPDATE = "+query_update);
             num_added++;
            conn.st.executeUpdate(query_update);
            message="Data Updated Successfully";
            }
             }
             else{// new data entry
                 has_prev_data=false;
                 //add entries to the main table data. append it there to be appended include user id, user pc
                 query +="user_id='"+user_id+"',user_pc='"+getComputerName()+"'";
                 query_insert +=""+query;
                 
        if(counted_values>0){
             System.out.println("Query INSERT = "+query_insert);
             num_added++;
            conn.st.executeUpdate(query_insert);
            message="Data Saved Successfully";
        }
        } 
        
        if(counted_values==0 && has_prev_data){
            //delete the record from the database and record the actions
            
            String deleter = "DELETE FROM "+table_name+" WHERE id='"+tableid+"'";
            conn.st.executeUpdate(deleter);
            
             String insert_audit_trails = "INSERT INTO fas_audit_trails (entry_id,table_name,fullname,facility,indicator,yearmonth,user_pc) VALUES(?,?,?,?,?,?,?)";
             conn.pst = conn.conn.prepareStatement(insert_audit_trails);
             conn.pst.setString(1, tableid);
             conn.pst.setString(2, table_name);
             conn.pst.setString(3, fullname);
             conn.pst.setString(4, health_facility);
             conn.pst.setString(5, "Deleted Data: "+indicator_name);
             conn.pst.setString(6, yearmonth);
             conn.pst.setString(7, getComputerName());
             
             conn.pst.executeUpdate();
             
        code=1;
        message="Record data deleted successfully.";  
        }
        else if(num_added==0){
         code=0;
        message="Nothing to be saved.";     
        }
        }
        
        if(num_added>0){
        code=1;
        }
            }
            else{
        // one or more information passed in session is missing
                 code=0;
                 error+="\nADVICE:Log out of the system and login again";
                  message=error;
            }
           
            JSONObject obj = new JSONObject();
            obj.put("code", code);
            obj.put("message", message);
            
            out.println(obj);
            
//        response.sendRedirect("form1a.jsp");
//            out.println("</html>");
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
        Logger.getLogger(save_cl_data.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(save_cl_data.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
   private static String removeLast(String str, int num) {
    return str.substring(0, str.length() - num);
}
   
 private String getComputerName(){
    Map<String, String> env = System.getenv();
    if (env.containsKey("COMPUTERNAME"))
        return env.get("COMPUTERNAME");
    else if (env.containsKey("HOSTNAME"))
        return env.get("HOSTNAME");
    else
        return "Unknown Computer";
}
}
