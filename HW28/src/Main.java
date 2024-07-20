import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final long KB_SIZE = 1024;
    private static final long MB_SIZE = KB_SIZE * 1024;
    private static final long GB_SIZE = MB_SIZE * 1024;

    public static void main(String[] args) throws IOException {
        System.out.println("============= ДЗ 2 =============");
        System.out.println("Введите путь к файлу или папке: ");
        String path = new Scanner(System.in).nextLine();
        printSize(path);
        System.out.println("============= ДЗ 3 =============");
        setSale();
    }

    private static void printSize(String path) {
        //todo Дописать код расчета размера и корректного отображения
        File file = new File(path);
        long fileSize;
        try {
            if (file.isFile()) {
                fileSize = file.length();
            } else {
                fileSize = Files.walk(Path.of(path))
                        .filter(f -> f.toFile().isFile())
                        .mapToLong(f -> f.toFile().length())
                        .sum();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sizeConverter(fileSize);
    }

    private static void sizeConverter(double fileSize) {
        if (fileSize <= KB_SIZE) {
            System.out.printf("%.2f b\n", fileSize);
        } else if (fileSize <= MB_SIZE) {
            System.out.printf("%.2f kb\n", fileSize / KB_SIZE);
        } else if (fileSize <= GB_SIZE) {
            System.out.printf("%.2f mb\n", fileSize / MB_SIZE);
        } else {
            System.out.printf("%.2f gb\n", fileSize / GB_SIZE);
        }
    }

    private static void setSale() throws IOException {
        //todo Тут написать код для ДЗ #3
        List<String> lists = Files.readAllLines(Path.of("HW28/data/car_price.txt"));
        List<Car> cars = lists.stream().map(l -> {
                    String[] items = l.split("\\s");
                    if (items.length < 3) {
                        throw new RuntimeException("Error!");
                    }
                    return new Car(items[0], Integer.parseInt(items[1]), Double.parseDouble(items[2]));
                })
                .toList();
        LocalDate now = LocalDate.now();
        List<String> carWithPrice = cars.stream()
                .map(car -> {
                    int age = now.getYear() - car.getYears();
                    double discount = age > 5 ? 0.05 : 0.02;
                    double priceWithDiscount = car.getPrice() * (1 - discount);
                    return car.getMark() + " " + car.getYears() + " " + priceWithDiscount;
                }).toList();
        Files.write(Path.of("HW28/data/car_price2.txt"), carWithPrice);
    }
}
