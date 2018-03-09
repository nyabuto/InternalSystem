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
 * @author GNyabuto
 */
public class validatenew731 extends HttpServlet {
HttpSession session;
String facilityID,year,month,userid,tableid;
String [] labels =  {"HV0101","HV0102","HV0103","HV0104","HV0105","HV0106","HV0107","HV0108","HV0109","HV0110","HV0111","HV0112","HV0113","HV0114","HV0115","HV0116","HV0117","HV0118","HV0119","HV0120","HV0121","HV0122","HV0123","HV0124","HV0125","HV0126","HV0127","HV0128","HV0129","HV0130","HV0131","HV0132","HV0133","HV0134","HV0135","HV0136","HV0137","HV0138","HV0139","HV0140","HV0141","HV0142","HV0143","HV0144","HV0145","HV0146","HV0147","HV0148","HV0149","HV0150","HV0201","HV0202","HV0203","HV0204","HV0205","HV0206","HV0207","HV0208","HV0209","HV0210","HV0211","HV0212","HV0213","HV0214","HV0215","HV0216","HV0217","HV0218","HV0219","HV0220","HV0221","HV0222","HV0223","HV0224","HV0225","HV0226","HV0227","HV0228","HV0229","HV0230","HV0231","HV0232","HV0233","HV0234","HV0235","HV0236","HV0237","HV0238","HV0239","HV0240","HV0241","HV0242","HV0243","HV0244","HV0245","HV0246","HV0247","HV0248","HV0249","HV0250","HV0251","HV0252","HV0253","HV0254","HV0255","HV0256","HV0257","HV0258","HV0259","HV03001","HV03002","HV03003","HV03004","HV03005","HV03006","HV03007","HV03008","HV03009","HV03010","HV03011","HV03012","HV03013","HV03014","HV03015","HV03016","HV03017","HV03018","HV03019","HV03020","HV03021","HV03022","HV03023","HV03024","HV03025","HV03026","HV03027","HV03028","HV03029","HV03030","HV03031","HV03032","HV03033","HV03034","HV03035","HV03036","HV03037","HV03038","HV03039","HV03040","HV03041","HV03042","HV03043","HV03044","HV03045","HV03046","HV03047","HV03048","HV03049","HV03050","HV03051","HV03052","HV03053","HV03054","HV03055","HV03056","HV03057","HV03058","HV03059","HV03060","HV03061","HV03062","HV03063","HV03064","HV03065","HV03066","HV03067","HV03068","HV03069","HV03070","HV03071","HV03072","HV03073","HV03074","HV03075","HV03076","HV03077","HV03078","HV03079","HV03080","HV03081","HV03082","HV03083","HV03084","HV03085","HV03086","HV03087","HV03088","HV03089","HV0401","HV0402","HV0403","HV0404","HV0405","HV0406","HV0407","HV0408","HV0409","HV0410","HV0411","HV0412","HV0413","HV0414","HV0415","HV0416","HV0417","HV0501","HV0502","HV0503","HV0505","HV0506","HV0507","HV0601","HV0602","HV0603","HV0604"};
String query = "",value,yearmonth;
int quarter;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

      
String data_elements,description;

        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
            session=request.getSession();
            
           if(session.getAttribute("year")!=null){        
   year=session.getAttribute("year").toString();
    }
      if(session.getAttribute("monthid")!=null){        
   month=session.getAttribute("monthid").toString();
    }
   
        if(session.getAttribute("facilityid")!=null){        
   facilityID=session.getAttribute("facilityid").toString();
    }
       
    tableid=year+"_"+month+"_"+facilityID; 

        
        yearmonth="";
        quarter= 0;
        String tempmonth=month;
        int pepfaryear=Integer.parseInt(year);
        if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
        else {pepfaryear--;}


        if(Integer.parseInt(month)>=10 && Integer.parseInt(month)<=12){
            quarter=1;
        }
        else if(Integer.parseInt(month)>=1 && Integer.parseInt(month)<=3){
            quarter=2;
        }
        else if(Integer.parseInt(month)>=4 && Integer.parseInt(month)<=6){
            quarter=3;
        }
        else if(Integer.parseInt(month)>=7 && Integer.parseInt(month)<=9){
            quarter=4;
        }

        yearmonth=pepfaryear+""+tempmonth;


 data_elements=request.getParameter("data_elements");
 description=request.getParameter("description");
 
//HV0110=HV0101+HV0102+HV0103+HV0104+HV0105+HV0106+HV0107+HV0108+HV0109;
//HV0126=HV0117+HV0118+HV0118+HV0120+HV0121+HV0122+HV0123+HV0124+HV0125;
//HV0135=HV0130+HV0131+HV0132+HV0133+HV0134;
//
//HV0207=HV0203+HV0204+HV0205+HV0206;
//HV0214=HV0210+HV0211+HV0212+HV0213;
//HV0220=HV0216+HV0217+HV0218+HV0219;
//HV0232=HV0229+HV0230+HV0231;
//HV0241=HV0238+HV0239+HV0240;
//
//HV03011=HV03001+HV03002+HV03003+HV03004+HV03005+HV03006+HV03007+HV03008+HV03009+HV03010;
//HV03015=HV03013+HV03014;
//HV03026=HV03016+HV03017+HV03018+HV03019+HV03020+HV03021+HV03022+HV03023+HV03024+HV03025;
//HV03038=HV03028+HV03029+HV03030+HV03031+HV03032+HV03033+HV03034+HV03035+HV03036+HV03037;
//HV03050=HV03044+HV03045+HV03046+HV03047+HV03048+HV03049;
//HV03057=HV03051+HV03052+HV03053+HV03054+HV03055+HV03056;
//HV03065=HV03059+HV03060+HV03061+HV03062+HV03063+HV03064;
//HV03069=HV03067+HV03068;
//HV03072=HV03070+HV03071;
//HV03075=HV03073+HV03074;
//HV03079=HV03077+HV03078;
//HV03081=HV03077+HV03080;
//HV03084=HV03082+HV03083;
//
//HV0407=HV0401+HV0402+HV0403+HV0404+HV0405+HV0406;
//
//HV0503=HV0501+HV0502;
//HV0506=HV0504+HV0505;
if(userid==null){
    userid="";
}
query = "REPLACE INTO moh731_new  set SubPartnerID='"+facilityID+"',Annee='"+year+"',Mois='"+month+"', id='"+tableid+"', user_id='"+userid+"', yearmonth='"+yearmonth+"',quarter='"+quarter+"',isValidated=1";
for (String label:labels){
    value = request.getParameter(label);
    if(value!=null){
        if(!value.equals("")){
          query += ","+label+"='"+value+"'";  
        }
        else{
            value=null;
            query += ","+label+"="+value;
        }
            }
    else{
     query += ","+label+"="+value;   
    }
}

    System.out.println("query : "+query);
    conn.st.executeUpdate(query);

     session.setAttribute("validate731", "<font color=\"green\"><b>New Form MOH 731 Validated Successfully.</b></font>");
    int monthDiff=0;
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM moh731_new WHERE id='"+tableid+"'";
     conn.rs1=conn.st1.executeQuery(getMonths);
     if(conn.rs1.next()==true){
         System.out.println("months are : "+conn.rs1.getString(1));
    monthDiff=conn.rs1.getInt(1);
     }
     if(monthDiff>0){
//      UPDATE THE COLUMN   
       java.util.Date date= new java.util.Date();
Timestamp lastUpdatedOn =new Timestamp(date.getTime()); 
userid=session.getAttribute("userid").toString();
     String updateLast="UPDATE moh731_new SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE id='"+tableid+"'" ;   
       conn.st2.executeUpdate(updateLast);
     }
    
     
     String dqaid="";
      System.out.println("++++++++++++++++++++++++++++++++here++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("errors : "+data_elements+"   descr : "+description);
      String checker="SELECT id FROM dqa WHERE year='"+year+"' && month='"+month+"' && facilityid='"+facilityID+"' && form='moh731_new' LIMIT 1";
     conn.rs=conn.st.executeQuery(checker);
     if(conn.rs.next()==true){
        dqaid=conn.rs.getString(1);
        
        if(!data_elements.equals("")){
//           UPDATE DQA TABLE
             System.out.println("to update");
            String updator="UPDATE dqa SET columns=?, errors=? WHERE id=? ";
            conn.pst=conn.conn.prepareStatement(updator);
            conn.pst.setString(1, data_elements);
            conn.pst.setString(2, description);
            conn.pst.setString(3, dqaid);
            conn.pst.executeUpdate();
        }
        else{
         System.out.println("to delete");
            String updator="DELETE FROM dqa WHERE id=? ";
            conn.pst=conn.conn.prepareStatement(updator);
            conn.pst.setString(1, dqaid);
           
            conn.pst.executeUpdate();    
        }
     }
     else{
         
    if(!data_elements.equals("")) {
      System.out.println("to insert");
    String inserter="INSERT INTO dqa (tableid,form,facilityid,year,month,columns,errors) VALUES(?,?,?,?,?,?,?)";
    conn.pst=conn.conn.prepareStatement(inserter);
    conn.pst.setString(1, tableid);
    conn.pst.setString(2, "moh731_new");
    conn.pst.setString(3, facilityID);
    conn.pst.setString(4, year);
    conn.pst.setString(5, month);
    conn.pst.setString(6, data_elements);
    conn.pst.setString(7, description);
    
    conn.pst.executeUpdate();
    }    
         
     } 
     
     
     if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.pst!=null){conn.pst.close();}
     if(conn.conn!=null){conn.conn.close();}
     
     response.sendRedirect("loadnew731.jsp");
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
        Logger.getLogger(validatenew731.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(validatenew731.class.getName()).log(Level.SEVERE, null, ex);
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
