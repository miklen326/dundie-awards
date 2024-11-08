package com.ninjaone.dundie_awards.repository;

import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(
            value = "select e from Employee e join fetch e.organization",
            countQuery = "select count(e) from Employee e")
    Page<Employee> findAllWithOrganization(Pageable pageable);

    @Query("select sum(e.dundieAwards) from Employee e")
    Integer calcTotalAwards();

    @Modifying
    @Query("update Employee e set e.firstName = :firstName, e.lastName = :lastName, e.organization = :organization where e.id = :employeeId")
    int updateEmployee(@Param("employeeId") long employeeId,
                       @Param("firstName") String firstName,
                       @Param("lastName") String lastName,
                       @Param("organization") Organization organization);

    @Modifying
    @Query("update Employee e set e.dundieAwards = coalesce(e.dundieAwards, 0) + :dundieAwards where e.id = :employeeId")
    int updateAwardsByEmployee(@Param("employeeId") long employeeId, @Param("dundieAwards") int dundieAwards);

    @Modifying
    @Query("update Employee e set e.dundieAwards = coalesce(e.dundieAwards, 0) + :dundieAwards where e.organization.id = :organizationId")
    int updateAwardsByOrganization(@Param("organizationId") long organizationId, @Param("dundieAwards") int dundieAwards);
}
