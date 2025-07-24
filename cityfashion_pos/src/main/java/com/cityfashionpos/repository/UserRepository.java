package com.cityfashionpos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	@Query(value = """
			SELECT * FROM users
			WHERE username = :username
			AND CONVERT(AES_DECRYPT(FROM_BASE64(password), :secretKey) USING UTF8MB4) = :rawPassword
			""", nativeQuery = true)
	
	Optional<UserEntity> authenticateUser(@Param("username") String username, @Param("rawPassword") String rawPassword,
			@Param("secretKey") String secretKey);
	
	Optional<UserEntity> findByUsername(String username);

}
