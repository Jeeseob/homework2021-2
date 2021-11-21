import java.io.*;
import java.util.ArrayList;

public class ReadData {

    String quary;
    ArrayList<DataDB> datas = new ArrayList<DataDB>();

    public String readRelation(String fileName) {
        quary = "";
        try{
            //파일 객체 생성
            File file = new File(fileName);
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                quary += line;
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.
            bufReader.close();
        }catch (FileNotFoundException e) {
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }
        return quary;
    }

    public ArrayList<DataDB> readDataDB(String fileName){
        try{
            //파일 객체 생성
            File file = new File(fileName);
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            String[] lines;
            while((line = bufReader.readLine()) != null){
                lines = line.split("[|]");
                datas.add(new DataDB(
                        lines[1],
                        lines[2],
                        lines[3],
                        lines[4],
                        lines[5],
                        lines[6],
                        lines[7],
                        lines[8],
                        lines[9]));
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.
            bufReader.close();
        }catch (FileNotFoundException e) {
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }
        return datas;
    }
}
