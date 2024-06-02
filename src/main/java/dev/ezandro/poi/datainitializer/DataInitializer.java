package dev.ezandro.poi.datainitializer;

import dev.ezandro.poi.entity.PointOfInterest;
import dev.ezandro.poi.repository.PointOfInterestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final PointOfInterestRepository pointOfInterestRepository;

    public DataInitializer(PointOfInterestRepository pointOfInterestRepository) {
        this.pointOfInterestRepository = pointOfInterestRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        var poi1 = new PointOfInterest("Snack Bar", 27L, 12L);
        var poi2 = new PointOfInterest("Gas Station", 31L, 18L);
        var poi3 = new PointOfInterest("Jewelry Store", 15L, 12L);
        var poi4 = new PointOfInterest("Flower Shop", 19L, 21L);
        var poi5 = new PointOfInterest("Pub", 12L, 8L);
        var poi6 = new PointOfInterest("Supermarket", 23L, 6L);
        var poi7 = new PointOfInterest("Steakhouse", 28L, 2L);

        this.pointOfInterestRepository.saveAll(List.of(poi1, poi2, poi3, poi4, poi5, poi6, poi7));
    }
}