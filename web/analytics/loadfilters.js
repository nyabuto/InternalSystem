/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function loadmonths(){
    
    var dt=new Date();
    
    
    var curmonth=dt.getMonth()+1;
   
       
        var curyr=dt.getYear()+1900;
     
        console.log("mwezi wa "+curmonth+"  mwaka wa "+curyr);
        
      var yr=document.getElementById("year").value;
//      alert(yr);
              $.ajax({
url:'loadLastMonth?year='+yr,
type:'post',
dataType:'html',
success:function (data){
    $("#month").html(data.replace("<option value=''>Select Month </option>",""));
    var select = document.getElementById('month');
                    select.size = select.length;
    
}
});  
     }
     
     
    function loadcounty(){
        
        
        $.ajax({
            url:'loadCounty',
            type:'post',
            dataType:'html',
            success:function (data){
                $("#county").html(data);
                 $('#county').select2();  
               
                //loadfacils();
              //  App.init();   
            }
            
            
        });
        
    }
    
   
    
       function loadsubcounty(){
           
           
           
        
        var mdt=document.getElementById("mdt").value;
        $.ajax({
            url:'loadSubcountymdt?mdt='+mdt,
            type:'post',
            dataType:'html',
            success:function (data)
            {
                $("#subcounty").html(data.replace("<option value=''>Select sub-county</option>",""));
                var select = document.getElementById('subcounty');
                    //select.size = select.length;
                 $('#subcounty').select2();  
              //  App.init();   
            }
            
            
        });
      return true;  
    }
    
    loadsubcounty();
    
    
    
     function loadmdt(){
           
           
           
        
        var county=document.getElementById("county").value;
        $.ajax({
            url:'loadMdt?county='+county,
            type:'post',
            dataType:'html',
            success:function (data)
            {
                $("#mdt").html(data.replace("<option value=''>Select Mdt</option>",""));
                var select = document.getElementById('mdt');
                    //select.size = select.length;
                 $('#mdt').select2();  
              //  App.init();   
            }
            
            
        });
      return true;  
    }
    
     $('#mdt').select2();  
    
     
    
    
    
       function loadfacils()
{
      var subcounty=$("#subcounty").val();
      
$.ajax({
url:'loadMultipleFacilities?subcounty='+subcounty,
type:'post',
dataType:'html',
success:function (data){
       $("#facility").html(data);
         if(document.getElementById("facility").value!==''){
      updatefacilsession();
      
     
      }  
      $('#facility').select2();  
         // $("#facility").chosen();
       
       
}


}); 
}
    loadfacils();
 
    
    
       function loadfacil2( far)
{
      var subcounty=$("#subcounty").val();
      
$.ajax({
url:'loadMultipleFacilities?subcounty='+subcounty,
type:'post',
dataType:'html',
success:function (data){
       $("#facility").html(data);
         if(document.getElementById("facility").value!==''){
      updatefacilsession();
      
      
     
      }  
      $("#facility").val(far);
    $("#facility").select2(); 
         // $("#facility").chosen();
       
       
}


}); 
}
    
 
 


function getPeriod(){
       
 
       
       
              $.ajax({
                         url:'loadyearmonth',                            
                    type:'post',  
                    dataType: 'json',  
                    success: function(data) {                        
                       
        var dat=data.periods;
        
      
        var o="";
                        
                        for(var a=0;a<dat.length;a++)
                        {                           
                     
                          o+="<option value='"+dat[a].id+"'>"+dat[a].year+" "+dat[a].month+"</option>";   
                        }
                        
                   $("#startdate").html(o);
                   $("#enddate").html(o);
                   $(document).ready(function() {
                    $('#startdate').select2(); 
                    $('#enddate').select2(); 
             
                                 } ); 
                        
                        
                    }});
   
   }
   

getPeriod();



function loadact(act){
     
     
     
   
     var ct=$("#county").val();
     var mdt=$("#mdt").val();
     var sct=$("#subcounty").val();
     var fac=$("#facility").val();
     
            //now load the data
          $.ajax({
                    url:'pulldata',                            
                    type:'post',  
                    dataType: 'json',  
                    data:{act:act,ct:ct,mdt:mdt,sct:sct,fc:fac},
                    success: function(data) 
                    {
                        var dt = data;
       
        console.log(data);
        $("#Total_Sites").html(data[0].Total_Sites);
        $("#HTS").html(data[0].HTS);
        $("#ART").html(data[0].ART);
        $("#PMTCT").html(data[0].PMTCT);
        $("#EMR").html(data[0].EMR);
        $("#poc").html(data[0].poc);
        $("#ehts").html(data[0].ehts);
        $("#labmanifest").html(data[0].labmanifest);
        $("#Ushauri").html(data[0].Ushauri);
        $("#districts").html(data[0].districts);
        $("#counties").html(data[0].county);
                        
                    }});    
           
              
}

loadact('getSitesSummary');


function updt(){
 loadact('getSitesSummary');   
    
}

  