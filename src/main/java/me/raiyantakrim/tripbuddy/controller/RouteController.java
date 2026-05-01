package me.raiyantakrim.tripbuddy.controller;

import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.entity.Route;
import me.raiyantakrim.tripbuddy.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoute() {
        return ResponseEntity.ok().body(routeService.getAllRoute());
    }

    @PostMapping
    public ResponseEntity<Route> addRoute(@RequestBody Route route) {
        return ResponseEntity.ok().body(routeService.saveRoute(route));
    }
}
