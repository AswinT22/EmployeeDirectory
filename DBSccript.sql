CREATE SCHEMA `employee_directory` ;

CREATE TABLE `employee_directory`.`employee` (
  `id` INT  AUTO_INCREMENT,
  `name` VARCHAR(80) ,
  `dob` DATE NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `salary` BIGINT(20) NOT NULL,
  `department` VARCHAR(7) NOT NULL,
  PRIMARY KEY (`id`, `name`));
  
  
  /*  
  
  Most query opereation is dependant on department column 
  
  */
  
  
CREATE INDEX idx_department
ON `employee_directory`.`employee` (department);

/*

Dateset is small creating a department table and fetching values using foreign key relation is a overhead 

There will no advantages in creating indexes for (department,salary) and (department,age)


*/


INSERT INTO  `employee_directory`.`employee`(`name`,`dob`,`gender`,`salary`,`department`) 
values('Adam','1988-07-15','Male',60000,'IT')
  
  
