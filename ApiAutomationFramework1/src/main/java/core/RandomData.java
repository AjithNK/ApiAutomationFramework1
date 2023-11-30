package core;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

public class RandomData {
	
	
	public String getRandomEmail() {
		FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
		
		String email = fakeValuesService.bothify("????##@gmail.com");
		System.out.println(email);
		return email;
		
	}
	
	//Assignment -regular expression \\d
	//implement regixy and generate random phone number
	public void getRandomPhoneNo() {
	//regify()
		FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
		String email = fakeValuesService.regexify(getRandomEmail());
	}
	
	public void getRandomData() {
		
	}

	
	public void getRandomAddress() {
		
		Faker faker = new Faker();
		faker.address().city();
		
		
	}
	
	

}
