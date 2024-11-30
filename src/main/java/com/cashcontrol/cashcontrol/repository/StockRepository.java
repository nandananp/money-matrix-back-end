package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.admin.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

}
