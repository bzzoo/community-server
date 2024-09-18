package com.app.community.api_docs;

import com.app.community.controller.ArticleQueryController;
import com.app.community.domain.agg.article.ArticleQuery;
import com.app.community.domain.agg.article.ArticleReadService;
import com.app.community.domain.agg.article.ArticleType;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;

public class ArticleQueryControllerRestDocsTest extends RestDocsTest {

    private ArticleReadService articleReadService;
    private ArticleQueryController articleQueryController;

    @BeforeEach
    public void setUp() {
        articleReadService = mock(ArticleReadService.class);
        articleQueryController = new ArticleQueryController(articleReadService);
        mockMvc = mockController(articleQueryController);
    }

    @Test
    void getArticleListTest() {

        List<ArticleQuery.ArticleSummary> dummyArticles = List.of(
                createArticleSummary(1L, "Test Title", "Test Body", "SHARE", "Author1")
        );

        when(articleReadService.getLatestArticleList(any(), any(), any())).thenReturn(dummyArticles);
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/articles")
                .then()
                .statusCode(HttpStatus.OK.value())
                .apply(document("get-article-list", requestPreprocessor(), responsePreprocessor(),
                        queryParameters(
                                parameterWithName("sz").optional().description("페이지 크기"),
                                parameterWithName("cr").optional().description("커서 ID"),
                                parameterWithName("tp").optional().description("아티클 유형")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("성공 여부"),
                                fieldWithPath("data.nextCursor").type(JsonFieldType.NULL).optional().description("다음 페이지의 커서 (null일 수 있음)"),
                                fieldWithPath("data.isLast").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
                                fieldWithPath("data.content[]").type(JsonFieldType.ARRAY).description("아티클 리스트"),
                                fieldWithPath("data.content[].id").type(JsonFieldType.NUMBER).optional().description("아티클 ID (null일 수 있음)"),
                                fieldWithPath("data.content[].contents.title").type(JsonFieldType.STRING).optional().description("아티클 제목 (null일 수 있음)"),
                                fieldWithPath("data.content[].contents.body").type(JsonFieldType.STRING).optional().description("아티클 본문 (null일 수 있음)"),
                                fieldWithPath("data.content[].type").type(JsonFieldType.STRING).optional().description("아티클 유형 (null일 수 있음)"),
                                fieldWithPath("data.content[].author.id").type(JsonFieldType.NUMBER).optional().description("작성자 ID (null일 수 있음)"),
                                fieldWithPath("data.content[].author.nickname").type(JsonFieldType.STRING).optional().description("작성자 닉네임 (null일 수 있음)"),
                                fieldWithPath("data.content[].author.profileImagePath").type(JsonFieldType.STRING).optional().description("작성자 프로필 이미지 경로 (null일 수 있음)"),
                                fieldWithPath("data.content[].author.createdAt").type(JsonFieldType.STRING).optional().description("작성자 계정 생성일 (null일 수 있음)"),
                                fieldWithPath("data.content[].author.updatedAt").type(JsonFieldType.STRING).optional().description("작성자 계정 수정일 (null일 수 있음)"),
                                fieldWithPath("data.content[].keywords[]").type(JsonFieldType.ARRAY).optional().description("키워드 리스트 (null일 수 있음)"),
                                fieldWithPath("data.content[].keywords[].id").type(JsonFieldType.NUMBER).optional().description("키워드 ID (null일 수 있음)"),
                                fieldWithPath("data.content[].keywords[].name").type(JsonFieldType.STRING).optional().description("키워드 이름 (null일 수 있음)"),
                                fieldWithPath("data.content[].createdAt").type(JsonFieldType.STRING).optional().description("작성 시간 (null일 수 있음)"),
                                fieldWithPath("data.content[].updatedAt").type(JsonFieldType.STRING).optional().description("수정 시간 (null일 수 있음)")
                        )));
    }

    @Test
    void getArticleDetailsTest() {
        ArticleQuery.ArticleDetails dummyArticle =
                createArticleDetails(1L, "Test Title", "Test Body", "SHARE", "Author1");


        when(articleReadService.getArticleDetails(any(), any())).thenReturn(dummyArticle);
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/articles/{articleId}", String.valueOf(1L))
                .then()
                .statusCode(HttpStatus.OK.value())
                .apply(document("get-article-details", requestPreprocessor(), responsePreprocessor(),
                        pathParameters(
                                parameterWithName("articleId").description("조회할 아티클 ID")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("성공 여부"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("아티클 ID"),
                                fieldWithPath("data.contents.title").type(JsonFieldType.STRING).optional().description("아티클 제목 (null일 수 있음)"),
                                fieldWithPath("data.contents.body").type(JsonFieldType.STRING).optional().description("아티클 본문 (null일 수 있음)"),
                                fieldWithPath("data.type").type(JsonFieldType.STRING).optional().description("아티클 유형"),
                                fieldWithPath("data.author.id").type(JsonFieldType.NUMBER).optional().description("작성자 ID"),
                                fieldWithPath("data.author.nickname").type(JsonFieldType.STRING).optional().description("작성자 닉네임"),
                                fieldWithPath("data.author.profileImagePath").type(JsonFieldType.STRING).optional().description("작성자 프로필 이미지 경로 (null일 수 있음)"),
                                fieldWithPath("data.author.createdAt").type(JsonFieldType.STRING).optional().description("작성자 계정 생성일 (null일 수 있음)"),
                                fieldWithPath("data.author.updatedAt").type(JsonFieldType.STRING).optional().description("작성자 계정 수정일 (null일 수 있음)"),
                                fieldWithPath("data.keywords").type(JsonFieldType.ARRAY).optional().description("키워드 리스트"),
                                fieldWithPath("data.keywords[].id").type(JsonFieldType.NUMBER).optional().description("키워드 ID"),
                                fieldWithPath("data.keywords[].name").type(JsonFieldType.STRING).optional().description("키워드 이름"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).optional().description("작성 시간"),
                                fieldWithPath("data.updatedAt").type(JsonFieldType.STRING).optional().description("수정 시간")
                        )));
    }

    @Test
    void getArticleListByMemberTest() {
        List<ArticleQuery.ArticleActivity> dummyArticles = List.of(
                createArticleActivity(1L, 1L, "Member Title", "Member Body", "SHARE")
        );

        // Mock 설정
        when(articleReadService.getArticleListByMemberId(any(), any(), any(), any())).thenReturn(dummyArticles);
        given().contentType(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .when()
                .get("/api/articles/profiles")
                .then()
                .statusCode(HttpStatus.OK.value())
                .apply(document("get-article-list-by-member", requestPreprocessor(), responsePreprocessor(),
                        queryParameters(
                                parameterWithName("sz").optional().description("페이지 크기"),
                                parameterWithName("cr").optional().description("커서 ID"),
                                parameterWithName("tp").optional().description("아티클 유형")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).optional().description("성공 여부"),
                                fieldWithPath("data.nextCursor").type(JsonFieldType.NUMBER).optional().description("다음 페이지의 커서"),
                                fieldWithPath("data.isLast").type(JsonFieldType.BOOLEAN).optional().description("마지막 페이지 여부"),
                                fieldWithPath("data.content[].id").type(JsonFieldType.NUMBER).optional().description("활동 ID"),
                                fieldWithPath("data.content[].articleId").type(JsonFieldType.NUMBER).optional().description("아티클 ID"),
                                fieldWithPath("data.content[].contents.title").type(JsonFieldType.STRING).optional().description("아티클 제목 (null일 수 있음)"),
                                fieldWithPath("data.content[].contents.body").type(JsonFieldType.STRING).optional().description("아티클 본문 (null일 수 있음)"),
                                fieldWithPath("data.content[].articleType").type(JsonFieldType.STRING).optional().description("아티클 유형"),
                                fieldWithPath("data.content[].createdAt").type(JsonFieldType.STRING).optional().description("작성 시간"),
                                fieldWithPath("data.content[].updatedAt").type(JsonFieldType.STRING).optional().description("수정 시간"),
                                fieldWithPath("data.nextCursor").type(JsonFieldType.NUMBER).optional().description("다음 페이지의 커서")
                        )));
    }


    private ArticleQuery.ArticleSummary createArticleSummary(Long id, String title, String body, String type, String authorNickname) {
        ArticleQuery.ArticleSummary articleSummary = new ArticleQuery.ArticleSummary();
        articleSummary.setId(id);
        articleSummary.setContents(new ArticleQuery.ArticleContentInfo(title, body));
        articleSummary.setType(ArticleType.valueOf(type));
        articleSummary.setAuthor(new ArticleQuery.ArticleAuthor(1L, authorNickname, null, LocalDateTime.now(), LocalDateTime.now()));
        articleSummary.setKeywords(List.of(new ArticleQuery.ArticleKeywordInfo()));
        return articleSummary;
    }

    private ArticleQuery.ArticleDetails createArticleDetails(Long id, String title, String body, String type, String authorNickname) {
        ArticleQuery.ArticleDetails articleDetails = new ArticleQuery.ArticleDetails();
        articleDetails.setId(id);
        articleDetails.setContents(new ArticleQuery.ArticleContentInfo(title, body));
        articleDetails.setType(ArticleType.valueOf(type));
        articleDetails.setAuthor(new ArticleQuery.ArticleAuthor(1L, authorNickname, null, LocalDateTime.now(), LocalDateTime.now()));
        articleDetails.setKeywords(List.of(new ArticleQuery.ArticleKeywordInfo()));
        return articleDetails;
    }

    private ArticleQuery.ArticleActivity createArticleActivity(Long id, Long articleId, String title, String body, String articleType) {
        ArticleQuery.ArticleActivity articleActivity = new ArticleQuery.ArticleActivity();
        articleActivity.setId(id);
        articleActivity.setArticleId(articleId);
        articleActivity.setContents(new ArticleQuery.ArticleContentInfo(title, body));
        articleActivity.setArticleType(ArticleType.valueOf(articleType));
        return articleActivity;
    }
}
