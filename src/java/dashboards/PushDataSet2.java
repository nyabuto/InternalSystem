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
//PUSHES ART,CARE,PMTCT,VL Data for the year month period selected
public class PushDataSet2 {
    dbConn conn = new dbConn();
    dbConnDash conndash = new dbConnDash();
    
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
    "IFNULL(all_highvolume,0) AS  allhv,burden_cartegory AS burdencategory,IFNULL(constituency,'') AS constituency,IFNULL(ward,'') AS ward, IFNULL(Owner,'') AS Owner,IFNULL(Type,'') AS Type,latitude,longitude,IFNULL(Male_clinics,0) AS Male_clinics,IFNULL(Adolescent_clinics,0) AS Adolescent_clinics,"+
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
    "IFNULL(all_highvolume,0) AS  allhv,burden_cartegory AS burdencategory,IFNULL(constituency,'') AS constituency,IFNULL(ward,'') AS ward, IFNULL(Owner,'') AS Owner,IFNULL(Type,'') AS Type,latitude,longitude,IFNULL(Male_clinics,0) AS Male_clinics,IFNULL(Adolescent_clinics,0) AS Adolescent_clinics,"+
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
    public int viral_load(String startdate,String enddate) throws SQLException{
     int num=0;
     String facilitiestable="subpartnera";
               String sets[] = {"n","d"};
               String fem_special[] = {"Preg_r","Breastfeeding_r","Preg_t","Breastfeeding_t","Preg_nd","Breastfeeding_nd"};
               String sections[] = {"r","t","nd"};
               
               String sets_ids[] = {"46:TX_PVLS Num","47:TX_PVLS Den"};
               String sections_ids[] = {"21:Routine","22:Targeted","23:Not Documented"};
               String fem_special_ids[] = {"21_1: Routine Pregnant","21_2: Routine Breastfeeding","22_1:Targeted Pregnant","22_2:Targeted Breastfeeding","23_1:Not Documented Pregnant","23_2:Not Documented Breastfeeding"};
               
     String county,sub_county,facilityName,support_type,mfl_code,arthv,pmtcthv,htchv,allhv,burdencategory,constituency;
     String ward,Owner,Type,latitude,longitude,Male_clinics,Adolescent_clinics,Viremia_clinics,EMR_Sites,Link_desks,yearmonth;
     int f_1,f_9,m_1,m_9,f_14,f_19,f_24,f_29,f_34,f_39,f_49,f_50,m_14,m_19,m_24,m_29,m_34,m_39,m_49,m_50;
     int total,total_f,total_m;
     String year,semi_annual,quarter,month;
     String id_="";
     
     
     
     String joinedwhere = " where 1=1 &&  Date_Tested BETWEEN '"+startdate+"' AND '"+enddate+"'  and ("+facilitiestable+".ART=1 OR "+facilitiestable+".PMTCT=1) "
             + "and "+facilitiestable+".active=1  and Sex !=''  AND "+facilitiestable+".active=1   and (AgeYrs!='' and AgeYrs>=0) AND Valid_Result='Y' ";
     
     String getVLData = "/*DSD TX_PVLS (Numerator Denominator) */ " +
        "SELECT "+facilitiestable+".SubPartnerNom AS facility,district.DistrictNom AS sub_county,county.County AS county,"+
        ""+facilitiestable+".CentreSanteId AS mfl_code,IFNULL(ART_Support,0) AS ART_Support,IFNULL(ART_highvolume,0) AS arthv, IFNULL(HTC_highvolume,0) AS htchv,IFNULL(PMTCT_highvolume,0) AS pmtcthv,"+
        "IFNULL(all_highvolume,0) AS  allhv,burden_cartegory AS burdencategory,IFNULL(constituency,'') AS constituency,IFNULL(ward,'') AS ward, IFNULL(Owner,'') AS Owner,IFNULL(Type,'') AS Type,latitude,longitude,IFNULL(Male_clinics,0) AS Male_clinics,IFNULL(Adolescent_clinics,0) AS Adolescent_clinics,"+
        "IFNULL(Viremia_clinics,0) AS Viremia_clinics,IFNULL(EMR_Sites,0) AS EMR_Sites,IFNULL(Link_desks,0) AS Link_desks," +
             
        "COUNT( CASE WHEN Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'Numerator' END) AS n, " +
        "COUNT( CASE WHEN Suppressed='Y' AND Justification='Routine VL' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'Routine' END) AS n_r, " +
        "COUNT( CASE WHEN Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND  (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') THEN  'Targeted' END) AS n_t, " +
        "COUNT( CASE WHEN Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other') THEN  'Not_Documented' END) AS n_nd, " +
        " " +
        "COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND Justification='Routine VL') THEN  'n_Preg_Routine' END) AS n_Preg_r, " +
        "COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'n_Preg_Targeted' END) AS n_Preg_t, " +
        "COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other')) THEN  'n_Preg_Not_Documented' END) AS n_Preg_nd, " +
        " " +
        "COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND Justification='Routine VL') THEN  'n_Breastfeeding_Routine' END) AS n_Breastfeeding_r, " +
        "COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'n_Breastfeeding_Targeted' END) AS n_Breastfeeding_t, " +
        "COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other')) THEN  'n_Breastfeeding_Not_Documented' END) AS n_Breastfeeding_nd, " +
        " " +
        "/*ROUTINE NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 0 AND 0.99999))) THEN  'n_r_f_1' END) AS n_r_f_1, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 1 AND 9.99999))) THEN  'n_r_f_9' END) AS n_r_f_9, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 0 AND 0.99999))) THEN  'n_r_m_1' END) AS n_r_m_1, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 1 AND 9.99999))) THEN  'n_r_m_9' END) AS n_r_m_9, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.99999))) THEN  'n_r_1' END) AS n_r_1, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.99999))) THEN  'n_r_9' END) AS n_r_9, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND  9.99999))) THEN  'n_r_0_9' END) AS n_r_0_9, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 10 AND 14.99999))) THEN  'n_r_f_14' END) AS n_r_f_14, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 15 AND 19.99999))) THEN  'n_r_f_19' END) AS n_r_f_19, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 20 AND 24.99999))) THEN  'n_r_f_24' END) AS n_r_f_24, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 25 AND 29.99999))) THEN  'n_r_f_29' END) AS n_r_f_29, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 30 AND 34.99999))) THEN  'n_r_f_34' END) AS n_r_f_34, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 35 AND 39.99999))) THEN  'n_r_f_39' END) AS n_r_f_39, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 40 AND 49.99999))) THEN  'n_r_f_49' END) AS n_r_f_49, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 50 AND 100))) THEN  'n_r_f_50' END) AS n_r_f_50, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 10 AND 100))) THEN  'n_r_10_50_f' END) AS n_r_10_50_f, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 10 AND 14.99999))) THEN  'n_r_m_14' END) AS n_r_m_14, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 15 AND 19.99999))) THEN  'n_r_m_19' END) AS n_r_m_19, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 20 AND 24.99999))) THEN  'n_r_m_24' END) AS n_r_m_24, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 25 AND 29.99999))) THEN  'n_r_m_29' END) AS n_r_m_29, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 30 AND 34.99999))) THEN  'n_r_m_34' END) AS n_r_m_34, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 35 AND 39.99999))) THEN  'n_r_m_39' END) AS n_r_m_39, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 40 AND 49.99999))) THEN  'n_r_m_49' END) AS n_r_m_49, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 50 AND 100))) THEN  'n_r_m_50' END) AS n_r_m_50, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 10 AND 100))) THEN  'n_r_10_50_m' END) AS n_r_10_50_m, " +
        "COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100))) THEN  'n_r_0_50' END) AS n_r_0_50, " +
        " " +
        "/*TARGETED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'n_t_f_1' END) AS n_t_f_1, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'n_r_t_9' END) AS n_t_f_9, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'n_t_m_1' END) AS n_t_m_1, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'n_t_m_9' END) AS n_t_m_9, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999)) THEN  'n_t_1' END) AS n_t_1, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND ( (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.9999)) THEN  'n_t_9' END) AS n_t_9, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999)) THEN  'n_t_0_9' END) AS n_t_0_9, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'n_t_f_14' END) AS n_t_f_14, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'n_t_f_19' END) AS n_t_f_19, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'n_t_f_24' END) AS n_t_f_24, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'n_t_f_29' END) AS n_t_f_29, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'n_t_f_34' END) AS n_t_f_34, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'n_t_f_39' END) AS n_t_f_39, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'n_t_f_49' END) AS n_t_f_49, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'n_t_f_50' END) AS n_t_f_50, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'n_t_10_50_f' END) AS n_t_10_50_f, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'n_t_m_14' END) AS n_t_m_14, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'n_t_m_19' END) AS n_t_m_19, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'n_t_m_24' END) AS n_t_m_24, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'n_t_m_29' END) AS n_t_m_29, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'n_t_m_34' END) AS n_t_m_34, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'n_t_m_39' END) AS n_t_m_39, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'n_t_m_49' END) AS n_t_m_49, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'n_t_m_50' END) AS n_t_m_50, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'n_t_10_50_m' END) AS n_t_10_50_m, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'n_t_0_50' END) AS n_t_0_50, " +
        " " +
        " " +
        "/*NOT DOCUMENTED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'n_nd_f_1' END) AS n_nd_f_1, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'n_nd_t_9' END) AS n_nd_f_9, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'n_nd_m_1' END) AS n_nd_m_1, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'n_nd_m_9' END) AS n_nd_m_9, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999)) THEN  'n_nd_1' END) AS n_nd_1, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs  BETWEEN 1 AND 9.9999)) THEN  'n_nd_9' END) AS n_nd_9, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999)) THEN  'n_nd_0_9' END) AS n_nd_0_9, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'n_nd_f_14' END) AS n_nd_f_14, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'n_nd_f_19' END) AS n_nd_f_19, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'n_nd_f_24' END) AS n_nd_f_24, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'n_nd_f_29' END) AS n_nd_f_29, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'n_nd_f_34' END) AS n_nd_f_34, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'n_nd_f_39' END) AS n_nd_f_39, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'n_nd_f_49' END) AS n_nd_f_49, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'n_nd_f_50' END) AS n_nd_f_50, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'n_nd_10_50_f' END) AS n_nd_10_50_f, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'n_nd_m_14' END) AS n_nd_m_14, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'n_nd_m_19' END) AS n_nd_m_19, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'n_nd_m_24' END) AS n_nd_m_24, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'n_nd_m_29' END) AS n_nd_m_29, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'n_nd_m_34' END) AS n_nd_m_34, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'n_nd_m_39' END) AS n_nd_m_39, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'n_nd_m_49' END) AS n_nd_m_49, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'n_nd_m_50' END) AS n_nd_m_50, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'n_nd_10_50_m' END) AS n_nd_10_50_m, " +
        "COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'n_nd_0_50' END) AS n_nd_0_50, " +
        " " +
        " " +
        "COUNT( CASE WHEN ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'Denominator' END) AS d, " +
        "COUNT( CASE WHEN Justification='Routine VL' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'Routine' END) AS d_r, " +
        "COUNT( CASE WHEN (((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'd_Targeted' END) AS d_t, " +
        "COUNT( CASE WHEN ((Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) ) THEN  'd_Not_Documented' END) AS d_nd, " +
        " " +
        "COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND Justification='Routine VL' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) ) THEN  'd_Preg_Routine' END) AS d_Preg_r, " +
        "COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'd_Preg_Targeted' END) AS d_Preg_t, " +
        "COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other')) THEN  'd_Preg_Not_Documented' END) AS d_Preg_nd, " +
        " " +
        "COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND Justification='Routine VL' )THEN  'd_Breastfeeding_Routine' END) AS d_Breastfeeding_r, " +
        "COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'd_Breastfeeding_Targeted' END) AS d_Breastfeeding_t, " +
        "COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other')) THEN  'd_Breastfeeding_Not_Documented' END) AS d_Breastfeeding_nd, " +
        " " +
        "/*ROUTINE NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_r_f_1' END) AS d_r_f_1, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_r_f_9' END) AS d_r_f_9, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_r_m_1' END) AS d_r_m_1, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_r_m_9' END) AS d_r_m_9, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_r_1' END) AS d_r_1, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_r_9' END) AS d_r_9, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999) THEN  'd_r_0_9' END) AS d_r_0_9, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_r_f_14' END) AS d_r_f_14, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_r_f_19' END) AS d_r_f_19, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_r_f_24' END) AS d_r_f_24, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_r_f_29' END) AS d_r_f_29, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_r_f_34' END) AS d_r_f_34, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_r_f_39' END) AS d_r_f_39, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_r_f_49' END) AS d_r_f_49, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_r_f_50' END) AS d_r_f_50, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_r_10_50_f' END) AS d_r_10_50_f, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_r_m_14' END) AS d_r_m_14, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_r_m_19' END) AS d_r_m_19, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_r_m_24' END) AS d_r_m_24, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_r_m_29' END) AS d_r_m_29, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_r_m_34' END) AS d_r_m_34, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_r_m_39' END) AS d_r_m_39, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_r_m_49' END) AS d_r_m_49, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_r_m_50' END) AS d_r_m_50, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_r_10_50_m' END) AS d_r_10_50_m, " +
        "COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100) THEN  'd_r_0_50' END) AS d_r_0_50, " +
        " " +
        "/*TARGETED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_t_f_1' END) AS d_t_f_1, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_r_t_9' END) AS d_t_f_9, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_t_m_1' END) AS d_t_m_1, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_t_m_9' END) AS d_t_m_9, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_t_1' END) AS d_t_1, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_t_9' END) AS d_t_9, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999) THEN  'd_t_0_9' END) AS d_t_0_9, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_t_f_14' END) AS d_t_f_14, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_t_f_19' END) AS d_t_f_19, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_t_f_24' END) AS d_t_f_24, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_t_f_29' END) AS d_t_f_29, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_t_f_34' END) AS d_t_f_34, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_t_f_39' END) AS d_t_f_39, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_t_f_49' END) AS d_t_f_49, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_t_f_50' END) AS d_t_f_50, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_t_10_50_f' END) AS d_t_10_50_f, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_t_m_14' END) AS d_t_m_14, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_t_m_19' END) AS d_t_m_19, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_t_m_24' END) AS d_t_m_24, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_t_m_29' END) AS d_t_m_29, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_t_m_34' END) AS d_t_m_34, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_t_m_39' END) AS d_t_m_39, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_t_m_49' END) AS d_t_m_49, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_t_m_50' END) AS d_t_m_50, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_t_10_50_m' END) AS d_t_10_50_m, " +
        "COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100) THEN  'd_t_0_50' END) AS d_t_0_50, " +
        " " +
        " " +
        "/*NOT DOCUMENTED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_nd_f_1' END) AS d_nd_f_1, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_nd_t_9' END) AS d_nd_f_9, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_nd_m_1' END) AS d_nd_m_1, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_nd_m_9' END) AS d_nd_m_9, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_nd_1' END) AS d_nd_1, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_nd_9' END) AS d_nd_9, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999) THEN  'd_nd_0_9' END) AS d_nd_0_9, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_nd_f_14' END) AS d_nd_f_14, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_nd_f_19' END) AS d_nd_f_19, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_nd_f_24' END) AS d_nd_f_24, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_nd_f_29' END) AS d_nd_f_29, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_nd_f_34' END) AS d_nd_f_34, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_nd_f_39' END) AS d_nd_f_39, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_nd_f_49' END) AS d_nd_f_49, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_nd_f_50' END) AS d_nd_f_50, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_nd_10_50_f' END) AS d_nd_10_50_f, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_nd_m_14' END) AS d_nd_m_14, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_nd_m_19' END) AS d_nd_m_19, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_nd_m_24' END) AS d_nd_m_24, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_nd_m_29' END) AS d_nd_m_29, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_nd_m_34' END) AS d_nd_m_34, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_nd_m_39' END) AS d_nd_m_39, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_nd_m_49' END) AS d_nd_m_49, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_nd_m_50' END) AS d_nd_m_50, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_nd_10_50_m' END) AS d_nd_10_50_m, " +
        "COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100) THEN  'd_nd_0_50' END) AS d_nd_0_50,"
        + " EXTRACT(YEAR_MONTH FROM Date_Tested) AS yearmonth " +
        "FROM vl_validation join "+facilitiestable+" ON vl_validation.MFL_Code="+facilitiestable+".CentreSanteId "
        +"join district on "+facilitiestable+".DistrictID=district.DistrictID join county on county.CountyID=district.CountyID " +
        ""+joinedwhere+" GROUP BY mfl_code,yearmonth ";
     
        System.out.println("query : "+getVLData);  
     
        conn.rs = conn.st.executeQuery(getVLData);
        while(conn.rs.next()){
            
                //basic information
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

                JSONObject period_data = getperiod(yearmonth);

                year = period_data.get("year").toString();
                semi_annual = period_data.get("semi_annual").toString();
                quarter = period_data.get("quarter").toString();
                month = period_data.get("month").toString();
       
               //add this information to the database for dashboarding
               
               int sets_c=0;
               
               for(String set:sets){
                  int sections_c=0;
                  
               for(String section:sections){
                total = conn.rs.getInt(set+"_"+section);
                f_1=conn.rs.getInt(set+"_"+section+"_f_1");
                f_9=conn.rs.getInt(set+"_"+section+"_f_9");
                m_1=conn.rs.getInt(set+"_"+section+"_m_1");
                m_9=conn.rs.getInt(set+"_"+section+"_m_9");
                f_14=conn.rs.getInt(set+"_"+section+"_f_14");
                f_19=conn.rs.getInt(set+"_"+section+"_f_19");
                f_24=conn.rs.getInt(set+"_"+section+"_f_24");
                f_29=conn.rs.getInt(set+"_"+section+"_f_29");
                f_34=conn.rs.getInt(set+"_"+section+"_f_34");
                f_39=conn.rs.getInt(set+"_"+section+"_f_39");
                f_49=conn.rs.getInt(set+"_"+section+"_f_49");
                f_50=conn.rs.getInt(set+"_"+section+"_f_50");
                m_14=conn.rs.getInt(set+"_"+section+"_m_14");
                m_19=conn.rs.getInt(set+"_"+section+"_m_19");
                m_24=conn.rs.getInt(set+"_"+section+"_m_24");
                m_29=conn.rs.getInt(set+"_"+section+"_m_29");
                m_34=conn.rs.getInt(set+"_"+section+"_m_34");
                m_39=conn.rs.getInt(set+"_"+section+"_m_39");
                m_49=conn.rs.getInt(set+"_"+section+"_m_49");
                m_50=conn.rs.getInt(set+"_"+section+"_m_50");
                
     total_f = f_1+f_9+f_14+f_19+f_24+f_29+f_34+f_39+f_49+f_50;
     total_m = m_1+m_9+m_14+m_19+m_24+m_29+m_34+m_39+m_49+m_50;
     
     String id_lv3,id_lv4,lb_lv3,lb_lv4;
            id_lv3 = sets_ids[sets_c].split(":")[0];
            lb_lv3 = sets_ids[sets_c].split(":")[1];
            id_lv4 = sections_ids[sections_c].split(":")[0];
            lb_lv4 = sections_ids[sections_c].split(":")[1];
            
          id_=mfl_code+"_"+yearmonth+"_"+id_lv3+"_"+id_lv4; //numerator 

   String[] hearder ={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","level4","f_1","m_1","f_1_9","m_1_9","f_14","m_14","f_19","m_19","f_24","m_24","f_29","m_29","f_34",
    "m_34","f_39","m_39","f_49","m_49","f_50","m_50","total","total_f","total_m","year",
    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
   
   String[] data =(""+id_+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","+mfl_code+","+support_type+","+
    "90:90:90= Viral Suppression,VL,"+lb_lv3+","+lb_lv4+","+f_1+","+m_1+","+f_9+","+m_9+","+f_14+","+m_14+","+f_19+","+m_19+","+
    ""+f_24+","+m_24+","+f_29+","+m_29+","+f_34+","+
    ""+m_34+","+f_39+","+m_39+","+f_49+","+m_49+","+f_50+","+m_50+","+total+","+total_f+","+total_m+","+year+","+
    ""+semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    ""+allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
    
   
   String query_params = "";
    for(int i=0;i<hearder.length;i++){
    query_params+=hearder[i]+"=?,";    
    }
    //remove last comma
    query_params = removeLastChars(query_params, 1);
    
   String query = "REPLACE INTO table3 SET "+query_params;
   conndash.pst = conndash.conn.prepareStatement(query);
   
    for(int i=0;i<data.length;i++){
        conndash.pst.setString((i+1), data[i]);   
    }
    conndash.pst.executeUpdate();
    
    
     sections_c++;
        }
     sets_c++;   
      }
//      end         
               
     //breastfeeding, preg
     
                sets_c=0;
               
               for(String set:sets){
                  int fem_c=0;
                  
               for(String fem:fem_special){
                total = conn.rs.getInt(set+"_"+fem);
                
     
            String id_lv3,id_lv4,lb_lv3,lb_lv4;
            id_lv3 = sets_ids[sets_c].split(":")[0];
            lb_lv3 = sets_ids[sets_c].split(":")[1];
            id_lv4 = fem_special_ids[fem_c].split(":")[0];
            lb_lv4 = fem_special_ids[fem_c].split(":")[1];
            
          id_=mfl_code+"_"+yearmonth+"_"+id_lv3+"_"+id_lv4; //numerator 

   String[] hearder ={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","level4","total","year",
    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
   
   String[] data =(""+id_+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","+mfl_code+","+support_type+","+
    "90:90:90= Viral Suppression,VL,"+lb_lv3+","+lb_lv4+","+total+","+year+","+semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","
    + ""+Type+","+arthv+","+htchv+","+pmtcthv+","+allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
    
   
   String query_params = "";
    for(int i=0;i<hearder.length;i++){
    query_params+=hearder[i]+"=?,";    
    }
    //remove last comma
    query_params = removeLastChars(query_params, 1);
    
   String query = "REPLACE INTO table3 SET "+query_params;
   conndash.pst = conndash.conn.prepareStatement(query);
   
    for(int i=0;i<data.length;i++){
        conndash.pst.setString((i+1), data[i]);   
    }
    conndash.pst.executeUpdate();
    
    
    fem_c++;
        }
     sets_c++;   
      }
               
   
               
               
               
               
    }
     
     return num;
    }
    public int pmtct_eid(String startdate,String enddate) throws SQLException{
        int num=0;
        String facilitiestable="subpartnera";
        String county,sub_county,facilityName,support_type,mfl_code,arthv,pmtcthv,htchv,allhv,burdencategory,constituency;
        String ward,Owner,Type,latitude,longitude,Male_clinics,Adolescent_clinics,Viremia_clinics,EMR_Sites,Link_desks,yearmonth;
        int m_0_2,m_2_12;
        String year,semi_annual,quarter,month;
        String id_="";
        
        String elems[] = {"eid_tested","eid_pos","eid_pos_ART"};
        String elem_ids[]={"28:PMTCT EID","29:PMTCT_HEI_POS","30:PMTCT_HEI_POS ART"};
     
     
        String eid_query=""+
                "SELECT facility,sub_county,county,mfl_code,ART_Support,arthv,htchv,pmtcthv,allhv,burdencategory,constituency,ward,"+
                "Owner,Type,latitude,longitude,Male_clinics,Adolescent_clinics,Viremia_clinics,EMR_Sites,Link_desks," +
                        
                "SUM(eid_tested_0_2months) AS eid_tested_0_2months,SUM(eid_tested_2_12months) AS eid_tested_2_12months," +
                "SUM(eid_pos_0_2months) AS eid_pos_0_2months,SUM(eid_pos_2_12months) AS eid_pos_2_12months,SUM(eid_pos_0_2months_ART) AS eid_pos_ART_0_2months,SUM(eid_pos_2_12months_ART) AS eid_pos_ART_2_12months,"+
                "yearmonth " +
                "FROM(" +
                "SELECT "+facilitiestable+".SubPartnerNom AS facility,district.DistrictNom AS sub_county,county.County AS county,"+
                ""+facilitiestable+".CentreSanteId AS mfl_code,IFNULL(ART_Support,0) AS ART_Support,IFNULL(ART_highvolume,0) AS arthv, IFNULL(HTC_highvolume,0) AS htchv,IFNULL(PMTCT_highvolume,0) AS pmtcthv,"+
                "IFNULL(all_highvolume,0) AS  allhv,burden_cartegory AS burdencategory,IFNULL(constituency,'') AS constituency,IFNULL(ward,'') AS ward, IFNULL(Owner,'') AS Owner,IFNULL(Type,'') AS Type,latitude,longitude,IFNULL(Male_clinics,0) AS Male_clinics,IFNULL(Adolescent_clinics,0) AS Adolescent_clinics,"+
                "IFNULL(Viremia_clinics,0) AS Viremia_clinics,IFNULL(EMR_Sites,0) AS EMR_Sites,IFNULL(Link_desks,0) AS Link_desks," +
                "0  AS eid_tested_0_2months," +
                "0  AS eid_tested_2_12months, " +
                "COUNT( CASE WHEN eid_raw_pos.Age<=2 THEN '<2 POS' END)  AS eid_pos_0_2months," +
                "COUNT( CASE WHEN eid_raw_pos.Age>2 AND eid_raw_pos.Age<=12 THEN '>2 <=12 POS' END)  AS eid_pos_2_12months," +
                "COUNT( CASE WHEN eid_raw_pos.Age<=2 AND treatment_init_date!=\"\" THEN '<2 POS ART' END)  AS eid_pos_0_2months_ART," +
                "COUNT( CASE WHEN eid_raw_pos.Age>2  AND treatment_init_date!=\"\" AND eid_raw_pos.Age<=12 THEN '>2  <=12 POST ART' END)  AS eid_pos_2_12months_ART,"+
                "EXTRACT(YEAR_MONTH FROM testingdate) AS yearmonth "+
                "FROM eid_raw_pos " +
                "LEFT JOIN "+facilitiestable+" ON eid_raw_pos.SubPartnerID="+facilitiestable+".SubPartnerID " +
                "LEFT JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID " +
                "LEFT JOIN county ON district.CountyID=county.CountyID " +
                " WHERE  testingdate BETWEEN '"+startdate+"' AND '"+enddate+"' and "+facilitiestable+".PMTCT=1 && PCR_Type like '%initial PCR%' && (`validation` !='A') && "+facilitiestable+".active=1 GROUP BY eid_raw_pos.SubPartnerID " +
                "   UNION ALL           " +
                "SELECT "+facilitiestable+".SubPartnerNom AS facility,district.DistrictNom AS sub_county,county.County AS county,"+
                ""+facilitiestable+".CentreSanteId AS mfl_code,IFNULL(ART_Support,0) AS ART_Support,IFNULL(ART_highvolume,0) AS arthv, IFNULL(HTC_highvolume,0) AS htchv,IFNULL(PMTCT_highvolume,0) AS pmtcthv,"+
                "IFNULL(all_highvolume,0) AS  allhv,burden_cartegory AS burdencategory,IFNULL(constituency,'') AS constituency,IFNULL(ward,'') AS ward, IFNULL(Owner,'') AS Owner,IFNULL(Type,'') AS Type,latitude,longitude,IFNULL(Male_clinics,0) AS Male_clinics,IFNULL(Adolescent_clinics,0) AS Adolescent_clinics,"+
                "IFNULL(Viremia_clinics,0) AS Viremia_clinics,IFNULL(EMR_Sites,0) AS EMR_Sites,IFNULL(Link_desks,0) AS Link_desks," +
                "COUNT( CASE WHEN eid_raw_tested.age_months<=2 THEN '<2 TST'  END)  AS eid_tested_0_2months," +
                "COUNT( CASE WHEN eid_raw_tested.age_months>2 AND eid_raw_tested.age_months<=12 THEN '>2 <=12 TST' END)  AS eid_tested_2_12months," +
                "0  AS eid_pos_0_2months," +
                "0  AS eid_pos_2_12months," +
                "0  AS eid_pos_0_2months_ART," +
                "0  AS eid_pos_2_12months_ART,"+
                "EXTRACT(YEAR_MONTH FROM datetested) AS yearmonth "+
                "FROM eid_raw_tested " +
                "LEFT JOIN "+facilitiestable+" ON eid_raw_tested.SubPartnerID="+facilitiestable+".SubPartnerID " +
                "LEFT JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID " +
                "LEFT JOIN county ON district.CountyID=county.CountyID " +
                " WHERE  datetested BETWEEN '"+startdate+"' AND '"+enddate+"' AND  "+facilitiestable+".PMTCT=1 && PCR_Type like '%initial PCR%' && "+facilitiestable+".active=1 GROUP BY eid_raw_tested.SubPartnerID " +
                ") AS eid_data group by mfl_code,yearmonth"; 
                    System.out.println("eid query : "+eid_query);

                    conn.rs = conn.st.executeQuery(eid_query);
                    while(conn.rs.next()){
                       //basic information
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

                JSONObject period_data = getperiod(yearmonth);

                year = period_data.get("year").toString();
                semi_annual = period_data.get("semi_annual").toString();
                quarter = period_data.get("quarter").toString();
                month = period_data.get("month").toString();
       
               //add this information to the database for dashboarding
               
                  int sections_c=0;
                  
               for(String section:elems){
                m_0_2=conn.rs.getInt(section+"_0_2months");
                m_2_12=conn.rs.getInt(section+"_2_12months");
                
     
     String id_lv3,lb_lv3;
            id_lv3 = elem_ids[sections_c].split(":")[0];
            lb_lv3 = elem_ids[sections_c].split(":")[1];
            
          id_=mfl_code+"_"+yearmonth+"_"+id_lv3; //numerator 

   String[] hearder ={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","mn_0_2","mn_2_12","year","semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
   
   String[] data =(""+id_+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","+mfl_code+","+support_type+","+
    "90=Knowing HIV Status,PMTCT,"+lb_lv3+","+m_0_2+","+m_2_12+","+year+","+
    ""+semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    ""+allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
    
   
   String query_params = "";
    for(int i=0;i<hearder.length;i++){
    query_params+=hearder[i]+"=?,";    
    }
    //remove last comma
    query_params = removeLastChars(query_params, 1);
    
   String query = "REPLACE INTO table3 SET "+query_params;
   conndash.pst = conndash.conn.prepareStatement(query);
   
    for(int i=0;i<data.length;i++){
        conndash.pst.setString((i+1), data[i]);   
    }
    conndash.pst.executeUpdate();
    
    sections_c++;
        }
     
//      end    
                        
    }
        return num;
    }
    public int pmtct_fo(String startyearmonth,String endyearmonth) throws SQLException{
         int num=0;
        String facilitiestable="subpartnera";
        String county,sub_county,facilityName,support_type,mfl_code,arthv,pmtcthv,htchv,allhv,burdencategory,constituency;
        String ward,Owner,Type,latitude,longitude,Male_clinics,Adolescent_clinics,Viremia_clinics,EMR_Sites,Link_desks,yearmonth;
        int total;
        String year,semi_annual,quarter,month;
        String id_="";
        
        String elems[] = {"Numerator","Denominator","HIV_infected","HIV_uninfected","HIV_final_status_unknown","Died_without_status_known"};
        String elem_ids[]={"33:PMTCT FO Num: : ","34:PMTCT FO Den: : ","33:PMTCT FO Num:4:HIV-infected EID","33:PMTCT FO Num:5:HIV-uninfected","33:PMTCT FO Num:6:HIV-final status unknown","33:PMTCT FO Num:7:Died without status known"};
     
     
      String pmtct_fo=""+
             "SELECT "+facilitiestable+".SubPartnerNom AS facility,district.DistrictNom AS sub_county,county.County AS county,"+
                ""+facilitiestable+".CentreSanteId AS mfl_code,IFNULL(ART_Support,0) AS ART_Support,IFNULL(ART_highvolume,0) AS arthv, IFNULL(HTC_highvolume,0) AS htchv,IFNULL(PMTCT_highvolume,0) AS pmtcthv,"+
                "IFNULL(all_highvolume,0) AS  allhv,burden_cartegory AS burdencategory,IFNULL(constituency,'') AS constituency,IFNULL(ward,'') AS ward, IFNULL(Owner,'') AS Owner,IFNULL(Type,'') AS Type,latitude,longitude,IFNULL(Male_clinics,0) AS Male_clinics,IFNULL(Adolescent_clinics,0) AS Adolescent_clinics,"+
                "IFNULL(Viremia_clinics,0) AS Viremia_clinics,IFNULL(EMR_Sites,0) AS EMR_Sites,IFNULL(Link_desks,0) AS Link_desks,hei.results.reportingyearmonth AS yearmonth" +
                " ,sum( case when indicator_id=23 then denominator end) as Denominator " +
                " ,sum( case when (indicator_id=21 or indicator_id=22 or indicator_id=23 or  indicator_id=24 or  indicator_id=25  or indicator_id=26 )  then numerator end) as Numerator " +
                " ,sum( case when (indicator_id=23)  then numerator end) as HIV_infected " +
                " ,sum( case when (indicator_id=21)  then numerator end) as HIV_uninfected " +
                " ,sum( case when (indicator_id=22 or  indicator_id=24 or  indicator_id=25 )  then numerator end) as HIV_final_status_unknown " +
                " ,sum( case when indicator_id=26   then numerator end) as Died_without_status_known " +
                " FROM hei.results join "+facilitiestable+" on hei.results.facility_id="+facilitiestable+".SubPartnerID join (internal_system.district "
                + "join internal_system.county on internal_system.county.CountyID=internal_system.district.CountyID ) on internal_system.district.DistrictID="+facilitiestable+".DistrictID " +
                " WHERE hei.results.reportingyearmonth BETWEEN "+startyearmonth+" AND "+endyearmonth+"   and ( "+facilitiestable+".PMTCT=1 )  AND "+facilitiestable+".active=1  " +
                " group by "+facilitiestable+".SubPartnerID,yearmonth ";
               System.out.println("PMTCT FO Query: "+pmtct_fo);
              conn.rs=conn.st.executeQuery(pmtct_fo);
             while(conn.rs.next()){

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

        JSONObject period_data = getperiod(yearmonth);

        year = period_data.get("year").toString();
        semi_annual = period_data.get("semi_annual").toString();
        quarter = period_data.get("quarter").toString();
        month = period_data.get("month").toString();

       //add this information to the database for dashboarding

          int sections_c=0;
        System.out.println("entered here");
       for(String section:elems){
        total=conn.rs.getInt(section);
                
     
     String id_lv3,lb_lv3,id_lv4,lb_lv4;
            id_lv3 = elem_ids[sections_c].split(":")[0];
            lb_lv3 = elem_ids[sections_c].split(":")[1];
            id_lv4 = elem_ids[sections_c].split(":")[2];
            lb_lv4 = elem_ids[sections_c].split(":")[3];
            
//            if(lb_lv4.equals(" ")){lb_lv4=null;}
          id_=mfl_code+"_"+yearmonth+"_"+id_lv3+"_"+id_lv4; //numerator 

   String[] hearder ={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","level4","total","year","semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
   
   String[] data =(""+id_+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","+mfl_code+","+support_type+","+
    "90=Knowing HIV Status,PMTCT FO,"+lb_lv3+","+lb_lv4+","+total+","+year+","+
    ""+semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    ""+allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
    
   
   String query_params = "";
    for(int i=0;i<hearder.length;i++){
    query_params+=hearder[i]+"=?,";    
    }
    //remove last comma
    query_params = removeLastChars(query_params, 1);
    
   String query = "REPLACE INTO table3 SET "+query_params;
   conndash.pst = conndash.conn.prepareStatement(query);
           System.out.println("query : "+query);
    for(int i=0;i<data.length;i++){
        conndash.pst.setString((i+1), data[i]);   
    }
    conndash.pst.executeUpdate();
    
    sections_c++;
        }
     
//      end    
        }
    
    return num;
    }
    public int pmtct(String startyearmonth, String endyearmonth) throws SQLException{
        int num=0;
        String facilitiestable="subpartnera";
        String county,sub_county,facilityName,support_type,mfl_code,arthv,pmtcthv,htchv,allhv,burdencategory,constituency;
        String ward,Owner,Type,latitude,longitude,Male_clinics,Adolescent_clinics,Viremia_clinics,EMR_Sites,Link_desks,yearmonth;
        int total;
        String year,semi_annual,quarter,month;
        String id_="";
        
             
        String get731data=""+
            "SELECT "+facilitiestable+".SubPartnerNom AS facility,district.DistrictNom AS sub_county,county.County AS county,"+
            ""+facilitiestable+".CentreSanteId AS mfl_code,IFNULL(ART_Support,0) AS ART_Support,IFNULL(ART_highvolume,0) AS arthv, IFNULL(HTC_highvolume,0) AS htchv,IFNULL(PMTCT_highvolume,0) AS pmtcthv,"+
            "IFNULL(all_highvolume,0) AS  allhv,burden_cartegory AS burdencategory,IFNULL(constituency,'') AS constituency,IFNULL(ward,'') AS ward, IFNULL(Owner,'') AS Owner,IFNULL(Type,'') AS Type,latitude,longitude,IFNULL(Male_clinics,0) AS Male_clinics,IFNULL(Adolescent_clinics,0) AS Adolescent_clinics,"+
            "IFNULL(Viremia_clinics,0) AS Viremia_clinics,IFNULL(EMR_Sites,0) AS EMR_Sites,IFNULL(Link_desks,0) AS Link_desks,moh731.yearmonth AS yearmonth, " +
            ""+
            //--------pmtct anc tested -------------------
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN HV0201 end)) as pmtct_anc_tes," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*0) end)) as pmtct_anc_tes_unknown," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_1) end)) as pmtct_anc_tes_1," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*(f_4+f_9)) end)) as pmtct_anc_tes_1_9," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_14) end)) as pmtct_anc_tes_10_14," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_19) end)) as pmtct_anc_tes_15_19," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_24) end)) as pmtct_anc_tes_20_24," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_29) end)) as pmtct_anc_tes_25_29," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_34) end)) as pmtct_anc_tes_30_34," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_39) end)) as pmtct_anc_tes_35_39," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_49) end)) as pmtct_anc_tes_40_49," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_50) end)) as pmtct_anc_tes_50, "+
            
            //-------pmtct anc new_positive------------------
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN HV0206 end)) as pmtct_newpositive ," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*0) end)) as pmtct_pos_unknown," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_1) end)) as pmtct_pos_1," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*(f_4+f_9)) end)) as pmtct_pos_1_9," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_14) end)) as pmtct_pos_10_14," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_19) end)) as pmtct_pos_15_19," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_24) end)) as pmtct_pos_20_24," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_29) end)) as pmtct_pos_25_29," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_34) end)) as pmtct_pos_30_34," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_39) end)) as pmtct_pos_35_39," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_49) end)) as pmtct_pos_40_49," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_50) end)) as pmtct_pos_50,"+
             
            
             //-------pmtct anc negative------------------\
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206) end)))  AS pmtct_new_negatives, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*0) end)))   - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*0) end))) AS pmtct_neg_unknown, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_1) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_1) end))) AS pmtct_neg_1, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*(f_4+f_9)) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*(f_4+f_9)) end)))   AS pmtct_neg_1_9, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_14) end))) - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_14) end)))  AS pmtct_neg_10_14, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_19) end))) - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_19) end)))   AS pmtct_neg_15_19, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_24) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_24) end)))  AS pmtct_neg_20_24, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_29) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_29) end)))  AS pmtct_neg_25_29, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_34) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_34) end)))   AS pmtct_neg_30_34, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_39) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_39) end)))   AS pmtct_neg_35_39, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_49) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_49) end)))   AS pmtct_neg_40_49, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_50) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_50) end)))   AS pmtct_neg_50,"+

            
                //-------pmtct stat numerator----------------
        
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201 + HV0205) end)) as pmtct_tes_numerator," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*0) end)) as pmtct_statnum_tes_unknown," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*(f_1+f_4+f_9)) end)) as pmtct_statnum_tes_10," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_14) end)) as pmtct_statnum_tes_10_14," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_19) end)) as pmtct_statnum_tes_15_19," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_24) end)) as pmtct_statnum_tes_20_24," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_29) end)) as pmtct_statnum_tes_25_29," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_34) end)) as pmtct_statnum_tes_30_34," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_39) end)) as pmtct_statnum_tes_35_39," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_49) end)) as pmtct_statnum_tes_40_49," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_50) end)) as pmtct_statnum_tes_50,"+
            
            //-------pmtct stat denominator----------------
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)) end)) as pmtct_tes_denominator , " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*0) end)) as pmtct_statden_tes_unknown, " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*(f_1+f_4+f_9)) end)) as pmtct_statden_tes_10, " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_14) end)) as pmtct_statden_tes_10_14," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_19) end)) as pmtct_statden_tes_15_19, " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_24) end)) as pmtct_statden_tes_20_24, " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_29) end)) as pmtct_statden_tes_25_29," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_34) end)) as pmtct_statden_tes_30_34," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_39) end)) as pmtct_statden_tes_35_39," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_49) end)) as pmtct_statden_tes_40_49," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_50) end)) as pmtct_statden_tes_50,"+
            
            //-------pmtct stat numerator----------------
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205) end)) as pmtct_knownpositive ," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*0) end)) as pmtct_kp_unknown, " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*(f_4+f_9)) end)) as pmtct_kp_1_9," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_14) end)) as pmtct_kp_10_14," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_19) end)) as pmtct_kp_15_19," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_24) end)) as pmtct_kp_20_24, " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_29) end)) as pmtct_kp_25_29," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_34) end)) as pmtct_kp_30_34," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_39) end)) as pmtct_kp_35_39," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_49) end)) as pmtct_kp_40_49," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_50) end)) as pmtct_kp_50,"
            
            + " county.County,district.DistrictNom,"
            + ""+facilitiestable+".SubPartnerNom,"+facilitiestable+".CentreSanteId,"+facilitiestable+".PMTCT_Support ,IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume"// facility details
            +" ,  "+facilitiestable+".SubPartnerID, IFNULL(HTC,0) as HTC, IFNULL(PMTCT,0) as PMTCT,IFNULL(SUM(new_anc),0) AS new_anc "//new numerator for 2017 //_raise athe issue of monthly and quartely data for eid
            + " FROM moh731 JOIN "+facilitiestable+" "
            + " ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
            + " JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON "
            + " district.CountyID=county.CountyID "
            + " left JOIN new_anc on moh731.id=new_anc.id "//added on 9th October 2017
            + " LEFT JOIN ratios ON county.CountyID=ratios.county_id "//added on 4th Jan 2018
            + " WHERE moh731.yearmonth BETWEEN "+startyearmonth+" AND "+endyearmonth+" && ( "+facilitiestable+".PMTCT=1) "
            + " AND (indicator='PMTCT_Known_Postive' OR indicator='PMTCT_New_Postive' OR indicator='PMTCT_ANC')  AND "+facilitiestable+".active=1  "
            + " GROUP BY moh731.SubPartnerID,yearmonth " ;
    
    
    int rowposit=6;
    
     System.out.println("2018q1 PMTCT : "+get731data);
    conn.rs=conn.st.executeQuery(get731data);
    while(conn.rs.next()){
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

        JSONObject period_data = getperiod(yearmonth);

        year = period_data.get("year").toString();
        semi_annual = period_data.get("semi_annual").toString();
        quarter = period_data.get("quarter").toString();
        month = period_data.get("month").toString();

     
            double pmtct_anc_tes=conn.rs.getDouble("pmtct_anc_tes");
            double pmtct_anc_tes_unknown=conn.rs.getDouble("pmtct_anc_tes_unknown");
            double pmtct_anc_tes_1=conn.rs.getDouble("pmtct_anc_tes_1");
            double pmtct_anc_tes_1_9=conn.rs.getDouble("pmtct_anc_tes_1_9");
            double pmtct_anc_tes_10_14=conn.rs.getDouble("pmtct_anc_tes_10_14");
            double pmtct_anc_tes_15_19=conn.rs.getDouble("pmtct_anc_tes_15_19");
            double pmtct_anc_tes_20_24=conn.rs.getDouble("pmtct_anc_tes_20_24");
            double pmtct_anc_tes_25_29=conn.rs.getDouble("pmtct_anc_tes_25_29");
            double pmtct_anc_tes_30_34=conn.rs.getDouble("pmtct_anc_tes_30_34");
            double pmtct_anc_tes_35_39=conn.rs.getDouble("pmtct_anc_tes_35_39");
            double pmtct_anc_tes_40_49=conn.rs.getDouble("pmtct_anc_tes_40_49");
            double pmtct_anc_tes_50=conn.rs.getDouble("pmtct_anc_tes_50");



            double pmtct_knownpositive=conn.rs.getDouble("pmtct_knownpositive");
            double pmtct_kp_unknown=conn.rs.getDouble("pmtct_kp_unknown");
            double pmtct_kp_1_9=conn.rs.getDouble("pmtct_kp_1_9");
            double pmtct_kp_10_14=conn.rs.getDouble("pmtct_kp_10_14");
            double pmtct_kp_15_19=conn.rs.getDouble("pmtct_kp_15_19");
            double pmtct_kp_20_24=conn.rs.getDouble("pmtct_kp_20_24");
            double pmtct_kp_25_29=conn.rs.getDouble("pmtct_kp_25_29");
            double pmtct_kp_30_34=conn.rs.getDouble("pmtct_kp_30_34");
            double pmtct_kp_35_39=conn.rs.getDouble("pmtct_kp_35_39");
            double pmtct_kp_40_49=conn.rs.getDouble("pmtct_kp_40_49");
            double pmtct_kp_50=conn.rs.getDouble("pmtct_kp_50");


            double pmtct_newpositive=conn.rs.getDouble("pmtct_newpositive");
            double pmtct_pos_unknown=conn.rs.getDouble("pmtct_pos_unknown");
            double pmtct_pos_1=conn.rs.getDouble("pmtct_pos_1");
            double pmtct_pos_1_9=conn.rs.getDouble("pmtct_pos_1_9");
            double pmtct_pos_10_14=conn.rs.getDouble("pmtct_pos_10_14");
            double pmtct_pos_15_19=conn.rs.getDouble("pmtct_pos_15_19");
            double pmtct_pos_20_24=conn.rs.getDouble("pmtct_pos_20_24");
            double pmtct_pos_25_29=conn.rs.getDouble("pmtct_pos_25_29");
            double pmtct_pos_30_34=conn.rs.getDouble("pmtct_pos_30_34");
            double pmtct_pos_35_39=conn.rs.getDouble("pmtct_pos_35_39");
            double pmtct_pos_40_49=conn.rs.getDouble("pmtct_pos_40_49");
            double pmtct_pos_50=conn.rs.getDouble("pmtct_pos_50");

            double pmtct_new_negatives=conn.rs.getDouble("pmtct_new_negatives");
            double pmtct_neg_unknown=conn.rs.getDouble("pmtct_neg_unknown");
            double pmtct_neg_1=conn.rs.getDouble("pmtct_neg_1");
            double pmtct_neg_1_9=conn.rs.getDouble("pmtct_neg_1_9");
            double pmtct_neg_10_14=conn.rs.getDouble("pmtct_neg_10_14");
            double pmtct_neg_15_19=conn.rs.getDouble("pmtct_neg_15_19");
            double pmtct_neg_20_24=conn.rs.getDouble("pmtct_neg_20_24");
            double pmtct_neg_25_29=conn.rs.getDouble("pmtct_neg_25_29");
            double pmtct_neg_30_34=conn.rs.getDouble("pmtct_neg_30_34");
            double pmtct_neg_35_39=conn.rs.getDouble("pmtct_neg_35_39");
            double pmtct_neg_40_49=conn.rs.getDouble("pmtct_neg_40_49");
            double pmtct_neg_50=conn.rs.getDouble("pmtct_neg_50");



            double pmtct_tes_numerator=conn.rs.getDouble("pmtct_tes_numerator");
            double pmtct_statnum_tes_unknown=conn.rs.getDouble("pmtct_statnum_tes_unknown");
            double pmtct_statnum_tes_10=conn.rs.getDouble("pmtct_statnum_tes_10");
            double pmtct_statnum_tes_10_14=conn.rs.getDouble("pmtct_statnum_tes_10_14");
            double pmtct_statnum_tes_15_19=conn.rs.getDouble("pmtct_statnum_tes_15_19");
            double pmtct_statnum_tes_20_24=conn.rs.getDouble("pmtct_statnum_tes_20_24");
            double pmtct_statnum_tes_25_29=conn.rs.getDouble("pmtct_statnum_tes_25_29");
            double pmtct_statnum_tes_30_34=conn.rs.getDouble("pmtct_statnum_tes_30_34");
            double pmtct_statnum_tes_35_39=conn.rs.getDouble("pmtct_statnum_tes_35_39");
            double pmtct_statnum_tes_40_49=conn.rs.getDouble("pmtct_statnum_tes_40_49");
            double pmtct_statnum_tes_50=conn.rs.getDouble("pmtct_statnum_tes_50");

            double pmtct_tes_denominator=conn.rs.getDouble("pmtct_tes_denominator");
            double pmtct_statden_tes_unknown=conn.rs.getDouble("pmtct_statden_tes_unknown");
            double pmtct_statden_tes_10=conn.rs.getDouble("pmtct_statden_tes_10");
            double pmtct_statden_tes_10_14=conn.rs.getDouble("pmtct_statden_tes_10_14");
            double pmtct_statden_tes_15_19=conn.rs.getDouble("pmtct_statden_tes_15_19");
            double pmtct_statden_tes_20_24=conn.rs.getDouble("pmtct_statden_tes_20_24");
            double pmtct_statden_tes_25_29=conn.rs.getDouble("pmtct_statden_tes_25_29");
            double pmtct_statden_tes_30_34=conn.rs.getDouble("pmtct_statden_tes_30_34");
            double pmtct_statden_tes_35_39=conn.rs.getDouble("pmtct_statden_tes_35_39");
            double pmtct_statden_tes_40_49=conn.rs.getDouble("pmtct_statden_tes_40_49");
            double pmtct_statden_tes_50=conn.rs.getDouble("pmtct_statden_tes_50");
            int ancno=conn.rs.getInt("new_anc");
      
     double ancdiff=0; 
     double numeratordiff=0;
     double denominatordiff=0;
     double knownpositivediff=0;
     double newpositivediff=0;
     double diff15to19=0;

     int negativediff=0;
     
    ancdiff=(pmtct_anc_tes-(pmtct_pos_unknown+pmtct_pos_1+pmtct_pos_1_9+pmtct_pos_10_14+pmtct_pos_15_19+pmtct_pos_20_24+pmtct_pos_25_29+pmtct_pos_30_34+pmtct_pos_35_39+pmtct_pos_40_49+pmtct_pos_50+pmtct_neg_unknown+pmtct_neg_1+pmtct_neg_1_9+pmtct_neg_10_14+pmtct_neg_15_19+pmtct_neg_20_24+pmtct_neg_25_29+pmtct_neg_30_34+pmtct_neg_35_39+pmtct_neg_40_49+pmtct_neg_50));
    numeratordiff=(pmtct_tes_numerator-(pmtct_statnum_tes_unknown+pmtct_statnum_tes_10+pmtct_statnum_tes_10_14+pmtct_statnum_tes_15_19+pmtct_statnum_tes_20_24+pmtct_statnum_tes_25_29+pmtct_statnum_tes_30_34+pmtct_statnum_tes_35_39+pmtct_statnum_tes_40_49+pmtct_statnum_tes_50));
    denominatordiff=(pmtct_tes_denominator-(pmtct_statden_tes_unknown+pmtct_statden_tes_10+pmtct_statden_tes_10_14+pmtct_statden_tes_15_19+pmtct_statden_tes_20_24+pmtct_statden_tes_25_29+pmtct_statden_tes_30_34+pmtct_statden_tes_35_39+pmtct_statden_tes_40_49+pmtct_statden_tes_50) );
    knownpositivediff=(pmtct_knownpositive-(pmtct_kp_unknown+pmtct_kp_1_9+pmtct_kp_10_14+pmtct_kp_15_19+pmtct_kp_20_24+pmtct_kp_25_29+pmtct_kp_30_34+pmtct_kp_35_39+pmtct_kp_40_49+pmtct_kp_50));
    newpositivediff=(pmtct_newpositive-(pmtct_pos_unknown+pmtct_pos_1+pmtct_pos_1_9+pmtct_pos_10_14+pmtct_pos_15_19+pmtct_pos_20_24+pmtct_pos_25_29+pmtct_pos_30_34+pmtct_pos_35_39+pmtct_pos_40_49+pmtct_pos_50));
    negativediff=((int) Math.round(pmtct_new_negatives)-(int) Math.round((pmtct_neg_unknown+pmtct_neg_1+pmtct_neg_1_9+pmtct_neg_10_14+pmtct_neg_15_19+pmtct_neg_20_24+pmtct_neg_25_29+pmtct_neg_30_34+pmtct_neg_35_39+pmtct_neg_40_49+pmtct_neg_50)));
     //compare anc_positive, anc_negative, knownpos against stat numerator   
    
    diff15to19=(pmtct_statden_tes_15_19-(pmtct_pos_15_19+pmtct_neg_15_19+pmtct_kp_15_19));
//    diff25to49=(pmtct_statden_tes_25_49-(pmtct_pos_25_49+pmtct_neg_25_49+pmtct_kp_25_49));

        System.out.println(" ");
     
      //do normalization
     if(1==1){
     while(ancdiff>0){ pmtct_neg_20_24=pmtct_neg_20_24+1; ancdiff--;}
     while(ancdiff<0){  pmtct_neg_20_24=pmtct_neg_20_24-1; ancdiff++;}
     //numerator normalization
      while(numeratordiff>0){ pmtct_statnum_tes_20_24=pmtct_statnum_tes_20_24+1;  numeratordiff--; }
      
       while(numeratordiff<0){  pmtct_statnum_tes_20_24=pmtct_statnum_tes_20_24-1;   numeratordiff++;  }
      
      //denominator normalization
      while(denominatordiff>0){ pmtct_statden_tes_20_24=pmtct_statden_tes_20_24+1;  denominatordiff--;}
       while(denominatordiff<0){  pmtct_statden_tes_20_24=pmtct_statden_tes_20_24-1;   denominatordiff++;  }
    }  
     
    ///**
     while(newpositivediff>0){ pmtct_pos_20_24=pmtct_pos_20_24+1;   newpositivediff--; }
     while(newpositivediff<0){   pmtct_pos_20_24=pmtct_pos_20_24-1;    newpositivediff++; }
 
     while(knownpositivediff>0){ pmtct_kp_20_24=pmtct_kp_20_24+1; knownpositivediff--; }
     while(knownpositivediff<0){   pmtct_kp_20_24=pmtct_kp_20_24-1;  knownpositivediff++;  }
     
     //repeat anc normalization again due to the other normalizations that have happened after
   ancdiff=(pmtct_anc_tes-(pmtct_pos_unknown+pmtct_pos_1+pmtct_pos_1_9+pmtct_pos_10_14+pmtct_pos_15_19+pmtct_pos_20_24+pmtct_pos_25_29+pmtct_pos_30_34+pmtct_pos_35_39+pmtct_pos_40_49+pmtct_pos_50+pmtct_neg_unknown+pmtct_neg_1+pmtct_neg_1_9+pmtct_neg_10_14+pmtct_neg_15_19+pmtct_neg_20_24+pmtct_neg_25_29+pmtct_neg_30_34+pmtct_neg_35_39+pmtct_neg_40_49+pmtct_neg_50));
 
      while(ancdiff>0){ pmtct_neg_20_24=pmtct_neg_20_24+1;  ancdiff--; }
     while(ancdiff<0){   pmtct_neg_20_24=pmtct_neg_20_24-1;  ancdiff++; }
     
     //to get pmtct stat numerator, add neg+pos _+ kps

     
             String alldatavals[]={county
             ,""+pmtct_pos_unknown,""+pmtct_pos_1,""+pmtct_pos_1_9,""+pmtct_pos_10_14,""+pmtct_pos_15_19,""+pmtct_pos_20_24,""+pmtct_pos_25_29,""+pmtct_pos_30_34,""+pmtct_pos_35_39,""+pmtct_pos_40_49,""+pmtct_pos_50
             ,""+pmtct_neg_unknown,""+pmtct_neg_1,""+pmtct_neg_1_9,""+pmtct_neg_10_14,""+pmtct_neg_15_19,""+pmtct_neg_20_24,""+pmtct_neg_25_29,""+pmtct_neg_30_34,""+pmtct_neg_35_39,""+pmtct_neg_40_49,""+pmtct_neg_50
             ,""+pmtct_anc_tes//total anc tested        
             ,""+pmtct_tes_numerator//numerator
             ,""+pmtct_statnum_tes_unknown,""+pmtct_statnum_tes_10,""+pmtct_statnum_tes_10_14,""+pmtct_statnum_tes_15_19,""+pmtct_statnum_tes_20_24,""+pmtct_statnum_tes_25_29,""+pmtct_statnum_tes_30_34,""+pmtct_statnum_tes_35_39,""+pmtct_statnum_tes_40_49,""+pmtct_statnum_tes_50
             
             
             ,""+pmtct_knownpositive//known positive
             ,""+pmtct_kp_unknown,""+pmtct_kp_1_9,""+pmtct_kp_10_14,""+pmtct_kp_15_19,""+pmtct_kp_20_24,""+pmtct_kp_25_29,""+pmtct_kp_30_34,""+pmtct_kp_35_39,""+pmtct_kp_40_49,""+pmtct_kp_50
             
             ,""+pmtct_newpositive//new positives
             ,""+pmtct_pos_unknown,""+pmtct_pos_1_9,""+pmtct_pos_10_14,""+pmtct_pos_15_19,""+pmtct_pos_20_24,""+pmtct_pos_25_29,""+pmtct_pos_30_34,""+pmtct_pos_35_39,""+pmtct_pos_40_49,""+pmtct_pos_50
             
             ,""+pmtct_new_negatives//new negatives
             ,""+pmtct_neg_unknown,""+pmtct_neg_1_9,""+pmtct_neg_10_14,""+pmtct_neg_15_19,""+pmtct_neg_20_24,""+pmtct_neg_25_29,""+pmtct_neg_30_34,""+pmtct_neg_35_39,""+pmtct_neg_40_49,""+pmtct_neg_50
                      
             ,""+pmtct_tes_denominator//denominator
             ,""+pmtct_statden_tes_unknown,""+pmtct_statden_tes_10,""+pmtct_statden_tes_10_14,""+pmtct_statden_tes_15_19,""+pmtct_statden_tes_20_24,""+pmtct_statden_tes_25_29,""+pmtct_statden_tes_30_34,""+pmtct_statden_tes_35_39,""+pmtct_statden_tes_40_49,""+pmtct_statden_tes_50,""+pmtct_tes_denominator
             ,""+arthv,""+htchv,""+pmtcthv,""+conn.rs.getString("HTC"),""+conn.rs.getString("PMTCT")};
    

    String[] header_Num ={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","f_1_9","f_14","f_19","f_24","f_29","f_34","f_39","f_49","f_50","total","total_f","year",
    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
              id_=mfl_code+"_"+yearmonth+"_26"; //
    String[] data_Num =(""+id_+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","+mfl_code+","+support_type+","+
    "90=Knowing HIV Status,PMTCT,PMTCT STAT Num,"+pmtct_statnum_tes_10+","+pmtct_statnum_tes_10_14+","+pmtct_statnum_tes_15_19+","+pmtct_statnum_tes_20_24+","+pmtct_statnum_tes_25_29+","+pmtct_statnum_tes_30_34+","+pmtct_statnum_tes_35_39+","+pmtct_statnum_tes_40_49+","+pmtct_statnum_tes_50+","+pmtct_tes_numerator+","+pmtct_tes_numerator+","+
    ""+year+","+semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    ""+allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
 
             
    String[] header_KP ={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","f_1_9","f_14","f_19","f_24","f_29","f_34","f_39","f_49","f_50","total","total_f","year",
    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
     id_=mfl_code+"_"+yearmonth+"_24"; //
    String[] data_KP =(""+id_+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","+mfl_code+","+support_type+","+
    "90=Knowing HIV Status,PMTCT,PMTCT STAT Known Pos,"+pmtct_kp_1_9+","+pmtct_kp_10_14+","+pmtct_kp_15_19+","+pmtct_kp_20_24+","+pmtct_kp_25_29+","+pmtct_kp_30_34+","+pmtct_kp_35_39+","+pmtct_kp_40_49+","+pmtct_kp_50+","+pmtct_knownpositive+","+pmtct_knownpositive+","+
    ""+year+","+semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    ""+allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
 
             
    String[] header_New ={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","f_1_9","f_14","f_19","f_24","f_29","f_34","f_39","f_49","f_50","total","total_f","year",
    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
    id_=mfl_code+"_"+yearmonth+"_25"; //
    String[] data_New =(""+id_+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","+mfl_code+","+support_type+","+
    "90=Knowing HIV Status,PMTCT,PMTCT STAT New Pos,"+pmtct_pos_1_9+","+pmtct_pos_10_14+","+pmtct_pos_15_19+","+pmtct_pos_20_24+","+pmtct_pos_25_29+","+pmtct_pos_30_34+","+pmtct_pos_35_39+","+pmtct_pos_40_49+","+pmtct_pos_50+","+pmtct_newpositive+","+pmtct_newpositive+","+
    ""+year+","+semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    ""+allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
 
             
    String[] header_Den ={"id","county","burdencategory","constituency","subcounty","ward","facility","mflcode","supporttype",
    "level1","level2","level3","f_1_9","f_14","f_19","f_24","f_29","f_34","f_39","f_49","f_50","total","total_f","year",
    "semiannual","quarter","month","yearmonth","ownedby","facilitytype","art_hv","htc_hv","pmtct_hv",
    "activity_hv","latitude","longitude","maleclinic","adoleclinic","viremiaclinic","emrsite","linkdesk","islocked"};
    id_=mfl_code+"_"+yearmonth+"_27"; //
    String[] data_Den =(""+id_+","+county+","+burdencategory+","+constituency+","+sub_county+","+ward+","+facilityName+","+mfl_code+","+support_type+","+
    "90=Knowing HIV Status,PMTCT,PMTCT_STAT Den,"+pmtct_statden_tes_10+","+pmtct_statden_tes_10_14+","+pmtct_statden_tes_15_19+","+pmtct_statden_tes_20_24+","+pmtct_statden_tes_25_29+","+pmtct_statden_tes_30_34+","+pmtct_statden_tes_35_39+","+pmtct_statden_tes_40_49+","+pmtct_statden_tes_50+","+pmtct_tes_denominator+","+pmtct_tes_denominator+","+
    ""+year+","+semi_annual+","+quarter+","+month+","+yearmonth+","+Owner+","+Type+","+arthv+","+htchv+","+pmtcthv+","+
    ""+allhv+","+latitude+","+longitude+","+Male_clinics+","+Adolescent_clinics+","+Viremia_clinics+","+EMR_Sites+","+Link_desks+",0").split(",");
 
    String[][] header = {header_Num,header_KP,header_New,header_Den};        
    String[][] data = {data_Num,data_KP,data_New,data_Den};  
    
    for(int a=0;a<header.length;a++){
    String query_params = "";
    for(int i=0;i<header[a].length;i++){
    query_params+=header[a][i]+"=?,";    
    }
    //remove last comma
    query_params = removeLastChars(query_params, 1);
    
   String query = "REPLACE INTO table3 SET "+query_params;
   conndash.pst = conndash.conn.prepareStatement(query);
           System.out.println("query : "+query);
    for(int i=0;i<data[a].length;i++){
        conndash.pst.setString((i+1), data[a][i]);   
    }
    conndash.pst.executeUpdate();
    
    }   
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
    
   