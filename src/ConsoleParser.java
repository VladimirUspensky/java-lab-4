import java.util.Date;


/**
 * Enum with genders
 * UNKNOWN if it is impossible to determine the gender
 */

enum Genders {
    MALE, FEMALE, UNKNOWN
}

public class ConsoleParser {
    private String data;
    private String name;
    private String birthDate;

    /**
     * Main constructor
     * @param data - String from user's input which includes full name and birth date
     */
    ConsoleParser(String data) {
        this.data = data;
        this.parseInputData();
    }

    /**
     * Creates resulted data
     * @return changed user's data if or `Incorrect Input`
     */
    public String getChangedData() {
        if (parseInputData() == 0) {
            int age = determineAge();
            String name = changeName();
            Genders gender = determineGender();
            return "Name: " + name + '\n' + "Age: " + age + '\n' + "Gender: " + gender;
        } else {
            return "Incorrect Input";
        }
    }

    /**
     * Parses user's input and divide it into name and birth date
     * @return 0 if data is correct, -1 if it is incorrect
     */
    private int parseInputData() {
        String[] splitedData = data.split(" ");
        try {
            birthDate = splitedData[splitedData.length - 1];
            if (birthDate.length() < 10) {
                return -1;
            }
            name = data.split(birthDate)[0];
            if (name.split(" ").length < 3) {
                return -1;
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            return -1;
        }
        return 0;
    }

    /**
     * Calculates age from birth date
     * @return age or -1 if something went wrong
     */
    private int determineAge() {
        try {
            String[] splitedBirthDate = birthDate.split("\\.");
            Integer birthYear = Integer.valueOf(splitedBirthDate[splitedBirthDate.length - 1]);
            Date date = new Date();
            String[] splitedDate = date.toString().split(" ");
            Integer currentYear = Integer.valueOf(splitedDate[splitedDate.length - 1]);
            int age = currentYear - birthYear;
            if (age < 0) {
                return -1;
            }
            return age;
        } catch (NumberFormatException exception) {
            return -1;
        }
    }

    /**
     * Determines gender from name
     * @return gender (MALE, FEMALE) or UNKNOWN if was not possible to determine
     */
    private Genders determineGender() {
        String[] splitedName = name.split(" ");
        String patronymic = splitedName[splitedName.length - 1];
        if (patronymic.substring(patronymic.length() - 1).equals("ч")) {
            return Genders.MALE;
        } else if (patronymic.substring(patronymic.length() - 1).equals("а") ||
                   patronymic.substring(patronymic.length() - 1).equals("я")) {
            return Genders.FEMALE;
        }
        return Genders.UNKNOWN;
    }

    /**
     * Changes the name from format: Surname Name Patronymic into Surname N. P.
     * @return changed name or `None` if something went wrong
     */
    private String changeName() {
        String[] splitedName = name.split(" ");
        String[] newName = new String[3];
        newName[0] = splitedName[0];
        StringBuilder result = new StringBuilder("" + newName[0] + " ");

        for (int i = 0; i < splitedName.length; i++) {
            if (i == 0) continue;
            try {
                newName[i] = splitedName[i].charAt(0) + ".";
            } catch (ArrayIndexOutOfBoundsException exception) {
                return "None";
            }
            result.append(newName[i]).append(" ");
        }
        return result.toString();
    }

}
