/*
 * Licence : 
 * This Software has no Copyrights attached to it.
 * You can Duplicate and Distribute it however you like as
 * it is not subject to any copyright infingment. 
 */
package Class;

//<editor-fold defaultstate="collapsed" desc="public DatabaseURLSyntaxException() class">
/**
 * This class is to be used together with the
 * DBUtils class to track  errors in the syntax
 * used for the DatabaseURL given as an argument
 * in its constructor.
 *@version 1.0
 * @author Kevin
 */
public class DatabaseURLSyntaxException extends RuntimeException{
        /**The message to be displayed by a call to getMessage() method*/
        private String message="";
        
        //<editor-fold defaultstate="collapsed" desc="Constructor ()">
        /**
         * This constructor initialises a DatabaseURLSyntaxException object
         * with no arguments passed to its constructor.
         * Constructs a new runtime exception with null as its detail message.
         * The cause is not initialised, and may subsequently be 
         * initialised by a call to Throwable.initCause(java.lang.Throwable).
         */
        public DatabaseURLSyntaxException(){
                super();
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Constructor (String)">
        /**
         * Constructs a new runtime exception with the specified detail message. 
         * The cause is not initialised, and may subsequently be initialised by a 
         * call to Throwable.initCause(java.lang.Throwable).
         * @param message the detail message. The detail message is saved
         * for later retrieval by the Throwable.getMessage() method.
         */
        public DatabaseURLSyntaxException(String message){
                this.message=message;
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Constructor (Throwable)">
        /**
         * Constructs a new runtime exception with the specified cause and a 
         * detail message of (cause==null ? null : cause.toString()) 
         * (which typically contains the class and detail message of cause). 
         * This constructor is useful for runtime exceptions that are little more 
         * than wrappers for other throwables.
         * @param cause :  the cause (which is saved for later retrieval by the
         * Throwable.getCause() method). (A null value is permitted, and indicates
         * that the cause is nonexistent or unknown.)
         */
        public DatabaseURLSyntaxException(Throwable cause){
                super(cause);
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Constructor (String,Throwable)">
        /**
         * Constructs a new runtime exception with the specified detail message 
         * and cause.
         * <b>Note : </b> that the detail message associated with cause is  
         *         automatically incorporated in this runtime exception's detail message.
         * @param message : the detail message (which is saved for later retrieval 
         * by the Throwable.getMessage() method).
         * @param cause :  the cause (which is saved for later retrieval by the 
         * Throwable.getCause() method). (A null value is permitted, and indicates 
         * that the cause is nonexistent or unknown.)
         */
        public DatabaseURLSyntaxException(String message,Throwable cause){
                super(cause);
                this.message=message;
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="public getExceptionType() method">
        /**
         * This class is used to get the exception type only.
         * @return a String containing this class and the class it extends.
         * This indicates the type of exception.
         */
        public String getExceptionType(){
                return "DatabaseURLSyntaxException extends RuntimeException";
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="overridden getMessage() method">
        /**
         * Returns the detail message string of this Throwable.
         * The detail message may be set or a default message
         * will be returned.
         * @return the detail message string of this Throwable 
         * instance (which cannot be null).
         */
        @Override
        public String getMessage(){
                if(message.equals("")){
                        return "The given database URL is not supported by this class";
                }else{
                        return message;
                }
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="overridden getLocalizedMessage() method">
        /**
         * Creates a localised description of this Throwable. Subclasses may 
         * override this method in order to produce a locale-specific message.
         * For subclasses that do not override this method, the default 
         * implementation returns a default message that gives more detail
         * of the exception and what to do.
         * @return The localised description of this Throwable.
         */
        @Override
        public String getLocalizedMessage(){
                return "The database URL given is either not a MYSQL server database URL or simply has been written in the wrong syntax. Ensure it is written in the form of \"jdbc:mysql://localhost/databaseNameHere\" replacing the \"databaseNameHere\" with the name of your database.";
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="overridden toString() method">
        /**
         * Returns a short description of this Throwable. The result is 
         * the concatenation of:
         * the name of the class of this object ": " (a colon and a 
         * space).
         * <b>Overrides: </b>toString in class Object.
         * @return a string representation of this Throwable.
         */
        @Override
        public String toString(){
                return "Class.DatabaseURLSyntaxException";
        }
        //</editor-fold>
        
}
//</editor-fold>