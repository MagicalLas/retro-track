package retrospective.application

import arrow.core.Either
import retrospective.domain.Retrospective
import retrospective.domain.RetrospectiveNotFoundError
import retrospective.domain.RetrospectiveRepository

class RetrospectiveApplicationService(
        private val repository: RetrospectiveRepository
) {
    fun logRetrospective(userName: String, memo: String): Retrospective {
        val retro = Retrospective(repository.nextId(), userName, memo)
        repository.save(retro)
        return retro
    }

    fun findSpecificRetrospective(id: Long): Either<Retrospective, RetrospectiveNotFoundError> {
        return repository.findById(id)
    }

    fun findMyAllRetrospective(userName: String): List<Retrospective> {
        return repository.findByUserName(userName)
    }
}
