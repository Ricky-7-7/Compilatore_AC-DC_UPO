# Compilatore AC-DC

Progetto universitario per il corso di Fondamenti di Linguaggi e Traduttori (FLT) presso l'Università del Piemonte Orientale (UPO).
Si tratta dell'implementazione completa di un compilatore per il linguaggio didattico "AC-DC", sviluppato interamente in Java.

## ⚙️ Architettura del Compilatore
Il progetto implementa la classica pipeline di compilazione, strutturata in package logici:
* **Scanner:** Analisi lessicale, riconoscimento e generazione dei Token.
* **Parser:** Analisi sintattica (Top-Down) e costruzione dell'albero di sintassi astratta (AST).
* **Symbol Table:** Struttura dati per la gestione degli identificatori, delle dichiarazioni e degli scope.
* **Type Checker:** Analisi semantica e validazione dei tipi (implementato tramite *Visitor Pattern*).
* **Code Generator:** Generazione del codice target finale (implementato tramite *Visitor Pattern*).

## 🛠️ Tecnologie e Pattern
* **Linguaggio:** Java
* **Design Pattern:** Visitor (per separare le operazioni di type-checking e generazione codice dalla struttura dell'AST)
* **Testing:** Suite di test integrata (Scanner, Parser, CodeGen, TypeChecker) con dataset di prova presenti in `src/test/data/`.

## 🚀 Struttura del Progetto
Tutto il codice sorgente è contenuto all'interno della directory `src/`, suddiviso in moduli indipendenti per garantire un'alta coesione e un basso accoppiamento tra le varie fasi della traduzione.