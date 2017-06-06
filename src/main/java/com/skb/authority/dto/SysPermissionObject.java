package com.skb.authority.dto;

import java.io.Serializable;
import java.util.List;

public class SysPermissionObject implements Serializable {


	private static final long serialVersionUID = -2909612953439935921L;
	
	private String routeKey;
	private String systemName;
	private List<SysPermissionInfo> items;

	public String getRouteKey() {
		return routeKey;
	}

	public void setRouteKey(String routeKey) {
		this.routeKey = routeKey;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public List<SysPermissionInfo> getItems() {
		return items;
	}

	public void setItems(List<SysPermissionInfo> items) {
		this.items = items;
	}
}
