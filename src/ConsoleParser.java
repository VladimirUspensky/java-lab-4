import java.util.Date;


enum Genders {
    MALE, FEMALE, UNKNOWN
}

public class ConsoleParser {
    private String data;
    private String name;
    private String birthDate;

    ConsoleParser(String data) {
        this.data = data;
        this.parseInputData();
    }

    public String getChangedData() {
        if (parseInputData() == 0) {
            int age = determineAge();
            String name = determineChangedName();
            Genders gender = determineGender();
            return "Name: " + name + '\n' + "Age: " + age + '\n' + "Gender: " + gender;
        } else {
            return "Incorrect Input";
        }
    }

    public int parseInputData() {
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

    public int determineAge() {
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

    public Genders determineGender() {
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

    public String determineChangedName() {
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
