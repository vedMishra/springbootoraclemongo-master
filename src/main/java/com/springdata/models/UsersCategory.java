package com.springdata.models;


import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;



@Entity
@Table(name = "USERSCATEGORY")
@Document(collection="UsersCategory")
@Data
public class UsersCategory {

    @org.springframework.data.annotation.Id
    @Id
    private String categoryId;
    private String name;
    private String category;
    private int age;
   /* @Lob
    private byte[] imageBlob;*/


	public UsersCategory() {
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

	
	



	/*public byte[] getImageBlob() {
		return imageBlob;
	}

	public void setImageBlob(byte[] imageBlob) {
		this.imageBlob = imageBlob;
	}*/

	

	

	@Override
	public String toString() {
		return "UsersCategory [categoryId=" + categoryId + ", name=" + name + ", category=" + category + ", age=" + age
				+ ", imageBlob=" +  "]";
	}

	

	public UsersCategory(String categoryId, String name, String category, int age, byte[] imageBlob) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.category = category;
		this.age = age;
		//this.imageBlob = imageBlob;
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + Arrays.hashCode(imageBlob);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}*/

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersCategory other = (UsersCategory) obj;
		if (age != other.age)
			return false;
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
		/*if (!Arrays.equals(imageBlob, other.imageBlob))
			return false;*/
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		//result = prime * result + ((imageBlob == null) ? 0 : imageBlob.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	
	
}