package in.nvijaykarthik.service.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	@RequestMapping("/")
	public String home() {
		return "Home";
	}
}
