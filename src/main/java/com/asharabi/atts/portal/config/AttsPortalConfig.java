package com.asharabi.atts.portal.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.asharabi.atts.portal.rest.template.interceptor.RestTemplateInterceptor;
import com.asharabi.atts.security.api.client.AttsSecurityClient;

@Configuration
public class AttsPortalConfig {

	@Autowired
	private RestTemplateInterceptor restTemplateInterceptor;

	@Bean
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();

		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(restTemplateInterceptor);

		restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}

	@Bean
	public AttsSecurityClient attsSecurityClient() {
		return new AttsSecurityClient();
	}

}
