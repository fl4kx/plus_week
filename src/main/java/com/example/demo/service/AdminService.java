package com.example.demo.service;

import com.example.demo.entity.Status;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private EntityManager em;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // TODO: 4. find or save 예제 개선
    @Transactional
    public void reportUsers(List<Long> userIds) {

        ListUtils.partition(userIds, 1000).forEach(batch -> {
            userRepository.blockUsersById(Status.BLOCKED, batch);
            em.clear();
        });

    }
}
