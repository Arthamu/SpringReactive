package com.artha.reactivepoc.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artha.reactivepoc.entity.Resturants;
import com.artha.reactivepoc.repo.MongoRepo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "*")
public class ReactiveController {
	@Autowired
	MongoRepo repo;
	
	long DELAY_PER_ITEM_MS=50;
	/*
	 * @GetMapping("/getAll") Flux<Resturants> getAll(){
	 * 
	 * return repo.findAll().delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS)); }
	 */
	
	@GetMapping("/getAllPaged")
    public Flux<Resturants> getQuoteFlux(final @RequestParam(name = "page") int page,
                                    final @RequestParam(name = "size") int size) {
        return repo.retrieveAllResturantsPaged(PageRequest.of(page, size))
                .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
    }
	
	@GetMapping("/getAll")
	String getAll() throws JsonMappingException, JsonProcessingException{
		
		String json="[{\"url\":\"https://picsum.photos/600\",\"id\":\"dkkdkdkd\",\"address\":\"228 City Road\",\"address line 2\":\"Cardiff\",\"name\":\".CN Chinese\",\"outcode\":\"CF24\",\"postcode\":\"3JH\",\"rating\":5,\"type_of_food\":\"Chinese\"}]";
			

		return json;
	}
}
