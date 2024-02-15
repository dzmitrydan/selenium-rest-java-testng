package utility;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVFileReader {

    private static final String directoryPath = "target/download";

    public static double getPriceFromCsv() {
        try (CSVReader reader = new CSVReader(new FileReader(directoryPath + "/" + getNameLastDownloadedFile()))) {
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

    private static String getNameLastDownloadedFile() {
        File directory = new File(Paths.get(directoryPath).toFile().getAbsolutePath());
        File[] files = directory.listFiles();
        return files[files.length - 1].getName();
    }
}
