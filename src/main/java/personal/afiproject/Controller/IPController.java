package personal.afiproject.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.afiproject.dto.PropencityDTO;
import personal.afiproject.service.PropencityService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/afi")
public class IPController {
    @Autowired
    private final PropencityService propencityService;
    @GetMapping("/ippTest")
    public String ippTest(){ return "ippTest";}

    @PostMapping("/ippAsk")
    public String ippAsk(){
        return "ippAsk";
    }

    @PostMapping("/ippRegist")
    public String registering(@ModelAttribute PropencityDTO propencityDTO) {
        propencityService.save(propencityDTO);
        return "main";
    }

}
