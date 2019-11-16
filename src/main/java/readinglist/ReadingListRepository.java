package readinglist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import readinglist.Read.ReaderDB;

@Repository //이 인터페이스는 JpaRepository임을 나타내줌으로 무조건 붙여야한다.
public interface ReadingListRepository extends JpaRepository<Book, Long> { // 데이터베이스를 접근하게 해줌.
    // <>안에는 Entity 클래스 이름과 ID필드 타입이 지정되어야 한다. ex) <엔티티 ID 유형>
    // Book이라는 엔티티 클래스는 ID를 Long이라는 유형으로 지정하고 있기 때문에 위 처럼 작성하였다.
    List<Book> findByReader(ReaderDB reader); // 독자의 이름으로 도서 목록 검색

    // findByReader의 메소드를 통해 Reader 클래스에 있는 reader의 이름으로 Book이라는 클래스를 List로 불러옴
}
