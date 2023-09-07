package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.GroupPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupPhotosRepository extends JpaRepository<GroupPhoto,Integer> {
    Optional<GroupPhoto> findTopByOrderByIdDesc();
    Optional<GroupPhoto> findByPhotoPath(String path);
}
