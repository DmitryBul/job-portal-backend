package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.demo.models.Data;
import com.example.demo.services.DataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping
    public ResponseEntity<List<Data>> getData(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String technology,
            @RequestParam(required = false) String seniority,
            @RequestParam(required = false) Double minAvgSalary,
            @RequestParam(required = false) Double maxAvgSalary,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month
    ) {
        List<Data> data = dataService.getFilteredData(
                companyName, location, technology, seniority, minAvgSalary, maxAvgSalary, year, month
        );
        return ResponseEntity.ok(data);
    }

    @GetMapping("/filters")
    public ResponseEntity<Map<String, List<String>>> getDistinctFilters() {
        Map<String, List<String>> filters = dataService.getDistinctFilters();
        return ResponseEntity.ok(filters);
    }

    @PostMapping
    public ResponseEntity<Data> createData(@RequestBody Data data) {
        try {
            Data createdData = dataService.createData(data);
            return new ResponseEntity<>(createdData, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Data> getDataById(@PathVariable String id) {
        Data data = dataService.getDataById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono oferty: " + id));
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Data> updateData(@PathVariable String id, @RequestBody Data newData) {
        try {
            Data updatedData = dataService.updateData(id, newData);
            return ResponseEntity.ok(updatedData);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteData(@PathVariable String id) {
        try {
            dataService.deleteData(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllData() {
        try {
            dataService.deleteAllData();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
