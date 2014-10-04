package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.moh.common._ParentDAO;
import com.moh.utils.Logger;
import java.util.HashMap;
import java.util.Map;

public class ValueParametersDAO extends _ParentDAO {

    private String GET_VALUE_PARAMETERS =
            "SELECT * FROM ValueParameters";

    public ValueParametersDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ValueParametersDAO" );
    }

    public Map<String, String> getValues() {
        logger.debugMethod( "getValues" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        
        Map<String, String> valueParameters = new HashMap<>();

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement( GET_VALUE_PARAMETERS );
            resultSet = preparedStatement.executeQuery();
            
            while( resultSet.next() ) {
                String referenceKey = resultSet.getString( "ReferenceKey" );
                String contentValue = resultSet.getString( "ContentValue" );

                valueParameters.put( referenceKey, contentValue );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return valueParameters;
    }
}