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
                    .orElseThrow(() -> new RuntimeException("Producto no existe"));

            if (!p.isActivo())
                throw new RuntimeException("Producto inactivo");

            if (p.getStock() < i.getQuantity())
                throw new RuntimeException("Stock insuficiente");

            p.setStock(p.getStock() - i.getQuantity());
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
                    .orElseThrow();

            p.setStock(p.getStock() + i.getQuantity());
            repo.save(p);
        }
    }
}
