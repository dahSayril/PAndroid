# Interfaccia grafica (GUI)
* [Il layout di un'app Android](#il-layout-di-unapp-android)
* [Definire layout in XML](#definire-layout-in-xml)
* [View: le basi dell'interfaccia grafica](#view-le-basi-dellinterfaccia-grafica)
* [Controlli utente](#controlli-utente)
* [Creare un menu](#creare-un-menu)
## Il layout di un'app Android
La struttura grafica di un'activity prende il nome di **Layout**. Esistono tre modi per **creare interfacce utenti in Android**:
* procedurale, ovvero l'implementazione dell'interfaccia grafica nel codice.
* dichiarativa, non richiede la scrittura di codice: l'esempio tipico del metodo dichiarativo è rappresentato dalla creazione di una pagina web statica, in cui utilizziamo l'HTML per descrivere cosa vogliamo vedere nella pagina.
* visuale, metodo totalmente affidato alla potenza degli ambienti di sviluppo. Le capacità di tali strumenti mette a disposizione del programmatore un ambiente completo per creare interfacce utente a colpi di mouse e drag-and-drop.
Android permette la creazione di interfacce sia procedurali sia dichiarative: possiamo creare un'interfaccia utente completamente in codice Java (metodo procedurale), oppure possiamo creare l'interfaccia utente attraverso un descrittore XML (metodo dichiarativo).
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

