package assignment2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DVD_Controller implements Runnable {

    private DVD_Library library;

    public DVD_Controller(DVD_Library library) {
        this.library = library;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Please select a service (Enter an integer from  1 to 6).");
            System.out.println("1. Save a new DVD.");
            System.out.println("2. Remove a DVD");
            System.out.println("3. Update a DVD");
            System.out.println("4. List all of the DVDs.");
            System.out.println("5. Search a DVD by title.");
            System.out.println("6. Exit");
            Scanner scanner = new Scanner(System.in);
            try {
                String line = scanner.nextLine().trim();
                int service_number = Integer.parseInt(line);
                String response = processService(service_number);
                System.out.println(response);
            } catch (Exception e) {
                System.err.println("Please select a service by entering an integer from 1 to 6.");
            }
        }
    }

    private String processService(int service_number) {
        switch (service_number) {
            case 1:
                return service_save();
            case 2:
                return service_remove();
            case 3:
                return service_update();
            case 4:
                return service_listAll();
            case 5:
                return service_findByTitle();
            case 6:
                return service_exit();
            default:
                throw new IllegalArgumentException();
        }
    }

    private String service_save() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the title of the DVD to save.");
        String title = scanner.nextLine();
        boolean existed = library.hasDVD(title);
        if (existed) {
            System.out.println("DVD already exists. Do you wish to continue to update? Key 'n' to exit, otherwise to continue.");
            String yOrN = scanner.nextLine();
            if (yOrN.equals("n")) {
                return "Canceled adding DVD. Return to service center.";
            }
        }
        System.out.println("Please enter the date of release of the DVD.");
        String release_date = scanner.nextLine();
        System.out.println("Please enter the rating of the DVD.");
        String rating = scanner.nextLine();
        System.out.println("Please enter the name of director of the DVD.");
        String director_name = scanner.nextLine();
        System.out.println("Please enter the name of studio of the DVD.");
        String studio = scanner.nextLine();
        System.out.println("Please add a note about the DVD.");
        String note = scanner.nextLine();
        System.out.println();
        DVD dvd = new DVD(title, release_date, rating, director_name, studio, note);
        System.out.println(dvd.info());
        System.out.println("Please confirm that you wish to save the above DVD. (y/n)");
        String confirmSave = scanner.nextLine();
        if (confirmSave.equals("y")) {
            library.save(dvd);
        } else {
            return "Canceled adding DVD. Return to service center.";
        }
        System.out.println("The above DVD is saved. Do you wish to add more DVDs? (y/n)");
        String continueAdding = scanner.nextLine();
        if (continueAdding.equals("y")) {
            return service_save();
        } else {
            return "Return to service center.";
        }
    }

    private String service_remove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the title of the DVD to remove.");
        String title = scanner.nextLine();
        boolean existed = library.hasDVD(title);
        if (!existed) {
            System.out.println("The DVD " + title + " does not exist in the library. Do you wish to remove another DVD? (y/n)");
            String continueRemove = scanner.nextLine();
            if (!continueRemove.equals("y")) {
                return "Canceled removal. Return to service center.";
            } else {
                return service_remove();
            }
        }
        DVD dvd = library.findByTitle(title);
        System.out.println(dvd.info());
        System.out.println();
        System.out.println("Please confirm that you wish to remove the above DVD. (y/n)");
        String confirmRemove = scanner.nextLine();
        if (!confirmRemove.equals("y")) {
            return "Canceled removal. Return to service center.";
        }
        library.remove(dvd);
        System.out.println("Successfully removed the DVD. Do you wish to remove more DVDs? (y/n)");
        String removeMore = scanner.nextLine();
        if (removeMore.equals("y")) {
            return service_remove();
        } else {
            return "Return to service center.";
        }
    }

    private String service_update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the title of the DVD to update.");
        String title = scanner.nextLine();
        boolean existed = library.hasDVD(title);
        if (!existed) {
            System.out.println("The DVD " + title + " does not exist in the library. Do you wish to update another DVD? (y/n)");
            String continueUpdate = scanner.nextLine();
            if (!continueUpdate.equals("y")) {
                return "Canceled update. Return to service center.";
            } else {
                return service_update();
            }
        }
        DVD old_dvd = library.findByTitle(title);
        DVD new_dvd = old_dvd.copy();
        System.out.println(old_dvd.info());
        boolean finishedEdit = false;
        while (!finishedEdit) {
            try {
                System.out.println("Please enter the attribute to edit.");
                System.out.println("1. Title");
                System.out.println("2. Date of Release");
                System.out.println("3. Rating");
                System.out.println("4. Director");
                System.out.println("5. Studio");
                System.out.println("6. Note");
                String line = scanner.nextLine().trim();
                int edit_number = Integer.parseInt(line);
                if (edit_number > 6 || edit_number < 1) throw new InputMismatchException();
                String response = edit(new_dvd, edit_number);
                System.out.println(response);
                System.out.println("Do you wish to edit a new attribute? (y/n)");
                String continueEdit = scanner.nextLine();
                if (!continueEdit.equals("y")) {
                    finishedEdit = true;
                }
            } catch (Exception e) {
                System.err.println("Please enter an integer between 1 to 6.");
            }
        }
        System.out.println(new_dvd.info());
        System.out.println();
        System.out.println("Please confirm that you wish to save the above updated DVD to the library. (y/n)");
        String confirmUpdate = scanner.nextLine();
        if (confirmUpdate.equals("y")) {
            library.remove(old_dvd);
            library.save(new_dvd);
        } else {
            return "Cancel update. Return to service center.";
        }
        System.out.println("Successfully updated DVD. Do you wish to update another DVD? (y/n)");
        String continueUpdate = scanner.nextLine();
        if (continueUpdate.equals("y")) {
            return service_update();
        } else {
            return "Return to service center.";
        }
    }

    private String edit(DVD dvd, int edit_number) {
        System.out.println("Please enter a new value");
        Scanner scanner = new Scanner(System.in);
        String new_value = scanner.nextLine();
        switch (edit_number) {
            case 1:
                dvd.setTitle(new_value);
                return "Successfully edit title.";
            case 2:
                dvd.setRelease_date(new_value);
                return "Successfully edit date of release.";
            case 3:
                dvd.setRating(new_value);
                return "Successfully edit rating.";
            case 4:
                dvd.setDirector_name(new_value);
                return "Successfully edit name of director.";
            case 5:
                dvd.setStudio(new_value);
                return "Successfully edit name of studio.";
            case 6:
                dvd.setNote(new_value);
                return "Successfully edit note.";
            default:
                throw new InputMismatchException();
        }
    }

    private String service_listAll() {
        System.out.println();
        for (DVD dvd : library.getAllDVDs()) {
            System.out.println(dvd.info());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("All DVDs are listed above. Press any key to return to service center.");
        try {
            System.in.read();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Return to service center.";
    }

    private String service_findByTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the title of the DVD.");
        String title = scanner.nextLine();
        DVD dvd = library.findByTitle(title);
        if (dvd == null) {
            System.out.println("No such DVD in the library. Do you wish to search another DVD? (y/n)");
            String searchMore = scanner.nextLine();
            if (searchMore.equals("y")) {
                return service_findByTitle();
            } else {
                return "Return to service center.";
            }
        }
        System.out.println();
        System.out.println(dvd.info());
        System.out.println("The DVD's info is listed above. Do you wish to search another DVD? (y/n)");
        String searchMore = scanner.nextLine();
        if (searchMore.equals("y")) {
            return service_findByTitle();
        } else {
            return "Return to service center.";
        }
    }

    private String service_exit() {
        library.update_library();
        System.exit(0);
        return null;
    }

}
