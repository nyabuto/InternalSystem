/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VL;

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
 * @author Administrator
 */
public class updateNewCalhivValues extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           
            dbConn conn = new dbConn();            
            
            String Msg="";
                
            String updatepart="";
            //Update CCC mnumber
             if(request.getParameter("weight")!=null)
            {
          
              String system_id=request.getParameter("sid");
              String wt=request.getParameter("weight"); 
              
              updatepart+=", Last_Visit_Weight='"+wt+"'"; 
              String qry=" update calhiv_cleaning set status='Updated' "+updatepart+" where Patient_Id='"+system_id+"' ;";
                System.out.println("Queeeery niii___"+qry);
                
                if(!wt.equals("0")){
            conn.st.executeUpdate(qry);
            Msg="<b>Weight Value "+wt+" updated successfully!!</b>";
                }
                else {
                 Msg="<b>Weight Value "+wt+" NOT updated !!</b>";
                }
            
            }
           
       // update Regimen line
                    if(request.getParameter("regimenline")!=null)
            {
          
              String system_id=request.getParameter("sid");
              String wt=request.getParameter("regimenline"); 
              
              updatepart+=", Current_Regimen_Line='"+wt+"'"; 
              String qry=" update calhiv_cleaning set status='Updated' "+updatepart+" where Patient_Id='"+system_id+"'; ";
                System.out.println(""+qry);
            conn.st.executeUpdate(qry);
            Msg="<b>Regimenline Value "+wt+" updated successfully!!</b>";
            
            }
     //Regimen
     
                if(request.getParameter("regimen")!=null)
            {
          
              String system_id=request.getParameter("sid");
              String wt=request.getParameter("regimen"); 
              
              updatepart+=", Current_Regimen='"+wt+"'"; 
              String qry=" update calhiv_cleaning set status='Updated' "+updatepart+" where Patient_Id='"+system_id+"'; ";
                System.out.println(""+qry);
            conn.st.executeUpdate(qry);
            Msg=" <b> Regimen Value "+wt+" updated successfully!!</b>";
            
            }
                     
             out.print(Msg);
             
             
             
         if(conn.rs!=null){conn.rs.close();}  
         if(conn.st!=null){conn.st.close();}  
        
         if(conn.pst1!=null){conn.pst1.close();}  
         if(conn.conn!=null){conn.conn.close();}  
             
            
            
        } catch (SQLException ex) {
            Logger.getLogger(updateNewRecords.class.getName()).log(Level.SEVERE, null, ex);
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
