package it.mcsolution.wbm.bean;

public class BeerBean {

	private String beer;
	private String type;
	private String nationality;
	private String short_Description;
	private String grade;
	private String note;
	
	public String getBeer() {
		return beer;
	}
	public void setBeer(String beer) {
		this.beer = beer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getShort_Description() {
		return short_Description;
	}
	public void setShort_Description(String short_Description) {
		this.short_Description = short_Description;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "UserBean [beer=" + beer + ", type=" + type + ", nationality="
				+ nationality + ", short_Description=" + short_Description
				+ ", grade=" + grade + ", getBeer()=" + getBeer()
				+ ", getType()=" + getType() + ", getNationality()="
				+ getNationality() + ", getShort_Description()="
				+ getShort_Description() + ", getGrade()=" + getGrade()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}
