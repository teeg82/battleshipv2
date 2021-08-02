package utils.stringUtils;

public final class StringUtils {

	private StringUtils(){}
	
	public static String getShortClassName(Class<?> clazz){
		String packageName = clazz.getPackage().toString().substring("package ".length());
		String className = clazz.toString().substring(("class ".length())+(packageName.length()+1));
		return className;
	}
	
	public static String getRelativePath(Class<?> clazz, String target){
		String classPackage = clazz.getPackage().getName();
		String[] parts = classPackage.split("\\.");
		StringBuilder path = new StringBuilder(target.length() + (parts.length * 3));
		for(int index = 0; index < parts.length; index++)
			path.append("../");
		path.append(target);		
		return path.toString();
	}
}