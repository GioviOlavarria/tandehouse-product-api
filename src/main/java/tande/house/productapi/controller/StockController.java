package tande.house.productapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import tande.house.productapi.dto.StockReserveRequest;
import tande.house.productapi.service.StockService;


@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @Value("${internal.serviceKey}")
    private String internalKey;

    private void checkKey(String key) {
        if (internalKey != null && !internalKey.isBlank()) {
            if (!internalKey.equals(key))
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/reserve")
    public void reserve(
            @RequestHeader("X-Internal-Key") String key,
            @Valid @RequestBody StockReserveRequest req
    ) {
        checkKey(key);
        stockService.reserve(req.getItems());
    }

    @PostMapping("/commit")
    public void commit(
            @RequestHeader("X-Internal-Key") String key,
            @Valid @RequestBody StockReserveRequest req
    ) {
        checkKey(key);
        stockService.commit(req.getItems());
    }

    @PostMapping("/release")
    public void release(
            @RequestHeader("X-Internal-Key") String key,
            @Valid @RequestBody StockReserveRequest req
    ) {
        checkKey(key);
        stockService.release(req.getItems());
    }
}