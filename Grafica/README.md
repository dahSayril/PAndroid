# Grafica e Media, Sensori, Notifiche e Alarms
* [Introduzione](#introduzione)
* [Animazioni con XML](#animazioni-con-xml)
* [Custom Views](#custom-views)
* [Multitouch](#multitouch)
* [Media Player](#media-player)
* [Sensori](#sensori)
* [Notifiche](#notifiche)
* [Alarms](#alarms)
## Introduzione
Un'immagine può essere disegnata in un oggetto:
* **View**, grafica semplice, senza necessità di cambiamenti.
* **Canvas**, grafica complessa, aggiornamenti frequenti.  
  
La classe Drawable rappresenta un oggetto che può essere disegnato, come:
* un'immagine, ma anche un colore, una forma, etc...
* ShapeDrawable, una forma.
* BitmapDrawable, una matrice di pixels.
* ColorDrawable, un colore (uniforme).
## Animazioni con XML
L'oggetto Drawwable deve essere inserito nell'oggetto View, direttamente nel file XML o in modo programmatico tramite `View.setImagineDrawable()`.  
Android permette di definire delle animazioni da applicare alle immagini. Possono essere descritte con file XML, e quindi specificare:
* rotazione.
* traslazione.
* scaling (dimensione).
* trasparenza.
* con controllo di vari parametri.  
  
Le animazioni, come altre cose nei nostri progetti, sono *risore*. La loro configurazione in XML dovrà essere inserita in un file all'interno della cartella *res/anim*.  
Per l'esempio che utilizzeremo, predisporremo un semplice layout con un testo "Hello world" in posizione centrale, ed un pulsante in alto a sinistra con su scritto "Attiva animazione".
Alla pressione di quest'utilmo controllo, l'animazione verrà avviata e sarà applicata alla scritta "Hello world". Data la facilità di configurazione delle animazioni in XML, le direttive 
saranno inserite nel file *res/anim/animazione.xml* e sarà sufficiente sostituire il contenuto per sperimentare nuove animazioni.  
Il **layout** che utilizzeremo è il seguente:
```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent">
  <Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_alignParentTop="true"
    android:layout_alignParentLeft="true"
    android:text="Avvia animazione"
    android:onClick="avvia"/>
  <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_centerInParent="true"
    android:id="@+id/txt"
    android:text="@string/hello_world" />
</RelativeLayout>
```
Di seguito è mostrato, invece, il codice Java dell'activity:
```
public class MainActivity extends Activity {
  private Animation anim = null;
  private TextView txt = null;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    txt = (TextView) findViewById(R.id.txt);
    anim = AnimationUtils.loadAnimation(getApplicationContext(),
      R.anim.animazione);
  }
  public void avvia(View v) {
    txt.startAnimation(anim);
  }
}
```
Come si vede, è molto semplice:
* la classe **AnimationUtils** che, all'interno del metodo `onCreate`, carica l'animazione semplicemente richiamandone l'ID di risorsa.
* il metodo **startAnimation** che attiva l'animazione sulla View, in questo caso una *TextView*.  
  
Ciò che manca è **completare il file dell'animazione**, che abbiamo deciso di chiamare *animazione.xml*. Come primo esperimento realizzeremo una rotazione a 360 gradi, ripetuta 3 volte:
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
  <rotate android:fromDegrees="0"
    android:toDegrees="2000"
    android:pivotX="50%"
    android:pivotY="50%"
    android:duration="360"
    android:repeatMode="restart"
    android:repeatCount="3"
    android:interpolator="@android:anim/cycle_interpolatoR"/>
</set>
```
I nuovi tag impiegati sono due **<set>** che racchiude l'insieme delle animazioni e **<rotate>**. specifico per l'operazione da effettuare. Ciò ci consentirà di eseguire l'animazione al click dell'apposito pulsante.  
Fatto questo, potremo provare un effetto di ingrandimento con il tag **<scale>**, semplicemente sostituendo il contenuto del file *animazione.xml*:
```
<scale
  xmlns:android="http://schemas.android.com/apk/res/android"
  android:duration="1000"
  android:fromXScale="1"
  android:fromYScale="1"
  android:toXScale="4"
  android:toYScale="4"
  android:pivotX="50%"
  android:pivotY="50%">
</scale>
```
Sottolineaiamo alcuni spunti interessanti:
* il tag **<set>** non deve necessariamente contenere una singola animazione, anzi il suo stesso nome richiama il concetto dell'insieme e lascia intendere che al suo interno possono essere concentrate più direttive XML.
* impostando opportunamente gli attributi sarà possibile ottenere effetti finali molto diversi. Oltre a <scale> e a <rotate> ci sono altri tag molto utili, come <alpha> che regola le variazioni di trasparenza permettendo di creare effetti di dissolvenza, e <translate> che permette di ordinare spostamenti delle View.
* se si vogliono effettuare **sequenze di animazioni**, ed eseguire operazioni tra di esse, è possibile trasformare l'activity o un altro oggetto in un listener per i relativi eventi. Ciò viene fatto implementando l'interfaccia **AnimationListener** 
e comunicandolo all'instanza *Animation* con il metodo `setAnimationListener`. Fatto questo, si inserirà il codice nei metodi di cui è obbligatorio fornire l'override: `onAnimationStart`, `onAnimationEnd` e `onAnimationRestart`.
## Custom Views
Android ha molti widget, ma, per esigenze particolari, possiamo definire dei widget personalizzati. Questo ci permette un maggiore controllo sulla grafica, ovviamente sono però più complicati da usare.  
Il meccanismo di layout è composto di 3 fasi: Misura, Posizionamento e Disegno.  
### Fase di misurazione
* "measured" size (width & height), ovvero quanto granda la view vorrebbe essere.
* size reale, ovvero quanto grande la view sarà in realta.  
  
Approccio top-down nell'albero: ogni view chiede ai suoi figli quanto vorrebbere essere grandi utilizzando la classe MeasureSpec.
```
int widthMode = MeasureSpec.getMode(widthMeasureSpec);
int width = MeasureSpec.getSize(widthMeasureSpec);
int heightMode = MeasuereSpec.getMode(heightMeasureSpec);
int height = MeasureSpec.getSize(heightMeasureSpec);
```
width e height sono in pixels, mentre widthMode e heightMode hanno come valore uno di questi tre:
* MeasureSpec.EXACTLY
* MeasureSpec.AT_MOST
* MeasureSpec.UNSPECIFIED  
  
Ogni view può esprimere la propria preferenza usando le opzioni della classe ViewGroup.LayoutParams:
* un numero (di pixel)
* MATCH_PARENT
* WRAP_CONTENT  
  
La misurazione avviene nel metodo onMeasure.
Quando il processo di misurazione finisce, ogni view deve aver definito `measuredWidth` e `measuredHeight`. In alcuni casi c'è un processo di negoziazione fra view parent e view figli (measure() chiamato più volte).
### Fase di layout
Visita top-down dell'albero delle view.  
View parent decide la grandezza e la posizione (in accordo alle misure fatte nella fase precedente). Questo avviene nel metodo `onLayout()`.  
### Fase di disegno
Dopo il posizionamento ogni view viene disegnata, metodo `onDraw()`. Quando c'è un cambiamento, `invalidate()`, chiama il metodo `onDraw()` sulle view.  
Il metodo requestLayout ripete l'intero processo su tutto l'albero.
### Meccanismo di layout
Il meccanismo di layout inizia quando viene chiamato il metodo requestLayout su una View dell'albero. Solitamente un widget chiama requestLayout quando ha bisogno di altro spazio.  
requestLayout mette un evento nella coda degli eventi UI; Quando l'evente viene processato, ogni container view ha la possibilità di interagire con i figli.
```
publi class MyView extends Views {
  public MyView(Context context) {
    super(context);
  }
  ...
  @Override
  public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeassuredDimension(
      getSuggestedMinimumWidth(),
      getSuggestedMinimumHeigh());
  }
  ...
}
```
Nella fase di layout le view container comunicano la posizione effettiva ad ogni view figlio.
```
public class MyView extends Views {
  ...
  @Override
  public void onLayout(int x1, int y1, int x2, int y2) {
    Log.d("DEBUG", "onLayout");
    Log.d("DEBUG", cordinate x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2);
    int smw = getSuggestedMinimumWidth();
    int smh = getSuggestedMinimumHeight();
    Log.d("DEBUG", "onLayout smw=" + smw + ", smh=" + smh);
    setMeasuredDimension(smw, smh);
  }
  ...
}
```
Quando la view è stata posizionata verrà disegnata con il metodo onDraw.
```
public class MyView extends Views {
  ...
  @Override
  public void onDraw(Canvas canvas) {
    // Codice per disegnare la view
  }
  ...
}
```
## Multitouch
MotionEvent: rappresenta un movimento registrato da una periferica, come una penna, trackball, mouse, dita sul display.  
Il movimento è rappresentato con ACTION_CODE (cambiamento avventuo) e ACTION_VALUES (posizione e proprietà del movimento: tempo, sorgente, pressione e altro).  
Il "pointer" è il signolo evento (es. un dito che tocca lo schermo). Un MotionEvent rappresenta, un singolo pointer o, a volte, più di un pointer (in questo caso possiamo accedere ai singoli pointer usando un indice). Ogni pointer ha un ID unico per tutto il tempo in cui esiste. L'indice di un MotionEvent multiplo NON è il pointer ID: il pointer ID è costante, mentre l'indicie può cambiare per eventi successivi.  
MotionEvents ACTION_CODES:
* ACTION_DOWN: un dito toccca lo scjermo ed è il primo.
* ACTION_POINTER_DOWN: un dito tocca lo schermo ma non è il primo.
* ACTION_MOVE: un dito che è sllo schermo si muove.
* ACTION_POINTER_UP: un dito che è sullo schermo non lo tocca più.
* ACTION_UP: l'ultimo dito sullo schermo viene alzato.  
  
 Per gestire i MotionEvent:
 * `getActionMasked()`: restituisce l'action code dell'evento.
 * `getPointerCount()`: numero di pointer coinvolti.
 * `getActionIndex()`: indice di un pointer.
 * `getPointerID(int pointerIndex)`.
 * `getX(int pointerIndex)`.
 * `getY(int pointerIndex)`.
 * `findPointerIndex(int pointerId)`.  
   
 Android notifica l'oggetto View con il metodo `View.onTouchEvent(MotionEvent e)`. onTouchEvent() deve restituire un boolean, ovvero true, se l'evento è stato consumato, false altrimenti. Gli oggetti che vogliono ricevere la notifica devono usare i metodi:
* `View.onTouchListener()`.
* `View.setOnTouchListener()`.  
  
onTouch verrà invocata quando c'è un evento (finger down, up o movimento). Essa viene chiamata prima che la View venga notificata dell'evento. Anche onTouch deve restituire un boolean, ovvero true, se l'evento è stato consumato, false altrimenti.  
Spesso si ha la necessità di gestire una combinazione di evente. Esempio il doppio "click" equivale a:
* ACTION_DOWN
* ACTION_UP
* ACTION_DOWN
* ACTION_UP
* in rapida successione.  
  
### GestureDetector
La classe GestureDetector permette di riconoscere dei gesti fatti sul display, come ad esempio:
* pressione semplice.
* doppia pressione (doppio "click").
* fling (scorrimento).  
  
Bisogna creare un GestureDetector che implementa l'interfaccia GestureDetector.OnGestureListener interface.  
Riscrivere (override) il metodo `onTouchEvent` che viene chiamato ad un gesto. Questo delega il riconoscimento del gesto al metodo GestureDetector.OnGestureListener.  
### ViewAnimator e ViewFlipper
ViewAnimator è una classe per un contenitore di tipo FrameLayout (animazione cambiamento fra View).
Simple ViewAnimator è una sottoclasse di ViewAnimator. Crea animazione fra 2 o più view del contenitore. Solo una view per volta viene visualizzata e permette anche di cambiare views ad intervalli regolari. I metodi di questa classe sono `showNext()` e `showPrevious()`.
## Media Player
* AudioManager: controlla le sorgenti audio e l'output (volume).
* MediaPlayer: play di audio e video.
* Sorgente dati: risorse locali, uri (interni), url.  
  
Play di un file in res/raw
```
MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.sound_file);
mediaPlayer.start();
```
Play di un file da URL
```
String url = "http://...";
MediaPlayer mediaPlayer = new MediaPlayer();
mediaPLayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
mediaPlayer.setDataSource(url); // richiede la gestione di IOExceptione o di IllegalArgumentException
mediaPlayer.prepare();
mediaPlayer.start();
```
Rilasciare la risorsa
```
mediaPlayer.release();
mediaPlayer = null;
```
I metodi di mediaPlayer sono:
* `mediaPlayer.start()`.
* `mediaPlayer.pause()`.
* `mediaPlayer.stop()`.  
  
Attenzione all'uso asincrono (prepareAsynch). Necessario per non appesantire l'app.  
Poiché c'è un solo canale di output, l'utilizzo da parte di più applicazioni può essere un problema, esempio: se stiamo ascoltando musica potremmo non sentire l'arrivo di un messaggio. E' possibile gestire l'accesso contemporaneo usando l'audio focus: un'app richiede l'audio focus per usare l'audio, e, se lo perde, deve o smetttere di suonare o abbassare il proprio volume. 
## Sensori
Molti smartphones, tablet hanno sensori di movimento (forze di accelerazione e di rotazione), di ambiente (temperatura, pressione, umidità), di posizione e quant'altro.  
Questi sensori forniscono dati "grezzi", ovvero l'accuratezza dipende dalla qualità.  
Il SensorManager ci dice quali sensori sono disponibili e le caratteristiche del singolo sensore (range massimo, accuratezza, etc...), e questo ci permette di leggere i dati grezzi del sensore e la posibilità di usare dei Listeners per poter sfruttare questi cambiamenti dei dati.  
Per gestire i sensori, l'attività deve implementare SensorEventListener:
```
public class SensorActivity extends Activity implements SensorEventListener {
  ...
}
```
Poi si deve controllare se il sensore esiste:
```
private SensorManager sM;
...
sM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
if(sM.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
  // Success! Thre's an ambient light sensor.
} else {
  // Failure! No light sensor
}
```
Per evitare di consumare la batteria, la registrazione e rilascio vengono effettuate nei metodi onResume e onPause:
```
@Override
protected void onResume() {
  super.onResume();
  sM.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
}
@Override
protected void onPause() {
  super.onPause();
  sM.unregisterListener(this);
}
```
## Notifiche
Le notifiche sono informazioni all'utente al di fuori dell'interfaccia grafica dell'app. Esistono diversi tipi di notifiche:
* toast: brevi messaggi temporanei.
* dialog: interazione con l'utente.
* notifiche: status bar e cassetto delle notifiche.  
  
Le notifiche vengono mostrate sulla barra di stato come icone, cerchietto sull'icona e nel "cassetto delle notitifhce" con testo aggiuntivo.  
Da Android 5.0 (API 21) vi sono anche le notifiche a comparsa (heads-up notification), dove compare anche un messaggio temporaneo simile a un toast.  
Da Android 8.0 (API >= 26) vi sono i "canali" di notifica. Prima in pratica ogni app aveva un singolo "canale". Ora ogni notifica deve essere "assegnata" a un canale, altrimenti non viene visualizzata. I canali possono essere controllati dall'utente che può disabilitare quindi solo determinati canali e contraollarne il comportamento (suoni, visualizzazione).  
Sistema di notifiche è in continua evoluzione. Conviene usare le classi NotificationCompat e NotificationManagerCompat per ottenere la migliore compatibilità possibile:
```
dependencies {
  implementation "com.android.support:suppoer-compat:28.0.0"
}
```
A partire dall'API level 26, l'icono dalle notifica deve essere un'immagine con il fondo trasparente e un solo colore, altrimenti compare un quadrato bianco.
## Alarms
Permettono di eseguire intent in funzione di specifici eventi: intent lanciati in base al tempo.  
Un'applicazione che usa un alarm riesce ad eseguire porzioni di codice anche se l'applicazione è terminata, cioè gli alarm permettono di "attivare" un'app.  
Un alarm è attivo anche se il telefono va in modalità di sleep:
* l'alarm può causare la ripresa dell'attività,
* oppure potrà essere gestito quando l'utente rimette il telefono in modalità normale.  
  
Gli alarms rimangono attivi fino a quando non vengono cancelli o la periferica viene spenta. Esempi di alarms:
* app per gli MMS: usa alarms per controllare periodicamente i messaggi non spediti (retry scheduler).
* settings: usa un alarm per rendere la periferica non visibile via Bluetooth dopo un determinato tempo.  
  
Per usare gli alarm in un'app si deve implementare AlarmManager.  
Ottenere un riferimento all'AlarmManager:
```
getSystemService(Context.ALARM_SERVICE)
```
Creare alarms
```
void set(int type, long triggerAtTime, PendingIntent i);
void setRepeating(...), setInexactRepeating(...);
```
A partire dall'API level 19, gli alarm non sono "esatti": il SO può modificare i triggerTime per minimizzare wakeups e l'uso della batteria.  
Esitono vari tipi di Alarm:
* ELAPSED_REALTIME (time since boot): lancia il "pending" intent dopo un determinato tempo (ma non fa il wakeup della device).
* ELAPSED_REALTIME_WAKEUP: se la device non è attiva fa il wakeup e lancia il "pending" intent.
* RTC (UTC time): lancia il pending event ad un determinato orario.
* RTC_WAKEUP: come prima ma fa il wakeup se serve.
