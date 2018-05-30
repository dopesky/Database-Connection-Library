/*
 * Licence : 
 * This Software has no Copyrights attached to it.
 * You can Duplicate and Distribute it however you like as
 * it is not subject to any copyright infingment. 
 */
package Class;

//<editor-fold defaultstate="collapsed" desc="public ColumnNumberMatchException()">
/**
 * This class is to be used together with the
 * DBUtils class to track  errors in the number
 * of columns used as an array in the 
 * sortMultipleColumns(JTable,int[],int[]) method.
 * This exception class will be thrown if the number 
 * of columns in the first array exceeds the column 
 * count for the table or if the the number of columns
 * in the second array is not equal to the first array.
 *@version 1.0
 * @author Kevin
 */
public class ColumnNumberMatchException extends RuntimeException {
        /**The message to be displayed by a call to getMessage() method*/
        private String message="";
        
        //<editor-fold defaultstate="collapsed" desc="Constructor ()">
        /**
         * This constructor initialises a ColumnNumberMatchException object
         * with no arguments passed to its constructor.
         * Constructs a new runtime exception with null as its detail message.
         * The cause is not initialised, and may subsequently be 
         * initialised by a call to Throwable.initCause(java.lang.Throwable).
         */
        public ColumnNumberMatchException(){
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
        public ColumnNumberMatchException(String message){
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
        public ColumnNumberMatchException(Throwable cause){
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
        public ColumnNumberMatchException(String message,Throwable cause){
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
                return "ColumnNumberMatchException extends RuntimeException";
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
                        return "The column number for columns and sort type do not match";
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
                return "Check to ensure that the columns do not exceed the number of columns in the table or the two arrays passed are equal. That is the reason leading to this exception being thrown.";
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
                return "Class.ColumnNumberMatchException";
        }
        //</editor-fold>
        
}
//</editor-fold>