package communication;

import java.io.Serializable;

public class CommandMessage implements Serializable {

	private static final long serialVersionUID = 8926637644102064994L;
	
	protected String command;
	protected Object object;
	
	public CommandMessage() {
		super();
	}

	public CommandMessage(String command, Object object) {
		super();
		this.command = command;
		this.object = object;
	}
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
}
