package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    int insertReview(Review review);

    List<Review> selectAllReviews();
}
