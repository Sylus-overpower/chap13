package web.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class BoardDBBean {

	private static BoardDBBean instance = new BoardDBBean();
	
	private BoardDBBean() {}
	
	public static BoardDBBean getInstance() {
		return instance;
	}
	
	public String doA() {
		return "연결";
	}
	
	public void insertArticle(BoardDataBean dataBean) {
//		System.out.println(dataBean);
		Connection conn=null; //db연결하는객체
		PreparedStatement pstmt = null; //sql 연결하는데 insert select update delete
		//jar 파일 관리하는 회사 maven or gradle
		
		//jar 파일 연결 되어있는지 확인 Class.forName();
		//Connection 객체 연결	Drivermanager.getConnection();
		//preparedStatement 생성 conn.preparedStatement();
		//실행
		try {
			//mysql =3306 oracle=1521 sqlserver= 1433
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://192.168.0.200:1433;databasename=hw_20200611", "sa", "8765432!");
//			System.out.println("DB연결 성공");
			pstmt = conn.prepareStatement("INSERT INTO [dbo].[board]" + 
					"           ([num]" + 
					"           ,[writer]" + 
					"           ,[email]" + 
					"           ,[subject]" + 
					"           ,[passwd]" + 
					"           ,[reg_date]" + 
					"           ,[readcount]" + 
					"           ,[ref]" + 
					"           ,[re_step]" + 
					"           ,[re_level]" + 
					"           ,[content]" + 
					"           ,[ip])" + 
					"     VALUES" + 
					"           ((select max(num)+1 as stair from board)" + 
					"           , ? " + 
					"           , ? " + 
					"           , ? " + 
					"           , ? " + 
					"           ,getdate()" + 
					"           ,0" + 
					"           ,0" + 
					"           ,0" + 
					"           ,0" + 
					"           , ? " + 
					"           ,'192.168.0.198')");
			pstmt.setString(1,  dataBean.getWriter());
			pstmt.setString(2,  dataBean.getEmail());
			pstmt.setString(3,  dataBean.getSubject());
			pstmt.setString(4,  dataBean.getPasswd());
			pstmt.setString(5,  dataBean.getContent());
			
			pstmt.executeUpdate();
			System.out.println("insert 성공");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertDetailArticle(String name, String sub, String email, String content, String password) {
		System.out.println("subject = "+sub);
		System.out.println("name = "+name);
		System.out.println("email = "+email);
		System.out.println("content = "+content);
		System.out.println("password = "+password);
	}
}
