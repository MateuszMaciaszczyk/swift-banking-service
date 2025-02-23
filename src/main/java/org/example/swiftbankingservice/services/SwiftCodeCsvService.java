package org.example.swiftbankingservice.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.swiftbankingservice.models.SwiftCode;
import org.example.swiftbankingservice.repositories.SwiftCodeRepository;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SwiftCodeCsvService {
    private final SwiftCodeRepository repository;

    public SwiftCodeCsvService(SwiftCodeRepository repository) {
        this.repository = repository;
    }

    public void loadSwiftCodesFromCsv(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<SwiftCode> swiftCodes = new ArrayList<>();
            String[] line;
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                SwiftCode swiftCode = new SwiftCode();
                swiftCode.setCountryISO2(line[0]);
                swiftCode.setSwiftCode(line[1]);
                swiftCode.setCodeType(line[2]);
                swiftCode.setBankName(line[3]);
                swiftCode.setAddress(line[4]);
                swiftCode.setTownName(line[5]);
                swiftCode.setCountryName(line[6]);
                swiftCode.setTimeZone(line[7]);
                swiftCode.setHeadquarter(line[1].endsWith("XXX"));

                swiftCodes.add(swiftCode);
            }

            repository.saveAll(swiftCodes);
            System.out.println("Dane SWIFT załadowane do bazy!");
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}