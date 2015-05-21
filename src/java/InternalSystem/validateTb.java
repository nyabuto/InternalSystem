/*
 * To change this template, choose Tools | Templates
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

/**
 *
 * @author Maureen
 */
public class validateTb extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    HttpSession session=null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
         String C31D="";
String TB_STATN="";
String TB_STATD="";
String TB_STATP="";
String TB_IPTN="";
String TB_IPTD="";
String TB_IPTP="";
String TB_IPT1="";
String TB_IPT4="";
String TB_IPT9="";
String TB_IPT14="";
String TB_IPT19="";
String TB_IPT20="";
String TB_IPTM="";
String TB_IPTF="";
String TB_SCREENN="";
String TB_SCREEND="";
String TB_SCREENP="";
String TB_OUTCOME="";
String CARPCTHTMPR="";
String CARPCTHTFPR="";
String CARPCTHTTPR=""; 

  session =request.getSession();
        
    
//    String col=request.getParameter("col");
//    String achieved=request.getParameter("achieved");
//    
    
    dbConn conn=new dbConn();
//get the existing data for the month, year and facility that is already on session

String month="";      
String year="";      
String facil="";
String userid="unknown";
 String error="";

//        if(achieved.equals("")){achieved="0";}
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
String checker="select * from TB WHERE id=?" ;
          conn.pst=conn.conn.prepareStatement(checker);
          conn.pst.setString(1, tableid);
          conn.rs=conn.pst.executeQuery();
          
          if(conn.rs.next()==true){


TB_STATN=conn.rs.getString("TB_STATN");
if(TB_STATN==null){TB_STATN="0"; }

TB_STATD=conn.rs.getString("TB_STATD");
if(TB_STATD==null){TB_STATD="0"; }

TB_STATP=conn.rs.getString("TB_STATP");
if(TB_STATP==null){TB_STATP="0"; }

TB_IPTN=conn.rs.getString("TB_IPTN");
if(TB_IPTD==null){TB_IPTN="0"; }

TB_IPTD=conn.rs.getString("TB_IPTD");
if(TB_IPTD==null){TB_IPTD="0"; }

TB_IPTP=conn.rs.getString("TB_IPTP");
if(TB_IPTP==null){TB_IPTP="0"; }

TB_IPT1=conn.rs.getString("TB_IPT1");
if(TB_IPT1==null){TB_IPT1="0"; }

TB_IPT4=conn.rs.getString("TB_IPT4");
if(TB_IPT4==null){TB_IPT4="0"; }

TB_IPT9=conn.rs.getString("TB_IPT9");
if(TB_IPT9==null){TB_IPT9="0"; }

TB_IPT14=conn.rs.getString("TB_IPT14");
if(TB_IPT14==null){TB_IPT14="0"; }

TB_IPT19=conn.rs.getString("TB_IPT19");
if(TB_IPT19==null){TB_IPT19="0"; }

TB_IPT20=conn.rs.getString("TB_IPT20");
if(TB_IPT20==null){TB_IPT20="0"; }

TB_IPTM=conn.rs.getString("TB_IPTM");
if(TB_IPTM==null){TB_IPTM="0"; }

TB_IPTF=conn.rs.getString("TB_IPTF");
if(TB_IPTF==null){TB_IPTF="0"; }

TB_SCREENN=conn.rs.getString("TB_SCREENN");
if(TB_SCREENN==null){TB_SCREENN="0"; }
        
TB_SCREEND=conn.rs.getString("TB_SCREEND");
if(TB_SCREEND==null){TB_SCREEND="0"; }
        
TB_SCREENP=conn.rs.getString("TB_SCREENP");
if(TB_SCREENP==null){TB_SCREENP="0"; }
        
TB_OUTCOME=conn.rs.getString("TB_OUTCOME");
if(TB_OUTCOME==null){TB_OUTCOME="0"; }

CARPCTHTMPR=conn.rs.getString("CARPCTHTMPR");
if(CARPCTHTMPR==null){CARPCTHTMPR="0"; }
        
CARPCTHTMPR=conn.rs.getString("CARPCTHTMPR");
if(CARPCTHTMPR==null){CARPCTHTMPR="0"; }
        
CARPCTHTFPR=conn.rs.getString("CARPCTHTFPR");
if(CARPCTHTFPR==null){CARPCTHTFPR="0"; }
        
CARPCTHTTPR=conn.rs.getString("CARPCTHTTPR");
if(CARPCTHTTPR==null){CARPCTHTTPR="0"; }
  
  String runvalidate="update tb set TB_STATN='"+TB_STATN+"',"
          + " TB_STATD='"+TB_STATD+"',"
          + " TB_STATP='"+TB_STATP+"',"
          + " TB_IPTN='"+TB_IPTN+"',"
          + "TB_IPTD='"+TB_IPTD+"',"
          + "TB_IPTP='"+TB_IPTP+"',"
          + " TB_IPT1='"+TB_IPT1+"',"
          + "TB_IPT4='"+TB_IPT4+"', "
          + "TB_IPT9='"+TB_IPT9+"',"
          + "TB_IPT14='"+TB_IPT14+"',"
          + " TB_IPT19 ='"+TB_IPT19+"',"
          + " TB_IPT20='"+TB_IPT20+"',"
          + " TB_IPTM='"+TB_IPTM+"',"
          + " TB_IPTF='"+TB_IPTF+"',"
          + "TB_SCREENN='"+TB_SCREENN+"',"
          + " TB_SCREEND='"+TB_SCREEND+"',"
          + "TB_SCREENP='"+TB_SCREENP+"',"
          + "CARPCTHTMPR='"+CARPCTHTMPR+"',"
          + " CARPCTHTFPR='"+CARPCTHTFPR+"',"
          + "CARPCTHTTPR='"+CARPCTHTTPR+"', isValidated='1' where ID='"+tableid+"'"; 
          System.out.println(runvalidate);
          conn.st.executeUpdate(runvalidate);
          }
 session.setAttribute("validatetb", "<font color=\"green\"><b>TB Form Validated Successfully.</b></font>");
      response.sendRedirect("loadTb.jsp");

        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
            Logger.getLogger(validateTb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
            Logger.getLogger(validateTb.class.getName()).log(Level.SEVERE, null, ex);
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
