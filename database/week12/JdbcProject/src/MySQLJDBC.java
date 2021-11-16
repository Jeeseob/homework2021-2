import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class MySQLJDBC {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String quary = "";
		ArrayList<DataDB> datas = new ArrayList<DataDB>();

		try{

			//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "rootroot");

			//Create Table
			try{
				//파일 객체 생성
				File file = new File("create_table.txt");
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


			System.out.println(quary);

			stmt = conn.createStatement();
			//stmt.executeUpdate(quary);

			try{
				//파일 객체 생성
				File file = new File("movie_data.txt");
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

			for (int i = 0; i <datas.size(); i++) {
				stmt.execute("INSERT INTO movie VALUES ("
						+ datas.get(i).getId() + ", "
						+ datas.get(i).getTitle() + ", "
						+ datas.get(i).getCompany() + ", "
						+ datas.get(i).getReleaseDate() + ", "
						+ datas.get(i).getContry() + ", "
						+ datas.get(i).getTotalScreen() + ", "
						+ datas.get(i).getProfit() +" , "
						+ datas.get(i).getTotalNum() +" , "
						+ datas.get(i).getGrade() + ")");
			}

			//Execute a query
			rs = stmt.executeQuery("SELECT * FROM movie");

			//Print results
            while(rs.next()) {
            	System.out.println(rs.getString("title") + "|" + "|" + rs.getString("company") + "|");
            }
//
//			rs = stmt.executeQuery("SELECT name FROM instructor WHERE dept_name = 'Comp. Sci.' and salary > 70000");
//			while(rs.next()) {
//				System.out.println(rs.getString("name"));
//			}
//
//			rs = stmt.executeQuery("SELECT name, course_id FROM instructor,teaches WHERE instructor.ID = teaches.ID and instructor.dept_name = 'Biology'");
//			while(rs.next()) {
//				System.out.println(rs.getString("name") + "|" + rs.getString("course_id"));
//			}
//
//			stmt.executeUpdate("UPDATE instructor set salary = salary*1.05");


            // close a connection
            stmt.close();
            conn.close();

		}catch (SQLException ex) {
			//Handle errors for JDBC
			ex.printStackTrace();
		} catch (Exception e){
		    //Handle errors for Class.forName
			e.printStackTrace();
		}
	}
}