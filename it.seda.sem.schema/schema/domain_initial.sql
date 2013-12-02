insert into cliente (nome, descrizione, regitrazione)
select old.cliente, old.descrizione, timestamp(old.data_in, old.time_in) from sem.clienti old;


insert into server (nome, descrizione, ip, registrazione)
select old.servente, old.descrizione, old.indirizzo_ip, timestamp(old.data_in, old.time_in) from sem.serventi old;


insert into societa (nome, descrizione)
select societa_esterno, societa_esterno from sem.responsabili
where esterno='S'
GROUP BY societa_esterno;
