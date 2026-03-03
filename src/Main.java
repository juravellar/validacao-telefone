public class Main {

    public static void main(String[] args) {
        String telefone = "36655951";

        String resultado = TelefoneUtils.padronizarTelefoneBR(telefone);

        System.out.println(resultado);
    }
}