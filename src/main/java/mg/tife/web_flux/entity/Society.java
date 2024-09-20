package mg.tife.web_flux.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data @AllArgsConstructor  @NoArgsConstructor @ToString
public class Society {
	@Id
	private String id;
	private String name;
	private double price;
}
