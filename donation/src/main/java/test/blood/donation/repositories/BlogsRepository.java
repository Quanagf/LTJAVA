package test.blood.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import test.blood.donation.model.Blogs;

import java.util.List;

public interface BlogsRepository extends JpaRepository<Blogs, Integer> {
    List<Blogs> findByUserId(int userId);
    List<Blogs> findByStatus(String status);
}
