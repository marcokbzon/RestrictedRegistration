package com.moh.mail;

import java.security.Security;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.moh.common.Constants;
import com.moh.utils.Logger;

public class EmailService implements Constants {

    private Context context = null;
    private static String JNDI_EMAILSESSION;
    private String messageSubject = EMPTY_STRING;
    private String messageText = EMPTY_STRING;
    private String messageRecepient = EMPTY_STRING;
    private Logger logger;
    //private boolean SECURED_EMAIL = true;
    private boolean SECURED_EMAIL = false;
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    static {
        JNDI_EMAILSESSION = EMAIL_JNDI_SESSION;
    }

    public EmailService() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "EmailService" );
    }

    public boolean sendMessage() {
        logger.debugMethod( "sendMessage" );

        boolean developmentMode = false;  //--- PAIRO:  MAKE SURE TO CHANGE BACK TO "false" BEFORE PUBLISHING TO PROD
        
        boolean returnedValue = false;

        if ( developmentMode ) {
            logger.debugComment( "Message Recepient: " + getMessageRecepient() );
            logger.debugComment( "Message Subject  : " + getMessageSubject() );
            logger.debugComment( "Message Text     : " + getMessageText() );
        }
        else {
            if( !SECURED_EMAIL ) {
                try {
                    context = new InitialContext();
                    Session session = ( Session ) context.lookup( JNDI_EMAILSESSION );

                    String WEBMASTER_USER = session.getProperty( "mail.smtp.username" );
                    String WEBMASTER_PASSWORD = session.getProperty( "mail.smtp.password" );
                    String WEBMASTER_ADDRESS = session.getProperty( "mail.smtp.address" );
                    String SMTP_SERVER = session.getProperty( "mail.smtp.host" );

                    MimeMessage mimeMessage = new MimeMessage( session );

                    mimeMessage.setFrom( new InternetAddress( WEBMASTER_ADDRESS ) );
                    mimeMessage.addRecipient( Message.RecipientType.TO, new InternetAddress( getMessageRecepient() ) );
                    mimeMessage.setSubject( getMessageSubject() );
                    mimeMessage.setText( getMessageText() );

                    Transport transport = session.getTransport( "smtp" );
                    transport.connect( SMTP_SERVER, WEBMASTER_USER, WEBMASTER_PASSWORD );
                    mimeMessage.saveChanges();
                    transport.sendMessage( mimeMessage, mimeMessage.getAllRecipients() );
                    transport.close();

                    returnedValue = true;
                }
                catch( NamingException nex ) {
                    logger.exception( nex );
                }
                catch( MessagingException mex ) {
                    logger.exception( mex );
                }
            }
            else {
                try {
                    context = new InitialContext();
                    Session mSession = ( Session ) context.lookup( JNDI_EMAILSESSION );

                    final String EMAIL_USER = mSession.getProperty( "application.email.username" );
                    final String EMAIL_PASSWORD = mSession.getProperty( "application.email.password" );
                    final String EMAIL_ADDRESS = mSession.getProperty( "application.email.address" );

                    final String TRANSPORT_PROTOCOL = mSession.getProperty( "server.transport.protocol" );
                    final String SERVER_HOST_ADDRESS = mSession.getProperty( "server.host.address" );
                    final String SERVER_HOST_PORT = mSession.getProperty( "server.host.port" ); //465 or 587
                    final String SESSION_TLS_ENABLED = mSession.getProperty( "session.tls.enabled" );
                    final String SESSION_AUTH_ENABLED = mSession.getProperty( "session.auth.enabled" );
                    final String SESSION_DEBUG_ENABLED = mSession.getProperty( "session.debug.enabled" );

                    logger.forceComment( "host=" + SERVER_HOST_ADDRESS + ",port=" + SERVER_HOST_PORT + ",protocol=" + TRANSPORT_PROTOCOL );
                    logger.forceComment( "auth=" + SESSION_AUTH_ENABLED + ",debug=" + SESSION_AUTH_ENABLED + ",tls=" + SESSION_TLS_ENABLED );
                    logger.forceComment( "account=" + EMAIL_ADDRESS + ",user=" + EMAIL_USER );

                    Security.addProvider( new com.sun.net.ssl.internal.ssl.Provider() );

                    Properties props = new Properties();
                    props.put( "mail.transport.protocol", TRANSPORT_PROTOCOL );
                    props.put( "mail.smtp.host", SERVER_HOST_ADDRESS );
                    props.put( "mail.smtp.port", SERVER_HOST_PORT );

                    props.put( "mail.smtp.user", EMAIL_USER );
                    props.put( "mail.smtp.password", EMAIL_PASSWORD );

                    props.put( "mail.smtp.starttls.enable", SESSION_TLS_ENABLED );
                    props.put( "mail.smtp.auth", SESSION_AUTH_ENABLED );
                    props.put( "mail.debug", SESSION_DEBUG_ENABLED );
                    props.put( "mail.smtp.quitwait", "false" );

                    props.put( "mail.smtp.socketFactory.port", SERVER_HOST_PORT );
                    props.put( "mail.smtp.socketFactory.class", SSL_FACTORY );
                    props.put( "mail.smtp.socketFactory.fallback", "false" );

                    Session session = Session.getDefaultInstance( props,
                            new Authenticator() {

                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication( EMAIL_USER, EMAIL_PASSWORD );
                                }
                            } );

                    session.setDebug( Boolean.getBoolean( SESSION_DEBUG_ENABLED ) );

                    Message mimeMessage = new MimeMessage( session );

                    mimeMessage.setFrom( new InternetAddress( EMAIL_ADDRESS ) );
                    mimeMessage.addRecipient( Message.RecipientType.TO, new InternetAddress( getMessageRecepient() ) );
                    mimeMessage.setSubject( getMessageSubject() );
                    mimeMessage.setText( getMessageText() );

                    Transport transport = session.getTransport();
                    transport.connect();
                    mimeMessage.saveChanges();
                    transport.sendMessage( mimeMessage, mimeMessage.getAllRecipients() );
                    transport.close();

                    returnedValue = true;
                }
                catch( NamingException nex ) {
                    logger.exception( nex );
                }
                catch( MessagingException mex ) {
                    logger.exception( mex );
                }
            }
        }

        return returnedValue;
    }

    private String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject( String messageSubject ) {
        this.messageSubject = messageSubject;
    }

    private String getMessageText() {
        return messageText;
    }

    public void setMessageText( String messageText ) {
        this.messageText = messageText;
    }

    private String getMessageRecepient() {
        return messageRecepient.toLowerCase();
    }

    public void setMessageRecepient( String messageRecepient ) {
        this.messageRecepient = messageRecepient.toLowerCase();
    }
}
