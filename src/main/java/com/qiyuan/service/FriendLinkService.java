package com.qiyuan.service;

import com.qiyuan.pojo.FriendLink;

import java.util.List;

public interface FriendLinkService {
    void insertFriendLink(FriendLink friendLink);

    List<FriendLink> getAllFriendLink();
    List<FriendLink> getEnableFriendLink();

    void deleteFriendLink(Integer id);

    FriendLink getFriendLinkById(Integer id);

    void updateFriendLink(FriendLink friendLink);
}

