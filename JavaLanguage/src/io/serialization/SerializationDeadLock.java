package io.serialization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializationDeadLock {
	
	private class Company{
		List<Division> divisions = new ArrayList<>();
		Company(){
			buildCompany();
		}
		
		private void buildCompany(){
			Division division = new Division();
			division.hireEmployees();
		}
	}

	private class Employee implements Serializable {
		private Division division;

		public Employee(Division division) {
			this.division = division;
		}

		private synchronized void writeObject(java.io.ObjectOutputStream s)
				throws java.io.IOException {
			s.defaultWriteObject();
			s.writeObject(division);
		}

	}

	private class Division implements Serializable {
		List<Employee> employees = new ArrayList<>();

		public void hireEmployees() {
			employees.add(new Employee(this));
		}

		private synchronized void writeObject(java.io.ObjectOutputStream s)
				throws java.io.IOException {
			s.defaultWriteObject();
			s.writeInt(employees.size());
			for(Employee e: employees){
				s.writeObject(e);
			}
		}
	}

}
