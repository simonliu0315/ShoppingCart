package com.waterproof.bjb.shopping.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.waterproof.bjb.shopping.dao.UserDao;
import com.waterproof.bjb.shopping.dao.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "userDetailService")
@Service
public class UserDetailServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
    private UserRepository userRepository;
	
//	@Resource
//	private ProductRepository productRepository;
//	
	
	public UserDetails loadUserByUsername(String id) {
		log.info("****loadUserByUsername****");
		com.waterproof.bjb.shopping.entity.UserDetails user = userDao.findUserById(id);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(
				user.getName(), 
				user.getPassword(), 
				getAuthority());
		
	}
	private List getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

//	public List getUsers() {
//		return userDao.getUserDetails();
//	}
	
	
    public List<com.waterproof.bjb.shopping.entity.UserDetails> findAll() {
        return userRepository.findAll();
    }

    public com.waterproof.bjb.shopping.entity.UserDetails insertByUser(com.waterproof.bjb.shopping.entity.UserDetails user) {
        log.info("新增使用者：" + user.toString());
        return userRepository.save(user);
    }

    public com.waterproof.bjb.shopping.entity.UserDetails update(com.waterproof.bjb.shopping.entity.UserDetails user) {
        log.info("更新使用者：" + user.toString());
        return userRepository.save(user);
    }

    public com.waterproof.bjb.shopping.entity.UserDetails delete(String id) {
    	com.waterproof.bjb.shopping.entity.UserDetails user = 
    			userRepository.findOne(id);
        userRepository.delete(user);

        log.info("删除使用者：" + user.toString());
        return user;
    }

    public com.waterproof.bjb.shopping.entity.UserDetails findById(String id) {
        log.info("獲取使用者 ID ：" + id);
        return userRepository.findOne(id);
    }
    
    
}
