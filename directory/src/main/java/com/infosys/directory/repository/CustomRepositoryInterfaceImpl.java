package com.infosys.directory.repository;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.infosys.directory.entity.Employee;
import com.infosys.directory.exceptions.InvalidValueException;

@Repository
public class CustomRepositoryInterfaceImpl   {


	Logger logger = LoggerFactory.getLogger(this.getClass());
   
	@PersistenceContext
	private EntityManager entityManager;

	public List<Employee> getData(String metric, String value) throws InvalidValueException {
		logger.info("in Query");
		try {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> query= cb.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);
		metric=metric.toLowerCase();
		
		logger.info(metric+"  "+value);
		switch (metric)
		{
		
		//name is like
		case "name":
		
			query.select(root).where(cb.like(root.get(metric),"%"+(String)value+"%"));
			break;
			
		case "gender":
			
			query.select(root).where(cb.equal(root.get(metric),(String)value));
			break;
		case "salary":
			query.select(root).where(cb.greaterThanOrEqualTo(root.get(metric),new BigInteger(value)));
			break;
			
		case "dateofbirth":
				metric="dateOfBirth";
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dob= LocalDate.parse(value,formatters);
				query.select(root).where(cb.greaterThanOrEqualTo(root.get(metric), (LocalDate) dob));
				break;

	}
		return entityManager.createQuery(query).getResultList(); 
		
		} 
		catch (Exception e) {
			logger.info("in  exc");
			e.printStackTrace();
			throw new InvalidValueException("Not a Valid query Entry");
		}
}
	
}
