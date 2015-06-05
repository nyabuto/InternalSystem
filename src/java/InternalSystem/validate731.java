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
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Geofrey Nyabuto
 */
public class validate731 extends HttpServlet {
HttpSession session;
String data,id;
String facilityId,year,month;
String HIV_CT,PMTCT,CT,VMMC,PEP,Blood;

String HV0101,HV0102,HV0103,HV0105,HV0106,HV0107,HV0108,HV0109,HV0110,HV0111,HV0112,HV0113,HV0114,
        HV0115,HV0116;
String HV0201,HV0202,HV0203,HV0204,HV0205,HV0206,HV0207,HV0208,HV0209,HV0210,HV0211,HV0212,HV0213,
HV0214,HV0215,HV0216,HV0217,HV0218,HV0219,HV0220,HV0221,HV0224,HV0225,HV0226,HV0227,HV0228,HV0229,
        HV0230,HV0231,HV0232,HV0233,HV0234,HV0235,HV0236,HV0237,HV0238,HV0239,HV0240,HV0241,HV0242,
        HV0243,HV0244;
String HV0301,HV0302,HV0303,HV0304,HV0305,HV0306,HV0307,HV0308,HV0309,HV0310,HV0311,HV0312,HV0313,HV0314,
        HV0315,HV0316,HV0317,HV0318,HV0319,HV0320,HV0321,HV0322,HV0323,HV0324,HV0325,HV0326,HV0327,HV0328,
        HV0329,HV0330,HV0331,HV0332,HV0333,HV0334,HV0335,HV0336,HV0337,HV0338,HV0339,HV0340,HV0341,
        HV0342,HV0343,HV0344,HV0345,HV0346,HV0347,HV0348,HV0349,HV0350,HV0351,HV0352,HV0353,
        HV0354,HV0355,HV0904,HV0905,HV0370,HV0371,HV0372,HV0373;
String HV0401,HV0402,HV0403,HV0406,HV0407,HV0408,HV0409,HV0410,HV0411,HV0412,HV0413,HV0414,HV0415;
String HV0501,HV0502,HV0503,HV0504,HV0505,HV0506,HV0507,HV0508,HV0509,HV0510,HV0511,HV0512,HV0513,HV0514;
String HV0601,HV0602,HV0605;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
            session=request.getSession();
            
          
           data="";
           if(session.getAttribute("year")!=null){        
   year=session.getAttribute("year").toString();
    }
      if(session.getAttribute("monthid")!=null){        
   month=session.getAttribute("monthid").toString();
    }
   
        if(session.getAttribute("facilityid")!=null){        
   facilityId=session.getAttribute("facilityid").toString();
    }
    id=year+"_"+month+"_"+facilityId; 
      
        
            
 HV0101=HV0102=HV0103=HV0105=HV0106=HV0107=HV0108=HV0109=HV0110=HV0111=HV0112=HV0113=HV0114=
        HV0115=HV0116="0";
HV0201=HV0202=HV0203=HV0204=HV0205=HV0206=HV0207=HV0208=HV0209=HV0210=HV0211=HV0212=HV0213=
HV0214=HV0215=HV0216=HV0217=HV0218=HV0219=HV0220=HV0221=HV0224=HV0225=HV0226=HV0227=HV0228=HV0229=
        HV0230=HV0231=HV0232=HV0233=HV0234=HV0235=HV0236=HV0237=HV0238=HV0239=HV0240=HV0241=HV0242=
        HV0243=HV0244="0";
HV0301=HV0302=HV0303=HV0304=HV0305=HV0306=HV0307=HV0308=HV0309=HV0310=HV0311=HV0312=HV0313=HV0314=
        HV0315=HV0316=HV0317=HV0318=HV0319=HV0320=HV0321=HV0322=HV0323=HV0324=HV0325=HV0326=HV0327=HV0328=
        HV0329=HV0330=HV0331=HV0332=HV0333=HV0334=HV0335=HV0336=HV0337=HV0338=HV0339=HV0340=HV0341=
        HV0342=HV0343=HV0344=HV0345=HV0346=HV0347=HV0348=HV0349=HV0350=HV0351=HV0352=HV0353=
        HV0354=HV0355=HV0904=HV0905=HV0370=HV0371=HV0372=HV0373="0";
HV0401=HV0402=HV0403=HV0406=HV0407=HV0408=HV0409=HV0410=HV0411=HV0412=HV0413=HV0414=HV0415="0";
 HV0501=HV0502=HV0503=HV0504=HV0505=HV0506=HV0507=HV0508=HV0509=HV0510=HV0511=HV0512=HV0513=HV0514="0";
 HV0601=HV0602=HV0605="0";
 
          String checker="SELECT * FROM moh731 WHERE id=?" ;
          conn.pst=conn.conn.prepareStatement(checker);
          conn.pst.setString(1, id);
          conn.rs=conn.pst.executeQuery();
          
          if(conn.rs.next()==true){
              System.out.println("Updating data-------------------");
              
 if(conn.rs.getString("HV0101")!=null){HV0101=conn.rs.getString("HV0101");}
  if(conn.rs.getString("HV0102")!=null){HV0102=conn.rs.getString("HV0102");}
  if(conn.rs.getString("HV0103")!=null){HV0103=conn.rs.getString("HV0103");}
  if(conn.rs.getString("HV0105")!=null){HV0105=conn.rs.getString("HV0105");}
  if(conn.rs.getString("HV0106")!=null){HV0106=conn.rs.getString("HV0106");}
  if(conn.rs.getString("HV0107")!=null){HV0107=conn.rs.getString("HV0107");}
  if(conn.rs.getString("HV0108")!=null){HV0108=conn.rs.getString("HV0108");}
  if(conn.rs.getString("HV0109")!=null){HV0109=conn.rs.getString("HV0109");}
  if(conn.rs.getString("HV0110")!=null){HV0110=conn.rs.getString("HV0110");}
  if(conn.rs.getString("HV0111")!=null){HV0111=conn.rs.getString("HV0111");}
  if(conn.rs.getString("HV0112")!=null){HV0112=conn.rs.getString("HV0112");}
  if(conn.rs.getString("HV0113")!=null){HV0113=conn.rs.getString("HV0113");}
  if(conn.rs.getString("HV0114")!=null){HV0114=conn.rs.getString("HV0114");}
  if(conn.rs.getString("HV0115")!=null){HV0115=conn.rs.getString("HV0115");}
  if(conn.rs.getString("HV0116")!=null){HV0116=conn.rs.getString("HV0116");}
 
if(conn.rs.getString("HV0201")!=null){HV0201=conn.rs.getString("HV0201");}
if(conn.rs.getString("HV0202")!=null){HV0202=conn.rs.getString("HV0202");}
if(conn.rs.getString("HV0203")!=null){HV0203=conn.rs.getString("HV0203");}
if(conn.rs.getString("HV0204")!=null){HV0204=conn.rs.getString("HV0204");}
if(conn.rs.getString("HV0205")!=null){HV0205=conn.rs.getString("HV0205");}
if(conn.rs.getString("HV0206")!=null){HV0206=conn.rs.getString("HV0206");}
if(conn.rs.getString("HV0207")!=null){HV0207=conn.rs.getString("HV0207");}
if(conn.rs.getString("HV0208")!=null){HV0208=conn.rs.getString("HV0208");}
if(conn.rs.getString("HV0209")!=null){HV0209=conn.rs.getString("HV0209");}
if(conn.rs.getString("HV0210")!=null){HV0210=conn.rs.getString("HV0210");}
if(conn.rs.getString("HV0211")!=null){HV0211=conn.rs.getString("HV0211");}
if(conn.rs.getString("HV0212")!=null){HV0212=conn.rs.getString("HV0212");}
if(conn.rs.getString("HV0213")!=null){HV0213=conn.rs.getString("HV0213");}
if(conn.rs.getString("HV0214")!=null){HV0214=conn.rs.getString("HV0214");}
if(conn.rs.getString("HV0215")!=null){HV0215=conn.rs.getString("HV0215");}
if(conn.rs.getString("HV0216")!=null){HV0216=conn.rs.getString("HV0216");}
if(conn.rs.getString("HV0217")!=null){HV0217=conn.rs.getString("HV0217");}
if(conn.rs.getString("HV0218")!=null){HV0218=conn.rs.getString("HV0218");}
if(conn.rs.getString("HV0219")!=null){HV0219=conn.rs.getString("HV0219");}
if(conn.rs.getString("HV0220")!=null){HV0220=conn.rs.getString("HV0220");}
if(conn.rs.getString("HV0221")!=null){HV0221=conn.rs.getString("HV0221");}
if(conn.rs.getString("HV0224")!=null){HV0224=conn.rs.getString("HV0224");}
if(conn.rs.getString("HV0225")!=null){HV0225=conn.rs.getString("HV0225");}
if(conn.rs.getString("HV0226")!=null){HV0226=conn.rs.getString("HV0226");}
if(conn.rs.getString("HV0227")!=null){HV0227=conn.rs.getString("HV0227");}
if(conn.rs.getString("HV0228")!=null){HV0228=conn.rs.getString("HV0228");}
if(conn.rs.getString("HV0229")!=null){HV0229=conn.rs.getString("HV0229");}
if(conn.rs.getString("HV0230")!=null){HV0230=conn.rs.getString("HV0230");}
if(conn.rs.getString("HV0231")!=null){HV0231=conn.rs.getString("HV0231");}
if(conn.rs.getString("HV0232")!=null){HV0232=conn.rs.getString("HV0232");}
if(conn.rs.getString("HV0233")!=null){HV0233=conn.rs.getString("HV0233");}
if(conn.rs.getString("HV0234")!=null){HV0234=conn.rs.getString("HV0234");}
if(conn.rs.getString("HV0235")!=null){HV0235=conn.rs.getString("HV0235");}
if(conn.rs.getString("HV0236")!=null){HV0236=conn.rs.getString("HV0236");}
if(conn.rs.getString("HV0237")!=null){HV0237=conn.rs.getString("HV0237");}
if(conn.rs.getString("HV0238")!=null){HV0238=conn.rs.getString("HV0238");}
if(conn.rs.getString("HV0239")!=null){HV0239=conn.rs.getString("HV0239");}
if(conn.rs.getString("HV0240")!=null){HV0240=conn.rs.getString("HV0240");}
if(conn.rs.getString("HV0241")!=null){HV0241=conn.rs.getString("HV0241");}
if(conn.rs.getString("HV0242")!=null){HV0242=conn.rs.getString("HV0242");}
if(conn.rs.getString("HV0243")!=null){HV0243=conn.rs.getString("HV0243");}
if(conn.rs.getString("HV0244")!=null){HV0244=conn.rs.getString("HV0244");}

if(conn.rs.getString("HV0301")!=null){HV0301=conn.rs.getString("HV0301");}
if(conn.rs.getString("HV0302")!=null){HV0302=conn.rs.getString("HV0302");}
if(conn.rs.getString("HV0303")!=null){HV0303=conn.rs.getString("HV0303");}
if(conn.rs.getString("HV0304")!=null){HV0304=conn.rs.getString("HV0304");}
if(conn.rs.getString("HV0305")!=null){HV0305=conn.rs.getString("HV0305");}
if(conn.rs.getString("HV0306")!=null){HV0306=conn.rs.getString("HV0306");}
if(conn.rs.getString("HV0307")!=null){HV0307=conn.rs.getString("HV0307");}
if(conn.rs.getString("HV0308")!=null){HV0308=conn.rs.getString("HV0308");}
if(conn.rs.getString("HV0309")!=null){HV0309=conn.rs.getString("HV0309");}
if(conn.rs.getString("HV0310")!=null){HV0310=conn.rs.getString("HV0310");}
if(conn.rs.getString("HV0311")!=null){HV0311=conn.rs.getString("HV0311");}
if(conn.rs.getString("HV0312")!=null){HV0312=conn.rs.getString("HV0312");}
if(conn.rs.getString("HV0313")!=null){HV0313=conn.rs.getString("HV0313");}
if(conn.rs.getString("HV0314")!=null){HV0314=conn.rs.getString("HV0314");}
if(conn.rs.getString("HV0315")!=null){HV0315=conn.rs.getString("HV0315");}
if(conn.rs.getString("HV0316")!=null){HV0316=conn.rs.getString("HV0316");}
if(conn.rs.getString("HV0317")!=null){HV0317=conn.rs.getString("HV0317");}
if(conn.rs.getString("HV0319")!=null){HV0319=conn.rs.getString("HV0319");}
if(conn.rs.getString("HV0320")!=null){HV0320=conn.rs.getString("HV0320");}
if(conn.rs.getString("HV0321")!=null){HV0321=conn.rs.getString("HV0321");}
if(conn.rs.getString("HV0322")!=null){HV0322=conn.rs.getString("HV0322");}
if(conn.rs.getString("HV0323")!=null){HV0323=conn.rs.getString("HV0323");}
if(conn.rs.getString("HV0324")!=null){HV0324=conn.rs.getString("HV0324");}
if(conn.rs.getString("HV0325")!=null){HV0325=conn.rs.getString("HV0325");}
if(conn.rs.getString("HV0326")!=null){HV0326=conn.rs.getString("HV0326");}
if(conn.rs.getString("HV0327")!=null){HV0327=conn.rs.getString("HV0327");}
if(conn.rs.getString("HV0328")!=null){HV0328=conn.rs.getString("HV0328");}
if(conn.rs.getString("HV0329")!=null){HV0329=conn.rs.getString("HV0329");}
if(conn.rs.getString("HV0330")!=null){HV0330=conn.rs.getString("HV0330");}
if(conn.rs.getString("HV0331")!=null){HV0331=conn.rs.getString("HV0331");}
if(conn.rs.getString("HV0332")!=null){HV0332=conn.rs.getString("HV0332");}
if(conn.rs.getString("HV0333")!=null){HV0333=conn.rs.getString("HV0333");}
if(conn.rs.getString("HV0334")!=null){HV0334=conn.rs.getString("HV0334");}
if(conn.rs.getString("HV0335")!=null){HV0335=conn.rs.getString("HV0335");}
if(conn.rs.getString("HV0336")!=null){HV0336=conn.rs.getString("HV0336");}
if(conn.rs.getString("HV0337")!=null){HV0337=conn.rs.getString("HV0337");}
if(conn.rs.getString("HV0338")!=null){HV0338=conn.rs.getString("HV0338");}
if(conn.rs.getString("HV0339")!=null){HV0339=conn.rs.getString("HV0339");}
if(conn.rs.getString("HV0340")!=null){HV0340=conn.rs.getString("HV0340");}
if(conn.rs.getString("HV0341")!=null){HV0341=conn.rs.getString("HV0341");}
if(conn.rs.getString("HV0342")!=null){HV0342=conn.rs.getString("HV0342");}
if(conn.rs.getString("HV0343")!=null){HV0343=conn.rs.getString("HV0343");}
if(conn.rs.getString("HV0344")!=null){HV0344=conn.rs.getString("HV0344");}
if(conn.rs.getString("HV0345")!=null){HV0345=conn.rs.getString("HV0345");}
if(conn.rs.getString("HV0346")!=null){HV0346=conn.rs.getString("HV0346");}
if(conn.rs.getString("HV0347")!=null){HV0347=conn.rs.getString("HV0347");}
if(conn.rs.getString("HV0348")!=null){HV0348=conn.rs.getString("HV0348");}
if(conn.rs.getString("HV0349")!=null){HV0349=conn.rs.getString("HV0349");}
if(conn.rs.getString("HV0350")!=null){HV0350=conn.rs.getString("HV0350");}
if(conn.rs.getString("HV0351")!=null){HV0351=conn.rs.getString("HV0351");}
if(conn.rs.getString("HV0352")!=null){HV0352=conn.rs.getString("HV0352");}
if(conn.rs.getString("HV0353")!=null){HV0353=conn.rs.getString("HV0353");}
if(conn.rs.getString("HV0354")!=null){HV0354=conn.rs.getString("HV0354");}
if(conn.rs.getString("HV0355")!=null){HV0355=conn.rs.getString("HV0355");}
if(conn.rs.getString("HV0904")!=null){HV0904=conn.rs.getString("HV0904");}
if(conn.rs.getString("HV0905")!=null){HV0905=conn.rs.getString("HV0905");}
if(conn.rs.getString("HV0370")!=null){HV0370=conn.rs.getString("HV0370");}
if(conn.rs.getString("HV0371")!=null){HV0371=conn.rs.getString("HV0371");}
if(conn.rs.getString("HV0372")!=null){HV0372=conn.rs.getString("HV0372");}
if(conn.rs.getString("HV0373")!=null){HV0373=conn.rs.getString("HV0373");}

if(conn.rs.getString("HV0401")!=null){HV0401=conn.rs.getString("HV0401");}
if(conn.rs.getString("HV0402")!=null){HV0402=conn.rs.getString("HV0402");}
if(conn.rs.getString("HV0403")!=null){HV0403=conn.rs.getString("HV0403");}
if(conn.rs.getString("HV0406")!=null){HV0406=conn.rs.getString("HV0406");}
if(conn.rs.getString("HV0407")!=null){HV0407=conn.rs.getString("HV0407");}
if(conn.rs.getString("HV0408")!=null){HV0408=conn.rs.getString("HV0408");}
if(conn.rs.getString("HV0409")!=null){HV0409=conn.rs.getString("HV0409");}
if(conn.rs.getString("HV0410")!=null){HV0410=conn.rs.getString("HV0410");}
if(conn.rs.getString("HV0411")!=null){HV0411=conn.rs.getString("HV0411");}
if(conn.rs.getString("HV0412")!=null){HV0412=conn.rs.getString("HV0412");}
if(conn.rs.getString("HV0413")!=null){HV0413=conn.rs.getString("HV0413");}
if(conn.rs.getString("HV0414")!=null){HV0414=conn.rs.getString("HV0414");}
if(conn.rs.getString("HV0415")!=null){HV0415=conn.rs.getString("HV0415");}

if(conn.rs.getString("HV0501")!=null){HV0501=conn.rs.getString("HV0501");}
if(conn.rs.getString("HV0502")!=null){HV0502=conn.rs.getString("HV0502");}
if(conn.rs.getString("HV0503")!=null){HV0503=conn.rs.getString("HV0503");}
if(conn.rs.getString("HV0504")!=null){HV0504=conn.rs.getString("HV0504");}
if(conn.rs.getString("HV0505")!=null){HV0505=conn.rs.getString("HV0505");}
if(conn.rs.getString("HV0506")!=null){HV0506=conn.rs.getString("HV0506");}
if(conn.rs.getString("HV0507")!=null){HV0507=conn.rs.getString("HV0507");}
if(conn.rs.getString("HV0508")!=null){HV0508=conn.rs.getString("HV0508");}
if(conn.rs.getString("HV0509")!=null){HV0509=conn.rs.getString("HV0509");}
if(conn.rs.getString("HV0510")!=null){HV0510=conn.rs.getString("HV0510");}
if(conn.rs.getString("HV0511")!=null){HV0511=conn.rs.getString("HV0511");}
if(conn.rs.getString("HV0512")!=null){HV0512=conn.rs.getString("HV0512");}
if(conn.rs.getString("HV0513")!=null){HV0513=conn.rs.getString("HV0513");}
if(conn.rs.getString("HV0514")!=null){HV0514=conn.rs.getString("HV0514");}

if(conn.rs.getString("HV0601")!=null){HV0601=conn.rs.getString("HV0601");}
if(conn.rs.getString("HV0602")!=null){HV0602=conn.rs.getString("HV0602");}
if(conn.rs.getString("HV0605")!=null){HV0605=conn.rs.getString("HV0605");}
         
          
          
  String runValidate="UPDATE moh731 SET HV0101=?,HV0102=?,HV0103=?,HV0105=?,HV0106=?,HV0107=?,HV0108=?,HV0109=?,HV0110=?,HV0111=?,HV0112=?,HV0113=?,HV0114=?," +
"        HV0115=?,HV0116=?," +
"HV0201=?,HV0202=?,HV0203=?,HV0204=?,HV0205=?,HV0206=?,HV0207=?,HV0208=?,HV0209=?,HV0210=?,HV0211=?,HV0212=?,HV0213=?," +
"HV0214=?,HV0215=?,HV0216=?,HV0217=?,HV0218=?,HV0219=?,HV0220=?,HV0221=?,HV0224=?,HV0225=?,HV0226=?,HV0227=?,HV0228=?,HV0229=?," +
"        HV0230=?,HV0231=?,HV0232=?,HV0233=?,HV0234=?,HV0235=?,HV0236=?,HV0237=?,HV0238=?,HV0239=?,HV0240=?,HV0241=?,HV0242=?," +
"        HV0243=?,HV0244=?," + //57
"HV0301=?,HV0302=?,HV0303=?,HV0304=?,HV0305=?,HV0306=?,HV0307=?,HV0308=?,HV0309=?,HV0310=?,HV0311=?,HV0312=?,HV0313=?,HV0314=?," +
"        HV0315=?,HV0316=?,HV0317=?,HV0318=?,HV0319=?,HV0320=?,HV0321=?,HV0322=?,HV0323=?,HV0324=?,HV0325=?,HV0326=?,HV0327=?,HV0328=?," +
"        HV0329=?,HV0330=?,HV0331=?,HV0332=?,HV0333=?,HV0334=?,HV0335=?,HV0336=?,HV0337=?,HV0338=?,HV0339=?,HV0340=?,HV0341=?," +
"        HV0342=?,HV0343=?,HV0344=?,HV0345=?,HV0346=?,HV0347=?,HV0348=?,HV0349=?,HV0350=?,HV0351=?,HV0352=?,HV0353=?," +
"        HV0354=?,HV0355=?,HV0904=?,HV0905=?,HV0370=?,HV0371=?,HV0372=?,HV0373=?," + //61
"HV0401=?,HV0402=?,HV0403=?,HV0406=?,HV0407=?,HV0408=?,HV0409=?,HV0410=?,HV0411=?,HV0412=?,HV0413=?,HV0414=?,HV0415=?," +
" HV0501=?,HV0502=?,HV0503=?,HV0504=?,HV0505=?,HV0506=?,HV0507=?,HV0508=?,HV0509=?,HV0510=?,HV0511=?,HV0512=?,HV0513=?,HV0514=?," +
" HV0601=?,HV0602=?,HV0605=?,isValidated=? WHERE id=?";     //31   
 conn.pst=conn.conn.prepareStatement(runValidate);
  
   conn.pst.setString(1, HV0101);
   conn.pst.setString(2, HV0102);
   conn.pst.setString(3, HV0103);
   conn.pst.setString(4, HV0105);
   conn.pst.setString(5, HV0106);
   conn.pst.setString(6, HV0107);
   conn.pst.setString(7, HV0108);
   conn.pst.setString(8, HV0109);
   conn.pst.setString(9, HV0110);
   conn.pst.setString(10, HV0111);
   conn.pst.setString(11, HV0112);
   conn.pst.setString(12, HV0113);
   conn.pst.setString(13, HV0114);
   conn.pst.setString(14, HV0115);
   conn.pst.setString(15, HV0116);
   
   conn.pst.setString(16, HV0201);
   conn.pst.setString(17, HV0202);
   conn.pst.setString(18, HV0203);
   conn.pst.setString(19, HV0204);
   conn.pst.setString(20, HV0205);
   conn.pst.setString(21, HV0206);
   conn.pst.setString(22, HV0207);
   conn.pst.setString(23, HV0208);
   conn.pst.setString(24, HV0209);
   conn.pst.setString(25, HV0210);
   conn.pst.setString(26, HV0211);
   conn.pst.setString(27, HV0212);
   conn.pst.setString(28, HV0213);
   conn.pst.setString(29, HV0214);
   conn.pst.setString(30, HV0215);
   conn.pst.setString(31, HV0216);
   conn.pst.setString(32, HV0217);
   conn.pst.setString(33, HV0218);
   conn.pst.setString(34, HV0219);
   conn.pst.setString(35, HV0220);
   conn.pst.setString(36, HV0221);
   conn.pst.setString(37, HV0224);
   conn.pst.setString(38, HV0225);
   conn.pst.setString(39, HV0226);
   conn.pst.setString(40, HV0227);
   conn.pst.setString(41, HV0228);
   conn.pst.setString(42, HV0229);
   conn.pst.setString(43, HV0230);
   conn.pst.setString(44, HV0231);
   conn.pst.setString(45, HV0232);
   conn.pst.setString(46, HV0233);
   conn.pst.setString(47, HV0234);
   conn.pst.setString(48, HV0235);
   conn.pst.setString(49, HV0236);
   conn.pst.setString(50, HV0237);
   conn.pst.setString(51, HV0238);
   conn.pst.setString(52, HV0239);
   conn.pst.setString(53, HV0240);
   conn.pst.setString(54, HV0241);
   conn.pst.setString(55, HV0242);
   conn.pst.setString(56, HV0243);
   conn.pst.setString(57, HV0244);
 
   
   conn.pst.setString(58, HV0301);
   conn.pst.setString(59, HV0302);
   conn.pst.setString(60, HV0303);
   conn.pst.setString(61, HV0304);
   conn.pst.setString(62, HV0305);
   conn.pst.setString(63, HV0306);
   conn.pst.setString(64, HV0307);
   conn.pst.setString(65, HV0308);
   conn.pst.setString(66, HV0309);
   conn.pst.setString(67, HV0310);
   conn.pst.setString(68, HV0311);
   conn.pst.setString(69, HV0312);
   conn.pst.setString(70, HV0313);
   conn.pst.setString(71, HV0314);
   conn.pst.setString(72, HV0315);
   conn.pst.setString(73, HV0316);
   conn.pst.setString(74, HV0317);
   conn.pst.setString(75, HV0318);
   conn.pst.setString(76, HV0319);
   conn.pst.setString(77, HV0320);
   conn.pst.setString(78, HV0321);
   conn.pst.setString(79, HV0322);
   conn.pst.setString(80, HV0323);
   conn.pst.setString(81, HV0324);
   conn.pst.setString(82, HV0325);
   conn.pst.setString(83, HV0326);
   conn.pst.setString(84, HV0327);
   conn.pst.setString(85, HV0328);
   conn.pst.setString(86, HV0329);
   conn.pst.setString(87, HV0330);
   conn.pst.setString(88, HV0331);
   conn.pst.setString(89, HV0332);
   conn.pst.setString(90, HV0333);
   conn.pst.setString(91, HV0334);
   conn.pst.setString(92, HV0335);
   conn.pst.setString(93, HV0336);
   conn.pst.setString(94, HV0337);
   conn.pst.setString(95, HV0338);
   conn.pst.setString(96, HV0339);
   conn.pst.setString(97, HV0340);
   conn.pst.setString(98, HV0341);
   conn.pst.setString(99, HV0342);
   conn.pst.setString(100, HV0343);
   conn.pst.setString(101, HV0344);
   conn.pst.setString(102, HV0345);
   conn.pst.setString(103, HV0346);
   conn.pst.setString(104, HV0347);
   conn.pst.setString(105, HV0348);
   conn.pst.setString(106, HV0349);
   conn.pst.setString(107, HV0350);
   conn.pst.setString(108, HV0351);
   conn.pst.setString(109, HV0352);
   conn.pst.setString(110, HV0353);
   conn.pst.setString(111, HV0354);
   conn.pst.setString(112, HV0355);
   conn.pst.setString(113, HV0904);
   conn.pst.setString(114, HV0905);
   conn.pst.setString(115, HV0370);
   conn.pst.setString(116, HV0371);
   conn.pst.setString(117, HV0372);
   conn.pst.setString(118, HV0373);   

   conn.pst.setString(119, HV0401);
   conn.pst.setString(120, HV0402);
   conn.pst.setString(121, HV0403);
   conn.pst.setString(122, HV0406);
   conn.pst.setString(123, HV0407);
   conn.pst.setString(124, HV0408);
   conn.pst.setString(125, HV0409);
   conn.pst.setString(126, HV0410);
   conn.pst.setString(127, HV0411);
   conn.pst.setString(128, HV0412);
   conn.pst.setString(129, HV0413);
   conn.pst.setString(130, HV0414);
   conn.pst.setString(131, HV0415);

   conn.pst.setString(132, HV0501);
   conn.pst.setString(133, HV0502);
   conn.pst.setString(134, HV0503);
   conn.pst.setString(135, HV0504);
   conn.pst.setString(136, HV0505);
   conn.pst.setString(137, HV0506);
   conn.pst.setString(138, HV0507);
   conn.pst.setString(139, HV0508);
   conn.pst.setString(140, HV0509);
   conn.pst.setString(141, HV0510);
   conn.pst.setString(142, HV0511);
   conn.pst.setString(143, HV0512);
   conn.pst.setString(144, HV0513);
   conn.pst.setString(145, HV0514);
   
   conn.pst.setString(146, HV0601);
   conn.pst.setString(147, HV0602);
   conn.pst.setString(148, HV0605);
   conn.pst.setString(149, "1");
   conn.pst.setString(150, id);
   
   conn.pst.executeUpdate();
   
   
     session.setAttribute("validate731", "<font color=\"green\"><b>Form MOH 731 Validated Successfully.</b></font>");
    int monthDiff=0;
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM moh731 WHERE id='"+id+"'";
     conn.rs1=conn.st1.executeQuery(getMonths);
     if(conn.rs1.next()==true){
         System.out.println("months are : "+conn.rs1.getString(1));
    monthDiff=conn.rs1.getInt(1);
     }
     if(monthDiff>0){
//      UPDATE THE COLUMN   
       java.util.Date date= new java.util.Date();
Timestamp lastUpdatedOn =new Timestamp(date.getTime()); 
String userid=session.getAttribute("userid").toString();
     String updateLast="UPDATE moh731 SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE id='"+id+"'" ;   
       conn.st2.executeUpdate(updateLast);
     }
     if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.conn!=null){conn.conn.close();}
     
     response.sendRedirect("Form731.jsp");
          }
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
        Logger.getLogger(validate731.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(validate731.class.getName()).log(Level.SEVERE, null, ex);
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
