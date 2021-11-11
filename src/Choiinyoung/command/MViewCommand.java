package Choiinyoung.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Choiinyoung.dao.MemberDAO;
import Choiinyoung.dto.MemberDTO;

public class MViewCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String id = request.getParameter("id");
		
		//1. MemberDAO를 생성
		MemberDAO dao = new MemberDAO();
		//2. 필요한 메소드를 호출
		MemberDTO dto = dao.view(id);
		//3. 반환된 값을 request 스코프에 저장
		request.setAttribute("dto", dto);
	}

}
