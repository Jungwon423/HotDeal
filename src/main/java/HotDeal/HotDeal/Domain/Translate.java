package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Data
@Document(collection = "translate")
public class Translate {

    @Id
    private String id;
    @Size(min = 2)
    private String sentence;
}