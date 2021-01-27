# Esame di Ingegneria del Software - Progetto MentCare
## Studente: Danieli Giovanni - VR


![Logo of Mentcare](./readmeImages/logo_small.png)

#
## Sommario
[1. Requisiti](#requisiti)\
[2. Modulo scelto](#modulo-scelto)\
[3. Scenari](#scenari)

#
## Requisiti
Bisogna sviluppare un sistema informativo che raccoglie i dati dei pazienti in cura presso strutture per la salute mentale. I dati raccolti contengono informazioni riguardo ai pazienti (cartelle cliniche), i vari trattamenti che ricevono e vari report(es: visite cliniche). Questi dati vengono salvati all'interno di un Database centralizzato, accessibile da host remoti (laptop e smartphone) previa autenticazione. Queste informazioni sono confidenziali e non possono essere divulgate ad altri se non il personale medico o il paziente stesso.

I pazienti non richiedono ospedalizzazione, ma devono recarsi periodicamente ad una clinica mentale per incontrare un dottore con una profonda conoscenza dei loro problemi. Per facilitare i pazienti, le cliniche non sono solo all'interno degli ospedali, ma anche in centri medici locali o centri comunitari. A causa del loro stato di salute mentale, alcuni pazienti potrebbero essere irrazionali, ovvero potrebbero:
- Perdere medicinali e/o prescrizioni (anche volontariamente)
- Perdersi in strutture cliniche (di colpo)
- Dimenticarsi di appuntamenti con i dottori

Di conseguenza si rende necessario memorizzare un contatto del paziente stessa (se relativamente autonoma), oppure di un tutore o parente.

Inoltre alcune malattie mentali potrebbero essere pericolose per i pazienti stessi o altri. In questo caso il sistema deve generare un alert per informare lo staff medico riguardo a casi pericolosi. Inoltre bisogna loggare tutte le decisioni prese per questi casi pericolosi, nel caso fossero necessarie alle forze dell'ordine oppure per processi giuridici.

Il sistema inoltre deve generare vari report mensile che mostrano i pazienti in cura in ogni clinica, il numero di pazienti che sono entrati o hanno lasciato il sistema di cura, i farmaci somministrati e il relativo costo.

Gli utenti del sistema si dividono in due gruppi:
- Staff Medico
    - Dottori: possono accedere alle cartelle cliniche dei pazienti e aggiornarne i relativi dati, prescrivere farmaci e trattamenti, visualizzare un riepilogo sui problemi del paziente selezionato e vedere i trattamenti prescritti. 
      Possono accedere alla loro agenda al fine di visionare i futuri appuntamenti.
    - Infermieri: possono accedere alle cartelle cliniche dei pazienti, somministrare i relativi farmaci prescritti ed inserire nel sistema la somministrazione.
    - Assistenti sanitari: sono infermieri che visitano i pazienti nelle loro case.
    
  
- Staff non Medico
    - Receptionist, si occupano degli appuntamenti. Devono poter accedere alle agende dei medici al fine di aggiungere, modificare o rimuovere i vari appuntamenti.
    - Addetti ai record medici, mantengono i record di sistema.
    - Addetti amministrativi, possono accedere ai report amministrativi, aggiungere pazienti al sistema con le relative pratiche.
    

## Modulo scelto

Come richiesto dal Professore verrà sviluppato un singolo componente del progetto Mentcare, ovvero verrà sviluppato il modulo riguardo al medico.

## Scenari 
