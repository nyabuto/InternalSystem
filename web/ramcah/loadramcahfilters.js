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



function loadBintisSummary()
{ 
    
    //once the edit form is clicked, the assumption is that the add section will be loaded
    //$('#home-tab').click();
    //$('#home-tab').html("Edit Projects");
    
   
//    console.log("_"+fc+"vs"+dt);

    var fc="";
    var cnt="";
    var sct="";
    var rgn="";
    var sd="";
    var ed="";
    if($("#facility").val()!==null){fc=$("#facility").val();}
    if($("#county").val()!==null){cnt=$("#county").val();}
    if($("#subcounty").val()!==null){sct=$("#subcounty").val();}
    if($("#mdt").val()!==null){rgn=$("#mdt").val();}
    
    if($("#sd").val()!==null){sd=$("#sd").val();} 
    if($("#ed").val()!==null){ed=$("#ed").val();} 
 
            
           
            
            //now load the data
          $.ajax({
                    url:'pullDashboardData',                            
                    type:'post',  
                    data:
                    {
                        act:'getBintisSummary',
                        fc:fc,
                        cnt:cnt,
                        sct:sct,
                        mdt:rgn,
                        sd:sd,
                        ed:ed
                    },
                    dataType: 'json',  
                    success: function(data) 
                    {
                        console.log(data);
                        
                        
                        for(var a=0;a<data.length;a++){
                            
                          console.log(data[a].value);   
                          console.log(data[a].indicator);   
                            
                             if($("#"+data[a].indicator)!==null){
     $("#"+data[a].indicator).html(data[a].value);
            }
                        }
//                        const keys = Object.keys(data[0]);  
//                        console.log(keys);
//for (const key in data[0]) 
//{  
//    
//    var ky=key;
//    var vl=data[0][key];
//    
//    
//    //don't include 
//    if(vl.indexOf(".")<0){
//        if($("#"+ky)!==null){
//     $("#"+ky).html(vl);
//            }
// }
//  
//}
//hideNonCompetitiveFields();
                    }  
//});
                        
                  
//                         $('.dates').datepicker({
//                             todayHighlight: true, daysOfWeekDisabled: "0,6",clearBtn: true, autoclose: true,format: "yyyy-mm-dd",
//     });
     
   
                        
                        
                    });    
             
    
    
}

  loadBintisSummary();
  
function updt()
{
 loadBintisSummary();  
}

  