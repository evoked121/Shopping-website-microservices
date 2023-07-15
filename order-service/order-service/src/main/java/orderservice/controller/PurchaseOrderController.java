package orderservice.controller;

import orderservice.dto.PurchaseOrderRequestDto;
import orderservice.dto.PurchaseOrderResponseDto;
import orderservice.service.OrderFulfillmentService;
import orderservice.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class PurchaseOrderController {

    @Autowired
    private OrderFulfillmentService orderFulfillmentService;

    @Autowired
    private OrderQueryService queryService;

    @PostMapping
    public Mono<ResponseEntity<PurchaseOrderResponseDto>> order(@RequestBody Mono<PurchaseOrderRequestDto> requestDtoMono){
        return orderFulfillmentService.processOrder(requestDtoMono)
                .map(ResponseEntity::ok)
                .onErrorReturn(WebClientException.class, ResponseEntity.badRequest().build())
                .onErrorReturn(WebClientException.class, ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
    }
    @GetMapping("user/{id}")
    public Flux<PurchaseOrderResponseDto> getOrdersByUserId(@PathVariable int id){
        return queryService.getProductByUserId(id);
    }
}
