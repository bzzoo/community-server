package com.app.community.api_docs;

import com.app.community.controller.ArticleController;
import com.app.community.controller.request.ArticleRequests.*;
import com.app.community.controller.request.ArticleRequests.NewArticleRequest;
import com.app.community.domain.agg.article.ArticleService;
import com.app.community.test.api.RestDocsTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static com.app.community.test.api.RestDocsUtils.requestPreprocessor;
import static com.app.community.test.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;

public class ArticleControllerRestDocsTest extends RestDocsTest {

    private ArticleService articleService;

    private ArticleController articleController;

    @BeforeEach
    public void setUp() {
        articleService = mock(ArticleService.class);
        articleController = new ArticleController(articleService);
        mockMvc = mockController(articleController);
    }

    @Test
    void createArticle() {
        NewArticleRequest request = new NewArticleRequest(
                "제목 입니다.",
                "내용 입니다.",
                "SHARE",
                List.of("Java", "Spring")
        );

        doNothing().when(articleService).create(any(), any(), any(), anyList());

        given().contentType(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .body(request)
                .post("/api/articles")
                .then()
                .statusCode(HttpStatus.OK.value())
                .apply(document("create-article", requestPreprocessor(), responsePreprocessor(),
                        requestFields(
                                fieldWithPath("title")
                                        .type(JsonFieldType.STRING)
                                        .description("제목")
                                        .attributes(key("constraints").value("최소 10자 이상")),
                                fieldWithPath("body")
                                        .type(JsonFieldType.STRING)
                                        .description("본문")
                                        .attributes(key("constraints").value("최소 10자 이상")),
                                fieldWithPath("articleType")
                                        .type(JsonFieldType.STRING)
                                        .description("아티클 유형")
                                        .attributes(key("constraints").value("[\"QUESTION\", \"SHARE\"만 허용]")),
                                fieldWithPath("keywords")
                                        .optional()
                                        .type(JsonFieldType.ARRAY)
                                        .description("키워드")
                                        .attributes(key("constraints").value("키워드 20자미만, 최대 5개의 키워드"))
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
    void updateArticle() {
        UpdateRequest request = new UpdateRequest(
                "수정된 제목 입니다.",
                "수정된 내용 입니다.",
                List.of("Java", "Spring Boot")
        );

        doNothing().when(articleService).update(any(), anyLong(), any(), anyList());

        given().contentType(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .body(request)
                .put("/api/articles/{articleId}",  String.valueOf(1L))
                .then()
                .statusCode(HttpStatus.OK.value())
                .apply(document("아티클 수정", requestPreprocessor(), responsePreprocessor(),
                        requestFields(
                                fieldWithPath("title")
                                        .type(JsonFieldType.STRING)
                                        .description("제목")
                                        .attributes(key("constraints").value("최소 10자 이상")),
                                fieldWithPath("body")
                                        .type(JsonFieldType.STRING)
                                        .description("본문")
                                        .attributes(key("constraints").value("최소 10자 이상")),
                                fieldWithPath("keywords")
                                        .optional()
                                        .type(JsonFieldType.ARRAY)
                                        .description("키워드")
                                        .attributes(key("constraints").value("키워드 20자미만, 최대 5개의 키워드"))
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
    void deleteArticle() {
        doNothing().when(articleService).delete(any(), anyLong());

        given().contentType(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .put("/api/articles/{articleId}",  String.valueOf(1L))
                .then()
                .statusCode(HttpStatus.OK.value())
                .apply(document("아티클 삭제", requestPreprocessor(), responsePreprocessor(),
                        pathParameters(
                                parameterWithName("articleId")
                                        .description("The ID of the article to delete.")
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