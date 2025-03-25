package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqReviewDto;
import korit.com.make_fitness.entity.Review;
import korit.com.make_fitness.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/makefitness")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Operation(summary = "리뷰", description = "리뷰작성 설명")
    @PostMapping("/review")
    public ResponseEntity<Review> writeReview(@RequestBody ReqReviewDto req) {
        Review review = reviewService.createReview(req);
        return ResponseEntity.ok().body(review);
    }

    @Operation(summary = "리뷰 목록 조회", description = "등록된 리뷰들을 모두 불러옵니다.")
    @GetMapping("/review")
    public ResponseEntity<List<Review>> getReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok().body(reviews);
    }

}
