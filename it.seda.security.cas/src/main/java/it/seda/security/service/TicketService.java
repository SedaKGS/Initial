/**
 * 
 */
package it.seda.security.service;

import it.seda.jdbc.commons.DataPage;
import it.seda.jdbc.commons.DefaultDataPage;
import it.seda.jdbc.ibatis.RowBoundsHelper;
import it.seda.security.domain.Ticket;
import it.seda.security.persistence.TicketMapper;

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
public class TicketService {

	@Autowired private TicketMapper ticketMapper;

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
	 public DataPage<Ticket> listCustomer(Ticket filter, int pageNumber, int rowsPerPage) {
		 RowBoundsHelper rbh = new RowBoundsHelper(rowsPerPage, pageNumber);
		 int totalrows=ticketMapper.listTicketCount(filter);

		 List<Ticket> ticketList=ticketMapper.listTicket(filter, rbh.buildRowBounds());
		 DataPage<Ticket> ticketPage= new DefaultDataPage<Ticket>(ticketList);
		 rbh.decorate(ticketPage, totalrows);

		 return ticketPage;
	 }
	 
}
