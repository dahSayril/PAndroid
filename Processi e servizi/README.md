# Processi e servizi
* [Threading](#threading)
## Threading
Le reattività dell'interfaccia è uno degli aspetti delle applicazioni più determinanti per il successo presso il pubblico e la longevità sul market. Operazioni "lente" dovrebbero essere preferibilmente svolte
su thread secondari, detti anche *worker thread*. Inoltre l'accesso in Rete, importantissimo nella programmazione moderna ma contraddistinto da tempi di latenza variabili, deve obbligatoriamente essere eseguito su un thread secondario.  
Android non permette ai thread in background di interagire con l'interfaccia utente, solo il main thread può farlo. 
## AsyncTask
Il framework offre un'alternativa che permette di usare un thread secondario in maniera corretta senza il problema di dover gestire la comunicazione tra thread: la classe **AsyncTask**.  
Immaginiamo di avere un'activity che contiene un solo pulsante. Al click di tale controllo viene **attivato un lavoro in background**. In questo caso si tratterà di un lavoro puramente fittizio: a scopo di esempio lasceremo il thread in attesa per alcuni secondi tanto per generare ritardo. A scandire i tempi dell'attività in background, ci sarà una finestra di dialogo di tipo ProgressDialog.  
Il layout dell'activity è molto semplice, contiene infatti un solo pulsante collacto in posizione centrale.
```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent" >
<Button
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_centerInParent="true"
android:onClick="start"
android:text="@string/btn_start" />
</RelativeLayout>
```
Il codice Java della classe invece è il seguente:
```
public class MainActivity extends Activity {
  ProgressDialog progress = null;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  progress = new ProgressDialog(MainActivity.this);
  progress.setMax(100);
  progress.setMessage(getString(R.string.progress_msg));
  progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
  progress.setCancelable(false);
  }
  public void start(View v) {
    new BackgroundTask().execute();
  }
  private class BackgroundTask extends AsyncTask <Void, Integer, String> {
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progress.setProgress(0);
      progress.show();
    }
    @Override
    protected String doInBackground(Void... arg0) {
      try {
        for(int i = 0; i < 10; i++) {
          publishProgress(new Integer[] {i * 10});
          Thread.sleep(1200);
        }
      } catch (InterruptedException e) { }
      return "Lavoro Terminato!";
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
      super.onProgressUpdate(values);
      progress.setProgress(values[0].intValue());
    }
    @Override
    protected void onPostExecute(String result) {
      super.onPostExecute(result);
      progress.dismiss();
      Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
    }
  }
}
```
Al click del pulsante viene invocato il metodo `start()` al cui interno si istanzia la classe BackgroundTask e la si manda in esecuzione direttamente. La classe *BackgroundTask estende AsyncTask* e pertanto rappresenta il cuore dell'esempio.  
I metodi contenuti nella classe BackgroundTask sono di due tipi diversi. **Il metodo doInBackground è l'unico di quelli implementati che viene eseguito su un thread secondario**. Qui dovremo collocare tutte le operazioni "lente".  
Al contrario, `onPreExecute`, `onPostExecute` e `onProgressUpdate` sono eseguiti sul thread principale e **si occupano della comunicazione tra thread**.  
Rispettivamente:
* `onPreExecute`: inizializza le operazioni prima che avvenga l'esecuzione di doInBackground. Nello specifico prepara la ProgressDialog e la mostra.
* `onPostExecute`: viene eseguito alla fine di doInBackground ed anch'esso svolge operazioni collegate all'interfaccia utente, provocando l'apparizione di un Toast.
* `onProgressUpdate`: viene invocato ogni volta che all'interno di doInBackground viene chiamato publichProgess. Serve a fornire aggiornamenti periodici all'interfaccia utente ed in questo caso a spostare la barra di progresso in avanti di un passo.  
  
Un altro aspetto di AsyncTask cui si deve prestare attenzione sono i parametri della classe. Nell'esempio, **BackgroundTask estende la versione** `<Void, Integer, String>` **di AsyncTask**. Questi tre tipi di dato saranno, rispettivamente, il tipo di dato accettato in input dai metodi `doInBackground`, `onProgressUpdate`, `onPostExecute`, infatti la classe Java generica é
```
class AsyncTask <Params, Progress, Result> {
  ...
}
```

