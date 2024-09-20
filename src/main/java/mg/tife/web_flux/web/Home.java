package mg.tife.web_flux.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mg.tife.web_flux.dao.SocietyRepository;
import reactor.core.publisher.Mono;

@Controller
public class Home {
	
	@Autowired
	SocietyRepository societyRepo;
	
	
	@GetMapping("/")
	public Mono<String> home(Model model){
		return societyRepo.findAll()
                .collectList()
                .doOnNext(societies -> model.addAttribute("societies", societies))
                .then(Mono.just("index"));
	}
}
