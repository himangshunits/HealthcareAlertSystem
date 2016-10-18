import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class TestProc {

    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
    private static final String DB_USER = "hborah";
    private static final String DB_PASSWORD = "200105222";

    public static void main(String[] argv) {

        try {

            callOracleStoredProcOUTParameter();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    private static void callOracleStoredProcOUTParameter() throws SQLException {

        Connection dbConnection = null;
        CallableStatement callableStatement = null;

        String insertPersonDataCall = "{call INSERT_PERSON_DATA(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try {
            dbConnection = getDBConnection();
            callableStatement = dbConnection.prepareCall(insertPersonDataCall);

            callableStatement.setString(1, "Moharnab Saikia");
            callableStatement.setDate(2, new Date(1990,06,12));
            callableStatement.setString(3, "MALE");
            callableStatement.setInt(4, 0);
            callableStatement.setString(5, "msaikia");
            callableStatement.setString(6, "test_qwerty12");
            callableStatement.setString(7, "517 Tartan Cirlce");
            callableStatement.setString(8, "Apartment 21");
            callableStatement.setString(9, "Raleigh");
            callableStatement.setString(10, "USA");
            callableStatement.setInt(11, 27606);
            callableStatement.setString(12, "NC");
            callableStatement.setString(13, "9876541324");
            callableStatement.setString(14, "9197858515");
            callableStatement.setString(15, "masaikia@ncsu.edu");
            callableStatement.setString(16, "123-456-9874");

            // out Parameters
            callableStatement.registerOutParameter(17, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(18, java.sql.Types.VARCHAR);

            // execute getDBUSERByUserId store procedure
            callableStatement.executeUpdate();

            String status = callableStatement.getString(17);
            String message = callableStatement.getString(18);

            System.out.println("Status is  : " + status);
            System.out.println("Message is : " + message);

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (callableStatement != null) {
                callableStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

    }

    private static Connection getDBConnection() {

        Connection dbConnection = null;

        try {

            Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {

            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER,DB_PASSWORD);
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }

}