package stepdefs;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TestUtils {

    public static List<String> getDataOnRegx(String str) throws IOException {
        Pattern regex = Pattern.compile("[A-Z][A-Z]\\s?([0][2-9]|[1-9][0-9])\\s?[A-Z]{3}");
        Matcher regexMatcher = regex.matcher(str);
        List<String> dataList = new ArrayList<String>();
        while (regexMatcher.find()) {
            if (regexMatcher.group(0) != null) {
                dataList.add(regexMatcher.group(0));
            }
        }
        System.out.println(dataList);
        return dataList;

    }

    //Read file content into string with - Files.lines(Path path, Charset cs)
    public static String readInputFileAsString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }


    public static Map<String, CsvFileReading> getOutPutDataFromCSV(String outputFile) throws FileNotFoundException {

        Map<String, CsvFileReading> map = new HashMap<>();

        List<CsvFileReading> treeParser = new CsvToBeanBuilder(new FileReader(outputFile))
                .withType(CsvFileReading.class).build().parse();

        for (CsvFileReading record : treeParser) {

            CsvFileReading csvData = new CsvFileReading();
            csvData.setVARIANT_REG(record.getVARIANT_REG());
            csvData.setMAKE(record.getMAKE());
            csvData.setMODEL(record.getMODEL());
            csvData.setYEAR(record.getYEAR());

            map.put(csvData.getVARIANT_REG(), csvData);
        }
        return map;
    }

    public static String randomMileageGenerator() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(1, 99999));
    }

}
