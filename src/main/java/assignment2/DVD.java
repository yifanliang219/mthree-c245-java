package assignment2;

public class DVD {

    private String title;
    private String release_date;
    private String rating;
    private String director_name;
    private String studio;
    private String note;

    public DVD(String title, String release_date, String rating, String director_name, String studio, String note) {
        this.title = title;
        this.release_date = release_date;
        this.rating = rating;
        this.director_name = director_name;
        this.studio = studio;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirector_name() {
        return director_name;
    }

    public void setDirector_name(String director_name) {
        this.director_name = director_name;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String info() {
        return "Title: " + title + "\n" +
                "Date of Release: " + release_date + "\n" +
                "Rating: " + rating + "\n" +
                "Director: " + director_name + "\n" +
                "Studio: " + studio + "\n" +
                "Note: " + note + "\n";
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s\n",
                title,
                release_date,
                rating,
                director_name,
                studio,
                note);
    }

    public static DVD parse(String dvd_string) {
        String[] attributes = dvd_string.split(",");
        String title = attributes[0];
        String release_date = attributes[1];
        String rating = attributes[2];
        String director_name = attributes[3];
        String studio = attributes[4];
        String note = attributes[5];
        return new DVD(title, release_date, rating, director_name, studio, note);
    }

    public DVD copy() {
        return new DVD(title, release_date, rating, director_name, studio, note);
    }

}
