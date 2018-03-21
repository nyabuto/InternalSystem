/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMapping;

import General.IdGenerator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author GNyabuto
 */
public class IMIS_DHIS_Mapping extends HttpServlet {
HttpSession session;
String yearmonth;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
     DHIS_IMIS imis_dhis_mapping = new DHIS_IMIS();

      XSSFWorkbook wb =  new XSSFWorkbook();
      yearmonth = "201801";
      wb = imis_dhis_mapping.get_data(yearmonth, wb);
        
  
      
  IdGenerator IG = new IdGenerator();
  String createdOn = IG.CreatedOn();

  ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
  wb.write(outByteStream);
  byte[] outArray = outByteStream.toByteArray();
  response.setContentType("application/ms-excel");
  response.setContentLength(outArray.length);
  response.setHeader("Expires:", "0"); // eliminates browser caching
  response.setHeader("Content-Disposition", "attachment; filename=IMIS_DHIS_Comparison_" + createdOn + ".xlsx");
  OutputStream outStream = response.getOutputStream();
  outStream.write(outArray);
  outStream.flush();
  outStream.close();  
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
        Logger.getLogger(IMIS_DHIS_Mapping.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(IMIS_DHIS_Mapping.class.getName()).log(Level.SEVERE, null, ex);
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
