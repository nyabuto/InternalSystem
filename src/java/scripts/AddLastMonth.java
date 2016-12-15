/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

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

/**
 *
 * @author EKaunda
 */
public class AddLastMonth extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           addfirstmonth();
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
            Logger.getLogger(AddQuarter.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddQuarter.class.getName()).log(Level.SEVERE, null, ex);
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

    public void addfirstmonth() throws SQLException{
    
    
            dbConn conn= new dbConn();
            
           String[] forms ={"eid_datim","viral_load","pmtct_fo","tb_stat_art"}; 
           String[] formid ={"id","id","id","id"}; 
           
           for(int a=0;a<forms.length;a++){
           
                try {
                    String select=" select quarter,"+formid[a]+" from "+forms[a]+" where (firstmonth is null || firstmonth='' )";
                    conn.rs=conn.st.executeQuery(select);
                    while(conn.rs.next()){
                        
                        int quarter=conn.rs.getInt(1);
                        String id=conn.rs.getString(2);
                        String firstmonth="";
                        if(quarter==1){firstmonth="10";}
                        if(quarter==2){firstmonth="1";}
                        if(quarter==3){firstmonth="4";}
                        if(quarter==4){firstmonth="7";}
                        
                        
                        System.out.println("Month :"+firstmonth+"  Quarter :"+quarter);
                        String updatecode=" update "+forms[a]+" set firstmonth='"+firstmonth+"' where "+formid[a]+"='"+id+"' ";
                        System.out.println(" update "+forms[a]+" set firstmonth='"+firstmonth+"' where "+formid[a]+"='"+id+"' ");
                        conn.st1.executeUpdate(updatecode);
                    }    
                
             
                } catch (SQLException ex) {
                    Logger.getLogger(AddLastMonth.class.getName()).log(Level.SEVERE, null, ex);
                }
           
           }
           
            
              if(conn.rs!=null){conn.rs.close();}
                if(conn.st!=null){conn.st.close();}
                if(conn.st1!=null){conn.st1.close();}
        
    
    }
    
}
