package com.app.community.api_docs;

import com.app.community.controller.CommentQueryController;
import com.app.community.domain.agg.comment.*;
import com.app.community.domain.agg.comment.CommentQuery.CommentDetails;
import com.app.community.domain.agg.comment.CommentQuery.ProfileComment;

import com.app.community.test.api.RestDocsTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.community.test.api.RestDocsUtils.requestPreprocessor;
import static com.app.community.test.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

public class CommentQueryControllerRestDocsTest extends RestDocsTest {

    private CommentReadService commentReadService;
    private CommentQueryController commentQueryController;

    @BeforeEach
    public void setUp() {
        commentReadService = mock(CommentReadService.class);
        commentQueryController = new CommentQueryController(commentReadService);
        mockMvc = mockController(commentQueryController);
    }

    @Test
    void getCommentList() {
        var author = new CommentQuery.CommentAuthor(1L, "user1", null, LocalDateTime.now(), LocalDateTime.now());
        var childComment = new CommentDetails(2L, 1L, 1L, "대댓글 내용", CommentStatus.STEADY, author, List.of(), LocalDateTime.now(), LocalDateTime.now(),0);
        var commentDetails = List.of(
                new CommentDetails(1L, 1L, null, "댓글 내용", CommentStatus.STEADY, author, List.of(childComment), LocalDateTime.now(), LocalDateTime.now(),0)
        );

        when(commentReadService.getCommentList(anyLong(), anyInt(), anyLong()))
                .thenReturn(commentDetails);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/api/comments/{articleId}", String.valueOf(1L))
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .apply(document("get-comment-list",
                        requestPreprocessor(),
                        responsePreprocessor(),
                        queryParameters(
                                parameterWithName("sz").description("가져올 댓글 수").optional(),
                                parameterWithName("cr").description("커서").optional()
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("성공 여부"),
                                fieldWithPath("data.nextCursor").type(JsonFieldType.NUMBER).optional().description("다음 페이지 시작 대상 ID"),
                                fieldWithPath("data.isLast").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
                                fieldWithPath("data.content[].id").type(JsonFieldType.NUMBER).description("댓글 ID"),
                                fieldWithPath("data.content[].articleId").type(JsonFieldType.NUMBER).description("아티클 ID"),
                                fieldWithPath("data.content[].parentId").type(JsonFieldType.NUMBER).optional().description("부모 댓글 ID"),
                                fieldWithPath("data.content[].body").type(JsonFieldType.STRING).description("댓글 내용"),
                                fieldWithPath("data.content[].status").type(JsonFieldType.STRING).description("댓글 상태"),
                                fieldWithPath("data.content[].createdAt").type(JsonFieldType.STRING).description("댓글 생성 날짜"),
                                fieldWithPath("data.content[].updatedAt").type(JsonFieldType.STRING).description("댓글 수정 날짜"),
                                fieldWithPath("data.content[].author.id").type(JsonFieldType.NUMBER).description("작성자 ID"),
                                fieldWithPath("data.content[].author.nickname").type(JsonFieldType.STRING).description("작성자 닉네임"),
                                fieldWithPath("data.content[].author.profileImagePath").type(JsonFieldType.STRING).optional().description("작성자 프로필 이미지 경로"),
                                fieldWithPath("data.content[].author.createdAt").type(JsonFieldType.STRING).description("작성자 생성 날짜"),
                                fieldWithPath("data.content[].author.updatedAt").type(JsonFieldType.STRING).description("작성자 수정 날짜"),
                                fieldWithPath("data.content[].upvoteCount").type(JsonFieldType.NUMBER).description("댓글 추천 수"),
                                fieldWithPath("data.content[].children").type(JsonFieldType.ARRAY).description("대댓글 목록"),
                                fieldWithPath("data.content[].children[].id").type(JsonFieldType.NUMBER).description("대댓글 ID"),
                                fieldWithPath("data.content[].children[].articleId").type(JsonFieldType.NUMBER).description("대댓글이 속한 아티클 ID"),
                                fieldWithPath("data.content[].children[].parentId").type(JsonFieldType.NUMBER).description("부모 댓글 ID"),
                                fieldWithPath("data.content[].children[].body").type(JsonFieldType.STRING).description("대댓글 내용"),
                                fieldWithPath("data.content[].children[].status").type(JsonFieldType.STRING).description("대댓글 상태"),
                                fieldWithPath("data.content[].children[].createdAt").type(JsonFieldType.STRING).description("대댓글 생성 날짜"),
                                fieldWithPath("data.content[].children[].updatedAt").type(JsonFieldType.STRING).description("대댓글 수정 날짜"),
                                fieldWithPath("data.content[].children[].author.id").type(JsonFieldType.NUMBER).description("대댓글 작성자 ID"),
                                fieldWithPath("data.content[].children[].author.nickname").type(JsonFieldType.STRING).description("대댓글 작성자 닉네임"),
                                fieldWithPath("data.content[].children[].author.profileImagePath").type(JsonFieldType.STRING).optional().description("대댓글 작성자 프로필 이미지 경로"),
                                fieldWithPath("data.content[].children[].author.createdAt").type(JsonFieldType.STRING).description("대댓글 작성자 생성 날짜"),
                                fieldWithPath("data.content[].children[].author.updatedAt").type(JsonFieldType.STRING).description("대댓글 작성자 수정 날짜"),
                                fieldWithPath("data.content[].children[].children").type(JsonFieldType.ARRAY).description("대대댓글 목록 [없음]"),
                                fieldWithPath("data.content[].children[].upvoteCount").type(JsonFieldType.NUMBER).description("대댓글 추천 수")
                        )));
    }

    @Test
    void getAnswerByMember() {
        var profileComments = List.of(
                new ProfileComment(1L, 1L, "아티클 제목", "답글 내용", LocalDateTime.now(), LocalDateTime.now()),
                new ProfileComment(2L, 1L, "아티클 제목", "답글 내용2", LocalDateTime.now(), LocalDateTime.now())
        );


        when(commentReadService.getAnswerByMember(any(), anyInt(), any()))
                .thenReturn(profileComments);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .param("sz", 20)
                .param("cr", -1)
                .when()
                .get("/api/comments/profiles-answer")
                .then()
                .log().all()  // 응답 로그를 확인
                .statusCode(HttpStatus.OK.value())
                .apply(document("get-answer-by-member",
                        requestPreprocessor(),
                        responsePreprocessor(),
                        queryParameters(
                                parameterWithName("sz").description("가져올 답글 수").optional(),
                                parameterWithName("cr").description("커서").optional()
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("성공 여부"),
                                fieldWithPath("data.content[]").type(JsonFieldType.ARRAY).description("댓글 리스트"),
                                fieldWithPath("data.content[].commentId").type(JsonFieldType.NUMBER).description("댓글 ID"),
                                fieldWithPath("data.content[].articleId").type(JsonFieldType.NUMBER).description("아티클 ID"),
                                fieldWithPath("data.content[].articleTitle").type(JsonFieldType.STRING).description("아티클 제목"),
                                fieldWithPath("data.content[].commentBody").type(JsonFieldType.STRING).description("댓글 내용"),
                                fieldWithPath("data.content[].createdAt").type(JsonFieldType.STRING).description("댓글 생성 날짜"),
                                fieldWithPath("data.content[].updatedAt").type(JsonFieldType.STRING).description("댓글 수정 날짜"),
                                fieldWithPath("data.nextCursor").type(JsonFieldType.NUMBER).optional().description("다음 페이지의 커서"),
                                fieldWithPath("data.isLast").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부")
                        )));

    }

}
