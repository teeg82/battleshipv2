package services.resource;

import java.util.Map;

public class ResourceServiceImpl implements ResourceService {

	@Override
	public Map<String, String> getSelectOptions(String resourceType) {
		ResourceResolver resourceResolver = new XMLResourceResolver();
		
		return null;
	}
}