package atos.lms.common.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuItemVO {
	private String title;
	private String url;
	private String data;
	private List<MenuItemVO> subMenuItems;

	public MenuItemVO() {
		this.subMenuItems = new ArrayList<>();
	}

	public MenuItemVO(String title, String url, String data) {
		this.title = title;
		this.url = url;
		this.data = data;
		this.subMenuItems = new ArrayList<>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<MenuItemVO> getSubMenuItems() {
		return subMenuItems;
	}

	public void setSubMenuItems(List<MenuItemVO> subMenuItems) {
		this.subMenuItems = subMenuItems;
	}

	public void addSubMenuItem(MenuItemVO subMenuItem) {
		this.subMenuItems.add(subMenuItem);
	}
}
