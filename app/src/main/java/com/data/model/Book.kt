package com.data.model
/**
 * ENTIDADE PRINCIPAL - Representa o modelo de dados de um livro
 * Data class com propriedades imutáveis para representar um livro
 * Atua como Model na arquitetura MVVM
 */

data class Book(
        val title: String,
        val author: String,
        val description: String
)