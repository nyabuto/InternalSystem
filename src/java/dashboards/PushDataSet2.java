/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboards;

import database.dbConn;
import database.dbConnDash;
import java.sql.SQLException;
import org.json.simple.JSONObject;

/**
 *
 * @author GNyabuto
 */
//PUSHES ART,CARE Data for the year month period selected
public class PushDataSet2 {
    dbConn conn = new dbConn();
    dbConnDash conndash = new dbConnDash();
    
    public int new_art(int startyearmonth,int endyearmonth){
      int num=0;  
      
      
      
      
      return num;
    } 
    public int current_art_care(int startyearmonth,int endyearmonth) throws SQLException{
      int num=0;  
      String id_art,id_care;
      String facilitiestable="subpartnera";
      int HV0314,HV0315,HV0316,HV0317,HV0318,HV0319,HV0334,HV0335,HV0336,HV0337,HV0338;
      double c_f_1,c_f_4,c_f_9,c_f_14,c_f_19,c_f_24,c_f_29,c_f_34,c_f_39,c_f_49,c_f_50;
      double c_m_1,c_m_4,c_m_9,c_m_14,c_m_19,c_m_24,c_m_29,c_m_34,c_m_39,c_m_49,c_m_50;
      int under1_curcare,under1_curtx;
      float under1_curcarem,under1_curcaref,under1_curtxm,under1_curtxf;
        double currentART1M,currentART1_4M,currentART5_9M,currentART10_14M,currentART15_19M,currentART20_24M,currentART25_29m,currentART30_34m,currentART35_39m,currentART40_49m,currentART50M;
        double currentART1F,currentART1_4F,currentART5_9F,currentART10_14F,currentART15_19F,currentART20_24F,currentART25_29f,currentART30_34f,currentART35_39f,currentART40_49f,currentART50F;
        double totalCurrentART,totalCurrentCARE;
        double currentCARE1M,currentCARE1_4M,currentCARE5_9M,currentCARE10_14M,currentCARE15_19M,currentCARE20_24M,currentCARE25_49M,currentCARE50M;
        double currentCARE1F,currentCARE1_4F,currentCARE5_9F,currentCARE10_14F,currentCARE15_19F,currentCARE20_24F,currentCARE25_49F,currentCARE50F;
        String county,sub_county,facilityName,support_type,mfl_code,arthv,pmtcthv,htchv,allhv,burdencategory,constituency;
        String ward,Owner,Type,latitude,longitude,Male_clinics,Adolescent_clinics,Viremia_clinics,EMR_Sites,Link_desks,yearmonth;
        int errorCARE=0,errorART=0;
        String year,semi_annual,quarter,month;
               
     String getCurrent="SELECT "+facilitiestable+".SubPartnerNom AS facility,district.DistrictNom AS sub_county,county.County AS county,"+
    ""+facilitiestable+".CentreSanteId AS mfl_code,IFNULL(ART_Support,0) AS ART_Support,IFNULL(ART_highvolume,0) AS arthv, IFNULL(HTC_highvolume,0) AS htchv,IFNULL(PMTCT_highvolume,0) AS pmtcthv,"+
    "IFNULL(all_highvolume,0) AS  allhv,burden_cartegory AS burdencategory,constituency,ward, Owner,Type,latitude,longitude,IFNULL(Male_clinics,0) AS Male_clinics,IFNULL(Adolescent_clinics,0) AS Adolescent_clinics,"+
    "IFNULL(Viremia_clinics,0) AS Viremia_clinics,IFNULL(EMR_Sites,0) AS EMR_Sites,IFNULL(Link_desks,0) AS Link_desks,yearmonth,HV0314,HV0315,HV0316,HV0317,HV0318,HV0334,HV0335,HV0336,HV0337,HV0338,HV0319, HV0314 as under1_curcare,HV0334 as under1_curtx,"+
    " IFNULL(f_1,0) AS f_1,IFNULL(f_4,0) AS f_4,IFNULL(f_9,0) AS f_9,IFNULL(f_14,0) AS f_14,IFNULL(f_19,0) AS f_19,IFNULL(f_24,0) AS f_24,IFNULL(f_29,0) f_29,IFNULL(f_34,0) f_34," +
    "IFNULL(f_39,0) AS f_39,IFNULL(f_49,0) f_49,IFNULL(f_50,0) f_50, IFNULL(m_1,0) AS m_1,IFNULL(m_4,0) AS m_4,IFNULL(m_9,0) AS m_9,IFNULL(m_14,0) AS m_14,IFNULL(m_19,0) AS m_19," +
    "IFNULL(m_24,0) AS m_24,IFNULL(m_29,0) m_29,IFNULL(m_34,0) AS m_34,IFNULL(m_39,0) AS m_39,IFNULL(m_49,0) AS m_49,IFNULL(m_50,0) AS m_50"+
    ""
    + " FROM moh731 "
    + " LEFT JOIN "+facilitiestable+" ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
    + " LEFT JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
    + " LEFT JOIN county ON district.CountyID=county.CountyID "
    + " LEFT JOIN ratios ON county.CountyID=ratios.county_id "
    + " WHERE ART_Support IS NOT null AND yearmonth BETWEEN "+startyearmonth+" AND "+endyearmonth+""
    + " AND  indicator='TX_CURR'  AND ART=1 AND "+facilitiestable+".active=1 "
    + " GROUP BY yearmonth,mfl_code";

     conn.rs1=conn.st1.executeQuery(getCurrent);
     while(conn.rs1.next()==true){
       county = conn.rs1.getString("county");
       sub_county = conn.rs1.getString("sub_county");
       facilityName = conn.rs1.getString("facility");
       support_type = conn.rs1.getString("ART_Support");
       mfl_code = conn.rs1.getString("mfl_code");
       
       arthv = conn.rs1.getString("arthv");
       pmtcthv = conn.rs1.getString("pmtcthv");
       htchv = conn.rs1.getString("htchv");
       allhv = conn.rs1.getString("allhv");
       burdencategory = conn.rs1.getString("burdencategory");
       constituency = conn.rs1.getString("constituency");
       ward = conn.rs1.getString("ward");
       Owner = conn.rs1.getString("Owner");
       Type = conn.rs1.getString("Type");
       latitude = conn.rs1.getString("latitude");
       longitude = conn.rs1.getString("longitude");
       Male_clinics = conn.rs1.getString("Male_clinics");
       Adolescent_clinics = conn.rs1.getString("Adolescent_clinics");
       Viremia_clinics = conn.rs1.getString("Viremia_clinics");
       EMR_Sites = conn.rs1.getString("EMR_Sites");
       Link_desks = conn.rs1.getString("Link_desks");
       yearmonth = conn.rs1.getString("yearmonth");
       
     HV0314=conn.rs1.getInt("HV0314");
     HV0315=conn.rs1.getInt("HV0315");
     HV0316=conn.rs1.getInt("HV0316");
     HV0317=conn.rs1.getInt("HV0317");
     HV0318=conn.rs1.getInt("HV0318");
     HV0334=conn.rs1.getInt("HV0334");
     HV0335=conn.rs1.getInt("HV0335");
     HV0336=conn.rs1.getInt("HV0336");
     HV0337=conn.rs1.getInt("HV0337");
     HV0338=conn.rs1.getInt("HV0338");
     HV0319=conn.rs1.getInt("HV0319");
     
    c_f_1 = conn.rs1.getDouble("f_1");
    c_f_4 = conn.rs1.getDouble("f_4");
    c_f_9 = conn.rs1.getDouble("f_9");
    c_f_14 = conn.rs1.getDouble("f_14");
    c_f_19 = conn.rs1.getDouble("f_19");
    c_f_24 = conn.rs1.getDouble("f_24");
    c_f_29 = conn.rs1.getDouble("f_29");
    c_f_34 = conn.rs1.getDouble("f_34");
    c_f_39 = conn.rs1.getDouble("f_39");
    c_f_49 = conn.rs1.getDouble("f_49");
    c_f_50 = conn.rs1.getDouble("f_50");
    c_m_1 = conn.rs1.getDouble("m_1");
    c_m_4 = conn.rs1.getDouble("m_4");
    c_m_9 = conn.rs1.getDouble("m_9");
    c_m_14 = conn.rs1.getDouble("m_14");
    c_m_19 = conn.rs1.getDouble("m_19");
    c_m_24 = conn.rs1.getDouble("m_24");
    c_m_29 = conn.rs1.getDouble("m_29");
    c_m_34 = conn.rs1.getDouble("m_34");
    c_m_39 = conn.rs1.getDouble("m_39");
    c_m_49 = conn.rs1.getDouble("m_49");
    c_m_50 = conn.rs1.getDouble("m_50"); 
        
        
    under1_curcare=conn.rs1.getInt("under1_curcare");
    under1_curtx=conn.rs1.getInt("under1_curtx");
     
        
     double malepercentagecare=0.5;
            
            if(HV0316>HV0315){
           
            if(HV0315==0){
             malepercentagecare=0;
            }
            else {
            
             malepercentagecare=0.4;
            }
            }
            else if(HV0316<HV0315) {
           
            
            if(HV0316==0){
             malepercentagecare=1;
            }
            else {            
             malepercentagecare=0.6;
            }
            
            
            }
            else {
            malepercentagecare=0.5;
            }
        
     under1_curcarem=(float)Math.round((malepercentagecare*under1_curcare));   
     under1_curcaref=under1_curcare-under1_curcarem;    
            
      under1_curtxm=(float)Math.round((c_m_1*under1_curtx));   
     under1_curtxf=under1_curtx-under1_curtxm;

    if(support_type!=null){
     double splitData; int adderPos=0;
  
   int _HV0335=HV0335;
//    VALUES FOR CURRENT ON ART

 currentART1M=under1_curtxm;

 if(_HV0335>=under1_curtxm){     
 _HV0335=(int) (_HV0335-under1_curtxm); 
 }
 else {
      }

 currentART1_4M=(float)Math.round((c_m_4*_HV0335));
 currentART5_9M=(float)Math.round((c_m_9*_HV0335));
 currentART10_14M=(float)Math.round((c_m_14*_HV0335));
 
 
  splitData=currentART1M+currentART1_4M+currentART5_9M+currentART10_14M;
  adderPos=0;
 if((splitData-HV0335)>10 ||(HV0335-splitData)>10 ){errorART++;}
 else{
while(splitData<HV0335){ 
 if(adderPos<2){
  currentART10_14M+=1;   
 }
 else{
 currentART5_9M+=1;    
 }
splitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(splitData==HV0335){}
}

  splitData=currentART1M+currentART1_4M+currentART5_9M+currentART10_14M;
  adderPos=0;
  
while(splitData>HV0335){ 
 if(adderPos<2){
  currentART10_14M-=1;   
 }
 else{
 currentART5_9M-=1;    
 }
splitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(splitData==HV0335){}
}
 }

 currentART15_19M=(float)Math.round((c_m_19*HV0337));
 currentART20_24M=(float)Math.round((c_m_24*HV0337));
currentART25_29m=(float)Math.round((c_m_29*HV0337));
currentART30_34m=(float)Math.round((c_m_34*HV0337));
currentART35_39m=(float)Math.round((c_m_39*HV0337));
currentART40_49m=(float)Math.round((c_m_49*HV0337));

 currentART50M=(float)Math.round((c_m_50*HV0337));
 
 splitData=currentART15_19M+currentART20_24M+currentART25_29m+currentART30_34m+currentART35_39m+currentART40_49m+currentART50M;
  if((splitData-HV0337)>10 ||(HV0337-splitData)>10 ){errorART++;}
  else{
 while(splitData<HV0337){ 
 currentART40_49m+=1; 
 splitData++;
}
 
 splitData=currentART15_19M+currentART20_24M+currentART25_29m+currentART30_34m+currentART35_39m+currentART40_49m+currentART50M;
 while(splitData>HV0337){ 
 currentART40_49m-=1; 
 splitData--;
}
}
currentART1F=under1_curtxf;  //NEED CLARIFICATION

int _HV0336=HV0336;
 if(_HV0336>=under1_curtxf){     
 _HV0336=(int) (_HV0336-under1_curtxf);
 }
 else {}

currentART1_4F=(float)Math.round((c_f_4*_HV0336));
currentART5_9F=(float)Math.round((c_f_9*_HV0336));
currentART10_14F=(float)Math.round((c_f_14*_HV0336));



  splitData=currentART10_14F+currentART1_4F+currentART5_9F+currentART1F;
  adderPos=0;
   if((splitData-HV0336)>10 ||(HV0336-splitData)>10 ){errorART++;}
   else{
while(splitData<HV0336){ 
 if(adderPos<2){currentART10_14F+=1; }
 else{currentART5_9F+=1; }
splitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}

splitData=currentART10_14F+currentART1_4F+currentART5_9F+currentART1F;
  adderPos=0;
  
while(splitData>HV0336){ 
 if(adderPos<2){currentART10_14F-=1; }
 else{currentART5_9F-=1; }
splitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}
   }
   

   
currentART15_19F=(float)Math.round((c_f_19*HV0338));
currentART20_24F=(float)Math.round((c_f_24*HV0338));
currentART25_29f=(float)Math.round((c_f_29*HV0338));
currentART30_34f=(float)Math.round((c_f_34*HV0338));
currentART35_39f=(float)Math.round((c_f_39*HV0338));
currentART40_49f=(float)Math.round((c_f_49*HV0338));
currentART50F=(float)Math.round((c_f_50*HV0338));

 
 splitData=currentART15_19F+currentART20_24F+currentART25_29f+currentART30_34f+currentART35_39f+currentART40_49f+currentART50F;
  if((splitData-HV0338)>10 ||(HV0338-splitData)>10 ){errorART++;}
  else{
 while(splitData<HV0338){ 
 currentART20_24F+=1;
 splitData++;
}

  splitData=currentART15_19F+currentART20_24F+currentART25_29f+currentART30_34f+currentART35_39f+currentART40_49f+currentART50F;
 while(splitData>HV0338){ 
 currentART20_24F-=1;
 splitData--;
}
  }
totalCurrentART=HV0338+HV0336+HV0337+HV0335;
 
    
        currentCARE1M=under1_curcarem;
        
        int _HV0315=HV0315;
 if(_HV0315>=under1_curcarem){  
     
 _HV0315=(int) (_HV0315-under1_curcarem); 
//     System.out.println(facilityName+" under1 cur care m :"+under1_curcarem+" HV0315 "+HV0315+" _HV0315 "+_HV0315);
     //System.out.println(facilityName+" under1 f :"+under1_curtxf+" HV0336 "+HV0336+" _HV0336 "+_HV0336);
     
 }
 else {
//     System.out.println("Should have subtracted curcare but dint "+facilityName+" "+under1_curcarem+" "+HV0315);
      }
        
        currentCARE1_4M=(float)Math.round((0.17*_HV0315));
        currentCARE5_9M=(float)Math.round((0.26*_HV0315));
        currentCARE10_14M=(float)Math.round((0.57*_HV0315));
        
  

splitData=currentCARE10_14M+currentCARE5_9M+currentCARE1_4M+currentCARE1M;
adderPos=0;
 if((splitData-HV0315)>10 ||(HV0315-splitData)>10 ){errorCARE++;}
 else{
while(splitData<HV0315){ 
    switch (adderPos) {
        case 0:
            currentCARE10_14M+=1;
            break;
        case 1:
            currentCARE5_9M+=1;
            break;
        case 2:
            currentCARE1_4M+=1;
            break;
        default:
            break;
    }
 
splitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}  
 splitData=currentCARE10_14M+currentCARE5_9M+currentCARE1_4M+currentCARE1M;
adderPos=0;
while(splitData>HV0315){ 
    switch (adderPos) {
        case 0:
            currentCARE10_14M-=1;
            break;
        case 1:
            currentCARE5_9M-=1;
            break;
        case 2:
            currentCARE1_4M-=1;
            break;
        default:
            break;
    }
 
splitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
} 
 }
 

        currentCARE15_19M=(float)Math.round((0.05*HV0317));
        currentCARE20_24M=(float)Math.round((0.04*HV0317));
        currentCARE25_49M=(float)Math.round((0.73*HV0317));
        currentCARE50M=(float)Math.round((0.18*HV0317));
        
splitData=currentCARE50M+currentCARE25_49M+currentCARE20_24M+currentCARE15_19M;
 if((splitData-HV0317)>10 ||(HV0317-splitData)>10 ){errorCARE++;}
 else{
while(splitData<HV0317){ 
currentCARE25_49M+=1; 
splitData++;
}
 splitData=currentCARE50M+currentCARE25_49M+currentCARE20_24M+currentCARE15_19M;
while(splitData>HV0317){ 
currentCARE25_49M-=1; 
splitData--;
} 
 }
        currentCARE1F=under1_curcaref;
           int _HV0316=HV0316;
 if(_HV0316>=under1_curcaref){     
 _HV0316=(int) (_HV0316-under1_curcaref); 
//     System.out.println(facilityName+" under1 cur care f :"+under1_curcaref+" HV0316 "+HV0316+" _HV0316 "+_HV0316);
     //System.out.println(facilityName+" under1 f :"+under1_curtxf+" HV0336 "+HV0336+" _HV0336 "+_HV0336);
 }
 else {
//     System.out.println("Should have multiplied but dint ");
      }
        
        currentCARE1_4F=(float)Math.round((0.172*_HV0316));
        currentCARE5_9F=(float)Math.round((0.2545*_HV0316));
        currentCARE10_14F=(float)Math.round((0.5735*_HV0316));
        
  
        
splitData=currentCARE10_14F+currentCARE5_9F+currentCARE1_4F+currentCARE1F;
 if((splitData-HV0316)>10 ||(HV0316-splitData)>10 ){errorCARE++;}
 else{
adderPos=0;
while(splitData<HV0316){ 
    switch (adderPos) {
        case 0:
            currentCARE10_14F+=1;
            break;
        case 1:
            currentCARE5_9F+=1;
            break;
        case 2:
            currentCARE1_4F+=1;
            break;
        default:
            break;
    }
 
splitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}        
 splitData=currentCARE10_14F+currentCARE5_9F+currentCARE1_4F+currentCARE1F;
adderPos=0;
while(splitData>HV0316){ 
    switch (adderPos) {
        case 0:
            currentCARE10_14F-=1;
            break;
        case 1:
            currentCARE5_9F-=1;
            break;
        case 2:
            currentCARE1_4F-=1;
            break;
        default:
            break;
    }
 
splitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
} 
 }
 
 
        currentCARE15_19F=(float)Math.round((0.03*HV0318));
        currentCARE20_24F=(float)Math.round((0.07*HV0318));
        currentCARE25_49F=(float)Math.round((0.78*HV0318));
        currentCARE50F=(float)Math.round((0.12*HV0318));

splitData=currentCARE50F+currentCARE25_49F+currentCARE20_24F+currentCARE15_19F;
 if((splitData-HV0318)>10 ||(HV0318-splitData)>10 ){errorCARE++;}
 else{
while(splitData<HV0318){ 
currentCARE25_49F+=1; 
splitData++;
}
 splitData=currentCARE50F+currentCARE25_49F+currentCARE20_24F+currentCARE15_19F;
while(splitData>HV0318){ 
currentCARE25_49F-=1; 
splitData--;
}
 }
       totalCurrentCARE=HV0318+HV0316+HV0317+HV0315;
       double total_ART_f = currentART1F+currentART1_4F+currentART5_9F+currentART10_14F+currentART15_19F+currentART20_24F+currentART25_29f+currentART30_34f+currentART35_39f+currentART40_49f+currentART50F;
       double total_ART_m = currentART1M+currentART1_4M+currentART5_9M+currentART10_14M+currentART15_19M+currentART20_24M+currentART25_29m+currentART30_34m+currentART35_39m+currentART40_49m+currentART50M;
       JSONObject period_data;
       period_data = getperiod(yearmonth);

       year = period_data.get("year").toString();
       semi_annual = period_data.get("semi_annual").toString();
       quarter = period_data.get("quarter").toString();
       month = period_data.get("month").toString();
      
       String datacurr_ART []=(county+","+sub_county+","+facilityName+","+mfl_code+",DSD,"+totalCurrentART+","
           + ""+currentART1F+","+currentART1_4F+","+currentART5_9F+","+currentART10_14F+","+currentART15_19F+","
           + ""+currentART20_24F+","+currentART25_29f+","+currentART30_34f+","+currentART35_39f+","
           + ""+currentART40_49f+","+currentART50F+","+currentART1M+","+currentART1_4M+","+currentART5_9M+","+currentART10_14M+","
           + ""+currentART15_19M+","+currentART20_24M+","+currentART25_29m+","+currentART30_34m+","
           + ""+currentART35_39m+","+currentART40_49m+","+currentART50M+","+arthv+","+htchv+","+pmtcthv).split(",");
   
   String datacurr_CARE []=(county+","+sub_county+","+facilityName+","+mfl_code+",DSD,"+totalCurrentCARE+","
           + ""+currentCARE1F+","+currentCARE1_4F+","+currentCARE5_9F+","+currentCARE10_14F+","+currentCARE15_19F+","
           + ""+currentCARE20_24F+","+currentCARE25_49F+","+currentCARE50F+","+currentCARE1M+","+currentCARE1_4M+","
           + ""+currentCARE5_9M+","+currentCARE10_14M+","+currentCARE15_19M+","+currentCARE20_24M+","
           + ""+currentCARE25_49M+","+currentCARE50M+","+arthv+","+htchv+","+pmtcthv).split(",");
    
//   ART
    String[] headers_currART={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","f_1","m_1","f_4",
    "m_4","f_5_9","m_5_9","f_14","m_14","f_19","m_19","f_24","m_24","f_29","m_29","f_34",
    "m_34","f_39","m_39","f_49","m_49","f_50","m_50","total","total_f","total_m","year",
    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
    
    id_art = mfl_code+"_"+yearmonth+"_10_36";
    
    String[] data_currART =(id_art+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","
    + ""+mfl_code+","+support_type+",90:90=ON ART,TX,TX CURR,"+currentART1F+","+currentART1M+","+currentART1_4F+","+currentART1_4M+","
    + ""+currentART5_9F+","+currentART5_9M+","+currentART10_14F+","+currentART10_14M+","+currentART15_19F+","+currentART15_19M+","+currentART20_24F+","+
    currentART20_24M+","+currentART25_29f+","+currentART25_29m+","+currentART30_34f+","+currentART30_34m+","+currentART35_39f+","+currentART35_39m+","
    + ""+currentART40_49f+","+currentART40_49m+","+currentART50F+","+currentART50M+","+totalCurrentART+","+total_ART_f+","+total_ART_m+","+year+","+
    semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
    
    String query_params = "";
    for(int i=0;i<headers_currART.length;i++){
    query_params+=headers_currART[i]+"=?,";    
    }
    //remove last comma
    query_params = removeLastChars(query_params, 1);
    
   String curr_art = "REPLACE INTO table3 SET "+query_params;
   conndash.pst = conndash.conn.prepareStatement(curr_art);
   
    for(int i=0;i<data_currART.length;i++){
        conndash.pst.setString((i+1), data_currART[i]);   
    }
    conndash.pst.executeUpdate();
    
    
    //CARE
     double total_CARE_f = currentCARE1F+currentCARE1_4F+currentCARE5_9F+currentCARE10_14F+currentCARE15_19F+currentCARE20_24F+currentCARE25_49F+currentCARE50F;
     double total_CARE_m = currentCARE1M+currentCARE1_4M+currentCARE5_9M+currentCARE10_14M+currentCARE15_19M+currentCARE20_24M+currentCARE25_49M+currentCARE50M;
      
    String[] headers_currCARE={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","f_1","m_1","f_4",
    "m_4","f_5_9","m_5_9","f_14","m_14","f_19","m_19","f_24","m_24","f_25_49","m_25_49","f_50","m_50","total","total_f","total_m","year",
    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
    
    id_care = mfl_code+"_"+yearmonth+"_1_38";
    
    String[] data_currCARE =(id_care+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","
    + ""+mfl_code+","+support_type+",90:90=ON ART,CARE,CARE CURR,"+currentCARE1F+","+currentCARE1M+","+currentCARE1_4F+","+currentCARE1_4M+","
    + ""+currentCARE5_9F+","+currentCARE5_9M+","+currentCARE10_14F+","+currentCARE10_14M+","+currentCARE15_19F+","+currentCARE15_19M+","+currentCARE20_24F+","+
    currentCARE20_24M+","+currentCARE25_49F+","+currentCARE25_49M+","+currentCARE50F+","+currentCARE50M+","+totalCurrentCARE+","+total_CARE_f+","+total_CARE_m+","+year+","+
    semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
    
    query_params = "";
    for(int i=0;i<headers_currCARE.length;i++){
    query_params+=headers_currCARE[i]+"=?,";    
    }
    //remove last comma
    query_params = removeLastChars(query_params, 1);
    
   String curr_care = "REPLACE INTO table3 SET "+query_params;
   conndash.pst = conndash.conn.prepareStatement(curr_care);
   
    for(int i=0;i<data_currCARE.length;i++){
        conndash.pst.setString((i+1), data_currCARE[i]);   
    }
    conndash.pst.executeUpdate();
       
 }
    }
      return num;
    } 
    public int new_art_care(int startyearmonth,int endyearmonth) throws SQLException{
      int num=0;  
      String id_art,id_care;
      String facilitiestable="subpartnera";
      int HV0308,HV0309,HV0310,HV0311,HV0312,HV0320,HV0321,HV0322,HV0323,HV0324;
    int HV0210,HV0209,HV0205,HV0217,HV0216,HV0201;
    int HV0224,HV0225,HV0227,HV0232,HV0229,HV0230,HV0231;
    int HV0302,HV0206,HV0207,HV0208;
    int HV0319,HV0350,HV0351,HV0352,HV0353,HV0354;
      double f_1,f_4,f_9,f_14,f_19,f_24,f_29,f_34,f_39,f_49,f_50;
      double m_1,m_4,m_9,m_14,m_19,m_24,m_29,m_34,m_39,m_49,m_50;
        double newART1M,newART1_4M,newART5_9M,newART10_14M,newART15_19M,newART20_24M,newART25_29m,newART30_34m,newART35_39m,newART40_49m,newART50M;
        double newART1F,newART1_4F,newART5_9F,newART10_14F,newART15_19F,newART20_24F,newART25_29f,newART30_34f,newART35_39f,newART40_49f,newART50F;
        double totalNewART,totalNewCARE;
        double newCARE1M,newCARE1_4M,newCARE5_9M,newCARE10_14M,newCARE15_19M,newCARE20_24M,newCARE25_49M,newCARE50M;
        double newCARE1F,newCARE1_4F,newCARE5_9F,newCARE10_14F,newCARE15_19F,newCARE20_24F,newCARE25_49F,newCARE50F;
        String county,sub_county,facilityName,support_type,mfl_code,arthv,pmtcthv,htchv,allhv,burdencategory,constituency;
        String ward,Owner,Type,latitude,longitude,Male_clinics,Adolescent_clinics,Viremia_clinics,EMR_Sites,Link_desks,yearmonth;
        int errorCARE=0,errorART=0;
        String year,semi_annual,quarter,month;
    double under1_newcare=0,under1_newtx=0;
    double under1_newcarem=0,under1_newtxm=0;
    double under1_newcaref=0,under1_newtxf=0;
    double Pregnant,breastfeeding,ontbtreatment;
    
     String getnew="SELECT "+facilitiestable+".SubPartnerNom AS facility,district.DistrictNom AS sub_county,county.County AS county,"+
    ""+facilitiestable+".CentreSanteId AS mfl_code,IFNULL(ART_Support,0) AS ART_Support,IFNULL(ART_highvolume,0) AS arthv, IFNULL(HTC_highvolume,0) AS htchv,IFNULL(PMTCT_highvolume,0) AS pmtcthv,"+
    "IFNULL(all_highvolume,0) AS  allhv,burden_cartegory AS burdencategory,constituency,ward, Owner,Type,latitude,longitude,IFNULL(Male_clinics,0) AS Male_clinics,IFNULL(Adolescent_clinics,0) AS Adolescent_clinics,"+
    "IFNULL(Viremia_clinics,0) AS Viremia_clinics,IFNULL(EMR_Sites,0) AS EMR_Sites,IFNULL(Link_desks,0) AS Link_desks,yearmonth,"
    + "SUM(HV0308) as HV0308,SUM(HV0309) as HV0309,SUM(HV0310) as HV0310,SUM(HV0311) as HV0311,SUM(HV0312) as HV0312,"
    + "SUM(HV0320) as HV0320 ,SUM(HV0321) as HV0321,SUM(HV0322) as HV0322,SUM(HV0323) as HV0323,SUM(HV0324) as HV0324,"
    + "SUM(HV0205) as HV0205,SUM(HV0209)as HV0209,SUM(HV0210) as HV0210,SUM(HV0216) as HV0216,SUM(HV0217) as HV0217,"
    + "SUM(HV0224) as HV0224,SUM(HV0225) as HV0225,SUM(HV0227) as HV0227,SUM(HV0229) as HV0229,SUM(HV0230) as HV0230,SUM(HV0231) as HV0231,SUM(HV0232) as HV0232,"
    + "SUM(HV0302) as HV0302,SUM(HV0206) as HV0206,SUM(HV0207) as HV0207,SUM(HV0208) as HV0208"
    + ",SUM(HV0350) as HV0350,SUM(HV0351) as HV0351,SUM(HV0352) as HV0352,SUM(HV0353) as HV0353,SUM(HV0354) as HV0354,"
    + " SUM(HV0320) as under1_newtx,SUM(HV0308) as under1_newcare , "
    + "IFNULL(SUM(HV0326),0) as pregnant, SUM(HV0327) as tbtreatment, SUM(HV0217-HV0241) as breastfeeding,SUM(HV0201) as HV0201,  "+
    " IFNULL(f_1,0) AS f_1,IFNULL(f_4,0) AS f_4,IFNULL(f_9,0) AS f_9,IFNULL(f_14,0) AS f_14,IFNULL(f_19,0) AS f_19,IFNULL(f_24,0) AS f_24,IFNULL(f_29,0) f_29,IFNULL(f_34,0) f_34," +
    "IFNULL(f_39,0) AS f_39,IFNULL(f_49,0) f_49,IFNULL(f_50,0) f_50, IFNULL(m_1,0) AS m_1,IFNULL(m_4,0) AS m_4,IFNULL(m_9,0) AS m_9,IFNULL(m_14,0) AS m_14,IFNULL(m_19,0) AS m_19," +
    "IFNULL(m_24,0) AS m_24,IFNULL(m_29,0) m_29,IFNULL(m_34,0) AS m_34,IFNULL(m_39,0) AS m_39,IFNULL(m_49,0) AS m_49,IFNULL(m_50,0) AS m_50"+
    ""
    + " FROM moh731 "
    + " LEFT JOIN "+facilitiestable+" ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
    + " LEFT JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
    + " LEFT JOIN county ON district.CountyID=county.CountyID "
    + " LEFT JOIN ratios ON county.CountyID=ratios.county_id "
    + " WHERE ART_Support IS NOT null AND yearmonth BETWEEN "+startyearmonth+" AND "+endyearmonth+""
    + " AND  indicator='TX_NEW' AND ART=1  AND "+facilitiestable+".active=1 "
    + " GROUP BY yearmonth,mfl_code";

     conn.rs=conn.st.executeQuery(getnew);
     while(conn.rs.next()==true){
         
    newART1M=newART1_4M=newART5_9M=newART10_14M=newART15_19M=newART20_24M=newART50M=Pregnant=breastfeeding=ontbtreatment=0;
    newART1F=newART1_4F=newART5_9F=newART10_14F=newART15_19F=newART20_24F=newART50F=0; 
    newART25_29f=newART30_34f=newART35_39f=newART40_49f=0;
    newART25_29m=newART30_34m=newART35_39m=newART40_49m=0;
    newCARE1M=newCARE1_4M=newCARE5_9M=newCARE10_14M=newCARE15_19M=newCARE20_24M=newCARE25_49M=newCARE50M=0;
    newCARE1F=newCARE1_4F=newCARE5_9F=newCARE10_14F=newCARE15_19F=newCARE20_24F=newCARE25_49F=newCARE50F=0;
         
         
         
         
         
       county = conn.rs.getString("county");
       sub_county = conn.rs.getString("sub_county");
       facilityName = conn.rs.getString("facility");
       support_type = conn.rs.getString("ART_Support");
       mfl_code = conn.rs.getString("mfl_code");
       
       arthv = conn.rs.getString("arthv");
       pmtcthv = conn.rs.getString("pmtcthv");
       htchv = conn.rs.getString("htchv");
       allhv = conn.rs.getString("allhv");
       burdencategory = conn.rs.getString("burdencategory");
       constituency = conn.rs.getString("constituency");
       ward = conn.rs.getString("ward");
       Owner = conn.rs.getString("Owner");
       Type = conn.rs.getString("Type");
       latitude = conn.rs.getString("latitude");
       longitude = conn.rs.getString("longitude");
       Male_clinics = conn.rs.getString("Male_clinics");
       Adolescent_clinics = conn.rs.getString("Adolescent_clinics");
       Viremia_clinics = conn.rs.getString("Viremia_clinics");
       EMR_Sites = conn.rs.getString("EMR_Sites");
       Link_desks = conn.rs.getString("Link_desks");
       yearmonth = conn.rs.getString("yearmonth");
       
        HV0308=conn.rs.getInt("HV0308");
        HV0309=conn.rs.getInt("HV0309");
        HV0310=conn.rs.getInt("HV0310");
        HV0311=conn.rs.getInt("HV0311");
        HV0312=conn.rs.getInt("HV0312");
        HV0320=conn.rs.getInt("HV0320");
        HV0321=conn.rs.getInt("HV0321");
        HV0322=conn.rs.getInt("HV0322");
        HV0323=conn.rs.getInt("HV0323");
        HV0324=conn.rs.getInt("HV0324");
        HV0205=conn.rs.getInt("HV0205");
        HV0209=conn.rs.getInt("HV0209");
        HV0210=conn.rs.getInt("HV0210");
        HV0216=conn.rs.getInt("HV0216");
        HV0217=conn.rs.getInt("HV0217");
        HV0224=conn.rs.getInt("HV0224");
        HV0225=conn.rs.getInt("HV0225");
        HV0227=conn.rs.getInt("HV0227");
        HV0229=conn.rs.getInt("HV0229");
        HV0230=conn.rs.getInt("HV0230");
        HV0231=conn.rs.getInt("HV0231");
        HV0232=conn.rs.getInt("HV0232");
        HV0302=conn.rs.getInt("HV0302");
        HV0206=conn.rs.getInt("HV0206");
        HV0207=conn.rs.getInt("HV0207");
        HV0208=conn.rs.getInt("HV0208");
        HV0350=conn.rs.getInt("HV0350");
        HV0351=conn.rs.getInt("HV0351");
        HV0352=conn.rs.getInt("HV0352");
        HV0353=conn.rs.getInt("HV0353");
        HV0354=conn.rs.getInt("HV0354");
     
    f_1 = conn.rs.getDouble("f_1");
    f_4 = conn.rs.getDouble("f_4");
    f_9 = conn.rs.getDouble("f_9");
    f_14 = conn.rs.getDouble("f_14");
    f_19 = conn.rs.getDouble("f_19");
    f_24 = conn.rs.getDouble("f_24");
    f_29 = conn.rs.getDouble("f_29");
    f_34 = conn.rs.getDouble("f_34");
    f_39 = conn.rs.getDouble("f_39");
    f_49 = conn.rs.getDouble("f_49");
    f_50 = conn.rs.getDouble("f_50");
    m_1 = conn.rs.getDouble("m_1");
    m_4 = conn.rs.getDouble("m_4");
    m_9 = conn.rs.getDouble("m_9");
    m_14 = conn.rs.getDouble("m_14");
    m_19 = conn.rs.getDouble("m_19");
    m_24 = conn.rs.getDouble("m_24");
    m_29 = conn.rs.getDouble("m_29");
    m_34 = conn.rs.getDouble("m_34");
    m_39 = conn.rs.getDouble("m_39");
    m_49 = conn.rs.getDouble("m_49");
    m_50 = conn.rs.getDouble("m_50"); 
        
        
     
        
      Pregnant=conn.rs.getInt("pregnant");
      breastfeeding=conn.rs.getInt("breastfeeding");
//      breastfeeding=0;
      ontbtreatment=conn.rs.getInt("tbtreatment");
    
      
       double under1txratio_male = 0.5;
      double under1txratio_female=0.5;
      
      
        double under1careratio_male = 0.5;
      double under1careratio_female=0.5;
      
      
      
      if(HV0321>=HV0322){
      
           if(HV0322==0){
               //if under 15 for females is 0, the take 100% to male
             under1txratio_male=1;
      under1txratio_female=0;
           }
           else {
               
           
      under1txratio_male=0.6;
      under1txratio_female=0.4;
           }
      }
      else   if(HV0321<HV0322){ 
        if(HV0321==0){
               //if under 15 for females is 0, the take 100% to male
             under1txratio_female=1;
      under1txratio_male=0;
           }
          
          else {         
      under1txratio_female=0.6;
      under1txratio_male=0.4;
      }
      }
      
     
       
       //care
       
       
      if(HV0309>=HV0310){
          
          
          if(HV0310==0){
               //if under 15 for females is 0, the take 100% to male
       under1careratio_male=1;
      under1careratio_female=0;
           }
           else {
      
      under1careratio_male=0.6;
      under1careratio_female=0.4;
          }
      }
      else   if(HV0309<HV0310){ 
      
           if(HV0309==0){
               //if under 15 for females is 0, the take 100% to male
      under1careratio_female=1;
      under1careratio_male=0;
           }
           else {
          
          
        under1careratio_male=0.4;
      under1careratio_female=0.6;
      }
      
      }
      
     under1_newtx=conn.rs.getInt("under1_newtx");
     
     under1_newtxm=(float)Math.round((m_1*under1_newtx));   
     under1_newtxf=under1_newtx-under1_newtxm;
     
     
     under1_newcare=conn.rs.getInt("under1_newcare");   
     under1_newcarem=(float)Math.round((under1careratio_male*under1_newcare));   
     under1_newcaref=under1_newcare-under1_newcarem;
     
     
     
     
        newART1M=under1_newtxm;
        newART1_4M=(float)Math.round((m_4*HV0321));
        newART5_9M=(float)Math.round((m_9*HV0321));
        newART10_14M=(float)Math.round((m_14*HV0321));
       
        if(newART1_4M>=under1_newtxm){
 newART1_4M=newART1_4M-under1_newtxm;
    }
 else {
//     System.out.println("Should have multiplied but dint ");
 }
   double splitData;
   int adderPos;
splitData=newART10_14M+newART1_4M+newART5_9M+newART1M;
adderPos=0;
 if((splitData-HV0321)>10 || (HV0321-splitData)>10 ){errorART++;}
 else{
while(splitData<HV0321){ 
 if(adderPos==0){newART10_14M+=1; splitData++;}
 else if(adderPos==1){newART5_9M+=1; splitData++; }
 else{newART1_4M+=1; splitData++;}
 
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}
 
splitData=newART10_14M+newART1_4M+newART5_9M+newART1M;
adderPos=0;
while(splitData>HV0321){
 if(newART10_14M==0 && newART5_9M==0 && newART1_4M==0){
     if(HV0320<=(HV0321+HV0322)){
     newART1M--; 
     newART1F++;
        if(newART1_4F!=0){
        newART1_4F--; 
        }
        else if(newART5_9F!=0){
        newART5_9F--; 
        }
        else if(newART10_14F!=0){
        newART10_14F--; 
        }
 splitData--;  
 adderPos--;
     }
     else{
         errorART  = 900000;
         break;
     }
 }
 else if(adderPos==0){if(newART10_14M>0){newART10_14M-=1; splitData--;} }
 else if(adderPos==1){ if(newART5_9M>0){newART5_9M-=1; splitData--;} }
 else{if(newART1_4M>0){newART1_4M-=1; splitData--;} }
 
adderPos++;
 if(adderPos>2){adderPos=0;}
}
 }
      
        newART15_19M=(float)Math.round((m_19*HV0323));
        newART20_24M=(float)Math.round((m_24*HV0323));
        newART25_29m=(float)Math.round((m_29*HV0323));
        newART30_34m=(float)Math.round((m_34*HV0323));
        newART35_39m=(float)Math.round((m_39*HV0323));
        newART40_49m=(float)Math.round((m_49*HV0323));
        newART50M=(float)Math.round((m_50*HV0323));
        
        
        
        splitData=newART50M+newART20_24M+newART15_19M+newART25_29m+newART30_34m+newART35_39m+newART40_49m;
         if((splitData-HV0323)>10 ||(HV0323-splitData)>10 ){errorART++;}
         else{
//   System.out.println("split data : "+splitData+" all data "+HV0323);     
adderPos=0;
while(splitData<HV0323){ 
 if(adderPos==0 || adderPos==3){newART40_49m+=1; splitData++; }
 if(adderPos==1 || adderPos==4){newART50M+=1; splitData++; }
 if(adderPos==2){newART35_39m+=1; splitData++; }
 if(adderPos==5){newART15_19M+=1; splitData++; }
 if(adderPos==6){newART30_34m+=1; splitData++; }
 if(adderPos==7){newART25_29m+=1; splitData++; }
 if(adderPos==8){newART20_24M+=1; splitData++; }
adderPos++  ;
 if(adderPos>8){adderPos=0;}
}
  splitData=newART50M+newART20_24M+newART15_19M+newART25_29m+newART30_34m+newART35_39m+newART40_49m;
//   System.out.println("split data : "+splitData+" all data"+HV0323);     
adderPos=0;
while(splitData>HV0323){ 
 if(adderPos==0 || adderPos==3){if(newART40_49m>0){newART40_49m-=1; splitData--;} }
 if(adderPos==1 || adderPos==4){if(newART50M>0){newART50M-=1; splitData--;} }
 if(adderPos==2){if(newART35_39m>0){newART35_39m-=1; splitData--;} }
 if(adderPos==5){if(newART15_19M>0){newART15_19M-=1; splitData--;} }
  if(adderPos==6){if(newART30_34m>0){newART30_34m-=1; splitData--;} }
 if(adderPos==7){if(newART25_29m>0){newART25_29m-=1; splitData--;} }
 if(adderPos==8){if(newART20_24M>0){newART20_24M-=1; splitData--;} }
adderPos++  ;
 if(adderPos>8){adderPos=0;}
}   }
        newART1F=under1_newtxf;
        newART1_4F=(float)Math.round((f_4*HV0322));
        newART5_9F=(float)Math.round((f_9*HV0322));
        newART10_14F=(float)Math.round((f_14*HV0322));
        
   if(newART1_4F>=under1_newtxf){
 newART1_4F=newART1_4F-under1_newtxf;
     }
 else {
//     System.out.println("Should have deducted newtx but dint "+facilityName+" "+newART1_4F+" -- "+under1_newtxf);
      }
    
splitData=newART10_14F+newART1_4F+newART5_9F+newART1F;
adderPos=0;
 if((splitData-HV0322)>10 ||(HV0322-splitData)>10 ){errorART++;}
// else{
while(splitData<HV0322){ 
 if(adderPos==0){newART10_14F+=1; splitData++; }
 else if(adderPos==1){newART5_9F+=1; splitData++; }
 else{newART1_4F+=1; splitData++; }
adderPos++  ;
 if(adderPos>2){adderPos=0;}
//    System.out.println("looping on HV0322+");
}   
  
splitData=newART10_14F+newART1_4F+newART5_9F+newART1F;
adderPos=0;
while(splitData>HV0322){ 
    if(newART10_14F==0 && newART5_9F==0 && newART1_4F==0){
        
        if(HV0320<=(HV0321+HV0322)){
        newART1F--; 
        newART1M++; 
        if(newART1_4M!=0){
        newART1_4M--; 
        }
        else if(newART5_9M!=0){
        newART5_9M--; 
        }
        else if(newART10_14M!=0){
        newART10_14M--; 
        }
        splitData--; 
        adderPos--;
        }
        else{
            errorART = 900000;
         break;
     }
        }
    else if(adderPos==0){if(newART10_14F>0){newART10_14F-=1; splitData--;} }
 else if(adderPos==1){ if(newART5_9F>0){newART5_9F-=1; splitData--;} }
 else{if(newART1_4F>0){newART1_4F-=1; splitData--;} }
adderPos++  ;
 if(adderPos>2){adderPos=0;}
//     System.out.println("looping on HV0322- split data"+splitData+" HV0322 : "+HV0322+" "+newART1_4F+" "+newART5_9F+" "+newART10_14F);
}
 //}
  
//  NORMALIZE ROUND 2




//END OF ROUND 2
        newART15_19F=(float)Math.round((f_19*HV0324));
        newART20_24F=(float)Math.round((f_24*HV0324));
        newART25_29f=(float)Math.round((f_29*HV0324));
        newART30_34f=(float)Math.round((f_34*HV0324));
        newART35_39f=(float)Math.round((f_39*HV0324));
        newART40_49f=(float)Math.round((f_49*HV0324));
        newART50F=(float)Math.round((f_50*HV0324));


    splitData=newART25_29f+newART30_34f+newART35_39f+newART40_49f+newART50F+newART20_24F+newART15_19F;
adderPos=0;
 if((splitData-HV0324)>10 ||(HV0324-splitData)>10 ){errorART++;}
 else{
 
while(splitData<HV0324){ 
 if(adderPos==0 || adderPos==3){newART40_49f+=1; splitData++; }
 if(adderPos==1 || adderPos==4){newART50F+=1; splitData++; }
 if(adderPos==2){newART35_39f+=1; splitData++; }
 if(adderPos==5){newART15_19F+=1; splitData++; }
  if(adderPos==6){newART30_34f+=1; splitData++; }
 if(adderPos==7){newART25_29f+=1; splitData++; }
 if(adderPos==8){newART20_24F+=1; splitData++; }
adderPos++  ;
 if(adderPos>8){adderPos=0;}
//     System.out.println("looping on HV0324+");
}     
     splitData=newART25_29f+newART30_34f+newART35_39f+newART40_49f+newART50F+newART20_24F+newART15_19F;
adderPos=0;
while(splitData>HV0324){ 
 if(adderPos==0 || adderPos==3){if(newART40_49f>0){newART40_49f-=1; splitData--;} }
 if(adderPos==1 || adderPos==4){if(newART50F>0){newART50F-=1; splitData--;} }
 if(adderPos==2){if(newART35_39f>0){newART35_39f-=1; splitData--;} }
 if(adderPos==5){if(newART15_19F>0){newART15_19F-=1; splitData--;} }
 if(adderPos==6){if(newART30_34f>0){newART30_34f-=1; splitData--;} }
 if(adderPos==7){if(newART25_29f>0){newART25_29f-=1; splitData--;} }
 if(adderPos==8){if(newART20_24F>0){newART20_24F-=1; splitData--;} }
adderPos++  ;
 if(adderPos>8){adderPos=0;}
//     System.out.println("looping on HV0324-");
}
 }
        totalNewART=HV0324+HV0322+HV0323+HV0321;
//        System.out.println();
   //    VALUES
//  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//     >>>>>>>>>>>>>>>>>>>>>>>>>>.CARE STARTS HERE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        newCARE1M=under1_newcarem;
         int _HV0309=HV0309;
 if(_HV0309>=under1_newcarem){     
 _HV0309=(int) (_HV0309-under1_newcarem); 
//     System.out.println(facilityName+" under1 care m :"+under1_newcarem+" HV0309 "+HV0309+" _HV0309 "+_HV0309);
     //System.out.println(facilityName+" under1 f :"+under1_curtxf+" HV0336 "+HV0336+" _HV0336 "+_HV0336);
 }
 else {
//     System.out.println("Should have multiplied but dint ");
      }
        
        newCARE1_4M=((float)Math.round((0.52*_HV0309)) );
        newCARE5_9M=(float)Math.round((0.28*_HV0309));
        newCARE10_14M=(float)Math.round((0.20*_HV0309));
        
 
  
splitData=newCARE10_14M+newCARE5_9M+newCARE1_4M+newCARE1M;
adderPos=0;
 if((splitData-HV0309)>10 || (HV0309-splitData)>10 ){errorCARE++;}
 else{
while(splitData<HV0309){ 
 if(adderPos==0){newCARE1_4M+=1; }
 else if(adderPos==1){newCARE5_9M+=1; }
 else if(adderPos==2){newCARE10_14M+=1; }
 else if(adderPos==3){newCARE1M+=1; }
 
splitData++;
adderPos++  ;
 if(adderPos>3){adderPos=0;}
}
 splitData=newCARE10_14M+newCARE5_9M+newCARE1_4M+newCARE1M;
adderPos=0;
while(splitData>HV0309){ 
 if(adderPos==0){newCARE1_4M-=1; }
 else if(adderPos==1){newCARE5_9M-=1; }
 else if(adderPos==2){newCARE10_14M-=1; }
 else if(adderPos==3){newCARE1M-=1; }
 
splitData--;
adderPos++  ;
 if(adderPos>3){adderPos=0;}
}
 }
 
 
 
        newCARE15_19M=(float)Math.round((0.02*HV0311));
        newCARE20_24M=(float)Math.round((0.09*HV0311));
        newCARE25_49M=(float)Math.round((0.80*HV0311));
        newCARE50M=(float)Math.round((0.09*HV0311));
        
splitData=newCARE50M+newCARE25_49M+newCARE20_24M+newCARE15_19M;
 if((splitData-HV0311)>10 ||(HV0311-splitData)>10 ){errorCARE++;}
 else{
 
while(splitData<HV0311){ 
newCARE25_49M+=1; 
splitData++;
}

splitData=newCARE50M+newCARE25_49M+newCARE20_24M+newCARE15_19M;
while(splitData>HV0311){ 
newCARE25_49M-=1; 
splitData--;
}        
 }
       newCARE1F=under1_newcaref;
       
        int _HV0310=HV0310;
 if(_HV0310>=under1_newcaref){     
 _HV0310=(int) (_HV0310-under1_newcaref); 
 }
 else {
      }
       
        newCARE1_4F=(float)Math.round((0.52*_HV0310));
        newCARE5_9F=(float)Math.round((0.28*_HV0310));
        newCARE10_14F=(float)Math.round((0.20*_HV0310));
        
  
        
splitData=newCARE10_14F+newCARE5_9F+newCARE1_4F+newCARE1F;
adderPos=0;
 if((splitData-HV0310)>10 ||(HV0310-splitData)>10 ){errorCARE++;}
 else{
while(splitData<HV0310){ 
 if(adderPos==0){newCARE1_4F+=1; }
 else if(adderPos==1){newCARE5_9F+=1; }
 else if(adderPos==2){newCARE10_14F+=1; }
 else if(adderPos==3){newCARE1F+=1; }
 
splitData++;
adderPos++  ;
 if(adderPos>3){adderPos=0;}
}
 
splitData=newCARE10_14F+newCARE5_9F+newCARE1_4F+newCARE1F;
adderPos=0;
while(splitData>HV0310){ 
 if(adderPos==0){newCARE1_4F-=1; }
 else if(adderPos==1){newCARE5_9F-=1; }
 else if(adderPos==2){newCARE10_14F-=1; }
 else if(adderPos==3){newCARE1F-=1; }
 
splitData--;
adderPos++  ;
 if(adderPos>3){adderPos=0;}
}
 }


 
        newCARE15_19F=(float)Math.round((0.02*HV0312));
        newCARE20_24F=(float)Math.round((0.09*HV0312));
        newCARE25_49F=(float)Math.round((0.80*HV0312));
        newCARE50F=(float)Math.round((0.09*HV0312));
       
splitData=newCARE50F+newCARE25_49F+newCARE20_24F+newCARE15_19F;
 if((splitData-HV0312)>10 ||(HV0312-splitData)>10 ){errorCARE++;}
 else{
while(splitData<HV0312){ 
newCARE25_49F+=1; 
splitData++;
}
splitData=newCARE50F+newCARE25_49F+newCARE20_24F+newCARE15_19F;
while(splitData>HV0312){ 
newCARE25_49F-=1; 
splitData--;
}
 }
        totalNewCARE=HV0312+HV0310+HV0311+HV0309;

  
   
       double total_ART_f = newART1F+newART1_4F+newART5_9F+newART10_14F+newART15_19F+newART20_24F+newART25_29f+newART30_34f+newART35_39f+newART40_49f+newART50F;
       double total_ART_m = newART1M+newART1_4M+newART5_9M+newART10_14M+newART15_19M+newART20_24M+newART25_29m+newART30_34m+newART35_39m+newART40_49m+newART50M;
       JSONObject period_data = getperiod(yearmonth);

       year = period_data.get("year").toString();
       semi_annual = period_data.get("semi_annual").toString();
       quarter = period_data.get("quarter").toString();
       month = period_data.get("month").toString();
    
//   ART
    String[] headers_newART={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","f_1","m_1","f_4",
    "m_4","f_5_9","m_5_9","f_14","m_14","f_19","m_19","f_24","m_24","f_29","m_29","f_34",
    "m_34","f_39","m_39","f_49","m_49","f_50","m_50","total","total_f","total_m","year",
    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
    
    id_art = mfl_code+"_"+yearmonth+"_10_35";
    
    String[] data_newART =(id_art+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","
    + ""+mfl_code+","+support_type+",90:90=ON ART,TX,TX NEW,"+newART1F+","+newART1M+","+newART1_4F+","+newART1_4M+","
    + ""+newART5_9F+","+newART5_9M+","+newART10_14F+","+newART10_14M+","+newART15_19F+","+newART15_19M+","+newART20_24F+","+
    newART20_24M+","+newART25_29f+","+newART25_29m+","+newART30_34f+","+newART30_34m+","+newART35_39f+","+newART35_39m+","
    + ""+newART40_49f+","+newART40_49m+","+newART50F+","+newART50M+","+totalNewART+","+total_ART_f+","+total_ART_m+","+year+","+
    semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
    
    String query_params = "";
    for(int i=0;i<headers_newART.length;i++){
    query_params+=headers_newART[i]+"=?,";    
    }
    //remove last comma
    query_params = removeLastChars(query_params, 1);
    
   String new_art = "REPLACE INTO table3 SET "+query_params;
   conndash.pst = conndash.conn.prepareStatement(new_art);
   
    for(int i=0;i<data_newART.length;i++){
        conndash.pst.setString((i+1), data_newART[i]);   
    }
    conndash.pst.executeUpdate();
    
    
    //CARE
     double total_CARE_f = newCARE1F+newCARE1_4F+newCARE5_9F+newCARE10_14F+newCARE15_19F+newCARE20_24F+newCARE25_49F+newCARE50F;
     double total_CARE_m = newCARE1M+newCARE1_4M+newCARE5_9M+newCARE10_14M+newCARE15_19M+newCARE20_24M+newCARE25_49M+newCARE50M;
      
    String[] headers_newCARE={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","f_1","m_1","f_4",
    "m_4","f_5_9","m_5_9","f_14","m_14","f_19","m_19","f_24","m_24","f_25_49","m_25_49","f_50","m_50","total","total_f","total_m","year",
    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
    
    id_care = mfl_code+"_"+yearmonth+"_1_37";
    
    String[] data_newCARE =(id_care+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","
    + ""+mfl_code+","+support_type+",90:90=ON ART,CARE,CARE NEW,"+newCARE1F+","+newCARE1M+","+newCARE1_4F+","+newCARE1_4M+","
    + ""+newCARE5_9F+","+newCARE5_9M+","+newCARE10_14F+","+newCARE10_14M+","+newCARE15_19F+","+newCARE15_19M+","+newCARE20_24F+","+
    newCARE20_24M+","+newCARE25_49F+","+newCARE25_49M+","+newCARE50F+","+newCARE50M+","+totalNewCARE+","+total_CARE_f+","+total_CARE_m+","+year+","+
    semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
    
    query_params = "";
    for(int i=0;i<headers_newCARE.length;i++){
    query_params+=headers_newCARE[i]+"=?,";    
    }
    //remove last comma
    query_params = removeLastChars(query_params, 1);
    
   String new_care = "REPLACE INTO table3 SET "+query_params;
   conndash.pst = conndash.conn.prepareStatement(new_care);
   
    for(int i=0;i<data_newCARE.length;i++){
        conndash.pst.setString((i+1), data_newCARE[i]);   
    }
    conndash.pst.executeUpdate();
       
      String query="",id_;
//     pregnant
    String[] headers_Preg={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","level4","total","year",
    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
    
    id_ = mfl_code+"_"+yearmonth+"_1_35_1";
    
    String[] data_Preg =(id_+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","
    + ""+mfl_code+","+support_type+",90:90=ON ART,TX,TX NEW,Pregnant,"+Pregnant+","+year+","+
    semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
    
    query_params = "";
    for(int i=0;i<headers_Preg.length;i++){
    query_params+=headers_Preg[i]+"=?,";    
    }
    //remove last comma
    query_params = removeLastChars(query_params, 1);
    
   query = "REPLACE INTO table3 SET "+query_params;
   conndash.pst = conndash.conn.prepareStatement(query);
   
    for(int i=0;i<data_Preg.length;i++){
        conndash.pst.setString((i+1), data_Preg[i]);   
    }
    conndash.pst.executeUpdate();


//breastfeeding
    String[] headers_Breastf={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","level4","total","year",
    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
    
    id_ = mfl_code+"_"+yearmonth+"_1_35_2";
    
    String[] data_Breastf =(id_+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","
    + ""+mfl_code+","+support_type+",90:90=ON ART,TX,TX NEW,Breastfeeding,"+breastfeeding+","+year+","+
    semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
    
    query_params = "";
    for(int i=0;i<headers_Breastf.length;i++){
    query_params+=headers_Breastf[i]+"=?,";    
    }
    //remove last comma
    query_params = removeLastChars(query_params, 1);
    
   query = "REPLACE INTO table3 SET "+query_params;
   conndash.pst = conndash.conn.prepareStatement(query);
   
    for(int i=0;i<data_Breastf.length;i++){
        conndash.pst.setString((i+1), data_Breastf[i]);   
    }
    conndash.pst.executeUpdate();





//tb treatment
  String[] headers_onTB={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","level4","total","year",
    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
    
    id_ = mfl_code+"_"+yearmonth+"_1_35_3";
    
    String[] data_onTB =(id_+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","
    + ""+mfl_code+","+support_type+",90:90=ON ART,TX,TX NEW,Breastfeeding,"+breastfeeding+","+year+","+
    semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
    
    query_params = "";
    for(int i=0;i<headers_onTB.length;i++){
    query_params+=headers_onTB[i]+"=?,";    
    }
    //remove last comma
    query_params = removeLastChars(query_params, 1);
    
   query = "REPLACE INTO table3 SET "+query_params;
   conndash.pst = conndash.conn.prepareStatement(query);
   
    for(int i=0;i<data_onTB.length;i++){
        conndash.pst.setString((i+1), data_onTB[i]);   
    }
    conndash.pst.executeUpdate();


     
    }

      return num;
    } 
    
    public JSONObject getperiod(String yearmonth){
        JSONObject obj = new JSONObject();
        String[] arraydata = yearmonth.split("");
        String year = arraydata[0]+""+arraydata[1]+""+arraydata[2]+""+arraydata[3];
        String month = arraydata[4]+""+arraydata[5];
        String semi_annual = "",quarter="";
        if(Integer.parseInt(month)>=4 && Integer.parseInt(month)<=9){
          semi_annual = "2. Apr - Sep" ; 
          if(Integer.parseInt(month)>=4 && Integer.parseInt(month)<=6){
              quarter = "3. Apr - Jun";
          }
          else{
              quarter = "4. Jul - Sep";
          }
        }
        else{
          semi_annual = "1. Oct - Mar" ; 
          if(Integer.parseInt(month)>=10 && Integer.parseInt(month)<=12){
              quarter = "1. Oct - Dec";
          }
          else{
              quarter = "2. Jan - Mar";
          }
        }
        
        obj.put("year", year);
        obj.put("semi_annual", semi_annual);
        obj.put("quarter", quarter);
        obj.put("month", month);
        
        return obj;
    }
    
   private static String removeLastChars(String str, int num) {
    return str.substring(0, str.length() - num);
}
}
//    String[] data_currART ={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
//    "level1","level2","level3","level4","unknown_f","unknown_m","d60","mn_0_2","mn_2_12","mn_2_4y","f_1","m_1","f_4",
//    "m_4","f_5_9","m_5_9","f_1_9","m_1_9","t_1_9","f_14","m_14","f_19","m_19","f_24","m_24","f_29","m_29","f_34",
//    "m_34","f_39","m_39","f_49","m_49","f_25_49","m_25_49","f_50","m_50","total","total_f","total_m",
//    "paeds_f","paeds_m","paeds","adult_f","adult_m","adult","f_15_24","m_15_24","t_15_24","year",
//    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
//    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
    
   