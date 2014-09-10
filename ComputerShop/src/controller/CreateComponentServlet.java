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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CategoryBean;
import model.ComponentBean;
import communication.CommandMessage;

public class CreateComponentServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1132456713082687859L;

	private final int TCP_PORT = 9000;
	private String hostname = "localhost";

	public CreateComponentServlet() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ServletContext context = getServletContext();
		HttpSession session = request.getSession();
		
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String quantity = request.getParameter("quantity");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		String homepage = request.getParameter("homepage");
		String picture = request.getParameter("picture");

		ComponentBean component = new ComponentBean();
		
		CommandMessage requestMessage = new CommandMessage();

		context.log("\n name: " + name 
				  + "\n price: " + Double.parseDouble(price.trim())
				  + "\n quantity: " + Integer.parseInt(quantity.trim())
				  + "\n description: " + description
				  + "\n category: " + category
				  + "\n homepage: " + homepage
				  + "\n picture: " + picture);
		
		
		if (name != null) {
			
			component.setName(name);
			component.setDescription(description);
			component.setHomeURL(homepage);
			component.setPictureURL(picture);	
			
			if (!price.equals(null)) {
				component.setPrice(Double.parseDouble(price.trim()));
			} else {
				component.setPrice(0);
			}
			
			if (!quantity.equals(null) && !quantity.isEmpty()) {
				component.setAvailableQuantity(Integer.parseInt(quantity.trim()));
			}
			
			for (CategoryBean c : (ArrayList<CategoryBean>) session.getAttribute("categories")) {
				if (c.getName().equals(category)) {
					component.setCategory(c);
				}
			}
				
			String retVal = "failed";
	
			try {
	
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
	
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
	
				requestMessage.setCommand("createComponent");
				requestMessage.setObject((Object) component);
	
				out.writeObject(requestMessage);
	
				CommandMessage responseMessage = (CommandMessage) in.readObject();
					
				retVal = responseMessage.getCommand();
	
				if (retVal.equals("success")) {
					
					requestMessage.setCommand("currentComponents");			
					out.writeObject(requestMessage);
					responseMessage = (CommandMessage) in.readObject();
					
					in.close();
					out.close();
					sock.close();
										
					session.setAttribute("components", (ArrayList<ComponentBean>) responseMessage.getObject());
	
					RequestDispatcher disp = getServletContext().getRequestDispatcher("/displayComponents.jsp");
					disp.forward(request, response);
					return;
				}
				
				in.close();
				out.close();
				sock.close();
				
				RequestDispatcher disp = getServletContext().getRequestDispatcher(
						"/createComponent.jsp");
				disp.forward(request, response);
	
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				RequestDispatcher disp = getServletContext().getRequestDispatcher(
						"/welcome.jsp");
				disp.forward(request, response);
			}

		}
		
		RequestDispatcher disp = getServletContext().getRequestDispatcher(
				"/welcome.jsp");
		disp.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
