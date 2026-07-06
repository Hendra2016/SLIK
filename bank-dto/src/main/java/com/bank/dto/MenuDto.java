package com.bank.dto;

import java.util.ArrayList;
import java.util.List;

import com.bank.tree.RODTreeNodeData;

public class MenuDto extends RODTreeNodeData {
	private Integer id;
	private String menuName;
	private String menuUrl;
	private String menuType;
	private Integer count;
	private Integer parentId;
	private MenuDto parent;
	private List<MenuDto> children = new ArrayList<MenuDto>();

	public void appendChild(MenuDto child) {
		if (this.children == null)
			this.children = new ArrayList<MenuDto>();
		this.children.add(child);
	}

	public MenuDto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public MenuDto getParent() {
		return parent;
	}

	public void setParent(MenuDto parent) {
		this.parent = parent;
	}

	public MenuDto(MenuDto parent, List<MenuDto> child) {
		this.parent = parent;
		this.children = child;
	}

	public MenuDto(MenuDto parent) {
		this.parent = parent;
	}

	public List<MenuDto> getChildren() {
		if (children == null)
			return new ArrayList<MenuDto>();

		return children;
	}

	public void setChildren(List<MenuDto> children) {
		this.children = children;
	}

	@Override
	public int getChildCount() {
		return children.size();
	}

}