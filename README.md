# 📞 Validador e Normalizador de Telefone Brasileiro

Este projeto é um utilitário em Java para **validar, corrigir e padronizar números de telefone brasileiros** para o formato internacional:

```
55 + DDD + número
```

Exemplo:

```
62996655951 → 5562996655951
96655951 → 5562996655951
062996655951 → 5562996655951
```

---

## 🎯 Objetivo

Garantir que qualquer telefone informado:

* Contenha apenas números
* Tenha DDD (adiciona 62 caso não tenha)
* Corrija celular antigo (8 dígitos → adiciona 9)
* Corrija erro comum de 9 duplicado (99XXXXXXXX)
* Remova prefixo nacional (0)
* Normalize para o formato internacional brasileiro
* Identifique números internacionais
* Lance exceção caso seja inválido

---

## 📌 Regras Aplicadas

### 1️⃣ Limpeza

* Remove qualquer caractere não numérico
* Remove zero inicial usado como prefixo nacional (ex: `062...`)
* Remove código `55` antes da validação interna

---

### 2️⃣ DDD

* Se o número tiver apenas 8 ou 9 dígitos → assume ausência de DDD
* Adiciona DDD padrão: **62**
* Valida se o DDD está entre 11 e 99

---

### 3️⃣ Celular (Formato Atual)

* 9 dígitos
* Deve começar com **9**
* Se não começar com 9 → o sistema adiciona automaticamente

---

### 4️⃣ Celular Antigo

* 8 dígitos
* Começa com **6, 7, 8 ou 9**
* O sistema adiciona automaticamente o dígito 9

---

### 5️⃣ Correção de 9 Duplicado

Caso comum em bases importadas:

```
629982137766
```

Após DDD:

```
9982137766
```

O sistema detecta:

* 10 dígitos
* Começa com 99
* Remove o 9 duplicado automaticamente

---

### 6️⃣ Telefone Fixo

* 8 dígitos
* Começa com **2, 3, 4 ou 5**
* Não adiciona 9

---

### 7️⃣ Internacional

Retorna:

```
INTERNACIONAL:numero
```

Somente quando:

* Não começa com 55
* Não se encaixa em padrão brasileiro
* Possui tamanho incompatível com BR

---

## 🚀 Como Usar

```java
String telefone = "96655951";
String resultado = TelefoneUtils.padronizarTelefoneBR(telefone);
System.out.println(resultado);
```

Saída:

```
5562996655951
```

---

## 🔎 Exemplos

| Entrada       | Saída                       |
| ------------- | --------------------------- |
| 96655951      | 5562996655951               |
| 6296655951    | 5562996655951               |
| 62996655951   | 5562996655951               |
| 062996655951  | 5562996655951               |
| 629982137766  | 5562982137766               |
| 5511930413418 | 5511930413418               |
| 2181999267100 | INTERNACIONAL:2181999267100 |
| 12345         | ❌ IllegalArgumentException  |

---

## ⚠️ Exceções

O sistema lança `IllegalArgumentException` quando:

* O telefone é nulo ou vazio
* O tamanho é inválido
* O DDD é inválido
* O formato não corresponde a fixo ou celular

---

## 🧠 Estrutura do Projeto

```
Main.java
TelefoneUtils.java
```

* `Main` → Executa exemplos simples
* `TelefoneUtils` → Contém toda a lógica de validação e padronização

---

## 📦 Possíveis Melhorias Futuras

* Criar classe para leitura de planilhas (CSV / XLSX)
* Validar contra lista oficial de DDDs existentes
* Criar objeto de domínio imutável `TelefoneBR`
* Adicionar enum de resultado (VALIDO, AJUSTADO, INTERNACIONAL)
* Criar modo STRICT vs IMPORTAÇÃO
* Adicionar testes unitários (JUnit)
* Criar versão para API REST

---

## 📄 Licença

Uso educacional e interno.

---
