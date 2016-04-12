import java.util.ArrayList;

public class Organization extends Party {
	ArrayList<Party> children;

	public Organization() {
		super();
	}
	
	public void addChild(Party c) {
		children.add(c);
		c.setParent(this);
	}
	
	public void removeChild(Party c) {
		children.remove(c);
		c.setParent(null);
	}

}
