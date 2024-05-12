package personal.afiproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IPController {
    @GetMapping("/afi/ippTest")
    public String ippTest(){ return "ippTest";}

    @PostMapping("/afi/ippAsk")
    public String ippAsk(){
        return "ippAsk";
    }

}
