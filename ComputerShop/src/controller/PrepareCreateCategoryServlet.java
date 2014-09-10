package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import communication.CommandMessage;
import model.CategoryBean;
import model.Role;
import model.UserBean;

/**
 * Servlet implementation class for Servlet: LoginServlet
 * 
 */
//@WebServlet("/LoginServlet");
public class PrepareCreateCategoryServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1132456713082687859L;

	private final int TCP_PORT = 9000;
	private String hostname = "localhost";
	

	public PrepareCreateCategoryServlet() {
		super();
	}

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		UserBean user = (UserBean) session.getAttribute("user");
		
		Role role = user.getRole();
		
				
		if (false/*(role == null) || (role != Role.ADMIN )*/) {
			RequestDispatcher disp = getServletContext()
					.getRequestDispatcher("/loginAdmin.jsp");
			disp.forward(request, response);
			
		} else if (true/*role == Role.ADMIN*/) {
				ArrayList<CategoryBean> categories = new ArrayList<CategoryBean>();				
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
					requestMessage.setCommand("currentCategories");			

					out.writeObject(requestMessage);

					CommandMessage responseMessage = (CommandMessage) in.readObject();
								
					in.close();
					out.close();
					sock.close();
					
					retVal = responseMessage.getCommand();
					if (retVal.equals("success")) {
						categories = (ArrayList<CategoryBean>) responseMessage.getObject();
					} else {
						categories = null;
					}
					session.setAttribute("categories", categories);
					// zatvori konekciju
					
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				RequestDispatcher disp = getServletContext()
						.getRequestDispatcher("/createCategory.jsp");
				disp.forward(request, response); 
		} else {
			RequestDispatcher disp = getServletContext()
					.getRequestDispatcher("/index.jsp");
			disp.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
