package in.nvijaykarthik.service.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="in.nvijaykarthik")
public class ExceptionMapping {

	private Map<Class<? extends Throwable>, String> errorMessageMapping=new HashMap<>();
	private Map<Class<? extends Throwable>, String> errorCodeMapping=new HashMap<>();

	public Map<Class<? extends Throwable>, String> getErrorCodeMapping() {
		return errorCodeMapping;
	}

	public void setErrorCodeMapping(Map<Class<? extends Throwable>, String> errorCodeMapping) {
		this.errorCodeMapping = errorCodeMapping;
	}

	public Map<Class<? extends Throwable>, String> getErrorMessageMapping() {
		return errorMessageMapping;
	}

	public void setErrorMessageMapping(Map<Class<? extends Throwable>, String> errorMessageMapping) {
		this.errorMessageMapping = errorMessageMapping;
	}


}
