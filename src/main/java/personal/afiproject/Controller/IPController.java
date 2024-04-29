package personal.afiproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IPController {
    @GetMapping("/afi/ippTest")
    public String ippTest(){ return "ippTest";}


}
