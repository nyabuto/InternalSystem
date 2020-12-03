/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form1a;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author EKaunda
 */
public class lockf1a extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           
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


 public  XSSFWorkbook lockexcel(Sheet s, XSSFWorkbook workbookx){
    String password= "f1";
    byte[] pwdBytes = null;
    try {
        pwdBytes  = Hex.decodeHex(password.toCharArray());
    } catch (DecoderException e) 
    {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
    XSSFSheet sheet = ((XSSFSheet)s);
    //removePivot(s,workbookx);
    sheet.lockDeleteColumns();
    sheet.lockDeleteRows();
    sheet.lockFormatCells();
    sheet.lockFormatColumns();
    sheet.lockFormatRows();
    sheet.lockInsertColumns();
    sheet.lockInsertRows();
    sheet.lockSelectLockedCells();
//    sheet.protectSheet("f1av4");
    sheet.getCTWorksheet().getSheetProtection().setPassword(pwdBytes);
    for(byte pwdChar :pwdBytes)
    {
        System.out.println(">>> Sheet protected with '" + pwdChar + "'");
    }
    sheet.enableLocking();

    workbookx.lockStructure();

    return workbookx;
}


}
