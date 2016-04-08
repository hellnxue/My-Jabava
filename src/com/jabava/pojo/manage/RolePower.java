package com.jabava.pojo.manage;

import java.util.List;

public class RolePower {
	private Long id;
	private String name;
	private Long pId;
	private Integer menuType;
	private Boolean checked;//多选框是否选中，选中为true
	private String type;//多选框的name值，inputName为button时，为button表里的id，保存时，好操作
	private List<RolePower> nodes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Boolean isChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RolePower> getNodes() {
		return nodes;
	}

	public void setNodes(List<RolePower> nodes) {
		this.nodes = nodes;
	}
}
