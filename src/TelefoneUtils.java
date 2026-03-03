public class TelefoneUtils {

    private static final String DDD_PADRAO = "62";

    public static String padronizarTelefoneBR(String telefone) {

        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio");
        }

        telefone = telefone.replaceAll("\\D", "");

        // Remove zero inicial
        if (telefone.startsWith("0")) {
            telefone = telefone.substring(1);
        }

        // Remove 55 se existir
        if (telefone.startsWith("55")) {
            telefone = telefone.substring(2);
        }

        // Se não tiver DDD, adiciona 62
        if (telefone.length() == 8 || telefone.length() == 9) {
            telefone = DDD_PADRAO + telefone;
        }

        if (telefone.length() != 10 && telefone.length() != 11) {
            throw new IllegalArgumentException("Telefone inválido");
        }

        String ddd = telefone.substring(0, 2);
        String numero = telefone.substring(2);

        // Validação simples de DDD
        int dddInt = Integer.parseInt(ddd);
        if (dddInt < 11 || dddInt > 99) {
            throw new IllegalArgumentException("DDD inválido");
        }

        // ==========================
        // 9 DÍGITOS (CELULAR ATUAL)
        // ==========================
        if (numero.length() == 9) {

            if (!numero.startsWith("9")) {
                throw new IllegalArgumentException("Celular inválido: 9 dígitos devem começar com 9");
            }

            return "55" + ddd + numero;
        }

        // ==========================
        // 8 DÍGITOS
        // ==========================
        if (numero.length() == 8) {

            char primeiro = numero.charAt(0);

            // FIXO
            if (primeiro >= '2' && primeiro <= '5') {
                return "55" + ddd + numero;
            }

            // CELULAR ANTIGO → adiciona 9
            if (primeiro >= '6' && primeiro <= '9') {
                return "55" + ddd + "9" + numero;
            }

            throw new IllegalArgumentException("Número inválido");
        }

        throw new IllegalArgumentException("Formato não reconhecido");
    }
}