package ru.plevda.pet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.plevda.pet.entity.db.UserEntity;
import ru.plevda.pet.entity.rest.User;
import ru.plevda.pet.entity.rest.UserResponse;
import ru.plevda.pet.repo.UserRepository;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse save(User user) {
        var userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setBirthdate(user.getBirthdate());
        userEntity.setAge(user.getAge());
        userRepository.save(userEntity);

        return UserResponse.builder()
                .message("Success")
                .build();
    }

    public UserResponse upload(Long id, MultipartFile file) throws IOException {
        Optional<UserEntity> temp = userRepository.findById(id);
        temp.ifPresent(t->{
            try {
                t.setFile(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            userRepository.save(t);
        });
        return UserResponse.builder()
                .message("Uploaded")
                .build();
    }

    public User get(String name) {
        return userRepository.findFirstByName(name)
                .map(userEntity -> User.builder()
                        .name(userEntity.getName())
                        .birthdate(userEntity.getBirthdate())
                        .age(userEntity.getAge())
                        .file(userEntity.getFile())
                        .build())
                .orElse(new User());
    }
}
