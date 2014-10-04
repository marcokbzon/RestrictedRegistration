// JavaScript Document
//var browserName=navigator.appName; 

//if (browserName=="Microsoft Internet Explorer") { 
//	document.write('<link href="css/styles.css" rel="stylesheet" type="text/css">');
//}
//else{ 
// 	document.write('<link href="css/styles_nc.css" rel="stylesheet" type="text/css">');
//}

function submitForm( elementID ) {
  if ( event.keyCode == 13 ) {
    document.getElementById( elementID ).click();
    return false;
  }
  return true;
}

function setFocusOn( elementID ) {
  document.getElementById( elementID ).focus();
}

function submitAndReset( formID, elementID ) {
  document.getElementById( elementID ).value = '0000';
  document.getElementById( formID ).submit();
}

function resetAndReload( formID, elementID ) {
  document.getElementById( elementID ).value = '0000';
  document.getElementById( formID ).reload();
}

function submitAndClear( formID, elementID ) {
  document.getElementById( elementID ).value = '';
  document.getElementById( formID ).submit();
}

function simpleSubmit( formID ) {
  document.getElementById( formID ).submit();
}
