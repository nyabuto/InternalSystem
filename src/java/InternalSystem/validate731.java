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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        String data,id;
String facilityId,year,month;
String HIV_CT,PMTCT,CT,VMMC,PEP,Blood;
 year="";
        month="";
        facilityId="";
int HV0101,HV0102,HV0103,HV0105,HV0106,HV0107,HV0108,HV0109,HV0110,HV0111,HV0112,HV0113,HV0114,
        HV0115,HV0116;
int HV0201,HV0202,HV0203,HV0204,HV0205,HV0206,HV0207,HV0208,HV0209,HV0210,HV0211,HV0212,HV0213,
HV0214,HV0215,HV0216,HV0217,HV0218,HV0219,HV0220,HV0221,HV0224,HV0225,HV0226,HV0227,HV0228,HV0229,
        HV0230,HV0231,HV0232,HV0233,HV0234,HV0235,HV0236,HV0237,HV0238,HV0239,HV0240,HV0241,HV0242,
        HV0243,HV0244;
int HV0301,HV0302,HV0303,HV0304,HV0305,HV0306,HV0307,HV0308,HV0309,HV0310,HV0311,HV0312,HV0313,HV0314,
        HV0315,HV0316,HV0317,HV0318,HV0319,HV0320,HV0321,HV0322,HV0323,HV0324,HV0325,HV0326,HV0327,HV0328,
        HV0329,HV0330,HV0331,HV0332,HV0333,HV0334,HV0335,HV0336,HV0337,HV0338,HV0339,
        HV0345,HV0346,HV0347,HV0348,HV0349,HV0350,HV0351,HV0352,HV0353,
        HV0354,HV0355,HV0904,HV0905,HV0370,HV0371,HV0372,HV0373;
int HV0401,HV0402,HV0403,HV0406,HV0407,HV0408,HV0409,HV0410,HV0411,HV0412,HV0413,HV0414,HV0415;
int HV0501,HV0502,HV0503,HV0504,HV0505,HV0506,HV0507,HV0508,HV0509,HV0510,HV0511,HV0512,HV0513,HV0514;
int HV0601,HV0602,HV0605;
String data_elements,description;
int HV0340_1,HV0341_1,HV0342_1,HV0343_1,HV0344_1;
int HV0340,HV0341,HV0342,HV0343,HV0344;
        
        
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
        HV0115=HV0116=0;
HV0201=HV0202=HV0203=HV0204=HV0205=HV0206=HV0207=HV0208=HV0209=HV0210=HV0211=HV0212=HV0213=
HV0214=HV0215=HV0216=HV0217=HV0218=HV0219=HV0220=HV0221=HV0224=HV0225=HV0226=HV0227=HV0228=HV0229=
        HV0230=HV0231=HV0232=HV0233=HV0234=HV0235=HV0236=HV0237=HV0238=HV0239=HV0240=HV0241=HV0242=
        HV0243=HV0244=0;
HV0301=HV0302=HV0303=HV0304=HV0305=HV0306=HV0307=HV0308=HV0309=HV0310=HV0311=HV0312=HV0313=HV0314=
        HV0315=HV0316=HV0317=HV0318=HV0319=HV0320=HV0321=HV0322=HV0323=HV0324=HV0325=HV0326=HV0327=HV0328=
        HV0329=HV0330=HV0331=HV0332=HV0333=HV0334=HV0335=HV0336=HV0337=HV0338=HV0339=
        HV0345=HV0346=HV0347=HV0348=HV0349=HV0350=HV0351=HV0352=HV0353=
        HV0354=HV0355=HV0904=HV0905=HV0370=HV0371=HV0372=HV0373=0;
HV0401=HV0402=HV0403=HV0406=HV0407=HV0408=HV0409=HV0410=HV0411=HV0412=HV0413=HV0414=HV0415=0;
 HV0501=HV0502=HV0503=HV0504=HV0505=HV0506=HV0507=HV0508=HV0509=HV0510=HV0511=HV0512=HV0513=HV0514=0;
 HV0601=HV0602=HV0605=0;
 
HV0340_1=HV0341_1=HV0342_1=HV0343_1=HV0344_1=0;
HV0340=HV0341=HV0342=HV0343=HV0344=0;
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
            System.out.println("**********"+request.getParameter("HV00229"));
            System.out.println("**********"+request.getParameter("HV00230"));
  if(request.getParameter("HV0101")!=null && !request.getParameter("HV0101").equals("")){HV0101=Integer.parseInt(request.getParameter("HV0101"));}
  if(request.getParameter("HV0102")!=null && !request.getParameter("HV0102").equals("")){HV0102=Integer.parseInt(request.getParameter("HV0102"));}
  if(request.getParameter("HV0103")!=null && !request.getParameter("HV0103").equals("")){HV0103=Integer.parseInt(request.getParameter("HV0103"));}
  if(request.getParameter("HV0105")!=null && !request.getParameter("HV0105").equals("")){HV0105=Integer.parseInt(request.getParameter("HV0105"));}
  if(request.getParameter("HV0106")!=null && !request.getParameter("HV0106").equals("")){HV0106=Integer.parseInt(request.getParameter("HV0106"));}
  if(request.getParameter("HV0107")!=null && !request.getParameter("HV0107").equals("")){HV0107=Integer.parseInt(request.getParameter("HV0107"));}
  if(request.getParameter("HV0108")!=null && !request.getParameter("HV0108").equals("")){HV0108=Integer.parseInt(request.getParameter("HV0108"));}
  if(request.getParameter("HV0109")!=null && !request.getParameter("HV0109").equals("")){HV0109=Integer.parseInt(request.getParameter("HV0109"));}
  if(request.getParameter("HV0110")!=null && !request.getParameter("HV0110").equals("")){HV0110=Integer.parseInt(request.getParameter("HV0110"));}
  if(request.getParameter("HV0111")!=null && !request.getParameter("HV0111").equals("")){HV0111=Integer.parseInt(request.getParameter("HV0111"));}
  if(request.getParameter("HV0112")!=null && !request.getParameter("HV0112").equals("")){HV0112=Integer.parseInt(request.getParameter("HV0112"));}
  if(request.getParameter("HV0113")!=null && !request.getParameter("HV0113").equals("")){HV0113=Integer.parseInt(request.getParameter("HV0113"));}
  if(request.getParameter("HV0114")!=null && !request.getParameter("HV0114").equals("")){HV0114=Integer.parseInt(request.getParameter("HV0114"));}
  if(request.getParameter("HV0115")!=null && !request.getParameter("HV0115").equals("")){HV0115=Integer.parseInt(request.getParameter("HV0115"));}
  if(request.getParameter("HV0116")!=null && !request.getParameter("HV0116").equals("")){HV0116=Integer.parseInt(request.getParameter("HV0116"));}
 
if(request.getParameter("HV0201")!=null && !request.getParameter("HV0201").equals("")){HV0201=Integer.parseInt(request.getParameter("HV0201"));}
if(request.getParameter("HV0202")!=null && !request.getParameter("HV0202").equals("")){HV0202=Integer.parseInt(request.getParameter("HV0202"));}
if(request.getParameter("HV0203")!=null && !request.getParameter("HV0203").equals("")){HV0203=Integer.parseInt(request.getParameter("HV0203"));}
if(request.getParameter("HV0204")!=null && !request.getParameter("HV0204").equals("")){HV0204=Integer.parseInt(request.getParameter("HV0204"));}
if(request.getParameter("HV0205")!=null && !request.getParameter("HV0205").equals("")){HV0205=Integer.parseInt(request.getParameter("HV0205"));}
if(request.getParameter("HV0206")!=null && !request.getParameter("HV0206").equals("")){HV0206=Integer.parseInt(request.getParameter("HV0206"));}
if(request.getParameter("HV0207")!=null && !request.getParameter("HV0207").equals("")){HV0207=Integer.parseInt(request.getParameter("HV0207"));}
if(request.getParameter("HV0208")!=null && !request.getParameter("HV0208").equals("")){HV0208=Integer.parseInt(request.getParameter("HV0208"));}
if(request.getParameter("HV0209")!=null && !request.getParameter("HV0209").equals("")){HV0209=Integer.parseInt(request.getParameter("HV0209"));}
if(request.getParameter("HV0210")!=null && !request.getParameter("HV0210").equals("")){HV0210=Integer.parseInt(request.getParameter("HV0210"));}
if(request.getParameter("HV0211")!=null && !request.getParameter("HV0211").equals("")){HV0211=Integer.parseInt(request.getParameter("HV0211"));}
if(request.getParameter("HV0212")!=null && !request.getParameter("HV0212").equals("")){HV0212=Integer.parseInt(request.getParameter("HV0212"));}
if(request.getParameter("HV0213")!=null && !request.getParameter("HV0213").equals("")){HV0213=Integer.parseInt(request.getParameter("HV0213"));}
if(request.getParameter("HV0214")!=null && !request.getParameter("HV0214").equals("")){HV0214=Integer.parseInt(request.getParameter("HV0214"));}
if(request.getParameter("HV0215")!=null && !request.getParameter("HV0215").equals("")){HV0215=Integer.parseInt(request.getParameter("HV0215"));}
if(request.getParameter("HV0216")!=null && !request.getParameter("HV0216").equals("")){HV0216=Integer.parseInt(request.getParameter("HV0216"));}
if(request.getParameter("HV0217")!=null && !request.getParameter("HV0217").equals("")){HV0217=Integer.parseInt(request.getParameter("HV0217"));}
if(request.getParameter("HV0218")!=null && !request.getParameter("HV0218").equals("")){HV0218=Integer.parseInt(request.getParameter("HV0218"));}
if(request.getParameter("HV0219")!=null && !request.getParameter("HV0219").equals("")){HV0219=Integer.parseInt(request.getParameter("HV0219"));}
if(request.getParameter("HV0220")!=null && !request.getParameter("HV0220").equals("")){HV0220=Integer.parseInt(request.getParameter("HV0220"));}
if(request.getParameter("HV0221")!=null && !request.getParameter("HV0221").equals("")){HV0221=Integer.parseInt(request.getParameter("HV0221"));}
if(request.getParameter("HV0224")!=null && !request.getParameter("HV0224").equals("")){HV0224=Integer.parseInt(request.getParameter("HV0224"));}
if(request.getParameter("HV0225")!=null && !request.getParameter("HV0225").equals("")){HV0225=Integer.parseInt(request.getParameter("HV0225"));}
if(request.getParameter("HV0226")!=null && !request.getParameter("HV0226").equals("")){HV0226=Integer.parseInt(request.getParameter("HV0226"));}
if(request.getParameter("HV0227")!=null && !request.getParameter("HV0227").equals("")){HV0227=Integer.parseInt(request.getParameter("HV0227"));}
if(request.getParameter("HV0228")!=null && !request.getParameter("HV0228").equals("")){HV0228=Integer.parseInt(request.getParameter("HV0228"));}
if(request.getParameter("HV00229")!=null && !request.getParameter("HV00229").equals("")){HV0229=Integer.parseInt(request.getParameter("HV00229"));}
if(request.getParameter("HV00230")!=null && !request.getParameter("HV00230").equals("")){HV0230=Integer.parseInt(request.getParameter("HV00230"));}
if(request.getParameter("HV0231")!=null && !request.getParameter("HV0231").equals("")){HV0231=Integer.parseInt(request.getParameter("HV0231"));}
if(request.getParameter("HV0232")!=null && !request.getParameter("HV0232").equals("")){HV0232=Integer.parseInt(request.getParameter("HV0232"));}
if(request.getParameter("HV0233")!=null && !request.getParameter("HV0233").equals("")){HV0233=Integer.parseInt(request.getParameter("HV0233"));}
if(request.getParameter("HV0234")!=null && !request.getParameter("HV0234").equals("")){HV0234=Integer.parseInt(request.getParameter("HV0234"));}
if(request.getParameter("HV0235")!=null && !request.getParameter("HV0235").equals("")){HV0235=Integer.parseInt(request.getParameter("HV0235"));}
if(request.getParameter("HV0236")!=null && !request.getParameter("HV0236").equals("")){HV0236=Integer.parseInt(request.getParameter("HV0236"));}
if(request.getParameter("HV0237")!=null && !request.getParameter("HV0237").equals("")){HV0237=Integer.parseInt(request.getParameter("HV0237"));}
if(request.getParameter("HV0238")!=null && !request.getParameter("HV0238").equals("")){HV0238=Integer.parseInt(request.getParameter("HV0238"));}
if(request.getParameter("HV0239")!=null && !request.getParameter("HV0239").equals("")){HV0239=Integer.parseInt(request.getParameter("HV0239"));}
if(request.getParameter("HV0240")!=null && !request.getParameter("HV0240").equals("")){HV0240=Integer.parseInt(request.getParameter("HV0240"));}
if(request.getParameter("HV0241")!=null && !request.getParameter("HV0241").equals("")){HV0241=Integer.parseInt(request.getParameter("HV0241"));}
if(request.getParameter("HV0242")!=null && !request.getParameter("HV0242").equals("")){HV0242=Integer.parseInt(request.getParameter("HV0242"));}
if(request.getParameter("HV0243")!=null && !request.getParameter("HV0243").equals("")){HV0243=Integer.parseInt(request.getParameter("HV0243"));}
if(request.getParameter("HV0244")!=null && !request.getParameter("HV0244").equals("")){HV0244=Integer.parseInt(request.getParameter("HV0244"));}

if(request.getParameter("HV0301")!=null && !request.getParameter("HV0301").equals("")){HV0301=Integer.parseInt(request.getParameter("HV0301"));}
if(request.getParameter("HV0302")!=null && !request.getParameter("HV0302").equals("")){HV0302=Integer.parseInt(request.getParameter("HV0302"));}
if(request.getParameter("HV0303")!=null && !request.getParameter("HV0303").equals("")){HV0303=Integer.parseInt(request.getParameter("HV0303"));}
if(request.getParameter("HV0304")!=null && !request.getParameter("HV0304").equals("")){HV0304=Integer.parseInt(request.getParameter("HV0304"));}
if(request.getParameter("HV0305")!=null && !request.getParameter("HV0305").equals("")){HV0305=Integer.parseInt(request.getParameter("HV0305"));}
if(request.getParameter("HV0306")!=null && !request.getParameter("HV0306").equals("")){HV0306=Integer.parseInt(request.getParameter("HV0306"));}
if(request.getParameter("HV0307")!=null && !request.getParameter("HV0307").equals("")){HV0307=Integer.parseInt(request.getParameter("HV0307"));}
if(request.getParameter("HV0308")!=null && !request.getParameter("HV0308").equals("")){HV0308=Integer.parseInt(request.getParameter("HV0308"));}
if(request.getParameter("HV0309")!=null && !request.getParameter("HV0309").equals("")){HV0309=Integer.parseInt(request.getParameter("HV0309"));}
if(request.getParameter("HV0310")!=null && !request.getParameter("HV0310").equals("")){HV0310=Integer.parseInt(request.getParameter("HV0310"));}
if(request.getParameter("HV0311")!=null && !request.getParameter("HV0311").equals("")){HV0311=Integer.parseInt(request.getParameter("HV0311"));}
if(request.getParameter("HV0312")!=null && !request.getParameter("HV0312").equals("")){HV0312=Integer.parseInt(request.getParameter("HV0312"));}
if(request.getParameter("HV0313")!=null && !request.getParameter("HV0313").equals("")){HV0313=Integer.parseInt(request.getParameter("HV0313"));}
if(request.getParameter("HV0314")!=null && !request.getParameter("HV0314").equals("")){HV0314=Integer.parseInt(request.getParameter("HV0314"));}
if(request.getParameter("HV0315")!=null && !request.getParameter("HV0315").equals("")){HV0315=Integer.parseInt(request.getParameter("HV0315"));}
if(request.getParameter("HV0316")!=null && !request.getParameter("HV0316").equals("")){HV0316=Integer.parseInt(request.getParameter("HV0316"));}
if(request.getParameter("HV0317")!=null && !request.getParameter("HV0317").equals("")){HV0317=Integer.parseInt(request.getParameter("HV0317"));}
if(request.getParameter("HV0318")!=null && !request.getParameter("HV0318").equals("")){HV0318=Integer.parseInt(request.getParameter("HV0318"));}
if(request.getParameter("HV0319")!=null && !request.getParameter("HV0319").equals("")){HV0319=Integer.parseInt(request.getParameter("HV0319"));}
if(request.getParameter("HV0320")!=null && !request.getParameter("HV0320").equals("")){HV0320=Integer.parseInt(request.getParameter("HV0320"));}
if(request.getParameter("HV0321")!=null && !request.getParameter("HV0321").equals("")){HV0321=Integer.parseInt(request.getParameter("HV0321"));}
if(request.getParameter("HV0322")!=null && !request.getParameter("HV0322").equals("")){HV0322=Integer.parseInt(request.getParameter("HV0322"));}
if(request.getParameter("HV0323")!=null && !request.getParameter("HV0323").equals("")){HV0323=Integer.parseInt(request.getParameter("HV0323"));}
if(request.getParameter("HV0324")!=null && !request.getParameter("HV0324").equals("")){HV0324=Integer.parseInt(request.getParameter("HV0324"));}
if(request.getParameter("HV0325")!=null && !request.getParameter("HV0325").equals("")){HV0325=Integer.parseInt(request.getParameter("HV0325"));}
if(request.getParameter("HV0326")!=null && !request.getParameter("HV0326").equals("")){HV0326=Integer.parseInt(request.getParameter("HV0326"));}
if(request.getParameter("HV0327")!=null && !request.getParameter("HV0327").equals("")){HV0327=Integer.parseInt(request.getParameter("HV0327"));}
if(request.getParameter("HV0328")!=null && !request.getParameter("HV0328").equals("")){HV0328=Integer.parseInt(request.getParameter("HV0328"));}
if(request.getParameter("HV0329")!=null && !request.getParameter("HV0329").equals("")){HV0329=Integer.parseInt(request.getParameter("HV0329"));}
if(request.getParameter("HV0330")!=null && !request.getParameter("HV0330").equals("")){HV0330=Integer.parseInt(request.getParameter("HV0330"));}
if(request.getParameter("HV0331")!=null && !request.getParameter("HV0331").equals("")){HV0331=Integer.parseInt(request.getParameter("HV0331"));}
if(request.getParameter("HV0332")!=null && !request.getParameter("HV0332").equals("")){HV0332=Integer.parseInt(request.getParameter("HV0332"));}
if(request.getParameter("HV0333")!=null && !request.getParameter("HV0333").equals("")){HV0333=Integer.parseInt(request.getParameter("HV0333"));}
if(request.getParameter("HV0334")!=null && !request.getParameter("HV0334").equals("")){HV0334=Integer.parseInt(request.getParameter("HV0334"));}
if(request.getParameter("HV0335")!=null && !request.getParameter("HV0335").equals("")){HV0335=Integer.parseInt(request.getParameter("HV0335"));}
if(request.getParameter("HV0336")!=null && !request.getParameter("HV0336").equals("")){HV0336=Integer.parseInt(request.getParameter("HV0336"));}
if(request.getParameter("HV0337")!=null && !request.getParameter("HV0337").equals("")){HV0337=Integer.parseInt(request.getParameter("HV0337"));}
if(request.getParameter("HV0338")!=null && !request.getParameter("HV0338").equals("")){HV0338=Integer.parseInt(request.getParameter("HV0338"));}
if(request.getParameter("HV0339")!=null && !request.getParameter("HV0339").equals("")){HV0339=Integer.parseInt(request.getParameter("HV0339"));}
if(request.getParameter("HV0340")!=null && !request.getParameter("HV0340").equals("")){HV0340=Integer.parseInt(request.getParameter("HV0340"));}
if(request.getParameter("HV0341")!=null && !request.getParameter("HV0341").equals("")){HV0341=Integer.parseInt(request.getParameter("HV0341"));}
if(request.getParameter("HV0342")!=null && !request.getParameter("HV0342").equals("")){HV0342=Integer.parseInt(request.getParameter("HV0342"));}
if(request.getParameter("HV0343")!=null && !request.getParameter("HV0343").equals("")){HV0343=Integer.parseInt(request.getParameter("HV0343"));}
if(request.getParameter("HV0344")!=null && !request.getParameter("HV0344").equals("")){HV0344=Integer.parseInt(request.getParameter("HV0344"));}
if(request.getParameter("HV0345")!=null && !request.getParameter("HV0345").equals("")){HV0345=Integer.parseInt(request.getParameter("HV0345"));}
if(request.getParameter("HV0346")!=null && !request.getParameter("HV0346").equals("")){HV0346=Integer.parseInt(request.getParameter("HV0346"));}
if(request.getParameter("HV0347")!=null && !request.getParameter("HV0347").equals("")){HV0347=Integer.parseInt(request.getParameter("HV0347"));}
if(request.getParameter("HV0348")!=null && !request.getParameter("HV0348").equals("")){HV0348=Integer.parseInt(request.getParameter("HV0348"));}
if(request.getParameter("HV0349")!=null && !request.getParameter("HV0349").equals("")){HV0349=Integer.parseInt(request.getParameter("HV0349"));}
if(request.getParameter("HV0350")!=null && !request.getParameter("HV0350").equals("")){HV0350=Integer.parseInt(request.getParameter("HV0350"));}
if(request.getParameter("HV0351")!=null && !request.getParameter("HV0351").equals("")){HV0351=Integer.parseInt(request.getParameter("HV0351"));}
if(request.getParameter("HV0352")!=null && !request.getParameter("HV0352").equals("")){HV0352=Integer.parseInt(request.getParameter("HV0352"));}
if(request.getParameter("HV0353")!=null && !request.getParameter("HV0353").equals("")){HV0353=Integer.parseInt(request.getParameter("HV0353"));}
if(request.getParameter("HV0354")!=null && !request.getParameter("HV0354").equals("")){HV0354=Integer.parseInt(request.getParameter("HV0354"));}
if(request.getParameter("HV0355")!=null && !request.getParameter("HV0355").equals("")){HV0355=Integer.parseInt(request.getParameter("HV0355"));}
if(request.getParameter("HV0904")!=null && !request.getParameter("HV0904").equals("")){HV0904=Integer.parseInt(request.getParameter("HV0904"));}
if(request.getParameter("HV0905")!=null && !request.getParameter("HV0905").equals("")){HV0905=Integer.parseInt(request.getParameter("HV0905"));}
if(request.getParameter("HV0370")!=null && !request.getParameter("HV0370").equals("")){HV0370=Integer.parseInt(request.getParameter("HV0370"));}
if(request.getParameter("HV0371")!=null && !request.getParameter("HV0371").equals("")){HV0371=Integer.parseInt(request.getParameter("HV0371"));}
if(request.getParameter("HV0372")!=null && !request.getParameter("HV0372").equals("")){HV0372=Integer.parseInt(request.getParameter("HV0372"));}
if(request.getParameter("HV0373")!=null && !request.getParameter("HV0373").equals("")){HV0373=Integer.parseInt(request.getParameter("HV0373"));}

if(request.getParameter("HV0401")!=null && !request.getParameter("HV0401").equals("")){HV0401=Integer.parseInt(request.getParameter("HV0401"));}
if(request.getParameter("HV0402")!=null && !request.getParameter("HV0402").equals("")){HV0402=Integer.parseInt(request.getParameter("HV0402"));}
if(request.getParameter("HV0403")!=null && !request.getParameter("HV0403").equals("")){HV0403=Integer.parseInt(request.getParameter("HV0403"));}
if(request.getParameter("HV0406")!=null && !request.getParameter("HV0406").equals("")){HV0406=Integer.parseInt(request.getParameter("HV0406"));}
if(request.getParameter("HV0407")!=null && !request.getParameter("HV0407").equals("")){HV0407=Integer.parseInt(request.getParameter("HV0407"));}
if(request.getParameter("HV0408")!=null && !request.getParameter("HV0408").equals("")){HV0408=Integer.parseInt(request.getParameter("HV0408"));}
if(request.getParameter("HV0409")!=null && !request.getParameter("HV0409").equals("")){HV0409=Integer.parseInt(request.getParameter("HV0409"));}
if(request.getParameter("HV0410")!=null && !request.getParameter("HV0410").equals("")){HV0410=Integer.parseInt(request.getParameter("HV0410"));}
if(request.getParameter("HV0411")!=null && !request.getParameter("HV0411").equals("")){HV0411=Integer.parseInt(request.getParameter("HV0411"));}
if(request.getParameter("HV0412")!=null && !request.getParameter("HV0412").equals("")){HV0412=Integer.parseInt(request.getParameter("HV0412"));}
if(request.getParameter("HV0413")!=null && !request.getParameter("HV0413").equals("")){HV0413=Integer.parseInt(request.getParameter("HV0413"));}
if(request.getParameter("HV0414")!=null && !request.getParameter("HV0414").equals("")){HV0414=Integer.parseInt(request.getParameter("HV0414"));}
if(request.getParameter("HV0415")!=null && !request.getParameter("HV0415").equals("")){HV0415=Integer.parseInt(request.getParameter("HV0415"));}

if(request.getParameter("HV0501")!=null && !request.getParameter("HV0501").equals("")){HV0501=Integer.parseInt(request.getParameter("HV0501"));}
if(request.getParameter("HV0502")!=null && !request.getParameter("HV0502").equals("")){HV0502=Integer.parseInt(request.getParameter("HV0502"));}
if(request.getParameter("HV0503")!=null && !request.getParameter("HV0503").equals("")){HV0503=Integer.parseInt(request.getParameter("HV0503"));}
if(request.getParameter("HV0504")!=null && !request.getParameter("HV0504").equals("")){HV0504=Integer.parseInt(request.getParameter("HV0504"));}
if(request.getParameter("HV0505")!=null && !request.getParameter("HV0505").equals("")){HV0505=Integer.parseInt(request.getParameter("HV0505"));}
if(request.getParameter("HV0506")!=null && !request.getParameter("HV0506").equals("")){HV0506=Integer.parseInt(request.getParameter("HV0506"));}
if(request.getParameter("HV0507")!=null && !request.getParameter("HV0507").equals("")){HV0507=Integer.parseInt(request.getParameter("HV0507"));}
if(request.getParameter("HV0508")!=null && !request.getParameter("HV0508").equals("")){HV0508=Integer.parseInt(request.getParameter("HV0508"));}
if(request.getParameter("HV0509")!=null && !request.getParameter("HV0509").equals("")){HV0509=Integer.parseInt(request.getParameter("HV0509"));}
if(request.getParameter("HV0510")!=null && !request.getParameter("HV0510").equals("")){HV0510=Integer.parseInt(request.getParameter("HV0510"));}
if(request.getParameter("HV0511")!=null && !request.getParameter("HV0511").equals("")){HV0511=Integer.parseInt(request.getParameter("HV0511"));}
if(request.getParameter("HV0512")!=null && !request.getParameter("HV0512").equals("")){HV0512=Integer.parseInt(request.getParameter("HV0512"));}
if(request.getParameter("HV0513")!=null && !request.getParameter("HV0513").equals("")){HV0513=Integer.parseInt(request.getParameter("HV0513"));}
if(request.getParameter("HV0514")!=null && !request.getParameter("HV0514").equals("")){HV0514=Integer.parseInt(request.getParameter("HV0514"));}

if(request.getParameter("HV0601")!=null && !request.getParameter("HV0601").equals("")){HV0601=Integer.parseInt(request.getParameter("HV0601"));}
if(request.getParameter("HV0602")!=null && !request.getParameter("HV0602").equals("")){HV0602=Integer.parseInt(request.getParameter("HV0602"));}
if(request.getParameter("HV0605")!=null && !request.getParameter("HV0605").equals("")){HV0605=Integer.parseInt(request.getParameter("HV0605"));}
         
// CUMULATIVES EVER PREVIOUS MONTH==========================================
if(request.getParameter("HV0340_1")!=null && !request.getParameter("HV0340_1").equals("")){HV0340_1=Integer.parseInt(request.getParameter("HV0340_1"));}
if(request.getParameter("HV0341_1")!=null && !request.getParameter("HV0341_1").equals("")){HV0341_1=Integer.parseInt(request.getParameter("HV0341_1"));}
if(request.getParameter("HV0342_1")!=null && !request.getParameter("HV0342_1").equals("")){HV0342_1=Integer.parseInt(request.getParameter("HV0342_1"));}
if(request.getParameter("HV0343_1")!=null && !request.getParameter("HV0343_1").equals("")){HV0343_1=Integer.parseInt(request.getParameter("HV0343_1"));}
if(request.getParameter("HV0344_1")!=null && !request.getParameter("HV0344_1").equals("")){HV0344_1=Integer.parseInt(request.getParameter("HV0344_1"));}
 
HV0103=HV0101+HV0102;
HV0116=HV0110+HV0111+HV0112+HV0113+HV0114+HV0115;

System.out.println("hv0201 : "+HV0201+" HV0202 : "+HV0202+" HV0203 : "+HV0203+" HV0204 : "+HV0204);
HV0204=HV0201+HV0202+HV0203;
HV0209=HV0205+HV0206+HV0207+HV0208;
HV0210=HV0204+HV0205;
HV0217=HV0213+HV0214+HV0215+HV0216;
HV0220=HV0218+HV0219;
HV0228=HV0224+HV0225+HV0226;
HV0232=HV0229+HV0230+HV0231;
HV0236=HV0233+HV0234+HV0235;
HV0240=HV0237+HV0238+HV0239;
HV0244=HV0241+HV0242+HV0243;

System.out.println("hv0201 : "+HV0201+" HV0202 : "+HV0202+" HV0203 : "+HV0203+" HV0204 : "+HV0204);
HV0307=HV0303+HV0304+HV0305+HV0306;
HV0313=HV0309+HV0310+HV0311+HV0312;
HV0319=HV0315+HV0316+HV0317+HV0318;
HV0325=HV0321+HV0322+HV0323+HV0324;
HV0333=HV0329+HV0330+HV0331+HV0332;
HV0339=HV0335+HV0336+HV0337+HV0338;
HV0344=HV0340+HV0341+HV0342+HV0343;
HV0349=HV0346+HV0347+HV0348;
HV0354=HV0350+HV0351+HV0352+HV0353;
HV0373=HV0371+HV0372;


HV0406=HV0401+HV0402;
HV0414=HV0410+HV0411;
HV0415=HV0412+HV0413;

HV0507=HV0501+HV0502+HV0503+HV0504+HV0505+HV0506;
HV0514=HV0508+HV0509+HV0510+HV0511+HV0512+HV0513;
System.out.println("hv0307 : "+HV0307);       
        
HV0340=HV0321+HV0340_1;
HV0341=HV0322+HV0341_1;
HV0342=HV0323+HV0342_1;
HV0343=HV0324+HV0343_1;
HV0344=HV0325+HV0344_1;

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
  
   conn.pst.setInt(1, HV0101);
   conn.pst.setInt(2, HV0102);
   conn.pst.setInt(3, HV0103);
   conn.pst.setInt(4, HV0105);
   conn.pst.setInt(5, HV0106);
   conn.pst.setInt(6, HV0107);
   conn.pst.setInt(7, HV0108);
   conn.pst.setInt(8, HV0109);
   conn.pst.setInt(9, HV0110);
   conn.pst.setInt(10, HV0111);
   conn.pst.setInt(11, HV0112);
   conn.pst.setInt(12, HV0113);
   conn.pst.setInt(13, HV0114);
   conn.pst.setInt(14, HV0115);
   conn.pst.setInt(15, HV0116);
   
   conn.pst.setInt(16, HV0201);
   conn.pst.setInt(17, HV0202);
   conn.pst.setInt(18, HV0203);
   conn.pst.setInt(19, HV0204);
   conn.pst.setInt(20, HV0205);
   conn.pst.setInt(21, HV0206);
   conn.pst.setInt(22, HV0207);
   conn.pst.setInt(23, HV0208);
   conn.pst.setInt(24, HV0209);
   conn.pst.setInt(25, HV0210);
   conn.pst.setInt(26, HV0211);
   conn.pst.setInt(27, HV0212);
   conn.pst.setInt(28, HV0213);
   conn.pst.setInt(29, HV0214);
   conn.pst.setInt(30, HV0215);
   conn.pst.setInt(31, HV0216);
   conn.pst.setInt(32, HV0217);
   conn.pst.setInt(33, HV0218);
   conn.pst.setInt(34, HV0219);
   conn.pst.setInt(35, HV0220);
   conn.pst.setInt(36, HV0221);
   conn.pst.setInt(37, HV0224);
   conn.pst.setInt(38, HV0225);
   conn.pst.setInt(39, HV0226);
   conn.pst.setInt(40, HV0227);
   conn.pst.setInt(41, HV0228);
   conn.pst.setInt(42, HV0229);
   conn.pst.setInt(43, HV0230);
   conn.pst.setInt(44, HV0231);
   conn.pst.setInt(45, HV0232);
   conn.pst.setInt(46, HV0233);
   conn.pst.setInt(47, HV0234);
   conn.pst.setInt(48, HV0235);
   conn.pst.setInt(49, HV0236);
   conn.pst.setInt(50, HV0237);
   conn.pst.setInt(51, HV0238);
   conn.pst.setInt(52, HV0239);
   conn.pst.setInt(53, HV0240);
   conn.pst.setInt(54, HV0241);
   conn.pst.setInt(55, HV0242);
   conn.pst.setInt(56, HV0243);
   conn.pst.setInt(57, HV0244);
 
   
   conn.pst.setInt(58, HV0301);
   conn.pst.setInt(59, HV0302);
   conn.pst.setInt(60, HV0303);
   conn.pst.setInt(61, HV0304);
   conn.pst.setInt(62, HV0305);
   conn.pst.setInt(63, HV0306);
   conn.pst.setInt(64, HV0307);
   conn.pst.setInt(65, HV0308);
   conn.pst.setInt(66, HV0309);
   conn.pst.setInt(67, HV0310);
   conn.pst.setInt(68, HV0311);
   conn.pst.setInt(69, HV0312);
   conn.pst.setInt(70, HV0313);
   conn.pst.setInt(71, HV0314);
   conn.pst.setInt(72, HV0315);
   conn.pst.setInt(73, HV0316);
   conn.pst.setInt(74, HV0317);
   conn.pst.setInt(75, HV0318);
   conn.pst.setInt(76, HV0319);
   conn.pst.setInt(77, HV0320);
   conn.pst.setInt(78, HV0321);
   conn.pst.setInt(79, HV0322);
   conn.pst.setInt(80, HV0323);
   conn.pst.setInt(81, HV0324);
   conn.pst.setInt(82, HV0325);
   conn.pst.setInt(83, HV0326);
   conn.pst.setInt(84, HV0327);
   conn.pst.setInt(85, HV0328);
   conn.pst.setInt(86, HV0329);
   conn.pst.setInt(87, HV0330);
   conn.pst.setInt(88, HV0331);
   conn.pst.setInt(89, HV0332);
   conn.pst.setInt(90, HV0333);
   conn.pst.setInt(91, HV0334);
   conn.pst.setInt(92, HV0335);
   conn.pst.setInt(93, HV0336);
   conn.pst.setInt(94, HV0337);
   conn.pst.setInt(95, HV0338);
   conn.pst.setInt(96, HV0339);
   conn.pst.setInt(97, HV0340);
   conn.pst.setInt(98, HV0341);
   conn.pst.setInt(99, HV0342);
   conn.pst.setInt(100, HV0343);
   conn.pst.setInt(101, HV0344);
   conn.pst.setInt(102, HV0345);
   conn.pst.setInt(103, HV0346);
   conn.pst.setInt(104, HV0347);
   conn.pst.setInt(105, HV0348);
   conn.pst.setInt(106, HV0349);
   conn.pst.setInt(107, HV0350);
   conn.pst.setInt(108, HV0351);
   conn.pst.setInt(109, HV0352);
   conn.pst.setInt(110, HV0353);
   conn.pst.setInt(111, HV0354);
   conn.pst.setInt(112, HV0355);
   conn.pst.setInt(113, HV0904);
   conn.pst.setInt(114, HV0905);
   conn.pst.setInt(115, HV0370);
   conn.pst.setInt(116, HV0371);
   conn.pst.setInt(117, HV0372);
   conn.pst.setInt(118, HV0373);   

   conn.pst.setInt(119, HV0401);
   conn.pst.setInt(120, HV0402);
   conn.pst.setInt(121, HV0403);
   conn.pst.setInt(122, HV0406);
   conn.pst.setInt(123, HV0407);
   conn.pst.setInt(124, HV0408);
   conn.pst.setInt(125, HV0409);
   conn.pst.setInt(126, HV0410);
   conn.pst.setInt(127, HV0411);
   conn.pst.setInt(128, HV0412);
   conn.pst.setInt(129, HV0413);
   conn.pst.setInt(130, HV0414);
   conn.pst.setInt(131, HV0415);

   conn.pst.setInt(132, HV0501);
   conn.pst.setInt(133, HV0502);
   conn.pst.setInt(134, HV0503);
   conn.pst.setInt(135, HV0504);
   conn.pst.setInt(136, HV0505);
   conn.pst.setInt(137, HV0506);
   conn.pst.setInt(138, HV0507);
   conn.pst.setInt(139, HV0508);
   conn.pst.setInt(140, HV0509);
   conn.pst.setInt(141, HV0510);
   conn.pst.setInt(142, HV0511);
   conn.pst.setInt(143, HV0512);
   conn.pst.setInt(144, HV0513);
   conn.pst.setInt(145, HV0514);
   
   conn.pst.setInt(146, HV0601);
   conn.pst.setInt(147, HV0602);
   conn.pst.setInt(148, HV0605);
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
    System.out.println("errors : "+data_elements+"   descr : "+description);
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
