package mx.com.dalvik;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {
	
	
	public static void main(String args[]){
		
		
		// compilamos el patron
		Pattern patron = Pattern.compile("a*b");
		
		
		// creamos el Matcher a partir del patron, la cadena como parametro
		Matcher encaja = patron.matcher("aabexampleaabexampleabexampleb");
		
		
		// invocamos el metodo replaceAll
		String resultado = encaja.replaceAll("-");
		System.out.println(resultado);
		
	}
	
	
	   public static void __main(String[] args) throws Exception {                
		      String input = "www.?regular.com";
		      
		      
		      // comprueba que no empieze por punto o @
		      Pattern p = Pattern.compile("^.|^@");
		      Matcher m = p.matcher(input);
		      if (m.find())
		         System.err.println("Las direcciones email no empiezan por punto o @");
		         
		      // comprueba que no empieze por www.
		      p = Pattern.compile("^www.");
		      m = p.matcher(input);
		      if (m.find())
		        System.out.println("Los emails no empiezan por www");

		      // comprueba que contenga @
		      p = Pattern.compile("@");
		      m = p.matcher(input);
		      if (!m.find())
		      	System.out.println("La cadena no tiene arroba");
		      	
		      // comprueba que no contenga caracteres prohibidos	
		      p = Pattern.compile("[^A-Za-z0-9.@_-~#]+");
		      m = p.matcher(input);
		      StringBuffer sb = new StringBuffer();
		      boolean resultado = m.find();
		      boolean caracteresIlegales = false;

		      while(resultado) {
		         caracteresIlegales = true;
		         m.appendReplacement(sb, "");
		         resultado = m.find();
		      }

		      // Añade el ultimo segmento de la entrada a la cadena
		      m.appendTail(sb);

		      input = sb.toString();

		      if (caracteresIlegales) {
		         System.out.println("La cadena contiene caracteres ilegales");
		      }
		   }
	   
    public static void ___main(String[] args) {
        String url = "http://localhost/blog/booking/?record_locator=FN0RGA";

        Pattern p = Pattern.compile("record_locator=((\\d|\\w){6})");

        Matcher matcher = p.matcher(url);
        if (matcher.find()) {
        	
            System.out.println(matcher.group(1));
        }
    }
}
