package me.raiyantakrim.tripbuddy.service;

import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.DTO.CreateRouteDTO;
import me.raiyantakrim.tripbuddy.DTO.CreateRouteResDTO;
import me.raiyantakrim.tripbuddy.entity.Route;
import me.raiyantakrim.tripbuddy.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;

    public List<CreateRouteResDTO> getAllRoute(){

        List<Route> allRoutes = routeRepository.findAll();
        return allRoutes.stream().map(route ->
                new CreateRouteResDTO(
                   route.getId(),
                   route.getOriginCity(),
                   route.getDestinationCity(),
                   route.getDistance()
                )).toList();
    }

    public Route saveRoute(CreateRouteDTO routeDetails) {
        Route route = new Route();
        route.setOriginCity(routeDetails.origin());
        route.setDestinationCity(routeDetails.destination());
        route.setDistance(routeDetails.distance());
        return routeRepository.save(route);
    }
}
