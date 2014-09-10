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

import communication.CommandMessage;
import model.CategoryBean;
import model.ComponentBean;
import model.DeviceBean;

public class CreateDeviceServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1132456713082687859L;

	private final int TCP_PORT = 9000;
	private String hostname = "localhost";

	public CreateDeviceServlet() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		ServletContext context = getServletContext();
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String[] componentsNames = request.getParameterValues("components");

		DeviceBean device = new DeviceBean();

		if (name != null && !name.isEmpty()) {

			device.setName(name);
			device.setDescription(description);
			
			
			if (componentsNames != null) {
				context.log("Selektovane komponente : " + componentsNames.length);
				for (String component : componentsNames) {
					context.log(component);
				}
				context.log("Pocinje trazenje komponenti za listu");
				ArrayList<ComponentBean> components = (ArrayList<ComponentBean>) session.getAttribute("components");
				context.log("Session components su : " + components.size());
				ArrayList<ComponentBean> temp = new ArrayList<ComponentBean>();
				
				if (componentsNames.length > 0) {
					for (String componentName : componentsNames) {
						for (ComponentBean c : components) {
							if (c.getName() != null) {
								if (c.getName().equals(componentName)) {
									temp.add(c);
									context.log("Dodata komponenta : " + c.getName() + "\n");
								}
							}
						}
					}
				}
				
				context.log("Pocinje upisivanje komponenti u listu");
				device.setComponents(temp);
				context.log("Zavrseno upisivanje komponenti u listu");
			}

			String retVal = "failed";

			try {

				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);

				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

				CommandMessage requestMessage = new CommandMessage();
				requestMessage.setCommand("createDevice");
				requestMessage.setObject((Object) device);

				out.writeObject(requestMessage);

				CommandMessage responseMessage = (CommandMessage) in.readObject();

				retVal = responseMessage.getCommand();

				in.close();
				out.close();
				sock.close();
				
				RequestDispatcher disp = getServletContext().getRequestDispatcher("MasterPrepareServlet?action=displayDevices");
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
		
		RequestDispatcher disp = getServletContext().getRequestDispatcher(
				"/displayDevices.jsp");
		disp.forward(request, response);
		return;

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
