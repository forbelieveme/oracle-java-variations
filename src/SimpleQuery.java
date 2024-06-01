import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;

import oracle.sql.*;
import oracle.jdbc.*;

public class SimpleQuery {
	public static void printEmployees(Connection connection)
			throws SQLException {
		// Statement and ResultSet are AutoCloseable and closed automatically.
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement
					.executeQuery("select * from testuser.employees where employee_id>6")) {
				System.out.println("FIRST_NAME" + "  " + "LAST_NAME");
				System.out.println("---------------------");

// 				ResultSet rs= stmt.executeQuery("SELECT * FROM struct_table");
// java.sql.Struct jdbcStruct = (java.sql.Struct)rs.getObject(1);

				while (resultSet.next()) {
					System.out.println("---------------------"+resultSet.getFetchSize());
					BigDecimal employeeId = (BigDecimal) resultSet.getObject(1);
					Struct person = (Struct) resultSet.getObject(8);
					Object [] array = person.getAttributes();
					StringBuffer sbuf = new StringBuffer();
					sbuf.append(employeeId.toString() + " (),");
					for (Object element : array) {
						sbuf.append(element.toString() + " (),");
					}

					// sbuf.append(person.getSQLTypeName() + ": ");

					// Datum[] attrs = (Datum[]) person.getAttributes();

					// for (int i = 0; i < attrs.length; i++) {

					// Write the attribute value to the string buffer
					// sbuf.append(attrs[i].stringValue() + " :" + i + " ()");
					// }
					// Print the stringBuffer value
					System.out.println(sbuf.toString());

					System.out.println(resultSet.getString(1));
					// System.out.println(resultSet.getString(1) + " "
					// + resultSet.getString(2));
					// + resultSet.getString(7) + " ");
				}
			}
		}
	}
}
