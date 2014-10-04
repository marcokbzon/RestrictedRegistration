package com.moh.data.load;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.moh.common._ParentDAO;

public class Table_UserInfo extends _ParentDAO {

    private String DELETE_USERINFO_SQL =
            "DELETE FROM UserInfo";
    private String INSERT_USERINFO_SQL =
            "INSERT INTO UserInfo "
            + "( Email, Password, FirstName, LastName, Gender, YearOfBirth, UnivProgID ) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ? )";

    public Table_UserInfo() {
    }

    public void clearTable() {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            int deleteCount = 0;

            connection = getConnection();

            connection.setAutoCommit( true );

            preparedStatement = connection.prepareStatement( DELETE_USERINFO_SQL );

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
            FileReader fileReader = new FileReader( SESSION_TBLXTR_PATH + SESSION_FILE_SEPARATOR + "userinfo.cvs" );
            BufferedReader bufferedReader = new BufferedReader( fileReader );

            String line;
            int lineCount = 0;
            int badRecords = 0;

            line = bufferedReader.readLine();
            lineCount++;

            while( line != null && ! line.trim().equals(EMPTY_STRING) ) {
                int fs01 = line.indexOf( FIELD_SEPARATOR );
                String email = line.substring( 0, fs01 ).trim();

                int fs02 = line.indexOf( FIELD_SEPARATOR, fs01 + 1 );
                String password = line.substring( fs01 + 1, fs02 ).trim();

                int fs03 = line.indexOf( FIELD_SEPARATOR, fs02 + 1 );
                String firstName = line.substring( fs02 + 1, fs03 ).trim();

                int fs04 = line.indexOf( FIELD_SEPARATOR, fs03 + 1 );
                String lastName = line.substring( fs03 + 1, fs04 ).trim();

                int fs05 = line.indexOf( FIELD_SEPARATOR, fs04 + 1 );
                String gender = line.substring( fs04 + 1, fs05 ).trim();

                int fs06 = line.indexOf( FIELD_SEPARATOR, fs05 + 1 );
                String yearOfBirth = line.substring( fs05 + 1, fs06 ).trim();

                String univProgID = line.substring( fs06 + 1 ).trim();

                //----
                preparedStatement = connection.prepareStatement( INSERT_USERINFO_SQL );

                preparedStatement.setString( 1, email );
                preparedStatement.setString( 2, password );
                preparedStatement.setString( 3, firstName );
                preparedStatement.setString( 4, lastName );
                preparedStatement.setString( 5, gender );
                preparedStatement.setInt( 6, Integer.parseInt( yearOfBirth ) );
                preparedStatement.setString( 7, formatNulls( univProgID ) );

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

            System.out.println( "\nSTATUS FOR TABLE LOAD : UserInfo" );
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
