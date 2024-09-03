package atos.lms.commons.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import atos.lms.commons.menu.MenuItem;
import atos.lms.commons.menu.MenuItemVO;

public class MenuInterceptor implements HandlerInterceptor {

	@Autowired
	private MenuItem menuItem;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			Map<String, MenuItemVO> menuItems = menuItem.getMenuItems();
			modelAndView.addObject("menuItems", menuItems);
		}
	}
}