package dev.ezandro.poi.controller;

import dev.ezandro.poi.controller.dto.CreatePointOfInterest;
import dev.ezandro.poi.entity.PointOfInterest;
import dev.ezandro.poi.service.PointOfInterestService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PointOfInterestController {
    private final PointOfInterestService pointOfInterestService;

    public PointOfInterestController(PointOfInterestService pointOfInterestService) {
        this.pointOfInterestService = pointOfInterestService;
    }

    @PostMapping(value = "/points-of-interests")
    public ResponseEntity<Void> createPoi(@RequestBody CreatePointOfInterest createPointOfInterest) {
        this.pointOfInterestService.save(new PointOfInterest(createPointOfInterest.name(), createPointOfInterest.x(), createPointOfInterest.y()));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/points-of-interests")
    public ResponseEntity<Page<PointOfInterest>> listPoi(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(this.pointOfInterestService.findAll(page, pageSize));
    }

    @GetMapping(value = "/near-me")
    public ResponseEntity<List<PointOfInterest>> nearMe(@RequestParam(value = "x") Long x,
                                                        @RequestParam(value = "y") Long y,
                                                        @RequestParam(value = "distanceMax") Long distanceMax) {
        return ResponseEntity.ok(this.pointOfInterestService.findNearMe(x, y, distanceMax));
    }
}