package org.example.swiftbankingservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "swift_codes")
public class SwiftCode {
    @Id
    @Column(unique = true, nullable = false)
    private String swiftCode;

    @Column(nullable = false)
    private String bankName;

    private String address;

    @Column(nullable = false)
    private String codeType;

    @Column(nullable = false)
    private String townName;

    @Column(nullable = false)
    private String timeZone;

    @Column(nullable = false)
    private String countryISO2;

    @Column(nullable = false)
    private String countryName;

    @Column(nullable = false)
    private boolean isHeadquarter;

}
