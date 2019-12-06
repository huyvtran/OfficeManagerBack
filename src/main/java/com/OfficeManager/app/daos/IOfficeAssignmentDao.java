package com.OfficeManager.app.daos;

import com.OfficeManager.app.entities.OfficeAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOfficeAssignmentDao extends JpaRepository<OfficeAssignment, Integer> {

}