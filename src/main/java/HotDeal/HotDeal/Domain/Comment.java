package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "comment")
public class Comment {
    @Id
    private String id;
    private String writerId;
    private String content;
    private List<String> goodUser;
    private List<String> badUser;
    private Date timestamp;
    private String productId;
}
