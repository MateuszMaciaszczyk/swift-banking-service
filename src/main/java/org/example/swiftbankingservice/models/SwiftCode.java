package org.example.swiftbankingservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "swift_codes")
public class SwiftCode {
    @Id
    @Column(unique = true, nullable = false)
    private String swiftCode;
    private String bankName;
    private String address;
    private String codeType;
    private String townName;
    private String timeZone;
    private String countryISO2;
    private String countryName;
    private boolean isHeadquarter;

    public SwiftCode(String swiftCode, String bankName, String address, String countryISO2, String countryName, boolean isHeadquarter) {
        this.swiftCode = swiftCode;
        this.bankName = bankName;
        this.address = address;
        this.countryISO2 = countryISO2;
        this.countryName = countryName;
        this.isHeadquarter = isHeadquarter;
    }
}
