package sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.entity.Buyer;
import sample.repository.BuyerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerService {

    private final String TABLE = "buyer";

    private Logger logger = LoggerFactory.getLogger(BuyerService.class);

    @Autowired
    private BuyerRepository buyerRepository;

    public Buyer getBuyerById(int id){
        Optional<Buyer> foundBuyer = buyerRepository.findById(id);
        return foundBuyer.orElse(null);
    }


    public void deleteById(int id) {
        boolean isDelete = buyerRepository.safeDelete(TABLE, id);
        if (!isDelete)
            logger.warn(String.format("You try to delete a doesn't exist entity from the table \"%s\"!",TABLE));
    }


    public Buyer updateSurname(int id, String name) {
        Buyer buyer = getBuyerById(id);
        buyer.setSurname(name);
        buyerRepository.save(buyer);
        return buyer;
    }

    public Buyer updateDiscount(int id, int discount) {
        Buyer buyer = getBuyerById(id);
        buyer.setDiscount(discount);
        buyerRepository.save(buyer);
        return buyer;
    }

    public Buyer updateArea(int id, String area) {
        Buyer buyer = getBuyerById(id);
        buyer.setArea(area);
        buyerRepository.save(buyer);
        return buyer;
    }


    public void addBuyer(Buyer buyer){
        buyerRepository.save(buyer);
    }



    public List<Buyer> getAllBuyers(){
        return buyerRepository.findAll();
    }



    public void fullReplaceById(int id, Buyer buyer){
        buyerRepository.updateBuyer(id, buyer.getSurname(), buyer.getArea(), buyer.getDiscount());
    }
}
