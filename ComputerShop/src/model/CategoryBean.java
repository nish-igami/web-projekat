package model;

import java.io.Serializable;

public class CategoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5954742338376618942L;
	
	private int id; 					// unique
	private String name; 				// unique
	private String description;
	private CategoryBean subcategory; 	// (kategorija može, a ne mora, da bude  podkategorija neke druge
	
	@SuppressWarnings("unused")
	private String subcategoryName;									
	
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

	public CategoryBean getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(CategoryBean subcategory) {
		this.subcategory = subcategory;
	}

	public CategoryBean() {
	}

	public String getSubcategoryName() {
		if (subcategory != null) {
			return subcategory.getName();
		} else {
			return "notSub";
		}
	}
	
	

}
