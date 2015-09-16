/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccessData;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nyabuto Geofrey
 */
public class processingLevel extends HttpServlet {
HttpSession session;
String progress,currentTable;
String position;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            position="";currentTable="";
//            System.out.println("called>>>>>>>>>>>>>>>");
          session=request.getSession();
          if(session.getAttribute("mergingAccess")!=null){
            progress= session.getAttribute("mergingAccess").toString();
          }
          else{
              progress="";
              session.removeAttribute("mergingAccess");
          }
           if(session.getAttribute("positionAccess")!=null){
            position= session.getAttribute("positionAccess").toString();
          }
           if(session.getAttribute("currentTable")!=null){
               currentTable=session.getAttribute("currentTable").toString();
           }
//          position="1";"+tableName+"currentPosition
          progress=progress.replace(""+currentTable+"currentPosition", "<b>Currently <font color=\"blue\">"+position+" </font> Rows merged");
           
            System.out.println("here called : "+progress);
        out.println(progress);
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
