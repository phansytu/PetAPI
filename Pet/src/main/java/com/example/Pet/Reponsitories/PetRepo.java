package com.example.Pet.Reponsitories;

import com.example.Pet.Entity.Pet;
import com.example.Pet.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepo extends JpaRepository<Pet, Integer> {
    List<Pet> findAllByOwner(User owner);
    @Query("SELECT p FROM Pet p WHERE p.weight = :weight AND p.owner = :owner")
    List<Pet> searchByWeight(@Param("weight") Double weight,@Param("owner") User owner);
    @Query("SELECT p FROM Pet p WHERE p.type LIKE %:key% AND p.owner = :owner")
    List<Pet> searchByType(@Param("key") String type,@Param("owner") User owner);
    @Query("SELECT p FROM Pet p WHERE p.nameHome LIKE %:keyName% AND p.owner = :owner")
    List<Pet> searchByNameHome(@Param("keyName") String nameHome,@Param("owner") User owner);
}
