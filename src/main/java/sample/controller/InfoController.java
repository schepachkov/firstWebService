package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.entity.Book;
import sample.entity.Buyer;
import sample.entity.Purchase;
import sample.entity.Seller;
import sample.entity.info.*;
import sample.service.InfoService;

import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/all/books/nameAndCost")
    public List<Book> getAllNameAndCost() {
        return infoService.allNameAndCost();
    }

    @GetMapping("/all/buyers/areas")
    public List<Buyer> getAllAreas() {
        return infoService.allDistinctAreas();
    }

    @GetMapping("/all/purchase/months")
    public List<Purchase> getAllMonths() {
        return infoService.allDistinctMonths();
    }

    @GetMapping("/buyers/fromNizhnyNovg")
    public List<Buyer> getBuyersFromNighny() {
        return infoService.buyersFromNihny();
    }

    @GetMapping("/sellers/NameAndArea")
    public List<Seller> shopNameAndArea() {
        return infoService.shopNameAndArea();
    }

    @GetMapping("/book/nameAndCostWithFilterAndSort")
    public List<Book> getBookNmeAndCostWithFilterAndSort() {
        return infoService.nameAndCostWithFilterAndSort();
    }


    @GetMapping("/join/getSurnameAndShop")
    public List<SurnameAndShop> getSurnameAndShop() {
        return infoService.surnameAndShop();
    }

    @GetMapping("/join/getBuyerBookPurchase")
    public List<SurnameDiscountNameAmountDate> getBuyerBookPurchase() {
        return infoService.joinBuyerBookPurchase();
    }

    @GetMapping("/join/getOrderMoreThan60K")
    public List<OrderMoreThan60K> getOrderMoreThan60K() {
        return infoService.orderMoreThan60K();
    }

    @GetMapping("/join/getBuyInYourselfAreaAfterThanMarch")
    public List<BuyInYourselfAreaAfterThanMarch> getBuyInYourselfAreaAfterThanMarch() {
        return infoService.buyInYourselfAreaAfterThanMarch();
    }

    @GetMapping("/join/getNotAvtozavodShopWithBuyerFilter")
    public List<NotAvtozavodShopWithBuyerFilter> getNotAvtozavodShopWithBuyerFilter() {
        return infoService.notAvtozavodShopWithBuyerFilter();
    }

    @GetMapping("/join/getDataByPurchase")
    public List<DataByPurchase> getDataByPurchase() {
        return infoService.getDataByPurchase();
    }

}
