package com.students;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ClassesRepository extends CrudRepository<Class, Long> {
	public List<Class> findByLabel(String label);
}
