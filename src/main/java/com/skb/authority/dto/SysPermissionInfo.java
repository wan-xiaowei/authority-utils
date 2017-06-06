package com.skb.authority.dto;

import java.io.Serializable;

/**
 * .net 提供的文档格式要求我们提供：
 * <p>
 * [
 * {
 * "object_id":"SellerCube.HMS",
 * "object_name":"人事系统",
 * "object_type":"51",
 * "parent_id":"",
 * "data_type":"4"
 * "description":""
 * },
 * {
 * "object_id":"SellerCube.HMS.ArchivesInfoController",
 * "object_name":"档案管理",
 * "object_type":"52",
 * "parent_id":"SellerCube.HMS",
 * "data_type":"4"
 * "description":""
 * },
 * {
 * "object_id":"SellerCube.HMS.ArchivesInfoController.Index",
 * "object_name":"档案管理-首页",
 * "object_type":"53",
 * "parent_id":"SellerCube.HMS.ArchivesInfoController",
 * "data_type":"4"
 * "description":""
 * },
 * {
 * "object_id":"SellerCube.HMS.ArchivesInfoController.Add",
 * "object_name":"功能区-新增档案",
 * "object_type":"53",
 * "parent_id":"SellerCube.HMS.ArchivesInfoController",
 * "data_type":"4"
 * "description":""
 * }
 * ]
 */
public class SysPermissionInfo implements Serializable {

	private static final long serialVersionUID = -7634083374832406546L;

	private String object_id;
	private String object_name;
	private String object_type;
	private String parent_id;
	private String data_type;
	private String description;

	public String getObject_id() {
		return object_id;
	}

	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}

	public String getObject_name() {
		return object_name;
	}

	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}

	public String getObject_type() {
		return object_type;
	}

	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SysPermissionInfo{" +
				"object_id='" + object_id + '\'' +
				", object_name='" + object_name + '\'' +
				", object_type='" + object_type + '\'' +
				", parent_id='" + parent_id + '\'' +
				", data_type='" + data_type + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
