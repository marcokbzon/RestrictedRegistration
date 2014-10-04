package com.moh.review;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.moh.common.AbstractBean;
import com.moh.utils.Logger;

public class UploadFileRQHelper extends AbstractBean {

    private UploadedFile supportFile;
    private boolean overrideFile;
    private String delineativeName;
    private String description;

    public UploadFileRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "UploadFileRQHelper" );
    }

    public String open() {
        logger.debugMethod( "open" );

        logger.debugPage( "/jsp/fileUpload.jsp" );
        return "fileUpload";
    }

    public String submit() {
        logger.debugMethod( "submit" );

        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            inputStream = new BufferedInputStream( supportFile.getInputStream() );

            // TODO: Fix Hardcoded
            fileOutputStream = new FileOutputStream( "C:\\rrdata\\docs\\kylie.jpg" );

            byte[] buf = new byte[256];
            int read = 0;

            while( ( read = inputStream.read( buf ) ) > 0 ) {
                fileOutputStream.write( buf, 0, read );
            }
        }
        catch( IOException ioex ) {
            logger.exception( ioex );
        }
        finally {
            try {
                inputStream.close();
                fileOutputStream.close();
            }
            catch( IOException ioex2 ) {
                logger.exception( ioex2 );
            }
        }

        logger.debugPage( "/jsp/mainReviewer.jsp" );
        return "mainReviewer";
    }

    public UploadedFile getSupportFile() {
        return supportFile;
    }

    public void setSupportFile( UploadedFile supportFile ) {
        this.supportFile = supportFile;
    }

    public boolean getOverrideFile() {
        return overrideFile;
    }

    public void setOverrideFile( boolean overrideFile ) {
        this.overrideFile = overrideFile;
    }

    public String getDelineativeName() {
        return delineativeName;
    }

    public void setDelineativeName( String delineativeName ) {
        this.delineativeName = delineativeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }
}
