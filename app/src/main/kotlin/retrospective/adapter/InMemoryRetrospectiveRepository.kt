package retrospective.adapter

import arrow.core.Either
import arrow.core.rightIfNotNull
import retrospective.domain.Retrospective
import retrospective.domain.RetrospectiveNotFoundError

class InMemoryRetrospectiveRepository {
    private val inMemoryCache: MutableMap<Long, Retrospective> = mutableMapOf()

    fun findById(id: Long): Either<Retrospective, RetrospectiveNotFoundError> {
        return inMemoryCache[id].rightIfNotNull { RetrospectiveNotFoundError(id) }.swap()
    }

    fun save(retro: Retrospective) {
        inMemoryCache[retro.id] = retro
    }

    fun findByUserName(userName: String): List<Retrospective> {
        return inMemoryCache.values.filter {
            it.userName == userName
        }
    }
}
