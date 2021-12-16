public class File implements Component{
    private String name;
    private double fileSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }


    public File(String name, double fileSize) {
        this.name = name;
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "파일: "+name+", 크기: "+fileSize+"바이트";
    }

    public int equals(String fileNam) {
        return 1;
    }
}
