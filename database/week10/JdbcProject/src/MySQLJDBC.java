import java.sql.*;

public class MySQLJDBC {

	public static void main(String[] args) {

		String dbTableName = "instructor";

		try{
			MysqlDAOImpl mysqlDAOImpl = new MysqlDAOImpl(dbTableName);

			mysqlDAOImpl.rs = mysqlDAOImpl.statement.executeQuery("SELECT * FROM instructor");

			//Print results
            while(mysqlDAOImpl.rs.next()) {
            	System.out.println(mysqlDAOImpl.rs.getString("dept_name") + "|" +
						mysqlDAOImpl.rs.getString(2) + "|" +
						mysqlDAOImpl.rs.getString(3) + "|" +
						mysqlDAOImpl.rs.getDouble("salary"));

            }

            // close a connection
            mysqlDAOImpl.statement.close();
            mysqlDAOImpl.connection.close();

		}catch (SQLException ex) {
			//Handle errors for JDBC
			ex.printStackTrace();
		} catch (Exception e){
		    //Handle errors for Class.forName
			e.printStackTrace();
		}
	}
}