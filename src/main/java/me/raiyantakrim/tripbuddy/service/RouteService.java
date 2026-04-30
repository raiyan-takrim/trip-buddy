package me.raiyantakrim.tripbuddy.service;

import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.entity.Route;
import me.raiyantakrim.tripbuddy.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;

    public List<Route> getAllRoute(){
        return routeRepository.findAll();
    }
}
