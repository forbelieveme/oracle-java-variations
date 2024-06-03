import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleQuery {
	public static void printEmployees(Connection connection)
			throws SQLException {

		// Statement and ResultSet are AutoCloseable and closed automatically.
		String sqlQuery = "SELECT EMPLOYEE_ID, CONCAT(CONCAT(FIRST_NAME, ' '), LAST_NAME) AS FULL_NAME FROM TESTUSER.EMPLOYEES WHERE EMPLOYEE_ID > 6";
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement
					.executeQuery(sqlQuery)) {

				// Aca trae la metaData del Result Set
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int columnCount = resultSetMetaData.getColumnCount();

				// El ciclo while recorre el Result Set
				while (resultSet.next()) {
					for (int i = 1; i <= columnCount; i++) {
						if (i > 1)
							System.out.print(",  ");
						String columnValue = resultSet.getString(i);
						System.out.print(columnValue + " " + resultSetMetaData.getColumnName(i));
					}
					System.out.println("");

				}
			}
		}
	}
}
