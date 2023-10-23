package ru.plevda.pet.repo;

import org.springframework.data.repository.CrudRepository;
import ru.plevda.pet.entity.db.UserEntity;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findFirstByName(String name);
}

