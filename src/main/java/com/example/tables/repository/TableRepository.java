package com.example.tables.repository;

import com.example.tables.entity.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.*;


@Repository
public interface TableRepository extends JpaRepository<Table, Long>, JpaSpecificationExecutor<Table> {

//    @Query("SELECT t FROM Table t WHERE CONCAT(t.brand, t.color, t.material, t.size) LIKE %?1%")
//    Page<Table> findForPage(String keyword, Pageable pageable);

}
