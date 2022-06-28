package assignment2;

import java.util.List;

public interface DVDRepo {

    String save(DVD dvd);
    String update(DVD dvd);
    String remove(DVD dvd);
    DVD findByTitle(String title);
    List<DVD> findAll();
    void list(String title);
    void listAll();


}
