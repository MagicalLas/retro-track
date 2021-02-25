package retrospective.application

import arrow.core.Either
import retrospective.domain.Retrospective
import retrospective.domain.RetrospectiveNotFoundError

class RetrospectiveApplicationService {
    fun logRetrospective(userName: String, memo: String): Retrospective {
        return Retrospective()
    }

    fun findSpecificRetrospective(id: Long): Either<Retrospective, RetrospectiveNotFoundError> {
        return Either.left(Retrospective())
    }
}
