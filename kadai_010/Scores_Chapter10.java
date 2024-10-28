package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement statement = null;
		
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"Wavy2012"
					);
			
			System.out.println("データベース接続完了：" + con);
			
			statement = con.createStatement();
			String sqlUpdate = "UPDATE scores SET score_math = 95,score_english = 80 WHERE ID = 5;";
			System.out.println("レコード更新を実行します");
			int rowCnt = statement.executeUpdate(sqlUpdate);
			System.out.println(rowCnt + "件のレコードが更新されました");
			
			String sqlOrder = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC;";
			ResultSet result = statement.executeQuery(sqlOrder);
			System.out.println("数学・英語の高い順に並び替えました");
			
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int score_math = result.getInt("score_math");
				int score_english = result.getInt("score_english");
					System.out.println(result.getRow() + "件目：生徒ID=" + id + "/氏名=" + name + "/数学=" + score_math + "/英語=" + score_english );
			}
			
		} catch (SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			if ( statement != null ) {
				try { statement.close(); } catch(SQLException ignore) {}
				}
			if( con != null ) {
				try { con.close(); } catch(SQLException ignore) {}
			}
		}
	}

}
