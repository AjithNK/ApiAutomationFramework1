package assignments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root {
	

	    public Address address;
	    public String name;
	    @JsonProperty("Height") 
	    public double height;
	    public boolean isEmployed;
	    public int age;
	    
	    
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getHeight() {
			return height;
		}
		public void setHeight(double height) {
			this.height = height;
		}
		public boolean isEmployed() {
			return isEmployed;
		}
		public void setEmployed(boolean isEmployed) {
			this.isEmployed = isEmployed;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	


}
