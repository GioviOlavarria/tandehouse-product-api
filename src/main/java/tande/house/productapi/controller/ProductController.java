package tande.house.productapi.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tande.house.productapi.dto.ProductRequest;
import tande.house.productapi.dto.ProductResponse;
import tande.house.productapi.model.Product;
import tande.house.productapi.repo.ProductRepository;


import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repo;

    @GetMapping
    public List<ProductResponse> all() {
        return repo.findAll().stream()
                .filter(Product::isActivo)
                .map(p -> new ProductResponse(
                        p.getId(), p.getNombre(), p.getDescripcion(),
                        p.getPrecio(), p.getStock(), p.isActivo(), p.getImagenUrl()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public ProductResponse one(@PathVariable Long id) {
        Product p = repo.findById(id).orElseThrow();
        return new ProductResponse(
                p.getId(), p.getNombre(), p.getDescripcion(),
                p.getPrecio(), p.getStock(), p.isActivo(), p.getImagenUrl()
        );
    }

    @PostMapping
    public ProductResponse create(@Valid @RequestBody ProductRequest r) {
        Product p = new Product(
                null, r.getNombre(), r.getDescripcion(),
                r.getPrecio(), r.getStock(),
                r.isActivo(), r.getImagenUrl()
        );
        p = repo.save(p);
        return new ProductResponse(
                p.getId(), p.getNombre(), p.getDescripcion(),
                p.getPrecio(), p.getStock(), p.isActivo(), p.getImagenUrl()
        );
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @Valid @RequestBody ProductRequest r) {
        Product p = repo.findById(id).orElseThrow();
        p.setNombre(r.getNombre());
        p.setDescripcion(r.getDescripcion());
        p.setPrecio(r.getPrecio());
        p.setStock(r.getStock());
        p.setActivo(r.isActivo());
        p.setImagenUrl(r.getImagenUrl());
        repo.save(p);
        return new ProductResponse(
                p.getId(), p.getNombre(), p.getDescripcion(),
                p.getPrecio(), p.getStock(), p.isActivo(), p.getImagenUrl()
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
