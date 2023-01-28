package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Data
@Document(collection = "category")
public class Category {

    @Id
    @Size(min = 2, max = 20, message = "길이는 2~20로")
    private String name;
    private int clickCount;
}
