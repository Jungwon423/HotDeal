package HotDeal.HotDeal.Domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Search")
public class Search {
    @Id
    private String name;
    private String SearchText;
}
