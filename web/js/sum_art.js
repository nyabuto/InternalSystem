/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function newART(){
var new_1m = 0;
var new_1f = 0;
var new_4m = 0;
var new_4f = 0;
var new_9m = 0;
var new_9f = 0;
var new_14m = 0;
var new_14f = 0;
var new_19m = 0;
var new_19f = 0;
var new_24m = 0;
var new_24f = 0;
var new_29m = 0;
var new_29f = 0;
var new_34m = 0;
var new_34f = 0;
var new_39m = 0;
var new_39f = 0;
var new_49m = 0;
var new_49f = 0;
var new_50m = 0;
var new_50f = 0;
var new_STm = 0;
var new_STf = 0;
var new_GT = 0;

if(document.getElementById("new_1m").value!=""){ new_1m = parseInt(document.getElementById("new_1m").value);}
if(document.getElementById("new_1f").value!=""){ new_1f = parseInt(document.getElementById("new_1f").value);}
if(document.getElementById("new_4m").value!=""){ new_4m = parseInt(document.getElementById("new_4m").value);}
if(document.getElementById("new_4f").value!=""){ new_4f = parseInt(document.getElementById("new_4f").value);}
if(document.getElementById("new_9m").value!=""){ new_9m = parseInt(document.getElementById("new_9m").value);}
if(document.getElementById("new_9f").value!=""){ new_9f = parseInt(document.getElementById("new_9f").value);}
if(document.getElementById("new_14m").value!=""){ new_14m = parseInt(document.getElementById("new_14m").value);}
if(document.getElementById("new_14f").value!=""){ new_14f = parseInt(document.getElementById("new_14f").value);}
if(document.getElementById("new_19m").value!=""){ new_19m = parseInt(document.getElementById("new_19m").value);}
if(document.getElementById("new_19f").value!=""){ new_19f = parseInt(document.getElementById("new_19f").value);}
if(document.getElementById("new_24m").value!=""){ new_24m = parseInt(document.getElementById("new_24m").value);}
if(document.getElementById("new_24f").value!=""){ new_24f = parseInt(document.getElementById("new_24f").value);}
if(document.getElementById("new_29m").value!=""){ new_29m = parseInt(document.getElementById("new_29m").value);}
if(document.getElementById("new_29f").value!=""){ new_29f = parseInt(document.getElementById("new_29f").value);}
if(document.getElementById("new_34m").value!=""){ new_34m = parseInt(document.getElementById("new_34m").value);}
if(document.getElementById("new_34f").value!=""){ new_34f = parseInt(document.getElementById("new_34f").value);}
if(document.getElementById("new_39m").value!=""){ new_39m = parseInt(document.getElementById("new_39m").value);}
if(document.getElementById("new_39f").value!=""){ new_39f = parseInt(document.getElementById("new_39f").value);}
if(document.getElementById("new_49m").value!=""){ new_49m = parseInt(document.getElementById("new_49m").value);}
if(document.getElementById("new_49f").value!=""){ new_49f = parseInt(document.getElementById("new_49f").value);}
if(document.getElementById("new_50m").value!=""){ new_50m = parseInt(document.getElementById("new_50m").value);}
if(document.getElementById("new_50f").value!=""){ new_50f = parseInt(document.getElementById("new_50f").value);}

new_STm = new_1m+new_4m+new_9m+new_14m+new_19m+new_24m+new_29m+new_34m+new_39m+new_49m+new_50m;
new_STf = new_1f+new_4f+new_9f+new_14f+new_19f+new_24f+new_29f+new_34f+new_39f+new_49f+new_50f;
document.getElementById("new_STm").value = new_STm;
document.getElementById("new_STf").value = new_STf;

new_GT = new_STm+new_STf;
document.getElementById("new_GT").value = new_GT;
}
function currentART(){
var current_1m = 0;
var current_1f = 0;
var current_4m = 0;
var current_4f = 0;
var current_9m = 0;
var current_9f = 0;
var current_14m = 0;
var current_14f = 0;
var current_19m = 0;
var current_19f = 0;
var current_24m = 0;
var current_24f = 0;
var current_29m = 0;
var current_29f = 0;
var current_34m = 0;
var current_34f = 0;
var current_39m = 0;
var current_39f = 0;
var current_49m = 0;
var current_49f = 0;
var current_50m = 0;
var current_50f = 0;
var current_STm = 0;
var current_STf = 0;
var current_GT = 0;

if(document.getElementById("current_1m").value!=""){ current_1m = parseInt(document.getElementById("current_1m").value);}
if(document.getElementById("current_1f").value!=""){ current_1f = parseInt(document.getElementById("current_1f").value);}
if(document.getElementById("current_4m").value!=""){ current_4m = parseInt(document.getElementById("current_4m").value);}
if(document.getElementById("current_4f").value!=""){ current_4f = parseInt(document.getElementById("current_4f").value);}
if(document.getElementById("current_9m").value!=""){ current_9m = parseInt(document.getElementById("current_9m").value);}
if(document.getElementById("current_9f").value!=""){ current_9f = parseInt(document.getElementById("current_9f").value);}
if(document.getElementById("current_14m").value!=""){ current_14m = parseInt(document.getElementById("current_14m").value);}
if(document.getElementById("current_14f").value!=""){ current_14f = parseInt(document.getElementById("current_14f").value);}
if(document.getElementById("current_19m").value!=""){ current_19m = parseInt(document.getElementById("current_19m").value);}
if(document.getElementById("current_19f").value!=""){ current_19f = parseInt(document.getElementById("current_19f").value);}
if(document.getElementById("current_24m").value!=""){ current_24m = parseInt(document.getElementById("current_24m").value);}
if(document.getElementById("current_24f").value!=""){ current_24f = parseInt(document.getElementById("current_24f").value);}
if(document.getElementById("current_29m").value!=""){ current_29m = parseInt(document.getElementById("current_29m").value);}
if(document.getElementById("current_29f").value!=""){ current_29f = parseInt(document.getElementById("current_29f").value);}
if(document.getElementById("current_34m").value!=""){ current_34m = parseInt(document.getElementById("current_34m").value);}
if(document.getElementById("current_34f").value!=""){ current_34f = parseInt(document.getElementById("current_34f").value);}
if(document.getElementById("current_39m").value!=""){ current_39m = parseInt(document.getElementById("current_39m").value);}
if(document.getElementById("current_39f").value!=""){ current_39f = parseInt(document.getElementById("current_39f").value);}
if(document.getElementById("current_49m").value!=""){ current_49m = parseInt(document.getElementById("current_49m").value);}
if(document.getElementById("current_49f").value!=""){ current_49f = parseInt(document.getElementById("current_49f").value);}
if(document.getElementById("current_50m").value!=""){ current_50m = parseInt(document.getElementById("current_50m").value);}
if(document.getElementById("current_50f").value!=""){ current_50f = parseInt(document.getElementById("current_50f").value);}

current_STm = current_1m+current_4m+current_9m+current_14m+current_19m+current_24m+current_29m+current_34m+current_39m+current_49m+current_50m;
current_STf = current_1f+current_4f+current_9f+current_14f+current_19f+current_24f+current_29f+current_34f+current_39f+current_49f+current_50f;
document.getElementById("current_STm").value = current_STm;
document.getElementById("current_STf").value = current_STf;

current_GT = current_STm+current_STf;
document.getElementById("current_GT").value = current_GT; 
}