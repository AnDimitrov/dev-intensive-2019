package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16) =
    if (this.trim().length > count) this.trim().take(count).trim().plus("...") else this.trim()

fun String.stripHtml(): String {
    return this
        .replace("\\<.+?>".toRegex(), "")
        .replace("&(?:[a-z0-9]+|#\\d+|#x[a-f0-9]+);".toRegex(), "")
        .replace("\\s+".toRegex(), " ")
}