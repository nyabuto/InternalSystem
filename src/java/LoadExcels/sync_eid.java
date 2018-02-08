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
import scripts.AddLastMonth;

/**
 *
 * @author Emmanuel E
 */
public class sync_eid extends HttpServlet {
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
            String SubPartnerID="";
            String less1_ftes,	oneto4_ftes,less1_mtes,oneto4_mtes;
            String less1_fpos,oneto4_fpos,less1_mpos,oneto4_mpos,less1_fneg,oneto4_fneg,less1_mneg,oneto4_mneg;
            String dead,enrolled,ltfu,other,tested,positive,negative,hivenrollment;
            String _0_2mpos, _2_12mpos, _0_2mneg, _2_12mneg, _0_2mno_result, _2_12mno_result;

           
//---------------------------------------------------------------------
            
            less1_ftes=oneto4_ftes=less1_mtes=oneto4_mtes="";
             less1_fpos=oneto4_fpos=less1_mpos=oneto4_mpos=less1_fneg=oneto4_fneg=less1_mneg=oneto4_mneg="";
             dead=enrolled=ltfu=other=tested=positive=negative=hivenrollment="";
             _0_2mpos= _2_12mpos= _0_2mneg= _2_12mneg= _0_2mno_result= _2_12mno_result="";
            
            //now get the unique facilities for that period from the raw data..
            
            
             year = 0;
            
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
            
            
            String getfacils="   SELECT concat(eid_raw_tested.Year,\"_\",eid_raw_tested.quarter,\"_\",eid_raw_tested.SubPartnerID) as id,eid_raw_tested.SubPartnerID as SubPartnerID,  " +
" COUNT( case when testresult !=''  and (sex='F') and (agebracket like '<1') then agebracket end ) as less1_ftes , " +
" COUNT( case when testresult !=''  and (sex='F') and (agebracket like '1-4') then agebracket end ) as 1to4_ftes , " +
" COUNT( case when testresult !=''  and (sex='M') and (agebracket like '<1') then agebracket end ) as less1_mtes , " +
" COUNT( case when testresult !=''  and (sex='M') and (agebracket like '1-4') then agebracket end ) as 1to4_mtes , " +
" " +
" COUNT( case when testresult like 'Positive'  and (sex='F') and (agebracket like '<1') then agebracket end ) as less1_fpos , " +
" COUNT( case when testresult like 'Positive'  and (sex='F') and (agebracket like '1-4') then agebracket end ) as 1to4_fpos , " +
" COUNT( case when testresult like 'Positive'  and (sex='M') and (agebracket like '<1') then agebracket end ) as less1_mpos , " +
" COUNT( case when testresult like 'Positive'  and (sex='M') and (agebracket like '1-4') then agebracket end ) as 1to4_mpos , " +
"  " +
" COUNT( case when testresult like 'Negative'  and (sex='F') and (agebracket like '<1') then agebracket end ) as less1_fneg , " +
" COUNT( case when testresult like 'Negative'  and (sex='F') and (agebracket like '1-4') then agebracket end ) as 1to4_fneg , " +
" COUNT( case when testresult like 'Negative'  and (sex='M') and (agebracket like '<1') then agebracket end ) as less1_mneg , " +
" COUNT( case when testresult like 'Negative'  and (sex='M') and (agebracket like '1-4') then agebracket end ) as 1to4_mneg , " +
" " +
" " +
" COUNT( case when enrollment like 'Dead'  then enrollment end ) as dead ," +
" COUNT( case when enrollment like 'Enrolled'  then enrollment end ) as enrolled ," +
" COUNT( case when enrollment like 'Lost to Follow Up'   then enrollment end ) as ltfu ," +
" COUNT( case when enrollment like 'Other'   then enrollment end ) as other ," +
" " +
"  COUNT( case when testresult !=''  and (sex='M' || sex='F' ) and agebracket!='' then testresult end ) as tested ," +
"  COUNT( case when testresult ='Positive'  and (sex='M' || sex='F' ) and agebracket!='' then testresult end ) as positive, " +
"  COUNT( case when testresult ='Negative'  and (sex='M' || sex='F' ) and agebracket!='' then testresult end ) as negative, " +
"  COUNT( case when enrollment like 'Dead' || enrollment like 'Enrolled'  || enrollment like 'Lost to Follow Up' || enrollment like 'Other'  then enrollment end ) as hivenrollment  " +
 
                    
                    
                    
                    
" , COUNT( case when testresult like 'Positive'   and (age_months between 0 and 2) then age_months end ) as 0_2mpos " +
" , COUNT( case when testresult like 'Positive'   and (age_months between 3 and 12) then age_months end ) as 2_12mpos " +
" , COUNT( case when testresult like 'Negative'   and (age_months between 0 and 2) then age_months end ) as 0_2mneg " +
" , COUNT( case when testresult like 'Negative'   and (age_months between 3 and 12) then age_months end ) as 2_12mneg  " +
" ,COUNT( case when (testresult like 'Collect New Sample' or testresult like 'Failed')   and (age_months between 0 and 2) then age_months end ) as 0_2mno_result " +
" ,COUNT( case when (testresult like 'Collect New Sample' or testresult like 'Failed')   and (age_months between 3 and 12) then age_months end ) as 2_12mno_result " +
"  " +
" FROM eid_raw_tested left join eid_raw_pos on eid_raw_tested.samplecode like eid_raw_pos.samplecode  where  eid_raw_tested.PCR_Type='Initial PCR' and eid_raw_tested.year='"+yearval+"' and eid_raw_tested.quarter='"+passedquarter+"' group by eid_raw_tested.SubPartnerID , eid_raw_tested.year,eid_raw_tested.quarter ";
  /**
   
  " , COUNT( case when testresult like 'Positive'  and (sex='M' || sex='F' ) and (age_months between 0 and 2) then age_months end ) as 0_2mpos " +
" , COUNT( case when testresult like 'Positive'  and (sex='M' || sex='F' ) and (age_months between 3 and 12) then age_months end ) as 2_12mpos " +
" , COUNT( case when testresult like 'Negative'  and (sex='M' || sex='F' ) and (age_months between 0 and 2) then age_months end ) as 0_2mneg " +
" , COUNT( case when testresult like 'Negative'  and (sex='M' || sex='F' ) and (age_months between 3 and 12) then age_months end ) as 2_12mneg  " +
" ,COUNT( case when (testresult not like 'Negative' && testresult not like 'Positive')   and (sex='M' || sex='F' ) and (age_months between 0 and 2) then age_months end ) as 0_2mno_result " +
" ,COUNT( case when (testresult not like 'Negative' && testresult not like 'Positive')  and (sex='M' || sex='F' ) and (age_months between 3 and 12) then age_months end ) as 2_12mno_result " +
"  " +
" FROM eid_raw_tested left join eid_raw_pos on eid_raw_tested.samplecode like eid_raw_pos.samplecode  where eid_raw_tested.year='"+yearval+"' and eid_raw_tested.quarter='"+passedquarter+"' group by eid_raw_tested.SubPartnerID , eid_raw_tested.year,eid_raw_tested.quarter ";
    
   
  **/ 
            
            //on 12th october , after a discussion with the clinical team, it was agreed that updates be done on the existing syntax . remove restitions on age
            
            //here is the status that was existing before
            //changes were done on the last sections
            
            System.out.println(""+getfacils);
            
            conn.rs=conn.st.executeQuery(getfacils);
           
            while (conn.rs.next()){
               
                       //do everything else
                
                id = conn.rs.getString("id");

                year = Integer.parseInt(yearval);

                quarter = Integer.parseInt(passedquarter);

                facilityID = conn.rs.getString("SubPartnerID");

                less1_ftes = "" + conn.rs.getString("less1_ftes");

                oneto4_ftes = "" + conn.rs.getString("1to4_ftes");

                less1_mtes = "" + conn.rs.getString("less1_mtes");

                oneto4_mtes = "" + conn.rs.getString("1to4_mtes");

                less1_fpos = "" + conn.rs.getString("less1_fpos");
                
                oneto4_fpos = "" + conn.rs.getString("1to4_fpos");
                
                less1_mpos = "" + conn.rs.getString("less1_mpos");
                
                oneto4_mpos = "" + conn.rs.getString("1to4_mpos");
                
                less1_fneg = "" + conn.rs.getString("less1_fneg");
                
                oneto4_fneg = "" + conn.rs.getString("1to4_fneg");
                
                less1_mneg = "" + conn.rs.getString("less1_mneg");
                
                oneto4_mneg = "" + conn.rs.getString("1to4_mneg");
                
                dead = "" + conn.rs.getString("dead");
                
                enrolled = "" + conn.rs.getString("enrolled");
                
                ltfu = "" + conn.rs.getString("ltfu");
                
                other = "" + conn.rs.getString("other");
                
                tested = "" + conn.rs.getString("tested");
                
                positive = "" + conn.rs.getString("positive");
                
                negative = "" + conn.rs.getString("negative");
                
                hivenrollment = "" + conn.rs.getString("hivenrollment");
                
                
                _0_2mpos = "" + conn.rs.getString("0_2mpos");
                _2_12mpos = "" + conn.rs.getString("2_12mpos");
                _0_2mneg = "" + conn.rs.getString("0_2mneg");
                _2_12mneg = "" + conn.rs.getString("2_12mneg");
                _0_2mno_result = "" + conn.rs.getString("0_2mno_result");
                _2_12mno_result = "" + conn.rs.getString("2_12mno_result");
                
                  //_0_2mpos= _2_12mpos= _0_2mneg= _2_12mneg= _0_2mno_result= _2_12mno_result="";
			
          //____________________complete the sysncing______________________      
                
           
                        
  //id	SubPartnerID	less1_ftes	1to4_ftes	less1_mtes	1to4_mtes	less1_fpos	1to4_fpos	less1_mpos	1to4_mpos	less1_fneg	1to4_fneg	less1_mneg	1to4_mneg	dead	enrolled	ltfu	other	tested	positive	negative	hivenrollment	year	quarter	timestamp
                      
                        
                        
                        checker=0;
//                        CHECK IF ALREADY ADDED TO viral_load table
                       
                       String checkerExisting="SELECT id FROM eid_datim WHERE id='"+id+"'";
                       conn.rs1=conn.st1.executeQuery(checkerExisting);
                       if(conn.rs1.next()==true){
                           checker++;
                       }
//                       
                      
                       if(checker==0){
                           newcount++;
                           System.out.println("INSERT >> "+tested);
  String inserter="INSERT INTO eid_datim (id,SubPartnerID,less1_ftes,1to4_ftes,less1_mtes,1to4_mtes,less1_fpos,1to4_fpos,less1_mpos,1to4_mpos,less1_fneg,1to4_fneg,less1_mneg,1to4_mneg,dead,enrolled,ltfu,other,tested,positive,negative,hivenrollment,year,quarter,0_2mpos, 2_12mpos, 0_2mneg, 2_12mneg, 0_2mno_result, 2_12mno_result) "
                         + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, id);
                        conn.pst.setString(2, facilityID);
                        conn.pst.setString(3, less1_ftes);
                        conn.pst.setString(4, oneto4_ftes);
                        conn.pst.setString(5, less1_mtes);
                        conn.pst.setString(6, oneto4_mtes);
                        conn.pst.setString(7, less1_fpos);
                        conn.pst.setString(8, oneto4_fpos);
                        conn.pst.setString(9, less1_mpos);
                        conn.pst.setString(10, oneto4_mpos);
                        conn.pst.setString(11, less1_fneg);
                        conn.pst.setString(12, oneto4_fneg);
                        conn.pst.setString(13, less1_mneg);
                        conn.pst.setString(14, oneto4_mneg);
                        conn.pst.setString(15, dead);
                        conn.pst.setString(16, enrolled);
                        conn.pst.setString(17, ltfu);
                        conn.pst.setString(18, other);
                        conn.pst.setString(19, tested);
                        conn.pst.setString(20, positive);
                        conn.pst.setString(21, negative);
                        conn.pst.setString(22, hivenrollment);
                        conn.pst.setInt(23, year);
                        conn.pst.setInt(24, quarter);
                        
                        conn.pst.setString(25, _0_2mpos);
                        conn.pst.setString(26, _2_12mpos);
                        conn.pst.setString(27, _0_2mneg);
                        conn.pst.setString(28, _2_12mneg);
                        conn.pst.setString(29, _0_2mno_result);
                        conn.pst.setString(30, _2_12mno_result);
                        //,_0_2mpos=?, _2_12mpos=?, _0_2mneg=?, _2_12mneg=?, _0_2mno_result=?, _2_12mno_result=?
                        conn.pst.executeUpdate();
                   
                      added++;
                                     }
                       else{
                           updatecount++;
        String inserter="UPDATE eid_datim SET SubPartnerID=?,less1_ftes=?,1to4_ftes=?,less1_mtes=?,1to4_mtes=?,less1_fpos=?,1to4_fpos=?,less1_mpos=?,1to4_mpos=?,less1_fneg=?,1to4_fneg=?,less1_mneg=?,1to4_mneg=?,dead=?,enrolled=?,ltfu=?,other=?,tested=?,positive=?,negative=?,hivenrollment=?,year=?,quarter=?,0_2mpos=?, 2_12mpos=?, 0_2mneg=?, 2_12mneg=?, 0_2mno_result=?, 2_12mno_result=? "
                + " WHERE id=?";

                        conn.pst=conn.conn.prepareStatement(inserter);
                        
                        conn.pst.setString(1, facilityID);
                        conn.pst.setString(2, less1_ftes);
                        conn.pst.setString(3, oneto4_ftes);
                        conn.pst.setString(4, less1_mtes);
                        conn.pst.setString(5, oneto4_mtes);
                        conn.pst.setString(6, less1_fpos);
                        conn.pst.setString(7, oneto4_fpos);
                        conn.pst.setString(8, less1_mpos);
                        conn.pst.setString(9, oneto4_mpos);
                        conn.pst.setString(10, less1_fneg);
                        conn.pst.setString(11, oneto4_fneg);
                        conn.pst.setString(12, less1_mneg);
                        conn.pst.setString(13, oneto4_mneg);
                        conn.pst.setString(14, dead);
                        conn.pst.setString(15, enrolled);
                        conn.pst.setString(16, ltfu);
                        conn.pst.setString(17, other);
                        conn.pst.setString(18, tested);
                        conn.pst.setString(19, positive);
                        conn.pst.setString(20, negative);
                        conn.pst.setString(21, hivenrollment);
                           conn.pst.setInt(22, year);
                           conn.pst.setInt(23, quarter);
                        
                        conn.pst.setString(24, _0_2mpos);
                        conn.pst.setString(25, _2_12mpos);
                        conn.pst.setString(26, _0_2mneg);
                        conn.pst.setString(27, _2_12mneg);
                        conn.pst.setString(28, _0_2mno_result);
                        conn.pst.setString(29, _2_12mno_result);
                        conn.pst.setString(30, id);                      
                        
                        
                        conn.pst.executeUpdate();
                       
                     updated++;
                       }
                        
                        
            }
            //a code to loop through all synced records without a last month
            //the affected tables are "eid_datim","viral_load","pmtct_fo","tb_stat_art"
            AddLastMonth am= new AddLastMonth();
            am.addfirstmonth();
            //end of sync last month
            
            PrintWriter out = response.getWriter();
            try {
                
                if(conn.rs!=null){conn.rs.close();}
                if(conn.rs1!=null){conn.rs1.close();}
                if(conn.st!=null){conn.st.close();}
                if(conn.st1!=null){conn.st1.close();}
                
                out.println("EID data Syncing completed for year "+yearval+" and quarter "+passedquarter+". <br/><br/>  <b>"+newcount+"</b>  facilities INSERTED. <br/> <br/><b>"+updatecount+"</b> facilities UPDATED ");
                
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
