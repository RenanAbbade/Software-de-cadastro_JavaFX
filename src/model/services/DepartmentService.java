package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {
	
	public List<Department> findAll(){
		List<Department> list = new ArrayList<>();
		//MOCK - Quando vou retornar uma previa dos dados
		list.add(new Department(1, "Books"));
		list.add(new Department(2, "Tech"));
		return list;
		
	}

}
