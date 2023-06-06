package com.example.homework28.Service;

import com.example.homework28.Model.MyUser;
import com.example.homework28.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public List<MyUser> getAll(){
        return authRepository.findAll();
    }

    public MyUser getUser(Integer id){
        return authRepository.findMyUserById(id);
    }

    public void register(MyUser myUser){
        String hash = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        myUser.setRole("CUSTOMER");
        authRepository.save(myUser);
    }

}
