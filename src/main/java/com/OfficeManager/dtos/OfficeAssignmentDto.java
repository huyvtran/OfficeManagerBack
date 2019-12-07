package com.OfficeManager.dtos;

import com.OfficeManager.app.entities.OfficeAssignment;

import java.time.LocalDate;

public class OfficeAssignmentDto {

    private LocalDate startDate, endDate;
    private PersonDto person;

    public OfficeAssignmentDto(OfficeAssignment officeAssignment) {
        this.startDate = officeAssignment.getStartDate();
        this.endDate = officeAssignment.getEndDate();
        this.person = new PersonDto(officeAssignment.getPerson());
    }

    public long getStartDate() {
        return startDate.toEpochDay()*24*60*60;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate.toEpochDay()*24*60*60;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getId() {
        return person.getId();
    }

    public String getFirstName() {
        return person.getFirstName();
    }

    public String getLastName() {
        return person.getLastName();
    }


    public void setPerson(PersonDto person) {
        this.person = person;
    }
}