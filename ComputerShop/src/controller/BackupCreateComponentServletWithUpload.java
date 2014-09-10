package controller;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.CategoryBean;
import model.ComponentBean;
import model.UploadedFileBean;
import communication.CommandMessage;

public class BackupCreateComponentServletWithUpload extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1132456713082687859L;

	private final int TCP_PORT = 9000;
	private String hostname = "localhost";

	public BackupCreateComponentServletWithUpload() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		ComponentBean component = new ComponentBean();
		CommandMessage requestMessage = new CommandMessage();

		this.getServletContext().setAttribute("javax.servlet.context.tempdir", this.getServletContext().getContextPath() + "/temp_pics");
		/*if (num != null && num.length( ) != 0) {
      try {
        n = Integer.parseInt(num);
      } catch (NumberFormatException e) {
        out.println("<P>I didn't think much of ");
        out.println(num);
        out.println(" as a number.</P>");
      }
    }*/
		
		String retVal = "failed";
		
		try {

			InetAddress addr = InetAddress.getByName(hostname);
			Socket sock = new Socket(addr, TCP_PORT);

			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	
			if (isMultipart) {
			
				// Create a factory for disk-based file items
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				// Configure a repository (to ensure a secure temp location is used)
				ServletContext servletContext = this.getServletConfig().getServletContext();
				File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				
				//System.out.println("document root is: "	+ new File(".").getAbsolutePath() + "\n");
				
				factory.setRepository(repository);
				
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				// Parse the request
				List<FileItem> items = upload.parseRequest(request);
				
				// Process the uploaded items
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
				    FileItem item = iter.next();
				
				    // Process a regular form field
					if (item.isFormField()) {
					    String name = item.getFieldName();
					    	switch (name) {
					    		case "name" : if(item.getString() != null) component.setName(item.getString()); break;
					    		case "price" : if(item.getString() != null) component.setPrice(Double.parseDouble(item.getString().trim())) ;break;
					    		case "quantity" : if(item.getString() != null) component.setAvailableQuantity(Integer.parseInt(item.getString().trim())); break;
					    		case "description" : if(item.getString() != null) component.setDescription(item.getString()); break;
					    		case "category" : if(item.getString() != null) 
					    			for (CategoryBean c : (ArrayList<CategoryBean>) session.getAttribute("categories")) {
										if (c.getName().equals(item.getString())) {
											component.setCategory(c);
											return;
										}
					    			}; break;
					    		case "homepage" : if(item.getString() != null) component.setHomeURL(item.getString()); break;
					    	}
					    
					} else 
						// Process a file upload
					if (!item.isFormField()) {
					    String fileName = item.getName();
					    
					    String root = getServletContext().getRealPath("/");
						
						UploadedFileBean fileForSending = new UploadedFileBean();
						
						File path = new File(root + "/uploads");
						if (!path.exists()) {
							@SuppressWarnings("unused")
							boolean status = path.mkdirs();
						}

						File uploadedFile = new File(path + "/" + fileName);
						item.write(uploadedFile);
																		
						fileForSending.setFile(uploadedFile);
						fileForSending.setFileName(fileName);
						fileForSending.setFilePath(path.getAbsolutePath());
						
						requestMessage.setObject((Object) fileForSending);
						requestMessage.setCommand("savePicture");
						out.writeObject(requestMessage);
						CommandMessage responseMessage = (CommandMessage) in.readObject();
					    
						component.setPictureURL((String) responseMessage.getObject());
					}
				}
			}			
	
				requestMessage.setCommand("createComponent");
				requestMessage.setObject((Object) component);
	
				out.writeObject(requestMessage);
	
				CommandMessage responseMessage = (CommandMessage) in.readObject();
	
				in.close();
				out.close();
				sock.close();
	
				retVal = responseMessage.getCommand();
	
				if (retVal.equals("success")) {
					
					ArrayList<ComponentBean> components = new ArrayList<ComponentBean>();
					
					if (session.getAttribute("components") != null) {
						components = (ArrayList<ComponentBean>) session.getAttribute("components");
					} 
					
					components.add(component);
					session.setAttribute("components", components);
	
					RequestDispatcher disp = getServletContext().getRequestDispatcher("/displayComponents.jsp");
					disp.forward(request, response);
					return;
				}
				
				RequestDispatcher disp = getServletContext().getRequestDispatcher(
						"/welcome.jsp");
				disp.forward(request, response);
						
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
