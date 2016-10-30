package com.healthcare;

import java.sql.*;
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
        catch(ClassNotFoundException | SQLException e)
        {

            System.out.println("Issue in connection = " + e.getMessage());
            e.printStackTrace();
        }
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
    HashMap<String, Integer> getDiseases(){
        HashMap<String, Integer> arr = new HashMap<>();
        arr.put("", 0);
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
                arr.put(rset.getString(2),rset.getInt(1));
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
        
        return arr;
        
    }
    ArrayList<String> addHealthSupporter(String username, String supporter, java.util.Date auth_date) throws SQLException
    {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        
        String insertHealthSupporterCall = "{call ADD_HEALTH_SUPPORTER(?, ?, ?, ?, ?)}";
        try {
            callableStatement = CONN.prepareCall(insertHealthSupporterCall);

            callableStatement.setString(1, username);
            callableStatement.setString(2, supporter);
            callableStatement.setDate(3, (Date)auth_date);
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
    Person getProfile(String username)
    {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String showProfileCall = "{call SHOW_PROFILE(?, ?, ?, ?)}";
        Person person = null;
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
            person = new Person(rset);
            person.username = username;
        } 
        catch (SQLException e) 
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
        return person;
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
    
    ArrayList<String> addPerson(HashMap<String, String> map) throws SQLException
    {
        
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String insertPersonDataCall = "{call INSERT_PERSON_DATA(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try {
            callableStatement = CONN.prepareCall(insertPersonDataCall);

            callableStatement.setString(1, map.get("name"));
            //String[] date = map.get("dob").split("-");
            //String[] today  = map.get("date").split("-");
            //String[] auth_date_1 = map.get("auth_date_1").split("-");
            //String[] auth_date_2 = map.get("auth_date_1").split("-");
            callableStatement.setDate(2, DateFormatManager.getSqlDateFromString(map.get("dob"), "yyyy/MM/dd"));
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
            callableStatement.setDate(17, DateFormatManager.getSqlDateFromString(map.get("date"),"yyyy/MM/dd"));
            callableStatement.setString(18, map.get("supporter"));
            callableStatement.setString(19, map.get("supporter2"));
            callableStatement.setInt(20, Integer.parseInt(map.get("disease_id")));
            callableStatement.setDate(21, DateFormatManager.getSqlDateFromString(map.get("auth_date_1"), "yyyy/MM/dd"));
            callableStatement.setDate(22, DateFormatManager.getSqlDateFromString(map.get("auth_date_2"), "yyyy/MM/dd"));
            // out Parameters
            callableStatement.registerOutParameter(23, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(24, java.sql.Types.VARCHAR);

            // execute getDBUSERByUserId store procedure
            callableStatement.executeUpdate();

            status = callableStatement.getString(23);
            message = callableStatement.getString(24);            

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

    ArrayList<String> addDisease(String username, String disease_id) 
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
            callableStatement.setInt(2, Integer.parseInt(disease_id));
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
                arr.add(new HsInfo(rset.getString(1), rset.getString(2), rset.getDate(3)));
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
        String name = "", gender = "", email_id = "", phone = "";
        Date dob = null;
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
                dob = rset.getDate(2);
                gender = rset.getString(3);
                email_id = rset.getString(14);
                phone = rset.getString(12);
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

    ArrayList<String> addObservation(String username, ObservationNew observation) throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String callAddObservation = "{call ADD_OBS_GENERIC(?, ?, ?, ?, ? ,?, ?, ?)}";
        
        try {
            callableStatement = CONN.prepareCall(callAddObservation);
            callableStatement.setString(1, username);
            callableStatement.setString(2, observation.type);
            callableStatement.setString(3, observation.value1);
            callableStatement.setString(4, observation.value2);
            callableStatement.setDate(5, (Date)observation.observed_on);
            callableStatement.setDate(6, (Date)observation.recorded_on);
            callableStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
            callableStatement.execute();
            status = callableStatement.getString(7);
            message = callableStatement.getString(8);
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
            callableStatement.setFloat(2, newReco.bpDiastolicLow);
            callableStatement.setFloat(3, newReco.bpDiastolicHigh);
            callableStatement.setFloat(4, newReco.bpSystolicLow);
            callableStatement.setFloat(5, newReco.bpSystolicHigh);
            callableStatement.setInt(6, newReco.bpFrequency);            
            callableStatement.setFloat(7, newReco.oxySatLow);
            callableStatement.setFloat(8, newReco.oxySatHigh);
            callableStatement.setInt(9, newReco.oxySatFrequency);
            callableStatement.setString(10, newReco.painLevel);
            callableStatement.setInt(11, newReco.painLevelFrequency);
            callableStatement.setString(12, newReco.mood);
            callableStatement.setInt(13, newReco.moodFrequency);
            callableStatement.setFloat(14, newReco.temperatureLow);
            callableStatement.setFloat(15, newReco.temperatureHigh);
            callableStatement.setInt(16, newReco.temperatureFrequency);
            callableStatement.setFloat(17, newReco.weightLow);
            callableStatement.setFloat(18, newReco.weightHigh);
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
                y.add(rset.getString(1));//sent alert id
                y.add(rset.getInt(2));//is_seen
                y.add(rset.getString(4));// alert_text
                y.add(rset.getTimestamp(5));//sent on
                
                y.add(rset.getString(6));//severity
                y.add(rset.getString(7));//reason for alert
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
        Integer weightFreq, bpFreq, osFreq, pFreq, mFreq, tFreq;
        Integer bpDiastolic, bpSystolic, painLevel;
        Float weight, oxySat, temperature;
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
                y.add("NO PAIN"); // pain_level
                y.add(7); // pain_level_freq
                y.add("HAPPY"); // mood
                y.add(7); // mood_freq
                y.add(new Float(95.0f)); // temp_low
                y.add(new Float(100.0f)); // temp_high
                y.add(7); // temp_freq
                y.add(new Float(100.0f)); // weight_low
                y.add(new Float(200.0f)); // weight_high
                y.add(7);
                y.add(60);
                y.add(80);
                y.add(100);
                y.add(130);
                y.add(7);
                y.add(new Float(75.0f));
                y.add(new Float(100.0f));
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
                y.add(rset.getString(1)); // pain_level
                y.add(rset.getInt(2)); // pain_level_freq
                y.add(rset.getString(3)); // mood
                y.add(rset.getInt(4)); // mood_freq
                y.add(rset.getFloat(5)); // temperature_low
                y.add(rset.getFloat(6)); // temperature_high
                y.add(rset.getInt(7)); // temperature_freq
                y.add(rset.getFloat(8)); //weight_low
                y.add(rset.getFloat(9)); // weight_high
                y.add(rset.getInt(10)); // weight_freq
                y.add(rset.getInt(11)); // bp_diastolic_low
                y.add(rset.getInt(12)); // bp_d.._high
                y.add(rset.getInt(13)); // bp_s.._low
                y.add(rset.getInt(14)); // bp_s.._high
                y.add(rset.getInt(15)); // bp_freq
                y.add(rset.getFloat(16)); // oxy_low
                y.add(rset.getFloat(17)); // oxy_high
                y.add(rset.getInt(18)); // oxy_freq
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
    }
    
    ArrayList<ArrayList<Object>> getAllObservations(String username) {
        ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
        
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String userDiseasesCall = "{call GET_OBSERVATIONS_FOR_USERNAME(?, ?, ?, ?)}";
        Observation ob = null;
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
                y.add(rset.getInt(3));
                y.add(rset.getString(4));
                y.add(rset.getFloat(5));
                y.add(rset.getString(6));
                y.add(rset.getFloat(7));
                y.add(rset.getFloat(8));
                y.add(rset.getDate(9));
                y.add(rset.getDate(10));
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
    Observation getLatestObservation(String username)
    {
        return null;
    }
    /*
    Observation getLatestObservation(String username) {
        Observation ob = null;
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String userDiseasesCall = "{call RETRIEVE_LAST_OBSERVATION(?, ?, ?, ?)}";
        
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
            if(rset.next()) {
                ob = new Observation(rset.getFloat("weight"),
                rset.getInt("bp_diastolic"),
                rset.getInt("bp_systolic"),
                rset.getFloat("oxygen_saturation"),
                rset.getString("pain_level"),
                rset.getString("mood"),
                rset.getFloat("temperature"),
                rset.getDate("observed_on").toString(),
                rset.getDate("recorded_on").toString()); 
            }            
            return ob;
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
        return ob;
    }
    */
    ArrayList<String> deleteDisease(String username, Integer diseaseId) {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String userDiseasesCall = "{call REMOVE_DISEASE_FOR_PATIENT(?, ?, ?, ?)}";

        try {
            callableStatement = CONN.prepareCall(userDiseasesCall);            
            callableStatement.setString(1, username);            
            callableStatement.setInt(2, diseaseId);
            // out Parameters            
            
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);

            // execute getDBUSERByUserId store procedure
            callableStatement.execute();

            status = callableStatement.getString(3);
            message = callableStatement.getString(4);
            
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
        ArrayList<String> out = new ArrayList<String>();
        out.add(status);
        out.add(message);
        return out;

    }
    
    
    ArrayList<String> deleteObservation(Integer observationId) {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String userDiseasesCall = "{call DELETE_OBSERVATION(?, ?, ?)}";

        try {
            callableStatement = CONN.prepareCall(userDiseasesCall);            
            callableStatement.setInt(1, observationId);            
            // out Parameters            
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);

            // execute getDBUSERByUserId store procedure
            callableStatement.execute();
            status = callableStatement.getString(2);
            message = callableStatement.getString(3);            
        } catch (SQLException e) {            
            System.out.println("Error is Delete Observation ::" + e.getMessage());
        } finally {
            if (callableStatement != null) {                try {
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
        ArrayList<String> out = new ArrayList<String>();
        out.add(status);
        out.add(message);
        return out;
    }
    
    
    ArrayList<String> deleteRecommendation(String username) {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String userDiseasesCall = "{call DELETE_RECOMMENDATION(?, ?, ?)}";

        try {
            callableStatement = CONN.prepareCall(userDiseasesCall);            
            callableStatement.setString(1, username);          
            // out Parameters
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            // execute getDBUSERByUserId store procedure
            callableStatement.execute();
            status = callableStatement.getString(2);
            message = callableStatement.getString(3);            
        } catch (SQLException e) {            
            System.out.println("Error is Delete Recommendation ::" + e.getMessage());
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
        ArrayList<String> out = new ArrayList<String>();
        out.add(status);
        out.add(message);
        return out;
    } 
    
    
    public void createAlert(String username, Integer alertId, String alertReason) throws SQLException{
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String callAddObservation = "{call CREATE_ALERT(?, ?, ?, ?, ?, ?, ?, ?)}";
        
        try {
            callableStatement = CONN.prepareCall(callAddObservation);
            callableStatement.setString(1, username);
            callableStatement.setInt(2, alertId);
            callableStatement.setDate(3, DateFormatManager.getSqlDateFromJavaDate(new java.util.Date()));
            callableStatement.setInt(4, 1);
            callableStatement.setInt(5, 0);
            callableStatement.setString(6, alertReason);
            callableStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
            callableStatement.execute();
            status = callableStatement.getString(7);
            message = callableStatement.getString(8);
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

    ArrayList<String> updateProfile(Person person) {
        ArrayList<String> result = new ArrayList<String>();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        System.out.println(person.toString());
        String updateProfileCall = "{call UPDATE_PROFILE(?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try {
            callableStatement = CONN.prepareCall(updateProfileCall);
            callableStatement.setString (1,  person.name);
            callableStatement.setDate   (2,  person.dob);
            callableStatement.setString (3,  person.gender);
            callableStatement.setString (4,  person.username);
            callableStatement.setString (5,  person.address1);
            callableStatement.setString (6,  person.address2);
            callableStatement.setString (7,  person.city);
            callableStatement.setString (8,  person.country);
            callableStatement.setString (9,  person.zipcode);
            callableStatement.setString (10, person.state);
            callableStatement.setString (11, person.phone1);
            callableStatement.setString (12, person.phone2);
            callableStatement.setString (13, person.email_id);
            callableStatement.setString (14, person.ssn);
                     
            callableStatement.registerOutParameter(15, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(16, java.sql.Types.VARCHAR);
            callableStatement.execute();
            status = callableStatement.getString(15);
            message = callableStatement.getString(16);
            System.out.println("Status : " + status + "\nMessage : " + message);
            
            result.add(status);
            result.add(message);

            
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
        return result;
    }

    void closeConn() {
        try {
                CONN.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


    ArrayList<String> updateSupporter(String patient, String username, String new_username, java.util.Date auth_date) 
    {
        ArrayList<String> result = new ArrayList<String>();

        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String callUpdateHS = "{call UPDATE_HS(?, ?, ?, ?, ?, ?)}";
        
        try {
            callableStatement = CONN.prepareCall(callUpdateHS);
            callableStatement.setString(1, patient);
            callableStatement.setString(2, username);
            callableStatement.setString(3, new_username);
            callableStatement.setDate(4, DateFormatManager.getSqlDateFromJavaDate(auth_date));
            callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
            callableStatement.execute();
            status = callableStatement.getString(5);
            message = callableStatement.getString(6);
            result.add(status); 
            result.add(message);
        } catch(SQLException e) {
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
    
    
    
    
    
    ArrayList<String> markAlertAsSeen(Integer sentAlertId) 
    {
        ArrayList<String> result = new ArrayList<String>();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String markCall = "{call MARK_AS_SEEN(?, ?, ?)}";
        try {
            callableStatement = CONN.prepareCall(markCall);
            callableStatement.setInt(1, sentAlertId);

            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.executeUpdate();
            status = callableStatement.getString(2);
            message = callableStatement.getString(3);
            result.add(status);
            result.add(message);
            System.out.println("Status : " + status + "\nMessage : " + message);            
        } catch(SQLException e) {
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
    
    
    ArrayList<String> deactivateAlert(Integer sentAlertId) 
    {
        ArrayList<String> result = new ArrayList<String>();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String deactivateCall = "{call DEACTIVATE_ALERT(?, ?, ?)}";
        
        try {
            callableStatement = CONN.prepareCall(deactivateCall);
            callableStatement.setInt(1, sentAlertId);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.executeUpdate();
            status = callableStatement.getString(2);
            message = callableStatement.getString(3);
            result.add(status);
            result.add(message);
            System.out.println("Status : " + status + "\nMessage : " + message);            
            
        } catch(SQLException e) {
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

    ArrayList<String> deleteHS(String username, String supporter) {
        ArrayList<String> result = new ArrayList<String>();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String removeHSCall = "{call REMOVE_HS(?, ?, ?, ?)}";
        
        try {
            callableStatement = CONN.prepareCall(removeHSCall);
            callableStatement.setString(1, username);
            callableStatement.setString(2, supporter);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.executeUpdate();
            status = callableStatement.getString(3);
            message = callableStatement.getString(4);
            result.add(status);
            result.add(message);
            System.out.println("Status : " + status + "\nMessage : " + message);            
            
        } catch(SQLException e) {
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
    
    
    public ObservationNew getObservedWeight (String username) {
        ObservationNew obs = new ObservationNew();        
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String GetObservedWeightCall = "{call GET_WEIGHT_OBS_FOR_USERNAME(?, ?, ?, ?)}";
        try 
        {
            callableStatement = CONN.prepareCall(GetObservedWeightCall);
            callableStatement.setString(1, username);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.execute();
            ResultSet rset = (ResultSet)callableStatement.getObject(2);
            
            if (rset.next()) {
                obs.value1 = new Float(rset.getFloat("weight_val")).toString() ;
                obs.observed_on = rset.getDate("observed_on");
            }
            status = callableStatement.getString(3);
            message = callableStatement.getString(4);
            System.out.println("Status : " + status + "\nMessage : " + message);            

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
        
        return obs;
    }
    
    public ObservationNew getObservedBloodPressure (String username) {
        ObservationNew obs = new ObservationNew();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String GetObservedBPCall = "{call GET_BP_OBS_FOR_USERNAME(?, ?, ?, ?)}";
        try 
        {
            callableStatement = CONN.prepareCall(GetObservedBPCall);
            callableStatement.setString(1, username);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.execute();
            ResultSet rset = (ResultSet)callableStatement.getObject(2);

            if (rset.next()) {
                obs.value1 = new Integer(rset.getInt("bp_diastolic")).toString();
                obs.value2 = new Integer(rset.getInt("bp_systolic")).toString();
                obs.observed_on = rset.getDate("observed_on");
            }
            status = callableStatement.getString(3);
            message = callableStatement.getString(4);
            System.out.println("Status : " + status + "\nMessage : " + message);            

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
        
        return obs;
    }
    
    public ObservationNew getObservedOxySat (String username) {
        ObservationNew obs = new ObservationNew();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String GetOxySatCall = "{call GET_OXY_OBS_FOR_USERNAME(?, ?, ?, ?)}";
        try 
        {
            callableStatement = CONN.prepareCall(GetOxySatCall);
            callableStatement.setString(1, username);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.execute();
            ResultSet rset = (ResultSet)callableStatement.getObject(2);

            if (rset.next()) {
                obs.value1 = new Float(rset.getFloat("oxy_sat_value")).toString();
                obs.observed_on = rset.getDate("observed_on");
            }
            status = callableStatement.getString(3);
            message = callableStatement.getString(4);
            System.out.println("Status : " + status + "\nMessage : " + message);            

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
        
        return obs;
    }
    
    public ObservationNew getObservedPain (String username) {
        ObservationNew obs = new ObservationNew();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String GetObservedPainCall = "{call GET_PAIN_OBS_FOR_USERNAME(?, ?, ?, ?)}";
        try 
        {
            callableStatement = CONN.prepareCall(GetObservedPainCall);
            callableStatement.setString(1, username);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.execute();
            ResultSet rset = (ResultSet)callableStatement.getObject(2);

            if (rset.next()) {
                obs.value1 = new Integer(rset.getInt("pain_val")).toString();
                obs.observed_on = rset.getDate("observed_on");
            }
            status = callableStatement.getString(3);
            message = callableStatement.getString(4);
            System.out.println("Status : " + status + "\nMessage : " + message);            

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
        
        return obs;
    }
    
    public ObservationNew getObservedMood (String username) {
        ObservationNew obs = new ObservationNew();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String GetObservedMoodCall = "{call GET_MOOD_OBS_FOR_USERNAME(?, ?, ?, ?)}";
        try 
        {
            callableStatement = CONN.prepareCall(GetObservedMoodCall);
            callableStatement.setString(1, username);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.execute();
            ResultSet rset = (ResultSet)callableStatement.getObject(2);

            if (rset.next()) {
                obs.value1 = rset.getString("mood_val");
                obs.observed_on = rset.getDate("observed_on");
            }
            status = callableStatement.getString(3);
            message = callableStatement.getString(4);
            System.out.println("Status : " + status + "\nMessage : " + message);            

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
        
        return obs;
    }
    
    public ObservationNew getObservedTemperature (String username) {
        ObservationNew obs = new ObservationNew();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String GetObservedTemperatureCall = "{call GET_TEMP_OBS_FOR_USERNAME(?, ?, ?, ?)}";
        try 
        {
            callableStatement = CONN.prepareCall(GetObservedTemperatureCall);
            callableStatement.setString(1, username);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.execute();
            ResultSet rset = (ResultSet)callableStatement.getObject(2);

            if (rset.next()) {
                obs.value1 = new Float(rset.getFloat("temp_val")).toString();
                obs.observed_on = rset.getDate("observed_on");
            }
            status = callableStatement.getString(3);
            message = callableStatement.getString(4);
            System.out.println("Status : " + status + "\nMessage : " + message);
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
        
        return obs;
    }
    
    ArrayList<String> updateSentAlerts(String username, Integer alertId) 
    {
        ArrayList<String> result = new ArrayList<>();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message, status;
        String updateAlertsCall = "{call UPDATE_SENT_ALERTS_NEW(?, ?, ?, ?)}";
        
        try {
            callableStatement = CONN.prepareCall(updateAlertsCall);
            callableStatement.setString(1, username);
            callableStatement.setInt(2, alertId);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.executeUpdate();
            status = callableStatement.getString(3);
            message = callableStatement.getString(4);
            result.add(status);
            result.add(message);
            System.out.println("Status : " + status + "\nMessage : " + message);            
            
        } catch(SQLException e) {
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
    
    ArrayList<String> deleteSentAlerts(String username) 
    {
        ArrayList<String> result = new ArrayList<>();
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message, status;
        String deleteLowActivityAlertsCall = "{call DELETE_LOW_ACTIVITY_ALERT(?, ?, ?)}";
        
        try {
            callableStatement = CONN.prepareCall(deleteLowActivityAlertsCall);
            callableStatement.setString(1, username);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.execute();
            status = callableStatement.getString(2);
            message = callableStatement.getString(3);
            result.add(status);
            result.add(message);
            System.out.println("Status : " + status + "\nMessage : " + message);            
            
        } catch(SQLException e) {
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

    ArrayList<ArrayList<Object>> getAllObservationsNew(String username) {
        ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();

        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String bpCall = "{call GET_BP_OBS_FOR_USERNAME(?, ?, ?, ?)}";
        String tempCall = "{call GET_TEMP_OBS_FOR_USERNAME(?, ?, ?, ?)}";
        String weightCall = "{call GET_WEIGHT_OBS_FOR_USERNAME(?, ?, ?, ?)}";
        String painCall = "{call GET_PAIN_OBS_FOR_USERNAME(?, ?, ?, ?)}";
        String moodCall = "{call GET_MOOD_OBS_FOR_USERNAME(?, ?, ?, ?)}";
        String oxyCall = "{call GET_OXY_OBS_FOR_USERNAME(?, ?, ?, ?)}";
        try {
            callableStatement = CONN.prepareCall(bpCall);            
            callableStatement.setString(1, username);            
            // out Parameters            
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.execute();
            status = callableStatement.getNString(3);
            message = callableStatement.getNString(4);
            ResultSet rset = (ResultSet)callableStatement.getObject(2);
            while(rset.next()) 
            {
                ArrayList<Object> y = new ArrayList<Object>();
                y.add(rset.getInt(1));
                y.add("Blood Pressure");
                y.add(rset.getInt(2));
                y.add(rset.getInt(3));
                y.add(rset.getDate(4));
                y.add(rset.getDate(5));
                result.add(y);
            } 
            callableStatement.close();
            
            //WEIGHT
            
            callableStatement = CONN.prepareCall(weightCall);            
            callableStatement.setString(1, username);            
            // out Parameters            
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.execute();
            status = callableStatement.getNString(3);
            message = callableStatement.getNString(4);
            rset = (ResultSet)callableStatement.getObject(2);
            while(rset.next()) 
            {
                ArrayList<Object> y = new ArrayList<Object>();
                y.add(rset.getInt(1));
                y.add("Weight");
                y.add(rset.getFloat(2));
                y.add("");
                y.add(rset.getDate(3));
                y.add(rset.getDate(4));
                result.add(y);
            } 
            callableStatement.close();
            
            // TEMP
            callableStatement = CONN.prepareCall(tempCall);            
            callableStatement.setString(1, username);            
            // out Parameters            
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.execute();
            status = callableStatement.getNString(3);
            message = callableStatement.getNString(4);
            rset = (ResultSet)callableStatement.getObject(2);
            while(rset.next()) 
            {
                ArrayList<Object> y = new ArrayList<Object>();
                y.add(rset.getInt(1));
                y.add("Temperature");
                y.add(rset.getFloat(2));
                y.add("");
                y.add(rset.getDate(3));
                y.add(rset.getDate(4));
                result.add(y);
            } 
            callableStatement.close();
            
            // MOOD
            callableStatement = CONN.prepareCall(moodCall);            
            callableStatement.setString(1, username);            
            // out Parameters            
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.execute();
            status = callableStatement.getNString(3);
            message = callableStatement.getNString(4);
            rset = (ResultSet)callableStatement.getObject(2);
            while(rset.next()) 
            {
                ArrayList<Object> y = new ArrayList<Object>();
                y.add(rset.getInt(1));
                y.add("Mood");
                y.add(rset.getString(2));
                y.add("");
                y.add(rset.getDate(3));
                y.add(rset.getDate(4));
                result.add(y);
            } 
            callableStatement.close();
            
            
            //PAIN
            callableStatement = CONN.prepareCall(painCall);            
            callableStatement.setString(1, username);            
            // out Parameters            
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.execute();
            status = callableStatement.getNString(3);
            message = callableStatement.getNString(4);
            rset = (ResultSet)callableStatement.getObject(2);
            while(rset.next()) 
            {
                ArrayList<Object> y = new ArrayList<Object>();
                y.add(rset.getInt(1));
                y.add("Pain");
                y.add(rset.getInt(2));
                y.add("");
                y.add(rset.getDate(3));
                y.add(rset.getDate(4));
                result.add(y);
            } 
            callableStatement.close();
            
            
            //OXY
            callableStatement = CONN.prepareCall(oxyCall);            
            callableStatement.setString(1, username);            
            // out Parameters            
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.execute();
            status = callableStatement.getNString(3);
            message = callableStatement.getNString(4);
            rset = (ResultSet)callableStatement.getObject(2);
            while(rset.next()) 
            {
                ArrayList<Object> y = new ArrayList<Object>();
                y.add(rset.getInt(1));
                y.add("Oxygen Saturation");
                y.add(rset.getFloat(2));
                y.add("");
                y.add(rset.getDate(3));
                y.add(rset.getDate(4));
                result.add(y);
            } 
            callableStatement.close();
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

    Recommendation getCustomRecommendation(String username) {
        Recommendation recom = null;
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String showAllRecommendationsCall = "{call SHOW_ALL_RECOMMENDATIONS(?, ?, ?, ?)}";
        try 
        {
            recom = new Recommendation();
            callableStatement = CONN.prepareCall(showAllRecommendationsCall);
            callableStatement.setString(1, username);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.execute();
            ResultSet rset = (ResultSet)callableStatement.getObject(2);
            if (rset.next()) 
            {
                ResultSetMetaData rsmd = rset.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for(int i=1;i<=columnCount;i++)
                {
                    System.out.println(rsmd.getColumnName(i));
                }
                
                recom.weightLow = rset.getFloat("weight_low");
                recom.weightFrequency = rset.getInt("weight_freq");
                recom.bpDiastolicLow = rset.getInt("bp_diastolic_low");
                recom.bpSystolicLow = rset.getInt("bp_systolic_low");
                recom.bpFrequency = rset.getInt("bp_freq");
                recom.oxySatLow = rset.getFloat("oxygen_saturation_low");
                recom.oxySatFrequency = rset.getInt("oxygen_saturation_freq");
                recom.painLevel = rset.getString("pain_level");
                recom.bpSystolicLow = rset.getInt("bp_systolic_low");
                recom.painLevelFrequency = rset.getInt("pain_level_freq");
                recom.mood = rset.getString("mood");
                recom.moodFrequency = rset.getInt("mood_freq");
                recom.temperatureLow = rset.getFloat("temperature_low");
                recom.temperatureFrequency = rset.getInt("temperature_freq");
                recom.temperatureHigh = rset.getFloat("temperature_high");
                recom.weightHigh = rset.getFloat("weight_high");
                recom.moodFrequency = rset.getInt("mood_freq");
                recom.bpDiastolicHigh = rset.getInt("bp_diastolic_high");
                recom.bpSystolicHigh = rset.getInt("bp_systolic_high");
                recom.oxySatHigh = rset.getFloat("oxygen_saturation_high");
            }
            status = callableStatement.getString(3);
            message = callableStatement.getString(4);
            System.out.println(recom.toString());
            System.out.println("Status : " + status + "\nMessage : " + message);
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
        return recom;
    }

    void updateRecommendation(Recommendation newReco, String mPatienUsername) {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String message = "", status = "";
        String callUpdateObservation = "{call UPDATE_RECOMMENDATIONS(?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?)}";
        
        try {
            callableStatement = CONN.prepareCall(callUpdateObservation);
            callableStatement.setString(1, mPatienUsername);
            callableStatement.setFloat(2, newReco.bpDiastolicLow);
            callableStatement.setFloat(3, newReco.bpDiastolicHigh);
            callableStatement.setFloat(4, newReco.bpSystolicLow);
            callableStatement.setFloat(5, newReco.bpSystolicHigh);
            callableStatement.setInt(6, newReco.bpFrequency);            
            callableStatement.setFloat(7, newReco.oxySatLow);
            callableStatement.setFloat(8, newReco.oxySatHigh);
            callableStatement.setInt(9, newReco.oxySatFrequency);
            callableStatement.setString(10, newReco.painLevel);
            callableStatement.setInt(11, newReco.painLevelFrequency);
            callableStatement.setString(12, newReco.mood);
            callableStatement.setInt(13, newReco.moodFrequency);
            callableStatement.setFloat(14, newReco.temperatureLow);
            callableStatement.setFloat(15, newReco.temperatureHigh);
            callableStatement.setInt(16, newReco.temperatureFrequency);
            callableStatement.setFloat(17, newReco.weightLow);
            callableStatement.setFloat(18, newReco.weightHigh);
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
                //callableStatement.close();
            }

            if (dbConnection != null) {
                //CONN.close();
            }
        }
    }
}