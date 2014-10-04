package com.moh.data.load;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.moh.common._ParentDAO;

public class Table_UnivProg extends _ParentDAO {

    private String DELETE_UNIVPROG_SQL =
            "DELETE FROM UnivProg";
    private String INSERT_UNIVPROG_SQL =
            "INSERT INTO UnivProg "
            + "( UnivProgID, UniversityID, ProgramID, Email ) "
            + "VALUES ( ?, ?, ?, ? )";

    public Table_UnivProg() {
    }

    public void clearTable() {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            int deleteCount = 0;

            connection = getConnection();

            connection.setAutoCommit( true );

            preparedStatement = connection.prepareStatement( DELETE_UNIVPROG_SQL );

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
            FileReader fileReader = new FileReader( SESSION_TBLXTR_PATH + SESSION_FILE_SEPARATOR + "univ_prog.cvs" );
            BufferedReader bufferedReader = new BufferedReader( fileReader );

            String line;
            int lineCount = 0;
            int badRecords = 0;

            line = bufferedReader.readLine();
            lineCount++;

            while( line != null && ! line.trim().equals(EMPTY_STRING) ) {
                int fs01 = line.indexOf( FIELD_SEPARATOR );
                String univProgID = line.substring( 0, fs01 ).trim();

                int fs02 = line.indexOf( FIELD_SEPARATOR, fs01 + 1 );
                String universityID = line.substring( fs01 + 1, fs02 ).trim();

                int fs03 = line.indexOf( FIELD_SEPARATOR, fs02 + 1 );
                String programID = line.substring( fs02 + 1, fs03 ).trim();

                String email = line.substring( fs03 + 1 ).trim();

                //----
                preparedStatement = connection.prepareStatement( INSERT_UNIVPROG_SQL );

                preparedStatement.setString( 1, univProgID );
                preparedStatement.setString( 2, universityID );
                preparedStatement.setString( 3, programID );
                preparedStatement.setString( 4, email );

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

            System.out.println( "\nSTATUS FOR TABLE LOAD : UnivProg" );
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
