public abstract class Party {
	Party parent;
	License license;
	LicensePolicy lp;

	public Party() {
		parent = null;
		license = null;
		lp = LicensePolicy.NoLicensePolicy;
	}

	public Party getParent() {
		return parent;
	}

	public void setParent(Organization p) {
		parent = p;
	}

	public void setLicense(License l) {
		license = l;
	}
	
	public boolean purchaseLicense(License l) {
		if(license != l) {
			if(getParent() != null) {
				if(getParent().purchaseLicense(l))
					return false;
			}
		}
		return true;
	}
}
