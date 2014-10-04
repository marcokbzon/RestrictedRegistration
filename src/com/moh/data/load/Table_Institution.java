package com.moh.data.load;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.moh.common._ParentDAO;

public class Table_Institution extends _ParentDAO {

    private String DELETE_INSTITUTION_SQL =
            "DELETE FROM Institution";
    private String INSERT_INSTITUTION_SQL =
            "INSERT INTO Institution "
            + "( InstitutionID, Name_EN, Abbreviation, UAPdesignated, Enabled ) "
            + "VALUES ( ?, ?, ?, ?, ? )";

    public Table_Institution() {
    }

    public void clearTable() {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            int deleteCount = 0;

            connection = getConnection();

            connection.setAutoCommit( true );

            preparedStatement = connection.prepareStatement( DELETE_INSTITUTION_SQL );

            deleteCount = preparedStatement.executeUpdate();

            if( deleteCount < 0 ) {
                System.out.println( "No Records were deleted" );
            }
        }
        catch( Exception ex ) {
            System.out.println( "JAVA ERROR: " + ex );
        }
        finally {
            try {
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( Exception ex ) {
                System.out.println( "JAVA ERROR: " + ex );
            }
        }
    }

    public void loadTable() {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            int insertCount = 0;

            connection = getConnection();

            //------------------------
            FileReader fileReader = new FileReader( SESSION_TBLXTR_PATH + SESSION_FILE_SEPARATOR + "institution.txt" );
            BufferedReader bufferedReader = new BufferedReader( fileReader );

            String line;
            int lineCount = 0;
            int badRecords = 0;

            line = bufferedReader.readLine();
            lineCount++;

            while( line != null && ! line.trim().equals(EMPTY_STRING) ) {
                int fs01 = line.indexOf( FIELD_SEPARATOR );
                String institutionID = line.substring( 0, fs01 ).trim();

                int fs02 = line.indexOf( FIELD_SEPARATOR, fs01 + 1 );
                String name_EN = line.substring( fs01 + 1, fs02 ).trim();

                int fs03 = line.indexOf( FIELD_SEPARATOR, fs02 + 1 );
                String uapDesignated = line.substring( fs02 + 1, fs03 ).trim();

                int fs04 = line.indexOf( FIELD_SEPARATOR, fs03 + 1 );
                String enabled = line.substring( fs03 + 1, fs04 ).trim();

                String abbreviation = line.substring( fs04 + 1 ).trim();

                //----
                preparedStatement = connection.prepareStatement( INSERT_INSTITUTION_SQL );

                preparedStatement.setString( 1, institutionID );
                preparedStatement.setString( 2, name_EN );
                preparedStatement.setString( 3, abbreviation );
                preparedStatement.setInt( 4, Short.parseShort( uapDesignated ) );
                preparedStatement.setInt( 5, Short.parseShort( enabled ) );

                insertCount = preparedStatement.executeUpdate();

                if( insertCount != 1 ) {
                    System.out.println( "DATA ERROR: [" + lineCount + "] -> " + line );
                    badRecords++;
                }
                //---

                line = bufferedReader.readLine();
                lineCount++;
            }

            bufferedReader.close();

            System.out.println( "\nSTATUS FOR TABLE LOAD : Institution" );
            System.out.println( "-----------------------------------------------------------" );
            System.out.println( "\tTotal Number of Records Read : " + ( lineCount - 1 ) );
            System.out.println( "\t  Number of Records Inserted : " + ( lineCount - badRecords - 1 ) );
            System.out.println( "\t  Number of Records Rejected : " + badRecords );
            System.out.println( "-----------------------------------------------------------\n" );
        }
        catch( Exception ex ) {
            System.out.println( "JAVA ERROR: " + ex );
        }
        finally {
            try {
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( Exception ex ) {
                System.out.println( "JAVA ERROR: " + ex );
            }
        }
    }
}
