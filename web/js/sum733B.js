/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function NumberOfClientsServed(elem){
       
      var    CS_New_Pos_Male_Adult  = document.getElementById("CS_New_Pos_Male_Adult").value;
      var    CS_New_Pos_Male_15_17  = document.getElementById("CS_New_Pos_Male_15_17").value;
      var    CS_New_Pos_Male_Postnatal  = document.getElementById("CS_New_Pos_Male_Postnatal").value;
      var    CS_New_Pos_Male_0_59M  = document.getElementById("CS_New_Pos_Male_0_59M").value;
      var    CS_New_Pos_Male_5_15  = document.getElementById("CS_New_Pos_Male_5_15").value;
      var    CS_New_Pos_Female_Adult  = document.getElementById("CS_New_Pos_Female_Adult").value;
      var    CS_New_Pos_Female_15_17  = document.getElementById("CS_New_Pos_Female_15_17").value;
      var    CS_New_Pos_Female_Postnatal  = document.getElementById("CS_New_Pos_Female_Postnatal").value;
      var    CS_New_Pos_Female_0_59M  = document.getElementById("CS_New_Pos_Female_0_59M").value;
      var    CS_New_Pos_Female_5_15  = document.getElementById("CS_New_Pos_Female_5_15").value;
      var    CS_New_Pos_Readmission  = document.getElementById("CS_New_Pos_Readmission").value;
      var    CS_New_Pos_Relapse  = document.getElementById("CS_New_Pos_Relapse").value;
      var    CS_New_Pos_LinkedOVC  = document.getElementById("CS_New_Pos_LinkedOVC").value;
      var    CS_New_Neg_Male_Adult  = document.getElementById("CS_New_Neg_Male_Adult").value;
      var    CS_New_Neg_Male_15_17  = document.getElementById("CS_New_Neg_Male_15_17").value;
      var    CS_New_Neg_Male_Postnatal  = document.getElementById("CS_New_Neg_Male_Postnatal").value;
      var    CS_New_Neg_Male_0_59M  = document.getElementById("CS_New_Neg_Male_0_59M").value;
      var    CS_New_Neg_Male_5_15  = document.getElementById("CS_New_Neg_Male_5_15").value;
      var    CS_New_Neg_Female_Adult  = document.getElementById("CS_New_Neg_Female_Adult").value;
      var    CS_New_Neg_Female_15_17  = document.getElementById("CS_New_Neg_Female_15_17").value;
      var    CS_New_Neg_Female_Postnatal  = document.getElementById("CS_New_Neg_Female_Postnatal").value;
      var    CS_New_Neg_Female_0_59M  = document.getElementById("CS_New_Neg_Female_0_59M").value;
      var    CS_New_Neg_Female_5_15  = document.getElementById("CS_New_Neg_Female_5_15").value;
      var    CS_New_Neg_Readmission  = document.getElementById("CS_New_Neg_Readmission").value;
      var    CS_New_Neg_Relapse  = document.getElementById("CS_New_Neg_Relapse").value;
      var    CS_New_Neg_LinkedOVC  = document.getElementById("CS_New_Neg_LinkedOVC").value;
      var    CS_Revisit_Pos_Adult  = document.getElementById("CS_Revisit_Pos_Adult").value;
      var    CS_Revisit_Pos_15_17  = document.getElementById("CS_Revisit_Pos_15_17").value;
      var    CS_Revisit_Pos_Postnatal  = document.getElementById("CS_Revisit_Pos_Postnatal").value;
      var    CS_Revisit_Pos_0_59M  = document.getElementById("CS_Revisit_Pos_0_59M").value;
      var    CS_Revisit_Pos_5_15  = document.getElementById("CS_Revisit_Pos_5_15").value;
//      var    CS_Revisit_Pos_Readmission  = document.getElementById("CS_Revisit_Pos_Readmission").value;
//      var    CS_Revisit_Pos_Relapse  = document.getElementById("CS_Revisit_Pos_Relapse").value;
//      var    CS_Revisit_Pos_LinkedOVC  = document.getElementById("CS_Revisit_Pos_LinkedOVC").value;
      var    CS_Revisit_Neg_Adult  = document.getElementById("CS_Revisit_Neg_Adult").value;
      var    CS_Revisit_Neg_15_17  = document.getElementById("CS_Revisit_Neg_15_17").value;
      var    CS_Revisit_Neg_Postnatal  = document.getElementById("CS_Revisit_Neg_Postnatal").value;
      var    CS_Revisit_Neg_0_59M  = document.getElementById("CS_Revisit_Neg_0_59M").value;
      var    CS_Revisit_Neg_5_15  = document.getElementById("CS_Revisit_Neg_5_15").value;
//      var    CS_Revisit_Neg_Readmission  = document.getElementById("CS_Revisit_Neg_Readmission").value;
//      var    CS_Revisit_Neg_Relapse  = document.getElementById("CS_Revisit_Neg_Relapse").value;
//      var    CS_Revisit_Neg_LinkedOVC  = document.getElementById("CS_Revisit_Neg_LinkedOVC").value;
      
      
      
      if(CS_New_Pos_Male_Adult==""){CS_New_Pos_Male_Adult=0;}
      if(CS_New_Pos_Male_15_17==""){CS_New_Pos_Male_15_17=0;}
      if(CS_New_Pos_Male_Postnatal==""){CS_New_Pos_Male_Postnatal=0;}
      if(CS_New_Pos_Male_0_59M==""){CS_New_Pos_Male_0_59M=0;}
      if(CS_New_Pos_Male_5_15==""){CS_New_Pos_Male_5_15=0;}
      if(CS_New_Pos_Female_Adult==""){CS_New_Pos_Female_Adult=0;}
      if(CS_New_Pos_Female_15_17==""){CS_New_Pos_Female_15_17=0;}
      if(CS_New_Pos_Female_Postnatal==""){CS_New_Pos_Female_Postnatal=0;}
      if(CS_New_Pos_Female_0_59M==""){CS_New_Pos_Female_0_59M=0;}
      if(CS_New_Pos_Female_5_15==""){CS_New_Pos_Female_5_15=0;}
      if(CS_New_Pos_Readmission==""){CS_New_Pos_Readmission=0;}
      if(CS_New_Pos_Relapse==""){CS_New_Pos_Relapse=0;}
      if(CS_New_Pos_LinkedOVC==""){CS_New_Pos_LinkedOVC=0;}
      if(CS_New_Neg_Male_Adult==""){CS_New_Neg_Male_Adult=0;}
      if(CS_New_Neg_Male_15_17==""){CS_New_Neg_Male_15_17=0;}
      if(CS_New_Neg_Male_Postnatal==""){CS_New_Neg_Male_Postnatal=0;}
      if(CS_New_Neg_Male_0_59M==""){CS_New_Neg_Male_0_59M=0;}
      if(CS_New_Neg_Male_5_15==""){CS_New_Neg_Male_5_15=0;}
      if(CS_New_Neg_Female_Adult==""){CS_New_Neg_Female_Adult=0;}
      if(CS_New_Neg_Female_15_17==""){CS_New_Neg_Female_15_17=0;}
      if(CS_New_Neg_Female_Postnatal==""){CS_New_Neg_Female_Postnatal=0;}
      if(CS_New_Neg_Female_0_59M==""){CS_New_Neg_Female_0_59M=0;}
      if(CS_New_Neg_Female_5_15==""){CS_New_Neg_Female_5_15=0;}
      if(CS_New_Neg_Readmission==""){CS_New_Neg_Readmission=0;}
      if(CS_New_Neg_Relapse==""){CS_New_Neg_Relapse=0;}
      if(CS_New_Neg_LinkedOVC==""){CS_New_Neg_LinkedOVC=0;}
      if(CS_Revisit_Pos_Adult==""){CS_Revisit_Pos_Adult=0;}
      if(CS_Revisit_Pos_15_17==""){CS_Revisit_Pos_15_17=0;}
      if(CS_Revisit_Pos_Postnatal==""){CS_Revisit_Pos_Postnatal=0;}
      if(CS_Revisit_Pos_0_59M==""){CS_Revisit_Pos_0_59M=0;}
      if(CS_Revisit_Pos_5_15==""){CS_Revisit_Pos_5_15=0;}
//      if(CS_Revisit_Pos_Readmission==""){CS_Revisit_Pos_Readmission=0;}
//      if(CS_Revisit_Pos_Relapse==""){CS_Revisit_Pos_Relapse=0;}
//      if(CS_Revisit_Pos_LinkedOVC==""){CS_Revisit_Pos_LinkedOVC=0;}
      if(CS_Revisit_Neg_Adult==""){CS_Revisit_Neg_Adult=0;}
      if(CS_Revisit_Neg_15_17==""){CS_Revisit_Neg_15_17=0;}
      if(CS_Revisit_Neg_Postnatal==""){CS_Revisit_Neg_Postnatal=0;}
      if(CS_Revisit_Neg_0_59M==""){CS_Revisit_Neg_0_59M=0;}
      if(CS_Revisit_Neg_5_15==""){CS_Revisit_Neg_5_15=0;}
//      if(CS_Revisit_Neg_Readmission==""){CS_Revisit_Neg_Readmission=0;}
//      if(CS_Revisit_Neg_Relapse==""){CS_Revisit_Neg_Relapse=0;}
//      if(CS_Revisit_Neg_LinkedOVC==""){CS_Revisit_Neg_LinkedOVC=0;}
      
      var    CS_Total_Adult,CS_Total_15_17,CS_Total_Postnatal,CS_Total_0_59M,
             CS_Total_5_15,CS_Total_Readmission,CS_Total_Relapse,CS_Total_LinkedOVC;
      
      
      CS_Total_Adult = parseInt(CS_New_Pos_Male_Adult)+parseInt(CS_New_Pos_Female_Adult)
              +parseInt(CS_New_Neg_Male_Adult)+parseInt(CS_New_Neg_Female_Adult)+
              parseInt(CS_Revisit_Pos_Adult)+parseInt(CS_Revisit_Neg_Adult);
      
      CS_Total_15_17 = parseInt(CS_New_Pos_Male_15_17)+parseInt(CS_New_Pos_Female_15_17)
              +parseInt(CS_New_Neg_Male_15_17)+parseInt(CS_New_Neg_Female_15_17)+
              parseInt(CS_Revisit_Pos_15_17)+parseInt(CS_Revisit_Neg_15_17);
      
      CS_Total_Postnatal = parseInt(CS_New_Pos_Male_Postnatal)+parseInt(CS_New_Pos_Female_Postnatal)
              +parseInt(CS_New_Neg_Male_Postnatal)+parseInt(CS_New_Neg_Female_Postnatal)+
              parseInt(CS_Revisit_Pos_Postnatal)+parseInt(CS_Revisit_Neg_Postnatal);
      
      CS_Total_0_59M = parseInt(CS_New_Pos_Male_0_59M)+parseInt(CS_New_Pos_Female_0_59M)
              +parseInt(CS_New_Neg_Male_0_59M)+parseInt(CS_New_Neg_Female_0_59M)+
              parseInt(CS_Revisit_Pos_0_59M)+parseInt(CS_Revisit_Neg_0_59M);
      
      CS_Total_5_15 = parseInt(CS_New_Pos_Male_5_15)+parseInt(CS_New_Pos_Female_5_15)
              +parseInt(CS_New_Neg_Male_5_15)+parseInt(CS_New_Neg_Female_5_15)+
              parseInt(CS_Revisit_Pos_5_15)+parseInt(CS_Revisit_Neg_5_15);
      
      CS_Total_Readmission = parseInt(CS_New_Pos_Readmission)+parseInt(CS_New_Neg_Readmission);
      
      CS_Total_Relapse = parseInt(CS_New_Pos_Relapse)+parseInt(CS_New_Neg_Relapse);
      
      CS_Total_LinkedOVC = parseInt(CS_New_Pos_LinkedOVC)+parseInt(CS_New_Neg_LinkedOVC);
      
      
      document.getElementById("CS_Total_Adult").value = CS_Total_Adult;
      document.getElementById("CS_Total_15_17").value = CS_Total_15_17;
      document.getElementById("CS_Total_Postnatal").value = CS_Total_Postnatal;
      document.getElementById("CS_Total_0_59M").value = CS_Total_0_59M;
      document.getElementById("CS_Total_5_15").value = CS_Total_5_15;
      document.getElementById("CS_Total_Readmission").value = CS_Total_Readmission;
      document.getElementById("CS_Total_Relapse").value = CS_Total_Relapse;
      document.getElementById("CS_Total_LinkedOVC").value = CS_Total_LinkedOVC;
      
     if(elem=="CS_New_Pos_Male_Adult" || elem=="CS_New_Pos_Female_Adult" || elem=="CS_New_Neg_Male_Adult" || elem=="CS_New_Neg_Female_Adult" || elem=="CS_Revisit_Pos_Adult" || elem=="CS_Revisit_Neg_Adult")
     {
         autosave('CS_Total_Adult');    
     }
     
    else if(elem=="" || elem=="" || elem=="CS_New_Pos_Male_15_17" || elem=="CS_New_Pos_Female_15_17" ||  elem=="CS_New_Neg_Male_15_17" ||  elem=="CS_New_Neg_Female_15_17" ||  elem=="CS_Revisit_Pos_15_17" ||  elem=="CS_Revisit_Neg_15_17")
    {
        autosave('CS_Total_15_17');
    }    
    else if(elem=="CS_New_Pos_Male_Postnatal" || elem=="CS_New_Pos_Female_Postnatal" ||  elem=="CS_New_Neg_Male_Postnatal" ||  elem=="CS_New_Neg_Female_Postnatal" ||  elem=="CS_Revisit_Pos_Postnatal" ||  elem=="CS_Revisit_Neg_Postnatal" )
    {
     autosave('CS_Total_Postnatal');   
    }    
    else if(elem=="CS_New_Pos_Male_0_59M" || elem=="CS_New_Pos_Female_0_59M" ||  elem=="CS_New_Neg_Male_0_59M" ||  elem=="CS_New_Neg_Female_0_59M" ||  elem=="CS_Revisit_Pos_0_59M" ||  elem=="CS_Revisit_Neg_0_59M" )
    {
             autosave('CS_Total_0_59M');   
    }   
    else if(elem=="CS_New_Pos_Male_5_15" || elem=="CS_New_Pos_Female_5_15" || elem=="CS_New_Neg_Male_5_15" || elem=="CS_New_Neg_Female_5_15" ||  elem=="CS_Revisit_Pos_5_15" ||  elem=="CS_Revisit_Neg_5_15"){
          autosave('CS_Total_5_15');    
    }
        
    else if(elem=="CS_New_Pos_Readmission" ||  elem=="CS_New_Neg_Readmission")
    {
          autosave('CS_Total_Readmission');    
    }   
    else if(elem=="CS_New_Pos_Relapse" ||  elem=="CS_New_Neg_Relapse")
    {
        autosave('CS_Total_Relapse');
    }
        
    else if(elem=="CS_New_Pos_LinkedOVC" ||  elem=="CS_New_Neg_LinkedOVC"){
      autosave('CS_Total_LinkedOVC');   
    }
  
}

function NH1(elem){
        var NH1_Pos_SAM_Adult  = document.getElementById("NH1_Pos_SAM_Adult").value;
        var NH1_Pos_SAM_15_17  = document.getElementById("NH1_Pos_SAM_15_17").value;
        var NH1_Pos_SAM_Postnatal  = document.getElementById("NH1_Pos_SAM_Postnatal").value;
        var NH1_Pos_SAM_0_59M  = document.getElementById("NH1_Pos_SAM_0_59M").value;
        var NH1_Pos_SAM_5_15  = document.getElementById("NH1_Pos_SAM_5_15").value;
        
        var NH1_Pos_MAM_Adult  = document.getElementById("NH1_Pos_MAM_Adult").value;
        var NH1_Pos_MAM_15_17  = document.getElementById("NH1_Pos_MAM_15_17").value;
        var NH1_Pos_MAM_Postnatal  = document.getElementById("NH1_Pos_MAM_Postnatal").value;
        var NH1_Pos_MAM_0_59M  = document.getElementById("NH1_Pos_MAM_0_59M").value;
        var NH1_Pos_MAM_5_15  = document.getElementById("NH1_Pos_MAM_5_15").value;
        
        var NH1_Neg_SAM_Adult  = document.getElementById("NH1_Neg_SAM_Adult").value;
        var NH1_Neg_SAM_15_17  = document.getElementById("NH1_Neg_SAM_15_17").value;
        var NH1_Neg_SAM_Postnatal  = document.getElementById("NH1_Neg_SAM_Postnatal").value;
        var NH1_Neg_SAM_0_59M  = document.getElementById("NH1_Neg_SAM_0_59M").value;
        var NH1_Neg_SAM_5_15  = document.getElementById("NH1_Neg_SAM_5_15").value;
        
        var NH1_Neg_MAM_Adult  = document.getElementById("NH1_Neg_MAM_Adult").value;
        var NH1_Neg_MAM_15_17  = document.getElementById("NH1_Neg_MAM_15_17").value;
        var NH1_Neg_MAM_Postnatal  = document.getElementById("NH1_Neg_MAM_Postnatal").value;
        var NH1_Neg_MAM_0_59M  = document.getElementById("NH1_Neg_MAM_0_59M").value;
        var NH1_Neg_MAM_5_15  = document.getElementById("NH1_Neg_MAM_5_15").value;
        
        
        
        if( NH1_Pos_SAM_Adult==""){ NH1_Pos_SAM_Adult=0;}
        if( NH1_Pos_SAM_15_17==""){ NH1_Pos_SAM_15_17=0;}
        if( NH1_Pos_SAM_Postnatal==""){ NH1_Pos_SAM_Postnatal=0;}
        if( NH1_Pos_SAM_0_59M==""){ NH1_Pos_SAM_0_59M=0;}
        if( NH1_Pos_SAM_5_15==""){ NH1_Pos_SAM_5_15=0;}
        
        
        if( NH1_Pos_MAM_Adult==""){ NH1_Pos_MAM_Adult=0;}
        if( NH1_Pos_MAM_15_17==""){ NH1_Pos_MAM_15_17=0;}
        if( NH1_Pos_MAM_Postnatal==""){ NH1_Pos_MAM_Postnatal=0;}
        if( NH1_Pos_MAM_0_59M==""){ NH1_Pos_MAM_0_59M=0;}
        if( NH1_Pos_MAM_5_15==""){ NH1_Pos_MAM_5_15=0;}
        
        if( NH1_Neg_SAM_Adult==""){ NH1_Neg_SAM_Adult=0;}
        if( NH1_Neg_SAM_15_17==""){ NH1_Neg_SAM_15_17=0;}
        if( NH1_Neg_SAM_Postnatal==""){ NH1_Neg_SAM_Postnatal=0;}
        if( NH1_Neg_SAM_0_59M==""){ NH1_Neg_SAM_0_59M=0;}
        if( NH1_Neg_SAM_5_15==""){ NH1_Neg_SAM_5_15=0;}
        
        if( NH1_Neg_MAM_Adult==""){ NH1_Neg_MAM_Adult=0;}
        if( NH1_Neg_MAM_15_17==""){ NH1_Neg_MAM_15_17=0;}
        if( NH1_Neg_MAM_Postnatal==""){ NH1_Neg_MAM_Postnatal=0;}
        if( NH1_Neg_MAM_0_59M==""){ NH1_Neg_MAM_0_59M=0;}
        if( NH1_Neg_MAM_5_15==""){ NH1_Neg_MAM_5_15=0;} 
        
        
        
        
        var NH1_Pos_SAM_Total  = parseInt(NH1_Pos_SAM_Adult)+parseInt(NH1_Pos_SAM_15_17)+parseInt(NH1_Pos_SAM_Postnatal)+parseInt(NH1_Pos_SAM_0_59M)+parseInt(NH1_Pos_SAM_5_15);
        var NH1_Pos_MAM_Total  = parseInt(NH1_Pos_MAM_Adult)+parseInt(NH1_Pos_MAM_15_17)+parseInt(NH1_Pos_MAM_Postnatal)+parseInt(NH1_Pos_MAM_0_59M)+parseInt(NH1_Pos_MAM_5_15);
        var NH1_Neg_SAM_Total  = parseInt(NH1_Neg_SAM_Adult)+parseInt(NH1_Neg_SAM_15_17)+parseInt(NH1_Neg_SAM_Postnatal)+parseInt(NH1_Neg_SAM_0_59M)+parseInt(NH1_Neg_SAM_5_15);
        var NH1_Neg_MAM_Total  = parseInt(NH1_Neg_MAM_Adult)+parseInt(NH1_Neg_MAM_15_17)+parseInt(NH1_Neg_MAM_Postnatal)+parseInt(NH1_Neg_MAM_0_59M)+parseInt(NH1_Neg_MAM_5_15);
        
        document.getElementById("NH1_Pos_SAM_Total").value = NH1_Pos_SAM_Total;        
        document.getElementById("NH1_Pos_MAM_Total").value = NH1_Pos_MAM_Total;
        document.getElementById("NH1_Neg_SAM_Total").value = NH1_Neg_SAM_Total;     
        document.getElementById("NH1_Neg_MAM_Total").value = NH1_Neg_MAM_Total;
    
        var NH1_Total_Adult,NH1_Total_15_17,NH1_Total_Postnatal,
                NH1_Total_0_59M,NH1_Total_5_15,NH1_Grand_Total;
        
        NH1_Total_Adult = parseInt(NH1_Pos_SAM_Adult)+parseInt(NH1_Pos_MAM_Adult)+parseInt(NH1_Neg_SAM_Adult)+parseInt(NH1_Neg_MAM_Adult);
        NH1_Total_15_17 = parseInt(NH1_Pos_SAM_15_17)+parseInt(NH1_Pos_MAM_15_17)+parseInt(NH1_Neg_SAM_15_17)+parseInt(NH1_Neg_MAM_15_17);
        NH1_Total_Postnatal = parseInt(NH1_Pos_SAM_Postnatal)+parseInt(NH1_Pos_MAM_Postnatal)+parseInt(NH1_Neg_SAM_Postnatal)+parseInt(NH1_Neg_MAM_Postnatal);
        NH1_Total_0_59M = parseInt(NH1_Pos_SAM_0_59M)+parseInt(NH1_Pos_MAM_0_59M)+parseInt(NH1_Neg_SAM_0_59M)+parseInt(NH1_Neg_MAM_0_59M);
        NH1_Total_5_15 = parseInt(NH1_Pos_SAM_5_15)+parseInt(NH1_Pos_MAM_5_15)+parseInt(NH1_Neg_SAM_5_15)+parseInt(NH1_Neg_MAM_5_15);
        
        NH1_Grand_Total = parseInt(NH1_Total_Adult)+parseInt(NH1_Total_15_17)+parseInt(NH1_Total_Postnatal)+parseInt(NH1_Total_0_59M)+parseInt(NH1_Total_5_15);
               
                document.getElementById("NH1_Total_Adult").value = NH1_Total_Adult;
                document.getElementById("NH1_Total_15_17").value = NH1_Total_15_17;
                document.getElementById("NH1_Total_Postnatal").value = NH1_Total_Postnatal;
                document.getElementById("NH1_Total_0_59M").value = NH1_Total_0_59M;
                document.getElementById("NH1_Total_5_15").value = NH1_Total_5_15;
                document.getElementById("NH1_Grand_Total").value = NH1_Grand_Total; 
        
       if(elem=="NH1_Pos_SAM_Adult"){
           autosave('NH1_Total_Adult');
           autosave('NH1_Pos_SAM_Total');
       }
        else if(elem=="NH1_Pos_SAM_15_17"){
            autosave('NH1_Total_15_17');
            autosave('NH1_Pos_SAM_Total');
        }
        else if(elem=="NH1_Pos_SAM_Postnatal"){
        autosave('NH1_Total_Postnatal');
        autosave('NH1_Pos_SAM_Total');
        }
        else if(elem=="NH1_Pos_SAM_0_59M"){
        autosave('NH1_Total_0_59M');  
        autosave('NH1_Pos_SAM_Total');
        }
        else if(elem=="NH1_Pos_SAM_5_15"){
        autosave('NH1_Total_5_15');  
        autosave('NH1_Pos_SAM_Total');
        }
        
        
        else if(elem=="NH1_Pos_MAM_Adult"){
            autosave('NH1_Total_Adult');
            autosave('NH1_Pos_MAM_Total');
        }
        else if(elem=="NH1_Pos_MAM_15_17"){
            autosave('NH1_Total_15_17');
            autosave('NH1_Pos_MAM_Total');
        }
        else if(elem=="NH1_Pos_MAM_Postnatal"){
            autosave('NH1_Pos_MAM_Total');
            autosave('NH1_Total_Postnatal');
        }
        else if(elem=="NH1_Pos_MAM_0_59M"){
         autosave('NH1_Total_0_59M'); 
         autosave('NH1_Pos_MAM_Total');
        }
        else if(elem=="NH1_Pos_MAM_5_15"){
         autosave('NH1_Total_5_15');
         autosave('NH1_Pos_MAM_Total');
        }
        
        else if(elem=="NH1_Neg_SAM_Adult"){
            autosave('NH1_Total_Adult');
            autosave('NH1_Neg_SAM_Total');
        }
        else if(elem=="NH1_Neg_SAM_15_17"){
            autosave('NH1_Total_15_17');
            autosave('NH1_Neg_SAM_Total');
        }
        else if(elem=="NH1_Neg_SAM_Postnatal"){
            autosave('NH1_Neg_SAM_Total');
             autosave('NH1_Total_Postnatal');
        }
        else if(elem=="NH1_Neg_SAM_0_59M"){
        autosave('NH1_Total_0_59M'); 
        autosave('NH1_Neg_SAM_Total');
        }
        else if(elem=="NH1_Neg_SAM_5_15"){
        autosave('NH1_Total_5_15');  
        autosave('NH1_Neg_SAM_Total');
        }
        
        else if(elem=="NH1_Neg_MAM_Adult"){
            autosave('NH1_Total_Adult');
            autosave('NH1_Neg_MAM_Total');
        }
        else if(elem=="NH1_Neg_MAM_15_17"){
            autosave('NH1_Total_15_17');
            autosave('NH1_Neg_MAM_Total');
        }
        else if(elem=="NH1_Neg_MAM_Postnatal"){
            autosave('NH1_Neg_MAM_Total');
            autosave('NH1_Total_Postnatal');    
        }
        else if(elem=="NH1_Neg_MAM_0_59M"){
         autosave('NH1_Total_0_59M'); 
         autosave('NH1_Neg_MAM_Total');
        }
        else if(elem=="NH1_Neg_MAM_5_15") {
        autosave('NH1_Total_5_15'); 
        autosave('NH1_Neg_MAM_Total');
        } 

    autosave('NH1_Grand_Total');
    
}

function ARTCoExistingInfection(elem){
        var ART_New_ARTInitiated_Adult  = document.getElementById("ART_New_ARTInitiated_Adult").value;
        var ART_New_ARTInitiated_15_17  = document.getElementById("ART_New_ARTInitiated_15_17").value;
        var ART_New_ARTInitiated_Postnatal  = document.getElementById("ART_New_ARTInitiated_Postnatal").value;
        var ART_New_ARTInitiated_0_59M  = document.getElementById("ART_New_ARTInitiated_0_59M").value;
        var ART_New_ARTInitiated_5_15  = document.getElementById("ART_New_ARTInitiated_5_15").value;
        
        var ART_New_TB_Adult  = document.getElementById("ART_New_TB_Adult").value;
        var ART_New_TB_15_17  = document.getElementById("ART_New_TB_15_17").value;
        var ART_New_TB_Postnatal  = document.getElementById("ART_New_TB_Postnatal").value;
        var ART_New_TB_0_59M  = document.getElementById("ART_New_TB_0_59M").value;
        var ART_New_TB_5_15  = document.getElementById("ART_New_TB_5_15").value;
        
        var ART_New_Diarrhoea_Adult  = document.getElementById("ART_New_Diarrhoea_Adult").value;
        var ART_New_Diarrhoea_15_17  = document.getElementById("ART_New_Diarrhoea_15_17").value;
        var ART_New_Diarrhoea_Postnatal  = document.getElementById("ART_New_Diarrhoea_Postnatal").value;
        var ART_New_Diarrhoea_0_59M  = document.getElementById("ART_New_Diarrhoea_0_59M").value;
        var ART_New_Diarrhoea_5_15  = document.getElementById("ART_New_Diarrhoea_5_15").value;
        
        var ART_New_OtherOIs_Adult  = document.getElementById("ART_New_OtherOIs_Adult").value;
        var ART_New_OtherOIs_15_17  = document.getElementById("ART_New_OtherOIs_15_17").value;
        var ART_New_OtherOIs_Postnatal  = document.getElementById("ART_New_OtherOIs_Postnatal").value;
        var ART_New_OtherOIs_0_59M  = document.getElementById("ART_New_OtherOIs_0_59M").value;
        var ART_New_OtherOIs_5_15  = document.getElementById("ART_New_OtherOIs_5_15").value;
        
        var ART_Revisits_ARTInitiated_Adult  = document.getElementById("ART_Revisits_ARTInitiated_Adult").value;
        var ART_Revisits_ARTInitiated_15_17  = document.getElementById("ART_Revisits_ARTInitiated_15_17").value;
        var ART_Revisits_ARTInitiated_Postnatal  = document.getElementById("ART_Revisits_ARTInitiated_Postnatal").value;
        var ART_Revisits_ARTInitiated_0_59M  = document.getElementById("ART_Revisits_ARTInitiated_0_59M").value;
        var ART_Revisits_ARTInitiated_5_15  = document.getElementById("ART_Revisits_ARTInitiated_5_15").value;
        
        var ART_Revisits_TB_Adult  = document.getElementById("ART_Revisits_TB_Adult").value;
        var ART_Revisits_TB_15_17  = document.getElementById("ART_Revisits_TB_15_17").value;
        var ART_Revisits_TB_Postnatal  = document.getElementById("ART_Revisits_TB_Postnatal").value;
        var ART_Revisits_TB_0_59M  = document.getElementById("ART_Revisits_TB_0_59M").value;
        var ART_Revisits_TB_5_15  = document.getElementById("ART_Revisits_TB_5_15").value;
        
        var ART_Revisits_Diarrhoea_Adult  = document.getElementById("ART_Revisits_Diarrhoea_Adult").value;
        var ART_Revisits_Diarrhoea_15_17  = document.getElementById("ART_Revisits_Diarrhoea_15_17").value;
        var ART_Revisits_Diarrhoea_Postnatal  = document.getElementById("ART_Revisits_Diarrhoea_Postnatal").value;
        var ART_Revisits_Diarrhoea_0_59M  = document.getElementById("ART_Revisits_Diarrhoea_0_59M").value;
        var ART_Revisits_Diarrhoea_5_15  = document.getElementById("ART_Revisits_Diarrhoea_5_15").value;
        
        var ART_Revisits_OtherOIs_Adult  = document.getElementById("ART_Revisits_OtherOIs_Adult").value;
        var ART_Revisits_OtherOIs_15_17  = document.getElementById("ART_Revisits_OtherOIs_15_17").value;
        var ART_Revisits_OtherOIs_Postnatal  = document.getElementById("ART_Revisits_OtherOIs_Postnatal").value;
        var ART_Revisits_OtherOIs_0_59M  = document.getElementById("ART_Revisits_OtherOIs_0_59M").value;
        var ART_Revisits_OtherOIs_5_15  = document.getElementById("ART_Revisits_OtherOIs_5_15").value;
        
        
        if(ART_New_ARTInitiated_Adult==""){ ART_New_ARTInitiated_Adult=0;}
        if(ART_New_ARTInitiated_15_17==""){ ART_New_ARTInitiated_15_17=0;}
        if(ART_New_ARTInitiated_Postnatal==""){ ART_New_ARTInitiated_Postnatal=0;}
        if(ART_New_ARTInitiated_0_59M==""){ ART_New_ARTInitiated_0_59M=0;}
        if(ART_New_ARTInitiated_5_15==""){ ART_New_ARTInitiated_5_15=0;}
        
        if(ART_New_TB_Adult==""){ ART_New_TB_Adult=0;}
        if(ART_New_TB_15_17==""){ ART_New_TB_15_17=0;}
        if(ART_New_TB_Postnatal==""){ ART_New_TB_Postnatal=0;}
        if(ART_New_TB_0_59M==""){ ART_New_TB_0_59M=0;}
        if(ART_New_TB_5_15==""){ ART_New_TB_5_15=0;}
        
        if(ART_New_Diarrhoea_Adult==""){ ART_New_Diarrhoea_Adult=0;}
        if(ART_New_Diarrhoea_15_17==""){ ART_New_Diarrhoea_15_17=0;}
        if(ART_New_Diarrhoea_Postnatal==""){ ART_New_Diarrhoea_Postnatal=0;}
        if(ART_New_Diarrhoea_0_59M==""){ ART_New_Diarrhoea_0_59M=0;}
        if(ART_New_Diarrhoea_5_15==""){ ART_New_Diarrhoea_5_15=0;}
        
        if(ART_New_OtherOIs_Adult==""){ ART_New_OtherOIs_Adult=0;}
        if(ART_New_OtherOIs_15_17==""){ ART_New_OtherOIs_15_17=0;}
        if(ART_New_OtherOIs_Postnatal==""){ ART_New_OtherOIs_Postnatal=0;}
        if(ART_New_OtherOIs_0_59M==""){ ART_New_OtherOIs_0_59M=0;}
        if(ART_New_OtherOIs_5_15==""){ ART_New_OtherOIs_5_15=0;}
        
        if(ART_Revisits_ARTInitiated_Adult==""){ ART_Revisits_ARTInitiated_Adult=0;}
        if(ART_Revisits_ARTInitiated_15_17==""){ ART_Revisits_ARTInitiated_15_17=0;}
        if(ART_Revisits_ARTInitiated_Postnatal==""){ ART_Revisits_ARTInitiated_Postnatal=0;}
        if(ART_Revisits_ARTInitiated_0_59M==""){ ART_Revisits_ARTInitiated_0_59M=0;}
        if(ART_Revisits_ARTInitiated_5_15==""){ ART_Revisits_ARTInitiated_5_15=0;}
        
        if(ART_Revisits_TB_Adult==""){ ART_Revisits_TB_Adult=0;}
        if(ART_Revisits_TB_15_17==""){ ART_Revisits_TB_15_17=0;}
        if(ART_Revisits_TB_Postnatal==""){ ART_Revisits_TB_Postnatal=0;}
        if(ART_Revisits_TB_0_59M==""){ ART_Revisits_TB_0_59M=0;}
        if(ART_Revisits_TB_5_15==""){ ART_Revisits_TB_5_15=0;}
        
        if(ART_Revisits_Diarrhoea_Adult==""){ ART_Revisits_Diarrhoea_Adult=0;}
        if(ART_Revisits_Diarrhoea_15_17==""){ ART_Revisits_Diarrhoea_15_17=0;}
        if(ART_Revisits_Diarrhoea_Postnatal==""){ ART_Revisits_Diarrhoea_Postnatal=0;}
        if(ART_Revisits_Diarrhoea_0_59M==""){ ART_Revisits_Diarrhoea_0_59M=0;}
        if(ART_Revisits_Diarrhoea_5_15==""){ ART_Revisits_Diarrhoea_5_15=0;}
        
        if(ART_Revisits_OtherOIs_Adult==""){ ART_Revisits_OtherOIs_Adult=0;}
        if(ART_Revisits_OtherOIs_15_17==""){ ART_Revisits_OtherOIs_15_17=0;}
        if(ART_Revisits_OtherOIs_Postnatal==""){ ART_Revisits_OtherOIs_Postnatal=0;}
        if(ART_Revisits_OtherOIs_0_59M==""){ ART_Revisits_OtherOIs_0_59M=0;}
        if(ART_Revisits_OtherOIs_5_15==""){ ART_Revisits_OtherOIs_5_15=0;}
        
        
        var ART_New_ARTInitiated_Total  = parseInt(ART_New_ARTInitiated_Adult)+parseInt(ART_New_ARTInitiated_15_17)+parseInt(ART_New_ARTInitiated_Postnatal)+parseInt(ART_New_ARTInitiated_0_59M)+parseInt(ART_New_ARTInitiated_5_15);
        var ART_New_TB_Total  =  parseInt(ART_New_TB_Adult)+parseInt(ART_New_TB_15_17)+parseInt(ART_New_TB_Postnatal)+parseInt(ART_New_TB_0_59M)+parseInt(ART_New_TB_5_15);
        var ART_New_Diarrhoea_Total  =  parseInt(ART_New_Diarrhoea_Adult)+parseInt(ART_New_Diarrhoea_15_17)+parseInt(ART_New_Diarrhoea_Postnatal)+parseInt(ART_New_Diarrhoea_0_59M)+parseInt(ART_New_Diarrhoea_5_15);
        var ART_New_OtherOIs_Total  =  parseInt(ART_New_OtherOIs_Adult)+parseInt(ART_New_OtherOIs_15_17)+parseInt(ART_New_OtherOIs_Postnatal)+parseInt(ART_New_OtherOIs_0_59M)+parseInt(ART_New_OtherOIs_5_15);
        var ART_Revisits_ARTInitiated_Total  =  parseInt(ART_Revisits_ARTInitiated_Adult)+parseInt(ART_Revisits_ARTInitiated_15_17)+parseInt(ART_Revisits_ARTInitiated_Postnatal)+parseInt(ART_Revisits_ARTInitiated_0_59M)+parseInt(ART_Revisits_ARTInitiated_5_15);
        var ART_Revisits_TB_Total  =  parseInt(ART_Revisits_TB_Adult)+parseInt(ART_Revisits_TB_15_17)+parseInt(ART_Revisits_TB_Postnatal)+parseInt(ART_Revisits_TB_0_59M)+parseInt(ART_Revisits_TB_5_15);
        var ART_Revisits_Diarrhoea_Total  =  parseInt(ART_Revisits_Diarrhoea_Adult)+parseInt(ART_Revisits_Diarrhoea_15_17)+parseInt(ART_Revisits_Diarrhoea_Postnatal)+parseInt(ART_Revisits_Diarrhoea_0_59M)+parseInt(ART_Revisits_Diarrhoea_5_15);
        var ART_Revisits_OtherOIs_Total  =  parseInt(ART_Revisits_OtherOIs_Adult)+parseInt(ART_Revisits_OtherOIs_15_17)+parseInt(ART_Revisits_OtherOIs_Postnatal)+parseInt(ART_Revisits_OtherOIs_0_59M)+parseInt(ART_Revisits_OtherOIs_5_15);
        
    document.getElementById("ART_New_ARTInitiated_Total").value = ART_New_ARTInitiated_Total;            
    document.getElementById("ART_New_TB_Total").value = ART_New_TB_Total;            
    document.getElementById("ART_New_Diarrhoea_Total").value = ART_New_Diarrhoea_Total;            
    document.getElementById("ART_New_OtherOIs_Total").value = ART_New_OtherOIs_Total;    
    document.getElementById("ART_Revisits_ARTInitiated_Total").value = ART_Revisits_ARTInitiated_Total;    
    document.getElementById("ART_Revisits_TB_Total").value = ART_Revisits_TB_Total;
    document.getElementById("ART_Revisits_Diarrhoea_Total").value = ART_Revisits_Diarrhoea_Total;
    document.getElementById("ART_Revisits_OtherOIs_Total").value = ART_Revisits_OtherOIs_Total;
    
    if(elem=="ART_New_ARTInitiated_Adult" || elem=="ART_New_ARTInitiated_15_17" || elem=="ART_New_ARTInitiated_Postnatal" || elem=="ART_New_ARTInitiated_0_59M" || elem=="ART_New_ARTInitiated_5_15" )
        {autosave('ART_New_ARTInitiated_Total');}
    else if(elem=="ART_New_TB_Adult" || elem=="ART_New_TB_15_17" || elem=="ART_New_TB_Postnatal" || elem=="ART_New_TB_0_59M" || elem=="ART_New_TB_5_15" )
        {autosave('ART_New_TB_Total'); }
     else if(elem=="ART_New_Diarrhoea_Adult" || elem=="ART_New_Diarrhoea_15_17" || elem=="ART_New_Diarrhoea_Postnatal" || elem=="ART_New_Diarrhoea_0_59M" || elem=="ART_New_Diarrhoea_5_15" )
        {autosave('ART_New_Diarrhoea_Total');}
     else if(elem=="ART_New_OtherOIs_Adult" || elem=="ART_New_OtherOIs_15_17" || elem=="ART_New_OtherOIs_Postnatal" || elem=="ART_New_OtherOIs_0_59M" || elem=="ART_New_OtherOIs_5_15" )
        {autosave('ART_New_OtherOIs_Total'); }
     else if(elem=="ART_Revisits_ARTInitiated_Adult" || elem=="ART_Revisits_ARTInitiated_15_17" || elem=="ART_Revisits_ARTInitiated_Postnatal" || elem=="ART_Revisits_ARTInitiated_0_59M" || elem=="ART_Revisits_ARTInitiated_5_15" )
        {autosave('ART_Revisits_ARTInitiated_Total'); }
     else if(elem=="ART_Revisits_TB_Adult" || elem=="ART_Revisits_TB_15_17" || elem=="ART_Revisits_TB_Postnatal" || elem=="ART_Revisits_TB_0_59M" || elem=="ART_Revisits_TB_5_15" )
        {autosave('ART_Revisits_TB_Total');}
     else if(elem=="ART_Revisits_Diarrhoea_Adult" || elem=="ART_Revisits_Diarrhoea_15_17" || elem=="ART_Revisits_Diarrhoea_Postnatal" || elem=="ART_Revisits_Diarrhoea_0_59M" || elem=="ART_Revisits_Diarrhoea_5_15" )
        {autosave('ART_Revisits_Diarrhoea_Total'); }   
     else if(elem=="ART_Revisits_OtherOIs_Adult" || elem=="ART_Revisits_OtherOIs_15_17" || elem=="ART_Revisits_OtherOIs_Postnatal" || elem=="ART_Revisits_OtherOIs_0_59M" || elem=="ART_Revisits_OtherOIs_5_15" )
        {autosave('ART_Revisits_OtherOIs_Total'); }
           
}

function Anaemia(elem){
 
        var Anaemia_New_Severe_AdultPos  = document.getElementById("Anaemia_New_Severe_AdultPos").value;
        var Anaemia_New_Severe_AllChildren  = document.getElementById("Anaemia_New_Severe_AllChildren").value;
        var Anaemia_New_Moderate_AdultPos = document.getElementById("Anaemia_New_Moderate_AdultPos").value;
        var Anaemia_New_Moderate_AllChildren  = document.getElementById("Anaemia_New_Moderate_AllChildren").value;
        var Anaemia_New_Mild_AdultPos  = document.getElementById("Anaemia_New_Mild_AdultPos").value;
        var Anaemia_New_Mild_AllChildren  = document.getElementById("Anaemia_New_Mild_AllChildren").value;
        var Anaemia_New_Normal_AdultPos  = document.getElementById("Anaemia_New_Normal_AdultPos").value;
        var Anaemia_New_Normal_AllChildren  = document.getElementById("Anaemia_New_Normal_AllChildren").value;
        
        var Anaemia_New_Severe_AdultNeg  = document.getElementById("Anaemia_New_Severe_AdultNeg").value;
        var Anaemia_New_Moderate_AdultNeg  = document.getElementById("Anaemia_New_Moderate_AdultNeg").value;
        var Anaemia_New_Mild_AdultNeg  = document.getElementById("Anaemia_New_Mild_AdultNeg").value;
        var Anaemia_New_Normal_AdultNeg  = document.getElementById("Anaemia_New_Normal_AdultNeg").value;
        
        
        if( Anaemia_New_Severe_AdultPos==""){ Anaemia_New_Severe_AdultPos=0;}
        if( Anaemia_New_Severe_AllChildren==""){ Anaemia_New_Severe_AllChildren=0;}
        if( Anaemia_New_Moderate_AdultPos==""){ Anaemia_New_Moderate_AdultPos=0;}
        if( Anaemia_New_Moderate_AllChildren==""){ Anaemia_New_Moderate_AllChildren=0;}
        if( Anaemia_New_Mild_AdultPos==""){ Anaemia_New_Mild_AdultPos=0;}
        if( Anaemia_New_Mild_AllChildren==""){ Anaemia_New_Mild_AllChildren=0;}
        if( Anaemia_New_Normal_AdultPos ==""){Anaemia_New_Normal_AdultPos =0;}
        if(  Anaemia_New_Normal_AllChildren==""){Anaemia_New_Normal_AllChildren =0;}
        
        if(  Anaemia_New_Severe_AdultNeg==""){Anaemia_New_Severe_AdultNeg =0;}
        if(  Anaemia_New_Moderate_AdultNeg==""){Anaemia_New_Moderate_AdultNeg =0;}
        if(  Anaemia_New_Mild_AdultNeg==""){Anaemia_New_Mild_AdultNeg =0;}
        if(  Anaemia_New_Normal_AdultNeg==""){Anaemia_New_Normal_AdultNeg =0;}
        
        var Anaemia_Total_AdultPos = parseInt(Anaemia_New_Severe_AdultPos)+ parseInt(Anaemia_New_Moderate_AdultPos)+ parseInt(Anaemia_New_Mild_AdultPos)+ parseInt(Anaemia_New_Normal_AdultPos); 
        var Anaemia_Total_AllChildren = parseInt(Anaemia_New_Severe_AllChildren)+ parseInt(Anaemia_New_Moderate_AllChildren)+ parseInt(Anaemia_New_Mild_AllChildren)+ parseInt(Anaemia_New_Normal_AllChildren); 
        var Anaemia_Total_AdultNeg  = parseInt(Anaemia_New_Severe_AdultNeg)+parseInt(Anaemia_New_Moderate_AdultNeg)+parseInt(Anaemia_New_Mild_AdultNeg)+parseInt(Anaemia_New_Normal_AdultNeg);
            
        document.getElementById("Anaemia_Total_AdultPos").value = Anaemia_Total_AdultPos;
        document.getElementById("Anaemia_Total_AllChildren").value = Anaemia_Total_AllChildren; 
        document.getElementById("Anaemia_Total_AdultNeg").value = Anaemia_Total_AdultNeg;
        
        if(elem=="Anaemia_New_Severe_AdultPos" || elem=="Anaemia_New_Moderate_AdultPos" || elem=="Anaemia_New_Mild_AdultPos" || elem=="Anaemia_New_Normal_AdultPos"){
         autosave('Anaemia_Total_AdultPos');   
        } 
        
        else if(elem=="Anaemia_New_Severe_AllChildren" || elem=="Anaemia_New_Moderate_AllChildren" || elem=="Anaemia_New_Mild_AllChildren" || elem=="Anaemia_New_Normal_AllChildren"){
        autosave('Anaemia_Total_AllChildren');    
        } 
        
        else if(elem=="Anaemia_New_Severe_AdultNeg" || elem=="Anaemia_New_Moderate_AdultNeg" || elem=="Anaemia_New_Mild_AdultNeg" || elem=="Anaemia_New_Normal_AdultNeg"){
         autosave('Anaemia_Total_AdultNeg');   
        }        
        
}

function InfantFeedingPractices(elem){
        var IFP_0_6_EBF_Pos  = document.getElementById("IFP_0_6_EBF_Pos").value;
        var IFP_0_6_EBF_Neg  = document.getElementById("IFP_0_6_EBF_Neg").value;
        var IFP_0_6_ERF_Pos  = document.getElementById("IFP_0_6_ERF_Pos").value;
        var IFP_0_6_ERF_Neg  = document.getElementById("IFP_0_6_ERF_Neg").value;
        var IFP_0_6_MF_Pos  = document.getElementById("IFP_0_6_MF_Pos").value;
        var IFP_0_6_MF_Neg  = document.getElementById("IFP_0_6_MF_Neg").value;
        var IFP_6_12_BF_Pos  = document.getElementById("IFP_6_12_BF_Pos").value;
        var IFP_6_12_BF_Neg  = document.getElementById("IFP_6_12_BF_Neg").value;
        var IFP_6_12_NBF_Pos  = document.getElementById("IFP_6_12_NBF_Pos").value;
        var IFP_6_12_NBF_Neg  = document.getElementById("IFP_6_12_NBF_Neg").value;
        var IFP_6_12_NotKnown_Pos  = document.getElementById("IFP_6_12_NotKnown_Pos").value;
        var IFP_6_12_NotKnown_Neg  = document.getElementById("IFP_6_12_NotKnown_Neg").value;
        var IFP_6_12_BCF_Pos  = document.getElementById("IFP_6_12_BCF_Pos").value;
        var IFP_6_12_BCF_Neg  = document.getElementById("IFP_6_12_BCF_Neg").value;
        
        if( IFP_0_6_EBF_Pos=="") { IFP_0_6_EBF_Pos=0;}
        if( IFP_0_6_EBF_Neg=="") { IFP_0_6_EBF_Neg=0;}
        if( IFP_0_6_ERF_Pos=="") { IFP_0_6_ERF_Pos=0;}
        if( IFP_0_6_ERF_Neg=="") { IFP_0_6_ERF_Neg=0;}
        if( IFP_0_6_MF_Pos=="") { IFP_0_6_MF_Pos=0;}
        if( IFP_0_6_MF_Neg=="") { IFP_0_6_MF_Neg=0;}
        
        if( IFP_6_12_BF_Pos=="") { IFP_6_12_BF_Pos=0;}
        if( IFP_6_12_BF_Neg=="") { IFP_6_12_BF_Neg=0;}
        if( IFP_6_12_NBF_Pos=="") { IFP_6_12_NBF_Pos=0;}
        if( IFP_6_12_NBF_Neg=="") { IFP_6_12_NBF_Neg=0;}
        if( IFP_6_12_NotKnown_Pos=="") { IFP_6_12_NotKnown_Pos=0;}
        if( IFP_6_12_NotKnown_Neg=="") { IFP_6_12_NotKnown_Neg=0;}
        if( IFP_6_12_BCF_Pos=="") { IFP_6_12_BCF_Pos=0;}
        if( IFP_6_12_BCF_Neg=="") { IFP_6_12_BCF_Neg=0;}
        
        
        var IFP_0_6_SubTotal_Pos  = parseInt(IFP_0_6_EBF_Pos)+ parseInt(IFP_0_6_ERF_Pos)+ parseInt(IFP_0_6_MF_Pos);
        var IFP_0_6_SubTotal_Neg  =  parseInt(IFP_0_6_EBF_Neg)+ parseInt(IFP_0_6_ERF_Neg)+ parseInt(IFP_0_6_MF_Neg);
        var IFP_6_12_SubTotal_Pos  =  parseInt(IFP_6_12_BF_Pos)+ parseInt(IFP_6_12_NBF_Pos)+ parseInt(IFP_6_12_NotKnown_Pos)+ parseInt(IFP_6_12_BCF_Pos);
        var IFP_6_12_SubTotal_Neg  =  parseInt(IFP_6_12_BF_Neg)+ parseInt(IFP_6_12_NBF_Neg)+ parseInt(IFP_6_12_NotKnown_Neg)+ parseInt(IFP_6_12_BCF_Neg);
        
                document.getElementById("IFP_0_6_SubTotal_Pos").value = IFP_0_6_SubTotal_Pos;
                document.getElementById("IFP_0_6_SubTotal_Neg").value = IFP_0_6_SubTotal_Neg;
                document.getElementById("IFP_6_12_SubTotal_Pos").value = IFP_6_12_SubTotal_Pos;
                document.getElementById("IFP_6_12_SubTotal_Neg").value = IFP_6_12_SubTotal_Neg;   
      if(elem=="IFP_0_6_EBF_Pos" || elem=="IFP_0_6_ERF_Pos" || elem=="IFP_0_6_MF_Pos"){
           autosave('IFP_0_6_SubTotal_Pos'); 
      } 
      if(elem=="IFP_0_6_EBF_Neg" || elem=="IFP_0_6_ERF_Neg" || elem=="IFP_0_6_MF_Neg"){
         autosave('IFP_0_6_SubTotal_Neg'); 
      } 
      if(elem=="IFP_6_12_BF_Pos" || elem=="IFP_6_12_NBF_Pos" || elem=="IFP_6_12_NotKnown_Pos" || elem=="IFP_6_12_BCF_Pos"){
        autosave('IFP_6_12_SubTotal_Pos');   
      } 
      if(elem=="IFP_6_12_BF_Neg" || elem=="IFP_6_12_NBF_Neg" || elem=="IFP_6_12_NotKnown_Neg" || elem=="IFP_6_12_BCF_Neg"){
        autosave('IFP_6_12_SubTotal_Neg');   
      } 
   
}

function NutritionIntervention(elem){
       
//        var NI_MaternalNutrition_Adults15_17_Pos  = document.getElementById("NI_MaternalNutrition_Adults15_17_Pos").value;
//        var NI_MaternalNutrition_Postnatal_Pos  = document.getElementById("NI_MaternalNutrition_Postnatal_Pos").value;
//        var NI_MaternalNutrition_AllAdults_Neg  = document.getElementById("NI_MaternalNutrition_AllAdults_Neg").value;
//        var NI_MaternalNutrition_0_59M_Pos  = document.getElementById("NI_MaternalNutrition_0_59M_Pos").value;
//        var NI_MaternalNutrition_5_15_Pos  = document.getElementById("NI_MaternalNutrition_5_15_Pos").value;
//        var NI_MaternalNutrition_0_59M_Neg  = document.getElementById("NI_MaternalNutrition_0_59M_Neg").value;
//        var NI_MaternalNutrition_5_15_Neg  = document.getElementById("NI_MaternalNutrition_5_15_Neg").value;
        
        var NI_Prenatal_Adults15_17_Pos  = document.getElementById("NI_Prenatal_Adults15_17_Pos").value;
        var NI_Prenatal_Postnatal_Pos  = document.getElementById("NI_Prenatal_Postnatal_Pos").value;
        var NI_Prenatal_AllAdults_Neg  = document.getElementById("NI_Prenatal_AllAdults_Neg").value;
//        var NI_Prenatal_0_59M_Pos  = document.getElementById("NI_Prenatal_0_59M_Pos").value;
//        var NI_Prenatal_5_15_Pos  = document.getElementById("NI_Prenatal_5_15_Pos").value;
//        var NI_Prenatal_0_59M_Neg  = document.getElementById("NI_Prenatal_0_59M_Neg").value;
//        var NI_Prenatal_5_15_Neg  = document.getElementById("NI_Prenatal_5_15_Neg").value;
        
        var NI_Postnatal_Adults15_17_Pos  = document.getElementById("NI_Postnatal_Adults15_17_Pos").value;
        var NI_Postnatal_Postnatal_Pos  = document.getElementById("NI_Postnatal_Postnatal_Pos").value;
        var NI_Postnatal_AllAdults_Neg  = document.getElementById("NI_Postnatal_AllAdults_Neg").value;
//        var NI_Postnatal_0_59M_Pos  = document.getElementById("NI_Postnatal_0_59M_Pos").value;
//        var NI_Postnatal_5_15_Pos  = document.getElementById("NI_Postnatal_5_15_Pos").value;
//        var NI_Postnatal_0_59M_Neg  = document.getElementById("NI_Postnatal_0_59M_Neg").value;
//        var NI_Postnatal_5_15_Neg  = document.getElementById("NI_Postnatal_5_15_Neg").value;
        
        var NI_CNP_Adults15_17_Pos  = document.getElementById("NI_CNP_Adults15_17_Pos").value;
        var NI_CNP_Postnatal_Pos  = document.getElementById("NI_CNP_Postnatal_Pos").value;
        var NI_CNP_AllAdults_Neg  = document.getElementById("NI_CNP_AllAdults_Neg").value;
        var NI_CNP_0_59M_Pos  = document.getElementById("NI_CNP_0_59M_Pos").value;
        var NI_CNP_5_15_Pos  = document.getElementById("NI_CNP_5_15_Pos").value;
        var NI_CNP_0_59M_Neg  = document.getElementById("NI_CNP_0_59M_Neg").value;
        var NI_CNP_5_15_Neg  = document.getElementById("NI_CNP_5_15_Neg").value;
        
//        var NI_IYCF_Adults15_17_Pos  = document.getElementById("NI_IYCF_Adults15_17_Pos").value;
//        var NI_IYCF_Postnatal_Pos  = document.getElementById("NI_IYCF_Postnatal_Pos").value;
//        var NI_IYCF_AllAdults_Neg  = document.getElementById("NI_IYCF_AllAdults_Neg").value;
        var NI_IYCF_0_59M_Pos  = document.getElementById("NI_IYCF_0_59M_Pos").value;
        var NI_IYCF_5_15_Pos  = document.getElementById("NI_IYCF_5_15_Pos").value;
        var NI_IYCF_0_59M_Neg  = document.getElementById("NI_IYCF_0_59M_Neg").value;
        var NI_IYCF_5_15_Neg  = document.getElementById("NI_IYCF_5_15_Neg").value;
        
//        if( NI_MaternalNutrition_Adults15_17_Pos==""){ NI_MaternalNutrition_Adults15_17_Pos=0;}
//        if( NI_MaternalNutrition_Postnatal_Pos==""){ NI_MaternalNutrition_Postnatal_Pos=0;}
//        if( NI_MaternalNutrition_AllAdults_Neg==""){ NI_MaternalNutrition_AllAdults_Neg=0;}
//        if( NI_MaternalNutrition_0_59M_Pos==""){ NI_MaternalNutrition_0_59M_Pos=0;}
//        if( NI_MaternalNutrition_5_15_Pos==""){ NI_MaternalNutrition_5_15_Pos=0;};
//        if( NI_MaternalNutrition_0_59M_Neg==""){ NI_MaternalNutrition_0_59M_Neg=0;}
//        if( NI_MaternalNutrition_5_15_Neg==""){ NI_MaternalNutrition_5_15_Neg=0;}
        
        if( NI_Prenatal_Adults15_17_Pos==""){ NI_Prenatal_Adults15_17_Pos=0;}
        if( NI_Prenatal_Postnatal_Pos==""){ NI_Prenatal_Postnatal_Pos=0;}
        if( NI_Prenatal_AllAdults_Neg==""){ NI_Prenatal_AllAdults_Neg=0;}
        var NI_Prenatal_0_59M_Pos=0;
        var NI_Prenatal_5_15_Pos=0;
        var NI_Prenatal_0_59M_Neg=0;
        var NI_Prenatal_5_15_Neg=0;
        
        if(NI_Postnatal_Adults15_17_Pos==""){ NI_Postnatal_Adults15_17_Pos=0;}
        if(NI_Postnatal_Postnatal_Pos==""){ NI_Postnatal_Postnatal_Pos=0;}
        if(NI_Postnatal_AllAdults_Neg==""){ NI_Postnatal_AllAdults_Neg=0;}
        var NI_Postnatal_0_59M_Pos=0;
        var NI_Postnatal_5_15_Pos=0;
        var NI_Postnatal_0_59M_Neg=0;
        var NI_Postnatal_5_15_Neg=0;
        
        if(NI_CNP_Adults15_17_Pos==""){ NI_CNP_Adults15_17_Pos=0;}
        if(NI_CNP_Postnatal_Pos==""){ NI_CNP_Postnatal_Pos=0;}
        if(NI_CNP_AllAdults_Neg==""){ NI_CNP_AllAdults_Neg=0;}
        if(NI_CNP_0_59M_Pos==""){ NI_CNP_0_59M_Pos=0;}
        if(NI_CNP_5_15_Pos==""){ NI_CNP_5_15_Pos=0;}
        if(NI_CNP_0_59M_Neg==""){ NI_CNP_0_59M_Neg=0;}
        if(NI_CNP_5_15_Neg==""){ NI_CNP_5_15_Neg=0;}
        
        var NI_IYCF_Adults15_17_Pos=0;
        var NI_IYCF_Postnatal_Pos=0;
        var NI_IYCF_AllAdults_Neg=0;
        if(NI_IYCF_0_59M_Pos==""){ NI_IYCF_0_59M_Pos=0;}
        if(NI_IYCF_5_15_Pos==""){ NI_IYCF_5_15_Pos=0;}
        if(NI_IYCF_0_59M_Neg==""){ NI_IYCF_0_59M_Neg=0;}
        if(NI_IYCF_5_15_Neg==""){ NI_IYCF_5_15_Neg=0;}
        
        
//        var NI_MaternalNutrition_Total  = 0;
        var NI_Prenatal_Total  = parseInt(NI_Prenatal_Adults15_17_Pos)+ parseInt(NI_Prenatal_Postnatal_Pos)
                +parseInt(NI_Prenatal_AllAdults_Neg)+parseInt(NI_Prenatal_0_59M_Pos)+parseInt(NI_Prenatal_5_15_Pos)
                +parseInt(NI_Prenatal_0_59M_Neg)+parseInt(NI_Prenatal_5_15_Neg);
        
        var NI_Postnatal_Total  = parseInt(NI_Postnatal_Adults15_17_Pos)+ parseInt(NI_Postnatal_Postnatal_Pos)
                +parseInt(NI_Postnatal_AllAdults_Neg)+parseInt(NI_Postnatal_0_59M_Pos)+parseInt(NI_Postnatal_5_15_Pos)
                +parseInt(NI_Postnatal_0_59M_Neg)+parseInt(NI_Postnatal_5_15_Neg);
        
        var NI_CNP_Total  = parseInt(NI_CNP_Adults15_17_Pos)+ parseInt(NI_CNP_Postnatal_Pos)
                +parseInt(NI_CNP_AllAdults_Neg)+parseInt(NI_CNP_0_59M_Pos)+parseInt(NI_CNP_5_15_Pos)
                +parseInt(NI_CNP_0_59M_Neg)+parseInt(NI_CNP_5_15_Neg);
        
        var NI_IYCF_Total  = parseInt(NI_IYCF_Adults15_17_Pos)+ parseInt(NI_IYCF_Postnatal_Pos)
                +parseInt(NI_IYCF_AllAdults_Neg)+parseInt(NI_IYCF_0_59M_Pos)+parseInt(NI_IYCF_5_15_Pos)
                +parseInt(NI_IYCF_0_59M_Neg)+parseInt(NI_IYCF_5_15_Neg);
        
    
        var NI_Total_Adults15_17_Pos  = parseInt(NI_Prenatal_Adults15_17_Pos)+ parseInt(NI_Postnatal_Adults15_17_Pos)+ parseInt(NI_CNP_Adults15_17_Pos)+ parseInt(NI_IYCF_Adults15_17_Pos);
        var NI_Total_Postnatal_Pos  =   parseInt(NI_Prenatal_Postnatal_Pos)+ parseInt(NI_Postnatal_Postnatal_Pos)+ parseInt(NI_CNP_Postnatal_Pos)+ parseInt(NI_IYCF_Postnatal_Pos);
        var NI_Total_AllAdults_Neg  =   parseInt(NI_Prenatal_AllAdults_Neg)+ parseInt(NI_Postnatal_AllAdults_Neg)+ parseInt(NI_CNP_AllAdults_Neg)+ parseInt(NI_IYCF_AllAdults_Neg);
        var NI_Total_0_59M_Pos  =   parseInt(NI_Prenatal_0_59M_Pos)+ parseInt(NI_Postnatal_0_59M_Pos)+ parseInt(NI_CNP_0_59M_Pos)+ parseInt(NI_IYCF_0_59M_Pos);
        var NI_Total_5_15_Pos  =   parseInt(NI_Prenatal_5_15_Pos)+ parseInt(NI_Postnatal_5_15_Pos)+ parseInt(NI_CNP_5_15_Pos)+ parseInt(NI_IYCF_5_15_Pos);
        var NI_Total_0_59M_Neg  =   parseInt(NI_Prenatal_0_59M_Neg)+ parseInt(NI_Postnatal_0_59M_Neg)+ parseInt(NI_CNP_0_59M_Neg)+ parseInt(NI_IYCF_0_59M_Neg);
        var NI_Total_5_15_Neg  =   parseInt(NI_Prenatal_5_15_Neg)+ parseInt(NI_Postnatal_5_15_Neg)+ parseInt(NI_CNP_5_15_Neg)+ parseInt(NI_IYCF_5_15_Neg);
        var NI_Grand_Total  =   parseInt(NI_Prenatal_Total)+ parseInt(NI_Postnatal_Total)+ parseInt(NI_CNP_Total)+ parseInt(NI_IYCF_Total);
        
                
//                document.getElementById("NI_MaternalNutrition_Total").value = NI_MaternalNutrition_Total;
                document.getElementById("NI_Prenatal_Total").value = NI_Prenatal_Total;
                document.getElementById("NI_Postnatal_Total").value = NI_Postnatal_Total;
                document.getElementById("NI_CNP_Total").value = NI_CNP_Total;
                document.getElementById("NI_IYCF_Total").value = NI_IYCF_Total;
                
                document.getElementById("NI_Total_Adults15_17_Pos").value = NI_Total_Adults15_17_Pos;
                document.getElementById("NI_Total_5_15_Neg").value = NI_Total_5_15_Neg;
                document.getElementById("NI_Total_0_59M_Neg").value = NI_Total_0_59M_Neg;
                document.getElementById("NI_Total_5_15_Pos").value = NI_Total_5_15_Pos;
                document.getElementById("NI_Total_0_59M_Pos").value = NI_Total_0_59M_Pos;
                document.getElementById("NI_Total_AllAdults_Neg").value = NI_Total_AllAdults_Neg;
                document.getElementById("NI_Total_Postnatal_Pos").value = NI_Total_Postnatal_Pos;
                document.getElementById("NI_Grand_Total").value = NI_Grand_Total;
                
        if(elem=="NI_Prenatal_Adults15_17_Pos"){ 
        autosave('NI_Prenatal_Total');
        autosave('NI_Total_Adults15_17_Pos');
    }
        else if(elem=="NI_Prenatal_Postnatal_Pos"){ 
          autosave('NI_Prenatal_Total');
          autosave('NI_Total_Postnatal_Pos');
    }
        else if( elem=="NI_Prenatal_AllAdults_Neg"){
        autosave('NI_Prenatal_Total');
        autosave('NI_Total_AllAdults_Neg');
        }
        
        
        else if(elem=="NI_Postnatal_Adults15_17_Pos"){ 
        autosave('NI_Postnatal_Total');
        autosave('NI_Total_Adults15_17_Pos');
        }
        else if(elem=="NI_Postnatal_Postnatal_Pos"){ 
        autosave('NI_Postnatal_Total');
        autosave('NI_Total_Postnatal_Pos');
        }
        else if(elem=="NI_Postnatal_AllAdults_Neg"){ 
       autosave('NI_Postnatal_Total'); 
       autosave('NI_Total_AllAdults_Neg');
        }
        
        
        else if(elem=="NI_CNP_Adults15_17_Pos"){ 
        autosave('NI_Total_Adults15_17_Pos');
        autosave('NI_CNP_Total');
        }
        else if(elem=="NI_CNP_Postnatal_Pos"){ 
        autosave('NI_CNP_Total');
        autosave('NI_Total_Postnatal_Pos');
        }
        else if(elem=="NI_CNP_AllAdults_Neg"){ 
        autosave('NI_CNP_Total');
        autosave('NI_Total_AllAdults_Neg');
        }
        else if(elem=="NI_CNP_0_59M_Pos"){ 
        autosave('NI_CNP_Total');
        autosave('NI_Total_0_59M_Pos');
        }
        else if(elem=="NI_CNP_5_15_Pos"){ 
        autosave('NI_CNP_Total');
        autosave('NI_Total_5_15_Pos');
        }
        else if(elem=="NI_CNP_0_59M_Neg"){
        autosave('NI_CNP_Total');
        autosave('NI_Total_0_59M_Neg');
        }
        else if(elem=="NI_CNP_5_15_Neg"){
        autosave('NI_CNP_Total');
         autosave('NI_Total_5_15_Neg');
        }
        
        else if(elem=="NI_IYCF_0_59M_Pos"){ 
        autosave('NI_IYCF_Total');
        autosave('NI_Total_0_59M_Pos');
        }
        else if(elem=="NI_IYCF_5_15_Pos"){ 
        autosave('NI_IYCF_Total');
        autosave('NI_Total_5_15_Pos');
        }
        else if(elem=="NI_IYCF_0_59M_Neg"){ 
        autosave('NI_IYCF_Total');
        autosave('NI_Total_0_59M_Neg');
        }
        else if(elem=="NI_IYCF_5_15_Neg"){ 
        autosave('NI_IYCF_Total');
        autosave('NI_Total_5_15_Neg');
        }
        
         autosave('NI_Grand_Total');
}

function NH2(elem){
 	var NH2_TCM_Adults15_17_Pos  = document.getElementById("NH2_TCM_Adults15_17_Pos").value;
        var NH2_TCM_Postnatal_Pos  = document.getElementById("NH2_TCM_Postnatal_Pos").value;
        var NH2_TCM_AllAdults_Neg  = document.getElementById("NH2_TCM_AllAdults_Neg").value;
        var NH2_TCM_0_59M_Pos  = document.getElementById("NH2_TCM_0_59M_Pos").value;
        var NH2_TCM_5_15_Pos  = document.getElementById("NH2_TCM_5_15_Pos").value;
        var NH2_TCM_0_59M_Neg  = document.getElementById("NH2_TCM_0_59M_Neg").value;
        var NH2_TCM_5_15_Neg = document.getElementById("NH2_TCM_5_15_Neg").value;
        
        var NH2_RUTF_Adults15_17_Pos  = document.getElementById("NH2_RUTF_Adults15_17_Pos").value;
        var NH2_RUTF_Postnatal_Pos  = document.getElementById("NH2_RUTF_Postnatal_Pos").value;
        var NH2_RUTF_AllAdults_Neg  = document.getElementById("NH2_RUTF_AllAdults_Neg").value;
        var NH2_RUTF_0_59M_Pos = document.getElementById("NH2_RUTF_0_59M_Pos").value;
        var NH2_RUTF_5_15_Pos  = document.getElementById("NH2_RUTF_5_15_Pos").value;
        var NH2_RUTF_0_59M_Neg  = document.getElementById("NH2_RUTF_0_59M_Neg").value;
        var NH2_RUTF_5_15_Neg  = document.getElementById("NH2_RUTF_5_15_Neg").value;
        
        var NH2_RUSF_Adults15_17_Pos  = document.getElementById("NH2_RUSF_Adults15_17_Pos").value;
        var NH2_RUSF_Postnatal_Pos  = document.getElementById("NH2_RUSF_Postnatal_Pos").value;
        var NH2_RUSF_AllAdults_Neg  = document.getElementById("NH2_RUSF_AllAdults_Neg").value;
        var NH2_RUSF_0_59M_Pos  = document.getElementById("NH2_RUSF_0_59M_Pos").value;
        var NH2_RUSF_5_15_Pos  = document.getElementById("NH2_RUSF_5_15_Pos").value;
        var NH2_RUSF_0_59M_Neg  = document.getElementById("NH2_RUSF_0_59M_Neg").value;
        var NH2_RUSF_5_15_Neg  = document.getElementById("NH2_RUSF_5_15_Neg").value;
        
        var NH2_FBFCSB_Adults15_17_Pos  = document.getElementById("NH2_FBFCSB_Adults15_17_Pos").value;
        var NH2_FBFCSB_Postnatal_Pos  = document.getElementById("NH2_FBFCSB_Postnatal_Pos").value;
        var NH2_FBFCSB_AllAdults_Neg  = document.getElementById("NH2_FBFCSB_AllAdults_Neg").value;
        var NH2_FBFCSB_0_59M_Pos  = document.getElementById("NH2_FBFCSB_0_59M_Pos").value;
        var NH2_FBFCSB_5_15_Pos  = document.getElementById("NH2_FBFCSB_5_15_Pos").value;
        var NH2_FBFCSB_0_59M_Neg  = document.getElementById("NH2_FBFCSB_0_59M_Neg").value;
        var NH2_FBFCSB_5_15_Neg  = document.getElementById("NH2_FBFCSB_5_15_Neg").value;
        
        var NH2_LNS_Adults15_17_Pos  = document.getElementById("NH2_LNS_Adults15_17_Pos").value;
        var NH2_LNS_Postnatal_Pos  = document.getElementById("NH2_LNS_Postnatal_Pos").value;
        var NH2_LNS_AllAdults_Neg  = document.getElementById("NH2_LNS_AllAdults_Neg").value;
        var NH2_LNS_0_59M_Pos  = document.getElementById("NH2_LNS_0_59M_Pos").value;
        var NH2_LNS_5_15_Pos  = document.getElementById("NH2_LNS_5_15_Pos").value;
        var NH2_LNS_0_59M_Neg  = document.getElementById("NH2_LNS_0_59M_Neg").value;
        var NH2_LNS_5_15_Neg  = document.getElementById("NH2_LNS_5_15_Neg").value;
        
        var NH2_Micronutrients_Adults15_17_Pos  = document.getElementById("NH2_Micronutrients_Adults15_17_Pos").value;
        var NH2_Micronutrients_Postnatal_Pos  = document.getElementById("NH2_Micronutrients_Postnatal_Pos").value;
        var NH2_Micronutrients_AllAdults_Neg  = document.getElementById("NH2_Micronutrients_AllAdults_Neg").value;
        var NH2_Micronutrients_0_59M_Pos  = document.getElementById("NH2_Micronutrients_0_59M_Pos").value;
        var NH2_Micronutrients_5_15_Pos  = document.getElementById("NH2_Micronutrients_5_15_Pos").value;
        var NH2_Micronutrients_0_59M_Neg  = document.getElementById("NH2_Micronutrients_0_59M_Neg").value;
        var NH2_Micronutrients_5_15_Neg  = document.getElementById("NH2_Micronutrients_5_15_Neg").value;
        
        var NH2_Others_Adults15_17_Pos  = document.getElementById("NH2_Others_Adults15_17_Pos").value;
        var NH2_Others_Postnatal_Pos  = document.getElementById("NH2_Others_Postnatal_Pos").value;
        var NH2_Others_AllAdults_Neg  = document.getElementById("NH2_Others_AllAdults_Neg").value;
        var NH2_Others_0_59M_Pos  = document.getElementById("NH2_Others_0_59M_Pos").value;
        var NH2_Others_5_15_Pos  = document.getElementById("NH2_Others_5_15_Pos").value;
        var NH2_Others_0_59M_Neg  = document.getElementById("NH2_Others_0_59M_Neg").value;
        var NH2_Others_5_15_Neg  = document.getElementById("NH2_Others_5_15_Neg").value;
        
        if(NH2_TCM_Adults15_17_Pos==""){ NH2_TCM_Adults15_17_Pos=0;}
        if(NH2_TCM_Postnatal_Pos==""){ NH2_TCM_Postnatal_Pos=0;}
        if(NH2_TCM_AllAdults_Neg==""){ NH2_TCM_AllAdults_Neg=0;}
        if(NH2_TCM_0_59M_Pos==""){ NH2_TCM_0_59M_Pos=0;}
        if(NH2_TCM_5_15_Pos==""){ NH2_TCM_5_15_Pos=0;}
        if(NH2_TCM_0_59M_Neg==""){ NH2_TCM_0_59M_Neg=0;}
        if(NH2_TCM_5_15_Neg==""){ NH2_TCM_5_15_Neg=0;}
        
        if(NH2_RUTF_Adults15_17_Pos==""){ NH2_RUTF_Adults15_17_Pos=0;}
        if(NH2_RUTF_Postnatal_Pos==""){ NH2_RUTF_Postnatal_Pos=0;}
        if(NH2_RUTF_AllAdults_Neg==""){ NH2_RUTF_AllAdults_Neg=0;}
        if(NH2_RUTF_0_59M_Pos==""){ NH2_RUTF_0_59M_Pos=0;}
        if(NH2_RUTF_5_15_Pos==""){ NH2_RUTF_5_15_Pos=0;}
        if(NH2_RUTF_0_59M_Neg==""){ NH2_RUTF_0_59M_Neg=0;}
        if(NH2_RUTF_5_15_Neg==""){ NH2_RUTF_5_15_Neg=0;}
        
        if(NH2_RUSF_Adults15_17_Pos==""){ NH2_RUSF_Adults15_17_Pos=0;}
        if(NH2_RUSF_Postnatal_Pos==""){ NH2_RUSF_Postnatal_Pos=0;}
        if(NH2_RUSF_AllAdults_Neg==""){ NH2_RUSF_AllAdults_Neg=0;}
        if(NH2_RUSF_0_59M_Pos==""){ NH2_RUSF_0_59M_Pos=0;}
        if(NH2_RUSF_5_15_Pos==""){ NH2_RUSF_5_15_Pos=0;}
        if(NH2_RUSF_0_59M_Neg==""){ NH2_RUSF_0_59M_Neg=0;}
        if(NH2_RUSF_5_15_Neg==""){ NH2_RUSF_5_15_Neg=0;}
        
        if(NH2_FBFCSB_Adults15_17_Pos==""){ NH2_FBFCSB_Adults15_17_Pos=0;}
        if(NH2_FBFCSB_Postnatal_Pos==""){ NH2_FBFCSB_Postnatal_Pos=0;}
        if(NH2_FBFCSB_AllAdults_Neg==""){ NH2_FBFCSB_AllAdults_Neg=0;}
        if(NH2_FBFCSB_0_59M_Pos==""){ NH2_FBFCSB_0_59M_Pos=0;}
        if(NH2_FBFCSB_5_15_Pos==""){ NH2_FBFCSB_5_15_Pos=0;}
        if(NH2_FBFCSB_0_59M_Neg==""){ NH2_FBFCSB_0_59M_Neg=0;}
        if(NH2_FBFCSB_5_15_Neg==""){ NH2_FBFCSB_5_15_Neg=0;}
        
        if(NH2_LNS_Adults15_17_Pos==""){ NH2_LNS_Adults15_17_Pos=0;}
        if(NH2_LNS_Postnatal_Pos==""){ NH2_LNS_Postnatal_Pos=0;}
        if(NH2_LNS_AllAdults_Neg==""){ NH2_LNS_AllAdults_Neg=0;}
        if(NH2_LNS_0_59M_Pos==""){ NH2_LNS_0_59M_Pos=0;}
        if(NH2_LNS_5_15_Pos==""){ NH2_LNS_5_15_Pos=0;}
        if(NH2_LNS_0_59M_Neg==""){ NH2_LNS_0_59M_Neg=0;}
        if(NH2_LNS_5_15_Neg==""){ NH2_LNS_5_15_Neg=0;}
        
        if(NH2_Micronutrients_Adults15_17_Pos==""){ NH2_Micronutrients_Adults15_17_Pos=0;}
        if(NH2_Micronutrients_Postnatal_Pos==""){ NH2_Micronutrients_Postnatal_Pos=0;}
        if(NH2_Micronutrients_AllAdults_Neg==""){ NH2_Micronutrients_AllAdults_Neg=0;}
        if(NH2_Micronutrients_0_59M_Pos==""){ NH2_Micronutrients_0_59M_Pos=0;}
        if(NH2_Micronutrients_5_15_Pos==""){ NH2_Micronutrients_5_15_Pos=0;}
        if(NH2_Micronutrients_0_59M_Neg==""){ NH2_Micronutrients_0_59M_Neg=0;}
        if(NH2_Micronutrients_5_15_Neg==""){ NH2_Micronutrients_5_15_Neg=0;}
        
        if(NH2_Others_Adults15_17_Pos==""){ NH2_Others_Adults15_17_Pos=0;}
        if(NH2_Others_Postnatal_Pos==""){ NH2_Others_Postnatal_Pos=0;}
        if(NH2_Others_AllAdults_Neg==""){ NH2_Others_AllAdults_Neg=0;}
        if(NH2_Others_0_59M_Pos==""){ NH2_Others_0_59M_Pos=0;}
        if(NH2_Others_5_15_Pos==""){ NH2_Others_5_15_Pos=0;}
        if(NH2_Others_0_59M_Neg==""){ NH2_Others_0_59M_Neg=0;}
        if(NH2_Others_5_15_Neg==""){ NH2_Others_5_15_Neg=0;}
        
        var NH2_TCM_Total  = parseInt(NH2_TCM_Adults15_17_Pos)+ parseInt(NH2_TCM_Postnatal_Pos)+ parseInt(NH2_TCM_AllAdults_Neg)+ parseInt(NH2_TCM_0_59M_Pos)+ parseInt(NH2_TCM_5_15_Pos)+ parseInt(NH2_TCM_0_59M_Neg)+ parseInt(NH2_TCM_5_15_Neg);
        var NH2_RUTF_Total  =  parseInt(NH2_RUTF_Adults15_17_Pos)+ parseInt(NH2_RUTF_Postnatal_Pos)+ parseInt(NH2_RUTF_AllAdults_Neg)+ parseInt(NH2_RUTF_0_59M_Pos)+ parseInt(NH2_RUTF_5_15_Pos)+ parseInt(NH2_RUTF_0_59M_Neg)+ parseInt(NH2_RUTF_5_15_Neg);
        var NH2_RUSF_Total  =  parseInt(NH2_RUSF_Adults15_17_Pos)+ parseInt(NH2_RUSF_Postnatal_Pos)+ parseInt(NH2_RUSF_AllAdults_Neg)+ parseInt(NH2_RUSF_0_59M_Pos)+ parseInt(NH2_RUSF_5_15_Pos)+ parseInt(NH2_RUSF_0_59M_Neg)+ parseInt(NH2_RUSF_5_15_Neg);
        var NH2_FBFCSB_Total  =  parseInt(NH2_FBFCSB_Adults15_17_Pos)+ parseInt(NH2_FBFCSB_Postnatal_Pos)+ parseInt(NH2_FBFCSB_AllAdults_Neg)+ parseInt(NH2_FBFCSB_0_59M_Pos)+ parseInt(NH2_FBFCSB_5_15_Pos)+ parseInt(NH2_FBFCSB_0_59M_Neg)+ parseInt(NH2_FBFCSB_5_15_Neg);
        var NH2_LNS_Total  =  parseInt(NH2_LNS_Adults15_17_Pos)+ parseInt(NH2_LNS_Postnatal_Pos)+ parseInt(NH2_LNS_AllAdults_Neg)+ parseInt(NH2_LNS_0_59M_Pos)+ parseInt(NH2_LNS_5_15_Pos)+ parseInt(NH2_LNS_0_59M_Neg)+ parseInt(NH2_LNS_5_15_Neg);
        var NH2_Micronutrients_Total  =  parseInt(NH2_Micronutrients_Adults15_17_Pos)+ parseInt(NH2_Micronutrients_Postnatal_Pos)+ parseInt(NH2_Micronutrients_AllAdults_Neg)+ parseInt(NH2_Micronutrients_0_59M_Pos)+ parseInt(NH2_Micronutrients_5_15_Pos)+ parseInt(NH2_Micronutrients_0_59M_Neg)+ parseInt(NH2_Micronutrients_5_15_Neg);
        var NH2_Others_Total  =  parseInt(NH2_Others_Adults15_17_Pos)+ parseInt(NH2_Others_Postnatal_Pos)+ parseInt(NH2_Others_AllAdults_Neg)+ parseInt(NH2_Others_0_59M_Pos)+ parseInt(NH2_Others_5_15_Pos)+ parseInt(NH2_Others_0_59M_Neg)+ parseInt(NH2_Others_5_15_Neg);
                
                
                document.getElementById("NH2_TCM_Total").value = NH2_TCM_Total;
                document.getElementById("NH2_RUTF_Total").value = NH2_RUTF_Total;
                document.getElementById("NH2_RUSF_Total").value = NH2_RUSF_Total;
                document.getElementById("NH2_FBFCSB_Total").value = NH2_FBFCSB_Total;
                document.getElementById("NH2_LNS_Total").value = NH2_LNS_Total;
                document.getElementById("NH2_Micronutrients_Total").value = NH2_Micronutrients_Total;
                document.getElementById("NH2_Others_Total").value = NH2_Others_Total;
                
        var NH2_Total_Adults15_17_Pos  = parseInt(NH2_TCM_Adults15_17_Pos)+parseInt(NH2_RUTF_Adults15_17_Pos)+parseInt(NH2_RUSF_Adults15_17_Pos)+parseInt(NH2_FBFCSB_Adults15_17_Pos)+parseInt(NH2_LNS_Adults15_17_Pos)+parseInt(NH2_Micronutrients_Adults15_17_Pos)+parseInt(NH2_Others_Adults15_17_Pos);
        var NH2_Total_Postnatal_Pos  = parseInt(NH2_TCM_Postnatal_Pos)+parseInt(NH2_RUTF_Postnatal_Pos)+parseInt(NH2_RUSF_Postnatal_Pos)+parseInt(NH2_FBFCSB_Postnatal_Pos)+parseInt(NH2_LNS_Postnatal_Pos)+parseInt(NH2_Micronutrients_Postnatal_Pos)+parseInt(NH2_Others_Postnatal_Pos);
        var NH2_Total_AllAdults_Neg  = parseInt(NH2_TCM_AllAdults_Neg)+parseInt(NH2_RUTF_AllAdults_Neg)+parseInt(NH2_RUSF_AllAdults_Neg)+parseInt(NH2_FBFCSB_AllAdults_Neg)+parseInt(NH2_LNS_AllAdults_Neg)+parseInt(NH2_Micronutrients_AllAdults_Neg)+parseInt(NH2_Others_AllAdults_Neg);
        var NH2_Total_0_59M_Pos  = parseInt(NH2_TCM_0_59M_Pos)+parseInt(NH2_RUTF_0_59M_Pos)+parseInt(NH2_RUSF_0_59M_Pos)+parseInt(NH2_FBFCSB_0_59M_Pos)+parseInt(NH2_LNS_0_59M_Pos)+parseInt(NH2_Micronutrients_0_59M_Pos)+parseInt(NH2_Others_0_59M_Pos);
        var NH2_Total_5_15_Pos  = parseInt(NH2_TCM_5_15_Pos)+parseInt(NH2_RUTF_5_15_Pos)+parseInt(NH2_RUSF_5_15_Pos)+parseInt(NH2_FBFCSB_5_15_Pos)+parseInt(NH2_LNS_5_15_Pos)+parseInt(NH2_Micronutrients_5_15_Pos)+parseInt(NH2_Others_5_15_Pos);
        var NH2_Total_0_59M_Neg  = parseInt(NH2_TCM_0_59M_Neg)+parseInt(NH2_RUTF_0_59M_Neg)+parseInt(NH2_RUSF_0_59M_Neg)+parseInt(NH2_FBFCSB_0_59M_Neg)+parseInt(NH2_LNS_0_59M_Neg)+parseInt(NH2_Micronutrients_0_59M_Neg)+parseInt(NH2_Others_0_59M_Neg);
        var NH2_Total_5_15_Neg  = parseInt(NH2_TCM_5_15_Neg)+parseInt(NH2_RUTF_5_15_Neg)+parseInt(NH2_RUSF_5_15_Neg)+parseInt(NH2_FBFCSB_5_15_Neg)+parseInt(NH2_LNS_5_15_Neg)+parseInt(NH2_Micronutrients_5_15_Neg)+parseInt(NH2_Others_5_15_Neg);
        var NH2_Grand_Total  = parseInt(NH2_Total_Adults15_17_Pos)+parseInt(NH2_Total_Postnatal_Pos)+parseInt(NH2_Total_AllAdults_Neg)+parseInt(NH2_Total_0_59M_Pos)+parseInt(NH2_Total_5_15_Pos)+parseInt(NH2_Total_0_59M_Neg)+parseInt(NH2_Total_5_15_Neg);
                   
        document.getElementById("NH2_Total_Adults15_17_Pos").value = NH2_Total_Adults15_17_Pos;
        document.getElementById("NH2_Total_Postnatal_Pos").value = NH2_Total_Postnatal_Pos;
        document.getElementById("NH2_Total_AllAdults_Neg").value = NH2_Total_AllAdults_Neg;
        document.getElementById("NH2_Total_0_59M_Pos").value = NH2_Total_0_59M_Pos;
        document.getElementById("NH2_Total_5_15_Pos").value = NH2_Total_5_15_Pos;
        document.getElementById("NH2_Total_0_59M_Neg").value = NH2_Total_0_59M_Neg;
        document.getElementById("NH2_Total_5_15_Neg").value = NH2_Total_5_15_Neg;
        document.getElementById("NH2_Grand_Total").value = NH2_Grand_Total;
        
        
    if(elem=="NH2_TCM_Adults15_17_Pos"){ 
      autosave('NH2_Total_Adults15_17_Pos');                  
    autosave('NH2_TCM_Total');
    }
        else if(elem=="NH2_TCM_Postnatal_Pos"){ 
        autosave('NH2_Total_Postnatal_Pos');
    autosave('NH2_TCM_Total');
    }
        else if(elem=="NH2_TCM_AllAdults_Neg"){ 
      autosave('NH2_Total_AllAdults_Neg');  
    autosave('NH2_TCM_Total');
    }
        else if(elem=="NH2_TCM_0_59M_Pos"){ 
       autosave('NH2_Total_0_59M_Pos'); 
    autosave('NH2_TCM_Total');
    }
        else if(elem=="NH2_TCM_5_15_Pos"){ 
       autosave('NH2_Total_5_15_Pos'); 
    autosave('NH2_TCM_Total');
    }
        else if(elem=="NH2_TCM_0_59M_Neg"){ 
       autosave('NH2_Total_0_59M_Neg'); 
    autosave('NH2_TCM_Total');
    }
        else if(elem=="NH2_TCM_5_15_Neg"){ 
       autosave('NH2_Total_5_15_Neg'); 
    autosave('NH2_TCM_Total');
    }
        
        else if(elem=="NH2_RUTF_Adults15_17_Pos"){ 
       autosave('NH2_Total_Adults15_17_Pos'); 
    autosave('NH2_RUTF_Total');
    }
        else if(elem=="NH2_RUTF_Postnatal_Pos"){ 
      autosave('NH2_Total_Postnatal_Pos');  
    autosave('NH2_RUTF_Total');
    }
        else if(elem=="NH2_RUTF_AllAdults_Neg"){ 
       autosave('NH2_Total_AllAdults_Neg'); 
    autosave('NH2_RUTF_Total');
    }
        else if(elem=="NH2_RUTF_0_59M_Pos"){ 
       autosave('NH2_Total_0_59M_Pos'); 
    autosave('NH2_RUTF_Total');
    }
        else if(elem=="NH2_RUTF_5_15_Pos"){ 
      autosave('NH2_Total_5_15_Pos');  
    autosave('NH2_RUTF_Total');
    }
        else if(elem=="NH2_RUTF_0_59M_Neg"){ 
        autosave('NH2_Total_0_59M_Neg');
    autosave('NH2_RUTF_Total');
    }
        else if(elem=="NH2_RUTF_5_15_Neg"){ 
        autosave('NH2_Total_5_15_Neg');
    autosave('NH2_RUTF_Total');
    }
        
        else if(elem=="NH2_RUSF_Adults15_17_Pos"){ 
        autosave('NH2_Total_Adults15_17_Pos');
    autosave('NH2_RUSF_Total');
    }
        else if(elem=="NH2_RUSF_Postnatal_Pos"){ 
     autosave('NH2_Total_Postnatal_Pos');   
    autosave('NH2_RUSF_Total');
    }
        else if(elem=="NH2_RUSF_AllAdults_Neg"){ 
       autosave('NH2_Total_AllAdults_Neg'); 
    autosave('NH2_RUSF_Total');
    }
        else if(elem=="NH2_RUSF_0_59M_Pos"){ 
       autosave('NH2_Total_0_59M_Pos'); 
    autosave('NH2_RUSF_Total');
    }
        else if(elem=="NH2_RUSF_5_15_Pos"){ 
        autosave('NH2_Total_5_15_Pos');
    autosave('NH2_RUSF_Total');
    }
        else if(elem=="NH2_RUSF_0_59M_Neg"){ 
       autosave('NH2_Total_0_59M_Neg'); 
    autosave('NH2_RUSF_Total');
    }
        else if(elem=="NH2_RUSF_5_15_Neg"){ 
      autosave('NH2_Total_5_15_Neg');  
    autosave('NH2_RUSF_Total');
    }
        
        else if(elem=="NH2_FBFCSB_Adults15_17_Pos"){ 
        autosave('NH2_Total_Adults15_17_Pos');
    autosave('NH2_FBFCSB_Total');
    }
        else if(elem=="NH2_FBFCSB_Postnatal_Pos"){ 
      autosave('NH2_Total_Postnatal_Pos');  
    autosave('NH2_FBFCSB_Total');
    }
        else if(elem=="NH2_FBFCSB_AllAdults_Neg"){ 
      autosave('NH2_Total_AllAdults_Neg');  
    autosave('NH2_FBFCSB_Total');
    }
        else if(elem=="NH2_FBFCSB_0_59M_Pos"){ 
        autosave('NH2_Total_0_59M_Pos');
    autosave('NH2_FBFCSB_Total');
    }
        else if(elem=="NH2_FBFCSB_5_15_Pos"){ 
        autosave('NH2_Total_5_15_Pos');
    autosave('NH2_FBFCSB_Total');
    }
        else if(elem=="NH2_FBFCSB_0_59M_Neg"){ 
       autosave('NH2_Total_0_59M_Neg'); 
    autosave('NH2_FBFCSB_Total');
    }
        else if(elem=="NH2_FBFCSB_5_15_Neg"){ 
        autosave('NH2_Total_5_15_Neg');
    autosave('NH2_FBFCSB_Total');
    }
        
        else if(elem=="NH2_LNS_Adults15_17_Pos"){ 
        autosave('NH2_Total_Adults15_17_Pos');
    autosave('NH2_LNS_Total');
    }
        else if(elem=="NH2_LNS_Postnatal_Pos"){ 
      autosave('NH2_Total_Postnatal_Pos');  
    autosave('NH2_LNS_Total');
    }
        else if(elem=="NH2_LNS_AllAdults_Neg"){ 
      autosave('NH2_Total_AllAdults_Neg');  
    autosave('NH2_LNS_Total');
    }
        else if(elem=="NH2_LNS_0_59M_Pos"){ 
       autosave('NH2_Total_0_59M_Pos'); 
    autosave('NH2_LNS_Total');
    }
        else if(elem=="NH2_LNS_5_15_Pos"){ 
       autosave('NH2_Total_5_15_Pos'); 
    autosave('NH2_LNS_Total');
    }
        else if(elem=="NH2_LNS_0_59M_Neg"){ 
       autosave('NH2_Total_0_59M_Neg'); 
    autosave('NH2_LNS_Total');
    }
        else if(elem=="NH2_LNS_5_15_Neg"){ 
       autosave('NH2_Total_5_15_Neg'); 
    autosave('NH2_LNS_Total');
    }
        
        else if(elem=="NH2_Micronutrients_Adults15_17_Pos"){ 
        autosave('NH2_Total_Adults15_17_Pos');
    autosave('NH2_Micronutrients_Total');
    }
        else if(elem=="NH2_Micronutrients_Postnatal_Pos"){ 
      autosave('NH2_Total_Postnatal_Pos');  
    autosave('NH2_Micronutrients_Total');
    }
        else if(elem=="NH2_Micronutrients_AllAdults_Neg"){ 
       autosave('NH2_Total_AllAdults_Neg');
    autosave('NH2_Micronutrients_Total');
    }
        else if(elem=="NH2_Micronutrients_0_59M_Pos"){ 
        autosave('NH2_Total_0_59M_Pos');
    autosave('NH2_Micronutrients_Total');
    }
        else if(elem=="NH2_Micronutrients_5_15_Pos"){ 
      autosave('NH2_Total_5_15_Pos');  
    autosave('NH2_Micronutrients_Total');
    }
        else if(elem=="NH2_Micronutrients_0_59M_Neg"){ 
        autosave('NH2_Total_0_59M_Neg');
    autosave('NH2_Micronutrients_Total');
    }
        else if(elem=="NH2_Micronutrients_5_15_Neg"){ 
       autosave('NH2_Total_5_15_Neg'); 
    autosave('NH2_Micronutrients_Total');
    }
        
        else if(elem=="NH2_Others_Adults15_17_Pos"){ 
        autosave('NH2_Total_Adults15_17_Pos');
        autosave('NH2_Others_Total');
    }
        else if(elem=="NH2_Others_Postnatal_Pos"){ 
     autosave('NH2_Total_Postnatal_Pos');   
    autosave('NH2_Others_Total');
    }
        else if(elem=="NH2_Others_AllAdults_Neg"){ 
       autosave('NH2_Total_AllAdults_Neg'); 
    autosave('NH2_Others_Total');
    }
        else if(elem=="NH2_Others_0_59M_Pos"){ 
       autosave('NH2_Total_0_59M_Pos'); 
    autosave('NH2_Others_Total');
    }
        else if(elem=="NH2_Others_5_15_Pos"){ 
      autosave('NH2_Total_5_15_Pos');  
    autosave('NH2_Others_Total');
    }
        else if(elem=="NH2_Others_0_59M_Neg"){ 
        autosave('NH2_Total_0_59M_Neg');
     autosave('NH2_Others_Total');   
    }
        else if(elem=="NH2_Others_5_15_Neg"){ 
       autosave('NH2_Total_5_15_Neg'); 
        autosave('NH2_Others_Total');  
    }
    
        autosave('NH2_Grand_Total');
        
}

function TreatmentOutcome(elem){
        var TO_GainingWeight_Adults15_17_Pos  = document.getElementById("TO_GainingWeight_Adults15_17_Pos").value;
        var TO_GainingWeight_Postnatal_Pos  = document.getElementById("TO_GainingWeight_Postnatal_Pos").value;
        var TO_GainingWeight_AllAdults_Neg  = document.getElementById("TO_GainingWeight_AllAdults_Neg").value;
        var TO_GainingWeight_0_59M_Pos  = document.getElementById("TO_GainingWeight_0_59M_Pos").value;
        var TO_GainingWeight_5_15_Pos  = document.getElementById("TO_GainingWeight_5_15_Pos").value;
        var TO_GainingWeight_0_59M_Neg  = document.getElementById("TO_GainingWeight_0_59M_Neg").value;
        var TO_GainingWeight_5_15_Neg  = document.getElementById("TO_GainingWeight_5_15_Neg").value;
        
        var TO_LosingWeight_Adults15_17_Pos  = document.getElementById("TO_LosingWeight_Adults15_17_Pos").value;
        var TO_LosingWeight_Postnatal_Pos  = document.getElementById("TO_LosingWeight_Postnatal_Pos").value;
        var TO_LosingWeight_AllAdults_Neg  = document.getElementById("TO_LosingWeight_AllAdults_Neg").value;
        var TO_LosingWeight_0_59M_Pos  = document.getElementById("TO_LosingWeight_0_59M_Pos").value;
        var TO_LosingWeight_5_15_Pos  = document.getElementById("TO_LosingWeight_5_15_Pos").value;
        var TO_LosingWeight_0_59M_Neg  = document.getElementById("TO_LosingWeight_0_59M_Neg").value;
        var TO_LosingWeight_5_15_Neg  = document.getElementById("TO_LosingWeight_5_15_Neg").value;
        
        var TO_StaticWeight_Adults15_17_Pos = document.getElementById("TO_StaticWeight_Adults15_17_Pos").value;
        var TO_StaticWeight_Postnatal_Pos  = document.getElementById("TO_StaticWeight_Postnatal_Pos").value;
        var TO_StaticWeight_AllAdults_Neg  = document.getElementById("TO_StaticWeight_AllAdults_Neg").value;
        var TO_StaticWeight_0_59M_Pos  = document.getElementById("TO_StaticWeight_0_59M_Pos").value;
        var TO_StaticWeight_5_15_Pos  = document.getElementById("TO_StaticWeight_5_15_Pos").value;
        var TO_StaticWeight_0_59M_Neg  = document.getElementById("TO_StaticWeight_0_59M_Neg").value;
        var TO_StaticWeight_5_15_Neg  = document.getElementById("TO_StaticWeight_5_15_Neg").value;
        
        var TO_Cured_Adults15_17_Pos  = document.getElementById("TO_Cured_Adults15_17_Pos").value;
        var TO_Cured_Postnatal_Pos  = document.getElementById("TO_Cured_Postnatal_Pos").value;
        var TO_Cured_AllAdults_Neg  = document.getElementById("TO_Cured_AllAdults_Neg").value;
        var TO_Cured_0_59M_Pos  = document.getElementById("TO_Cured_0_59M_Pos").value;
        var TO_Cured_5_15_Pos  = document.getElementById("TO_Cured_5_15_Pos").value;
        var TO_Cured_0_59M_Neg  = document.getElementById("TO_Cured_0_59M_Neg").value;
        var TO_Cured_5_15_Neg  = document.getElementById("TO_Cured_5_15_Neg").value;
        
        var TO_Discharged_Adults15_17_Pos  = document.getElementById("TO_Discharged_Adults15_17_Pos").value;
        var TO_Discharged_Postnatal_Pos  = document.getElementById("TO_Discharged_Postnatal_Pos").value;
        var TO_Discharged_AllAdults_Neg  = document.getElementById("TO_Discharged_AllAdults_Neg").value;
        var TO_Discharged_0_59M_Pos  = document.getElementById("TO_Discharged_0_59M_Pos").value;
        var TO_Discharged_5_15_Pos  = document.getElementById("TO_Discharged_5_15_Pos").value;
        var TO_Discharged_0_59M_Neg  = document.getElementById("TO_Discharged_0_59M_Neg").value;
        var TO_Discharged_5_15_Neg  = document.getElementById("TO_Discharged_5_15_Neg").value;
        
        var TO_RNS_Adults15_17_Pos  = document.getElementById("TO_RNS_Adults15_17_Pos").value;
        var TO_RNS_Postnatal_Pos  = document.getElementById("TO_RNS_Postnatal_Pos").value;
        var TO_RNS_AllAdults_Neg  = document.getElementById("TO_RNS_AllAdults_Neg").value;
        var TO_RNS_0_59M_Pos  = document.getElementById("TO_RNS_0_59M_Pos").value;
        var TO_RNS_5_15_Pos  = document.getElementById("TO_RNS_5_15_Pos").value;
        var TO_RNS_0_59M_Neg = document.getElementById("TO_RNS_0_59M_Neg").value;
        var TO_RNS_5_15_Neg  = document.getElementById("TO_RNS_5_15_Neg").value;
         
    
        if(TO_GainingWeight_Adults15_17_Pos==""){ TO_GainingWeight_Adults15_17_Pos=0;}
        if(TO_GainingWeight_Postnatal_Pos==""){ TO_GainingWeight_Postnatal_Pos=0;}
        if(TO_GainingWeight_AllAdults_Neg==""){ TO_GainingWeight_AllAdults_Neg=0;}
        if(TO_GainingWeight_0_59M_Pos==""){ TO_GainingWeight_0_59M_Pos=0;}
        if(TO_GainingWeight_5_15_Pos==""){ TO_GainingWeight_5_15_Pos=0;}
        if(TO_GainingWeight_0_59M_Neg==""){ TO_GainingWeight_0_59M_Neg=0;}
        if(TO_GainingWeight_5_15_Neg==""){ TO_GainingWeight_5_15_Neg=0;}
        
        if(TO_LosingWeight_Adults15_17_Pos==""){ TO_LosingWeight_Adults15_17_Pos=0;}
        if(TO_LosingWeight_Postnatal_Pos==""){ TO_LosingWeight_Postnatal_Pos=0;}
        if(TO_LosingWeight_AllAdults_Neg==""){ TO_LosingWeight_AllAdults_Neg=0;}
        if(TO_LosingWeight_0_59M_Pos==""){ TO_LosingWeight_0_59M_Pos=0;}
        if(TO_LosingWeight_5_15_Pos==""){ TO_LosingWeight_5_15_Pos=0;}
        if(TO_LosingWeight_0_59M_Neg==""){ TO_LosingWeight_0_59M_Neg=0;}
        if(TO_LosingWeight_5_15_Neg==""){ TO_LosingWeight_5_15_Neg=0;}
        
        if(TO_StaticWeight_Adults15_17_Pos==""){ TO_StaticWeight_Adults15_17_Pos=0;}
        if(TO_StaticWeight_Postnatal_Pos==""){ TO_StaticWeight_Postnatal_Pos=0;}
        if(TO_StaticWeight_AllAdults_Neg==""){ TO_StaticWeight_AllAdults_Neg=0;}
        if(TO_StaticWeight_0_59M_Pos==""){ TO_StaticWeight_0_59M_Pos=0;}
        if(TO_StaticWeight_5_15_Pos==""){ TO_StaticWeight_5_15_Pos=0;}
        if(TO_StaticWeight_0_59M_Neg==""){ TO_StaticWeight_0_59M_Neg=0;}
        if(TO_StaticWeight_5_15_Neg==""){ TO_StaticWeight_5_15_Neg=0;}
        
        if(TO_Cured_Adults15_17_Pos==""){ TO_Cured_Adults15_17_Pos=0;}
        if(TO_Cured_Postnatal_Pos==""){ TO_Cured_Postnatal_Pos=0;}
        if(TO_Cured_AllAdults_Neg==""){ TO_Cured_AllAdults_Neg=0;}
        if(TO_Cured_0_59M_Pos==""){ TO_Cured_0_59M_Pos=0;}
        if(TO_Cured_5_15_Pos==""){ TO_Cured_5_15_Pos=0;}
        if(TO_Cured_0_59M_Neg==""){ TO_Cured_0_59M_Neg=0;}
        if(TO_Cured_5_15_Neg==""){ TO_Cured_5_15_Neg=0;}
        
        if(TO_Discharged_Adults15_17_Pos==""){ TO_Discharged_Adults15_17_Pos=0;}
        if(TO_Discharged_Postnatal_Pos==""){ TO_Discharged_Postnatal_Pos=0;}
        if(TO_Discharged_AllAdults_Neg==""){ TO_Discharged_AllAdults_Neg=0;}
        if(TO_Discharged_0_59M_Pos==""){ TO_Discharged_0_59M_Pos=0;}
        if(TO_Discharged_5_15_Pos==""){ TO_Discharged_5_15_Pos=0;}
        if(TO_Discharged_0_59M_Neg==""){ TO_Discharged_0_59M_Neg=0;}
        if(TO_Discharged_5_15_Neg==""){ TO_Discharged_5_15_Neg=0;}
        
        if(TO_RNS_Adults15_17_Pos==""){ TO_RNS_Adults15_17_Pos=0;}
        if(TO_RNS_Postnatal_Pos==""){ TO_RNS_Postnatal_Pos=0;}
        if(TO_RNS_AllAdults_Neg==""){ TO_RNS_AllAdults_Neg=0;}
        if(TO_RNS_0_59M_Pos==""){ TO_RNS_0_59M_Pos=0;}
        if(TO_RNS_5_15_Pos==""){ TO_RNS_5_15_Pos=0;}
        if(TO_RNS_0_59M_Neg==""){ TO_RNS_0_59M_Neg=0;}
        if(TO_RNS_5_15_Neg==""){ TO_RNS_5_15_Neg=0;}
        
        
        var TO_GainingWeight_Total  =parseInt(TO_GainingWeight_Adults15_17_Pos)+parseInt(TO_GainingWeight_Postnatal_Pos)+parseInt(TO_GainingWeight_AllAdults_Neg)+parseInt(TO_GainingWeight_0_59M_Pos)+parseInt(TO_GainingWeight_5_15_Pos)+parseInt(TO_GainingWeight_0_59M_Neg)+parseInt(TO_GainingWeight_5_15_Neg); 
        var TO_LosingWeight_Total = parseInt(TO_LosingWeight_Adults15_17_Pos)+ parseInt(TO_LosingWeight_Postnatal_Pos)+ parseInt(TO_LosingWeight_AllAdults_Neg)+ parseInt(TO_LosingWeight_0_59M_Pos)+ parseInt(TO_LosingWeight_5_15_Pos)+ parseInt(TO_LosingWeight_0_59M_Neg)+ parseInt(TO_LosingWeight_5_15_Neg);
        var TO_StaticWeight_Total  =  parseInt(TO_StaticWeight_Adults15_17_Pos)+ parseInt(TO_StaticWeight_Postnatal_Pos)+ parseInt(TO_StaticWeight_AllAdults_Neg)+ parseInt(TO_StaticWeight_0_59M_Pos)+ parseInt(TO_StaticWeight_5_15_Pos)+ parseInt(TO_StaticWeight_0_59M_Neg)+ parseInt(TO_StaticWeight_5_15_Neg);
        var TO_Cured_Total  =  parseInt(TO_Cured_Adults15_17_Pos)+ parseInt(TO_Cured_Postnatal_Pos)+ parseInt(TO_Cured_AllAdults_Neg)+ parseInt(TO_Cured_0_59M_Pos)+ parseInt(TO_Cured_5_15_Pos)+ parseInt(TO_Cured_0_59M_Neg)+ parseInt(TO_Cured_5_15_Neg);
        var TO_Discharged_Total  =  parseInt(TO_Discharged_Adults15_17_Pos)+ parseInt(TO_Discharged_Postnatal_Pos)+ parseInt(TO_Discharged_AllAdults_Neg)+ parseInt(TO_Discharged_0_59M_Pos)+ parseInt(TO_Discharged_5_15_Pos)+ parseInt(TO_Discharged_0_59M_Neg)+ parseInt(TO_Discharged_5_15_Neg);
        var TO_RNS_Total  =  parseInt(TO_RNS_Adults15_17_Pos)+ parseInt(TO_RNS_Postnatal_Pos)+ parseInt(TO_RNS_AllAdults_Neg)+ parseInt(TO_RNS_0_59M_Pos)+ parseInt(TO_RNS_5_15_Pos)+ parseInt(TO_RNS_0_59M_Neg)+ parseInt(TO_RNS_5_15_Neg);
        
        document.getElementById("TO_GainingWeight_Total").value = TO_GainingWeight_Total; 
        document.getElementById("TO_LosingWeight_Total").value = TO_LosingWeight_Total;
        document.getElementById("TO_StaticWeight_Total").value = TO_StaticWeight_Total;
        document.getElementById("TO_Cured_Total").value = TO_Cured_Total;
        document.getElementById("TO_Discharged_Total").value = TO_Discharged_Total;
        document.getElementById("TO_RNS_Total").value = TO_RNS_Total;
        var TO_Total_Adults15_17_Pos  = parseInt(TO_GainingWeight_Adults15_17_Pos)+parseInt(TO_LosingWeight_Adults15_17_Pos)+parseInt(TO_StaticWeight_Adults15_17_Pos)+parseInt(TO_Cured_Adults15_17_Pos)+parseInt(TO_Discharged_Adults15_17_Pos)+parseInt(TO_RNS_Adults15_17_Pos);
        var TO_Total_Postnatal_Pos  =  parseInt(TO_GainingWeight_Postnatal_Pos)+parseInt(TO_LosingWeight_Postnatal_Pos)+parseInt(TO_StaticWeight_Postnatal_Pos)+parseInt(TO_Cured_Postnatal_Pos)+parseInt(TO_Discharged_Postnatal_Pos)+parseInt(TO_RNS_Postnatal_Pos);
        var TO_Total_AllAdults_Neg  =  parseInt(TO_GainingWeight_AllAdults_Neg)+parseInt(TO_LosingWeight_AllAdults_Neg)+parseInt(TO_StaticWeight_AllAdults_Neg)+parseInt(TO_Cured_AllAdults_Neg)+parseInt(TO_Discharged_AllAdults_Neg)+parseInt(TO_RNS_AllAdults_Neg);
        var TO_Total_0_59M_Pos  =  parseInt(TO_GainingWeight_0_59M_Pos)+parseInt(TO_LosingWeight_0_59M_Pos)+parseInt(TO_StaticWeight_0_59M_Pos)+parseInt(TO_Cured_0_59M_Pos)+parseInt(TO_Discharged_0_59M_Pos)+parseInt(TO_RNS_0_59M_Pos);
        var TO_Total_5_15_Pos  =  parseInt(TO_GainingWeight_5_15_Pos)+parseInt(TO_LosingWeight_5_15_Pos)+parseInt(TO_StaticWeight_5_15_Pos)+parseInt(TO_Cured_5_15_Pos)+parseInt(TO_Discharged_5_15_Pos)+parseInt(TO_RNS_5_15_Pos);
        var TO_Total_0_59M_Neg  =  parseInt(TO_GainingWeight_0_59M_Neg)+parseInt(TO_LosingWeight_0_59M_Neg)+parseInt(TO_StaticWeight_0_59M_Neg)+parseInt(TO_Cured_0_59M_Neg)+parseInt(TO_Discharged_0_59M_Neg)+parseInt(TO_RNS_0_59M_Neg);
        var TO_Total_5_15_Neg  =  parseInt(TO_GainingWeight_5_15_Neg)+parseInt(TO_LosingWeight_5_15_Neg)+parseInt(TO_StaticWeight_5_15_Neg)+parseInt(TO_Cured_5_15_Neg)+parseInt(TO_Discharged_5_15_Neg)+parseInt(TO_RNS_5_15_Neg);
        var TO_Grand_Total  =  parseInt(TO_Total_Adults15_17_Pos)+parseInt(TO_Total_Postnatal_Pos)+parseInt(TO_Total_AllAdults_Neg)+parseInt(TO_Total_0_59M_Pos)+parseInt(TO_Total_5_15_Pos)+parseInt(TO_Total_0_59M_Neg)+parseInt(TO_Total_5_15_Neg);
        
        document.getElementById("TO_Total_Adults15_17_Pos").value = TO_Total_Adults15_17_Pos;
        document.getElementById("TO_Total_Postnatal_Pos").value = TO_Total_Postnatal_Pos;
        document.getElementById("TO_Total_AllAdults_Neg").value = TO_Total_AllAdults_Neg;
        document.getElementById("TO_Total_0_59M_Pos").value = TO_Total_0_59M_Pos;
        document.getElementById("TO_Total_5_15_Pos").value = TO_Total_5_15_Pos;
        document.getElementById("TO_Total_0_59M_Neg").value = TO_Total_0_59M_Neg;
        document.getElementById("TO_Total_5_15_Neg").value = TO_Total_5_15_Neg;
        document.getElementById("TO_Grand_Total").value = TO_Grand_Total;  
        
        if(elem=="TO_GainingWeight_Adults15_17_Pos"){
        autosave('TO_Total_Adults15_17_Pos');
    autosave('TO_GainingWeight_Total');
    }
        else if(elem=="TO_GainingWeight_Postnatal_Pos"){ 
        autosave('TO_Total_Postnatal_Pos');
    autosave('TO_GainingWeight_Total');
    }
        else if(elem=="TO_GainingWeight_AllAdults_Neg"){ 
        autosave('TO_Total_AllAdults_Neg'); 
    autosave('TO_GainingWeight_Total');
    }
        else if(elem=="TO_GainingWeight_0_59M_Pos"){
      autosave('TO_Total_0_59M_Pos');  
    autosave('TO_GainingWeight_Total');
    }
        else if(elem=="TO_GainingWeight_5_15_Pos"){
        autosave('TO_Total_5_15_Pos'); 
    autosave('TO_GainingWeight_Total');
    }
        else if(elem=="TO_GainingWeight_0_59M_Neg"){
        autosave('TO_Total_0_59M_Neg'); 
    autosave('TO_GainingWeight_Total');
    }
        else if(elem=="TO_GainingWeight_5_15_Neg"){ 
        autosave('TO_Total_5_15_Neg'); 
    autosave('TO_GainingWeight_Total');
    }
        
        else if(elem=="TO_LosingWeight_Adults15_17_Pos"){ 
        autosave('TO_Total_Adults15_17_Pos');
    autosave('TO_LosingWeight_Total'); 
    }
        else if(elem=="TO_LosingWeight_Postnatal_Pos"){ 
        autosave('TO_Total_Postnatal_Pos');
    autosave('TO_LosingWeight_Total'); 
    }
        else if(elem=="TO_LosingWeight_AllAdults_Neg"){ 
       autosave('TO_Total_AllAdults_Neg');  
    autosave('TO_LosingWeight_Total'); 
    }
        else if(elem=="TO_LosingWeight_0_59M_Pos"){ 
        autosave('TO_Total_0_59M_Pos');
    autosave('TO_LosingWeight_Total'); 
    }
        else if(elem=="TO_LosingWeight_5_15_Pos"){ 
        autosave('TO_Total_5_15_Pos'); 
    autosave('TO_LosingWeight_Total'); 
    }
        else if(elem=="TO_LosingWeight_0_59M_Neg"){ 
        autosave('TO_Total_0_59M_Neg'); 
    autosave('TO_LosingWeight_Total'); 
    }
        else if(elem=="TO_LosingWeight_5_15_Neg"){ 
        autosave('TO_Total_5_15_Neg'); 
    autosave('TO_LosingWeight_Total'); 
    }
        
        else if(elem=="TO_StaticWeight_Adults15_17_Pos"){ 
        autosave('TO_Total_Adults15_17_Pos');
    autosave('TO_StaticWeight_Total'); 
    }
        else if(elem=="TO_StaticWeight_Postnatal_Pos"){ 
        autosave('TO_Total_Postnatal_Pos');
    autosave('TO_StaticWeight_Total'); 
    }
        else if(elem=="TO_StaticWeight_AllAdults_Neg"){ 
        autosave('TO_Total_AllAdults_Neg'); 
    autosave('TO_StaticWeight_Total'); 
    }
        else if(elem=="TO_StaticWeight_0_59M_Pos"){ 
        autosave('TO_Total_0_59M_Pos');
    autosave('TO_StaticWeight_Total'); 
    }
        else if(elem=="TO_StaticWeight_5_15_Pos"){ 
        autosave('TO_Total_5_15_Pos'); 
    autosave('TO_StaticWeight_Total'); 
    }
        else if(elem=="TO_StaticWeight_0_59M_Neg"){ 
        autosave('TO_Total_0_59M_Neg'); 
    autosave('TO_StaticWeight_Total'); 
    }
        else if(elem=="TO_StaticWeight_5_15_Neg"){ 
        autosave('TO_Total_5_15_Neg'); 
    autosave('TO_StaticWeight_Total'); 
    }
        
        else if(elem=="TO_Cured_Adults15_17_Pos"){ 
        autosave('TO_Total_Adults15_17_Pos');
    autosave('TO_Cured_Total');
    }
        else if(elem=="TO_Cured_Postnatal_Pos"){ 
        autosave('TO_Total_Postnatal_Pos');
    autosave('TO_Cured_Total');
    }
        else if(elem=="TO_Cured_AllAdults_Neg"){ 
        autosave('TO_Total_AllAdults_Neg'); 
    autosave('TO_Cured_Total');
    }
        else if(elem=="TO_Cured_0_59M_Pos"){
        autosave('TO_Total_0_59M_Pos');
    autosave('TO_Cured_Total');
    }
        else if(elem=="TO_Cured_5_15_Pos"){
        autosave('TO_Total_5_15_Pos'); 
    autosave('TO_Cured_Total');
    }
        else if(elem=="TO_Cured_0_59M_Neg"){ 
        autosave('TO_Total_0_59M_Neg'); 
    autosave('TO_Cured_Total');
    }
        else if(elem=="TO_Cured_5_15_Neg"){ 
        autosave('TO_Total_5_15_Neg'); 
    autosave('TO_Cured_Total');
    }
        
        else if(elem=="TO_Discharged_Adults15_17_Pos"){ 
       autosave('TO_Total_Adults15_17_Pos');
       autosave('TO_Discharged_Total');
    }
        else if(elem=="TO_Discharged_Postnatal_Pos"){ 
        autosave('TO_Total_Postnatal_Pos');
    autosave('TO_Discharged_Total');
    }
        else if(elem=="TO_Discharged_AllAdults_Neg"){ 
        autosave('TO_Total_AllAdults_Neg'); 
      autosave('TO_Discharged_Total');  
    }
        else if(elem=="TO_Discharged_0_59M_Pos"){ 
        autosave('TO_Total_0_59M_Pos');
    autosave('TO_Discharged_Total');
    }
        else if(elem=="TO_Discharged_5_15_Pos"){ 
        autosave('TO_Total_5_15_Pos'); 
    autosave('TO_Discharged_Total');
    }
        else if(elem=="TO_Discharged_0_59M_Neg"){ 
        autosave('TO_Total_0_59M_Neg'); 
    autosave('TO_Discharged_Total');
    }
        else if(elem=="TO_Discharged_5_15_Neg"){ 
        autosave('TO_Total_5_15_Neg'); 
      autosave('TO_Discharged_Total');  
    }
     
        else if(elem=="TO_RNS_Adults15_17_Pos"){
          autosave('TO_Total_Adults15_17_Pos');  
          autosave('TO_RNS_Total');  
        }
        else if(elem=="TO_RNS_Postnatal_Pos"){
          autosave('TO_Total_Postnatal_Pos');  
         autosave('TO_RNS_Total');   
        }
        else if(elem=="TO_RNS_AllAdults_Neg"){
         autosave('TO_Total_AllAdults_Neg');   
         autosave('TO_RNS_Total');   
        }
        else if(elem=="TO_RNS_0_59M_Pos"){
          autosave('TO_Total_0_59M_Pos');  
         autosave('TO_RNS_Total');   
        }
        else if(elem=="TO_RNS_5_15_Pos"){
         autosave('TO_Total_5_15_Pos');   
         autosave('TO_RNS_Total');   
        }
        else if(elem=="TO_RNS_0_59M_Neg"){
         autosave('TO_Total_0_59M_Neg');   
        autosave('TO_RNS_Total');    
        }
        else if(elem=="TO_RNS_5_15_Neg"){
         autosave('TO_Total_5_15_Neg');  
        autosave('TO_RNS_Total');                    
        }
       
     autosave('TO_Grand_Total');   
}

function ReferralsandTransfers(elem){
        var RT_InPatient_Adults15_17_Pos  = document.getElementById("RT_InPatient_Adults15_17_Pos").value;
        var RT_InPatient_Postnatal_Pos  = document.getElementById("RT_InPatient_Postnatal_Pos").value;
        var RT_InPatient_AllAdults_Neg  = document.getElementById("RT_InPatient_AllAdults_Neg").value;
        var RT_InPatient_0_59M_Pos  = document.getElementById("RT_InPatient_0_59M_Pos").value;
        var RT_InPatient_5_15_Pos  = document.getElementById("RT_InPatient_5_15_Pos").value;
        var RT_InPatient_0_59M_Neg  = document.getElementById("RT_InPatient_0_59M_Neg").value;
        var RT_InPatient_5_15_Neg  = document.getElementById("RT_InPatient_5_15_Neg").value;
        
        
        var RT_LS_Adults15_17_Pos  = document.getElementById("RT_LS_Adults15_17_Pos").value;
        var RT_LS_Postnatal_Pos  = document.getElementById("RT_LS_Postnatal_Pos").value;
        var RT_LS_AllAdults_Neg  = document.getElementById("RT_LS_AllAdults_Neg").value;
        var RT_LS_0_59M_Pos  = document.getElementById("RT_LS_0_59M_Pos").value;
        var RT_LS_5_15_Pos  = document.getElementById("RT_LS_5_15_Pos").value;
        var RT_LS_0_59M_Neg  = document.getElementById("RT_LS_0_59M_Neg").value;
        var RT_LS_5_15_Neg  = document.getElementById("RT_LS_5_15_Neg").value;
        
        var RT_Transferred_Adults15_17_Pos  = document.getElementById("RT_Transferred_Adults15_17_Pos").value;
        var RT_Transferred_Postnatal_Pos  = document.getElementById("RT_Transferred_Postnatal_Pos").value;
        var RT_Transferred_AllAdults_Neg  = document.getElementById("RT_Transferred_AllAdults_Neg").value;
        var RT_Transferred_0_59M_Pos  = document.getElementById("RT_Transferred_0_59M_Pos").value;
        var RT_Transferred_5_15_Pos  = document.getElementById("RT_Transferred_5_15_Pos").value;
        var RT_Transferred_0_59M_Neg  = document.getElementById("RT_Transferred_0_59M_Neg").value;
        var RT_Transferred_5_15_Neg  = document.getElementById("RT_Transferred_5_15_Neg").value;
        
        if(RT_InPatient_Adults15_17_Pos==""){ RT_InPatient_Adults15_17_Pos=0;}
        if(RT_InPatient_Postnatal_Pos==""){ RT_InPatient_Postnatal_Pos=0;}
        if(RT_InPatient_AllAdults_Neg==""){ RT_InPatient_AllAdults_Neg=0;}
        if(RT_InPatient_0_59M_Pos==""){ RT_InPatient_0_59M_Pos=0;}
        if(RT_InPatient_5_15_Pos==""){ RT_InPatient_5_15_Pos=0;}
        if(RT_InPatient_0_59M_Neg==""){ RT_InPatient_0_59M_Neg=0;}
        if(RT_InPatient_5_15_Neg==""){ RT_InPatient_5_15_Neg=0;}
        
        if(RT_LS_Adults15_17_Pos==""){ RT_LS_Adults15_17_Pos=0;}
        if(RT_LS_Postnatal_Pos==""){ RT_LS_Postnatal_Pos=0;}
        if(RT_LS_AllAdults_Neg==""){ RT_LS_AllAdults_Neg=0;}
        if(RT_LS_0_59M_Pos==""){ RT_LS_0_59M_Pos=0;}
        if(RT_LS_5_15_Pos==""){ RT_LS_5_15_Pos=0;}
        if(RT_LS_0_59M_Neg==""){ RT_LS_0_59M_Neg=0;}
        if(RT_LS_5_15_Neg==""){ RT_LS_5_15_Neg=0;}
        
        if(RT_Transferred_Adults15_17_Pos==""){ RT_Transferred_Adults15_17_Pos=0;}
        if(RT_Transferred_Postnatal_Pos==""){ RT_Transferred_Postnatal_Pos=0;}
        if(RT_Transferred_AllAdults_Neg==""){ RT_Transferred_AllAdults_Neg=0;}
        if(RT_Transferred_0_59M_Pos==""){ RT_Transferred_0_59M_Pos=0;}
        if(RT_Transferred_5_15_Pos==""){ RT_Transferred_5_15_Pos=0;}
        if(RT_Transferred_0_59M_Neg==""){ RT_Transferred_0_59M_Neg=0;}
        if(RT_Transferred_5_15_Neg==""){ RT_Transferred_5_15_Neg=0;}
        
        
        
        
        
        
        var RT_InPatient_Total  =  parseInt(RT_InPatient_Adults15_17_Pos)+ parseInt(RT_InPatient_Postnatal_Pos)+ parseInt(RT_InPatient_AllAdults_Neg)+ parseInt(RT_InPatient_0_59M_Pos)+ parseInt(RT_InPatient_5_15_Pos)+ parseInt(RT_InPatient_0_59M_Neg)+ parseInt(RT_InPatient_5_15_Neg);
        var RT_LS_Total  =   parseInt(RT_LS_Adults15_17_Pos)+ parseInt(RT_LS_Postnatal_Pos)+ parseInt(RT_LS_AllAdults_Neg)+ parseInt(RT_LS_0_59M_Pos)+ parseInt(RT_LS_5_15_Pos)+ parseInt(RT_LS_0_59M_Neg)+ parseInt(RT_LS_5_15_Neg);
        var RT_Transferred_Total  =   parseInt(RT_Transferred_Adults15_17_Pos)+ parseInt(RT_Transferred_Postnatal_Pos)+ parseInt(RT_Transferred_AllAdults_Neg)+ parseInt(RT_Transferred_0_59M_Pos)+ parseInt(RT_Transferred_5_15_Pos)+ parseInt(RT_Transferred_0_59M_Neg)+ parseInt(RT_Transferred_5_15_Neg);
        
         document.getElementById("RT_InPatient_Total").value = RT_InPatient_Total;
         document.getElementById("RT_LS_Total").value = RT_LS_Total;
         document.getElementById("RT_Transferred_Total").value = RT_Transferred_Total;
         
        var RT_Total_Adults15_17_Pos  = parseInt(RT_InPatient_Adults15_17_Pos)+parseInt(RT_LS_Adults15_17_Pos)+parseInt(RT_Transferred_Adults15_17_Pos);
        var RT_Total_Postnatal_Pos  =  parseInt(RT_InPatient_Postnatal_Pos)+parseInt(RT_LS_Postnatal_Pos)+parseInt(RT_Transferred_Postnatal_Pos);
        var RT_Total_AllAdults_Neg  =  parseInt(RT_InPatient_AllAdults_Neg)+parseInt(RT_LS_AllAdults_Neg)+parseInt(RT_Transferred_AllAdults_Neg);
        var RT_Total_0_59M_Pos  =  parseInt(RT_InPatient_0_59M_Pos)+parseInt(RT_LS_0_59M_Pos)+parseInt(RT_Transferred_0_59M_Pos);
        var RT_Total_5_15_Pos  =  parseInt(RT_InPatient_5_15_Pos)+parseInt(RT_LS_5_15_Pos)+parseInt(RT_Transferred_5_15_Pos);
        var RT_Total_0_59M_Neg  =  parseInt(RT_InPatient_0_59M_Neg)+parseInt(RT_LS_0_59M_Neg)+parseInt(RT_Transferred_0_59M_Neg);
        var RT_Total_5_15_Neg  =  parseInt(RT_InPatient_5_15_Neg )+parseInt(RT_LS_5_15_Neg )+parseInt(RT_Transferred_5_15_Neg );
        var RT_Grand_Total  =    parseInt(RT_Total_Adults15_17_Pos)+parseInt(RT_Total_Postnatal_Pos)+parseInt(RT_Total_AllAdults_Neg)+parseInt(RT_Total_0_59M_Pos)+parseInt(RT_Total_5_15_Pos)+parseInt(RT_Total_0_59M_Neg)+parseInt(RT_Total_5_15_Neg);
        
        document.getElementById("RT_Total_Adults15_17_Pos").value = RT_Total_Adults15_17_Pos;
        document.getElementById("RT_Total_Postnatal_Pos").value = RT_Total_Postnatal_Pos;
        document.getElementById("RT_Total_AllAdults_Neg").value = RT_Total_AllAdults_Neg;
        document.getElementById("RT_Total_0_59M_Pos").value = RT_Total_0_59M_Pos;
        document.getElementById("RT_Total_5_15_Pos").value = RT_Total_5_15_Pos;
        document.getElementById("RT_Total_0_59M_Neg").value = RT_Total_0_59M_Neg;
        document.getElementById("RT_Total_5_15_Neg").value = RT_Total_5_15_Neg;
        document.getElementById("RT_Grand_Total").value = RT_Grand_Total;
     
        if(elem=="RT_InPatient_Adults15_17_Pos"){
        autosave('RT_Total_Adults15_17_Pos');
        autosave('RT_InPatient_Total');
    }
        else if(elem=="RT_InPatient_Postnatal_Pos"){ 
        autosave('RT_Total_Postnatal_Pos');
        autosave('RT_InPatient_Total');
    }
        else if(elem=="RT_InPatient_AllAdults_Neg"){ 
        autosave('RT_Total_AllAdults_Neg');
        autosave('RT_InPatient_Total');
    }
        else if(elem=="RT_InPatient_0_59M_Pos"){ 
        autosave('RT_Total_0_59M_Pos');
        autosave('RT_InPatient_Total');
    }
        else if(elem=="RT_InPatient_5_15_Pos"){ 
        autosave('RT_Total_5_15_Pos');
        autosave('RT_InPatient_Total');
    }
        else if(elem=="RT_InPatient_0_59M_Neg"){ 
        autosave('RT_Total_0_59M_Neg');
        autosave('RT_InPatient_Total');
    }
        else if(elem=="RT_InPatient_5_15_Neg"){ 
        autosave('RT_Total_5_15_Neg');
        autosave('RT_InPatient_Total');
    } 
        else if(elem=="RT_LS_Adults15_17_Pos"){ 
        autosave('RT_Total_Adults15_17_Pos');
        autosave('RT_LS_Total');
    }
        else if(elem=="RT_LS_Postnatal_Pos"){ 
        autosave('RT_Total_Postnatal_Pos');
        autosave('RT_LS_Total');
    }
        else if(elem=="RT_LS_AllAdults_Neg"){ 
        autosave('RT_Total_AllAdults_Neg');
        autosave('RT_LS_Total');
    }
        else if(elem=="RT_LS_0_59M_Pos"){ 
        autosave('RT_Total_0_59M_Pos');
        autosave('RT_LS_Total');
    }
        else if(elem=="RT_LS_5_15_Pos"){ 
        autosave('RT_Total_5_15_Pos');
        autosave('RT_LS_Total');
    }
        else if(elem=="RT_LS_0_59M_Neg"){ 
        autosave('RT_Total_0_59M_Neg');
        autosave('RT_LS_Total');
    }
        else if(elem=="RT_LS_5_15_Neg"){ 
        autosave('RT_Total_5_15_Neg');
        autosave('RT_LS_Total');
    }
        else if(elem=="RT_Transferred_Adults15_17_Pos"){ 
        autosave('RT_Total_Adults15_17_Pos');
        autosave('RT_Transferred_Total');
    }
        else if(elem=="RT_Transferred_Postnatal_Pos"){ 
        autosave('RT_Total_Postnatal_Pos');
        autosave('RT_Transferred_Total');
    }
        else if(elem=="RT_Transferred_AllAdults_Neg"){ 
        autosave('RT_Total_AllAdults_Neg');
        autosave('RT_Transferred_Total');
    }
        else if(elem=="RT_Transferred_0_59M_Pos"){ 
        autosave('RT_Total_0_59M_Pos');
        autosave('RT_Transferred_Total');
    }
        else if(elem=="RT_Transferred_5_15_Pos"){ 
        autosave('RT_Total_5_15_Pos');
        autosave('RT_Transferred_Total');
    }
        else if(elem=="RT_Transferred_0_59M_Neg"){ 
        autosave('RT_Total_0_59M_Neg');
        autosave('RT_Transferred_Total');
    }
        else if(elem=="RT_Transferred_5_15_Neg"){ 
       autosave('RT_Total_5_15_Neg');
       autosave('RT_Transferred_Total');
    }

    autosave('RT_Grand_Total');
}