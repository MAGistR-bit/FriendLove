package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.GroupPhotoComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPhotoCommentsRepository extends JpaRepository<GroupPhotoComment,Integer> {
}
