package model;

import java.io.Serializable;

public class BoughtObjectBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7555522184627280716L;

	private static int soldObjects = 0;
	private int id;
	private ComponentBean component;
	private DeviceBean device;
	private int quantity;
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ComponentBean getComponent() {
		return component;
	}

	public void setComponent(ComponentBean component) {
		this.component = component;
	}

	public DeviceBean getDevice() {
		return device;
	}

	public void setDevice(DeviceBean device) {
		this.device = device;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static int getSoldObjects() {
		return soldObjects;
	}

	public static void increaseSoldObjects() {
		++soldObjects;
	}

	public BoughtObjectBean() {
	}

}
