/* =========================================================
 *  COUNTRY EVENT -populates- PROVINCE-STATE LIST
 * =========================================================
 */
function getCountryData( form ) {
  var dataString = "";

  function addParam( name, value ) {
    dataString += ( dataString.length > 0 ? "&" : "" )
    + escape( name ).replace( /\+/g, "%2B" ) + "="
    + escape( value ? value : "" ).replace( /\+/g, "%2B" );
  }

  var elemArray = form.elements;
    
  for ( var i = 0 ; i < elemArray.length ; i++ ) {
    var element = elemArray[i];
    var elemType = element.type.toUpperCase();
    var elemName = element.name;
        
    if ( elemName ) {
      if ( elemType.indexOf( "SELECT" ) != -1 ) {
        for ( var j = 0 ; j < element.options.length ; j++ ) {
          var option = element.options[j];
                    
          if ( option.selected ) {
            addParam( elemName, option.value ? option.value : option.text );
          }
        }
      }
    }
  }
    
  return dataString;
}

var countryChangeDebug = true;

function submitCountryData( form ) {
  var xmlHttpReq;
    
  if ( window.ActiveXObject ) {
    xmlHttpReq = new ActiveXObject( "Microsoft.XMLHTTP" );
  }
  else {
    if ( window.XMLHttpRequest ) {
      xmlHttpReq = new XMLHttpRequest();
    }
    else {
      return null;
    }
  }
        
  var method = form.method ? form.method.toUpperCase() : "GET";
  var action = form.action ? form.action : document.URL;
  var data = getCountryData( form );

  var url = action;
    
  if ( data && method == "GET" ) {
    url += "?" + data;
  }
    
  xmlHttpReq.open(method, url, true);
    
  function submitCallback() {
    if ( countryChangeDebug && xmlHttpReq.readyState == 4 && xmlHttpReq.status != 200 ) {
      countryChangeDebug = false;
      alert( "Country-Change Debug Error: " + xmlHttpReq.status + " " + xmlHttpReq.statusText );
    }
  }
    
  xmlHttpReq.onreadystatechange = submitCallback;

  xmlHttpReq.setRequestHeader( "Ajax-Request", "Country-Change" );
    
  if ( method == "POST" ) {
    xmlHttpReq.setRequestHeader( "Content-Type", "application/x-www-form-urlencoded" );
    xmlHttpReq.send( data );
  }
  else {
    xmlHttpReq.send( null );
  }
    
  return xmlHttpReq;
}

var autoSaveXHR = new Array(document.forms.length);

function submitAllForms() {
  var formArray = document.forms;
    
  for ( var i = 0 ; i < formArray.length ; i++ ) {
    if ( autoSaveXHR[i] ) {
      var oldXHR = autoSaveXHR[i];
      oldXHR.onreadystatechange = function() { };
      oldXHR.abort();
      delete oldXHR;
    }
        
    autoSaveXHR[i] = submitFormData( formArray[i] );
  }
}

function gimeMore() {
  alert("fuck off");
}

