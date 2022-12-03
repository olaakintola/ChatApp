package com.dec.chatapp.mapper;

import com.dec.chatapp.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES (#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    Integer insertUser(User user);

    @Delete("DELETE FROM USERS WHERE userid = #{userid}")
    void deleteUser(Integer userid);
}
