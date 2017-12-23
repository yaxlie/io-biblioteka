package pl.put.poznan.transformer.applicationController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class applicationController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	 public String greeting() {
        return ("index.html");
    }
}
