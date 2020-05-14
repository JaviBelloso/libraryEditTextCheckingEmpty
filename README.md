# libraryEditTextCheckingEmpty
Libreria Android de Extension para Activity, Appcompat y Fragment, de comprobacion de campos InputText no vacios.

Libreria de Extension de Activitys y fragment para el checking de Views inputText vacios. Realiza el Focus sobre el primero que encuentra.
Incluye posibilidad de lista de Edittext para no hacer el checking sobre ellos, y un mensaje personalizado para el Toast emergente.

Gradle

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


dependencies {
	        implementation 'com.github.JaviBelloso:libraryEditTextCheckingEmpty:Release'
	}


Implementacion.

Extendemos de la clase correspondiente donde se carga el layout:

  extends CheckingEditTestEmptyandFocusAppCompatActivity
  
  extends CheckingEditTestEmptyandFocusActivity
  
  extends CheckingEditTestEmptyandFocusFragment
  
  En el lugar de comprobacion(Button,etc...)
  
    EditText myEditTextExcetion1;
    ArrayList<EditText> listExceptions = new ArrayList<>();   // lista de Excepciones
    listExceptions.add(myEditTextExcetion1);
    String messageToast = "rellena este campo";
    // solo para Fragment
    // setViewGroup((ViewGroup)getView());
    //-----------------
     EditText edittextvacio= getEditTextEmpty(listExceptions,"");
     
     if(edittextvacio==null){
        // do something
        // Todos los campos edittext esta rellenos, no existe ninguno "";
     }else{
        // haz lo que quieras con el edittext
        // edittextvacio
     }
     
   Javadoc page web:
	https://javibelloso.github.io/libraryEditTextCheckingEmpty/
