package aula20201106;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;

import aula20201027.BaseEntity;

public class BaseController<ENTITY extends BaseEntity, REPOSITORY extends JpaRepository<ENTITY, String>> {
    @Autowired
    private REPOSITORY repo;

    @GetMapping
    public List<ENTITY> getAll() {
        return repo.findAll();
    }

}
