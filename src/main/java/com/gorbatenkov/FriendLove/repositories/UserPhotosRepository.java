package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPhotosRepository extends JpaRepository<UserPhoto,Integer> {

    Optional<UserPhoto> findTopByOrderByIdDesc();
    Optional<UserPhoto> findByPhotoPath(String path);

}
