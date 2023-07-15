package userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import userservice.dto.TransactionRequestDto;
import userservice.dto.TransactionResponseDto;
import userservice.dto.TransactionStatus;
import userservice.entity.UserTransaction;
import userservice.repository.UserRepository;
import userservice.repository.UserTransactionRepository;
import userservice.util.EntityDtoUtil;

@Service
public class TransactionService {
    @Autowired
    private UserTransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto){
        return userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> EntityDtoUtil.toEntity(requestDto))
                .flatMap(transactionRepository::save)
                .map(ut -> EntityDtoUtil.toDto(requestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toDto(requestDto, TransactionStatus.DECLINED));
    }

    public Flux<UserTransaction> getByUserId(int userId){
        return transactionRepository.findByUserId(userId);
    }
}
