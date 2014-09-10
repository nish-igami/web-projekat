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

public class CreateCategoryServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1132456713082687859L;

	private final int TCP_PORT = 9000;
	private String hostname = "localhost";

	public CreateCategoryServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String subCategoryName = request.getParameter("subCategory");

		CategoryBean category = new CategoryBean();

		if ((name != null) || (!"".equals(name))) {

			category.setName(name);
			category.setDescription(description);
			category.setSubcategory(null);
			
			if ((null != subCategoryName) || (!"notSub".equals(subCategoryName))) {
				@SuppressWarnings("unchecked")
				ArrayList<CategoryBean> categories = (ArrayList<CategoryBean>) session
						.getAttribute("categories");
				for (CategoryBean c : categories) {
					if (c.getName().equals(subCategoryName)) {
						category.setSubcategory(c);
					}
				}
			}

			String retVal = "failed";

			try {

				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);

				ObjectOutputStream out = new ObjectOutputStream(
						sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(
						sock.getInputStream());

				CommandMessage requestMessage = new CommandMessage();
				requestMessage.setCommand("createCategory");
				requestMessage.setObject((Object) category);

				out.writeObject(requestMessage);

				CommandMessage responseMessage = (CommandMessage) in
						.readObject();

				in.close();
				out.close();
				sock.close();

				retVal = responseMessage.getCommand();

				if (retVal.equals("success")) {
					@SuppressWarnings("unchecked")
					ArrayList<CategoryBean> categories = (ArrayList<CategoryBean>) session
							.getAttribute("categories");
					categories.add(category);
					session.setAttribute("categories", categories);
					
					RequestDispatcher disp = getServletContext()
							.getRequestDispatcher("/displayCategories.jsp");
					disp.forward(request, response);
					return;
				}

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher disp = getServletContext().getRequestDispatcher(
				"/createCategory.jsp");
		disp.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
