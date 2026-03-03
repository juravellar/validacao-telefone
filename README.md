## 📞 Validador e Padronizador de Telefone Brasileiro

Este projeto é um utilitário simples em Java para **validar e padronizar números de telefone brasileiros** para o formato internacional:

```
55 + DDD + número
```

Exemplo:

```
62996655951 → 5562996655951
96655951 → 5562996655951
```

---

## 🎯 Objetivo

Garantir que qualquer telefone informado:

* Tenha apenas números
* Possua DDD (adiciona 62 caso não tenha)
* Tenha o dígito 9 caso seja celular antigo
* Esteja no formato internacional brasileiro
* Lance exceção caso seja inválido

---

## 📌 Regras Aplicadas

### 1️⃣ Limpeza

* Remove qualquer caractere não numérico
* Remove zero inicial (ex: 062...)
* Remove código 55 antes da validação interna

### 2️⃣ DDD

* Caso o número tenha 8 ou 9 dígitos, assume ausência de DDD
* Adiciona DDD padrão: **62**
* Valida se o DDD está entre 11 e 99

### 3️⃣ Celular

Formato atual:

* 9 dígitos
* Deve começar com **9**

Formato antigo:

* 8 dígitos
* Pode começar com **6, 7, 8 ou 9**
* O sistema adiciona automaticamente o dígito 9

### 4️⃣ Telefone Fixo

* 8 dígitos
* Começa com **2, 3, 4 ou 5**
* Não adiciona 9

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

| Entrada     | Saída         |
| ----------- | ------------- |
| 96655951    | 5562996655951 |
| 6296655951  | 5562996655951 |
| 62996655951 | 5562996655951 |
| 32123456    | 556232123456  |
| 12345       | ❌ Exception   |

---

## ⚠️ Exceções

O sistema lança `IllegalArgumentException` quando:

* O telefone é nulo ou vazio
* O tamanho é inválido
* O DDD é inválido
* O formato não corresponde a fixo ou celular

---

## 🧠 Estrutura

```
Main.java
TelefoneUtils.java
```

* `Main` → Executa exemplos
* `TelefoneUtils` → Contém a lógica de validação e padronização

---

## 📦 Possíveis Melhorias Futuras

* Validar contra lista oficial de DDDs existentes
* Criar objeto de domínio imutável `TelefoneBR`
* Adicionar testes unitários (JUnit)
* Criar versão para API REST
* Adicionar logs estruturados

---

## 📄 Licença

Uso educacional e interno.

---

Desenvolvido para fins de estudo e higienização de base de dados.
