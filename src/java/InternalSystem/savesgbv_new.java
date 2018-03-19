/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalSystem;

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
import javax.servlet.http.HttpSession;
import scripts.AddQuarter;

/**
 *
 * @author Geofrey Nyabuto
 */
public class savesgbv_new extends HttpServlet {
HttpSession session=null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    session =request.getSession();
    
    String error="success";
    String col=request.getParameter("col");
    String achieved=request.getParameter("achieved");
    
     if(achieved.equals("")){
    achieved=null;
    }
     
    // if(achieved.equalsIgnoreCase("Infinity")){  achieved="0";}
   //  if(achieved.equalsIgnoreCase("NaN")){  achieved="0";}
    dbConn conn=new dbConn();
//get the existing data for the month, year and facility that is already on session

String month="";      
String year="";      
String facil="";
String userid="unknown";

    if(session.getAttribute("userid")!=null){        
userid=session.getAttribute("userid").toString();
}

if(session.getAttribute("year")!=null){        
year=session.getAttribute("year").toString();
}
  if(session.getAttribute("monthid")!=null){        
month=session.getAttribute("monthid").toString();
}

    if(session.getAttribute("facilityid")!=null){        
facil=session.getAttribute("facilityid").toString();
}
String tableid=year+"_"+month+"_"+facil;
 
  if(year.equals("") || month.equals("") ||facil.equals("") ){
       error="<font color=\"red\">ERROR : Please select year and facility.</font>";}
    else{

int tempyear=Integer.parseInt(year);
String yearmonth="";
String tempmonth=month;
if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
if(Integer.parseInt(month)>=10){tempyear=Integer.parseInt(year)-1;}
yearmonth=tempyear+tempmonth;

String Insertqr= "insert into sgbv_new  set SubPartnerID='"+facil+"',Annee='"+year+"',Mois='"+month+"', "+col+"="+achieved+" , id='"+tableid+"' , userid='"+userid+"' , yearmonth='"+yearmonth+"' , isValidated='0'";
    String updateqr="update sgbv_new set "+col+"="+achieved+" , isValidated='0' where id='"+tableid+"'";
//check whether data for that month, year and facility has been saved

String checker="select "+col+" from sgbv_new where id='"+tableid+"'";


conn.rs=conn.st.executeQuery(checker);


if(conn.rs.next()){
    
    conn.st.executeUpdate(updateqr);
    System.out.println("~~ "+updateqr);
}
else {
    System.out.println(">> "+Insertqr);
        conn.st.executeUpdate(Insertqr);

}

//a code to loop through all synced records without a quarter
            //the affected tables are "moh711","moh731","moh711_new","kmmp","gender","tb","vmmc","nutrition"
            AddQuarter am= new AddQuarter();
            am.addQuarter();
            //end of sync last month
        }
    PrintWriter out = response.getWriter();
    try {
          System.out.println("error : "+error);
 out.println(error);
       
    } finally {
        
        if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}         
         if(conn.st!=null){ conn.st.close();}
        
        
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(savesgbv_new.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
