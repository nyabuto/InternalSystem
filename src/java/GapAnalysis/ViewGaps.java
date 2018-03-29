/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GapAnalysis;

import database.dbConnWeb;
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
 * @author GNyabuto
 */
public class ViewGaps extends HttpServlet {
HttpSession session;
String id,year,month,rule,gap,program_area,county,subcounty,ward,facility,latitude,longitude,explanation,status_label,downloaded,timestamp;
String query,headers,data,Update_btn,lock;
int status,pos,approvesgaps;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConnWeb conn = new dbConnWeb();
            session = request.getSession();
         
            if(session.getAttribute("access_gapanalysis")!=null){
                
            
         approvesgaps=Integer.parseInt(session.getAttribute("access_gapanalysis").toString());   
             
        headers = "<thead>"
        + "<tr border=\"1px;\">"
        + "<th class=\"title\" style=\"width:2%\">No.</th>"
//        + "<th class=\"title\" style=\"width:15%\">Rule</th>"
        + "<th class=\"title\" style=\"width:13%\">Gap</th>"
        + "<th class=\"title\" style=\"width:8%\">Program Area</th>"
        + "<th class=\"title\" style=\"width:8%\">County</th>"
        + "<th class=\"title\" style=\"width:8%\">Sub County</th>"
        + "<th class=\"title\" style=\"width:8%\">Ward</th>"
        + "<th class=\"title\" style=\"width:9%\">Facility</th>"
        + "<th class=\"title\" style=\"width:4%\">Year</th>"
        + "<th class=\"title\" style=\"width:5%\">Month</th>"
        + "<th class=\"title\" style=\"width:18%\">Justification</th>"
        + "<th class=\"title\" style=\"width:6%\">Status</th>"
        + "<th class=\"title\" style=\"width:6%\">Action</th>"
        + "</tr>"
        + "</thead><tdata>"; 
        
            
          data = "";  
            
            
            
            
            
            query = " SELECT * FROM gaps WHERE 1=1 AND ";
            pos = 0;
            if(request.getParameter("year")!=null){
            year = request.getParameter("year");
            if(!year.equals("")){
              query += "year='"+year+"' AND ";  
            }
            }
            if(request.getParameter("month")!=null){
            month = request.getParameter("month");
            if(!month.equals("")){
              query += "month='"+month+"' AND ";      
            }
            }
            if(request.getParameter("gap")!=null){
            gap = request.getParameter("gap");
            if(!gap.equals("")){
              query += "gap='"+gap+"' AND ";      
            }
            }
            if(request.getParameter("county")!=null){
            county = request.getParameter("county");
            if(!county.equals("")){
              query += "county='"+county+"' AND ";      
            }
            }
            if(request.getParameter("subcounty")!=null){
            subcounty = request.getParameter("subcounty");
            if(!subcounty.equals("")){
              query += "sub_county='"+subcounty+"' AND ";      
            }
            }
            if(request.getParameter("facility")!=null){
            facility = request.getParameter("facility");
            if(!facility.equals("")){
              query += "facility='"+facility+"' AND ";      
            }
            }
            if(request.getParameter("status")!=null){
            status_label = request.getParameter("status");
            if(!status_label.equals("")){
              query += "status='"+status_label+"' AND ";      
            }
            }
            //remove the last AND from the query
            query = removeLast(query, 5);
            //end of removing the AND
            System.out.println("Query is : "+query);
            
            conn.rs = conn.st.executeQuery(query);
            while(conn.rs.next()){
                pos++;
            id = conn.rs.getString("id");
            year = conn.rs.getString("year");
            month = conn.rs.getString("month");
            rule = conn.rs.getString("rule");
            gap = conn.rs.getString("gap");
            program_area = conn.rs.getString("program_area");
            county = conn.rs.getString("county");
            subcounty = conn.rs.getString("sub_county");
            ward = conn.rs.getString("ward");
            facility = conn.rs.getString("facility");
            latitude = conn.rs.getString("latitude");
            longitude = conn.rs.getString("longitude");
            explanation = conn.rs.getString("explanation");
            status = conn.rs.getInt("status");
            if(explanation==null){
            explanation="";
            }
            else if(explanation.equals("null")){
             explanation="";
            }
                System.out.println("explanation : "+explanation);
            downloaded = conn.rs.getString("downloaded");
            timestamp = conn.rs.getString("timestamp");    
            
             if(status==1){
                 status_label="Approved";
                 Update_btn=""; 
                 lock="disabled";
             }
             
             else{
                status_label="Not Approved";
                if(approvesgaps==1 && !explanation.equals("")){
                Update_btn="<input type='submit' class='btn green'   style='height:30px;' value='Approve Gap' onclick=\"approve_gap("+pos+");\" name='approve_gap' id='approve_gap'/>";    
                lock="disabled";
                }
                else if(approvesgaps==1 && explanation.equals("")){
                Update_btn="";  
                lock="disabled";
                }
                
                else if(approvesgaps==0){
                 Update_btn="<input type='submit' class='btn blue '  style='height:30px;' value='Update Gap' onclick=\"update_gap("+pos+");\" name='update_gap' id='update_gap'/>";   
                lock="";
                }
                else{
                Update_btn="";
                lock="disabled";
                }
             }
            data = data+ "<tr border=\"1px;\">"
                       + ""
                        + "<td class=\"title\">"+pos+" <input type=\"hidden\" name=\"gapid_"+pos+"\" id=\"gapid_"+pos+"\" value=\""+id+"\"></td>"
//                        + "<td class=\"title\">"+rule+"</td>"
                        + "<td class=\"title\">"+gap+"</td>"
                        + "<td class=\"title\">"+program_area+"</td>"
                        + "<td class=\"title\">"+county+"</td>"
                        + "<td class=\"title\">"+subcounty+"</td>"
                        + "<td class=\"title\">"+ward+"</td>"
                        + "<td class=\"title\">"+facility+"</td>"
                        + "<td class=\"title\">"+year+"</td>"
                        + "<td class=\"title\">"+month+"</td>";
                        if(lock.equals("")){
                       data+= "<td class=\"title\"><textarea style=\"width:95%\" id=\"explanation_"+pos+"\" "+lock+" name=\"explanation_"+pos+"\">"+explanation+"</textarea></td>";
                        }
                        else{
                        data+= "<td class=\"title\">"+explanation+"</td>";    
                        }
                        data+= "<td class=\"title\">"+status_label+"</td>"
                         + "<td class=\"title\">"+Update_btn+"</td>"
                        + "</tr>"
                        + "";  
            }
          data+="</tdata>";
          }
            else{
              headers="";
              data="<div style=\"height:50px; margin: 10 10 10 10 \" ><br><b style=\"font-size: 25px; color:black; font-weight: bolder;\">Not Authorised to view Gaps</b></div>";
            }
           String output=headers+""+data;
           out.println(output);
       
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
        Logger.getLogger(ViewGaps.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(ViewGaps.class.getName()).log(Level.SEVERE, null, ex);
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
  
    public String removeLast(String str, int num) {
    if (str != null && str.length() > 0) {
        str = str.substring(0, str.length() - num);
    }
    return str;
    } 
}
