package org.example.swiftbankingservice;

import org.example.swiftbankingservice.services.SwiftCodeCsvService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SwiftBankingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwiftBankingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(SwiftCodeCsvService swiftService) {
        return args -> swiftService.loadSwiftCodesFromCsv("src/main/resources/swift-codes.csv");
    }
}