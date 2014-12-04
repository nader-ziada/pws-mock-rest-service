package hello;

import org.springframework.data.annotation.Id;

public class MockRestService {

	@Id
	private String id;
	
	private String name;
	private String response;
	
	public MockRestService(){
	}
	
	public MockRestService(String id, String name, String response) {
		super();
		this.id = id;
		this.name = name;
		this.response = response;
	}
	
	public MockRestService(String name, String response) {
		super();
		this.name = name;
		this.response = response;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	@Override
    public String toString() {
		return String.format(
                "Mock REST response[id=%s, name='%s', response='%s']",
                id, name, response);
    }
	
}
