package utilidades;
import java.util.Random;


public class Placa {

    //método para gerar placa aleatória
    public static String gerarPlacaMercosul(){
        Random r = new Random();

        char letra1 = (char)(r.nextInt(26) + 'A');
        char letra2 = (char)(r.nextInt(26) + 'A');
        char letra3 = (char)(r.nextInt(26) + 'A');
        int numero1 = r.nextInt(10);
        char letra4 = (char)(r.nextInt(26) + 'A');
        int numero2 = r.nextInt(10);
        int numero3 = r.nextInt(10);

        return "" + letra1 + letra2 + letra3 + numero1 + letra4 + numero2 + numero3;
    }


    //método para validar placa
    public static boolean validarPlaca(String placa){
        boolean mercosul = placa.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}");
        boolean antiga = placa.matches("[A-Z]{3}-?[0-9]{4}");
        return mercosul || antiga;
    }


    //método para converter placa antiga na nova
    public static String converterPlaca(String antiga){
        //variável para remover - e espaçamentos
        String limpa = antiga.replace("-", "").toUpperCase();
        //variavel para diferenciar letras e numeros
        String letras = limpa.substring(0, 3);
        String numeros = limpa.substring(3);
        // extrai cada letra
        char n1 = numeros.charAt(0);
        int n2 = Character.getNumericValue(numeros.charAt(1));
        char n3 = numeros.charAt(2);
        char n4 = numeros.charAt(3);
        //converter
        char letraConvertida = (char) ('A' + n2);
        //novo formato
        return letras + n1 + letraConvertida + n3 + n4;
    }



    //método para padronizar, coverter e gerar nova placa
    public static String formatarPlaca(String placaEntrada) {
        if (placaEntrada == null) return null;

        placaEntrada = placaEntrada.toUpperCase().trim().replace(" ", "");

        if (placaEntrada.matches("[A-Z]{3}-?[0-9]{4}")) {
            return converterPlaca(placaEntrada);
        } else if (placaEntrada.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}")) {
            return placaEntrada;
        } else {
            return gerarPlacaMercosul();
        }
    }
}
