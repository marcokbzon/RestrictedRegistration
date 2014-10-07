<%-- 
    Created on : May 20, 2014
    Author     : Marco Sosa
    Company    : HeadLong Media
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Restricted Registration</title>
  </head>
  <body>
      <big><b>Release Information</b></big>
      <ul>
          <li><b>R-140521:</b> Change password retrieval functionality to allow anyone to retrieve their own passwords.  Originally it was only available to Residents.</li>
          <li><b>R-140526:</b> Add information message to the Forgot Password page.  When the message is successfully sent over, a info message will appear at the top of the screen, and the user will remain on the same page.</li>
          <li><b>R-140529:</b> Add Active=1 to the WHERE clause of query "GET_UNIVPROGID_SQL" to retrieve only the active Program Director.</li>
      </ul>
  </body>
</html>