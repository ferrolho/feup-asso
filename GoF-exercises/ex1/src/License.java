
public class License {
	
	String name;
	LicenseType licenseType;

	public License() {}
	
	public License(String name, LicenseType lp) {
		this.name = name;
		licenseType = lp;
	}
	
	public String getName() {
		return this.name;
	}

	public LicenseType getLicenseType() {
		return licenseType;
	}
}
