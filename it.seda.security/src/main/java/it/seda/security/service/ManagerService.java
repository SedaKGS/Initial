/**
 * 
 */
package it.seda.security.service;

import it.seda.jdbc.commons.DataPage;
import it.seda.jdbc.commons.DefaultDataPage;
import it.seda.jdbc.ibatis.RowBoundsHelper;
import it.seda.security.domain.Application;
import it.seda.security.domain.Customer;
import it.seda.security.domain.CustomerApplication;
import it.seda.security.domain.CustomerUser;
import it.seda.security.persistence.ManagerMapper;
import it.seda.security.service.exceptions.DuplicateApplicationException;
import it.seda.security.service.exceptions.DuplicateCustomerException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author f.ricci
 *
 */
@Service
@Transactional
public class ManagerService {

	@Autowired private ManagerMapper managerMapper;

	public void insertCustomer(Customer customer) {
		if (managerMapper.existsCustomer(customer.getId())) {
			throw new DuplicateCustomerException(customer.getId());
		}
		managerMapper.insertCustomer(customer);
	}

	 public void deleteCustomer(Customer customer) {
		 managerMapper.deleteCustomer(customer);
	 }
	
	 public void updateCustomer(Customer customer) {
		 managerMapper.updateCustomer(customer);
	 }
	 
	 @Transactional(readOnly=true)
	 public Customer selectCustomer(String customerId) {
		 return managerMapper.selectCustomer(customerId);
	 }
	 
	 @Transactional(readOnly=true)
	 public DataPage<Customer> listCustomer(int pageNumber, int rowsPerPage) {
		 RowBoundsHelper rbh = new RowBoundsHelper(rowsPerPage, pageNumber);
		 int totalrows=managerMapper.listCustomerCount();

		 List<Customer> customerList=managerMapper.listCustomer(rbh.buildRowBounds());
		 DataPage<Customer> customerPage= new DefaultDataPage<Customer>(customerList);
		 rbh.decorate(customerPage, totalrows);

		 return customerPage;
	 }
	 
	 public void deleteCustomerUser(CustomerUser customerUser) {
		 managerMapper.deleteCustomerUser(customerUser);
	 }
	 
	 public void insertApplication(Application application) {
		 if (managerMapper.existsApplicationByName(application.getName())) {
			throw new DuplicateApplicationException(application.getName());
		}
		 managerMapper.insertApplication(application);
	 }
	 
	 public void updateApplication(Application application) {
		 if (managerMapper.existsApplicationByName(application.getName())) {
			 throw new DuplicateApplicationException(application.getName());
		 }
		 managerMapper.updateApplication(application);
	 }
	 
	 
	 @Transactional(readOnly=true)
	 public Application selectApplication(String applicationId) {
		 return managerMapper.selectApplication(applicationId);
	 }

	 /* Metodo che dato il nome della applicazione in output mi restituisce l'oggetto applicazione da cui poi recuparare l' Id*/
	 @Transactional(readOnly=true)
	 public Application selectApplicationIdByName(String applicationName) {
		 return managerMapper.selectApplicationIdByName(applicationName);
	 }
	 
	 @Transactional(readOnly=true)
	 public DataPage<Application> listApplication(int pageNumber, int rowsPerPage) {
		 RowBoundsHelper rbh = new RowBoundsHelper(rowsPerPage, pageNumber);
		 int totalrows=managerMapper.listApplicationCount();

		 List<Application> applicationList=managerMapper.listApplication(rbh.buildRowBounds());
		 DataPage<Application> applicationPage= new DefaultDataPage<Application>(applicationList);
		 rbh.decorate(applicationPage, totalrows);

		 return applicationPage;		 
	 }
	 
	 public void insertCustomerApplication(CustomerApplication customerApplication) {
		 managerMapper.insertCustomerApplication(customerApplication);
	 }
	 
	 public void updateCustomerApplication(CustomerApplication customerApplication) {
		 managerMapper.updateCustomerApplication(customerApplication);
	 }
	 
	 public void deleteCustomerApplication(CustomerApplication customerApplication) {
		 managerMapper.deleteCustomerApplication(customerApplication);
	 }
	 
	 @Transactional(readOnly=true)
	 public DataPage<CustomerApplication> listCustomerApplication(String customerId, int pageNumber, int rowsPerPage) {
		 RowBoundsHelper rbh = new RowBoundsHelper(rowsPerPage, pageNumber);
		 int totalrows=managerMapper.listCustomerApplicationCount(customerId);

		 List<CustomerApplication> customerApplicationList=managerMapper.listCustomerApplication(customerId, rbh.buildRowBounds());
		 DataPage<CustomerApplication> customerApplicationPage= new DefaultDataPage<CustomerApplication>(customerApplicationList);
		 rbh.decorate(customerApplicationPage, totalrows);

		 return customerApplicationPage;		 
	 }
}
