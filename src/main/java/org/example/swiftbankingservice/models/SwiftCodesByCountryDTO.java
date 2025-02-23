package org.example.swiftbankingservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public record SwiftCodesByCountryDTO(String countryISO2, String countryName, List<SwiftCodeDTO> swiftCodes) {
}
