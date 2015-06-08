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
public class validateNutrition extends HttpServlet {

         
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

        
    String updqr1="update nutrition set ";


   
         
         
    String SMCHCCNtrTM=request.getParameter("MCHCCNtrTM");
    updqr1+="MCHCCNtrTM="+SMCHCCNtrTM +", ";
    
    String SMCHCCNtrTF=request.getParameter("MCHCCNtrTF");
    updqr1+="MCHCCNtrTF="+SMCHCCNtrTF +", ";
    
    
    String SC51DCM=request.getParameter("C51DCM");
    updqr1+="C51DCM="+SC51DCM +", ";

    String SC51DCF=request.getParameter("C51DCF");
    updqr1+="C51DCF="+SC51DCF +", ";
    
    String SMCHNtrnFoodOVC=request.getParameter("MCHNtrnFoodOVC");
    updqr1+="MCHNtrnFoodOVC="+SMCHNtrnFoodOVC +", ";
    
    String SMCHNtrnFoodPLHIV=request.getParameter("MCHNtrnFoodPLHIV");
    updqr1+="MCHNtrnFoodPLHIV="+SMCHNtrnFoodPLHIV +", ";
    
    
    updqr1+=" isValidated='0' where  tableid='"+tableid+"'";

    conn.st.executeUpdate(updqr1);
        
        
      String getexistingdata="select * from nutrition where tableid='"+tableid+"'";
        
        
        
    String MCHCCNtrTM="";
    String MCHCCNtrTF="";
    String MCHCCNtrTT="";
    String MCHNtrnCHWTrain="";
    String MCHNutChRch="";
    String MCHNtrnWasted="";
    String MCHNtrnUnderweight="";
    String MCHChild5D="";
    String MCHNtrnHealthFacility="";
    String MCHVaccVitA="";
    String MCHNtrnFoodOVC="";
    String MCHNtrnFoodPLHIV="";
    String MCHNtrnFood="";
    String C51DCM="";
    String C51DCF="";
    String C51DC="";
    String C51DAM="";
    String C51DAF="";
    String C51DA="";
    String C51DP="";
    String C51DMT="";
    String C51DFT="";
    String C51DT="";
    
    String MCHCCNtrTMC="";
    String MCHCCNtrTFC="";
    String MCHCCNtrTTC="";

        
       
    String updatestring="";
        
        conn.rs=conn.st.executeQuery(getexistingdata);
        while(conn.rs.next()){
        
            MCHCCNtrTMC=conn.rs.getString("MCHCCNtrTMC");
    if(MCHCCNtrTMC==null){MCHCCNtrTMC="";
        updatestring+=" MCHCCNtrTMC='0' ,";
    }
         
     MCHCCNtrTFC=conn.rs.getString("MCHCCNtrTFC");
    if(MCHCCNtrTFC==null){MCHCCNtrTFC="";
        updatestring+=" MCHCCNtrTFC='0' ,";
    }
    
    
     MCHCCNtrTTC=conn.rs.getString("MCHCCNtrTTC");
    if(MCHCCNtrTTC==null){MCHCCNtrTTC="";
        updatestring+=" MCHCCNtrTTC='0' ,";
    }
            
            //now load the column values here
           
    MCHCCNtrTM=conn.rs.getString("MCHCCNtrTM");
    if(MCHCCNtrTM==null){MCHCCNtrTM="";
        updatestring+=" MCHCCNtrTM='0' ,";
    }

    MCHCCNtrTF=conn.rs.getString("MCHCCNtrTF");
    if(MCHCCNtrTF==null){MCHCCNtrTF="";
        updatestring+=" MCHCCNtrTF='0' ,";
    }

    MCHCCNtrTT=conn.rs.getString("MCHCCNtrTT");
    if(MCHCCNtrTT==null){MCHCCNtrTT="";
        updatestring+=" MCHCCNtrTT='0' ,";
    }

    MCHNtrnCHWTrain=conn.rs.getString("MCHNtrnCHWTrain");
    if(MCHNtrnCHWTrain==null){MCHNtrnCHWTrain="";
        updatestring+=" MCHNtrnCHWTrain='0' ,";
    }

    MCHNutChRch=conn.rs.getString("MCHNutChRch");
    if(MCHNutChRch==null){MCHNutChRch="";
        updatestring+=" MCHNutChRch='0' ,";
    }

    MCHNtrnWasted=conn.rs.getString("MCHNtrnWasted");
    if(MCHNtrnWasted==null){MCHNtrnWasted="";
        updatestring+=" MCHNtrnWasted='0' ,";
    }

    MCHNtrnUnderweight=conn.rs.getString("MCHNtrnUnderweight");
    if(MCHNtrnUnderweight==null){MCHNtrnUnderweight="";
        updatestring+=" MCHNtrnUnderweight='0' ,";
    }

    MCHChild5D=conn.rs.getString("MCHChild5D");
    if(MCHChild5D==null){MCHChild5D="";
        updatestring+=" MCHChild5D='0' ,";
    }

    MCHNtrnHealthFacility=conn.rs.getString("MCHNtrnHealthFacility");
    if(MCHNtrnHealthFacility==null){MCHNtrnHealthFacility="";
        updatestring+=" MCHNtrnHealthFacility='0' ,";
    }

    MCHVaccVitA=conn.rs.getString("MCHVaccVitA");
    if(MCHVaccVitA==null){MCHVaccVitA="";
        updatestring+=" MCHVaccVitA='0' ,";
    }

    MCHNtrnFoodOVC=conn.rs.getString("MCHNtrnFoodOVC");
    if(MCHNtrnFoodOVC==null){MCHNtrnFoodOVC="";
        updatestring+=" MCHNtrnFoodOVC='0' ,";
    }
            
    MCHNtrnFoodPLHIV=conn.rs.getString("MCHNtrnFoodPLHIV");
    if(MCHNtrnFoodPLHIV==null){MCHNtrnFoodPLHIV="";
        updatestring+=" MCHNtrnFoodPLHIV='0' ,";
    }
            
    MCHNtrnFood=conn.rs.getString("MCHNtrnFood");
    if(MCHNtrnFood==null){MCHNtrnFood="";
        updatestring+=" MCHNtrnFood='0' ,";
    }
            
    C51DCM=conn.rs.getString("C51DCM");
    if(C51DCM==null){C51DCM="";
        updatestring+=" C51DCM='0' ,";
    }
            
    C51DCF=conn.rs.getString("C51DCF");
    if(C51DCF==null){C51DCF="";
        updatestring+=" C51DCF='0' ,";
    }
            
    C51DC=conn.rs.getString("C51DC");
    if(C51DC==null){C51DC="";
        updatestring+=" C51DC='0' ,";
    }
            
    C51DAM=conn.rs.getString("C51DAM");
    if(C51DAM==null){C51DAM="";
        updatestring+=" C51DAM='0' ,";
    }
            
    C51DAF=conn.rs.getString("C51DAF");
    if(C51DAF==null){C51DAF="";
        updatestring+=" C51DAF='0' ,";
    }
            
    C51DA=conn.rs.getString("C51DA");
    if(C51DA==null){C51DA="";
        updatestring+=" C51DA='0' ,";
    }
            
    C51DP=conn.rs.getString("C51DP");
    if(C51DP==null){C51DP="";
        updatestring+=" C51DP='0' ,";
    }
            
    C51DMT=conn.rs.getString("C51DMT");
    if(C51DMT==null){C51DMT="";
        updatestring+=" C51DMT='0' ,";
    }
            
    C51DFT=conn.rs.getString("C51DFT");
    if(C51DFT==null){C51DFT="";
        updatestring+=" C51DFT='0' ,";
    }
            
    C51DT=conn.rs.getString("C51DT");
    if(C51DT==null){C51DT="";
        updatestring+=" C51DT='0' ,";
    }
            
        }//end of while 
        
         String updqr="update nutrition set  ";

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
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM nutrition WHERE tableid='"+tableid+"'";
     conn.rs1=conn.st1.executeQuery(getMonths);
     if(conn.rs1.next()==true){
         System.out.println("months are : "+conn.rs1.getString(1));
    monthDiff=conn.rs1.getInt(1);
     }
     if(monthDiff>0){
//      UPDATE THE COLUMN   
       java.util.Date date= new java.util.Date();
Timestamp lastUpdatedOn =new Timestamp(date.getTime()); 

     String updateLast="UPDATE nutrition SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE tableid='"+tableid+"'" ;   
       conn.st2.executeUpdate(updateLast);
     }
     
    
    
session.setAttribute("nutritionresponse", "<font color=\"green\"><b>Nutrition Form validated Successfully!</b></font>");

}
        
        
        if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}       
         if(conn.st!=null){ conn.st.close();}
         if(conn.st2!=null){ conn.st2.close();} 
        
        
        } catch (SQLException ex) {
            Logger.getLogger(validateNutrition.class.getName()).log(Level.SEVERE, null, ex);
        }
    
     response.sendRedirect("loadNutrition.jsp");   
        
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
