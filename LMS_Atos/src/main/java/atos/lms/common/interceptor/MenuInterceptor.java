package atos.lms.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import atos.lms.common.menu.MenuItem;
import atos.lms.common.menu.MenuItemVO;

public class MenuInterceptor implements HandlerInterceptor {

	@Autowired
	private MenuItem menuItem;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			Map<String, MenuItemVO> menuItems = menuItem.getMenuItems();
			modelAndView.addObject("menuItems", menuItems);
			
			String currentUrl = request.getRequestURI();
            
            String activeMenuUrl = findActiveMenuUrl(menuItems, currentUrl);
            modelAndView.addObject("activeMenuUrl", activeMenuUrl);
		}
	}
	
    private String findActiveMenuUrl(Map<String, MenuItemVO> menuItems, String currentUrl) {
        String activeMenuUrl = "";
        int longestMatch = -1;
        
        for (MenuItemVO menuItem : menuItems.values()) {
            for (MenuItemVO subMenuItem : menuItem.getSubMenuItems()) {
                String linkUrl = subMenuItem.getUrl();
                // 정확히 일치하거나, baseUrl로 시작하는지 확인
                if (currentUrl.equals(linkUrl) || currentUrl.startsWith(linkUrl.replace("List.do", ""))) {
                    if (linkUrl.length() > longestMatch) {
                        longestMatch = linkUrl.length();
                        activeMenuUrl = linkUrl;
                    }
                }
            }
        }
        return activeMenuUrl;
    }
}