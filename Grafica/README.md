# Grafica
* [Introduzione](#introduzione)
* [Animazioni con XML](#animazioni-con-xml)
* [Custom Views](#custom-views)
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

