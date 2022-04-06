package com.project.note.repository;

import com.project.note.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {
    //Profile
    @Query(value="select * from profile p where p.user_uid=?1",nativeQuery=true)
    Optional<Profile> findProfileByUserId(Long uid);

}
