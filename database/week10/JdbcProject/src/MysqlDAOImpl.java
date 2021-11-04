import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlDAOImpl extends DAOImpl<DBClassDatabase,String>{
    //public abstract Connection createConnection();

    final static String DB_SERVER = "DB";

    Connection connection = null;
    ResultSet rs = null;
    Statement statement = null;

    public MysqlDAOImpl(String dbTableName) {
        super(dbTableName);
        try {

            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //Open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection("jdbc:mysql://" + DB_SERVER, "user", "password");

            statement = connection.createStatement();

        } catch (SQLException ex) {
            //Handle errors for JDBC
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Statement getStement() {
        return statement;
    }

    @Override
    public DBClassDatabase getNewData(ResultSet rs) {
        DBClassDatabase dBClassDatabase = null;
        if (rs != null) {
            try {
                dBClassDatabase = new DBClassDatabase(rs.getString("URL"), rs.getString("ID"), rs.getString("PASSWORD"));
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dBClassDatabase;
    }

    @Override
    public String getKeyColumnName() {
        return "URL";
    }

    @Override
    public String getInsertValueStr(DBClassDatabase dBClassDatabase) {
        String fmt = "'%s', '%s', '%s'";
        String quary = String.format(fmt, dBClassDatabase.getKey(), dBClassDatabase.getId(), dBClassDatabase.getPassword());
        return quary;
    }

    @Override
    public String getUpdateValueStr(DBClassDatabase dBClassDatabase) {
        String fmt = "id = '%s', password = '%s'";
        String quary = String.format(fmt, dBClassDatabase.getId(), dBClassDatabase.getPassword());
        return quary;
    }
}