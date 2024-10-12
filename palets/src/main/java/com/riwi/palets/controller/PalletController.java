package com.riwi.palets.controller;

import com.riwi.palets.model.entity.Pallet;
import com.riwi.palets.model.enums.PalletStatus;
import com.riwi.palets.service.PalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/pallets")
public class PalletController {

    @Autowired
    PalletService palletService;

    @GetMapping
    @Operation(summary = "get all Pallets", description = "Endpoint to get all Pallets")
    @ApiResponse(responseCode = "200", description = "successfully gotten all pallets")
    @ApiResponse(responseCode = "401", description = "Not authorized to see pallets")
    public List<Pallet> getAllPallets(){
        return palletService.getAllPallets();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint to get a Pallet by ID")
    public Pallet getPalletById(@PathVariable Long id) {
        return palletService.getPalletById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new Pallet")
    @ApiResponse(responseCode = "404", description = "Location Not found")
    public ResponseEntity<?> createPallet(
            @Parameter(name = "Pallet Number") Long PalletNumber,
            @Parameter(name = "Maximum Weight") Long MaximumWeight,
            @Parameter(name = "Status") PalletStatus Status,
            @Parameter(name = "Location Id") Long LocationId,
            @Parameter(hidden = true) @RequestHeader(name = "Authorization") String token) {
        try {
            token = token.substring(7);
            Pallet pallet = new Pallet();
            pallet.setPalletNumber(PalletNumber);
            pallet.setMaximumWeight(MaximumWeight);
            pallet.setStatus(Status);
            Pallet newPallet = palletService.createPallet(pallet, LocationId, token);
            return new ResponseEntity<>(newPallet, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Location not found", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping
    @Operation(summary = "Update a Pallet", description = "Updates an existing pallet")
    @ApiResponse(responseCode = "404", description = "Location Not found")
    public ResponseEntity<?> updatePallet(
            @Parameter(name = "New Pallet Number") Long NewPalletNumber,
            @Parameter(name = "New Maximum Weight") Long NewMaximumWeight,
            @Parameter(name = "New Status") PalletStatus NewStatus,
            @Parameter(name = "New Location Id") Long NewLocationId,
            @Parameter(hidden = true) @RequestHeader(name = "Authorization") String token) {
        try {
            token = token.substring(7);
            Pallet pallet = new Pallet();
            pallet.setPalletNumber(NewPalletNumber);
            pallet.setMaximumWeight(NewMaximumWeight);
            pallet.setStatus(NewStatus);
            Pallet newPallet = palletService.updatePallet(pallet, NewLocationId, token);
            return new ResponseEntity<>(newPallet, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Location not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        palletService.deletePallet(id);
    }
}
