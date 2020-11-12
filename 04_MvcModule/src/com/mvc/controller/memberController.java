package com.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dto.MemberDTO;
import com.mvc.service.MemberSerivce;

// 회원과 관련된 요청만 받을 예정
// login, join, logout, main, memberDel
@WebServlet({"/login","/join","/logout","/main","/memberDel","/memberInfo","/update"})
public class memberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}

	// 실제 일은 여기서 한다.
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// index.jsp 에서 받아온 파라메터를 뽑아서 콘솔로 찍어보기
		System.out.println("요청 받음");
		
		// 요청 주소 추출
		// URI - CONTEXT = 요청 URL 
		String reqAddr = req.getRequestURI().substring(req.getContextPath().length());
		
		
		// Controller 에서는 요청만 분리하는데도 많은 코드가 들어간다.
		// 그래서 실질적인 업무(복잡한...) 는 Service 에게 맡긴다.
		MemberSerivce service = new MemberSerivce();
		RequestDispatcher dis = null;
		
		switch (reqAddr) {
		case "/login":
			String id = req.getParameter("userId");
			String pw = req.getParameter("userPass");
			String page = "index.jsp";
			String msg = "로그인에 실패했습니다.";
			
			if(service.login(id, pw)) { // login 성공 시 : main 으로 보낸다. msg : 로그인 성공!
				page = "main";
				msg = "로그인 성공!";
				// java 에서는 세션을 request 로 부터 가져와야 한다.
				req.getSession().setAttribute("loginId", id);
			}
			req.setAttribute("msg", msg);
			dis = req.getRequestDispatcher(page);
			dis.forward(req, resp);
			
			break;
/*---------------------------------------------------------------------------------------------------*/
			
		case "/logout":
			req.getSession().removeAttribute("loginId");
			resp.sendRedirect("index.jsp");
			
			break;

/*---------------------------------------------------------------------------------------------------*/

		case "/join":
			// 1. 파라메터 값 확인
			req.setCharacterEncoding("UTF-8");
			id = req.getParameter("id");
			pw = req.getParameter("pw");
			String name = req.getParameter("name");
			int age = Integer.parseInt(req.getParameter("age"));
			String gender = req.getParameter("gender");
			String email = req.getParameter("email");
			
			System.out.println(id+pw+name+age+gender+email);
			// 2. 서비스 객체화 -> DAO 생성 -> DB에 파라메터값을 이용하여 데이터 넣기
			boolean success = service.join(id, pw, name, age, gender, email); 
			
			// 3. 결과에 따라 성공 : index.jsp , 실패하면 : joinForm.jsp 이동
			
			if(success) {
				msg = "회원가입 성공!";
				page = "index.jsp";
				req.setAttribute("msg", msg);
				dis = req.getRequestDispatcher(page);
				dis.forward(req, resp);
			}else {				
				msg = "회원가입 실패";
				page = "joinForm.jsp";
				req.setAttribute("msg", msg);
				dis = req.getRequestDispatcher(page);
				dis.forward(req, resp);
			}
			break;
			
			
/*---------------------------------------------------------------------------------------------------*/
			
		case "/main":
				System.out.println("main page!!!");
				// 1. 파라메터 확인(X)
				// 2. 서비스 사용 여부
				// 3. DAO 사용 여부 -> 쿼리 준비 -> 실행 -> 결과값 가져오기 -> 서비스에 보내기
				// 4. 서비스에서 받은 내용을 view로 보내기
				ArrayList<MemberDTO> list = service.memberList();
				req.setAttribute("list", list);
				dis = req.getRequestDispatcher("main.jsp");
				dis.forward(req, resp);
			break;
			
/*---------------------------------------------------------------------------------------------------*/
			
		case "/memberDel":
				id = req.getParameter("id");
				System.out.println(id);
				page = "main";
				msg = "삭제 실패!";
				if(service.memberDel(id)) {
					msg = "삭제 성공!";
				}
				req.setAttribute("msg", msg);
				dis = req.getRequestDispatcher(page);
				dis.forward(req, resp);
			break;
			
/*---------------------------------------------------------------------------------------------------*/
			
		case "/memberInfo":					
			id = req.getParameter("id");
			if(id == null) { // 보낼 때 setAttribute 로 넣었으므로 getAttribute 로 가져온다.
				id = (String) req.getAttribute("id");
			}
			System.out.println(id);
			MemberDTO dto = service.detail(id);
			req.setAttribute("info", dto);
			dis = req.getRequestDispatcher("detail.jsp");
			dis.forward(req, resp);
			
			break;			
/*---------------------------------------------------------------------------------------------------*/		
	
		case "/update":
			// 1. 파라메터 확인
			req.setCharacterEncoding("UTF-8");
			id = req.getParameter("userId");
			pw = req.getParameter("userPass");
			name = req.getParameter("userName");
			age = Integer.parseInt(req.getParameter("age"));
			gender = req.getParameter("gender");
			email = req.getParameter("email");
			System.out.println(id);
			// 2. 서비스에 요청 DAO 요청
			// 3. 서비스로부터 결과가 오면...
			msg = "수정에 실패했습니다.";
			//page = "memberInfo?id="+id;
			page = "memberInfo";
			if(service.update(id, pw, name, age, gender, email)) {
				// 4. 성공, 실패 : memberInfo
				msg = "수정에 성공했습니다.";
			}
			req.setAttribute("msg", msg);
			req.setAttribute("id", id);
			
			dis = req.getRequestDispatcher(page);
			dis.forward(req, resp);
			
			// 5. 메시지 추가: 성공 - 수정에 성공했습니다. / 실패 - 수정에 실패했습니다.
			// 6. 보내기
			break;
		}
		

		
		
		
	}

}
