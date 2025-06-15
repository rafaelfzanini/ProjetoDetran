package utilidades;

public class CPF {
    public static boolean validarCPF(String cpf){

        //verificar número de caracteres
        if (cpf==null || cpf.length()!= 11){
            return false;
        }

        //verificar se todos são iguais
        boolean todosIguais = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais){
            return false;
        }


        //converte String para int
        int [] numeros = new int[11];
        for (int i = 0; i < 11; i++){
            numeros[i] = cpf.charAt(i) - '0';
        }


        //calculo digito validador 1
        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += numeros[i] * (10 - i);
        }
        int digito1 = (soma1 * 10) % 11;
        if (digito1 == 10) {
            digito1 = 0;
        }
        if (numeros[9] != digito1) {
            return false;
        }


        //calculo digito validador 2
        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += numeros[i] * (11 - i);
        }
        int digito2 = (soma2 * 10) % 11;
        if (digito2 == 10) {
            digito2 = 0;
        }
        if (numeros[10] != digito2) {
            return false;
        }


        //CPF sem erros
        return true;
    }
}
