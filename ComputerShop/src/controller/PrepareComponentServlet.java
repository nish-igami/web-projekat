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

public class PrepareComponentServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1132456713082687859L;

	private final int TCP_PORT = 9000;
	private String hostname = "localhost";
	
	public PrepareComponentServlet() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession session = request.getSession();
		CommandMessage requestMessage = new CommandMessage();
		
		String action = request.getParameter("action");
				
		if (action.equals("displayComponents")) {
			try {
					
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
			
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
							
				requestMessage.setCommand("currentComponents");			
				out.writeObject(requestMessage);
				
				CommandMessage responseMessage = (CommandMessage) in.readObject();
				String retVal = responseMessage.getCommand();
			
				in.close();
				out.close();
				sock.close();
												
				session.setAttribute("components", (ArrayList<ComponentBean>) responseMessage.getObject());
			
				RequestDispatcher disp = getServletContext().getRequestDispatcher("/displayComponents.jsp");
				disp.forward(request, response);
				return;
			
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}	
		
		RequestDispatcher disp = getServletContext().getRequestDispatcher("/createComponent.jsp");
		disp.forward(request, response); 
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
