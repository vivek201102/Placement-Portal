package org.vivek.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vivek.placementportal.models.PlacementDrive;

import java.util.Date;
import java.util.List;

@Repository
public interface PlacementDriveRepository extends JpaRepository<PlacementDrive, Integer> {
    public List<PlacementDrive> findAllByStatusAndDeadlineForApplicationIsGreaterThan(String status, Date today);
    public List<PlacementDrive> findAllByStatusAndDeadlineForApplicationLessThan(String status, Date today);
    public List<PlacementDrive> findAllByStatus(String status);
    public List<PlacementDrive> findByDeadlineForApplicationGreaterThan(Date today);
}
