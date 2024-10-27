package com.app.community.api_docs;

import com.app.community.api.controller.CommentController;
import com.app.community.api.controller.request.CommentRequests.NewCommentRequest;
import com.app.community.api.controller.request.CommentRequests.UpdateRequest;
import com.app.community.domain.model.comment.CommentService;
import com.app.community.test.api.RestDocsTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.app.community.test.api.RestDocsUtils.requestPreprocessor;
import static com.app.community.test.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;

public class CommentControllerRestDocsTest extends RestDocsTest {

    private CommentService commentService;
    private CommentController commentController;

    @BeforeEach
    public void setUp() {
        commentService = mock(CommentService.class);
        commentController = new CommentController(commentService);
        mockMvc = mockController(commentController);
    }

    @Test
    void createComment() {
        NewCommentRequest request = new NewCommentRequest(
                1L,
                "댓글 내용입니다.",
                2L,
                "COMMENT"
        );

        doNothing().when(commentService).create(any(), anyLong(), any(), anyString());

        given().contentType(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .body(request)
                .post("/api/comments")
                .then()
                .statusCode(HttpStatus.OK.value())
                .apply(document("댓글 생성", requestPreprocessor(), responsePreprocessor(),
                        requestFields(
                                fieldWithPath("articleId")
                                        .type(JsonFieldType.NUMBER)
                                        .description("아티클 ID")
                                        .attributes(key("constraints").value("댓글을 작성하는 아티클 ID")),
                                fieldWithPath("body")
                                        .type(JsonFieldType.STRING)
                                        .description("댓글 내용")
                                        .attributes(key("constraints").value("최소 1자 이상, 200자 이하.")),
                                fieldWithPath("targetId")
                                        .type(JsonFieldType.NUMBER)
                                        .optional()
                                        .description("댓글을 다는 대상 ID"),
                                fieldWithPath("targetType")
                                        .type(JsonFieldType.STRING)
                                        .optional()
                                        .description("댓글을 다는 대상 타입")
                                        .attributes(key("constraints").value("[\"QUESTION\", \"SHARE\" ,\"SHARE\"만 허용]"))
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

    @Test
    void updateComment() {
        UpdateRequest request = new UpdateRequest(
                "수정된 댓글 내용입니다."
        );

        doNothing().when(commentService).update(any(), anyLong(), anyString());

        given().contentType(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .body(request)
                .post("/api/comments/{commentId}", String.valueOf(1L))
                .then()
                .statusCode(HttpStatus.OK.value())
                .apply(document("댓글 수정", requestPreprocessor(), responsePreprocessor(),
                        pathParameters(
                                parameterWithName("commentId")
                                        .description("수정할 댓글의 ID")
                        ),
                        requestFields(
                                fieldWithPath("body")
                                        .type(JsonFieldType.STRING)
                                        .description("수정된 댓글 내용")
                                        .attributes(key("constraints").value("최소 1자 이상, 200자 이하."))
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

    @Test
    void deleteComment() {
        doNothing().when(commentService).delete(any(), anyLong());

        given().contentType(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .put("/api/comments/{commentId}", String.valueOf(1L))
                .then()
                .statusCode(HttpStatus.OK.value())
                .apply(document("댓글 삭제", requestPreprocessor(), responsePreprocessor(),
                        pathParameters(
                                parameterWithName("commentId")
                                        .description("삭제할 댓글의 ID")
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

    @Test
    void upvote() {
        // given
        Long memberId = 1L;
        Long commentId = 1L;
        doNothing().when(commentService).upvote(anyLong(), anyLong());


        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .pathParam("commentId", commentId)
                .when()
                .post("/api/comments/{commentId}/upvote")
                .then()
                .statusCode(HttpStatus.OK.value())
                .apply(document("comment-upvote",
                        requestPreprocessor(),
                        responsePreprocessor(),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("Bearer 인증 토큰")
                        ),
                        pathParameters(
                                parameterWithName("commentId")
                                        .description("추천할 댓글 ID")
                        ),
                        responseFields(
                                fieldWithPath("result")
                                        .type(JsonFieldType.STRING)
                                        .description("요청 처리 성공 여부"),
                                fieldWithPath("data")
                                        .type(JsonFieldType.NULL)
                                        .description("응답 데이터 (없음)")
                        )));
    }
}
