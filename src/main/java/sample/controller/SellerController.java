package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.entity.Seller;
import sample.service.SellerService;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;


    @GetMapping("/get/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable int id) {
        Seller seller = sellerService.getSellerById(id);
        if (seller != null)
            return ResponseEntity.ok(seller);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteSellerById(@PathVariable int id) {
        sellerService.deleteById(id);
    }


    @PatchMapping("/update/name/{id}")
    public ResponseEntity<Seller> updateSellerName(@PathVariable int id, @RequestBody String name) {
        Seller seller = sellerService.updateName(id, name);
        if (seller != null)
            return ResponseEntity.ok(seller);
        else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/update/commission/{id}")
    public ResponseEntity<Seller> updateSellerCommission(@PathVariable int id, @RequestBody int commission) {
        Seller seller = sellerService.updateCommission(id, commission);
        if (seller != null)
            return ResponseEntity.ok(seller);
        else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/update/area/{id}")
    public ResponseEntity<Seller> updateSellerArea(@PathVariable int id, @RequestBody String area) {
        Seller seller = sellerService.updateArea(id, area);
        if (seller != null)
            return ResponseEntity.ok(seller);
        else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createSeller(@RequestBody Seller seller) {
        sellerService.addSeller(seller);
    }


    @GetMapping("/get/all")
    public List<Seller> getAllSellers() {
        return sellerService.getAllSellers();
    }


    @PutMapping("/put/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void fullReplace(@PathVariable int id, @RequestBody Seller seller) {
        sellerService.fullReplaceById(id, seller);
    }
}
