package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebpageController {

	@Autowired
	private MockRestServiceRepository repository;
	
    @RequestMapping(value = "/")
    public String list(Model model) {
        
    	List<MockRestService> allMocks = new ArrayList<MockRestService>();
    	allMocks = repository.findAll();
    	model.addAttribute("allMocks", allMocks);
        return "index";
    }
    
    @RequestMapping(value = "/add", method=RequestMethod.GET)
    public String showForm(Model model) {
    	model.addAttribute("mock", new MockRestService());
        return "add";
    }
    
    @RequestMapping(value = "/add", method=RequestMethod.POST )
    public String submitForm(@ModelAttribute MockRestService mock, Model model) {
    	repository.save(new MockRestService(mock.getName(), mock.getResponse()));
    	MockRestService mockInstance = repository.findByName(mock.getName());
    	model.addAttribute("mock", mockInstance);
        return "result";
    }
    
    @RequestMapping(value = "/update", method=RequestMethod.GET)
    public String showFormForEdit(@RequestParam(value="id", required=true) String mockId, Model model) {
    	System.out.print("mockId********" + mockId);
    	MockRestService mock = repository.findById(mockId);
    	if(mock == null){
    		return "index";
    	}
    	model.addAttribute("mock", mock);
        return "update";
    }
    
    @RequestMapping(value = "/update", method=RequestMethod.POST )
    public String submitFormUpdate(@ModelAttribute MockRestService mock, Model model) {
    	MockRestService mock1 = repository.findOne(mock.getId());
    	mock1.setResponse(mock.getResponse());
    	repository.save(mock1);
    	model.addAttribute("mock", mock1);
        return "result";
    }

}
