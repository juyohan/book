package readinglist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import readinglist.Read.ReaderDB;

@Repository
public interface ReaderRepository extends JpaRepository<ReaderDB, String> {

}
