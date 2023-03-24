package com.example.ouistrez.repository;

import com.example.ouistrez.entity.GeneralPayment;
import com.example.ouistrez.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralPaymentRepository extends JpaRepository<GeneralPayment, String> {

    GeneralPayment findByOrderIdAndIsDeletedAndProvider(String orderId, Deleted isDeleted, String provider);

}
