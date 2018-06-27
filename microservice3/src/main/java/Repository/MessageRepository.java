package Repository;

import Domain.Message;
import Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("SELECT m FROM Message m WHERE m.poster.id IN (SELECT f.id FROM App_User u LEFT JOIN u.following f WHERE u.id = :userId) ORDER BY m.postTime DESC")
    List<Message> generateTimeLine(@Param("userId") int posterID);
    List<Message> findByPoster_Id(int posterId);
    List<Message> findByContentLike(String content);
}
