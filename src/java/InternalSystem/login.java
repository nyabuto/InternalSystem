/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalSystem;

import General.IdGenerator2;
import database.dbConn;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class login extends HttpServlet {
HttpSession session;
String username,password,fname,mname,lname,userid,level,pass,fullname,status,nextPage,email,ipv4ad,useractive;
MessageDigest m;
String userAccess;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, SQLException {
      session=request.getSession();
          dbConn conn = new dbConn();
         //conn.st.executeUpdate("Set GLOBAL  max_connections=6000");
           IdGenerator2 ig= new IdGenerator2();
          username=password=fname=mname=lname=userid=level=pass=fullname=status=nextPage=ipv4ad=useractive="";
          userAccess=",";
          
          
          if(request.getParameter("username")!=null){ username=request.getParameter("username").trim();}
          if(request.getParameter("password")!=null){pass=request.getParameter("password").trim();}
          if(request.getParameter("ipv4ad")!=null){ ipv4ad=request.getParameter("ipv4ad").trim();}
          
          
          
          //ipv4ad
           HashMap<String, String> kd= new HashMap<>();


if(isMaxLimitsReached(conn, username, 5)){// denie user login
    
    
     status="<font color=\"red\"><b>Failed:</b> You have entered incorrect Password too many times. Please Try login again after 15 minutes!.</font>";
             nextPage="index.jsp";
             session.setAttribute("login", status);
}

else {
    //proceed
        
         
          System.out.println("username : "+username+" password : "+pass);
          m = MessageDigest.getInstance("MD5");
       m.update(pass.getBytes(), 0, pass.length());
       password = new BigInteger(1, m.digest()).toString(16);
        System.out.println("username : "+username+" password : "+password);  
        String logger="SELECT userid,fname,mname,lname,level,"
       + "access_reports,access_maintenance,access_moh711,access_moh731,"
                + "access_tb,access_gender,access_nutrition,access_kmmp,access_vmmc,access_uploads,gapanalysis,access_hts,access_art,access_pmtct,access_form1a,readonly_form1a,access_checklist,email,isactive " 
                + " FROM user WHERE username=? && password=?" ;
        conn.pst=conn.conn.prepareStatement(logger);
        conn.pst.setString(1, username);
        conn.pst.setString(2, password);
         conn.rs=conn.pst.executeQuery();
         if(conn.rs.next()==true)
         {
             userid=conn.rs.getString(1);
             fname=conn.rs.getString(2);
             mname=conn.rs.getString(3);
             lname=conn.rs.getString(4);
             level=conn.rs.getString(5);
             email=conn.rs.getString("email");
             useractive=conn.rs.getString("isactive");
             fullname=fname+" "+mname+" "+lname;
             session.setAttribute("userid", userid);
             session.setAttribute("fullname", fullname);
             session.setAttribute("level", level);
             
             session.setAttribute("username", username);
             session.setAttribute("fname", fname);
             session.setAttribute("mname", mname);
             session.setAttribute("lname", lname);
             session.setAttribute("email", email);
             
             
             
             kd.put("userid", userid);
             kd.put("fullname", fullname);
             kd.put("level", level);
             kd.put("username", username);
             kd.put("fname", fname);
             kd.put("mname", mname);
             kd.put("lname", lname);
             kd.put("email", email);
             kd.put("password", pass);
             
             
            
             
         
           
             updateuserlogin(conn,username, ipv4ad ,session.getId(), 0,""+ig.timestamp());
             
             if(conn.rs.getInt("access_reports")==1){userAccess+="reports,";}
             if(conn.rs.getInt("access_maintenance")==1){userAccess+="maintenance,";}
             if(conn.rs.getInt("access_moh711")==1){userAccess+="moh711,";}
             if(conn.rs.getInt("access_moh731")==1){userAccess+="moh731,";}
             if(conn.rs.getInt("access_tb")==1){userAccess+="tb,";}
             if(conn.rs.getInt("access_gender")==1){userAccess+="gender,";}
             if(conn.rs.getInt("access_nutrition")==1){userAccess+="nutrition,";}
             if(conn.rs.getInt("access_kmmp")==1){userAccess+="kmmp,";}
             if(conn.rs.getInt("access_vmmc")==1){userAccess+="vmmc,";}
             if(conn.rs.getInt("access_uploads")==1){userAccess+="uploads,";}
             if(conn.rs.getInt("access_hts")==1){userAccess+="hts,";}
             if(conn.rs.getInt("access_art")==1){userAccess+="art,";}
             if(conn.rs.getInt("access_pmtct")==1){userAccess+="pmtct,";}
             if(conn.rs.getInt("access_form1a")==1){userAccess+="form1a,";}
             if(conn.rs.getInt("access_checklist")==1){userAccess+="checklist,";}
             
             
             session.setAttribute("access_gapanalysis",conn.rs.getInt("gapanalysis"));
             session.setAttribute("f1a_readonly",conn.rs.getInt("readonly_form1a"));
         
             
           session.setAttribute("userAccess", userAccess);  
           status="success"; 
            if(useractive.equals("0")){
                nextPage="index.jsp";
            
            status="<font color=\"red\"><b>Failed:</b> This account was disabled by the Administrator.</font>";
            }
           else if(level.equals("4")){
                 session.setAttribute("kd_session", kd); 
               nextPage="rmcahdashboards.jsp";}
           
           else {
                 session.setAttribute("kd_session", kd); 
           nextPage="imishome.jsp";
           }
          
           System.out.println("account active : "+useractive);
         }
         else
         {
              updateuserlogin(conn,username, ipv4ad ,session.getId(), 1,"");
             
             status="<font color=\"red\"><b>Failed:</b> Wrong username and password combination.</font>";
             nextPage="index.jsp";
             session.setAttribute("login", status);
         }
         
         
    }
         
         
         System.out.println("Next Page is :  "+nextPage);
         if(conn.conn!=null){
 conn.conn.close();
         }
         
         if(conn.rs!=null){
         conn.rs.close();
         }
         
         if(conn.pst!=null){
         conn.pst.close();
         }
         if(conn.conn!=null){
         conn.conn.close();
         }
         response.sendRedirect(nextPage);
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
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
   public void updateuserlogin(dbConn conn,String uname, String ipadress ,String sessionid, int wrong_attempt_no,String succesfullogin ) throws SQLException
   {
       
       
       IdGenerator2 ig= new IdGenerator2();
       
       String tm=ig.timestamp();
       
       
       String loginattemptqry=" select wrongattempts,lastsuccesfullogin from loginlogs where username='"+uname+"'" ;
       
       int totalwrongattempts=0;
       int prev_attempt_no=0;
       String lastsuccesfullogin="";
       
       conn.rs4=conn.st4.executeQuery(loginattemptqry);
       
       if(conn.rs4.next())
       {
       prev_attempt_no=conn.rs4.getInt(1);
       lastsuccesfullogin=conn.rs4.getString(2);
       
       }
       
       
       if(succesfullogin.equals("")){succesfullogin=lastsuccesfullogin;}
       //if the current login is succesful, meaning wrong attempt is 0, user will be categorized as having no worng attempts, regardless of the previous wrong attempts
       
       if(wrong_attempt_no==0){totalwrongattempts=0;} 
       else 
       {           
           //increment previous wrong attempts by 1
           totalwrongattempts=prev_attempt_no+1;
           
       }
       String upd="replace into loginlogs (username,wrongattempts,ipadress,lastsuccesfullogin,lastloginattempt) value (?,?,?,?,?)";
       
        conn.pst3=conn.conn.prepareStatement(upd);
        
        conn.pst3.setString(1,uname);
        conn.pst3.setString(2,""+totalwrongattempts);
        conn.pst3.setString(3,ipadress);
        conn.pst3.setString(4,succesfullogin);
        conn.pst3.setString(5,tm);
        
         conn.rs3=conn.pst3.executeQuery();
       
   
   } 
   
   
   public boolean isMaxLimitsReached(dbConn conn, String uname, int wrongattemptsthreshold) throws SQLException
   {
       IdGenerator2 ig= new IdGenerator2();
       
       
       boolean reachedlimits=false;
       
       String maxlimits="select wrongattempts, TIMESTAMPDIFF(MINUTE, substring(lastloginattempt,1,19), '"+ig.timestamp().substring(0, 19)+"') AS minutes from loginlogs where username='"+uname+"' and wrongattempts >='"+wrongattemptsthreshold+"' and TIMESTAMPDIFF(MINUTE, lastloginattempt, '"+ig.timestamp()+"') <15 ";
       
       System.out.println("max Limit query: "+maxlimits);
       conn.rs=conn.st.executeQuery(maxlimits);
       
       if(conn.rs.next())
       {
           
         reachedlimits=true;  
           
       }
       
              
    return reachedlimits;
   
       
   
   }
   
    
   

}
