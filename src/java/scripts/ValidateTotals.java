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
public class ValidateTotals extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
            
            dbConn conn= new dbConn();
            
           //do a comparison of queries and place each query in an arraylist then excecute them for comparison 
           
            //get a list of elements where we are ensuring consistency
            
            String getdata="select section,subsets,mainelement from validatetotals where active='1'";
            conn.rs=conn.st.executeQuery(getdata);
            
            while(conn.rs.next()){
            
                //start monitoing in july 2017
                String qry="select "+conn.rs.getString(2)+" as subset,IFNULL("+conn.rs.getString(3)+",0) as mainelement ,'"+conn.rs.getString(1)+"' as elementname , id from moh731 where ("+conn.rs.getString(3)+") !=("+conn.rs.getString(2)+") and yearmonth>=201707 ";
                
                conn.rs1=conn.st1.executeQuery(qry);
                while(conn.rs1.next()){
                int subset=conn.rs1.getInt("subset");
                int mainelement=conn.rs1.getInt("mainelement");
                String id=conn.rs1.getString("id");
                //if the main total is less than the subsets total
                if(mainelement  < subset)
                {
                    
                    //update
                    String update=" update moh731 set  "+conn.rs.getString(3)+"='"+subset+"' where id='"+id+"' ";
                conn.st2.executeUpdate(update); 
                    System.out.println( update+" "+conn.rs.getString(1));
                }
                //if the main total is greater than the subsets total
                else {
                    
                System.out.println(id+" Total is more than subsets . "+mainelement+"> "+subset+"  "+conn.rs.getString(1)+" ");
                
                 //update
                    String update=" update moh731 set  "+conn.rs.getString(3)+"='"+subset+"' where id='"+id+"' ";
                conn.st2.executeUpdate(update); 
                    System.out.println( update+" "+conn.rs.getString(1));
                
                }
                }
            }
            
             if(conn.st!=null){conn.st.close();}  
         if(conn.rs!=null){conn.rs.close();}  
         if(conn.rs1!=null){conn.rs1.close();}  
         if(conn.st1!=null){conn.st1.close();}  
      
         if(conn.st2!=null){conn.st2.close();}  
       
            
            //out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(ValidateTotals.class.getName()).log(Level.SEVERE, null, ex);
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
