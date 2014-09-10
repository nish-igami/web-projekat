package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CategoryBean;
import model.ComponentBean;
import model.DeviceBean;
import communication.CommandMessage;

public class MasterPrepareServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1132456713082687859L;

	private final int TCP_PORT = 9000;
	private String hostname = "localhost";
	
	public MasterPrepareServlet() {
		super();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession session = request.getSession();
		CommandMessage requestMessage = new CommandMessage();
		
		String action = request.getParameter("action");
				
		if (action.equals("displayDevices")) {
			try {
					
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
			
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
							
				requestMessage.setCommand("currentDevices");			
				out.writeObject(requestMessage);
				
				CommandMessage responseMessage = (CommandMessage) in.readObject();
				String retVal = responseMessage.getCommand();
			
				in.close();
				out.close();
				sock.close();
				
				if (retVal.equals("success")) {
					session.setAttribute("devices", (ArrayList<DeviceBean>) responseMessage.getObject());
				}
				
				RequestDispatcher disp = getServletContext().getRequestDispatcher("/displayDevices.jsp");
				disp.forward(request, response);
				return;
			
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if (action.equals("displayComponents")) {
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
				
				if (retVal.equals("success")) {
					session.setAttribute("components", (ArrayList<ComponentBean>) responseMessage.getObject());
				}
				
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
		} else if (action.equals("displayCategories")) {
			try {
					
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
			
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
							
				requestMessage.setCommand("currentCategories");			
				out.writeObject(requestMessage);
				
				CommandMessage responseMessage = (CommandMessage) in.readObject();
				String retVal = responseMessage.getCommand();
			
				in.close();
				out.close();
				sock.close();
				
				if (retVal.equals("success")) {
					session.setAttribute("categories", (ArrayList<ComponentBean>) responseMessage.getObject());
				}
				
				RequestDispatcher disp = getServletContext().getRequestDispatcher("/displayCategories.jsp");
				disp.forward(request, response);
				return;
			
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if (action.equals("editCategory")) {
			try {
					
				String categoryID = request.getParameter("id");
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
			
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
							
				requestMessage.setCommand("getCategory");	
				requestMessage.setObject((String) categoryID);
				out.writeObject(requestMessage);
				
				CommandMessage responseMessage = (CommandMessage) in.readObject();
				String retVal = responseMessage.getCommand();
			
				in.close();
				out.close();
				sock.close();
				
				if (retVal.equals("success")) {
					session.setAttribute("category", (CategoryBean) responseMessage.getObject());
				}
				
				editCategory((CategoryBean) responseMessage.getObject(), request, response);

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if (action.equals("deleteCategory")) {
			try {
					
				String categoryID = request.getParameter("id");
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
			
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
							
				requestMessage.setCommand("deleteCategory");	
				requestMessage.setObject((String) categoryID);
				out.writeObject(requestMessage);
				
				CommandMessage responseMessage = (CommandMessage) in.readObject();
				String retVal = responseMessage.getCommand();
			
				in.close();
				out.close();
				sock.close();
				
				if (retVal.equals("success")) {
					session.setAttribute("categories", (ArrayList<CategoryBean>) responseMessage.getObject());
				}
				
				RequestDispatcher disp = getServletContext().getRequestDispatcher("/displayCategories.jsp");
				disp.forward(request, response);
				return;

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if (action.equals("editComponent")) {
			try {
					
				String componentID = request.getParameter("id");
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
			
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
							
				requestMessage.setCommand("getComponent");	
				requestMessage.setObject((String) componentID);
				out.writeObject(requestMessage);
				
				CommandMessage responseMessage = (CommandMessage) in.readObject();
				String retVal = responseMessage.getCommand();
			
				in.close();
				out.close();
				sock.close();
				
				if (retVal.equals("success")) {
					session.setAttribute("component", (ComponentBean) responseMessage.getObject());
				}
				
				editComponent((ComponentBean) responseMessage.getObject(), request, response);

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if (action.equals("deleteComponent")) {
			try {
					
				String componentID = request.getParameter("id");
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
			
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
							
				requestMessage.setCommand("deleteComponent");	
				requestMessage.setObject((String) componentID);
				out.writeObject(requestMessage);
				
				CommandMessage responseMessage = (CommandMessage) in.readObject();
				String retVal = responseMessage.getCommand();
			
				in.close();
				out.close();
				sock.close();
				
				if (retVal.equals("success")) {
					session.setAttribute("components", (ArrayList<ComponentBean>) responseMessage.getObject());
				}
				
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
		} else if (action.equals("editDevice")) {
			try {
					
				String deviceID = request.getParameter("id");
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
			
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
							
				requestMessage.setCommand("getDevice");	
				requestMessage.setObject((String) deviceID);
				out.writeObject(requestMessage);
				
				CommandMessage responseMessage = (CommandMessage) in.readObject();
				String retVal = responseMessage.getCommand();
			
				in.close();
				out.close();
				sock.close();
				
				if (retVal.equals("success")) {
					session.setAttribute("device", (DeviceBean) responseMessage.getObject());
				}
				
				editDevice((DeviceBean) responseMessage.getObject(), request, response);

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if (action.equals("deleteDevice")) {
			try {
					
				String deviceID = request.getParameter("id");
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
			
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
							
				requestMessage.setCommand("deleteDevice");	
				requestMessage.setObject((String) deviceID);
				out.writeObject(requestMessage);
				
				CommandMessage responseMessage = (CommandMessage) in.readObject();
				String retVal = responseMessage.getCommand();
			
				in.close();
				out.close();
				sock.close();
				
				if (retVal.equals("success")) {
					session.setAttribute("devices", (ArrayList<DeviceBean>) responseMessage.getObject());
				}
				
				RequestDispatcher disp = getServletContext().getRequestDispatcher("/displayDevices.jsp");
				disp.forward(request, response);
				return;

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if (action.equals("search")) {
			try {
					
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
			
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
							
				requestMessage.setCommand("prepareSearch");			
				out.writeObject(requestMessage);
				
				CommandMessage responseMessage = (CommandMessage) in.readObject();
				String retVal = responseMessage.getCommand();
			
				in.close();
				out.close();
				sock.close();
				
				StringTokenizer st = new StringTokenizer(retVal);
				int firstSize = Integer.parseInt(st.nextToken().trim());
				int secondSize = Integer.parseInt(st.nextToken().trim());
				int thirdSize = Integer.parseInt(st.nextToken().trim());
				
				session.setAttribute("components", ((ArrayList<ComponentBean>) responseMessage.getObject()).subList(0, firstSize));
				session.setAttribute("categories", ((ArrayList<DeviceBean>) responseMessage.getObject()).subList(firstSize, firstSize + secondSize));
				session.setAttribute("devices", ((ArrayList<ArrayList>) responseMessage.getObject()).subList(firstSize + secondSize, secondSize + thirdSize));
				
				RequestDispatcher disp = getServletContext().getRequestDispatcher("/search.jsp");
				disp.forward(request, response);
				return;
			
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if (action.equals("buy")) {
			try {
					
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
			
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
							
				requestMessage.setCommand("prepareSearch");			
				out.writeObject(requestMessage);
				
				CommandMessage responseMessage = (CommandMessage) in.readObject();
				String retVal = responseMessage.getCommand();
				
				in.close();
				out.close();
				sock.close();
				
				StringTokenizer st = new StringTokenizer(retVal);
				int firstSize = Integer.parseInt(st.nextToken().trim());
				int secondSize = Integer.parseInt(st.nextToken().trim());
				
				session.setAttribute("components", ((ArrayList<ComponentBean>) responseMessage.getObject()).subList(0, firstSize));
				session.setAttribute("devices", ((ArrayList<DeviceBean>) responseMessage.getObject()).subList(firstSize, firstSize + secondSize));
				
				
				RequestDispatcher disp = getServletContext().getRequestDispatcher("/buy.jsp");
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
		
	}

	@SuppressWarnings("unchecked")
	private void editCategory(CategoryBean object, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
			HttpSession session = request.getSession();
			CommandMessage requestMessage = new CommandMessage();
			
			InetAddress addr = InetAddress.getByName(hostname);
			Socket sock = new Socket(addr, TCP_PORT);
		
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
						
			requestMessage.setCommand("currentCategories");			
			out.writeObject(requestMessage);
			
			CommandMessage responseMessage = (CommandMessage) in.readObject();
			String retVal = responseMessage.getCommand();
		
			in.close();
			out.close();
			sock.close();
			
			if (retVal.equals("success")) {
				session.setAttribute("categories", (ArrayList<CategoryBean>) responseMessage.getObject());
			}
			
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/editCategory.jsp");
			disp.forward(request, response);
			return;
		
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	private void editComponent(ComponentBean object,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			
			HttpSession session = request.getSession();
			CommandMessage requestMessage = new CommandMessage();
			
			InetAddress addr = InetAddress.getByName(hostname);
			Socket sock = new Socket(addr, TCP_PORT);
		
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
						
			requestMessage.setCommand("currentCategories");			
			out.writeObject(requestMessage);
			
			CommandMessage responseMessage = (CommandMessage) in.readObject();
			String retVal = responseMessage.getCommand();
		
			in.close();
			out.close();
			sock.close();
			
			if (retVal.equals("success")) {
				session.setAttribute("categories", (ArrayList<CategoryBean>) responseMessage.getObject());
			}
			
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/editComponent.jsp");
			disp.forward(request, response);
			return;
		
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	private void editDevice(DeviceBean device, HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
				
			HttpSession session = request.getSession();
			CommandMessage requestMessage = new CommandMessage();
			
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
			
			if (retVal.equals("success")) {
				session.setAttribute("components", (ArrayList<ComponentBean>) responseMessage.getObject());
			}
			
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/editDevice.jsp");
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

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}




/* package controller;

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

public class MasterPrepareServlet extends HttpServlet implements Servlet {

	private final int TCP_PORT = 9000;
	private String hostname = "localhost";
	
	public MasterPrepareServlet() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession session = request.getSession();
		CommandMessage requestMessage = new CommandMessage();
		
		String action = request.getParameter("action");
				
		if (action.equals("displayCategories")) {
			try {
					
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
			
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
							
				requestMessage.setCommand("currentCategories");			
				out.writeObject(requestMessage);
				
				CommandMessage responseMessage = (CommandMessage) in.readObject();
				String retVal = responseMessage.getCommand();
			
				in.close();
				out.close();
				sock.close();
												
				session.setAttribute("categories", (ArrayList<CategoryBean>) responseMessage.getObject());
			
				RequestDispatcher disp = getServletContext().getRequestDispatcher("/displayCategories.jsp");
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
		
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}

*/