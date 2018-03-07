/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
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
public class sitestracker extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           dbConn conn = new dbConn();
           
           String qrybuild="";
           String getbasic=" select * from trackermain ";
           
           String startdate="201710";
           String enddate="201712";
           String year="2018";
           
           int sd= new Integer(startdate);
           int ed= new Integer(enddate);
           
  Calendar ca= Calendar.getInstance();
  int currentyear=ca.get(Calendar.YEAR);
 
  String facilitiestable="subpartnera";
  
  int selectedyear=new Integer(year);
  
  if(selectedyear<currentyear){
      
      if(selectedyear<2014){          
      //db for 2014 is the smallest          
       facilitiestable="subpartnera2014";  
      }
      else 
      {      
  facilitiestable="subpartnera"+year;  
      }
  }
           
           conn.rs=conn.st.executeQuery(getbasic);
           int count=0;
           while(conn.rs.next())
           {
               
             
               
               String facilityvalue=conn.rs.getString("facilityvalue"); //facility value
               String value=conn.rs.getString("value"); //condition
               String indicator=conn.rs.getString("indicator"); //label
               String mflvalue=conn.rs.getString("mflvalue");  //mflcode
               String table=conn.rs.getString("table"); //table source
               
               
                
               
              
               
               
               if(facilityvalue.equals(" ") || facilityvalue.trim().equals("") ){
               
               facilityvalue="''";
               }
               else if(!facilityvalue.contains("SubPartnerNom")){
               
               facilityvalue="'"+facilityvalue+"'";
               }
               
                if(mflvalue.equals(" ") || mflvalue.trim().equals("") ){
               
               mflvalue="''";
               
                                                                       }
                         
           if(!table.equals("subpartnera")){
           
                if(count>0){
             qrybuild+=" union ";  
                          }
                else {
                 count++;
                }
               
           qrybuild+=" Select County, DistrictNom as subcounty ,"+facilityvalue+" as Facility , "+mflvalue+" as   MFLCode ,'"+year+"' as year, case " +
"  when Mois = 1 then '[1] Jan'" +
"  when Mois = 2 then '[2] Feb'" +
"  when Mois = 3 then '[3] Mar'" +
"  when Mois = 4 then '[4] Apr'" +
"  when Mois = 5 then '[5] May'" +
"  when Mois = 6 then '[6] Jun'" +
"  when Mois = 7 then '[7] Jul'" +
"  when Mois = 8 then '[8] Aug'" +
"  when Mois = 9 then '[9] Sep'" +
" when Mois = 10 then '[10] Oct'" +
" when Mois = 11 then '[11] Nov'" +
" when Mois = 12 then '[12] Dec'" +
" end as Month, " +
" yearmonth, "
+ " case when "+value+" then 1 else 0 end AS 'value', " +
"'"+indicator+"' as indicator FROM "+table+" "
+" JOIN ( "+facilitiestable+" JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID " +
"JOIN county ON district.CountyID=county.CountyID ) ON "+table+".SubPartnerID="+facilitiestable+".SubPartnerID where yearmonth between '"+startdate+"' and '"+enddate+"' group by yearmonth,"+table+".SubPartnerID ";
          
           
           
  
               
               
                                          }
           else {
          //do a loop
         
          for(int a=sd; a<=ed; a++){
              
           if(count>0){
             qrybuild+=" union ";  
                       }
           else {
            count++;
           }
           
           qrybuild+="  SELECT County,DistrictNom as subcounty ,"+facilityvalue+" as Facility,  "+mflvalue+" as MFLCode,substring("+a+",1,4) as year ,"
+ " case " +
" when substring("+a+",5,2) = '01' then '[1] Jan'" +
" when substring("+a+",5,2) = '02' then '[2] Feb'" +
" when substring("+a+",5,2) = '03' then '[3] Mar'" +
" when substring("+a+",5,2) = '04' then '[4] Apr'" +
" when substring("+a+",5,2) = '05' then '[5] May'" +
" when substring("+a+",5,2) = '06' then '[6] Jun'" +
" when substring("+a+",5,2) = '07' then '[7] Jul'" +
" when substring("+a+",5,2) = '08' then '[8] Aug'" +
" when substring("+a+",5,2) = '09' then '[9] Sep'" +
" when substring("+a+",5,2) = '10' then '[10] Oct'" +
" when substring("+a+",5,2) = '11' then '[11] Nov'" +
" when substring("+a+",5,2) = '12' then '[12] Dec'" +
" end as Month, '"+a+"' ,  count( case when "+value+" then 1 else 0 end ) as value, " +
" '"+indicator+"' as indicator  "
                   + "   FROM  "+facilitiestable+" JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID " +
" JOIN county ON district.CountyID=county.CountyID  group by  "; 
           
              
              
          
          
          }
               
               
               
           
           
           }
           
           
           
           }
            out.println(qrybuild);
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
            Logger.getLogger(sitestracker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(sitestracker.class.getName()).log(Level.SEVERE, null, ex);
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
