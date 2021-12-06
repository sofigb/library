
package com.egg.web.library.repository;

import com.egg.web.library.entity.Roll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RollRepository extends JpaRepository<Roll, Integer> {
    
}
