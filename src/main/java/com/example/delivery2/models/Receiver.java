package com.example.delivery2.models;

import com.example.delivery2.Enums.Position;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Enumerated(EnumType.STRING)
    Position position;
    String location;
    String ownerName;

    @ManyToOne
    Client client;
}
