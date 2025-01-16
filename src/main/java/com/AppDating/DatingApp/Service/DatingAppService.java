package com.AppDating.DatingApp.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.AppDating.DatingApp.Entity.User;
import com.AppDating.DatingApp.Repository.UserRepository;
@Service
public class DatingAppService {

	    private final UserRepository userRepository;

	     public DatingAppService(UserRepository userRepository){
			// TODO Auto-generated constructor stub
	    	this.userRepository = userRepository;
		}

	    public List<User> findMatches(Long userId, int topN) {
	        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	        return userRepository.findAll().stream()
	                .filter(u -> !u.getId().equals(user.getId())) // Exclude the current user
	                .sorted(Comparator.comparing((User u) -> u.getGender().equals(user.getGender()) ? 1 : 0)
	                        .thenComparing(u -> Math.abs(u.getAge() - user.getAge()))
	                        .thenComparing(u -> calculateInterestMatch(user.getInterests(), u.getInterests()), Comparator.reverseOrder()))
	                .limit(topN)
	                .collect(Collectors.toList());
	    }

	    private int calculateInterestMatch(List<String> interests1, List<String> interests2) {
	        return (int) interests1.stream().filter(interests2::contains).count();
	    }
	}


