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
public class validateGender extends HttpServlet {
    
HttpSession session=null;


    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    
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


String updqr1="update gender set ";

String SP121DM0=request.getParameter("P121DM0");
updqr1+="P121DM0="+SP121DM0 +", ";
String SP121DF0=request.getParameter("P121DF0");
updqr1+="P121DF0="+SP121DF0 +", ";
String SP121DM10=request.getParameter("P121DM10");
updqr1+="P121DM10="+SP121DM10 +", ";
String SP121DF10=request.getParameter("P121DF10");
updqr1+="P121DF10="+SP121DF10 +", ";
String SP121DM15=request.getParameter("P121DM15");
updqr1+="P121DM15="+SP121DM15 +", ";
String SP121DF15=request.getParameter("P121DF15");
updqr1+="P121DF15="+SP121DF15 +", ";
String SP121DM20=request.getParameter("P121DM20");
updqr1+="P121DM20="+SP121DM20 +", ";
String SP121DF20=request.getParameter("P121DF20");
updqr1+="P121DF20="+SP121DF20 +", ";
String SP121DM25=request.getParameter("P121DM25");
updqr1+="P121DM25="+SP121DM25 +", ";
String SP121DF25=request.getParameter("P121DF25");
updqr1+="P121DF25="+SP121DF25 +", ";



updqr1+=" isValidated='0' where  tableid='"+tableid+"'";

conn.st.executeUpdate(updqr1);

String P121DM0="";
String P121DF0="";
String P121DM10="";
String P121DF10="";
String P121DM15="";
String P121DF15="";
String P121DM20="";
String P121DF20="";
String P121DM25="";
String P121DF25="";
String P121DMT="";
String P121DFT="";
String P121DTT="";
String P122DM0="";
String P122DF0="";
String P122DM15="";
String P122DF15="";
String P122DM25="";
String P122DF25="";
String P122DMT="";
String P122DFT="";
String P122DTT="";
String P123DM0="";
String P123DF0="";
String P123DM15="";
String P123DF15="";
String P123DM25="";
String P123DF25="";
String P123DMT="";
String P123DFT="";
String P123DTT="";
String P124DM0="";
String P124DF0="";
String P124DM15="";
String P124DF15="";
String P124DM25="";
String P124DF25="";
String P124DMT="";
String P124DFT="";
String P124DTT="";
String GEND_GBV9M="";
String GEND_GBV9F="";
String GEND_GBV9="";
String GEND_GBV14M="";
String GEND_GBV14F="";
String GEND_GBV14="";
String GEND_GBV17M="";
String GEND_GBV17F="";
String GEND_GBV17="";
String GEND_GBV24M="";
String GEND_GBV24F="";
String GEND_GBV24="";
String GEND_GBV25M="";
String GEND_GBV25F="";
String GEND_GBV25="";
String GEND_GBVM="";
String GEND_GBVF="";
String GEND_GBV="";


String P121D0="";
String P121D10="";
String P121D15="";
String P121D20="";
String P121D25="";
String P122D0="";
String P122D15="";
String P122D25="";
String P123D0="";
String P123D15="";
String P123D25="";
String P124D0="";
String P124D15="";
String P124D25="";




String getdata="select * from gender where tableid ='"+tableid+"'";


conn.rs=conn.st.executeQuery(getdata);

String updatestring="";


while (conn.rs.next()){

       //now load the column values here
//====================================================================p122 
    
    
P121D0=conn.rs.getString("P121D0");
if(P121D0==null){P121D0="0";
updatestring+=" P121D0='0' ,";
}    
  

P121D10=conn.rs.getString("P121D10");
if(P121D10==null){P121D10="0";
updatestring+=" P121D10='0' ,";
}    

P121D15=conn.rs.getString("P121D15");
if(P121D15==null){P121D15="0";
updatestring+=" P121D15='0' ,";
}    

P121D20=conn.rs.getString("P121D20");
if(P121D20==null){P121D20="0";
updatestring+=" P121D20='0' ,";
}    

P121D25=conn.rs.getString("P121D25");
if(P121D25==null){P121D25="0";
updatestring+=" P121D25='0' ,";
}    

P122D0=conn.rs.getString("P122D0");
if(P122D0==null){P122D0="0";
updatestring+=" P122D0='0' ,";
}    

P122D15=conn.rs.getString("P122D15");
if(P122D15==null){P122D15="0";
updatestring+=" P122D15='0' ,";
}    

P122D25=conn.rs.getString("P122D25");
if(P122D25==null){P122D25="0";
updatestring+=" P122D25='0' ,";
}    

P123D0=conn.rs.getString("P123D0");
if(P123D0==null){P123D0="0";
updatestring+=" P123D0='0' ,";
}    

P123D15=conn.rs.getString("P123D15");
if(P123D15==null){P123D15="0";
updatestring+=" P123D15='0' ,";
}    

P123D25=conn.rs.getString("P123D25");
if(P123D25==null){P123D25="0";
updatestring+=" P123D25='0' ,";
}    

P124D0=conn.rs.getString("P124D0");
if(P124D0==null){P124D0="0";
updatestring+=" P124D0='0' ,";
}    

P124D15=conn.rs.getString("P124D15");
if(P124D15==null){P124D15="0";
updatestring+=" P124D15='0' ,";
}    

P124D25=conn.rs.getString("P124D25");
if(P124D25==null){P124D25="0";
updatestring+=" P124D25='0' ,";
}    
//---------------------------------------------------------------------------------    
    
    
P121DM0=conn.rs.getString("P121DM0");
if(P121DM0==null){P121DM0="0";
updatestring+=" P121DM0='0' ,";
}

P121DF0=conn.rs.getString("P121DF0");
if(P121DF0==null){P121DF0="0"; 
updatestring+=" P121DF0='0' ,";
}

P121DM10=conn.rs.getString("P121DM10");
if(P121DM10==null){P121DM10="0";
updatestring+=" P121DM10='0' ,";
}

P121DF10=conn.rs.getString("P121DF10");
if(P121DF10==null){P121DF10="0";
updatestring+=" P121DF10='0' ,";
}

P121DM15=conn.rs.getString("P121DM15");
if(P121DM15==null){P121DM15="0";
updatestring+=" P121DM15='0' ,";
}

P121DF15=conn.rs.getString("P121DF15");
if(P121DF15==null){P121DF15="0";
updatestring+=" P121DF15='0' ,";
}

P121DM20=conn.rs.getString("P121DM20");
if(P121DM20==null){P121DM20="0"; 
updatestring+=" P121DM20='0' ,";
}

P121DF20=conn.rs.getString("P121DF20");
if(P121DF20==null){P121DF20="0";
updatestring+=" P121DF20='0' ,";
}


P121DM25=conn.rs.getString("P121DM25");
if(P121DM25==null){P121DM25="0"; 
updatestring+=" P121DM25='0' ,";
}

P121DF25=conn.rs.getString("P121DF25");
if(P121DF25==null){P121DF25="0"; 
updatestring+=" P121DF25='0' ,";
}


P121DMT=conn.rs.getString("P121DMT");
if(P121DMT==null){P121DMT="0";
updatestring+=" P121DMT='0' ,";
}

P121DFT=conn.rs.getString("P121DFT");
if(P121DFT==null){P121DFT="0"; 
updatestring+=" P121DFT='0' ,";
}


P121DTT=conn.rs.getString("P121DTT");
if(P121DTT==null){P121DTT="0"; 
updatestring+=" P121DTT='0' ,";
}

//====================================================================p122

P122DM0=conn.rs.getString("P122DM0");
if(P122DM0==null){P122DM0="0";
updatestring+=" P122DM0='0' ,";
}

P122DF0=conn.rs.getString("P122DF0");
if(P122DF0==null){P122DF0="0"; 
updatestring+=" P122DF0='0' ,";
}



P122DM15=conn.rs.getString("P122DM15");
if(P122DM15==null){P122DM15="0";
updatestring+=" P122DM15='0' ,";
}

P122DF15=conn.rs.getString("P122DF15");
if(P122DF15==null){P122DF15="0";
updatestring+=" P122DF15='0' ,";
}



P122DM25=conn.rs.getString("P122DM25");
if(P122DM25==null){P122DM25="0"; 
updatestring+=" P122DM25='0' ,";
}

P122DF25=conn.rs.getString("P122DF25");
if(P122DF25==null){P122DF25="0"; 
updatestring+=" P122DF25='0' ,";
}


P122DMT=conn.rs.getString("P122DMT");
if(P122DMT==null){P122DMT="0";
updatestring+=" P122DMT='0' ,";
}

P122DFT=conn.rs.getString("P122DFT");
if(P122DFT==null){P122DFT="0"; 
updatestring+=" P122DFT='0' ,";
}


P122DTT=conn.rs.getString("P122DTT");
if(P122DTT==null){P122DTT="0";
updatestring+=" P122DTT='0' ,";

}


//====================================================================p123

P123DM0=conn.rs.getString("P123DM0");
if(P123DM0==null){P123DM0="0"; 
updatestring+=" P123DM0='0' ,";
}

P123DF0=conn.rs.getString("P123DF0");
if(P123DF0==null){P123DF0="0";
updatestring+=" P123DF0='0' ,";
}



P123DM15=conn.rs.getString("P123DM15");
if(P123DM15==null){P123DM15="0"; 
updatestring+=" P123DM15='0' ,";
}

P123DF15=conn.rs.getString("P123DF15");
if(P123DF15==null){P123DF15="0";
updatestring+=" P123DF15='0' ,";
}



P123DM25=conn.rs.getString("P123DM25");
if(P123DM25==null){P123DM25="0";
updatestring+=" P123DM25='0' ,";
}

P123DF25=conn.rs.getString("P123DF25");
if(P123DF25==null){P123DF25="0"; 
updatestring+=" P123DF25='0' ,";
}


P123DMT=conn.rs.getString("P123DMT");
if(P123DMT==null){P123DMT="0"; 
updatestring+=" P123DMT='0' ,";
}

P123DFT=conn.rs.getString("P123DFT");
if(P123DFT==null){P123DFT="0"; 
updatestring+=" P123DFT='0' ,";
}


P123DTT=conn.rs.getString("P123DTT");
if(P123DTT==null){P123DTT="0";
updatestring+=" P123DTT='0' ,";
}
     

//====================================================================p124

P124DM0=conn.rs.getString("P124DM0");
if(P124DM0==null){P124DM0="0";
updatestring+=" P124DM0='0' ,";
}

P124DF0=conn.rs.getString("P124DF0");
if(P124DF0==null){P124DF0="0"; 
updatestring+=" P124DF0='0' ,";
}



P124DM15=conn.rs.getString("P124DM15");
if(P124DM15==null){P124DM15="0"; 
updatestring+=" P124DM15='0' ,";
}

P124DF15=conn.rs.getString("P124DF15");
if(P124DF15==null){P124DF15="0"; 
updatestring+=" P124DF15='0' ,";
}



P124DM25=conn.rs.getString("P124DM25");
if(P124DM25==null){P124DM25="0"; 
updatestring+=" P124DM25='0' ,";
}

P124DF25=conn.rs.getString("P124DF25");
if(P124DF25==null){P124DF25="0"; 
updatestring+=" P124DF25='0' ,";
}


P124DMT=conn.rs.getString("P124DMT");
if(P124DMT==null){P124DMT="0"; 
updatestring+=" P124DMT='0' ,";

}

P124DFT=conn.rs.getString("P124DFT");
if(P124DFT==null){P124DFT="0"; 
updatestring+=" P124DFT='0' ,";
}


P124DTT=conn.rs.getString("P124DTT");
if(P124DTT==null){P124DTT="0";
updatestring+=" P124DTT='0' ,";
}


//=========================================================GEND_GBV

GEND_GBV9M=conn.rs.getString("GEND_GBV9M");
if(GEND_GBV9M==null){GEND_GBV9M="0"; 
updatestring+=" GEND_GBV9M='0' ,";
}

GEND_GBV9F=conn.rs.getString("GEND_GBV9F");
if(GEND_GBV9F==null){GEND_GBV9F="0";
updatestring+=" GEND_GBV9F='0' ,";
}

GEND_GBV9=conn.rs.getString("GEND_GBV9");
if(GEND_GBV9==null){GEND_GBV9="0"; 
updatestring+=" GEND_GBV9='0' ,";
}

//============
GEND_GBV14M=conn.rs.getString("GEND_GBV14M");
if(GEND_GBV14M==null){GEND_GBV14M="0"; 
updatestring+=" GEND_GBV14M='0' ,";
}

GEND_GBV14F=conn.rs.getString("GEND_GBV14F");
if(GEND_GBV14F==null){GEND_GBV14F="0";
updatestring+=" GEND_GBV14F='0' ,";
}

GEND_GBV14=conn.rs.getString("GEND_GBV14");
if(GEND_GBV14==null){GEND_GBV14="0"; 
updatestring+=" GEND_GBV14='0' ,";
}
//=======


//============
GEND_GBV17M=conn.rs.getString("GEND_GBV17M");
if(GEND_GBV17M==null){GEND_GBV17M="0"; 
updatestring+=" GEND_GBV17M='0' ,";

}

GEND_GBV17F=conn.rs.getString("GEND_GBV17F");
if(GEND_GBV17F==null){GEND_GBV17F="0"; 
updatestring+=" GEND_GBV17F='0' ,";
}

GEND_GBV17=conn.rs.getString("GEND_GBV17");
if(GEND_GBV17==null){GEND_GBV17="0";
updatestring+=" GEND_GBV17='0' ,";
}
//=======

//============
GEND_GBV24M=conn.rs.getString("GEND_GBV24M");
if(GEND_GBV24M==null){GEND_GBV24M="0";
updatestring+=" GEND_GBV24M='0' ,";
}

GEND_GBV24F=conn.rs.getString("GEND_GBV24F");
if(GEND_GBV24F==null){GEND_GBV24F="0"; 
updatestring+=" GEND_GBV24F='0' ,";
}

GEND_GBV24=conn.rs.getString("GEND_GBV24");
if(GEND_GBV24==null){GEND_GBV24="0"; 
updatestring+=" GEND_GBV24='0' ,";

}
//=======


//============
GEND_GBV25M=conn.rs.getString("GEND_GBV25M");
if(GEND_GBV25M==null){GEND_GBV25M="0"; 
updatestring+=" GEND_GBV25M='0' ,";
}

GEND_GBV25F=conn.rs.getString("GEND_GBV25F");
if(GEND_GBV25F==null){GEND_GBV25F="0"; 
updatestring+=" GEND_GBV25F='0' ,";
}

GEND_GBV25=conn.rs.getString("GEND_GBV25");
if(GEND_GBV25==null){GEND_GBV25="0"; 

updatestring+=" GEND_GBV25='0' ,";
}
//=======

//============
GEND_GBVM=conn.rs.getString("GEND_GBVM");
if(GEND_GBVM==null){GEND_GBVM="0"; 
updatestring+=" GEND_GBVM='0' ,";
}

GEND_GBVF=conn.rs.getString("GEND_GBVF");
if(GEND_GBVF==null){GEND_GBVF="0";
updatestring+=" GEND_GBVF='0' ,";
}

GEND_GBV=conn.rs.getString("GEND_GBV");
if(GEND_GBV==null){GEND_GBV="0"; 
updatestring+=" GEND_GBV='0' ,";
}
   


}

String updqr="update gender set  ";

if(!updatestring.equals("")){
updqr+=" "+updatestring+" isValidated='1'";
}
else {
updqr+=" isValidated='1'";
}

updqr+=" where tableid='"+tableid+"'";

            System.out.println("__update qr  "+updqr);

if(conn.st.executeUpdate(updqr)==1){

session.setAttribute("genderresponse", "<font color=\"green\"> <b>Gender Form validated Successfully!</b></font>");

int monthDiff=0;
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM gender WHERE tableid='"+tableid+"'";
     conn.rs1=conn.st1.executeQuery(getMonths);
     if(conn.rs1.next()==true){
         System.out.println("months are : "+conn.rs1.getString(1));
    monthDiff=conn.rs1.getInt(1);
     }
     if(monthDiff>0){
//      UPDATE THE COLUMN   
       java.util.Date date= new java.util.Date();
Timestamp lastUpdatedOn =new Timestamp(date.getTime()); 

     String updateLast="UPDATE gender SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE tableid='"+tableid+"'" ;   
       conn.st2.executeUpdate(updateLast);
     }
     


}


 
         if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}       
         if(conn.st!=null){ conn.st.close();}
         if(conn.st2!=null){ conn.st2.close();} 
    
    
 
}       catch (SQLException ex) {
            Logger.getLogger(validateGender.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
           response.sendRedirect("loadGender.jsp");
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
