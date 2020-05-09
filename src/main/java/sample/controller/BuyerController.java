package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.entity.Buyer;
import sample.service.BuyerService;

import java.util.List;


@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable int id) {
        Buyer buyer = buyerService.getBuyerById(id);
        if (buyer != null)
            return ResponseEntity.ok(buyer);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        buyerService.deleteById(id);
    }


    @PatchMapping("/update/surname/{id}")
    public ResponseEntity<Buyer> updateSurname(@PathVariable int id, @RequestBody String surname) {
        Buyer buyer = buyerService.updateSurname(id, surname);
        if (buyer != null)
            return ResponseEntity.ok(buyer);
        else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/update/discount/{id}")
    public ResponseEntity<Buyer> updateDiscount(@PathVariable int id, @RequestBody int discount) {
        Buyer buyer = buyerService.updateDiscount(id, discount);
        if (buyer != null)
            return ResponseEntity.ok(buyer);
        else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/update/area/{id}")
    public ResponseEntity<Buyer> updateArea(@PathVariable int id, @RequestBody String area) {
        Buyer buyer = buyerService.updateArea(id, area);
        if (buyer != null)
            return ResponseEntity.ok(buyer);
        else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createBuyer(@RequestBody Buyer buyer) {
        buyerService.addBuyer(buyer);
    }


    @GetMapping("/get/all")
    public List<Buyer> getAllBuyers() {
        return buyerService.getAllBuyers();
    }


    @PutMapping("/put/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void fullReplaceById(@PathVariable int id, @RequestBody Buyer buyer) {
        buyerService.fullReplaceById(id, buyer);
    }

}
