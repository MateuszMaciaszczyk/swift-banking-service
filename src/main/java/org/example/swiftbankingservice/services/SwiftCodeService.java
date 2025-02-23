package org.example.swiftbankingservice.services;

import org.example.swiftbankingservice.models.SwiftCode;
import org.example.swiftbankingservice.models.SwiftCodeDTO;
import org.example.swiftbankingservice.models.SwiftCodesByCountryDTO;
import org.example.swiftbankingservice.repositories.SwiftCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SwiftCodeService {
    private final SwiftCodeRepository repository;

    public SwiftCodeService(SwiftCodeRepository repository) {
        this.repository = repository;
    }

    public Optional<SwiftCodeDTO> getSwiftCode(String swiftCode) {
        Optional<SwiftCode> optionalSwiftCode = repository.findBySwiftCode(swiftCode);

        if (optionalSwiftCode.isEmpty()) {
            return Optional.empty();
        }

        SwiftCode swiftCodeEntity = optionalSwiftCode.get();
        List<SwiftCodeDTO> branches = null;

        if (swiftCodeEntity.isHeadquarter()) {
            String swiftPrefix = swiftCodeEntity.getSwiftCode().substring(0, 8);
            branches = repository.findBySwiftCodeStartingWith(swiftPrefix)
                    .stream()
                    .filter(branch -> !branch.getSwiftCode().equals(swiftCode))
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }

        return Optional.of(convertToDTO(swiftCodeEntity, branches));
    }

    public SwiftCodesByCountryDTO getSwiftCodesByCountry(String countryISO2) {
        List<SwiftCode> swiftCodes = repository.findByCountryISO2(countryISO2);

        String countryName = swiftCodes.getFirst().getCountryName();

        List<SwiftCodeDTO> swiftCodesDTO = swiftCodes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new SwiftCodesByCountryDTO(countryISO2, countryName, swiftCodesDTO);
    }

    public SwiftCode addSwiftCode(SwiftCode swiftCode) {
        return repository.save(swiftCode);
    }

    public void deleteSwiftCode(String swiftCode) {
        repository.deleteById(swiftCode);
    }

    private SwiftCodeDTO convertToDTO(SwiftCode swiftCode) {
        return new SwiftCodeDTO(
                swiftCode.getAddress(),
                swiftCode.getBankName(),
                swiftCode.getCountryISO2(),
                swiftCode.isHeadquarter(),
                swiftCode.getSwiftCode()
        );
    }

    private SwiftCodeDTO convertToDTO(SwiftCode swiftCode, List<SwiftCodeDTO> branches) {
        return new SwiftCodeDTO(
                swiftCode.getAddress(),
                swiftCode.getBankName(),
                swiftCode.getCountryISO2(),
                swiftCode.getCountryName(),
                swiftCode.isHeadquarter(),
                swiftCode.getSwiftCode(),
                branches
        );
    }
}