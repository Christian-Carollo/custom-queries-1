package co.develhope.customqueries1.controllers;

import co.develhope.customqueries1.entities.Flight;
import co.develhope.customqueries1.entities.FlightStatus;
import co.develhope.customqueries1.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;
    private Random random = new Random();



    private String randomString(){
        return String.valueOf(random.ints(48, 123)
                .filter(value -> (value <= 57 || value >= 65) && (value <= 90 || value >= 97)).limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString()
        );
    }


    @GetMapping("/provision")
    public List<Flight> getFlightProvision() {
        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Flight flight = new Flight();
            flight.setDescription(randomString());
            flight.setFromAirport(randomString());
            flight.setToAirport(randomString());
            flight.setFlightStatus(FlightStatus.ONTIME);
            flights.add(flight);
        }
        flightRepository.saveAll(flights);
        return flights;
    }


    @GetMapping("/getAllFlights")
    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }


}
