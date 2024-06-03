import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;

public class TypeQuery {
    public static void printEmployees(Connection connection)
            throws SQLException {

        // Statement and ResultSet are AutoCloseable and closed automatically.
        String sqlQuery = "SELECT EMPLOYEE_ID, CONCAT(CONCAT(FIRST_NAME, ' '), LAST_NAME) AS FULL_NAME, INFO_COLUMN FROM TESTUSER.EMPLOYEES WHERE EMPLOYEE_ID > 6";
        
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement
                    .executeQuery(sqlQuery)) {

                StringBuilder sbuf = new StringBuilder();

                // El ciclo while recorre el Result Set
                while (resultSet.next()) {

                    // Limpia el string builder
                    sbuf.setLength(0);

                    // Recoge los datos retornados por el query usando los indices
                    Integer employeeId = resultSet.getInt(1);
                    String fullName = resultSet.getString(2);
                    Struct person = (Struct) resultSet.getObject(3);

                    sbuf.append(employeeId.toString() + ", ");
                    sbuf.append(fullName + ", ");

                    // Con este objeto recoge la informacion contenida en el type
                    Object[] array = person.getAttributes();
                    for (Object element : array) {
                        sbuf.append(element.toString() + ", ");
                    }

                    System.out.println(sbuf.toString());

                }
            }
        }
    }
}
