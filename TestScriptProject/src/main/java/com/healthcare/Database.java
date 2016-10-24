package com.healthcare;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public ArrayList<String> getNameAndIdForUsername(String username) throws SQLException
    {
        ArrayList<String> out = new ArrayList<String>();        
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String name;
        Integer person_id;
        String insertPersonDataCall = "{call GET_NAME_ID_FOR_USERNAME(?, ?, ?)}";

        try {
            callableStatement = CONN.prepareCall(insertPersonDataCall);

            callableStatement.setString(1, username);
            
            // out Parameters
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);

            // execute getDBUSERByUserId store procedure
            callableStatement.execute();

            name = callableStatement.getString(2);
            person_id = callableStatement.getInt(3);  
            String pid_str = String.valueOf(person_id);
            out.add(name);
            out.add(pid_str);

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
        return out;
    }

    
    
    
    
    
    public ResultSet execute(String query) throws SQLException{
        Statement stmt = CONN.createStatement();
        stmt.executeQuery(query);
        ResultSet result = stmt.getResultSet();
        //stmt.close();
        return result;
    }
    
    
    
    LinkedList<HsInfo> getPatientsUnderYou(Integer person_id) throws SQLException{
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        LinkedList<HsInfo> result = new LinkedList<>();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "";
        String status = "";
        String GetUsersCall = "{call GET_PATIENTS_UNDER_HS(?, ?, ?,?)}";
        try 
        {
            callableStatement = CONN.prepareCall(GetUsersCall);
            callableStatement.setInt(1, person_id);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            ResultSet rset = (ResultSet)callableStatement.getObject(4);
            while(rset.next())
            {
                HsInfo temp = new HsInfo(rset.getString(2), rset.getString(1));
                result.add(temp);
            }
            message = callableStatement.getString(3);
            status = callableStatement.getString(4);

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
        return result;
    }

    public ArrayList<HsInfo> getHsInfo(Integer person_id) throws SQLException{
        ArrayList<HsInfo> arr = new ArrayList<>();
        
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String GetUsersCall = "{call GET_HS_FOR_USERNAME(?, ?)}";
        try 
        {
            callableStatement = CONN.prepareCall(GetUsersCall);
            callableStatement.setInt(1, person_id);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            ResultSet rset = (ResultSet)callableStatement.getObject(2);
            while(rset.next())
            {
                arr.add(new HsInfo(rset.getString(1), rset.getString(2)));
            }
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
        return arr;
    }
    
    Person showLimitedProfile(String username) throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String showProfile = "{call SHOW_PROFILE(?, ?, ?, ?)}";
        String name = "", dob = "", gender = "", email_id = "", phone = "";
        try {
            callableStatement = CONN.prepareCall(showProfile);
            callableStatement.setString(1, username);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            status = callableStatement.getString(2);
            message = callableStatement.getString(3);
            System.out.println("Status : " + status + "\nMessage : " + message);
            ResultSet rset = (ResultSet)callableStatement.getObject(4);
            if(rset.next()) {
                name = rset.getString(1);
                dob = rset.getString(2);
                gender = rset.getString(3);
                email_id = rset.getString(8);
                phone = rset.getString(9);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            if (callableStatement != null) {
                callableStatement.close();
            }

            if (dbConnection != null) {
                CONN.close();
            }
        }
        
        Person patient = new Person(username, name, dob, gender, email_id, phone);
        return patient;
    }

ArrayList<String> addObservation(String patientName, Observation observation) throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String callAddObservation = "{call ADD_OBSERVATION(?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?)}";
        
        try {
            callableStatement = CONN.prepareCall(callAddObservation);
            callableStatement.setString(1, patientName);
            callableStatement.setInt(2, observation.bpDiastolic);
            callableStatement.setInt(3, observation.bpSystolic);
            callableStatement.setFloat(4, observation.oxygenSat);
            callableStatement.setString(5, observation.painLevel);
            callableStatement.setString(6, observation.mood);
            callableStatement.setFloat(7, observation.temperature);
            callableStatement.setFloat(8, observation.weight);
            
            try{
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                java.util.Date utilDate = formatter.parse(observation.observedOn);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                callableStatement.setDate(9, sqlDate);
            } catch(Exception e){
                callableStatement.setDate(9, null);
                System.out.println("Error in Observed On parse =" + e.getMessage());
                e.printStackTrace();
            }


            try{
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                java.util.Date utilDate = formatter.parse(observation.recordedOn);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                callableStatement.setDate(10, sqlDate);
            } catch(Exception e){
                callableStatement.setDate(10, null);
                System.out.println("Error in Observed On parse =" + e.getMessage());
                e.printStackTrace();
            }
            
            callableStatement.registerOutParameter(11, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(12, java.sql.Types.VARCHAR);
            callableStatement.execute();
            status = callableStatement.getString(11);
            message = callableStatement.getString(12);
            System.out.println("Status : " + status + "\nMessage : " + message);
            
            result.add(status);
            result.add(message);

            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            if (callableStatement != null) {
                callableStatement.close();
            }

            if (dbConnection != null) {
                CONN.close();
            }
        }
        return result;
    }
    
    
    public void addRecommendation(Recommendation newReco, String username) throws SQLException{
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String callAddObservation = "{call ADD_RECOMMENDATION(?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?)}";
        
        try {
            callableStatement = CONN.prepareCall(callAddObservation);
            callableStatement.setString(1, username);
            callableStatement.setFloat(2, (float)newReco.bpDiastolicLow);
            callableStatement.setFloat(3, (float)newReco.bpDiastolicHigh);
            callableStatement.setFloat(4, (float)newReco.bpSystolicLow);
            callableStatement.setFloat(5, (float)newReco.bpSystolicHigh);
            callableStatement.setInt(6, newReco.bpFrequency);            
            callableStatement.setFloat(7, (float)newReco.oxySatLow);
            callableStatement.setFloat(8, (float)newReco.oxySatHigh);
            callableStatement.setInt(9, newReco.oxySatFrequency);
            callableStatement.setInt(10, newReco.painLevel);
            callableStatement.setInt(11, newReco.painLevelFrequency);
            callableStatement.setString(12, newReco.mood);
            callableStatement.setInt(13, newReco.moodFrequency);
            callableStatement.setFloat(14, (float)newReco.temperatureLow);
            callableStatement.setFloat(15, (float)newReco.temperatureHigh);
            callableStatement.setInt(16, newReco.tempertureFrequency);
            callableStatement.setFloat(17, (float)newReco.weightLow);
            callableStatement.setFloat(18, (float)newReco.weightHigh);
            callableStatement.setInt(19, newReco.weightFrequency);
            callableStatement.registerOutParameter(20, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(21, java.sql.Types.VARCHAR);
            callableStatement.execute();
            status = callableStatement.getString(10);
            message = callableStatement.getString(11);
            System.out.println("Status : " + status + "\nMessage : " + message);            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }

            if (dbConnection != null) {
                CONN.close();
            }
        }
    }

    void removeDiseaseForName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    ArrayList<ArrayList<Object>> getAlertsForUsername(String username) {
        //return null;
        ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
        
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String userDiseasesCall = "{call SHOW_ALERTS_FOR_USERNAME(?, ?, ?, ?)}";

        try {
            callableStatement = CONN.prepareCall(userDiseasesCall);            
            callableStatement.setString(1, username);            
            // out Parameters            
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);

            // execute getDBUSERByUserId store procedure
            callableStatement.execute();
            status = callableStatement.getNString(2);
            message = callableStatement.getNString(3);
            ResultSet rset = (ResultSet)callableStatement.getObject(4);
            while(rset.next())
            {
                ArrayList<Object> y = new ArrayList<Object>();
                y.add(rset.getString(1));
                y.add(rset.getTimestamp(2));
                y.add(rset.getString(3));
                result.add(y);
            }            
            return result;
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
        return result;        
    }

    ArrayList<ArrayList<Object>> getBestRecommendations(String username) {
        ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
        
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String userDiseasesCall = "{call GET_BEST_RECOMMENDATION(?, ?, ?, ?)}";

        try {
            callableStatement = CONN.prepareCall(userDiseasesCall);            
            callableStatement.setString(1, username);            
            // out Parameters            
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);

            // execute getDBUSERByUserId store procedure
            callableStatement.execute();
            status = callableStatement.getNString(3);
            message = callableStatement.getNString(4);
            // Handle Falied status here!
            if(status.equals("DEFAULT")){
                //Give him the normal person's reco
                ArrayList<Object> y = new ArrayList<Object>();
                y.add("NO PAIN");
                y.add(7);
                y.add("HAPPY");
                y.add(7);
                y.add(95.0);
                y.add(100.0);
                y.add(7);
                y.add(100.0);
                y.add(200.0);
                y.add(7);
                y.add(60);
                y.add(80);
                y.add(100);
                y.add(130);
                y.add(7);
                y.add(75.0);
                y.add(100.0);
                y.add(7);
                result.add(y);
                ArrayList<Object> statusMessage = new ArrayList<>();
                statusMessage.add(status);
                statusMessage.add(message);
                result.add(statusMessage);
                return result;
            }
            ResultSet rset = (ResultSet)callableStatement.getObject(2);
            while(rset.next())
            {
                ArrayList<Object> y = new ArrayList<Object>();
                y.add(rset.getString(1));
                y.add(rset.getInt(2));
                y.add(rset.getString(3));
                y.add(rset.getString(4));
                y.add(rset.getFloat(5));
                y.add(rset.getFloat(6));
                y.add(rset.getInt(7));
                y.add(rset.getFloat(8));
                y.add(rset.getFloat(9));
                y.add(rset.getInt(10));
                y.add(rset.getFloat(11));
                y.add(rset.getFloat(12));
                y.add(rset.getFloat(13));
                y.add(rset.getFloat(14));
                y.add(rset.getInt(15));
                y.add(rset.getFloat(16));
                y.add(rset.getFloat(17));
                y.add(rset.getInt(18));
                result.add(y);
            } 
            ArrayList<Object> statusMessage = new ArrayList<>();
            statusMessage.add(status);
            statusMessage.add(message);
            result.add(statusMessage);
            return result;
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
        ArrayList<Object> statusMessage = new ArrayList<>();
        statusMessage.add(status);
        statusMessage.add(message);
        result.add(statusMessage);
        return result;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    ArrayList<ArrayList<Object>> getAllObservations(String username) {
        ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
        
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String userDiseasesCall = "{call GET_OBSERVATIONS_FOR_USERNAME(?, ?, ?, ?)}";

        try {
            callableStatement = CONN.prepareCall(userDiseasesCall);            
            callableStatement.setString(1, username);            
            // out Parameters            
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);

            // execute getDBUSERByUserId store procedure
            callableStatement.execute();
            status = callableStatement.getNString(3);
            message = callableStatement.getNString(4);
            ResultSet rset = (ResultSet)callableStatement.getObject(2);
            while(rset.next())
            {
                ArrayList<Object> y = new ArrayList<Object>();
                y.add(rset.getInt(1));
                y.add(rset.getInt(2));
                y.add(rset.getString(3));
                y.add(rset.getFloat(4));
                y.add(rset.getString(5));
                y.add(rset.getFloat(6));
                y.add(rset.getFloat(7));
                y.add(rset.getDate(8));
                y.add(rset.getDate(9));
                result.add(y);
            }            
            return result;
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
        return result;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}