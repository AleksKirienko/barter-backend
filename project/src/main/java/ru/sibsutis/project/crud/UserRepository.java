package ru.sibsutis.project.crud;

import ru.sibsutis.project.databases.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);

    User getUserByEmail(String email);
}

