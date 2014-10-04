package com.moh.utils;

import com.moh.common.Constants;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator, Serializable, Constants {

    private static final long serialVersionUID = 1690036216832858010L;

    public void validate( FacesContext context, UIComponent uiComponent, Object value ) throws ValidatorException {
        String fieldValue = ( ( String ) value ).trim();

        int atPosition = fieldValue.indexOf( "@" );
        int dotPositiion = fieldValue.lastIndexOf( "." );

        if( !( ( atPosition > 0 ) & ( dotPositiion > 0 ) & ( atPosition < dotPositiion ) ) ) {
            FacesMessage facesMsg = new FacesMessage( FacesMessage.SEVERITY_FATAL, "Invalid email address", EMPTY_STRING );
            throw new ValidatorException( facesMsg );
        }
    }
    
    public boolean validate ( Object email ) {
        String fieldValue = ( ( String ) email ).trim().toLowerCase();
        boolean returnedValue = true;

        int atPosition = fieldValue.indexOf( "@" );
        int dotPositiion = fieldValue.lastIndexOf( "." );

        if( !( ( atPosition > 0 ) & ( dotPositiion > 0 ) & ( atPosition < dotPositiion ) ) ) {
            returnedValue = false;
        }
        
        return returnedValue;
    }
}
