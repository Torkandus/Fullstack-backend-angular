package com.info5059.casestudy.purchaseorder;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
// @CrossOrigin
@RepositoryRestResource(collectionResourceRel = "vendors", path = "vendors")
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {
    // extend so we can return the number of rows deleted
    @Modifying
    @Transactional
    @Query("delete from PurchaseOrder where id = ?1")
    int deleteOne(Long poid);

    List<PurchaseOrder> findByVendorid(Long vendorid);
}
