package me.raiyantakrim.tripbuddy.controller;

import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.entity.Route;
import me.raiyantakrim.tripbuddy.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;
    @GetMapping
    public ResponseEntity<List<Route>> getAllRoute() {
        return ResponseEntity.ok().body(routeService.getAllRoute());
    }
}
