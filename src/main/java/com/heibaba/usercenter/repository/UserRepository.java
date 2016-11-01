package com.heibaba.usercenter.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.heibaba.usercenter.entity.rdb.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	Page<UserEntity> findAll(Specification<UserEntity> spec, Pageable pageable);

	List<UserEntity> findByTypeAndEnabled(String type, int enabled);

	UserEntity findByUserName(String userName);
	
	UserEntity findByTelephone(String telephone);

	@Modifying
	@Query("update UserEntity a set a.password = :pwd where a.userId = :userId")
	void changePwd(@Param("userId") int userId, @Param("pwd") String pwd);

	@Modifying
	@Query("update UserEntity a set a.userName = :userName where a.userId = :userId")
	void changeUserName(@Param("userId") int userId, @Param("userName") String userName);

	@Modifying
	@Query("update UserEntity a set a.telephone = :telephone where a.userId = :userId")
	void changeTelephone(@Param("userId") int userId, @Param("telephone") String telephone);

	@Modifying
	@Query("update UserEntity a set a.locked = 1 where a.userId = :userId")
	void lockUser(@Param("userId") int userId);

	@Modifying
	@Query("update UserEntity a set a.locked = 0 where a.userId = :userId")
	void unlockUser(@Param("userId") int userId);
	
	@Modifying
	@Query("update UserEntity a set a.enabled = 1 where a.userId = :userId")
	void enableUser(@Param("userId") int userId);
	
	@Modifying
	@Query("update UserEntity a set a.enabled = 0 where a.userId = :userId")
	void forbiddenUser(@Param("userId") int userId);
}
