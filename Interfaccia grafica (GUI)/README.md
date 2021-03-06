# Interfaccia grafica (GUI)
* [Il layout di un'app Android](#il-layout-di-unapp-android)
* [Definire layout in XML](#definire-layout-in-xml)
* [View: le basi dell'interfaccia grafica](#view-le-basi-dellinterfaccia-grafica)
* [Controlli utente](#controlli-utente)
* [Creare un menu](#creare-un-menu)
* [ActionBar](#actionbar)
* [Le notifiche in Android](#le-notifiche-in-android)
* [ListView e GridView](#listview-e-gridview)
* [Creare Custom Adapter](#creare-custom-adapter)
* [Fragment in Android](#fragment-in-android)
## Il layout di un'app Android
La struttura grafica di un'activity prende il nome di **Layout**. Esistono tre modi per **creare interfacce utenti in Android**:
* procedurale, ovvero l'implementazione dell'interfaccia grafica nel codice.
* dichiarativa, non richiede la scrittura di codice: l'esempio tipico del metodo dichiarativo è rappresentato dalla creazione di una pagina web statica, in cui utilizziamo l'HTML per descrivere cosa vogliamo vedere nella pagina.
* visuale, metodo totalmente affidato alla potenza degli ambienti di sviluppo. Le capacità di tali strumenti mette a disposizione del programmatore un ambiente completo per creare interfacce utente a colpi di mouse e drag-and-drop.
Android permette la creazione di interfacce sia procedurali sia dichiarative: possiamo creare un'interfaccia utente completamente in codice Java (metodo procedurale), oppure possiamo creare l'interfaccia utente attraverso un descrittore XML (metodo dichiarativo).  
Con XML i vantaggi sono:
* facilità nella specifica.
* possibilità di separare in modo netto la definizione dell'UI dal codice dell'applicazione, e quindi facile da modificare.  
  
Lo svantaggio invece è che gli elementi sono statici.  
Con il metodo programmatico, il vantaggio è la possibilità di avere un layout dinamico e quindi facile da adattare, mentre lo svantaggio e che dobbiamo gestire il layout nel codice dell'applicazione.  
Android inoltre permette anche un approccio ibrido, in cui si crea un'interfaccia in modo dichiarativo e la si controlla e specifica in modo procedurale.  
### Tipi di Layout
Nel framework Android sono stati definiti vari tipi di layout, ma ce ne sono alcuni di utilizzo molto comune, ovvero:
* LinearLayout: contiene un insieme di elementi che distribuisce in maniera sequenziale dall'alto verso il basso (se definito con orientamento verticale) o da sinistra a destra (se ha orientamento orizzontale, il valore di default).
* TableLayout: altro layout piuttosto semplice, inquadra gli elementi in una tabella e quindi è particolarmente adatto a mostrare strutture regolari suddivise in righe e colonne come form o griglie.
* RelativeLayout: adatto a disporre in maniera meno strutturata gli elementi. Essendo "relative" gli elementi si posizionano in relazione l'uno all'altro o rispetto al loro contenitore, permettendo un layout fluido che si adatta bene a display diversi.
* ConstraintLayout: una delle più importanti evoluzioni del settore, nato appositamente per la creazione di interfacce responsive ed integrato perfettamente negli ambienti visuali di Android Studio.
## Definire layout in XML
Gli attributi XML utilizzati per la maggior parte proverranno da un **namespace** avente URI *http://schemas.android.com/apk/res/android*.
Per questo motivo quando definiremo layout in un progetto Android il nodo root che conterrà tutti gli elementi mostrerà al suo interno la dichiarazione
```
xmlns:android="http://schemas.android.com/apk/res/android"
```
Secondo aspetto comune è la presenza obbligatoria di due attributi: **layout_width** e **layout_height**, che definiscono la capacità dell'elemento di estendersi, rispettivamente, in larghezza (width) o altezza (height).
Il loro valore può essere una dimensione, oppure una costante da scegliere tra:
* **wrap_content**: l'elemento sarà alto o largo a sufficienza per includere il suo contenuto.
* **match_parent**: l'elemento si estenderà in altezza o in larghezza fino a toccare il suo contenitore.
## View: le basi dell'interfaccia grafica
Una classe che incontreremo spesso nelle interfacce utente Android è **View**. Con questo termine intendiamo un qualunque elemento che appare in un'interfaccia utente e che svolge **due funzionalità**:
* mostra un aspetto grafico.
* gestisce eventi relativi all'interazione con l'utente.
Una classe derivata da View è **ViewGroup**. Esso è al contempo un tipo di View e un contenitore di altre View. Tanto per fare un esempio di ViewGroup pensiamo ai layout, sono tipici raggrupamenti di View.
Questa particolare relazione tra View e ViewGroup è cio che permette l'annidamento di elementi grafici l'uno nell'altro come quello tra layout.
### Classificazione di View
Tutto ciò che tratteremo come un controllo utente in Android sarà direttamente o indirettamente discendente di una View. Le view si articolano per lo più in tre categorie:
* i **layout**.
* i **controlli utente** che renderanno le nostre applicazioni interattive.
* gli **AdapterView** sono delle View, generalmente nello stesso package dei controlli utente, che collaborano nella realizzazione del pattern *Adapter*.
### View e id
View contiene in sè le caratteristiche comuni a tutti gli elementi che popolano le interfacce Android. Prima di tutto gli id.  
Nel file di risorse, gli attributi *id* hanno un valore definito come @+id/*identificatore* dove per identificatore si intende il nome dell'id scelto dall'utente. Il simbolo + apposto dopo la @ indica che se l'id con quel nome non è stato ancora definito nel sistema sarà definito per l'occassione.
```
<TextView
android:id="@+id/nome"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
. . .
. . .
/>
```
In questo esempio possiamo notare che l'elemento TextView ha un'id con cui gli si potrà fare riferimento:
* in XML con @id/nome.
* nel codice Java come R.id.nome.
### Gestione degli eventi
La gestione degli eventi viene realizzata con il meccanismo dei listener la cui sintassi si è fortemente snellita nel tempo con l'introduzione anche nella programmazione Android delle espressioni lambda di Java.  
![Eventi](https://user-images.githubusercontent.com/48457431/101813952-b1528e00-3b1d-11eb-83e0-06e6d1f3070d.png)  
Questo esempio rappresenta i tratti salienti di qualunque gestione degli eventi. I numeri raffigurati indicano:
* 1 - il recupero di un riferimento alla View della quale verranno monitorati gli eventi.
* 2 - la definizione di un oggetto (anonimo in questo caso) che contiene un metodo onClick definito appositamente per gestire l'evento di click.
* 3 - la registrazione del listener tramite un metodo *setter* affinché il pulsante sappia chi è l'oggetto a cui delegare la gestione dei click.  
  
Grazie alle espressioni lambda, lo stess codice si può scrivere nel seguente modo:
```
Button pulsante = findViewById(R.id.pulsante);
pulsante.setOnClickListener((View view) -> { /* Gestione dell'evento */ });
```
## Controlli utente
I più comuni componenti sono:
* **TextView**: la classica label.
* **EditText**: corrisponde all'inputbox di altre tecnologie. Con l'attributo android:inputType è possibile definire il formato di input tra i più comuni (date, password, testo, etc...).
* **Button**: è il pulsante.
* **CheckBox**: come ci si aspetta definisce un classico flag che può essere attivato o disattivato.
* **Radio**: esistono i radiobutton come in ogni altra tecnologia per interfacce utente.  
### Usare i controlli utente
Prendiamo come esempio un layout che contiene un form per il login.
```
<android.widget.LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="10dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginRight="5dp"
            android:text="Username:" />

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:id="@+id/username"/>
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginRight="5dp"
            android:text="Password:" />

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="textPassword"
            android:id="@+id/password"/>
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:padding="10dp">

        <Button
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:onClick="login"
            android:text="Login" />

        <Button
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:onClick="cancella"
            android:text="Cancella" />
    </LinearLayout>

</android.widget.LinearLayout>
```
Notiamo che come layout è stato utilizzato un LinearLayout con una struttura innestata. Tra i ccontrolli usati alcuni mostrano l'attributo **android:id**. Serve per lo più alle EditText
usate per inserire username e password dell'utente che sta tentando il login. Infine, i due Button, presentano l'attributo **onClick** usato per definire quale metodo dell'Activity si occuperà della gestione del click sul pulsante.
Rappresenta una scorciatoia rispetto all'uso dei listener.  
Il primo Button presenta questa valorizzazione di onClick:
```
android:onClick="login"
```
Ciò comporta che nell'activity dovremo trovare un metodo dalla seguente forma:
```
public void login(View arg0)
```
dove il nome del metodo corrisponde al valore dell'attributo onClick e il parametro View in input rappresenta il controllo che ha sollevato l'evento, in questo caso il Button.
## Creare un menu
Le tipologie di menu che le applicazioni android offrono sono tre:
* **Options menu**: è il menu principale dell'applicazione e contiene voci rigurandati operazioni di interesse generale nella vita dell'app.
* **Context Menu**: è un menu invocabile su un singolo componente dell'interfaccia utente. Le voci richiamabili serviranno ad avviare operazioni sull'elemento su cui è stato richiesto il menu.
* **Popup menu**: è un menu, ancorato ad un elemento dell'interfaccia utente, che permette di mostrare una lista verticale di opzioni, relative ad una parte specifica del contenuto senza comportarne la modifica.  
  
### Definire la struttura del menu
I menu sono risorse, quindi il loro layout va definito nella sottocartella *res/menu*. Il seguente codice mostra un layout di menu:
```
<menu xmlns:android="http://schemas.android.com/apk/res/android" >
  <item
    android:id="@+id/MENU_1"
    android:title="Nuova nota"/>
  <item
    android:id="@+id/MENU_2"
    android:title="Elenco note"/>
</menu
```
Per poter creare un menu minimale, sono sufficienti due tag: `<menu>` che definisce il menu del suo complesso e `<item>` che dichiara la singola voce del menu. Gli attributi impiegati nella configurazione sono due:
* *id* che serve per la gestione delle voci del menu.
* *title* che contiene una stringa che dà il titolo alla voce di menu.  
  
![Menu](https://user-images.githubusercontent.com/48457431/102017501-9ffcc200-3d67-11eb-8d0b-a0bb83f645f6.png)
### Attivare il menu nell'activity
Affinché il menu venga collegato all'activity è necessario fare l'override di un metodo denominato `onCreateOptionsMenu`. Quella che segue è l'implementazione utilizzata nell'esempio:
```
@Override
public boolean onCreateOptionsMenu(Menu menu) {
  MenuInflater inflater = getMenuInflater();
  inflater.inflate(R.menu.main, menu);
  return true;
}
```
Questo metodo, richiesto nell'activity che desidera il menu, prende come argomento un riferimento ad un oggetto Menu che non dovremo mai preoccuparci di istanziare in quanto sarà già presente nel sistema. Ciò che resta da fare è configurarlo assegnandogli il layout che abbiamo predisposto nelle risorse. Questo sarà compito delle tre righe:
* `MenuInflater inflater = getMenuInflater()`: recupera un riferimento ad un inflater di Menu, ossia un servizio del sistema in grado di modellare la struttura dell'oggetto Menu in basse alle direttive imposte in XML.
* `inflater.inflate(R.menu.main, menu)`: questo è il momento in cui l'azione dell'*inflating* viene veramente svolta. Il metodo inflate richiede due parametri: la risorsa contente il layout del menu e l'oggetto Menu da configurare.
* `return true`: solo se il valore boolean restituito da onCreateOptionMenu sarà true il menu sarà attivo.
### Gestire le voci del menu
Per poter usare il menu manca solo la gestione del click. QUesto viene fatto mediante il metodo `onOptionsItemSelected`:
```
@Override
public boolean onOptionItemSelected(MenuItem item) {
  int id = item.getItemId();
  switch(id) {
    case R.id.MENU_1: //
                      break;
    case R.id.MENU_2: //
                      break;
  }
  return false;
}
```
### Creare un Context Menu
Un Context menu viene creato in maniere del tutto simile ad un menu Options. Le operazioni da effettuare sono le seguenti:
* definizione della struttura del menu contestuale in un apposito file della cartella *res/menu*.
* predisposizione del metodo `onCreateContextMenu` in cui verranno eseguite le medesime operazioni svolte nell'`onCreateOptionMenu`.
* definizione delle azioni di risposta al click sulle voci metiante `onContextItemSelected`.
* effettuazione di **un'operazione molto importante che non è, viceversa, usata negli OptionMenu**: la registrazione dell'elemento che vuole usare il menu contestuale.  
  
Visto che il menu contestuale viene richiamato con click lungo su un elemento del layout, si deve segnalare all'activity quale elemento sarà dotato di questa caratteristica. Per fare ciò ci invoca il metodo `registerForContextMenu(View v)`, solitamente nell'onCreate dell'activity, e la View che viene passata come parametro di ingresso è proprio il riferimento all'elemento sul quale può essere attivato il menu contestuale.
### Creare un PopupMenu
Anche il Popup Menu potrà essere aggiunto sulla falsa riga di quanto visto sinora. Prima di tutto dovremo creare il metodo in grado di farlo apparire. Potrebbe essere qualcosa del genere:
```
public void showPopup(View view) {
  PopupMenu menu = new PopupMenu(this, view);
  MenuInflater inflater = menu.getMenuInflater();
  menu.setOnMenuItemClickListener(this);
  inflater.inflate(R.menu.popup, menu.getMenu());
  menu.show();
}
```
Anche in questo caso serve una risorsa menu, disegnata in XML, che verrà mostrata mediante inflater. Rispetto a quando visto prima il menu viene creato come oggetto di classe `PopupMenu` e come argomento perviene un oggetto `View` che sarà il controllo cui il menu è stato associato. Per attivare il menu, va collegato questo metodo ad un'azione di un lemento dell'interfaccia, ad esempio, agganciandolo al suo click:
```
android:onClick="showPopup"
```
Per la gestione delle singole azioni, abbiamo richiamato il metodo `setOneMenuItemClickListener` che imposta come listenere l'activity stessa a patto che questa implementi l'interfaccia `PopupMenu.OnMenuItemClickListener` con conseguente integrazione del metodo `onMenuItemClick(MenuItem item)` il cui funzionamento ricalca quello visto per `onOptionItemSelected`.
## ActionBar
L'ActionBar può essere definita come una **"cornice" programmabile** destinata ad ospitare opzioni di navigazione e di interazione di utilità più o meno comune all'intera applicazione, tra cui:
* *actions* ossia pulsanti cliccabili per attivare azioni. Altro non sono in realtà che voci dell'Options Menu collocate sull'ActionBar.
* navigazione con "tab".
* navigazione con menu a tendina.
* campi di ricerca.
* etc...  
### Comandi nell'ActionBar
Una tipica ActionBar Android
* 1 **icona** dell'applicazione e **titolo**.
* 2 due **actions**.
* 3 **action overflow** che ospita altre actions che non hanno trovato posto sull'ActionBar.
Per poter mostrare le actions all'interno dell'ActionBar i basterà mettere mano al layout del menu impostando opportunamente il valore dell'attributo `showAsAction`, tra i seguenti valori:
* `ifRoom`: mostra le icone sull'ActionBar compatibilmente con lo spazio disponibile.
* `never`: non mostra le voci del menu sull'ActionBar ma solo nell'OptionsMenu.
* `always`: il layout viene forzato a mostrare le voci in ActionBar. E' un valore sempre sconsigliabile, meglio optare per ifRoom.
* `withText`: oltra all'icona viene mostrato in ActionBar anche il testo, solitamento collegato all'attributo android:title.  
  
Se si ha necessità di combinare più valori per showAsAction lo si può fare sfruttando un `OR` (|).  
Un esempio XML di un OptionsMenu che collocherà entrambe le sue voci sull'ActionBar.
```
<menu xmlns:android="http://schemas.android.com/apk/res/android" >
<item
  android:id="@+id/MENU_1"
  android:showAsAction="ifRoom"
  android:title="Nuovo"
  android:icon="@android:drawable/ic_menu_add"/>
<item
  android:id="@+id/MENU_2"
  android:showAsAction="ifRoom"
  android:title="Elenco"
  android:icon="@android:drawable/ic_menu_agenda"/>
  </menu>
```
## Le notifiche in Android
Osservando la figura si possono riconoscere i vari elementi che costituiscono una comune notifica Facciamoci guidare dai numeri indicati:
* 1 titolo della notifica (content title).
* 2 icona grande (large icon).
* 3 contenuto della notifica (content text).
* 4 informazioni accessorie (content info).
* 5 icona piccola (small icon) che di norma appare anche nella barra del display.
* 6 ora della notifica (when) impostata dal programmatore o di default del sistema.  
### La prima notifica
Visto che le notifiche appaiono in zone del display non gestiste dall'applicazione, dovremo interagire con il sistema mediante un apposito servizio: il **NotificationManager**. Ne recuperiamo un riferimento:
```
NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
```
Nonostante la molteplicità di aspetti che contraddistinguono una notifica, ve ne sono **tre assolutamente obbligatori**:
* l'icona piccola.
* il titolo.
+ il contenuto.  
  
QUesti saranno configurati, rispettivamente, con i metodi `setSmallIcon`, `setContextTitle` e `setContentText`.  
Vediamo subito un esempio:
```
NotificationCompat.Builder n = new NotificationCompat.Builder(this)
  .setContentTitle("Arrivato un nuovo messaggio!!!")
  .setContentText("Autore: Nicola Rossi")
  .setSmallIcon(android.R.drawable.ic_dialog_email);
Notificationmanager notificationManager = (NotificationManager)
  getSystemService(NOTIFICATION_SERVICE);
notificationManager.notify(0, n.build());
```
La creazione della notifica ha seguito due fasi:
* è stata costruita mediante il *Builder* incluso nella classe NotificationCompat. I vari metodi *setter* permettono di configurarne i molti aspetti.
* finita la fase di build, la notifica verrà pubblicita dal NotificationManager mediante invocazione del metodo `notify`.
### Collegare un'azione alla notifica
Al click della notifica di solito viene aperta una nuova activity. Per fare ciò, l'apertura dell'activity avviene mediante `Intent` ma non sarà attivata subito con il medoto `startActivity` bensì sarà predisposta per "usi futuri" mediante la classe `PendingIntent`. Si tratta di una classe che, per così dire, conserva l'Intent e la descrizione dell'azione che esso porta con sè per poterlo attivare successivamente. Ciò che faremp sarà:
* predisporre un normale `Intent` per l'apertura della `MessageActivity`.
* creazione del `PendingIntent` sfruttando l'`Intent` del punto precedente.
* assegnazione del `PendingIntent` alla notifica mediante il meotodo `setContentIntent` del `NotificationCompat.Builder`.  
  
Di seguito le modifiche da apportare al codice precedente:
```
Intent i = new Intent(this, MessageActivity.class);
PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
NotificationCompat.Builder n = new NotificationCompat.Builder(this);
...
...
.setContentIntent(pi)
.setAutoCancel(true);
NotificationManger notificationManager = (NotificationManager)
  getSystemService(NOTIFICATION_SERVICE);
notificationManager.notify(0, n.build());
```
Ora, dopo l'apertura del "Notification drawer", si potrà cliccare sulla notifica e ciò comporterà l'esecuzione dell'azione contenuta nel `PendingIntent` con conseguente avvio della `MessageActivity`. La notifica scomparirà dalla barra dell'applicazione non appena seleziona, merito dell'invocazione al metodo `setAutoCancel()`.
## ListView e GridView
Finora abbiamo apprezzato due modalità diverse per realizzare le varie parti delle nostre app:
* **design in XML**, per layout e risorse, dall'approccio piuttosto visuale ma orientato alal definizione di parti statiche.
* **sviluppo in Java**, fondamnetale per le funzionalità dinamiche ma meno pratico per il disegno di porzioni di layout.  
  
Ma se dovessimo realizzare una "via di mezzo": una visualizzazione iterativa di contenuti archiviati in strutture dati, potenzialmente variabili, come dovremmo comportarci? Questo lavoro viene affrontato con Adapter e AdapterView dove:
* **Adapter** è un componente collegato ad una struttura dati di oggetti Java (array, Collections, risultati di query) e che incapsula il meccanismo di trasformazione di questi oggetti in altrettante View da mostrare su layout.
* **AdapterView** è un componente visuale che è collegato ad un adapter e raccoglie tutte le View prodotte dall'adapter per mostrarle secondo le sue politiche.  
### ListView, un AdapterView molto comune
Abbiamo un array di oggetti `String` e vorremmo che, iterativamente, una View ci mostrasse tutte le stringhe disposte in righe. Agiamo cosi:
* otteniamo un riferimento alla struttura data, in questo caso l'array di String.
* istanziamo un ArrayAdapter assegnandogli, via costruttore, l'array che fungerà da sorgente dei dati e il layout da usare per ogni singola riga.
* recuperiamo la ListView predisposta nel layout e le assegnamo il riferimento all'adapter che sarà il suo "fornitore" di View.  
  
Creiamo due layout:
* uno per l'activity, nel file activity_main.xml, costituito solo dalla ListView:  
```
<ListView xmlns:android="http://schemas.android.com/apk/res/android"
android:id="@+id/listview"
android:layout_width="match_parent"
android:layout_height="match_parent"/>
```
* l'altro per la singola riga, in row.xml, contenente sola la TextView che mostrerà **il testo di ogni riga**:
```
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"/>
```
Il collegamento dinamico tra struttura dati/Adapter/ListView viene realizzato nell'`onCreate` dell'activity:
```
@Override
protected void onCreate(Bundle savedInstancesState) {
  super.onCreate(savedInstancesState);
  String[] città = new String[]
    {"Torino", "Roma", "Milano", "Napoli", "Firenze"};
  ArrayAdapter <String> adapter = new ArrayAdapter <String> (this, R.layout.row, città);
  ListView listView = (ListView) findViewById(R.id.listView);
  listView.setAdapter(adapter);
}
```
![ListView](https://user-images.githubusercontent.com/48457431/102020128-72b81000-3d77-11eb-9aaa-c3d65783c4d2.png)
### GridView
Se volessimo sostituire la ListView con un altro AdapterView, diciamo la GridView specializzata in grigle sarà sufficiente:  
**nel layout, sostituire la ListView con la GridView**, assegnando un numero di colonne alla griglia:
```
<GridView xmlns:android="http://schemas.android.com/apk/res/android
  android:id="@+id/gridview"
  android:numColumns="3"
  android:layout_width="match_parent"
  android:layout_height="match_parent" />
```
nel codice Java semplicemente **sostituire l'uso della classe ListView con GridView**:
```
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main2);
  String[] nomi = new String[]
    {"Torino", "Roma", "Milano", "Napoli", "Firenze"};
  ArrayAdapter <String> adapter = new ArrayAdapter <String> (this, R.layout.row.nomi);
  GridView gridView = (GridView) findViewById(R.id.gridView);
  gridView.setAdapter(adapter);
}
```
### Gestione degli eventi
Gli AdapterView hanno un altro ruolo molto importante: la gestion degli eventi. Come per tutte le View viene effettuato mediante il meccanismo dei listener. Un caso molto comune è la gestione del click su un elemento della lista, ciò che viene normalmente interpretato come selezione. Tornando all'esempio della GridView, se volessimo far visualizzare un Toast che notifica quale elemento è stato selezionato dovremmo inserire il seguente codice:
```
GridView gridView = (GridView) findViewById(R.id.gridView);
gridView.setOnItemClickListener(new OnItemClickListener() {
  @Override
  public void onItemClick(AdapterView <?> av, View v, int pos, long id) {
    Toast.makeText(getApplicationContext(), "Selezionato" + citta[pos], Toast.LENGTH_LONG).show();
  }
});
```
## Creare Custom Adapter
Vediamo un caso in cui si realizza un Adapter in versione *custom* che rappresenta degli oggetti non primitivi.  
Prendiamo come esempio la seguente classe Java:
```
public class ArticleInfo {
  private String title;
  private String category;
  private Date date;
  *
  *
  *
```
Affinchè ognuno di questi oggetti, per così dire, si trasformi in una riga della ListView contenuta nell'activity **prepariamo subito un layout** nel file *res/layout/listaactivity_row_article.xml*:
```
<RelativeLayout xmls:android="http://schemas.android.com/apk/res/android"
  android:layout_width="match_parent"
  android:layout_height="200dp"
  android:background="@drawable/row_background"
  android:descendantFocusability="blocksDescendants"
  android:padding="10dp">
  <LinearLayout
   android:layout_height="wrap_content"
   android:layout_width="wrap_content"
   android:layout_alignParentLeft="true"
   android:layout_centerVertical="true"
   android:orientation="vertical"
   android:id="@+id/ll_text"
   android:layout_toLeftOf="@+id/btn_bookmark">
   <TextView
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     style="@style/big_textstyle"
     android:id="@+id/txt_article_description"/>
   <TextView
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     style="@style/small_textstyle"
     android:id="@+id/txt_article_url"/>
  </LinearLayout>
  <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:maxLength="5"
    style="@style/small_textstyle"
    android:layout_alignParentRight="true"
    android:layout_alignParentTop="true"
    android:id="@+id/txt_article_datetime"/>
</RelativeLayout>
```
Questo layout rappresenta la form di una singola riga che apparirà nell'AdapterView.  
L'elemento che si occuperà della trasformazione di ogni oggetto ArticlesInfo in una View sarà proprio l'Adapter: ma non uno standard, uno personallizato creato da noi.  
Per fare questo dobbiamo:
1. creare una classe che dichiaremo `ArticlesAdapter`, estensione di `BaseAdapter`.  
2. fare in modo che la classe possieda un riferimento alla struttra dati da visualizzare, magari passato tramite costruttore. Nel nostro caso sarà una List `<ArticlesInfo>`.  
3. **implementare obbligatoriamente i metodi astratti** di BaseAdapter:
* `getCount()`: restituisce la quantità di elementi presenti nella struttura dati.
* `getItem(int position)`: restituisce un Object che rappresenta l'elemento della struttura dati nella posizione position.
* `getItemId(int position)`: restituisce un id univoco per l'elemento in posizione position.
* `getView(int position, View v, ViewGroup vg)`: restituisce la View che costituirà una riga della ListView. La view sarà strutturata secondo il layout *listactivity_row_article* e popolata con i dati dell'elemento in posizione *position*.  
  
Vediamo il codice dell'Adapter:
```
public List <ArticleInfo> articles = null;
private Context context = null;
private SimpleDateFormat simple = new SimpleDateFormat("dd/MM", Locale.ITALIAN);
public ArticlesAdapter(Context context, List <ArticleInfo> articles) {
  this.articles = articles;
  this.context = context;
}
@Override
public int getCount() { return articles.size(); }
@Override
public Object getItem(int position) { return articles.get(position); }
@Override
public long getItemId(int position) { return getItem(position).hashCode(); }
@Override
public View getView(int postion, View v, ViewGroup vg) {
  if(v == null)
    v = LayoutInflater.from(context).inflate(R.layout.listactivity_row_article, null);
  ArticleInfo ai = (ArticleInfo) getItem(position);
  TextView txt (TextView) v.findViewById(R.id.text_article_description);
  txt.setText(ai.getTitle());
  ...
  return v;
}
```
Al suo interno, per prima cosa, viene controllato se la View passata in input è nulla e solo in questo caso viene inizializzata con il `LayoutInflater`. Questo aspetto è molto importante ai fini della salvaguardia delle risorse infatti Android riciclerà quanto possibile le View già create. Il LayoutInflater attua per i layout quello che abbiamo già visto fare per i menu con il `MenuInflater`. In pratica la View da creare verrà strutturata in base al "progetto" definito nel layout XML indicatogli.  
Dopo il blocco `if`, la View non sarà sicuramente nulla perciç procederemo al completamento dei suoi campi. I dati verrano prelevati dall'oggetto ArticleInfo di posizione position recuperato mediante `getItem`, già implementato. Al termine, `getView` restituirà la View realizzata.  
Questo Adapter incarnerà tutta la logica di trasformazione infatti per il resto l'activity è molto semplice. Tra l'altro estende la classe ListActivity che ha un layout costituito da una ListView e alcuni metodi per la sua gestione.
```
public class MainActivity extends ListActivity {
  private ArticlesAdapter adapter = new ArticlesAdapter(this, generateNews());
  @Override
  protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
    getListView().setPadding(10, 10, 10, 10);
    setListAdapter(adapter);
  }
  private List<ArticleInfo> generateNews() {
    ArrayList<ArticleInfo> list = new ArrayList<ArticleInfo>();
    Calendar c = Calendar.getInstance();
    ArticleInfo tmp = new ArticleInfo();
    tmp.setTitle("WordPress: integrare un pannello opzioni nel tema");
    tmp.setCategory("CMS");
    c.set(2014,3,23);
    tmp.setDate(new Date(c.getTimeInMillis()));
    list.add(tmp);
    /*
    * OMISSIS: il codice crea altri oggetti "fittizi" da visualizzare
    * */
    return list;
  }
}
```
## Fragment in Android
Un Fragment è una porzione di activity. Ma si faccia attenzione a comprenderne bene il ruolo. Non si tratta solo di un gruppo di controlli o di una sezione del layout. Può essere definito più come una specie di sub-activity con un suo ruolo funzionale molto importante ed un suo ciclo di vita.  
Un fragment non può vivere senza un'activity. Tipicamente nei nostri programmi creeremo più fragments che si alterneranno nel layout mentre di activity ne sarà sufficiente una. Come detto il fragment ha il suo ciclo di vita fortemente collegato con quello dell'activity di appartenenza, se ad esempio l'activity è in pausa (stato "paused" del ciclo di vita), lo sono anche tutti i suoi frammenti.  
Questo è la sequenza di stati che scandiscono la vita del fragment:  
![Ciclo di vita Fragment](https://user-images.githubusercontent.com/48457431/102267863-c9118400-3f1a-11eb-8937-9126988c0d41.jpg)  
Come si vede ricordano molto quelli dell'activity. La fase più variegata è l'inizializzazione del fragment:
* **onAttach**: segnala il momento in cui il fragment scopre l'activity di appartenenza. Attenzione che a quel punto l'activity non è stata ancora creata quindi si può solo conservare un riferimento ad essa ma non interagirvi.
* **onCreate**: è la creazione del fragment in quanto componente.
* **onCreateView**: il programmatore vi lavorerà spesso. E' il momento in cui viene creato il layout del fragment. Solitamente qui si fa uso del LayoutInflater.
* **onActivityCreated**: segnala che la creazione dell'activity è stata completata, vi si può interagire in tutto e per tutto.  
  
Gli altri metodi di callback del ciclo di vita vengono chiamati in corrispondenza degli omonimi metodi dell'activity.  
Normalmente dovremo implementare almeno:
* onCreate(), dove si effettua la inizializzazione come in un activity, ma NON definiamo il layout.
* onCreateView(), dove definiamo il layout, e quindi facciamo l'inflate di un file di layout (il metodo deve restituire una View).
* onPause(), il primo metodo chiamato quando il frammento viene eliminato (si dovrebbero rendere permanenti evenutali cambiamenti altrimenti si perdono).
### Hello Fragment!
Per creare il fragment seguiremo questi *step*:
* creeremo il layout per l'activity in cui ricaveremo il posto per alloggiare il nostro fragment.
* definiremo il layout del fragment che conterrà il vero aspetto dell'activity, e, di conseguenza, anche la stringa "Hello World!".
* definiremo la classe fragment che essenzialmente servirà a caricare il layout di cui al punto precedente.
* creeremo la nostra activity che svolgerà per lo più il ruolo di bacino di fragment.  
  
Il primo frammento di codice mostra il layout del fragment (file: res/layout/fragment_main.xml).  
Come si vede, se fosse stato destinato ad un'activity sarebbe stato identico. Quindi la novità architetturale dei fragment non influenza il layout:
```
<RelativeLayout
xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:padding="@dimen/activity_vertical_margin">
<TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="@string/hello_world" />
</RelativeLayout>
```
Il layout dell'activity è il seguente (file: res/layout/activity_main.xml):
```
<FrameLayout
xmlns:android="http://schemas.android.com/apk/res/android"
android:id="@+id/container"
android:layout_width="match_parent"
android:layout_height="match_parent"/>
```
Tutto lo spazio disponibile verrà riempito da un layout che non abbiamo mai usato sinora: il **FrameLayout**.  
Viene utilizzato quando vi si deve ospitare un unico elemento, in questo caso il fragment. Fondamentale definire l'id in quanto questo layout svolgerà il ruolo di contenitore del fragment e pertanto verrà invocato dal codice java.  
La classe fragment mostra evidenti alcune caratteristiche:
* estende la classe Fragment del framework.
* presenta metodi propri del ciclo di vita dei fragment. COme già accenato, sarà frequente l'override del metodo `onCreateView` in quanto è il momento in cui viene allestita l'interfaccia utente mostrata dal fragment. Non ci soprendono (ormai) le operazioni svolte al suo interno: assegnazioni di un layout mediante LayoutInflater.
```
public class HelloFragment extends Fragment {
  public HelloFragment() { }
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);
    return rootView;
  }
}
```
Il codice dell'activity contiene solo il metodo `onCreate`. Al suo interno, vengono svolte nelle prime due righe le operazioni consuete ma questa volta il caricamento del layout con `setContentView` non basta. Infatti questo porterà a display solo il FrameLayout ancora vuoto.  
Per aggiungere il fragment, si procederà per via dinamica richiedendo al FragmentManager l0avvio di una transazione `add` che aggiungerà il nuovo Fragment di classe HelloFragment al layout identificato da `R.id.container`.
```
public class MainActivity extends ActionBarActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
      .add(R.id.container, new HelloFragment()).commit();
    }
  }
}
```
L'operazione `add` fa parte delle **FragmentTransactions**. Le più comuni sono:
* `add`: aggiunge un fragment all'activity.
* `remove`: rimuove un fragment precedentemente aggiunto.
* `replace`: sostituisce un fragment con un altro.
* `hide`: nasconde un fragment.
* `show`: mostra un fragment precedentemente nascosto.  
  
Notare ancora che, come avviene per le transazioni nei database, le operazioni iniziano con un `beginTransaction` e vengono definitivamente salvate con un `commit`. Si può esprimere l'uso dei fragment realizzando layout basati su strutture e tab e a schede, molto comuni nelle interfacce utente.
### Layout adattabili Smartphone / Tablet in Android
I fragments possono essere visti come blocchi componibili che permettono di rendere il layout adattabile al dispositivo. Se la frammentazione dei dispositivi rappresenta una problematica di rilievo per i programmatori Android, i fragments rappresentano in buona parte una soluzione.
![25_img01](https://user-images.githubusercontent.com/48457431/102267951-e8101600-3f1a-11eb-95df-0637f610692a.png)
La figura mostra due dispotivi di tipo diverso ed in configurazioni differenti:
* uno **smartphone in portrait**. Immaginiamo con uno schermo piccolo, anche 3 pollici.
* un **tablet**, quindi almeno da 7 pollici, posizionato **in landscape**.  
  
I layout presenti su entrambi sono costituiti da due fragments, gli stessi due fragments a e b. Con adeguate configurazioni delle risorse e qualche aggiunta al codice visto prima, possiamo creare anche noi un **layout adattabile** che riesca a mostrarsi in *one-pane* su smartphone e *two-pane* su tablet in landscape.
### Configurazione delle risorse
Creiamo **due cartelle di risorse layout**:
* *layout-large-land* che verrà usato solo per dispositivi con display large in posizione landscape.
* *layout*, la cartella di default. Verrà chiamata in causa per tutte le altre soluzioni.  
  
La configurazione multipla ha successo se in entrambe le cartelle mettiamo il file di layout con il medesimo nome, *activity_main.xml*. Da questo momento, l'activity cercherà sempre la risorsa `R.layout.activity_main` ma questa, **n base alla configurazione del dispositivo**, corrisponderà al file *res/layout-large-lande/activity_main.xml* o al file 
*res/layout/activity_main.xml*.  
Vediamo entrambi i file di layout. File 1: *res/layout/activity_main.xml* :
```
<FrameLayout
xmlns:android="http://schemas.android.com/apk/res/android"
android:id="@+id/container"
android:layout_width="match_parent"
android:layout_height="match_parent"/>
```
Questo primo layout è identico a quello visto prima. E' un FrameLayout che ospiterà un fragment singolo assegnato dinamicamente con FragmentsTransactions.  
File 2: *res/layout-large-land/activity_main.xml*:
```
<LinearLayout
xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent">
<fragment android:name="it.html.guida.gui.fragments.CountryFragment"
android:id="@+id/countryfrag"
android:layout_width="0dp"
android:layout_height="match_parent"
android:layout_weight="1"/>
<fragment android:name="it.html.guida.gui.fragments.CityFragment"
android:id="@+id/cityfrag"
android:layout_width="0dp"
android:layout_height="match_parent"
android:layout_weight="2"/>
</LinearLayout>
```
Nel layout two-pane, i fragments appaiono insieme sin dall'inziio mentre le FragmentsTransactions non dovranno più intervenire. Come segnaposti, abbiamo i tag `<fragment>`. Il loro attributo android:name indica quale tipo di Fragment dovrà posizionarsi in ogni collocazione.
