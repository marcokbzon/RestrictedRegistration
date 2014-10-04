package com.moh.data.load;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.moh.common._ParentDAO;

public class Table_InstLHIN extends _ParentDAO {

    private String DELETE_INSTLHIN_SQL =
            "DELETE FROM InstLHIN";
    private String INSERT_INSTLHIN_SQL =
            "INSERT INTO InstLHIN "
            + "( InstitutionID, LHIN_ID ) "
            + "VALUES ( ?, ? )";

    public Table_InstLHIN() {
    }

    public void clearTable() {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            int deleteCount = 0;

            connection = getConnection();

            connection.setAutoCommit( true );

            preparedStatement = connection.prepareStatement( DELETE_INSTLHIN_SQL );

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
            FileReader fileReader = new FileReader( SESSION_TBLXTR_PATH + SESSION_FILE_SEPARATOR + "instlhin.txt" );
            BufferedReader bufferedReader = new BufferedReader( fileReader );

            String line;
            int lineCount = 0;
            int badRecords = 0;

            line = bufferedReader.readLine();
            lineCount++;

            while( line != null && ! line.trim().equals(EMPTY_STRING) ) {
                int fs01 = line.indexOf( FIELD_SEPARATOR );
                String institutionID = line.substring( 0, fs01 ).trim();

                String lhin_ID = line.substring( fs01 + 1 ).trim();

                //----
                preparedStatement = connection.prepareStatement( INSERT_INSTLHIN_SQL );

                preparedStatement.setString( 1, institutionID );
                preparedStatement.setString( 2, lhin_ID );

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

            System.out.println( "\nSTATUS FOR TABLE LOAD : InstLHIN" );
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
