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
 var _emrversion=["17.3.3","17.3.2","17.3.1","17.2.0"];
 var _adtversion=["4.0.0","3.4.2","3.4.1","3.4.0"];

 
 var mns=[{id:'01',val:'January'},{id:'02',val:'February'},{id:'03',val:'March'},{id:'04',val:'April'},{id:'05',val:'May'},{id:'06',val:'June'},{id:'07',val:'July'},{id:'08',val:'August'},{id:'09',val:'September'},{id:'10',val:'October'},{id:'11',val:'November'},{id:'12',val:'December'}];
   
  $scope.emrstatus=_emrstatus;   
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

       



