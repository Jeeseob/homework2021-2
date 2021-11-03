import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PasswordDAOList extends DAOImpl<PasswordInfo, String>{

    List<PasswordInfo> passwordInfos;

    public PasswordDAOList(String dbTableName) {
        super(dbTableName);
        passwordInfos = new ArrayList<PasswordInfo>();
    }


    @Override
    public Statement getStement() {
        return null;
    }

    @Override
    public PasswordInfo getNewData(ResultSet rs) {
        return null;
    }

    @Override
    public String getKeyColumnName() {
        return null;
    }

    @Override
    public String getInsertValueStr(PasswordInfo data) {
        return null;
    }

    @Override
    public String getUpdateValueStr(PasswordInfo data) {
        return null;
    }
}
