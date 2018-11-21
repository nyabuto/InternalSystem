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
    }
    
        function section_saved(section_id){
      $("#section_"+section_id).css('background','#0bb708'); 
    }
