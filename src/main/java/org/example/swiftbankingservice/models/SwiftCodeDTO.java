package org.example.swiftbankingservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "address", "bankName", "countryISO2", "countryName", "isHeadquarter", "swiftCode", "branches" })
public class SwiftCodeDTO {
    private String address;
    private String bankName;
    private String countryISO2;
    private String countryName;
    @JsonProperty("isHeadquarter")
    private boolean isHeadquarter;
    private String swiftCode;
    private List<SwiftCodeDTO> branches;

    public SwiftCodeDTO(String address, String bankName, String countryISO2, String countryName, boolean isHeadquarter, String swiftCode, List<SwiftCodeDTO> branches) {
        this.address = address;
        this.bankName = bankName;
        this.countryISO2 = countryISO2;
        this.countryName = countryName;
        this.isHeadquarter = isHeadquarter;
        this.swiftCode = swiftCode;
        this.branches = branches;
    }

    public SwiftCodeDTO(String address, String bankName, String countryISO2, boolean isHeadquarter, String swiftCode) {
        this(address, bankName, countryISO2, null, isHeadquarter, swiftCode, null);
    }
}