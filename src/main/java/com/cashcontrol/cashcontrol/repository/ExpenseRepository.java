package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.admin.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
}
