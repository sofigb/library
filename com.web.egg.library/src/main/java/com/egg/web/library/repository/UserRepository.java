
package com.egg.web.library.repository;


import com.egg.web.library.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    
  Optional <Users> findByUsername(String username);  
    
}
