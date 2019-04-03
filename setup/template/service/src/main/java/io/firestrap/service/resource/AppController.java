package io.firestrap.{{service}}.resource;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api/{{service}}")
public class AppController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String home() {
	    return "Welcome to {{service}}";
	}

}
