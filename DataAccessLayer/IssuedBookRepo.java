package DataAccessLayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedBookRepo extends JpaRepository<IssuedBooks,Integer> {
}
