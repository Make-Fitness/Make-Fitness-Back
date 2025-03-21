package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Review;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    int insertReview(Review review);
}
