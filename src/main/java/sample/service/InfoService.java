package sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.entity.Book;
import sample.entity.Buyer;
import sample.entity.Purchase;
import sample.entity.Seller;
import sample.entity.info.*;


import javax.persistence.EntityManager;

import java.util.List;

@Service
public class InfoService {


    @Autowired
    EntityManager manager;


    public List<Book> allNameAndCost() {
        return manager.createNamedQuery("allNameAndCost").getResultList();
    }

    public List<Buyer> allDistinctAreas() {
        return manager.createNamedQuery("allDistinctAreas").getResultList();
    }

    public List<Purchase> allDistinctMonths() {
        return manager.createNamedQuery("allDistinctMonths").getResultList();
    }

    public List<Buyer> buyersFromNihny(){
        return manager.createNamedQuery("surnameAndDiscountIfAreaNizhny").getResultList();
    }

    public List<Seller> shopNameAndArea(){
        return manager.createNamedQuery("shopNameAndArea").getResultList();
    }

    public List<Book> nameAndCostWithFilterAndSort(){
        return manager.createNamedQuery("nameAndCostWithFilterAndSort").getResultList();
    }

    public List<SurnameAndShop> surnameAndShop(){
        return manager.createNamedQuery("surnameAndShop").getResultList();
    }

    public List<SurnameDiscountNameAmountDate> joinBuyerBookPurchase(){
        return manager.createNamedQuery("joinBuyerBookPurchase").getResultList();
    }

    public List<OrderMoreThan60K> orderMoreThan60K(){
        return manager.createNamedQuery("orderMoreThan60K").getResultList();
    }

    public List<BuyInYourselfAreaAfterThanMarch> buyInYourselfAreaAfterThanMarch(){
        return manager.createNamedQuery("buyInYourselfAreaAfterThanMarch").getResultList();
    }

    public List<NotAvtozavodShopWithBuyerFilter> notAvtozavodShopWithBuyerFilter(){
        return manager.createNamedQuery("notAvtozavodShopWithBuyerFilter").getResultList();
    }

    public List<DataByPurchase> getDataByPurchase(){
        return manager.createNamedQuery("dataByPurchase").getResultList();
    }
}
