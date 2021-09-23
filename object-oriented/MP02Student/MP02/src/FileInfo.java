import java.util.Date;

public class FileInfo {
    private String name;
    private String type;

    private int size;
    private Date modifiedDate;

    public FileInfo(String name, String type, int size, Date date) {
        this.name =name;
        this.type = type;
        this.size = size;
        this.modifiedDate = date;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public String toString() {
        return "Name: " + this.getName()+"\n"
        +"Type: " + this.getType()+"\n"
        +"Size: " + this.getSize()+"\n"
        +"ModifiedDate :" + this.getModifiedDate();
    }
}
