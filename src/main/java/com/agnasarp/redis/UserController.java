package com.agnasarp.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")
    @GetMapping("/{userId}")
    public Optional getUser(@PathVariable Long userId) {
        LOG.info("Getting user with ID {}.", userId);
        return userRepository.findById(userId);
    }

    @CachePut(value = "users", key = "#user.id")
    @PutMapping("/update")
    public User updatePersonByID(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @CacheEvict(value = "users", key = "#id")
    @DeleteMapping("/{id}")
    public void deleteUserByID(@PathVariable Long id) {
        LOG.info("deleting person with id {}", id);
        userRepository.deleteById(id);
    }

    @CacheEvict(value = "users", allEntries = true)
    @PostMapping("/evictCaches")
    public String evictAllCacheValues() {
        return "Cache cleared.";
    }
}
