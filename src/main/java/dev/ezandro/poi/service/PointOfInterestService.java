package dev.ezandro.poi.service;

import dev.ezandro.poi.entity.PointOfInterest;
import dev.ezandro.poi.repository.PointOfInterestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointOfInterestService {
    private final PointOfInterestRepository pointOfInterestRepository;

    public PointOfInterestService(PointOfInterestRepository pointOfInterestRepository) {
        this.pointOfInterestRepository = pointOfInterestRepository;
    }

    public void save(PointOfInterest pointOfInterest) {
        this.pointOfInterestRepository.save(pointOfInterest);
    }

    public Page<PointOfInterest> findAll(Integer page, Integer pageSize) {
        return this.pointOfInterestRepository.findAll(PageRequest.of(page, pageSize));
    }

    public List<PointOfInterest> findNearMe(long x, long y, long distanceMax) {
        var xMin = x - distanceMax;
        var xMax = x + distanceMax;
        var yMin = y - distanceMax;
        var yMax = y + distanceMax;

        return this.pointOfInterestRepository.findNearMe(xMin, xMax, yMin, yMax)
                .stream()
                .filter(p -> this.distanceBetweenPoints(x, y, p.getX(), p.getY()) <= distanceMax)
                .toList();
    }

    private Double distanceBetweenPoints(Long x1, Long y1, Long x2, Long y2) {
        return Math.sqrt(Math.pow((double) x2 - x1, 2) + Math.pow((double) y2 - y1, 2));
    }
}