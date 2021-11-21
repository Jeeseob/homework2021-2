
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class MySQLJDBC {

	public void makeRealation(Connection conn) {

		ReadData readData = new ReadData();
		ArrayList<DataDB> datas = new ArrayList<DataDB>();

		Statement stmt = null;
		String quary = "";

		//Create Table
		try {
			quary = readData.readRelation("create_table.txt");

			stmt = conn.createStatement();
			stmt.executeUpdate(quary);

			System.out.println("movie relation이 생성되었습니다.");

			datas = readData.readDataDB("movie_data.txt");

			for (int i = 0; i <datas.size(); i++) {
				stmt.execute("INSERT INTO movie VALUES ("
						+ datas.get(i).getId() + ", "
						+ datas.get(i).getTitle() + ", "
						+ datas.get(i).getCompany() + ", "
						+ datas.get(i).getReleaseDate() + ", "
						+ datas.get(i).getContry() + ", "
						+ datas.get(i).getTotalScreen() + ", "
						+ datas.get(i).getProfit() + " , "
						+ datas.get(i).getTotalNum() + " , "
						+ datas.get(i).getGrade() + ")");
			}
			stmt.close();
			System.out.println("movie 데이터가 추가 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}