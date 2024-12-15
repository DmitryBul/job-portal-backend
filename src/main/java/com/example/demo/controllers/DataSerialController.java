package com.example.demo.controllers;

import com.example.demo.models.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataSerialController {

    private final List<Data> dataList = new ArrayList<>();

    public DataSerialController() {
        Data data = new Data();
        data.setCompanyName("Link Group");
        data.setLocation("Warszawa");
        data.setTechnology("Java");
        data.setSeniority("mid");
        data.setAvgSalary(5000.0);
        dataList.add(data);
    }

    @GetMapping(value = "/export/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Data> exportToJson() {
        return dataList;
    }

    @GetMapping(value = "/export/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Data> exportToXml() {
        return dataList;
    }

    @PostMapping(value = "/import/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String importFromJson(@RequestBody List<Data> data) {
        dataList.addAll(data);
        return "Import " + data.size() + " rekordow";
    }

    @PostMapping(value = "/import/xml", consumes = MediaType.APPLICATION_XML_VALUE)
    public String importFromXml(@RequestBody List<Data> data) {
        dataList.addAll(data);
        return "Import " + data.size() + " rekordow";
    }
}
