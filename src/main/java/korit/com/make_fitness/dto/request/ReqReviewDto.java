package korit.com.make_fitness.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class ReqReviewDto {

    @Schema(description = "별점")
    int likeStar;

    @Schema(description = "리뷰 내용")
    String content;
}
