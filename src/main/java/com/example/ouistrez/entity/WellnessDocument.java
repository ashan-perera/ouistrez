package com.example.ouistrez.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "WELLNESS_DOCUMENT")
public class WellnessDocument extends BaseEntity {

    @Column(name = "POLICY_NO")
    private String policyNo;

    @Column(name = "PHONE_NO")
    private String phoneNo;

    @Column(name = "FILE_NAME", nullable = false, length = 50)
    private String fileName;

    @Column(name = "FILE_PATH", nullable = false, length = 500)
    private String filePath;

    @Column(name = "RELATIVE_PATH", nullable = false, length = 500)
    private String relativePath;

}
