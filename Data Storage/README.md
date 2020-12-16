# Data Storage
* [Gestire i file](#gestire-i-file)
* [Memorizzare informazioni con SharedPreferences](#memorizzare-informazioni-con-sharedpreferences)
* [Database e SQLite](#database-e-sqlite)
## Gestire i file
La prima definizione da imparare è quella tra lo spazio interno all'applicazione e quello esterno.  
Ogni app ha a disposizione uno spazio disco, detto Storage interno (o Internal Storage) che risiede in una parte del filesystem e a cui solo l'applicazione dovrebbe accedere. Il percorso in Android che
porta in questa poszione è /data/data/package_java_della_applicazione/files.  
Per External Storage, Android sceglie una poszione in cui collocare tutto un insieme di risorse che sono di utilità e consultazione generale nel dispositivo (musica, suonerie, e così via). 
### Storage interno
Per accedere allo storage interno si usano per lo più due metodi, entrambi appartenenti al Context:
* `FileInputStream **openFileInput**(String filename)`: apre un file in lettura. Come parametro in input viene passato il nome del file. Non serve specificae il percorso in quanto sarà
obbligatoriamente quello messo a disposizione dallo storage interno.
* `FileOutputStream **openFileOutput**(String filename, int mode)`: apre uno stream in scrittura anche questo nello storage interno. Per il nomde del file, valo quanto detto per l'input. 
A proposito del secondo parametro, si può impostare alla costante Context.MODE_APPEND per concatenare i nuovi contenuti a quelli già esistenti nel file.  
  
Notare che sono disponibili anche due costanti *MODE_WORLD_REDABLE* e *MODE_WORLD_WRITEABLE* che servono a rendere il file accessibile anche al di fuori dello storage intero. Questi valori sono stati deèrecati in quanto non in linea con il principio di riservatezza dei dati interni all'applicazione.
### Storage esterno
Anche per lo Storage esterno, le operazioni su filesystem si svolgono mediante Stream e le consuete classi Java. L'accesso avverrà mediante la classe **Environment**.  
La prima operazione da svolgere è controllare lo stato del supporto. Lo si fa con il metodo statico `String Environment.getExternalStorageState()`.  
La stringa restituita può avere una molteplicità di valori, tutti associati a costanti della classe Environment, eccone alcuni:
* *Environment.MEDIA_MOUNTER*: il caso migliore. Supporto disponibile in lettura/scrittura.
* *Environment.MEDIA_MOUNTER_READ_ONLY*: il supporto è disponibile ma solo in lettura.  
  
Una volta controllato lo stato del supporto e del relativo filesystem, + arrivato il momento di lavorarci direttamente. L'accesso alla cartella root dello Storage esterno primario si ottiene con il metodo statico: `File getExternalStorageDirectory()`.  
E' sconsigliabile salvare file direttamente nella cartella principale dello storage esterno, normalmente esso contiene delle cartelle associate alle principali tipologie di contenuti.  
## Memorizzare informazioni con SharedPreferences
L'utilizzo dei file come collocazione di dati persistenti è molto duttile. Ai fini della programmazione Android, la possibilità di salvare dati grezzi mediante Stream apre la strada a moltissime possibilità. 
Si possono salvare file binari, con codifiche proprie, serializzare strutture dati Java o ricorrere a formati "a caratteri" come CSV o altro ancora.  
A volte però farebbe comodo una specie di mappa in cui salvare coppie chiave/valore con la possibilità di renderla persistente su disco. Tutto ciò è disponibile e prende il nome di **SharedPreferences**.  
I dati collocati nelle SharedPreferences vengono salvati in un file XML contenuto nello storage interno, precisamente nella cartella *shared_prefs*. Il file in cui sono contenute può avere un nome di default o assegnato dal programmatore pertanto si potrà accedere alle Preferences in due modi:
* nella modalità di default con il metodo `getPreferences()`. Questo metodo è concepito per salvare dati privati dell'activity. Il nome di default prodotto per il file richiamaerà infatti il mnome dell'activity.
* specificando un modo di file con `getSharedPreferences(String filename, int mode)` dove il primo parametro indica il nome che si vuole dare al file di preferenze mentre il secondo sono i privilegi da concedere al file.  
  
Entrambi i metodi restituiranno un oggetto SharedPreferences sul quale si potrà agire come su di una mappa. Si noterà subito che un oggetto SharedPreferences contiene tipici metodi di lettura come:
* `contains(String key)` che verifica se una proprietà con una certa etichetta è già stata salvata.
* una serie di **getter* come `getInt`, `getString`, `getFloat` e via dicendo con i quali si potrà recuperare i dati salvati per una certa chiave.  
  
Tra gli altrim si vedrà anche un metodo `edit()` che restituisce un oggetto di tipo Editor. QUesta classe è il **meccanismo di modifica delle SharedPreferences**. Dall'editor si avrà accesso a molti metodi put che permettono di modificare le proprietà. Al termine delle modifiche è molto importante che si richieda il salvataggio delle stesse invocando il meotdo `apply()`, anch'esso dell'oggetto Editor.  
Semplicità delle mappe, utilità della persistenza, se vi si aggiunge la frequenza in cui si necessita di salvare dati così semplici si comprende perché le SharedPreferences siano considerate uno strumento importantissimo per il programmatore.
## Database e SQLite
