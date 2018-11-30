package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rate")
public class Rate implements Serializable {

    @Id
    private Long id;

    @Min(1)
    @Max(5)
    @Column
    private Integer rate;

    @OneToOne
    @JoinColumn(unique = true)
    private Movie movie;

    @OneToOne
    @JoinColumn(unique = true)
    private UserEntity author;

}
