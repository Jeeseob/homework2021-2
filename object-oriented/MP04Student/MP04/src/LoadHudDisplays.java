import java.io.*;
import java.util.ArrayList;

public class LoadHudDisplays implements LoadDisplayInterface{
    private String fileName;
    private String readBuffer;
    private ArrayList<String> strings = new ArrayList<String>();

    public LoadHudDisplays(String displayFileName) {
        this.fileName = displayFileName;
    }
    @Override
    public ArrayList<String> load() {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // BufferedReader의 readLine함수를 사용하여, 데이터를 한줄씩 받아 readBuffer에 저장 후, 이를 ArrayList에 추가
            while ((readBuffer = bufferedReader.readLine()) != null) {
                strings.add(readBuffer);
            }
            //종료
            bufferedReader.close();
        // 오류메시지 출력
        }catch (FileNotFoundException e) {
            System.out.println(e);
        }catch (IOException e) {
            System.out.println(e);
        }
        return strings;
    }
}
