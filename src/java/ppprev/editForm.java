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
import org.json.simple.JSONObject;

/**
 *
 * @author EKaunda
 */
public class editForm extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
          
            dbConn conn = new dbConn();
   
            String where="";
            
            if(request.getParameter("formid")!=null){
                String fid=request.getParameter("formid");
            where=" where formid='"+fid+"' ";
            
            }
            
            String wardid="";
            if(request.getParameter("ward")!=null){
            
            wardid=request.getParameter("ward");
            
            }
            
        String qry="SELECT * FROM internal_system.hc_formdata_1 "+where;    
            
        //String 
           String variablesform[]={"id","formid","partner","subcounty","targetpop","curriculum","group","lessons","facilitator","cofacilitator","startdate","enddate","agegroup"};   
         String variables[]={"id","formid","partner","subcounty","targetpop","curriculum","kundi","lessons","facilitator","cofacilitator","startdate","enddate","agegroup"};       
            JSONObject obj= new JSONObject();
        obj.put("ward", wardid);
          conn.rs=conn.st.executeQuery(qry);
          while(conn.rs.next()){
          
            for(int a=0;a<variables.length;a++){
                
                obj.put(variablesform[a], conn.rs.getString(variables[a]));
            
            }  
              
          
          
          }
          
          
            out.println(obj);
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
            Logger.getLogger(editForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editForm.class.getName()).log(Level.SEVERE, null, ex);
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
