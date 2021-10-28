import java.util.List;
public interface PasswordDAO {
    public void insert(PasswordInfo passwordInfo);
    public List<PasswordInfo> findAll();
    public PasswordInfo findByKey(String url);
    public void update(PasswordInfo passwordInfo);
    public void delete(String url);
    public void delete(PasswordInfo passwordInfo);
}
