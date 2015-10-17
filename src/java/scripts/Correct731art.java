/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

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
 * @author Emmanuel E
 */
public class Correct731art extends HttpServlet {

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
           String HV0321,HV0322,HV0323,HV0324,HV0325;
           HV0321=HV0322=HV0323=HV0324=HV0325="0";
           
        String HV0340_1,HV0341_1,HV0342_1,HV0343_1,HV0344_1;
       HV0340_1=HV0341_1=HV0342_1=HV0343_1=HV0344_1="0";
       
         //current cummulatives
         int HV0340=0,HV0341=0,HV0342=0,HV0343=0,HV0344=0;
          
          //HV0340=HV0340_1+HV0321
          //HV0341=HV0341_1+HV0322
          //HV0342=HV0342_1+HV0323
          //HV0343=HV0343_1+HV0324
          //HV0344=HV0344_1+HV0325
          
            /* TODO output your page here. You may use following sample code. */
           dbConn conn=new dbConn();
            String get711data="select distinct(SubPartnerID) from moh731";
    conn.rs_6=conn.st_6.executeQuery(get711data);
    while(conn.rs_6.next()){
  int maxyearmonth=201504;         
for(int a=201505;a<=201509;a++){
    
   
int yearmonth=a;
//____We need to get the cumulatives up to the maximum reported yearmonth   as long as its less than the current month and year
//eg if its 2016 jan, get the max reported yearmonth.from 2014 12 backwards. Also ensure that the value of accumulatives for that max month is not Zero
//this is the default maxyearmonth
maxyearmonth=a-1; 
 
 String all731="";


//if(yrmonth>201503 && yrmonth<=201504){
 System.out.println("Max Year Month____"+maxyearmonth);
//After getting the Maximum valid YearMonth,(By Valid I mean a month that was reported with a cumulative greater than zero) select the cumulatives for male, female and totals
String facilid=conn.rs_6.getString(1);

String gatmaxs="select HV0340 , HV0341 ,HV0342,HV0343,HV0344  from moh731 where yearmonth='"+maxyearmonth+"' and SubPartnerID='"+facilid+"'";
    
      conn.rs=conn.st.executeQuery(gatmaxs);
    if(conn.rs.next()==true){
    
HV0340_1=conn.rs.getString("HV0340");
if(HV0340_1==null){HV0340_1="0"; }

HV0341_1=conn.rs.getString("HV0341");
if(HV0341_1==null){HV0341_1="0"; }

HV0342_1=conn.rs.getString("HV0342");
if(HV0342_1==null){HV0342_1="0"; }
 
HV0343_1=conn.rs.getString("HV0343");
if(HV0343_1==null){HV0343_1="0"; }

HV0344_1=conn.rs.getString("HV0344");
if(HV0344_1==null){HV0344_1="0"; }

}//end of if conn.rs.next

    System.out.println("HV0343_1__"+HV0343_1);
    System.out.println("HV0344_1__"+HV0344_1);
 //now that you have the accumulatives for the previous months, get the equivalent start art  for the current month   
    
   String getstartart=" select  HV0321,HV0322,HV0323,HV0324,HV0325 from moh731 where yearmonth='"+yearmonth+"' and SubPartnerID='"+facilid+"'"; 
   conn.rs2=conn.st2.executeQuery(getstartart);
   
   if(conn.rs2.next()){
       
   HV0321=conn.rs2.getString("HV0321");
   if(HV0321==null){HV0321="0"; }
   HV0322=conn.rs2.getString("HV0322");
   if(HV0322==null){HV0322="0"; }
   HV0323=conn.rs2.getString("HV0323");
   if(HV0323==null){HV0323="0"; }
   HV0324=conn.rs2.getString("HV0324");
   if(HV0324==null){HV0324="0"; }
   HV0325=conn.rs2.getString("HV0325");
   if(HV0325==null){HV0325="0"; }   
   
                          }//end of while
   
     //now do the update to the sites
     HV0340=Integer.parseInt(HV0340_1)+Integer.parseInt(HV0321);
     HV0341=Integer.parseInt(HV0341_1)+Integer.parseInt(HV0322);
     HV0342=Integer.parseInt(HV0342_1)+Integer.parseInt(HV0323);
     HV0343=Integer.parseInt(HV0343_1)+Integer.parseInt(HV0324);
     HV0344=Integer.parseInt(HV0344_1)+Integer.parseInt(HV0325);
          
   String updatecums="update moh731 set HV0340="+HV0340+",HV0341="+HV0341+",HV0342="+HV0342+",HV0343="+HV0343+",HV0344="+HV0344+" where yearmonth='"+yearmonth+"' and SubPartnerId='"+facilid+"' ";
   conn.st4.executeUpdate(updatecums);
    System.out.println(updatecums);
    }//end of maxyearmonths loop  


    } //end of while loop

    
    conn.st4.close();
    conn.st_6.close();
    conn.rs_6.close();
    conn.rs.close();
    conn.st.close();
    conn.rs2.close();
    conn.st2.close();
    
    
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
            Logger.getLogger(Correct731art.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Correct731art.class.getName()).log(Level.SEVERE, null, ex);
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
