import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordDAOImpl implements PasswordDAO{
    final static String DB_FILE_NAME = "Password.db";
    final static String DB_TABLE_NAME = "password";

    Connection connection = null;
    ResultSet rs = null;
    Statement statement = null;


    public PasswordDAOImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE_NAME);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            final String table = " (url text PRIMARY KEY, id text, password text)";

            // create table
            statement.executeUpdate("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
            statement.executeUpdate("CREATE TABLE " + DB_TABLE_NAME + table);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(PasswordInfo passwordInfo) {
        try {
            String fmt = "INSERT INTO %s VALUES('%s', '%s', '%s')";
            String quary = String.format(fmt, DB_TABLE_NAME, passwordInfo.getUrl(), passwordInfo.getId(), passwordInfo.getPassword());

            statement.execute(quary);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PasswordInfo> findAll() {
        ArrayList<PasswordInfo> passwordInfos = new ArrayList<PasswordInfo>();
        try {
            rs = statement.executeQuery("SELECT * FROM " + DB_TABLE_NAME);
            while (rs.next()) {
                passwordInfos.add(new PasswordInfo(rs.getString("url"), rs.getString("id"),
                        rs.getString("password")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return passwordInfos;
    }

    @Override
    public PasswordInfo findByKey(String url) {
        PasswordInfo passwordInfo = null;
        try {
            String fmt = "SELECT * FROM %s WHERE url = %s";
            String q = String.format(fmt, DB_TABLE_NAME, url);
            rs = statement.executeQuery(q);
            if (rs.next()) {
                passwordInfo = new PasswordInfo(rs.getString("url"), rs.getString("id"),
                        rs.getString("password"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return passwordInfo;
    }

    @Override
    public void update(PasswordInfo passwordInfo) {
        if (passwordInfo != null) {
            try {
                String fmt = "UPDATE %s SET id = '%s', password = '%s' WHERE url = '%s'";
                String quary = String.format(fmt, DB_TABLE_NAME, passwordInfo.getId(), passwordInfo.getPassword(), passwordInfo.getUrl());

                statement.execute(quary);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void delete(String url) {
        try {
            String fmt = "DELETE FROM %s WHERE url = '%s'";
            String quary = String.format(fmt, DB_TABLE_NAME, url);

            statement.execute(quary);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(PasswordInfo passwordInfo) {
        delete(passwordInfo.getUrl());
    }
}