package com.niakhtu.backend.niakhtu.repository;


import com.niakhtu.backend.niakhtu.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByPlaignantIdAndVerificationCode(String plaignantId, String verificationCode);
}
