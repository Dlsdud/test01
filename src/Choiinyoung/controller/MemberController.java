package Choiinyoung.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Choiinyoung.command.MCommand;
import Choiinyoung.command.MDeleteCommand;
import Choiinyoung.command.MInsertCommand;
import Choiinyoung.command.MListCommand;
import Choiinyoung.command.MUpdateCommand;
import Choiinyoung.command.MViewCommand;

@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		request.setCharacterEncoding("utf-8");
//		String viewPage = null;
		MCommand command = null;
		
		String uri = request.getRequestURI();		
		String com = uri.substring(uri.lastIndexOf("/")+ 1, uri.lastIndexOf(".do"));
		String viewPage = "";
		
		if(com != null && com.trim().equals("list")) {
			command = new MListCommand();
			command.execute(request, response);
			viewPage = "/WEB-INF/view/mList.jsp";
		} else if(com != null && com.trim().equals("insertForm")) {
			//회원을 등록할 수 있는 폼을 보여주어야 한다.
			viewPage = "/WEB-INF/view/minsertForm.jsp";
		} else if(com != null && com.trim().equals("insertForm")) {
			//1. MInsertCommand 생성
			command = new MInsertCommand();
			//2. execute 메소드 실행
			command.execute(request, response);
			//3. 리스트를 재사용
			viewPage="list.do";
		} else if(com != null && com.trim().equals("view")) {
			//1. MViewCommand 생성
			command = new MViewCommand();
			//2. execute 메소드 호출
			command.execute(request, response);
			//3. 필요한 View 페이지를 설정
			viewPage="/WEB-INF/view/mView.jsp";
		} else if(com != null && com.trim().equals("update")) {
			//1. MUpdateCommand 생성
			command = new MUpdateCommand();
			//2. execute 메소드 호출
			command.execute(request, response);
			//3. 리스트를 재사용
			viewPage="list.do";
		} else if(com != null && com.trim().equals("delete")) {
			command = new MDeleteCommand();
			command.execute(request, response);
			viewPage="list.do";
		}
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러에 도착했습니다.");
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

}
