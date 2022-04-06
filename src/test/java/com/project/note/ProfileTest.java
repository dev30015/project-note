package com.project.note;

import com.project.note.entity.Profile;
import com.project.note.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Optional;

@SpringBootTest
public class ProfileTest {
    @Resource
    private ProfileRepository profileRespo;

    @Test
    void findRespo() {
        Optional<Profile> profile = profileRespo.findProfileByUserId(1L);
        System.out.println(profile);
    }
}
