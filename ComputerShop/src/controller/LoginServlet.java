package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserBean;

/**
 * Servlet implementation class for Servlet: LoginServlet
 * 
 */
public class LoginServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1132456713082687859L;


	public LoginServlet() {
		super();
	}


	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String logoff = request.getParameter("logoff");
		
		if ((username.equals(null) || username.isEmpty())) {
			// nisu uneti username ili password
			RequestDispatcher disp = request.getRequestDispatcher("/login.jsp");
			disp.forward(request, response);
			return;
		}
		
		//UserBean user = (UserBean) session.getAttribute("user");
		UserBean user = new UserBean();
		
		
		if (user != null) {
			if (logoff != null && logoff.equals("true")) {
				// ako je poslat parametar za logoff, odjavimo se
				user.setUsername(username);
				user.logout();
				session.setAttribute("user", user);
				
				// i odemo na login stranicu
				RequestDispatcher disp = request
						.getRequestDispatcher("/login.jsp");
				
				// redirektovacemo na login stranicu
				disp.forward(request, response);
				return;
			}
			if (username != null && password != null) {
				// user session bean postoji, uname i passwd postoje,
				// pa cemo pokusati da se prijavimo i
				// da odemo na stranicu results.jsp
				user.setUsername(username);
				user.setPassword(password);
				
				// TODO
				/* ovde setovati sve userove atribute.. ili ovde ili u login
				 * 
				 */
				user.login(session);
										
				// ovo je takodje legalno:
				// apsolutna putanja se u ovom slucaju tretira kao apsolutna
				// unutar konteksta.
				// RequestDispatcher disp =
				// request.getRequestDispatcher("/primer06/results.jsp");
				// ovako dobijen dispecer MORA da ima URL sa apsolutnom putanjom
				RequestDispatcher disp = getServletContext()
						.getRequestDispatcher("/results.jsp");
				// redirektovacemo na results stranicu bez obzira da li je
				// uspelo logovanje ili ne
				disp.forward(request, response);
			} else {
				// ne postoji uname ili passwd, pa idemo na login
				// redirektovacemo ga relativno u odnosu na url pozvanog
				// servleta
				RequestDispatcher disp = request
						.getRequestDispatcher("/login.jsp");
				// redirektovacemo na results stranicu bez obzira da li je
				// uspelo logovanje ili ne
				disp.forward(request, response);
			}
		} else {
			// ako user ne postoji, neko je pokusao direktno da gadja ovaj
			// servlet

			// Ovaj RequestDispatcher je dobijen preko getContext metode koja
			// zahteva
			// da se podesi crossContext="true" u context tagu ovog web modula:
			// <Context path="/kurs" docBase="kurs" crossContext="true"
			// debug="0" reloadable="true" privileged="true" />
			// na ovaj nacin se zapravo omogucuje da se iz jednog konteksta
			// pozivaju servleti i jsp stranice
			// drugog konteksta
			// Ovako dobijen dispecer MORA da ima URL sa apsolutnom putanjom
			// RequestDispatcher disp =
			// getServletContext().getContext("/kurs").getRequestDispatcher
			// ("/primer06/login.jsp");
			RequestDispatcher disp = request
					.getRequestDispatcher("/login.jsp");
			// redirektovacemo na login stranicu
			disp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
