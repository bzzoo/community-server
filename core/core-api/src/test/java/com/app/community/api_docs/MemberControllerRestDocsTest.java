package com.app.community.api_docs;

import com.app.community.controller.MemberController;
import com.app.community.controller.request.MemberRequests.UpdateProfileRequest;
import com.app.community.domain.agg.member.*;
import com.app.community.test.api.RestDocsTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.app.community.test.api.RestDocsUtils.requestPreprocessor;
import static com.app.community.test.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class MemberControllerRestDocsTest extends RestDocsTest {

    private MemberService memberService;
    private MemberController memberController;

    @BeforeEach
    public void setUp() {
        memberService = mock(MemberService.class);
        memberController = new MemberController(memberService);
        mockMvc = mockController(memberController);
    }

    @Test
    void updateProfile() {

        UpdateProfileRequest request = new UpdateProfileRequest(
                "test@example.com",
                "https://example.com/image.png",
                "newNickname",
                100,
                false,
                MemberPosition.NONE
        );

        when(memberService.updateProfile(any(), any())).thenReturn(null);

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .body(request)
                .when()
                .post("/api/members", String.valueOf(1L))
                .then()
                .statusCode(HttpStatus.OK.value())
                .apply(document("프로필 수정", requestPreprocessor(), responsePreprocessor(),
                        requestFields(
                                fieldWithPath("email")
                                        .type(JsonFieldType.STRING)
                                        .description("회원의 이메일 주소"),
                                fieldWithPath("profileImagePath")
                                        .type(JsonFieldType.STRING)
                                        .description("회원의 프로필 이미지 URL"),
                                fieldWithPath("nickname")
                                        .type(JsonFieldType.STRING)
                                        .description("회원의 새로운 닉네임"),
                                fieldWithPath("chatPeePoint")
                                        .type(JsonFieldType.NUMBER)
                                        .description("회원의 채팅 금액"),
                                fieldWithPath("chatRefusal")
                                        .type(JsonFieldType.BOOLEAN)
                                        .description("회원의 채팅 거부 여부"),
                                fieldWithPath("memberPosition")
                                        .type(JsonFieldType.STRING)
                                        .description("회원의 위치 (예: USER, ADMIN 등)")
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
