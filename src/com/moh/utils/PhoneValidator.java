package com.moh.utils;

import com.moh.common.Constants;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class PhoneValidator implements Validator, Serializable, Constants {

    private static final long serialVersionUID = 1690036216832858010L;

    public void validate( FacesContext context, UIComponent uiComponent, Object value ) throws ValidatorException {
        String fieldValue = EMPTY_STRING;

        if( value != "" ) {
            fieldValue = ( ( String ) value ).trim();

            if( fieldValue.length() != 12 ) {
                FacesMessage facesMsg = new FacesMessage( FacesMessage.SEVERITY_FATAL, "Invalid phone number format", EMPTY_STRING );
                throw new ValidatorException( facesMsg );
            }

            // 999-999-9999
            String areaCode = ( ( String ) fieldValue ).substring( 0, 3 ).trim();
            String dashFirst = ( ( String ) fieldValue ).substring( 3, 4 );
            String phoneThree = ( ( String ) fieldValue ).substring( 4, 7 ).trim();
            String dashSecond = ( ( String ) fieldValue ).substring( 7, 8 );
            String phoneFour = ( ( String ) fieldValue ).substring( 8 ).trim();

            String fullPhone = areaCode + phoneThree + phoneFour;

            if( ( fullPhone.length() == 10 ) && ( dashFirst.equals( "-" ) ) && ( dashSecond.equals( "-" ) ) ) {
                if( !hasOnlyDigits( fullPhone ) ) {
                    FacesMessage facesMsg = new FacesMessage( FacesMessage.SEVERITY_FATAL, "Invalid phone number format", EMPTY_STRING );
                    throw new ValidatorException( facesMsg );
                }
            }
            else {
                FacesMessage facesMsg = new FacesMessage( FacesMessage.SEVERITY_FATAL, "Invalid phone number format", EMPTY_STRING );
                throw new ValidatorException( facesMsg );
            }
        }
    }

    private boolean hasOnlyDigits( String s ) {
        for( int i = 0 ; i < s.length() ; i++ ) {
            if( !Character.isDigit( s.charAt( i ) ) ) {
                return false;
            }
        }

        return true;
    }
}
