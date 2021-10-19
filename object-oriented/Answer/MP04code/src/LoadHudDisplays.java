import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadHudDisplays implements LoadDisplayInterface {
    String fileName;

    public LoadHudDisplays(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ArrayList<String> load() {
        ArrayList<String> alist = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                alist.add(line);
                line = br.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(fileName + " does not exist.");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return alist;
    }
}
