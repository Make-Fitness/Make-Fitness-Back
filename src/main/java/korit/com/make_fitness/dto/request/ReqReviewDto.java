package korit.com.make_fitness.dto.request;

import lombok.Data;

@Data
public class ReqReviewDto {

    private int customerId;
    int likeStar;
    String content;
}
