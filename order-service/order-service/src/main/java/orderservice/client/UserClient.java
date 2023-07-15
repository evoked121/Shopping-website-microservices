package orderservice.client;

import orderservice.dto.TransactionRequestDto;
import orderservice.dto.TransactionResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserClient {

    private final WebClient webClient;

    public UserClient(@Value("${user.service.url}") String url){
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public Mono<TransactionResponseDto> authorizeTransaction(TransactionRequestDto transactionRequestDto){
        return this.webClient
                .post()
                .uri("transaction")
                .bodyValue(transactionRequestDto)
                .retrieve()
                .bodyToMono(TransactionResponseDto.class);
    }
}
