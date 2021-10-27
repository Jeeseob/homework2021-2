import java.util.ArrayList;
import java.util.List;

public class PasswordDAOImpl implements PasswordDAO{

    List<PasswordInfo> passwordInfos;

    public PasswordDAOImpl() {
        passwordInfos = new ArrayList<PasswordInfo>();
    }

    @Override
    public void insert(PasswordInfo p) {
        passwordInfos.add(p);
    }

    @Override
    public List<PasswordInfo> findAll() {
        return passwordInfos;
    }

    @Override
    public PasswordInfo findByKey(String url) {
        for(PasswordInfo passwordInfo : passwordInfos) {
            if(passwordInfo.getUrl().equals(url)) {
                return passwordInfo;
            }
        }
        return null;
    }

    @Override
    public void update(PasswordInfo p) {
        PasswordInfo passwordInfo = findByKey(p.getUrl());
        if(passwordInfo != null) {
            passwordInfo.setId(p.getId());
            passwordInfo.setPassword(p.getPassword());
        }
    }

    @Override
    public void delete(String url) {
        PasswordInfo passwordInfo = findByKey(url);
        if(passwordInfo != null){
            passwordInfos.remove(passwordInfo);
        }
    }

    @Override
    public void delete(PasswordInfo p) {
        delete(p.getUrl());
    }
}
