package com.disney.demo.repository;

import com.disney.demo.entity.CharacterEntity;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long>{
    
    public List<CharacterEntity> findAll(Specification<CharacterEntity> spec);


}

