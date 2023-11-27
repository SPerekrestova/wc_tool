package org.example;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

public class App {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] arguments = reader.readLine().split("\\s");

        File foundFile;

        if (arguments.length < 3) {
            String file = arguments[1];
            foundFile = getFile(file);
            countLines(foundFile, file);
            countWords(foundFile, file);
            countChars(foundFile, file);
        } else {
            String option = arguments[1];
            String file = arguments[2];
            foundFile = getFile(file);

            if (option.equals("-c")) { // bytes
                countBytes(foundFile, file);
            } else if (option.equals("-l")) { // lines count
                countLines(foundFile, file);
            } else if (option.equals("-w")) { // words count
                countWords(foundFile, file);
            } else if (option.equals("-m")) { // count chars
                countChars(foundFile, file);
            }
        }
    }

    private static void countChars(File foundFile, String file) throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(foundFile);
        String text1 = new String(bytes, StandardCharsets.UTF_8);
        char[] chars = text1.toCharArray();
        System.out.println(chars.length + " " + file);
    }

    private static File getFile(String file) {
        System.out.println("Looking for a file: " + file);
        return FileUtils.listFiles(new File("/Users/svetlana/IdeaProjects/wc_tool"),
                new NameFileFilter(file),
                FileFilterUtils.trueFileFilter()
        ).iterator().next();
    }

    private static void countWords(File foundFile, String file) throws IOException {
        LineIterator lineIterator = FileUtils.lineIterator(foundFile);
        long wordCount = 0L;
        while (lineIterator.hasNext()) {
            String s = lineIterator.nextLine();
            wordCount += WordUtils.initials(s).length();
        }
        lineIterator.close();
        System.out.println(wordCount + " " + file);
    }

    private static void countLines(File foundFile, String file) throws IOException {
        List<String> lines = FileUtils.readLines(foundFile, StandardCharsets.UTF_8);
        System.out.println(lines.size() + " " + file);
    }

    private static void countBytes(File foundFile, String file) throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(foundFile);
        System.out.println(bytes.length + " " + file);
    }
}
