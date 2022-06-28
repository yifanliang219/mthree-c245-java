package assignment2;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        DVD_Library library = new DVD_Library("dvd_library.txt");
        DVD_Controller controller = new DVD_Controller(library);
        controller.run();
    }

}
