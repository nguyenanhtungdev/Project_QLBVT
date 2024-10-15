package view;

public class Page {
	String name;
    String imagePath;

    public Page(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }
    public Page(String name) {
    	this.name = name;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
    
}
