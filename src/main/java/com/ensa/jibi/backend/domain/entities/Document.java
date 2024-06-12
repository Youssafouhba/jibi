package com.ensa.jibi.backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String docUrl;
    private String description;

    @ManyToOne
    @JsonManagedReference
    private Agent agent;

    @ManyToOne
    @JsonManagedReference
    private Client client;
}
