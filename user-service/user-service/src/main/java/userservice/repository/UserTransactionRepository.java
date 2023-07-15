package userservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import userservice.entity.UserTransaction;

@Repository
public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction,Integer> {

    Flux<UserTransaction> findByUserId(int userId);
}
