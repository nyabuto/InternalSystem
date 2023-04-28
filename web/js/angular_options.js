/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

/* swap open/close side menu icons */


//Load the years drop down 

var d = new Date();
var curryear = d.getFullYear();


 var yearsarray=[];
 
var appl = angular.module("emrapp",[]);





appl.controller('emrcont', function($scope , $http) {  
    
     $http.get("getPeriod?per=yes").then(function (response) 
  {
  
    $scope.yearmonth = response.data ;
  });

  
 
 var _yesno=["Yes","No"];
 var _emrstatus=["POC","RDE","Stalled"];
 var _emrstatusr=["Point Of Care","Retrospective Data Entry","Not in Use"];
 var _emrversion=["18.5.2","18.5.1","18.5.0","18.4.2","18.4.1","18.3.0","18.2.0","18.1.2","18.1.1","18.1.0","18.0.1"];
 var _adtversion=["4.0.2","4.0.1","4.0.0","3.4.2","3.4.1","3.4.0"];

 
 var mns=[{id:'01',val:'January'},{id:'02',val:'February'},{id:'03',val:'March'},{id:'04',val:'April'},{id:'05',val:'May'},{id:'06',val:'June'},{id:'07',val:'July'},{id:'08',val:'August'},{id:'09',val:'September'},{id:'10',val:'October'},{id:'11',val:'November'},{id:'12',val:'December'}];
   var est=[{id:'POC',val:'Point Of Care/Paperless'},{id:'RDE',val:'Retrospective Data Entry/Hybrid'},{id:'Stalled',val:'Not in Use'}];
  $scope.emrstatus=est;   
 $scope.yesno=_yesno;
 $scope.months=mns;

 $scope.emrversion=_emrversion;
 $scope.adtversion=_adtversion;
 
 


 
});



appl.directive("requiredField", function() {
  return {
    template : `<font color='red'><b>*</b></font>`
  };
});


// Directive to show refresh, Data export button and Options for excel reports

       



