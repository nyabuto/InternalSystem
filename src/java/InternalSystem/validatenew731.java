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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
          
        String data,id;
String facilityId,year,month;
String HIV_CT,PMTCT,CT,VMMC,PEP,Blood;
 year="";
        month="";
        facilityId="";
int HV0101,HV0102,HV0103,HV0104,HV0105,HV0106,HV0107,HV0108,HV0109,HV0110,HV0111,HV0112,HV0113,HV0114,
        HV0115,HV0116,HV0117,HV0118,HV0119,HV0120,HV0121,HV0122,HV0123,HV0124,HV0125,HV0126,
        HV0127,HV0128,HV0129,HV0130,HV0131,HV0132,HV0133,HV0134,HV0135,HV0136,HV0137,HV0138,HV0139,HV0140;

int HV0201,HV0202,HV0203,HV0204,HV0205,HV0206,HV0207,HV0208,HV0209,HV0210,HV0211,HV0212,HV0213,
        HV0214,HV0215,HV0216,HV0217,HV0218,HV0219,HV0220,HV0221,HV0222,HV0223,HV0224,HV0225,HV0226,HV0227,
        HV0228,HV0229,HV0230,HV0231,HV0232,HV0233,HV0234,HV0235,HV0236,HV0237,HV0238,HV0239,HV0240,HV0241,
        HV0242,HV0243,HV0244,HV0245,HV0246,HV0247,HV0248,HV0249,HV0250,HV0251,HV0252,HV0253,HV0254,HV0255,
        HV0256,HV0257,HV0258;


int HV03001,HV03002,HV03003,HV03004,HV03005,HV03006,HV03007,HV03008,HV03009,HV03010,HV03011,HV03012,HV03013,HV03014,
        HV03015,HV03016,HV03017,HV03018,HV03019,HV03020,HV03021,HV03022,HV03023,HV03024,HV03025,HV03026,HV03027,HV03028,
        HV03029,HV03030,HV03031,HV03032,HV03033,HV03034,HV03035,HV03036,HV03037,HV03038,HV03039,HV03045,HV03046,HV03047,
        HV03048,HV03049,HV03050,HV03051,HV03052,HV03053,HV03054,HV03055,HV03056,HV03057,HV03058,HV03059,HV03060,HV03061,
        HV03062,HV03063,HV03064,HV03065,HV03066,HV03067,HV03068,HV03069,HV03070,HV03071,HV03072,HV03073,HV03074,HV03075,
        HV03076,HV03077,HV03078,HV03079,HV03080,HV03081,HV03082,HV03083,HV03084,HV03085,HV03086,HV03087,HV03088,HV03089;

int HV0401,HV0402,HV0403,HV0404,HV0405,HV0406,HV0407,HV0408,HV0409,HV0410,HV0411,HV0412,HV0413,HV0414,
        HV0415,HV0416,HV0417;

int HV0501,HV0502,HV0503,HV0504,HV0505,HV0506;

int HV0601,HV0602,HV0603,HV0604,HV0605;

int HV03040_1,HV03041_1,HV03042_1,HV03043_1,HV03044_1;

int HV03040,HV03041,HV03042,HV03043,HV03044;
        
String data_elements,description;

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
      
        
            
    HV0101=HV0102=HV0104=HV0103=HV0105=HV0106=HV0107=HV0108=HV0109=HV0110=HV0111=HV0112=HV0113=HV0114=
    HV0115=HV0116=HV0117=HV0118=HV0119=HV0120=HV0121=HV0122=HV0123=HV0124=HV0125=HV0126=
    HV0127=HV0128=HV0129=HV0130=HV0131=HV0132=HV0133=HV0134=HV0135=HV0136=HV0137=HV0138=HV0139=
    HV0140=0;
 
    HV0201=HV0202=HV0203=HV0204=HV0205=HV0206=HV0207=HV0208=HV0209=HV0210=HV0211=HV0212=HV0213=
    HV0214=HV0215=HV0216=HV0217=HV0218=HV0219=HV0220=HV0221=HV0222=HV0223=HV0224=HV0225=HV0226=
    HV0227=HV0228=HV0229=HV0230=HV0231=HV0232=HV0233=HV0234=HV0235=HV0236=HV0237=HV0238=HV0239=
    HV0240=HV0241=HV0242=HV0243=HV0244=HV0245=HV0246=HV0247=HV0248=HV0249=HV0250=HV0251=HV0252=
    HV0253=HV0254=HV0255=HV0256=HV0257=HV0258=0;

    HV03001=HV03002=HV03003=HV03004=HV03005=HV03006=HV03007=HV03008=HV03009=HV03010=HV03011=HV03012=HV03013=HV03014=
    HV03015=HV03016=HV03017=HV03018=HV03019=HV03020=HV03021=HV03022=HV03023=HV03024=HV03025=HV03026=HV03027=HV03028=
    HV03029=HV03030=HV03031=HV03032=HV03033=HV03034=HV03035=HV03036=HV03037=HV03038=HV03039=HV03045=HV03046=HV03047=
    HV03048=HV03049=HV03050=HV03051=HV03052=HV03053=HV03054=HV03055=HV03056=HV03057=HV03058=HV03059=HV03060=HV03061=
    HV03062=HV03063=HV03064=HV03065=HV03066=HV03067=HV03068=HV03069=HV03070=HV03071=HV03072=HV03073=HV03074=HV03075=
    HV03076=HV03077=HV03078=HV03079=HV03080=HV03081=HV03082=HV03083=HV03084=HV03085=HV03086=HV03087=HV03088=HV03089=0;

    HV0401=HV0402=HV0403=HV0404=HV0405=HV0406=HV0407=HV0408=HV0409=HV0410=HV0411=HV0412=HV0413=HV0414=
    HV0415=HV0416=HV0417=0;
    HV0501=HV0502=HV0503=HV0504=HV0505=HV0506=0;
    HV0601=HV0602=HV0603=HV0604=HV0605=0;
 
    HV03040_1=HV03041_1=HV03042_1=HV03043_1=HV03044_1=0;
    HV03040=HV03041=HV03042=HV03043=HV03044=0;

 data_elements=request.getParameter("data_elements");
  description=request.getParameter("description");
            System.out.println("**********"+request.getParameter("HV00229"));
            System.out.println("**********"+request.getParameter("HV00230"));
  if(request.getParameter("HV0101")!=null && !request.getParameter("HV0101").equals("")){HV0101=Integer.parseInt(request.getParameter("HV0101"));}
  if(request.getParameter("HV0102")!=null && !request.getParameter("HV0102").equals("")){HV0102=Integer.parseInt(request.getParameter("HV0102"));}
  if(request.getParameter("HV0103")!=null && !request.getParameter("HV0103").equals("")){HV0103=Integer.parseInt(request.getParameter("HV0103"));}
  if(request.getParameter("HV0104")!=null && !request.getParameter("HV0104").equals("")){HV0104=Integer.parseInt(request.getParameter("HV0104"));}
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
  if(request.getParameter("HV0117")!=null && !request.getParameter("HV0117").equals("")){HV0117=Integer.parseInt(request.getParameter("HV0117"));}
  if(request.getParameter("HV0118")!=null && !request.getParameter("HV0118").equals("")){HV0118=Integer.parseInt(request.getParameter("HV0118"));}
  if(request.getParameter("HV0119")!=null && !request.getParameter("HV0119").equals("")){HV0119=Integer.parseInt(request.getParameter("HV0119"));}
  if(request.getParameter("HV0120")!=null && !request.getParameter("HV0120").equals("")){HV0120=Integer.parseInt(request.getParameter("HV0120"));}
  if(request.getParameter("HV0121")!=null && !request.getParameter("HV0121").equals("")){HV0121=Integer.parseInt(request.getParameter("HV0121"));}
  if(request.getParameter("HV0122")!=null && !request.getParameter("HV0122").equals("")){HV0122=Integer.parseInt(request.getParameter("HV0122"));}
  if(request.getParameter("HV0123")!=null && !request.getParameter("HV0123").equals("")){HV0123=Integer.parseInt(request.getParameter("HV0123"));}
  if(request.getParameter("HV0124")!=null && !request.getParameter("HV0124").equals("")){HV0124=Integer.parseInt(request.getParameter("HV0124"));}
  if(request.getParameter("HV0125")!=null && !request.getParameter("HV0125").equals("")){HV0125=Integer.parseInt(request.getParameter("HV0125"));}
  if(request.getParameter("HV0126")!=null && !request.getParameter("HV0126").equals("")){HV0126=Integer.parseInt(request.getParameter("HV0126"));}
  if(request.getParameter("HV0127")!=null && !request.getParameter("HV0127").equals("")){HV0127=Integer.parseInt(request.getParameter("HV0127"));}
  if(request.getParameter("HV0128")!=null && !request.getParameter("HV0128").equals("")){HV0128=Integer.parseInt(request.getParameter("HV0128"));}
  if(request.getParameter("HV0129")!=null && !request.getParameter("HV0129").equals("")){HV0129=Integer.parseInt(request.getParameter("HV0129"));}
  if(request.getParameter("HV0130")!=null && !request.getParameter("HV0130").equals("")){HV0130=Integer.parseInt(request.getParameter("HV0130"));}
  if(request.getParameter("HV0131")!=null && !request.getParameter("HV0131").equals("")){HV0131=Integer.parseInt(request.getParameter("HV0131"));}
  if(request.getParameter("HV0132")!=null && !request.getParameter("HV0132").equals("")){HV0132=Integer.parseInt(request.getParameter("HV0132"));}
  if(request.getParameter("HV0133")!=null && !request.getParameter("HV0133").equals("")){HV0133=Integer.parseInt(request.getParameter("HV0133"));}
  if(request.getParameter("HV0134")!=null && !request.getParameter("HV0134").equals("")){HV0134=Integer.parseInt(request.getParameter("HV0134"));}
  if(request.getParameter("HV0135")!=null && !request.getParameter("HV0135").equals("")){HV0135=Integer.parseInt(request.getParameter("HV0135"));}
  if(request.getParameter("HV0136")!=null && !request.getParameter("HV0136").equals("")){HV0136=Integer.parseInt(request.getParameter("HV0136"));}
  if(request.getParameter("HV0137")!=null && !request.getParameter("HV0137").equals("")){HV0137=Integer.parseInt(request.getParameter("HV0137"));}
  if(request.getParameter("HV0138")!=null && !request.getParameter("HV0138").equals("")){HV0138=Integer.parseInt(request.getParameter("HV0138"));}
  if(request.getParameter("HV0139")!=null && !request.getParameter("HV0139").equals("")){HV0139=Integer.parseInt(request.getParameter("HV0139"));}
  if(request.getParameter("HV0140")!=null && !request.getParameter("HV0140").equals("")){HV0140=Integer.parseInt(request.getParameter("HV0140"));}
 
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
if(request.getParameter("HV0222")!=null && !request.getParameter("HV0222").equals("")){HV0222=Integer.parseInt(request.getParameter("HV0222"));}
if(request.getParameter("HV0223")!=null && !request.getParameter("HV0223").equals("")){HV0223=Integer.parseInt(request.getParameter("HV0223"));}
if(request.getParameter("HV0224")!=null && !request.getParameter("HV0224").equals("")){HV0224=Integer.parseInt(request.getParameter("HV0224"));}
if(request.getParameter("HV0225")!=null && !request.getParameter("HV0225").equals("")){HV0225=Integer.parseInt(request.getParameter("HV0225"));}
if(request.getParameter("HV0226")!=null && !request.getParameter("HV0226").equals("")){HV0226=Integer.parseInt(request.getParameter("HV0226"));}
if(request.getParameter("HV0227")!=null && !request.getParameter("HV0227").equals("")){HV0227=Integer.parseInt(request.getParameter("HV0227"));}
if(request.getParameter("HV0228")!=null && !request.getParameter("HV0228").equals("")){HV0228=Integer.parseInt(request.getParameter("HV0228"));}
if(request.getParameter("HV0229")!=null && !request.getParameter("HV0229").equals("")){HV0229=Integer.parseInt(request.getParameter("HV0229"));}
if(request.getParameter("HV0230")!=null && !request.getParameter("HV0230").equals("")){HV0230=Integer.parseInt(request.getParameter("HV0230"));}
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
if(request.getParameter("HV0245")!=null && !request.getParameter("HV0245").equals("")){HV0245=Integer.parseInt(request.getParameter("HV0245"));}
if(request.getParameter("HV0246")!=null && !request.getParameter("HV0246").equals("")){HV0246=Integer.parseInt(request.getParameter("HV0246"));}
if(request.getParameter("HV0247")!=null && !request.getParameter("HV0247").equals("")){HV0247=Integer.parseInt(request.getParameter("HV0247"));}
if(request.getParameter("HV0248")!=null && !request.getParameter("HV0248").equals("")){HV0248=Integer.parseInt(request.getParameter("HV0248"));}
if(request.getParameter("HV0249")!=null && !request.getParameter("HV0249").equals("")){HV0249=Integer.parseInt(request.getParameter("HV0249"));}
if(request.getParameter("HV0250")!=null && !request.getParameter("HV0250").equals("")){HV0250=Integer.parseInt(request.getParameter("HV0250"));}
if(request.getParameter("HV0251")!=null && !request.getParameter("HV0251").equals("")){HV0251=Integer.parseInt(request.getParameter("HV0251"));}
if(request.getParameter("HV0252")!=null && !request.getParameter("HV0252").equals("")){HV0252=Integer.parseInt(request.getParameter("HV0252"));}
if(request.getParameter("HV0253")!=null && !request.getParameter("HV0253").equals("")){HV0253=Integer.parseInt(request.getParameter("HV0253"));}
if(request.getParameter("HV0254")!=null && !request.getParameter("HV0254").equals("")){HV0254=Integer.parseInt(request.getParameter("HV0254"));}
if(request.getParameter("HV0255")!=null && !request.getParameter("HV0255").equals("")){HV0255=Integer.parseInt(request.getParameter("HV0255"));}
if(request.getParameter("HV0256")!=null && !request.getParameter("HV0256").equals("")){HV0256=Integer.parseInt(request.getParameter("HV0256"));}
if(request.getParameter("HV0257")!=null && !request.getParameter("HV0257").equals("")){HV0257=Integer.parseInt(request.getParameter("HV0257"));}
if(request.getParameter("HV0258")!=null && !request.getParameter("HV0258").equals("")){HV0258=Integer.parseInt(request.getParameter("HV0258"));}

if(request.getParameter("HV03001")!=null && !request.getParameter("HV03001").equals("")){HV03001=Integer.parseInt(request.getParameter("HV03001"));}
if(request.getParameter("HV03002")!=null && !request.getParameter("HV03002").equals("")){HV03002=Integer.parseInt(request.getParameter("HV03002"));}
if(request.getParameter("HV03003")!=null && !request.getParameter("HV03003").equals("")){HV03003=Integer.parseInt(request.getParameter("HV03003"));}
if(request.getParameter("HV03004")!=null && !request.getParameter("HV03004").equals("")){HV03004=Integer.parseInt(request.getParameter("HV03004"));}
if(request.getParameter("HV03005")!=null && !request.getParameter("HV03005").equals("")){HV03005=Integer.parseInt(request.getParameter("HV03005"));}
if(request.getParameter("HV03006")!=null && !request.getParameter("HV03006").equals("")){HV03006=Integer.parseInt(request.getParameter("HV03006"));}
if(request.getParameter("HV03007")!=null && !request.getParameter("HV03007").equals("")){HV03007=Integer.parseInt(request.getParameter("HV03007"));}
if(request.getParameter("HV03008")!=null && !request.getParameter("HV03008").equals("")){HV03008=Integer.parseInt(request.getParameter("HV03008"));}
if(request.getParameter("HV03009")!=null && !request.getParameter("HV03009").equals("")){HV03009=Integer.parseInt(request.getParameter("HV03009"));}
if(request.getParameter("HV03010")!=null && !request.getParameter("HV03010").equals("")){HV03010=Integer.parseInt(request.getParameter("HV03010"));}
if(request.getParameter("HV03011")!=null && !request.getParameter("HV03011").equals("")){HV03011=Integer.parseInt(request.getParameter("HV03011"));}
if(request.getParameter("HV03012")!=null && !request.getParameter("HV03012").equals("")){HV03012=Integer.parseInt(request.getParameter("HV03012"));}
if(request.getParameter("HV03013")!=null && !request.getParameter("HV03013").equals("")){HV03013=Integer.parseInt(request.getParameter("HV03013"));}
if(request.getParameter("HV03014")!=null && !request.getParameter("HV03014").equals("")){HV03014=Integer.parseInt(request.getParameter("HV03014"));}
if(request.getParameter("HV03015")!=null && !request.getParameter("HV03015").equals("")){HV03015=Integer.parseInt(request.getParameter("HV03015"));}
if(request.getParameter("HV03016")!=null && !request.getParameter("HV03016").equals("")){HV03016=Integer.parseInt(request.getParameter("HV03016"));}
if(request.getParameter("HV03017")!=null && !request.getParameter("HV03017").equals("")){HV03017=Integer.parseInt(request.getParameter("HV03017"));}
if(request.getParameter("HV03018")!=null && !request.getParameter("HV03018").equals("")){HV03018=Integer.parseInt(request.getParameter("HV03018"));}
if(request.getParameter("HV03019")!=null && !request.getParameter("HV03019").equals("")){HV03019=Integer.parseInt(request.getParameter("HV03019"));}
if(request.getParameter("HV03020")!=null && !request.getParameter("HV03020").equals("")){HV03020=Integer.parseInt(request.getParameter("HV03020"));}
if(request.getParameter("HV03021")!=null && !request.getParameter("HV03021").equals("")){HV03021=Integer.parseInt(request.getParameter("HV03021"));}
if(request.getParameter("HV03022")!=null && !request.getParameter("HV03022").equals("")){HV03022=Integer.parseInt(request.getParameter("HV03022"));}
if(request.getParameter("HV03023")!=null && !request.getParameter("HV03023").equals("")){HV03023=Integer.parseInt(request.getParameter("HV03023"));}
if(request.getParameter("HV03024")!=null && !request.getParameter("HV03024").equals("")){HV03024=Integer.parseInt(request.getParameter("HV03024"));}
if(request.getParameter("HV03025")!=null && !request.getParameter("HV03025").equals("")){HV03025=Integer.parseInt(request.getParameter("HV03025"));}
if(request.getParameter("HV03026")!=null && !request.getParameter("HV03026").equals("")){HV03026=Integer.parseInt(request.getParameter("HV03026"));}
if(request.getParameter("HV03027")!=null && !request.getParameter("HV03027").equals("")){HV03027=Integer.parseInt(request.getParameter("HV03027"));}
if(request.getParameter("HV03028")!=null && !request.getParameter("HV03028").equals("")){HV03028=Integer.parseInt(request.getParameter("HV03028"));}
if(request.getParameter("HV03029")!=null && !request.getParameter("HV03029").equals("")){HV03029=Integer.parseInt(request.getParameter("HV03029"));}
if(request.getParameter("HV03030")!=null && !request.getParameter("HV03030").equals("")){HV03030=Integer.parseInt(request.getParameter("HV03030"));}
if(request.getParameter("HV03031")!=null && !request.getParameter("HV03031").equals("")){HV03031=Integer.parseInt(request.getParameter("HV03031"));}
if(request.getParameter("HV03032")!=null && !request.getParameter("HV03032").equals("")){HV03032=Integer.parseInt(request.getParameter("HV03032"));}
if(request.getParameter("HV03033")!=null && !request.getParameter("HV03033").equals("")){HV03033=Integer.parseInt(request.getParameter("HV03033"));}
if(request.getParameter("HV03034")!=null && !request.getParameter("HV03034").equals("")){HV03034=Integer.parseInt(request.getParameter("HV03034"));}
if(request.getParameter("HV03035")!=null && !request.getParameter("HV03035").equals("")){HV03035=Integer.parseInt(request.getParameter("HV03035"));}
if(request.getParameter("HV03036")!=null && !request.getParameter("HV03036").equals("")){HV03036=Integer.parseInt(request.getParameter("HV03036"));}
if(request.getParameter("HV03037")!=null && !request.getParameter("HV03037").equals("")){HV03037=Integer.parseInt(request.getParameter("HV03037"));}
if(request.getParameter("HV03038")!=null && !request.getParameter("HV03038").equals("")){HV03038=Integer.parseInt(request.getParameter("HV03038"));}
if(request.getParameter("HV03039")!=null && !request.getParameter("HV03039").equals("")){HV03039=Integer.parseInt(request.getParameter("HV03039"));}
if(request.getParameter("HV03040")!=null && !request.getParameter("HV03040").equals("")){HV03040=Integer.parseInt(request.getParameter("HV03040"));}
if(request.getParameter("HV03041")!=null && !request.getParameter("HV03041").equals("")){HV03041=Integer.parseInt(request.getParameter("HV03041"));}
if(request.getParameter("HV03042")!=null && !request.getParameter("HV03042").equals("")){HV03042=Integer.parseInt(request.getParameter("HV03042"));}
if(request.getParameter("HV03043")!=null && !request.getParameter("HV03043").equals("")){HV03043=Integer.parseInt(request.getParameter("HV03043"));}
if(request.getParameter("HV03044")!=null && !request.getParameter("HV03044").equals("")){HV03044=Integer.parseInt(request.getParameter("HV03044"));}
if(request.getParameter("HV03045")!=null && !request.getParameter("HV03045").equals("")){HV03045=Integer.parseInt(request.getParameter("HV03045"));}
if(request.getParameter("HV03046")!=null && !request.getParameter("HV03046").equals("")){HV03046=Integer.parseInt(request.getParameter("HV03046"));}
if(request.getParameter("HV03047")!=null && !request.getParameter("HV03047").equals("")){HV03047=Integer.parseInt(request.getParameter("HV03047"));}
if(request.getParameter("HV03048")!=null && !request.getParameter("HV03048").equals("")){HV03048=Integer.parseInt(request.getParameter("HV03048"));}
if(request.getParameter("HV03049")!=null && !request.getParameter("HV03049").equals("")){HV03049=Integer.parseInt(request.getParameter("HV03049"));}
if(request.getParameter("HV03050")!=null && !request.getParameter("HV03050").equals("")){HV03050=Integer.parseInt(request.getParameter("HV03050"));}
if(request.getParameter("HV03051")!=null && !request.getParameter("HV03051").equals("")){HV03051=Integer.parseInt(request.getParameter("HV03051"));}
if(request.getParameter("HV03052")!=null && !request.getParameter("HV03052").equals("")){HV03052=Integer.parseInt(request.getParameter("HV03052"));}
if(request.getParameter("HV03053")!=null && !request.getParameter("HV03053").equals("")){HV03053=Integer.parseInt(request.getParameter("HV03053"));}
if(request.getParameter("HV03054")!=null && !request.getParameter("HV03054").equals("")){HV03054=Integer.parseInt(request.getParameter("HV03054"));}
if(request.getParameter("HV03055")!=null && !request.getParameter("HV03055").equals("")){HV03055=Integer.parseInt(request.getParameter("HV03055"));}
if(request.getParameter("HV03056")!=null && !request.getParameter("HV03056").equals("")){HV03056=Integer.parseInt(request.getParameter("HV03056"));}
if(request.getParameter("HV03057")!=null && !request.getParameter("HV03057").equals("")){HV03057=Integer.parseInt(request.getParameter("HV03057"));}
if(request.getParameter("HV03058")!=null && !request.getParameter("HV03058").equals("")){HV03058=Integer.parseInt(request.getParameter("HV03058"));}
if(request.getParameter("HV03059")!=null && !request.getParameter("HV03059").equals("")){HV03059=Integer.parseInt(request.getParameter("HV03059"));}
if(request.getParameter("HV03060")!=null && !request.getParameter("HV03060").equals("")){HV03060=Integer.parseInt(request.getParameter("HV03060"));}
if(request.getParameter("HV03061")!=null && !request.getParameter("HV03061").equals("")){HV03061=Integer.parseInt(request.getParameter("HV03061"));}
if(request.getParameter("HV03062")!=null && !request.getParameter("HV03062").equals("")){HV03062=Integer.parseInt(request.getParameter("HV03062"));}
if(request.getParameter("HV03063")!=null && !request.getParameter("HV03063").equals("")){HV03063=Integer.parseInt(request.getParameter("HV03063"));}
if(request.getParameter("HV03064")!=null && !request.getParameter("HV03064").equals("")){HV03064=Integer.parseInt(request.getParameter("HV03064"));}
if(request.getParameter("HV03065")!=null && !request.getParameter("HV03065").equals("")){HV03065=Integer.parseInt(request.getParameter("HV03065"));}
if(request.getParameter("HV03066")!=null && !request.getParameter("HV03066").equals("")){HV03066=Integer.parseInt(request.getParameter("HV03066"));}
if(request.getParameter("HV03067")!=null && !request.getParameter("HV03067").equals("")){HV03067=Integer.parseInt(request.getParameter("HV03067"));}
if(request.getParameter("HV03068")!=null && !request.getParameter("HV03068").equals("")){HV03068=Integer.parseInt(request.getParameter("HV03068"));}
if(request.getParameter("HV03069")!=null && !request.getParameter("HV03069").equals("")){HV03069=Integer.parseInt(request.getParameter("HV03069"));}
if(request.getParameter("HV03070")!=null && !request.getParameter("HV03070").equals("")){HV03070=Integer.parseInt(request.getParameter("HV03070"));}
if(request.getParameter("HV03071")!=null && !request.getParameter("HV03071").equals("")){HV03071=Integer.parseInt(request.getParameter("HV03071"));}
if(request.getParameter("HV03072")!=null && !request.getParameter("HV03072").equals("")){HV03072=Integer.parseInt(request.getParameter("HV03072"));}
if(request.getParameter("HV03073")!=null && !request.getParameter("HV03073").equals("")){HV03073=Integer.parseInt(request.getParameter("HV03073"));}
if(request.getParameter("HV03074")!=null && !request.getParameter("HV03074").equals("")){HV03074=Integer.parseInt(request.getParameter("HV03074"));}
if(request.getParameter("HV03075")!=null && !request.getParameter("HV03075").equals("")){HV03075=Integer.parseInt(request.getParameter("HV03075"));}
if(request.getParameter("HV03076")!=null && !request.getParameter("HV03076").equals("")){HV03076=Integer.parseInt(request.getParameter("HV03076"));}
if(request.getParameter("HV03077")!=null && !request.getParameter("HV03077").equals("")){HV03077=Integer.parseInt(request.getParameter("HV03077"));}
if(request.getParameter("HV03078")!=null && !request.getParameter("HV03078").equals("")){HV03078=Integer.parseInt(request.getParameter("HV03078"));}
if(request.getParameter("HV03079")!=null && !request.getParameter("HV03079").equals("")){HV03079=Integer.parseInt(request.getParameter("HV03079"));}
if(request.getParameter("HV03080")!=null && !request.getParameter("HV03080").equals("")){HV03080=Integer.parseInt(request.getParameter("HV03080"));}
if(request.getParameter("HV03081")!=null && !request.getParameter("HV03081").equals("")){HV03081=Integer.parseInt(request.getParameter("HV03081"));}
if(request.getParameter("HV03082")!=null && !request.getParameter("HV03082").equals("")){HV03082=Integer.parseInt(request.getParameter("HV03082"));}
if(request.getParameter("HV03083")!=null && !request.getParameter("HV03083").equals("")){HV03083=Integer.parseInt(request.getParameter("HV03083"));}
if(request.getParameter("HV03084")!=null && !request.getParameter("HV03084").equals("")){HV03084=Integer.parseInt(request.getParameter("HV03084"));}
if(request.getParameter("HV03085")!=null && !request.getParameter("HV03085").equals("")){HV03085=Integer.parseInt(request.getParameter("HV03085"));}
if(request.getParameter("HV03086")!=null && !request.getParameter("HV03086").equals("")){HV03086=Integer.parseInt(request.getParameter("HV03086"));}
if(request.getParameter("HV03087")!=null && !request.getParameter("HV03087").equals("")){HV03087=Integer.parseInt(request.getParameter("HV03087"));}
if(request.getParameter("HV03088")!=null && !request.getParameter("HV03088").equals("")){HV03088=Integer.parseInt(request.getParameter("HV03088"));}
if(request.getParameter("HV03089")!=null && !request.getParameter("HV03089").equals("")){HV03089=Integer.parseInt(request.getParameter("HV03089"));}

if(request.getParameter("HV0401")!=null && !request.getParameter("HV0401").equals("")){HV0401=Integer.parseInt(request.getParameter("HV0401"));}
if(request.getParameter("HV0402")!=null && !request.getParameter("HV0402").equals("")){HV0402=Integer.parseInt(request.getParameter("HV0402"));}
if(request.getParameter("HV0403")!=null && !request.getParameter("HV0403").equals("")){HV0403=Integer.parseInt(request.getParameter("HV0403"));}
if(request.getParameter("HV0404")!=null && !request.getParameter("HV0404").equals("")){HV0404=Integer.parseInt(request.getParameter("HV0404"));}
if(request.getParameter("HV0405")!=null && !request.getParameter("HV0405").equals("")){HV0405=Integer.parseInt(request.getParameter("HV0405"));}
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
if(request.getParameter("HV0416")!=null && !request.getParameter("HV0416").equals("")){HV0416=Integer.parseInt(request.getParameter("HV0416"));}
if(request.getParameter("HV0417")!=null && !request.getParameter("HV0417").equals("")){HV0417=Integer.parseInt(request.getParameter("HV0417"));}

if(request.getParameter("HV0501")!=null && !request.getParameter("HV0501").equals("")){HV0501=Integer.parseInt(request.getParameter("HV0501"));}
if(request.getParameter("HV0502")!=null && !request.getParameter("HV0502").equals("")){HV0502=Integer.parseInt(request.getParameter("HV0502"));}
if(request.getParameter("HV0503")!=null && !request.getParameter("HV0503").equals("")){HV0503=Integer.parseInt(request.getParameter("HV0503"));}
if(request.getParameter("HV0504")!=null && !request.getParameter("HV0504").equals("")){HV0504=Integer.parseInt(request.getParameter("HV0504"));}
if(request.getParameter("HV0505")!=null && !request.getParameter("HV0505").equals("")){HV0505=Integer.parseInt(request.getParameter("HV0505"));}
if(request.getParameter("HV0506")!=null && !request.getParameter("HV0506").equals("")){HV0506=Integer.parseInt(request.getParameter("HV0506"));}

if(request.getParameter("HV0601")!=null && !request.getParameter("HV0601").equals("")){HV0601=Integer.parseInt(request.getParameter("HV0601"));}
if(request.getParameter("HV0602")!=null && !request.getParameter("HV0602").equals("")){HV0602=Integer.parseInt(request.getParameter("HV0602"));}
if(request.getParameter("HV0603")!=null && !request.getParameter("HV0603").equals("")){HV0603=Integer.parseInt(request.getParameter("HV0603"));}
if(request.getParameter("HV0604")!=null && !request.getParameter("HV0604").equals("")){HV0604=Integer.parseInt(request.getParameter("HV0604"));}
if(request.getParameter("HV0605")!=null && !request.getParameter("HV0605").equals("")){HV0605=Integer.parseInt(request.getParameter("HV0605"));}
         
// CUMULATIVES EVER PREVIOUS MONTH==========================================
if(request.getParameter("HV03040_1")!=null && !request.getParameter("HV03040_1").equals("")){HV03040_1=Integer.parseInt(request.getParameter("HV03040_1"));}
if(request.getParameter("HV03041_1")!=null && !request.getParameter("HV03041_1").equals("")){HV03041_1=Integer.parseInt(request.getParameter("HV03041_1"));}
if(request.getParameter("HV03042_1")!=null && !request.getParameter("HV03042_1").equals("")){HV03042_1=Integer.parseInt(request.getParameter("HV03042_1"));}
if(request.getParameter("HV03043_1")!=null && !request.getParameter("HV03043_1").equals("")){HV03043_1=Integer.parseInt(request.getParameter("HV03043_1"));}
if(request.getParameter("HV03044_1")!=null && !request.getParameter("HV03044_1").equals("")){HV03044_1=Integer.parseInt(request.getParameter("HV03044_1"));}


HV0110=HV0101+HV0102+HV0103+HV0104+HV0105+HV0106+HV0107+HV0108+HV0109;
HV0126=HV0117+HV0118+HV0118+HV0120+HV0121+HV0122+HV0123+HV0124+HV0125;
HV0135=HV0130+HV0131+HV0132+HV0133+HV0134;

HV0207=HV0203+HV0204+HV0205+HV0206;
HV0214=HV0210+HV0211+HV0212+HV0213;
HV0220=HV0216+HV0217+HV0218+HV0219;
HV0232=HV0229+HV0230+HV0231;
HV0241=HV0238+HV0239+HV0240;

HV03011=HV03001+HV03002+HV03003+HV03004+HV03005+HV03006+HV03007+HV03008+HV03009+HV03010;
HV03015=HV03013+HV03014;
HV03026=HV03016+HV03017+HV03018+HV03019+HV03020+HV03021+HV03022+HV03023+HV03024+HV03025;
HV03038=HV03028+HV03029+HV03030+HV03031+HV03032+HV03033+HV03034+HV03035+HV03036+HV03037;
HV03050=HV03044+HV03045+HV03046+HV03047+HV03048+HV03049;
HV03057=HV03051+HV03052+HV03053+HV03054+HV03055+HV03056;
HV03065=HV03059+HV03060+HV03061+HV03062+HV03063+HV03064;
HV03069=HV03067+HV03068;
HV03072=HV03070+HV03071;
HV03075=HV03073+HV03074;
HV03079=HV03077+HV03078;
HV03081=HV03077+HV03080;
HV03084=HV03082+HV03083;

HV0407=HV0401+HV0402+HV0403+HV0404+HV0405+HV0406;

HV0503=HV0501+HV0502;
HV0506=HV0504+HV0505;

 String runValidate="UPDATE moh731_new SET "
                  + "HV0101=?,HV0102=?,HV0103=?,HV0104=?,HV0105=?,HV0106=?,HV0107=?,HV0108=?,HV0109=?,"
         + "HV0110=?,HV0111=?,HV0112=?,HV0113=?,HV0114=?,HV0115=?,HV0116=?,HV0117=?,HV0118=?,HV0119=?,"
         + "HV0120=?,HV0121=?,HV0122=?,HV0123=?,HV0124=?,HV0125=?,HV0126=?,HV0127=?,HV0128=?,HV0129=?,"
         + "HV0130=?,HV0131=?,HV0132=?,HV0133=?,HV0134=?,HV0135=?,HV0136=?,HV0137=?,HV0138=?,HV0139=?,"
         + "HV0140=?,"
         + "" //end of HV01
         + "HV0201=?,HV0202=?,HV0203=?,HV0204=?,HV0205=?,HV0206=?,HV0207=?,HV0208=?,HV0209=?,HV0210=?,"
         + "HV0211=?,HV0212=?,HV0213=?,HV0214=?,HV0215=?,HV0216=?,HV0217=?,HV0218=?,HV0219=?,HV0220=?,"
         + "HV0221=?,HV0222=?,HV0223=?,HV0224=?,HV0225=?,HV0226=?,HV0227=?,HV0228=?,HV0229=?,HV0230=?,"
         + "HV0231=?,HV0232=?,HV0233=?,HV0234=?,HV0235=?,HV0236=?,HV0237=?,HV0238=?,HV0239=?,HV0240=?,"
         + "HV0241=?,HV0242=?,HV0243=?,HV0244=?,HV0245=?,HV0246=?,HV0247=?,HV0248=?,HV0249=?,HV0250=?,"
         + "HV0251=?,HV0252=?,HV0253=?,HV0254=?,HV0255=?,HV0256=?,HV0257=?,HV0258=?,"
         + ""
         + "" + //end of HV02
           "HV03001=?,HV03002=?,HV03003=?,HV03004=?,HV03005=?,HV03006=?,HV03007=?,HV03008=?,HV03009=?,HV03010=?,"
         + "HV03011=?,HV03012=?,HV03013=?,HV03014=?,HV03015=?,HV03016=?,HV03017=?,HV03018=?,HV03019=?,HV03020=?,"
         + "HV03021=?,HV03022=?,HV03023=?,HV03024=?,HV03025=?,HV03026=?,HV03027=?,HV03028=?,HV03029=?,HV03030=?,"
         + "HV03031=?,HV03032=?,HV03033=?,HV03034=?,HV03035=?,HV03036=?,HV03037=?,HV03038=?,HV03039=?,HV03040=?,"
         + "HV03041=?,HV03042=?,HV03043=?,HV03044=?,HV03045=?,HV03046=?,HV03047=?,HV03048=?,HV03049=?,HV03050=?,"
         + "HV03051=?,HV03052=?,HV03053=?,HV03054=?,HV03055=?,HV03056=?,HV03057=?,HV03058=?,HV03059=?,HV03060=?,"
         + "HV03061=?,HV03062=?,HV03063=?,HV03064=?,HV03065=?,HV03066=?,HV03067=?,HV03068=?,HV03069=?,HV03070=?,"
         + "HV03071=?,HV03072=?,HV03073=?,HV03074=?,HV03075=?,HV03076=?,HV03077=?,HV03078=?,HV03079=?,HV03080=?,"
         + "HV03081=?,HV03082=?,HV03083=?,HV03084=?,HV03085=?,HV03086=?,HV03087=?,HV03088=?,HV03089=?,"
         + "" //end of HV03
         + "HV0401=?,HV0402=?,HV0403=?,HV0404=?,HV0405=?,HV0406=?,HV0407=?,HV0408=?,HV0409=?,HV0410=?,"
         + "HV0411=?,HV0412=?,HV0413=?,HV0414=?,HV0415=?,HV0416=?,HV0417=?,"
         + ""  //end of HV04
         + "HV0501=?,HV0502=?,HV0503=?,HV0504=?,HV0505=?,HV0506=?,"
         + "" //end of HV05
         + "HV0601=?,HV0602=?,HV0603=?,HV0604=?,HV0605=?,"
         + "" //end of HV06
         + "isValidated=? WHERE id=?";     //31   
    conn.pst=conn.conn.prepareStatement(runValidate);
  
    conn.pst.setInt(1, HV0101);
    conn.pst.setInt(2, HV0102);
    conn.pst.setInt(3, HV0103);
    conn.pst.setInt(4, HV0104);
    conn.pst.setInt(5, HV0105);
    conn.pst.setInt(6, HV0106);
    conn.pst.setInt(7, HV0107);
    conn.pst.setInt(8, HV0108);
    conn.pst.setInt(9, HV0109);
    conn.pst.setInt(10, HV0110);
    conn.pst.setInt(11, HV0111);
    conn.pst.setInt(12, HV0112);
    conn.pst.setInt(13, HV0113);
    conn.pst.setInt(14, HV0114);
    conn.pst.setInt(15, HV0115);
    conn.pst.setInt(16, HV0116);
    conn.pst.setInt(17, HV0117);
    conn.pst.setInt(18, HV0118);
    conn.pst.setInt(19, HV0119);
    conn.pst.setInt(20, HV0120);
    conn.pst.setInt(21, HV0121);
    conn.pst.setInt(22, HV0122);
    conn.pst.setInt(23, HV0123);
    conn.pst.setInt(24, HV0124);
    conn.pst.setInt(25, HV0125);
    conn.pst.setInt(26, HV0126);
    conn.pst.setInt(27, HV0127);
    conn.pst.setInt(28, HV0128);
    conn.pst.setInt(29, HV0129);
    conn.pst.setInt(30, HV0130);
    conn.pst.setInt(31, HV0131);
    conn.pst.setInt(32, HV0132);
    conn.pst.setInt(33, HV0133);
    conn.pst.setInt(34, HV0134);
    conn.pst.setInt(35, HV0135);
    conn.pst.setInt(36, HV0136);
    conn.pst.setInt(37, HV0137);
    conn.pst.setInt(38, HV0138);
    conn.pst.setInt(39, HV0139);
    conn.pst.setInt(40, HV0140);

    conn.pst.setInt(41, HV0201);
    conn.pst.setInt(42, HV0202);
    conn.pst.setInt(43, HV0203);
    conn.pst.setInt(44, HV0204);
    conn.pst.setInt(45, HV0205);
    conn.pst.setInt(46, HV0206);
    conn.pst.setInt(47, HV0207);
    conn.pst.setInt(48, HV0208);
    conn.pst.setInt(49, HV0209);
    conn.pst.setInt(50, HV0210);
    conn.pst.setInt(51, HV0211);
    conn.pst.setInt(52, HV0212);
    conn.pst.setInt(53, HV0213);
    conn.pst.setInt(54, HV0214);
    conn.pst.setInt(55, HV0215);
    conn.pst.setInt(56, HV0216);
    conn.pst.setInt(57, HV0217);
    conn.pst.setInt(58, HV0218);
    conn.pst.setInt(59, HV0219);
    conn.pst.setInt(60, HV0220);
    conn.pst.setInt(61, HV0221);
    conn.pst.setInt(62, HV0222);
    conn.pst.setInt(63, HV0223);
    conn.pst.setInt(64, HV0224);
    conn.pst.setInt(65, HV0225);
    conn.pst.setInt(66, HV0226);
    conn.pst.setInt(67, HV0227);
    conn.pst.setInt(68, HV0228);
    conn.pst.setInt(69, HV0229);
    conn.pst.setInt(70, HV0230);
    conn.pst.setInt(71, HV0231);
    conn.pst.setInt(72, HV0232);
    conn.pst.setInt(73, HV0233);
    conn.pst.setInt(74, HV0234);
    conn.pst.setInt(75, HV0235);
    conn.pst.setInt(76, HV0236);
    conn.pst.setInt(77, HV0237);
    conn.pst.setInt(78, HV0238);
    conn.pst.setInt(79, HV0239);
    conn.pst.setInt(80, HV0240);
    conn.pst.setInt(81, HV0241);
    conn.pst.setInt(82, HV0242);
    conn.pst.setInt(83, HV0243);
    conn.pst.setInt(84, HV0244);
    conn.pst.setInt(85, HV0245);
    conn.pst.setInt(86, HV0246);
    conn.pst.setInt(87, HV0247);
    conn.pst.setInt(88, HV0248);
    conn.pst.setInt(89, HV0249);
    conn.pst.setInt(90, HV0250);
    conn.pst.setInt(91, HV0251);
    conn.pst.setInt(92, HV0252);
    conn.pst.setInt(93, HV0253);
    conn.pst.setInt(94, HV0254);
    conn.pst.setInt(95, HV0255);
    conn.pst.setInt(96, HV0256);
    conn.pst.setInt(97, HV0257);
    conn.pst.setInt(98, HV0258);


    conn.pst.setInt(99, HV03001);
    conn.pst.setInt(100, HV03002);
    conn.pst.setInt(101, HV03003);
    conn.pst.setInt(102, HV03004);
    conn.pst.setInt(103, HV03005);
    conn.pst.setInt(104, HV03006);
    conn.pst.setInt(105, HV03007);
    conn.pst.setInt(106, HV03008);
    conn.pst.setInt(107, HV03009);
    conn.pst.setInt(108, HV03010);
    conn.pst.setInt(109, HV03011);
    conn.pst.setInt(110, HV03012);
    conn.pst.setInt(111, HV03013);
    conn.pst.setInt(112, HV03014);
    conn.pst.setInt(113, HV03015);
    conn.pst.setInt(114, HV03016);
    conn.pst.setInt(115, HV03017);
    conn.pst.setInt(116, HV03018);
    conn.pst.setInt(117, HV03019);
    conn.pst.setInt(118, HV03020);
    conn.pst.setInt(119, HV03021);
    conn.pst.setInt(120, HV03022);
    conn.pst.setInt(121, HV03023);
    conn.pst.setInt(122, HV03024);
    conn.pst.setInt(123, HV03025);
    conn.pst.setInt(124, HV03026);
    conn.pst.setInt(125, HV03027);
    conn.pst.setInt(126, HV03028);
    conn.pst.setInt(127, HV03029);
    conn.pst.setInt(128, HV03030);
    conn.pst.setInt(129, HV03031);
    conn.pst.setInt(130, HV03032);
    conn.pst.setInt(131, HV03033);
    conn.pst.setInt(132, HV03034);
    conn.pst.setInt(133, HV03035);
    conn.pst.setInt(134, HV03036);
    conn.pst.setInt(135, HV03037);
    conn.pst.setInt(136, HV03038);
    conn.pst.setInt(137, HV03039);
    conn.pst.setInt(138, HV03040);
    conn.pst.setInt(139, HV03041);
    conn.pst.setInt(140, HV03042);
    conn.pst.setInt(141, HV03043);
    conn.pst.setInt(142, HV03044);
    conn.pst.setInt(143, HV03045);
    conn.pst.setInt(144, HV03046);
    conn.pst.setInt(145, HV03047);
    conn.pst.setInt(146, HV03048);
    conn.pst.setInt(147, HV03049);
    conn.pst.setInt(148, HV03050);
    conn.pst.setInt(149, HV03051);
    conn.pst.setInt(150, HV03052);
    conn.pst.setInt(151, HV03053);
    conn.pst.setInt(152, HV03054);
    conn.pst.setInt(153, HV03055);
    conn.pst.setInt(154, HV03056);
    conn.pst.setInt(155, HV03057);
    conn.pst.setInt(156, HV03058);
    conn.pst.setInt(157, HV03059);
    conn.pst.setInt(158, HV03060);
    conn.pst.setInt(159, HV03061);
    conn.pst.setInt(160, HV03062);
    conn.pst.setInt(161, HV03063);
    conn.pst.setInt(162, HV03064);
    conn.pst.setInt(163, HV03065);
    conn.pst.setInt(164, HV03066);
    conn.pst.setInt(165, HV03067);
    conn.pst.setInt(166, HV03068);
    conn.pst.setInt(167, HV03069);
    conn.pst.setInt(168, HV03070);
    conn.pst.setInt(169, HV03071);
    conn.pst.setInt(170, HV03072);
    conn.pst.setInt(171, HV03073);   
    conn.pst.setInt(172, HV03074);   
    conn.pst.setInt(173, HV03075);   
    conn.pst.setInt(174, HV03076);   
    conn.pst.setInt(175, HV03077);   
    conn.pst.setInt(176, HV03078);   
    conn.pst.setInt(177, HV03079);   
    conn.pst.setInt(178, HV03080);   
    conn.pst.setInt(179, HV03081);   
    conn.pst.setInt(180, HV03082);   
    conn.pst.setInt(181, HV03083);   
    conn.pst.setInt(182, HV03084);   
    conn.pst.setInt(183, HV03085);   
    conn.pst.setInt(184, HV03086);   
    conn.pst.setInt(185, HV03087);   
    conn.pst.setInt(186, HV03088);   
    conn.pst.setInt(187, HV03089);   

    conn.pst.setInt(188, HV0401);
    conn.pst.setInt(189, HV0402);
    conn.pst.setInt(190, HV0403);
    conn.pst.setInt(191, HV0404);
    conn.pst.setInt(192, HV0405);
    conn.pst.setInt(193, HV0406);
    conn.pst.setInt(194, HV0407);
    conn.pst.setInt(195, HV0408);
    conn.pst.setInt(196, HV0409);
    conn.pst.setInt(197, HV0410);
    conn.pst.setInt(198, HV0411);
    conn.pst.setInt(199, HV0412);
    conn.pst.setInt(200, HV0413);
    conn.pst.setInt(201, HV0414);
    conn.pst.setInt(202, HV0415);
    conn.pst.setInt(203, HV0416);
    conn.pst.setInt(204, HV0417);

    conn.pst.setInt(205, HV0501);
    conn.pst.setInt(206, HV0502);
    conn.pst.setInt(207, HV0503);
    conn.pst.setInt(208, HV0504);
    conn.pst.setInt(209, HV0505);
    conn.pst.setInt(210, HV0506);

    conn.pst.setInt(211, HV0601);
    conn.pst.setInt(212, HV0602);
    conn.pst.setInt(213, HV0603);
    conn.pst.setInt(214, HV0604);
    conn.pst.setInt(215, HV0605);

    conn.pst.setInt(216, 1);
    conn.pst.setString(217, id);

    conn.pst.executeUpdate();
   
            System.out.println("THE UPDATED ID IS : "+id);
     session.setAttribute("validate731", "<font color=\"green\"><b>New Form MOH 731 Validated Successfully.</b></font>");
    int monthDiff=0;
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM moh731_new WHERE id='"+id+"'";
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
     String updateLast="UPDATE moh731_new SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE id='"+id+"'" ;   
       conn.st2.executeUpdate(updateLast);
     }
    
     
     String dqaid="";
      System.out.println("++++++++++++++++++++++++++++++++here++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("errors : "+data_elements+"   descr : "+description);
      String checker="SELECT id FROM dqa WHERE year='"+year+"' && month='"+month+"' && facilityid='"+facilityId+"' && form='moh731_new' LIMIT 1";
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
    conn.pst.setString(2, "moh731_new");
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
