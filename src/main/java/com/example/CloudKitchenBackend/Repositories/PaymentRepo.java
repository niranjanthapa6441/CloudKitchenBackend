package com.example.CloudKitchenBackend.Repositories;

import com.example.CloudKitchenBackend.Model.CustomerOrder;
import com.example.CloudKitchenBackend.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,String> {
    Optional<Payment> findByCustomerOrder(CustomerOrder customerOrder);
}
