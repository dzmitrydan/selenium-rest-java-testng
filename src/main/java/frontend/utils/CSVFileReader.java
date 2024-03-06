package frontend.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVFileReader {
    private static final String DIR_PATH = "target/download";

    public static double getPriceFromCsv() {
        try (CSVReader reader = new CSVReader(new FileReader(DIR_PATH + "/" + getNameLastDownloadedFile()))) {
            List<String[]> records = reader.readAll();
            for (String[] record : records) {
                for (int i = 0; i < record.length - 1; i++) {
                    if ("Total Price:".equals(record[i])) {
                        Pattern pattern = Pattern.compile("\\d+\\.\\d+");
                        Matcher matcher = pattern.matcher(record[i + 1]);
                        if (matcher.find()) {
                            String extractedNumber = matcher.group();
                            DecimalFormat decimalFormat = new DecimalFormat("#.##");
                            return Double.parseDouble(decimalFormat.format(Double.parseDouble(extractedNumber)));
                        }
                    }
                }
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Error processing CSV file: " + e.getMessage(), e);
        }
        return 0.0;
    }

    public static void deleteAllFilesInDirectory(){
        Path path = Paths.get(DIR_PATH);

        if (Files.exists(path) && Files.isDirectory(path)) {
            try {
                Files.walkFileTree(path, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        if (exc == null) {
                            Files.delete(dir);
                            return FileVisitResult.CONTINUE;
                        } else {
                            throw exc;
                        }
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Directory does not exist or is not a directory");
        }
    }

    private static String getNameLastDownloadedFile() {
        File directory = new File(Paths.get(DIR_PATH).toFile().getAbsolutePath());
        File[] files = directory.listFiles();
        return files[0].getName();
    }
}
