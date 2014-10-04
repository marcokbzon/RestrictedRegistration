package com.moh.utils;

// Marco Sosa 2013-12-11.  See comment on getBeginingOfLine()
//import java.util.Calendar;

import javax.faces.context.FacesContext;

import com.moh.common.Constants;

public class Logger implements Constants {

    public static final String TYPE_PARAMETER = "PARAM";
    public static final String TYPE_VARIABLE = "VARIABLE";
    public static final String EXCEPTION_INDENTATION = "                  ";  // 18 spaces
    public static final int DEBUG_LEVEL_NONE = 0;
    public static final int DEBUG_LEVEL_INFO = 1; // PROD setting
    public static final int DEBUG_LEVEL_DATA = 2; // DEV setting
    private int debugLevel = 0;
    private String fullClassName = EMPTY_STRING;
    private String userID = EMPTY_STRING;
    // Marco Sosa 2013-12-11.  See comment on getBeginingOfLine()
    //private Calendar rightnow = null;
    //private long currentTime = 0;
    //private DateFormatter df = null;
    @SuppressWarnings( "unused" )
	private ObjectManipulation om = null;

    /**
     * Constructor Cannot be instantiated: use instead Logger(Class) constructor
     */
    @SuppressWarnings( "unused" )
	private Logger() {}

    /**
     * Constructor: Use this method to instantiate Logger e.g. Logger logger = new
     * Logger(this.getClass())
     * 
     * @param runningClass
     */
    public Logger( @SuppressWarnings( "rawtypes" ) Class runningClass ) {
        // Marco Sosa 2013-12-11.  See comment on getBeginingOfLine()
        //rightnow = Calendar.getInstance();
        //df = new DateFormatter();
        om = new ObjectManipulation();

        debugLevel = DEBUG_LEVEL_DATA;   //---- PAIRO:  CHANGE TO "DEBUG_LEVEL_INFO" FOR PROD

        userID = ( String ) getFromSession( SESSION_EMAIL );
        
        if ( ! ( userID == null || userID.equals( EMPTY_STRING ) ) ) {
            userID = userID.trim().toLowerCase();
        }

        fullClassName = runningClass.getName();
    }

    public String getBeginingOfLine() {
        // Marco Sosa 2013-12-11
        // Every row in the Glassfish log contains a timestamp, so we are removing the timestamp within the Logger
        
        //currentTime = System.currentTimeMillis();
        //rightnow.setTimeInMillis( currentTime );

        //return "--LOGGER: " + userID + " " + df.formatCalendar( rightnow, 0 ) + " [" + fullClassName + "] ";
        return "--LOGGER: " + userID + " [" + fullClassName + "] ";
    }

    /*------------------------------------------------------------------------
     *  DEBUG METHOD
     * -----------------------------------------------------------------------
     */
    /**
     * 
     */
    public void debugMethod( String methodName ) {
        if( debugLevel >= DEBUG_LEVEL_INFO ) {
            sendLineToOutput( getBeginingOfLine() + "METHOD: " + methodName + "()" );
        }
    }
    
    public void debugPage( String pageName ) {
        if( debugLevel >= DEBUG_LEVEL_INFO ) {
            sendLineToOutput( getBeginingOfLine() + "JSP: " + pageName );
        }
    }
    

    /*------------------------------------------------------------------------
     *  DEBUG DATA (VARIABLES and PARAMETERS)
     * -----------------------------------------------------------------------
     * byte:    It is an 8-bit signed two's complement integer 
     *          Range from -128 to 127 (inclusive)
     *          Default value: 0
     * short:   It is a 16-bit signed two's complement integer 
     *          Range from -32,768 to 32,767 (inclusive)
     *          Default value: 0
     * int:     It is a 32-bit signed two's complement integer 
     *          Range from -2,147,483,648 to 2,147,483,647 (inclusive)
     *          Default value: 0 
     *          e.g. 26 (decimal), 032 (octal), 0x1a (hexadecimal)
     * long:    It is a 64-bit signed two's complement integer
     *          Range from -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807 (inclusive)
     *          Default value: 0L
     * float:   It is a single-precision 32-bit IEEE 754 floating point
     *          Default value: 0.0f
     *          e.g. 123.4f
     * double:  It is a double-precision 64-bit IEEE 754 floating point
     *          Default value: 0.0d
     *          e.g. 123.4, 1.234e2
     * boolean: It has only two possible values: true and false
     *          Default value: false
     * char:    It is a single 16-bit Unicode character. 
     *          Range from '\u0000' (or 0) to '\uffff' (or 65,535 inclusive).
     *          Default value: '\u0000'
     *          \b (backspace), \t (tab), \n (line feed), \f (form feed), \r (carriage return)
     *          \" (double quote), \' (single quote), \\ (backslash) 
     * String:  Default value: null
     * 
     */
    public void debugParameter( String parameterName, Object parameterValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            debugData( TYPE_PARAMETER, parameterName, parameterValue );
        }
    }

    public void debugParameter( String parameterName, byte parameterValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Byte byteValue = Byte.valueOf( EMPTY_STRING + parameterValue );
            debugData( TYPE_PARAMETER, parameterName, byteValue );
        }
    }

    public void debugParameter( String parameterName, short parameterValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Short shortValue = Short.valueOf( EMPTY_STRING + parameterValue );
            debugData( TYPE_PARAMETER, parameterName, shortValue );
        }
    }

    public void debugParameter( String parameterName, int parameterValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Integer integerValue = Integer.valueOf( EMPTY_STRING + parameterValue );
            debugData( TYPE_PARAMETER, parameterName, integerValue );
        }
    }

    public void debugParameter( String parameterName, long parameterValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Long longValue = Long.valueOf( EMPTY_STRING + parameterValue );
            debugData( TYPE_PARAMETER, parameterName, longValue );
        }
    }

    public void debugParameter( String parameterName, float parameterValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Float floatValue = Float.valueOf( EMPTY_STRING + parameterValue );
            debugData( TYPE_PARAMETER, parameterName, floatValue );
        }
    }

    public void debugParameter( String parameterName, double parameterValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Double doubleValue = Double.valueOf( EMPTY_STRING + parameterValue );
            debugData( TYPE_PARAMETER, parameterName, doubleValue );
        }
    }

    public void debugParameter( String parameterName, boolean parameterValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Boolean booleanValue = Boolean.valueOf( parameterValue );
            debugData( TYPE_PARAMETER, parameterName, booleanValue );
        }
    }

    public void debugParameter( String parameterName, char parameterValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            //Character characterValue = String.valueOf( parameterValue );
            String stringValue = String.valueOf( parameterValue );
            debugData( TYPE_PARAMETER, parameterName, stringValue );
        }
    }

    public void debugVariable( String variableName, Object variableValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            debugData( TYPE_VARIABLE, variableName, variableValue );
        }
    }

    public void debugVariable( String variableName, byte variableValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Byte byteValue = Byte.valueOf( EMPTY_STRING + variableValue );
            debugData( TYPE_VARIABLE, variableName, byteValue );
        }
    }

    public void debugVariable( String variableName, short variableValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Short shortValue = Short.valueOf( EMPTY_STRING + variableValue );
            debugData( TYPE_VARIABLE, variableName, shortValue );
        }
    }

    public void debugVariable( String variableName, int variableValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Integer integerValue = Integer.valueOf( EMPTY_STRING + variableValue );
            debugData( TYPE_VARIABLE, variableName, integerValue );
        }
    }

    public void debugVariable( String variableName, long variableValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Long longValue = Long.valueOf( EMPTY_STRING + variableValue );
            debugData( TYPE_VARIABLE, variableName, longValue );
        }
    }

    public void debugVariable( String variableName, float variableValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Float floatValue = Float.valueOf( EMPTY_STRING + variableValue );
            debugData( TYPE_VARIABLE, variableName, floatValue );
        }
    }

    public void debugVariable( String variableName, double variableValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Double doubleValue = Double.valueOf( EMPTY_STRING + variableValue );
            debugData( TYPE_VARIABLE, variableName, doubleValue );
        }
    }

    public void debugVariable( String variableName, boolean variableValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            Boolean booleanValue = Boolean.valueOf( variableValue );
            debugData( TYPE_VARIABLE, variableName, booleanValue );
        }
    }

    public void debugVariable( String variableName, char variableValue ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            //Character characterValue = String.valueOf( parameterValue );
            String stringValue = String.valueOf( variableValue );
            debugData( TYPE_VARIABLE, variableName, stringValue );
        }
    }

    public void debugComment( String comment ) {
        if( debugLevel >= DEBUG_LEVEL_DATA ) {
            debugData( TYPE_VARIABLE, "CODE-COMMENT", comment );
        }
    }

    public void forceComment( String comment ) {
        debugData( TYPE_VARIABLE, "CODE-COMMENT", comment );
    }

    /**
     * 
     * @param typeOfDataInClass
     * @param dataName
     * @param dataValue
     */
    private void debugData( String typeOfDataInClass, String dataName, Object dataValue ) {
        String dataType = EMPTY_STRING;
        String dataValueCasted = EMPTY_STRING;

        /*
         *  String[], String[][] and String[][][]
         */
        if( dataValue instanceof String[] ) {
            dataType = "String[]";

            String[] castedObject = ( String[] ) dataValue;

            dataValueCasted = "{ ";

            for( int i = 0 ; i < castedObject.length ; i++ ) {
                if( !( castedObject[i] == null ) ) {
                    if( i == 0 ) {
                        dataValueCasted = dataValueCasted + i + "=\"" + castedObject[i].toString() + "\"";
                    }
                    else {
                        dataValueCasted = dataValueCasted + ", " + i + "=\"" + castedObject[i].toString() + "\"";
                    }
                }
            }

            dataValueCasted = dataValueCasted + " }";
        }
        else {
            if( dataValue instanceof String[][] ) {
                dataType = "String[][]";

                String[][] castedObject = ( String[][] ) dataValue;

                dataValueCasted = "{ ";

                for( int i = 0 ; i < castedObject.length ; i++ ) {
                    String[] subArray = castedObject[i];

                    for( int a = 0 ; a < subArray.length ; a++ ) {
                        if( a == 0 & i == 0 ) {
                            dataValueCasted = dataValueCasted + i + "." + a + "=\"" + subArray[a].toString() + "\"";
                        }
                        else {
                            dataValueCasted = dataValueCasted + ", " + i + "." + a + "=\"" + subArray[a].toString() + "\"";
                        }
                    }
                }

                dataValueCasted = dataValueCasted + " }";
            }
            else {
                if( dataValue instanceof String[][][] ) {
                    dataType = "String[][][]";

                    String[][][] castedObject = ( String[][][] ) dataValue;

                    dataValueCasted = "{ ";

                    for( int i = 0 ; i < castedObject.length ; i++ ) {
                        String[][] subArray = castedObject[i];

                        for( int a = 0 ; a < subArray.length ; a++ ) {
                            String[] deepArray = subArray[a];

                            for( int u = 0 ; u < deepArray.length ; u++ ) {
                                if( i == 0 & a == 0 & u == 0 ) {
                                    dataValueCasted = dataValueCasted + i + "." + a + "." + u + "=\"" + deepArray[u].toString() + "\"";
                                }
                                else {
                                    dataValueCasted = dataValueCasted + ", " + i + "." + a + "." + u + "=\"" + deepArray[u].toString() + "\"";
                                }
                            }
                        }
                    }

                    dataValueCasted = dataValueCasted + " }";
                }
            }
        }

        /*
         *  int[], int[][] and int[][][]
         */
        if( dataValue instanceof int[] ) {
            dataType = "int[]";

            int[] castedObject = ( int[] ) dataValue;

            dataValueCasted = "{ ";

            for( int i = 0 ; i < castedObject.length ; i++ ) {
                if( i == 0 ) {
                    dataValueCasted = dataValueCasted + i + "=" + Integer.toString( castedObject[i] );
                }
                else {
                    dataValueCasted = dataValueCasted + ", " + i + "=" + Integer.toString( castedObject[i] );
                }
            }

            dataValueCasted = dataValueCasted + " }";
        }
        else {
            if( dataValue instanceof int[][] ) {
                dataType = "int[][]";

                int[][] castedObject = ( int[][] ) dataValue;

                dataValueCasted = "{ ";

                for( int i = 0 ; i < castedObject.length ; i++ ) {
                    int[] subArray = castedObject[i];

                    for( int a = 0 ; a < subArray.length ; a++ ) {
                        if( a == 0 & i == 0 ) {
                            dataValueCasted = dataValueCasted + i + "." + a + "=" + Integer.toString( subArray[a] );
                        }
                        else {
                            dataValueCasted = dataValueCasted + ", " + i + "." + a + "=" + Integer.toString( subArray[a] );
                        }
                    }
                }

                dataValueCasted = dataValueCasted + " }";
            }
            else {
                if( dataValue instanceof int[][][] ) {
                    dataType = "int[][][]";

                    int[][][] castedObject = ( int[][][] ) dataValue;

                    dataValueCasted = "{ ";

                    for( int i = 0 ; i < castedObject.length ; i++ ) {
                        int[][] subArray = castedObject[i];

                        for( int a = 0 ; a < subArray.length ; a++ ) {
                            int[] deepArray = subArray[a];

                            for( int u = 0 ; u < deepArray.length ; u++ ) {
                                if( i == 0 & a == 0 & u == 0 ) {
                                    dataValueCasted = dataValueCasted + i + "." + a + "." + u + "=" + Integer.toString( deepArray[u] );
                                }
                                else {
                                    dataValueCasted = dataValueCasted + ", " + i + "." + a + "." + u + "=" + Integer.toString( deepArray[u] );
                                }
                            }
                        }
                    }

                    dataValueCasted = dataValueCasted + " }";
                }
            }
        }

        // String
        if( dataValue instanceof String ) {
            dataType = "String";
            dataValueCasted = "\"" + dataValue.toString() + "\"";
        }
        else {
            // int
            if( dataValue instanceof Integer ) {
                dataType = "int";
                dataValueCasted = ( ( Integer ) dataValue ).toString();
            }
            else {
                // boolean
                if( dataValue instanceof Boolean ) {
                    dataType = "boolean";
                    dataValueCasted = ( ( Boolean ) dataValue ).toString();
                }
                else {
                    // float
                    if( dataValue instanceof Float ) {
                        dataType = "float";
                        dataValueCasted = ( ( Float ) dataValue ).toString();
                    }
                    else {
                        // byte
                        if( dataValue instanceof Byte ) {
                            dataType = "byte";
                            dataValueCasted = ( ( Byte ) dataValue ).toString();
                        }
                        else {
                            // short
                            if( dataValue instanceof Short ) {
                                dataType = "short";
                                dataValueCasted = ( ( Short ) dataValue ).toString();
                            }
                            else {
                                // long
                                if( dataValue instanceof Long ) {
                                    dataType = "long";
                                    dataValueCasted = ( ( Long ) dataValue ).toString();
                                }
                                else {
                                    // double
                                    if( dataValue instanceof Double ) {
                                        dataType = "double";
                                        dataValueCasted = ( ( Double ) dataValue ).toString();
                                    }
                                    else {
                                        // char
                                        if( dataValue instanceof Character ) {
                                            dataType = "char";
                                            dataValueCasted = "\'" + ( ( Character ) dataValue ).toString() + "\'";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        sendLineToOutput( getBeginingOfLine() + EMPTY_STRING + typeOfDataInClass + ": (" + dataType + ") " + dataName + " = " + dataValueCasted );
    }

    /*------------------------------------------------------------------------
     *  EXCEPTIONS
     * -----------------------------------------------------------------------
     */
    /**
     * Writes exceptions to an specific log-file and to a debug-file (if runDebugger is true)
     * 
     * @param exceptionObj
     */
    public void exception( Object exceptionObj ) {
        StackTraceElement[] ste = null;
        String exceptionName = EMPTY_STRING;
        String exMsg = EMPTY_STRING;

        if( exceptionObj instanceof Exception ) {
            Exception castedException = ( Exception ) exceptionObj;
            exceptionName = castedException.getClass().getName();
            ste = castedException.getStackTrace();
            exMsg = castedException.getMessage();
        }

        System.out.println( "o---EXCEPTION---> " + exceptionName );
        sendLineToOutput( EXCEPTION_INDENTATION + "MESSAGE: " + exMsg );

        for( int e = 0 ; e < ste.length ; e++ ) {
            sendLineToOutput( EXCEPTION_INDENTATION + ( e + 1 ) + ". " + ste[e].getClassName() + "." + ste[e].getMethodName() + "() line:" + ste[e].getLineNumber() + " " );
        }
        
        System.out.println( "o---END-OF-EXCEPTION---o" );
    }

    /**
     * Writes exceptions to an specific log-file and to a debug-file (if runDebugger is true)
     * 
     * @param errorMessage
     */
    public void error( String errorMessage ) {
        sendLineToOutput( "o---APP-ERROR----> " + errorMessage );
    }

    public void info( String infoMessage ) {
        sendLineToOutput( "o---APP-INFO-----> " + infoMessage );
    }

    public void trace( String traceMessage ) {
        sendLineToOutput( "o---TRACE-INFO---> " + traceMessage );
    }    
    
    // output to console
    private void sendLineToOutput( String message ) {
        System.out.println( message );
    }

    @SuppressWarnings( "unused" )
	private void sendToOutput( String message ) {
        System.out.print( message );
    }

    /**
     * <p>
     * Return the <code>FacesContext</code> instance for the current request.
     */
    protected FacesContext context() {
        return ( FacesContext.getCurrentInstance() );
    }

    protected void addToSession( String key, Object value ) {
        context().getExternalContext().getSessionMap().put( key, value );
    }

    protected Object getFromSession( String key ) {
        Object sValue = context().getExternalContext().getSessionMap().get( key );

        return sValue;
    }

    protected void removeFromSession( String key ) {
        context().getExternalContext().getSessionMap().remove( key );
    }

    protected boolean existsInSession( String key ) {
        boolean exists = context().getExternalContext().getSessionMap().containsKey( key );

        return exists;
    }

    protected void clearSession() {
        context().getExternalContext().getSessionMap().clear();
    }
}
