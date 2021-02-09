# Esame di Ingegneria del Software - Progetto MentCare
## Studente: Danieli Giovanni - VR463656


![Logo of Mentcare](./readmeImages/logo_small.png)

#
## Sommario
[1. Requisiti](#requisiti)\
[2. Modulo scelto](#modulo-scelto)\
[3. Scenari](#scenari)\
[4. Use case diagram](#use-case-diagram)\
[5. Class diagram](#class-diagram)\
[6. Sequence diagram](#sequence-diagram)\
[7. Activity diagram](#activity-diagram)

#
## Requisiti
Bisogna sviluppare un sistema informativo che raccoglie i dati dei pazienti in cura presso strutture per la salute mentale. 
I dati raccolti contengono informazioni riguardo ai pazienti (cartelle cliniche), i vari trattamenti che ricevono e vari 
report(es: visite cliniche). Questi dati vengono salvati all'interno di un Database centralizzato, accessibile da host remoti 
(laptop e smartphone) previa autenticazione. Queste informazioni sono confidenziali e non possono essere divulgate ad altri 
se non il personale medico o il paziente stesso.

I pazienti non richiedono ospedalizzazione, ma devono recarsi periodicamente ad una clinica mentale per incontrare un dottore 
con una profonda conoscenza dei loro problemi. Per facilitare i pazienti, le cliniche non sono solo all'interno degli ospedali, 
ma anche in centri medici locali o centri comunitari. A causa del loro stato di salute mentale, alcuni pazienti potrebbero 
essere irrazionali, ovvero potrebbero:
- Perdere medicinali e/o prescrizioni (anche volontariamente)
- Perdersi in strutture cliniche (di colpo)
- Dimenticarsi di appuntamenti con i dottori

Di conseguenza si rende necessario memorizzare un contatto del paziente stessa (se relativamente autonomo), oppure di un tutore o parente.

Inoltre alcune malattie mentali potrebbero essere pericolose per i pazienti stessi o altri. In questo caso il sistema deve 
generare un alert per informare lo staff medico riguardo a casi pericolosi. Inoltre bisogna loggare tutte le decisioni prese 
per questi casi pericolosi, nel caso fossero necessarie alle forze dell'ordine oppure per processi giuridici.

Il sistema inoltre deve generare vari report mensile che mostrano i pazienti in cura in ogni clinica, il numero di pazienti 
che sono entrati o hanno lasciato il sistema di cura, i farmaci somministrati e il relativo costo.

Gli utenti del sistema si dividono in due gruppi:
- Staff Medico
    - Dottori: possono accedere alle cartelle cliniche dei pazienti e aggiornarne i relativi dati, prescrivere farmaci e 
      trattamenti, visualizzare un riepilogo sui problemi del paziente selezionato e vedere i trattamenti prescritti. 
      Possono accedere alla loro agenda al fine di visionare i futuri appuntamenti.
    - Infermieri: possono accedere alle cartelle cliniche dei pazienti, somministrare i relativi farmaci prescritti ed 
      inserire nel sistema la somministrazione.
    - Assistenti sanitari: sono infermieri che visitano i pazienti nelle loro case.
    
  
- Staff non Medico
    - Receptionist, si occupano degli appuntamenti. Devono poter accedere alle agende dei medici al fine di aggiungere, 
      modificare o rimuovere i vari appuntamenti.
    - Addetti ai record medici, mantengono i record di sistema.
    - Addetti amministrativi, possono accedere ai report amministrativi, aggiungere pazienti al sistema con le relative pratiche.
    

## Modulo scelto

Come richiesto dal Professore verrà sviluppato un singolo componente del progetto Mentcare, ovvero verrà sviluppato il 
modulo riguardo al medico.

## Scenari 
### 1. Visualizzare la lista dei pazienti
**Assunzione iniziale:** Un medico deve visualizzare la lista dei pazienti in cura. Il medico deve essersi già 
autenticato al sistema attraverso un browser e trovarsi sulla home page (/).\
**Normalità:** Il medico deve cliccare sul link "Visualizza la lista dei pazienti" dalla home page (/). Verrà reindirizzato 
alla pagina "Lista pazienti" (/lista-pazienti) dove troverà una tabella con tutti i pazienti in cura presso il sistema di cliniche.\
**Stato del sistema a scenario completato:** Il medico è autenticato e si trova sulla pagina "Lista pazienti" (/lista-pazienti).

### 2. Visualizzare i prossimi appuntamenti
**Assunzione iniziale:** Un medico deve visualizzare i prossimi appuntamenti con i pazienti. Il medico deve essersi già 
autenticato al sistema attraverso un browser e trovarsi sulla home page (/).\
**Normalità:** Il medico deve cliccare sul link "La mia agenda" dalla home page (/). Verrà reindirizzato alla pagina 
"Agenda" (/agenda) dove sarà presente una tabella con i suoi prossimi appuntamenti. Ogni riga della tabella 
conterrà la data e l'ora della visita, nome e cognome del paziente (sarà un link che porta alla cartella clinica del paziente) 
accompagnato da un warning sign (&#x26A0;) se questo presenta comportamenti pericolosi.\
**Stato del sistema a scenario completato:** Il medico è autenticato e si trova sulla pagina "Agenda" (/agenda).

### 3. Aggiornare la cartella clinica di un paziente
**Assunzione iniziale:** Un medico deve poter aggiornare la cartella clinica di un paziente. Il medico deve essersi già 
autenticato al sistema attraverso un browser e trovarsi sulla home page (/).\
**Normalità:** Il medico deve cliccare sul link "Visualizza la lista dei pazienti" dalla home page (/). Verrà reindirizzato 
alla pagina "Lista pazienti" (/lista-pazienti) dove troverà una tabella con tutti i pazienti in cura presso il sistema di 
cliniche. Una volta individuato il paziente la cui cartella clinica è da modificare, scorrendo sulla riga vedrà due bottoni: 
uno Blu con la scritta ("Visualizza cartella") e uno rosso con la scritta ("Modifica cartella"). 
Una volta cliccato su quest'ultimo verranno portati alla pagina "Modifica cartella clinica Mario Rossi" (/modifica-cartella/idpaziente)
(Mario Rossi viene utilizzato come esempio). Qui potrà modificare i vari campi disponibili. Una volta completate le modifiche
troverà due bottoni: "Salva modifiche" oppure "Annulla".
Il primo salverà le modifiche alla cartella clinica e reindirizzerà il medico alla pagina "Lista pazienti" (/lista-pazienti).
Il secondo invece ignorerà le modifiche e reindirizzerà il medico alla pagina "Lista pazienti" (/lista-pazienti).\
**Stato del sistema a scenario completato:** Il medico è autenticato e si trova sulla pagina "Lista pazienti" (/lista-pazienti).

### 4. Prescrivere un farmaco
**Assunzione iniziale:** Un medico deve prescrivere un farmaco ad un paziente. Il medico deve essersi già autenticato al
sistema attraverso un browser e trovarsi sulla home page (/).\
**Normalità:** Il medico deve cliccare sul link "Visualizza la lista dei pazienti" dalla home page (/). Verrà reindirizzato
alla pagina "Lista pazienti" (/lista-pazienti) dove troverà una tabella con tutti i pazienti in cura presso il sistema di 
cliniche.Una volta individuato il paziente la cui cartella clinica è da modificare, scorrendo sulla riga vedrà due bottoni: 
uno Blu con la scritta ("Visualizza cartella") e uno rosso con la scritta ("Modifica cartella").
Una volta cliccato sul primo verranno portati alla pagina "Cartella clinica di Mario Rossi" (/cartella-clinica/idpaziente) 
(Mario Rossi viene utilizzato come esempio) dove il medico visualizzerà la cartella clinica del paziente selezionato. 
Oltre alla cartella clinica ci saranno due bottoni: "Nuova visita" e "Nuova prescrizione". Cliccato il pulsante "Nuova prescrizione",
il medico verrà portato alla pagina "Nuova prescrizione" (/nuova-prescrizione/idpaziente) dove compilerà un form per una nuova prescrizione. 
Una volta compilato il form potrà salvare la prescrizione cliccando su "Inserisci" oppure annullarla cliccando su "Annulla".
In entrambi i casi verrà reindirizzato alla cartella clinica del paziente.
**Stato del sistema a scenario completato:** Il medico è autenticato e si trova sulla pagina "Cartella clinica di Mario 
Rossi" (/cartella-clinica/idpaziente).

### 5. Inserire una visita

#
## Use case diagram
### Use case del Medico
![Use case Medico](./readmeImages/Use%20Case%20Medico.svg)

#
## Class diagram
![Class Diagram](./readmeImages/ClassDiagram.svg)

#
## Sequence Diagram
### Il seguente sequence diagram presenta la sequenza per aggiungere una prescrizione ad un paziente
![Sequence Diagram](./readmeImages/SequenceDiagram.svg)

#
## Activity Diagram
### Il seguente activity diagram presenta il flusso delle azioni per modificare la cartella di un paziente
![Activity Diagram](./readmeImages/ActivityDiagram.svg)
