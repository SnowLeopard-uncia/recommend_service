package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
    private int historyId;
    private int newsId;
    private int userId;
    private String historyTime;

}
