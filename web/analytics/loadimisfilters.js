/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function loadmonths(){
    
    var dt=new Date();
    
    
    var curmonth=dt.getMonth()+1;
   
       
        var curyr=dt.getYear()+1900;
     
        console.log("mwezi wa "+curmonth+"  mwaka wa "+curyr);
        
      var yr=document.getElementById("year").value;
//      alert(yr);
              $.ajax({
url:'loadLastMonth?year='+yr,
type:'post',
dataType:'html',
success:function (data){
    $("#month").html(data.replace("<option value=''>Select Month </option>",""));
    var select = document.getElementById('month');
                    select.size = select.length;
    
}
});  
     }
     
     
    function loadcounty(){
        
        
        $.ajax({
            url:'loadCounty',
            type:'post',
            dataType:'html',
            success:function (data){
                $(".county").html(data);
                 $('.county').select2();  
               
                //loadfacils();
              //  App.init();   
            }
            
            
        });
        
    }
    
   
    
       function loadsubcounty(){
           
           
           
        
        var mdt=$(".mdt").val();
        $.ajax({
            url:'loadSubcountymdt?mdt='+mdt,
            type:'post',
            dataType:'html',
            success:function (data)
            {
                $(".subcounty").html(data.replace("<option value=''>Select sub-county</option>",""));
                var select = document.getElementById('subcounty');
                    //select.size = select.length;
                 $('.subcounty').select2();  
              //  App.init();   
            }
            
            
        });
      return true;  
    }
    
    loadsubcounty();
    
    
    
     function loadmdt(){
           
           
           
        
        var county=$(".county").val();
        $.ajax({
            url:'loadMdt?county='+county,
            type:'post',
            dataType:'html',
            success:function (data)
            {
                $(".mdt").html(data.replace("<option value=''>Select Mdt</option>",""));
//                var select = document.getElementById('mdt');
                    //select.size = select.length;
                 $('#mdt').select2();  
              //  App.init();   
            }
            
            
        });
      return true;  
    }
    
     $('#mdt').select2();  
    
     
    
    
    
       function loadfacils()
{
      var subcounty=$(".subcounty").val();
      
$.ajax({
url:'loadMultipleFacilities?subcounty='+subcounty,
type:'post',
dataType:'html',
success:function (data){
       $(".facility").html(data);
         if($(".facility").val()!==''){
      updatefacilsession();
      
     
      }  
      $('.facility').select2();  
         // $("#facility").chosen();
       
       
}


}); 
}
    loadfacils();
 
    
    
       function loadfacil2( far)
{
      var subcounty=$(".subcounty").val();
      
$.ajax({
url:'loadMultipleFacilities?subcounty='+subcounty,
type:'post',
dataType:'html',
success:function (data){
       $(".facility").html(data);
         if($(".facility").val()!==''){
      updatefacilsession();
      
      
     
      }  
      $(".facility").val(far);
    $(".facility").select2(); 
         // $("#facility").chosen();
       
       
}


}); 
}
    
 
 


function getPeriod(){
       
 
       
       
              $.ajax({
                         url:'loadyearmonth',                            
                    type:'post',  
                    dataType: 'json',  
                    success: function(data) {                        
                       
        var dat=data.periods;
        
      
        var std="";
        var end="";
                        
                        for(var a=0;a<dat.length;a++)
                        {                           
                     
                          std+="<option data-startdate='"+dat[a].year+"-"+dat[a].monthid+"-01' value='"+dat[a].id+"'>"+dat[a].year+" "+dat[a].month+"</option>";   
                          end+="<option data-enddate='"+dat[a].year+"-"+dat[a].monthid+"-"+dat[a].enddate+"' value='"+dat[a].id+"'>"+dat[a].year+" "+dat[a].month+"</option>";   
                        }
                       
                   $(".startdate").html(std);
                   $(".enddate").html(end);
                   $(document).ready(function() {
                    $('.startdate').select2(); 
                    $('.enddate').select2(); 
                     updtimis();
             
                                 } ); 
                        
                        
                    }});
   
   }
   

getPeriod();



function emrimisdataSummaryOverall()
{ 
    
    //once the edit form is clicked, the assumption is that the add section will be loaded
    //$('#home-tab').click();
    //$('#home-tab').html("Edit Projects");
      $("#ovc_enrolled_perc_rd").html('<div class="d-flex justify-content-center"><div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div></div>');
      $("#otz_enrolled_perc_rd").html('<div class="d-flex justify-content-center"><div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div></div>');
   
//    console.log("_"+fc+"vs"+dt);

    var fc="";
    var cnt="";
    var sct="";
    var rgn="";
    var sd="";
    var ed="";
    
    var full_sd="";
    var full_ed="";
    
    if($(".facility").val()!==null){fc=$(".facility").val();}
    if($(".county").val()!==null){cnt=$(".county").val();}
    if($(".subcounty").val()!==null){sct=$(".subcounty").val();}
    if($(".mdt").val()!==null){rgn=$(".mdt").val();}
    
    if($(".startdate").val()!==null){sd=$(".startdate").val();  full_sd=$(".startdate option:selected").data("startdate"); } 
    if($(".enddate").val()!==null){ed=$(".enddate").val();  full_ed=$(".enddate option:selected").data("enddate");} 
 
            
           
            
            //now load the data
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',  
                    data:
                    {
                        act:'getEmrCascades',
                        fc:fc,
                        cnt:cnt,
                        sct:sct,
                        mdt:rgn,
                        sd:sd,
                        ed:ed,
                        full_sd:full_sd,
                        full_ed:full_ed,                   
                         groupbyorgunit:'\"UTJ\" as OrganizationUnit,',
                        groupby:' '
                    },
                    dataType: 'json',  
                    success: function(data) 
                    {
                           console.log("data ni:"+data);
                  
//                      if I want to display ovc list only   
                      var rdlitems=['ovc_enrolled_perc','otz_enrolled_perc'];  
                        
                        
                        console.log(data);
                        
                        const keys = Object.keys(data[0]);  
                        console.log(keys);
for (const key in data[0]) 
{  
    
    
      const radialdt=[];
      const radiallabel=[];
    
    
    var ky=key;
    var vl=data[0][key];
    
    
    vl=vl.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    
    if(rdlitems.includes(ky)){
        
     
     var percent_numeric=vl.replace('%','');
     radialdt.push(percent_numeric);
     radiallabel.push(ky.replace('_',' ').replace('_perc',''));
     
     
     loadRadialChart(ky+"_rd",percent_numeric,radialdt,radiallabel);
     
 }
   console.log("The length of Radial Length is :"+radialdt.length) 
    //don't include 
    if($("."+ky)!==null){
     $("."+ky).html(vl);
 }
 
  if($("."+ky+"_pb")!==null){
     $("."+ky+"_pb").css("width",vl);
 }
  
}
                  

                    }  
//});
                        
                  
//                         $('.dates').datepicker({
//                             todayHighlight: true, daysOfWeekDisabled: "0,6",clearBtn: true, autoclose: true,format: "yyyy-mm-dd",
//     });
     
   
                        
                        
                    });    
             
    
    
}





//This function build all the tables that has data by region.

//it makes use of one query to load all data into a table
function emrimisdataSummarybyMdt()
{ 
    
  

    var fc="";
    var cnt="";
    var sct="";
    var rgn="";
    var sd="";
    var ed="";
    
    var full_sd="";
    var full_ed="";
    
    if($(".facility").val()!==null){fc=$(".facility").val();}
    if($(".county").val()!==null){cnt=$(".county").val();}
    if($(".subcounty").val()!==null){sct=$(".subcounty").val();}
    if($(".mdt").val()!==null){rgn=$(".mdt").val();}
    
    if($(".startdate").val()!==null){sd=$(".startdate").val();  full_sd=$(".startdate option:selected").data("startdate"); } 
    if($(".enddate").val()!==null){ed=$(".enddate").val();  full_ed=$(".enddate option:selected").data("enddate");} 
 
            
           
            
            //now load the data. Note that The data will be grouped into organization units
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',  
                    data:
                    {
                        act:'getEmrCascadesReadable',
                        fc:fc,
                        cnt:cnt,
                        sct:sct,
                        mdt:rgn,
                        sd:sd,
                        ed:ed,
                        full_sd:full_sd,
                        full_ed:full_ed,
                        groupbyorgunit:' `mdt` as Region,',
                        groupby:' group by mdt '
                    },
                    dataType: 'JSON',  
                    success: function(data) 
                    {
                        
                         const d=JSON.parse("["+data+"]");
                        
//                        data=d.toString().replace(/\\"/g, '"');
                        data=JSON.parse("["+data+"]");
//                        data=JSON.stringify(data);
                        
                          console.log("data 1 ni:"+d);
                          console.log("data 2 ni:"+data);
                  
//                      if I want to display certain Tables, I will build tables for each Cascade 
// Note that below arrays contains the names of the table colums as written on the stored procedure for ease of  management. Any renaming done to the mysql column should also be done on the table below
  
                        
   var ncdtableitems=['ncd_summary','Region','TX_CURR','Screened for Hypertension','Screened for Hypertension(%)','Hypertension Cases','Hypertension Cases (%)','Hypertension Controlled','Hypertension Controlled (%)','Diabetic Cases','Diabetis Cases Controlled','Diabetis Cases Controlled (%)'];  
   var ipttableitems=['ipt_summary','Region','TX_CURR','Eligible for IPT/TPT','Started TPT/IPT','Started TPT/IPT (%)','Has TPT/IPT Outcome','Completed IPT/TPT','Completed IPT/TPT (%)','TX New Eligible for TPT/TPT ','TX New Started on IPT/TPT','TX New Started on IPT/TPT (%)'];  
   var mhtableitems=['mh_summary','Region','TX_CURR','Screened for Mental Health 1yr','Screened for Mental Health 1 yr %','Mental Health Cases 1 yr','Anxiety Screening 1 yr','Anxiety Screening 1 yr (%)','Anxiety Cases 1 yr','Anxiety Cases 1 yr(%)'];  
   var fpttableitems=['fpt_summary','Region','TX_CURR','Contacts Listing Done','Contacts Listing Done (%)','Listed Contacts','HIV -ve Contacts','HIV +ve','HIV Unknown'];  
   
   var avl_tableitems=['avl_summary','Region','TX_CURR','Eligible For VL','VL Done','VL Done (%)','VL Sup','VL Sup(%)','Eligible Not Done VL'];  
   var cvl_tableitems=['cvl_summary','Region','CALHIV Eligible For VL','CALHIV VL Done','CALHIV VL Done (%)','CALHIV VL Sup','CALHIV VL Sup (%) ','CALHIV Eligible Not Done VL'];  
   var pvl_tableitems=['pvl_summary','Region','TX_CURR PMTCT','PMTCT Eligible for VL','PMTCT VL Done','PMTCT VL Done (%)','PMTCT VL Sup','PMTCT VL Sup (%)','PMTCT Eligible Not Done VL'];  
   var txnew_tableitems=['txnew_summary','Region','TX_NEW','TX_New Done for CD4','TX_New Done for CD4 (%)','TX_New NUPI Verified','Tx_New NUPI Verified(%)'];  
   var cx_tableitems=['cxca_summary','Region','Women of Reproductive Age(WRA)','WRA Screened','WRA Screened(%)','WRA Positive','WRA Positive (%)','WRA Treated','WRA Treated (%)'];  
                        
                        
                        var tblheader=""; //only include the table columns. The row is alreday defined
                        var tblrows="";
                       
                        var ipttblheader=""; //only include the table columns. The row is alreday defined
                        var ipttblrows="";
                        
                        var mhtblheader=""; //only include the table columns. The row is alreday defined
                        var mhtblrows="";
                        
                        var fpttblheader=""; //only include the table columns. The row is alreday defined
                        var fpttblrows="";
                        
                        var avl_tblheader=""; //only include the table columns. The row is alreday defined
                        var avl_tblrows="";
                        
                        var cvl_tblheader=""; //only include the table columns. The row is alreday defined
                        var cvl_tblrows="";
                        
                        var pvl_tblheader=""; //only include the table columns. The row is alreday defined
                        var pvl_tblrows="";
                        
                        
                        var txnew_tblheader=""; //only include the table columns. The row is alreday defined
                        var txnew_tblrows="";
                        
                        
                        var cx_tblheader=""; //only include the table columns. The row is alreday defined
                        var cx_tblrows="";
                        
                        
//                        console.log(data);
                        
                        
                        for( var rw=0;rw<data.length;rw++){
                            
                       var keys = Object.keys(data[rw]);  
                        console.log("Headers by order:"+keys);     
              
                            
                            
                            //
                            
                       var rowstart="<tr>";

if(rw===parseInt((data.length))-1){
    
    rowstart+="<tfoot class='table-info'>";
}     
                            
         //build the table row start 
         tblrows+=rowstart;
         ipttblrows+=rowstart;
         mhtblrows+=rowstart;
         fpttblrows+=rowstart;
         avl_tblrows+=rowstart;
         cvl_tblrows+=rowstart;
         pvl_tblrows+=rowstart;
         txnew_tblrows+=rowstart;
         cx_tblrows+=rowstart;
                            
for (var key in data[rw]) 
{  
   
    
    
    var ky=key;
    var vl=data[rw][key];
   
    
   
    
    vl=vl.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    
    vl=conditionalformat(vl);
     
    //___Ncds_tables_now build table for specific table elements based on our identified data elements
                    if (ncdtableitems.includes(ky))
                    {
      //build the table header if this is the first row
                        if (rw === 0) {
                            tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }


   //IPT Table now build table for specific table elements based on our identified data elements
                    if (ipttableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            ipttblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        ipttblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }

 //MH Table now build table for specific table elements based on our identified data elements
                    if (mhtableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            mhtblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        mhtblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }

 //FPT Table now build table for specific table elements based on our identified data elements
                    if (fpttableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            fpttblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        fpttblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }

 //All vl Table now build table for specific table elements based on our identified data elements
                    if (avl_tableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            avl_tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        avl_tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }

 //CALHIV vl Table now build table for specific table elements based on our identified data elements
                    if (cvl_tableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            cvl_tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        cvl_tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }

 //PMTCT vl Table now build table for specific table elements based on our identified data elements
                    if (pvl_tableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            pvl_tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        pvl_tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }
                    
 //TXNew Table now build table for specific table elements based on our identified data elements
                    if (txnew_tableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            txnew_tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        txnew_tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }
 //CXCA Table now build table for specific table elements based on our identified data elements
                    if (cx_tableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            cx_tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        cx_tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }


  
}
//end the row outside the data elements for loop

var rowend="</tr>";

if(rw===parseInt((data.length))-1){
    
    rowend+="</tfoot>";
}


tblrows+=rowend;
ipttblrows+=rowend;
mhtblrows+=rowend;
fpttblrows+=rowend;
avl_tblrows+=rowend;
cvl_tblrows+=rowend;
pvl_tblrows+=rowend;
txnew_tblrows+=rowend;
cx_tblrows+=rowend;
                  
                  
                  if(rw===parseInt((data.length))-1){
                      console.log("This is the end of orgunit rows");
   //note that the first item on the array (refer to argument below) contains the name of the DOM element that will host our table
                      htmltablebuilder( ncdtableitems[0],tblheader,tblrows );
                      htmltablebuilder( ipttableitems[0],ipttblheader,ipttblrows );
                      htmltablebuilder( mhtableitems[0],mhtblheader,mhtblrows );
                      htmltablebuilder( fpttableitems[0],fpttblheader,fpttblrows );
                      htmltablebuilder( avl_tableitems[0],avl_tblheader,avl_tblrows );
                      htmltablebuilder( cvl_tableitems[0],cvl_tblheader,cvl_tblrows );
                      htmltablebuilder( pvl_tableitems[0],pvl_tblheader,pvl_tblrows );
                      htmltablebuilder( txnew_tableitems[0],txnew_tblheader,txnew_tblrows );
                      htmltablebuilder( cx_tableitems[0],cx_tblheader,cx_tblrows );
                      
                  }
                  
                  }//end of outer loop that increments rows
                    }  
//});
                        
                  
//                         $('.dates').datepicker({
//                             todayHighlight: true, daysOfWeekDisabled: "0,6",clearBtn: true, autoclose: true,format: "yyyy-mm-dd",
//     });
     
   
                        
                        
                    });    
             
    
    
}

//This function build all the tables that has data by region.

//it makes use of one query to load all data into a table
function emrRdqabyMdt()
{ 
    
  

    var fc="";
    var cnt="";
    var sct="";
    var rgn="";
    var sd="";
    var ed="";
    
    var full_sd="";
    var full_ed="";
    
    if($(".facility").val()!==null){fc=$(".facility").val();}
    if($(".county").val()!==null){cnt=$(".county").val();}
    if($(".subcounty").val()!==null){sct=$(".subcounty").val();}
    if($(".mdt").val()!==null){rgn=$(".mdt").val();}
    
    if($(".startdate").val()!==null){sd=$(".startdate").val();  full_sd=$(".startdate option:selected").data("startdate"); } 
    if($(".enddate").val()!==null){ed=$(".enddate").val();  full_ed=$(".enddate option:selected").data("enddate");} 
 
            
           
            
            //now load the data. Note that The data will be grouped into organization units
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',  
                    data:
                    {
                        act:'getEmrRdqaData',
                        fc:fc,
                        cnt:cnt,
                        sct:sct,
                        mdt:rgn,
                        sd:sd,
                        ed:ed,
                        full_sd:full_sd,
                        full_ed:full_ed,
                        groupbyorgunit:' `mdt` as Region,',
                        groupby:' group by mdt '
                    },
                    dataType: 'JSON',  
                    success: function(data) 
                    {
                        
                         const d=JSON.parse("["+data+"]");
                        
//                        data=d.toString().replace(/\\"/g, '"');
                        data=JSON.parse("["+data+"]");
//                        data=JSON.stringify(data);
                        
                     
                  
//                      if I want to display certain Tables, I will build tables for each Cascade 
// Note that below arrays contains the names of the table colums as written on the stored procedure for ease of  management. Any renaming done to the mysql column should also be done on the table below
  
                        
   var ncdtableitems=['rdqa_summary','Region','Data Accuracy','Completeness','Data Concordance','RDQA Score'];  
   var ipttableitems=['rdqadetails_summary','Region','Missing Entry Point','Missing Locator Information','Missing Date Confirmed HIV +ve','Missing Most Recent WHO Stage','Missing Most Recent Regimen Line','Has Triage Captured But No TCA Date','No Recent VL','Missing Baseline CD4','Missing Baseline WHO','Started on Prep without Follow UP Prep','Transfer Out Patients Missing TO Verification','Confirmed HIV +ve Before Date of Birth','Ever on TPT in Grencard But Not Enrolled in TPT Module','First VL before ART Start Date'];  
                        
                        
                        var tblheader=""; //only include the table columns. The row is alreday defined
                        var tblrows="";
                       
                        var ipttblheader=""; //only include the table columns. The row is alreday defined
                        var ipttblrows="";
                        
                        
//                        console.log(data);
                        
                        
                        for( var rw=0;rw<data.length;rw++){
                            
                       var keys = Object.keys(data[rw]);  
                        console.log("Headers by order:"+keys);     
                            
                            
                              var rowstart="<tr>";

if(rw===parseInt((data.length))-1){
    
    rowstart+="<tfoot class='table-info'>";
}     
                            
         //build the table row start 
         tblrows+=rowstart;
         ipttblrows+=rowstart;
                            
for (var key in data[rw]) 
{  
   
    
    
    var ky=key;
    var vl=data[rw][key];
   
    
   
    
    vl=vl.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    
    vl=conditionalformat(vl);
     
    //___Ncds_tables_now build table for specific table elements based on our identified data elements
                    if (ncdtableitems.includes(ky))
                    {
      //build the table header if this is the first row
                        if (rw === 0) {
                            tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }


   //IPT Table now build table for specific table elements based on our identified data elements
                    if (ipttableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            ipttblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        ipttblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }



  
}
//end the row outside the data elements for loop
var rowend="</tr>";

if(rw===parseInt((data.length))-1){
    
    rowend+="</tfoot>";
}


tblrows+=rowend;
ipttblrows+=rowend;

                  
                  
                  if(rw===parseInt((data.length))-1){
//                      console.log("This is the end of orgunit rows");
   //note that the first item on the array (refer to argument below) contains the name of the DOM element that will host our table
                      htmltablebuilder( ncdtableitems[0],tblheader,tblrows );
                      htmltablebuilder( ipttableitems[0],ipttblheader,ipttblrows );
                    
                      
                  }
                  
                  }//end of outer loop that increments rows
                    }  
//});
                        
                  
//                         $('.dates').datepicker({
//                             todayHighlight: true, daysOfWeekDisabled: "0,6",clearBtn: true, autoclose: true,format: "yyyy-mm-dd",
//     });
     
   
                        
                        
                    });    
             
    
    
}





function htmltablebuilder( hostelementname,tableheaders,tablebody )
{
    
    var tbl = "<thead>"
            + " <tr> "
            + tableheaders
           
            + "</tr>"
            + "</thead>"
            + "<tbody>"
            
            + tablebody
            

            + "</tbody>";
             
    
    
    console.log(" Table for element "+hostelementname+" is: "+tbl);
    
    $("."+hostelementname).html(tbl);
    
}


//have a function that adds percentage values and does conditional formating 

function conditionalformat( val ){
    
   var conditioned_value="";
    
    var numericval=val.replace('%','');
   
   if(val.indexOf("%")>=0 && $.isNumeric(val.replace('%',''))){
   
         if(numericval>=90)                   { conditioned_value='<b>'+val+'</b> <div class="progress"><div class="progress-bar bg-success" style="width: '+numericval+'%"></div></div>';  }
    else if(numericval>=85  && numericval<90 ){ conditioned_value='<b>'+val+'</b> <div class="progress"><div class="progress-bar bg-success" style="width: '+numericval+'%"></div></div>';  }
    else if(numericval>=55 && numericval<85 ) { conditioned_value='<b>'+val+'</b> <div class="progress"><div class="progress-bar bg-warning" style="width: '+numericval+'%"></div></div>';  }
    else if(numericval>=35 && numericval<55 ) { conditioned_value='<b>'+val+'</b> <div class="progress"><div class="progress-bar bg-infor" style="width: '+numericval+'%"></div></div>';  }
    else                                      { conditioned_value='<b>'+val+'</b> <div class="progress"><div class="progress-bar bg-danger" style="width: '+numericval+'%"></div></div>';  }
       
   }
   else {
       
   conditioned_value= '<b>'+val+'</b>';    
   }
    
    
    
    
    return conditioned_value;
}

  
function updtimis()
{
 emrimisdataSummaryOverall();  
 emrimisdataSummarybyMdt();
 emrRdqabyMdt();
}

  