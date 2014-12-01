package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
