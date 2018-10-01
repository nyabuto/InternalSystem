/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppprev;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EKaunda
 */
public class getForm extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
        
            dbConn conn= new dbConn();
            
            //group details
            


int lessons=7;
int totalrows=1;
            
            if (request.getParameter("lessons") != null && !request.getParameter("lessons").equals("")) {
               lessons = new Integer(request.getParameter("lessons"));}
            
            if(request.getParameter("totalrows")!=null && !request.getParameter("totalrows").equals("")){
            totalrows=new Integer(request.getParameter("totalrows"));}
            
/**
 id       
**/      

 String partner="";
 String subcounty="";
 String targetpop="";
 String curriculum="";
 String group="";
 String facilitator="";
 String cofacilitator="";
 String startdate="";
 String enddate="";
 String agegroup="";

 
if(request.getParameter("partner")!=null) {partner=request.getParameter("partner"); }
if(request.getParameter("subcounty")!=null) {subcounty=request.getParameter("subcounty"); }
if(request.getParameter("targetpop")!=null) {targetpop=request.getParameter("targetpop"); }
if(request.getParameter("curriculum")!=null) {curriculum=request.getParameter("curriculum"); }
if(request.getParameter("group")!=null) {group=request.getParameter("group"); }
//if(request.getParameter("lessons")!=null) {lessons=request.getParameter("lessons"); }
if(request.getParameter("facilitator")!=null) {facilitator=request.getParameter("facilitator"); }
if(request.getParameter("cofacilitator")!=null) {cofacilitator=request.getParameter("cofacilitator"); }
if(request.getParameter("startdate")!=null) {startdate=request.getParameter("startdate"); }
if(request.getParameter("enddate")!=null) {enddate=request.getParameter("enddate"); }
if(request.getParameter("agegroup")!=null) {agegroup=request.getParameter("agegroup"); }





//___________________________________________Session details____________________________________________________
for(int a=0;a<lessons;a++){

 String topic ="";
 String method ="";
 String date ="";
 String time ="";
 String malecondoms ="";
 String femalecondoms ="";
 
if(request.getParameter("s"+a+"_topic")!=null) {          topic=request.getParameter("s"+a+"_topic");     }
if(request.getParameter("s"+a+"_method")!=null) {         method=request.getParameter("s"+a+"_method");   }
if(request.getParameter("s"+a+"_date")!=null) {           date=request.getParameter("s"+a+"_date");       }
if(request.getParameter("s"+a+"_time")!=null) {           time=request.getParameter("s"+a+"_time");       }
if(request.getParameter("s"+a+"_malecondoms")!=null) {    malecondoms=request.getParameter("s"+a+"_malecondoms");     }
if(request.getParameter("s"+a+"_femalecondoms")!=null) {  femalecondoms=request.getParameter("s"+a+"_femalecondoms"); }
    



}



//Participant details____________________________________________________
for(int a=0;a<totalrows;a++){

 String firstname ="";
 String middlename ="";
 String lastname ="";
 String age ="";
 String sex ="";

 
if(request.getParameter("firstname1")!=null) {firstname=request.getParameter("firstname1"); }
if(request.getParameter("middlename1")!=null) {middlename=request.getParameter("middlename1"); }
if(request.getParameter("lastname1")!=null) {lastname=request.getParameter("lastname1"); }
if(request.getParameter("age1")!=null) {age=request.getParameter("age1"); }
if(request.getParameter("sex1")!=null) {sex=request.getParameter("sex1"); }
   
}


//___________________________________________Session details____________________________________________________
for(int a=0;a<lessons;a++){
    
for(int b=0;b<totalrows;b++){
 
    String attendance ="";
 
if(request.getParameter("s"+a+"_"+b)!=null) { attendance=request.getParameter("s"+a+"_"+b);     }

}
}




String Inserttable=" insert into hc_participants () values ()";
      
      
      
     String variablesform[]={"formid","partner","subcounty","targetpop","curriculum","group","lessons","facilitator","cofacilitator","startdate","enddate","agegroup"};       
            
     String table1="hc_formdata_1";     
            
     ArrayList sessiondetails= new ArrayList();
     
     ArrayList participanttable= new ArrayList();
    
    
            out.println("</html>");
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
