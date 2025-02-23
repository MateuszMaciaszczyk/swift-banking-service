package org.example.swiftbankingservice.models;

import java.util.List;

public record SwiftCodesByCountryDTO(String countryISO2, String countryName, List<SwiftCodeDTO> swiftCodes) {
}
