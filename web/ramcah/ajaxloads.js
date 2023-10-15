/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


  




                                   $('.dates').datepicker({
                                       todayHighlight: true, clearBtn: true, autoclose: true, format: "yyyy-mm-dd"
                                   });



                                  


                              
 function getFacilitiesJson(){
       
   
       var ym=$("#period").val();
       var ot=$("#dataset").val();
       var act="";


       if(ot==="facility"){act="getNakuruSites"; $("#ou").html("<font color='red'>*</font> <b>Facility Name</b>");}
       else if(ot==="ward"){act="getNakuruWards"; $("#ou").html("<font color='red'>*</font> <b>Ward Name</b>");}
       else if(ot==="subcounty"){act="getNakuruDistricts"; $("#ou").html("<font color='red'>*</font> <b>Sub-County Name</b>");}
      if(ot!==''){
       loadSelectOptionsFromDb('facility',act);
              $('#facility').select2(); 
          }    
   
   }


getFacilitiesJson();
 

function loadSelectOptionsFromDb(elementtoappend,act){
      
  //now load the data
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',
                    dataType: 'html',  
                    data:{act:act,
                        table_docker:elementtoappend
			},
                    success: function(data) 
                    {
                        
            $(document).ready(function(){
                        
                        var dt = data;
            
//<label class='btn btn-success'>Edit</label>          
$("#"+elementtoappend).html(""+dt);
              

        });
                        
                    }});    
         
           
            
          
    
}
    

   
   
   
 function getPeriod(){
       
   var sec=$("#section").val();
       
       
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
                        
                   $("#period").html(o);
                   $(document).ready(function() {
                   // $('#period').select2(); 
             
                                 } ); 
                        
                        
                    }});
   
   }
   

getPeriod();


                




                                   function save_data(dbname,sci) {



//___indicators to pull___

                                       var facil = "";
                                     
                                       var ym = "";
                           facil = $("#facility").val();
                                     
                           ym = $("#period").val();
                                     
              if (facil === '' || facil === 'Select facility') {

                                           alert("enter Facility");
              } 
                                       
      
        else if (ym === '') {

                                           alert("enter reporting period");

                                       } 
                                       
       // else if (runvalidation() === false) { } 
                                       else {
                                           
                                           var ou=$("dataset").val();
                                           
   $.ajax({
                                               url: 'getIndicators?scid='+sci,
                                               type: 'post',
                                               dataType: 'json',
                                               success: function (data) {

                                                   for (a = 0; a < data.length; a++) {
                                                       var isend = false;

                                                       var indicatorid = data[a].id;
                                                       var value = $("#" + indicatorid).val();
                                                      
                                                       var identifier = facil + "_" +ym + "_" + indicatorid;


                                                       //save the data
                       
                                                       var saveddata = {
                                                           id: identifier,
                                                           yearmonth: ym,
                                                           facility_id: facil,                                                          
                                                           indicator_id: indicatorid,
                                                           value: value,
                                                           week:new Date().getWeekNumber(),
                                                           userid:'909090',
                                                           orgunit:ou

                                                       };




                                                       if (a === parseInt(data.length) - 1) {
                                                           isend = true;
                                                         
                                                       }

                                                       exportData(saveddata, isend, dbname, sci);

                                                   }

                                               }
                                           });



                                       }






                                   }


                                   function exportData(data, isend, tbl,secid) {


                                       $.ajax({
                                           url: 'saveRamcah_sum_Data?tbl='+tbl,
                                           type: 'post',
                                           dataType: 'html',
                                           data: data,
                                           success: function (dat) {
                                               if (isend) {

                                                   console.log("Data saved Succesfully!");
                                                   $("#msg"+secid).html("<font color='green'><b>Data saved Succesfully!!!</b></font>");
                                              section_saved(secid);
                                         setTimeout(refreshujumbe,2000);
    
                                               }

                                           }
                                       });


                                   }

//call the function that displays the data

function refreshujumbe(eid){
    
    $(".feedback").html("");
    
}

//==================function to import data

// $('#exportbutton').on('click',function() {
                                   $("#exportbutton").prop("disabled", false);
                                   $(this).removeClass('btn-lg btn-default').addClass('btn btn-success');
//});



                                   function numbers(evt) {
                                       var charCode = (evt.which) ? evt.which : event.keyCode
                                       if (charCode > 31 && (charCode < 48 || charCode > 57))
                                           return false;
                                       return true;
                                   }

//Codes to prevent default form submission

                                   $("#userform").submit(function (e) {
                                       return false;
                                   });

                                   $("#weeklydataform").submit(function (e) {
                                       return true;
                                   });
                                   $("#exportdataform").submit(function (e) {
                                       return false;
                                   });

                                   $("#reportsform").submit(function (e) {
                                       return false;
                                   });

                                   $('input').css('border-color', '#337ab7');


//prevent numbers scrolling

                                   $('form').on('focus', 'input[type=number]', function (e) {
                                       $(this).on('mousewheel.disableScroll', function (e) {
                                           e.preventDefault();
                                       });
                                   });
                                   $('form').on('blur', 'input[type=number]', function (e) {
                                       $(this).off('mousewheel.disableScroll');
                                   });



//additional changes








                                   $('#dataentry').on('keydown', 'input, select, textarea', function (e) {
                                       var self = $(this)
                                               , form = self.parents('form:eq(0)')
                                               , focusable
                                               , next
                                               ;
                                       if (e.keyCode === 13) {
                                           focusable = form.find('input,a,select,button,textarea').filter(':visible');
                                           next = focusable.eq(focusable.index(this) + 1);
                                           if (next.length) {
                                               next.focus();
                                           } else {
                                               form.submit();
                                           }
                                           return false;
                                       }
                                   });

                                   function isdisplayindicators()
                                   {
                                       
                                     
            
       
                                       var ym = $("#period").val().trim();
                                      
                                       var fc = $("#facility").val().trim();
                                       var ds = $("#dataset").val().trim();
                                       var fg = $("#formgroup").val().trim();
//    console.log("_"+fc+"vs"+dt);
                                       if ( ds !== '' &&ym !== '' && fc !== 'Select facility' && fc !== '')
                                       {
                                           // display facility name
                                           $("#form1a_accordion").show();


                                           //now load the data
                                           $.ajax({
                                               url: 'getIndicators?ym=' + ym + "&fc=" + fc+ "&ds=" + ds+ "&fg=" + fg,
                                               type: 'post',
                                               dataType: 'html',
                                               success: function (data)
                                               {

                                                   $("#form1a_accordion").html(data);
                                               console.log(data);

                                                }});



                                       } else
                                       {
                                           console.log("Hide accordion");
                                           $("#form1a_accordion").hide();
                                           //        
                                       }


                                   }

Date.prototype.getWeekNumber = function(){
  var d = new Date(Date.UTC(this.getFullYear(), this.getMonth(), this.getDate()));
  var dayNum = d.getUTCDay() || 7;
  d.setUTCDate(d.getUTCDate() + 4 - dayNum);
  var yearStart = new Date(Date.UTC(d.getUTCFullYear(),0,1));
  return Math.ceil((((d - yearStart) / 86400000) + 1)/7)
};

function checkFormAction (){
    
  $('#reportingForm').attr('action', $("#report").val());  
    
}


       