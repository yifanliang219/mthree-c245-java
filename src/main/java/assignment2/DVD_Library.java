package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class DVD_Library implements DVDRepo {

    private final Map<String, DVD> dvds = new HashMap<>();
    private final String FILE_LOCATION;

    public DVD_Library(String FILE_LOCATION){
        this.FILE_LOCATION = FILE_LOCATION;
        import_from_file();
    }

    public Collection<DVD> getAllDVDs(){
        return dvds.values();
    }

    @Override
    public String save(DVD dvd) {
        dvds.put(dvd.getTitle(), dvd);
        return dvd.getTitle() + " has been saved to library.";
    }

    @Override
    public String update(DVD dvd) {
        dvds.put(dvd.getTitle(), dvd);
        return dvd.getTitle() + " has been updated.";
    }

    @Override
    public String remove(DVD dvd) {
        dvds.remove(dvd.getTitle());
        return dvd.getTitle() + " has been removed.";
    }

    @Override
    public DVD findByTitle(String title) {
        return dvds.get(title);
    }

    @Override
    public List<DVD> findAll() {
        return new ArrayList<>(dvds.values());
    }

    @Override
    public void list(String title) {
        DVD dvd = dvds.get(title);
        System.out.println(dvd.info());
    }

    @Override
    public void listAll() {
        for(DVD dvd: dvds.values()){
            System.out.println(dvd.info());
        }
    }

    public boolean hasDVD(String dvd_title){
        return dvds.containsKey(dvd_title);
    }

    private void import_from_file(){
        try {
            Scanner sc = new Scanner(
                    new BufferedReader(new FileReader(FILE_LOCATION)));
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.isEmpty()) break;
                DVD dvd = DVD.parse(line);
                dvds.put(dvd.getTitle(), dvd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update_library(){
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_LOCATION));
            for(DVD dvd: dvds.values()){
                pw.print(dvd.toString());
            }
            pw.flush();
            pw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
