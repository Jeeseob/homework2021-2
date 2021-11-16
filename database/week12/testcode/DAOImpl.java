import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOImpl<D extends DBData<K>, K> implements DAO<D, K>{

    private String dbTableName;

    public abstract Statement getStement();

    public abstract D getNewData(ResultSet rs);

    public abstract String getKeyColumnName();

    public abstract String getInsertValueStr(D data);
    public abstract String getUpdateValueStr(D data);

    public DAOImpl(String dbTableName) {
        this.dbTableName = dbTableName;
    }

    @Override
    public void insert(D data) {
        try {
            String fmt = "INSERT INTO %s VALUES(%s)";
            String quary = String.format(fmt, dbTableName, getInsertValueStr(data));

            getStement().execute(quary);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<D> findAll() {
        ArrayList<D> dataList = new ArrayList<D>();
        ResultSet rs;
        try {
            rs = getStement().executeQuery("SELECT * FROM " + dbTableName);
            while (rs.next()) {
                dataList.add(getNewData(rs));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    @Override
    public D findByKey(K key) {
        D data = null;
        try {
            ResultSet rs;
            String fmt = "SELECT * FROM %s WHERE %s = '%s'";
            String q = String.format(fmt, dbTableName, getKeyColumnName(),key.toString());
            rs = getStement().executeQuery(q);
            if (rs.next()) {
                data = getNewData(rs);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void update(K key, D data) {
        D newData = findByKey(key);
        if (data != null) {
            try {
                String fmt = "UPDATE %s SET %s  WHERE %s = '%s'";
                String quary = String.format(fmt, dbTableName,
                        getUpdateValueStr(newData),
                        getKeyColumnName(), key.toString());

                getStement().execute(quary);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void deleteByKey(K key) {
        try {
            String fmt = "DELETE FROM %s WHERE '%s'";
            String quary = String.format(fmt, dbTableName, key.toString());
            getStement().execute(quary);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(D data) {
        deleteByKey(data.getKey());
    }
}
