/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalSystem;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Elkant
 */
public class validateKmmp extends HttpServlet {

       
HttpSession session=null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            
             session =request.getSession();
        
        
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

    String getexistingdata="select * from kmmp where tableid='"+tableid+"'";
        
    String KMMP1="";
    String KMMP2="";
    String KMMP3a="";
    String KMMP3b="";
    String KMMP3c="0";
    String KMMP4="";
    String KMMP5a="";
    String KMMP5b="";
    String KMMP5c="";
    String HV0205="";
    String HV0206="";

    String updatestring="";
        
        conn.rs=conn.st.executeQuery(getexistingdata);
        while(conn.rs.next()){
        
            //now load the column values here
           
    KMMP1=conn.rs.getString("KMMP1");
    if(KMMP1==null){KMMP1="";
    updatestring+=" KMMP1='0' ,";
    }

    KMMP2=conn.rs.getString("KMMP2");
    if(KMMP2==null){KMMP2="";
    updatestring+=" KMMP2='0' ,";
    }

    KMMP3a=conn.rs.getString("KMMP3a");
    if(KMMP3a==null){KMMP3a="";
    updatestring+=" KMMP3a='0' ,";
    }

    KMMP3b=conn.rs.getString("KMMP3b");
    if(KMMP3b==null){KMMP3b=""; 
       updatestring+=" KMMP3b='0' ,";
    }

    KMMP3c=conn.rs.getString("KMMP3c");
    if(KMMP3c==null){KMMP3c="";
       updatestring+=" KMMP3c='0' ,";
    }

    KMMP4=conn.rs.getString("KMMP4");
    if(KMMP4==null){KMMP4="";
       updatestring+=" KMMP4='0' ,";
    }

    KMMP5a=conn.rs.getString("KMMP5a");
    if(KMMP5a==null){KMMP5a="";
       updatestring+=" KMMP5a='0' ,";
    }

    KMMP5b=conn.rs.getString("KMMP5b");
    if(KMMP5b==null){KMMP5b="";
       updatestring+=" KMMP5b='0' ,";
    }

    KMMP5c=conn.rs.getString("KMMP5c");
    if(KMMP5c==null){KMMP5c="";
       updatestring+=" KMMP5c='0' ,";
    }

    HV0205=conn.rs.getString("HV0205");
    if(HV0205==null){HV0205=""; 
       updatestring+=" HV0205='0' ,";
    }

    HV0206=conn.rs.getString("HV0206");
    if(HV0206==null){HV0206=""; 
       updatestring+=" HV0206='0' ,";
    }
            
        }
        
        String updqr="update kmmp set  ";

if(!updatestring.equals("")){
updqr+=" "+updatestring+" isValidated='1'";
}
else {
updqr+=" isValidated='1'";
}

updqr+=" where tableid='"+tableid+"'";

            System.out.println("__update qr  "+updqr);

if(conn.st.executeUpdate(updqr)==1){

    
    int monthDiff=0;
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM kmmp WHERE tableid='"+tableid+"'";
     conn.rs1=conn.st1.executeQuery(getMonths);
     if(conn.rs1.next()==true){
         System.out.println("months are : "+conn.rs1.getString(1));
    monthDiff=conn.rs1.getInt(1);
     }
     if(monthDiff>0){
//      UPDATE THE COLUMN   
       java.util.Date date= new java.util.Date();
Timestamp lastUpdatedOn =new Timestamp(date.getTime()); 

     String updateLast="UPDATE kmmp SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE tableid='"+tableid+"'" ;   
       conn.st2.executeUpdate(updateLast);
     }
     
    

session.setAttribute("kmmpresponse", "<font color=\"green\"><b>KMMP Form validated Successfully!</b></font>");
}
        
    if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}       
         if(conn.st!=null){ conn.st.close();}
         if(conn.st2!=null){ conn.st2.close();}     
        
        } catch (SQLException ex) {
            Logger.getLogger(validateKmmp.class.getName()).log(Level.SEVERE, null, ex);
        }

response.sendRedirect("loadKmmp.jsp");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
