package com.example.CloudKitchenBackend.Controller;

import com.example.CloudKitchenBackend.Request.RestaurantRequest;
import com.example.CloudKitchenBackend.Service.RestaurantService;
import com.example.CloudKitchenBackend.Util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findALL(
            @RequestParam(required = false) String restaurantName,
            @RequestParam(defaultValue = "0.0") double rating,
            @RequestParam(required = false) String latitude,
            @RequestParam(required = false) String longitude,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return RestResponse.ok(service.findAll(restaurantName,rating,latitude,longitude,page,size),"Data Retrieval Successful");
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(
            @Valid @RequestBody RestaurantRequest request
    ){
        return RestResponse.ok(service.save(request),"Restaurant Saved Successful");
    }
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@PathVariable String id){
        return RestResponse.ok(service.delete(id),"Restaurant deleted Successful");
    }
    @PostMapping(value ="/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody RestaurantRequest request){
        return RestResponse.ok(service.update(request, id),"Restaurant updated Successful");
    }
}
