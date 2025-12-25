package com.qiyuan.service.impl;

import com.qiyuan.mapper.FriendLinkMapper;
import com.qiyuan.pojo.FriendLink;
import com.qiyuan.service.FriendLinkService;
import com.qiyuan.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class FriendLinkServiceImpl implements FriendLinkService {
    @Autowired
    private FriendLinkMapper friendLinkMapper;
    @Autowired
    private MailUtil mailUtil;

    @Override
    public void insertFriendLink(FriendLink friendLink) {
        friendLink.setStatus(0);
        friendLink.setUpdateTime(LocalDateTime.now());
        friendLink.setCreateTime(LocalDateTime.now());
        friendLinkMapper.insertFriendLink(friendLink);
    }

    @Override
    public List<FriendLink> getAllFriendLink() {
        return friendLinkMapper.getAllFriendLink();
    }

    @Override
    public List<FriendLink> getEnableFriendLink() {
        return friendLinkMapper.getEnableFriendLink();
    }

    @Override
    public void deleteFriendLink(Integer id) {
        friendLinkMapper.deleteFriendLinkById(id);
    }

    @Override
    public FriendLink getFriendLinkById(Integer id) {
        return friendLinkMapper.getFriendLinkById(id);
    }

    @Override
    public void updateFriendLink(FriendLink friendLink) {
        if (Objects.equals(friendLink.getStatus(), FriendLink.ENABLE)) {
            mailUtil.sendEmail(friendLink.getAuthorEmail(), "友链申请通过通知",
                    "您好，您的友链 " + friendLink.getName() + " 申请已通过审核，感谢您的支持！");
        }
        friendLink.setUpdateTime(LocalDateTime.now());
        friendLinkMapper.updateFriendLink(friendLink);
    }
}

