/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppprev;

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
public class saveServices extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            dbConn conn= new dbConn();
          
            
            String cols[]={"participantid","clientphone","pregnant","maritalstatus","ref_from","ref_to","hivresults","htc_ref","htc_ref_date","vmmc_ref","vmmc_ref_date","anc_pmtct_ref","anc_pmtct_ref_date","sti_ref","sti_ref_date","tb_ref","tb_ref_date","familyplanning_ref","familyplanning_ref_date","pep_ref","pep_ref_date","care_ref","care_ref_date","psychosocial_ref","psychosocial_ref_date","drugabuse_ref","drugabuse_ref_date","counselling_ref","counselling_ref_date","bcc_ref","bcc_ref_date","other_ref","other_specific_ref","other_ref_date","htc_rec","htc_rec_date","vmmc_rec","vmmc_rec_date","anc_pmtct_rec","anc_pmtct_rec_date","sti_rec","sti_rec_date","tb_rec","tb_rec_date","familyplanning_rec","familyplanning_rec_date","pep_rec","pep_rec_date","care_rec","care_rec_date","psychosocial_rec","psychosocial_rec_date","drugabuse_rec","drugabuse_rec_date","counselling_rec","counselling_rec_date","bcc_rec","bcc_rec_date","other_rec","other_specific_rec","other_rec_date","ref_by","rec_by","reason_no_service_rec","other_reason_not_rec"};
           
            
                        
String formdatainsert=" replace into hc_services ";
String formdatacolumns="(";
String formdatavalues=") values (";
            
      for (int p = 0; p < cols.length; p++) 
            {
                
                formdatacolumns += cols[p] + ",";
                formdatavalues += "?,";
            }            
formdatainsert+=formdatacolumns+formdatavalues+") ";

formdatainsert=formdatainsert.replace("?,)", "?)");
formdatainsert=formdatainsert.replace(",)", ")");

System.out.println(""+formdatainsert); 

conn.pst1=conn.conn.prepareStatement(formdatainsert);  

   int count=1;
            for (int p = 0; p < cols.length; p++) {
                
                String vals = "";
                String valsa[] = null;
                if(cols[p].equals("reason_no_service_rec")){
                if (request.getParameterValues(cols[p]) != null) 
                {
                valsa = request.getParameterValues(cols[p]);
                conn.pst1.setString(p+1,ArraytoString(valsa));
                //count++;
                }
                else{
                      vals = "";
                conn.pst1.setString(p+1,vals);
                    System.out.println("null request.getParameter("+cols[p]+")");
                }
            }
                else {
                    if (request.getParameter(cols[p]) != null) 
                {
                vals = request.getParameter(cols[p]);
                conn.pst1.setString(p+1,vals);
                //count++;
                }
                else{
                      vals = "";
                conn.pst1.setString(p+1,vals);
                    System.out.println("null request.getParameter("+cols[p]+")");
                }
                
                
                }
                                                    }
            
            System.out.println(""+conn.pst1.toString());
            conn.pst1.executeUpdate();   

            /* TODO output your page here. You may use following sample code. */
         String returnnmsg="";

          

returnnmsg="<font color=\'green\'><b>Data saved successfully! </b></font>";




out.println(returnnmsg);

   if(conn.rs!=null){conn.rs.close();}
   if(conn.rs1!=null){conn.rs1.close();}
   if(conn.st!=null){conn.st.close();}
   if(conn.st1!=null){conn.st1.close();}
   if(conn.pst1!=null){conn.pst1.close();}
      if(conn.conn!=null){conn.conn.close();}
            
           
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
            Logger.getLogger(saveServices.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(saveServices.class.getName()).log(Level.SEVERE, null, ex);
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


public String ArraytoString(String [] array){
String al=",";

        for (String array1 : array) {
            al += array1 + ",";
                                     }

return al;
}

}
