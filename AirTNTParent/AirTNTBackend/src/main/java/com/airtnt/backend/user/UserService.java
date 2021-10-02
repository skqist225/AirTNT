package com.airtnt.backend.user;

import java.util.List;
import java.util.NoSuchElementException;

import com.airtnt.backend.address.CountryRepository;
import com.airtnt.common.entity.Country;
import com.airtnt.common.entity.Role;
import com.airtnt.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    public static final int USERS_PER_PAGE = 4;

    @Autowired RoleRepository roleRepo;
    @Autowired CountryRepository countryRepo;
    
    @Autowired UserRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

    public List<User> findAllUsers(){
        return repo.findAll();
    }

    public Page<User> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() :sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum -1, USERS_PER_PAGE, sort);
		
		if(keyword!=null) {
			return repo.findAll(keyword, pageable);
		}
		
		return repo.findAll(pageable);
    }

	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}

    public User save(User user){
        boolean isUpdatingUser = (user.getId() != null);
		
		if(isUpdatingUser) {
			User existingUser = repo.findById(user.getId()).get();
			
			if(user.getPassword().isEmpty()) {
				user.setPassword(existingUser.getPassword());
			} else {
				encodePassword(user);
			}
			
		} else {
			encodePassword(user);			
		}
		
		return repo.save(user);
    }

    public List<Role> listRoles(){
        return (List<Role>) roleRepo.findAll();
    }

    public List<Country> listCountries(){
        return (List<Country>) countryRepo.findAll();
    }

    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = repo.findByEmail(email);
		
		if(userByEmail == null) return true;
		
		boolean isCreatingNew = (id ==null);
		
		if(isCreatingNew) {
			if(userByEmail !=null) return false;
		} else {
			if(userByEmail.getId() != id) {
				return false;
			}
		}
		
		return true;
    }

    public User get(Integer id) throws UserNotFoundException {
		try {
			return repo.findById(id).get();
		}catch(NoSuchElementException ex) {
			throw new UserNotFoundException("Could not find any user with ID " + id);
		}
	}
	
	public void  delete(Integer id) throws UserNotFoundException {
		Long countById =  repo.countById(id);
		if((countById == null || countById == 0)) {
			throw new UserNotFoundException("Could not find any user with ID " + id);
		}
		
		repo.deleteById(id);
	}
	
	public void updateUserEnabledStatus(Integer id, boolean enabled) {
		repo.updateStatus(id, enabled);
	}

	public User findById(Integer id){
		return repo.findById(id).get();
	}
}
