package com.example.delivery2.repositories;

import com.example.delivery2.models.Ecard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcardRepository extends JpaRepository<Ecard, Long> {
}
