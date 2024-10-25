package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement statement = null;
		
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"Wavy2012"
					);
			System.out.println("データベース接続成功：" + con );
			
			Statement writing = con.createStatement();
			System.out.println("レコード追加を実行する");
			
			String sqlInsert = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES "
					+ "(1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13),"
					+ "(1002, '2023-02-08', 'お疲れ様です！', 12),"
					+ "(1003, '2023-02-09', '今日も頑張ります！', 18),"
					+ "(1001, '2023-02-09', '無理は禁物ですよ！', 17),"
					+ "(1002, '2023-02-10', '明日から連休ですね！', 20);";
			
			int counting = writing.executeUpdate(sqlInsert);
			System.out.println(counting + "件のレコードが追加されました");
			
			Statement selectStatement = con.createStatement();
			String selectsql = "SELECT * FROM posts WHERE user_id = 1002";
			ResultSet resultSet = selectStatement.executeQuery(selectsql);
			
			System.out.println("ユーザーIDが1002のレコードを検索しました");
			
			int count = 0;
			while(resultSet.next()) {
				count++;
				String posted_at = resultSet.getString("posted_at");
				String post_content = resultSet.getString("post_content");
				int likes = resultSet.getInt("likes");
				System.out.println(resultSet.getRow() + "件目：投稿日時＝" + posted_at + "/投稿内容＝" + post_content + "/いいね数=" + likes );
				if (count >=2 ) {
					break;
				}
			}
		} catch(SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			if( statement != null ) {
				try { statement.close(); } catch(SQLException ignore) {}
			}
			if( con != null ) {
				try { con.close(); } catch(SQLException ignore) {}
			}
		}
	}

}
