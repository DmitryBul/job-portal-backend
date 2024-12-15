package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.example.demo.models.Data;
import com.example.demo.repositories.DataRepository;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DataService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private DataRepository dataRepository;

    public List<Data> getFilteredData(
            String companyName, String location, String technology, String seniority,
            Double minAvgSalary, Double maxAvgSalary, Integer year, Integer month
    ) {
        Query query = new Query();

        if (companyName != null) {
            query.addCriteria(Criteria.where("companyName").is(companyName));
        }
        if (location != null) {
            query.addCriteria(Criteria.where("location").in(location.split(",")));
        }
        if (technology != null) {
            query.addCriteria(Criteria.where("technology").in(technology.split(",")));
        }
        if (seniority != null) {
            query.addCriteria(Criteria.where("seniority").in(seniority.split(",")));
        }
        if (minAvgSalary != null) {
            query.addCriteria(Criteria.where("avgSalary").gte(minAvgSalary));
        }
        if (maxAvgSalary != null) {
            query.addCriteria(Criteria.where("avgSalary").lte(maxAvgSalary));
        }
        if (year != null) {
            query.addCriteria(Criteria.where("year").is(year));
        }
        if (month != null) {
            query.addCriteria(Criteria.where("month").is(month));
        }

        return mongoTemplate.find(query, Data.class);
    }

    public Map<String, List<String>> getDistinctFilters() {
        List<String> locations = mongoTemplate.getCollection("data").distinct("location", String.class).into(new ArrayList<>());
        List<String> technologies = mongoTemplate.getCollection("data").distinct("technology", String.class).into(new ArrayList<>());
        List<String> seniorities = mongoTemplate.getCollection("data").distinct("seniority", String.class).into(new ArrayList<>());
        List<Integer> years = mongoTemplate.getCollection("data").distinct("year", Integer.class).into(new ArrayList<>());
        List<Integer> months = mongoTemplate.getCollection("data").distinct("month", Integer.class).into(new ArrayList<>());

        return Map.of(
                "location", locations,
                "technology", technologies,
                "seniority", seniorities,
                "year", years.stream().map(String::valueOf).toList(),
                "month", months.stream().map(String::valueOf).toList()
        );
    }

    public Data createData(Data data) {
        return dataRepository.save(data); 
    }

    public Optional<Data> getDataById(String id) {
        return dataRepository.findById(id);
    }

    public Data updateData(String id, Data newData) {
        return dataRepository.findById(id)
                .map(existingData -> {
                    existingData.setCompanyName(newData.getCompanyName());
                    existingData.setLocation(newData.getLocation());
                    existingData.setTechnology(newData.getTechnology());
                    existingData.setSeniority(newData.getSeniority());
                    existingData.setAvgSalary(newData.getAvgSalary());
                    return dataRepository.save(existingData);
                })
                .orElseThrow(() -> new RuntimeException("Nie znaleziono oferty: " + id));
    }

    public void deleteData(String id) {
        dataRepository.deleteById(id);
    }   

    public void deleteAllData() {
        dataRepository.deleteAll();
    }

}
