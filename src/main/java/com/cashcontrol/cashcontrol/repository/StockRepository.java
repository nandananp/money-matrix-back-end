package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

}
