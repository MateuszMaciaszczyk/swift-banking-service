package org.example.swiftbankingservice.controllers;

import org.example.swiftbankingservice.models.SwiftCodeDTO;
import org.example.swiftbankingservice.models.SwiftCodesByCountryDTO;
import org.example.swiftbankingservice.services.SwiftCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/swift-codes")
public class SwiftCodeController {
    private final SwiftCodeService service;

    public SwiftCodeController(SwiftCodeService service) {
        this.service = service;
    }

    @GetMapping("/{swiftCode}")
    public ResponseEntity<?> getSwiftCode(@PathVariable String swiftCode) {
        Optional<SwiftCodeDTO> swift = service.getSwiftCode(swiftCode);

        return swift.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/country/{countryISO2}")
    public ResponseEntity<?>  getSwiftCodesByCountry(@PathVariable String countryISO2) {
        SwiftCodesByCountryDTO response = service.getSwiftCodesByCountry(countryISO2);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> addSwiftCode(@RequestBody SwiftCodeDTO swiftCodeDTO) {
        if (swiftCodeDTO.getSwiftCode() == null || swiftCodeDTO.getSwiftCode().isEmpty()) {
            return ResponseEntity.badRequest().body("{\"message\": \"SWIFT code cannot be empty\"}");
        }

        String message = service.addSwiftCode(swiftCodeDTO);

        if (message.startsWith("Error")) {
            return ResponseEntity.badRequest().body("{\"message\": \"" + message + "\"}");
        }

        return ResponseEntity.ok("{\"message\": \"" + message + "\"}");
    }

    @DeleteMapping("/{swiftCode}")
    public ResponseEntity<?> deleteSwiftCode(@PathVariable String swiftCode) {
        String message = service.deleteSwiftCode(swiftCode);

        if (message.startsWith("Error")) {
            return ResponseEntity.badRequest().body("{\"message\": \"" + message + "\"}");
        }

        return ResponseEntity.ok("{\"message\": \"" + message + "\"}");
    }
}