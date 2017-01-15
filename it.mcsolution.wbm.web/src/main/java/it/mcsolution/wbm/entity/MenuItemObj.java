package it.mcsolution.wbm.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "menuItemObj" database table.
 * 
 */
@Entity
@Table(name="\"menuItemObj\"")
@NamedQuery(name="MenuItemObj.findAll", query="SELECT m FROM MenuItemObj m")
public class MenuItemObj implements Serializable {
	private static final long serialVersionUID = 1L;

	private String beer;

	private String glass;

	private String grade;
	@Id
	private Integer id;

	@Column(name="\"localInfo\"")
	private String localInfo;

	private String nationality;

	private String note;

	@Column(name="\"short-description\"")
	private String short_description;

	private String type;

	public MenuItemObj() {
	}

	public String getBeer() {
		return this.beer;
	}

	public void setBeer(String beer) {
		this.beer = beer;
	}

	public String getGlass() {
		return this.glass;
	}

	public void setGlass(String glass) {
		this.glass = glass;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocalInfo() {
		return this.localInfo;
	}

	public void setLocalInfo(String localInfo) {
		this.localInfo = localInfo;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getShort_description() {
		return this.short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}