
public class Main {

	public static void main(String[] args) {
		Organization o1 = new Organization();
		Organization o2 = new Organization();
		Organization o3 = new Organization();
		
		o1.addChild(o2);
		o1.addChild(o3);

		License l1 = new License("Office", LicenseType.FullSiteLicense);
	}

}
