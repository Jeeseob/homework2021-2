import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Search {

    Scanner scanner = new Scanner(System.in);

    Connection conn;
    Statement stmt;
    ResultSet rs;

    String inputUser;
    public Search(Connection conn) {
        this.conn = conn;
    }

    public void searchTitle() {

        System.out.print("제목 입력 : ");
        inputUser = scanner.next();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM movie WHERE title LIKE '%"+inputUser+"%'");
            while(rs.next()) {
                System.out.println(rs.getString("id") + "|" + rs.getString("title")+ "|"
                        + rs.getString("company")+ "|" + rs.getString("releasedate")+ "|"
                        + rs.getString("country")+ "|" + rs.getString("totalscreen")+ "|"
                        + rs.getString("profit")+ "|" + rs.getString("totalnum")+ "|"
                        + rs.getString("grade"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchTotalNum() {
        System.out.print("관객수 입력 : ");
        inputUser = scanner.next();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM movie WHERE totalnum > "+inputUser);
            while(rs.next()) {
                System.out.println(rs.getString("id") + "|" + rs.getString("title")+ "|"
                        + rs.getString("company")+ "|" + rs.getString("releasedate")+ "|"
                        + rs.getString("country")+ "|" + rs.getString("totalscreen")+ "|"
                        + rs.getString("profit")+ "|" + rs.getString("totalnum")+ "|"
                        + rs.getString("grade"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void searchReleasedate() {
        System.out.print("날짜 입력(시작, 끝) : ");
        inputUser = scanner.next();
        String date[] = inputUser.split(",");
        String dates = scanner.next();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM movie WHERE releasedate BETWEEN '"+date[0]+"' and '"+dates+"'");
            while(rs.next()) {
                System.out.println(rs.getString("id") + "|" + rs.getString("title")+ "|"
                        + rs.getString("company")+ "|" + rs.getString("releasedate")+ "|"
                        + rs.getString("country")+ "|" + rs.getString("totalscreen")+ "|"
                        + rs.getString("profit")+ "|" + rs.getString("totalnum")+ "|"
                        + rs.getString("grade"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
