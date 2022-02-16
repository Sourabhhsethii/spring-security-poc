package com.example.securitydemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class Student {
    private Integer studentId;
    private String studentName;
}
