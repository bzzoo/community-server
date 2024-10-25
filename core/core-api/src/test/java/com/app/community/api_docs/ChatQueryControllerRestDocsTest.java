package com.app.community.api_docs;

import com.app.community.api.controller.ChatQueryController;
import com.app.community.domain.query.ChatQueryService;
import com.app.community.domain.model.chat.MessageType;
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
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;

public class ChatQueryControllerRestDocsTest extends RestDocsTest {

    private ChatQueryService chatQueryService;
    private ChatQueryController chatQueryController;

    @BeforeEach
    public void setUp() {
        chatQueryService = mock(ChatQueryService.class);
        chatQueryController = new ChatQueryController(chatQueryService);
        mockMvc = mockController(chatQueryController);
    }

    @Test
    void getMyChatList() {
        var chatList = List.of(
                new ChatSummary(1L, new Participant(1L, "user1"), new Participant(2L, "user2"), "마지막 메시지", LocalDateTime.now(),
                        new ChatDateTimeInfo(LocalDateTime.now(), LocalDateTime.now())),
                new ChatSummary(2L, new Participant(1L, "user1"), new Participant(3L, "user3"), "마지막 메시지2", LocalDateTime.now(),
                        new ChatDateTimeInfo(LocalDateTime.now(), LocalDateTime.now()))
        );

        when(chatQueryService.getChatListByMemberId(any(), anyInt(), any()))
                .thenReturn(chatList);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .param("sz", 20)
                .param("cr", -1)
                .when()
                .get("/api/chats")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .apply(document("get-my-chat-list",
                        requestPreprocessor(),
                        responsePreprocessor(),
                        queryParameters(
                                parameterWithName("sz").description("가져올 채팅 수").optional(),
                                parameterWithName("cr").description("커서").optional()
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("성공 여부"),
                                fieldWithPath("data.nextCursor").type(JsonFieldType.NUMBER).optional().description("다음 페이지 시작 대상 ID"),
                                fieldWithPath("data.isLast").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
                                fieldWithPath("data.content[]").type(JsonFieldType.ARRAY).description("채팅 리스트"),
                                fieldWithPath("data.content[].id").type(JsonFieldType.NUMBER).description("채팅 ID"),
                                fieldWithPath("data.content[].sender.id").type(JsonFieldType.NUMBER).description("발신자 ID"),
                                fieldWithPath("data.content[].sender.nickname").type(JsonFieldType.STRING).description("발신자 닉네임"),
                                fieldWithPath("data.content[].receiver.id").type(JsonFieldType.NUMBER).description("수신자 ID"),
                                fieldWithPath("data.content[].receiver.nickname").type(JsonFieldType.STRING).description("수신자 닉네임"),
                                fieldWithPath("data.content[].lastMessage").type(JsonFieldType.STRING).description("마지막 메시지"),
                                fieldWithPath("data.content[].lastUpdatedAt").type(JsonFieldType.STRING).description("마지막 메시지 업데이트 시간"),
                                fieldWithPath("data.content[].period.createdAt").type(JsonFieldType.STRING).description("채팅방 생성 시간"),
                                fieldWithPath("data.content[].period.endDate").type(JsonFieldType.STRING).description("채팅 종료 날짜")
                        )));
    }

    @Test
    void getChatMessageList() {
        var chatMessages = List.of(
                new ChatMessageInfo(1L, 1L, new Participant(1L, "user1"), "메시지 본문", LocalDateTime.now(), MessageType.MESSAGE, true),
                new ChatMessageInfo(2L, 1L, new Participant(2L, "user2"), "메시지 본문2", LocalDateTime.now(), MessageType.MESSAGE, false)
        );

        when(chatQueryService.getChatMessageList(any(), anyLong(), anyInt(), any()))
                .thenReturn(chatMessages);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer token")
                .param("sz", 20)
                .param("cr", -1)
                .when()
                .get("/api/chats/{chatId}/messages", String.valueOf(1L  ))
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .apply(document("get-chat-message-list",
                        requestPreprocessor(),
                        responsePreprocessor(),
                        queryParameters(
                                parameterWithName("sz").description("가져올 메시지 수").optional(),
                                parameterWithName("cr").description("커서").optional()
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("성공 여부"),
                                fieldWithPath("data.nextCursor").type(JsonFieldType.NUMBER).optional().description("다음 페이지 시작 대상 ID"),
                                fieldWithPath("data.isLast").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
                                fieldWithPath("data.content[]").type(JsonFieldType.ARRAY).description("메시지 리스트"),
                                fieldWithPath("data.content[].id").type(JsonFieldType.NUMBER).description("메시지 ID"),
                                fieldWithPath("data.content[].chatId").type(JsonFieldType.NUMBER).description("채팅방 ID"),
                                fieldWithPath("data.content[].sender.id").type(JsonFieldType.NUMBER).description("발신자 ID"),
                                fieldWithPath("data.content[].sender.nickname").type(JsonFieldType.STRING).description("발신자 닉네임"),
                                fieldWithPath("data.content[].body").type(JsonFieldType.STRING).description("메시지 본문"),
                                fieldWithPath("data.content[].createdAt").type(JsonFieldType.STRING).description("메시지 생성 시간"),
                                fieldWithPath("data.content[].messageType").type(JsonFieldType.STRING).description("메시지 타입"),
                                fieldWithPath("data.content[].isRead").type(JsonFieldType.BOOLEAN).description("메시지 읽음 여부")
                        )));
    }
}
