package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.servlet.http.HttpSession;

import communication.CommandMessage;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 7197465866238550820L;

	private Role role;
	
	private int id;           // unique
	private String username;  // unique
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;     // unique
	private boolean isLoggedIn = false;
	
	public Role getRole() {
		return role;
	}	
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String login(HttpSession session) {
		
		final int TCP_PORT = 9000;
		String hostname = "localhost";

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
			
			CommandMessage request = new CommandMessage();
			request.setCommand("loginUser");
			UserBean user = this;
			setLoggedIn(true);
			request.setObject((Object) user);			

			// posalji zahtev
			out.writeObject(request);
			
			// procitaj odgovor
			CommandMessage response = (CommandMessage) in.readObject();
						
			in.close();
			out.close();
			sock.close();
			
			retVal = response.getCommand();
			
			session.setAttribute("user", (UserBean) response.getObject());	
			
			
			return retVal;
			
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
			return "failed";
		} catch (IOException e2) {
			e2.printStackTrace();
			return "failed";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "failed";
		}
			
	}
	
	public String logout() {
		
		final int TCP_PORT = 9000;
		String hostname = "localhost";

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
			
			CommandMessage request = new CommandMessage();
			request.setCommand("logoutUser");
			
			String user = username + " " + password;
			request.setObject((Object) user);					

			// posalji zahtev
			out.writeObject(request);
			
			// procitaj odgovor
			CommandMessage response = (CommandMessage) in.readObject();
						
			in.close();
			out.close();
			sock.close();
			
			retVal = response.getCommand();
			
			if (retVal.equals("success"))  {
				setLoggedIn(false);
			}
			
			return retVal;
			
			// zatvori konekciju
			
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
			return "failed";
		} catch (IOException e2) {
			e2.printStackTrace();
			return "failed";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "failed";
		}
			
	}
	
	public UserBean() {
		
	}	
}
