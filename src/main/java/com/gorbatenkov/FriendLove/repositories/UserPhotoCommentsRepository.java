package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.UserPhotoComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotoCommentsRepository extends JpaRepository<UserPhotoComment,Integer> {
}
