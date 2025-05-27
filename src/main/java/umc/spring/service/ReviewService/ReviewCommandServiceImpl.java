package umc.spring.service.ReviewService;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.web.dto.review.ReviewRequestDTO;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private ReviewRepository reviewRepository;
    private MemberRepository memberRepository;

    @Override
    public Review registerReview(ReviewRequestDTO.ReviewRegisterDTO requestDto){
        Review review = ReviewConverter.toReview(requestDto);
        List<ReviewImage> reviewImages = requestDto.getReviewImages().stream()
                .map(reviewImage ->
                ReviewImage.builder()
                        .review(review)
                        .imageUrl(reviewImage.getImageUrl())
                        .build()
        )
                .toList();
        review.addReviewImage(reviewImages);
        reviewRepository.save(review);
        return review;
    }

    @Override
    public Page<Review> getMemberReviews(Long memberId, Integer page){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(page, 10);
        return reviewRepository.findAllByMember(member, pageRequest);
    }
}
