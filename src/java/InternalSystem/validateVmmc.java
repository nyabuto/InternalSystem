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
public class validateVmmc extends HttpServlet {

     HttpSession session=null;
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
           
            session=request.getSession();
        
        
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
            
         
        
        
    String updqr1="update vmmc set ";


    String SP51D1=request.getParameter("P51D1");
      if(SP51D1.equals("")){SP51D1=null;} 
    updqr1+="P51D1="+SP51D1 +", ";
    String SP51D9=request.getParameter("P51D9");
       if(SP51D1.equals("")){SP51D1=null;} 
    updqr1+="P51D9="+SP51D9 +", ";
    String SP51D10=request.getParameter("P51D10");
       if(SP51D10.equals("")){SP51D10=null;} 
    updqr1+="P51D10="+SP51D10 +", ";
    String SP51D19=request.getParameter("P51D19");
       if(SP51D19.equals("")){SP51D19=null;} 
    updqr1+="P51D19="+SP51D19 +", ";
    String SP51D24=request.getParameter("P51D24");
       if(SP51D24.equals("")){SP51D24=null;} 
    updqr1+="P51D24="+SP51D24 +", ";
    
     String SP51D29=request.getParameter("P51D29");
       if(SP51D29.equals("")){SP51D29=null;} 
    updqr1+="P51D29="+SP51D29 +", ";
    String SP51D49=request.getParameter("P51D49");
       if(SP51D49.equals("")){SP51D49=null;} 
    updqr1+="P51D49="+SP51D49 +", ";
    String SP51D50=request.getParameter("P51D50");
       if(SP51D50.equals("")){SP51D50=null;} 
    updqr1+="P51D50="+SP51D50 +", ";

    updqr1+=" isValidated='0' where  tableid='"+tableid+"'";

    conn.st.executeUpdate(updqr1);
        
        
        
        String getexistingdata="select * from vmmc where tableid='"+tableid+"'";
        
    String P51D1="";
    String P51D9="";
    String P51D10="";
    String P51D19="";
    String P51D24="";
    String P51D29="";
    String P51D49="";
    String P51D50="";
    String P51DT="";
    String P521DM="";
    String P521DS="";
    String P521DT="";
    String P522DM="";
    String P522DS="";
    String P522DT="";
    String P52DM="";
    String P52DS="";
    String P52DT="";
    String P511KP="";
    String P511KN="";
    String P511KU="";
    String P511Surg="";
    String P511Dev="";
    String P53DF="";
    String P53DO="";
    String P53DM="";
    String P53D="";
    String P54D="";


  String updatestring="";


        conn.rs=conn.st.executeQuery(getexistingdata);
        while(conn.rs.next()){
        
            //now load the column values here
           
    P51D1=conn.rs.getString("P51D1");
    if(P51D1==null){P51D1="";
    updatestring+=" P51D1='0' ,";
    }

    P51D9=conn.rs.getString("P51D9");
    if(P51D9==null){P51D9="";
     updatestring+=" P51D9='0' ,";
    }

    P51D10=conn.rs.getString("P51D10");
    if(P51D10==null){P51D10="";
     updatestring+=" P51D10='0' ,";
    }

    P51D19=conn.rs.getString("P51D19");
    if(P51D19==null){P51D19="";
     updatestring+=" P51D19='0' ,";
    }

    P51D24=conn.rs.getString("P51D24");
    if(P51D24==null){P51D24="";
     updatestring+=" P51D24='0' ,";
    }

     P51D29=conn.rs.getString("P51D29");
    if(P51D29==null){P51D29="";
     updatestring+=" P51D29='0' ,";
    }
    
    P51D49=conn.rs.getString("P51D49");
    if(P51D49==null){P51D49="";
     updatestring+=" P51D49='0' ,";
    }

    P51D50=conn.rs.getString("P51D50");
    if(P51D50==null){P51D50="";
     updatestring+=" P51D50='0' ,";
    }

    P51DT=conn.rs.getString("P51DT");
    if(P51DT==null){P51DT="";
     updatestring+=" P51DT='0' ,";
    }

    P521DM=conn.rs.getString("P521DM");
    if(P521DM==null){P521DM="";
     updatestring+=" P521DM='0' ,";
    }

    P521DS=conn.rs.getString("P521DS");
    if(P521DS==null){P521DS="";
     updatestring+=" P521DS='0' ,";
    }

    P521DT=conn.rs.getString("P521DT");
    if(P521DT==null){P521DT="";
     updatestring+=" P521DT='0' ,";
    }  



    P522DM=conn.rs.getString("P522DM");
    if(P522DM==null){P522DM="";
     updatestring+=" P522DM='0' ,";
    }

    P522DS=conn.rs.getString("P522DS");
    if(P522DS==null){P522DS="";
     updatestring+=" P522DS='0' ,";
    }

    P522DT=conn.rs.getString("P522DT");
    if(P522DT==null){P522DT="";
     updatestring+=" P522DT='0' ,";
    }


    P52DM=conn.rs.getString("P52DM");
    if(P52DM==null){P52DM="";
     updatestring+=" P52DM='0' ,";
    }


    P52DS=conn.rs.getString("P52DS");
    if(P52DS==null){P52DS="";
     updatestring+=" P52DS='0' ,";
    }


    P52DT=conn.rs.getString("P52DT");
    if(P52DT==null){P52DT="";
     updatestring+=" P52DT='0' ,";
    }


    P511KP=conn.rs.getString("P511KP");
    if(P511KP==null){P511KP="";
     updatestring+=" P511KP='0' ,";
    }


    P511KN=conn.rs.getString("P511KN");
    if(P511KN==null){P511KN="";
     updatestring+=" P511KN='0' ,";
    }

    P511KU=conn.rs.getString("P511KU");
    if(P511KU==null){P511KU="";
     updatestring+=" P511KU='0' ,";
    }

    P511Surg=conn.rs.getString("P511Surg");
    if(P511Surg==null){P511Surg="";
     updatestring+=" P511Surg='0' ,";
    }


    P511Dev=conn.rs.getString("P511Dev");
    if(P511Dev==null){P511Dev="";
     updatestring+=" P511Dev='0' ,";
    }

    P53DF=conn.rs.getString("P53DF");
    if(P53DF==null){P53DF="";
     updatestring+=" P53DF='0' ,";
    }

    P53DO=conn.rs.getString("P53DO");
    if(P53DO==null){P53DO="";
     updatestring+=" P53DO='0' ,";
    }

    P53DM=conn.rs.getString("P53DM");
    if(P53DM==null){P53DM="";
     updatestring+=" P53DM='0' ,";
    }

    P53D=conn.rs.getString("P53D");
    if(P53D==null){P53D="";
     updatestring+=" P53D='0' ,";
    }
            
     P54D=conn.rs.getString("P54D");
    if(P54D==null){P54D="";
     updatestring+=" P54D='0' ,";
    }       

        }//end of while
        
        
        
         String updqr="update vmmc set  ";

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
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM vmmc WHERE tableid='"+tableid+"'";
     conn.rs1=conn.st1.executeQuery(getMonths);
     if(conn.rs1.next()==true){
         System.out.println("months are : "+conn.rs1.getString(1));
    monthDiff=conn.rs1.getInt(1);
     }
     if(monthDiff>0){
//      UPDATE THE COLUMN   
       java.util.Date date= new java.util.Date();
Timestamp lastUpdatedOn =new Timestamp(date.getTime()); 

     String updateLast="UPDATE vmmc SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE tableid='"+tableid+"'" ;   
       conn.st2.executeUpdate(updateLast);
     }
     
    

session.setAttribute("vmmcresponse", "<font color=\"green\"><b>VMMC Form validated Successfully!</b></font>");

}
        
        
    if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}       
         if(conn.st!=null){ conn.st.close();}
         if(conn.st2!=null){ conn.st2.close();}     
        
        
        
        } catch (SQLException ex) {
            Logger.getLogger(validateVmmc.class.getName()).log(Level.SEVERE, null, ex);
        }

        
   response.sendRedirect("loadVmmc.jsp");     
        
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
