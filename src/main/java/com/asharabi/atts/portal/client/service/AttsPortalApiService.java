package com.asharabi.atts.portal.client.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.asharabi.atts.security.api.client.AttsSecurityClient;

@Component
public class AttsPortalApiService {

	@Value("${service.url.be}")
	private String beURL;

	@Autowired
	private AttsSecurityClient attsSecurityClient;

	public AttsPortalApiService() {
	}

	public AttsSecurityClient attsSecurityClient() {
		return attsSecurityClient;
	}

	@PostConstruct
	public void setPropertiesUrl() {
		attsSecurityClient.setUrl(beURL);
	}

}
