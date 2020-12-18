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
SQLite è considerato il motori di database più diffuso al mondo. Si tratta, in realtà, di una libreria software che permette di gestire in un unico file un database relazionale.
### Database nelle proprie App
Per avere un database SQLite nella propria App Android, non è neccessario scaricare nè installare niente: semplicemente basta chiedere. La libreria SQLite infatti è già inclusa nel sistema operativo e le API disponibili nel framework offrono tutto il supporto necessario. I passi sono:
* **creare la struttura del database**. Il programmatore dovrà preparare uno script SQL che crei la struttura interna del database (tabelle, viste, ecc...). Nel realizzarla, potrà procedere nella maniere che gli è più congeniale scrivendola a mano o aiutandosi con uno strumento visuale come **Sqliteman**.
* **creare una classe Java che estenda** `SQLiteOpenHelper`. Questa classe servirà a gestire la nascita e l'aggiornamento dek database su memoria fisica e a recuperare un riferimento all'oggetto SQliteDatabase, usato come accesso ai dati.
* **creare una classe per l'interazione con il database.** Solitamente questa ha due caratteristiche: (1) contiene un riferimento all'oggetto helper definito al punto precedente, (2) contiene i metodi con cui, dalle altre componenti dell'app, verrano richieste operazioni e selezioni sui dati.
### Esempio pratico
Creiamo la classe Helper
```
public class DBHelper extends SQLiteOpenHelper {
  public static final String DBNAME = "BILLBOOK";
  public DBHelper(Context context) {
    super(context, DBNAME, null, 1);
  }
  @Override
  public void onCreate(SQLiteDatabase db) {
    String q = "CREATE TABLE" + DatabaseStrings.TBL_NAME + 
      " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
      DatabaseStrings.FIELD_SUBJECT + " TEXT," +
      DatabaseStrings.FIELD_TEXT + " TEXT," +
      DatabaseStrings.FIELD_DATE + " TEXT)";
  db.execSQL(q);
  }
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersione, int newVersion) { }
}
```
Per questioni "oranizzative" del codice, i nomi dei campi e della tabella sono stati definiti in costanti nella sequente classe:
```
public class DatabaseStrings {
  public static final String FIELD_ID = "_id";
  public static final String FIELD_SUBJECT = "oggetto";
  public static final String FIELD_TEXT = "testo";
  public static final String FIELD_DATE = "data";
  public static final String TBL_NAME = "Scadenze";
}
```
Notiamo che per prima cosa viene creato un **costruttore** al cui interno si invoca quello della classe base:
```
super(context, DBNAME, null, 1);
```  
Tra gli argomenti passati ne notiamo due in particolare:
* il **nome del database**: è il secondo parametro, di tipo String, valorizzato con la costante DBNAME. Questo è il nome che il database avrà nello spazio disco dell'applicazione.
* la **versione del database**: è il quarto argomento, di tipo intero e valore 1.  
  
Inoltre è stato fatto l'override di due metodi:
* `onCreate`: viene invocato nel momento in cui non si trova nello spazio dell'applicazione un database con un nome indicato nel costruttore. Da ricordare che onCreate verrà invocato una sola volta, quando il database non esiste ancora. Il parametro passato in input è un riferimento all'oggetto che astrae il database. **La classe SQLiteDatabase è importantissima in quanto per suo tramite invieremo i comandi di gestione dei dati**.
* `onUpgrade`: viene invocato nel momento in cui si richiede una versione del database più aggiornata di quella presente su disco. Questo metodo contiene solitamente alcune query che permettono di adeguare il database alla versione richiesta.  
  
La classe in cui gestiremo il database prende il nome di `DbManager`.
```
public class DbManager {
  private DBHelper dbhelper;
  public DbManager(Context ctx) {
    dbhelper = new DBHelper(ctx);
  }
  public void save(String sub, String txt, String date) {
    SQLiteDatabase db dbhelper.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(DatabaseStrings.FIELD_SUBJECT, sub);
    cv.put(DatabaseStrings.FIELD_TEXT, txt);
    cv.put(databaseStrings.FIELD_DATE, date);
    try {
      db.insert(databaseStrings.TBL_NAME, null, cv);
    } catch (SQLiteException e) {
      //
    }
  }
  public boolean delete(long id) {
    SQLiteDatabase db = dbhelper.getWritableDatabase();
    try {
      if(db.delete(DatabaseString.TBL_NAME, DatabaseString.FIELD_ID + "=?", new String[] {Long.toString(id)}) > 0)
        return true;
      return false;
    } catch (SQLiteException e) {
      return false;
    }
  }
  public Cursor query() {
    Cursor crs = null;
    try {
      SQLiteDatabase db = dbhelper.getReadableDatabase();
      crs = db.query(DatabaseStrings.TBL_NAME, null, null, null, null, null, null, null);
    } catch (SQLiteException e) {
      return null;
    }
    return crs;
  }
}
```
Prima cosa da notare: **la classe contiene un riferimento al DbHelper**.  
I metodi che vengon implementati mostrano tre operazioni basilari da svolgere sulla tabella del db: *save* per salvare una nuova scadenza, *delete* per cancellare una in base all'id, *query* per recuperarne l'intero contenuto.  
Da questi metodi, **emerge un modus operandi comune**. Infatti lavorare su un oggetto SQLiteDatabase, la prima cosa da fare è recuperarne un riferimento. Lo si può fare con i metodi di SQçiteOpenHelper, `getReadableDatabase()` e `getWriteableDatabase()` che restituiscono, rispettivamente, un riferimento al database "in sola lettura" e uno che ne permette la modifica.  
Sull'oggetto SQLiteDatabase recuperato, si svolge una delle quattro operazioni CRUD, le azioni fondamentale della persistenza (Create, Read, Update, Delete).  
Nelle API Android per SQLite esiste almeno un metodo per ogni tipo di azione:
* `query`: esegue la lettura sulle tabelle: mette in pratica il SELECT sui dati. I suoi svariati overload predispongono argomenti per ogni parametro che può essere inserito in una interoggazione di questo tipo (selezione, ordinamento, numero, massimo di record, raggruppamento, etc...).
* `delete`: per la cancellazione di uno o più record della tabella.
* `insert` per l'inserimento. Riceve in input una stringa che contiene il nome della tabella e la lista di valori **ContentValues**. Questa è una struttura a mappa che accetta coppie chiave/valore dove la chiave rappresenta il nome del campo delal tabella.
* `update`: esegue modifiche. Il metodo associa i parametri usati nell'insert e nel delete.  
  
Tutti questi metodi non richiedono un uso esplicito di SQL. Chi ne avesse bisogno o preferisse per altre ragioni scrivere totalmente i propri comandi e query può utilizzare metodi di SQLiteDatabase come `execSQL` e `rawSQL`.  
Vale anche la pena sottolineare che i metodi appena indicati offrono una versione "parametrica" delle condizioni di selezioen dei record (la classica clausolo WHERE di SQL che spesso è indispensabile in selezioni, cancellazioni e aggiornamenti). Ciò e visibile nella classe DBManager, nel metodo che si occupa della cancellazione:
```
db.delete(DatabaseStrings.TBL_NAME, DatabaseStrings.FIELD_ID + "=?", new String[] {Long.toString(id)}) > 0
```
In questi casi, la classe SQLiteDatabase vuole che una stringa raccolga la parte fissa del contenuto della clausola `WHERE` sostituendo le parti variabili con punti interrogativi. Gli argomenti attuali verrano passati ad ogni invocazione in un array di stringhe. Nell'esecuzione della query ogni punto interrogativo verrà, in ordine, sostiuito con un parametro dell'array.  
Altra classe cui fare attenzione è **Cursor**. Rappresenta un puntatore ad un set di risultati della query. Somiglia a quell'elemento che in altre tecnologie prende il nome di `RecordSet` o `ResultSet`. Un oggetto Cursor può essere spostato per puntare ad una riga differente del set di risultati. Ciò viene fatto con i metodi `moveToNext`, `moveTOoFirst`, `moveToLast` e così via.  
