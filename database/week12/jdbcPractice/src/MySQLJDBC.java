import java.sql.*;

public class MySQLJDBC {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{

			//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "rootroot");

			//Execute a query
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM instructor");

			//Print results
            while(rs.next()) {
            	System.out.println(rs.getString("dept_name") + "|" + rs.getString(2) + "|" + rs.getString(3) + "|" + rs.getDouble("salary"));
            }

			rs = stmt.executeQuery("SELECT name FROM instructor WHERE dept_name = 'Comp. Sci.' and salary > 70000");
			while(rs.next()) {
				System.out.println(rs.getString("name"));
			}

			rs = stmt.executeQuery("SELECT name, course_id FROM instructor,teaches WHERE instructor.ID = teaches.ID and instructor.dept_name = 'Biology'");
			while(rs.next()) {
				System.out.println(rs.getString("name") + "|" + rs.getString("course_id"));
			}

			stmt.executeUpdate("UPDATE instructor set salary = salary*1.05");


            // close a connection
            stmt.close();
            conn.close();

		}catch (SQLException ex) {
			//Handle errors for JDBC
			ex.printStackTrace();
		} catch (Exception e){
		    //Handle errors for Class.forName
			e.printStackTrace();
		}
	}
}