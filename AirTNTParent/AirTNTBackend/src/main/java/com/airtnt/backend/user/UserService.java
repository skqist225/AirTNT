package com.airtnt.backend.user;

import java.util.List;

import com.airtnt.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    public static final int USERS_PER_PAGE = 4;
    
    @Autowired UserRepository repo;

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
}
