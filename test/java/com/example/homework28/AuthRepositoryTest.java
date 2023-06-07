package com.example.homework28;

import com.example.homework28.Model.MyUser;
import com.example.homework28.Repository.AuthRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthRepositoryTest {

    @Autowired
    AuthRepository authRepository;

    MyUser user1, user2;

    @BeforeEach
    void setUp(){
        user1 = new MyUser(null, "user","1234","Admin",null);
    }

    @Test
    public void findMyUserById(){
        authRepository.save(user1);
        user2 = authRepository.findMyUserById(user1.getId());
        Assertions.assertThat(user2).isEqualTo(user1);
    }

    @Test
    public void findMyUserByUsername(){
        authRepository.save(user1);
        user2 = authRepository.findMyUserByUsername(user1.getUsername());
        Assertions.assertThat(user2).isEqualTo(user1);
    }
}
