package userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import userservice.dto.TransactionRequestDto;
import userservice.dto.TransactionResponseDto;
import userservice.entity.UserTransaction;
import userservice.service.TransactionService;

@RestController
@RequestMapping("user/transaction")
public class UserTransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody  Mono<TransactionRequestDto> requestDto){
        return requestDto
                .flatMap(transactionService::createTransaction);
    }

    @GetMapping
    public Flux<UserTransaction> getByUserId(@RequestParam("userId") int userId){
        return transactionService.getByUserId(userId);
    }
}
