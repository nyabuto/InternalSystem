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
public class petermwarogosrequest extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            dbConn conn= new dbConn();
            
            if(1==1){
            String getfacils="select * from peterrequest where mflcode is null and subpartnerID is not null";
            
            
            conn.rs=conn.st.executeQuery(getfacils);
            
            while(conn.rs.next()){
            
            String getdetails="select  CentreSanteId , owner, Type from subpartnera2014 where SP_ID='"+conn.rs.getString("subpartnerID")+"'";
            
            conn.rs1=conn.st1.executeQuery(getdetails);
            if(conn.rs1.next()){
            String update="update peterrequest set mflcode='"+conn.rs1.getString("CentreSanteId")+"',owner='"+conn.rs1.getString("Type")+"' where  subpartnerID='"+conn.rs.getString("subpartnerID")+"'";
            System.out.println(""+update);
            
            conn.st2.executeUpdate(update);
            
            
            }
                
            
            
            }
            
            }
            
            
            if(2==2){
                
            String getfacils="select * from peterrequest where Owner is null and mflcode is not null ";
            
            
            conn.rs=conn.st.executeQuery(getfacils);
            
            while(conn.rs.next()){
            
            String getdetails="select  CentreSanteId , owner, Type from subpartnera2015 where CentreSanteId='"+conn.rs.getString("mflcode")+"'";
            
            conn.rs1=conn.st1.executeQuery(getdetails);
            if(conn.rs1.next()){
                
            String update="update peterrequest set owner='"+conn.rs1.getString("Type")+"' where  mflcode='"+conn.rs.getString("mflcode")+"'";
            System.out.println(""+update);
            
            conn.st2.executeUpdate(update);
            
            
            }
                
            
            
            }
            
            }
            
            
             if(3==3){
                
            String getfacils="select * from peterrequest where Owner is null ";
            
            
            conn.rs=conn.st.executeQuery(getfacils);
            
            while(conn.rs.next()){
            
            String getdetails="select  CentreSanteId , owner, Type from subpartnera where SubPartnerNom like ?";
            
            
             conn.pst=conn.conn.prepareStatement(getdetails);
            conn.pst.setString(1, conn.rs.getString("facility")+"%");
                        
            //conn.pst.executeUpdate();
            //
            conn.rs1= conn.pst.executeQuery();
            if(conn.rs1.next()){
                
             String update="update peterrequest set mflcode='"+conn.rs1.getString("CentreSanteId")+"',owner='"+conn.rs1.getString("Type")+"' where  facility=?";
             conn.pst1=conn.conn.prepareStatement(update);
            conn.pst1.setString(1, conn.rs.getString("facility"));
                        
            conn.pst1.executeUpdate();
             
             //'"+conn.rs.getString("SubPartnerNom")+"'
             System.out.println(""+update);
            
           // conn.st2.executeUpdate(update);
            
            
            }
                
            
            
            }
            
            }
            
             
             
             
             
             //
               if(4==4){
                
            String getfacils="select * from peterrequest where Owner is null ";
            
            
            conn.rs=conn.st.executeQuery(getfacils);
            
            while(conn.rs.next()){
            
            String getdetails="select  mflcode , owner from peterrequest where facility like ? and owner is not null";
            
            
             conn.pst=conn.conn.prepareStatement(getdetails);
            conn.pst.setString(1, conn.rs.getString("facility")+"%");
                        
            //conn.pst.executeUpdate();
            //
            conn.rs1= conn.pst.executeQuery();
            if(conn.rs1.next()){
                
             String update="update peterrequest set mflcode='"+conn.rs1.getString("mflcode")+"',owner='"+conn.rs1.getString("owner")+"' where  facility=?";
             conn.pst1=conn.conn.prepareStatement(update);
            conn.pst1.setString(1, conn.rs.getString("facility"));
                        
            conn.pst1.executeUpdate();
             
             //'"+conn.rs.getString("SubPartnerNom")+"'
             System.out.println(""+update);
            
           // conn.st2.executeUpdate(update);
            
            
            }
                
            
            
            }
            
            }
             
            
            /* TODO output your page here. You may use following sample code. */
           
            out.println("<h1>Servlet petermwarogosrequest at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            Logger.getLogger(petermwarogosrequest.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(petermwarogosrequest.class.getName()).log(Level.SEVERE, null, ex);
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
