package uz.app.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.pcmarket.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
