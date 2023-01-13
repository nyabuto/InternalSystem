/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMR;

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
 * @author EKaunda
 */
public class saveEmrStatus extends HttpServlet {

    
    HttpSession session=null;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        session=request.getSession();
        
        String msg="EMR status Data Saved succesfully ";
                
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            //list of all data elements to be saved
            String dataelementsarr[]={"id","yearmonth","facility_id","emr","emr_version","emr_status","has_backup_disk","no_of_emr_adt_comps","has_adt","adt_version","has_power_backup","is_tx_curr_emr","tx_curr_paper","tx_curr_emr","site_dropped_ccc_dar","comments","hts_emr","art_emr","anc_emr","eid_emr","tb_emr","emr_accuracy","emr_completeness"};
            /* TODO output your page here. You may use following sample code. */
            
            
            //sample query being constructed
            
            
            dbConn conn = new dbConn();
            String table=" emr_status ";
            
            String mfl="";
            
            if(request.getParameter("facility_id")!=null){
                mfl=request.getParameter("facility_id");
                
            }
            
            //String[] orgunitsarr= {"county","`sub-county`"};
            
            
            String insertqr_parta= "replace into "+table+" (";  // finish with )
            String insertqr_partb= " values ("; // finish with )
            
            for(int a=0;a<dataelementsarr.length;a++)
            {
                
//build an inster qry
                
                if(a==dataelementsarr.length-1){
                    //last row, dont add a comma
                    insertqr_parta+=dataelementsarr[a]+"";
                    insertqr_partb+="?";
                }
                else {
                    insertqr_parta+=dataelementsarr[a]+",";
                    insertqr_partb+="?,";
                }
            }
            
//last section
insertqr_parta+=")";
insertqr_partb+=")";



//append  

String insertqry=insertqr_parta+insertqr_partb;

//System.out.println(""+insertqry);

//conn.st_2.executeUpdate(updateqr);
conn.pst1=conn.conn.prepareStatement(insertqry);   
//facilityname.startdate.enddate.hiv_pos_target_child.hiv_pos_target_adult.hiv_pos_target_total.hiv_pos_child.hiv_pos_adult.hiv_pos_total.new_care_child.new_care_adult.new_care_total.new_art_target_child.new_art_target_adult.new_art_target_total.started_art_child.started_art_adult.started_art_total.viral_load_target_child.viral_load_target_adult.viral_load_target_total.viral_load_done_child.viral_load_done_adult.viral_load_done_total.ipt_target_child.ipt_target_adult.ipt_target_total.ipt_child.ipt_adult.ipt_total.testing_target_child.testing_target_adult.testing_target_total.test_child.test_adult.test_total.pmtct_hiv_pos_target.pmtct_hiv_pos.eid_target.eid_done.viral_load_mothers_target.viral_load_mothers_done.user.hiv_pos_yield_perc_child.hiv_pos_yield_perc_adult.hiv_pos_yield_perc_all.hiv_pos_care_perc_child.hiv_pos_care_perc_adult.hiv_pos_care_perc_all.started_art_perc_child.started_art_perc_adult.started_art_perc_all.viral_test_perc_child.viral_test_perc_adult.viral_test_perc_all.ipt_done_perc_child.ipt_done_perc_adult.ipt_done_perc_all.tested_perc_child.tested_perc_adult.tested_perc_all.hiv_pos_yield_cmts.hiv_pos_care_cmts.started_art_cmts.viral_test_cmts.ipt_done_cmts.tested_cmts.viral_load_mothers_perc.eid_done_perc.pmtct_hiv_pos_perc.viral_load_mothers_cmts.eid_done_cmts.pmtct_hiv_pos_cmts




//______________________________________________________________________________________

int rowcount=1;

            for (String dataelementsarr1 : dataelementsarr) {
                String data="";
                if (request.getParameter("" + dataelementsarr1) != null) {
                    data = request.getParameter("" + dataelementsarr1);
                }
                conn.pst1.setString(rowcount,data);
                rowcount++;
            }

//______________________________________________________________________________________




if(conn.pst1.executeUpdate()==1)
{
     msg="Data Saved succesfully ";
    out.println(msg);    
    session.setAttribute("saveEmrStaus",msg);
}
else {
    msg="Data Not saved Succesfully!";
    out.println(msg);
    
      session.setAttribute("saveEmrStaus",msg);
    
}


if(conn.rs!=null){conn.rs.close();}
if(conn.rs1!=null){conn.rs1.close();}
if(conn.st!=null){conn.st.close();}
if(conn.st1!=null){conn.st1.close();}
if(conn.conn!=null){conn.conn.close();}
        } catch (SQLException ex) {
            Logger.getLogger(saveEmrStatus.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        
    response.sendRedirect("EMR.jsp");
        
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
