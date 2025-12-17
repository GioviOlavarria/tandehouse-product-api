package tande.house.productapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tande.house.productapi.dto.ProductRequest;
import tande.house.productapi.dto.ProductResponse;
import tande.house.productapi.model.Product;
import tande.house.productapi.repo.ProductRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repo;

    private static ProductResponse toResp(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getStock(),
                p.getCategoria(),
                p.getOferta(),
                p.getPortada(),
                p.getSku()
        );
    }

    @GetMapping
    public List<ProductResponse> all() {
        return repo.findAll().stream().map(ProductController::toResp).toList();
    }

    @GetMapping("/{id}")
    public ProductResponse one(@PathVariable String id) {
        Product p = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado")
        );
        return toResp(p);
    }

    @PostMapping
    public ProductResponse create(@Valid @RequestBody ProductRequest r) {
        String id = (r.getId() == null || r.getId().isBlank())
                ? ("p_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8))
                : r.getId();

        if (repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un producto con id=" + id);
        }

        Product p = new Product(
                id,
                r.getNombre(),
                r.getPrecio(),
                r.getStock(),
                r.getCategoria(),
                r.getOferta(),
                r.getPortada(),
                r.getSku()
        );

        p = repo.save(p);
        return toResp(p);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable String id, @Valid @RequestBody ProductRequest r) {
        Product p = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado")
        );

        p.setNombre(r.getNombre());
        p.setPrecio(r.getPrecio());
        p.setStock(r.getStock());
        p.setCategoria(r.getCategoria());
        p.setOferta(r.getOferta());
        p.setPortada(r.getPortada());
        p.setSku(r.getSku());

        p = repo.save(p);
        return toResp(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        repo.deleteById(id);
    }
}
