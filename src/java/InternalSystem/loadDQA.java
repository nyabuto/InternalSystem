/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalSystem;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maureen
 */
public class loadDQA extends HttpServlet {
HttpSession session;
int counter;
String county,facilityName,form,districtName;
int elementsCounter;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        session= request.getSession();
        PrintWriter out = response.getWriter();
       String year="";
        String month="";
        String allcolumns []=null;
        String allerrors []=null;
        String data="";
           dbConn conn = new dbConn();
           county=facilityName=form=districtName="";
           elementsCounter=0;
        try {
//         System.out.println("entered here for dqa");
//               if(session.getAttribute("year")!=null){        
//   year=session.getAttribute("year").toString();
//    }
//      if(session.getAttribute("monthid")!=null){        
//   month=session.getAttribute("monthid").toString();
//    }
            year=request.getParameter("year");
            month=request.getParameter("month");

  System.out.println("to process data for dqa");
            data+="<thead><th>No.</th><th>COUNTY</th><th>SUB COUNTY</th><th>HEALTH FACILITY</th><th>FORM </th><th>COLUMN</th><th>ERROR</th> </thead><tbody>";
            
            String getdata="SELECT county.County,subpartnera.SubPartnerNom,dqa.columns,dqa.errors,dqa.form,district.DistrictNom "
        + "FROM dqa JOIN subpartnera ON subpartnera.SubPartnerID=dqa.facilityid "
        + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
        + "JOIN county ON county.CountyID=district.CountyID "
        + "WHERE year='"+year+"' && month='"+month+"' ORDER BY dqa.form";
           System.out.println(getdata);
            conn.rs= conn.st.executeQuery(getdata);
            while(conn.rs.next()){
                county=conn.rs.getString(1);
                facilityName=conn.rs.getString(2);
                
          allcolumns=conn.rs.getString(3).split("@");
          allerrors=conn.rs.getString(4).split("@");
          form=conn.rs.getString(5);
          districtName=conn.rs.getString(6);
          System.out.println(conn.rs.getString(1));
         counter=0;
          for(String column:allcolumns){
        if(!allcolumns[counter].equals("") && !allerrors[counter].equals("") ){
            elementsCounter++;
        data+="<tr>"
         + "<td>"+elementsCounter+"</td><td>"+county+"</td><td>"+districtName+"</td><td>"+facilityName+"</td><td>"+form+"</td><td>"+allcolumns[counter]+"</td><td>"+allerrors[counter] +"</td>"
         + "</tr>";     
          }
         counter++; 
          }
            }
          data+="</tbody>";

            out.println(data);     
          System.out.println(   data  );  
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
            Logger.getLogger(loadDQA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
            Logger.getLogger(loadDQA.class.getName()).log(Level.SEVERE, null, ex);
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
