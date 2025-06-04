package com.example.Pet.Reponsitories;

import com.example.Pet.Entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<Services, Integer> {
}
