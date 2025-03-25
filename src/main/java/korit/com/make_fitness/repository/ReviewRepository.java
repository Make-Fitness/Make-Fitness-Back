package korit.com.make_fitness.repository;

import korit.com.make_fitness.entity.Review;
import korit.com.make_fitness.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {

    @Autowired
    private ReviewMapper reviewMapper;

    public Review save(Review review) {

        reviewMapper.insertReview(review);
        return review;
    }

    public List<Review> findAll() {
        return reviewMapper.selectAllReviews();
    }
}
