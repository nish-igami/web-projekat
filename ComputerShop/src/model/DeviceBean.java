package model;

import java.io.Serializable;
import java.util.ArrayList;

public class DeviceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4962710672255382112L;
	
	private int id;
	private String name; // unique
	private String description;
	private ArrayList<ComponentBean> components = new ArrayList<ComponentBean>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<ComponentBean> getComponents() {
		return components;
	}

	public void setComponents(ArrayList<ComponentBean> components) {
		this.components = components;
	}

	public DeviceBean() {
	}

}
