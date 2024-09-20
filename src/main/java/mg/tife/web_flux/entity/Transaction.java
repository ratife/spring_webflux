package mg.tife.web_flux.entity;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Transaction {
	private String id;
	private Instant time;
	private Double price;
	@DBRef
	private Society society;
}
