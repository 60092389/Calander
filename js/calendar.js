

function selectCheck(form){
    form.submit();
}
function monthDown(form){
 if(form.month.value>1){
     form.month.value--;
 }else {
     form.month.value=12;
     form.year.value--;
 }

 form.submit();
}
function monthUp(form){
 if(form.month.value<12){
     form.month.value++;
 }else {
     form.month.value=1;
     form.year.value++;
 }

 form.submit();
}

