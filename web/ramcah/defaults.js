/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//alert("Testiii");

 function loadMonth(elm){
 
       
              $.ajax({
                         url:'getParameterData?per=yes',                            
                    type:'post',  
                    dataType: 'json',  
                    success: function(data) {                        
                       
        var dat=data.periods;
        
      
        var o="";
                        
                        for(var a=0;a<dat.length;a++)
                        {                           
                     
                          o+="<option value='"+dat[a].id+"'>"+dat[a].year+" "+dat[a].month+"</option>";   
                        }
                        
                   $("#"+elm).html(o);
                   $(document).ready(function() {
                   // $('#period').select2(); 
             
             //alert("Tunatestii");
             
                                 } ); 
                        
                        
                    }});
   
   }
   


               loadMonth("startdate");
                loadMonth("enddate");
                
                
                function getReport(url)
{
    
    
  
   var sd=$("#startdate").val();
   var ed=$("#enddate").val();
  
        
        
   //end date
       if (sd==='')
     {
         
     alert('Select report starting date');
   $("#startdate").focus();    
     } 
       else if (ed==='')
     {
         
     alert('Select report end date');
   $("#enddate").focus();    
     }
     
      else if (sd>ed)
     {
         
     alert('Start Date cannot be greater than end date');
   $("#enddate").focus();    
     }

                
                  
                
                else {
                    //call the report generation page
                 downloadrpt(sd,ed,url) ;  
                    
                }
        
    
}



  function downloadrpt(startdate,enddate,_ur){
      
                $('.loading').show();
                $('#generaterpt').hide();
               var urel=""+_ur;
                //?startdate=" + startdate + "&enddate=" + enddate + "&cbos=" + cbos
             
                var ur=urel+"?enddate=" + enddate+"&startdate="+startdate;
                  console.log(ur);
                $.fileDownload(ur).done(function () { $('.loading').hide(); $('#generaterpt').show(); $('#generaterpt').html("<i class='glyphicon glyphicon-ok'></i> Report Generated"); }).fail(function () { alert('Report generation failed, kindly try again!'); $('.loading').hide(); $('#generaterpt').show(); });
 
                //$('.loading').hide();
            }
