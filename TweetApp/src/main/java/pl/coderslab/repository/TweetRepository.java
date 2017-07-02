package pl.coderslab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.model.Tweet;
import pl.coderslab.model.User;

public interface TweetRepository extends JpaRepository<Tweet, Long>{

	public List<Tweet> findByUserId(Long id);
	public List<Tweet> findByUser(User user);
	public List<Tweet> findAllByOrderByCreatedDesc();
	public List<Tweet> findByUserIdOrderByCreatedDesc(Long id);
}
