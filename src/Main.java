import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static final TreeMap<Character, Integer> _frequency = new TreeMap<>();
    private static boolean read(String name) {
        var file = new File(name);
        try (var br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                countFrequency(line);
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("error: file not found");
            return false;
        } catch (IOException e) {
            System.out.println("error while file opening");
            return false;
        }
    }
    private static void write(String name) {
        var str = new StringBuilder();
        for (Map.Entry<Character, Integer> entry: _frequency.entrySet())
            str.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        try {
            Files.writeString(Path.of(name), str.toString());
        } catch (IOException e) {
            System.out.println("error while writing to file");
        }
    }
    private static void countFrequency(String line) {
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (!Character.isLetter(c)) continue;
            if (_frequency.containsKey(c)) _frequency.put(c, _frequency.get(c) + 1);
            else _frequency.put(c, 1);
        }
    }
    public static void main(String[] args) {
        System.out.println("Write the path to file for reading:");
        var in = new Scanner(System.in);
        String inputFile = in.nextLine();
        boolean result = read(inputFile);
        if (!result) return;
        System.out.println("Write the path to file for writing:");
        String outputFile = in.nextLine();
        write(outputFile);
    }
}