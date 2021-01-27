# Esame di Ingegneria del Software - Progetto MentCare
## Studente: Danieli Giovanni - VR


![Logo of Mentcare](./readmeImages/logo_small.png)

#
# Sommario
## [1. Requisiti](#requisiti)
## [2. Scenari](#scenari)


## Requisiti
Bisogna sviluppare un sistema informativo che raccoglie i dati dei pazienti in cura presso strutture per la salute mentale. I dati raccolti contengono informazioni riguardo ai pazienti e i vari trattamenti che ricevono. Questi dati vengono salvati all'interno di un Database centralizzato, accessibile da host remoti (laptop e smartphone) previa autenticazione. Queste informazioni sono confidenziali e non possono essere divulgate ad altri se non il personale medico o il paziente stesso.

I pazienti non richiedono ospedalizzazione, ma devono recarsi periodicamente ad una clinica mentale per incontrare un dottore con una profonda conoscenza dei loro problemi. Per facilitare i pazienti, le cliniche non sono solo all'interno degli ospedali, ma anche in centri medici locali o centri comunitari. A causa del loro stato di salute mentale, alcuni pazienti potrebbero essere irrazionali, ovvero potrebbero:
- Perdere medicinali e/o prescrizioni (anche volontariamente)
- Perdersi in strutture cliniche (di colpo)
- Dimenticarsi di appuntamenti con i dottori

Di conseguenza si rende necessario memorizzare un contatto del paziente stessa (se relativamente autonoma), oppure di un tutore o parente.

Inoltre alcune malattie mentali potrebbero essere pericolose per i pazienti stessi o altri. In questo caso il sistema deve generare un alert per informare lo staff medico riguardo a casi pericolosi. Inoltre bisogna loggare tutte le decisioni prese per questi casi pericolosi, nel caso fossero necessarie alle forze dell'ordine oppure per processi giuridici.

Gli utenti del sistema si dividono in due gruppi:
- Staff Medico
    - Dottori
    - Infermieri
    - Assistenti sanitari
    

- Staff non Medico
    - Receptionist, si occupano degli appuntamenti
    - Addetti ai record medici, mantengono i record di sistema
    - Addetti amministrativi, generano report
    

## Scenari 
