# Content Providers, Broadcast e Services
In Android vi sono 4 componenti fondamentali, le activity, broadcasts, content providers e services. Le activity servono per lo sviluppo delle app! Le altre componenti sono di ausilio e servono 
in casi particolari, ma in alcuni casi sono estremamente utili.
* [Broadcast Receiver](#broadcast-receiver)
* [Content Provider](#content-provider)
* [Services](#services)
## Broadcast Receiver
La classe BroadcastReceiver serve per ricevere e "reagire" ad eventi che sono rappresentati da Intent.  
Un "ricevitore" deve "registrarsi" dichiarando gli eventi ai quali è interessato, ad esempio, esiste un BroadcastReceiver che ha il compito di spedire messaggi MMS.  
Quando un'altra componente crea un MMS invia un evento (intent). L'intente viene mandato in broadcast al sistema dove vi sarà un ricevitore che lo intercetta e spedisce il messaggio.  
Il ricevitore riceve l'intent tramite il metodo `onReceive(Contexxt c, Intent i)`.  
Riassumendo:
* il "ricevitore" si "registra" usando registerReceiver() (disponibile nel LocalBroadcastManager o nel Context).
* l'evento viene creato (da qualche altra componente del sistema).
* android notifica il ricevitore chiamando `onReceive()`.  
  
Lo si può fare staticamente nel Manifesto dell'app, dinamicamente usando registerReceiver().
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
  ...
  <application
    ...
    <receiver
      android:name=".My_Receiver"
      android:exported="false"
      <intent-filter>
        <action android:name="it.unisa.mp.MY_ACTION" />
      </intent-filter>
    </receiver>
  </application>
</manifest>
```
Se la registrazione è statica, il ricevitore viene registrando durante il Boot del sistema (oppure quando l'app viene installata), mentre se la registrazione è dinamica, il ricevitore 
viene registrato quando si chiama `LocalBroadcastManager.registerReceiver()` per i broadcast locali all'app, o `Context.registerReceiver()` per i broadcast system-wide.  
E' possibile anche revocare la registrazione usando `unregisterReceiver()`.  
Per spedire un messaggio si usa il metodo `sendBroadcast(Intent i)` o `sendBroadcast(Intent i, String permission)`. Se si specifica anche una stringa di permesso l'intent verrà consegnato 
solo ai ricevitore che hanno il permesso (il permesso deve essere specificato nel manifesto).  
Il metodo `sendBroadcast(Intent i)` è disponibile sia nel LocalBroadcastManager che nel Context. Si utilizza il primo per messaggi locali all'app ed il secondo per messaggi system-wide. 
`sendBroadcast(Intent i, String permission)` è disponibile solo nel Context.
## Content Provider
Rappresentano contenitori dati (progettati per condividere le informazioni fra le applicazioni).  
Per accedere ad un ContentProvider si utilizza un ContentResolver (interfaccia simile a quella di un database), utilizzando comandi SQL-like, in più, notifiche su cambiamenti dei dati.  
Per usare il resolver occorre recuperare un suo riferimento chiamando `Context.getContentResolver()`.  
ContentProviders standard:
* Browser (info su bookmakers, history).
* Call Log (info sulle chiamate).
* Contact (info sui contatti presenti in rubrica).
* Media (lista dei file multimediali utilizzabili).
* UserDirectory (lista delle parole digitate).
* ... molti altri.  
  
I dati contenuti in un provider sono memorizzati in tabelle. Gli utenti possono far riferimento ad uno specifico ContentProvider usando un URI.  
Un esempio di URI: `content://com.android.contacts/contacts/`. Non c'è nessun ID, quindi l'URI identifica l'intera tabella dei contatti.  
Per ottenere i dati usiamo una query ed un Cursor.
```
Cursor query(Uri uri,
  String[] projection // colonne
  String selection // SQL selection
  String[] args // SQL args
  String sortOrder // ordinamento)
```
Restituisce un Cursor che ci permette di iterare sull'insieme di record restituiti dalla query `ContentResolver.query()`.
## Services
Servono a eseguire operazioni complesse che possono richiedere molto tempo, come ad esempio, scaricare un file da internet, sincronizzare informazioni locali con un server ecc...  
I services non interagiscono con l'utente, non c'è una UI. Servono per eseguire delle operazioni in background, quindi esiste un iterazione tra l'app e il servizio.  
La prima cosa da fare è far partire il Service con `Context.startService(Intent i);`. Una volta partito, il Service può continuare la sua esecuzione fino a che la device è accessa:
* potrebbe anche essere interrotto se occorrono le risorse che esso usa.
* potrebbe anche terminare volontariamente.  
  
Nell'utilizzo tipico un Service fatto partire da un'app termina la propria esecuzione dopo aver eseguito l'operazione richiesta. Per default, il Service gira nel main thread dell'app che lo ha fatto partire (in alcuni casi deve essere esplicitamente fatto girare su un thread separato).  
Le componenti che vogliano interagire con un Service devono effettuare un "bind" `Contenxt.bindService(Intent service, ServiceConnection conn, int flags);`. Il binfing permette di inviare richieste e riceverne.  
Se al momento della richiesta di bind il Service non è ancora attivo, esso viene fatto partire e rimane attivo fino a quando c'è almeno un client connesso.  
Occorre dichiarare il Service nel Manifesto:
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http:///schemas.android.com/apk/res/android"
  ...
  <application
    ...
    <service
      android:name="MyService"
    </service>
  </application>
</manifest>
```
