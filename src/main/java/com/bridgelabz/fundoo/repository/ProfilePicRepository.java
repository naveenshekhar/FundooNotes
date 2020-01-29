package com.bridgelabz.fundoo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.model.ProfilePic;

@Repository
public interface ProfilePicRepository extends JpaRepository<ProfilePic, Long> {

	@Modifying
	@Transactional
	@Query(value = "delete from profile_pic where user_id=?", nativeQuery = true)
	void delete(ProfilePic profile);

	@Modifying
	@Transactional
	@Query(value = "insert into profile_pic(profile_pic_name,user_id) values(?,?)",nativeQuery = true)
	void saveData(String profileName,Long userId);

}
