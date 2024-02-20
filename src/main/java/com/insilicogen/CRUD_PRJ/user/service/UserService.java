package com.insilicogen.CRUD_PRJ.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insilicogen.CRUD_PRJ.user.repository.UserRepository;
import com.insilicogen.CRUD_PRJ.user.service.VO.UserModifyVO;
import com.insilicogen.CRUD_PRJ.user.service.dto.UserDto;

@Service
public class UserService {

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User registerUser(UserDto userDto, PSWD_HINT pswdHint) {
		// UserDto 객체를 User 엔티티로 변환
		User user = new User();

		System.out.println("유저 로그인 아이디 : " + userDto.getUserLoginId());
		System.out.println("유저 이름 : " + userDto.getUserNm());
		System.out.println("유저 비밀번호 : " + userDto.getPassword());
		System.out.println("유저 성별 : " + userDto.getSex());
		System.out.println("유저 생일 : " + userDto.getDateOfBirth());
		System.out.println("유저 비밀번호 힌트 답변 : " + userDto.getHintAnswer());

		user.setUserLoginId(userDto.getUserLoginId());
		user.setUserNm(userDto.getUserNm());
		user.setPassword(userDto.getPassword());
		user.setSex(userDto.getSex());
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setHintAnswer(userDto.getHintAnswer());
		user.setPswdHintSn(pswdHint);
		// UserRepository를 사용하여 User 엔티티를 저장
		this.userRepository.save(user);

		System.out.println("유저 정보 db 저장 완료");
		return user;
	}

	public boolean isUserIdDuplicated(String lgnId) { // 중복 검사 로직
		User user = userRepository.findByUserLoginId(lgnId);
		return user != null;
	}

	public UserDto authenticate(String userLoginId, String password) { // 사용자 인증
		User user = userRepository.findByUserLoginId(userLoginId);
		// 사용자 정보가 없는 경우 인증 실패
		if (user == null) {
			throw new UserNotFoundException("유저정보 없음"); // null 대신 throw로 예외를 던지도록 변경
		}
		// 사용자의 비밀번호와 입력된 비밀번호를 비교하여 일치하는지 확인
		if (!user.getPassword().equals(password)) {
			throw new UserNotFoundException("비밀번호가 맞지 않음");
		}

		// 인증된 사용자 정보를 UserDto로 변환하여 반환
		UserDto userDto = new UserDto();
		userDto.setUserLoginId(user.getUserLoginId());
		userDto.setUserNm(user.getUserNm());
		userDto.setPassword(user.getPassword());
		userDto.setSex(user.getSex());
		userDto.setDateOfBirth(user.getDateOfBirth());
		userDto.setHintAnswer(user.getHintAnswer());
		return userDto;
	}

	public boolean isUserExisted(String userLoginId) {
		User user = userRepository.findByUserLoginId(userLoginId);

		if (user == null)
			return false;
		else
			return true;
	}

	public UserDto findUserByHint(String userLoginId, Integer hintSn, String hintAnswer) {
		// 로그인 아이디와 힌트 SN을 사용하여 유저를 조회
		User user = userRepository.findByUserLoginId(userLoginId);

		// 유저가 없는 경우 예외
		if (user == null) {
			throw new UserNotFoundException("User not found with login ID: " + userLoginId);
		}

		// 유저가 가진 힌트 SN과 입력된 힌트 SN을 비교
		if (user.getPswdHintSn().getPswdHintSn() != hintSn) {
			throw new IllegalArgumentException("Invalid hint for user: " + userLoginId);
		}

		// 힌트 답변이 일치하는지 확인합니다.
		if (!user.getHintAnswer().equals(hintAnswer)) {
			throw new IllegalArgumentException("Incorrect hint answer for user: " + userLoginId);
		}

		// 유저 정보를 UserDto 객체로 변환하여 반환
		UserDto userDto = new UserDto();
		userDto.setUserLoginId(user.getUserLoginId());
		userDto.setUserNm(user.getUserNm());
		userDto.setPassword(user.getPassword());
		userDto.setSex(user.getSex());
		userDto.setDateOfBirth(user.getDateOfBirth());
		userDto.setHintAnswer(user.getHintAnswer());

		return userDto;
	}

	@Transactional
	public boolean updateUserPassword(String userLoginId, String newPassword) {
		// 사용자를 검색
		User user = userRepository.findByUserLoginId(userLoginId);
		System.out.println("업데이트할 유저 비밀번호 " + user.getPassword());
		if (user != null) {
			// 새로운 비밀번호로 업데이트
			user.setPassword(newPassword);
			System.out.println("디비에 넣을 비밀번호 " + user.getPassword());
			userRepository.saveAndFlush(user); // 변경된 엔티티를 즉시 저장
			System.out.println(userRepository.saveAndFlush(user));
			return true;
		} else {
			return false; // 사용자가 존재하지 않는 경우
		}
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}

	public User getUserByLoginId(String userLoginId) {
		return userRepository.findByUserLoginId(userLoginId);
	}
}