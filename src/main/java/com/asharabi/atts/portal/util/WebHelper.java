package com.asharabi.atts.portal.util;

import javax.servlet.http.HttpServletRequest;

public class WebHelper {
	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}
}