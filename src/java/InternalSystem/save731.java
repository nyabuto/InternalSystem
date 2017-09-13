/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalSystem;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
public class save731 extends HttpServlet {
HttpSession session;
String columnName,value;
String facilityID,year,month,userid,tableid;
String error;
String found_db;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           session=request.getSession();
           dbConn conn = new dbConn();
    
     userid="unknown";
     facilityID=year=month="";error="";

    columnName=request.getParameter("columnName").trim();
    value=request.getParameter("value").trim();
    if(value.equals("")){value=null;}
    System.out.println("col : "+columnName+" value : "+value);
    
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
        facilityID=session.getAttribute("facilityid").toString();
    }
    
   found_db=session.getAttribute("found_db").toString();
    
//    year="";
    if(year.equals("") || month.equals("") ||facilityID.equals("") ){error="<font color=\"red\">ERROR : Please select year and facility.</font>";}
    else{
        error="success";
    
    String yearmonth="";
String tempmonth=month;
int pepfaryear=Integer.parseInt(year);
if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
else {pepfaryear--;}

yearmonth=pepfaryear+""+tempmonth;

tableid=year+"_"+month+"_"+facilityID;
  
       
String Insertqr= "insert into moh731_temp  set SubPartnerID='"+facilityID+"',Annee='"+year+"',Mois='"+month+"', "+columnName+"="+value+" , id='"+tableid+"' , user_id='"+userid+"', yearmonth='"+yearmonth+"'";
String updateqr="update moh731_temp set "+columnName+"="+value+",isValidated='0', yearmonth='"+yearmonth+"' where id='"+tableid+"'";
//check whether data for that month, year and facility has been saved
System.out.println("found : "+found_db);
if(found_db.equals("master")){
String fetchMaster="SELECT * FROM moh731 WHERE id=?";
conn.pst=conn.conn.prepareStatement(fetchMaster);
conn.pst.setString(1, tableid);
conn.rs=conn.pst.executeQuery();
if(conn.rs.next()){
    saveResultset(conn,conn.rs);
}

found_db="temp";
}

if(found_db.equals("temp")){
    
 conn.st.executeUpdate(updateqr);
// System.out.println("~~ "+updateqr);
}

else if(found_db.equals("none")){
 int inserted=conn.st.executeUpdate(Insertqr);
 if(inserted==0){
    conn.st.executeUpdate(updateqr);   
 }   
found_db="temp";    
}
else{

}

session.setAttribute("found_db", found_db);

//if(session.getAttribute("table_id")!=null){    
//   conn.st.executeUpdate(updateqr);
//   System.out.println("~~ "+updateqr);  
//}
//
//else{
//     session.setAttribute("table_id", tableid);
//     int inserted=conn.st.executeUpdate(Insertqr);
//    if(inserted==0){
//      conn.st.executeUpdate(updateqr);   
//    }
//}
  //a code to loop through all synced records without a quarter
            //the affected tables are "moh711","moh731","moh711_new","kmmp","gender","tb","vmmc","nutrition"
            AddQuarter am= new AddQuarter();
            am.addQuarter();
            //end of sync last month
    }
     if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.conn!=null){conn.conn.close();}
     
System.out.println("error : "+error);
 out.println(error);
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
        Logger.getLogger(save731.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(save731.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean saveResultset(database.dbConn conn, ResultSet rs) throws SQLException{
       boolean res=false;
       
  String id = rs.getString("id");	
  String  SubPartnerID = rs.getString("SubPartnerID");	
  String Mois = rs.getString("Mois");	
  String Annee =rs.getString("Annee"); 
     
  String HV0101=rs.getString("HV0101");
  String HV0102=rs.getString("HV0102");
  String HV0103=rs.getString("HV0103");
  String HV0105=rs.getString("HV0105");
  String HV0106=rs.getString("HV0106");
  String HV0107=rs.getString("HV0107");
  String HV0108=rs.getString("HV0108");
  String HV0109=rs.getString("HV0109");
  String HV0110=rs.getString("HV0110");
  String HV0111=rs.getString("HV0111");
  String HV0112=rs.getString("HV0112");
  String HV0113=rs.getString("HV0113");
  String HV0114=rs.getString("HV0114");
  String HV0115=rs.getString("HV0115");
  String HV0116=rs.getString("HV0116");
 
String HV0201=rs.getString("HV0201");
String HV0202=rs.getString("HV0202");
String HV0203=rs.getString("HV0203");
String HV0204=rs.getString("HV0204");
String HV0205=rs.getString("HV0205");
String HV0206=rs.getString("HV0206");
String HV0207=rs.getString("HV0207");
String HV0208=rs.getString("HV0208");
String HV0209=rs.getString("HV0209");
String HV0210=rs.getString("HV0210");
String HV0211=rs.getString("HV0211");
String HV0212=rs.getString("HV0212");
String HV0213=rs.getString("HV0213");
String HV0214=rs.getString("HV0214");
String HV0215=rs.getString("HV0215");
String HV0216=rs.getString("HV0216");
String HV0217=rs.getString("HV0217");
String HV0218=rs.getString("HV0218");
String HV0219=rs.getString("HV0219");
String HV0220=rs.getString("HV0220");
String HV0221=rs.getString("HV0221");
String HV0224=rs.getString("HV0224");
String HV0225=rs.getString("HV0225");
String HV0226=rs.getString("HV0226");
String HV0227=rs.getString("HV0227");
String HV0228=rs.getString("HV0228");
String HV0229=rs.getString("HV0229");
String HV0230=rs.getString("HV0230");
String HV0231=rs.getString("HV0231");
String HV0232=rs.getString("HV0232");
String HV0233=rs.getString("HV0233");
String HV0234=rs.getString("HV0234");
String HV0235=rs.getString("HV0235");
String HV0236=rs.getString("HV0236");
String HV0237=rs.getString("HV0237");
String HV0238=rs.getString("HV0238");
String HV0239=rs.getString("HV0239");
String HV0240=rs.getString("HV0240");
String HV0241=rs.getString("HV0241");
String HV0242=rs.getString("HV0242");
String HV0243=rs.getString("HV0243");
String HV0244=rs.getString("HV0244");

String HV0301=rs.getString("HV0301");
String HV0302=rs.getString("HV0302");
String HV0303=rs.getString("HV0303");
String HV0304=rs.getString("HV0304");
String HV0305=rs.getString("HV0305");
String HV0306=rs.getString("HV0306");
String HV0307=rs.getString("HV0307");
String HV0308=rs.getString("HV0308");
String HV0309=rs.getString("HV0309");
String HV0310=rs.getString("HV0310");
String HV0311=rs.getString("HV0311");
String HV0312=rs.getString("HV0312");
String HV0313=rs.getString("HV0313");
String HV0314=rs.getString("HV0314");
String HV0315=rs.getString("HV0315");
String HV0316=rs.getString("HV0316");
String HV0317=rs.getString("HV0317");
String HV0318=rs.getString("HV0318");
String HV0319=rs.getString("HV0319");
String HV0320=rs.getString("HV0320");
String HV0321=rs.getString("HV0321");
String HV0322=rs.getString("HV0322");
String HV0323=rs.getString("HV0323");
String HV0324=rs.getString("HV0324");
String HV0325=rs.getString("HV0325");
String HV0326=rs.getString("HV0326");
String HV0327=rs.getString("HV0327");
String HV0328=rs.getString("HV0328");
String HV0329=rs.getString("HV0329");
String HV0330=rs.getString("HV0330");
String HV0331=rs.getString("HV0331");
String HV0332=rs.getString("HV0332");
String HV0333=rs.getString("HV0333");
String HV0334=rs.getString("HV0334");
String HV0335=rs.getString("HV0335");
String HV0336=rs.getString("HV0336");
String HV0337=rs.getString("HV0337");
String HV0338=rs.getString("HV0338");
String HV0339=rs.getString("HV0339");
String HV0340=rs.getString("HV0340");
String HV0341=rs.getString("HV0341");
String HV0342=rs.getString("HV0342");
String HV0343=rs.getString("HV0343");
String HV0344=rs.getString("HV0344");
String HV0345=rs.getString("HV0345");
String HV0346=rs.getString("HV0346");
String HV0347=rs.getString("HV0347");
String HV0348=rs.getString("HV0348");
String HV0349=rs.getString("HV0349");
String HV0350=rs.getString("HV0350");
String HV0351=rs.getString("HV0351");
String HV0352=rs.getString("HV0352");
String HV0353=rs.getString("HV0353");
String HV0354=rs.getString("HV0354");
String HV0355=rs.getString("HV0355");
String HV0904=rs.getString("HV0904");
String HV0905=rs.getString("HV0905");
String HV0370=rs.getString("HV0370");
String HV0371=rs.getString("HV0371");
String HV0372=rs.getString("HV0372");
String HV0373=rs.getString("HV0373");

String HV0401=rs.getString("HV0401");
String HV0402=rs.getString("HV0402");
String HV0403=rs.getString("HV0403");
String HV0406=rs.getString("HV0406");
String HV0407=rs.getString("HV0407");
String HV0408=rs.getString("HV0408");
String HV0409=rs.getString("HV0409");
String HV0410=rs.getString("HV0410");
String HV0411=rs.getString("HV0411");
String HV0412=rs.getString("HV0412");
String HV0413=rs.getString("HV0413");
String HV0414=rs.getString("HV0414");
String HV0415=rs.getString("HV0415");

String HV0501=rs.getString("HV0501");
String HV0502=rs.getString("HV0502");
String HV0503=rs.getString("HV0503");
String HV0504=rs.getString("HV0504");
String HV0505=rs.getString("HV0505");
String HV0506=rs.getString("HV0506");
String HV0507=rs.getString("HV0507");
String HV0508=rs.getString("HV0508");
String HV0509=rs.getString("HV0509");
String HV0510=rs.getString("HV0510");
String HV0511=rs.getString("HV0511");
String HV0512=rs.getString("HV0512");
String HV0513=rs.getString("HV0513");
String HV0514=rs.getString("HV0514");

String HV0601=rs.getString("HV0601");
String HV0602=rs.getString("HV0602");
String HV0605=rs.getString("HV0605"); 

String user_id = rs.getString("user_id"); 
String timestamp = rs.getString("timestamp"); 	
String isValidated = rs.getString("isValidated"); 	
String yearmonth = rs.getString("yearmonth"); 	
String updatedBy = rs.getString("updatedBy"); 	
String updatedOn = rs.getString("updatedOn"); 	
String isLocked = rs.getString("isLocked"); 	
String quarter = rs.getString("quarter"); 


// INSERT INTO TEMP DB

String copyToTemp="INSERT INTO moh731_temp SET id=?, SubPartnerID=?, Mois=?, Annee=?, HV0101=?, HV0102=?, HV0103=?, HV0105=?, HV0106=?, HV0107=?, HV0108=?, HV0109=?, HV0110=?, HV0111=?, HV0112=?, HV0113=?, HV0114=?, HV0115=?, HV0116=?, HV0201=?, HV0202=?, HV0203=?, HV0204=?, HV0205=?, HV0206=?, HV0207=?, HV0208=?, HV0209=?, HV0210=?, HV0211=?, HV0212=?, HV0213=?, HV0214=?, HV0215=?, HV0216=?, HV0217=?, HV0218=?, HV0219=?, HV0220=?, HV0221=?, HV0224=?, HV0225=?, HV0226=?, HV0227=?, HV0228=?, HV0229=?, HV0230=?, HV0231=?, HV0232=?, HV0233=?, HV0234=?, HV0235=?, HV0236=?, HV0237=?, HV0238=?, HV0239=?, HV0240=?, HV0241=?, HV0242=?, HV0243=?, HV0244=?, HV0301=?, HV0302=?, HV0303=?, HV0304=?, HV0305=?, HV0306=?, HV0307=?, HV0308=?, HV0309=?, HV0310=?, HV0311=?, HV0312=?, HV0313=?, HV0314=?, HV0315=?, HV0316=?, HV0317=?, HV0318=?, HV0319=?, HV0320=?, HV0321=?, HV0322=?, HV0323=?, HV0324=?, HV0325=?, HV0326=?, HV0327=?, HV0328=?, HV0329=?, HV0330=?, HV0331=?, HV0332=?, HV0333=?, HV0334=?, HV0335=?, HV0336=?, HV0337=?, HV0338=?, HV0339=?, HV0340=?, HV0341=?, HV0342=?, HV0343=?, HV0344=?, HV0345=?, HV0346=?, HV0347=?, HV0348=?, HV0349=?, HV0350=?, HV0351=?, HV0352=?, HV0353=?, HV0354=?, HV0355=?, HV0904=?, HV0905=?, HV0370=?, HV0371=?, HV0372=?, HV0373=?, HV0401=?, HV0402=?, HV0403=?, HV0406=?, HV0407=?, HV0408=?, HV0409=?, HV0410=?, HV0411=?, HV0412=?, HV0413=?, HV0414=?, HV0415=?, HV0501=?, HV0502=?, HV0503=?, HV0504=?, HV0505=?, HV0506=?, HV0507=?, HV0508=?, HV0509=?, HV0510=?, HV0511=?, HV0512=?, HV0513=?, HV0514=?, HV0601=?, HV0602=?, HV0605=?, user_id=?, timestamp=?, isValidated=?, yearmonth=?, updatedBy=?, updatedOn=?, isLocked=?, quarter=?";
conn.pst=conn.conn.prepareStatement(copyToTemp);
  conn.pst.setString(1 ,id);	
  conn.pst.setString(2 , SubPartnerID);	
  conn.pst.setString(3 ,Mois);	
  conn.pst.setString(4 ,Annee); 
     
  conn.pst.setString(5 ,HV0101);
  conn.pst.setString(6 ,HV0102);
  conn.pst.setString(7 ,HV0103);
  conn.pst.setString(8 ,HV0105);
  conn.pst.setString(9 ,HV0106);
  conn.pst.setString(10 ,HV0107);
  conn.pst.setString(11 ,HV0108);
  conn.pst.setString(12 ,HV0109);
  conn.pst.setString(13 ,HV0110);
  conn.pst.setString(14 ,HV0111);
  conn.pst.setString(15 ,HV0112);
  conn.pst.setString(16 ,HV0113);
  conn.pst.setString(17 ,HV0114);
  conn.pst.setString(18 ,HV0115);
  conn.pst.setString(19 ,HV0116);
 
conn.pst.setString(20 ,HV0201);
conn.pst.setString(21 ,HV0202);
conn.pst.setString(22 ,HV0203);
conn.pst.setString(23 ,HV0204);
conn.pst.setString(24 ,HV0205);
conn.pst.setString(25 ,HV0206);
conn.pst.setString(26 ,HV0207);
conn.pst.setString(27 ,HV0208);
conn.pst.setString(28 ,HV0209);
conn.pst.setString(29 ,HV0210);
conn.pst.setString(30 ,HV0211);
conn.pst.setString(31 ,HV0212);
conn.pst.setString(32 ,HV0213);
conn.pst.setString(33 ,HV0214);
conn.pst.setString(34 ,HV0215);
conn.pst.setString(35 ,HV0216);
conn.pst.setString(36 ,HV0217);
conn.pst.setString(37 ,HV0218);
conn.pst.setString(38 ,HV0219);
conn.pst.setString(39 ,HV0220);
conn.pst.setString(40 ,HV0221);
conn.pst.setString(41 ,HV0224);
conn.pst.setString(42 ,HV0225);
conn.pst.setString(43 ,HV0226);
conn.pst.setString(44 ,HV0227);
conn.pst.setString(45 ,HV0228);
conn.pst.setString(46 ,HV0229);
conn.pst.setString(47 ,HV0230);
conn.pst.setString(48 ,HV0231);
conn.pst.setString(49 ,HV0232);
conn.pst.setString(50 ,HV0233);
conn.pst.setString(51 ,HV0234);
conn.pst.setString(52 ,HV0235);
conn.pst.setString(53 ,HV0236);
conn.pst.setString(54 ,HV0237);
conn.pst.setString(55 ,HV0238);
conn.pst.setString(56 ,HV0239);
conn.pst.setString(57 ,HV0240);
conn.pst.setString(58 ,HV0241);
conn.pst.setString(59 ,HV0242);
conn.pst.setString(60 ,HV0243);
conn.pst.setString(61 ,HV0244);

conn.pst.setString(62 ,HV0301);
conn.pst.setString(63 ,HV0302);
conn.pst.setString(64 ,HV0303);
conn.pst.setString(65 ,HV0304);
conn.pst.setString(66 ,HV0305);
conn.pst.setString(67 ,HV0306);
conn.pst.setString(68 ,HV0307);
conn.pst.setString(69 ,HV0308);
conn.pst.setString(70 ,HV0309);
conn.pst.setString(71 ,HV0310);
conn.pst.setString(72 ,HV0311);
conn.pst.setString(73 ,HV0312);
conn.pst.setString(74 ,HV0313);
conn.pst.setString(75 ,HV0314);
conn.pst.setString(76 ,HV0315);
conn.pst.setString(77 ,HV0316);
conn.pst.setString(78 ,HV0317);
conn.pst.setString(79 ,HV0318);
conn.pst.setString(80 ,HV0319);
conn.pst.setString(81 ,HV0320);
conn.pst.setString(82 ,HV0321);
conn.pst.setString(83 ,HV0322);
conn.pst.setString(84 ,HV0323);
conn.pst.setString(85 ,HV0324);
conn.pst.setString(86 ,HV0325);
conn.pst.setString(87 ,HV0326);
conn.pst.setString(88 ,HV0327);
conn.pst.setString(89 ,HV0328);
conn.pst.setString(90 ,HV0329);
conn.pst.setString(91 ,HV0330);
conn.pst.setString(92 ,HV0331);
conn.pst.setString(93 ,HV0332);
conn.pst.setString(94 ,HV0333);
conn.pst.setString(95 ,HV0334);
conn.pst.setString(96 ,HV0335);
conn.pst.setString(97 ,HV0336);
conn.pst.setString(98 ,HV0337);
conn.pst.setString(99 ,HV0338);
conn.pst.setString(100 ,HV0339);
conn.pst.setString(101 ,HV0340);
conn.pst.setString(102 ,HV0341);
conn.pst.setString(103 ,HV0342);
conn.pst.setString(104 ,HV0343);
conn.pst.setString(105 ,HV0344);
conn.pst.setString(106 ,HV0345);
conn.pst.setString(107 ,HV0346);
conn.pst.setString(108 ,HV0347);
conn.pst.setString(109 ,HV0348);
conn.pst.setString(110 ,HV0349);
conn.pst.setString(111 ,HV0350);
conn.pst.setString(112 ,HV0351);
conn.pst.setString(113 ,HV0352);
conn.pst.setString(114 ,HV0353);
conn.pst.setString(115 ,HV0354);
conn.pst.setString(116 ,HV0355);
conn.pst.setString(117 ,HV0904);
conn.pst.setString(118 ,HV0905);
conn.pst.setString(119 ,HV0370);
conn.pst.setString(120 ,HV0371);
conn.pst.setString(121 ,HV0372);
conn.pst.setString(122 ,HV0373);

conn.pst.setString(123 ,HV0401);
conn.pst.setString(124 ,HV0402);
conn.pst.setString(125 ,HV0403);
conn.pst.setString(126 ,HV0406);
conn.pst.setString(127 ,HV0407);
conn.pst.setString(128 ,HV0408);
conn.pst.setString(129 ,HV0409);
conn.pst.setString(130 ,HV0410);
conn.pst.setString(131 ,HV0411);
conn.pst.setString(132 ,HV0412);
conn.pst.setString(133 ,HV0413);
conn.pst.setString(134 ,HV0414);
conn.pst.setString(135 ,HV0415);

conn.pst.setString(136 ,HV0501);
conn.pst.setString(137 ,HV0502);
conn.pst.setString(138 ,HV0503);
conn.pst.setString(139 ,HV0504);
conn.pst.setString(140 ,HV0505);
conn.pst.setString(141 ,HV0506);
conn.pst.setString(142 ,HV0507);
conn.pst.setString(143 ,HV0508);
conn.pst.setString(144 ,HV0509);
conn.pst.setString(145 ,HV0510);
conn.pst.setString(146 ,HV0511);
conn.pst.setString(147 ,HV0512);
conn.pst.setString(148 ,HV0513);
conn.pst.setString(149 ,HV0514);

conn.pst.setString(150 ,HV0601);
conn.pst.setString(151 ,HV0602);
conn.pst.setString(152 ,HV0605); 

conn.pst.setString(153 ,user_id); 
conn.pst.setString(154 ,timestamp); 	
conn.pst.setString(155 ,isValidated); 	
conn.pst.setString(156 ,yearmonth); 	
conn.pst.setString(157 ,updatedBy); 	
conn.pst.setString(158 ,updatedOn); 	
conn.pst.setString(159 ,isLocked); 	
conn.pst.setString(160 ,quarter); 

conn.pst.executeUpdate();
     return res;   
    }
}
