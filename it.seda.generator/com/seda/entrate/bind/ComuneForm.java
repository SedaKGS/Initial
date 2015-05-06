package com.seda.entrate.bind;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;



public class ComuneForm{

        private java.lang.String societa;
        private java.lang.String codiceBelfiore;
        private java.lang.String descrizioneComune;
        private java.util.Date dataInizioValidita;
        private java.lang.String flagSospensione;
        private java.lang.String flagStatoEstero;
        private java.lang.String siglaProvincia;
        private java.lang.String cap;
        private java.lang.String codiceRegione;
        private java.lang.String codiceNazione;
        private java.lang.String codiceIstat;
        private java.lang.String codiceCatastale;
        private java.lang.String codiceCab;
        private java.lang.String conservatoria;
        private java.lang.String codiceTribunale;
        private java.util.Date dataCessazione;
        private java.lang.String operInserimento;
        private java.util.Date dataInserimento;
        private java.lang.String operVariazione;
        private java.util.Date dataVariazione;
        private String esito;
        
        public String getEsito(){
           return this.esito;
        }
        
        public void setEsito(String esito){
           this.esito=esito;
        }
            
        public  java.lang.String getSocieta(){
           return this.societa;
        }
    
        public void setSocieta(java.lang.String societa){
           this.societa=societa;
        }
        public  java.lang.String getCodiceBelfiore(){
           return this.codiceBelfiore;
        }
    
        public void setCodiceBelfiore(java.lang.String codiceBelfiore){
           this.codiceBelfiore=codiceBelfiore;
        }
        public  java.lang.String getDescrizioneComune(){
           return this.descrizioneComune;
        }
    
        public void setDescrizioneComune(java.lang.String descrizioneComune){
           this.descrizioneComune=descrizioneComune;
        }

        public  java.util.Date getDataInizioValidita(){
           return this.dataInizioValidita;
        }
    
        public void setDataInizioValidita(java.util.Date dataInizioValidita){
           this.dataInizioValidita=dataInizioValidita;
        }
        public  java.lang.String getFlagSospensione(){
           return this.flagSospensione;
        }
    
        public void setFlagSospensione(java.lang.String flagSospensione){
           this.flagSospensione=flagSospensione;
        }
        public  java.lang.String getFlagStatoEstero(){
           return this.flagStatoEstero;
        }
    
        public void setFlagStatoEstero(java.lang.String flagStatoEstero){
           this.flagStatoEstero=flagStatoEstero;
        }
        public  java.lang.String getSiglaProvincia(){
           return this.siglaProvincia;
        }
    
        public void setSiglaProvincia(java.lang.String siglaProvincia){
           this.siglaProvincia=siglaProvincia;
        }
        public  java.lang.String getCap(){
           return this.cap;
        }
    
        public void setCap(java.lang.String cap){
           this.cap=cap;
        }
        public  java.lang.String getCodiceRegione(){
           return this.codiceRegione;
        }
    
        public void setCodiceRegione(java.lang.String codiceRegione){
           this.codiceRegione=codiceRegione;
        }
        public  java.lang.String getCodiceNazione(){
           return this.codiceNazione;
        }
    
        public void setCodiceNazione(java.lang.String codiceNazione){
           this.codiceNazione=codiceNazione;
        }
        public  java.lang.String getCodiceIstat(){
           return this.codiceIstat;
        }
    
        public void setCodiceIstat(java.lang.String codiceIstat){
           this.codiceIstat=codiceIstat;
        }
        public  java.lang.String getCodiceCatastale(){
           return this.codiceCatastale;
        }
    
        public void setCodiceCatastale(java.lang.String codiceCatastale){
           this.codiceCatastale=codiceCatastale;
        }
        public  java.lang.String getCodiceCab(){
           return this.codiceCab;
        }
    
        public void setCodiceCab(java.lang.String codiceCab){
           this.codiceCab=codiceCab;
        }
        public  java.lang.String getConservatoria(){
           return this.conservatoria;
        }
    
        public void setConservatoria(java.lang.String conservatoria){
           this.conservatoria=conservatoria;
        }
        public  java.lang.String getCodiceTribunale(){
           return this.codiceTribunale;
        }
    
        public void setCodiceTribunale(java.lang.String codiceTribunale){
           this.codiceTribunale=codiceTribunale;
        }
        public  java.util.Date getDataCessazione(){
           return this.dataCessazione;
        }
    
        public void setDataCessazione(java.util.Date dataCessazione){
           this.dataCessazione=dataCessazione;
        }
        public  java.lang.String getOperInserimento(){
           return this.operInserimento;
        }
    
        public void setOperInserimento(java.lang.String operInserimento){
           this.operInserimento=operInserimento;
        }
        public  java.util.Date getDataInserimento(){
           return this.dataInserimento;
        }
    
        public void setDataInserimento(java.util.Date dataInserimento){
           this.dataInserimento=dataInserimento;
        }
        public  java.lang.String getOperVariazione(){
           return this.operVariazione;
        }
    
        public void setOperVariazione(java.lang.String operVariazione){
           this.operVariazione=operVariazione;
        }
        public  java.util.Date getDataVariazione(){
           return this.dataVariazione;
        }
    
        public void setDataVariazione(java.util.Date dataVariazione){
           this.dataVariazione=dataVariazione;
        }
    
    
        public String toString(){
           return " FormComune [societa=" +  societa + 
	        " , codiceBelfiore=" + codiceBelfiore + 
	        " , descrizioneComune=" + descrizioneComune + 
	        " , dataInizioValidita=" + dataInizioValidita + 
	        " , flagSospensione=" + flagSospensione + 
	        " , flagStatoEstero=" + flagStatoEstero + 
	        " , siglaProvincia=" + siglaProvincia + 
	        " , cap=" + cap + 
	        " , codiceRegione=" + codiceRegione + 
	        " , codiceNazione=" + codiceNazione + 
	        " , codiceIstat=" + codiceIstat + 
	        " , codiceCatastale=" + codiceCatastale + 
	        " , codiceCab=" + codiceCab + 
	        " , conservatoria=" + conservatoria + 
	        " , codiceTribunale=" + codiceTribunale + 
	        " , dataCessazione=" + dataCessazione + 
	        " , operInserimento=" + operInserimento + 
	        " , dataInserimento=" + dataInserimento + 
	        " , operVariazione=" + operVariazione + 
	        " , dataVariazione=" + dataVariazione + 
        "]";
        }
    





}
