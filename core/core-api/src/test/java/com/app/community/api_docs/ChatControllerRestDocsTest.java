package com.app.community.api_docs;

import com.app.community.controller.ChatController;
import com.app.community.controller.request.ChatRequests.ChatRequest;
import com.app.community.domain.agg.chat.ChatService;
import com.app.community.domain.agg.chat.Chat;
import com.app.community.domain.agg.chat.ChatStatus;
import com.app.community.test.api.RestDocsTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.app.community.test.api.RestDocsUtils.requestPreprocessor;
import static com.app.community.test.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class ChatControllerRestDocsTest extends RestDocsTest {

    private ChatService chatService;
    private ChatController chatController;

    @BeforeEach
    public void setUp() {
        chatService = mock(ChatService.class);
        chatController = new ChatController(chatService);
        mockMvc = mockController(chatController);
    }

    @Test
    void createOrGetChat() {
        ChatRequest request = new ChatRequest(1L, 2L, 30);
        Chat chat = new Chat(1L, null, null, ChatStatus.PROGRESS);
        when(chatService.requestChat(any(), any())).thenReturn(chat);

        given().contentType(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .body(request)
                .post("/api/chats")
                .then()
                .statusCode(HttpStatus.OK.value())
                .apply(document("채팅 생성 또는 가져오기", requestPreprocessor(), responsePreprocessor(),
                        requestFields(
                                fieldWithPath("requesterId")
                                        .type(JsonFieldType.NUMBER)
                                        .description("요청자 ID"),
                                fieldWithPath("respondentId")
                                        .type(JsonFieldType.NUMBER)
                                        .description("응답자 ID"),
                                fieldWithPath("period")
                                        .type(JsonFieldType.NUMBER)
                                        .description("응답자가 설정한 채팅 기간")
                        ),
                        responseFields(
                                fieldWithPath("result")
                                        .type(JsonFieldType.STRING)
                                        .description("성공 여부"),
                                fieldWithPath("data.chatId")
                                        .type(JsonFieldType.NUMBER)
                                        .description("요청된 채팅 ID"),
                                fieldWithPath("data.status")
                                        .type(JsonFieldType.STRING)
                                        .description("채팅 가능 여부")
                        )));
    }
}
