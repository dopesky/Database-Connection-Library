/*
 * Licence : 
 * This Software has no Copyrights attached to it.
 * You can Duplicate and Distribute it however you like as
 * it is not subject to any copyright infingment. 
 */
package Class;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//<editor-fold defaultstate="collapsed" desc="public DBUtils class">
/**
 * DBUtils class for retrieving data from a database. Can set the data in a
 * JTable or returns a string that can be set on a textArea or TextField.
 * Can also be used to create PreparedStatements and use them to execute
 * non-select statements like update,insert or delete statements. Basically
 * does database connection, access and retrieval of data from database.
 *
 * @version 1.0
 * @author Kevin Mwenda Kathendu
 */
public class DBUtils {

        /**
         * Establishes a connection to the database
         */
        private Connection conn = null;

        /**
         * Used to Query the database
         */
        private Statement stmt = null;

        /**
         * Used to store what is returned by a select query
         */
        private ResultSet set = null;
        
        /**
         * Used to send query statements to the database.
         */
        private PreparedStatement pst=null;

        /**
         * Use this variable to check if connection to database was successful
         */
        public final boolean isConnectionSuccessful;

        //<editor-fold defaultstate="collapsed" desc="Constructor">
        /**
         * The constructor for initialising the database URL, username and
         * password. Connects to the localhost database and initialises the
         * private Connection and Statement objects.
         * <pre><b>Warning : </b> The database being referred to has
         *           to be a MYSQL database saved on the localhost of your
         *           machine or any other machine.
         *           This class imports the mysql connector to its lib
         *           folder thus no need to import it on your project
         *           unless you want to do other sql statements that
         *           are not supported by this class or the attached
         *           connector isn't working anymore. Make sure that the
         *           jar file and the lib folder containing the mysql
         *           connector are in the same folder. After initialization,
         *           use isConnectionSuccessful variable to check if the
         *           connection to database was successful.
         *           A DatabaseURLSyntax exception will be thrown by this
         *           class if the syntax of the url is not valid.
         * </pre>
         *
         * @param UserName : The username used to access the database.
         * @param PassWord : Password to the username as saved on DB.
         * @param DB_URL : The url to the database in the form of
         * :<pre>        <font style="font-size:10px;font-family:arial black"><b>jdbc:mysql://localhost/nameOfTheDatabase</b></font></pre>
         */
        public DBUtils(String DB_URL, String UserName, String PassWord) {
                if (!DB_URL.startsWith("jdbc:mysql://")) {
                        throw new DatabaseURLSyntaxException();
                }
                isConnectionSuccessful = connectToDatabase(DB_URL, UserName, PassWord);
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="private connectToDatabase(String,String,String) method" >
        /**
         * This method connects to the database that is set by the user. It is
         * called by the constructor at initialisation of the class' object.
         *
         * @param url : DBurl from the constructor.
         * @param userName : userName set in the constructor.
         * @param password : password associated with the set userName.
         * @return true if the connection is made successfully, false otherwise.
         * <br>
         * <b>Note: </b>If an error occurs during runtime, a JOptionPane will
         * appear with a message about the error.
         */
        private boolean connectToDatabase(String url, String userName, String password) {
                try {
                        Class.forName("com.mysql.jdbc.Driver");
                        conn = DriverManager.getConnection(url, userName, password);
                        stmt = conn.createStatement();
                        return true;
                } catch (ClassNotFoundException cnfe) {
                        JOptionPane.showMessageDialog(null, "Cannot load Driver at this time.\nEither the jar file is broken or the sql connector is unavailable or deprecated.\nPlease check that the lib folder and jar file are in the same folder.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException sqle) {
                        JOptionPane.showMessageDialog(null, "Type  :  SQLException: \n" + sqle.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Type  :  " + e.getClass().getName() + ":  \n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                return false;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="public getResultSet(String) method">
        /**
         * This method executes a statement and returns a ResultSet containing
         * the data from the query.<br><br>
         * <b> Warning : </b> The sql has to be a select statement.
         *
         * @param sql : the select statement to be executed in the method
         * @return a ResultSet containing what is returned from the sql
         * statement, null if the connection to database was unsuccessful.
         * @throws SQLException if a database access error occurs, this method
         * is called on a closed Statement, the given SQL statement produces
         * anything other than a single ResultSet object, the method is called
         * on a PreparedStatement or CallableStatement.
         */
        public final ResultSet getResultSet(String sql) throws SQLException {
                if (isConnectionSuccessful) {
                        set = stmt.executeQuery(sql);
                }
                return set;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="public getColumnCount(ResultSet) method">
        /**
         * This method counts the number of columns in your ResultSet
         *
         * @param rset : The ResultSet object in question.
         * @return an integer representing the number of columns in the
         * ResultSet object.
         * @throws SQLException if a database access error occurs or this method
         * is called on a closed result set
         */
        public int getColumnCount(ResultSet rset) throws SQLException {
                return rset.getMetaData().getColumnCount();
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="public getRowCount(ResultSet) method">
        /**
         * This method counts the number of rows in your ResultSet
         *
         * @param rset : The ResultSet object in question.
         * @return an integer representing the number of rows in the ResultSet
         * object.
         * @throws SQLException if a database access error occurs or this method
         * is called on a closed result set
         */
        public int getRowCount(ResultSet rset) throws SQLException {
                int rows = 0;
                rset.beforeFirst();
                while (rset.next()) {
                        rows++;
                        if (rset.isAfterLast()) {
                                break;
                        }
                }
                rset.beforeFirst();
                return rows;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="public getColumnNames(ResultSet) method">
        /**
         * This method retrieves the names of the columns in the ResultSet. The
         * suggested titles are usually specified by the SQL AS clause. If a SQL
         * AS is not specified, the value returned from this method will be the
         * same as the value specified in the database itself.
         *
         * @param rset : The ResultSet in question.
         * @return a array of Strings containing the column names from the
         * ResultSet object in chronological order from the first column to the
         * last.
         * @throws SQLException : if a database error occurs during execution.
         */
        public String[] getColumnNames(ResultSet rset) throws SQLException {
                int temp = getColumnCount(rset);
                String[] data = new String[temp];
                for (int i = 0; i < temp; i++) {
                        data[i] = rset.getMetaData().getColumnLabel(i + 1);
                }
                return data;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="public getTableModel(ResultSet) method">
        /**
         * This method turns a ResultSet object into a TableModel object. The
         * TableModel object will have all its data as Strings. One can convert
         * to desired data type on retrieval.
         *
         * @param rset : The ResultSet object in question.
         * @return a TableModel object containing all the information in the
         * ResultSet. This TableModel will have column names as returned from
         * calling getColumnNames() method with this ResultSet. It will also
         * contain all rows from this ResultSet.
         * @throws SQLException : If an SQL error occurs at runtime or if any of
         * the methods called within this method throw an SQLException.
         */
        public TableModel getTableModel(ResultSet rset) throws SQLException {
                DefaultTableModel temp = new DefaultTableModel();
                String[] columnNames = getColumnNames(rset);
                String[] row = new String[columnNames.length];
                for (String columnName : columnNames) {
                        temp.addColumn(columnName);
                }
                while (rset.next()) {
                        for (int i = 0; i < columnNames.length; i++) {
                                row[i] = rset.getObject(i + 1).toString();
                        }
                        temp.addRow(row);
                }
                return temp;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="public setTableModel(ResultSet,JTable) method">
        /**
         * This method takes a ResultSet and a JTable and puts all the
         * information in the ResultSet in the JTable. This is by setting the
         * TableModel of the JTable to be one that contains all the information
         * from the ResultSet.<br>
         * <b>Note : </b>The JTable, unless otherwise specified by overriding
         * its methods during initialisation, will have resizable, movable
         * columns.Its cells will also be editable by default. If you intend on
         * using TableRowSorter or RowFilter, ensure you really understand the
         * workings of the Model View Controller (MVC) used by java JTable.
         *
         * @param rset : The ResultSet in question.
         * @param table : A user's table that is supposed to contain data from
         * the ResultSet.
         * @throws SQLException : If a database error occurs or if any methods
         * called within this method throw an exception.
         */
        public void setTableModel(ResultSet rset, JTable table) throws SQLException {
                table.setModel(getTableModel(rset));
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="public prepareStatement(String) method">
        /**
         * Prepares the PreparedStatement object of this class with the specified string.
         * @param stmt - The string used to prepare the PreparedStatement with. This 
         * can be an INSERT, DELETE, UPDATE or SELECT statement. Use the method
         * setObject(Object[]) of this class to fill the prepared statement with values.
         * If your PreparedStatement is ready for execution use the executePst() or the 
         * executeQueryPst() methods to execute your PreparedStatement depending 
         * on the type of SQL statement used in the PreparedStatement.
         * @throws SQLException if a database access error occurs or this method 
         * is called on a closed connection.
         */
        public void prepareStatement(String stmt) throws SQLException {
                pst=conn.prepareStatement(stmt);
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="public setObjects(Object[])">
        /**
         * This method should only be called after the prepareStatement(String)
         * method to fill the PreparedStatement with values.
         * @param value - The values to be put in the corresponding positions. The
         * first value will be put in the first position, second value in the second and so on.
         * @throws SQLException  if a database access error occurs or this method 
         * is called on a closed connection.
         */
        public void setObjects(Object[] value) throws SQLException{
                for(int i=0;i<value.length;i++){
                        pst.setObject(i+1,value[i]);
                }
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="public executePst() method">
        /**
         * This method executes the class PreparedStatement as
         * long as it does not return a ResultSet ie It does not contain
         * a SELECT statement.
         * @return true if the PreparedStatement was executed
         * successfully otherwise throws an exception.
         * @throws SQLException if a database access error occurs; 
         * this method is called on a closed PreparedStatement or the
         * SQL statement returns a ResultSet object
         */
        public boolean executePst() throws SQLException{
                pst.executeUpdate();
                return true;
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="public executeQueryPst() method">
        /**
         * Executes the SQL query in this class PreparedStatement object 
         * and returns the ResultSet object generated by the query.
         * @return a ResultSet object that contains results from the database.
         * The results should result from a SELECT statement.
         * @throws SQLException if a database access error occurs; 
         * this method is called on a closed PreparedStatement or the 
         * SQL statement does not return a ResultSet object.
         */
        public ResultSet executeQueryPst() throws SQLException{
                set=pst.executeQuery();
                return set;
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="public execute(String) method">
        /**
         * This method executes a sting in a Statement object.
         * The statement can be any SQL statement except a
         * SELECT statement.
         * @param sql - the statement to be executed.
         * @throws SQLException if a database access error 
         * occurs, this method is called on a closed Statement,
         * the given SQL statement produces a ResultSet object.
         */
        public void execute(String sql)throws SQLException{
                Statement st=conn.createStatement();
                st.executeUpdate(sql);
        }
        //</editor-fold>
}
//</editor-fold>
