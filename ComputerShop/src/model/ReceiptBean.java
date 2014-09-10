package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ReceiptBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6984672290109924090L;
	
	private int id;
	private ArrayList<BoughtObjectBean> articles = new ArrayList<BoughtObjectBean>();
	private double tax;
	private double total;
	private Date timeAndDate;
	private UserBean buyer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<BoughtObjectBean> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<BoughtObjectBean> articles) {
		this.articles = articles;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getTimeAndDate() {
		return timeAndDate;
	}

	public void setTimeAndDate(Date timeAndDate) {
		this.timeAndDate = timeAndDate;
	}

	public UserBean getBuyer() {
		return buyer;
	}

	public void setBuyer(UserBean buyer) {
		this.buyer = buyer;
	}

	public ReceiptBean() {
	}

}
