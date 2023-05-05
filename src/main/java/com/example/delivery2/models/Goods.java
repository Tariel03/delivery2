package com.example.delivery2.models;

import javax.persistence.*;

import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    Long id;
    @ManyToOne
    @JoinColumn(name = "distributor_id")
    Distributor distributor;
    String type;
    String name;
    @Column(columnDefinition = "text")
    String description;
    int price;


    public Goods(String type, String description, int price) {
        this.type = type;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
