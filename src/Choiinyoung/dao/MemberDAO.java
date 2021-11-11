package Choiinyoung.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Choiinyoung.dto.MemberDTO;

public class MemberDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public MemberDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/mvc");
//			con = ds.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(con != null) {
				con.close();
				con=null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 전체 멤버 목록보기
	public ArrayList<MemberDTO> list() {
		String sql = "select * from member";
		
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setJoinDate(rs.getDate("joinDate"));
				dtos.add(dto);
			}
			rs.close(); 
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dtos;
	}
	
	//DB에 회원정보를 저장할 메소드 작성
	public void insert(MemberDTO dto) {
		//1. SQL - insert
		String sql = "insert into member(id, pwd, name, email, joindate) values(?, ?, ?, ?, sysdate)";

		//2. Connection 얻어오기
		try {
			con = ds.getConnection();
			//3. 해당 Connection을 통해 SQL 실행 - PreparedStatement  객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			
			pstmt.executeLargeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	//DB에 회원정보를 가져올 메소드 작성
	public MemberDTO view(String id) {
		//1. SQL - select
		String sql = "select * from member where id=?";
		
		MemberDTO dto = new MemberDTO();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			rs.next();
			dto.setId(id);
			dto.setPwd(rs.getString("pwd"));
			dto.setName(rs.getString("name"));
			dto.setEmail(rs.getString("email"));
			dto.setJoinDate(rs.getDate("joinDate"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
		
	}
	
	
	// Update
	public void update(MemberDTO dto) {
		String sql = "update member set pwd=?, name=?, email=?, joindate=? where id=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setDate(4, dto.getJoinDate());
			pstmt.setString(5, dto.getId());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	   
   public void delete(String id) {
      String sql = "DELETE FROM MEMBER WHERE ID=?";
      
      try {
         con = ds.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.executeQuery();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
