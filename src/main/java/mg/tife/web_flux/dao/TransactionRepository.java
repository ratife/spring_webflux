package mg.tife.web_flux.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import mg.tife.web_flux.entity.Transaction;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String>{
	public Flux<Transaction> findBySocietyId(String id);
}
