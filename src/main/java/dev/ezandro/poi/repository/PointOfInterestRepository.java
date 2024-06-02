package dev.ezandro.poi.repository;

import dev.ezandro.poi.entity.PointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {
    @Query(value = """
            SELECT p FROM PointOfInterest p WHERE (p.x >= :xMin AND p.x <= :xMax AND p.y >= :yMin AND p.y <= :yMax)
            """)
    List<PointOfInterest> findNearMe(@Param(value = "xMin") long xMin,
                                     @Param(value = "xMax") long xMax,
                                     @Param(value = "yMin") long yMin,
                                     @Param(value = "yMax") long yMax);
}