/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VL;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Administrator
 */
public class getSitesWithUncleanCalhiv extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           
            dbConn conn = new dbConn();
            
            try {
                
                
              
                
                 out.println(GetSitesInJson(GetSites(conn)));
                
            } catch (SQLException ex) {
                Logger.getLogger(getSitesWithNonStandardCccNos.class.getName()).log(Level.SEVERE, null, ex);
            }
           
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
            Logger.getLogger(getSitesWithNonStandardCccNos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(getSitesWithNonStandardCccNos.class.getName()).log(Level.SEVERE, null, ex);
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

    
    
    public String GetSitesCount(dbConn conn) throws SQLException{
     String sites="0";
    
        String qr="select count(distinct(Facility)) from calhiv_cleaning ";
     conn.rs=conn.st.executeQuery(qr);
     
     while(conn.rs.next()){
     sites=conn.rs.getString(1);
     }
    
     return sites;
    }
    
    public ResultSet GetSites(dbConn conn) throws SQLException
    {
     
    
     String qr="select Facility,mflcode,`Sub-county`,County from calhiv_cleaning group by mflcode";
     return conn.st.executeQuery(qr);   
   
     
    }
    
    
    
      public JSONObject GetSitesInJson( ResultSet sites) throws SQLException{
     
          JSONArray masterarr=new JSONArray();
         JSONArray tablesarr=new JSONArray();
          
     ResultSetMetaData metaData = sites.getMetaData();
        int columnCount = metaData.getColumnCount();

         metaData = sites.getMetaData();
         columnCount = metaData.getColumnCount();
         
        int count = 1;
        int count1 = 1;
        
        ArrayList mycolumns = new ArrayList();
 JSONArray jarr=new JSONArray();
        while (sites.next()) {

            if (count == (count1)) 
            {
                for (int i = 1; i <= columnCount; i++) 
                {
                    mycolumns.add(metaData.getColumnLabel(i));                                       

                }//end of for loop
                count++;
            }//end of if
            
            org.json.JSONObject jobj= new org.json.JSONObject();
            for (int i = 1; i <= columnCount; i++) 
                {
               mycolumns.add(metaData.getColumnLabel(i));                                       
               jobj.put(metaData.getColumnLabel(i),sites.getString(i));
     
               }//end of for loop         
         
            jarr.put(jobj);            
      
         }
        
        JSONObject tablesobject= new JSONObject();  
        
        tablesobject.put("facilities", jarr);
               
         
   
        return tablesobject;
     
    }
    
    
}
