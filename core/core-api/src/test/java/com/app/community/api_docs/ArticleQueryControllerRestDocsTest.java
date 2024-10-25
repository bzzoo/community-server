package com.app.community.api_docs;

import com.app.community.api.controller.ArticleQueryController;
import com.app.community.domain.query.ArticleQuery;
import com.app.community.domain.query.ArticleQueryService;
import com.app.community.domain.model.article.ArticleType;
import com.app.community.test.api.RestDocsTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.app.community.test.api.RestDocsUtils.requestPreprocessor;
import static com.app.community.test.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;

public class ArticleQueryControllerRestDocsTest extends RestDocsTest {

    private ArticleQueryService articleQueryService;
    private ArticleQueryController articleQueryController;

    @BeforeEach
    public void setUp() {
        articleQueryService = mock(ArticleQueryService.class);
        articleQueryController = new ArticleQueryController(articleQueryService);
        mockMvc = mockController(articleQueryController);
    }

    @Test
    void getArticleListTest() {
        List<ArticleQuery.ArticleSummary> dummyArticles = List.of(
                createArticleSummary(1L, "Test Title", "Test Body", "SHARE", "Author1"));
        when(articleQueryService.getLatestArticleList(any(), any(), any())).thenReturn(dummyArticles);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/articles")
                .then()
                .statusCode(HttpStatus.OK.value())
                .log().all()
                .apply(document("get-article-list", requestPreprocessor(), responsePreprocessor(),
                        queryParameters(
                                parameterWithName("sz").optional().description("페이지 크기 [기본값: 20]"),
                                parameterWithName("cr").optional().description("커서 대상 ID [기본값: null]"),
                                parameterWithName("tp").optional().description("아티클 유형 [기본값: null]")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("성공 여부"),
                                fieldWithPath("data.nextCursor").type(JsonFieldType.NUMBER).optional().description("다음 페이지 시작 대상 ID"),
                                fieldWithPath("data.isLast").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
                                fieldWithPath("data.content[]").type(JsonFieldType.ARRAY).description("아티클 리스트"),
                                fieldWithPath("data.content[].id").type(JsonFieldType.NUMBER).optional().description("아티클 ID"),
                                fieldWithPath("data.content[].contents.title").type(JsonFieldType.STRING).description("아티클 제목"),
                                fieldWithPath("data.content[].contents.body").type(JsonFieldType.STRING).description("아티클 본문"),
                                fieldWithPath("data.content[].type").type(JsonFieldType.STRING).optional().description("아티클 유형"),
                                fieldWithPath("data.content[].author.id").type(JsonFieldType.NUMBER).description("아이클 ID"),
                                fieldWithPath("data.content[].author.nickname").type(JsonFieldType.STRING).description("작성자 닉네임"),
                                fieldWithPath("data.content[].author.profileImagePath").optional().type(JsonFieldType.STRING).description("작성자 프로필 이미지 경로"),
                                fieldWithPath("data.content[].author.createdAt").type(JsonFieldType.STRING).description("작성자 계정 생성일"),
                                fieldWithPath("data.content[].author.updatedAt").type(JsonFieldType.STRING).description("작성자 계정 수정일"),
                                fieldWithPath("data.content[].keywords[]").optional().type(JsonFieldType.ARRAY).description("키워드 리스트"),
                                fieldWithPath("data.content[].keywords[].id").type(JsonFieldType.NUMBER).description("키워드 ID"),
                                fieldWithPath("data.content[].keywords[].name").type(JsonFieldType.STRING).description("키워드 이름"),
                                fieldWithPath("data.content[].createdAt").type(JsonFieldType.STRING).description("작성 시간"),
                                fieldWithPath("data.content[].updatedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                fieldWithPath("data.content[].viewCount").type(JsonFieldType.NUMBER).description("조회 수"),
                                fieldWithPath("data.content[].commentCount").type(JsonFieldType.NUMBER).description("댓글 수"),
                                fieldWithPath("data.content[].upvoteCount").type(JsonFieldType.NUMBER).description("추천 수")
                        )));
    }

    @Test
    void getArticleDetailsTest() {
        ArticleQuery.ArticleDetails dummyArticle =
                createArticleDetails(1L, "Test Title", "Test Body", "SHARE", "Author1");
        when(articleQueryService.getArticleDetails(any(), any())).thenReturn(dummyArticle);

        given().contentType(ContentType.JSON)
                .when()
                .get("/api/articles/{articleId}", String.valueOf(1L))
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .apply(document("get-article-details", requestPreprocessor(), responsePreprocessor(),
                        pathParameters(
                                parameterWithName("articleId").description("조회할 아티클 ID")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("성공 여부"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("아티클 ID"),
                                fieldWithPath("data.contents.title").type(JsonFieldType.STRING).description("아티클 제목"),
                                fieldWithPath("data.contents.body").type(JsonFieldType.STRING).description("아티클 본문"),
                                fieldWithPath("data.type").type(JsonFieldType.STRING).description("아티클 유형"),
                                fieldWithPath("data.author.id").type(JsonFieldType.NUMBER).description("작성자 ID"),
                                fieldWithPath("data.author.nickname").type(JsonFieldType.STRING).description("작성자 닉네임"),
                                fieldWithPath("data.author.profileImagePath").optional().type(JsonFieldType.STRING).description("작성자 프로필 이미지 경로"),
                                fieldWithPath("data.author.createdAt").type(JsonFieldType.STRING).description("작성자 계정 생성일"),
                                fieldWithPath("data.author.updatedAt").type(JsonFieldType.STRING).description("작성자 계정 수정일"),
                                fieldWithPath("data.keywords").optional().type(JsonFieldType.ARRAY).description("키워드 리스트"),
                                fieldWithPath("data.keywords[].id").type(JsonFieldType.NUMBER).description("키워드 ID"),
                                fieldWithPath("data.keywords[].name").type(JsonFieldType.STRING).description("키워드 이름"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("작성 시간"),
                                fieldWithPath("data.updatedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                fieldWithPath("data.viewCount").type(JsonFieldType.NUMBER).description("조회 수"),
                                fieldWithPath("data.commentCount").type(JsonFieldType.NUMBER).description("댓글 수"),
                                fieldWithPath("data.upvoteCount").type(JsonFieldType.NUMBER).description("추천 수")
                        )));
    }

    @Test
    void getArticleListByMemberTest() {
        List<ArticleQuery.ArticleActivity> dummyArticles = List.of(
                createArticleActivity(1L, 1L, "Member Title", "Member Body", "SHARE"));
        when(articleQueryService.getArticleListByMemberId(anyInt(), any(), any(), any())).thenReturn(dummyArticles);

        given().log().all()
                .body(dummyArticles)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .when()
                .get("/api/articles/profiles")
                .then()
                .statusCode(HttpStatus.OK.value())
                .apply(document("get-article-list-by-member", requestPreprocessor(), responsePreprocessor(),
                        queryParameters(
                                parameterWithName("sz").optional().description("페이지 크기 [기본값: 20]"),
                                parameterWithName("cr").optional().description("커서 대상 ID [기본값: null]"),
                                parameterWithName("tp").optional().description("아티클 유형 [기본값: null]")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("성공 여부"),
                                fieldWithPath("data.nextCursor").type(JsonFieldType.NUMBER).optional().description("다음 페이지 시작 대상 ID"),
                                fieldWithPath("data.isLast").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
                                fieldWithPath("data.content[].id").type(JsonFieldType.NUMBER).description("아티클 ID"),
                                fieldWithPath("data.content[].contents.title").type(JsonFieldType.STRING).description("아티클 제목"),
                                fieldWithPath("data.content[].contents.body").type(JsonFieldType.STRING).description("아티클 본문"),
                                fieldWithPath("data.content[].type").type(JsonFieldType.STRING).description("아티클 유형"),
                                fieldWithPath("data.content[].createdAt").type(JsonFieldType.STRING).description("작성 시간"),
                                fieldWithPath("data.content[].updatedAt").type(JsonFieldType.STRING).description("마지막 수정 시간")
                        )));
    }


    private ArticleQuery.ArticleSummary createArticleSummary(Long id, String title, String body, String type, String authorNickname) {
        ArticleQuery.ArticleSummary articleSummary = new ArticleQuery.ArticleSummary();
        articleSummary.setId(id);
        articleSummary.setContents(new ArticleQuery.ArticleContentInfo(title, body));
        articleSummary.setType(ArticleType.valueOf(type));
        articleSummary.setAuthor(new ArticleQuery.ArticleAuthor(1L, authorNickname, null, LocalDateTime.now(), LocalDateTime.now()));
        articleSummary.setKeywords(Collections.emptyList());
        articleSummary.setCreatedAt(LocalDateTime.now());
        articleSummary.setUpdatedAt(LocalDateTime.now());
        articleSummary.setViewCount(0);
        articleSummary.setUpvoteCount(0);
        articleSummary.setCommentCount(0);
        return articleSummary;
    }

    private ArticleQuery.ArticleDetails createArticleDetails(Long id, String title, String body, String type, String authorNickname) {
        ArticleQuery.ArticleDetails articleDetails = new ArticleQuery.ArticleDetails();
        articleDetails.setId(id);
        articleDetails.setContents(new ArticleQuery.ArticleContentInfo(title, body));
        articleDetails.setType(ArticleType.valueOf(type));
        articleDetails.setAuthor(new ArticleQuery.ArticleAuthor(1L, authorNickname, null, LocalDateTime.now(), LocalDateTime.now()));
        articleDetails.setKeywords(Collections.emptyList());
        articleDetails.setCreatedAt(LocalDateTime.now());
        articleDetails.setUpdatedAt(LocalDateTime.now());
        articleDetails.setViewCount(0);
        articleDetails.setUpvoteCount(0);
        articleDetails.setCommentCount(0);
        return articleDetails;
    }

    private ArticleQuery.ArticleActivity createArticleActivity(Long id, Long articleId, String title, String body, String articleType) {
        ArticleQuery.ArticleActivity articleActivity = new ArticleQuery.ArticleActivity();
        articleActivity.setId(id);
        articleActivity.setId(articleId);
        articleActivity.setContents(new ArticleQuery.ArticleContentInfo(title, body));
        articleActivity.setType(ArticleType.valueOf(articleType));
        articleActivity.setCreatedAt(LocalDateTime.now());
        articleActivity.setUpdatedAt(LocalDateTime.now());
        return articleActivity;
    }
}
