/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






function loadqueries(){
  
        
      
        
         $.ajax({
            url:'loadQueryHistory',
            type:'post',
            dataType:'json',
            success:function (data){
                 var qrs="<option value=''>Select Query </value>";
                for(var as=0;as<data.length;as++){
               
            qrs+="<option data-datetype=\""+data[as].datetype+"\" data-startdateactive=\""+data[as].startdate_active+"\" data-enddateactive=\""+data[as].enddate_active+"\" data-qname=\""+data[as].queryname+"\" value=\""+data[as].qry+"\">"+data[as].queryname+"</option>";
            
            $("#queryhistory").html(qrs);
            $("#queryhistory").select2();
                
            }
            }

                                   });
            
            
                                 }
        
    loadqueries();


function showqry(){
    
    var vl1=$("#queryhistory").val();
    var qn=$("#queryhistory").find(':selected').attr('data-qname');
    
    var datetype=$("#queryhistory").find(':selected').attr('data-datetype');
    var startdateactive=$("#queryhistory").find(':selected').attr('data-startdateactive');
    var enddateactive=$("#queryhistory").find(':selected').attr('data-enddateactive');
    
    qn=qn.replace("/"," or ");
    qn=qn.replaceAll(" ","_");
    qn=qn.substring(0,60);
    
    
    if(datetype==='fulldate'){
        
        if(startdateactive==='1')
        {
        $(".startdatediv").show();
    }
    else {
         $(".startdatediv").hide();
    }
    
       if(enddateactive==='1'){
        $(".enddatediv").show();
    }
    else {
         $(".enddatediv").hide();
    }
}

//here, yearmonths will take effect
    else  if(datetype==='yearmonth') {
        
          if(startdateactive==='1')
        {
        $(".startdatediv").show();
    }
    else {
         $(".startdatediv").hide();
    }
    
       if(enddateactive==='1'){
        $(".enddatediv").show();
    }
    else {
         $(".enddatediv").hide();
    }
        
        
    }//end of else
    
    
    else 
{
    $(".startdatediv").hide();
    $(".enddatediv").hide();
}
    
    
   $("#query").val(vl1);
   $("#qname").val(qn);
    
  
    
}


function replaceStartDate(){
   
    var qry= $("#query").val();
    
    var sd=$("#startdate").val();
    
    var ym=sd.replaceAll("-",'');
    ym=ym.substring(0,6);

    qry=qry.replaceAll(/@sd/g,sd);
    qry=qry.replaceAll(/@sym/g,ym);
    
    var datetype=$("#queryhistory").find(':selected').attr('data-datetype');
    if(datetype==='fulldate'){ $("#sd").val(sd);}
    else if(datetype==='yearmonth'){ $("#sd").val(ym);}
    else { $("#sd").val("");}
       console.log(qry);
   
    
}




function replaceEndDate(){
   
    var qry= $("#query").val();
    
    var sd=$("#enddate").val();
    
    var ym=sd.replaceAll("-",'');
    ym=ym.substring(0,6);
    qry=qry.replaceAll(/@ed/g,sd);
    qry=qry.replaceAll(/@eym/g,ym);
    
//     $("#query").val(qry);
     var datetype=$("#queryhistory").find(':selected').attr('data-datetype');
    if(datetype==='fulldate'){ $("#ed").val(sd);}
    else if(datetype==='yearmonth'){ $("#ed").val(ym);}
    else { $("#ed").val("");}
    
}

//$('.tarehe').datepicker().on('show', function () {
//    $('.datepicker').css({
//        'z-index': 9999,
//        'position': 'absolute'  // Or 'fixed' depending on your layout
//    });
//});



