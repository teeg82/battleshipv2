package test.pathTests;

public class PackageTest {

	public static void main(String[] args) {
		String imagesString = "images";
		Package[] test = Package.getPackages();
		//Package.getPackage(imagesString);
		if(test == null)
			System.out.println("Package is null. Unable to find package with the name " + imagesString);
		for(Package packages : test){
			System.out.println(packages.getName());
			
		}
//		else
//			System.out.println(test.getName());
	}
}