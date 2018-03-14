/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function index_clients_listed(){
var clientsListed_1m = 0;
var clientsListed_1f = 0;
var clientsListed_4m = 0;
var clientsListed_4f = 0;
var clientsListed_9m = 0;
var clientsListed_9f = 0;
var clientsListed_14m = 0;
var clientsListed_14f = 0;
var clientsListed_19m = 0;
var clientsListed_19f = 0;
var clientsListed_24m = 0;
var clientsListed_24f = 0;
var clientsListed_29m = 0;
var clientsListed_29f = 0;
var clientsListed_34m = 0;
var clientsListed_34f = 0;
var clientsListed_39m = 0;
var clientsListed_39f = 0;
var clientsListed_49m = 0;
var clientsListed_49f = 0;
var clientsListed_50m = 0;
var clientsListed_50f = 0;
var clientsListed_STm = 0;
var clientsListed_STf = 0;
var clientsListed_GT = 0;

if(document.getElementById("clientsListed_1m").value!=""){ clientsListed_1m = parseInt(document.getElementById("clientsListed_1m").value);}
if(document.getElementById("clientsListed_1f").value!=""){ clientsListed_1f = parseInt(document.getElementById("clientsListed_1f").value);}
if(document.getElementById("clientsListed_4m").value!=""){ clientsListed_4m = parseInt(document.getElementById("clientsListed_4m").value);}
if(document.getElementById("clientsListed_4f").value!=""){ clientsListed_4f = parseInt(document.getElementById("clientsListed_4f").value);}
if(document.getElementById("clientsListed_9m").value!=""){ clientsListed_9m = parseInt(document.getElementById("clientsListed_9m").value);}
if(document.getElementById("clientsListed_9f").value!=""){ clientsListed_9f = parseInt(document.getElementById("clientsListed_9f").value);}
if(document.getElementById("clientsListed_14m").value!=""){ clientsListed_14m = parseInt(document.getElementById("clientsListed_14m").value);}
if(document.getElementById("clientsListed_14f").value!=""){ clientsListed_14f = parseInt(document.getElementById("clientsListed_14f").value);}
if(document.getElementById("clientsListed_19m").value!=""){ clientsListed_19m = parseInt(document.getElementById("clientsListed_19m").value);}
if(document.getElementById("clientsListed_19f").value!=""){ clientsListed_19f = parseInt(document.getElementById("clientsListed_19f").value);}
if(document.getElementById("clientsListed_24m").value!=""){ clientsListed_24m = parseInt(document.getElementById("clientsListed_24m").value);}
if(document.getElementById("clientsListed_24f").value!=""){ clientsListed_24f = parseInt(document.getElementById("clientsListed_24f").value);}
if(document.getElementById("clientsListed_29m").value!=""){ clientsListed_29m = parseInt(document.getElementById("clientsListed_29m").value);}
if(document.getElementById("clientsListed_29f").value!=""){ clientsListed_29f = parseInt(document.getElementById("clientsListed_29f").value);}
if(document.getElementById("clientsListed_34m").value!=""){ clientsListed_34m = parseInt(document.getElementById("clientsListed_34m").value);}
if(document.getElementById("clientsListed_34f").value!=""){ clientsListed_34f = parseInt(document.getElementById("clientsListed_34f").value);}
if(document.getElementById("clientsListed_39m").value!=""){ clientsListed_39m = parseInt(document.getElementById("clientsListed_39m").value);}
if(document.getElementById("clientsListed_39f").value!=""){ clientsListed_39f = parseInt(document.getElementById("clientsListed_39f").value);}
if(document.getElementById("clientsListed_49m").value!=""){ clientsListed_49m = parseInt(document.getElementById("clientsListed_49m").value);}
if(document.getElementById("clientsListed_49f").value!=""){ clientsListed_49f = parseInt(document.getElementById("clientsListed_49f").value);}
if(document.getElementById("clientsListed_50m").value!=""){ clientsListed_50m = parseInt(document.getElementById("clientsListed_50m").value);}
if(document.getElementById("clientsListed_50f").value!=""){ clientsListed_50f = parseInt(document.getElementById("clientsListed_50f").value);}

clientsListed_STm = clientsListed_1m+clientsListed_4m+clientsListed_9m+clientsListed_14m+clientsListed_19m+clientsListed_24m+clientsListed_29m+clientsListed_34m+clientsListed_39m+clientsListed_49m+clientsListed_50m;
clientsListed_STf = clientsListed_1f+clientsListed_4f+clientsListed_9f+clientsListed_14f+clientsListed_19f+clientsListed_24f+clientsListed_29f+clientsListed_34f+clientsListed_39f+clientsListed_49f+clientsListed_50f;
document.getElementById("clientsListed_STm").value = clientsListed_STm;
document.getElementById("clientsListed_STf").value = clientsListed_STf;

clientsListed_GT = clientsListed_STm+clientsListed_STf;
document.getElementById("clientsListed_GT").value = clientsListed_GT;
}
function index_clients_startedART(){
var clientStartedART_1m = 0;
var clientStartedART_1f = 0;
var clientStartedART_4m = 0;
var clientStartedART_4f = 0;
var clientStartedART_9m = 0;
var clientStartedART_9f = 0;
var clientStartedART_14m = 0;
var clientStartedART_14f = 0;
var clientStartedART_19m = 0;
var clientStartedART_19f = 0;
var clientStartedART_24m = 0;
var clientStartedART_24f = 0;
var clientStartedART_29m = 0;
var clientStartedART_29f = 0;
var clientStartedART_34m = 0;
var clientStartedART_34f = 0;
var clientStartedART_39m = 0;
var clientStartedART_39f = 0;
var clientStartedART_49m = 0;
var clientStartedART_49f = 0;
var clientStartedART_50m = 0;
var clientStartedART_50f = 0;
var clientStartedART_STm = 0;
var clientStartedART_STf = 0;
var clientStartedART_GT = 0;

if(document.getElementById("clientStartedART_1m").value!=""){ clientStartedART_1m = parseInt(document.getElementById("clientStartedART_1m").value);}
if(document.getElementById("clientStartedART_1f").value!=""){ clientStartedART_1f = parseInt(document.getElementById("clientStartedART_1f").value);}
if(document.getElementById("clientStartedART_4m").value!=""){ clientStartedART_4m = parseInt(document.getElementById("clientStartedART_4m").value);}
if(document.getElementById("clientStartedART_4f").value!=""){ clientStartedART_4f = parseInt(document.getElementById("clientStartedART_4f").value);}
if(document.getElementById("clientStartedART_9m").value!=""){ clientStartedART_9m = parseInt(document.getElementById("clientStartedART_9m").value);}
if(document.getElementById("clientStartedART_9f").value!=""){ clientStartedART_9f = parseInt(document.getElementById("clientStartedART_9f").value);}
if(document.getElementById("clientStartedART_14m").value!=""){ clientStartedART_14m = parseInt(document.getElementById("clientStartedART_14m").value);}
if(document.getElementById("clientStartedART_14f").value!=""){ clientStartedART_14f = parseInt(document.getElementById("clientStartedART_14f").value);}
if(document.getElementById("clientStartedART_19m").value!=""){ clientStartedART_19m = parseInt(document.getElementById("clientStartedART_19m").value);}
if(document.getElementById("clientStartedART_19f").value!=""){ clientStartedART_19f = parseInt(document.getElementById("clientStartedART_19f").value);}
if(document.getElementById("clientStartedART_24m").value!=""){ clientStartedART_24m = parseInt(document.getElementById("clientStartedART_24m").value);}
if(document.getElementById("clientStartedART_24f").value!=""){ clientStartedART_24f = parseInt(document.getElementById("clientStartedART_24f").value);}
if(document.getElementById("clientStartedART_29m").value!=""){ clientStartedART_29m = parseInt(document.getElementById("clientStartedART_29m").value);}
if(document.getElementById("clientStartedART_29f").value!=""){ clientStartedART_29f = parseInt(document.getElementById("clientStartedART_29f").value);}
if(document.getElementById("clientStartedART_34m").value!=""){ clientStartedART_34m = parseInt(document.getElementById("clientStartedART_34m").value);}
if(document.getElementById("clientStartedART_34f").value!=""){ clientStartedART_34f = parseInt(document.getElementById("clientStartedART_34f").value);}
if(document.getElementById("clientStartedART_39m").value!=""){ clientStartedART_39m = parseInt(document.getElementById("clientStartedART_39m").value);}
if(document.getElementById("clientStartedART_39f").value!=""){ clientStartedART_39f = parseInt(document.getElementById("clientStartedART_39f").value);}
if(document.getElementById("clientStartedART_49m").value!=""){ clientStartedART_49m = parseInt(document.getElementById("clientStartedART_49m").value);}
if(document.getElementById("clientStartedART_49f").value!=""){ clientStartedART_49f = parseInt(document.getElementById("clientStartedART_49f").value);}
if(document.getElementById("clientStartedART_50m").value!=""){ clientStartedART_50m = parseInt(document.getElementById("clientStartedART_50m").value);}
if(document.getElementById("clientStartedART_50f").value!=""){ clientStartedART_50f = parseInt(document.getElementById("clientStartedART_50f").value);}

clientStartedART_STm = clientStartedART_1m+clientStartedART_4m+clientStartedART_9m+clientStartedART_14m+clientStartedART_19m+clientStartedART_24m+clientStartedART_29m+clientStartedART_34m+clientStartedART_39m+clientStartedART_49m+clientStartedART_50m;
clientStartedART_STf = clientStartedART_1f+clientStartedART_4f+clientStartedART_9f+clientStartedART_14f+clientStartedART_19f+clientStartedART_24f+clientStartedART_29f+clientStartedART_34f+clientStartedART_39f+clientStartedART_49f+clientStartedART_50f;
document.getElementById("clientStartedART_STm").value = clientStartedART_STm;
document.getElementById("clientStartedART_STf").value = clientStartedART_STf;

clientStartedART_GT = clientStartedART_STm+clientStartedART_STf;
document.getElementById("clientStartedART_GT").value = clientStartedART_GT;
}
function contacts_listed(){
var contactsListed_1m = 0;
var contactsListed_1f = 0;
var contactsListed_4m = 0;
var contactsListed_4f = 0;
var contactsListed_9m = 0;
var contactsListed_9f = 0;
var contactsListed_14m = 0;
var contactsListed_14f = 0;
var contactsListed_19m = 0;
var contactsListed_19f = 0;
var contactsListed_24m = 0;
var contactsListed_24f = 0;
var contactsListed_29m = 0;
var contactsListed_29f = 0;
var contactsListed_34m = 0;
var contactsListed_34f = 0;
var contactsListed_39m = 0;
var contactsListed_39f = 0;
var contactsListed_49m = 0;
var contactsListed_49f = 0;
var contactsListed_50m = 0;
var contactsListed_50f = 0;
var contactsListed_STm = 0;
var contactsListed_STf = 0;
var contactsListed_GT = 0;

if(document.getElementById("contactsListed_1m").value!=""){ contactsListed_1m = parseInt(document.getElementById("contactsListed_1m").value);}
if(document.getElementById("contactsListed_1f").value!=""){ contactsListed_1f = parseInt(document.getElementById("contactsListed_1f").value);}
if(document.getElementById("contactsListed_4m").value!=""){ contactsListed_4m = parseInt(document.getElementById("contactsListed_4m").value);}
if(document.getElementById("contactsListed_4f").value!=""){ contactsListed_4f = parseInt(document.getElementById("contactsListed_4f").value);}
if(document.getElementById("contactsListed_9m").value!=""){ contactsListed_9m = parseInt(document.getElementById("contactsListed_9m").value);}
if(document.getElementById("contactsListed_9f").value!=""){ contactsListed_9f = parseInt(document.getElementById("contactsListed_9f").value);}
if(document.getElementById("contactsListed_14m").value!=""){ contactsListed_14m = parseInt(document.getElementById("contactsListed_14m").value);}
if(document.getElementById("contactsListed_14f").value!=""){ contactsListed_14f = parseInt(document.getElementById("contactsListed_14f").value);}
if(document.getElementById("contactsListed_19m").value!=""){ contactsListed_19m = parseInt(document.getElementById("contactsListed_19m").value);}
if(document.getElementById("contactsListed_19f").value!=""){ contactsListed_19f = parseInt(document.getElementById("contactsListed_19f").value);}
if(document.getElementById("contactsListed_24m").value!=""){ contactsListed_24m = parseInt(document.getElementById("contactsListed_24m").value);}
if(document.getElementById("contactsListed_24f").value!=""){ contactsListed_24f = parseInt(document.getElementById("contactsListed_24f").value);}
if(document.getElementById("contactsListed_29m").value!=""){ contactsListed_29m = parseInt(document.getElementById("contactsListed_29m").value);}
if(document.getElementById("contactsListed_29f").value!=""){ contactsListed_29f = parseInt(document.getElementById("contactsListed_29f").value);}
if(document.getElementById("contactsListed_34m").value!=""){ contactsListed_34m = parseInt(document.getElementById("contactsListed_34m").value);}
if(document.getElementById("contactsListed_34f").value!=""){ contactsListed_34f = parseInt(document.getElementById("contactsListed_34f").value);}
if(document.getElementById("contactsListed_39m").value!=""){ contactsListed_39m = parseInt(document.getElementById("contactsListed_39m").value);}
if(document.getElementById("contactsListed_39f").value!=""){ contactsListed_39f = parseInt(document.getElementById("contactsListed_39f").value);}
if(document.getElementById("contactsListed_49m").value!=""){ contactsListed_49m = parseInt(document.getElementById("contactsListed_49m").value);}
if(document.getElementById("contactsListed_49f").value!=""){ contactsListed_49f = parseInt(document.getElementById("contactsListed_49f").value);}
if(document.getElementById("contactsListed_50m").value!=""){ contactsListed_50m = parseInt(document.getElementById("contactsListed_50m").value);}
if(document.getElementById("contactsListed_50f").value!=""){ contactsListed_50f = parseInt(document.getElementById("contactsListed_50f").value);}

contactsListed_STm = contactsListed_1m+contactsListed_4m+contactsListed_9m+contactsListed_14m+contactsListed_19m+contactsListed_24m+contactsListed_29m+contactsListed_34m+contactsListed_39m+contactsListed_49m+contactsListed_50m;
contactsListed_STf = contactsListed_1f+contactsListed_4f+contactsListed_9f+contactsListed_14f+contactsListed_19f+contactsListed_24f+contactsListed_29f+contactsListed_34f+contactsListed_39f+contactsListed_49f+contactsListed_50f;
document.getElementById("contactsListed_STm").value = contactsListed_STm;
document.getElementById("contactsListed_STf").value = contactsListed_STf;

contactsListed_GT = contactsListed_STm+contactsListed_STf;
document.getElementById("contactsListed_GT").value = contactsListed_GT; 
}
function contacts_newly_tested(){
var newlyTested_1m = 0;
var newlyTested_1f = 0;
var newlyTested_4m = 0;
var newlyTested_4f = 0;
var newlyTested_9m = 0;
var newlyTested_9f = 0;
var newlyTested_14m = 0;
var newlyTested_14f = 0;
var newlyTested_19m = 0;
var newlyTested_19f = 0;
var newlyTested_24m = 0;
var newlyTested_24f = 0;
var newlyTested_29m = 0;
var newlyTested_29f = 0;
var newlyTested_34m = 0;
var newlyTested_34f = 0;
var newlyTested_39m = 0;
var newlyTested_39f = 0;
var newlyTested_49m = 0;
var newlyTested_49f = 0;
var newlyTested_50m = 0;
var newlyTested_50f = 0;
var newlyTested_STm = 0;
var newlyTested_STf = 0;
var newlyTested_GT = 0;

if(document.getElementById("newlyTested_1m").value!=""){ newlyTested_1m = parseInt(document.getElementById("newlyTested_1m").value);}
if(document.getElementById("newlyTested_1f").value!=""){ newlyTested_1f = parseInt(document.getElementById("newlyTested_1f").value);}
if(document.getElementById("newlyTested_4m").value!=""){ newlyTested_4m = parseInt(document.getElementById("newlyTested_4m").value);}
if(document.getElementById("newlyTested_4f").value!=""){ newlyTested_4f = parseInt(document.getElementById("newlyTested_4f").value);}
if(document.getElementById("newlyTested_9m").value!=""){ newlyTested_9m = parseInt(document.getElementById("newlyTested_9m").value);}
if(document.getElementById("newlyTested_9f").value!=""){ newlyTested_9f = parseInt(document.getElementById("newlyTested_9f").value);}
if(document.getElementById("newlyTested_14m").value!=""){ newlyTested_14m = parseInt(document.getElementById("newlyTested_14m").value);}
if(document.getElementById("newlyTested_14f").value!=""){ newlyTested_14f = parseInt(document.getElementById("newlyTested_14f").value);}
if(document.getElementById("newlyTested_19m").value!=""){ newlyTested_19m = parseInt(document.getElementById("newlyTested_19m").value);}
if(document.getElementById("newlyTested_19f").value!=""){ newlyTested_19f = parseInt(document.getElementById("newlyTested_19f").value);}
if(document.getElementById("newlyTested_24m").value!=""){ newlyTested_24m = parseInt(document.getElementById("newlyTested_24m").value);}
if(document.getElementById("newlyTested_24f").value!=""){ newlyTested_24f = parseInt(document.getElementById("newlyTested_24f").value);}
if(document.getElementById("newlyTested_29m").value!=""){ newlyTested_29m = parseInt(document.getElementById("newlyTested_29m").value);}
if(document.getElementById("newlyTested_29f").value!=""){ newlyTested_29f = parseInt(document.getElementById("newlyTested_29f").value);}
if(document.getElementById("newlyTested_34m").value!=""){ newlyTested_34m = parseInt(document.getElementById("newlyTested_34m").value);}
if(document.getElementById("newlyTested_34f").value!=""){ newlyTested_34f = parseInt(document.getElementById("newlyTested_34f").value);}
if(document.getElementById("newlyTested_39m").value!=""){ newlyTested_39m = parseInt(document.getElementById("newlyTested_39m").value);}
if(document.getElementById("newlyTested_39f").value!=""){ newlyTested_39f = parseInt(document.getElementById("newlyTested_39f").value);}
if(document.getElementById("newlyTested_49m").value!=""){ newlyTested_49m = parseInt(document.getElementById("newlyTested_49m").value);}
if(document.getElementById("newlyTested_49f").value!=""){ newlyTested_49f = parseInt(document.getElementById("newlyTested_49f").value);}
if(document.getElementById("newlyTested_50m").value!=""){ newlyTested_50m = parseInt(document.getElementById("newlyTested_50m").value);}
if(document.getElementById("newlyTested_50f").value!=""){ newlyTested_50f = parseInt(document.getElementById("newlyTested_50f").value);}

newlyTested_STm = newlyTested_1m+newlyTested_4m+newlyTested_9m+newlyTested_14m+newlyTested_19m+newlyTested_24m+newlyTested_29m+newlyTested_34m+newlyTested_39m+newlyTested_49m+newlyTested_50m;
newlyTested_STf = newlyTested_1f+newlyTested_4f+newlyTested_9f+newlyTested_14f+newlyTested_19f+newlyTested_24f+newlyTested_29f+newlyTested_34f+newlyTested_39f+newlyTested_49f+newlyTested_50f;
document.getElementById("newlyTested_STm").value = newlyTested_STm;
document.getElementById("newlyTested_STf").value = newlyTested_STf;

newlyTested_GT = newlyTested_STm+newlyTested_STf;
document.getElementById("newlyTested_GT").value = newlyTested_GT; 
}
function contacts_HIVPos(){
var identifiedPOS_1m = 0;
var identifiedPOS_1f = 0;
var identifiedPOS_4m = 0;
var identifiedPOS_4f = 0;
var identifiedPOS_9m = 0;
var identifiedPOS_9f = 0;
var identifiedPOS_14m = 0;
var identifiedPOS_14f = 0;
var identifiedPOS_19m = 0;
var identifiedPOS_19f = 0;
var identifiedPOS_24m = 0;
var identifiedPOS_24f = 0;
var identifiedPOS_29m = 0;
var identifiedPOS_29f = 0;
var identifiedPOS_34m = 0;
var identifiedPOS_34f = 0;
var identifiedPOS_39m = 0;
var identifiedPOS_39f = 0;
var identifiedPOS_49m = 0;
var identifiedPOS_49f = 0;
var identifiedPOS_50m = 0;
var identifiedPOS_50f = 0;
var identifiedPOS_STm = 0;
var identifiedPOS_STf = 0;
var identifiedPOS_GT = 0;

if(document.getElementById("identifiedPOS_1m").value!=""){ identifiedPOS_1m = parseInt(document.getElementById("identifiedPOS_1m").value);}
if(document.getElementById("identifiedPOS_1f").value!=""){ identifiedPOS_1f = parseInt(document.getElementById("identifiedPOS_1f").value);}
if(document.getElementById("identifiedPOS_4m").value!=""){ identifiedPOS_4m = parseInt(document.getElementById("identifiedPOS_4m").value);}
if(document.getElementById("identifiedPOS_4f").value!=""){ identifiedPOS_4f = parseInt(document.getElementById("identifiedPOS_4f").value);}
if(document.getElementById("identifiedPOS_9m").value!=""){ identifiedPOS_9m = parseInt(document.getElementById("identifiedPOS_9m").value);}
if(document.getElementById("identifiedPOS_9f").value!=""){ identifiedPOS_9f = parseInt(document.getElementById("identifiedPOS_9f").value);}
if(document.getElementById("identifiedPOS_14m").value!=""){ identifiedPOS_14m = parseInt(document.getElementById("identifiedPOS_14m").value);}
if(document.getElementById("identifiedPOS_14f").value!=""){ identifiedPOS_14f = parseInt(document.getElementById("identifiedPOS_14f").value);}
if(document.getElementById("identifiedPOS_19m").value!=""){ identifiedPOS_19m = parseInt(document.getElementById("identifiedPOS_19m").value);}
if(document.getElementById("identifiedPOS_19f").value!=""){ identifiedPOS_19f = parseInt(document.getElementById("identifiedPOS_19f").value);}
if(document.getElementById("identifiedPOS_24m").value!=""){ identifiedPOS_24m = parseInt(document.getElementById("identifiedPOS_24m").value);}
if(document.getElementById("identifiedPOS_24f").value!=""){ identifiedPOS_24f = parseInt(document.getElementById("identifiedPOS_24f").value);}
if(document.getElementById("identifiedPOS_29m").value!=""){ identifiedPOS_29m = parseInt(document.getElementById("identifiedPOS_29m").value);}
if(document.getElementById("identifiedPOS_29f").value!=""){ identifiedPOS_29f = parseInt(document.getElementById("identifiedPOS_29f").value);}
if(document.getElementById("identifiedPOS_34m").value!=""){ identifiedPOS_34m = parseInt(document.getElementById("identifiedPOS_34m").value);}
if(document.getElementById("identifiedPOS_34f").value!=""){ identifiedPOS_34f = parseInt(document.getElementById("identifiedPOS_34f").value);}
if(document.getElementById("identifiedPOS_39m").value!=""){ identifiedPOS_39m = parseInt(document.getElementById("identifiedPOS_39m").value);}
if(document.getElementById("identifiedPOS_39f").value!=""){ identifiedPOS_39f = parseInt(document.getElementById("identifiedPOS_39f").value);}
if(document.getElementById("identifiedPOS_49m").value!=""){ identifiedPOS_49m = parseInt(document.getElementById("identifiedPOS_49m").value);}
if(document.getElementById("identifiedPOS_49f").value!=""){ identifiedPOS_49f = parseInt(document.getElementById("identifiedPOS_49f").value);}
if(document.getElementById("identifiedPOS_50m").value!=""){ identifiedPOS_50m = parseInt(document.getElementById("identifiedPOS_50m").value);}
if(document.getElementById("identifiedPOS_50f").value!=""){ identifiedPOS_50f = parseInt(document.getElementById("identifiedPOS_50f").value);}

identifiedPOS_STm = identifiedPOS_1m+identifiedPOS_4m+identifiedPOS_9m+identifiedPOS_14m+identifiedPOS_19m+identifiedPOS_24m+identifiedPOS_29m+identifiedPOS_34m+identifiedPOS_39m+identifiedPOS_49m+identifiedPOS_50m;
identifiedPOS_STf = identifiedPOS_1f+identifiedPOS_4f+identifiedPOS_9f+identifiedPOS_14f+identifiedPOS_19f+identifiedPOS_24f+identifiedPOS_29f+identifiedPOS_34f+identifiedPOS_39f+identifiedPOS_49f+identifiedPOS_50f;
document.getElementById("identifiedPOS_STm").value = identifiedPOS_STm;
document.getElementById("identifiedPOS_STf").value = identifiedPOS_STf;

identifiedPOS_GT = identifiedPOS_STm+identifiedPOS_STf;
document.getElementById("identifiedPOS_GT").value = identifiedPOS_GT;  
}
function contacts_startedART(){
var contactStartedART_1m = 0;
var contactStartedART_1f = 0;
var contactStartedART_4m = 0;
var contactStartedART_4f = 0;
var contactStartedART_9m = 0;
var contactStartedART_9f = 0;
var contactStartedART_14m = 0;
var contactStartedART_14f = 0;
var contactStartedART_19m = 0;
var contactStartedART_19f = 0;
var contactStartedART_24m = 0;
var contactStartedART_24f = 0;
var contactStartedART_29m = 0;
var contactStartedART_29f = 0;
var contactStartedART_34m = 0;
var contactStartedART_34f = 0;
var contactStartedART_39m = 0;
var contactStartedART_39f = 0;
var contactStartedART_49m = 0;
var contactStartedART_49f = 0;
var contactStartedART_50m = 0;
var contactStartedART_50f = 0;
var contactStartedART_STm = 0;
var contactStartedART_STf = 0;
var contactStartedART_GT = 0;

if(document.getElementById("contactStartedART_1m").value!=""){ contactStartedART_1m = parseInt(document.getElementById("contactStartedART_1m").value);}
if(document.getElementById("contactStartedART_1f").value!=""){ contactStartedART_1f = parseInt(document.getElementById("contactStartedART_1f").value);}
if(document.getElementById("contactStartedART_4m").value!=""){ contactStartedART_4m = parseInt(document.getElementById("contactStartedART_4m").value);}
if(document.getElementById("contactStartedART_4f").value!=""){ contactStartedART_4f = parseInt(document.getElementById("contactStartedART_4f").value);}
if(document.getElementById("contactStartedART_9m").value!=""){ contactStartedART_9m = parseInt(document.getElementById("contactStartedART_9m").value);}
if(document.getElementById("contactStartedART_9f").value!=""){ contactStartedART_9f = parseInt(document.getElementById("contactStartedART_9f").value);}
if(document.getElementById("contactStartedART_14m").value!=""){ contactStartedART_14m = parseInt(document.getElementById("contactStartedART_14m").value);}
if(document.getElementById("contactStartedART_14f").value!=""){ contactStartedART_14f = parseInt(document.getElementById("contactStartedART_14f").value);}
if(document.getElementById("contactStartedART_19m").value!=""){ contactStartedART_19m = parseInt(document.getElementById("contactStartedART_19m").value);}
if(document.getElementById("contactStartedART_19f").value!=""){ contactStartedART_19f = parseInt(document.getElementById("contactStartedART_19f").value);}
if(document.getElementById("contactStartedART_24m").value!=""){ contactStartedART_24m = parseInt(document.getElementById("contactStartedART_24m").value);}
if(document.getElementById("contactStartedART_24f").value!=""){ contactStartedART_24f = parseInt(document.getElementById("contactStartedART_24f").value);}
if(document.getElementById("contactStartedART_29m").value!=""){ contactStartedART_29m = parseInt(document.getElementById("contactStartedART_29m").value);}
if(document.getElementById("contactStartedART_29f").value!=""){ contactStartedART_29f = parseInt(document.getElementById("contactStartedART_29f").value);}
if(document.getElementById("contactStartedART_34m").value!=""){ contactStartedART_34m = parseInt(document.getElementById("contactStartedART_34m").value);}
if(document.getElementById("contactStartedART_34f").value!=""){ contactStartedART_34f = parseInt(document.getElementById("contactStartedART_34f").value);}
if(document.getElementById("contactStartedART_39m").value!=""){ contactStartedART_39m = parseInt(document.getElementById("contactStartedART_39m").value);}
if(document.getElementById("contactStartedART_39f").value!=""){ contactStartedART_39f = parseInt(document.getElementById("contactStartedART_39f").value);}
if(document.getElementById("contactStartedART_49m").value!=""){ contactStartedART_49m = parseInt(document.getElementById("contactStartedART_49m").value);}
if(document.getElementById("contactStartedART_49f").value!=""){ contactStartedART_49f = parseInt(document.getElementById("contactStartedART_49f").value);}
if(document.getElementById("contactStartedART_50m").value!=""){ contactStartedART_50m = parseInt(document.getElementById("contactStartedART_50m").value);}
if(document.getElementById("contactStartedART_50f").value!=""){ contactStartedART_50f = parseInt(document.getElementById("contactStartedART_50f").value);}

contactStartedART_STm = contactStartedART_1m+contactStartedART_4m+contactStartedART_9m+contactStartedART_14m+contactStartedART_19m+contactStartedART_24m+contactStartedART_29m+contactStartedART_34m+contactStartedART_39m+contactStartedART_49m+contactStartedART_50m;
contactStartedART_STf = contactStartedART_1f+contactStartedART_4f+contactStartedART_9f+contactStartedART_14f+contactStartedART_19f+contactStartedART_24f+contactStartedART_29f+contactStartedART_34f+contactStartedART_39f+contactStartedART_49f+contactStartedART_50f;
document.getElementById("contactStartedART_STm").value = contactStartedART_STm;
document.getElementById("contactStartedART_STf").value = contactStartedART_STf;

contactStartedART_GT = contactStartedART_STm+contactStartedART_STf;
document.getElementById("contactStartedART_GT").value = contactStartedART_GT; 
   
}