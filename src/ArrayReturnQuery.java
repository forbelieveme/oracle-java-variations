import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;

import oracle.jdbc.*;
import oracle.sql.ARRAY;
import oracle.sql.STRUCT;

public class ArrayReturnQuery {
    
    public static void printEmployeeById(Connection connection, Integer id)
            throws SQLException {

        CallableStatement callableStatement = null;
        StringBuilder sbuf = new StringBuilder();

        String sqlQuery = "{CALL GET_EMPLOYEES_BY_ID(?, ?, ?)}";

        try {

            callableStatement = connection.prepareCall(sqlQuery);

            callableStatement.setLong(1, id);

            callableStatement.registerOutParameter(2, OracleTypes.NUMBER);
            callableStatement.registerOutParameter(3, OracleTypes.ARRAY, "EMP_ARRAY");

            callableStatement.execute();

            Long outEmpId = callableStatement.getLong(2);
            ARRAY empArray = (ARRAY) callableStatement.getObject(3);

            // Retrieve and print the elements of the array
            ResultSet arrayRs = empArray.getResultSet();
            while (arrayRs.next()) {

                sbuf.setLength(0);

                STRUCT empStruct = (STRUCT) arrayRs.getObject(2);
                Object[] attributes = empStruct.getAttributes();
                Integer employeeId = ((BigDecimal) attributes[0]).intValue();
                String firstName = (String) attributes[1];
                String lastName = (String) attributes[2];
                String fullName = firstName + " " + lastName;

                sbuf.append(employeeId.toString() + ", ");
                sbuf.append(fullName);

                System.out.println(sbuf.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null)
                    callableStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printEmployees(Connection connection)
            throws SQLException {

        CallableStatement callableStatement = null;
        StringBuilder sbuf = new StringBuilder();

        String sqlQuery = "{CALL TESTUSER.GET_EMPLOYEES_BY_ID(?, ?, ?)}";

        try {

            callableStatement = connection.prepareCall(sqlQuery);

            callableStatement.setNull(1, OracleTypes.NUMBER);

            callableStatement.registerOutParameter(2, OracleTypes.NUMBER);
            callableStatement.registerOutParameter(3, OracleTypes.ARRAY, "TESTUSER.EMP_ARRAY");

            callableStatement.execute();

            Long outEmpId = callableStatement.getLong(2);
            ARRAY empArray = (ARRAY) callableStatement.getObject(3);

            // Retrieve and print the elements of the array
            ResultSet arrayRs = empArray.getResultSet();
            while (arrayRs.next()) {

                sbuf.setLength(0);

                STRUCT empStruct = (STRUCT) arrayRs.getObject(2);
                Object[] attributes = empStruct.getAttributes();
                Integer employeeId = ((BigDecimal) attributes[0]).intValue();
                String firstName = (String) attributes[1];
                String lastName = (String) attributes[2];
                String fullName = firstName + " " + lastName;

                sbuf.append(employeeId.toString() + ", ");
                sbuf.append(fullName);

                System.out.println(sbuf.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null)
                    callableStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
