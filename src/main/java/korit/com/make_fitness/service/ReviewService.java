package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqReviewDto;
import korit.com.make_fitness.entity.Review;
import korit.com.make_fitness.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional(rollbackFor = Exception.class)
    public Review createReview(ReqReviewDto reqReviewDto) {

        Review review = Review.builder()
                .likeStar(reqReviewDto.getLikeStar())
                .content(reqReviewDto.getContent())
                .build();

        return reviewRepository.save(review);
    }
}
