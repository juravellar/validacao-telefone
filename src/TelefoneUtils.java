public class TelefoneUtils {

    private static final String DDD_PADRAO = "62";

    public static String padronizarTelefoneBR(String telefone) {

        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio");
        }

        telefone = telefone.replaceAll("\\D", "");

        if (telefone.isEmpty()) {
            throw new IllegalArgumentException("Telefone inválido");
        }

        // ===============================
        // 1️⃣ Remover ZERO inicial (prefixo nacional)
        // ===============================
        if (telefone.startsWith("0") && telefone.length() > 10) {
            telefone = telefone.substring(1);
        }

        // ===============================
        // 2️⃣ Verificar e remover 55
        // ===============================
        boolean tinha55 = false;

        if (telefone.startsWith("55")) {
            telefone = telefone.substring(2);
            tinha55 = true;
        }

        // ===============================
        // 3️⃣ Se não tinha 55 e tamanho impossível para BR → internacional
        // ===============================
        if (!tinha55 && telefone.length() > 11) {
            return "INTERNACIONAL:" + telefone;
        }

        // ===============================
        // 4️⃣ Se não tiver DDD → adicionar padrão
        // ===============================
        if (telefone.length() == 8 || telefone.length() == 9) {
            telefone = DDD_PADRAO + telefone;
        }

        if (telefone.length() != 10 && telefone.length() != 11) {
            throw new IllegalArgumentException("Formato inválido");
        }

        String ddd = telefone.substring(0, 2);
        String numero = telefone.substring(2);

        validarDDD(ddd);

        // ===============================
        // 🔥 CORREÇÃO — 9 duplicado (99XXXXXXXX)
        // ===============================
        if (numero.length() == 10 && numero.startsWith("99")) {

            String possivelCorrecao = numero.substring(1);

            if (possivelCorrecao.length() == 9 && possivelCorrecao.startsWith("9")) {
                numero = possivelCorrecao;
            }
        }

        // ===============================
        // 5️⃣ Celular com 9 dígitos
        // ===============================
        if (numero.length() == 9) {

            if (!numero.startsWith("9")) {
                numero = "9" + numero;
            }

            return "55" + ddd + numero;
        }

        // ===============================
        // 6️⃣ Número com 8 dígitos
        // ===============================
        if (numero.length() == 8) {

            char primeiro = numero.charAt(0);

            // FIXO
            if (primeiro >= '2' && primeiro <= '5') {
                return "55" + ddd + numero;
            }

            // CELULAR ANTIGO
            if (primeiro >= '6' && primeiro <= '9') {
                return "55" + ddd + "9" + numero;
            }

            throw new IllegalArgumentException("Número inválido");
        }

        throw new IllegalArgumentException("Formato não reconhecido");
    }

    private static void validarDDD(String ddd) {
        int dddInt = Integer.parseInt(ddd);

        if (dddInt < 11 || dddInt > 99) {
            throw new IllegalArgumentException("DDD inválido");
        }
    }
}