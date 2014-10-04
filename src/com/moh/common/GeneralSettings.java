package com.moh.common;

import com.moh.data.dao.ValueParametersDAO;
import java.util.Iterator;
import java.util.Map;

public class GeneralSettings extends AbstractBean {
    
    public GeneralSettings() {
    }
    
    public void initialize () {
        
        ValueParametersDAO valueParametersDAO = new ValueParametersDAO();
        
        Iterator<Map.Entry<String, String>> iter = ( Iterator<Map.Entry<String, String>> ) valueParametersDAO.getValues().entrySet().iterator();
        
        while( iter.hasNext() ) {
            Map.Entry<String, String> valueParam = iter.next();
            
            String referenceKey = valueParam.getKey();
            String contentValue = valueParam.getValue();
            
            if ( referenceKey.trim().equals( SESSION_YEAROFBIRTH_MIN ) ) {
                addToSession( SESSION_YEAROFBIRTH_MIN, contentValue );
            }
            else {
                if ( referenceKey.trim().equals( SESSION_YEAROFBIRTH_MAX ) ) {
                    addToSession( SESSION_YEAROFBIRTH_MAX, contentValue );
                }
                else {
                    if ( referenceKey.trim().equals( SESSION_YEAROFEXAM_MIN ) ) {
                        addToSession( SESSION_YEAROFEXAM_MIN, contentValue );
                    }
                    else {
                        if ( referenceKey.trim().equals( SESSION_YEAROFEXAM_MAX ) ) {
                            addToSession( SESSION_YEAROFEXAM_MAX, contentValue );
                        }
                        else {
                            if ( referenceKey.trim().equals( SESSION_FILE_SEPARATOR ) ) {
                                addToSession( SESSION_FILE_SEPARATOR, contentValue );
                            }
                            else {
                                if ( referenceKey.trim().equals( SESSION_RRDOCS_PATH ) ) {
                                    addToSession( SESSION_RRDOCS_PATH, contentValue );
                                }
                                else {
                                    if ( referenceKey.trim().equals( SESSION_TBLXTR_PATH ) ) {
                                    addToSession( SESSION_TBLXTR_PATH, contentValue );
                                }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
