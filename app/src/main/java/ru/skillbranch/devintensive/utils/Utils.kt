package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName?.ifEmpty { null } to lastName?.ifEmpty { null }
    }

    fun transliteration(payload: String, divider: String = " ") = payload.map {
        val isUpperCase = it.isUpperCase()
        val letter = when (it.toLowerCase()) {
            'а' -> "a"
            'б' -> "b"
            'в' -> "v"
            'г' -> "g"
            'д' -> "d"
            'е', 'ё', 'э' -> "e"
            'ж' -> "zh"
            'з' -> "z"
            'и', 'й', 'ы' -> "i"
            'к' -> "k"
            'л' -> "l"
            'м' -> "m"
            'н' -> "n"
            'о' -> "o"
            'п' -> "p"
            'р' -> "r"
            'с' -> "s"
            'т' -> "t"
            'у' -> "u"
            'ф' -> "f"
            'х' -> "h"
            'ц' -> "c"
            'ч' -> "ch"
            'ш' -> "sh"
            'щ' -> "sh'"
            'ъ', 'ь' -> ""
            'ю' -> "yu"
            'я' -> "ya"
            ' ' -> divider
            else -> it.toString()
        }
        if (isUpperCase) letter.capitalize() else letter
    }.joinToString("")

    fun toInitials(firstName: String?, lastName: String? = ""): String? {
        val firstNameLetter: Char? = firstName?.trim()?.getOrNull(0)?.toUpperCase()
        val lastNameLetter: Char? = lastName?.trim()?.getOrNull(0)?.toUpperCase()
        return if (firstNameLetter == null && lastNameLetter == null) null else "${firstNameLetter
            ?: ""}${lastNameLetter ?: ""}"
    }
}