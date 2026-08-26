package com.projectirp.institutemanagementsystem.DTOs;

import java.util.List;

public record TeacherResponseDTO(List<String> teachers) {
}

/*
... is equivalent to writing this entire class manually:

public final class TeacherResponse {
    private final List<String> teachers;

    public TeacherResponse(List<String> teachers) {
        this.teachers = teachers;
    }

    public List<String> teachers() {
        return teachers;
    }

    @Override
    public boolean equals(Object o) { }

@Override
public int hashCode() { }

@Override
public String toString() {  }
}
 */