package com.OfficeManager.app.controllers;

import com.OfficeManager.app.dtos.DepartmentDto;
import com.OfficeManager.app.dtos.TeamDto;
import com.OfficeManager.app.dtos.UpdateDepartmentDto;
import com.OfficeManager.app.entities.Department;
import com.OfficeManager.app.services.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/department", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
public class DepartmentRestController {

    @Autowired
    DepartmentServiceImpl departmentService;

    @GetMapping("")
    ResponseEntity<List<DepartmentDto>> getDepartments(){
        ArrayList<DepartmentDto> list = new ArrayList<DepartmentDto>();
        for (Department d: departmentService.fetchAll()) {
            list.add(mapDepartmentToDepartmentDto(d));
        }
        return new ResponseEntity<List<DepartmentDto>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Integer id){
        if (departmentService.findById(id).isPresent()){
            Department department = departmentService.findById(id).get();
            DepartmentDto departmentDto = mapDepartmentToDepartmentDto(department);
            return new ResponseEntity<>(departmentDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("")
    ResponseEntity<DepartmentDto> addDepartment(@RequestBody UpdateDepartmentDto updateDepartmentDto){
        Department department = new Department(updateDepartmentDto.getName());
        departmentService.saveDepartment(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Integer id, @RequestBody UpdateDepartmentDto updateDepartmentDto){
        if (departmentService.findById(id).isPresent()){
            Department department = departmentService.findById(id).get();
            department.setName(updateDepartmentDto.getName());
            departmentService.saveDepartment(department);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable int id){
        if (departmentService.findById(id).isPresent()){
            departmentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public DepartmentDto mapDepartmentToDepartmentDto(Department department){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        List<TeamDto> teamsDto = new ArrayList<TeamDto>();
        department.getTeams().forEach(team -> {
            teamsDto.add(new TeamDto(team));
        });
        departmentDto.setTeams(teamsDto);
        return departmentDto;
    }

}
