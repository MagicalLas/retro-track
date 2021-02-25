package retrospective.adapter

import arrow.core.Either
import retrospective.domain.Retrospective
import retrospective.domain.RetrospectiveNotFoundError

class InMemoryRetrospectiveRepository {
    fun findById(id: Long): Either<Retrospective, RetrospectiveNotFoundError> {
        return Either.left(Retrospective(id, "", ""))
    }

    fun save(retro: Retrospective) {

    }
}
