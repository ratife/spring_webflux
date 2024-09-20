package mg.tife.web_flux.web;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.tife.web_flux.dao.SocietyRepository;
import mg.tife.web_flux.dao.TransactionRepository;
import mg.tife.web_flux.entity.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TransactionReactiveRestController {
	
	@Autowired
	TransactionRepository transactionRepo;
	
	@Autowired
	SocietyRepository societyRepo;
	
	@GetMapping(value="/trans",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Transaction> findAll(){
		return transactionRepo.findAll().delayElements(Duration.ofMillis(1000));
	}
	
	@GetMapping(value="/trans/{id}",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Transaction> findBySociety(@PathVariable String id){
		return transactionRepo.findBySocietyId(id).delayElements(Duration.ofMillis(1000));
	}
	
	
	@PostMapping("/trans")
	public Mono<Transaction> save(@PathVariable Transaction soc){
		return transactionRepo.save(soc);
	}
	
	@PutMapping("/trans/{id}")
	public Mono<Transaction> update(@PathVariable Transaction soc,@PathVariable String id){
		soc.setId(id);
		return transactionRepo.save(soc);
	}
	
	@DeleteMapping("/trans/{id}")
	public String delete(String id){
		transactionRepo.deleteById(id);
		 return "ok";
	}
	
	@GetMapping(value = "/streamtrans",produces = MediaType.TEXT_XML_VALUE)
	public Flux<Transaction> findAllStream(){
		return transactionRepo.findAll();
	}
	
	
	@GetMapping(value = "/generatetrans/{id}",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Transaction> generateTransaction(@PathVariable String id){
		return societyRepo.findById(id)
				.flatMapMany(soc->{
					Flux<Long> interval = Flux.interval(Duration.ofMillis(1000));
					Flux<Transaction> transactionsFlux = Flux.fromStream(Stream.generate(()->{
						Transaction trans = new Transaction();
						trans.setSociety(soc);
						trans.setTime(Instant.now());
						trans.setPrice(soc.getPrice()*Math.random());
						transactionRepo.save(trans).subscribe(res->{System.out.println(res);});
						return trans;
					}));
					
					return Flux.zip(interval, transactionsFlux)
								.map(data->{
									return data.getT2();
							     });
				});
	}
}
