package Boyer_Moore_Algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BM {
    static String[] states = {
            "Alabama",
            "Alaska",
            "Arizona",
            "Arkansas",
            "California",
            "Colorado",
            "Connecticut",
            "Delaware",
            "Florida",
            "Georgia",
            "Hawaii",
            "Idaho",
            "Illinois",
            "Indiana",
            "Iowa",
            "Kansas",
            "Kentucky",
            "Louisiana",
            "Maine",
            "Maryland",
            "Massachusetts",
            "Michigan",
            "Minnesota",
            "Mississippi",
            "Missouri",
            "Montana",
            "Nebraska",
            "Nevada",
            "New Hampshire",
            "New Jersey",
            "New Mexico",
            "New York",
            "North Carolina",
            "North Dakota",
            "Ohio",
            "Oklahoma",
            "Oregon",
            "Pennsylvania",
            "Rhode Island",
            "South Carolina",
            "South Dakota",
            "Tennessee",
            "Texas",
            "Utah",
            "Vermont",
            "Virginia",
            "Washington",
            "West Virginia",
            "Wisconsin",
            "Wyoming"
    };

    // word alignments in left-to-right order
    // character alignments in right-to-left order

    static void displayString(String[] stateArray) {
        System.out.println("States:");

        for (String state : stateArray) {
            System.out.println(state);
            System.out.println();
        }
    }

    static String buildString(String[] stateArray) {
        StringBuilder longString = new StringBuilder();

        for (String state : stateArray) {
            longString.append(state);
        }

        return longString.toString().toLowerCase().replaceAll("\\s+", "").trim();
    }

    static String searchString(Scanner scanner, String states) {
        StringBuilder matches = new StringBuilder();
        String pattern = "";

        System.out.print("Enter pattern to search: ");
        pattern = scanner.nextLine().toLowerCase().replaceAll("\\s+", "").trim();
        System.out.println("You have entered: " + pattern);

        System.out.println("String to search: " + states);

        Map<Character, Integer> badCharTable = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            badCharTable.put(pattern.charAt(i), i); // Last occurrence of each character
        }

        int shift = pattern.length();
        int i = 0;
        while (i <= states.length() - pattern.length()) {

            int j = pattern.length() - 1;
            while (j >= 0 && states.charAt(i + j) == pattern.charAt(j)) {
                j--;
            }

            if (j < 0) {
                // Full Match
                matches.append("Match at position ")
                        .append(i)
                        .append(": '")
                        .append(states.substring(i, i + pattern.length()))
                        .append("'\n");
                shift = pattern.length(); // move past match
            } else {
                // Bad character
                char badChar = states.charAt(i + j);
                int lastOccurrence = badCharTable.getOrDefault(badChar, -1);
                shift = Math.max(1, j - lastOccurrence);

            }

            i += shift;
        }

        return matches.toString();
    }

    static String menu(Scanner scanner) {
        String response = "";

        System.out.println("Select options 1-3 on the menu");
        System.out.println(
                "1) Display the text\n" +
                        "2) Search\n" +
                        "3) Exit program\n");

        response = scanner.nextLine();

        return response;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String allStates = buildString(states);

        while (true) {
            String response = menu(scanner);

            switch (response) {
                case "1":
                    displayString(states);
                    break;
                case "2":
                    String matches = searchString(scanner, allStates);

                    System.out.println("Your matches:");
                    System.out.println(matches);

                    break;
                case "3":
                    System.out.println("Exiting Program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, select 1-3");
            }
        }

    }
}
