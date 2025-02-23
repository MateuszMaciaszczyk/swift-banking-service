package org.example.swiftbankingservice.repositories;

import org.example.swiftbankingservice.models.SwiftCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SwiftCodeRepository extends JpaRepository<SwiftCode, String> {
    List<SwiftCode> findByCountryISO2(String countryISO2);
    Optional<SwiftCode> findBySwiftCode(String swiftCode);
    List<SwiftCode> findBySwiftCodeStartingWith(String swiftPrefix);
}
