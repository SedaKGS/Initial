/**
 * 
 */
package it.seda.security.service;

import it.seda.jdbc.commons.DataPage;
import it.seda.jdbc.commons.DefaultDataPage;
import it.seda.jdbc.ibatis.RowBoundsHelper;
import it.seda.security.domain.Ticket;
import it.seda.security.persistence.ManagerMapper;
import it.seda.security.persistence.TicketMapper;
import it.seda.security.service.exceptions.ApplicationNotFoundException;

import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author f.ricci
 *
 */
@Service
@Transactional
public class TicketService {

	@Autowired private TicketMapper ticketMapper;
	@Autowired private ManagerMapper managerMapper;
	
	/**
	 * 
	 * @return the new generated ticket
	 * @exception ApplicationNotFoundException if the application id was not found
	 */
	public Ticket generate(String username, String applicationId) {
		if (!managerMapper.existsApplication(applicationId)) {
			throw ApplicationNotFoundException.idNotFound(applicationId); 
		}
		DateTime dateTime = new DateTime();

		Ticket ticket = new Ticket();
		ticket.setId(UUID.randomUUID().toString());
		ticket.setUsername(username);
		ticket.setApplicationId(applicationId);
		ticket.setCreationDate(dateTime.toDate());
		ticket.setExpirationDate(dateTime.plusMinutes(1).plusSeconds(30).toDate());
		
		insertTicket(ticket);
		
		return ticket;
	}
	
	public void insertTicket(Ticket ticket) {
		ticketMapper.insertTicket(ticket);
	}

	 public void deleteTicket(Ticket ticket) {
		 ticketMapper.deleteTicket(ticket);
	 }
	
	 @Transactional(readOnly=true)
	 public Ticket selectTicket(String ticketId) {
		 return ticketMapper.selectTicket(ticketId);
	 }
	 
	 @Transactional(readOnly=true)
	 public DataPage<Ticket> listTicket(Ticket filter, int pageNumber, int rowsPerPage) {
		 RowBoundsHelper rbh = new RowBoundsHelper(rowsPerPage, pageNumber);
		 int totalrows=ticketMapper.listTicketCount(filter);

		 List<Ticket> ticketList=ticketMapper.listTicket(filter, rbh.buildRowBounds());
		 DataPage<Ticket> ticketPage= new DefaultDataPage<Ticket>(ticketList);
		 rbh.decorate(ticketPage, totalrows);

		 return ticketPage;
	 }
	 
}
