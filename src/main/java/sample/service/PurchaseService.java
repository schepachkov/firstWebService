package sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.entity.Book;
import sample.entity.Purchase;
import sample.repository.PurchaseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private Logger logger = LoggerFactory.getLogger(PurchaseService.class);

    private final String TABLE = "purchase";

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private BookService bookService;

    public Purchase getPurchaseById(int id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);

        return purchase.orElse(null);
    }

    public void deleteById(int id) {
        Purchase purchase = getPurchaseById(id);
        if (purchase != null) {
            Book book = bookService.updateBookAmount(purchase.getBook(), purchase.getAmount());
            if (book != null) {
                boolean isDelete = purchaseRepository.safeDelete(TABLE, id);
                if (!isDelete) {
                    logger.warn(String.format("You try to delete not existed entity from the table \"%s\"!", TABLE));
                }
            }
        }
    }


    public Purchase updateSeller(int id, int seller) {
        Purchase purchase = getPurchaseById(id);
        if (purchase != null) {
            purchase.setSeller(seller);
            purchaseRepository.save(purchase);
            updateTotal(id);
            return purchase;
        } else {
            logger.warn("You try to update not existed entity!");
        }
        return null;
    }

    public Purchase updateBuyer(int id, int buyer) {
        Purchase purchase = getPurchaseById(id);
        if (purchase != null) {
            purchase.setBuyer(buyer);
            purchaseRepository.save(purchase);
            updateTotal(id);
            return purchase;
        } else {
            logger.warn("You try to update not existed entity!");
        }
        return null;
    }

    public Purchase updateBook(int id, int book) {
        Purchase purchase = getPurchaseById(id);
        if (purchase != null) {
            bookService.updateBookAmount(purchase.getBook(), purchase.getAmount());
            purchase.setBook(book);
            bookService.updateBookAmount(book, -purchase.getAmount());
            purchaseRepository.save(purchase);
            updateTotal(id);
            return purchase;
        } else {
            logger.warn("You try to update not existed entity!");
        }
        return null;
    }


    public Purchase updateAmount(int id, int amount) {
        Purchase purchase = getPurchaseById(id);
        if (purchase != null) {
            bookService.updateBookAmount(purchase.getBook(), purchase.getAmount());
            purchase.setAmount(amount);
            bookService.updateBookAmount(purchase.getBook(), -amount);
            purchaseRepository.save(purchase);
            updateTotal(id);
            return purchase;
        } else {
            logger.warn("You try to update not existed entity!");
        }
        return null;
    }

    public Purchase updateTotal(int id) {
        Purchase purchase = getPurchaseById(id);
        if (purchase != null) {
            double newTotal = purchaseRepository.calculateTotal(id);
            purchase.setTotal(newTotal);
            purchaseRepository.save(purchase);
            return purchase;
        } else {
            logger.warn("You try to update not existed entity!");
        }
        return null;
    }


    public boolean addPurchase(Purchase purchase) {
        boolean canInsert = purchaseRepository.canInsert(purchase.getBook(), purchase.getAmount());
        if (canInsert) {
            int savedPurchaseID = purchaseRepository.save(purchase).getId();
            purchase = updateTotal(savedPurchaseID);
            bookService.updateBookAmount(purchase.getBook(), -purchase.getAmount());
            purchaseRepository.save(purchase);
            return true;
        } else {
            logger.warn("There are not enough books in the stock!");
        }
        return false;
    }


    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public boolean fullReplaceById(int id, Purchase purchase) {
        Purchase updated = getPurchaseById(id);
        bookService.updateBookAmount(updated.getBook(), updated.getAmount());
        boolean canUpdate = purchaseRepository.canInsert(purchase.getBook(), purchase.getAmount());
        if (canUpdate) {
            updated.setSeller(purchase.getSeller());
            updated.setBuyer(purchase.getBuyer());
            updated.setBook(purchase.getBook());
            updated.setAmount(purchase.getAmount());
            bookService.updateBookAmount(purchase.getBook(), -purchase.getAmount());
            updateTotal(id);
            purchaseRepository.save(updated);
            return true;
        } else {
            logger.warn("There are not enough books in the stock!");
            bookService.updateBookAmount(updated.getBook(), -updated.getAmount());
        }
        return false;
    }
}
