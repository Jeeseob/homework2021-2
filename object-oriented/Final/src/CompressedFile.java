import java.util.ArrayList;

public class CompressedFile implements Component{
    private ArrayList<File> files = new ArrayList();
    private String name;
    private int copmpressedRate;

    public CompressedFile( String name, int copmpressedRate) {
        this.name = name;
        this.copmpressedRate = copmpressedRate;
    }

    public void addFile(File file){
        System.out.println("CompressedFile: 압축 파일"+name+"생성, 압축률:"+copmpressedRate);
        if(equals(file) == 0) {
            file.setFileSize(file.getFileSize()-file.getFileSize()*0.3);
            files.add(file);
            System.out.println("CompressedFile: 파일"+file.getName()+"추가. 파일 크기: "+(int)file.getFileSize());
        }
    }
    @Override
    public String toString() {
        double a = 0;
        for(File file : files) {
            a = a + file.getFileSize();
        }
        return "파일: "+name+", 크기: "+a+"바이트";
    }
    private int equals(File fileName) {
        for(File file : files) {
            if (fileName == file) {
                return 1;
            }
        }
        return 0;
    }
    public void removeFile(File file) {
        files.remove(file);
        System.out.println("CompressedFile: 파일"+file.getName()+"가(이) 삭제되었습니다.\n");

    }

    public File getFile(int num) {
        return files.get(num);
    }

    public void uncompress() {

    }

    @Override
    public int equals(String fileNam) {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getFileSize() {
        double a = 0;
        for(File file : files) {
            a = a+file.getFileSize();
        }
        return a;
    }
}
