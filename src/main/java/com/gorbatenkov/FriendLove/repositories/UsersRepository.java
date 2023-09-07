package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User,Integer> {

    Optional<User> findByPhoneNumber(String phoneNumber);
    List<User> findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(String name,String surname);

}
