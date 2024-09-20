package mg.tife.web_flux;

import java.time.Instant;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import mg.tife.web_flux.dao.SocietyRepository;
import mg.tife.web_flux.dao.TransactionRepository;
import mg.tife.web_flux.entity.Society;
import mg.tife.web_flux.entity.Transaction;

@SpringBootApplication
public class WebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(SocietyRepository societyRepo, TransactionRepository transactionRepo) {
		return args->{
			Stream.of("BFV","BNI","BOA","MVOLA","ORANGE").forEach(s->{
				societyRepo.save(new Society(s,s,500+Math.random()*1000)).subscribe(soc->{
					System.out.println(soc.toString());
				});
			});
			Stream.of("BFV","BNI","BOA","MVOLA","ORANGE").forEach(s->{
				societyRepo.findById(s).subscribe(soc->{
					for(int i = 1;i<10;i++) {
						Transaction trans = new Transaction();
						trans.setSociety(soc);
						trans.setTime(Instant.now());
						trans.setPrice(soc.getPrice()*Math.random());
						transactionRepo.save(trans).subscribe(res->{System.out.println(res);});
					}
				});
			});
		};
	}

}
