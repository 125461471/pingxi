package com.heibaba.usercenter.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.heibaba.common.exception.BusinessException;
import com.heibaba.common.utils.PasswordUtil;
import com.heibaba.common.utils.UserNameUtil;
import com.heibaba.usercenter.entity.rdb.UserEntity;
import com.heibaba.usercenter.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public UserEntity addUser(UserEntity entity) {

		return repository.save(entity);
	}

	public UserEntity updateUser(UserEntity entity)  {

		return repository.save(entity);
	}

	public void deleteUser(int id) {

		repository.delete(id);
	}

	public UserEntity getUser(int id) {

		return repository.findOne(id);
	}

	public Page<UserEntity> listUser(String userName, String nickName, Pageable pageable)
			{

		Page<UserEntity> list = demoListDynamic(userName, nickName, pageable);
		// 注释 。如果这样操作，只要刷新用户列表，数据库中当前页的所有用户的密码都会被清空。
		// for (UserEntity record : list) {
		// record.setPassword("");
		// }
		return list;
	}

	public Page<UserEntity> demoListDynamic(String userName, String nickName, Pageable pageable)
			{

		Page<UserEntity> list = repository.findAll(new Specification<UserEntity>() {
			@Override
			public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();
				if (!StringUtils.isEmpty(userName)) {
					expressions.add(cb.like(root.<String> get("userName"), "%" + userName + "%"));
				}
				if (!StringUtils.isEmpty(nickName)) {
					expressions.add(cb.like(root.<String> get("nickName"), "%" + nickName + "%"));
				}
				return predicate;
			}
		}, pageable);

		return list;
	}

	/**
	 * 修改密码 
	 * @param userId 
	 * @param pwd 加密后的密码
	 */
	public void changePwd(int userId, String pwd) {

		//验证密码合法性
		if (!PasswordUtil.isPassword(pwd)) {
			throw new BusinessException("密码格式错误.");
		}
		repository.changePwd(userId, pwd);
	}

	public void changeUserName(int userId, String userName) {

		//判断用户名合法性
		if (!UserNameUtil.isUserName(userName)) {
			throw new BusinessException("用户名格式错误.");
		}
		//判断用户名是否存在
		if (checkUserNameExist(userName)) {
			throw new BusinessException("此用户名已注册.");
		}
		repository.changeUserName(userId, userName);
	}
	
	public void changeTelephone(int userId, String telephone) {

		//判断手机号合法性
		if (!UserNameUtil.isTelephone(telephone)) {
			throw new BusinessException("手机号格式错误.");
		}
		//判断手机号是否存在
		if (checkTelephoneExist(telephone)) {
			throw new BusinessException("此手机号已注册.");
		}
		repository.changeTelephone(userId, telephone);
	}
	
	public void lockUser(int userId) {

		repository.lockUser(userId);
	}

	public void unlockUser(int userId) {

		repository.unlockUser(userId);
	}

	public void enableUser(int userId) {

		repository.enableUser(userId);
	}

	public void forbiddenUser(int userId) {

		repository.forbiddenUser(userId);
	}
	
	/**
	 * 验证用户名是否已存在
	 * @param userName
	 * @return
	 */
	public boolean checkUserNameExist(String userName) {
		
		UserEntity uesrEntity = repository.findByUserName(userName);
		if (uesrEntity == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 验证用户手机是否已存在
	 * @param telephone
	 * @return
	 */
	public boolean checkTelephoneExist(String telephone) {
		
		UserEntity uesrEntity = repository.findByTelephone(telephone);
		if (uesrEntity == null) {
			return false;
		}

		return true;
	}
}
