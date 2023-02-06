package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "comment")
public class Comment {
    @Id
    private String id;
    private String writerId;
    private String content;
    private List<String> goodUser = new ArrayList<>();
    private List<String> badUser = new ArrayList<>();
    private String timestamp;
    private String productId;
}
