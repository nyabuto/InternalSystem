/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package General;

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
 * @author Geofrey Nyabuto
 */
public class errors731 extends HttpServlet {
HttpSession session;
String data,description;
String month,year,facilityId,id;
int counter;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn = new dbConn();
    
        
       year="2015";
       month="4";
       counter=0;
       
String getData="SELECT id,SubPartnerID,Annee,Mois,HV0201,HV0202,HV0203,HV0204,HV0205,HV0206,HV0207,HV0208,HV0209,HV0210,HV0211,HV0212,HV0213," +
"HV0214,HV0215,HV0216,HV0217,HV0218,HV0219,HV0220,HV0221,HV0224,HV0225,HV0226,HV0227,HV0228,HV0229," +
"HV0230,HV0231,HV0232,HV0233,HV0234,HV0235,HV0236,HV0237,HV0238,HV0239,HV0240,HV0241,HV0242," +
"HV0243,HV0244,HV0301,HV0302,HV0303,HV0304,HV0305,HV0306,HV0307,HV0308,HV0309,HV0310,HV0311,HV0312,HV0313,HV0314," +
"HV0315,HV0316,HV0317,HV0318,HV0319,HV0320,HV0321,HV0322,HV0323,HV0324,HV0325,HV0326,HV0327,HV0328," +
"HV0329,HV0330,HV0331,HV0332,HV0333,HV0334,HV0335,HV0336,HV0337,HV0338,HV0339,HV0340,HV0341," +
"HV0342,HV0343,HV0344,HV0345,HV0346,HV0347,HV0348,HV0349,HV0350,HV0351,HV0352,HV0353," +
"HV0354,HV0355,HV0904,HV0905,HV0370,HV0371,HV0372,HV0373,"+
"HV0501,HV0502,HV0503,HV0504,HV0505,HV0506,HV0507,HV0508,HV0509,HV0510,HV0511,HV0512,HV0513,HV0514 "+
"FROM moh731 WHERE isValidated=1";
//" FROM moh731 WHERE Mois='"+month+"' && Annee='"+year+"' && isValidated=1";
      conn.rs=conn.st.executeQuery(getData);
     while (conn.rs.next()){      
        data=description="";
        int checker=0;

        id=conn.rs.getString(1);
        facilityId=conn.rs.getString(2);
        year=conn.rs.getString(3);
        month=conn.rs.getString(4);
        
        
int HV0201=conn.rs.getInt("HV0201");
int HV0202=conn.rs.getInt("HV0202");
int HV0203=conn.rs.getInt("HV0203");
int HV0204=conn.rs.getInt("HV0204");
int HV0205=conn.rs.getInt("HV0205");
int HV0206=conn.rs.getInt("HV0206");
int HV0207=conn.rs.getInt("HV0207");
int HV0208=conn.rs.getInt("HV0208");
int HV0209=conn.rs.getInt("HV0209");
int HV0210=conn.rs.getInt("HV0210");
int HV0211=conn.rs.getInt("HV0211");
int HV0212=conn.rs.getInt("HV0212");
int HV0213=conn.rs.getInt("HV0213");
int HV0214=conn.rs.getInt("HV0214");
int HV0215=conn.rs.getInt("HV0215");
int HV0216=conn.rs.getInt("HV0216");
int HV0217=conn.rs.getInt("HV0217");
int HV0218=conn.rs.getInt("HV0218");
int HV0219=conn.rs.getInt("HV0219");
int HV0220=conn.rs.getInt("HV0220");
int HV0221=conn.rs.getInt("HV0221");
int HV0224=conn.rs.getInt("HV0224");
int HV0225=conn.rs.getInt("HV0225");
int HV0226=conn.rs.getInt("HV0226");
int HV0227=conn.rs.getInt("HV0227");
int HV0228=conn.rs.getInt("HV0228");
int HV0229=conn.rs.getInt("HV0229");
int HV0230=conn.rs.getInt("HV0230");
int HV0231=conn.rs.getInt("HV0231");
int HV0232=conn.rs.getInt("HV0232");
int HV0233=conn.rs.getInt("HV0233");
int HV0234=conn.rs.getInt("HV0234");
int HV0235=conn.rs.getInt("HV0235");
int HV0236=conn.rs.getInt("HV0236");
int HV0237=conn.rs.getInt("HV0237");
int HV0238=conn.rs.getInt("HV0238");
int HV0239=conn.rs.getInt("HV0239");
int HV0240=conn.rs.getInt("HV0240");
int HV0241=conn.rs.getInt("HV0241");
int HV0242=conn.rs.getInt("HV0242");
int HV0243=conn.rs.getInt("HV0243");
int HV0244=conn.rs.getInt("HV0244");

int HV0301=conn.rs.getInt("HV0301");
int HV0302=conn.rs.getInt("HV0302");
int HV0303=conn.rs.getInt("HV0303");
int HV0304=conn.rs.getInt("HV0304");
int HV0305=conn.rs.getInt("HV0305");
int HV0306=conn.rs.getInt("HV0306");
int HV0307=conn.rs.getInt("HV0307");
int HV0308=conn.rs.getInt("HV0308");
int HV0309=conn.rs.getInt("HV0309");
int HV0310=conn.rs.getInt("HV0310");
int HV0311=conn.rs.getInt("HV0311");
int HV0312=conn.rs.getInt("HV0312");
int HV0313=conn.rs.getInt("HV0313");
int HV0314=conn.rs.getInt("HV0314");
int HV0315=conn.rs.getInt("HV0315");
int HV0316=conn.rs.getInt("HV0316");
int HV0317=conn.rs.getInt("HV0317");
int HV0318=conn.rs.getInt("HV0318");
int HV0319=conn.rs.getInt("HV0319");
int HV0320=conn.rs.getInt("HV0320");
int HV0321=conn.rs.getInt("HV0321");
int HV0322=conn.rs.getInt("HV0322");
int HV0323=conn.rs.getInt("HV0323");
int HV0324=conn.rs.getInt("HV0324");
int HV0325=conn.rs.getInt("HV0325");
int HV0326=conn.rs.getInt("HV0326");
int HV0327=conn.rs.getInt("HV0327");
int HV0328=conn.rs.getInt("HV0328");
int HV0329=conn.rs.getInt("HV0329");
int HV0330=conn.rs.getInt("HV0330");
int HV0331=conn.rs.getInt("HV0331");
int HV0332=conn.rs.getInt("HV0332");
int HV0333=conn.rs.getInt("HV0333");
int HV0334=conn.rs.getInt("HV0334");
int HV0335=conn.rs.getInt("HV0335");
int HV0336=conn.rs.getInt("HV0336");
int HV0337=conn.rs.getInt("HV0337");
int HV0338=conn.rs.getInt("HV0338");
int HV0339=conn.rs.getInt("HV0339");
int HV0340=conn.rs.getInt("HV0340");
int HV0341=conn.rs.getInt("HV0341");
int HV0342=conn.rs.getInt("HV0342");
int HV0343=conn.rs.getInt("HV0343");
int HV0344=conn.rs.getInt("HV0344");
int HV0345=conn.rs.getInt("HV0345");
int HV0346=conn.rs.getInt("HV0346");
int HV0347=conn.rs.getInt("HV0347");
int HV0348=conn.rs.getInt("HV0348");
int HV0349=conn.rs.getInt("HV0349");
int HV0350=conn.rs.getInt("HV0350");
int HV0351=conn.rs.getInt("HV0351");
int HV0352=conn.rs.getInt("HV0352");
int HV0353=conn.rs.getInt("HV0353");
int HV0354=conn.rs.getInt("HV0354");
int HV0355=conn.rs.getInt("HV0355");
int HV0904=conn.rs.getInt("HV0904");
int HV0905=conn.rs.getInt("HV0905");
int HV0370=conn.rs.getInt("HV0370");
int HV0371=conn.rs.getInt("HV0371");
int HV0372=conn.rs.getInt("HV0372");
int HV0373=conn.rs.getInt("HV0373");

int HV0501=conn.rs.getInt("HV0501");
int HV0502=conn.rs.getInt("HV0502");
int HV0503=conn.rs.getInt("HV0503");
int HV0504=conn.rs.getInt("HV0504");
int HV0505=conn.rs.getInt("HV0505");
int HV0506=conn.rs.getInt("HV0506");
int HV0507=conn.rs.getInt("HV0507");
int HV0508=conn.rs.getInt("HV0508");
int HV0509=conn.rs.getInt("HV0509");
int HV0510=conn.rs.getInt("HV0510");
int HV0511=conn.rs.getInt("HV0511");
int HV0512=conn.rs.getInt("HV0512");
int HV0513=conn.rs.getInt("HV0513");
int HV0514=conn.rs.getInt("HV0514");
   


if((HV0201)>(HV0204)){checker++;
 data+="@HV0201,HV0204";
 description+="@HV0201 is greater than HV0204";
 }
if((HV0202)>(HV0204)){checker++;
 data+="@HV0202,HV0204";
description+="@HV0204 is less than HV0202";
}
   if((HV0203)>(HV0204)){checker++;
data+="@HV0203,HV0204";
description+="@HV0204 is less than HV0203";
}
   if((HV0204)!=((HV0201)+(HV0202)+(HV0203))){checker++;
data+="@HV0201,HV0202,HV0203,HV0204";
description+="@Sum of HV0201,HV0202,HV0203 is not equal to HV0204";
}  
   
 //2.2++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    PRIMARY
   if((HV0206)>(HV0210)){checker++;
data+="@HV0206,HV0210";
description+="@HV0210 is less than HV0206";
}
   if((HV0208)>(HV0210)){checker++;
data+="@HV0208,HV0210";
description+="@HV0210 is less than HV0208";
}
   if((HV0207)>(HV0210)){checker++;
data+="@HV0207,HV0210";
description+="@HV0210 is less than HV0207";
}
  
//2.3++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    PRIMARY
   if((HV0211)>(HV0204)){checker++;
data+="@HV0211,HV0204";
description+="@HV0204 is less than HV0211";
}
   if((HV0212)>(HV0211)){checker++;
data+="@HV0210,HV0204";
description+="@HV0204 is less than HV0210";
}
   
   
//2.4+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//  PRIMARY
   if((HV0213)>(HV0209)){checker++;
data+="@HV0213,HV0209";
description+="@HV0209 is less than HV0213";
}
   if((HV0214)>(HV0209)){checker++;
data+="@HV0214,HV0209";
description+="@HV0209 is less than HV0214";
}
   if((HV0215)>(HV0209)){checker++;
data+="@HV0215,HV0209";
description+="@HV0209 is less than HV0215";
}
   if((HV0216)>(HV0209)){checker++;
data+="@HV0216,HV0209";
description+="@HV0209 is less than HV0216";
}

  if((HV0217)!=(HV0209)){checker++;
data+="@HV0217,HV0209";
description+="@HV0209 is not equal to HV0217";
}

//   2.5+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if((HV0219)>(HV0209)){checker++;
data+="@HV0219,HV0209";
description+="@HV0209 is less than HV0219";
}
   if((HV0220)>(HV0209)){checker++;
data+="@HV0220,HV0209";
description+="@HV0209 is less than HV0220";
}
   if((HV0221)>(HV0209)){checker++;
data+="@HV0221,HV0209";
description+="@HV0209 is less than HV0221";
}
   
//   2.7+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if((HV0224)>(HV0228)){checker++;
data+="@HV0224,HV0228";
description+="@HV0228 is less than HV0224";
}
   if((HV0225)>(HV0228)){checker++;
data+="@HV0225,HV0228";
description+="@HV0228 is less than HV0225";
}
   if((HV0226)>(HV0228)){checker++;
data+="@HV0226,HV0228";
description+="@HV0228 is less than HV0226";
}
   
   if((HV0228)>(HV0240)){checker++;
data+="@HV0228,HV0240";
description+="@HV0240 is less than HV0228";
}
 
    
  //   2.8+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if((HV0229)>(HV0224)){checker++;
data+="@HV0229,HV0224";
description+="@HV0224 is less than HV0229";
}
   if((HV0230)>(HV0225)){checker++;
data+="@HV0225,HV0230";
description+="@HV0225 is less than HV0230";
}
   if((HV0231)>(HV0227)){checker++;
data+="@HV0227,HV0231";
description+="@HV0227 is less than HV0231";
}
   if((HV0232)>(HV0228)){checker++;
data+="@HV0228,HV0232";
description+="@HV0228 is less than HV0232";
}  
   
   //   2.9+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if((HV0236)>(HV0240)){checker++;
data+="@HV0236,HV0240";
description+="@HV0240 is less than HV0236";
}
   
  //   2.10+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if((HV0241)>((HV0206)+(HV0205))){checker++;
data+="@HV0205,HVO2O6,HV0241";
description+="@HV0241 is greater than th sum of HV0206 and HV0205";
}
   if((HV0241)>(HV0207)){checker++;
data+="@HV0207,HV0241";
description+="@HV0207 is less than HV0241";
}
   if((HV0241)>(HV0208)){checker++;
data+="@HV0208,HV0241";
description+="@HV0208 is less than HV0241";
}

 //SECONDARY
   if((HV0241)>(HV0209)){checker++;
data+="@HV0209,HV0241";
description+="@HV0209 is less than HV0241";
}
   if((HV0242)>(HV0209)){checker++;
data+="@HV0209,HV0242";
description+="@HV0209 is less than HV0242";
}
   if((HV0243)>(HV0209)){checker++;
data+="@HV0209,HV0243";
description+="@HV0209 is less than HV0243";
}
   
// CARE AND TREATMENT=====================================================================================  
   
   
 if((HV0240)<(HV0301)){checker++;
data+="@HV0301,HV0240";
description+="@HV0240 is less than HV0301";
   
   
 }
   if((HV0240)!=(HV0302)){checker++;
data+="@HV0302,HV0240";
description+="@HV0240 is not equal HV0302";
}
   if((HV0307)<((HV0303)+(HV0304))){checker++;
data+="@HV0303,HV0304,HV0307";
description+="@HV0307 is less than sum of HV0303 and HV0304";
}
   if((HV0307)<((HV0305)+(HV0306))){checker++;
data+="@HV0305,HV0306,HV0307";
description+="@HV0307 is less than sum of HV0305 and HV0306";
}
 //SECONDARY
   
   if((HV0307)>(HV0319)){checker++;
data+="@HV0307,HV0319";
description+="@HV0319 is less than HV0307";
}
   
   //   3.2+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if((HV0308)>((HV0309)+(HV0310))){checker++;
data+="@HV0308,HV0309,HV0310";
description+="@sum of HV0310 and HV0309 is less than HV0308";
}
   if((HV0313)<((HV0309)+(HV0310))){checker++;
data+="@HV0309,HV0310,HV0313";
description+="@HV0313 is less than sum of HV0309 and HV0310";
}
   if((HV0313)<((HV0311)+(HV0312))){checker++;
data+="@HV0311,HV0312,HV0313";
description+="@HV0313 is less than sum of HV0311 and HV0312";
}
 //SECONDARY
   
   if((HV0313)>=(HV0307)){checker++;
data+="@HV0307,HV0313";
description+="@HV0307 is less than or equal to  HV0313";
} 
   
  //   3.3+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
 if((HV0314)>((HV0315)+(HV0316))){checker++;
data+="@HV0314,HV0315,HV0316";
description+="@sum of HV0316 and HV0315 is less than HV0314";
 }
 
   if((HV0319)<((HV0315)+(HV0316))){checker++;
data+="@HV0315,HV0316,HV0319";
description+="@HV0319 is less than sum of HV0315 and HV0316";
 }
   if((HV0319)<((HV0317)+(HV0318))){checker++;
data+="@HV0317,HV0318,HV0319";
description+="@HV0319 is less than sum of HV0317 and HV0318";
}
 //SECONDARY
  
   if(((HV0303)+(HV0304))>((HV0315)+(HV0316))){checker++;
data+="@HV0303,HV0304,HV0315,HV0316";
description+="@sum of HV0303 and HV0304  is greater than sum of HV0315 and HV0316";
}
   if(((HV0311)+(HV0312))>((HV0315)+(HV0316))){checker++;
data+="@HV0311,HV0312,HV0315,HV0316";
description+="@sum of HV0311 and HV0312  is greater than sum of HV0315 and HV0316";
 }
   if((HV0313)>=(HV0319)){checker++;
data+="@HV0313,HV0319";
description+="@HV0319 is less than or equal to  HV0313";
}
   
    //   3.4+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if((HV0334)<(HV0320)){checker++;
data+="@HV0334,HV0320";
description+="@HV0320 is greater than HV0334";
}
   if((HV0325)<((HV0321)+(HV0322))){checker++;
data+="@HV0325,HV0321,HV0322";
description+="@HV0325 is less than sum of HV0321 and HV0322";
}
   if((HV0325)<((HV0323)+(HV0324))){checker++;
data+="@HV0323,HV0324,HV0325";
    
   
    
    description+="@HV0325 is less than sum of HV0323 and HV0324";
   
   
 }
   if((HV0325)!=((HV0321)+(HV0322)+(HV0323)+(HV0324))){checker++;
data+="@HV0321,HV0322,HV0323,HV0324,HV0325";
description+="@HV0325 is not equal to sum of HV0321,HV0322,HV0323 and HV0324";
   
}
 if((HV0326)>(HV0324)){checker++;
data+="@HV0324,HV0326";
description+="@HV0326 is greater than HV0324";
}
 
   if((HV0325)<(HV0327)){checker++;
data+="@HV0325,HV0327";
description+="@HV0327 is greater than HV0325";
}
 //SECONDARY
  
   if(((HV0321)+(HV0322))>((HV0335)+(HV0336))){checker++;
data+="@HV0321,HV0322,HV0335,HV0336";
description+="@sum of HV0321 and HV0322  is greater than sum of HV0335 and HV0336";
}
   if(((HV0323)+(HV0324))>((HV0337)+(HV0338))){checker++;
data+="@HV0323,HV0324,HV0337,HV0338";
description+="@sum of HV0323 and HV0324  is greater than sum of HV0337 and HV0338";
}
   if((HV0325)>(HV0339)){checker++;
data+="@HV0325,HV0339";
description+="@HV0325 is greater than HV0339";
}
   
     if((HV0221)>(HV0326)){checker++;
data+="@HV0221,HV0326";
description+="@HV0221 is greater than HV0326";
}
 
      //   3.5+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if((HV0320)>(HV0328)){checker++;
data+="@HV0320,HV0328";
description+="@HV0320 is greater than HV0328";
}
   if((HV0333)<((HV0329)+(HV0330))){checker++;
data+="@HV0333,HV0329,HV0330";
description+="@HV0333 is less than sum of HV0329 and HV0330";
}
   if((HV0333)<((HV0331)+(HV0332))){checker++;
data+="@HV0331,HV0332,HV0333";
description+="@HV0333 is less than sum of HV0331 and HV0332";
}
   if((HV0333)!=((HV0329)+(HV0330)+(HV0331)+(HV0332))){checker++;
data+="@HV0329,HV0330,HV0331,HV0332,HV0333";
description+="@HV0333 is not equal to sum of HV0329,HV0330,HV0331,HV0332";
}
  

   if(((HV0329)+(HV0330))>((HV0335)+(HV0336))){checker++;
data+="@HV0329,HV0330,HV0335,HV0336";
description+="@sum of HV0329 and HV0330  is greater than sum of HV0335 and HV0336";
 }
   if(((HV0331)+(HV0332))>((HV0337)+(HV0338))){checker++;
 data+="@HV0331,HV0332,HV0337,HV0338";
description+="@sum of HV0331 and HV0332  is greater than sum of HV0337 and HV0338";
}
   if((HV0333)>(HV0339)){checker++;
data+="@HV0333,HV0339";
description+="@HV0333 is greater than HV0339";
 }
   
   
 //   3.6+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY

   if((HV0328)>(HV0334)){checker++;
data+="@HV0328,HV0334";
description+="@HV0328 is greater than HV0334";
}

   if((HV0339)<((HV0335)+(HV0336))){checker++;
data+="@HV0335,HV0336,HV0339";
description+="@HV0339 is less than sum of HV0335 and HV0336";
}
   if((HV0339)<((HV0337)+(HV0338))){checker++;
data+="@HV0337,HV0338,HV0339";
description+="@HV0339 is less than sum of HV0337 and HV0338";
}
   if((HV0333)!=((HV0335)+(HV0336)+(HV0337)+(HV0338))){checker++;
data+="@HV0333,HV0335,HV0336,HV0337,HV0338";
description+="@HV0333 is not equal to sum of HV0335,HV0336,HV0337 and HV0338";
 }
 //SECONDARY
  if(((HV0335)+(HV0336))>((HV0340)+(HV0341))){checker++;
data+="@HV0335,HV0336,HV0340,HV0341";
description+="@sum of HV0335 and HV0336  is greater than sum of HV0340 and HV0341";
  }
   if(((HV0337)+(HV0338))>((HV0342)+(HV0343))){checker++;
data+="@HV0337,HV0338,HV0342,HV0343";
description+="@sum of HV0337 and HV0338  is greater than sum of HV0342 and HV0343";
}
   if((HV0333)>(HV0344)){checker++;
data+="@HV0333,HV0344";
description+="@HV0333 is greater than HV0344";
}  
   
  //   3.7+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if((HV0344)<((HV0340)+(HV0341))){checker++;
data+="@HV0340,HV0341,HV0344";
description+="@HV0344 is less than sum of HV0340 and HV0341";
}
   if((HV0344)<((HV0342)+(HV0343))){checker++;
data+="@HV0342,HV0343,HV0344";
description+="@HV0344 is less than sum of HV0342 and HV0343";
}
   if((HV0333)!=((HV0340)+(HV0341)+(HV0342)+(HV0343))){checker++;
data+="@HV0333,HV0340,HV0341,HV0341,HV0342,HV0343";
description+="@HV0333 is not equal to sum of HV0340,HV0341,HV0342 and HV0343";
}
   //   
 //   3.8+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if((HV0345)<(HV0349)){checker++;
data+="@HV0345,HV0349";
description+="@HV0349 is greater than HV0344";
}
   if((HV0346)>(HV0349)){checker++;
data+="@HV0346,HV0349";
description+="@HV0346 is greater than HV0349";
}  
   if((HV0347)>(HV0349)){checker++;
data+="@HV0347,HV0349";
description+="@HV0347 is greater than HV0349";
}
   if((HV0348)>(HV0349)){checker++;
data+="@HV0348,HV0349";
description+="@HV0348 is greater than HV0349";
} 
   if((HV0349)>(HV0345)){checker++;
data+="@HV0345,HV0349";
description+="@HV0349 is greater than HV0345";  
 }
    //SECONDARY
   if((HV0346)>(HV0345)){checker++;
data+="@HV0345,HV0346";
description+="@HV0346 is greater than HV0345";
} 
   if((HV0347)>(HV0345)){checker++;
data+="@HV0345,HV0347";
description+="@HV0347 is greater than HV0345";
}
   if((HV0348)>(HV0345)){checker++;
data+="@HV0345,HV0348";
description+="@HV0348 is greater than HV0345";
} 
   
    //   3.9+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if((HV0354)>(HV0373)){checker++;
data+="@HV0354,HV0373";
description+="@HV0354 is greater than HV0373";
}
   if((HV0355)>(HV0370)){checker++;
data+="@HV0355,HV0370";
description+="@HV0355 is greater than HV0370";
} 
   
   //   3.10+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if((HV0904)>(HV0373)){checker++;
data+="@HV0373,HV0904";
description+="@HV0904 is greater than HV0373";
}

  
   //   3.11+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if((HV0373)!=(HV0319)){checker++;
data+="@HV0373,HV0319";
description+="@HV0319 is not be equal to HV0373";
}
 
 if((HV0507)!=((HV0501)+(HV0502)+(HV0503)+(HV0504)+(HV0505)+(HV0506))){checker++;
data+="@HV0501,HV0502,HV0503,HV0504,HV0505,HV0506,HV0507";
description+="@HV0507 is not equal to the sum of  HV0501,HV0502,HV0503,HV0504,HV0505 and HV0506.";
}

   if(((HV0501)+(HV0502))!=((HV0508)+(HV0509))){checker++;
data+="@HV0501,HV0502,HV0508,HV0509";
description+="@sum of HV0501 and HV0502 is not equal to HV0508 and HV0509.";
}
 if(((HV0503)+(HV0504))!=((HV0510)+(HV0511))){checker++;
data+="@HV0503,HV0504,HV05,HV0510,HV0511";
description+="@ sum of HV0503 and HV0504 is not equal to HV0510 and HV0511.";
}
 
  if(((HV0505)+(HV0506))!=((HV0512)+(HV0513))){checker++;
data+="@HV0505,HV0506,HV0512,HV0513";
description+="@ sum of HV0505 and HV0506 is not equal to the sum of HV0512 and HV0513.";
}
 
 if((HV0514)!=((HV0508)+(HV0509)+(HV0510)+(HV0511)+(HV0512)+(HV0513))){checker++;
data+="@HV0508,HV0509,HV0510,HV0511,HV0512,HV0513,HV0514";
description+="@HV0514 is not equal to the sum HV0508,HV0509,HV0510,HV0511,HV0512 and HV0513.";
   
   
}

 
 String dqaid="";
      System.out.println("++++++++++++++++++++++++++++++++here++++++++++++++++++++++++++++++++++++++++++++");
     String dqachecker="SELECT id FROM dqa WHERE year='"+year+"' && month='"+month+"' && facilityid='"+facilityId+"' && form='moh731' LIMIT 1";
     conn.rs1=conn.st1.executeQuery(dqachecker);
     if(conn.rs1.next()==true){
        dqaid=conn.rs1.getString(1);
        
        if(!data.equals("")){
//           UPDATE DQA TABLE
             System.out.println("to update");
            String updator="UPDATE dqa SET columns=?, errors=? WHERE id=? ";
            conn.pst=conn.conn.prepareStatement(updator);
            conn.pst.setString(1, data);
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
         
    if(!data.equals("")) {
      System.out.println("to insert");
    String inserter="INSERT INTO dqa (tableid,form,facilityid,year,month,columns,errors) VALUES(?,?,?,?,?,?,?)";
    conn.pst=conn.conn.prepareStatement(inserter);
    conn.pst.setString(1, id);
    conn.pst.setString(2, "moh731");
    conn.pst.setString(3, facilityId);
    conn.pst.setString(4, year);
    conn.pst.setString(5, month);
    conn.pst.setString(6, data);
    conn.pst.setString(7, description);
    
    conn.pst.executeUpdate();
    }    
         
     }
counter++;

System.out.println("position : "+counter);
// end of facility data loop===================
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
        Logger.getLogger(errors731.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(errors731.class.getName()).log(Level.SEVERE, null, ex);
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
