/* Ashu  */

function newCookie(name,value,days) {
 var days = 10;   //  number of days for the cookie to last
                
 if (days) {
   var date = new Date();
   date.setTime(date.getTime()+(days*24*60*60*1000));
   var expires = "; expires="+date.toGMTString(); }
   else var expires = "";
   document.cookie = name+"="+value+expires+"; path=/"; 
   }

function readCookie(name) {
   var nameSG = name + "=";
   var nuller = '';
  if (document.cookie.indexOf(nameSG) == -1)
    return nuller;

   var ca = document.cookie.split(';');
  for(var i=0; i<ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0)==' ') c = c.substring(1,c.length);
  if (c.indexOf(nameSG) == 0) return c.substring(nameSG.length,c.length); }
   // return null;
     }

function eraseCookie(name) {
  newCookie(name,"",1); }

function toMem(a) {
    newCookie('theName', document.loginForm.userID.value,1);     // add a new cookie as shown at left for every
    newCookie('thePwd', document.loginForm.password.value,1);   // field you wish to have the script remember
	newCookie('remMe', document.loginForm.checker.checked,1);
}

function delMem(a) {
  eraseCookie('theName');   // make sure to add the eraseCookie function for every field
  eraseCookie('thePwd');
  eraseCookie('remMe');

  }


function remCookie() {
document.loginForm.userID.value = readCookie("theName");
document.loginForm.password.value = readCookie("thePwd");
document.loginForm.checker.checked=readCookie("remMe");
}


function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      if (oldonload) {
        oldonload();
      }
      func();
    }
  }
}

addLoadEvent(function() {
  remCookie();
});