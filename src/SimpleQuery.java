import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;

import oracle.sql.*;
import oracle.jdbc.*;

public class SimpleQuery {
	public static void printEmployees(Connection connection)
			throws SQLException {

		// Statement and ResultSet are AutoCloseable and closed automatically.
		String sqlQuery = "select employee_id, concat(concat(first_name, ' '), last_name) as full_name from testuser.employees where employee_id>6";
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement
					.executeQuery(sqlQuery)) {

				// Acá trae la metaData del Result Set
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

					// System.out.println("---------------------"+resultSet.getFetchSize());
					// BigDecimal employeeId = (BigDecimal) resultSet.getObject(1);
					// Struct person = (Struct) resultSet.getObject(8);
					// Object [] array = person.getAttributes();
					// StringBuffer sbuf = new StringBuffer();
					// sbuf.append(employeeId.toString() + " (),");
					// for (Object element : array) {
					// sbuf.append(element.toString() + " (),");
					// }

					// sbuf.append(person.getSQLTypeName() + ": ");

					// Datum[] attrs = (Datum[]) person.getAttributes();

					// for (int i = 0; i < attrs.length; i++) {

					// Write the attribute value to the string buffer
					// sbuf.append(attrs[i].stringValue() + " :" + i + " ()");
					// }
					// Print the stringBuffer value
					// System.out.println(sbuf.toString());

					// System.out.println(resultSet.getString(1));
					// System.out.println(resultSet.getString(1) + " "
					// + resultSet.getString(2));
					// + resultSet.getString(7) + " ");
				}
			}
		}
	}
}
