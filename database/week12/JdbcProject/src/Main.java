import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {

        CLIInterface cliInterface = new CLIInterface();
        Scanner scanner = new Scanner(System.in);
        MySQLJDBC mySQLJDBC = new MySQLJDBC();

        int inputNumber = -1;

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&" +
                            "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "root"); //코드 실행시에는 변경하였습니다.
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Search search = new Search(conn);

        while(inputNumber != 0) {
            //interface 출력
            cliInterface.showInterfcae();
            inputNumber = scanner.nextInt();

            switch (inputNumber) {
                case 1:
                    mySQLJDBC.makeRealation(conn);
                    break;
                case 2:
                    search.searchTitle();
                    break;
                case 3:
                    search.searchTotalNum();
                    break;
                case 4:
                    search.searchReleasedate();
                    break;
            }
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("프로그램 종료");
    }
}
