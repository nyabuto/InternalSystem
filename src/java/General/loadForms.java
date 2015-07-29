/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

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
 * @author Elkant
 */
public class loadForms extends HttpServlet {

   HttpSession session=null;
   String user_access;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session=request.getSession();
        try {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    dbConn conn= new dbConn();
   String forms="<option value=''> Select Form</option>"; 
    String getForms="select * from forms";
   
    conn.rs=conn.st.executeQuery(getForms);
 String form="";
 if(session.getAttribute("form")!=null){
 form =session.getAttribute("form").toString();
 }
 System.out.println("form session "+form);
    
    while(conn.rs.next()){
      if(conn.rs.getString("form").equals("MOH 711A")){user_access="moh711";}  
      if(conn.rs.getString("form").equals("MOH 731")){user_access="moh731";}   
      if(conn.rs.getString("form").equals("Gender")){user_access="gender";}  
      if(conn.rs.getString("form").equals("VMMC")){user_access="vmmc";}  
      if(conn.rs.getString("form").equals("Nutrition")){user_access="nutrition";}  
      if(conn.rs.getString("form").equals("KMMP")){user_access="kmmp";}  
      if(conn.rs.getString("form").equals("TB")){user_access="tb";}  
      if(conn.rs.getString("form").equals("HEI")){user_access="hei";}  
      
        
        
        
        if(form.equals(conn.rs.getString("nextpage"))){
       if(session.getAttribute("forms_holder")!=null){ if(session.getAttribute("forms_holder").toString().contains(conn.rs.getString("form"))){     
      if(session.getAttribute("userAccess")!=null){ if(session.getAttribute("userAccess").toString().contains(","+user_access+",")) {  
        forms+="<option selected value='"+conn.rs.getString("nextpage") +"'>"+conn.rs.getString("form")+"</option>";   
     }
     else{
     forms+="<option selected value='"+conn.rs.getString("nextpage") +"' disabled>"+conn.rs.getString("form")+"  [ Inaccessible form ]</option>";   
        }  
      }
         }
        
         }
    }
       
        else{
            
     if(session.getAttribute("forms_holder")!=null){ if(session.getAttribute("forms_holder").toString().contains(conn.rs.getString("form"))){     
    if(session.getAttribute("userAccess")!=null){ if(session.getAttribute("userAccess").toString().contains(","+user_access+",")) {         
    
         forms+="<option value='"+conn.rs.getString("nextpage") +"'>"+conn.rs.getString("form")+"</option>";   
     }
    else{
     forms+="<option value='"+conn.rs.getString("nextpage") +"' disabled>"+conn.rs.getString("form")+"  [ Inaccessible form ]</option>";   
    }
    }
    }
    
    }
                         }
    }
    try {
        
        if(forms.equalsIgnoreCase("<option value=''> Select Form</option>")){
        forms="<option value=''>No Form available for this site</option>"; 
        
        }
        
        out.println(forms);
    } finally {    
           if(conn.conn!=null){ conn.conn.close();}
               if(conn.rs!=null){ conn.rs.close();}
               if(conn.st!=null){ conn.st.close();}
        out.close();
        
    }
}       catch (SQLException ex) {
            Logger.getLogger(loadForms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
