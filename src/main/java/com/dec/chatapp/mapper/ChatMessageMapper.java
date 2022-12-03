package com.udacity.jwdnd.c1.review.mapper;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ChatMessageMapper {

    @Select("SELECT * FROM MESSAGES WHERE messageid = #{messageid}")
    ChatMessage findChatMessage(Integer messageid);

    @Select("SELECT * FROM MESSAGES")
    List<ChatMessage> getChatHistory();

    @Insert("INSERT INTO MESSAGES (usernameinput, messageinput) VALUES (#{usernameinput}, #{messageinput})")
    @Options(useGeneratedKeys = true, keyProperty = "messageid")
    Integer insertChatMessage(ChatMessage chatMessage);

    @Delete("DELETE * FROM MESSAGES WHERE messageid = #{messageid}")
    void deleteChatMessage(Integer messageid);
}
