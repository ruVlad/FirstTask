package sample.model;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.*;

public class Model {


    private List<Path> paths = new ArrayList<>();
    private String path;
    private Path output;

// Генерация файлов: создается 100 файло, в каждый из которых записывается 100.000 строк
    public void generation(String path) {

        Random random = new Random();
        this.path = path;

        for (int i = 0; i < 100; i++) {
            File file = new File(path + "\\" + i + ".txt");
            paths.add(Paths.get(path + "\\" + i + ".txt"));
            try (FileWriter fileWriter = new FileWriter(path + "\\" + i + ".txt")) {
                // запись всей строки
                for (int j = 0; j < 100_000; j++)
                    fileWriter.write(randomDate() + "||" + randomLetterEng() + "||" + randomLetterRus() + "||" +
                            (random.nextInt(100_000_000) + 1) + "||" + (double) (random.nextInt(19_0000_0000) + 1_0000_0000) / 1_0000_0000 + "\n");


                fileWriter.flush();
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }

        }
    }

//генерация даты
    private String randomDate() {
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        int minDay = (int) LocalDate.of(year - 5, month, day).toEpochDay();
        int maxDay = (int) LocalDate.of(year, month, day).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        LocalDate date = LocalDate.ofEpochDay(randomDay);

        return date.toString();
    }


//    Метод для генерации 10 латинский символов
    private String randomLetterEng() {
        String str = "";
        Random random = new Random();

        for (; str.length() < 10; ) {
            int number = random.nextInt(58) + 65;
            if (number > 90 && number < 97) continue;
            str += (char) number;
        }
        return str;
    }

    //    Метод для генерации 10 русских символов
    private String randomLetterRus() {
        String str = "";
        Random random = new Random();

        for (; str.length() < 10; ) {
            int number = random.nextInt(64) + 1040;
            str += (char) number;
        }
        return str;
    }

//    Считываются все строки каждого файла и записываются в общий
//    Те строки которые содержат введенную подстроку не записываются
    public String union(String line, String fileName) throws IOException {

        output = Paths.get(fileName);
        int count = 0;
        for (Path path : paths) {
            List<String> lines = Files.readAllLines(path);

            if (!line.isEmpty()) {
                Iterator<String> strIterator = lines.iterator();
                while (strIterator.hasNext()) {

                    String nextStr = strIterator.next();
                    if (nextStr.contains(line)) {
                        strIterator.remove();
                        count++;
                    }
                }
            }
            Files.write(output, lines, StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        }
        return count + " rows were deleted";
    }


}
