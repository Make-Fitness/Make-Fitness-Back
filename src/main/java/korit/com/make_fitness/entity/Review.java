package korit.com.make_fitness.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private int reviewId;
    private int likeStar;
    private String content;
    private LocalDateTime createdAt;
}
