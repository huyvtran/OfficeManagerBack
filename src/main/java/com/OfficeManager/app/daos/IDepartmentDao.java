package com.OfficeManager.app.daos;

import com.OfficeManager.app.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDepartmentDao extends JpaRepository<Department, Integer> {

    @Query(
            value = "SELECT * FROM department d WHERE d.name = :name",
            nativeQuery = true)
    Department findByName(String name);

    @Query(
            value = "SELECT (count(d) > 0) FROM Department d WHERE d.name = :name"
    )
    boolean existsByName(String name);

}
