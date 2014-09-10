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
import model.DeviceBean;
import model.Role;
import model.UserBean;

public class MasterServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1132456713082687859L;

	private final int TCP_PORT = 9000;
	private String hostname = "localhost";

	public MasterServlet() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession session = request.getSession();
		CommandMessage requestMessage = new CommandMessage();
		
		String action = request.getParameter("action");
				
		if (action.equals("editedDevice")) {
			try {
				String deviceID = request.getParameter("id");
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				String[] componentsNames = request.getParameterValues("components");

				DeviceBean device = new DeviceBean();

				device.setId(Integer.parseInt(deviceID.trim()));
				device.setName(name);
				device.setDescription(description);
								
				if (componentsNames != null) {
					ArrayList<ComponentBean> components = (ArrayList<ComponentBean>) session.getAttribute("components");
					ArrayList<ComponentBean> temp = new ArrayList<ComponentBean>();
						
					if (componentsNames.length > 0) {
						for (String componentName : componentsNames) {
							for (ComponentBean c : components) {
								if (c.getName() != null) {
									if (c.getName().equals(componentName)) {
										temp.add(c);
									}
								}
							}
						}
					}
					
					device.setComponents(temp);
				}

				String retVal = "failed";

				try {

					InetAddress addr = InetAddress.getByName(hostname);
					Socket sock = new Socket(addr, TCP_PORT);

					ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
					ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

					requestMessage.setCommand("editDevice");
					requestMessage.setObject((Object) device);

					out.writeObject(requestMessage);

					CommandMessage responseMessage = (CommandMessage) in.readObject();

					retVal = responseMessage.getCommand();

					in.close();
					out.close();
					sock.close();
						
					RequestDispatcher disp = getServletContext().getRequestDispatcher("/MasterPrepareServlet?action=displayDevices");
					disp.forward(request, response);
					return;
						
						
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
			} finally {}
			
		} else if (action.equals("editedComponent")) {
			
				String componentID = request.getParameter("id");
				String name = request.getParameter("name");
				String price = request.getParameter("price");
				String quantity = request.getParameter("quantity");
				String description = request.getParameter("description");
				String category = request.getParameter("category");
				String homepage = request.getParameter("homepage");
				String picture = request.getParameter("picture");

				ComponentBean component = new ComponentBean();
				
				requestMessage = new CommandMessage();
					
				component.setId(Integer.parseInt(componentID.trim()));
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
			
					requestMessage.setCommand("editComponent");
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
			
						RequestDispatcher disp = getServletContext().getRequestDispatcher("/MasterPrepareServlet?action=displayComponents");
						disp.forward(request, response);
						return;
					}
						
					in.close();
					out.close();
					sock.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {}
									
		} else if (action.equals("editedCategory")) {
			try {
				String categoryID = request.getParameter("id");
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				
				CategoryBean category = new CategoryBean();

				category.setId(Integer.parseInt(categoryID.trim()));
				category.setName(name);
				category.setDescription(description);
							

				String retVal = "failed";

				try {

					InetAddress addr = InetAddress.getByName(hostname);
					Socket sock = new Socket(addr, TCP_PORT);

					ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
					ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

					requestMessage.setCommand("editCategory");
					requestMessage.setObject((Object) category);

					out.writeObject(requestMessage);

					CommandMessage responseMessage = (CommandMessage) in.readObject();

					retVal = responseMessage.getCommand();

					in.close();
					out.close();
					sock.close();
						
					RequestDispatcher disp = getServletContext().getRequestDispatcher("/MasterPrepareServlet?action=displayCategories");
					disp.forward(request, response);
					return;
						
						
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
			} finally {}
		}
	}	
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
