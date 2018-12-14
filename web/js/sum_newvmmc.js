/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function v1(){
    var  total;
    var  v1_60d=document.getElementById("v1_60d").value;
    var  v1_4y=document.getElementById("v1_4y").value;
    var  v1_9y=document.getElementById("v1_9y").value;
    var  v1_14y=document.getElementById("v1_14y").value;
    var  v1_19y=document.getElementById("v1_19y").value;
    var  v1_24y=document.getElementById("v1_24y").value;
    var  v1_29y=document.getElementById("v1_29y").value;
    var  v1_34y=document.getElementById("v1_34y").value;
    var  v1_39y=document.getElementById("v1_39y").value;
    var  v1_44y=document.getElementById("v1_44y").value;
    var  v1_49y=document.getElementById("v1_49y").value;
    var  v1_50y=document.getElementById("v1_50y").value;
    
    if(v1_60d==""){v1_60d=0;}
    if(v1_4y==""){v1_4y=0;}
    if(v1_9y==""){v1_9y=0;}
    if(v1_14y==""){v1_14y=0;}
    if(v1_19y==""){v1_19y=0;}
    if(v1_24y==""){v1_24y=0;}
    if(v1_29y==""){v1_29y=0;}
    if(v1_34y==""){v1_34y=0;}
    if(v1_39y==""){v1_39y=0;}
    if(v1_44y==""){v1_44y=0;}
    if(v1_49y==""){v1_49y=0;}
    if(v1_50y==""){v1_50y=0;}
    
    total = parseInt(v1_60d)+parseInt(v1_4y)+parseInt(v1_9y)+parseInt(v1_14y)+parseInt(v1_19y)+parseInt(v1_24y)+parseInt(v1_29y)+parseInt(v1_34y)+parseInt(v1_39y)+parseInt(v1_44y)+parseInt(v1_49y)+parseInt(v1_50y);
    document.getElementById("v1_total").value=total;
}

function v2_dc(){
    var v2_dc_m_60d=document.getElementById("v2_dc_m_60d").value;
    var v2_dc_m_4y=document.getElementById("v2_dc_m_4y").value;
    var v2_dc_m_9y=document.getElementById("v2_dc_m_9y").value;
    var v2_dc_m_14y=document.getElementById("v2_dc_m_14y").value;
    var v2_dc_m_19y=document.getElementById("v2_dc_m_19y").value;
    var v2_dc_m_24y=document.getElementById("v2_dc_m_24y").value;
    var v2_dc_m_29y=document.getElementById("v2_dc_m_29y").value;
    var v2_dc_m_34y=document.getElementById("v2_dc_m_34y").value;
    var v2_dc_m_39y=document.getElementById("v2_dc_m_39y").value;
    var v2_dc_m_44y=document.getElementById("v2_dc_m_44y").value;
    var v2_dc_m_49y=document.getElementById("v2_dc_m_49y").value;
    var v2_dc_m_50y=document.getElementById("v2_dc_m_50y").value;
    if(v2_dc_m_60d==""){v2_dc_m_60d=0;}
    if(v2_dc_m_4y==""){v2_dc_m_4y=0;}
    if(v2_dc_m_9y==""){v2_dc_m_9y=0;}
    if(v2_dc_m_14y==""){v2_dc_m_14y=0;}
    if(v2_dc_m_19y==""){v2_dc_m_19y=0;}
    if(v2_dc_m_24y==""){v2_dc_m_24y=0;}
    if(v2_dc_m_29y==""){v2_dc_m_29y=0;}
    if(v2_dc_m_34y==""){v2_dc_m_34y=0;}
    if(v2_dc_m_39y==""){v2_dc_m_39y=0;}
    if(v2_dc_m_44y==""){v2_dc_m_44y=0;}
    if(v2_dc_m_49y==""){v2_dc_m_49y=0;}
    if(v2_dc_m_50y==""){v2_dc_m_50y=0;}
    
    var v2_dc_m=parseInt(v2_dc_m_60d)+parseInt(v2_dc_m_4y)+parseInt(v2_dc_m_9y)+parseInt(v2_dc_m_14y)+parseInt(v2_dc_m_19y)+parseInt(v2_dc_m_24y)+parseInt(v2_dc_m_29y)+parseInt(v2_dc_m_34y)+parseInt(v2_dc_m_39y)+parseInt(v2_dc_m_44y)+parseInt(v2_dc_m_49y)+parseInt(v2_dc_m_50y);
    var v2_dc_m_total=document.getElementById("v2_dc_m_total").value=v2_dc_m;
    
    var v2_dc_s_60d=document.getElementById("v2_dc_s_60d").value;
    var v2_dc_s_4y=document.getElementById("v2_dc_s_4y").value;
    var v2_dc_s_9y=document.getElementById("v2_dc_s_9y").value;
    var v2_dc_s_14y=document.getElementById("v2_dc_s_14y").value;
    var v2_dc_s_19y=document.getElementById("v2_dc_s_19y").value;
    var v2_dc_s_24y=document.getElementById("v2_dc_s_24y").value;
    var v2_dc_s_29y=document.getElementById("v2_dc_s_29y").value;
    var v2_dc_s_34y=document.getElementById("v2_dc_s_34y").value;
    var v2_dc_s_39y=document.getElementById("v2_dc_s_39y").value;
    var v2_dc_s_44y=document.getElementById("v2_dc_s_44y").value;
    var v2_dc_s_49y=document.getElementById("v2_dc_s_49y").value;
    var v2_dc_s_50y=document.getElementById("v2_dc_s_50y").value;
    if(v2_dc_s_60d==""){v2_dc_s_60d=0;}
    if(v2_dc_s_4y==""){v2_dc_s_4y=0;}
    if(v2_dc_s_9y==""){v2_dc_s_9y=0;}
    if(v2_dc_s_14y==""){v2_dc_s_14y=0;}
    if(v2_dc_s_19y==""){v2_dc_s_19y=0;}
    if(v2_dc_s_24y==""){v2_dc_s_24y=0;}
    if(v2_dc_s_29y==""){v2_dc_s_29y=0;}
    if(v2_dc_s_34y==""){v2_dc_s_34y=0;}
    if(v2_dc_s_39y==""){v2_dc_s_39y=0;}
    if(v2_dc_s_44y==""){v2_dc_s_44y=0;}
    if(v2_dc_s_49y==""){v2_dc_s_49y=0;}
    if(v2_dc_s_50y==""){v2_dc_s_50y=0;}
    
    var v2_dc_s = parseInt(v2_dc_s_60d)+parseInt(v2_dc_s_4y)+parseInt(v2_dc_s_9y)+parseInt(v2_dc_s_14y)+parseInt(v2_dc_s_19y)+parseInt(v2_dc_s_24y)+parseInt(v2_dc_s_29y)+parseInt(v2_dc_s_34y)+parseInt(v2_dc_s_39y)+parseInt(v2_dc_s_44y)+parseInt(v2_dc_s_49y)+parseInt(v2_dc_s_50y);
    var v2_dc_s_total=document.getElementById("v2_dc_s_total").value = v2_dc_s;
    
    document.getElementById("v2_dc_t_60d").value = (parseInt(v2_dc_m_60d)+parseInt(v2_dc_s_60d));
    document.getElementById("v2_dc_t_4y").value = (parseInt(v2_dc_m_4y)+parseInt(v2_dc_s_4y));
    document.getElementById("v2_dc_t_9y").value = (parseInt(v2_dc_m_9y)+parseInt(v2_dc_s_9y));
    document.getElementById("v2_dc_t_14y").value = (parseInt(v2_dc_m_14y)+parseInt(v2_dc_s_14y));
    document.getElementById("v2_dc_t_19y").value = (parseInt(v2_dc_m_19y)+parseInt(v2_dc_s_19y));
    document.getElementById("v2_dc_t_24y").value = (parseInt(v2_dc_m_24y)+parseInt(v2_dc_s_24y));
    document.getElementById("v2_dc_t_29y").value = (parseInt(v2_dc_m_29y)+parseInt(v2_dc_s_29y));
    document.getElementById("v2_dc_t_34y").value = (parseInt(v2_dc_m_34y)+parseInt(v2_dc_s_34y));
    document.getElementById("v2_dc_t_39y").value = (parseInt(v2_dc_m_39y)+parseInt(v2_dc_s_39y));
    document.getElementById("v2_dc_t_44y").value = (parseInt(v2_dc_m_44y)+parseInt(v2_dc_s_44y));
    document.getElementById("v2_dc_t_49y").value = (parseInt(v2_dc_m_49y)+parseInt(v2_dc_s_49y));
    document.getElementById("v2_dc_t_50y").value = (parseInt(v2_dc_m_50y)+parseInt(v2_dc_s_50y));
    
    var v2_dc_t=parseInt(v2_dc_m_total)+parseInt(v2_dc_s_total);
    document.getElementById("v2_dc_t_total").value = v2_dc_t;
    total_ae();
}

function v2_pc(){
    var v2_pc_m_60d=document.getElementById("v2_pc_m_60d").value;
    var v2_pc_m_4y=document.getElementById("v2_pc_m_4y").value;
    var v2_pc_m_9y=document.getElementById("v2_pc_m_9y").value;
    var v2_pc_m_14y=document.getElementById("v2_pc_m_14y").value;
    var v2_pc_m_19y=document.getElementById("v2_pc_m_19y").value;
    var v2_pc_m_24y=document.getElementById("v2_pc_m_24y").value;
    var v2_pc_m_29y=document.getElementById("v2_pc_m_29y").value;
    var v2_pc_m_34y=document.getElementById("v2_pc_m_34y").value;
    var v2_pc_m_39y=document.getElementById("v2_pc_m_39y").value;
    var v2_pc_m_44y=document.getElementById("v2_pc_m_44y").value;
    var v2_pc_m_49y=document.getElementById("v2_pc_m_49y").value;
    var v2_pc_m_50y=document.getElementById("v2_pc_m_50y").value;
    if(v2_pc_m_60d==""){v2_pc_m_60d=0;}
    if(v2_pc_m_4y==""){v2_pc_m_4y=0;}
    if(v2_pc_m_9y==""){v2_pc_m_9y=0;}
    if(v2_pc_m_14y==""){v2_pc_m_14y=0;}
    if(v2_pc_m_19y==""){v2_pc_m_19y=0;}
    if(v2_pc_m_24y==""){v2_pc_m_24y=0;}
    if(v2_pc_m_29y==""){v2_pc_m_29y=0;}
    if(v2_pc_m_34y==""){v2_pc_m_34y=0;}
    if(v2_pc_m_39y==""){v2_pc_m_39y=0;}
    if(v2_pc_m_44y==""){v2_pc_m_44y=0;}
    if(v2_pc_m_49y==""){v2_pc_m_49y=0;}
    if(v2_pc_m_50y==""){v2_pc_m_50y=0;}
    
    var v2_pc_m=parseInt(v2_pc_m_60d)+parseInt(v2_pc_m_4y)+parseInt(v2_pc_m_9y)+parseInt(v2_pc_m_14y)+parseInt(v2_pc_m_19y)+parseInt(v2_pc_m_24y)+parseInt(v2_pc_m_29y)+parseInt(v2_pc_m_34y)+parseInt(v2_pc_m_39y)+parseInt(v2_pc_m_44y)+parseInt(v2_pc_m_49y)+parseInt(v2_pc_m_50y);
    var v2_pc_m_total=document.getElementById("v2_pc_m_total").value=v2_pc_m;
    
    var v2_pc_s_60d=document.getElementById("v2_pc_s_60d").value;
    var v2_pc_s_4y=document.getElementById("v2_pc_s_4y").value;
    var v2_pc_s_9y=document.getElementById("v2_pc_s_9y").value;
    var v2_pc_s_14y=document.getElementById("v2_pc_s_14y").value;
    var v2_pc_s_19y=document.getElementById("v2_pc_s_19y").value;
    var v2_pc_s_24y=document.getElementById("v2_pc_s_24y").value;
    var v2_pc_s_29y=document.getElementById("v2_pc_s_29y").value;
    var v2_pc_s_34y=document.getElementById("v2_pc_s_34y").value;
    var v2_pc_s_39y=document.getElementById("v2_pc_s_39y").value;
    var v2_pc_s_44y=document.getElementById("v2_pc_s_44y").value;
    var v2_pc_s_49y=document.getElementById("v2_pc_s_49y").value;
    var v2_pc_s_50y=document.getElementById("v2_pc_s_50y").value;
    if(v2_pc_s_60d==""){v2_pc_s_60d=0;}
    if(v2_pc_s_4y==""){v2_pc_s_4y=0;}
    if(v2_pc_s_9y==""){v2_pc_s_9y=0;}
    if(v2_pc_s_14y==""){v2_pc_s_14y=0;}
    if(v2_pc_s_19y==""){v2_pc_s_19y=0;}
    if(v2_pc_s_24y==""){v2_pc_s_24y=0;}
    if(v2_pc_s_29y==""){v2_pc_s_29y=0;}
    if(v2_pc_s_34y==""){v2_pc_s_34y=0;}
    if(v2_pc_s_39y==""){v2_pc_s_39y=0;}
    if(v2_pc_s_44y==""){v2_pc_s_44y=0;}
    if(v2_pc_s_49y==""){v2_pc_s_49y=0;}
    if(v2_pc_s_50y==""){v2_pc_s_50y=0;}
    
    var v2_pc_s = parseInt(v2_pc_s_60d)+parseInt(v2_pc_s_4y)+parseInt(v2_pc_s_9y)+parseInt(v2_pc_s_14y)+parseInt(v2_pc_s_19y)+parseInt(v2_pc_s_24y)+parseInt(v2_pc_s_29y)+parseInt(v2_pc_s_34y)+parseInt(v2_pc_s_39y)+parseInt(v2_pc_s_44y)+parseInt(v2_pc_s_49y)+parseInt(v2_pc_s_50y);
    var v2_pc_s_total=document.getElementById("v2_pc_s_total").value = v2_pc_s;
    
    document.getElementById("v2_pc_t_60d").value = (parseInt(v2_pc_m_60d)+parseInt(v2_pc_s_60d));
    document.getElementById("v2_pc_t_4y").value = (parseInt(v2_pc_m_4y)+parseInt(v2_pc_s_4y));
    document.getElementById("v2_pc_t_9y").value = (parseInt(v2_pc_m_9y)+parseInt(v2_pc_s_9y));
    document.getElementById("v2_pc_t_14y").value = (parseInt(v2_pc_m_14y)+parseInt(v2_pc_s_14y));
    document.getElementById("v2_pc_t_19y").value = (parseInt(v2_pc_m_19y)+parseInt(v2_pc_s_19y));
    document.getElementById("v2_pc_t_24y").value = (parseInt(v2_pc_m_24y)+parseInt(v2_pc_s_24y));
    document.getElementById("v2_pc_t_29y").value = (parseInt(v2_pc_m_29y)+parseInt(v2_pc_s_29y));
    document.getElementById("v2_pc_t_34y").value = (parseInt(v2_pc_m_34y)+parseInt(v2_pc_s_34y));
    document.getElementById("v2_pc_t_39y").value = (parseInt(v2_pc_m_39y)+parseInt(v2_pc_s_39y));
    document.getElementById("v2_pc_t_44y").value = (parseInt(v2_pc_m_44y)+parseInt(v2_pc_s_44y));
    document.getElementById("v2_pc_t_49y").value = (parseInt(v2_pc_m_49y)+parseInt(v2_pc_s_49y));
    document.getElementById("v2_pc_t_50y").value = (parseInt(v2_pc_m_50y)+parseInt(v2_pc_s_50y));
    
    var v2_pc_t=parseInt(v2_pc_m_total)+parseInt(v2_pc_s_total);
    document.getElementById("v2_pc_t_total").value = v2_pc_t;
    total_ae();
}
function total_ae(){
    var v2_dc_t_60d=document.getElementById("v2_dc_t_60d").value;
    var v2_dc_t_4y=document.getElementById("v2_dc_t_4y").value;
    var v2_dc_t_9y=document.getElementById("v2_dc_t_9y").value;
    var v2_dc_t_14y=document.getElementById("v2_dc_t_14y").value;
    var v2_dc_t_19y=document.getElementById("v2_dc_t_19y").value;
    var v2_dc_t_24y=document.getElementById("v2_dc_t_24y").value;
    var v2_dc_t_29y=document.getElementById("v2_dc_t_29y").value;
    var v2_dc_t_34y=document.getElementById("v2_dc_t_34y").value;
    var v2_dc_t_39y=document.getElementById("v2_dc_t_39y").value;
    var v2_dc_t_44y=document.getElementById("v2_dc_t_44y").value;
    var v2_dc_t_49y=document.getElementById("v2_dc_t_49y").value;
    var v2_dc_t_50y=document.getElementById("v2_dc_t_50y").value;
    var v2_dc_t_total=document.getElementById("v2_dc_t_total").value;
    
    var v2_pc_t_60d=document.getElementById("v2_pc_t_60d").value;
    var v2_pc_t_4y=document.getElementById("v2_pc_t_4y").value;
    var v2_pc_t_9y=document.getElementById("v2_pc_t_9y").value;
    var v2_pc_t_14y=document.getElementById("v2_pc_t_14y").value;
    var v2_pc_t_19y=document.getElementById("v2_pc_t_19y").value;
    var v2_pc_t_24y=document.getElementById("v2_pc_t_24y").value;
    var v2_pc_t_29y=document.getElementById("v2_pc_t_29y").value;
    var v2_pc_t_34y=document.getElementById("v2_pc_t_34y").value;
    var v2_pc_t_39y=document.getElementById("v2_pc_t_39y").value;
    var v2_pc_t_44y=document.getElementById("v2_pc_t_44y").value;
    var v2_pc_t_49y=document.getElementById("v2_pc_t_49y").value;
    var v2_pc_t_50y=document.getElementById("v2_pc_t_50y").value;
    var v2_pc_t_total=document.getElementById("v2_pc_t_total").value;
    
    if(v2_dc_t_60d==""){v2_dc_t_60d=0;}
    if(v2_dc_t_4y==""){v2_dc_t_4y=0;}
    if(v2_dc_t_9y==""){v2_dc_t_9y=0;}
    if(v2_dc_t_14y==""){v2_dc_t_14y=0;}
    if(v2_dc_t_19y==""){v2_dc_t_19y=0;}
    if(v2_dc_t_24y==""){v2_dc_t_24y=0;}
    if(v2_dc_t_29y==""){v2_dc_t_29y=0;}
    if(v2_dc_t_34y==""){v2_dc_t_34y=0;}
    if(v2_dc_t_39y==""){v2_dc_t_39y=0;}
    if(v2_dc_t_44y==""){v2_dc_t_44y=0;}
    if(v2_dc_t_49y==""){v2_dc_t_49y=0;}
    if(v2_dc_t_50y==""){v2_dc_t_50y=0;}
    if(v2_dc_t_total==""){v2_dc_t_total=0;}
    
     if(v2_pc_t_60d==""){v2_pc_t_60d=0;}
    if(v2_pc_t_4y==""){v2_pc_t_4y=0;}
    if(v2_pc_t_9y==""){v2_pc_t_9y=0;}
    if(v2_pc_t_14y==""){v2_pc_t_14y=0;}
    if(v2_pc_t_19y==""){v2_pc_t_19y=0;}
    if(v2_pc_t_24y==""){v2_pc_t_24y=0;}
    if(v2_pc_t_29y==""){v2_pc_t_29y=0;}
    if(v2_pc_t_34y==""){v2_pc_t_34y=0;}
    if(v2_pc_t_39y==""){v2_pc_t_39y=0;}
    if(v2_pc_t_44y==""){v2_pc_t_44y=0;}
    if(v2_pc_t_49y==""){v2_pc_t_49y=0;}
    if(v2_pc_t_50y==""){v2_pc_t_50y=0;}
    if(v2_pc_t_total==""){v2_pc_t_total=0;}
    
     document.getElementById("v2_60d_total").value = (parseInt(v2_dc_t_60d)+parseInt(v2_pc_t_60d));
     document.getElementById("v2_4y_total").value = (parseInt(v2_dc_t_4y)+parseInt(v2_pc_t_4y));
     document.getElementById("v2_9y_total").value = (parseInt(v2_dc_t_9y)+parseInt(v2_pc_t_9y));
     document.getElementById("v2_14y_total").value = (parseInt(v2_dc_t_14y)+parseInt(v2_pc_t_14y));
     document.getElementById("v2_19y_total").value = (parseInt(v2_dc_t_19y)+parseInt(v2_pc_t_19y));
     document.getElementById("v2_24y_total").value = (parseInt(v2_dc_t_24y)+parseInt(v2_pc_t_24y));
     document.getElementById("v2_29y_total").value = (parseInt(v2_dc_t_29y)+parseInt(v2_pc_t_29y));
     document.getElementById("v2_34y_total").value = (parseInt(v2_dc_t_34y)+parseInt(v2_pc_t_34y));
     document.getElementById("v2_39y_total").value = (parseInt(v2_dc_t_39y)+parseInt(v2_pc_t_39y));
     document.getElementById("v2_44y_total").value = (parseInt(v2_dc_t_44y)+parseInt(v2_pc_t_44y)); 
    document.getElementById("v2_49y_total").value = (parseInt(v2_dc_t_49y)+parseInt(v2_pc_t_49y));    
     document.getElementById("v2_50y_total").value = (parseInt(v2_dc_t_50y)+parseInt(v2_pc_t_50y));
     document.getElementById("v2_total").value = (parseInt(v2_dc_t_total)+parseInt(v2_pc_t_total));
}
 function v3(){
                    var v3_tp_60d=document.getElementById("v3_tp_60d").value;
                    var v3_tp_4y=document.getElementById("v3_tp_4y").value;
                    var v3_tp_9y=document.getElementById("v3_tp_9y").value;
                    var v3_tp_14y=document.getElementById("v3_tp_14y").value;
                    var v3_tp_19y=document.getElementById("v3_tp_19y").value;
                    var v3_tp_24y=document.getElementById("v3_tp_24y").value;
                    var v3_tp_29y=document.getElementById("v3_tp_29y").value;
                    var v3_tp_34y=document.getElementById("v3_tp_34y").value;
                    var v3_tp_39y=document.getElementById("v3_tp_39y").value;
                    var v3_tp_44y=document.getElementById("v3_tp_44y").value;
                    var v3_tp_49y=document.getElementById("v3_tp_49y").value;
                    var v3_tp_50y=document.getElementById("v3_tp_50y").value;
                    if( v3_tp_60d==""){ v3_tp_60d=0;}
                    if( v3_tp_4y==""){ v3_tp_4y=0;}
                    if( v3_tp_9y==""){ v3_tp_9y=0;}
                    if( v3_tp_14y==""){ v3_tp_14y=0;}
                    if( v3_tp_19y==""){ v3_tp_19y=0;}
                    if( v3_tp_24y==""){ v3_tp_24y=0;}
                    if( v3_tp_29y==""){ v3_tp_29y=0;}
                    if( v3_tp_34y==""){ v3_tp_34y=0;}
                    if( v3_tp_39y==""){ v3_tp_39y=0;}
                    if( v3_tp_44y==""){ v3_tp_44y=0;}
                    if( v3_tp_49y==""){ v3_tp_49y=0;}
                    if( v3_tp_50y==""){ v3_tp_50y=0;}
                    var total_tp=parseInt(v3_tp_60d)+parseInt(v3_tp_4y)+parseInt(v3_tp_9y)+parseInt(v3_tp_14y)+parseInt(v3_tp_19y)+parseInt(v3_tp_24y)+parseInt(v3_tp_29y)+parseInt(v3_tp_34y)+parseInt(v3_tp_39y)+parseInt(v3_tp_44y)+parseInt(v3_tp_49y)+parseInt(v3_tp_50y);
                    document.getElementById("v3_tp_total").value = total_tp;
                    
                    var v3_srp_60d=document.getElementById("v3_srp_60d").value;
                    var v3_srp_4y=document.getElementById("v3_srp_4y").value;
                    var v3_srp_9y=document.getElementById("v3_srp_9y").value;
                    var v3_srp_14y=document.getElementById("v3_srp_14y").value;
                    var v3_srp_19y=document.getElementById("v3_srp_19y").value;
                    var v3_srp_24y=document.getElementById("v3_srp_24y").value;
                    var v3_srp_29y=document.getElementById("v3_srp_29y").value;
                    var v3_srp_34y=document.getElementById("v3_srp_34y").value;
                    var v3_srp_39y=document.getElementById("v3_srp_39y").value;
                    var v3_srp_44y=document.getElementById("v3_srp_44y").value;
                    var v3_srp_49y=document.getElementById("v3_srp_49y").value;
                    var v3_srp_50y=document.getElementById("v3_srp_50y").value;
                    if( v3_srp_60d==""){ v3_srp_60d=0;}
                    if( v3_srp_4y==""){ v3_srp_4y=0;}
                    if( v3_srp_9y==""){ v3_srp_9y=0;}
                    if( v3_srp_14y==""){ v3_srp_14y=0;}
                    if( v3_srp_19y==""){ v3_srp_19y=0;}
                    if( v3_srp_24y==""){ v3_srp_24y=0;}
                    if( v3_srp_29y==""){ v3_srp_29y=0;}
                    if( v3_srp_34y==""){ v3_srp_34y=0;}
                    if( v3_srp_39y==""){ v3_srp_39y=0;}
                    if( v3_srp_44y==""){ v3_srp_44y=0;}
                    if( v3_srp_49y==""){ v3_srp_49y=0;}
                    if( v3_srp_50y==""){ v3_srp_50y=0;}
                    var total_srp=parseInt(v3_srp_60d)+parseInt(v3_srp_4y)+parseInt(v3_srp_9y)+parseInt(v3_srp_14y)+parseInt(v3_srp_19y)+parseInt(v3_srp_24y)+parseInt(v3_srp_29y)+parseInt(v3_srp_34y)+parseInt(v3_srp_39y)+parseInt(v3_srp_44y)+parseInt(v3_srp_49y)+parseInt(v3_srp_50y);
                    document.getElementById("v3_srp_total").value = total_srp;
                    
                    var v3_tn_60d=document.getElementById("v3_tn_60d").value;
                    var v3_tn_4y=document.getElementById("v3_tn_4y").value;
                    var v3_tn_9y=document.getElementById("v3_tn_9y").value;
                    var v3_tn_14y=document.getElementById("v3_tn_14y").value;
                    var v3_tn_19y=document.getElementById("v3_tn_19y").value;
                    var v3_tn_24y=document.getElementById("v3_tn_24y").value;
                    var v3_tn_29y=document.getElementById("v3_tn_29y").value;
                    var v3_tn_34y=document.getElementById("v3_tn_34y").value;
                    var v3_tn_39y=document.getElementById("v3_tn_39y").value;
                    var v3_tn_44y=document.getElementById("v3_tn_44y").value;
                    var v3_tn_49y=document.getElementById("v3_tn_49y").value;
                    var v3_tn_50y=document.getElementById("v3_tn_50y").value;
                    if( v3_tn_60d==""){ v3_tn_60d=0;}
                    if( v3_tn_4y==""){ v3_tn_4y=0;}
                    if( v3_tn_9y==""){ v3_tn_9y=0;}
                    if( v3_tn_14y==""){ v3_tn_14y=0;}
                    if( v3_tn_19y==""){ v3_tn_19y=0;}
                    if( v3_tn_24y==""){ v3_tn_24y=0;}
                    if( v3_tn_29y==""){ v3_tn_29y=0;}
                    if( v3_tn_34y==""){ v3_tn_34y=0;}
                    if( v3_tn_39y==""){ v3_tn_39y=0;}
                    if( v3_tn_44y==""){ v3_tn_44y=0;}
                    if( v3_tn_49y==""){ v3_tn_49y=0;}
                    if( v3_tn_50y==""){ v3_tn_50y=0;}
                    var total_tn=parseInt(v3_tn_60d)+parseInt(v3_tn_4y)+parseInt(v3_tn_9y)+parseInt(v3_tn_14y)+parseInt(v3_tn_19y)+parseInt(v3_tn_24y)+parseInt(v3_tn_29y)+parseInt(v3_tn_34y)+parseInt(v3_tn_39y)+parseInt(v3_tn_44y)+parseInt(v3_tn_49y)+parseInt(v3_tn_50y);
                    document.getElementById("v3_tn_total").value = total_tn;
                    
                    var v3_nt_60d=document.getElementById("v3_nt_60d").value;
                    var v3_nt_4y=document.getElementById("v3_nt_4y").value;
                    var v3_nt_9y=document.getElementById("v3_nt_9y").value;
                    var v3_nt_14y=document.getElementById("v3_nt_14y").value;
                    var v3_nt_19y=document.getElementById("v3_nt_19y").value;
                    var v3_nt_24y=document.getElementById("v3_nt_24y").value;
                    var v3_nt_29y=document.getElementById("v3_nt_29y").value;
                    var v3_nt_34y=document.getElementById("v3_nt_34y").value;
                    var v3_nt_39y=document.getElementById("v3_nt_39y").value;
                    var v3_nt_44y=document.getElementById("v3_nt_44y").value;
                    var v3_nt_49y=document.getElementById("v3_nt_49y").value;
                    var v3_nt_50y=document.getElementById("v3_nt_50y").value;
                    if( v3_nt_60d==""){ v3_nt_60d=0;}
                    if( v3_nt_4y==""){ v3_nt_4y=0;}
                    if( v3_nt_9y==""){ v3_nt_9y=0;}
                    if( v3_nt_14y==""){ v3_nt_14y=0;}
                    if( v3_nt_19y==""){ v3_nt_19y=0;}
                    if( v3_nt_24y==""){ v3_nt_24y=0;}
                    if( v3_nt_29y==""){ v3_nt_29y=0;}
                    if( v3_nt_34y==""){ v3_nt_34y=0;}
                    if( v3_nt_39y==""){ v3_nt_39y=0;}
                    if( v3_nt_44y==""){ v3_nt_44y=0;}
                    if( v3_nt_49y==""){ v3_nt_49y=0;}
                    if( v3_nt_50y==""){ v3_nt_50y=0;}
                    var total_nt=parseInt(v3_nt_60d)+parseInt(v3_nt_4y)+parseInt(v3_nt_9y)+parseInt(v3_nt_14y)+parseInt(v3_nt_19y)+parseInt(v3_nt_24y)+parseInt(v3_nt_29y)+parseInt(v3_nt_34y)+parseInt(v3_nt_39y)+parseInt(v3_nt_44y)+parseInt(v3_nt_49y)+parseInt(v3_nt_50y);
                    document.getElementById("v3_nt_total").value = total_nt;
                    
                    var v3_us_60d=document.getElementById("v3_us_60d").value;
                    var v3_us_4y=document.getElementById("v3_us_4y").value;
                    var v3_us_9y=document.getElementById("v3_us_9y").value;
                    var v3_us_14y=document.getElementById("v3_us_14y").value;
                    var v3_us_19y=document.getElementById("v3_us_19y").value;
                    var v3_us_24y=document.getElementById("v3_us_24y").value;
                    var v3_us_29y=document.getElementById("v3_us_29y").value;
                    var v3_us_34y=document.getElementById("v3_us_34y").value;
                    var v3_us_39y=document.getElementById("v3_us_39y").value;
                    var v3_us_44y=document.getElementById("v3_us_44y").value;
                    var v3_us_49y=document.getElementById("v3_us_49y").value;
                    var v3_us_50y=document.getElementById("v3_us_50y").value;
                    if( v3_us_60d==""){ v3_us_60d=0;}
                    if( v3_us_4y==""){ v3_us_4y=0;}
                    if( v3_us_9y==""){ v3_us_9y=0;}
                    if( v3_us_14y==""){ v3_us_14y=0;}
                    if( v3_us_19y==""){ v3_us_19y=0;}
                    if( v3_us_24y==""){ v3_us_24y=0;}
                    if( v3_us_29y==""){ v3_us_29y=0;}
                    if( v3_us_34y==""){ v3_us_34y=0;}
                    if( v3_us_39y==""){ v3_us_39y=0;}
                    if( v3_us_44y==""){ v3_us_44y=0;}
                    if( v3_us_49y==""){ v3_us_49y=0;}
                    if( v3_us_50y==""){ v3_us_50y=0;}
                    var total_us=parseInt(v3_us_60d)+parseInt(v3_us_4y)+parseInt(v3_us_9y)+parseInt(v3_us_14y)+parseInt(v3_us_19y)+parseInt(v3_us_24y)+parseInt(v3_us_29y)+parseInt(v3_us_34y)+parseInt(v3_us_39y)+parseInt(v3_us_44y)+parseInt(v3_us_49y)+parseInt(v3_us_50y);
                    document.getElementById("v3_us_total").value = total_us;
                    
                    var v3_srn_60d=document.getElementById("v3_srn_60d").value;
                    var v3_srn_4y=document.getElementById("v3_srn_4y").value;
                    var v3_srn_9y=document.getElementById("v3_srn_9y").value;
                    var v3_srn_14y=document.getElementById("v3_srn_14y").value;
                    var v3_srn_19y=document.getElementById("v3_srn_19y").value;
                    var v3_srn_24y=document.getElementById("v3_srn_24y").value;
                    var v3_srn_29y=document.getElementById("v3_srn_29y").value;
                    var v3_srn_34y=document.getElementById("v3_srn_34y").value;
                    var v3_srn_39y=document.getElementById("v3_srn_39y").value;
                    var v3_srn_44y=document.getElementById("v3_srn_44y").value;
                    var v3_srn_49y=document.getElementById("v3_srn_49y").value;
                    var v3_srn_50y=document.getElementById("v3_srn_50y").value;
                    if( v3_srn_60d==""){ v3_srn_60d=0;}
                    if( v3_srn_4y==""){ v3_srn_4y=0;}
                    if( v3_srn_9y==""){ v3_srn_9y=0;}
                    if( v3_srn_14y==""){ v3_srn_14y=0;}
                    if( v3_srn_19y==""){ v3_srn_19y=0;}
                    if( v3_srn_24y==""){ v3_srn_24y=0;}
                    if( v3_srn_29y==""){ v3_srn_29y=0;}
                    if( v3_srn_34y==""){ v3_srn_34y=0;}
                    if( v3_srn_39y==""){ v3_srn_39y=0;}
                    if( v3_srn_44y==""){ v3_srn_44y=0;}
                    if( v3_srn_49y==""){ v3_srn_49y=0;}
                    if( v3_srn_50y==""){ v3_srn_50y=0;}
                    var total_srn=parseInt(v3_srn_60d)+parseInt(v3_srn_4y)+parseInt(v3_srn_9y)+parseInt(v3_srn_14y)+parseInt(v3_srn_19y)+parseInt(v3_srn_24y)+parseInt(v3_srn_29y)+parseInt(v3_srn_34y)+parseInt(v3_srn_39y)+parseInt(v3_srn_44y)+parseInt(v3_srn_49y)+parseInt(v3_srn_50y);
                    document.getElementById("v3_srn_total").value = total_srn;
                    
                    document.getElementById("v3_t_60d").value = parseInt(v3_tp_60d)+parseInt(v3_srp_60d)+parseInt(v3_tn_60d)+parseInt(v3_nt_60d)+parseInt(v3_us_60d)+parseInt(v3_srn_60d);
                    document.getElementById("v3_t_4y").value = parseInt(v3_tp_4y)+parseInt(v3_srp_4y)+parseInt(v3_tn_4y)+parseInt(v3_nt_4y)+parseInt(v3_us_4y)+parseInt(v3_srn_4y);
                    document.getElementById("v3_t_9y").value = parseInt(v3_tp_9y)+parseInt(v3_srp_9y)+parseInt(v3_tn_9y)+parseInt(v3_nt_9y)+parseInt(v3_us_9y)+parseInt(v3_srn_9y);
                    document.getElementById("v3_t_14y").value = parseInt(v3_tp_14y)+parseInt(v3_srp_14y)+parseInt(v3_tn_14y)+parseInt(v3_nt_14y)+parseInt(v3_us_14y)+parseInt(v3_srn_14y);
                    document.getElementById("v3_t_19y").value = parseInt(v3_tp_19y)+parseInt(v3_srp_19y)+parseInt(v3_tn_19y)+parseInt(v3_nt_19y)+parseInt(v3_us_19y)+parseInt(v3_srn_19y);
                    document.getElementById("v3_t_24y").value = parseInt(v3_tp_24y)+parseInt(v3_srp_24y)+parseInt(v3_tn_24y)+parseInt(v3_nt_24y)+parseInt(v3_us_24y)+parseInt(v3_srn_24y);
                    document.getElementById("v3_t_29y").value = parseInt(v3_tp_29y)+parseInt(v3_srp_29y)+parseInt(v3_tn_29y)+parseInt(v3_nt_29y)+parseInt(v3_us_29y)+parseInt(v3_srn_29y);
                    document.getElementById("v3_t_34y").value = parseInt(v3_tp_34y)+parseInt(v3_srp_34y)+parseInt(v3_tn_34y)+parseInt(v3_nt_34y)+parseInt(v3_us_34y)+parseInt(v3_srn_34y);
                    document.getElementById("v3_t_39y").value = parseInt(v3_tp_39y)+parseInt(v3_srp_39y)+parseInt(v3_tn_39y)+parseInt(v3_nt_39y)+parseInt(v3_us_39y)+parseInt(v3_srn_39y);
                    document.getElementById("v3_t_44y").value = parseInt(v3_tp_44y)+parseInt(v3_srp_44y)+parseInt(v3_tn_44y)+parseInt(v3_nt_44y)+parseInt(v3_us_44y)+parseInt(v3_srn_44y);
                    document.getElementById("v3_t_49y").value = parseInt(v3_tp_49y)+parseInt(v3_srp_49y)+parseInt(v3_tn_49y)+parseInt(v3_nt_49y)+parseInt(v3_us_49y)+parseInt(v3_srn_49y);
                    document.getElementById("v3_t_50y").value = parseInt(v3_tp_50y)+parseInt(v3_srp_50y)+parseInt(v3_tn_50y)+parseInt(v3_nt_50y)+parseInt(v3_us_50y)+parseInt(v3_srn_50y);
                    
                    document.getElementById("v3_t_total").value = parseInt(total_tp)+parseInt(total_srp)+parseInt(total_tn)+parseInt(total_nt)+parseInt(total_us)+parseInt(total_srn);
 }
 
 function v4(){
                    var v4_s_vmmc_60d=document.getElementById("v4_s_vmmc_60d").value;
                    var v4_s_vmmc_4y=document.getElementById("v4_s_vmmc_4y").value;
                    var v4_s_vmmc_9y=document.getElementById("v4_s_vmmc_9y").value;
                    var v4_s_vmmc_14y=document.getElementById("v4_s_vmmc_14y").value;
                    var v4_s_vmmc_19y=document.getElementById("v4_s_vmmc_19y").value;
                    var v4_s_vmmc_24y=document.getElementById("v4_s_vmmc_24y").value;
                    var v4_s_vmmc_29y=document.getElementById("v4_s_vmmc_29y").value;
                    var v4_s_vmmc_34y=document.getElementById("v4_s_vmmc_34y").value;
                    var v4_s_vmmc_39y=document.getElementById("v4_s_vmmc_39y").value;
                    var v4_s_vmmc_44y=document.getElementById("v4_s_vmmc_44y").value;
                    var v4_s_vmmc_49y=document.getElementById("v4_s_vmmc_49y").value;
                    var v4_s_vmmc_50y=document.getElementById("v4_s_vmmc_50y").value;
                    if( v4_s_vmmc_60d==""){ v4_s_vmmc_60d=0;}
                    if( v4_s_vmmc_4y==""){ v4_s_vmmc_4y=0;}
                    if( v4_s_vmmc_9y==""){ v4_s_vmmc_9y=0;}
                    if( v4_s_vmmc_14y==""){ v4_s_vmmc_14y=0;}
                    if( v4_s_vmmc_19y==""){ v4_s_vmmc_19y=0;}
                    if( v4_s_vmmc_24y==""){ v4_s_vmmc_24y=0;}
                    if( v4_s_vmmc_29y==""){ v4_s_vmmc_29y=0;}
                    if( v4_s_vmmc_34y==""){ v4_s_vmmc_34y=0;}
                    if( v4_s_vmmc_39y==""){ v4_s_vmmc_39y=0;}
                    if( v4_s_vmmc_44y==""){ v4_s_vmmc_44y=0;}
                    if( v4_s_vmmc_49y==""){ v4_s_vmmc_49y=0;}
                    if( v4_s_vmmc_50y==""){ v4_s_vmmc_50y=0;}
                    var v4_s=parseInt(v4_s_vmmc_60d)+parseInt(v4_s_vmmc_4y)+parseInt(v4_s_vmmc_9y)+parseInt(v4_s_vmmc_14y)+parseInt(v4_s_vmmc_19y)+parseInt(v4_s_vmmc_24y)+parseInt(v4_s_vmmc_29y)+parseInt(v4_s_vmmc_34y)+parseInt(v4_s_vmmc_39y)+parseInt(v4_s_vmmc_44y)+parseInt(v4_s_vmmc_49y)+parseInt(v4_s_vmmc_50y);
                    
                    document.getElementById("v4_s_vmmc_total").value=v4_s;
                    
                    var v4_db_vmmc_60d=document.getElementById("v4_db_vmmc_60d").value;
                    var v4_db_vmmc_4y=document.getElementById("v4_db_vmmc_4y").value;
                    var v4_db_vmmc_9y=document.getElementById("v4_db_vmmc_9y").value;
                    var v4_db_vmmc_14y=document.getElementById("v4_db_vmmc_14y").value;
                    var v4_db_vmmc_19y=document.getElementById("v4_db_vmmc_19y").value;
                    var v4_db_vmmc_24y=document.getElementById("v4_db_vmmc_24y").value;
                    var v4_db_vmmc_29y=document.getElementById("v4_db_vmmc_29y").value;
                    var v4_db_vmmc_34y=document.getElementById("v4_db_vmmc_34y").value;
                    var v4_db_vmmc_39y=document.getElementById("v4_db_vmmc_39y").value;
                    var v4_db_vmmc_44y=document.getElementById("v4_db_vmmc_44y").value;
                    var v4_db_vmmc_49y=document.getElementById("v4_db_vmmc_49y").value;
                    var v4_db_vmmc_50y=document.getElementById("v4_db_vmmc_50y").value;
                    if(v4_db_vmmc_60d==""){v4_db_vmmc_60d=0;}
                    if(v4_db_vmmc_4y==""){v4_db_vmmc_4y=0;}
                    if(v4_db_vmmc_9y==""){v4_db_vmmc_9y=0;}
                    if(v4_db_vmmc_14y==""){v4_db_vmmc_14y=0;}
                    if(v4_db_vmmc_19y==""){v4_db_vmmc_19y=0;}
                    if(v4_db_vmmc_24y==""){v4_db_vmmc_24y=0;}
                    if(v4_db_vmmc_29y==""){v4_db_vmmc_29y=0;}
                    if(v4_db_vmmc_34y==""){v4_db_vmmc_34y=0;}
                    if(v4_db_vmmc_39y==""){v4_db_vmmc_39y=0;}
                    if(v4_db_vmmc_44y==""){v4_db_vmmc_44y=0;}
                    if(v4_db_vmmc_49y==""){v4_db_vmmc_49y=0;}
                    if(v4_db_vmmc_50y==""){v4_db_vmmc_50y=0;}
                    var v4_db=parseInt(v4_db_vmmc_60d)+parseInt(v4_db_vmmc_4y)+parseInt(v4_db_vmmc_9y)+parseInt(v4_db_vmmc_14y)+parseInt(v4_db_vmmc_19y)+parseInt(v4_db_vmmc_24y)+parseInt(v4_db_vmmc_29y)+parseInt(v4_db_vmmc_34y)+parseInt(v4_db_vmmc_39y)+parseInt(v4_db_vmmc_44y)+parseInt(v4_db_vmmc_49y)+parseInt(v4_db_vmmc_50y);
                    
                    document.getElementById("v4_db_vmmc_total").value=v4_db;
                    
                    var v4_t_vmmc_60d=document.getElementById("v4_t_vmmc_60d").value=parseInt(v4_s_vmmc_60d)+parseInt(v4_db_vmmc_60d);
                    var v4_t_vmmc_4y=document.getElementById("v4_t_vmmc_4y").value=parseInt(v4_s_vmmc_4y)+parseInt(v4_db_vmmc_4y);
                    var v4_t_vmmc_9y=document.getElementById("v4_t_vmmc_9y").value=parseInt(v4_s_vmmc_9y)+parseInt(v4_db_vmmc_9y);
                    var v4_t_vmmc_14y=document.getElementById("v4_t_vmmc_14y").value=parseInt(v4_s_vmmc_14y)+parseInt(v4_db_vmmc_14y);
                    var v4_t_vmmc_19y=document.getElementById("v4_t_vmmc_19y").value=parseInt(v4_s_vmmc_19y)+parseInt(v4_db_vmmc_19y);
                    var v4_t_vmmc_24y=document.getElementById("v4_t_vmmc_24y").value=parseInt(v4_s_vmmc_24y)+parseInt(v4_db_vmmc_24y);
                    var v4_t_vmmc_29y=document.getElementById("v4_t_vmmc_29y").value=parseInt(v4_s_vmmc_29y)+parseInt(v4_db_vmmc_29y);
                    var v4_t_vmmc_34y=document.getElementById("v4_t_vmmc_34y").value=parseInt(v4_s_vmmc_34y)+parseInt(v4_db_vmmc_34y);
                    var v4_t_vmmc_39y=document.getElementById("v4_t_vmmc_39y").value=parseInt(v4_s_vmmc_39y)+parseInt(v4_db_vmmc_39y);
                    var v4_t_vmmc_44y=document.getElementById("v4_t_vmmc_44y").value=parseInt(v4_s_vmmc_44y)+parseInt(v4_db_vmmc_44y);
                    var v4_t_vmmc_49y=document.getElementById("v4_t_vmmc_49y").value=parseInt(v4_s_vmmc_49y)+parseInt(v4_db_vmmc_49y);
                    var v4_t_vmmc_50y=document.getElementById("v4_t_vmmc_50y").value=parseInt(v4_s_vmmc_50y)+parseInt(v4_db_vmmc_50y);
                    
                    document.getElementById("v4_t_vmmc_total").value=parseInt(v4_s)+parseInt(v4_db);
 }
 
 
 function v5_followup(){
     	            var v5_followup_60d=document.getElementById("v5_followup_60d").value;
                    var v5_followup_4y=document.getElementById("v5_followup_4y").value;
                    var v5_followup_9y=document.getElementById("v5_followup_9y").value;
                    var v5_followup_14y=document.getElementById("v5_followup_14y").value;
                    var v5_followup_19y=document.getElementById("v5_followup_19y").value;
                    var v5_followup_24y=document.getElementById("v5_followup_24y").value;
                    var v5_followup_29y=document.getElementById("v5_followup_29y").value;
                    var v5_followup_34y=document.getElementById("v5_followup_34y").value;
                    var v5_followup_39y=document.getElementById("v5_followup_39y").value;
                    var v5_followup_44y=document.getElementById("v5_followup_44y").value;
                    var v5_followup_49y=document.getElementById("v5_followup_49y").value;
                    var v5_followup_50y=document.getElementById("v5_followup_50y").value;
                    if(v5_followup_60d==""){v5_followup_60d=0;}
                    if(v5_followup_4y==""){v5_followup_4y=0;}
                    if(v5_followup_9y==""){v5_followup_9y=0;}
                    if(v5_followup_14y==""){v5_followup_14y=0;}
                    if(v5_followup_19y==""){v5_followup_19y=0;}
                    if(v5_followup_24y==""){v5_followup_24y=0;}
                    if(v5_followup_29y==""){v5_followup_29y=0;}
                    if(v5_followup_34y==""){v5_followup_34y=0;}
                    if(v5_followup_39y==""){v5_followup_39y=0;}
                    if(v5_followup_44y==""){v5_followup_44y=0;}
                    if(v5_followup_49y==""){v5_followup_49y=0;}
                    if(v5_followup_50y==""){v5_followup_50y=0;}
                    
                   var v5=parseInt(v5_followup_60d)+ parseInt(v5_followup_4y)+ parseInt(v5_followup_9y)+ parseInt(v5_followup_14y)+ parseInt(v5_followup_19y)+ parseInt(v5_followup_24y)+ parseInt(v5_followup_29y)+ parseInt(v5_followup_34y)+ parseInt(v5_followup_39y)+ parseInt(v5_followup_44y)+ parseInt(v5_followup_49y)+ parseInt(v5_followup_50y);
                   document.getElementById("v5_followup_total").value=v5;
 }
 
 function v6_nofollowup(){
                    var v6_nofollowup_60d=document.getElementById("v6_nofollowup_60d").value;
                    var v6_nofollowup_4y=document.getElementById("v6_nofollowup_4y").value;
                    var v6_nofollowup_9y=document.getElementById("v6_nofollowup_9y").value;
                    var v6_nofollowup_14y=document.getElementById("v6_nofollowup_14y").value;
                    var v6_nofollowup_19y=document.getElementById("v6_nofollowup_19y").value;
                    var v6_nofollowup_24y=document.getElementById("v6_nofollowup_24y").value;
                    var v6_nofollowup_29y=document.getElementById("v6_nofollowup_29y").value;
                    var v6_nofollowup_34y=document.getElementById("v6_nofollowup_34y").value;
                    var v6_nofollowup_39y=document.getElementById("v6_nofollowup_39y").value;
                    var v6_nofollowup_44y=document.getElementById("v6_nofollowup_44y").value;
                    var v6_nofollowup_49y=document.getElementById("v6_nofollowup_49y").value;
                    var v6_nofollowup_50y=document.getElementById("v6_nofollowup_50y").value;
                    if(v6_nofollowup_60d==""){v6_nofollowup_60d=0;}
                    if(v6_nofollowup_4y==""){v6_nofollowup_4y=0;}
                    if(v6_nofollowup_9y==""){v6_nofollowup_9y=0;}
                    if(v6_nofollowup_14y==""){v6_nofollowup_14y=0;}
                    if(v6_nofollowup_19y==""){v6_nofollowup_19y=0;}
                    if(v6_nofollowup_24y==""){v6_nofollowup_24y=0;}
                    if(v6_nofollowup_29y==""){v6_nofollowup_29y=0;}
                    if(v6_nofollowup_34y==""){v6_nofollowup_34y=0;}
                    if(v6_nofollowup_39y==""){v6_nofollowup_39y=0;}
                    if(v6_nofollowup_44y==""){v6_nofollowup_44y=0;}
                    if(v6_nofollowup_49y==""){v6_nofollowup_49y=0;}
                    if(v6_nofollowup_50y==""){v6_nofollowup_50y=0;}
                    var v6=parseInt(v6_nofollowup_60d)+parseInt(v6_nofollowup_4y)+ parseInt(v6_nofollowup_9y)+ parseInt(v6_nofollowup_14y)+ parseInt(v6_nofollowup_19y)+ parseInt(v6_nofollowup_24y)+ parseInt(v6_nofollowup_29y)+ parseInt(v6_nofollowup_34y)+ parseInt(v6_nofollowup_39y)+ parseInt(v6_nofollowup_44y)+ parseInt(v6_nofollowup_49y)+ parseInt(v6_nofollowup_50y);
                    document.getElementById("v6_nofollowup_total").value=v6;
 }