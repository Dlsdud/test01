package Choiinyoung.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Choiinyoung.dao.MemberDAO;
import Choiinyoung.dto.MemberDTO;

public class MInsertCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");	
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//1. 사용자가 입력한 회원 정보를 받는다.
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		
		//DB에 저장되도록 dto를 전달
		MemberDAO dao = new MemberDAO();
		dao.insert(dto);
	}

}
