/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoadExcels;

import database.dbConn;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emmanuel E
 */
public class syncviralload extends HttpServlet {
//This servlet imports data from the viral_load_raw table, analyses it and inputs it  into the  viral_load table
   
    
   String county_name,county_id, district_name,district_id,hf_name,hf_id;
  
   int checker_dist,checker_hf;
   
  HttpSession session;
  
  String nextpage="";
  String quarterName,facilityName,facilityID,id,missingFacility,mflcode;
  int year,quarter,checker,missing,added,updated;

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            session=request.getSession();
            dbConn conn = new dbConn();
             int newcount=0;
             int updatecount=0;
            nextpage="sync_viral_load.jsp";
            
//---------------------------------------------------------------------
            
            String numerator_un ,denominator_un;
            String fun_less1,fun_1to4,fun_5to14,fun_15to19,fun_20;
            String mun_less1,mun_1to4,mun_5to14,mun_15to19,mun_20;
            String subtotal_un,numerator_vi,denominator_vi;
            String fvi_less1,fvi_1to4 ,fvi_5to14,fvi_15to19,fvi_20;
            String mvi_less1,mvi_1to4,mvi_5to14,mvi_15to19,mvi_20 ,subtotal_vi;
            
//---------------------------------------------------------------------
            
            numerator_un =denominator_un="";
            fun_less1=fun_1to4=fun_5to14=fun_15to19=fun_20="";
            mun_less1=mun_1to4=mun_5to14=mun_15to19=mun_20="";
            subtotal_un=numerator_vi=denominator_vi="";
            fvi_less1=fvi_1to4 =fvi_5to14=fvi_15to19=fvi_20="";
            mvi_less1=mvi_1to4=mvi_5to14=mvi_15to19=mvi_20 =subtotal_vi="";
            
            //now get the unique facilities for that period from the raw data..
            
            
            int year = 0;
            String yearval = "";
            
            String passedquarter = "";
            
            if(request.getParameter("year")!=null){
                yearval = request.getParameter("year").toString();
            }
            else {
                yearval="2015";
                
            }
            if(request.getParameter("quarter")!=null){
                passedquarter=request.getParameter("quarter");
            }
            else {
                
                passedquarter="4";
                
            }
            
            
            String getfacils="  SELECT concat(Year,\"_\",Quarter,\"_\",SubPartnerID) as id,SubPartnerID, Mflcode,supporttype, " +
                    "" +
                    " COUNT( case when suppression_status like 'Y' and (sex='F'||sex='M') then suppression_status end ) as numerator_un , " +
                    " COUNT( case when (suppression_status like 'Y' || suppression_status like 'N') and (sex='F'||sex='M') then suppression_status end ) as denominator_un, " +
                    "" +
                    " COUNT( case when agebracket like '<1' and sex like 'F' and suppression_status like 'Y' then agebracket end ) as fun_less1,  " +
                    " COUNT( case when agebracket like '1-4' and sex like 'F' and suppression_status like 'Y' then agebracket end ) as fun_1to4, " +
                    " COUNT( case when agebracket like '5-14' and sex like 'F' and suppression_status like 'Y' then agebracket end ) as fun_5to14, " +
                    " COUNT( case when agebracket like '15-19' and sex like 'F' and suppression_status like 'Y' then agebracket end ) as fun_15to19, " +
                    " COUNT( case when agebracket like '20+' and sex like 'F' and suppression_status like 'Y' then agebracket end ) as fun_20, " +
                    "" +
                    " COUNT( case when agebracket like '<1' and sex like 'M' and suppression_status like 'Y' then agebracket end ) as mun_less1, " +
                    " COUNT( case when agebracket like '1-4' and sex like 'M' and suppression_status like 'Y' then agebracket end ) as mun_1to4, " +
                    " COUNT( case when agebracket like '5-14' and sex like 'M' and suppression_status like 'Y' then agebracket end ) as mun_5to14, " +
                    " COUNT( case when agebracket like '15-19' and sex like 'M' and suppression_status like 'Y' then agebracket end ) as mun_15to19, " +
                    " COUNT( case when agebracket like '20+' and sex like 'M' and suppression_status like 'Y' then agebracket end ) as mun_20, " +
                    " COUNT( case when  suppression_status like 'Y' and (sex='F'||sex='M') then suppression_status end ) as subtotal_un, " +
                    "" +
                    " COUNT( case when (suppression_status like 'Y' || suppression_status like 'N') and (sex='F'||sex='M')  then suppression_status end ) as numerator_vi, " +
                    " COUNT( case when (suppression_status like 'Y' || suppression_status like 'N') and (sex='F'||sex='M') then suppression_status end ) as denominator_vi, " +
                    "" +
                    " COUNT( case when agebracket like '<1' and sex like 'F' and (suppression_status like 'Y' || suppression_status like 'N')  then agebracket end ) as fvi_less1, " +
                    " COUNT( case when agebracket like '1-4' and sex like 'F' and (suppression_status like 'Y' || suppression_status like 'N')  then agebracket end ) as fvi_1to4, " +
                    " COUNT( case when agebracket like '5-14' and sex like 'F' and (suppression_status like 'Y' || suppression_status like 'N')  then agebracket end ) as fvi_5to14, " +
                    " COUNT( case when agebracket like '15-19' and sex like 'F' and (suppression_status like 'Y' || suppression_status like 'N')  then agebracket end ) as fvi_15to19, " +
                    " COUNT( case when agebracket like '20+' and sex like 'F'  and (suppression_status like 'Y' || suppression_status like 'N')   then agebracket end ) as fvi_20, " +
                    "" +
                    " COUNT( case when agebracket like '<1' and sex like 'M' and (suppression_status like 'Y' || suppression_status like 'N') then agebracket end ) as mvi_less1, " +
                    " COUNT( case when agebracket like '1-4' and sex like 'M' and (suppression_status like 'Y' || suppression_status like 'N') then agebracket end ) as mvi_1to4, " +
                    " COUNT( case when agebracket like '5-14' and sex like 'M' and (suppression_status like 'Y' || suppression_status like 'N') then agebracket end ) as mvi_5to14, " +
                    " COUNT( case when agebracket like '15-19' and sex like 'M' and (suppression_status like 'Y' || suppression_status like 'N') then agebracket end ) as mvi_15to19, " +
                    " COUNT( case when agebracket like '20+' and sex like 'M' and (suppression_status like 'Y' || suppression_status like 'N')    then agebracket end ) as mvi_20, " +
                    " COUNT( case when (suppression_status like 'Y' || suppression_status like 'N') and (sex='F'||sex='M') then suppression_status end ) as subtotal_vi " +
                    "" +
                    " FROM viral_load_raw  where year='"+yearval+"' and quarter='"+passedquarter+"' group by SubPartnerID;  ";
            conn.rs=conn.st.executeQuery(getfacils);
           
            while (conn.rs.next()){
               
                       //do everything else
                
                       id=conn.rs.getString("id");
                
			year=Integer.parseInt(yearval);
                        
			
			
                        quarter=Integer.parseInt(passedquarter);
                     
                        
			facilityID = conn.rs.getString("SubPartnerID");
                        
                 
			String supporttype = conn.rs.getString("supporttype");
                        
                       
			numerator_un = ""+conn.rs.getString("numerator_un");
                        
                    
			denominator_un = ""+conn.rs.getString("denominator_un");

                       
			fun_less1 =  ""+conn.rs.getString("fun_less1");
                        
                        
			fun_1to4 = ""+conn.rs.getString("fun_1to4");
                        
                        
			fun_5to14 = ""+conn.rs.getString("fun_5to14");
                        
                       
			fun_15to19 = ""+conn.rs.getString("fun_15to19");
                        
                       
			fun_20 =  ""+conn.rs.getString("fun_20");
                        
                        
                       
			mun_less1 =  ""+conn.rs.getString("mun_less1");
                        
                    
			mun_1to4 =   ""+conn.rs.getString("mun_1to4");
                        
                  
			mun_5to14 =  ""+conn.rs.getString("mun_5to14");
                        
                       
			mun_15to19 =  ""+conn.rs.getString("mun_15to19");
                    
                        
                        
			mun_20 = ""+conn.rs.getString("mun_20");
                        
                        
                       
			subtotal_un = ""+conn.rs.getString("subtotal_un");
                        
                        
                        
                    
			numerator_vi =  ""+conn.rs.getString("numerator_vi");
                        
                      
			denominator_vi = ""+conn.rs.getString("denominator_vi");
                        
                       
			fvi_less1 =   ""+conn.rs.getString("fvi_less1");
                        
                       
			fvi_1to4 =  ""+conn.rs.getString("fvi_1to4");
                        
                        
			fvi_5to14 =  ""+conn.rs.getString("fvi_5to14");
                        
                        
			fvi_15to19 =  ""+conn.rs.getString("fvi_15to19");
                        
                       
			fvi_20 = ""+conn.rs.getString("fvi_20");
                        
                         
			mvi_less1 =  ""+conn.rs.getString("mvi_less1");
                        
                      
			mvi_1to4 = ""+conn.rs.getString("mvi_1to4");
                        
                     
			mvi_5to14 = ""+conn.rs.getString("mvi_5to14");
                        
                        
			mvi_15to19= ""+conn.rs.getString("mvi_15to19");
//                        
                     
			mvi_20 = ""+conn.rs.getString("mvi_20");
                        
                        
                        
			subtotal_vi = ""+conn.rs.getString("subtotal_vi");
                        
  
          //____________________complete the sysncing______________________      
                
           
                        
                        
                        
                        
                        checker=0;
//                        CHECK IF ALREADY ADDED TO viral_load table
                       
                       String checkerExisting="SELECT id FROM viral_load WHERE id='"+id+"'";
                       conn.rs1=conn.st1.executeQuery(checkerExisting);
                       if(conn.rs1.next()==true){
                           checker++;
                       }
//                       
//                       
//                       
                       if(checker==0){
                           newcount++;
                           System.out.println("INSERT >> "+numerator_un);
  String inserter="INSERT INTO viral_load (id,SubPartnerID,year,quarter,numerator_un ,denominator_un,less1_fun,1to4_fun,5to14_fun,15to19_fun,20_fun,less1_mun,1to4_mun,5to14_mun,15to19_mun,20_mun,subtotal_un,numerator_vi,denominator_vi,less1_fvi,1to4_fvi ,5to14_fvi,15to19_fvi,20_fvi,less1_mvi,1to4_mvi,5to14_mvi,15to19_mvi,20_mvi ,subtotal_vi,supporttype) "
                         + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, id);
                        conn.pst.setString(2, facilityID);
                        conn.pst.setInt(3, year);
                        conn.pst.setInt(4, quarter);
                        conn.pst.setString(5, numerator_un);
                        conn.pst.setString(6, denominator_un);
                        conn.pst.setString(7, fun_less1);
                        conn.pst.setString(8, fun_1to4);
                        conn.pst.setString(9, fun_5to14);
                        conn.pst.setString(10, fun_15to19);
                        conn.pst.setString(11, fun_20);
                        conn.pst.setString(12, mun_less1);
                        conn.pst.setString(13, mun_1to4);
                        conn.pst.setString(14, mun_5to14);
                        conn.pst.setString(15, mun_15to19);
                        conn.pst.setString(16, mun_20);
                        conn.pst.setString(17, subtotal_un);
                        conn.pst.setString(18, numerator_vi);
                        conn.pst.setString(19, denominator_vi);
                        conn.pst.setString(20, fvi_less1);
                        conn.pst.setString(21, fvi_1to4);
                        conn.pst.setString(22, fvi_5to14);
                        conn.pst.setString(23, fvi_15to19);
                        conn.pst.setString(24, fvi_20);
                        conn.pst.setString(25, mvi_less1);
                        conn.pst.setString(26, mvi_1to4);
                        conn.pst.setString(27, mvi_5to14);
                        conn.pst.setString(28, mvi_15to19);
                        conn.pst.setString(29, mvi_20);
                        conn.pst.setString(30, subtotal_vi);
                        conn.pst.setString(31, supporttype);
                        conn.pst.executeUpdate();
                   
                      added++;
                       }
                       else{
                           updatecount++;
        String inserter="UPDATE viral_load SET SubPartnerID=?,year=?,quarter=?,numerator_un =?,denominator_un=?,less1_fun=?,1to4_fun=?,5to14_fun=?,15to19_fun=?,20_fun=?,less1_mun=?,1to4_mun=?,5to14_mun=?,15to19_mun=?,20_mun=?,subtotal_un=?,numerator_vi=?,denominator_vi=?,less1_fvi=?,1to4_fvi=? ,5to14_fvi=?,15to19_fvi=?,20_fvi=?,less1_mvi=?,1to4_mvi=?,5to14_mvi=?,15to19_mvi=?,20_mvi=?,subtotal_vi=?,supporttype=?"
                + " WHERE id=?";

                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, facilityID);
                        conn.pst.setInt(2, year);
                        conn.pst.setInt(3, quarter);
                        conn.pst.setString(4, numerator_un);
                        conn.pst.setString(5, denominator_un);
                        conn.pst.setString(6, fun_less1);
                        conn.pst.setString(7, fun_1to4);
                        conn.pst.setString(8, fun_5to14);
                        conn.pst.setString(9, fun_15to19);
                        conn.pst.setString(10, fun_20);
                        conn.pst.setString(11, mun_less1);
                        conn.pst.setString(12, mun_1to4);
                        conn.pst.setString(13, mun_5to14);
                        conn.pst.setString(14, mun_15to19);
                        conn.pst.setString(15, mun_20);
                        conn.pst.setString(16, subtotal_un);
                        conn.pst.setString(17, numerator_vi);
                        conn.pst.setString(18, denominator_vi);
                        conn.pst.setString(19, fvi_less1);
                        conn.pst.setString(20, fvi_1to4);
                        conn.pst.setString(21, fvi_5to14);
                        conn.pst.setString(22, fvi_15to19);
                        conn.pst.setString(23, fvi_20);
                        conn.pst.setString(24, mvi_less1);
                        conn.pst.setString(25, mvi_1to4);
                        conn.pst.setString(26, mvi_5to14);
                        conn.pst.setString(27, mvi_15to19);
                        conn.pst.setString(28, mvi_20);
                        conn.pst.setString(29, subtotal_vi);
                        conn.pst.setString(30, supporttype);
                        conn.pst.setString(31, id);
                        conn.pst.executeUpdate();
                       
                     updated++;
                       }
                        
                        
            }
            PrintWriter out = response.getWriter();
            try {
                
                if(conn.rs!=null){conn.rs.close();}
                if(conn.rs1!=null){conn.rs1.close();}
                if(conn.st!=null){conn.st.close();}
                if(conn.st1!=null){conn.st1.close();}
                
                out.println("Syncing completed for year "+yearval+" and quarter "+passedquarter+". <br/><br/>  <b>"+newcount+"</b>  facilities INSERTED. <br/> <br/><b>"+updatecount+"</b> facilities UPDATED ");
                
                //response.sendRedirect("sync_viral_load.jsp");
            } finally {
                out.close();
            }
        }  catch (SQLException ex) {
           Logger.getLogger(syncviralload.class.getName()).log(Level.SEVERE, null, ex);
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
