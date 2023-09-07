package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Post,Integer> {
}
