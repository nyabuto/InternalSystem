/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form1a;

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
public class loadValidation extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
         
            out.println("</html>");
            
            
            JSONArray arr= new JSONArray();
            
            
            String sectionid="";
            
            if(request.getParameter("sectionid")!=null)
            {
            sectionid=request.getParameter("sectionid");
            }
            
            String validation="";
            
            dbConn conn = new dbConn();
            
            String getList="select * from fas_validation where sectionid='"+sectionid+"'";
            
            
            conn.rs=conn.st.executeQuery(getList);
            
            while(conn.rs.next()){
                JSONObject obj=new JSONObject();
                
            //(valids, message, iscritical, sectionid)
            
            String iscritical="";
            if(conn.rs.getString("iscritical").equals("1")){
            iscritical="yes";
            }
            else {
            iscritical="no";
            }
            
            obj.put("validation", conn.rs.getString("iscritical"));
            obj.put("message", conn.rs.getString("message"));
            obj.put("iscritical", conn.rs.getString("iscritical"));
            obj.put("sectionid", sectionid);
           arr.add(obj);
            
            
            }
            
          
            if(conn.rs!=null){conn.rs.close();}
            if(conn.st!=null){conn.st.close();}
            
            out.println(arr);
            
        } catch (SQLException ex) {
            Logger.getLogger(loadValidation.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
