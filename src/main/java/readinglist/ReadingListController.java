package readinglist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; // bean이란 스프링 컨테이너에서 생성된 객체를 스프링 빈이라고 한다.
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import readinglist.Read.ReaderDB;

@Controller
// bean으로 등록(= 스프링 컨테이너에서 객체를 생성) 즉, 이 클래스가 Controller임을 나타냄.
@RequestMapping("/") // URL경로인 /로 매핑함, 그리고 어떤 메소드가 처리할지 맵핑
public class ReadingListController {
//    private static final String reader="craig";

    private ReadingListRepository readingListRepository;

    @Autowired // 컨테이너 안에 존재하는 bean을 자동으로 주입함.
    public ReadingListController(ReadingListRepository readingListRepository){
        this.readingListRepository=readingListRepository;
    }

    @RequestMapping(method=RequestMethod.GET) //RequestMethod는 HTTP일때 사용
    // GET은 정보가 노출됨. 원래 (value="경로", method=RequestMethod.GET) 이렇게 사용함.
    // "/" < 이게 포함 될때 실행, HTTP Request 메소드 값을 매핑 조건으로 부여
    public String readersBooks(ReaderDB reader, Model model){
        // 메소드(인스턴스 변수에 지정한 reader의 Book리스트를 컨트롤러의 생성자로 주입된 리포지토리에서 조회하는 메소드)
        List<Book> readingList=readingListRepository.findByReader(reader);
        if (readingList != null){
            model.addAttribute("books", readingList); // books는 key.
            model.addAttribute("reader", reader);
            // readingList, reader 객체를 books, reader라는 이름으로 추가한다.
            // model 객체를 이용해 view로 데이터를 전달함. ↑이거.
        }
        return "readingList"; // view 파일 리턴
    }

    @RequestMapping(method = RequestMethod.POST) // POST는 정보가 패킷 안에 숨겨져서 전송이 된다.
    public String addToReadingList(ReaderDB reader, Book book){ // 메소드(프로퍼티의 값이 함수인 경우)
        book.setReader(reader);
        readingListRepository.save(book); // save메서드를 이용해 book이라는 객체를 저장한다.
        return "redirect:/";
    }

}
