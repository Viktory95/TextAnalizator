package analizator.fileworkers;

import analizator.entities.Word;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Vi on 30.05.2017.
 */
public final class LocalFileWriter {
    private LocalFileWriter() {
    }

    public static void writeAnalizeData(String outputPath, List<Word> wordsList, String analyzingData) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputPath
                + File.separator
                + "TextAnalizatorResult.txt"))) {
            writer.write(analyzingData + "\n\n");
            for (Word word : wordsList) {
                writer.write(word.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
