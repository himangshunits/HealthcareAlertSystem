package com.healthcare;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;


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
    public ResultSet getPerson(String username) 
    {
        try{
            Statement stmt = CONN.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Person p WHERE p.\"username\" = '"+username+"'");
            return rs;
        }
        catch(SQLException se)
        {
            System.out.println(se.getMessage());
        }
        return null;
    }
    ArrayList<String> toggleIsSick(String username) throws SQLException
    {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String toggleIsSickCall = "{call TOGGLE_IS_SICK(?, ?, ?)}";
        try {
            callableStatement = CONN.prepareCall(toggleIsSickCall);

            callableStatement.setString(1, username);
            // out Parameters
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);

            // execute getDBUSERByUserId store procedure
            callableStatement.executeUpdate();

            status = callableStatement.getString(2);
            message = callableStatement.getString(3);
            
            System.out.println(message);

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
        ArrayList<String> out = new ArrayList<String>();
        out.add(status);
        out.add(message);
        return out;
    }
    String[] getDiseases(){
        ArrayList<String> arr = new ArrayList<String>();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "";
        String GetDiseasesCall = "{call GET_ALL_DISEASES(?, ?, ?)}";
        try 
        {
            callableStatement = CONN.prepareCall(GetDiseasesCall);
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            ResultSet rset = (ResultSet)callableStatement.getObject(3);
            while(rset.next())
            {
                arr.add(rset.getString(1));
            }
            message = callableStatement.getString(2);

        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } finally {

            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (dbConnection != null) {
                try {
                    CONN.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String[] ret = new String[arr.size()];
        for(int i=0;i<arr.size();i++)
        {
            ret[i] = arr.get(i);
        }
        return ret;
        
    }
    ArrayList<String> addHealthSupporter(String username, String supporter, Date auth_date) throws SQLException
    {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String insertHealthSupporterCall = "{call ADD_HEALTH_SUPPORTER(?, ?, ?, ?, ?)}";
        try {
            callableStatement = CONN.prepareCall(insertHealthSupporterCall);

            callableStatement.setString(1, username);
            callableStatement.setString(2, supporter);
            callableStatement.setDate(3, auth_date);
            // out Parameters
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);

            // execute getDBUSERByUserId store procedure
            callableStatement.executeUpdate();

            status = callableStatement.getString(4);
            message = callableStatement.getString(5);

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
        ArrayList<String> out = new ArrayList<String>();
        out.add(status);
        out.add(message);
        return out;
    }
    ArrayList<String> inDatabase(String username, String password) throws SQLException
    {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "", category = "";
        String authenticate = "{call AUTHENTICATION(?, ?, ?, ?, ?)}";
        try 
        {
            callableStatement = CONN.prepareCall(authenticate);

            callableStatement.setString(1, username);
            callableStatement.setString(2, password);
            // out Parameters
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);

            // execute getDBUSERByUserId store procedure
            callableStatement.executeUpdate();

            status = callableStatement.getString(3);
            message = callableStatement.getString(4);
            category = callableStatement.getString(5);

        } 
        catch (SQLException e) {

            System.out.println(e.getMessage());

        } 
        finally {

            if (callableStatement != null) {
                callableStatement.close();
            }

            if (dbConnection != null) {
                CONN.close();
            }
        }
        ArrayList<String> out = new ArrayList<String>();
        out.add(status);
        out.add(message);
        out.add(category);
        return out;
    }
    HashMap<String, Object> getProfile(String username)
    {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String showProfileCall = "{call SHOW_PROFILE(?, ?, ?, ?)}";
        HashMap<String, Object> map = new HashMap<String, Object>();
        try 
        {
            callableStatement = CONN.prepareCall(showProfileCall);
            callableStatement.setString(1, username);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            ResultSet rset = (ResultSet)callableStatement.getObject(4);
            rset.next();
            ResultSetMetaData rsmd = rset.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            for(int i=1;i<=numberOfColumns;i++)
            {
                System.out.println(rsmd.getColumnName(i));
                System.out.println("--------------");
                System.out.println(rset.getObject(rsmd.getColumnName(i)));
                map.put(rsmd.getColumnName(i),rset.getObject(rsmd.getColumnName(i)));
            }
            status = callableStatement.getString(2);
            message = callableStatement.getString(3);            

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (dbConnection != null) {
                try {
                    CONN.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        
        map.put(status, message);
        return map;
    }
    String[] getSupporters()
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
                arr.add(rset.getString(1));
            }
            message = callableStatement.getString(2);

        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } finally {

            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (dbConnection != null) {
                try {
                    CONN.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String[] ret = new String[arr.size()];
        for(int i=0;i<arr.size();i++)
        {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    void removePerson(String username) throws SQLException
    {
        Statement stmt = CONN.createStatement();
        //TODO: Add remove Person proc
        stmt.executeUpdate("DELETE FROM PERSON p WHERE p.\"username\"='"+username+"'");
    }
    void removeHealthSupporter(String username, String supporter) throws SQLException
    {
        Statement stmt = CONN.createStatement();
        //TODO: Add remove Health Supporter proc
        int uid = Integer.parseInt(stmt.executeQuery("SELECT p.\"person_id\" FROM PERSON p WHERE p.\"username\"='"+username+"'").getString(1));
        System.out.println(uid);
        int sid = Integer.parseInt(stmt.executeQuery("SELECT p.\"person_id\" FROM PERSON p WHERE p.\"username\"='"+supporter+"'").getString(1));
        System.out.println(sid);
        String query = String.format("DELETE FROM TAKES_CARE t WHERE t.\"person_id\" = %d t.\"supporter_id\" = %d", uid, sid);
        stmt.executeUpdate(query);
        System.out.println("Success kiss");
    }
    ArrayList<String> addPerson(HashMap<String, String> map) throws SQLException
    {
        
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String insertPersonDataCall = "{call INSERT_PERSON_DATA(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try {
            callableStatement = CONN.prepareCall(insertPersonDataCall);

            callableStatement.setString(1, map.get("name"));
            String[] date = map.get("dob").split("-");
            String[] today  = map.get("date").split("-");
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
            callableStatement.setDate(17, new Date(Integer.parseInt(today[0])%100,Integer.parseInt(today[1]),Integer.parseInt(today[2])));
            if(map.get("supporter").equals(""))
            {
                callableStatement.setString(18, null);
            }
            else{
                callableStatement.setString(18, map.get("supporter1"));
            }
            if(map.get("supporter2").equals(""))
            {
                callableStatement.setString(19, null);
            }
            else
            {
                callableStatement.setString(19, map.get("supporter2"));
            }

            // out Parameters
            callableStatement.registerOutParameter(20, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(21, java.sql.Types.VARCHAR);

            // execute getDBUSERByUserId store procedure
            callableStatement.executeUpdate();

            status = callableStatement.getString(20);
            message = callableStatement.getString(21);            

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
        ArrayList<String> out = new ArrayList<String>();
        out.add(status);
        out.add(message);
        return out;
    }

    ArrayList<ArrayList<Object>> getDiseases(String username) {
        //return null;
        ArrayList<ArrayList<Object>> x = new ArrayList<ArrayList<Object>>();
        
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String userDiseasesCall = "{call SHOW_USER_DISEASES(?, ?, ?, ?)}";

        try {
            callableStatement = CONN.prepareCall(userDiseasesCall);

            
            callableStatement.setString(1, username);
            
            // out Parameters
            
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);

            // execute getDBUSERByUserId store procedure
            callableStatement.execute();
            ResultSet rset = (ResultSet)callableStatement.getObject(4);
            while(rset.next())
            {
                ArrayList<Object> y = new ArrayList<Object>();
                y.add(rset.getString(1));
                y.add(rset.getTimestamp(2));
                x.add(y);
            }
            
            return x;

        } catch (SQLException e) {
            
            System.out.println(e.getMessage());

        } finally {

            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (dbConnection != null) {
                try {
                    CONN.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        return x;
        
    }

    ArrayList<String> addDisease(String username, String disease) 
    {
        
        ArrayList<String> out = new ArrayList<String>();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String AddDiagnosisCall = "{call ADD_DIAGNOSIS(?, ?, ?, ?, ?)}";
        try 
        {
            
            java.util.Date d = new java.util.Date();
            Timestamp t = new java.sql.Timestamp(d.getTime());
            
            callableStatement = CONN.prepareCall(AddDiagnosisCall);
            callableStatement.setString(1, username);
            callableStatement.setString(2, disease);
            callableStatement.setTimestamp(3, t);
            
            
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
            callableStatement.executeUpdate();
            
            status = callableStatement.getString(4);
            message = callableStatement.getString(5);
            
            
            out.add(status);
            out.add(message);
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } finally {

            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (dbConnection != null) {
                try {
                    CONN.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return out;
    }
}