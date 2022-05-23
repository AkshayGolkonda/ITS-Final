package com.its.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.its.dto.User;
import com.its.entity.UserEntity;
import com.its.exception.InvalidAuthTokenException;
import com.its.exception.InvalidCredentialsException;
import com.its.repo.UserRepo;
import com.its.security.JwtUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	//@Autowired
	//BlackListTokenRepo blackListTokenRepo;

	@Override
	public String authenticate(User user) {
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())	
					);
		}
		catch(AuthenticationException ex){
			throw new InvalidCredentialsException();
		}
		String jwt=jwtUtil.generateToken(user.getUserName());
		return jwt;
	}

	/*
	 * @Override public boolean logout(String authToken) { BlacklistedTokensDocument
	 * blacklistedToken=blackListTokenRepo.findByToken(authToken);
	 * if(blacklistedToken!=null) { throw new AlreadyLoggedOutException(); }
	 * BlacklistedTokensDocument newToken=new
	 * BlacklistedTokensDocument(authToken,LocalDate.now());
	 * blackListTokenRepo.save(newToken); return true; }
	 */

	@Override
	public User addUser(User user) {
		UserEntity userEntity=convertDTOIntoEntity(user);
		userRepo.save(userEntity);
		return convertEntityIntoDTO(userEntity);
	}

	@Override
	public User getUser(String authToken) {
		try {
			authToken=authToken.substring(7);
			String username=jwtUtil.extractUsername(authToken);
			List<UserEntity> userEntityList = userRepo.findByUserName(username);
			if(userEntityList==null || userEntityList.size()==0) {
				throw new UsernameNotFoundException(username);
			}
			UserEntity userEnitity=userEntityList.get(0);
			return convertEntityIntoDTO(userEnitity);
		}
		catch (Exception e) {
			throw new InvalidAuthTokenException(e.toString());
		}
	}

		@Override
		public boolean validateJWT(String authToken) {
			authToken=authToken.substring(7);
			/*
			 * BlacklistedTokensDocument
			 * blacklistedToken=blackListTokenRepo.findByToken(authToken);
			 * if(blacklistedToken!=null) { throw new InvalidAuthTokenException(); }
			 */
			String username=jwtUtil.extractUsername(authToken);
			UserDetails userDetails=userDetailsService.loadUserByUsername(username);
			return jwtUtil.validateToken(authToken, userDetails);
		}
	
	
	private UserEntity convertDTOIntoEntity(User user) {
		UserEntity userEntity=modelMapper.map(user,UserEntity.class);
		return userEntity;
	}
	
	private User convertEntityIntoDTO(UserEntity userEntity) {
		User user=modelMapper.map(userEntity, User.class);
		return user;
	}

	@Override
	public boolean validateAdminJWT(String authToken) {
		authToken=authToken.substring(7);
		String username=jwtUtil.extractUsername(authToken);
		String role=userRepo.findByUserName(username).get(0).getRole();
		UserDetails userDetails=userDetailsService.loadUserByUsername(username);
		boolean valid=jwtUtil.validateToken(authToken, userDetails);
		if(valid && role.equalsIgnoreCase("admin"))
			return true;
		else
			return false;
	}

	@Override
	public boolean validateTechJWT(String authToken) {
		authToken=authToken.substring(7);
		String username=jwtUtil.extractUsername(authToken);
		String role=userRepo.findByUserName(username).get(0).getRole();
		UserDetails userDetails=userDetailsService.loadUserByUsername(username);
		boolean valid=jwtUtil.validateToken(authToken, userDetails);
		if(valid && role.equalsIgnoreCase("tech"))
			return true;
		else
			return false;
	}

	@Override
	public boolean validateHRJWT(String authToken) {
		authToken=authToken.substring(7);
		String username=jwtUtil.extractUsername(authToken);
		String role=userRepo.findByUserName(username).get(0).getRole();
		UserDetails userDetails=userDetailsService.loadUserByUsername(username);
		boolean valid=jwtUtil.validateToken(authToken, userDetails);
		if(valid && role.equalsIgnoreCase("hr"))
			return true;
		else
			return false;
	}

	
}
