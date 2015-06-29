/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportsAjax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Elkant
 */
public class loadQuarter extends HttpServlet {

    HttpSession session=null;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session=request.getSession();
        
        response.setContentType("text/html;charset=UTF-8");
        
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String passedyear = "" + year;

        if (request.getParameter("year") != null && !request.getParameter("year").equals("")) {

            passedyear = request.getParameter("year");

        }
        //get the previous year

        int prevyear = 0;

        if (!passedyear.equals("")) {

            prevyear = Integer.parseInt(passedyear) - 1;

        }
       
        String quarters[]={"Select Period","1. Oct-Dec "+prevyear+"","2. Jan-Mar "+year+"","3. Apr-Jun "+year+"","4. Jul-Sept "+year+""};
        String quartersmonths[]={"","10,11,12","1,2,3","4,5,6","7,8,9"};
        
        String sannual[]={"Select Period","1. Oct "+prevyear+" - Mar "+year+" ","2. Apr "+year+" - Sep "+year};
        String sannualmonths[]={"","10,11,12,1,2,3","4,5,6,7,8,9"};
        
      String opts=""; 
      String opts2="";
        for(int a=0;a<quarters.length;a++){        
        opts+="<option value='"+quartersmonths[a]+"'>"+quarters[a]+"</option>";
        }
        
        for(int a=0;a<sannual.length;a++){        
        opts2+="<option value='"+sannualmonths[a]+"'>"+sannual[a]+"</option>";
        }
        
     
        
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */

            out.println(opts+"#"+opts2);
            
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
