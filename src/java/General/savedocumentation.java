/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;


import database.dbConn;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)

/**
 *
 * @author EKaunda
 */
public class savedocumentation extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //try {
           
           
            String title= request.getParameter("title");
            String tasktype= request.getParameter("tasktype");
            String details= request.getParameter("details");
            String pstate= request.getParameter("pstate");
            String adate= request.getParameter("adate");
            String edate= request.getParameter("edate");
                String venue= request.getParameter("venue");
            String isactive= request.getParameter("isactive");
            String reasonforending= "";
            String emaildetails= request.getParameter("emaildetails");
            //String supportdoc= request.getParameter("destination");
        String yr="";
        String mn="";
        String qtr="";
       
           if(!edate.equals("")&& edate.length()>=10 && edate.contains("-")){
           yr=edate.split("-")[0];
           mn=edate.split("-")[1];
           
           if(new Integer(edate.split("-")[1])>=10){
           qtr="Oct-Dec";
           }
           else if(new Integer(edate.split("-")[1])>=1 && new Integer(edate.split("-")[1])<=3 ){
           qtr="Jan-Mar";
           }
           else if(new Integer(edate.split("-")[1])>=4 && new Integer(edate.split("-")[1])<=6 ){
           qtr="Apr-Jun";
           }
           else if(new Integer(edate.split("-")[1])>=7 && new Integer(edate.split("-")[1])<=9 ){
           qtr="Jul-Sep";
           }
           else {
           qtr="No quarter";
           }
           
           }
            
            // Create path components to save the file
        String allpath = getServletContext().getRealPath("/dbase.txt");
        String mydrive = allpath.substring(0, 1);
      
    String  path=mydrive+":\\APHIAPLUS\\InternalSystem\\DO_NOT_DELETE\\"; 
            
            
    //final String path = request.getParameter("destination");
   
   dbConn conn = new dbConn();
    
    final Part filePart = request.getPart("supportdoc");
    final String fileName = getFileName(filePart);

    OutputStream outs = null;
    InputStream filecontent = null;
    final PrintWriter writer = response.getWriter();

    try {
        outs = new FileOutputStream(new File(path + File.separator
                + fileName));
        filecontent = filePart.getInputStream();

        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = filecontent.read(bytes)) != -1) {
            outs.write(bytes, 0, read);
        }
        writer.println("New file " + fileName + " created at " + path);
        
        //LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", new Object[]{fileName, path});
        
        //insert data into the database
        
        String qry="insert into documentation (title,tasktype,details,previous_state,date_agreed,date_effected,venue_of_agreement,year,month,quarter,document_url,isactive,reason_for_ending,emailinstructions) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
        
        conn.pst1=conn.conn.prepareStatement(qry);   
//facilityname.startdate.enddate.hiv_pos_target_child.hiv_pos_target_adult.hiv_pos_target_total.hiv_pos_child.hiv_pos_adult.hiv_pos_total.new_care_child.new_care_adult.new_care_total.new_art_target_child.new_art_target_adult.new_art_target_total.started_art_child.started_art_adult.started_art_total.viral_load_target_child.viral_load_target_adult.viral_load_target_total.viral_load_done_child.viral_load_done_adult.viral_load_done_total.ipt_target_child.ipt_target_adult.ipt_target_total.ipt_child.ipt_adult.ipt_total.testing_target_child.testing_target_adult.testing_target_total.test_child.test_adult.test_total.pmtct_hiv_pos_target.pmtct_hiv_pos.eid_target.eid_done.viral_load_mothers_target.viral_load_mothers_done.user.hiv_pos_yield_perc_child.hiv_pos_yield_perc_adult.hiv_pos_yield_perc_all.hiv_pos_care_perc_child.hiv_pos_care_perc_adult.hiv_pos_care_perc_all.started_art_perc_child.started_art_perc_adult.started_art_perc_all.viral_test_perc_child.viral_test_perc_adult.viral_test_perc_all.ipt_done_perc_child.ipt_done_perc_adult.ipt_done_perc_all.tested_perc_child.tested_perc_adult.tested_perc_all.hiv_pos_yield_cmts.hiv_pos_care_cmts.started_art_cmts.viral_test_cmts.ipt_done_cmts.tested_cmts.viral_load_mothers_perc.eid_done_perc.pmtct_hiv_pos_perc.viral_load_mothers_cmts.eid_done_cmts.pmtct_hiv_pos_cmts
            
            conn.pst1.setString(1,title);
            conn.pst1.setString(2,tasktype);
            conn.pst1.setString(3,details);
            conn.pst1.setString(4,pstate);
            conn.pst1.setString(5,adate);
            conn.pst1.setString(6,edate);
            conn.pst1.setString(7,venue);
            conn.pst1.setString(8,yr);
            conn.pst1.setString(9,mn);
            conn.pst1.setString(10,qtr);
            conn.pst1.setString(11,path);
            conn.pst1.setString(12,isactive);
            conn.pst1.setString(13,reasonforending);
            conn.pst1.setString(14,emaildetails);
            if(conn.pst1.executeUpdate()==1){
                System.out.println(" Data saved successfully");
            }
        response.sendRedirect("documentation.jsp");
    } 
    
    catch (FileNotFoundException fne) {
        
        writer.println("You either did not specify a file to upload or are "
                + "trying to upload a file to a protected or nonexistent "
                + "location.");
        writer.println("<br/> ERROR: " + fne.getMessage());

      
        
        
    } 
    finally {
            if (outs != null) {
            outs.close();
        }
        if (filecontent != null) {
            filecontent.close();
        }
        if (writer != null) {
            writer.close();
        }
          
       
    }

            
            
  
       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(savedocumentation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(savedocumentation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
  
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
}
    
}
