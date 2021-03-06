package com.bitacademy.emaillist.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bitacademy.emaillist.vo.EmaillistVo;

@Repository
public class EmaillistRepository {
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			//1. JDBC Driver 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2. 연결하기
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException ex) {
			//1. 사과
			//2. log
			System.out.println("error" + ex);
			ex.printStackTrace();
			//3. 안전하게 종료
			// ex) return ...
		} 
		return conn;
	}
	
	
	public boolean insert(EmaillistVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {			
			conn = getConnection();
			
			//3. SQL 준비
			String sql = "insert into emaillist values (null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			pstmt.setString(1, vo.getFirst_name());
			pstmt.setString(2, vo.getLast_name());
			pstmt.setString(3, vo.getEmail());
			//5. SQL문 실행
			int count = pstmt.executeUpdate();
			//6. 결과
			result = count == 1;
		}  catch (SQLException ex) {
			//1. 사과
			//2. log
			System.out.println(ex);
		} finally {
			// 자원정리
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return result;
	}
	
	public List<EmaillistVo> findAll() {
		List<EmaillistVo> list = new ArrayList<EmaillistVo>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			//3. SQL 준비
			String sql = "select no, first_name, last_name, email from emaillist order by no desc";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			
			//5. SQL문 실행
			rs = pstmt.executeQuery();
			
			//6. 데이터 가져오기
			while(rs.next()) {
				Long no = rs.getLong(1);
				String first_name = rs.getString(2);
				String last_name = rs.getString(3);
				String email = rs.getString(4);
				EmaillistVo vo = new EmaillistVo();
				vo.setNo(no);
				vo.setFirst_name(first_name);
				vo.setLast_name(last_name);
				vo.setEmail(email);
				list.add(vo);
			}
		}  catch (SQLException ex) {
			//1. 사과
			//2. log
			System.out.println(ex);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(rs != null) {
					rs.close();
				}
				if(conn != null) {
					conn.close();	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
	