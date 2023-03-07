/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMR;

import database.OSValidator;
import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import static jdk.nashorn.internal.objects.NativeError.getFileName;
import scripts.copytemplates;


@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 20 MB 
        maxFileSize = 1024 * 1024 * 500, // 500 MB
        maxRequestSize = 1024 * 1024 * 500)
/**
 *
 * @author EKaunda
 */


public class saveEmrStatus extends HttpServlet {

  String full_path="";
  String fileName="";
  File file_source;
    private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "emrfiles";
    
    HttpSession session=null;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
                
        session=request.getSession();
        
        String msg="EMR status Data Saved succesfully ";
                
   String ym="";
   String facil="";
   
    dbConn conn = new dbConn();
   
   if(request.getParameter("yearmonth")!=null){ym=request.getParameter("yearmonth");}
   if(request.getParameter("facility_id")!=null){facil=getFacilitynameGivenId(conn,request.getParameter("facility_id")).replace(" ","_").replace("'","");}
   
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            //list of all data elements to be saved
            String dataelementsarr[]={"id","yearmonth","facility_id","emr","emr_version","emr_status","has_backup_disk","no_of_emr_adt_comps","has_adt","adt_version","has_power_backup","is_tx_curr_emr","tx_curr_paper","tx_curr_emr","site_dropped_ccc_dar","comments","hts_emr","art_emr","anc_emr","eid_emr","tb_emr","emr_accuracy","emr_completeness","emr_last_backup_date"};
            /* TODO output your page here. You may use following sample code. */
            
            
            
            
            
               String applicationPath = request.getServletContext().getRealPath("");
         String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
         session=request.getSession();
          File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        
        for (Part part : request.getParts()) {
            if(!getFileName(part).equals("")){
           fileName = (String) getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            }
         ArrayList uploadedfiles=new ArrayList();
        if(!fileName.endsWith(".gz")){
          session.setAttribute("upload_success", "<font color=\"red\">Failed to save backup file.</font>");   
        }
        else {
            
            
            full_path=fileSaveDir.getAbsolutePath()+"/"+fileName;
            
            System.out.println("Full path ni__"+full_path);

  FileInputStream fileInputStream = new FileInputStream(full_path);
            
  
  
  
  
  String allpath = getServletContext().getRealPath("/F1av7.xlsx");
   String mydrive = allpath.substring(0, 1);
  
  
  String np = "";
            
            if (OSValidator.isWindows()) 
            {
                np = mydrive + ":\\HSDSA\\EMR_Files\\EMR_" + facil +"_"+ym+ ".gz";
                 
            }
            else if (OSValidator.isUnix()) 
            {
                np = "/HSDSA/EMR_Files/EMR_" + facil+"_"+ym + ".gz";
             
                
            }
            
        String sr = full_path;
            
             File f = new File(np);
            if (!f.exists() && !f.isDirectory()) 
            {
                /* do something */
                copytemplates ct = new copytemplates();
                ct.transfermacros(sr, np);
                //rem np is the destination file name  

                System.out.println("Copying  template..");

            } 
            
            else //copy the file alone  
            {
                copytemplates ct = new copytemplates();

                ct.copymacros(sr, np);

            }
  
            
             }//end of file upload
        }//end of for loop
            //sample query being constructed
            
            
           
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
      try {
          processRequest(request, response);
      } catch (SQLException ex) {
          Logger.getLogger(saveEmrStatus.class.getName()).log(Level.SEVERE, null, ex);
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
          Logger.getLogger(saveEmrStatus.class.getName()).log(Level.SEVERE, null, ex);
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
    
     private String getFileName(Part part) {
            String file_name="";
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
      
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                file_name = token.substring(token.indexOf("=") + 2, token.length()-1);
              break;  
            }
            
        }
         System.out.println("content-disposition final : "+file_name);
        return file_name;
    }
     
     
      public String getFacilitynameGivenId(dbConn conn, String facilityid) throws SQLException{
     String facility="unkown";
     
     
     String qry="select concat(subpartnernom,'_',CentreSanteID) as facil from subpartnera where CentreSanteID='"+facilityid+"'";
          System.out.println("_____Queryni:"+qry);
     conn.rs_6=conn.st_6.executeQuery(qry);
     
     while(conn.rs_6.next())
     {
     facility=conn.rs_6.getString(1);
     }
     
     return facility;
     
     }

}
