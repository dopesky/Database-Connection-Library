/*
 * Licence : 
 * This Software has no Copyrights attached to it.
 * You can Duplicate and Distribute it however you like as
 * it is not subject to any copyright infingment. 
 */
package Class;

import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

//<editor-fold defaultstate="collapsed" desc="public TableUtils class">
/**
 *This class is used to manipulate a table and its contents to
 * include sorters, filters and to update table contents incase
 * of a change in the view.
 * @version 1.0
 * @author Kevin
 */
public class TableUtils {
        
        //<editor-fold defaultstate="collapsed" desc="public setRowSorter(JTable) method">
        /**
         * This method sets a row sorter to the table such that each time a
         * column header is clicked, rows are sorted according to that column.
         * Clicking a table header once sorts in ascending order while clicking
         * twice sorts in descending order.
         *
         * @param table : The JTable in question.
         */
        public void setRowSorter(JTable table) {
                int columns = table.getModel().getColumnCount();
                TableRowSorter<TableModel> temp = new TableRowSorter<>(table.getModel());
                for (int i = 0; i < columns; i++) {
                        try {
                                int check = Integer.parseInt(table.getModel().getValueAt(0, i).toString());
                                temp.setComparator(i, (Object o1, Object o2) -> {
                                        if (Integer.parseInt(o1.toString()) > Integer.parseInt(o2.toString())) {
                                                return 1;
                                        } else if (Integer.parseInt(o1.toString()) < Integer.parseInt(o2.toString())) {
                                                return -1;
                                        }
                                        return 0;
                                });
                        } catch (NumberFormatException nfe) {
                                try{
                                        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                                        java.util.Date date=dateFormat.parse(table.getModel().getValueAt(0, i).toString());
                                        temp.setComparator(i, (Object o1, Object o2) -> {
                                                try {
                                                        SimpleDateFormat dateformat=new SimpleDateFormat("dd/MM/yyyy");
                                                        java.util.Date d1=dateformat.parse(o1.toString());
                                                        java.util.Date d2=dateformat.parse(o2.toString());
                                                        return d1.compareTo(d2);
                                                } catch (ParseException ex) {
                                                        return 0;
                                                }
                                        });
                                }catch(ParseException pe){
                                        temp.setComparator(i, (Object o1, Object o2) -> o1.toString().compareTo(o2.toString()));
                                }
                        }
                }
                table.setRowSorter(temp);
                table.getTableHeader().setEnabled(true);
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="public sortMultipleColumns(JTable,int[]) method">
        /**
         * This method enables one to sort a JTable by multiple columns. This is
         * by setting sort keys for the JTable's columns such that if records in
         * a column are equal, they are sorted by the next column in the given
         * array of column indexes and so on.<br>
         * NOTE : This method should normally be called after an array of
         * sorting columns is gotten from the user. This method sorts all rows
         * in the specified columns in ascending order. To sort in various
         * orders use sortMultipleColumns(JTable,int[],int[]). The table header
         * is disabled after this method is called so that users only have one
         * way of sorting columns and that is by specifying the sorting columns
         * and passing them to this method. If you turn the table header to
         * enabled, sorting can and will give undefined behaviour if user uses
         * column header to sort after invoking this method. This means that
         * columns will be immovable after invoking this method.
         *
         * @param table : The JTable in question.
         * @param num : The columns to be sorted in the order in which they are
         * to be sorted. Be careful with columns containing integer values. Make sure
         * you have specified Comparators for the columns in the table.
         */
        public void sortMultipleColumns(JTable table, int[] num) {
                if (table.getColumnCount() < num.length) {
                        throw new ColumnNumberMatchException("Column count in the table exceeds the number of columns brought foward in the array of integers.");
                }
                TableRowSorter temp = (TableRowSorter) table.getRowSorter();
                if (temp == null) {
                        temp = new TableRowSorter<>(table.getModel());
                }
                List sortList = new ArrayList();
                for (int i = 0; i < num.length; i++) {
                        RowSorter.SortKey sortKey = new RowSorter.SortKey(num[i], SortOrder.ASCENDING);
                        sortList.add(sortKey);
                }
                temp.setSortKeys(sortList);
                table.setRowSorter(temp);
                table.getTableHeader().setEnabled(false);
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="public sortMultipleColumns(JTable, int[], int[]) method">
        /**
         * This method enables one to sort a JTable by multiple columns. This is
         * by setting sort keys for the JTable's columns such that if records in
         * a column are equal, they are sorted by the next column in the given
         * array of column indexes in model and so on.<br>
         * NOTE : This method should normally be called after an array of
         * sorting columns is gotten from the user. This method sorts all rows
         * in the specified columns in the order specified by the sorting order
         * array given as the second array in the method. The table header
         * is disabled after this method is called so that users only have one
         * way of sorting columns and that is by specifying the sorting columns
         * and passing them to this method. If you turn the table header to
         * enabled, sorting can and will give undefined behaviour if user uses
         * column header to sort after invoking this method.
         * @param table - The table in question.
         * @param columns - The array of columns to be sorted in the order 
         * in which they are to be sorted. Column indexes refer to columns in 
         * model and not view.
         * @param sortOrder - An array specifying how to sort each of the columns
         * in the columns array. The first order will be used to sort the first
         * column in the columns array and so on.
         */
        public void sortMultipleColumns(JTable table, int[] columns, int[] sortOrder) {
                if (table.getColumnCount() < columns.length) {
                        throw new ColumnNumberMatchException("Column count in the table exceeds the number of columns brought foward in the first array of integers.");
                }
                if (columns.length != sortOrder.length) {
                        throw new ColumnNumberMatchException("Size of arrays doesn't match");
                }
                TableRowSorter temp = (TableRowSorter) table.getRowSorter();
                if (temp == null) {
                        temp = new TableRowSorter<>(table.getModel());
                }
                List sortList = new ArrayList();
                for (int i = 0; i < columns.length; i++) {
                        if (sortOrder[i] > 0) {
                                RowSorter.SortKey sortKey = new RowSorter.SortKey(columns[i], SortOrder.ASCENDING);
                                sortList.add(sortKey);
                        } else if (sortOrder[i] < 0) {
                                RowSorter.SortKey sortKey = new RowSorter.SortKey(columns[i], SortOrder.DESCENDING);
                                sortList.add(sortKey);
                        } else {
                                RowSorter.SortKey sortKey = new RowSorter.SortKey(columns[i], SortOrder.UNSORTED);
                                sortList.add(sortKey);
                        }
                }
                temp.setSortKeys(sortList);
                table.setRowSorter(temp);
                table.getTableHeader().setEnabled(false);
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="public setFilter(JTable,String) method">
        /**
         * This method gets a string and a JTable object and looks for
         * the string in all columns of the JTable and displays only the 
         * rows containing the specified string in one of their columns.
         * If the columns do not contain the string, the table becomes 
         * empty in the view.
         * @param table - The table in question.
         * @param filter - The word to look for in the table.
         */
        public void setFilter(JTable table, String filter){
                TableRowSorter dtm=(TableRowSorter)table.getRowSorter();
                if(dtm==null){
                        dtm=new TableRowSorter<>(table.getModel());
                }
                RowFilter<TableModel,Object> Filter=new RowFilter<TableModel,Object>() {
                        @Override
                        public boolean include(RowFilter.Entry entry) {
                                for(int i=0;i<table.getColumnCount();i++){
                                        if(entry.getValue(i).toString().toLowerCase().contains(filter.toLowerCase())){
                                                return true;
                                        }
                                }
                                return false;
                        }
                };
                dtm.setRowFilter(Filter);
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="public setFilter(JTable,String,int) method">
        /**
         * This method gets a string and a JTable object and looks for
         * the string in the given column of the JTable and displays only  
         * the rows containing the specified string in the specified column.
         * If the column does not contain the string, the table becomes 
         * empty in the view.
         * @param table - The JTable in question.
         * @param filter - The string to look for in the table.
         * @param column - The column in which to search the
         * string. This column index represents the column index in
         * the model and not the view.
         */
        public void setFilter(JTable table,String filter,int column){
                TableRowSorter dtm=(TableRowSorter)table.getRowSorter();
                if(dtm==null){
                        dtm=new TableRowSorter<>(table.getModel());
                }
                RowFilter<TableModel,Object> Filter=new RowFilter<TableModel,Object>() {
                        @Override
                        public boolean include(RowFilter.Entry entry) {
                                return (entry.getValue(column).toString().toLowerCase().contains(filter.toLowerCase()));
                        }
                };
                dtm.setRowFilter(Filter);
        }
        //</editor-fold>
        
}
//</editor-fold>