package assignment1.dog_genetics;

import java.util.Scanner;

public class DogGenetics {

    private enum DOG_BREED {
        BERNARD("St. Bernard"), CHIHUAHUA("Chihuahua"), PUG("Dramatic RedNosed Asian Pug"), CUR("Common Cur"), DOBERMAN("King Doberman");
        String breed;

        DOG_BREED(String breed) {
            this.breed = breed;
        }
    }

    private static int[] random_percentages(int numOfCategories) {
        double[] random_values = new double[numOfCategories];
        double sum = 0;
        for (int i = 0; i < numOfCategories; i++) {
            double random = Math.random();
            sum += random;
            random_values[i] = random;
        }

        if(sum == 0) return random_percentages(numOfCategories);

        double scale = 100.0 / sum;
        int[] percentages = new int[numOfCategories];
        int realSum = 0;
        for (int i = 0; i < numOfCategories; i++) {
            int percentage = (int) Math.round(random_values[i] * scale);
            realSum += percentage;
            percentages[i] = percentage;
        }
        int difference = 100 - realSum;
        percentages[numOfCategories - 1] += difference;
        return percentages;
    }

    public static void main(String[] args) {
        int numOfCategories = DOG_BREED.values().length;
        int[] percentages = random_percentages(numOfCategories);
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < numOfCategories; i++) {
            String breed = DOG_BREED.values()[i].breed;
            int p = percentages[i];
            report.append(p).append("% ").append(breed).append("\n");
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your dog's name?");
        String dog_name = scanner.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + dog_name + "'s prestigious background right here.\n\n" +
                dog_name + " is:\n\n" +
                report +
                "Wow, that's QUITE the dog!"
        );
    }

}
