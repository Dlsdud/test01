package Choiinyoung.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Choiinyoung.dao.MemberDAO;
import Choiinyoung.dto.MemberDTO;

public class MDeleteCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
				
		MemberDAO dao = new MemberDAO();
		dao.delete(id);
	}

}
