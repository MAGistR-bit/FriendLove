package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.GroupPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPostCommentsRepository extends JpaRepository<GroupPostComment,Integer> {
}
