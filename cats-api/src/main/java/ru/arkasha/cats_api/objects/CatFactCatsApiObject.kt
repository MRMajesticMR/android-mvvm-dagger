package ru.arkasha.cats_api.objects

class CatFactCatsApiObject(
    val status: Status,
    val type: String,
    val deleted: Boolean,
    val text: String,
    val createdAt: String,
    val updatedAt: String,
    val used: Boolean
) {

    class Status(
        val verified: Boolean,
        val sentCount: Int,
        val feedback: String?,
    ) {

        override fun toString(): String {
            return "Status(verified=$verified, sentCount=$sentCount, feedback=$feedback)"
        }
    }

    override fun toString(): String {
        return "CatFactCatsApiObject(status=$status, type='$type', deleted=$deleted, text='$text', createdAt='$createdAt', updatedAt='$updatedAt', used=$used)"
    }


}

