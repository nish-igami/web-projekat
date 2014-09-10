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
import model.ComponentBean;
import model.Role;
import model.UserBean;


public class PrepareCreateDeviceServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1132456713082687859L;

	private final int TCP_PORT = 9000;
	private String hostname = "localhost";
	

	public PrepareCreateDeviceServlet() {
		super();
	}

	@SuppressWarnings({ "unused", "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		UserBean user = (UserBean) session.getAttribute("user");
		
		Role role = user.getRole();
				
		if ((role == null) || (role != Role.ADMIN )) {
			RequestDispatcher disp = getServletContext()
					.getRequestDispatcher("/loginAdmin.jsp");
			disp.forward(request, response);
			
		} else if (role == Role.ADMIN) {
				ArrayList<ComponentBean> components = new ArrayList<ComponentBean>();				
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
					requestMessage.setCommand("currentComponents");			

					out.writeObject(requestMessage);

					CommandMessage responseMessage = (CommandMessage) in.readObject();
								
					in.close();
					out.close();
					sock.close();
					
					retVal = responseMessage.getCommand();
					
					session.setAttribute("components", (ArrayList<ComponentBean>) responseMessage.getObject());
					
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				RequestDispatcher disp = getServletContext()
						.getRequestDispatcher("/createDevice.jsp");
				disp.forward(request, response); 
		} else {
			RequestDispatcher disp = getServletContext()
					.getRequestDispatcher("/welcome.jsp");
			disp.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
