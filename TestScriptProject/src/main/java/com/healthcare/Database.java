package com.healthcare;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import oracle.jdbc.OracleTypes;


public class Database {

    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
    private static final String DB_USER = "hborah";
    private static final String DB_PASSWORD = "200105222";
    private static Connection CONN;
    static Database db = new Database();
    static Database getInstance()
    {
        return db;
    }    
    private Database()
    {
        
        try
        {   
            Class.forName(DB_DRIVER);
            CONN = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            System.out.println("Connection Successful");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    boolean inDatabase(String username, String password)
    {
        try
        {
            Statement stmt = CONN.createStatement();
            String query = String.format("SELECT * FROM PERSON WHERE username=%s AND password=%s", username, password);
            ResultSet rs = stmt.executeQuery(query);
            //TODO: SELECT QUERY CHECK
            
        }
        catch(Exception e)
        {
            
        }
        return true;
    }
    String[] getSupporters() throws SQLException
    {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("");
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "";
        String GetUsersCall = "{call GET_CURRENT_USERS(?, ?, ?)}";
        try 
        {
            callableStatement = CONN.prepareCall(GetUsersCall);
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            ResultSet rset = (ResultSet)callableStatement.getObject(3);
            while(rset.next())
            {
                arr.add(rset.getString(1)+ " - " + rset.getString(2));
            }
            message = callableStatement.getString(2);

        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } finally {

            if (callableStatement != null) {
                callableStatement.close();
            }

            if (dbConnection != null) {
                CONN.close();
            }
        }
        String[] ret = new String[arr.size()];
        for(int i=0;i<arr.size();i++)
        {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    String addPerson(HashMap<String, String> map) throws SQLException
    {
        
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "";
        String insertPersonDataCall = "{call INSERT_PERSON_DATA(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try {
            callableStatement = CONN.prepareCall(insertPersonDataCall);

            callableStatement.setString(1, map.get("name"));
            String[] date = map.get("dob").split("-");
            callableStatement.setDate(2, new Date(Integer.parseInt(date[2])%100,Integer.parseInt(date[1]),Integer.parseInt(date[0])));
            callableStatement.setString(3, map.get("gender"));
            callableStatement.setInt(4, Integer.parseInt(map.get("isSick")));
            callableStatement.setString(5, map.get("username"));
            callableStatement.setString(6, map.get("password"));
            callableStatement.setString(7, map.get("street"));
            callableStatement.setString(8, map.get("apt"));
            callableStatement.setString(9, map.get("city"));
            callableStatement.setString(10, map.get("country"));
            callableStatement.setInt(11, Integer.parseInt(map.get("zip")));
            callableStatement.setString(12, map.get("state"));
            callableStatement.setString(13, map.get("pmobile"));
            callableStatement.setString(14, map.get("smobile"));
            callableStatement.setString(15, map.get("email"));
            callableStatement.setString(16, map.get("ssn"));

            // out Parameters
            callableStatement.registerOutParameter(17, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(18, java.sql.Types.VARCHAR);

            // execute getDBUSERByUserId store procedure
            callableStatement.executeUpdate();

            String status = callableStatement.getString(17);
            message = callableStatement.getString(18);
            
            //System.out.println("Status is  : " + status);
            //System.out.println("Message is : " + message);

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (callableStatement != null) {
                callableStatement.close();
            }

            if (dbConnection != null) {
                CONN.close();
            }
        }
        //TODO: proc to insert person
        //String s = "INSERT INTO PERSON VALUES (4, '%s',TO_DATE ('%s', 'yyyy/mm/dd hh24:mi:ss'),'%s',%s,'%s','%s')";
        //String query = String.format(s, map.get("name"), map.get("dob"), map.get("gender"), map.get("isSick"), map.get("username"), map.get("password"));
         /*      
        try
        {
            Statement stmt = db.conn.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e)
        {
            
        }
        System.out.println(query);
        */
        return message;
    }
}
    /*
    public static void main(String[] args) {
//        try {
            
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
            });
            
            //System.exit(0);

            // Load the driver. This creates an instance of the driver
            // and calls the registerDriver method to make Oracle Thin
            // driver available to clients.
           /*

            Class.forName("oracle.jdbc.driver.OracleDriver");

            String user = "rshah5";	// For example, "jsmith"
            String passwd = "200105222";	// Your 9 digit student ID number


            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {

                // Get a connection from the first driver in the
                // DriverManager list that recognizes the URL jdbcURL

                conn = DriverManager.getConnection(jdbcURL, user, passwd);

                // Create a statement object that will be sending your
                // SQL statements to the DBMS

                stmt = conn.createStatement();

                // Create the COFFEES table

                stmt.executeUpdate("CREATE TABLE COFFEES1 " +
                        "(COF_NAME VARCHAR(32), SUP_ID INTEGER, " +
                        "PRICE FLOAT, SALES INTEGER, TOTAL INTEGER)");

                // Populate the COFFEES table

                stmt.executeUpdate("INSERT INTO COFFEES1 " +
                        "VALUES ('Colombian', 101, 7.99, 0, 0)");

                stmt.executeUpdate("INSERT INTO COFFEES1 " +
                        "VALUES ('French_Roast', 49, 8.99, 0, 0)");

                stmt.executeUpdate("INSERT INTO COFFEES1 " +
                        "VALUES ('Espresso', 150, 9.99, 0, 0)");

                stmt.executeUpdate("INSERT INTO COFFEES1 " +
                        "VALUES ('Colombian_Decaf', 101, 8.99, 0, 0)");

                stmt.executeUpdate("INSERT INTO COFFEES1 " +
                        "VALUES ('French_Roast_Decaf', 49, 9.99, 0, 0)");

                // Get data from the COFFEES table

                rs = stmt.executeQuery("SELECT COF_NAME, PRICE FROM COFFEES1");

                // Now rs contains the rows of coffees and prices from
                // the COFFEES table. To access the data, use the method
                // NEXT to access all rows in rs, one row at a time

                while (rs.next()) {
                    String s = rs.getString("COF_NAME");
                    float n = rs.getFloat("PRICE");
                    System.out.println(s + "   " + n);
                }

            } finally {
                close(rs);
                close(stmt);
                close(conn);
            }
        } catch(Throwable oops) {
            oops.printStackTrace();
        }
    }

    static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }
    }*/



