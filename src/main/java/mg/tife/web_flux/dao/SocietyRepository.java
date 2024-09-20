package mg.tife.web_flux.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import mg.tife.web_flux.entity.Society;

public interface SocietyRepository extends ReactiveMongoRepository<Society, String>{

}
