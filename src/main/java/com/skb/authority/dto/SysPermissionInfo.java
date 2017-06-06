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

	private String itemId;
	private String itemName;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
