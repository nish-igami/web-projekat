package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import model.Role;
import model.UserBean;

/**
 * Servlet implementation class for Servlet: LoginServlet
 * 
 */
//@WebServlet("/LoginServlet");
public class RegisterServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1132456713082687859L;

	static Logger log = Logger.getLogger(RegisterServlet.class.getName());
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("Hello this is an debug message");
	    log.info("Hello this is an info message");
		
	    //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bootstrapLogin.jsp");
		//dispatcher.forward(request, response);
		
	/*	HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean role = request.getParameter("admin") != null;
		
		String roleToString = null;
		if (role) { roleToString = "ADMIN"; } else { roleToString = "SHOPER"; }
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String age = request.getParameter("age");
		
		UserBean user = new UserBean();*/
		
	/*	if (email != null && password != null) {
			
				DBMS.getInstance().loadDBMS();
				// uname i passwd postoje,
				// pa cemo pokusati da kreiramo korisnika i
				// da odemo na stranicu login.jsp
				if (role) {
					user.setRole(Role.ADMIN);
				} else {
					user.setRole(Role.SHOPER);
				}
				user.setEmail(email);
				user.setPassword(password);
				user.setFirstName(name);
				user.setLastName(address);
				user.setPhone(age);
							
				if (DBMS.getInstance().getUsersDatabase().contains(user)) {
					RequestDispatcher disp = getServletContext()
							.getRequestDispatcher("/login.jsp");
					disp.forward(request, response);
				}
					
					// Ili ovo, ili DBMS.addUser..
					
				//	for (UserBean tempUser : DBMS.getInstance().getUsersDatabase()) {
				//		if (tempUser.getUsername().equals(user.getUsername())) {
				//			return retVal;
				//		}
				//	}
					
					user.setId(DBMS.getInstance().getUserID());
					DBMS.getInstance().getUsersDatabase().add(user);
					DBMS.getInstance().increaseUserID();
					DBMS.getInstance().saveUsers();
					DBMS.getInstance().saveDBMS();
					
					System.out.println("\t=> Kreiran korisnik - " + user.getUsername());
					
					
				RequestDispatcher disp = getServletContext()
						.getRequestDispatcher("/bootstrapLogin.jsp");
				disp.forward(request, response);
			} */
		//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
		//dispatcher.forward(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
