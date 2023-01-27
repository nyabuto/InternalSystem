/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VL;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Emmanuel E
 */
public class load_unclean_calhiv extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
        
            
         dbConn conn= new dbConn();
         
         
         
             String sps[]={"internal_system.sp_vl_get_calhiv_notupdated"};
             
         String dbnames[]={"calhiv_cleaning"};
         
            
        
         
         
         String mflcode="15288";
         
         if(request.getParameter("mflcode_sync")!=null) 
         
         {
         mflcode=request.getParameter("mflcode_sync");
         }

//sp_get_nonemr_vl
//sp_get_nonemr_ipt
//sp_get_nonemr_curr
//sp_get_nonemr_all
//sp_get_nonemr_diffcare
         
            
         JSONArray masterarr=new JSONArray();
         JSONArray tablesarr=new JSONArray();
        
          
         
         
         for(int x=0;x<sps.length;x++){
         
         String current_storedprocedure=sps[x];    
         
         String getcns=" call "+current_storedprocedure+"('"+mflcode+"');";
         
         
         conn.rs=conn.st.executeQuery(getcns);
            
             System.out.println(""+getcns);
         ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();

         metaData = conn.rs.getMetaData();
         columnCount = metaData.getColumnCount();
         
        int count = 1;
        int count1 = 1;
        
        ArrayList mycolumns = new ArrayList();
 JSONArray jarr=new JSONArray();
        while (conn.rs.next()) {

            if (count == (count1)) 
            {
                for (int i = 1; i <= columnCount; i++) 
                {
                    mycolumns.add(metaData.getColumnLabel(i));                                       

                }//end of for loop
                count++;
            }//end of if
            
            JSONObject jobj= new JSONObject();
            for (int i = 1; i <= columnCount; i++) 
                {
               mycolumns.add(metaData.getColumnLabel(i));                                       
               jobj.put(metaData.getColumnLabel(i),conn.rs.getString(i));
     
               }//end of for loop
         
         
            jarr.put(jobj);
            
      
         }
        
          JSONObject tablesobject= new JSONObject();    
             
         
         tablesobject.put(dbnames[x], jarr);
         
         tablesarr.put(tablesobject);
         
         
         
        }/// end of tables loop
         
         masterarr.put(tablesarr);
            
              if(conn.rs!=null){conn.rs.close();}
              if(conn.st!=null){conn.st.close();}
              if(conn.conn!=null){conn.conn.close();}
            
            
          // System.out.println(""+jarr); 
          PrintWriter out = response.getWriter();  
          out.println(tablesarr);
         
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
            Logger.getLogger(load_unclean_data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(load_unclean_data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
