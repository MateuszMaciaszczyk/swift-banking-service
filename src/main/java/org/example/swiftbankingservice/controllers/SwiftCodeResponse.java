package org.example.swiftbankingservice.controllers;

import lombok.Getter;
import org.example.swiftbankingservice.models.SwiftCodeDTO;

import java.util.List;

@Getter
public class SwiftCodeResponse {
    private final SwiftCodeDTO headquarter;
    private final List<SwiftCodeDTO> branches;

    public SwiftCodeResponse(SwiftCodeDTO headquarter, List<SwiftCodeDTO> branches) {
        this.headquarter = headquarter;
        this.branches = branches;
    }
}