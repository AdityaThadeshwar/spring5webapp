package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("PublisherName1");
        publisher.setAddressLine1("Address Line One");
        publisher.setCity("City");
        publisher.setZip("54122");

        publisherRepository.save(publisher);

        Author a1 = new Author("FirstName1", "LastName1");
        Book b1 = new Book("BookName", "2314231234");

        a1.getBooks().add(b1);
        b1.getAuthors().add(a1);
        b1.setPublisher(publisher);
        publisher.getBooks().add(b1);

        authorRepository.save(a1);
        bookRepository.save(b1);

        Author a2 = new Author("FirstName2", "LastName2");
        Book b2 = new Book("BookName", "2314231234");

        a2.getBooks().add(b2);
        b2.getAuthors().add(a2);
        b2.setPublisher(publisher);
        publisher.getBooks().add(b2);

        authorRepository.save(a2);
        bookRepository.save(b2);

        publisherRepository.save(publisher);

        System.out.println("In Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Books by Publisher: " + publisher.getBooks().size());

    }
}