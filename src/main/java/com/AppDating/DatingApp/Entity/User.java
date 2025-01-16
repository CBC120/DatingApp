package com.AppDating.DatingApp.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String gender;
    @Column
    private int age;

    @ElementCollection
    private List<String> interests;

	public User() {
		super();
	}

	public User(String name, String gender, int age, List<String> interests) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.interests = interests;
	}

	public User(Long id, String name, String gender, int age, List<String> interests) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.interests = interests;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}
    
    
    

    // Getters, Setters, Constructors
}
