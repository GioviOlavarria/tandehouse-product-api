package tande.house.productapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tande.house.productapi.dto.StockItemRequest;
import tande.house.productapi.model.Product;
import tande.house.productapi.repo.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final ProductRepository repo;

    @Transactional
    public void reserve(List<StockItemRequest> items) {
        for (StockItemRequest i : items) {
            Product p = repo.findById(i.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no existe: " + i.getProductId()));

            int stock = p.getStock() == null ? 0 : p.getStock();
            if (stock < i.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para " + p.getId());
            }

            p.setStock(stock - i.getQuantity());
            repo.save(p);
        }
    }

    @Transactional
    public void commit(List<StockItemRequest> items) {
    }

    @Transactional
    public void release(List<StockItemRequest> items) {
        for (StockItemRequest i : items) {
            Product p = repo.findById(i.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no existe: " + i.getProductId()));

            int stock = p.getStock() == null ? 0 : p.getStock();
            p.setStock(stock + i.getQuantity());
            repo.save(p);
        }
    }
}
