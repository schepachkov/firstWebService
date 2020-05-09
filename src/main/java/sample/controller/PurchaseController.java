package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.entity.Purchase;
import sample.service.PurchaseService;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable int id) {
        Purchase purchase = purchaseService.getPurchaseById(id);

        if (purchase != null)
            return ResponseEntity.ok(purchase);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        purchaseService.deleteById(id);
    }


    @PatchMapping("/update/seller/{id}/{id_seller}")
    public ResponseEntity<Purchase> updateSeller(@PathVariable("id") int id, @PathVariable("id_seller") int idSeller) {
        Purchase purchase = purchaseService.updateSeller(id, idSeller);

        if (purchase != null) {
            return ResponseEntity.ok(purchase);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update/buyer/{id}/{id_buyer}")
    public ResponseEntity<Purchase> updateBuyer(@PathVariable("id") int id, @PathVariable("id_buyer") int idBuyer) {
        Purchase purchase = purchaseService.updateBuyer(id, idBuyer);

        if (purchase != null) {
            return ResponseEntity.ok(purchase);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update/book/{id}/{id_book}")
    public ResponseEntity<Purchase> updateBook(@PathVariable("id") int id, @PathVariable("id_book") int idBook) {
        Purchase purchase = purchaseService.updateBook(id, idBook);

        if (purchase != null) {
            return ResponseEntity.ok(purchase);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update/amount/{id}/{amount}")
    public ResponseEntity<Purchase> updateAmount(@PathVariable("id") int id, @PathVariable("amount") int amount) {
        Purchase purchase = purchaseService.updateAmount(id, amount);

        if (purchase != null) {
            return ResponseEntity.ok(purchase);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update/total/{id}")
    private ResponseEntity<Purchase> updateTotal(@PathVariable("id") int id) {
        Purchase purchase = purchaseService.updateTotal(id);

        if (purchase != null)
            return ResponseEntity.ok(purchase);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createPurchase(@RequestBody Purchase purchase) {
        boolean isCreated = purchaseService.addPurchase(purchase);
        if (isCreated)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/get/all")
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }


    @PutMapping("/put/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> fullReplaceById(@PathVariable int id, @RequestBody Purchase purchase) {
        boolean isUpdate = purchaseService.fullReplaceById(id, purchase);
        if (isUpdate)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
