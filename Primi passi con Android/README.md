# Primi passi con Android
* [Activity](#activity)
* [Ciclo di vita di un'Activity](#il-ciclo-di-vita-di-unactivity)
* [Gestire le risorse e gli asset](#gestire-le-risorse-e-gli-asset)
* [Service](#service)
* [Content Provider](#content-provider)
* [Broadcast Receiver](#broadcast-receiver)
* [Intent](#intent)
* [Gli Extras](#gli-extras)
* [Le permission](#le-permission)
## Componenti di un sistema android.
Ogni applicazione Android affida le sue funzionalità a quattro tipi di componenti:
Activity, Service, Content Provider e BroadcastReceiver.
## Activity 
L'utilizzo di un'app è definito dall'interazione con una o più "pagine", ovvero le activity, con le quali l'utente può consultare dati e immettere input.  
Per creare un'activity è necessario fare due cose:
* Estendere la classe Activity o una da essa derivata.
* Registrare l'Activity nell'AndroidManifest.xml.  
  
  
Un esempio di activity nel codice Java:
```
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```  
Il metodo `setContentView(R.layout.activity_main)` specifica quale sarà il "volto" dell'activity. Il suo effetto è quello di imporre
come struttura grafica dell'activity il contenuto del file activity_main.xml presente nella cartella res/layout.  
### Il ciclo di vita di un'Activity
![Ciclo di vita Activity](https://user-images.githubusercontent.com/48457431/101798283-341e1d80-3b0b-11eb-8e5e-283a3b9a3d75.jpg)  
Quando un'activity va in esecuzione per interagire direttamente con l'utente vengono obbligatoriamente invocati tre metodi:
* onCreate: l'activity viene creata. Il programmatore deve assegnare le configurazioni di base e definire quale sarà il layout dell'interfaccia.
* onStart: l'activity diventa visibile. È il momento in cui si possono attivare funzionalità e servizi che devono offrire informazioni all'utente.
* onResume: l'activity diventa la destinataria di tutti gli input dell'utente.  
  
Android pone a riposo l'activity nel momento in cui l'utente sposta la sua attenzione su un'altra attività del sistema.  
Anche in questo caso passa per tre metodi di callback:
* onPause: (l'inverso di onResume) notifica la cessata interazione dell'utente con l'activity.
* onStop: (l'inverso di onStart) segna la fine della visibilità dell'activity.
* onDestroy (l'inverso di onCreate) segna la distruzione dell'activity.  
I metodi di callback sono concepiti a coppie (un metodo di avvio con un metodo di arresto: `onCreate` - `onDestroy`, `onStart` - `onStop`, `onResume` - `onPause`).  
Un altro aspetto significativo della vita di un'activity consiste nel salvataggio della cosidetta `view hierarchy` ovvero lo stato dei componenti visuali presenti nell'interfaccia utente.  
Questo viene fatto in automatico dal sistema, ma il programmatore può chiedere di salvare ulteriori dati inserendoli come elementi chiave/valore in un ogetto di classe `Bundle`. 
Nel metodo onSaveInstanceState, chiamato durante la messa in pausa dell'activity, si può effettuare il salvataggio mentre in onRestoreInstanceState, invocato in fase di start dell'activity, verrà consegnato il Bundle con i dati salvati nella sessione precedente.
## Gestire le risorse e gli asset
Nelle applicazioni Android il codice Java richiama spesso degli elemtni interni al progetto come file XML, stringhe, numeri, immagini e altro ancora.
Il modo migliroe per conservare tutti questi "valori" a disposizione dell'applicazione è collocarli all'interno della cartella di progetto denominata `res` e gestirli mediante l'apposito meccanismo delle risorse. 
### Dove si trovano le risorse
Res conta diverse sottocartelle i cui nomi indentificano il loro contenuto.
* layout: conterrà l'architettura grafica dei componenti dell'interfaccia utente.
* values: conterrà stringhe, colori, dimensioni e altre tipologie di valori che potranno essere usate in ulteriori risorse o nel codice Java.
* drawable: sono immagini nei formati più comuni o configurate in XML.
* mipmap: sono immagini che costituiscono l'icona dell'applicazione, quella con cui viene lanciata dal sistema operativo.
### Come richiamare le risorse
Le risorse vengono compilate in un formato binario ed indicizzate mediante un ID univoco.
Tali ID sono conservati in una classe Java, di nome `R`, autogenerata ad ogni modifica.
Mediante i loro identificativi, le risorse sono accessibili sia da codice Java che da altre risorse definite in XML:
* in Java: tramite `R.tipo_risorsa.nome_risorsa.`
* in XML: `@tipo_risorsa/nome_risorsa`.
Ad esempio, la risorsa di tipo stringa identificata con il nome `appname`:
```
<string android:name="appname">Hello world!</string>
```
potrà essere recuperata, in Java, mediante `R.string.appname` o dall'interno di altre risorse XML con `@string/appname`.
### Adattamento multipiattaforma delle applicazioni
Un organizzazione tipica in un progetto Android Studio delle risorse è la sequente:  
![Vista res](https://user-images.githubusercontent.com/48457431/101794733-77768d00-3b07-11eb-8ff4-e9bd3a53c75e.png)  
Alcune cartelle mostrano risorse con lo stesso nome. Ad esempio, in layout c'è activity_main con la notazione (2) che indica due file di quella tipologia ed uno di essi riporta la denominazione (land) intendendo che il layout sarà da attivare
solo quando il dispositivo sarà in posizione con il lato lungo in orizzontale.
### Gli assets
Esiste anche un tipo di risorsa "grezza" collocabile nella cartella res/raw dove vi si potrà collocare tutto ciò che non si riesce ad inquadrare in una particolare tipologia.
In alternativa alle risorse raw, si possono definire gli assets. Questi eslano dal meccanismo delle risorse e vanno depositati nell'omonima cartella di progetto.  
![Creazione cartella Assets](https://user-images.githubusercontent.com/48457431/101795422-2adf8180-3b08-11eb-8452-0646a43d3bbf.png)  
Non vengono nè compilati in formato binario nè etichettati con un ID univoco. La loro fruizione da parte dell'applicazione avverrà mediante uno stream che potrà essere richiesto ad una classe Java di nome AssetManager.
## Service
Un service è l'opposto di un activity, ovvero, esse compiono un lavoro che viene svolto in background e quindi senza un'interazione diretta con l'utente.
I service sono di due tipologie: started e bounded.
Un service è di tipo started quando un'app ha bisogno di svolgere un'attività con uno scopo ben preciso, e che necessità di essere completata.
Un service, invece, è di tipo bounded, quando l'app ha bisogno di svolgere un'attività che necessita di interazioni con processi diversi, in poche parole quando un app esterna si connette alla nostra app.
## Content Provider
Un content provider è un componente che permette di convidere, nell'ambito del sistema, contenuti custoditi in un database, su file o reperibili mediante accessi in rete.
## Broadcast Receiver
Un broadcast receiver è un componente che gestisce la ricezione di messaggi a livello di sistema con cui Android notifica l'avvenimento di un determinato evento. La loro esecuzione dovrebbe essere istantanea delegando a Service o JobScheduler eventuali operazioni da attivare.
## Intent
Un componente può attivarne un'altro grazie ad apposite invocazioni di sistema che viene codificata con un Intent, ovvero una normale classe Java.  
Gli intent rappresentano una forma di messaggistica dal sistema operativo con cui una componente può richiedere l'esecuzione di un'azione da parte di un'altra componente.  
Sono uno strumento molto duttile anche se gli utilizzi più comuni ricadono in queste tre casistiche:
* avviare un'Activity.
* avviare un Service.
* inviare un messaggio in broadcast che può essere ricevuto da ogni applicazione.  
  
Inoltre, gli Intent possono essere:
* espliciti: viene dichiarato quale componente dovrà essere attivata. Particolarmente utili nel'apertura di una nuova Activity.
* impliciti: non specificano una componente da attivare ma quale azione deve essere svolta. La loro invocazione si estrinseca spesso nell'apertura di una finestra di dialogo che chiede all'utente quale app vuole si apra per completare l'azione.
## Gli Extras
Un altro aspetto molto utile degli Intent è che essi, nel recapitare questo messaggio, hanno a disposizione una specie di "bagagliaio", in cui custodiscono
dati che possono essere letti dal destinatario. Questi valori condivisi mediante Intent vengono generalmente chiamati Extras e possono essere di varie tipologie, sia appartenenti a classi più comuni che ad altre purché serializzabili.
### L'esempio: un form di login
Abbiamo due activity:
* MainActivity contiene un semplice form di login. Dopo aver inserito username e password viene controllata la validità dei dati ed in caso positivo viene invocata l'apertura di un'altra activity.
* SecretActivity è l'area accessibile solo mediante login e contine dati riservati.  
  
All'ingresso della seconda Activity, l'applicazione vuole dare il benvenuto all'utente, ma per farlo ha bisogno di sapere come si chiama.
Tutto ciò che serve è già incluso nel meccanismo degli Intent. A livello di codice, nella MainActivity, una volta ottenuto il successo nel login, troveremo:
```
Intent i = new Intent(this, SecretActivity.class);
i.putExtra("username", account_username);
startActivity(i);
```
Le tre operazioni rappresentano:
* dichiarazione dell'intent come normale oggetto Java. In questo caso avremo un cosidetto intent esplicito in quanto appare chiaramente il nome della classe che verrà invocata.
* viene inserito, tra gli extras, una String, la variabile `account_username`, che sarà trasportata con l'intent fino a destinazione ossia la classe SecretActivity.
* infine il metodo `startActivity` dimostra quale azione vogliamo attivare con questo Intent, si tratta, in questo caso, dell'avvio di un'activity.
  
Nel metodo `onCreate` della seconda Activity, quella con il contenuto riservato, troveremo le seguenti righe:
```
Intent i = getIntent();
String username = i.getStringExtra("username");
```
Notiamo subito che l'activity attivata si trova a disposizione, mediante `getIntent()`.
### A livello di ciclo di vita, che succede?
Il passaggio da un'activity ad un'altra coinvolge i cicli di vita di entrambe. La prima, quella messa a riposo, dovrà passare
almeno per `onPause` (cessazione interazione con l'utente) e `onStop` (activity non più visibile) mentre la seconda percorrerà la catena di creazione `onCreate` - `onStart` - `onResume`.  
Ma in che ordine avverrò tutto cio? La priorità del sistema è il **mantenimento della fluidità della user-experience.** Per questo la consecutio delle operazioni sarà:
* la prima activity passa per **onPause** e viene fermata in stato Paused.
* la seconda activity va in **Running** venendo attivata completamente. In tale maniera l'utente potrà usarla al più presto non subendo tempi di ritardo.
* a questo punto, mentre l'utente sta già usando la seconda activity, il sistema può invocare **onStop** sulla prima.
## Le permission
Un'applicazione Android vive all'interno di una **sandbox**, ovvero un ambiente chiuso in cui l'applicazione opera in maniera sostanzialmente isolata dal resto del sistema.
Talvolta, però, può essere necessario che essa debba "uscire" da questa gabbia per accedere a informazione, funzionalità o apparati hardware del dispositivo. Per far si che l'app acceda a tali servizi, essa deve possedere alcuni "permessi" speciali che è necessario dichiarare
espressamente nel file *AndroidManifest.xml*. Ogni permission viene dichiarata tramite il tag `<user-permission>`, da collocare al di fuori del nodo `<application>`:
```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
package="...." >
<uses-permission android:name="android.permission.INTERNET" />
<application>
...
...
</application>
...
</manifest>
```
### Tipi di permission
Le permission Android vengono suddivise in tre famiglie in base al livello di protezione:
* permission normal, che non mettono cioè a rischio la privacy dell'utente.
* permission dangerous, potenzialmente più lesive della riservatezza degli utenti.
* permission signature, gestite a livello di installazione ma utilizzabili solo se l'app che le richiede è firmata con lo stesso certificato di quella che ha definito le permission.
### Gestione delle permission
Le permission dangerous vanno accettate a runtime dall'utente al momento dell'utilizzo della funzionalità che le richiedono mentre per quelle normal è sufficiente dichiararne l'impiego al momento dell'installazione dell'app.
Ciò che cambia sul piano della progettazione è che l'app, in prospettivam potrebbe avere più comportamenti: uno in cui tutte le permission dangerous sono state concesse dall'utente, altri in cui una o più di esse sono state negate o revocate.  
La classe `ContextCompat`, mette a disposizione un metodo per effettuare controlli sullo stato delle permission:
```
int statoPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
```
dove `this` rappresenta la nostra activity (che fungerà da Context). Il valore della variabile interna `statoPermission` sarà confrontato con le costanti `PackageManager.PERMISSION_GRANTED` oppure `PackageManager.PERMISSION_DENIED`.
Per fare in modo che le nostre app funzionino su versioni Android con API 23 o superiori dovremo verifica la disponibilità di una permission prima di utilizzare il metodo che ne ha bisogno, e qualora essa non fosse disponibile
dovremo chiedere all'utente di acconsentire lanciando un apposito Intent.
```
ActivityCompat.requestPermission(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, ID_RICHIESTA_PERMISSION);
```
Abbiamo così passato un array di stringhe contenente le permission che dovranno essere accettate, ed una costante intera che noi abbiamo chiamato `ID_RICHIESTA_PERMISSION`, che permetterà di identificare la specifica richiesta che abbiamo inoltrato.  
Il risultato sarà inviato ad un metodo che noi predisporremo:
```
@Override  
public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {  
  switch (requestCode) {  
    case ID_RICHIESTA_PERMISSION: {  
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {  
        // permission concessa: eseguiamo il codice  
      } else {  
        // permission negata: provvediamo in qualche maniera  
      }  
      return;  
    }  
  }  
}  
```
Il metodo ricerverà tre elementi: il codice della richiesta (utile per riconoscere a cosa sta rispondendo), l'array delle permission e l'array dei risultati (che conterrà in ogni posizione se la permission è stata accettata o meno).  
Riepilogando, quella che segue è la struttura di controllo da usare per far sì che l'utente acconsenta a svolgere attività soggette ad una permission dangerous.
```
// controlliamo se la permission è concessa
if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
  // se arriviamo qui è perchè la permission non è stata ancora concessa
if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
  // mostriamo ulteriori informazioni all'utente riguardante l'uso della permission nell'app ed eventualmente richiediamo la permission
} else {
  // se siamo qui è perchè non si è mostrata alcuna spiegazione all'utente, richiesta di permission
  ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ID_RICHIESTA_PERMISSION);
  }
}
```
