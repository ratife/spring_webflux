package mg.tife.web_flux.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.tife.web_flux.dao.SocietyRepository;
import mg.tife.web_flux.entity.Society;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SocietyReactiveRestController {
	
	@Autowired
	SocietyRepository societyRepo;
	
	
	
	@GetMapping("/societies")
	public Flux<Society> findAll(){
		return societyRepo.findAll();
	}
	
	@GetMapping("/societies/{id}")
	public Mono<Society> findAll(@PathVariable String id){
		return societyRepo.findById(id);
	}
	
	@PostMapping("/societies")
	public Mono<Society> save(@PathVariable Society soc){
		return societyRepo.save(soc);
	}
	
	@PutMapping("/societies/{id}")
	public Mono<Society> update(@PathVariable Society soc,@PathVariable String id){
		soc.setId(id);
		return societyRepo.save(soc);
	}
	
	@DeleteMapping("/societies/{id}")
	public String delete(String id){
		 societyRepo.deleteById(id);
		 return "ok";
	}
	
}
