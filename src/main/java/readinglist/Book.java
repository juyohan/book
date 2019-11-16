package readinglist;

import readinglist.Read.ReaderDB;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

 @Entity // class를 JPA로 지정함. 즉, book이라는 클래스는 DB의 테이블과 매칭됨.
public class Book {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     // Entity의 유일성을 식별하고 자동으로 값을 제공하는 필드로 지정
     // @GeneratedValue는 기본키를 설정하는 것에 대한 속성값을 조정할 수 있다.
     // .AUTO는 기본설정이며, .IDENTITY : 기본키 생성을 데이터베이스가 함 ,
     // .SEQUENCE : DB시퀀스를 사용해 기본키 할당, .TABLE : 키 생성 테이블 생성
     private Long id;
     private String isbn;
     private String title;
     private String author;
     private String description;
     private ReaderDB reader;

     public Long getid(){
         return id;
     }
     public void setId(Long id) {
         this.id = id;
     }
     public ReaderDB getReader() {
         return reader;
     }

     public void setReader(ReaderDB reader) {
         this.reader = reader;
     }

     public String getIsbn() {
         return isbn;
     }

     public void setIsbn(String isbn) {
         this.isbn = isbn;
     }

     public String getTitle() {
         return title;
     }

     public void setTitle(String title) {
         this.title = title;
     }

     public String getAuthor() {
         return author;
     }

     public void setAuthor(String author) {
         this.author = author;
     }

     public String getDescription() {
         return description;
     }

     public void setDescription(String description) {
         this.description = description;
     }
}
