package it.mcsolution.wbm.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "menuList" database table.
 * 
 */
@Entity
@Table(name="menulist")
@NamedQuery(name="MenuList.findAll", query="SELECT m FROM MenuList m")
public class MenuList implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;

	@Column(name="itemlistid")
	private Integer itemListId;

	@Column(name="menuid")
	private Integer menuId;

	private Integer priority;

	public MenuList() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemListId() {
		return this.itemListId;
	}

	public void setItemListId(Integer itemListId) {
		this.itemListId = itemListId;
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}