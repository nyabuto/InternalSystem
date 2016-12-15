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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import scripts.AddLastMonth;


/**
 *
 * @author Geofrey Nyabuto
 */
public class synctbdata extends HttpServlet {
 String county_name,county_id, district_name,district_id,hf_name,hf_id;


   int checker_dist,checker_hf;
   File file_source;
  HttpSession session;
 
    int year,quarter,Denominator,checker,missing,added,updated;
     String quarterName,facilityName,facilityID,id,missingFacility,Numerator,mflcode;
   String nextpage="";
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
      session=request.getSession();
      dbConn conn = new dbConn();
   
  quarterName=facilityName=facilityID=id=missingFacility=Numerator=mflcode="";

  
  year=quarter=Denominator=checker=missing=added=updated=0;
       facilityID="";
      
      
            int year = 0;
            String yearval = "";
            
            String passedquarter = "";
            
            if(request.getParameter("year")!=null){
                yearval = request.getParameter("year").toString();
            }
            else {
                yearval="2016";
                
            }
            if(request.getParameter("quarter")!=null){
                passedquarter=request.getParameter("quarter");
            }
            else {
                
                passedquarter="2";
                
            }
      
      
      String getdata=" SELECT concat(year,\"_\",quarter,\"_\",SubPartnerID) as id, " +
"SubPartnerID, " +
" COUNT( case when (hivstatus like 'Neg' || hivstatus like 'Pos')  then hivstatus end ) as numerator , " +
" COUNT( case when (hivstatus like 'Neg' || hivstatus like 'Pos' || hivstatus like 'ND' || hivstatus like 'D')   then hivstatus end ) as denominator,  " +
"	COUNT( case when (hivstatus like 'Neg' || hivstatus like 'Pos') and sex like 'F'  then sex end ) as female , " +
"  COUNT( case when (hivstatus like 'Neg' || hivstatus like 'Pos') and sex like 'M'  then sex end ) as male , " +
"    COUNT( case when (hivstatus like 'Neg' || hivstatus like 'Pos') and agebracket like '<1'  then age end ) as less1 , " +
"        COUNT( case when (hivstatus like 'Neg' || hivstatus like 'Pos') and agebracket like '1-4'  then age end ) as 1to4, " +
"        COUNT( case when (hivstatus like 'Neg' || hivstatus like 'Pos') and agebracket like '5-9'  then age end ) as 5to9, " +
"        COUNT( case when (hivstatus like 'Neg' || hivstatus like 'Pos') and agebracket like '10-14'  then age end ) as 10to14, " +
"        COUNT( case when (hivstatus like 'Neg' || hivstatus like 'Pos') and agebracket like '15-19'  then age end ) as 15to19, " +
"        COUNT( case when (hivstatus like 'Neg' || hivstatus like 'Pos') and agebracket like '20+'  then age end ) as 20above, " +
"        COUNT( case when ( hivstatus like 'Pos')  then hivstatus end ) as positive, " +
"        COUNT( case when ( hivstatus like 'Neg')  then hivstatus end ) as negative, " +
"        COUNT( case when ( hivstatus like 'Pos') and artstatus like 'Y' then artstatus end ) as art_numerator, " +
"        COUNT( case when ( hivstatus like 'Pos')  then hivstatus end ) as art_denominator, " +
"         COUNT( case when ( hivstatus like 'Pos') and artstatus like 'Y' and sex like 'F' then artstatus end ) as art_female, " +
"         COUNT( case when ( hivstatus like 'Pos') and artstatus like 'Y' and sex like 'M' then artstatus end ) as art_male, " +

"        COUNT( case when (hivstatus like 'Pos')  and artstatus like 'Y' and agebracket like '<1'  then age end ) as art_less1 , " +
"        COUNT( case when (hivstatus like 'Pos')  and artstatus like 'Y' and agebracket like '1-4'  then age end ) as art_1to4, " +
"        COUNT( case when (hivstatus like 'Pos')  and artstatus like 'Y' and agebracket like '5-9'  then age end ) as art_5to9, " +
"        COUNT( case when (hivstatus like 'Pos')  and artstatus like 'Y' and agebracket like '10-14'  then age end ) as art_10to14, " +
"        COUNT( case when (hivstatus like 'Pos')  and artstatus like 'Y' and agebracket like '15-19'  then age end ) as art_15to19, " +
"        COUNT( case when (hivstatus like 'Pos')  and artstatus like 'Y' and agebracket like '20+'  then age end ) as art_20above " +
"        ,year, " +
"        quarter, " +
"        supporttype " +
" FROM tibu_tb_raw where  year='"+yearval+"' and quarter='"+passedquarter+"' group by SubPartnerID   ";
      
      
   nextpage="sync_tb.jsp";
String female_stat,male_stat;
String less1, stat_1to4,stat_5to9,stat_10to14,stat_15to19,stat_20above,positive,negative;
 
String art_numerator, art_denominator, art_female;
String art_male,art_less1,art_1to4,art_5to9,art_10to14,art_15to19,art_20above;

 female_stat=male_stat="";
 less1=stat_1to4=stat_5to9=stat_10to14=stat_15to19=stat_20above=positive=negative="";
 
 art_numerator=art_denominator=art_female="";
 art_male=art_less1=art_1to4=art_5to9=art_10to14=art_15to19=art_20above="";

     
         session=request.getSession();
      
       

// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
conn.rs_5=conn.st_5.executeQuery(getdata);
                        int i=2,y=0;
			while(conn.rs_5.next()) {
                            
                            
                            //endelea from here
                            System.out.println(" in while");
			
                        
			year = conn.rs_5.getInt("year");
			
			quarter = conn.rs_5.getInt("quarter");
                       
			facilityID = conn.rs_5.getString("SubPartnerID");
                        
			
                        
                        
			String supporttype = conn.rs_5.getString("supporttype");
                        
                      
			Numerator = conn.rs_5.getString("numerator");
                        
			Denominator = conn.rs_5.getInt("denominator");

                       
			female_stat =  conn.rs_5.getString("female");
                        
                        
			male_stat = conn.rs_5.getString("male");
                        
                       
			less1 = conn.rs_5.getString("less1");
                        
                       
			stat_1to4 =  conn.rs_5.getString("1to4");
                        
                       
			stat_5to9 =  conn.rs_5.getString("5to9");
                        
                        
                       
			stat_10to14 =  conn.rs_5.getString("10to14");
                        
                       
			stat_15to19 = conn.rs_5.getString("15to19");
                        
                       
			stat_20above = conn.rs_5.getString("20above");
                        
                      
			positive = conn.rs_5.getString("positive");
                    
                        
                          
			negative = conn.rs_5.getString("negative");
                        
                        
                    
			art_numerator = conn.rs_5.getString("art_numerator");
                        
                          
			art_denominator = conn.rs_5.getString("art_denominator");
                        
                      
			art_female = conn.rs_5.getString("art_female");
                        
                         
			art_male = conn.rs_5.getString("art_male");
                        
                       
			art_less1 = conn.rs_5.getString("art_less1");
                        
                           
			art_1to4 =conn.rs_5.getString("art_1to4");
                        
                        
			art_5to9 =  conn.rs_5.getString("art_5to9");
                        
                      
			art_10to14 =conn.rs_5.getString("art_10to14");
                        
                     
			art_15to19 =  conn.rs_5.getString("art_15to19");
                        
                        
			art_20above = conn.rs_5.getString("art_20above");
//                        
//                        int female_stat,male_stat;
//int less1, stat_1to4,stat_5to9,stat_10to14,stat_15to19,stat_20above,positive,negative;
// 
//int art_numerator, art_denominator, art_female;
//int art_male,art_less1,art_1to4,art_5to9,art_10to14,art_15to19,art_20above;
                        
          
            checker=0;     
           
                
                    if(facilityID.length()>0) {
//                        DISTRICT FOUND ADD THE HF TO THE SYSTEM.........................
                        
                      
                       checker=0;
//                        CHECK IF ALREADY ADDED TO PMTCT_FO TABLE
                       id=year+"_"+quarter+"_"+facilityID; 
//                   System.out.println("to add data : "+facilityName+" id : "+facilityID+"mfl code "+mflcode+" year : "+year+" quarter : "+quarter+" numerator : "+Numerator+" denominator : "+Denominator);
                       
                       String checkerExisting="SELECT id FROM tb_stat_art WHERE id='"+id+"'";
                       conn.rs=conn.st.executeQuery(checkerExisting);
                       if(conn.rs.next()==true){
                           checker++;
                       }
//                       
//                       
//                       
                       if(checker==0){
  String inserter="INSERT INTO tb_stat_art (id,SubPartnerID,year,quarter,numerator,denominator,female,male,less1,1to4,5to9,10to14,15to19,20above,positive,negative,art_numerator,art_denominator,art_female,"
                                + "art_male,art_less1,art_1to4,art_5to9,art_10to14,art_15to19,art_20above,supporttype) "
                         + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, id);
                        conn.pst.setString(2, facilityID);
                        conn.pst.setInt(3, year);
                        conn.pst.setInt(4, quarter);
                        conn.pst.setString(5, Numerator);
                        conn.pst.setInt(6, Denominator);
                        conn.pst.setString(7, female_stat);
                        conn.pst.setString(8, male_stat);
                        conn.pst.setString(9, less1);
                        conn.pst.setString(10, stat_1to4);
                        conn.pst.setString(11, stat_5to9);
                        conn.pst.setString(12, stat_10to14);
                        conn.pst.setString(13, stat_15to19);
                        conn.pst.setString(14, stat_20above);
                        conn.pst.setString(15, positive);
                        conn.pst.setString(16, negative);
                        conn.pst.setString(17, art_numerator);
                        conn.pst.setString(18, art_denominator);
                        conn.pst.setString(19, art_female);
                        conn.pst.setString(20, art_male);
                        conn.pst.setString(21, art_less1);
                        conn.pst.setString(22, art_1to4);
                        conn.pst.setString(23, art_5to9);
                        conn.pst.setString(24, art_10to14);
                        conn.pst.setString(25, art_15to19);
                        conn.pst.setString(26, art_20above);
                        conn.pst.setString(27, supporttype);
                       
                        conn.pst.executeUpdate();
                   
                      added++;
                       }
                       else{
        String inserter="UPDATE tb_stat_art SET SubPartnerID=?,year=?,quarter=?,numerator=?,denominator=?, "
                +"female=?,male=?,less1=?,1to4=?,5to9=?,10to14=?,15to19=?,20above=?,positive=?,negative=?,art_numerator=?,art_denominator=?,"
                + "art_female=?,art_male=?,art_less1=?,art_1to4=?,art_5to9=?,art_10to14=?,art_15to19=?,art_20above=?,supporttype=?"
                + "WHERE id=?";

                        conn.pst=conn.conn.prepareStatement(inserter);
                        conn.pst.setString(1, facilityID);
                        conn.pst.setInt(2, year);
                        conn.pst.setInt(3, quarter);
                        conn.pst.setString(4, Numerator);
                        conn.pst.setInt(5, Denominator);
                        conn.pst.setString(6, female_stat);
                        conn.pst.setString(7, male_stat);
                        conn.pst.setString(8, less1);
                        conn.pst.setString(9, stat_1to4);
                        conn.pst.setString(10, stat_5to9);
                        conn.pst.setString(11, stat_10to14);
                        conn.pst.setString(12, stat_15to19);
                        conn.pst.setString(13, stat_20above);
                        conn.pst.setString(14, positive);
                        conn.pst.setString(15, negative);
                        conn.pst.setString(16, art_numerator);
                        conn.pst.setString(17, art_denominator);
                        conn.pst.setString(18, art_female);
                        conn.pst.setString(19, art_male);
                        conn.pst.setString(20, art_less1);
                        conn.pst.setString(21, art_1to4);
                        conn.pst.setString(22, art_5to9);
                        conn.pst.setString(23, art_10to14);
                        conn.pst.setString(24, art_15to19);
                        conn.pst.setString(25, art_20above);
                        conn.pst.setString(26, supporttype);
                        conn.pst.setString(27, id);
                        conn.pst.executeUpdate();
                       
                     updated++;
                       }
    
                    }
                    
                    else{
                       missing++; 
//                        missing facilities
                     missingFacility+="facility name : "+facilityName+" mfl code : "+mflcode+" excel row num : "+i+"<br>"; 
                        System.out.println(facilityName+ "facility is missing mflcode :"+mflcode);
                    }
                    i++;
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
                if(conn.st_5!=null){conn.st_5.close();}
                if(conn.rs_5!=null){conn.rs_5.close();}
                
                out.println("Syncing completed for year "+yearval+" and quarter "+passedquarter+". <br/><br/>  <b>"+added+"</b>  facilities INSERTED. <br/> <br/><b>"+updated+"</b> facilities UPDATED ");
                
                //response.sendRedirect("sync_viral_load.jsp");
            } finally {
                out.close();
            }        
                        
                        
                        
         }
         catch (SQLException ex) {
         Logger.getLogger(loadTBExcel.class.getName()).log(Level.SEVERE, null, ex);
     }
    
 //response.sendRedirect(nextpage);  
    
      
      
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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//     try {
//         processRequest(request, response);
//     } catch (SQLException ex) {
//         Logger.getLogger(loadPMTCT_FO.class.getName()).log(Level.SEVERE, null, ex);
//     }
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//     try {
//         processRequest(request, response);
//     } catch (SQLException ex) {
//         Logger.getLogger(loadPMTCT_FO.class.getName()).log(Level.SEVERE, null, ex);
//     }
//    }

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
