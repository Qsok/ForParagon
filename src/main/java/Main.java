/**
 * Created by Kru on 13.03.2016.
 */

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static final int amountOfWords = 20;

    public static <String, Integer extends Comparable> List<Map.Entry<String, Integer>>
    sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
                return (obj2.getValue()).compareTo(obj1.getValue());
            }
        });
        return list;
    }

    public static String readFromFileToStr(String path) {
        StringBuilder str = new StringBuilder();
        try {
            List<String> lines =
                    Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
            for (String line : lines) {
                str.append(line + " ");
            }
        } catch (IOException e) {
            System.err.println("Error in file reading.");
            e.printStackTrace();
            System.exit(0);
        }
        return str.toString().replace("\uFEFF", "");
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.print("Error : empty args in main");
            System.exit(0);
        }
        String[] words = readFromFileToStr(args[0])
                .toLowerCase()
                .split("\\s*[^a-zA-Zа-яА-Я]+\\s*");
        HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
        for (String currWord : words) {
            if (wordMap.containsKey(currWord)) {
                wordMap.replace(currWord, wordMap.get(currWord) + 1);
            } else {
                wordMap.put(currWord, 1);
            }
        }
        List<Map.Entry<String, Integer>> wordList = sortByValue(wordMap);
        for (int i = 0; i < amountOfWords && i < wordList.size(); i++) {
            System.out.println(wordList.get(i).toString());
        }

    }

}
