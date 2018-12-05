/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function sum_indicators(indicator_id){
    var total=0, elem_value="";
var columns = ["m_uk","f_uk","m_1","f_1","m_4","f_4","m_9","f_9","m_14","f_14","m_19","f_19","m_24","f_24","m_29","f_29","m_34","f_34","m_39","f_39","m_44","f_44","m_49","f_49","m_50","f_50"];
//    var indicator_id=document.getElementById("indic_pos_"+indic_pos).value;
//    alert(indicator_id);
var num_elems = columns.length;
for(var i=0;i<num_elems;i++){
//    alert(columns[i]+"_"+indicator_id);
    elem_value=document.getElementById(columns[i]+"_"+indicator_id).value;
    if(elem_value!=""){
  total = total+parseInt(elem_value);  
    }
}

document.getElementById("total_"+indicator_id).value = total;
//alert(total);
}

    function indicate_changed(id){
     $("#"+id).css('background-color', '#fae512');
    }  
    function section_changed(section_id){
      $("#section_"+section_id).css('background','red');
      format_section(section_id);
    }
    
    function section_saved(section_id){
      $("#section_"+section_id).css('background','#0bb708');
      format_section(section_id);
    }
    
    function format_section(section_id){
     $("#collapse"+section_id).collapse();
     $("#section_"+section_id).removeClass('blue');
     $("#section_"+section_id).css('color','white'); 
     $("#section_"+section_id).css('font-weight','700'); 
    }
//eg function sum_multipleindicators('1+2+3-1','4'){

function autocalculate(sourceindicators,destination_indicator){
    console.log(sourceindicators+" = source indicators");
    console.log(destination_indicator+" = destination indicators");
var agesets = ["m_uk","f_uk","m_1","f_1","m_4","f_4","m_9","f_9","m_14","f_14","m_19","f_19","m_24","f_24","m_29","f_29","m_34","f_34","m_39","f_39","m_44","f_44","m_49","f_49","m_50","f_50","total"];
//    var indicator_id=document.getElementById("indic_pos_"+indic_pos).value;
//    alert(indicator_id);

//if the split function contains both + and -, work on splitting the + first then - inside the function loops

 var sourcearrays=sourceindicators.split("+");

var num_agesets = agesets.length;

var num_indics = sourcearrays.length;

//alert(num_indics);

for(var i=0;i<num_agesets;i++){
 var total=0, elem_value="",value="";   
for(var f=0;f<num_indics;f++){
//    alert(columns[i]+"_"+indicator_id);
//check for existance of -

//check if there is subtraction
if(sourcearrays[f].indexOf("-")>=0){
    
  //  deal with splitting the -
  var sourcearrays_neg=sourcearrays[f].split("-");
  
  for(var g=0;g<sourcearrays_neg.length;g++){
    
   value=document.getElementById(agesets[i]+"_"+sourcearrays_neg[g]).value;
   //in array index 0, pick the value in the array and summ with 0, then onwards
   
                    if(g===0){
                        console.log(g+" value ya "+sourcearrays_neg[g]+" ni "+value);
                    if(value!==''){
                        
  total = total+parseInt(value);
  
    } 
    else 
    { 
        value=0;
       total = total+parseInt(value);    
    }
  }
  else 
    {
        if(value===''){ value=0;}
       total = total-parseInt(value);    
    }
      
  }
  
    
}
//if no subtration i.e only addition
else {
    
  value=document.getElementById(agesets[i]+"_"+sourcearrays[f]).value;
    if(value!=='')
    {
        
  total = total+parseInt(value); 
  console.log(" sum of "+agesets[i]+"_"+sourcearrays[f]+" is "+total);
  
    }
    
    
}
   
    
 if(isNumber(total))
{
document.getElementById(agesets[i]+"_"+destination_indicator).value = total;
}   
   
    
}//end of looping of indicators



}//end of age sets


//alert(total);
}

function isNumber(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}