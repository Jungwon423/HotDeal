package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "exchangeRate")
public class ExchangeRate {

    @Id
    private String id;
    private String name;
    private Double exchangeRate;
}
