public class Main {

    public static void main(String[] args) {

        String telefones = "seu-telefone";

        String[] lista = telefones.split("\\R");

        for (String telefone : lista) {

            if (telefone.isBlank()) continue;

            try {
                String resultado = TelefoneUtils.padronizarTelefoneBR(telefone);
                System.out.println(resultado);
            } catch (Exception e) {
                System.out.println("INVÁLIDO → " + telefone);
            }
        }
    }
}