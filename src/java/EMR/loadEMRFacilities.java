/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMR;

import General.IdGenerator;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author EKaunda
 */
public class loadEMRFacilities extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
              dbConn conn = new dbConn();
            
            IdGenerator ig = new IdGenerator();
            
            
            String yrm=""+ig.CurrentYearMonth();
            
            if(request.getParameter("yrm")!=null)
            {
                
                String ym=request.getParameter("yrm");
                
                if(!ym.equals(""))
                {
                 yrm = request.getParameter("yrm");
                }                
             
              //print a list of facilities
              out.println(getEmrSites(conn));
                  System.out.println("output sites");        
            }
            
            
         
            
            
          
        } catch (SQLException ex) {
            Logger.getLogger(loadEMRFacilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    
    public JSONObject getEmrSites(dbConn conn) throws SQLException{
    
    JSONArray ja = new JSONArray();
    
  
    
    
    String qry=" call Emr_getEmrSites(); ";
    
    conn.rs=conn.st.executeQuery(qry);
    
    while(conn.rs.next())
        
    {
    JSONObject jo = new JSONObject();
    
    jo.put("county",conn.rs.getString("county"));
    jo.put("subcounty",conn.rs.getString("subcounty"));
    jo.put("facility_id",conn.rs.getString("facility_id"));    
    jo.put("facility_name",conn.rs.getString("facility_name"));    
    jo.put("mflcode",conn.rs.getString("mflcode"));
    
     ja.add(jo);
     
    }
           
    JSONObject final_object = new JSONObject();
    
        final_object.put("facilities", ja);
            
    
    return final_object;     
        
        
    
    }
    
    
}
