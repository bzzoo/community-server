package com.app.community.api_docs;

import com.app.community.api.controller.UpvoteController;
import com.app.community.api.controller.request.UpvoteRequests.UpvotedRequest;
import com.app.community.domain.model.upvote.UpvoteService;
import com.app.community.domain.model.upvote.UpvoteTarget;
import com.app.community.domain.model.upvote.UpvoteTargetType;
import com.app.community.test.api.RestDocsTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.app.community.test.api.RestDocsUtils.requestPreprocessor;
import static com.app.community.test.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class UpvoteControllerRestDocsTest extends RestDocsTest {

    private UpvoteService upvoteService;
    private UpvoteController upvoteController;

    @BeforeEach
    public void setUp() {
        upvoteService = mock(UpvoteService.class);
        upvoteController = new UpvoteController(upvoteService);
        mockMvc = mockController(upvoteController);
    }

    @Test
    void upvote() {
        Long memberId = 1L;
        UpvotedRequest request = new UpvotedRequest(2L, 3L, UpvoteTargetType.QUESTION_COMMENT);
        doNothing().when(upvoteService).upvote(anyLong(), anyLong(), any(UpvoteTarget.class));

        given()
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer token")
            .body(request)
            .when()
            .post("/api/upvotes")
            .then()
            .statusCode(HttpStatus.OK.value())
            .apply(document("추천 하기", requestPreprocessor(), responsePreprocessor(),
                    requestFields(
                        fieldWithPath("opponentId")
                                .type(JsonFieldType.NUMBER)
                                .description("상대방 ID"),
                        fieldWithPath("targetId")
                                .type(JsonFieldType.NUMBER)
                                .description("업보트 대상의 ID"),
                        fieldWithPath("targetType")
                                .type(JsonFieldType.STRING)
                                .description("업보트 대상의 유형 (예: POST, COMMENT)")
                    ),
                    responseFields(
                            fieldWithPath("result")
                                    .type(JsonFieldType.STRING)
                                    .description("성공 여부"),
                            fieldWithPath("data")
                                    .type(JsonFieldType.NULL)
                                    .description("관련 데이터")
                    )));
    }
}
