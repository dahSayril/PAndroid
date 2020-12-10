# Ciclo di vita delle attività
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
* **onDestroy**  
  
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
