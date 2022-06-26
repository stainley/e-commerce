package com.salapp.ecommerce.productservice.services;


import com.salapp.ecommerce.productservice.services.RandomReviewParameterResolverExtension.RandomReview;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RandomReviewParameterResolverExtension.class)
class ReviewVerifierTest {

    private ReviewVerifier reviewVerifier;

    @BeforeEach
    public void setup(TestInfo testInfo) {
        System.out.println("Before each: [" + testInfo.getTestMethod().orElseThrow().getName() + "]");
        reviewVerifier = new ReviewVerifier();
    }

    @Test
    void shouldFailWhenReviewContainsSwearWord() {
        String review = "This product is shit";

        boolean result = reviewVerifier.doesMeetQualityStandards(review);

        assertFalse(result);
    }

    @ParameterizedTest(name = "{index} -) doesMeetQualityStandards({0}) should return {1}")
    @CsvFileSource(resources = "/badReview.csv", delimiter = '|')
    @DisplayName("Should fail when review is of bad quality")
    void testReviewBadQuality(String review, boolean status) {
        boolean result = reviewVerifier.doesMeetQualityStandards(review);
        assertEquals(result, status);
        assertFalse(result);

    }


    @RepeatedTest(value = 5)
    void shouldFailWhenRandomReviewQualityIsBad(@RandomReview String review) {
        System.out.println(review);
        boolean result = reviewVerifier.doesMeetQualityStandards(review);
        assertFalse(result);
    }

    @Test
    void shouldPassWhenReviewIsGood() {
        String review = "I can totally recommend this product who is interested in buying it!";
        boolean result = reviewVerifier.doesMeetQualityStandards(review);
        assertTrue(result, "ReviewVerifier did not pass a good review");
    }
}