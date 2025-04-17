/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loaders;

import General.IdGenerator;
import General.IdGenerator2;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Emmanuel Kaunda
 */
public class loadyearmonth extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            dbConn conn = new dbConn();
            
            
            out.println(getYearMonths(conn));
          
            if(conn.rs!=null){conn.rs.close();}
            if(conn.st!=null){conn.st.close();}
            
        } catch (SQLException ex) {
            Logger.getLogger(loadyearmonth.class.getName()).log(Level.SEVERE, null, ex);
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


 public JSONObject getYearMonths(dbConn conn) throws SQLException{
    
        JSONArray armain = new JSONArray();
        JSONObject jomain = new JSONObject();
        
        
             IdGenerator2 ig= new IdGenerator2();
        
        
        String qry="select * from internal_system.htsself_period where concat(year,'-',monthid,'-',enddate) <='"+ig.LastMonthEndDate()+"' order by id desc limit 14";
        
             System.out.println(qry);
        
        conn.rs= conn.st.executeQuery(qry);
        
        while(conn.rs.next()){
            
          JSONObject jo = new JSONObject();
            jo.put("id", conn.rs.getString("id"));
            jo.put("year", conn.rs.getString("year"));
            jo.put("month", conn.rs.getString("month"));
            jo.put("monthid", conn.rs.getString("monthid"));
            jo.put("enddate", conn.rs.getString("enddate"));
          
        armain.put(jo);
        }
        
    jomain.put("periods",armain);
        
    return jomain;
    
    }
    

}
