package org.example.swiftbankingservice.controllers;

import org.example.swiftbankingservice.models.SwiftCode;
import org.example.swiftbankingservice.models.SwiftCodeDTO;
import org.example.swiftbankingservice.services.SwiftCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<SwiftCodeDTO> getSwiftCodesByCountry(@PathVariable String countryISO2) {
        return service.getSwiftCodesByCountry(countryISO2);
    }

    @PostMapping
    public ResponseEntity<SwiftCode> addSwiftCode(@RequestBody SwiftCode swiftCode) {
        return ResponseEntity.ok(service.addSwiftCode(swiftCode));
    }

    @DeleteMapping("/{swiftCode}")
    public ResponseEntity<String> deleteSwiftCode(@PathVariable String swiftCode) {
        service.deleteSwiftCode(swiftCode);
        return ResponseEntity.ok("SWIFT code deleted");
    }
}