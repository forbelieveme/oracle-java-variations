import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleQuery {
	public static void printEmployees(Connection connection)
			throws SQLException {

		// Statement and ResultSet are AutoCloseable and closed automatically.
		String sqlQuery = "select employee_id, concat(concat(first_name, ' '), last_name) as full_name from testuser.employees where employee_id>6";
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
