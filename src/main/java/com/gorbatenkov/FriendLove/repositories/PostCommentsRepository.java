package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentsRepository extends JpaRepository<PostComment,Integer> {
}
