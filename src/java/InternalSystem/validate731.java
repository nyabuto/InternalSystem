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
String data_elements,description;
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
 
//          String checker="SELECT * FROM moh731 WHERE id=?" ;
//          conn.pst=conn.conn.prepareStatement(checker);
//          conn.pst.setString(1, id);
//          conn.rs=conn.pst.executeQuery();
//          
//          if(conn.rs.next()==true){
//             System.out.println("Updating data-------------------");
//              
//          }
 
 data_elements=request.getParameter("data_elements");
  description=request.getParameter("description");
 
  if(request.getParameter("HV0101")!=null && !request.getParameter("HV0101").equals("")){HV0101=request.getParameter("HV0101");}
  if(request.getParameter("HV0102")!=null && !request.getParameter("HV0102").equals("")){HV0102=request.getParameter("HV0102");}
  if(request.getParameter("HV0103")!=null && !request.getParameter("HV0103").equals("")){HV0103=request.getParameter("HV0103");}
  if(request.getParameter("HV0105")!=null && !request.getParameter("HV0105").equals("")){HV0105=request.getParameter("HV0105");}
  if(request.getParameter("HV0106")!=null && !request.getParameter("HV0106").equals("")){HV0106=request.getParameter("HV0106");}
  if(request.getParameter("HV0107")!=null && !request.getParameter("HV0107").equals("")){HV0107=request.getParameter("HV0107");}
  if(request.getParameter("HV0108")!=null && !request.getParameter("HV0108").equals("")){HV0108=request.getParameter("HV0108");}
  if(request.getParameter("HV0109")!=null && !request.getParameter("HV0109").equals("")){HV0109=request.getParameter("HV0109");}
  if(request.getParameter("HV0110")!=null && !request.getParameter("HV0110").equals("")){HV0110=request.getParameter("HV0110");}
  if(request.getParameter("HV0111")!=null && !request.getParameter("HV0111").equals("")){HV0111=request.getParameter("HV0111");}
  if(request.getParameter("HV0112")!=null && !request.getParameter("HV0112").equals("")){HV0112=request.getParameter("HV0112");}
  if(request.getParameter("HV0113")!=null && !request.getParameter("HV0113").equals("")){HV0113=request.getParameter("HV0113");}
  if(request.getParameter("HV0114")!=null && !request.getParameter("HV0114").equals("")){HV0114=request.getParameter("HV0114");}
  if(request.getParameter("HV0115")!=null && !request.getParameter("HV0115").equals("")){HV0115=request.getParameter("HV0115");}
  if(request.getParameter("HV0116")!=null && !request.getParameter("HV0116").equals("")){HV0116=request.getParameter("HV0116");}
 
if(request.getParameter("HV0201")!=null && !request.getParameter("HV0201").equals("")){HV0201=request.getParameter("HV0201");}
if(request.getParameter("HV0202")!=null && !request.getParameter("HV0202").equals("")){HV0202=request.getParameter("HV0202");}
if(request.getParameter("HV0203")!=null && !request.getParameter("HV0203").equals("")){HV0203=request.getParameter("HV0203");}
if(request.getParameter("HV0204")!=null && !request.getParameter("HV0204").equals("")){HV0204=request.getParameter("HV0204");}
if(request.getParameter("HV0205")!=null && !request.getParameter("HV0205").equals("")){HV0205=request.getParameter("HV0205");}
if(request.getParameter("HV0206")!=null && !request.getParameter("HV0206").equals("")){HV0206=request.getParameter("HV0206");}
if(request.getParameter("HV0207")!=null && !request.getParameter("HV0207").equals("")){HV0207=request.getParameter("HV0207");}
if(request.getParameter("HV0208")!=null && !request.getParameter("HV0208").equals("")){HV0208=request.getParameter("HV0208");}
if(request.getParameter("HV0209")!=null && !request.getParameter("HV0209").equals("")){HV0209=request.getParameter("HV0209");}
if(request.getParameter("HV0210")!=null && !request.getParameter("HV0210").equals("")){HV0210=request.getParameter("HV0210");}
if(request.getParameter("HV0211")!=null && !request.getParameter("HV0211").equals("")){HV0211=request.getParameter("HV0211");}
if(request.getParameter("HV0212")!=null && !request.getParameter("HV0212").equals("")){HV0212=request.getParameter("HV0212");}
if(request.getParameter("HV0213")!=null && !request.getParameter("HV0213").equals("")){HV0213=request.getParameter("HV0213");}
if(request.getParameter("HV0214")!=null && !request.getParameter("HV0214").equals("")){HV0214=request.getParameter("HV0214");}
if(request.getParameter("HV0215")!=null && !request.getParameter("HV0215").equals("")){HV0215=request.getParameter("HV0215");}
if(request.getParameter("HV0216")!=null && !request.getParameter("HV0216").equals("")){HV0216=request.getParameter("HV0216");}
if(request.getParameter("HV0217")!=null && !request.getParameter("HV0217").equals("")){HV0217=request.getParameter("HV0217");}
if(request.getParameter("HV0218")!=null && !request.getParameter("HV0218").equals("")){HV0218=request.getParameter("HV0218");}
if(request.getParameter("HV0219")!=null && !request.getParameter("HV0219").equals("")){HV0219=request.getParameter("HV0219");}
if(request.getParameter("HV0220")!=null && !request.getParameter("HV0220").equals("")){HV0220=request.getParameter("HV0220");}
if(request.getParameter("HV0221")!=null && !request.getParameter("HV0221").equals("")){HV0221=request.getParameter("HV0221");}
if(request.getParameter("HV0224")!=null && !request.getParameter("HV0224").equals("")){HV0224=request.getParameter("HV0224");}
if(request.getParameter("HV0225")!=null && !request.getParameter("HV0225").equals("")){HV0225=request.getParameter("HV0225");}
if(request.getParameter("HV0226")!=null && !request.getParameter("HV0226").equals("")){HV0226=request.getParameter("HV0226");}
if(request.getParameter("HV0227")!=null && !request.getParameter("HV0227").equals("")){HV0227=request.getParameter("HV0227");}
if(request.getParameter("HV0228")!=null && !request.getParameter("HV0228").equals("")){HV0228=request.getParameter("HV0228");}
if(request.getParameter("HV0229")!=null && !request.getParameter("HV0229").equals("")){HV0229=request.getParameter("HV0229");}
if(request.getParameter("HV0230")!=null && !request.getParameter("HV0230").equals("")){HV0230=request.getParameter("HV0230");}
if(request.getParameter("HV0231")!=null && !request.getParameter("HV0231").equals("")){HV0231=request.getParameter("HV0231");}
if(request.getParameter("HV0232")!=null && !request.getParameter("HV0232").equals("")){HV0232=request.getParameter("HV0232");}
if(request.getParameter("HV0233")!=null && !request.getParameter("HV0233").equals("")){HV0233=request.getParameter("HV0233");}
if(request.getParameter("HV0234")!=null && !request.getParameter("HV0234").equals("")){HV0234=request.getParameter("HV0234");}
if(request.getParameter("HV0235")!=null && !request.getParameter("HV0235").equals("")){HV0235=request.getParameter("HV0235");}
if(request.getParameter("HV0236")!=null && !request.getParameter("HV0236").equals("")){HV0236=request.getParameter("HV0236");}
if(request.getParameter("HV0237")!=null && !request.getParameter("HV0237").equals("")){HV0237=request.getParameter("HV0237");}
if(request.getParameter("HV0238")!=null && !request.getParameter("HV0238").equals("")){HV0238=request.getParameter("HV0238");}
if(request.getParameter("HV0239")!=null && !request.getParameter("HV0239").equals("")){HV0239=request.getParameter("HV0239");}
if(request.getParameter("HV0240")!=null && !request.getParameter("HV0240").equals("")){HV0240=request.getParameter("HV0240");}
if(request.getParameter("HV0241")!=null && !request.getParameter("HV0241").equals("")){HV0241=request.getParameter("HV0241");}
if(request.getParameter("HV0242")!=null && !request.getParameter("HV0242").equals("")){HV0242=request.getParameter("HV0242");}
if(request.getParameter("HV0243")!=null && !request.getParameter("HV0243").equals("")){HV0243=request.getParameter("HV0243");}
if(request.getParameter("HV0244")!=null && !request.getParameter("HV0244").equals("")){HV0244=request.getParameter("HV0244");}

if(request.getParameter("HV0301")!=null && !request.getParameter("HV0301").equals("")){HV0301=request.getParameter("HV0301");}
if(request.getParameter("HV0302")!=null && !request.getParameter("HV0302").equals("")){HV0302=request.getParameter("HV0302");}
if(request.getParameter("HV0303")!=null && !request.getParameter("HV0303").equals("")){HV0303=request.getParameter("HV0303");}
if(request.getParameter("HV0304")!=null && !request.getParameter("HV0304").equals("")){HV0304=request.getParameter("HV0304");}
if(request.getParameter("HV0305")!=null && !request.getParameter("HV0305").equals("")){HV0305=request.getParameter("HV0305");}
if(request.getParameter("HV0306")!=null && !request.getParameter("HV0306").equals("")){HV0306=request.getParameter("HV0306");}
if(request.getParameter("HV0307")!=null && !request.getParameter("HV0307").equals("")){HV0307=request.getParameter("HV0307");}
if(request.getParameter("HV0308")!=null && !request.getParameter("HV0308").equals("")){HV0308=request.getParameter("HV0308");}
if(request.getParameter("HV0309")!=null && !request.getParameter("HV0309").equals("")){HV0309=request.getParameter("HV0309");}
if(request.getParameter("HV0310")!=null && !request.getParameter("HV0310").equals("")){HV0310=request.getParameter("HV0310");}
if(request.getParameter("HV0311")!=null && !request.getParameter("HV0311").equals("")){HV0311=request.getParameter("HV0311");}
if(request.getParameter("HV0312")!=null && !request.getParameter("HV0312").equals("")){HV0312=request.getParameter("HV0312");}
if(request.getParameter("HV0313")!=null && !request.getParameter("HV0313").equals("")){HV0313=request.getParameter("HV0313");}
if(request.getParameter("HV0314")!=null && !request.getParameter("HV0314").equals("")){HV0314=request.getParameter("HV0314");}
if(request.getParameter("HV0315")!=null && !request.getParameter("HV0315").equals("")){HV0315=request.getParameter("HV0315");}
if(request.getParameter("HV0316")!=null && !request.getParameter("HV0316").equals("")){HV0316=request.getParameter("HV0316");}
if(request.getParameter("HV0317")!=null && !request.getParameter("HV0317").equals("")){HV0317=request.getParameter("HV0317");}
if(request.getParameter("HV0318")!=null && !request.getParameter("HV0318").equals("")){HV0318=request.getParameter("HV0318");}
if(request.getParameter("HV0319")!=null && !request.getParameter("HV0319").equals("")){HV0319=request.getParameter("HV0319");}
if(request.getParameter("HV0320")!=null && !request.getParameter("HV0320").equals("")){HV0320=request.getParameter("HV0320");}
if(request.getParameter("HV0321")!=null && !request.getParameter("HV0321").equals("")){HV0321=request.getParameter("HV0321");}
if(request.getParameter("HV0322")!=null && !request.getParameter("HV0322").equals("")){HV0322=request.getParameter("HV0322");}
if(request.getParameter("HV0323")!=null && !request.getParameter("HV0323").equals("")){HV0323=request.getParameter("HV0323");}
if(request.getParameter("HV0324")!=null && !request.getParameter("HV0324").equals("")){HV0324=request.getParameter("HV0324");}
if(request.getParameter("HV0325")!=null && !request.getParameter("HV0325").equals("")){HV0325=request.getParameter("HV0325");}
if(request.getParameter("HV0326")!=null && !request.getParameter("HV0326").equals("")){HV0326=request.getParameter("HV0326");}
if(request.getParameter("HV0327")!=null && !request.getParameter("HV0327").equals("")){HV0327=request.getParameter("HV0327");}
if(request.getParameter("HV0328")!=null && !request.getParameter("HV0328").equals("")){HV0328=request.getParameter("HV0328");}
if(request.getParameter("HV0329")!=null && !request.getParameter("HV0329").equals("")){HV0329=request.getParameter("HV0329");}
if(request.getParameter("HV0330")!=null && !request.getParameter("HV0330").equals("")){HV0330=request.getParameter("HV0330");}
if(request.getParameter("HV0331")!=null && !request.getParameter("HV0331").equals("")){HV0331=request.getParameter("HV0331");}
if(request.getParameter("HV0332")!=null && !request.getParameter("HV0332").equals("")){HV0332=request.getParameter("HV0332");}
if(request.getParameter("HV0333")!=null && !request.getParameter("HV0333").equals("")){HV0333=request.getParameter("HV0333");}
if(request.getParameter("HV0334")!=null && !request.getParameter("HV0334").equals("")){HV0334=request.getParameter("HV0334");}
if(request.getParameter("HV0335")!=null && !request.getParameter("HV0335").equals("")){HV0335=request.getParameter("HV0335");}
if(request.getParameter("HV0336")!=null && !request.getParameter("HV0336").equals("")){HV0336=request.getParameter("HV0336");}
if(request.getParameter("HV0337")!=null && !request.getParameter("HV0337").equals("")){HV0337=request.getParameter("HV0337");}
if(request.getParameter("HV0338")!=null && !request.getParameter("HV0338").equals("")){HV0338=request.getParameter("HV0338");}
if(request.getParameter("HV0339")!=null && !request.getParameter("HV0339").equals("")){HV0339=request.getParameter("HV0339");}
if(request.getParameter("HV0340")!=null && !request.getParameter("HV0340").equals("")){HV0340=request.getParameter("HV0340");}
if(request.getParameter("HV0341")!=null && !request.getParameter("HV0341").equals("")){HV0341=request.getParameter("HV0341");}
if(request.getParameter("HV0342")!=null && !request.getParameter("HV0342").equals("")){HV0342=request.getParameter("HV0342");}
if(request.getParameter("HV0343")!=null && !request.getParameter("HV0343").equals("")){HV0343=request.getParameter("HV0343");}
if(request.getParameter("HV0344")!=null && !request.getParameter("HV0344").equals("")){HV0344=request.getParameter("HV0344");}
if(request.getParameter("HV0345")!=null && !request.getParameter("HV0345").equals("")){HV0345=request.getParameter("HV0345");}
if(request.getParameter("HV0346")!=null && !request.getParameter("HV0346").equals("")){HV0346=request.getParameter("HV0346");}
if(request.getParameter("HV0347")!=null && !request.getParameter("HV0347").equals("")){HV0347=request.getParameter("HV0347");}
if(request.getParameter("HV0348")!=null && !request.getParameter("HV0348").equals("")){HV0348=request.getParameter("HV0348");}
if(request.getParameter("HV0349")!=null && !request.getParameter("HV0349").equals("")){HV0349=request.getParameter("HV0349");}
if(request.getParameter("HV0350")!=null && !request.getParameter("HV0350").equals("")){HV0350=request.getParameter("HV0350");}
if(request.getParameter("HV0351")!=null && !request.getParameter("HV0351").equals("")){HV0351=request.getParameter("HV0351");}
if(request.getParameter("HV0352")!=null && !request.getParameter("HV0352").equals("")){HV0352=request.getParameter("HV0352");}
if(request.getParameter("HV0353")!=null && !request.getParameter("HV0353").equals("")){HV0353=request.getParameter("HV0353");}
if(request.getParameter("HV0354")!=null && !request.getParameter("HV0354").equals("")){HV0354=request.getParameter("HV0354");}
if(request.getParameter("HV0355")!=null && !request.getParameter("HV0355").equals("")){HV0355=request.getParameter("HV0355");}
if(request.getParameter("HV0904")!=null && !request.getParameter("HV0904").equals("")){HV0904=request.getParameter("HV0904");}
if(request.getParameter("HV0905")!=null && !request.getParameter("HV0905").equals("")){HV0905=request.getParameter("HV0905");}
if(request.getParameter("HV0370")!=null && !request.getParameter("HV0370").equals("")){HV0370=request.getParameter("HV0370");}
if(request.getParameter("HV0371")!=null && !request.getParameter("HV0371").equals("")){HV0371=request.getParameter("HV0371");}
if(request.getParameter("HV0372")!=null && !request.getParameter("HV0372").equals("")){HV0372=request.getParameter("HV0372");}
if(request.getParameter("HV0373")!=null && !request.getParameter("HV0373").equals("")){HV0373=request.getParameter("HV0373");}

if(request.getParameter("HV0401")!=null && !request.getParameter("HV0401").equals("")){HV0401=request.getParameter("HV0401");}
if(request.getParameter("HV0402")!=null && !request.getParameter("HV0402").equals("")){HV0402=request.getParameter("HV0402");}
if(request.getParameter("HV0403")!=null && !request.getParameter("HV0403").equals("")){HV0403=request.getParameter("HV0403");}
if(request.getParameter("HV0406")!=null && !request.getParameter("HV0406").equals("")){HV0406=request.getParameter("HV0406");}
if(request.getParameter("HV0407")!=null && !request.getParameter("HV0407").equals("")){HV0407=request.getParameter("HV0407");}
if(request.getParameter("HV0408")!=null && !request.getParameter("HV0408").equals("")){HV0408=request.getParameter("HV0408");}
if(request.getParameter("HV0409")!=null && !request.getParameter("HV0409").equals("")){HV0409=request.getParameter("HV0409");}
if(request.getParameter("HV0410")!=null && !request.getParameter("HV0410").equals("")){HV0410=request.getParameter("HV0410");}
if(request.getParameter("HV0411")!=null && !request.getParameter("HV0411").equals("")){HV0411=request.getParameter("HV0411");}
if(request.getParameter("HV0412")!=null && !request.getParameter("HV0412").equals("")){HV0412=request.getParameter("HV0412");}
if(request.getParameter("HV0413")!=null && !request.getParameter("HV0413").equals("")){HV0413=request.getParameter("HV0413");}
if(request.getParameter("HV0414")!=null && !request.getParameter("HV0414").equals("")){HV0414=request.getParameter("HV0414");}
if(request.getParameter("HV0415")!=null && !request.getParameter("HV0415").equals("")){HV0415=request.getParameter("HV0415");}

if(request.getParameter("HV0501")!=null && !request.getParameter("HV0501").equals("")){HV0501=request.getParameter("HV0501");}
if(request.getParameter("HV0502")!=null && !request.getParameter("HV0502").equals("")){HV0502=request.getParameter("HV0502");}
if(request.getParameter("HV0503")!=null && !request.getParameter("HV0503").equals("")){HV0503=request.getParameter("HV0503");}
if(request.getParameter("HV0504")!=null && !request.getParameter("HV0504").equals("")){HV0504=request.getParameter("HV0504");}
if(request.getParameter("HV0505")!=null && !request.getParameter("HV0505").equals("")){HV0505=request.getParameter("HV0505");}
if(request.getParameter("HV0506")!=null && !request.getParameter("HV0506").equals("")){HV0506=request.getParameter("HV0506");}
if(request.getParameter("HV0507")!=null && !request.getParameter("HV0507").equals("")){HV0507=request.getParameter("HV0507");}
if(request.getParameter("HV0508")!=null && !request.getParameter("HV0508").equals("")){HV0508=request.getParameter("HV0508");}
if(request.getParameter("HV0509")!=null && !request.getParameter("HV0509").equals("")){HV0509=request.getParameter("HV0509");}
if(request.getParameter("HV0510")!=null && !request.getParameter("HV0510").equals("")){HV0510=request.getParameter("HV0510");}
if(request.getParameter("HV0511")!=null && !request.getParameter("HV0511").equals("")){HV0511=request.getParameter("HV0511");}
if(request.getParameter("HV0512")!=null && !request.getParameter("HV0512").equals("")){HV0512=request.getParameter("HV0512");}
if(request.getParameter("HV0513")!=null && !request.getParameter("HV0513").equals("")){HV0513=request.getParameter("HV0513");}
if(request.getParameter("HV0514")!=null && !request.getParameter("HV0514").equals("")){HV0514=request.getParameter("HV0514");}

if(request.getParameter("HV0601")!=null && !request.getParameter("HV0601").equals("")){HV0601=request.getParameter("HV0601");}
if(request.getParameter("HV0602")!=null && !request.getParameter("HV0602").equals("")){HV0602=request.getParameter("HV0602");}
if(request.getParameter("HV0605")!=null && !request.getParameter("HV0605").equals("")){HV0605=request.getParameter("HV0605");}
         
          
          
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
     String dqaid="";
      System.out.println("++++++++++++++++++++++++++++++++here++++++++++++++++++++++++++++++++++++++++++++");
     String checker="SELECT id FROM dqa WHERE year='"+year+"' && month='"+month+"' && facilityid='"+facilityId+"' && form='moh731' LIMIT 1";
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
    conn.pst.setString(1, id);
    conn.pst.setString(2, "moh731");
    conn.pst.setString(3, facilityId);
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
     
     response.sendRedirect("Form731.jsp");
//          }
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
