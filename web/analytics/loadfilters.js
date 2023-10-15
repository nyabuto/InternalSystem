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
                loadsubcounty();
                loadfacils();
              //  App.init();   
            }
            
            
        });
        
    }
    
    
       function loadsubcounty(){
           
           
           
        
        var county=document.getElementById("county").value;
        $.ajax({
            url:'loadSubcounty?county='+county,
            type:'post',
            dataType:'html',
            success:function (data)
            {
                $("#subcounty").html(data.replace("<option value=''>Select sub-county</option>",""));
                var select = document.getElementById('subcounty');
                    select.size = select.length;
                
              //  App.init();   
            }
            
            
        });
      return true;  
    }
    
    
       function loadsubcounty2(arr,far){
           
           
           
        
        var county=document.getElementById("county").value;
        $.ajax({
            url:'loadSubcounty?county='+county,
            type:'post',
            dataType:'html',
            success:function (data)
            {
                $("#subcounty").html(data.replace("<option value=''>Select sub-county</option>",""));
                var select = document.getElementById('subcounty');
                select.size = select.length;
                 $("#subcounty").val(arr);  
                 
                 loadfacil2(far);
              //  App.init();   
            }
            
            
        });
       
    }
    
    
    
    
    
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
    
 
 loadcounty();

