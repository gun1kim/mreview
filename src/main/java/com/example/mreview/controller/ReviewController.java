package com.example.mreview.controller;

import com.example.mreview.dto.ReviewDTO;
import com.example.mreview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno) {
        log.info("---------------list---------------");
        log.info("MNO: {}", mno);

        List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO movieReviewDTO) {
        log.info("---------------add MovieReview---------------");
        log.info("reviewDTO: {}", movieReviewDTO);

        Long reviewNum = reviewService.register(movieReviewDTO);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }

    @PutMapping("/{mno}/{reviewNum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewNum, @RequestBody ReviewDTO movieReviewDTO) {
        log.info("---------------modify MovieReview---------------");
        log.info("reviewDTO: {}", movieReviewDTO);

        reviewService.modify(movieReviewDTO);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);

    }

    @DeleteMapping("/{mno}/{reviewNum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewNum) {
        log.info("---------------delete MovieReview---------------");
        log.info("reviewNum: {}", reviewNum);

        reviewService.remove(reviewNum);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }
}
