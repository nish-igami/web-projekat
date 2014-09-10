package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Role;
import model.UserBean;

import communication.CommandMessage;

/**
 * Servlet implementation class for Servlet: LoginServlet
 * 
 */
//@WebServlet("/LoginServlet");
public class RegisterServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1132456713082687859L;

	private final int TCP_PORT = 9000;
	private String hostname = "localhost";
	
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
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean role = request.getParameter("admin") != null;
		
		String roleToString = null;
		if (role) { roleToString = "ADMIN"; } else { roleToString = "SHOPER"; }
		
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		UserBean user = new UserBean();
		
		if (username != null && password != null) {
				// uname i passwd postoje,
				// pa cemo pokusati da kreiramo korisnika i
				// da odemo na stranicu login.jsp
				if (role) {
					user.setRole(Role.ADMIN);
				} else {
					user.setRole(Role.SHOPER);
				}
				user.setUsername(username);
				user.setPassword(password);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setPhone(phone);
				user.setEmail(email);
				
				String retVal = "failed";
				
				try {

					// odredi adresu racunara sa kojim se povezujemo
					InetAddress addr = InetAddress.getByName(hostname);

					// otvori socket prema drugom racunaru
					Socket sock = new Socket(addr, TCP_PORT);
					
					// inicijalizuj izlazni stream
					ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());

					// inicijalizuj ulazni stream
					ObjectInputStream in = new ObjectInputStream(sock.getInputStream());	
					
					CommandMessage requestMessage = new CommandMessage();
					requestMessage.setCommand("registerUser");
					requestMessage.setObject((Object) user);			

					// posalji zahtev
					out.writeObject(requestMessage);
					
					// procitaj odgovor
					CommandMessage responseMessage = (CommandMessage) in.readObject();
								
					in.close();
					out.close();
					sock.close();
					
					retVal = responseMessage.getCommand();
					
					// zatvori konekciju
					
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				RequestDispatcher disp = getServletContext()
						.getRequestDispatcher("/login.jsp");
				disp.forward(request, response);
			} 
		
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
