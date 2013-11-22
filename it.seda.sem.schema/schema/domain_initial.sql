insert into cliente (nome, descrizione, regitrazione)
select old.cliente, old.descrizione, timestamp(old.data_in, old.time_in) from sem.clienti old;


insert into server (nome, descrizione, ip, regitrazione)
select old.servente, old.descrizione, old.indirizzo_ip, timestamp(old.data_in, old.time_in) from sem.serventi old;
