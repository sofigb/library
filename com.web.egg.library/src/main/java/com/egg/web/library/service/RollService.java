package com.egg.web.library.service;
import java.util.List;
import com.egg.web.library.entity.Roll;
import com.egg.web.library.repository.RollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RollService {

    @Autowired
    private RollRepository rollRep;

    @Transactional
    public void createRol(String name) {

        Roll roll = new Roll();
        roll.setName(name);

        rollRep.save(roll);
    }

    @Transactional(readOnly = true)
    public List<Roll> showAll() {
        return rollRep.findAll();
    }
}
