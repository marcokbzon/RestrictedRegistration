package com.moh.data.maintenance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import com.moh.common._ParentDAO;
import com.moh.utils.Logger;
import com.moh.utils.ObjectManipulation;

public class DatabaseUtility extends _ParentDAO {

    private File xmlDoc = new File( "C:\\RRBACKUP\\databaseTables.xml" );
    private Document jDoc;
    private ObjectManipulation objMan = new ObjectManipulation();

    public DatabaseUtility() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "DatabaseUtility" );

        // Load databaseTables XML
        try {
            SAXBuilder saxBuilder = new SAXBuilder( "org.apache.xerces.parsers.SAXParser" );
            jDoc = saxBuilder.build( xmlDoc );
        }
        catch( IOException ioex ) {
            logger.exception( ioex );
        }
        catch( JDOMException jex ) {
            logger.exception( jex );
        }
    }

    public void backup() {
        logger.debugMethod( "backup" );

        backup( "Agreement" );
        backup( "AgreeTo" );
        backup( "AppCounter" );
        backup( "Application" );
        backup( "ApprovalCommittee" );
        backup( "ApprovalPgmeDean" );
        backup( "ApprovalProgDirector" );
        backup( "ApprovalSupervisor" );
        backup( "Classification" );
        backup( "Code" );
        backup( "ContactInfo" );
        backup( "Country" );
        backup( "Document" );
        backup( "Education" );
        backup( "FileUpload" );
        backup( "InstClassification" );
        backup( "Institution" );
        backup( "InstLHIN" );
        backup( "InstLocation" );
        backup( "InstReviewer" );
        backup( "InstServType" );
        backup( "JobPosting" );
        backup( "LHIN" );
        backup( "Location" );
        backup( "Program" );
        backup( "ProvinceState" );
        backup( "Role" );
        backup( "RotationHistory" );
        backup( "ServiceType" );
        backup( "Shift" );
        backup( "ShiftSummary" );
        backup( "ShiftTracking" );
        backup( "Status" );
        backup( "University" );
        backup( "UnivProg" );
        backup( "UnivReviewer" );
        backup( "UserInfo" );
        backup( "UserRole" );
    }

    @SuppressWarnings( "unchecked" )
    private boolean backup( String tableName ) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean operationWasSuccessful = true;
        BufferedWriter out = null;

        String listOfFields = EMPTY_STRING;
        String xQuery = EMPTY_STRING;
        List<Element> listElems = null;

        xQuery = "/database//table[@name='" + tableName + "']";

        try {
            Element tableElem = ( Element ) XPath.selectSingleNode( jDoc, xQuery );

            listElems = tableElem.getChildren();

            for( Element fieldElem : listElems ) {
                String fieldName = fieldElem.getTextTrim();
                listOfFields = listOfFields + fieldName + ", ";
            }
        }
        catch( JDOMException jex ) {
            logger.exception( jex );
            operationWasSuccessful = false;
        }
        catch( Exception ex ) {
            logger.exception( ex );
            operationWasSuccessful = false;
        }

        listOfFields = listOfFields.substring( 0, listOfFields.length() - 2 );

        // Delete backup file
        boolean fileDeletedSuccessfully = ( new File( "C:\\RRBACKUP\\" + tableName + ".TXT" ).delete() );

        if( !fileDeletedSuccessfully ) {
            logger.error( "Failed to delete file: " + tableName + ".TXT" );
            operationWasSuccessful = false;
        }

        String sqlStatement = "SELECT " + listOfFields + " FROM " + tableName;
        //logger.forceComment( "sqlStatement=" + sqlStatement );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( sqlStatement );

            resultSet = preparedStatement.executeQuery();

            out = new BufferedWriter( new FileWriter( "C:\\RRBACKUP\\" + tableName + ".TXT" ) );

            while( resultSet.next() ) {
                String dataRow = EMPTY_STRING;

                //Cycle thru list of elements to get table column names (one by one) and their data
                for( Element fieldElem : listElems ) {
                    String currentData = resultSet.getString( fieldElem.getTextTrim() );
                    dataRow = dataRow + objMan.nullsToString( objMan.replaceCarriageReturn( currentData, "<CR>" ) ) + "|";
                }

                dataRow = dataRow.substring( 0, dataRow.length() - 1 );

                // Write data row to backup file
                out.write( dataRow + "\n" );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
            operationWasSuccessful = false;
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
                out.close();
            }
            catch( SQLException sex ) {
                logger.exception( sex );
                operationWasSuccessful = false;
            }
            catch( IOException ioex ) {
                logger.exception( ioex );
                operationWasSuccessful = false;
            }
        }

        return operationWasSuccessful;
    }

    public void restore() {
        //restore( "Institution" );
    }

    @SuppressWarnings( { "unchecked", "unused" } )
    private boolean restore( String tableName ) {
        boolean operationWasSuccessful = true;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;
        BufferedReader in = null;

        String listOfFields = EMPTY_STRING;
        String questionMarks = EMPTY_STRING;
        //String listOfTypes = EMPTY_STRING;
        int fieldCount = 0;
        Map<String, String> hashTypes = new HashMap<>();
        Map<String, String> hashValues = new HashMap<>();
        String xQuery = EMPTY_STRING;
        List<Element> listElems = null;

        xQuery = "/database//table[@name='" + tableName + "']";

        try {
            Element tableElem = ( Element ) XPath.selectSingleNode( jDoc, xQuery );

            listElems = tableElem.getChildren();

            for( Element fieldElem : listElems ) {
                fieldCount++;

                String fieldName = fieldElem.getTextTrim();
                listOfFields = listOfFields + fieldName + ", ";

                questionMarks = questionMarks + "?, ";

                String fieldType = fieldElem.getAttributeValue( "type" );
                hashTypes.put( EMPTY_STRING + fieldCount, fieldType );
            }
        }
        catch( JDOMException jex ) {
            logger.exception( jex );
            operationWasSuccessful = false;
        }
        catch( Exception ex ) {
            logger.exception( ex );
            operationWasSuccessful = false;
        }

        listOfFields = listOfFields.substring( 0, listOfFields.length() - 2 );
        questionMarks = questionMarks.substring( 0, questionMarks.length() - 2 );

        String sqlStatement = "INSERT INTO " + tableName + " ( " + listOfFields + " ) VALUES ( " + questionMarks + " )";

        try {
            int[] insertCount;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( sqlStatement );

            in = new BufferedReader( new FileReader( "C:\\RRBACKUP\\" + tableName + ".TXT" ) );
            String dataRow;

            // loop thru file
            while( ( dataRow = in.readLine() ) != null ) {
                logger.forceComment( dataRow );

                boolean continueLoop = true;
                int hashCount = 1;
                int x = dataRow.indexOf( "|" ); // check if any pipe exists

                if( x > 0 ) {
                    while( continueLoop ) {
                        x = dataRow.indexOf( "|" ); // pipe position

                        if( x > 0 ) {
                            String dataField = dataRow.substring( 0, x );
                            hashValues.put( EMPTY_STRING + hashCount, dataField );
                            hashCount++;

                            String remainingData = dataRow.substring( x + 1, dataRow.length() );
                            dataRow = remainingData;
                        }
                        else {
                            hashValues.put( EMPTY_STRING + hashCount, dataRow );
                            continueLoop = false;
                        }
                    }
                }

                // for each field within a row
                for( int i = 1 ; i <= fieldCount ; i++ ) {
                    if( hashTypes.get( EMPTY_STRING + i ).equals( "String" ) ) {
                        logger.forceComment( i + " = ( String ) " + hashValues.get( EMPTY_STRING + i ) );
                        //preparedStatement.setString( i, EMPTY_STRING + hashValues.get( EMPTY_STRING + i ) );
                    }
                    else {
                        if( hashTypes.get( EMPTY_STRING + i ).equals( "int" ) ) {
                            logger.forceComment( i + " = ( int ) " + hashValues.get( EMPTY_STRING + i ) );
                            //preparedStatement.setInt( i, Integer.parseInt( hashValues.get( EMPTY_STRING + i ) ) );
                        }
                        else {
                            if( hashTypes.get( EMPTY_STRING + i ).equals( "boolean" ) ) {
                                logger.forceComment( i + " = ( boolean ) " + hashValues.get( EMPTY_STRING + i ) );
                                //preparedStatement.setBoolean( i, hashValues.get( EMPTY_STRING + i ) );
                            }
                            else {
                                if( hashTypes.get( EMPTY_STRING + i ).equals( "timestamp" ) ) {
                                    logger.forceComment( i + " = ( timestamp ) " + hashValues.get( EMPTY_STRING + i ) );
                                    //preparedStatement.setTimeStamp( i, hashValues.get( EMPTY_STRING + i ) );
                                }
                            }
                        }
                    }
                }

                //preparedStatement.addBatch( "SQL" );
            }

            //insertCount = preparedStatement.executeBatch();
            insertCount = new int[1];

            int insertions = insertCount.length;

            for( int i = 0 ; i < insertions ; i++ ) {
                if( insertCount[i] < 0 ) {
                    operationWasSuccessful = false;
                }
            }

            if( !operationWasSuccessful ) {
                logger.error( "Failed to Insert Data to DB" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );

            try {
                connection.rollback( savePoint );
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }
        finally {
            try {
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
                in.close();
            }
            catch( Exception ex ) {
                logger.exception( ex );
            }
        }

        return operationWasSuccessful;
    }
}
