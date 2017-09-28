/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EKaunda
 */
public class hts_methods extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
            
            
        } finally {
            out.close();
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    
    
    
    
  public ArrayList testedSpecial(ResultSet rset, String age_set) throws SQLException{
      //1
        int HV0103,HV0201,serology,total;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        
        HV0103 = rset.getInt("HV0103");
        HV0201 = rset.getInt("HV0201");
        serology = rset.getInt("HV0226");
        total = rset.getInt("total");
       
 //===HTC 731==============
        
        //initialize
        //male female
         double testedmale_731,testedfemale_731;
       
      // adult, child by gender
         int TestAdultF_731, TestChildF_731;         
         int TestAdultM_731, TestChildM_731;
         
        float f_731_1,f_731_4,f_731_9,f_731_14,f_731_19,f_731_24,f_731_49,f_731_50;	
        float m_731_1,m_731_4,m_731_9,m_731_14,m_731_19,m_731_24,m_731_49,m_731_50 ;
        float f_pmtct_1,f_pmtct_4,f_pmtct_9,f_pmtct_14,f_pmtct_19,f_pmtct_24,f_pmtct_49,f_pmtct_50; 
        double ttlposM_731=0;   
        double ttlposF_731=0; 
        
        float ttltesM=0;
        float ttltesF=0;
         
       
//-------------male and female------------------   
   testedmale_731=(float)Math.round((0.38*HV0103));
   testedfemale_731=(float)Math.round((0.62*HV0103));
  
  
  double tft=HV0103-(testedmale_731+testedfemale_731);
  if(tft!=0){ testedfemale_731+=tft; }
  
  
  //-----------------adult and children by gender-------
         //--Female
         TestAdultF_731 = (int) Math.round((0.88 * testedfemale_731));  //adult   
         TestChildF_731 = (int) Math.round((0.12 * testedfemale_731)); //child

         tft = testedfemale_731 - (TestAdultF_731 + TestChildF_731);
         if (tft != 0) {
             TestAdultF_731 += tft;
         }
    
	//--Male
         TestAdultM_731 = (int) Math.round((0.83 * testedmale_731));  //adult   
         TestChildM_731 = (int) Math.round((0.17 * testedmale_731)); //child

         tft = testedmale_731 - (TestAdultM_731 + TestChildM_731);
         if (tft != 0) {
             TestAdultM_731 += tft;
         }
  
  
  //----------------------------------------------
 
f_731_1=(float)Math.round((0*TestChildF_731));
f_731_4=(float)Math.round((0.27*TestChildF_731));
f_731_9=(float)Math.round((0.30*TestChildF_731));
f_731_14=(float)Math.round((0.43*TestChildF_731));

f_731_19=(float)Math.round((0.13*TestAdultF_731));
f_731_24=(float)Math.round((0.26*TestAdultF_731));
f_731_49=(float)Math.round((0.54*TestAdultF_731));
f_731_50=(float)Math.round((0.07*TestAdultF_731));



 

  m_731_1=(float)Math.round((0*TestChildM_731));
  m_731_4=(float)Math.round((0.26*TestChildM_731));
  m_731_9=(float)Math.round((0.29*TestChildM_731));
  m_731_14=(float)Math.round((0.45*TestChildM_731));

  m_731_19=(float)Math.round((0.13*TestAdultM_731));
  m_731_24=(float)Math.round((0.20*TestAdultM_731));
  m_731_49=(float)Math.round((0.56*TestAdultM_731));
  m_731_50=(float)Math.round((0.11*TestAdultM_731));

  //--normalization moh 731-----
   ttlposF_731=f_731_19+f_731_24+f_731_49+f_731_50+f_731_1+f_731_4+f_731_9+f_731_14 ;
   ttlposM_731=m_731_19+m_731_24+m_731_49+m_731_50+m_731_1+m_731_4+m_731_9+m_731_14 ;

   double splitData=0; int adderPos=0;
   double childSplitData=0;
   
   //tested female adults
splitData=f_731_19+f_731_24+f_731_49+f_731_50 ;
 while(splitData<TestAdultF_731){ 
 f_731_49+=1; 
 splitData++;
}
 
splitData=f_731_19+f_731_24+f_731_49+f_731_50 ;
 while(splitData>TestAdultF_731){ 
 f_731_49-=1; 
 splitData--;
}
 
 
  //tested male adults
splitData=m_731_19+m_731_24+m_731_49+m_731_50;
 while(splitData<TestAdultM_731){ 
 m_731_49+=1; 
 splitData++;
}
splitData=m_731_19+m_731_24+m_731_49+m_731_50;
 while(splitData>TestAdultM_731){ 
 m_731_49-=1; 
 splitData--;
}

 //children 
 
 //female
  childSplitData=f_731_1+f_731_4+f_731_9+f_731_14;   

   adderPos=0;
while(childSplitData<TestChildF_731){ 
if(adderPos==0){
  f_731_14+=1;   
 }
if(adderPos==1){
 f_731_9+=1;    
 }
if(adderPos==2){
 f_731_4+=1;    
 }
childSplitData++;
adderPos++;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestChildF_731){}
}
 
   childSplitData=f_731_1+f_731_4+f_731_9+f_731_14; 
  adderPos=0;
  
while(childSplitData>TestChildF_731){ 
 if(adderPos==0){
  f_731_14-=1;   
 }
if(adderPos==1){
 f_731_9-=1;    
 }
if(adderPos==2){
 f_731_4-=1;    
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestChildF_731){}
}

//male


  childSplitData=m_731_1+m_731_4+m_731_9+m_731_14;   

   adderPos=0;
while(childSplitData<TestChildM_731){ 
if(adderPos==0){
 m_731_14+=1;   
 }
if(adderPos==1){
 m_731_9+=1;    
 }
if(adderPos==2){
 m_731_4+=1;    
 }
childSplitData++;
adderPos++;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestChildM_731){}
}
 
   childSplitData=m_731_1+m_731_4+m_731_9+m_731_14; 
  adderPos=0;
  
while(childSplitData>TestChildM_731){ 
 if(adderPos==0){
  m_731_14-=1;   
 }
if(adderPos==1){
 m_731_9-=1;    
 }
if(adderPos==2){
 m_731_4-=1;    
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestChildM_731){}
}



	   //======end of htc from 731=========


//--------------pmtct tested-----------------
//,,,,,,,; 
            f_pmtct_1= HV0201*0;           
            f_pmtct_4= HV0201*0;            
            f_pmtct_9= HV0201*0;         
            f_pmtct_14=HV0201*0;            
            f_pmtct_19=(float)(HV0201*0.094);            
            f_pmtct_24=(float)(HV0201*0.384);            
            f_pmtct_49= (float)(HV0201*0.52);
            f_pmtct_50= (float)(HV0201*0);

float pmtctttl=f_pmtct_1+f_pmtct_4+f_pmtct_9+f_pmtct_14+f_pmtct_19+f_pmtct_24+f_pmtct_49+f_pmtct_50;
          
float ancdiff=HV0201-pmtctttl;

         while(ancdiff>0){
       
         //deduct from anctested
           f_pmtct_49=f_pmtct_49+1; 
           ancdiff--;
        
     }
     while(ancdiff<0){       
       
             //deduct from main number contributing the ratios             
         f_pmtct_49=f_pmtct_49-1;         
       ancdiff++;
     }
            
float f_ser_1=(float)Math.round((0.4*serology));
float m_ser_1=(float)Math.round((0.6*serology));
  
        tft = serology - (f_ser_1 + m_ser_1);
         if (tft != 0) {
             m_ser_1 += tft;
         }        


        m_1=(int)(m_ser_1+m_731_1);
        m_4 = (int) (m_731_4);
        m_9 = (int) (m_731_9);
        m_14 = (int) (m_731_14);                
        m_19 = (int) (m_731_19);
        m_24 = (int) (m_731_24);
        m_49 = (int) (m_731_49);
        m_50 = (int) (m_731_50); 
        
        f_1=(int) (f_ser_1+f_731_1+f_pmtct_1);
        
        f_4 = (int) (f_731_4+f_pmtct_4);
        f_9 = (int) (f_731_9+f_pmtct_9);
        f_14 = (int) (f_731_14+f_pmtct_14);
                
        f_19 = (int) (f_731_14+f_pmtct_14);
        f_24 = (int) (f_731_24+f_pmtct_24);
        f_49 = (int) (f_731_49+f_pmtct_49);
        f_50 = (int) (f_731_50+f_pmtct_50);
        
//        normalization
//        
       
//        male 1-14

        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
        
         
         ttltesF=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
         ttltesM=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        
//        Hashmap array
//        Datim ages
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
         new_data.add(ttltesM);
         new_data.add(ttltesF);
         new_data.add(total);
 
        return new_data;
    }
  
   public ArrayList testedOld731(ResultSet rset, String age_set) throws SQLException{
      //62
        int HV0103,total;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        
        HV0103 = rset.getInt("HV0103");        
       
        total = rset.getInt("HV0103");
       
 //===HTC 731==============
        
        //initialize
        //male female
         double testedmale_731,testedfemale_731;
       
      // adult, child by gender
         int TestAdultF_731, TestChildF_731;         
         int TestAdultM_731, TestChildM_731;
         
        float f_731_1,f_731_4,f_731_9,f_731_14,f_731_19,f_731_24,f_731_49,f_731_50;	
        float m_731_1,m_731_4,m_731_9,m_731_14,m_731_19,m_731_24,m_731_49,m_731_50 ;
        float f_pmtct_1,f_pmtct_4,f_pmtct_9,f_pmtct_14,f_pmtct_19,f_pmtct_24,f_pmtct_49,f_pmtct_50; 
      
        
        float ttltesM=0;
        float ttltesF=0;
         
       
//-------------male and female------------------   
   testedmale_731=(float)Math.round((0.38*HV0103));
   testedfemale_731=(float)Math.round((0.62*HV0103));
  
  
  double tft=HV0103-(testedmale_731+testedfemale_731);
  if(tft!=0){ testedfemale_731+=tft; }
  
  
  //-----------------adult and children by gender-------
         //--Female
         TestAdultF_731 = (int) Math.round((0.88 * testedfemale_731));  //adult   
         TestChildF_731 = (int) Math.round((0.12 * testedfemale_731)); //child

         tft = testedfemale_731 - (TestAdultF_731 + TestChildF_731);
         if (tft != 0) {
             TestAdultF_731 += tft;
         }
    
	//--Male
         TestAdultM_731 = (int) Math.round((0.83 * testedmale_731));  //adult   
         TestChildM_731 = (int) Math.round((0.17 * testedmale_731)); //child

         tft = testedmale_731 - (TestAdultM_731 + TestChildM_731);
         if (tft != 0) {
             TestAdultM_731 += tft;
         }
  
  
  //----------------------------------------------
 
f_731_1=(float)Math.round((0*TestChildF_731));
f_731_4=(float)Math.round((0.27*TestChildF_731));
f_731_9=(float)Math.round((0.30*TestChildF_731));
f_731_14=(float)Math.round((0.43*TestChildF_731));

f_731_19=(float)Math.round((0.13*TestAdultF_731));
f_731_24=(float)Math.round((0.26*TestAdultF_731));
f_731_49=(float)Math.round((0.54*TestAdultF_731));
f_731_50=(float)Math.round((0.07*TestAdultF_731));



 

  m_731_1=(float)Math.round((0*TestChildM_731));
  m_731_4=(float)Math.round((0.26*TestChildM_731));
  m_731_9=(float)Math.round((0.29*TestChildM_731));
  m_731_14=(float)Math.round((0.45*TestChildM_731));

  m_731_19=(float)Math.round((0.13*TestAdultM_731));
  m_731_24=(float)Math.round((0.20*TestAdultM_731));
  m_731_49=(float)Math.round((0.56*TestAdultM_731));
  m_731_50=(float)Math.round((0.11*TestAdultM_731));

  //--normalization moh 731-----
   float ttltsF_731=f_731_19+f_731_24+f_731_49+f_731_50+f_731_1+f_731_4+f_731_9+f_731_14 ;
   float ttltsM_731=m_731_19+m_731_24+m_731_49+m_731_50+m_731_1+m_731_4+m_731_9+m_731_14 ;

   double splitData=0; int adderPos=0;
   double childSplitData=0;
   
   //tested female adults
splitData=f_731_19+f_731_24+f_731_49+f_731_50 ;
 while(splitData<TestAdultF_731){ 
 f_731_49+=1; 
 splitData++;
}
 
splitData=f_731_19+f_731_24+f_731_49+f_731_50 ;
 while(splitData>TestAdultF_731){ 
 f_731_49-=1; 
 splitData--;
}
 
 
  //tested male adults
splitData=m_731_19+m_731_24+m_731_49+m_731_50;
 while(splitData<TestAdultM_731){ 
 m_731_49+=1; 
 splitData++;
}
splitData=m_731_19+m_731_24+m_731_49+m_731_50;
 while(splitData>TestAdultM_731){ 
 m_731_49-=1; 
 splitData--;
}

 //children 
 
 //female
  childSplitData=f_731_1+f_731_4+f_731_9+f_731_14;   

   adderPos=0;
while(childSplitData<TestChildF_731){ 
if(adderPos==0){
  f_731_14+=1;   
 }
if(adderPos==1){
 f_731_9+=1;    
 }
if(adderPos==2){
 f_731_4+=1;    
 }
childSplitData++;
adderPos++;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestChildF_731){}
}
 
   childSplitData=f_731_1+f_731_4+f_731_9+f_731_14; 
  adderPos=0;
  
while(childSplitData>TestChildF_731){ 
 if(adderPos==0){
  f_731_14-=1;   
 }
if(adderPos==1){
 f_731_9-=1;    
 }
if(adderPos==2){
 f_731_4-=1;    
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestChildF_731){}
}

//male


  childSplitData=m_731_1+m_731_4+m_731_9+m_731_14;   

   adderPos=0;
while(childSplitData<TestChildM_731){ 
if(adderPos==0){
 m_731_14+=1;   
 }
if(adderPos==1){
 m_731_9+=1;    
 }
if(adderPos==2){
 m_731_4+=1;    
 }
childSplitData++;
adderPos++;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestChildM_731){}
}
 
   childSplitData=m_731_1+m_731_4+m_731_9+m_731_14; 
  adderPos=0;
  
while(childSplitData>TestChildM_731){ 
 if(adderPos==0){
  m_731_14-=1;   
 }
if(adderPos==1){
 m_731_9-=1;    
 }
if(adderPos==2){
 m_731_4-=1;    
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestChildM_731){}
}



	   //======end of htc from 731=========



        m_1=(int)(m_731_1);
        m_4 = (int) (m_731_4);
        m_9 = (int) (m_731_9);
        m_14 = (int) (m_731_14);                
        m_19 = (int) (m_731_19);
        m_24 = (int) (m_731_24);
        m_49 = (int) (m_731_49);
        m_50 = (int) (m_731_50); 
        
        f_1=(int) (f_731_1);
        
        f_4 = (int) (f_731_4);
        f_9 = (int) (f_731_9);
        f_14 = (int) (f_731_14);
                
        f_19 = (int) (f_731_14);
        f_24 = (int) (f_731_24);
        f_49 = (int) (f_731_49);
        f_50 = (int) (f_731_50);
        
//        normalization
       
//        male 1-14
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
        
         
         ttltesF=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
         ttltesM=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        

//        Datim ages
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
         new_data.add(ttltesM);
         new_data.add(ttltesF);
         new_data.add(total);
 
        return new_data;
    }
  public ArrayList hivPositiveSpecial(ResultSet rset, String age_set) throws SQLException{
      //2
        int HV0110,HV0111,HV0112,HV0113,HV0114,HV0115,HV0116,HV0206,total;
         HV0110=HV0111=HV0112=HV0113=HV0114=HV0115=HV0116=HV0206=total=0;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        
        HV0116 = rset.getInt("HV0116");
        HV0206 = rset.getInt("HV0206");       
        total = rset.getInt("total");
        
        HV0110 = rset.getInt("HV0110");
        HV0111 = rset.getInt("HV0111");
        HV0112 = rset.getInt("HV0112");
        HV0113 = rset.getInt("HV0113");
        HV0114 = rset.getInt("HV0114");
        HV0115 = rset.getInt("HV0115");
       
 //===HTC 731==============
        
        //initialize
        
        
        float f_731_1,f_731_4,f_731_9,f_731_14,f_731_19,f_731_24,f_731_49,f_731_50;	
        float m_731_1,m_731_4,m_731_9,m_731_14,m_731_19,m_731_24,m_731_49,m_731_50 ;
        float f_pmtct_1,f_pmtct_4,f_pmtct_9,f_pmtct_14,f_pmtct_19,f_pmtct_24,f_pmtct_49,f_pmtct_50; 
        double ttlposM_731=0;   
        double ttlposF_731=0; 
        
        float ttlM=0;
        float ttlF=0;
       //female 
     //female 45% of HV0111	25% of HV0111	30% of HV0111	23%  of HV0113	77% of HV0113	90% of HV0115	10% of HV0115

        f_731_1=(float)Math.round((0*HV0111));
        f_731_4=(float)Math.round((0.45*HV0111));
        f_731_9=(float)Math.round((0.25*HV0111));
        f_731_14=(float)Math.round((0.30*HV0111));
        
        f_731_19=(float)Math.round((0.23*HV0113));
        f_731_24=(float)Math.round((0.77*HV0113));
        f_731_49=(float)Math.round((0.90*HV0115));
        f_731_50=(float)Math.round((0.10*HV0115));

      
        
        
        //male 
      // male 37% of HV0110	27% of HV0110	36% of HV0110	36% of HV0112	64%  of HV0112	79% of HV0114	21% of HV0114
        
  m_731_1=(float)Math.round((0*HV0110));
  m_731_4=(float)Math.round((0.37*HV0110));
  m_731_9=(float)Math.round((0.27*HV0110));
  m_731_14=(float)Math.round((0.36*HV0110));
  
  m_731_19=(float)Math.round((0.36*HV0112));
  m_731_24=(float)Math.round((0.64*HV0112));
  m_731_49=(float)Math.round((0.79*HV0114));
  m_731_50=(float)Math.round((0.21*HV0114));

  float HIV_AdultFemale=HV0113+HV0115;
 
  float splitData=0;  
   float adderPos=0;
  float  childSplitData=0;   
//   // adult female hiv+
splitData=f_731_19+f_731_24+f_731_49+f_731_50 ;
//

 adderPos=0;
 
 while(splitData<HIV_AdultFemale){ 
 f_731_49+=1; 
 splitData++; 
}
 
splitData=f_731_19+f_731_24+f_731_49+f_731_50;
 while(splitData>HIV_AdultFemale){ 
 f_731_49-=1; 
 splitData--;
}
  
 
 
 
 splitData=m_731_19+m_731_24+m_731_49+m_731_50 ;
 
  float HIV_AdultMale=HV0112+HV0114;
 
  adderPos=0;
 while(splitData<HIV_AdultMale){ 
 m_731_49+=1; 
 splitData++;
}
 
splitData=m_731_19+m_731_24+m_731_49+m_731_50 ;
 adderPos=0;
 while(splitData>HIV_AdultMale){ 
 m_731_49-=1; 
 splitData--;
                               }
 
 
 
 //children
 //female child
 
  childSplitData=f_731_1+f_731_4+f_731_9+f_731_14; 
 
  float HIV_ChildFemale=HV0111;
 while(childSplitData<HIV_ChildFemale){ 
 f_731_4+=1; 
 childSplitData++;
}
 
childSplitData=f_731_1+f_731_4+f_731_9+f_731_14; 

 while(childSplitData>HIV_ChildFemale){ 
 f_731_4-=1; 
 childSplitData--;
                               }
 
 
 //male child
 
 childSplitData=m_731_1+m_731_4+m_731_9+m_731_14; 
 
  float HIV_ChildMale=HV0110;
 while(childSplitData<HIV_ChildMale){ 
 m_731_4+=1; 
 childSplitData++;
}
 
childSplitData=m_731_1+m_731_4+m_731_9+m_731_14; 

 while(childSplitData>HIV_ChildMale){ 
 m_731_4-=1; 
 childSplitData--;
                               }
 
 
 
 
 
	   //======end of htc from 731=========


//--------------pmtct tested-----------------
//,,,,,,,; 
            f_pmtct_1= HV0206*0;           
            f_pmtct_4= HV0206*0;            
            f_pmtct_9= HV0206*0;         
            f_pmtct_14=HV0206*0;            
            f_pmtct_19=(float)(HV0206*0.05);            
            f_pmtct_24=(float)(HV0206*0.26);            
            f_pmtct_49= (float)(HV0206*0.69);
            f_pmtct_50= (float)(HV0206*0);
           

float pmtctttl=f_pmtct_1+f_pmtct_4+f_pmtct_9+f_pmtct_14+f_pmtct_19+f_pmtct_24+f_pmtct_49+f_pmtct_50;
          
float ancdiff=HV0206-pmtctttl;

         while(ancdiff>0){
       
         //deduct from anctested
           f_pmtct_49=f_pmtct_49+1; 
           ancdiff--;
        
     }
     while(ancdiff<0){       
       
             //deduct from main number contributing the ratios             
         f_pmtct_49=f_pmtct_49-1;         
       ancdiff++;
     }
            


        m_1=(int)(m_731_1);
        m_4 = (int) (m_731_4);
        m_9 = (int) (m_731_9);
        m_14 = (int) (m_731_14);                
        m_19 = (int) (m_731_19);
        m_24 = (int) (m_731_24);
        m_49 = (int) (m_731_49);
        m_50 = (int) (m_731_50); 
        
        f_1=(int) (f_731_1+f_pmtct_1);
        
        f_4 = (int) (f_731_4+f_pmtct_4);
        f_9 = (int) (f_731_9+f_pmtct_9);
        f_14 = (int) (f_731_14+f_pmtct_14);
                
        f_19 = (int) (f_731_14+f_pmtct_14);
        f_24 = (int) (f_731_24+f_pmtct_24);
        f_49 = (int) (f_731_49+f_pmtct_49);
        f_50 = (int) (f_731_50+f_pmtct_50);
        
//        normalization
//        
       
//        male 1-14

        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
        
         
         ttlF=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
         ttlM=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        
//        Hashmap array
//        Datim ages
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
         new_data.add(ttlM);
         new_data.add(ttlF);
         new_data.add(total);
 
        return new_data;
    }
  
   public ArrayList hivPositiveOld731(ResultSet rset, String age_set) throws SQLException{
      //2
        int HV0110,HV0111,HV0112,HV0113,HV0114,HV0115,HV0116,total;
        HV0110=HV0111=HV0112=HV0113=HV0114=HV0115=HV0116=total=0;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        
        HV0116 = rset.getInt("total");
        HV0110 = rset.getInt("HV0110");
        HV0111 = rset.getInt("HV0111");
        HV0112 = rset.getInt("HV0112");
        HV0113 = rset.getInt("HV0113");
        HV0114 = rset.getInt("HV0114");
        HV0115 = rset.getInt("HV0115");
             
        total = rset.getInt("total");
       
 //===HTC 731==============
        
        //initialize
        
        float f_731_1,f_731_4,f_731_9,f_731_14,f_731_19,f_731_24,f_731_49,f_731_50;	
        float m_731_1,m_731_4,m_731_9,m_731_14,m_731_19,m_731_24,m_731_49,m_731_50 ;
        double ttlposM_731=0;   
        double ttlposF_731=0; 
        
        float ttlM=0;
        float ttlF=0;
       //female 
     //female 45% of HV0111	25% of HV0111	30% of HV0111	23%  of HV0113	77% of HV0113	90% of HV0115	10% of HV0115

        f_731_1=(float)Math.round((0*HV0111));
        f_731_4=(float)Math.round((0.45*HV0111));
        f_731_9=(float)Math.round((0.25*HV0111));
        f_731_14=(float)Math.round((0.30*HV0111));
        
        f_731_19=(float)Math.round((0.23*HV0113));
        f_731_24=(float)Math.round((0.77*HV0113));
        f_731_49=(float)Math.round((0.90*HV0115));
        f_731_50=(float)Math.round((0.10*HV0115));

      
        
        
        //male 
      // male 37% of HV0110	27% of HV0110	36% of HV0110	36% of HV0112	64%  of HV0112	79% of HV0114	21% of HV0114
        
  m_731_1=(float)Math.round((0*HV0110));
  m_731_4=(float)Math.round((0.37*HV0110));
  m_731_9=(float)Math.round((0.27*HV0110));
  m_731_14=(float)Math.round((0.36*HV0110));
  
  m_731_19=(float)Math.round((0.36*HV0112));
  m_731_24=(float)Math.round((0.64*HV0112));
  m_731_49=(float)Math.round((0.79*HV0114));
  m_731_50=(float)Math.round((0.21*HV0114));

  float HIV_AdultFemale=HV0113+HV0115;
 
  float splitData=0;  
   float adderPos=0;
  float  childSplitData=0;   
//   // adult female hiv+
splitData=f_731_19+f_731_24+f_731_49+f_731_50 ;
//

 adderPos=0;
 
 while(splitData<HIV_AdultFemale){ 
 f_731_49+=1; 
 splitData++; 
}
 
splitData=f_731_19+f_731_24+f_731_49+f_731_50;
 while(splitData>HIV_AdultFemale){ 
 f_731_49-=1; 
 splitData--;
}
  
 
 
 
 splitData=m_731_19+m_731_24+m_731_49+m_731_50 ;
 
  float HIV_AdultMale=HV0112+HV0114;
 
  adderPos=0;
 while(splitData<HIV_AdultMale){ 
 m_731_49+=1; 
 splitData++;
}
 
splitData=m_731_19+m_731_24+m_731_49+m_731_50 ;
 adderPos=0;
 while(splitData>HIV_AdultMale){ 
 m_731_49-=1; 
 splitData--;
                               }
 
 
 
 //children
 //female child
 
  childSplitData=f_731_1+f_731_4+f_731_9+f_731_14; 
 
  float HIV_ChildFemale=HV0111;
 while(childSplitData<HIV_ChildFemale){ 
 f_731_4+=1; 
 childSplitData++;
}
 
childSplitData=f_731_1+f_731_4+f_731_9+f_731_14; 

 while(childSplitData>HIV_ChildFemale){ 
 f_731_4-=1; 
 childSplitData--;
                               }
 
 
 //male child
 
 childSplitData=m_731_1+m_731_4+m_731_9+m_731_14; 
 
  float HIV_ChildMale=HV0110;
 while(childSplitData<HIV_ChildMale){ 
 m_731_4+=1; 
 childSplitData++;
}
 
childSplitData=m_731_1+m_731_4+m_731_9+m_731_14; 

 while(childSplitData>HIV_ChildMale){ 
 m_731_4-=1; 
 childSplitData--;
                               }
 
 
 
 
 
	   //======end of htc from 731=========


           
      


        m_1=(int)(m_731_1);
        m_4 = (int) (m_731_4);
        m_9 = (int) (m_731_9);
        m_14 = (int) (m_731_14);                
        m_19 = (int) (m_731_19);
        m_24 = (int) (m_731_24);
        m_49 = (int) (m_731_49);
        m_50 = (int) (m_731_50); 
        
        f_1=(int) (f_731_1);
        
        f_4 = (int) (f_731_4);
        f_9 = (int) (f_731_9);
        f_14 = (int) (f_731_14);
        f_19 = (int) (f_731_14);
        f_24 = (int) (f_731_24);
        f_49 = (int) (f_731_49);
        f_50 = (int) (f_731_50);
        
//        normalization
//        
       
//        male 1-14

        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
        
         
         ttlF=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
         ttlM=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        
//        Hashmap array
//        Datim ages
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
         new_data.add(ttlM);
         new_data.add(ttlF);
         new_data.add(total);
 
        return new_data;
    }
  
  
  public ArrayList pmtctAncPositive(ResultSet rset, String age_set) throws SQLException{
      //5,17
        int HV0206,total;
       HV0206=total=0;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        
     
        HV0206 = rset.getInt("HV0206");       
        total = rset.getInt("HV0206");
       
 //===HTC 731==============
        
        //initialize
        
        
          float f_731_1,f_731_4,f_731_9,f_731_14,f_731_19,f_731_24,f_731_49,f_731_50;	
        float m_731_1,m_731_4,m_731_9,m_731_14,m_731_19,m_731_24,m_731_49,m_731_50 ;
        float f_pmtct_1,f_pmtct_4,f_pmtct_9,f_pmtct_14,f_pmtct_19,f_pmtct_24,f_pmtct_49,f_pmtct_50; 
        double ttlposM_731=0;   
        double ttlposF_731=0; 
        
        float ttlM=0;
        float ttlF=0;
       //female 
   
      m_731_1=m_731_4=m_731_9=m_731_14=m_731_19=m_731_24=m_731_49=m_731_50=0;
      f_731_1=f_731_4=f_731_9=f_731_14=f_731_19=f_731_24=f_731_49=f_731_50=0;
        
        
  

  
 
	   //======end of htc from 731=========


//--------------pmtct tested-----------------
//,,,,,,,; 
            f_pmtct_1= HV0206*0;           
            f_pmtct_4= HV0206*0;            
            f_pmtct_9= HV0206*0;         
            f_pmtct_14=HV0206*0;            
            f_pmtct_19=(float)(HV0206*0.05);            
            f_pmtct_24=(float)(HV0206*0.26);            
            f_pmtct_49= (float)(HV0206*0.69);
            f_pmtct_50= (float)(HV0206*0);
           

float pmtctttl=f_pmtct_1+f_pmtct_4+f_pmtct_9+f_pmtct_14+f_pmtct_19+f_pmtct_24+f_pmtct_49+f_pmtct_50;
          
float ancdiff=HV0206-pmtctttl;

         while(ancdiff>0){
       
         //deduct from anctested
           f_pmtct_49=f_pmtct_49+1; 
           ancdiff--;
        
     }
     while(ancdiff<0){       
       
             //deduct from main number contributing the ratios             
         f_pmtct_49=f_pmtct_49-1;         
       ancdiff++;
     }
            


        m_1=(int)(m_731_1);
        m_4 = (int) (m_731_4);
        m_9 = (int) (m_731_9);
        m_14 = (int) (m_731_14);                
        m_19 = (int) (m_731_19);
        m_24 = (int) (m_731_24);
        m_49 = (int) (m_731_49);
        m_50 = (int) (m_731_50); 
        
        f_1=(int) (f_731_1+f_pmtct_1);
        
        f_4 = (int) (f_731_4+f_pmtct_4);
        f_9 = (int) (f_731_9+f_pmtct_9);
        f_14 = (int) (f_731_14+f_pmtct_14);
                
        f_19 = (int) (f_731_14+f_pmtct_14);
        f_24 = (int) (f_731_24+f_pmtct_24);
        f_49 = (int) (f_731_49+f_pmtct_49);
        f_50 = (int) (f_731_50+f_pmtct_50);
        
//        normalization
//        
       
//        male 1-14

        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
        
         
         ttlF=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
         ttlM=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        
//        Hashmap array
//        Datim ages
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
         new_data.add(ttlM);
         new_data.add(ttlF);
         new_data.add(total);
 
        return new_data;
    }
          
  public ArrayList pmtctStatTested(ResultSet rset, String age_set) throws SQLException{
     //16
        int HV0201,HV0205,total;
        HV0201=HV0205=total=0;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        
     
        HV0201 = rset.getInt("HV0201");       
        HV0205 = rset.getInt("HV0201");       
        total = rset.getInt("total");
       
 //===HTC 731==============
        
        //initialize
        
        
          float f_731_1,f_731_4,f_731_9,f_731_14,f_731_19,f_731_24,f_731_49,f_731_50;	
        float m_731_1,m_731_4,m_731_9,m_731_14,m_731_19,m_731_24,m_731_49,m_731_50 ;
        float f_pmtct_1,f_pmtct_4,f_pmtct_9,f_pmtct_14,f_pmtct_19,f_pmtct_24,f_pmtct_49,f_pmtct_50; 
        double ttlposM_731=0;   
        double ttlposF_731=0; 
        
        float ttlM=0;
        float ttlF=0;
       //female 
   
      m_731_1=m_731_4=m_731_9=m_731_14=m_731_19=m_731_24=m_731_49=m_731_50=0;
      f_731_1=f_731_4=f_731_9=f_731_14=f_731_19=f_731_24=f_731_49=f_731_50=0;
        
        
  

  

//--------------pmtct tested-----------------
//,,,,,,,; 
             f_pmtct_1=total*0;           
             f_pmtct_4=total*0;            
             f_pmtct_9=total*0;         
            f_pmtct_14=(float)(total*0.01);            
            f_pmtct_19=(float)(total*0.07);            
            f_pmtct_24=(float)(total*0.29);            
            f_pmtct_49=(float)(total*0.63);
            f_pmtct_50=(float)(total*0);
           
           
            

float pmtctttl=f_pmtct_1+f_pmtct_4+f_pmtct_9+f_pmtct_14+f_pmtct_19+f_pmtct_24+f_pmtct_49+f_pmtct_50;
          
float ancdiff=total-pmtctttl;

         while(ancdiff>0){
       
         //deduct from anctested
           f_pmtct_49=f_pmtct_49+1; 
           ancdiff--;
        
     }
     while(ancdiff<0){       
       
             //deduct from main number contributing the ratios             
         f_pmtct_49=f_pmtct_49-1;         
       ancdiff++;
     }
            


        m_1=(int)(m_731_1);
        m_4 = (int) (m_731_4);
        m_9 = (int) (m_731_9);
        m_14 = (int) (m_731_14);                
        m_19 = (int) (m_731_19);
        m_24 = (int) (m_731_24);
        m_49 = (int) (m_731_49);
        m_50 = (int) (m_731_50); 
        
        f_1=(int) (f_731_1+f_pmtct_1);
        
        f_4 = (int) (f_731_4+f_pmtct_4);
        f_9 = (int) (f_731_9+f_pmtct_9);
        f_14 = (int) (f_731_14+f_pmtct_14);
                
        f_19 = (int) (f_731_14+f_pmtct_14);
        f_24 = (int) (f_731_24+f_pmtct_24);
        f_49 = (int) (f_731_49+f_pmtct_49);
        f_50 = (int) (f_731_50+f_pmtct_50);
        
//        normalization
//        
       
//        male 1-14

        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
        
         
         ttlF=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
         ttlM=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        
//        Hashmap array
//        Datim ages
        ArrayList new_data = new ArrayList();
        if(age_set.contains("datim")){
        
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
         new_data.add(ttlM);
         new_data.add(ttlF);
         new_data.add(total);
 
        return new_data;
    }         
          
  public ArrayList pmtctEIDTested(ResultSet rset, String age_set) throws SQLException{
       //18
       
        int mless1,fless1,total,no_gender_1;
        mless1=fless1=total=no_gender_1=0;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        
     
        mless1 = rset.getInt("m_1");       
        fless1 = rset.getInt("f_1");       
        no_gender_1 = rset.getInt("no_gender_1");       
        total = rset.getInt("total");
       
 //===HTC 731==============
        
        //initialize
        
        
        float f_731_1,f_731_4,f_731_9,f_731_14,f_731_19,f_731_24,f_731_49,f_731_50;	
        float m_731_1,m_731_4,m_731_9,m_731_14,m_731_19,m_731_24,m_731_49,m_731_50 ;
        
        double ttlposM_731=0;   
        double ttlposF_731=0; 
        
        float ttlM=0;
        float ttlF=0;
       //female 
   
      m_731_1=m_731_4=m_731_9=m_731_14=m_731_19=m_731_24=m_731_49=m_731_50=0;
      f_731_1=f_731_4=f_731_9=f_731_14=f_731_19=f_731_24=f_731_49=f_731_50=0;
              
  

 

//--------------pmtct eid-----------------
  


        m_1=(int)(mless1);
        m_4 = 0;
        m_9 = 0;
        m_14 =0;                
        m_19 = 0;
        m_24 = 0;
        m_49 = 0;
        m_50 = 0; 
        
        f_1=(int) (fless1);
        
        f_4 = 0;
        f_9 = 0;
        f_14 = 0;
                
        f_19 = 0;
        f_24 = 0;
        f_49 = 0;
        f_50 = 0;
        
//        normalization
//        
       
//        male 1-14

        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
        
         
         ttlF=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
         ttlM=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        
//        Hashmap array
//        Datim ages
        ArrayList new_data = new ArrayList();
        
        if(age_set.contains("datim")){
        
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
         new_data.add(ttlM);
         new_data.add(ttlF);
         new_data.add(total);
 
        return new_data;
    }         
           
  public ArrayList tbPositive(ResultSet rset, String age_set) throws SQLException{
       //8
        int total;
        total=0;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
        
     
              
        total = rset.getInt("total");
       
 //===HTC 731==============
        
        //initialize
        
     
        double ttlposM_731=0;   
        double ttlposF_731=0; 
        
        float ttlM=0;
        float ttlF=0;
    

//--------------pmtct eid-----------------
  


        m_1=(int)(rset.getInt("m_1"));
        m_4 = (int)(rset.getInt("m_4"));
        m_9 = (int)(rset.getInt("m_9"));
        m_14 =(int)(rset.getInt("m_14"));               
        m_19 = (int)(rset.getInt("m_19"));
        m_24 = (int)(rset.getInt("m_24"));
        m_49 = (int)(rset.getInt("m_49"));
        m_50 = (int)(rset.getInt("m_50"));
        
        f_1=(int)(rset.getInt("f_1"));
        
        f_4 = (int)(rset.getInt("f_4"));
        f_9 = (int)(rset.getInt("f_9"));
        f_14 = (int)(rset.getInt("f_14"));
                
        f_19 = (int)(rset.getInt("f_19"));
        f_24 = (int)(rset.getInt("f_24"));
        f_49 = (int)(rset.getInt("f_49"));
        f_50 = (int)(rset.getInt("f_50"));
        
//        normalization
//        
       
//        male 1-14

        
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
        
         
         ttlF=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
         ttlM=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        
//        Hashmap array
//        Datim ages
        ArrayList new_data = new ArrayList();
        
        if(age_set.contains("datim")){
        
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
         new_data.add(ttlM);
         new_data.add(ttlF);
         new_data.add(total);
 
        return new_data;
    }         
            
  public ArrayList tbStatTested(ResultSet rset, String age_set) throws SQLException{
      //9
        int total;
        total=0;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
            
        total = rset.getInt("total");
       
 //===HTC 731==============
        
        float ttlM=0;
        float ttlF=0;
    
//--------------pmtct eid-----------------
  
        m_1=(int)(rset.getInt("m_1"));
        m_4 = (int)(rset.getInt("m_4"));
        m_9 = (int)(rset.getInt("m_9"));
        m_14 =(int)(rset.getInt("m_14"));               
        m_19 = (int)(rset.getInt("m_19"));
        m_24 = (int)(rset.getInt("m_24"));
        m_49 = (int)(rset.getInt("m_49"));
        m_50 = (int)(rset.getInt("m_50"));
        
        f_1=(int)(rset.getInt("f_1"));
        
        f_4 = (int)(rset.getInt("f_4"));
        f_9 = (int)(rset.getInt("f_9"));
        f_14 = (int)(rset.getInt("f_14"));
                
        f_19 = (int)(rset.getInt("f_19"));
        f_24 = (int)(rset.getInt("f_24"));
        f_49 = (int)(rset.getInt("f_49"));
        f_50 = (int)(rset.getInt("f_50"));
        
    
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
        
         
         ttlF=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
         ttlM=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        

//        Datim ages
        ArrayList new_data = new ArrayList();
        
        if(age_set.contains("datim")){
        
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
         new_data.add(ttlM);
         new_data.add(ttlF);
         new_data.add(total);
 
        return new_data;
    }         
            
  public ArrayList tbHivPositiveOnART(ResultSet rset, String age_set) throws SQLException{
       //10
        int total;
        total=0;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
            
        total = rset.getInt("total");
       
 //===HTC 731==============
        
        float ttlM=0;
        float ttlF=0;
    
//--------------pmtct eid-----------------
  
        m_1=(int)(rset.getInt("m_1"));
        m_4 = (int)(rset.getInt("m_4"));
        m_9 = (int)(rset.getInt("m_9"));
        m_14 =(int)(rset.getInt("m_14"));               
        m_19 = (int)(rset.getInt("m_19"));
        m_24 = (int)(rset.getInt("m_24"));
        m_49 = (int)(rset.getInt("m_49"));
        m_50 = (int)(rset.getInt("m_50"));
        
        f_1=(int)(rset.getInt("f_1"));
        
        f_4 = (int)(rset.getInt("f_4"));
        f_9 = (int)(rset.getInt("f_9"));
        f_14 = (int)(rset.getInt("f_14"));
                
        f_19 = (int)(rset.getInt("f_19"));
        f_24 = (int)(rset.getInt("f_24"));
        f_49 = (int)(rset.getInt("f_49"));
        f_50 = (int)(rset.getInt("f_50"));
        
    
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
        
         
         ttlF=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
         ttlM=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        

//        Datim ages
        ArrayList new_data = new ArrayList();
        
        if(age_set.contains("datim")){
        
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
         new_data.add(ttlM);
         new_data.add(ttlF);
         new_data.add(total);
 
        return new_data;
    }         
    
  public ArrayList txTBTested(ResultSet rset, String age_set) throws SQLException{
       //12
        int total;
        total=0;
        int m_1=0,m_4=0,m_9=0,m_14=0,m_19=0,m_24=0,m_49=0,m_50=0,f_1=0,f_4=0,f_9=0,f_14=0,f_19=0,f_24=0,f_49=0,f_50=0;
        int m_0_14=0,m_10_19=0,m_15_50=0,m_24_35=0,f_0_14=0,f_10_19=0,f_15_50=0,f_24_35=0;
            
        total = rset.getInt("total");
       
 //===HTC 731==============
        
        float ttlM=0;
        float ttlF=0;
    
//--------------pmtct eid-----------------
  
        m_1=(int)(rset.getInt("m_1"));
        m_4 = (int)(rset.getInt("m_4"));
        m_9 = (int)(rset.getInt("m_9"));
        m_14 =(int)(rset.getInt("m_14"));               
        m_19 = (int)(rset.getInt("m_19"));
        m_24 = (int)(rset.getInt("m_24"));
        m_49 = (int)(rset.getInt("m_49"));
        m_50 = (int)(rset.getInt("m_50"));
        
        f_1=(int)(rset.getInt("f_1"));
        
        f_4 = (int)(rset.getInt("f_4"));
        f_9 = (int)(rset.getInt("f_9"));
        f_14 = (int)(rset.getInt("f_14"));
                
        f_19 = (int)(rset.getInt("f_19"));
        f_24 = (int)(rset.getInt("f_24"));
        f_49 = (int)(rset.getInt("f_49"));
        f_50 = (int)(rset.getInt("f_50"));
        
    
        m_0_14=m_1+m_4+m_9+m_14;
        m_10_19=m_14+m_19;
        m_15_50=m_19+m_24+m_49+m_50;
        m_24_35=m_49;
                
        f_0_14=f_1+f_4+f_9+f_14;
        f_10_19=f_14+f_19;
        f_15_50=f_19+f_24+f_49+f_50;
        f_24_35=f_49;
        
        
         
         ttlF=f_1+f_4+f_9+f_14+f_19+f_24+f_49+f_50;
         ttlM=m_1+m_4+m_9+m_14+m_19+m_24+m_49+m_50;
        

//        Datim ages
        ArrayList new_data = new ArrayList();
        
        if(age_set.contains("datim")){
        
        new_data.add(m_1);
        new_data.add(m_4);
        new_data.add(m_9);
        new_data.add(m_14);
        new_data.add(m_19);
        new_data.add(m_24);
        new_data.add(m_49);
        new_data.add(m_50);
        new_data.add(f_1);
        new_data.add(f_4);
        new_data.add(f_9);
        new_data.add(f_14);
        new_data.add(f_19);
        new_data.add(f_24);
        new_data.add(f_49);
        new_data.add(f_50);
        }
        
        if(age_set.contains("special")){
        new_data.add(m_0_14);
        new_data.add(m_10_19);
        new_data.add(m_15_50);
        new_data.add(m_24_35);
        new_data.add(f_0_14);
        new_data.add(f_10_19);
        new_data.add(f_15_50);
        new_data.add(f_24_35);
        }
         new_data.add(ttlM);
         new_data.add(ttlF);
         new_data.add(total);
 
        return new_data;
    }         
   
}
          