package com.cir3.chessgame.repository;


import com.cir3.chessgame.domain.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepository extends JpaRepository<Friends,Long> {


}
