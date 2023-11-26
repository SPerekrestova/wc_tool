package org.example;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.NameFileFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] arguments = reader.readLine().split("\\s");

        String command = arguments[0];
        String option = arguments[1];
        String file = arguments[2];

        System.out.println("Looking for a file: " + file);
        File foundFile = FileUtils.listFiles(new File("/Users/svetlana/IdeaProjects/wc_tool"),
                new NameFileFilter(file),
                FileFilterUtils.trueFileFilter()
        ).iterator().next();

        if (option.equals("-c")) { // bytes
            byte[] bytes = FileUtils.readFileToByteArray(foundFile);
            System.out.println(bytes.length + " " + file);
        } else if (option.equals("-l")) { // lines count
            LineIterator lineIterator = FileUtils.lineIterator(foundFile);
            Long linesCount = 0L;
            while (lineIterator.hasNext()) {
                linesCount++;
                lineIterator.next();
            }
            lineIterator.close();
            System.out.println(linesCount + " " + file);
        } else if (option.equals("-w")) {
            LineIterator lineIterator = FileUtils.lineIterator(foundFile);
            long wordCount = 0L;
            while (lineIterator.hasNext()) {
                String[] split = lineIterator.nextLine().split("\\s");
                if (split.length != 0) {
                    wordCount += split.length;
                }
            }
            lineIterator.close();
            System.out.println(wordCount + " " + file);
        }
    }
}
