package com.qiyuan.mapper;

import com.qiyuan.pojo.FriendLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendLinkMapper {
    void insertFriendLink(FriendLink friendLink);

    List<FriendLink> getAllFriendLink();
    List<FriendLink> getEnableFriendLink();

    Integer deleteFriendLinkById(Integer id);

    FriendLink getFriendLinkById(Integer id);

    Integer updateFriendLink(FriendLink friendLink);
}

