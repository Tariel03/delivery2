package com.example.delivery2.repositories;

import com.example.delivery2.Enums.RequestStatus;
import com.example.delivery2.models.Client;
import com.example.delivery2.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByClient(Client client);
    List<Request>findByRequestStatus(RequestStatus requestStatus);


}
