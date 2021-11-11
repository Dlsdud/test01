package Choiinyoung.command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Choiinyoung.dao.MemberDAO;
import Choiinyoung.dto.MemberDTO;

public class MUpdateCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//1. 변경된 데이터 가져오기 - dto에 담아 보낸다.
		MemberDTO dto = new MemberDTO();
		
		//2.
		dto.setId(request.getParameter("id"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("emial"));
		dto.setJoinDate(Date.valueOf(request.getParameter("joinDate")));
		
		//3. dto를 DB에 업데이트 할 수 있는 MemberDAO 메소드에 전달
		MemberDAO dao = new MemberDAO();
		dao.update(dto);
	}

}
