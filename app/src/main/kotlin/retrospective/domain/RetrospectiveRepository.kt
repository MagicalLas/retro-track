package retrospective.domain

import arrow.core.Either

interface RetrospectiveRepository {
    fun findById(id: Long): Either<Retrospective, RetrospectiveNotFoundError>
    fun findByUserName(userName: String): List<Retrospective>
    fun save(retro: Retrospective)
    fun nextId(): Long
}