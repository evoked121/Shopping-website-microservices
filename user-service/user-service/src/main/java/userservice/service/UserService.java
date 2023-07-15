package userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import userservice.dto.UserDto;
import userservice.repository.UserRepository;
import userservice.util.EntityDtoUtil;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Flux<UserDto> all(){
        return userRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> getUserById(Integer id){
        return userRepository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> createUser(Mono<UserDto> userDtoMono){
        return userDtoMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    //先找到对应的entity然后把他换成带入的Dto同时set id, 利用map转换
    public Mono<UserDto> updateUser(int id, Mono<UserDto> userDtoMono){
        return userRepository.findById(id)
                .flatMap(u -> userDtoMono
                        .map(EntityDtoUtil::toEntity)
                        .doOnNext(e->e.setId(id)))
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteUser(int id){
        return userRepository.deleteById(id);
    }
}
