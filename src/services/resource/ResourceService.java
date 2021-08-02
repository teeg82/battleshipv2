package services.resource;

import java.util.Map;

public interface ResourceService {

	public static final String SHIP_SIZES = "shipSizes";
	
	Map<String, String> getSelectOptions(String resourceType);
}