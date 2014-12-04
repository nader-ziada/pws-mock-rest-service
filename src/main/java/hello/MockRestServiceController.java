package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/mock")
public class MockRestServiceController {

    @Autowired 
    ApplicationInstanceInfo instanceInfo;

	@Autowired
	private MockRestServiceRepository repository;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<MockRestService> list() {
    	List<MockRestService> allMocks = new ArrayList<MockRestService>();
    	allMocks = repository.findAll();
    	if(allMocks == null)
    		return null;
    	
    	return allMocks;
    }
    
    @RequestMapping(value="/getByName/{name}", method=RequestMethod.GET)
    public String getbyname(@PathVariable("name") String name) {
    	
    	MockRestService mockInstance = repository.findByName(name);
    	if(mockInstance == null)
    		return null;
    	
    	return mockInstance.getResponse();
    }
    
    @RequestMapping(value="/getById/{id}", method=RequestMethod.GET)
    public String get(@PathVariable("id") String mockId) {
    	
    	MockRestService mockInstance = repository.findById(mockId);
    	if(mockInstance == null)
    		return null;
    	
    	return mockInstance.getResponse();
    }
    
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(MockRestService mock) {
    	
    	repository.save(new MockRestService(mock.getName(), mock.getResponse()));
    	Map<String, Object> properties = instanceInfo.getProperties();
    	@SuppressWarnings("unchecked")
		List<String> applicationUris = (List<String>) properties.get("application_uris");
    	String url = "http://" + applicationUris.get(0) + "/v1/mock/getByName/" + mock.getName();
    	return url;
    }
    
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String update(MockRestService mock) {
    	System.out.println("*****" + mock.getId() + mock.getName());
    	repository.save(mock);
    	
    	Map<String, Object> properties = instanceInfo.getProperties();
    	@SuppressWarnings("unchecked")
		List<String> applicationUris = (List<String>) properties.get("application_uris");
    	String url = "http://" + applicationUris.get(0) + "/v1/mock/getByName/" + mock.getName();
    	return url;
    }
    
}
