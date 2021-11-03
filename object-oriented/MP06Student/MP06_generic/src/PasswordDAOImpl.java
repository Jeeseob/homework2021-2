import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class PasswordDAOImpl extends DAOImpl<PasswordInfo,String>{
    //public abstract Connection createConnection();

    final static String DB_FILE_NAME = "Password.db";

    Connection connection = null;
    ResultSet rs = null;
    Statement statement = null;

    public PasswordDAOImpl(String dbTableName) {
        super(dbTableName);
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE_NAME);
            //connection = createConnection();
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            final String table = " (url text PRIMARY KEY, id text, password text)";

            // create table
            statement.executeUpdate("DROP TABLE IF EXISTS " + dbTableName);
            statement.executeUpdate("CREATE TABLE " + dbTableName + table);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Statement getStement() {
        return statement;
    }

    @Override
    public PasswordInfo getNewData(ResultSet rs) {
        PasswordInfo passwordInfo = null;
        if (rs != null) {
            try {
                passwordInfo = new PasswordInfo(rs.getString("URL"), rs.getString("ID"), rs.getString("PASSWORD"));
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return passwordInfo;
    }

    @Override
    public String getKeyColumnName() {
        return "URL";
    }

    @Override
    public String getInsertValueStr(PasswordInfo passwordInfo) {
        String fmt = "'%s', '%s', '%s'";
        String quary = String.format(fmt, passwordInfo.getKey(), passwordInfo.getId(), passwordInfo.getPassword());
        return quary;
    }

    @Override
    public String getUpdateValueStr(PasswordInfo passwordInfo) {
        String fmt = "id = '%s', password = '%s'";
        String quary = String.format(fmt, passwordInfo.getId(), passwordInfo.getPassword());
        return quary;
    }
}