import java.util.List;

public interface DAO<D, K> {
    public void insert(D data);
    public List<D> findAll();
    public D findByKey(K key);
    public void update(K key, D data);
    public void deleteByKey(K key);
    public void delete(D data);
}
