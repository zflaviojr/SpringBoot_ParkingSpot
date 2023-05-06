package edu.ufcg.br.parkingspot.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ufcg.br.parkingspot.model.UserModel;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, UUID>{


}