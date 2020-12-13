# Menu di navigazione
* [Ciclo di vita delle attività](#ciclo-di-vita-delle-attività)
* [Backstack](#backstack)
* [Intent](#intent)
## Ciclo di vita delle attività
Quando l'utente preme il pulsante "Home" vengono chiamate
* **onPause()**
* **onStop()**  
  

Quando si ritorna all'attività
* **onRestart()**
* **onStart()**
* **onResume()**  
  
Quando l'utente ruota il dispositivo, l'attività viene prima eliminata:
* **onPause()**
* **onStop()**
* **onDestroy()**  
  
e poi ricreata:
* **onCreate()**
* **onStart()**
* **onResume()**  
  
**onDestroy() comporta la perdita dello stato!**  
Per evitare la perdita dello stato, esso si salva nel metodo `onSaveInstanceState()`
```
@Override
  public void onSaveInstanceState(Bundle savedInstanceState) {
    // Salvare lo stato dell'app
    saveInstanceState.putStringArrayList("LISTA_STRINGHE", array_di_stringhe);
    saveInstanceState.putInt("CONTATORE", counter);
    // Always call the superclass so it can save the view hierarchy state
    super.onSaveInstanceState(savedInstanceState);
  }
```
E lo si recupera in onCreate()
```
@Override
  protected void onCreate(Bundle savedInstanceState) {
  ...
  if (savedInstanceState != null) {
      array_di_stringhe = savedInstanceState.getStringArrayList("LISTA_STRINGHE");
      counter = savedInstanceState.getInt("CONTATORE");
    }
  }
```   
Possibile anche poter gestire in proprio il cambiamento, ma non consigliato il suo utilizzo stesso da Android. Basta impostare nel manifesto:
```
<activity android:name=".MainActivity"
  android:configChanges="orientation">
```  
L'activity ora NON viene distrutta e viene eseguito il metodo onConfigurationChanged();
```
@Override
public void onConfigurationChanged(Configuration newConfig) {
  super.onConfigurationChanged(newConfig);
  
  // Checks the orientation of the screen
  if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
    Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
  } else if(newConfig.orientation == Configuration.ORIENTATION_PORTAIT) {
    Toast.makeText(this, "portait", Toast.LENGTH_SHORT).show();
  }
}
```
Valori dell'oggetto **Configuration**, ed interi specificati nella classe **Configuration**.  
Nella main activity
```
<activity android:name=".MainActivity" android:configChanges="orientation">
  <intent-filter>
    <action android:name="android.intent.action.MAIN" />
    <category android:name="android.intent.category.LAUNCHER" />
  </intent-filter>
</activity>
```
## Backstack
Un'app nornmalmente è fatta di più activity, ogni activity ha uno specifico compito, questa tecnica è dettà *modularità*.  
Prendiamo ad esempio un'app per la posta elettronica:
* un'attività per la scrittura del messaggio.
* un'attività per spedire il messaggio.
* un'attività per vedere una lista dei messaggi.
* un'attività per vedere il contenuto di un messaggio.
* etc...
  
Un' attività può lanciare un'altra attivia ma anche attività che appartengono ad altre app, grazie agli Intent. La classe Intent serve a lanciare una nuova attività e "passare" i dati all'attività che si lancia.  
Più attività possono coesistere e vengono organizzate in un **backstack**.  
Soliutamente una task parte dall'Home screen dove l'utente clicca un'icona e lancia un'attività. Essa viene mostrata sullo schermo, in gergo tecnico, viene portata in "foreground". Se vengono lanciate nuove attività, l'attività corrente viene messa nel backstack, dove, l'utente grazie al pulsante di back, può tornare alla vecchia attività.  
![Backstack](https://user-images.githubusercontent.com/48457431/102016609-07177800-3d62-11eb-87a9-2e4809bb2a90.PNG)  
Continuando a premere Back si ritorna all'Home screen.  
Un task con le sue attività può essere spostato in "background" quando l'utente inizia un nuovo task, oppure preme il tasto Home. In questo caso, le attività vengono messe in stato di stop, ma il loro backstack rimane intatto.  
Se un'attività può essere lanciata da più di un'altra attività si possono avere istanze multiple (**MultiActivity**).
## Intent
Un'intent è una descrizione (astratta) di un'operazione da svolgere. Permette di:
* startActivity: lanciare una nuova attività.
* broadcastIntent: spedire l'intent in broadcast, verrà ricevuto dai BroadcastReceiver interessati.
* startService o bindService: comunicare con un servizion di background.  
  
Le parti principali di un oggetto Intent sono:
* Action: l'azione da svolgere, esempio: ACTION_VIEW, ACTION_EDIT, ACTION_MAIN...
* Data: i dati su cui operare espressi come URI (Uniform Resourse Identifier: <schema>:<parte specifica>).
* Category: informazioni aggiuntive sull'azione da eseguire.
* Type: specifica in modo esplicito il tipo (MIME) dei dati. Normalmente il tipo viene dedotto automaticamente.
* Component: specifica in modo esplicito l'attività da eseguire (che altrimenti verebbe dedotta dalle altre informazioni=.
* Extras: un bundle di informazioni addizionali (dati specifici per l'attività).  
  
Vi sono due tipologie di risoluzione:
* esplicita: quando specifichiamo in modo esplicito l'attività (Component) che vogliamo lanciare.
* implicita: quanda la Component non è specificata, e quindi Android sceglie un'attività appropriata, in base all'action, type, uri e category. Le attività, infatti, devono dichiarare le action che possono soddisfare nel manifesto.  
  
Nel codice abbiamo:
```
Intent i;
i = new Intent (Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
startActivityForResult(i, REQUEST_CODE);
```
* Azione: ACTION_PICK, chiede di selezionare un item.
* Data: ContactsContract.Contacts.CONTENT_URI (content://com.android.contacts/contacts").
* startActivityForResult: lancia l'attività chiedendo un risultato, REQUEST_CODE serve appunto ad identificare la richiesta.
```
@Override
protected void onActivityResult(int request, int result, Intent data) {
  if(request == REQUEST_CODE && result == Activity.RESULT_OK) {
    ...
  }
}
```
* onActivityResult: viene chiamato quando si ritorna all'attività di partenza.  
  
Per poter inserire gli "extra"m quindi coppie chiave-valore, bassta utilizzare il metodo putExtra per l'inserimento, e getExtra per la lettura.
```
intent.putExtra("CONTATORE", c);
intent.putExtra("STRINGA", "Ciao");
intent.putExtras(bundle); // Inserisce tutti i dati del Bundle bundle
```
```
c = intent.getExtra("CONTATORE");
stringa = intent.getExtra("STRINGA");
Bundle b = intent.getExtras();
```
