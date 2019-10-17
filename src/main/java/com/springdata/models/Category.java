package com.springdata.models;


import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;



@Entity
@Table(name = "CATEGORY")
@Document(collection = "category")
@Data
public class Category {

	@org.springframework.data.annotation.Id
	@Id
	private String categoryId;
	private String name;
	private String category;
	@Lob
	private byte[] imageBlob;

	public Category() {
		super();
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

	

	public byte[] getImageBlob() {
		return imageBlob;
	}

	public void setImageBlob(byte[] imageBlob) {
		this.imageBlob = imageBlob;
	}

	

	

	public Category(String categoryId, String name, String category, byte[] imageBlob) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.category = category;
		this.imageBlob = imageBlob;
	}


	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + ", category=" + category + ", imageBlob="
				+ Arrays.toString(imageBlob) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + Arrays.hashCode(imageBlob);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (!Arrays.equals(imageBlob, other.imageBlob))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}