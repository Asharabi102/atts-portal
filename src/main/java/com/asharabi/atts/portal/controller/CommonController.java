package com.asharabi.atts.portal.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.asharabi.atts.portal.client.service.AttsPortalApiService;
import com.asharabi.atts.portal.util.WebHelper;
import com.asharabi.atts.security.api.dto.AuthenticationRequest;
import com.asharabi.atts.security.api.dto.AuthenticationResponse;
import com.asharabi.atts.security.api.dto.UserDTO;

@Controller
@SessionAttributes("requestDTO")
public class CommonController {

	@Autowired
	private AttsPortalApiService apiService;

	private final Logger log = LoggerFactory.getLogger(CommonController.class);

	@GetMapping("/login")
	public ModelAndView loginView(@ModelAttribute("requestDTO") AuthenticationRequest requestDTO) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject(requestDTO);
		return mav;
	}

	@PostMapping("/login")
	public ModelAndView login(HttpServletRequest httpRequest,
			@ModelAttribute("requestDTO") AuthenticationRequest requestDTO) {
		AuthenticationResponse responseDTO = null;
		ModelAndView mav = new ModelAndView("redirect:/home");
		try {
			responseDTO = apiService.attsSecurityClient().postForObject("/login", requestDTO,
					AuthenticationResponse.class);
			String jwt = responseDTO.getJwt();
			httpRequest.getSession().setAttribute("jwt", jwt);
		} catch (Exception e) {
			// log.error("Login error: Bad credentials: Incorrect username or password");
			mav = new ModelAndView("login");
			mav.addObject("error", "Bad credentials: Incorrect username or password");
		}
		mav.addObject("username", requestDTO.getUsername());
		return mav;
	}

	@GetMapping("/register")
	public ModelAndView registerView(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
			@ModelAttribute UserDTO userDTO) {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject(userDTO);
		return mav;
	}

	@PostMapping("/register")
	public ModelAndView register(@ModelAttribute UserDTO userDTO) {
		userDTO = apiService.attsSecurityClient().postForObject("/register", userDTO, UserDTO.class);
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@Value("${server.servlet.session.timeout}")
	private String timeout;

	@GetMapping("/")
	public ModelAndView index(@ModelAttribute("requestDTO") AuthenticationRequest requestDTO) {
		ModelAndView mav = new ModelAndView("redirect:/login");
		boolean sessionActive = requestDTO.getUsername() != null || requestDTO.getPassword() != null;
		if (sessionActive) {
			mav = new ModelAndView("redirect:/home");
			mav.addObject("username", requestDTO.getUsername());
			mav.addObject("timeout", timeout);
			return mav;
		}
		return mav;
	}

	@GetMapping("/home")
	public ModelAndView welcome(@ModelAttribute("requestDTO") AuthenticationRequest requestDTO) {
		ModelAndView mav = new ModelAndView("redirect:/login");
		boolean sessionActive = requestDTO.getUsername() != null || requestDTO.getPassword() != null;
		if (sessionActive) {
			mav = new ModelAndView("home");
			mav.addObject("username", requestDTO.getUsername());
			mav.addObject("timeout", timeout);
			return mav;
		}
		return mav;
	}

	@GetMapping(value = "/forgot")
	public ModelAndView forgotView() {
		return new ModelAndView("forgot");
	}

	@PostMapping(value = "/forgot")
	public ModelAndView forgot(HttpServletRequest httpRequest, @ModelAttribute UserDTO userDTO) {
		userDTO.setPortalURL(WebHelper.getSiteURL(httpRequest));
		Map<String, String> mapResponse = apiService.attsSecurityClient().postForObject("/forgot", userDTO, Map.class);
		ModelAndView mav = new ModelAndView("forgot");
		mapResponse.forEach((k, v) -> {
			mav.addObject(k, v);
		});
		return mav;
	}

	@GetMapping(value = "/resetPassword")
	public ModelAndView resetPasswordView(@ModelAttribute UserDTO userDTO,
			@ModelAttribute("requestDTO") AuthenticationRequest requestDTO) {
		//ModelAndView mav = new ModelAndView("redirect:/login");
		//boolean sessionActive = requestDTO.getUsername() != null || requestDTO.getPassword() != null;
		//if (sessionActive) {
		ModelAndView mav = new ModelAndView("reset");
			mav.addObject("userDTO", userDTO);
			return mav;
		//}
		//return mav;
	}

	@GetMapping(value = "/resetPassword2")
	public ModelAndView resetPassword2(@RequestParam(name = "token") String token) {
		boolean resetTokenCheck = apiService.attsSecurityClient().postForObject("/resetPasswordCheck", token,
				boolean.class);
		ModelAndView mav = new ModelAndView("reset");
		if (!resetTokenCheck) {
			mav = new ModelAndView("forgot");
			mav.addObject("error", "Error: Invalid Token");
			return mav;
		}
		mav.addObject("token", token);
		return mav;
	}

	@PostMapping(value = "/resetPassword")
	public ModelAndView resetPassword(@ModelAttribute UserDTO userDTO,
			@ModelAttribute("requestDTO") AuthenticationRequest requestDTO) {
		ModelAndView mav = new ModelAndView("redirect:/login");
		boolean sessionActive = requestDTO.getUsername() != null || requestDTO.getPassword() != null;
		if (sessionActive) {
			boolean passwordReset = apiService.attsSecurityClient().postForObject("/resetPassword", userDTO, boolean.class);
			mav = new ModelAndView("reset_success");
			if (passwordReset) {
				mav.addObject("success", "You have successfully changed your password.");
				return mav;
			}
		}
		mav.addObject("error", "Error: Reset password failed!");
		return mav;
	}

	@ModelAttribute("requestDTO")
	public AuthenticationRequest requestDTO() {
		return new AuthenticationRequest();
	}

}
