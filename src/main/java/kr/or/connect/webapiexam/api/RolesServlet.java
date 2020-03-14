package kr.or.connect.webapiexam.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.jdbcexam.dao.RoleDao;
import kr.or.connect.jdbcexam.dto.Role;


@WebServlet(description = "/roles", urlPatterns = { "/RolesServlet" })
public class RolesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public RolesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8"); //응답객체의 인코딩 설정 
		response.setContentType("application/json"); //json 타입으로 설정

		RoleDao dao = new RoleDao();

		List<Role> list = dao.getRoles(); //dao 객체를 이용해 role 테이블의 모든 튜플을 리스트로 뽑아옴.

		ObjectMapper objectMapper = new ObjectMapper(); //objectMapper 객체를 json으로 바꾸거나, json을 객체로 만들 수 있음
		String json = objectMapper.writeValueAsString(list); //튜플 리스트가 json으로 변환됨.

		PrintWriter out = response.getWriter();
		out.println(json); //json으로 변환된 데이터 전송.
		out.close();
	}

}
