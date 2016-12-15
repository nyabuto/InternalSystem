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
public class AddQuarter extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
         addQuarter();
           
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

    
    public void addQuarter() throws SQLException{
    
      
            dbConn conn= new dbConn();
            
           String[] forms ={"moh711","moh731","moh711_new","kmmp","gender","tb","vmmc","nutrition"}; 
           String[] formid ={"ID","id","id","tableid","tableid","ID","tableid","tableid"}; 
           
           for(int a=0;a<forms.length;a++){
           
                try {
                    String select=" select Mois,"+formid[a]+" from "+forms[a]+" where (quarter is null || quarter='' )";
                    conn.rs=conn.st.executeQuery(select);
                    while(conn.rs.next()){
                        
                        int month=conn.rs.getInt(1);
                        String id=conn.rs.getString(2);
                        String quarter="";
                        if(month>=10 && month <=12){quarter="1";}
                        if(month>=1 && month <=3){quarter="2";}
                        if(month>=4 && month <=6){quarter="3";}
                        if(month>=7 && month <=9){quarter="4";}
                        
                        System.out.println("Month :"+month+"  Quarter :"+quarter);
                        String updatecode=" update "+forms[a]+" set quarter='"+quarter+"' where "+formid[a]+"='"+id+"' ";
                        System.out.println("update "+forms[a]+" set quarter='"+quarter+"' where "+formid[a]+"='"+id+"' ");
                        conn.st1.executeUpdate(updatecode);
                        
                        
                    }    } catch (SQLException ex) {
                    Logger.getLogger(AddQuarter.class.getName()).log(Level.SEVERE, null, ex);
                }
           
           }
           
                if(conn.rs!=null){conn.rs.close();}
                if(conn.st!=null){conn.st.close();}
                if(conn.st1!=null){conn.st1.close();}
    
    }
    
}
