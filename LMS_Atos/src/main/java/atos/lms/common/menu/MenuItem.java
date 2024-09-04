package atos.lms.common.menu;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:atos/message/menu/message_ko.properties")
public class MenuItem {

	@Autowired
	private Environment env;

	public Map<String, MenuItemVO> getMenuItems() {
		Map<String, MenuItemVO> menuItems = new LinkedHashMap<>();
		int i = 1;

		while (env.containsProperty("menu." + i + ".title")) {
			String title = env.getProperty("menu." + i + ".title");
			String url = env.getProperty("menu." + i + ".url");
			String data = env.getProperty("menu." + i + ".data");

			MenuItemVO menuItem = new MenuItemVO(title, url, data);

			// 하위 메뉴 처리
			int j = 1;
			while (env.containsProperty("menu." + i + "." + j + ".title")) {
				String subTitle = env.getProperty("menu." + i + "." + j + ".title");
				String subUrl = env.getProperty("menu." + i + "." + j + ".url");

				MenuItemVO subMenuItem = new MenuItemVO(subTitle, subUrl, null); // 하위 메뉴에는 data가 없음
				menuItem.addSubMenuItem(subMenuItem);

				j++;
			}

			menuItems.put("menu." + i, menuItem);
			i++;
		}

		return menuItems;
	}
}
