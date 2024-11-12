package stepdefs;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CsvFileReading {
    @CsvBindByName
    private String VARIANT_REG;

    @CsvBindByName
    private String MAKE;

    @CsvBindByName
    private String MODEL;

    @CsvBindByName
    private String YEAR;

    @Override
    public String toString() {
        return "CsvReading{" +
                "REGISTRATION='" + VARIANT_REG + '\'' +
                ", MAKE='" + MAKE + '\'' +
                ", MODEL='" + MODEL + '\'' +
                ", YEAR='" + YEAR + '\'' +
                '}';
    }
}
