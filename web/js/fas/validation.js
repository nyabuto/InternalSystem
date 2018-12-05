/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//The assumption is that all the validation string will be a list of indicators separated by the expected validation string

//1=2 means for all the age disagregations, element 1 should be eqaul to element 2
//1>2 means for all the disagregations, element 1 should be greater than element 2
//use , (comma) to separate various validations rules

function loadValidation(sectionid){
    
    
    
}


function runvalidation(valids, message, iscritical, sectionid) 
{ 
      if(valids.indexOf("<=")>=0){   lessOrEqual(valids,message,iscritical,sectionid);  }
 else if(valids.indexOf(">=")>=0){   greaterOrEqual(valids,message,iscritical,sectionid);  }
 else if(valids.indexOf("=")>=0){   Equal(valids,message,iscritical,sectionid);  }
 else if(valids.indexOf("<")>=0){   less(valids,message,iscritical,sectionid);  }
 else if(valids.indexOf(">")>=0){   greater(valids,message,iscritical,sectionid);  }

}



//raise an alarm if indicator 1 is not less than indicator 2

function less(valids, message, iscritical, sectionid) {
    //1<2
    //eg 1<2 means for all the age disagregations, indicator 1 should be less than indicator 2


    $("#msg" + sectionid).html("");

    var val1, val2;

    var columns = ["m_uk", "f_uk", "m_1", "f_1", "m_4", "f_4", "m_9", "f_9", "m_14", "f_14", "m_19", "f_19", "m_24", "f_24", "m_29", "f_29", "m_34", "f_34", "m_39", "f_39", "m_44", "f_44", "m_49", "f_49", "m_50", "f_50"];



    //    
//    var indicator_id=document.getElementById("indic_pos_"+indic_pos).value;
//    alert(indicator_id);
    var num_elems = columns.length;
    for (var i = 0; i < num_elems; i++) {

        var valarray = valids.split("<");



        //    alert(columns[i]+"_"+indicator_id);
        val1 = multisum(valarray[0], columns[i]);
        val2 = multisum(valarray[1], columns[i]);
        //m_uk

        var agegender = columns[i].split("_");
        var sx = "";
        var ag = "";
        if (agegender[0] === 'm') {
            sx = "Male";
        } else if (agegender[0] === 'f') {
            sx = "Female";
        }

        if (agegender[1] === 'uk') {
            ag = "Unknown";
        } else {
            ag = agegender[1];
        }

        if (val1 > 0 || val2 > 0) {
            if (val1 >= val2) {
                if (iscritical === 'yes') {
                    alert("For age disaggregation " + ag + " years " + sx + " , " + message);

                    $("#msg" + sectionid).html("For age " + ag + " years " + sx + " , " + message);

                    redborder(columns[i], valarray[0]);
                    redborder(columns[i], valarray[1]);

                    break;
                    //end the loop so that the data entry person can make corrections

                } else {

                    $("#msg" + sectionid).append("For age " + ag + " years " + sx + " , " + message + "<br/>");



                }
            } else {

                blackborder(columns[i], valarray[0]);
                blackborder(columns[i], valarray[1]);


            }


        }


        // }


    }



}//end of less than 




function greater(valids, message, iscritical, sectionid) {
    //1>2
    //eg 1>2 means for all the age disagregations, indicator 1 should be greater than indicator 2


    $("#msg" + sectionid).html("");

    var val1, val2;

    var columns = ["m_uk", "f_uk", "m_1", "f_1", "m_4", "f_4", "m_9", "f_9", "m_14", "f_14", "m_19", "f_19", "m_24", "f_24", "m_29", "f_29", "m_34", "f_34", "m_39", "f_39", "m_44", "f_44", "m_49", "f_49", "m_50", "f_50"];



    //    
//    var indicator_id=document.getElementById("indic_pos_"+indic_pos).value;
//    alert(indicator_id);
    var num_elems = columns.length;
    for (var i = 0; i < num_elems; i++) {

        var valarray = valids.split(">");



        //    alert(columns[i]+"_"+indicator_id);
        val1 = multisum(valarray[0], columns[i]);
        val2 = multisum(valarray[1], columns[i]);
        //m_uk

        var agegender = columns[i].split("_");
        var sx = "";
        var ag = "";
        if (agegender[0] === 'm') {
            sx = "Male";
        } else if (agegender[0] === 'f') {
            sx = "Female";
        }

        if (agegender[1] === 'uk') {
            ag = "Unknown";
        } else {
            ag = agegender[1];
        }

        if (val1 > 0 || val2 > 0) {
            if (val1 <= val2) {
                if (iscritical === 'yes') {
                    alert("For age disaggregation " + ag + " years " + sx + " , " + message);

                    $("#msg" + sectionid).html("For age " + ag + " years " + sx + " , " + message);

                    redborder(columns[i], valarray[0]);
                    redborder(columns[i], valarray[1]);

                    break;
                    //end the loop so that the data entry person can make corrections

                } else {

                    $("#msg" + sectionid).append("For age " + ag + " years " + sx + " , " + message + "<br/>");



                }
            } else {

                blackborder(columns[i], valarray[0]);
                blackborder(columns[i], valarray[1]);


            }


        }


        // }


    }



}//end of greater than


function lessOrEqual(valids, message, iscritical, sectionid) {
    //1>2
    //eg 1>2 means for all the age disagregations, indicator 1 should be greater than indicator 2


    $("#msg" + sectionid).html("");

    var val1, val2;

    var columns = ["m_uk", "f_uk", "m_1", "f_1", "m_4", "f_4", "m_9", "f_9", "m_14", "f_14", "m_19", "f_19", "m_24", "f_24", "m_29", "f_29", "m_34", "f_34", "m_39", "f_39", "m_44", "f_44", "m_49", "f_49", "m_50", "f_50"];



    //    
//    var indicator_id=document.getElementById("indic_pos_"+indic_pos).value;
//    alert(indicator_id);
    var num_elems = columns.length;
    for (var i = 0; i < num_elems; i++) {

        var valarray = valids.split("<=");



        //    alert(columns[i]+"_"+indicator_id);
        val1 = multisum(valarray[0], columns[i]);
        val2 = multisum(valarray[1], columns[i]);
        //m_uk

        var agegender = columns[i].split("_");
        var sx = "";
        var ag = "";
        if (agegender[0] === 'm') {
            sx = "Male";
        } else if (agegender[0] === 'f') {
            sx = "Female";
        }

        if (agegender[1] === 'uk') {
            ag = "Unknown";
        } else {
            ag = agegender[1];
        }

        if (val1 > 0 || val2 > 0) {
            if (val1 > val2) {
                if (iscritical === 'yes') {
                    alert("For age disaggregation " + ag + " years " + sx + " , " + message);

                    $("#msg" + sectionid).html("For age " + ag + " years " + sx + " , " + message);

                    redborder(columns[i], valarray[0]);
                    redborder(columns[i], valarray[1]);

                    break;
                    //end the loop so that the data entry person can make corrections

                } else {

                    $("#msg" + sectionid).append("For age " + ag + " years " + sx + " , " + message + "<br/>");



                }
            } else {

                blackborder(columns[i], valarray[0]);
                blackborder(columns[i], valarray[1]);


            }


        }


        // }


    }



} //end of less or equal



function greaterOrEqual(valids, message, iscritical, sectionid) {
    //1>2
    //eg 1>2 means for all the age disagregations, indicator 1 should be greater than indicator 2


    $("#msg" + sectionid).html("");

    var val1, val2;

    var columns = ["m_uk", "f_uk", "m_1", "f_1", "m_4", "f_4", "m_9", "f_9", "m_14", "f_14", "m_19", "f_19", "m_24", "f_24", "m_29", "f_29", "m_34", "f_34", "m_39", "f_39", "m_44", "f_44", "m_49", "f_49", "m_50", "f_50"];



    //    
//    var indicator_id=document.getElementById("indic_pos_"+indic_pos).value;
//    alert(indicator_id);
    var num_elems = columns.length;
    for (var i = 0; i < num_elems; i++) {

        var valarray = valids.split(">=");



        //    alert(columns[i]+"_"+indicator_id);
        val1 = multisum(valarray[0], columns[i]);
        val2 = multisum(valarray[1], columns[i]);
        //m_uk

        var agegender = columns[i].split("_");
        var sx = "";
        var ag = "";
        if (agegender[0] === 'm') {
            sx = "Male";
        } else if (agegender[0] === 'f') {
            sx = "Female";
        }

        if (agegender[1] === 'uk') {
            ag = "Unknown";
        } else {
            ag = agegender[1];
        }

        if (val1 > 0 || val2 > 0) {
            if (val1 < val2) {
                if (iscritical === 'yes') {
                    alert("For age disaggregation " + ag + " years " + sx + " , " + message);

                    $("#msg" + sectionid).html("For age " + ag + " years " + sx + " , " + message);

                    redborder(columns[i], valarray[0]);
                    redborder(columns[i], valarray[1]);

                    break;
                    //end the loop so that the data entry person can make corrections

                } else {

                    $("#msg" + sectionid).append("For age " + ag + " years " + sx + " , " + message + "<br/>");



                }
            } else {

                blackborder(columns[i], valarray[0]);
                blackborder(columns[i], valarray[1]);


            }


        }


        // }


    }



} //end of greater or equal


function Equal(valids, message, iscritical, sectionid) {
    //1>2
    //eg 1>2 means for all the age disagregations, indicator 1 should be greater than indicator 2


    $("#msg" + sectionid).html("");

    var val1, val2;

    var columns = ["m_uk", "f_uk", "m_1", "f_1", "m_4", "f_4", "m_9", "f_9", "m_14", "f_14", "m_19", "f_19", "m_24", "f_24", "m_29", "f_29", "m_34", "f_34", "m_39", "f_39", "m_44", "f_44", "m_49", "f_49", "m_50", "f_50"];



    //    
//    var indicator_id=document.getElementById("indic_pos_"+indic_pos).value;
//    alert(indicator_id);
    var num_elems = columns.length;
    for (var i = 0; i < num_elems; i++) {

        var valarray = valids.split("=");



        //    alert(columns[i]+"_"+indicator_id);
        val1 = multisum(valarray[0], columns[i]);
        val2 = multisum(valarray[1], columns[i]);
        //m_uk

        var agegender = columns[i].split("_");
        var sx = "";
        var ag = "";
        if (agegender[0] === 'm') {
            sx = "Male";
        } else if (agegender[0] === 'f') {
            sx = "Female";
        }

        if (agegender[1] === 'uk') {
            ag = "Unknown";
        } else {
            ag = agegender[1];
        }

        if (val1 > 0 || val2 > 0) {
            if ((val1 < val2) || (val1 > val2)) {
                if (iscritical === 'yes') {
                    alert("For age disaggregation " + ag + " years " + sx + " , " + message);

                    $("#msg" + sectionid).html("For age " + ag + " years " + sx + " , " + message);


                    redborder(columns[i], valarray[0]);
                    redborder(columns[i], valarray[1]);
                    break;
                    //end the loop so that the data entry person can make corrections

                } else {

                    $("#msg" + sectionid).append("For age " + ag + " years " + sx + " , " + message + "<br/>");



                }
            } else {

                blackborder(columns[i], valarray[0]);
                blackborder(columns[i], valarray[1]);


            }


        }


        // }


    }



}


function multisum(vals, age) {

    var valsarr = vals.split("+");

    var totl = 0;

    for (var v = 0; v < valsarr.length; v++)
    {
        var val = document.getElementById(age + "_" + valsarr[v]).value;
        if (isNumber(val))
        {

            totl = totl + parseInt(val);


        }

    }

    return totl;

}




function redborder(age, elem) {

    var valsarr = elem.split("+");



    for (var v = 0; v < valsarr.length; v++)
    {

        $("#" + age + "_" + valsarr[v]).css('border-color', '#ff0000');
    }

}




function blackborder(age, elem) {

    var valsarr = elem.split("+");



    for (var v = 0; v < valsarr.length; v++)
    {
        $("#" + age + "_" + valsarr[v]).css('border-color', '#000000');
    }



}