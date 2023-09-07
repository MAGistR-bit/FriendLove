package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.GroupPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPostsRepository extends JpaRepository<GroupPost,Integer> {
}
