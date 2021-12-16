import java.awt.*;
import java.util.ArrayList;

public class Directory {

    String dirName;
    private ArrayList<Component> components = new ArrayList();
    Boolean replace;

    public Directory(String dirName) {
        this.dirName = dirName;
    }

    public void delete(String fileName){
        components.remove(fileName);
        System.out.println("Directory:  바이트 파일 c.txt 를(을) 삭제했습니다.");

    }


    public void copy(Component file, Boolean replace) {
        if (components.equals(file)) {
            if (replace) {
                components.add(file);
                System.out.println("Diretory: " + file.getFileSize()+ "바이트의 파일" +file.getName()+"를 바이트 파일로 교체합니다.");
                return;

            } else {
                return;
            }
        }
        components.add(file);
        System.out.println("Diretory: " + (int)file.getFileSize()+ "바이트의 파일" +file.getName()+"가 복사되었습니다.");

    }

    public String toString(){
        return "";
    }
}
