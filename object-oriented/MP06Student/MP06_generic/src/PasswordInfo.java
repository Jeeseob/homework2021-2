public class PasswordInfo implements DBData<String>{
    private String url;
    private String id;
    private String password;

    public PasswordInfo(String url, String id, String password){
        this.url = url;
        this.id = id;
        this.password = password;
    }
    public String toString() {
        return "" + url + ", " + id + ", " + password;
    }

    @Override
    public String getKey() {
        return url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}