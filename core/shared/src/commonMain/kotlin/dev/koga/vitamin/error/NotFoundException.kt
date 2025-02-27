package dev.koga.vitamin.error

class NotFoundException(
    override val message: String
) : Error(message)