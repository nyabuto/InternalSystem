/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function sumHV0110(){
    var HV01,sum=0,i=1;
    while(i<=9){
    HV01=document.getElementById("HV010"+i).value; 
    if(HV01==""){HV01="0";}
    sum+=parseInt(HV01);
    i++;
    }
    document.getElementById("HV0110").value=sum;
    
    autosave("HV0110");
}

function sumHV0126(){
    var HV01,sum=0,i=17;
    while(i<=25){
    HV01=document.getElementById("HV01"+i).value; 
    if(HV01==""){HV01="0";}
    sum+=parseInt(HV01);
    i++;
    }
    document.getElementById("HV0126").value=sum;
    
    autosave("HV0126");
}

function sumHV0135(){
    var HV01,sum=0,i=30;
    while(i<=34){
    HV01=document.getElementById("HV01"+i).value; 
    if(HV01==""){HV01="0";}
    sum+=parseInt(HV01);
    i++;
    }
    document.getElementById("HV0135").value=sum;
    
    autosave("HV0135");
}
function sumHV0145(){
    var HV01,sum=0,i=37;
    while(i<=44){
    HV01=document.getElementById("HV01"+i).value; 
    if(HV01==""){HV01="0";}
    sum+=parseInt(HV01);
    i++;
    }
    document.getElementById("HV0145").value=sum;
    
    autosave("HV0145");
}

function sumHV0150(){
    var HV01,sum=0,i=46;
    while(i<=49){
    HV01=document.getElementById("HV01"+i).value; 
    if(HV01==""){HV01="0";}
    sum+=parseInt(HV01);
    i++;
    }
    document.getElementById("HV0150").value=sum;
    
    autosave("HV0150");
}


function sumHV0207(){
    var HV02,sum=0,i=3;
    while(i<=6){
    HV02=document.getElementById("HV020"+i).value; 
    if(HV02==""){HV02="0";}
    sum+=parseInt(HV02);
    i++;
    }
    document.getElementById("HV0207").value=sum;
    
    autosave("HV0207");
}

function sumHV0214(){
    var HV02,sum=0,i=10;
    while(i<=13){
    HV02=document.getElementById("HV02"+i).value; 
    if(HV02==""){HV02="0";}
    sum+=parseInt(HV02);
    i++;
    }
    document.getElementById("HV0214").value=sum;
    
    autosave("HV0214");
}

function sumHV0220(){
    var HV02,sum=0,i=10;
    while(i<=13){
    HV02=document.getElementById("HV02"+i).value; 
    if(HV02==""){HV02="0";}
    sum+=parseInt(HV02);
    i++;
    }
    document.getElementById("HV0214").value=sum;
    
    autosave("HV0214");
}

function sumHV0220(){
    var HV02,sum=0,i=16;
    while(i<=19){
    HV02=document.getElementById("HV02"+i).value; 
    if(HV02==""){HV02="0";}
    sum+=parseInt(HV02);
    i++;
    }
    document.getElementById("HV0220").value=sum;
    
    autosave("HV0220");
}

function sumHV0233(){
    var HV02,sum=0,i=29;
    while(i<=32){
    HV02=document.getElementById("HV02"+i).value; 
    if(HV02==""){HV02="0";}
    sum+=parseInt(HV02);
    i++;
    }
    document.getElementById("HV0233").value=sum;
    
    autosave("HV0233");
}

function sumHV0242(){
    var HV02,sum=0,i=39;
    while(i<=41){
    HV02=document.getElementById("HV02"+i).value; 
    if(HV02==""){HV02="0";}
    sum+=parseInt(HV02);
    i++;
    }
    document.getElementById("HV0242").value=sum;
    
    autosave("HV0242");
}

function sumHV0246(){
    var HV02,sum=0,i=44;
    while(i<=45){
    HV02=document.getElementById("HV02"+i).value; 
    if(HV02==""){HV02="0";}
    sum+=parseInt(HV02);
    i++;
    }
    document.getElementById("HV0246").value=sum;
    
    autosave("HV0246");
}

function sumHV03011(){
    var HV030,sum=0,i=1;
    while(i<=10){
        if(i<10){
    HV030=document.getElementById("HV0300"+i).value; 
        }
        else{
    HV030=document.getElementById("HV030"+i).value;        
        }
    if(HV030==""){HV030="0";}
    sum+=parseInt(HV030);
    i++;
    }
    document.getElementById("HV03011").value=sum;
    
    autosave("HV03011");
}

function sumHV03015(){
    var HV030,sum=0,i=13;
    while(i<=14){
    HV030=document.getElementById("HV030"+i).value; 
    if(HV030==""){HV030="0";}
    sum+=parseInt(HV030);
    i++;
    }
    document.getElementById("HV03015").value=sum;
    
    autosave("HV03015");
}

function sumHV03026(){
    var HV030,sum=0,i=16;
    while(i<=25){
    HV030=document.getElementById("HV030"+i).value; 
    if(HV030==""){HV030="0";}
    sum+=parseInt(HV030);
    i++;
    }
    document.getElementById("HV03026").value=sum;
    
    autosave("HV03026");
}

function sumHV03038(){
    var HV030,sum=0,i=28;
    while(i<=37){
    HV030=document.getElementById("HV030"+i).value; 
    if(HV030==""){HV030="0";}
    sum+=parseInt(HV030);
    i++;
    }
    document.getElementById("HV03038").value=sum;
     var hv03050 = 0;
    if(document.getElementById("HV03050").value!="")
    {hv03050 = document.getElementById("HV03050").value;}
    var hv03039 = parseInt(hv03050)+sum;
    document.getElementById("HV03039").value=hv03039;
    
    autosave("HV03038");
    autosave("HV03039");
}

function sumHV03050(){
    var HV030,sum=0,i=44;
    while(i<=49){
    HV030=document.getElementById("HV030"+i).value; 
    if(HV030==""){HV030="0";}
    sum+=parseInt(HV030);
    i++;
    }
    document.getElementById("HV03050").value=sum;
    var hv03038 = 0;
    if(document.getElementById("HV03038").value!="")
    {hv03038 = document.getElementById("HV03038").value;}
    
    var hv03039 = parseInt(hv03038)+sum;
    document.getElementById("HV03039").value=hv03039;
    autosave("HV03050");
    autosave("HV03039");
}


function sumHV03057(){
    var HV030,sum=0,i=51;
    while(i<=56){
    HV030=document.getElementById("HV030"+i).value; 
    if(HV030==""){HV030="0";}
    sum+=parseInt(HV030);
    i++;
    }
    document.getElementById("HV03057").value=sum;
    
    autosave("HV03057");
}

function sumHV03065(){
    var HV030,sum=0,i=59;
    while(i<=64){
    HV030=document.getElementById("HV030"+i).value; 
    if(HV030==""){HV030="0";}
    sum+=parseInt(HV030);
    i++;
    }
    document.getElementById("HV03065").value=sum;
    
    autosave("HV03065");
}


function sumHV03069(){
    var HV030,sum=0,i=67;
    while(i<=68){
    HV030=document.getElementById("HV030"+i).value; 
    if(HV030==""){HV030="0";}
    sum+=parseInt(HV030);
    i++;
    }
    document.getElementById("HV03069").value=sum;
    
    autosave("HV03069");
}

function sumHV03072(){
    var HV030,sum=0,i=70;
    while(i<=71){
    HV030=document.getElementById("HV030"+i).value; 
    if(HV030==""){HV030="0";}
    sum+=parseInt(HV030);
    i++;
    }
    document.getElementById("HV03072").value=sum;
    
    autosave("HV03072");
}

function sumHV03075(){
    var HV030,sum=0,i=73;
    while(i<=74){
    HV030=document.getElementById("HV030"+i).value; 
    if(HV030==""){HV030="0";}
    sum+=parseInt(HV030);
    i++;
    }
    document.getElementById("HV03075").value=sum;
    
    autosave("HV03075");
}

function sumHV03079(){
    var HV030,sum=0,i=77;
    while(i<=78){
    HV030=document.getElementById("HV030"+i).value; 
    if(HV030==""){HV030="0";}
    sum+=parseInt(HV030);
    i++;
    }
    document.getElementById("HV03079").value=sum;
    
    autosave("HV03079");
}

function sumHV03081(){
    var HV03077,HV03080;
    HV03077=document.getElementById("HV03077").value;
    HV03080=document.getElementById("HV03080").value;
    
    
    if(HV03077==""){HV03077="0";}
    if(HV03080==""){HV03080="0";}
    
    var sum=parseInt(HV03077)+parseInt(HV03080);
    document.getElementById("HV03081").value=sum;
    
    autosave("HV03081");
}

function sumHV03084(){
    var HV030,sum=0,i=82;
    while(i<=83){
    HV030=document.getElementById("HV030"+i).value; 
    if(HV030==""){HV030="0";}
    sum+=parseInt(HV030);
    i++;
    }
    document.getElementById("HV03084").value=sum;
    
    autosave("HV03084");
}

function sumHV0407(){
    var HV040,sum=0,i=1;
    while(i<=6){
    HV040=document.getElementById("HV040"+i).value; 
    if(HV040==""){HV040="0";}
    sum+=parseInt(HV040);
    i++;
    }
    document.getElementById("HV0407").value=sum;
    
    autosave("HV0407");
}

function sumHV0503(){
    var HV050,sum=0,i=1;
    while(i<=2){
    HV050=document.getElementById("HV050"+i).value; 
    if(HV050==""){HV050="0";}
    sum+=parseInt(HV050);
    i++;
    }
    document.getElementById("HV0503").value=sum;
    
    autosave("HV0503");
}
function sumHV0507(){
    var HV050,sum=0,i=5;
    while(i<=6){
    HV050=document.getElementById("HV050"+i).value; 
    if(HV050==""){HV050="0";}
    sum+=parseInt(HV050);
    i++;
    }
    document.getElementById("HV0507").value=sum;
    
    autosave("HV0507");
}