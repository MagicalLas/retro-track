package retrospective.application

import arrow.core.Either
import retrospective.adapter.InMemoryRetrospectiveRepository
import retrospective.domain.Retrospective
import retrospective.domain.RetrospectiveNotFoundError

class RetrospectiveApplicationService(
        private val repository: InMemoryRetrospectiveRepository
) {
    fun logRetrospective(userName: String, memo: String): Retrospective {
        val retro = Retrospective(0, userName, memo)
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
