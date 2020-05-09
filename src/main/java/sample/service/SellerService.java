package sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.entity.Seller;
import sample.repository.SellerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private Logger logger = LoggerFactory.getLogger(SellerService.class);

    private final String TABLE = "seller";

    @Autowired
    private SellerRepository sellerRepository;

    public Seller getSellerById(int id){
        Optional<Seller> foundBuyer = sellerRepository.findById(id);
        return foundBuyer.orElse(null);
    }


    public void deleteById(int id) {
        boolean isDelete = sellerRepository.safeDelete(TABLE, id);
        if (!isDelete)
            logger.warn(String.format("You try to delete a doesn't exist entity from the table \"%s\"!",TABLE));
    }


    public Seller updateName(int id, String name) {
        Seller seller = getSellerById(id);
        seller.setName(name);
        sellerRepository.save(seller);
        return seller;
    }

    public Seller updateCommission(int id, int commission) {
        Seller seller = getSellerById(id);
        seller.setCommission(commission);
        sellerRepository.save(seller);
        return seller;
    }

    public Seller updateArea(int id, String area) {
        Seller seller = getSellerById(id);
        seller.setArea(area);
        sellerRepository.save(seller);
        return seller;
    }


    public void addSeller(Seller seller){
        sellerRepository.save(seller);
    }



    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }



    public void fullReplaceById(int id, Seller seller){
        sellerRepository.updateSeller(id, seller.getName(), seller.getArea(), seller.getCommission());
    }
}
