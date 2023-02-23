package com.info5059.casestudy.purchaseorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.info5059.casestudy.product.Product;
import com.info5059.casestudy.product.ProductRepository;
import javax.persistence.EntityManager;
//import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Component
public class PurchaseOrderDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductRepository prodRepo;

    @Transactional
    public PurchaseOrder create(PurchaseOrder clientpo) {
        PurchaseOrder realPo = new PurchaseOrder();
        realPo.setPodate(LocalDateTime.now());
        realPo.setVendorid(clientpo.getVendorid());
        realPo.setAmount(clientpo.getAmount());
        entityManager.persist(realPo);
        for (PurchaseOrderLineitem item : clientpo.getItems()) {
            PurchaseOrderLineitem realItem = new PurchaseOrderLineitem();
            realItem.setPoid(realPo.getId());
            realItem.setProductid(item.getProductid());
            realItem.setQty(item.getQty());
            realItem.setPrice(item.getPrice());
            // we also need to update the QOO on the product table
            Product prod = prodRepo.getReferenceById(item.getProductid());
            prod.setQoo(prod.getQoo() + item.getQty());
            prodRepo.saveAndFlush(prod);
            entityManager.persist(realItem);
        }
        entityManager.refresh(realPo);
        return realPo;
    }
}